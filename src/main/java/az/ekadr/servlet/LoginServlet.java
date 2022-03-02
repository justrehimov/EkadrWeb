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
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(email != null & password != null){
            Long id = new CompanyOperationDaoImpl().login(email,password);
            if(id !=null){
                HttpSession session = req.getSession();
                CompanyDaoImpl companyDao = new CompanyDaoImpl();
                session.setAttribute("company",companyDao.getCompanyById(id));
                resp.sendRedirect("index.jsp");
            }
            else{
                resp.getWriter().print("<script>alert(Email or password is invalid)</script>");
                resp.sendRedirect("/login");
            }
        }
        else{
            resp.getWriter().print("<script>alert(Email or password is invalid)</script>");
            resp.sendRedirect("/login");
        }

    }
}
