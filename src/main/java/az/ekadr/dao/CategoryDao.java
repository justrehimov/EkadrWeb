package az.ekadr.dao;

import az.ekadr.entites.Category;

import java.util.List;

public interface CategoryDao {

    public List<Category> getAllCategory();
    public Category getCategoryById(Long categoryId);
    public void addCategory(Category newCategory);
    public void updateCategory(Category newCategory,Long categoryId);
    public void deleteAgeById(Long categoryId);
}
