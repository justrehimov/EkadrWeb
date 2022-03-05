package az.ekadr.servlet;

import az.ekadr.dao.impl.*;
import az.ekadr.entites.Company;
import az.ekadr.entites.Vacancy;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "new_post", value = "/new_post")
@MultipartConfig
public class NewPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("new-post.jsp");
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String errormessage = "";
        String vacancyname = req.getParameter("vacancyname");
        String information = req.getParameter("information");
        String requirements = req.getParameter("requirements");
        Long category = Long.valueOf(req.getParameter("category"));
        Long workmode = Long.valueOf(req.getParameter("workmode"));
        Long age = Long.valueOf(req.getParameter("age"));
        String companyId = req.getParameter("companyid");
        String expdate = req.getParameter("expdate");
        Long experience = Long.valueOf(req.getParameter("experience"));
        Long education = Long.valueOf(req.getParameter("education"));
        String address = req.getParameter("address");
        String salary = req.getParameter("salary");
        if (!vacancyname.isEmpty() & !information.isEmpty() & !requirements.isEmpty() & category != 0 & workmode != 0
                & age != 0 & companyId != null & !expdate.isEmpty() & experience != 0 & education != 0 & !address.isEmpty() & !salary.isEmpty())
        {
            Long company = Long.valueOf(companyId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
            CompanyDaoImpl cdi = new CompanyDaoImpl();
            Company addcompany = cdi.getCompanyById(company);
            Date exp = sdf.parse(expdate);
            Vacancy vacancy = new Vacancy();
            vacancy.setVacancyName(vacancyname);
            vacancy.setInformation(information);
            vacancy.setRequirements(requirements);
            vacancy.setCategoryId(new CategoryDaoImpl().getCategoryById(category));
            vacancy.setCompanyId(addcompany);
            vacancy.setWorkmodeId(new WorkmodeDaoImpl().getWorkmodeById(workmode));
            vacancy.setAgeId(new AgeDaoImpl().getAgeById(age));
            vacancy.setExperienceId(new ExperienceDaoImpl().getExperienceById(experience));
            vacancy.setEducationId(new EducationDaoImpl().getEducationById(education));
            vacancy.setAddress(address);
            vacancy.setExpDate(exp);
            vacancy.setDataDate(new Date());
            vacancy.setActive(1);
            vacancy.setId(null);
            vacancy.setSalary(salary);
            VacancyDaoImpl vdi = new VacancyDaoImpl();
            Company c = (Company) session.getAttribute("company");
            if (c.getCount_ad()>0) {
                CompanyOperationDaoImpl codi = new CompanyOperationDaoImpl();
                boolean success = codi.decreaseCountAd(company,c.getCount_ad()-1);
                if(success){
                    session.setAttribute("company",cdi.getCompanyById(company));
                    vdi.addVacancy(vacancy);
                    resp.sendRedirect("/index");
                }
                else {
                    errormessage = "Something went wrong";
                    session.setAttribute("errorpost",errormessage);
                    resp.sendRedirect("/new_post");
                }
            }
            else{
                errormessage = "Buy new packet";
                session.setAttribute("errorpost",errormessage);
                resp.sendRedirect("/new_post");
            }
        }
        else {
            errormessage = "Data cannot be empty";
            session.setAttribute("errorpost",errormessage);
            resp.sendRedirect("/new_post");
        }
    }
}
