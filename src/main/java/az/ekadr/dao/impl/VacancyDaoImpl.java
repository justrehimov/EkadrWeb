package az.ekadr.dao.impl;

import az.ekadr.dao.VacancyDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.Company;
import az.ekadr.entites.Vacancy;
import az.ekadr.entites.Workmode;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VacancyDaoImpl implements VacancyDao {
    public List<Vacancy> getAllVacancy() {
        List<Vacancy> vacancyList = new ArrayList<>();
        String sql = "SELECT * FROM VACANCY WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Vacancy vacancy = new Vacancy();
                vacancy.setId(rs.getLong("ID"));
                vacancy.setVacancyName(rs.getString("VACANCY_NAME"));
                vacancy.setInformation(rs.getString("INFORMATION"));
                vacancy.setRequirements(rs.getString("REQUIREMENTS"));
                vacancy.setAddress(rs.getString("ADDRESS"));
                vacancy.setAgeId(new AgeDaoImpl().getAgeById(rs.getLong("AGE_ID")));
                vacancy.setCategoryId(new CategoryDaoImpl().getCategoryById(rs.getLong("CATEGORY_ID")));
                vacancy.setCompanyId(new CompanyDaoImpl().getCompanyById(rs.getLong("COMPANY_ID")));
                vacancy.setExpDate(rs.getDate("EXP_DATE"));
                vacancy.setEducationId(new EducationDaoImpl().getEducationById(rs.getLong("EDUCATION_ID")));
                vacancy.setActive(rs.getInt("ACTIVE"));
                vacancy.setDataDate(rs.getDate("DATA_DATE"));
                vacancy.setSalary(rs.getString("SALARY"));
                vacancy.setWorkmodeId(new WorkmodeDaoImpl().getWorkmodeById(rs.getLong("WORKMODE_ID")));
                vacancy.setExperienceId(new ExperienceDaoImpl().getExperienceById(rs.getLong("EXPERIENCE_ID")));
                vacancyList.add(vacancy);
            }
            return vacancyList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Vacancy> getAllVacancyByCompanyId(Long companyId) {

        List<Vacancy> vacancyList = new ArrayList<>();
        String sql = "SELECT * FROM VACANCY WHERE ACTIVE = 1 AND COMPANY_ID = ?";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,companyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vacancy vacancy = new Vacancy();
                vacancy.setId(rs.getLong("ID"));
                vacancy.setVacancyName(rs.getString("VACANCY_NAME"));
                vacancy.setInformation(rs.getString("INFORMATION"));
                vacancy.setRequirements(rs.getString("REQUIREMENTS"));
                vacancy.setAddress(rs.getString("ADDRESS"));
                vacancy.setAgeId(new AgeDaoImpl().getAgeById(rs.getLong("AGE_ID")));
                vacancy.setCategoryId(new CategoryDaoImpl().getCategoryById(rs.getLong("CATEGORY_ID")));
                vacancy.setCompanyId(new CompanyDaoImpl().getCompanyById(rs.getLong("COMPANY_ID")));
                vacancy.setExpDate(rs.getDate("EXP_DATE"));
                vacancy.setEducationId(new EducationDaoImpl().getEducationById(rs.getLong("EDUCATION_ID")));
                vacancy.setActive(rs.getInt("ACTIVE"));
                vacancy.setDataDate(rs.getDate("DATA_DATE"));
                vacancy.setSalary(rs.getString("SALARY"));
                vacancy.setWorkmodeId(new WorkmodeDaoImpl().getWorkmodeById(rs.getLong("WORKMODE_ID")));
                vacancy.setExperienceId(new ExperienceDaoImpl().getExperienceById(rs.getLong("EXPERIENCE_ID")));
                vacancyList.add(vacancy);
            }
            return vacancyList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Vacancy> getVacancyByName(String vacancyName) {
        List<Vacancy> vacancyList = new ArrayList<>();
        String sql = "SELECT * FROM VACANCY WHERE ACTIVE = 1 AND VACANCY_NAME LIKE ?";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,"%" + vacancyName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vacancy vacancy = new Vacancy();
                vacancy.setId(rs.getLong("ID"));
                vacancy.setVacancyName(rs.getString("VACANCY_NAME"));
                vacancy.setInformation(rs.getString("INFORMATION"));
                vacancy.setRequirements(rs.getString("REQUIREMENTS"));
                vacancy.setAddress(rs.getString("ADDRESS"));
                vacancy.setAgeId(new AgeDaoImpl().getAgeById(rs.getLong("AGE_ID")));
                vacancy.setCategoryId(new CategoryDaoImpl().getCategoryById(rs.getLong("CATEGORY_ID")));
                vacancy.setCompanyId(new CompanyDaoImpl().getCompanyById(rs.getLong("COMPANY_ID")));
                vacancy.setExpDate(rs.getDate("EXP_DATE"));
                vacancy.setEducationId(new EducationDaoImpl().getEducationById(rs.getLong("EDUCATION_ID")));
                vacancy.setActive(rs.getInt("ACTIVE"));
                vacancy.setDataDate(rs.getDate("DATA_DATE"));
                vacancy.setSalary(rs.getString("SALARY"));
                vacancy.setWorkmodeId(new WorkmodeDaoImpl().getWorkmodeById(rs.getLong("WORKMODE_ID")));
                vacancy.setExperienceId(new ExperienceDaoImpl().getExperienceById(rs.getLong("EXPERIENCE_ID")));
                vacancyList.add(vacancy);
            }
            return vacancyList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Vacancy> searchVacancyByIdAndName(Long categoryId, Long experienceId, Long companyId, Long workmodeId,String name) {
        List<Vacancy> vacancyList = new ArrayList<>();
        String sql = "SELECT * FROM VACANCY WHERE ACTIVE = 1 AND ((COMPANY_ID = ? OR EXPERIENCE_ID = ? OR" +
                " CATEGORY_ID = ? OR WORKMODE_ID = ?) AND (LOWER(VACANCY_NAME) LIKE LOWER(?)" +
                " OR LOWER(REQUIREMENTS) LIKE LOWER(?)))";

        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,companyId);
            ps.setLong(2,experienceId);
            ps.setLong(3,categoryId);
            ps.setLong(4,workmodeId);
            ps.setString(5,"%" + name + "%");
            ps.setString(6,"%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vacancy vacancy = new Vacancy();
                vacancy.setId(rs.getLong("ID"));
                vacancy.setVacancyName(rs.getString("VACANCY_NAME"));
                vacancy.setInformation(rs.getString("INFORMATION"));
                vacancy.setRequirements(rs.getString("REQUIREMENTS"));
                vacancy.setAddress(rs.getString("ADDRESS"));
                vacancy.setAgeId(new AgeDaoImpl().getAgeById(rs.getLong("AGE_ID")));
                vacancy.setCategoryId(new CategoryDaoImpl().getCategoryById(rs.getLong("CATEGORY_ID")));
                vacancy.setCompanyId(new CompanyDaoImpl().getCompanyById(rs.getLong("COMPANY_ID")));
                vacancy.setExpDate(rs.getDate("EXP_DATE"));
                vacancy.setEducationId(new EducationDaoImpl().getEducationById(rs.getLong("EDUCATION_ID")));
                vacancy.setActive(rs.getInt("ACTIVE"));
                vacancy.setDataDate(rs.getDate("DATA_DATE"));
                vacancy.setSalary(rs.getString("SALARY"));
                vacancy.setWorkmodeId(new WorkmodeDaoImpl().getWorkmodeById(rs.getLong("WORKMODE_ID")));
                vacancy.setExperienceId(new ExperienceDaoImpl().getExperienceById(rs.getLong("EXPERIENCE_ID")));
                vacancyList.add(vacancy);
            }
            return vacancyList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Vacancy getVacancyById(Long vacancyId) {
        Vacancy vacancy = new Vacancy();
        String sql = "SELECT * FROM VACANCY WHERE ACTIVE = 1 AND ID = ?";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,vacancyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                vacancy.setId(rs.getLong("ID"));
                vacancy.setVacancyName(rs.getString("VACANCY_NAME"));
                vacancy.setInformation(rs.getString("INFORMATION"));
                vacancy.setRequirements(rs.getString("REQUIREMENTS"));
                vacancy.setAddress(rs.getString("ADDRESS"));
                vacancy.setAgeId(new AgeDaoImpl().getAgeById(rs.getLong("AGE_ID")));
                vacancy.setCategoryId(new CategoryDaoImpl().getCategoryById(rs.getLong("CATEGORY_ID")));
                vacancy.setCompanyId(new CompanyDaoImpl().getCompanyById(rs.getLong("COMPANY_ID")));
                vacancy.setExpDate(rs.getDate("EXP_DATE"));
                vacancy.setEducationId(new EducationDaoImpl().getEducationById(rs.getLong("EDUCATION_ID")));
                vacancy.setActive(rs.getInt("ACTIVE"));
                vacancy.setDataDate(rs.getDate("DATA_DATE"));
                vacancy.setSalary(rs.getString("SALARY"));
                vacancy.setWorkmodeId(new WorkmodeDaoImpl().getWorkmodeById(rs.getLong("WORKMODE_ID")));
                vacancy.setExperienceId(new ExperienceDaoImpl().getExperienceById(rs.getLong("EXPERIENCE_ID")));
            }
            return vacancy;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addVacancy(Vacancy newVacancy) {
        String sql = "INSERT INTO VACANCY (VACANCY_NAME, INFORMATION, REQUIREMENTS, SALARY, ADDRESS, AGE_ID, CATEGORY_ID, COMPANY_ID, EXPERIENCE_ID, EDUCATION_ID, WORKMODE_ID, ACTIVE, DATA_DATE, EXP_DATE) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newVacancy.getVacancyName());
            ps.setString(2,newVacancy.getInformation());
            ps.setString(3,newVacancy.getRequirements());
            ps.setString(4,newVacancy.getSalary());
            ps.setString(5,newVacancy.getAddress());
            ps.setLong(6,newVacancy.getAgeId().getId());
            ps.setLong(7,newVacancy.getCategoryId().getId());
            ps.setLong(8,newVacancy.getCompanyId().getId());
            ps.setLong(9,newVacancy.getExperienceId().getId());
            ps.setLong(10,newVacancy.getEducationId().getId());
            ps.setLong(11,newVacancy.getWorkmodeId().getId());
            ps.setInt(12,newVacancy.getActive());
            ps.setDate(13,new Date(newVacancy.getDataDate().getTime()));
            ps.setDate(14,new Date(newVacancy.getExpDate().getTime()));
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateVacancy(Vacancy newVacancy, Long vacancyId) {
        String sql = "UPDATE VACANCY SET VACANCY_NAME = ?, INFORMATION = ?, REQUIREMENTS = ?, SALARY = ?, ADDRESS = ?, AGE_ID = ?, CATEGORY_ID = ?, COMPANY_ID = ?, EXPERIENCE_ID = ?, EDUCATION_ID, WORKMODE_ID = ?, ACTIVE = ?, DATA_DATE = ?, EXP_DATE = ? WHERE ID = ?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newVacancy.getVacancyName());
            ps.setString(2,newVacancy.getInformation());
            ps.setString(3,newVacancy.getRequirements());
            ps.setString(4,newVacancy.getSalary());
            ps.setString(5,newVacancy.getAddress());
            ps.setLong(6,newVacancy.getAgeId().getId());
            ps.setLong(7,newVacancy.getCategoryId().getId());
            ps.setLong(8,newVacancy.getCompanyId().getId());
            ps.setLong(9,newVacancy.getExperienceId().getId());
            ps.setLong(10,newVacancy.getEducationId().getId());
            ps.setLong(11,newVacancy.getWorkmodeId().getId());
            ps.setInt(12,newVacancy.getActive());
            ps.setDate(13,new Date(newVacancy.getDataDate().getTime()));
            ps.setDate(14,new Date(newVacancy.getExpDate().getTime()));
            ps.setLong(15,vacancyId);
            ps.executeUpdate();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteVacancyById(Long vacancyId) {
        String sql = "UPDATE VACANCY SET ACTIVE = 0 WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,vacancyId);
            ps.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Vacancy> getVacanciesByCategoryId(Long companyId) {
        List<Vacancy> vacancyList = new ArrayList<>();
        String sql = "SELECT * FROM VACANCY WHERE ACTIVE = 1 AND CATEGORY_ID = ?";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,companyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vacancy vacancy = new Vacancy();
                vacancy.setId(rs.getLong("ID"));
                vacancy.setVacancyName(rs.getString("VACANCY_NAME"));
                vacancy.setInformation(rs.getString("INFORMATION"));
                vacancy.setRequirements(rs.getString("REQUIREMENTS"));
                vacancy.setAddress(rs.getString("ADDRESS"));
                vacancy.setAgeId(new AgeDaoImpl().getAgeById(rs.getLong("AGE_ID")));
                vacancy.setCategoryId(new CategoryDaoImpl().getCategoryById(rs.getLong("CATEGORY_ID")));
                vacancy.setCompanyId(new CompanyDaoImpl().getCompanyById(rs.getLong("COMPANY_ID")));
                vacancy.setExpDate(rs.getDate("EXP_DATE"));
                vacancy.setEducationId(new EducationDaoImpl().getEducationById(rs.getLong("EDUCATION_ID")));
                vacancy.setActive(rs.getInt("ACTIVE"));
                vacancy.setDataDate(rs.getDate("DATA_DATE"));
                vacancy.setSalary(rs.getString("SALARY"));
                vacancy.setWorkmodeId(new WorkmodeDaoImpl().getWorkmodeById(rs.getLong("WORKMODE_ID")));
                vacancy.setExperienceId(new ExperienceDaoImpl().getExperienceById(rs.getLong("EXPERIENCE_ID")));
                vacancyList.add(vacancy);
            }
            return vacancyList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Vacancy> getVacanciesByIds(Long categoryId, Long companyId, Long experienceId, Long workmodeId) {
        List<Vacancy> vacancyList = new ArrayList<>();
        String sql = "SELECT * FROM VACANCY WHERE ACTIVE = 1 AND (COMPANY_ID = ? OR EXPERIENCE_ID = ? OR" +
                " CATEGORY_ID = ? OR WORKMODE_ID = ?)";

        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,companyId);
            ps.setLong(2,experienceId);
            ps.setLong(3,categoryId);
            ps.setLong(4,workmodeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vacancy vacancy = new Vacancy();
                vacancy.setId(rs.getLong("ID"));
                vacancy.setVacancyName(rs.getString("VACANCY_NAME"));
                vacancy.setInformation(rs.getString("INFORMATION"));
                vacancy.setRequirements(rs.getString("REQUIREMENTS"));
                vacancy.setAddress(rs.getString("ADDRESS"));
                vacancy.setAgeId(new AgeDaoImpl().getAgeById(rs.getLong("AGE_ID")));
                vacancy.setCategoryId(new CategoryDaoImpl().getCategoryById(rs.getLong("CATEGORY_ID")));
                vacancy.setCompanyId(new CompanyDaoImpl().getCompanyById(rs.getLong("COMPANY_ID")));
                vacancy.setExpDate(rs.getDate("EXP_DATE"));
                vacancy.setEducationId(new EducationDaoImpl().getEducationById(rs.getLong("EDUCATION_ID")));
                vacancy.setActive(rs.getInt("ACTIVE"));
                vacancy.setDataDate(rs.getDate("DATA_DATE"));
                vacancy.setSalary(rs.getString("SALARY"));
                vacancy.setWorkmodeId(new WorkmodeDaoImpl().getWorkmodeById(rs.getLong("WORKMODE_ID")));
                vacancy.setExperienceId(new ExperienceDaoImpl().getExperienceById(rs.getLong("EXPERIENCE_ID")));
                vacancyList.add(vacancy);
            }
            return vacancyList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int getVacancySize() {
        String sql = "SELECT COUNT(ID) AS COUNT FROM VACANCY WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql);ResultSet rs = ps.executeQuery()){
            if(rs.next()){
                return rs.getInt("COUNT");
            }
            else{
                return 0;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
}
