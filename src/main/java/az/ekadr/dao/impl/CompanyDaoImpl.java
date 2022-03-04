package az.ekadr.dao.impl;

import az.ekadr.dao.CompanyDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.Company;
import az.ekadr.entites.Packet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    @Override
    public List<Company> getAllCompany() {
        List<Company> companyList = new ArrayList<>();
        String sql = "SELECT * FROM COMPANY WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Company company = new Company();
                company.setId(rs.getLong("ID"));
                company.setCompanyName(rs.getString("COMPANY_NAME"));
                company.setEmail(rs.getString("EMAIL"));
                company.setPhone(rs.getString("PHONE"));
                company.setPassword(rs.getString("PASSWORD"));
                company.setName(rs.getString("NAME"));
                company.setSurname(rs.getString("SURNAME"));
                company.setWebsite(rs.getString("WEBSITE"));
                company.setAboutCompany(rs.getString("ABOUT_COMPANY"));
                company.setLogo(rs.getBlob("LOGO"));
                company.setBalance(rs.getFloat("BALANCE"));
                company.setCityId(new CityDaoImpl().getCityById(rs.getLong("CITY_ID")));
                company.setDataDate(rs.getDate("DATA_DATE"));
                PacketDaoImpl pdi = new PacketDaoImpl();
                Packet p = pdi.getPacketById(rs.getLong("PACKET_ID"));
                company.setPacketId(p);
                company.setCount_ad(rs.getInt("COUNT_AD"));
                company.setVerified(rs.getInt("VERIFIED"));
                company.setActive(rs.getInt("ACTIVE"));
                companyList.add(company);
            }
            return companyList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Company getCompanyById(Long companyId) {
        Company company = new Company();
        String sql = "SELECT * FROM COMPANY WHERE ACTIVE = 1 AND ID = ?";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,companyId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                company.setId(rs.getLong("ID"));
                company.setCompanyName(rs.getString("COMPANY_NAME"));
                company.setEmail(rs.getString("EMAIL"));
                company.setPhone(rs.getString("PHONE"));
                company.setPassword(rs.getString("PASSWORD"));
                company.setName(rs.getString("NAME"));
                company.setSurname(rs.getString("SURNAME"));
                company.setWebsite(rs.getString("WEBSITE"));
                company.setAboutCompany(rs.getString("ABOUT_COMPANY"));
                company.setLogo(rs.getBlob("LOGO"));
                company.setBalance(rs.getFloat("BALANCE"));
                PacketDaoImpl pdi = new PacketDaoImpl();
                Packet p = pdi.getPacketById(rs.getLong("PACKET_ID"));
                company.setPacketId(p);
                company.setCount_ad(rs.getInt("COUNT_AD"));
                company.setCityId(new CityDaoImpl().getCityById(rs.getLong("CITY_ID")));
                company.setDataDate(rs.getDate("DATA_DATE"));
                company.setVerified(rs.getInt("VERIFIED"));
                company.setActive(rs.getInt("ACTIVE"));
            }
            return company;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCompany(Company newCompany) {
        String sql = "INSERT INTO COMPANY(ABOUT_COMPANY,ACTIVE,COMPANY_NAME,DATA_DATE,EMAIL,LOGO,NAME,PASSWORD,PHONE,SURNAME,VERIFIED,WEBSITE,CITY_ID,BALANCE)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, newCompany.getAboutCompany());
            ps.setInt(2,newCompany.getActive());
            ps.setString(3, newCompany.getCompanyName());
            ps.setDate(4,new Date(newCompany.getDataDate().getTime()));
            ps.setString(5, newCompany.getEmail());
            ps.setBlob(6,newCompany.getLogo());
            ps.setString(7, newCompany.getName());
            ps.setString(8, newCompany.getPassword());
            ps.setString(9, newCompany.getPhone());
            ps.setString(10, newCompany.getSurname());
            ps.setInt(11,newCompany.getVerified());
            ps.setString(12, newCompany.getWebsite());
            ps.setLong(13,newCompany.getCityId().getId());
            ps.setFloat(14,newCompany.getBalance());
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCompany(Company newCompany, Long companyId) {
        String sql = "UPDATE COMPANY SET ABOUT_COMPANY = ?,COMPANY_NAME = ?,LOGO = ?,NAME = ?,PHONE = ?,SURNAME = ?,WEBSITE = ?,CITY_ID = ?)  WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, newCompany.getAboutCompany());
            ps.setString(2, newCompany.getCompanyName());
            ps.setBlob(3,newCompany.getLogo());
            ps.setString(4, newCompany.getName());
            ps.setString(5, newCompany.getPhone());
            ps.setString(6, newCompany.getSurname());
            ps.setString(7, newCompany.getWebsite());
            ps.setLong(8,newCompany.getCityId().getId());
            ps.setLong(9,companyId);
            ps.executeUpdate();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteCompanyById(Long companyId) {
        String sql = "UPDATE COMPANY SET ACTIVE = 0 WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,companyId);
            ps.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
