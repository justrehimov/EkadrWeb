package az.ekadr.dao;

import az.ekadr.entites.Age;

import java.util.List;

public interface AgeDao {

    public List<Age> getAllList();
    public Age getAgeById(Long ageId);
    public void addAge(Age newAge);
    public void updateAge(Age newAge,Long ageId);
    public void deleteAgeById(Long ageId);
}
