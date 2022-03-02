package az.ekadr.dao.impl;

import az.ekadr.dao.ExperienceDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.Experience;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDaoImpl implements ExperienceDao {
    @Override
    public List<Experience> getAllExperience() {
        List<Experience> educationList = new ArrayList<>();
        String sql = "SELECT * FROM EXPERIENCE WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Experience experience = new Experience();
                experience.setId(rs.getLong("ID"));
                experience.setExperience(rs.getString("EXPERIENCE"));
                experience.setActive(rs.getInt("ACTIVE"));
                educationList.add(experience);
            }
            return educationList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Experience getExperienceById(Long experienceId) {
        Experience experience = new Experience();
        String sql = "SELECT * FROM EXPERIENCE WHERE ACTIVE = 1 AND ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,experienceId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                experience.setId(rs.getLong("ID"));
                experience.setActive(rs.getInt("ACTIVE"));
                experience.setExperience(rs.getString("EXPERIENCE"));
            }
            return experience;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addExperience(Experience newExperience) {
        String sql = "INSERT INTO EXPERIENCE(EXPERIENCE,ACTIVE) VALUES(?,?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newExperience.getExperience());
            ps.setInt(2,newExperience.getActive());
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateExperience(Experience newExperience, Long experienceId) {
        String sql = "UPDATE EXPERIENCE SET EXPERIENCE = ?,ACTIVE = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newExperience.getExperience());
            ps.setInt(2,newExperience.getActive());
            ps.setLong(3,experienceId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteExperienceById(Long experienceId) {
        String sql = "DELETE FROM EXPERIENCE WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,experienceId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
