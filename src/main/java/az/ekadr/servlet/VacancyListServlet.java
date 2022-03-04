package az.ekadr.servlet;

import az.ekadr.dao.impl.VacancyDaoImpl;
import az.ekadr.entites.Vacancy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "vacancies",value = "/vacancies")
public class VacancyListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("vacancylist.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String categoryId = req.getParameter("category");
        String experienceId = req.getParameter("experience");
        String companyId = req.getParameter("company");
        String workmodeId = req.getParameter("workmode");
        String vacancyname = req.getParameter("vacancyname");
        if(vacancyname==null){
            vacancyname = "";
        }
        if(categoryId !=null & experienceId !=null & companyId !=null & workmodeId != null & !vacancyname.isEmpty()){
            Long category = Long.valueOf(categoryId);
            Long experience = Long.valueOf(experienceId);
            Long company = Long.valueOf(companyId);
            Long workmode = Long.valueOf(workmodeId);
            VacancyDaoImpl vdi = new VacancyDaoImpl();
            List<Vacancy> vacancyList = vdi.searchVacancyByIdAndName(category,experience,company,workmode,vacancyname);
            session.removeAttribute("vacancy");
            session.setAttribute("vacancy",vacancyList);
            resp.sendRedirect("/vacancies");
        }
        else if(categoryId==null & experienceId==null & companyId==null & workmodeId==null){
            VacancyDaoImpl vdi = new VacancyDaoImpl();
            List<Vacancy> vacancyList = vdi.getVacancyByName(vacancyname);
            session.removeAttribute("vacancy");
            session.setAttribute("vacancy",vacancyList);
            resp.sendRedirect("/vacancies");
        }
        else if((categoryId!=null | experienceId!=null | companyId!=null | workmodeId !=null) & vacancyname.isEmpty()){
            if(categoryId==null){categoryId = "0";}
            if(experienceId==null){experienceId="0";}
            if(workmodeId==null){workmodeId="0";}
            if(companyId==null){companyId="0";}
            Long category = Long.valueOf(categoryId);
            Long experience = Long.valueOf(experienceId);
            Long company = Long.valueOf(companyId);
            Long workmode = Long.valueOf(workmodeId);
            VacancyDaoImpl vdi = new VacancyDaoImpl();
            List<Vacancy> vacancyList = vdi.getVacanciesByIds(category,company,experience,workmode);
            session.removeAttribute("vacancy");
            session.setAttribute("vacancy",vacancyList);
            resp.sendRedirect("/vacancies");
        }
        else{
            session.setAttribute("vacancy",null);
            resp.sendRedirect("/vacancies");
        }
    }

}
