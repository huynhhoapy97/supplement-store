package com.huynhhoapy97.repositories.admin;

import com.huynhhoapy97.models.Category;

import java.util.List;

public interface CategoryDAO {
    boolean save(Category category);
    List<Category> getAll();
    List<Category> getAllWithoutCommodity();
    Category getById(Integer categoryId);
    boolean update(Category category);
    void fillCommodity(int commodityId, int categoryId);
    List<Category> getByCommodityId(Integer commodityId);
    void refreshCommodity(int categoryId);
}
