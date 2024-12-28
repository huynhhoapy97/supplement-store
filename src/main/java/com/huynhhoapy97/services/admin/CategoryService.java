package com.huynhhoapy97.services.admin;

import com.huynhhoapy97.models.Category;
import com.huynhhoapy97.repositories.admin.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public boolean save(Category category) {
        return categoryDAO.save(category);
    }

    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    public List<Category> getAllWithoutCommodity() {
        return categoryDAO.getAllWithoutCommodity();
    }

    public Category getById(Integer categoryId) {
        return categoryDAO.getById(categoryId);
    }

    public boolean update(Category category) {
        Category categoryExisting = updateCategoryExisting(category);

        return categoryDAO.update(categoryExisting);
    }

    public boolean delete(Integer categoryId) {
        Category category = categoryDAO.getById(categoryId);
        category.setIsDeleted(1);
        category.setDeletedDay(new Date());

        return categoryDAO.update(category);
    }

    private Category updateCategoryExisting(Category category) {
        Category categoryExisting = categoryDAO.getById(category.getId());
        categoryExisting.setName(category.getName());
        categoryExisting.setDescription(category.getDescription());
        categoryExisting.setUpdatedDay(category.getUpdatedDay());
        if (category.getCoverPhoto() != null && !category.getCoverPhoto().isEmpty()) {
            categoryExisting.setCoverPhoto(category.getCoverPhoto());
        }

        return categoryExisting;
    }

    public void fillCommodity(int commodityId, int categoryId) {
        categoryDAO.fillCommodity(commodityId, categoryId);
    }

    public List<Category> getByCommodityId(Integer commodityId) {
        return categoryDAO.getByCommodityId(commodityId);
    }

    public void refreshCommodity(int categoryId) {
        categoryDAO.refreshCommodity(categoryId);
    }
}
