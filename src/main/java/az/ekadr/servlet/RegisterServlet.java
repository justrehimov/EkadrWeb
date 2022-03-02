package az.ekadr.servlet;

import az.ekadr.dao.impl.CityDaoImpl;
import az.ekadr.dao.impl.CompanyDaoImpl;
import az.ekadr.entites.City;
import az.ekadr.entites.Company;
import az.ekadr.service.FileUploadService;
import az.ekadr.service.SendVertificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Blob;
import java.util.Date;
import java.util.Random;

@MultipartConfig
@WebServlet(name = "register",value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String companyName = req.getParameter("companyname");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String password = req.getParameter("password");
            String aboutCompany = req.getParameter("about");
            Integer verified = 0;
            Integer active = 1;
            Float balance = 0F;
            String website = req.getParameter("website");
            Long cityId = Long.valueOf(req.getParameter("city"));
            City city = new CityDaoImpl().getCityById(cityId);
            Part file = req.getPart("logo");
            Blob logo = FileUploadService.uploadFile(file);
            Company company = new Company();
            company.setAboutCompany(aboutCompany);
            company.setCompanyName(companyName);
            company.setName(name);
            company.setSurname(surname);
            company.setEmail(email);
            company.setPhone(phone);
            company.setWebsite(website);
            company.setPassword(password);
            company.setVerified(verified);
            company.setBalance(balance);
            company.setActive(active);
            company.setCityId(city);
            company.setLogo(logo);
            company.setDataDate(new Date());
            new CompanyDaoImpl().addCompany(company);
            SendVertificationService svs = new SendVertificationService();
            Random random = new Random();
            int code = 100000 + random.nextInt(999999-100000);
            HttpSession session = req.getSession();
            session.setAttribute("code",code);
            session.setAttribute("email",email);
            boolean send = svs.sendVertificationMail(email,"Ekadr Vertification",code);
            if(send){
                resp.sendRedirect("/vertification");
            }
            else
                resp.getWriter().println("<script>alert('Vertification code cannot send')</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
