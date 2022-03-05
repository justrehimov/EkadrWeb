package az.ekadr.servlet;

import az.ekadr.dao.impl.CompanyDaoImpl;
import az.ekadr.dao.impl.VacancyDaoImpl;
import az.ekadr.entites.Company;
import az.ekadr.entites.Vacancy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "my_vacancies",value = "/my_vacancies")
public class MyVacanyServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Company company = (Company) session.getAttribute("company");
        if(company!=null){
            VacancyDaoImpl vdi = new VacancyDaoImpl();
            List<Vacancy> vacancyList = vdi.getAllVacancyByCompanyId(company.getId());
            Company c = new CompanyDaoImpl().getCompanyById(company.getId());
            session.setAttribute("company",c);
            session.setAttribute("vacancies",vacancyList);
            resp.sendRedirect("myvacancies.jsp");
        }
        else{
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String delete = req.getParameter("delete");
        HttpSession session = req.getSession();
        if(delete!=null){
            VacancyDaoImpl vdi = new VacancyDaoImpl();
            vdi.deleteVacancyById(Long.valueOf(delete));
            Company c = (Company) session.getAttribute("company");
            session.setAttribute("vacancies",vdi.getVacanciesByCategoryId(c.getId()));
            session.setAttribute("company",c);
            resp.sendRedirect("/my_vacancies");
        }
    }
}
