package az.ekadr.dao;

import az.ekadr.entites.Packet;

import java.util.List;

public interface PacketDao {
    public List<Packet> getAllPacket();
    public Packet getPacketById(Long packetId);
    public void addPacket(Packet newPacket);
    public void updatePacket(Long packetId,Packet packet);
    public void deletePacket(Long packetId);
}
