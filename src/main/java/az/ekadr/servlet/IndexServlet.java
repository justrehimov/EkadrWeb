package az.ekadr.servlet;

import az.ekadr.entites.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "index",value = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Company company = (Company) session.getAttribute("company");
        if(company!=null){
            req.setAttribute("company",company);
        }
        else{
            req.setAttribute("company",null);
        }
        resp.sendRedirect("index.jsp");
    }
}
