package az.ekadr.dao.impl;

import az.ekadr.dao.EducationDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.Education;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EducationDaoImpl implements EducationDao {
    @Override
    public List<Education> getAllEducation() {
        List<Education> educationList = new ArrayList<>();
        String sql = "SELECT * FROM EDUCATION WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Education education = new Education();
                education.setId(rs.getLong("ID"));
                education.setEducation(rs.getString("EDUCATION"));
                education.setActive(rs.getInt("ACTIVE"));
                educationList.add(education);
            }
            return educationList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Education getEducationById(Long educationId) {
        Education education = new Education();
        String sql = "SELECT * FROM EDUCATION WHERE ACTIVE = 1 AND ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,educationId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                education.setId(rs.getLong("ID"));
                education.setActive(rs.getInt("ACTIVE"));
                education.setEducation(rs.getString("EDUCATION"));
            }
            return education;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addEducation(Education newEducation) {
        String sql = "INSERT INTO EDUCATION(EDUCATION,ACTIVE) VALUES(?,?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newEducation.getEducation());
            ps.setInt(2,newEducation.getActive());
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateEducation(Education newEducation,Long educationId) {
        String sql = "UPDATE EDUCATION SET EDUCATION = ?,ACTIVE = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newEducation.getEducation());
            ps.setInt(2,newEducation.getActive());
            ps.setLong(3,educationId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEducationById(Long educationId) {
        String sql = "DELETE FROM EDUCATION WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,educationId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
