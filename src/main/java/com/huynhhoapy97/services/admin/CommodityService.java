package com.huynhhoapy97.services.admin;

import com.huynhhoapy97.models.Commodity;
import com.huynhhoapy97.repositories.admin.CommodityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommodityService {
    private final CommodityDAO commodityDAO;

    @Autowired
    public CommodityService(CommodityDAO commodityDAO) {
        this.commodityDAO = commodityDAO;
    }

    public List<Commodity> getAll() {
        return commodityDAO.getAll();
    }

    public int save(Commodity commodity) {
        return commodityDAO.save(commodity);
    }

    public Commodity getById(Integer commodityId) {
        return commodityDAO.getById(commodityId);
    }

    public boolean delete(Integer commodityId) {
        Commodity commodity = commodityDAO.getById(commodityId);
        commodity.setIsDeleted(1);
        commodity.setDeletedDay(new Date());

        return commodityDAO.update(commodity);
    }

    public boolean update(Commodity commodity) {
        Commodity commodityExisting = updateCommodityExisting(commodity);

        return commodityDAO.update(commodityExisting);
    }

    private Commodity updateCommodityExisting(Commodity commodity) {
        Commodity commodityExisting = commodityDAO.getById(commodity.getId());
        commodityExisting.setName(commodity.getName());
        commodityExisting.setUpdatedDay(commodity.getUpdatedDay());

        return commodityExisting;
    }
}
