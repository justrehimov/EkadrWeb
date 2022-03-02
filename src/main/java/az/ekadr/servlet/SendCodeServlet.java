package az.ekadr.servlet;

import az.ekadr.dao.impl.CompanyOperationDaoImpl;
import az.ekadr.service.SendVertificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "sendcode",value = "/sendcode")
public class SendCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("sendcode.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if(email != null){
            CompanyOperationDaoImpl codi = new CompanyOperationDaoImpl();
            Long id = codi.existsCompany(email);
            if(id != null & id>0){
                SendVertificationService svs = new SendVertificationService();
                Random random = new Random();
                int code = 100000 + random.nextInt(999999-100000);
                HttpSession session = req.getSession();
                session.setAttribute("code",code);
                session.setAttribute("email",email);
                boolean send = svs.sendVertificationMail(email,"Ekadr Vertification",code);
                if(send){
                    resp.sendRedirect("/forgotpassword");
                }
                else{
                    resp.sendRedirect("/sendcode");
                }
            }
            else{
                resp.getWriter().print("<script>alert(Invalid email)</script>");
            }
        }
        else{
            resp.getWriter().print("<script>alert(Email cannot be empty)</script>");
        }

    }
}
