package az.ekadr.dao.impl;

import az.ekadr.dao.CompanyOperationDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.Packet;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompanyOperationDaoImpl implements CompanyOperationDao {
    @Override
    public Long login(String email, String password) {
        String sql = "SELECT ID FROM COMPANY WHERE LOWER(EMAIL) = LOWER(?) AND PASSWORD = ? AND ACTIVE = 1 AND VERIFIED = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,email);
            ps.setString(2,password);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getLong("ID");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void confirmAccount(String email) {
        String sql = "UPDATE COMPANY SET VERIFIED = 1 WHERE LOWER(EMAIL) = LOWER(?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,email);
            ps.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void changePassword(String password, String email) {
        String sql = "UPDATE COMPANY SET PASSWORD = ? WHERE LOWER(EMAIL) = LOWER(?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,password);
            ps.setString(2,email);
            ps.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Long existsCompany(String email) {
        String sql = "SELECT ID FROM COMPANY WHERE LOWER(EMAIL) = LOWER(?) AND ACTIVE = 1 AND VERIFIED = 1";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getLong("ID");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean withdrawBalance(Long companyId, Float balance) {
        String sql = "UPDATE COMPANY SET BALANCE = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
            ps.setFloat(1,balance);
            ps.setLong(2,companyId);
            ps.executeUpdate();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void buyPacket(Long companyId, Long packetId) {
        String sql = "UPDATE COMPANY SET PACKET_ID = ?,COUNT_AD = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            PacketDaoImpl pdi = new PacketDaoImpl();
            Packet p = pdi.getPacketById(packetId);
            ps.setLong(1,packetId);
            ps.setInt(2,p.getCount_ad());
            ps.setLong(3,companyId);
            ps.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean decreaseCountAd(Long companyId, Integer countAd) {
        String sql = "UPDATE COMPANY SET COUNT_AD = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){;
            ps.setInt(1,countAd);
            ps.setLong(2,companyId);
            ps.executeUpdate();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProfilePicture(Long companyId, Blob blob) {
        String sql = "UPDATE COMPANY SET LOGO = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){;
            ps.setBlob(1,blob);
            ps.setLong(2,companyId);
            ps.executeUpdate();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
