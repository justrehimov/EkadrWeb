package az.ekadr.dao.impl;

import az.ekadr.dao.PacketDao;
import az.ekadr.db.DbHelper;
import az.ekadr.entites.Packet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacketDaoImpl implements PacketDao {
    @Override
    public List<Packet> getAllPacket() {
        List<Packet> packetList = new ArrayList<>();
        String sql = "SELECT * FROM PACKET WHERE ACTIVE = 1";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Packet p = new Packet();
                p.setId(rs.getLong("ID"));
                p.setPrice(rs.getFloat("PRICE"));
                p.setCount_ad(rs.getInt("COUNT_AD"));
                p.setAbout(rs.getString("ABOUT"));
                p.setPacket_name(rs.getString("PACKET_NAME"));
                p.setActive(rs.getInt("ACTIVE"));
                packetList.add(p);
            }
            return packetList;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Packet getPacketById(Long packetId) {
        Packet p = new Packet();
        String sql = "SELECT * FROM PACKET WHERE ACTIVE = 1 AND ID = ?";
        try(Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setLong(1,packetId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                p.setId(rs.getLong("ID"));
                p.setPrice(rs.getFloat("PRICE"));
                p.setCount_ad(rs.getInt("COUNT_AD"));
                p.setPacket_name(rs.getString("PACKET_NAME"));
                p.setAbout(rs.getString("ABOUT"));
                p.setActive(rs.getInt("ACTIVE"));
            }
            return p;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addPacket(Packet newPacket) {

    }

    @Override
    public void updatePacket(Long packetId, Packet packet) {

    }

    @Override
    public void deletePacket(Long packetId) {

    }
}
