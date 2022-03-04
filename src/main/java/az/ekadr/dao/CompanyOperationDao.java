package az.ekadr.dao;

public interface CompanyOperationDao {
    public Long login(String email,String password);
    public void confirmAccount(String email);
    public void changePassword(String password,String email);
    public Long existsCompany(String email);
    public boolean withdrawBalance(Long companyId,Float amount);
    public void buyPacket(Long companyId,Long packetId);
    public boolean decreaseCountAd(Long companyId,Integer countAd);
}
