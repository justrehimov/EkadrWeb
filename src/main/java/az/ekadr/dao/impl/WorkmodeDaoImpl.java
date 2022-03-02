package az.ekadr.dao.impl;

import az.ekadr.dao.WorkmodeDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.Workmode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WorkmodeDaoImpl implements WorkmodeDao {
    @Override
    public List<Workmode> getAllWorkmode() {
        List<Workmode> workmodeList = new ArrayList<>();
        String sql = "SELECT * FROM WORKMODE WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Workmode workmode = new Workmode();
                workmode.setId(rs.getLong("ID"));
                workmode.setWorkmode(rs.getString("WORKMODE"));
                workmode.setActive(rs.getInt("ACTIVE"));
                workmodeList.add(workmode);
            }
            return workmodeList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Workmode getWorkmodeById(Long workmodeId) {
        Workmode workmode = new Workmode();
        String sql = "SELECT * FROM WORKMODE WHERE ACTIVE = 1 AND ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,workmodeId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                workmode.setId(rs.getLong("ID"));
                workmode.setActive(rs.getInt("ACTIVE"));
                workmode.setWorkmode(rs.getString("WORKMODE"));
            }
            return workmode;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addWorkmode(Workmode newWorkmode) {
        String sql = "INSERT INTO WORKMODE(WORKMODE,ACTIVE) VALUES(?,?)";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newWorkmode.getWorkmode());
            ps.setInt(2,newWorkmode.getActive());
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateWorkmode(Workmode newWorkmode, Long workmodeId) {
        String sql = "UPDATE WORKMODE SET WORKMODE = ?,ACTIVE = ? WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,newWorkmode.getWorkmode());
            ps.setInt(2,newWorkmode.getActive());
            ps.setLong(3,workmodeId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteWorkmodeById(Long workmodeId) {
        String sql = "DELETE FROM WORKMODE WHERE ID = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,workmodeId);
            ps.execute();
            c.commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
