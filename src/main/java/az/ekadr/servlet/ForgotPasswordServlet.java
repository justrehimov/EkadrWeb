package az.ekadr.servlet;

import az.ekadr.dao.impl.CompanyOperationDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "forgotpassword",value = "/forgotpassword")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("forgotpassword.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String code = req.getParameter("code");
        String password = req.getParameter("password");
        String confirmpassword = req.getParameter("confirmpassword");
        String email = String.valueOf(session.getAttribute("email"));
        Integer realcode = (Integer) session.getAttribute("code");
        if(code != null & password != null & confirmpassword != null){
            if(password.equals(confirmpassword) & Integer.valueOf(code).compareTo(realcode) == 0){
                CompanyOperationDaoImpl codi = new CompanyOperationDaoImpl();
                codi.changePassword(password,email);
                resp.sendRedirect("/login");
            }
            else{
                resp.getWriter().print("<script>alert(Data cannot contain eachother)</script>");
            }
        }
        else {
            resp.getWriter().print("<script>alert(Invalid input)</script>");
        }

    }
}
