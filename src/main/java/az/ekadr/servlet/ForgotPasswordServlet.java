package az.ekadr.servlet;

import az.ekadr.dao.impl.CompanyOperationDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "forgotpassword",value = "/forgotpassword")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("forgotpassword.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errormessage = "";
        HttpSession session = req.getSession();
        String code = req.getParameter("code");
        String password = req.getParameter("password");
        String confirmpassword = req.getParameter("confirmpassword");
        String email = String.valueOf(session.getAttribute("email"));
        Integer realcode = (Integer) session.getAttribute("code");
        if(!code.isEmpty() & !password.isEmpty() & !confirmpassword.isEmpty()){
            if(password.equals(confirmpassword)){
                if(Integer.valueOf(code).compareTo(realcode) == 0){
                    CompanyOperationDaoImpl codi = new CompanyOperationDaoImpl();
                    codi.changePassword(password,email);
                    session.removeAttribute("errorforgotpassword");
                    session.removeAttribute("code");
                    resp.sendRedirect("/login");
                }
                else {
                    errormessage = "Confirm code is not true";
                    session.setAttribute("errorforgotpassword",errormessage);
                    resp.sendRedirect("/forgotpassword");
                }
            }
            else{
                errormessage = "Password cannot contain";
                session.setAttribute("errorforgotpassword",errormessage);
                resp.sendRedirect("/forgotpassword");            }
        }
        else {
            errormessage = "Data cannot be empty";
            session.setAttribute("errorforgotpassword",errormessage);
            resp.sendRedirect("/forgotpassword");
        }

    }
}
