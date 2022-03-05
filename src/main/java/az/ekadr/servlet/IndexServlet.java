package az.ekadr.servlet;

import az.ekadr.dao.impl.CompanyDaoImpl;
import az.ekadr.entites.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "index",value = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Company company = (Company) session.getAttribute("company");
        if(company!=null){
            Company c = new CompanyDaoImpl().getCompanyById(company.getId());
            session.setAttribute("company",c);
        }
        else{
            session.setAttribute("company",null);
        }
        resp.sendRedirect("index.jsp");
    }
}
