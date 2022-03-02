package az.ekadr.dao;

import az.ekadr.entites.Vacancy;

import java.util.List;

public interface VacancyDao {

    public List<Vacancy> getAllVacancy();
    public List<Vacancy> getAllVacancyByCompanyId(Long companyId);
    public List<Vacancy> getVacancyByName(String vacancyName);
    public List<Vacancy> searchVacancyByIds(Long categoryId,Long experienceId,Long cityId,Long workmodeId,String name);
    public Vacancy getVacancyById(Long vacancyId);
    public void addVacancy(Vacancy newVacancy);
    public void updateVacancy(Vacancy newVacancy,Long vacancyId);
    public void deleteVacancyById(Long vacancyId);

}
