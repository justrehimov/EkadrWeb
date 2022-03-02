package az.ekadr.dao;

import az.ekadr.entites.Company;

import java.util.List;

public interface CompanyDao {

    public List<Company> getAllCompany();
    public Company getCompanyById(Long companyId);
    public void addCompany(Company newCompany);
    public void updateCompany(Company newCompany,Long companyId);
    public void deleteCompanyById(Long companyId);

}
