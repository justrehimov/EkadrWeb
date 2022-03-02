package az.ekadr.dao;

import az.ekadr.entites.Education;

import java.util.List;

public interface EducationDao {

    public List<Education> getAllEducation();
    public Education getEducationById(Long educationId);
    public void addEducation(Education newEducation);
    public void updateEducation(Education newEducation,Long educationId);
    public void deleteEducationById(Long educationId);
}
