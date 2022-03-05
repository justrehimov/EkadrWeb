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
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        resp.sendRedirect("sendcode.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        String errormessage = "";
        if(!email.isEmpty()){
            CompanyOperationDaoImpl codi = new CompanyOperationDaoImpl();
            Long id = codi.existsCompany(email);
            if(id != null){
                SendVertificationService svs = new SendVertificationService();
                Random random = new Random();
                int code = 100000 + random.nextInt(999999-100000);
                session.setAttribute("code",code);
                session.setAttribute("email",email);
                boolean send = svs.sendVertificationMail(email,"Ekadr Vertification",code);
                if(send){
                    session.removeAttribute("error");
                    resp.sendRedirect("/forgotpassword");
                }
                else{
                    errormessage = "Confirm code couldn't be sent";
                    session.setAttribute("errorsendcode",errormessage);
                    resp.sendRedirect("/sendcode");
                }
            }
            else{
                errormessage = "Email not found";
                session.setAttribute("errorsendcode",errormessage);
                resp.sendRedirect("/sendcode");
            }
        }
        else{
            errormessage = "Email cannot be empty";
            session.setAttribute("errorsendcode",errormessage);
            resp.sendRedirect("/sendcode");
        }

    }
}
