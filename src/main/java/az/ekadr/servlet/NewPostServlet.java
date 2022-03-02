package az.ekadr.servlet;

import az.ekadr.dao.impl.*;
import az.ekadr.entites.Company;
import az.ekadr.entites.Vacancy;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "new_post",value = "/new_post")
@MultipartConfig
public class NewPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Company company = (Company) session.getAttribute("company");
        if(company == null){
            resp.sendRedirect("/login");
        }
        else{
            resp.sendRedirect("new-post.jsp");
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vacancyname = req.getParameter("vacancyname");
        String information = req.getParameter("information");
        String requirements = req.getParameter("requirements");
        Long category = Long.valueOf(req.getParameter("category"));
        Long workmode = Long.valueOf(req.getParameter("workmode"));
        Long age = Long.valueOf(req.getParameter("age"));
        Long companyid = Long.valueOf(req.getParameter("companyid"));
        String expdate = req.getParameter("expdate");
        Long experience = Long.valueOf(req.getParameter("experience"));
        Long education = Long.valueOf(req.getParameter("education"));
        String address = req.getParameter("address");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
        String salary = req.getParameter("salary");
        Date exp = sdf.parse(expdate);
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyName(vacancyname);
        vacancy.setInformation(information);
        vacancy.setRequirements(requirements);
        vacancy.setCategoryId(new CategoryDaoImpl().getCategoryById(category));
        vacancy.setCompanyId(new CompanyDaoImpl().getCompanyById(companyid));
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
        if(vacancy!=null){
            vdi.addVacancy(vacancy);
            resp.sendRedirect("/index");
        }
    }
}
