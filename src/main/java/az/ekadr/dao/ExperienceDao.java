package az.ekadr.dao;

import az.ekadr.entites.Experience;

import java.util.List;

public interface ExperienceDao {

    public List<Experience> getAllExperience();
    public Experience getExperienceById(Long experienceId);
    public void addExperience(Experience newExperience);
    public void updateExperience(Experience newExperience,Long experienceId);
    public void deleteExperienceById(Long experienceId);

}
