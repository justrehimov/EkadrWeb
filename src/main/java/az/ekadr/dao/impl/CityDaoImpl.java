package az.ekadr.dao.impl;

import az.ekadr.dao.CityDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl implements CityDao {
    @Override
    public List<City> getAllCity() {
        List<City> cityList = new ArrayList<>();
        String sql = "SELECT * FROM CITY WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                City city = new City();
                city.setId(rs.getLong("ID"));
                city.setCity(rs.getString("CITY"));
                city.setActive(rs.getInt("ACTIVE"));
                cityList.add(city);
            }
            return cityList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public City getCityById(Long cityId) {
        City city = new City();
        String sql = "SELECT * FROM CITY WHERE ACTIVE = 1 AND ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,cityId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                city.setId(rs.getLong("ID"));
                city.setActive(rs.getInt("ACTIVE"));
                city.setCity(rs.getString("CITY"));
            }
            return city;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCity(City newCity) {
        String sql = "INSERT INTO CITY(CITY,ACTIVE) VALUES(?,?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newCity.getCity());
            ps.setInt(2,newCity.getActive());
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCity(City newCity, Long cityId) {
        String sql = "UPDATE CITY SET CITY = ?,ACTIVE = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newCity.getCity());
            ps.setInt(2,newCity.getActive());
            ps.setLong(3,cityId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteCityById(Long cityId) {
        String sql = "DELETE FROM CITY WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,cityId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
