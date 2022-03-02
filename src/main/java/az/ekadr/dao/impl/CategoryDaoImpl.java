package az.ekadr.dao.impl;

import az.ekadr.dao.CategoryDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> getAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORY WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Category category = new Category();
                category.setId(rs.getLong("ID"));
                category.setCategory(rs.getString("CATEGORY"));
                category.setActive(rs.getInt("ACTIVE"));
                categoryList.add(category);
            }
            return categoryList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Category category = new Category();
        String sql = "SELECT * FROM CATEGORY WHERE ACTIVE = 1 AND ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,categoryId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                category.setId(rs.getLong("ID"));
                category.setActive(rs.getInt("ACTIVE"));
                category.setCategory(rs.getString("CATEGORY"));
            }
            return category;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCategory(Category newCategory) {
        String sql = "INSERT INTO CATEGORY(CATEGORY,ACTIVE) VALUES(?,?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newCategory.getCategory());
            ps.setInt(2,newCategory.getActive());
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCategory(Category newCategory, Long categoryId) {
        String sql = "UPDATE CATEGORY SET CATEGORY = ?,ACTIVE = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newCategory.getCategory());
            ps.setInt(2,newCategory.getActive());
            ps.setLong(3,categoryId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAgeById(Long categoryId) {
        String sql = "DELETE FROM CATEGORY WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,categoryId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
