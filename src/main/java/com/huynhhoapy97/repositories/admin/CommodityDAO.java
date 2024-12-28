package com.huynhhoapy97.repositories.admin;

import com.huynhhoapy97.models.Commodity;

import java.util.List;

public interface CommodityDAO {
    List<Commodity> getAll();
    int save(Commodity commodity);
    Commodity getById(Integer commodityId);
    boolean update(Commodity commodity);
}
