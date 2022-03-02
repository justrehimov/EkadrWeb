package az.ekadr.dao.impl;

import az.ekadr.dao.AgeDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.Age;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AgeDaoImpl implements AgeDao {
    @Override
    public List<Age> getAllList() {
        List<Age> ageList = new ArrayList<>();
        String sql = "SELECT * FROM AGE WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Age age = new Age();
                age.setId(rs.getLong("ID"));
                age.setAge(rs.getString("AGE"));
                age.setActive(rs.getInt("ACTIVE"));
                ageList.add(age);
            }
            return ageList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Age getAgeById(Long ageId) {
        Age age = new Age();
        String sql = "SELECT * FROM AGE WHERE ACTIVE = 1 AND ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,ageId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                age.setId(rs.getLong("ID"));
                age.setActive(rs.getInt("ACTIVE"));
                age.setAge(rs.getString("AGE"));
            }
            return age;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addAge(Age newAge) {
        String sql = "INSERT INTO AGE(AGE,ACTIVE) VALUES(?,?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newAge.getAge());
            ps.setInt(2,newAge.getActive());
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateAge(Age newAge, Long ageId) {
        String sql = "UPDATE AGE SET AGE = ?,ACTIVE = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newAge.getAge());
            ps.setInt(2,newAge.getActive());
            ps.setLong(3,ageId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAgeById(Long ageId) {
        String sql = "DELETE FROM AGE WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,ageId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
