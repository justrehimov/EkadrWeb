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
        Integer code = Integer.valueOf(req.getParameter("code"));
        HttpSession session = req.getSession();
        Integer confirmcode = (Integer)session.getAttribute("code");
        String email = String.valueOf(session.getAttribute("email"));
        if(code.compareTo(confirmcode) == 0){
            new CompanyOperationDaoImpl().confirmAccount(email);
            resp.sendRedirect("/login");
        }else {
            System.out.println("error vertificate");
        }


    }
}

