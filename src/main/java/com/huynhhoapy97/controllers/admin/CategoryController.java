package com.huynhhoapy97.controllers.admin;

import com.huynhhoapy97.entities.ImageProperties;
import com.huynhhoapy97.enums.PathUtils;
import com.huynhhoapy97.enums.StatusNotificationUtils;
import com.huynhhoapy97.enums.ViewPageUtils;
import com.huynhhoapy97.models.Category;
import com.huynhhoapy97.services.admin.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    private final ServletContext context;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(ServletContext context, CategoryService categoryService) {
        this.context = context;
        this.categoryService = categoryService;
    }

    @ResponseBody
    @PostMapping("save")
    public ResponseEntity<String> save(@RequestParam("categoryName")
                                       String categoryName,
                                       @RequestParam("categoryDescription")
                                       String categoryDescription,
                                       @RequestParam(value = "categoryCoverPhoto", required = false)
                                       MultipartFile categoryCoverPhoto) {
        Category category = new Category();

        try {
            if (categoryCoverPhoto != null) {
                String fileName = categoryCoverPhoto.getOriginalFilename();
                if (fileName == null) {
                    fileName = "default-category.jpg";
                }
                String specificPath = PathUtils.CATEGORY_UPLOADS.getName().concat(fileName);
                String fullPath = context.getRealPath(specificPath);

                categoryCoverPhoto.transferTo(new File(fullPath));
                category.setCoverPhoto(specificPath);
            }

            category.setName(categoryName);
            category.setDescription(categoryDescription);
            category.setCreatedDay(new Date());
            category.setIsDeleted(0);

            boolean isSuccess = categoryService.save(category);
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
    @PutMapping("update")
    public ResponseEntity<String> update(@RequestParam("categoryId")
                                         Integer categoryId,
                                         @RequestParam("categoryName")
                                         String categoryName,
                                         @RequestParam("categoryDescription")
                                         String categoryDescription,
                                         @RequestParam(value = "categoryCoverPhoto", required = false)
                                         MultipartFile categoryCoverPhoto) {
        Category category = new Category();

        try {
            if (categoryCoverPhoto != null) {
                String fileName = categoryCoverPhoto.getOriginalFilename();
                if (fileName == null) {
                    fileName = "default-category.jpg";
                }
                String specificPath = PathUtils.CATEGORY_UPLOADS.getName().concat(fileName);
                String fullPath = context.getRealPath(specificPath);

                categoryCoverPhoto.transferTo(new File(fullPath));
                category.setCoverPhoto(specificPath);
            }

            category.setId(categoryId);
            category.setName(categoryName);
            category.setDescription(categoryDescription);
            category.setUpdatedDay(new Date());

            boolean isSuccess = categoryService.update(category);
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
    @PutMapping("delete")
    public ResponseEntity<String> delete(@RequestParam("category_id") Integer categoryId) {
        try {
            boolean isSuccess = categoryService.delete(categoryId);
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
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = new ArrayList<>();
        try {
            categories = categoryService.getAll();
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(categories);
        }

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @ResponseBody
    @GetMapping("get-all-without-commodity")
    public ResponseEntity<List<Category>> getAllWithoutCommodity() {
        List<Category> categories = new ArrayList<>();
        try {
            categories = categoryService.getAllWithoutCommodity();
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(categories);
        }

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @ResponseBody
    @GetMapping("get-by-id")
    public ResponseEntity<Category> getById(@RequestParam("category_id") Integer categoryId) {
        Category category = null;
        try {
            category = categoryService.getById(categoryId);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(category);
        }

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @ResponseBody
    @GetMapping("get-by-commodity-id")
    public ResponseEntity<List<Category>> getByCommodityId(@RequestParam("commodity_id") Integer commodityId) {
        List<Category> categories = new ArrayList<>();
        try {
            categories = categoryService.getByCommodityId(commodityId);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(categories);
        }

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @ResponseBody
    @PostMapping("file-upload")
    public ResponseEntity<String> fileUpload(@RequestParam("files") MultipartFile[] files) {

        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                String path = context.getRealPath(PathUtils.CATEGORY_UPLOADS.getName().concat(fileName));
                file.transferTo(new File(path));
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(StatusNotificationUtils.DATA_MANIPULATION_ERROR.getName());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(StatusNotificationUtils.DATA_MANIPULATION_SUCCESSFULLY.getName());
    }

    @GetMapping("file-browse")
    public String fileBrowse(ModelMap modelMap) {
        List<ImageProperties> imageProperties = new ArrayList<>();
        File folder = new File(context.getRealPath(PathUtils.CATEGORY_UPLOADS.getName()));
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                try {
                    BufferedImage image = ImageIO.read(file);
                    int width = image.getWidth();
                    int height = image.getHeight();
                    String name = file.getName();

                    ImageProperties properties = new ImageProperties(name, width, height);
                    imageProperties.add(properties);
                } catch (IOException e) {
                    log.info(e.getMessage());
                }
            }
        }

        modelMap.put("imageProperties", imageProperties);

        return ViewPageUtils.CATEGORY_FILE_BROWSE.getName();
    }
}
