package az.ekadr.servlet;

import az.ekadr.dao.impl.CompanyDaoImpl;
import az.ekadr.dao.impl.CompanyOperationDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "login",value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session!=null){
            session.removeAttribute("company");
        }
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String errormessage = "";
        if(!email.isEmpty() & !password.isEmpty()){
            Long id = new CompanyOperationDaoImpl().login(email,password);
            if(id !=null){
                CompanyDaoImpl companyDao = new CompanyDaoImpl();
                session.setAttribute("company",companyDao.getCompanyById(id));
                session.removeAttribute("errorlogin");
                resp.sendRedirect("index.jsp");
            }
            else{
                errormessage = "Email or password is invalid or not verified account";
                session.setAttribute("errorlogin",errormessage);
                resp.sendRedirect("/login");
            }
        }
        else{
            errormessage = "Email or password cannot be empty";
            session.setAttribute("errorlogin",errormessage);
            resp.sendRedirect("/login");
        }

    }
}
