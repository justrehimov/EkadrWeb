package az.ekadr.servlet;
import az.ekadr.dao.impl.*;
import az.ekadr.entites.Company;
import az.ekadr.entites.Vacancy;
import lombok.SneakyThrows;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "edit",value = "/edit")
public class EditVacancyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Company company = (Company) session.getAttribute("company");
        if(company!=null){
            String editId = req.getParameter("editId");
            VacancyDaoImpl vdi = new VacancyDaoImpl();
            Company c = new CompanyDaoImpl().getCompanyById(company.getId());
            session.removeAttribute("company");
            session.removeAttribute("editvacancy");
            session.setAttribute("company",c);
            session.setAttribute("editvacancy",vdi.getVacancyById(Long.valueOf(editId)));
            resp.sendRedirect("edit.jsp");
        }
        else{
            resp.sendRedirect("/login");
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String errormessage = "";
        Company c = (Company) session.getAttribute("company");
        String vacancyname = req.getParameter("vacancyname");
        String salary = req.getParameter("salary");
        String experience = req.getParameter("experience");
        String education = req.getParameter("education");
        String category = req.getParameter("category");
        String workmode = req.getParameter("workmode");
        String age = req.getParameter("age");
        String expdate = req.getParameter("expdate");
        String information = req.getParameter("information");
        String requirements = req.getParameter("requirements");
        String address = req.getParameter("address");
        String vacanyId = req.getParameter("editId");
        if(c!=null){
            if(!vacancyname.isEmpty() & !salary.isEmpty() & experience!=null & category!=null & education!=null
            & workmode!=null & age!=null & !information.trim().isEmpty() & !requirements.trim().isEmpty() & !address.trim().isEmpty() & vacanyId!=null){
                Vacancy v = new Vacancy();
                v.setId(Long.valueOf(vacanyId));
                v.setVacancyName(vacancyname);
                v.setSalary(salary);
                v.setEducationId(new EducationDaoImpl().getEducationById(Long.valueOf(education)));
                v.setExperienceId(new ExperienceDaoImpl().getExperienceById(Long.valueOf(experience)));
                v.setWorkmodeId(new WorkmodeDaoImpl().getWorkmodeById(Long.valueOf(workmode)));
                v.setCategoryId(new CategoryDaoImpl().getCategoryById(Long.valueOf(category)));
                v.setAgeId(new AgeDaoImpl().getAgeById(Long.valueOf(age)));
                v.setInformation(information);
                v.setRequirements(requirements);
                v.setAddress(address);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
                Date date = sdf.parse(expdate);
                v.setExpDate(date);
                VacancyDaoImpl vdi = new VacancyDaoImpl();
                vdi.updateVacancy(v,v.getId());
                resp.sendRedirect("/my_vacancies");
            }
            else{
                errormessage = "Data cannot be empty";
                session.setAttribute("erroredit",errormessage);
                resp.sendRedirect("/edit?editId=" + vacanyId);
            }
        }
        else{
            resp.sendRedirect("/login");
        }
    }
}
