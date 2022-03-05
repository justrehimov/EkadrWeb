package az.ekadr.servlet;

import az.ekadr.dao.impl.CityDaoImpl;
import az.ekadr.dao.impl.CompanyDaoImpl;
import az.ekadr.entites.Company;
import lombok.SneakyThrows;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "profile",value = "/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Company company = (Company) session.getAttribute("company");
        if(company!=null){
            Company c = new CompanyDaoImpl().getCompanyById(company.getId());
            session.setAttribute("company",c);
            resp.sendRedirect("profile.jsp");
        }
        else{
            resp.sendRedirect("/login");
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Company company = (Company) session.getAttribute("company");
        String errormessage = "";
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String companyname = req.getParameter("company");
        String phone = req.getParameter("phone");
        String website = req.getParameter("website");
        String city = req.getParameter("city");
        String about = req.getParameter("about");
        if(name!=null & surname!=null & companyname!=null & phone!=null & company != null & website!=null & city!=null & about!=null)
        {
            Company c = new Company();
            c.setId(company.getId());
            c.setCompanyName(companyname);
            c.setCityId(new CityDaoImpl().getCityById(Long.valueOf(city)));
            c.setName(name);
            c.setSurname(surname);
            c.setWebsite(website);
            c.setPhone(phone);
            c.setAboutCompany(about);
            CompanyDaoImpl cdi = new CompanyDaoImpl();
            cdi.updateCompany(c,c.getId());
            session.setAttribute("company",cdi.getCompanyById(c.getId()));
            resp.sendRedirect("/index");
        }
        else {
           errormessage = "Data cannot be empty";
           session.setAttribute("errorprofile",errormessage);
           resp.sendRedirect("/profile");
        }


    }
}
