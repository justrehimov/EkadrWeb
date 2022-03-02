package az.ekadr.servlet;

import az.ekadr.dao.impl.CityDaoImpl;
import az.ekadr.dao.impl.CompanyDaoImpl;
import az.ekadr.dao.impl.CompanyOperationDaoImpl;
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
            HttpSession session = req.getSession();
            String errormessage = "";
            String companyName = req.getParameter("companyname");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String password = req.getParameter("password");
            String aboutCompany = req.getParameter("about");
            String website = req.getParameter("website");
            Long cityId = Long.valueOf(req.getParameter("city"));
            Part file = req.getPart("logo");
            if(!name.isEmpty() & !surname.isEmpty() & !companyName.isEmpty() & !email.isEmpty() & !password.isEmpty() &
            !phone.isEmpty() & !aboutCompany.isEmpty() & cityId!=0 & file!=null & !website.isEmpty()){
                CompanyOperationDaoImpl codi = new CompanyOperationDaoImpl();
                if(codi.existsCompany(email) != null){;
                    errormessage = "This email already registered";
                    session.setAttribute("error",errormessage);
                    resp.sendRedirect("/register");
                }
                else {
                    City city = new CityDaoImpl().getCityById(cityId);
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
                    company.setVerified(0);
                    company.setBalance(0F);
                    company.setActive(1);
                    company.setCityId(city);
                    company.setLogo(logo);
                    company.setDataDate(new Date());
                    new CompanyDaoImpl().addCompany(company);
                    SendVertificationService svs = new SendVertificationService();
                    Random random = new Random();
                    int code = 100000 + random.nextInt(999999-100000);
                    session.setAttribute("code",code);
                    session.setAttribute("email",email);
                    boolean send = svs.sendVertificationMail(email,"Ekadr Vertification",code);
                    if(send){
                        session.removeAttribute("error");
                        resp.sendRedirect("/vertification");
                    }
                    else{
                        errormessage = "Confirm code couldn't be sent";
                        session.setAttribute("error",errormessage);
                        resp.sendRedirect("/register");
                    }
                }

            }
            else{
                errormessage = "Data cannot be empty";
                session.setAttribute("error",errormessage);
                resp.sendRedirect("/register");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
