package az.ekadr.dao;

import az.ekadr.entites.City;

import java.util.List;

public interface CityDao {

    public List<City> getAllCity();
    public City getCityById(Long cityId);
    public void addCity(City newCity);
    public void updateCity(City newCity,Long cityId);
    public void deleteCityById(Long cityId);
}
