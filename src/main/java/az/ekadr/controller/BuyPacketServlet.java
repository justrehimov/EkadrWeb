package az.ekadr.controller;

import az.ekadr.dao.impl.CompanyDaoImpl;
import az.ekadr.dao.impl.CompanyOperationDaoImpl;
import az.ekadr.dao.impl.PacketDaoImpl;
import az.ekadr.entites.Company;
import az.ekadr.entites.Packet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "buy_packet",value = "/buy_packet")
public class BuyPacketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PacketDaoImpl packetDao = new PacketDaoImpl();
        List<Packet> packetList = packetDao.getAllPacket();
        session.setAttribute("packet",packetList);
        resp.sendRedirect("packet.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String packet = req.getParameter("packet");
        HttpSession session = req.getSession();
        Company company = (Company) session.getAttribute("company");
        String errormessage = "";
        if(!packet.isEmpty() & company!=null){
            Long packetId = Long.valueOf(packet);
            PacketDaoImpl pdi = new PacketDaoImpl();
            Packet p = pdi.getPacketById(packetId);
            CompanyOperationDaoImpl codi = new CompanyOperationDaoImpl();
            CompanyDaoImpl cdi = new CompanyDaoImpl();
            if(company.getBalance() >= p.getPrice()){
                boolean success = codi.withdrawBalance(company.getId(),company.getBalance()-p.getPrice());
                if(success){
                    session.setAttribute("company",cdi.getCompanyById(company.getId()));
                    session.removeAttribute("errorbuypacket");
                    codi.buyPacket(company.getId(),packetId);
                    resp.sendRedirect("/index");
                }
                else{
                    errormessage = "Something went wrong";
                    session.setAttribute("errorbuypacket",errormessage);
                    resp.sendRedirect("/error");
                }
            }
            else{
                    errormessage = "Insufficient balance";
                    session.setAttribute("errorbuypacket",errormessage);
                    resp.sendRedirect("/error");
            }
        }
        else{
            resp.sendRedirect("/login");
        }
    }
}
