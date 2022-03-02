package az.ekadr.dao;

public interface CompanyOperationDao {
    public Long login(String email,String password);
    public void confirmAccount(String email);
    public void changePassword(String password,String email);
    public Long existsCompany(String email);
}