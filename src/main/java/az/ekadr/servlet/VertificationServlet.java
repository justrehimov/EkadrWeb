package az.ekadr.servlet;

import az.ekadr.dao.impl.CompanyOperationDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "vertification",value = "/vertification")
public class VertificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("vertification.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String errormessage = "";
        String code = req.getParameter("code");
        Integer usercode = 0;
        if(!code.isEmpty()){
            usercode = Integer.valueOf(code);
        }
        else {
            errormessage = "Code cannot be empty";
            session.setAttribute("error",errormessage);
            resp.sendRedirect("/vertification");
        }
        Integer confirmcode = (Integer)session.getAttribute("code");
        String email = String.valueOf(session.getAttribute("email"));
        if(usercode.compareTo(confirmcode) == 0){
            session.removeAttribute("error");
            session.removeAttribute("code");
            new CompanyOperationDaoImpl().confirmAccount(email);
            resp.sendRedirect("/login");
        }else {
            errormessage = "Confirm code is not true";
            session.setAttribute("error",errormessage);
            resp.sendRedirect("/vertification");
        }
    }
}

