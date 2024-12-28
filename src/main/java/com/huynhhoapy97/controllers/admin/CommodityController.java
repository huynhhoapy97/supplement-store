package com.huynhhoapy97.controllers.admin;

import com.huynhhoapy97.entities.CommodityDetails;
import com.huynhhoapy97.enums.StatusNotificationUtils;
import com.huynhhoapy97.models.Category;
import com.huynhhoapy97.models.Commodity;
import com.huynhhoapy97.services.admin.CategoryService;
import com.huynhhoapy97.services.admin.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("commodity")
public class CommodityController {
    private static final Logger log = LoggerFactory.getLogger(CommodityController.class);
    private final CommodityService commodityService;
    private final CategoryService categoryService;

    @Autowired
    public CommodityController(CommodityService commodityService, CategoryService categoryService) {
        this.commodityService = commodityService;
        this.categoryService = categoryService;
    }

    @ResponseBody
    @PostMapping("save")
    public ResponseEntity<String> save(@RequestParam("commodityName") String commodityName,
                                       @RequestParam("categoryIds") int[] categoryIds) {
        Commodity commodity = new Commodity();

        try {
            commodity.setName(commodityName);
            commodity.setCreatedDay(new Date());
            commodity.setIsDeleted(0);

            int commodityId = commodityService.save(commodity);
            if (commodityId != -1) {
                updateCommodityForCategory(categoryIds, commodityId);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(StatusNotificationUtils.DATA_MANIPULATION_SUCCESSFULLY.getName());
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(StatusNotificationUtils.DATA_MANIPULATION_FAIL.getName());
            }
        } catch (Exception e) {
            log.info(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(StatusNotificationUtils.DATA_MANIPULATION_ERROR.getName());
        }
    }

    @ResponseBody
    @PutMapping("update")
    public ResponseEntity<String> update(@RequestParam("commodityId") Integer commodityId,
                                         @RequestParam("commodityName") String commodityName,
                                         @RequestParam("categoryIds") Integer[] updatedCategoryIds) {
        List<Integer> currentCategoryIds;
        Commodity commodity = new Commodity();

        try {
            currentCategoryIds = getCurrentCategoryIds(commodityId);
            if (checkEmptyUpdatedCategoryIds(updatedCategoryIds)) {
                // Xét commodity NULL cho cac category trong currentCategories
                refreshCommodity(currentCategoryIds);
            } else if (!checkDuplicateUpdatedCategoryIds(updatedCategoryIds, currentCategoryIds)) {
                for (int categoryId : updatedCategoryIds) {
                    if (currentCategoryIds.contains(categoryId)) {
                        // Xoa categoryId trong currentCategoryIds
                        currentCategoryIds.remove(Integer.valueOf(categoryId));
                    } else {
                        // Xet commodity cho categoryId nay
                        categoryService.fillCommodity(commodityId, categoryId);
                    }
                }

                // Xet commodity NULL cho cac categoryId trong currentCategoryIds
                refreshCommodity(currentCategoryIds);
            }

            commodity.setId(commodityId);
            commodity.setName(commodityName);
            commodity.setUpdatedDay(new Date());

            boolean isSuccess = commodityService.update(commodity);
            if (isSuccess) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(StatusNotificationUtils.DATA_MANIPULATION_SUCCESSFULLY.getName());
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(StatusNotificationUtils.DATA_MANIPULATION_FAIL.getName());
            }
        } catch (Exception e) {
            log.info(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(StatusNotificationUtils.DATA_MANIPULATION_ERROR.getName());
        }
    }

    @ResponseBody
    @GetMapping("get-all")
    public ResponseEntity<List<Commodity>> getAll() {
        List<Commodity> commodities = new ArrayList<>();
        try {
            commodities = commodityService.getAll();
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(commodities);
    }

    @ResponseBody
    @GetMapping("get-by-id")
    public ResponseEntity<CommodityDetails> getById(@RequestParam("commodity_id") Integer commodityId) {
        CommodityDetails commodityDetails = new CommodityDetails();

        try {
            Commodity commodity = commodityService.getById(commodityId);
            List<Category> selectedCategories = categoryService.getByCommodityId(commodityId);
            List<Category> allCategories = categoryService.getAllWithoutCommodity();
            allCategories.addAll(selectedCategories);

            commodityDetails.setId(commodityId);
            commodityDetails.setName(commodity.getName());
            commodityDetails.setSelectedCategories(selectedCategories);
            commodityDetails.setAllCategories(allCategories);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commodityDetails);
        }

        return ResponseEntity.status(HttpStatus.OK).body(commodityDetails);
    }

    @ResponseBody
    @PutMapping("delete")
    public ResponseEntity<String> delete(@RequestParam("commodity_id") Integer commodityId) {
        try {
            boolean isSuccess = commodityService.delete(commodityId);
            if (isSuccess) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(StatusNotificationUtils.DATA_MANIPULATION_SUCCESSFULLY.getName());
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(StatusNotificationUtils.DATA_MANIPULATION_FAIL.getName());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(StatusNotificationUtils.DATA_MANIPULATION_ERROR.getName());
        }
    }

    private void updateCommodityForCategory(int[] categoryIds, int commodityId) {
        for (int categoryId : categoryIds) {
            categoryService.fillCommodity(commodityId, categoryId);
        }
    }

    private List<Integer> getCurrentCategoryIds(int commodityId) {
        List<Category> categories = categoryService.getByCommodityId(commodityId);
        List<Integer> currentCategoryIds = new ArrayList<>();

        for (Category category : categories) {
            currentCategoryIds.add(category.getId());
        }

        return currentCategoryIds;
    }

    private boolean checkEmptyUpdatedCategoryIds(Integer[] updatedCategoryIds) {
        return updatedCategoryIds.length == 0;
    }

    private boolean checkDuplicateUpdatedCategoryIds(Integer[] updatedCategoryIds,
                                                     List<Integer> currentCategoryIds) {
        return currentCategoryIds.equals(Arrays.asList(updatedCategoryIds));
    }

    private void refreshCommodity(List<Integer> categoryIds) {
        if (!categoryIds.isEmpty()) {
            for (int categoryId : categoryIds){
                categoryService.refreshCommodity(categoryId);
            }
        }
    }
}
