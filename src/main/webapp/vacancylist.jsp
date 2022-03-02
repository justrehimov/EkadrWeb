<%@ page import="java.util.Base64" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.util.List" %>
<%@ page import="az.ekadr.entites.*" %>
<%@ page import="az.ekadr.dao.impl.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Company company = (Company) session.getAttribute("company");
    List<Category> categoryList = new CategoryDaoImpl().getAllCategory();
    List<City> cityList = new CityDaoImpl().getAllCity();
    List<Experience> experienceList = new ExperienceDaoImpl().getAllExperience();
    List<Workmode> workmodeList = new WorkmodeDaoImpl().getAllWorkmode();
    List<Vacancy> vacancyList  = new VacancyDaoImpl().getAllVacancy();
    String login = "display:inline !important;";
    String profile = "display:none !important;";
    byte[] logo = null;
    String email = "";
    String fullname = "";
    Float balance = 0F;
    String base64Encoded = "";
    if(company!=null) {
        login = "display:none  !important;";
        profile = "display:inline-block  !important;";
        Blob blob = company.getLogo();
        logo = blob.getBytes(1,(int)blob.length());
        byte[] encodeBase64 = Base64.getEncoder().encode(logo);
        base64Encoded = new String(encodeBase64, "UTF-8");
        email = company.getEmail();
        fullname = company.getName() + " " + company.getSurname();
        balance = company.getBalance();
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./images/icon.png" type="image/icon type">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/generally.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./mobile-css/generally.css">
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/vacancylist.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./mobile-css/vacancylist.css">
    <title>Vacancy list</title>
</head>
<body>
<nav class="navbar" id="navbar">
    <img class="logo" src="./images/logo.png" width="200px">

    <button class="hamburger-btn" id="hamburgerbtn" value="close">
        <span></span>
        <span></span>
        <span></span>
    </button>
    <ul class="navbar-menu">
        <li><a href="/index.html">Home</a></li>
        <li><a href="/vacancies">Vacancies</a></li>
        <li><a href="/new-post">Add vacancy</a></li>
        <li><a href="/login" src="<%=login%>">Login</a></li>
        <li>
            <div class="dropdown" style="<%=profile%>">
                <a><i class="fas fa-user-circle"></i></a>
                <div class="dropdown-content">
                    <div class="account">
                        <div class="img-container"><img class="account-profile" src="data:image/*;base64,<%=base64Encoded%>"></div>
                        <div class="about-user">
                            <span><%=fullname%></span>
                            <span><%=email%></span>
                        </div>
                    </div>
                    <div class="account-menu">
                        <a href="profile.jsp"><span>My Account</span><i class="fas fa-user"></i></a>
                        <a href="profile.jsp"><span>Balance </span><span><%=balance%> &#8380;</span></a>
                        <a href="/login"><span>Logout</span><i class="fas fa-sign-out-alt"></i></a>
                    </div>
                </div>
            </div>
        </li>
    </ul>
</nav>
<div class="top-bar" id="topbar">
    <ul class="topbar-menu">
        <li><a href="./index.html">Home</a></li>
        <li><a href="./vacancylist.html">Vacancies</a></li>
        <li><a href="./new-post.html">Add vacancy</a></li>
        <li><a href="./login.html">Login</a></li>
    </ul>
    <div class="social-media-topbar">
        <div class="links">
            <a class="link insta" href="#">
                <div><i class="fab fa-instagram"></i></div><span>Instagram</span>
            </a>
            <a class="link whatsapp" href="#">
                <div><i class="fab fa-whatsapp"></i></div><span>Whatsapp</span>
            </a>
            <a class="link telegram" href="#">
                <div><i class="fab fa-telegram-plane"></i></div><span>Telegram</span>
            </a>
            <a class="link face" href="#">
                <div><i class="fab fa-facebook-f"></i></div><span>Facebook</span>
            </a>
        </div>
    </div>
</div>
<div class="content">
    <form class="search-form">
        <select class = "custom-select" id = "inputCategory">
            <option disabled selected> Category </option>
            <%for(Category c:categoryList){%>
            <option value = "<%=c.getId()%>"><%=c.getCategory()%></option>
            <%}%>
        </select>

        <select class="custom-select" id="inputCity">
            <option disabled selected>City</option>
            <%for(City c:cityList){%>
            <option value = "<%=c.getId()%>"><%=c.getCity()%></option>
            <%}%>
        </select>

        <select class="custom-select" id="inputWorkmode">
            <option disabled selected>Work mode</option>
            <%for(Workmode w:workmodeList){%>
            <option value = "<%=w.getId()%>"><%=w.getWorkmode()%></option>
            <%}%>
        </select>

        <select class="custom-select" id="inputExperience">
            <option disabled selected>Experience</option>
            <%for(Experience e:experienceList){%>
            <option value = "<%=e.getId()%>"><%=e.getExperience()%></option>
            <%}%>
        </select>

        <input type="search" class="input-search" placeholder="Java developer...">
        <button type="submit" class="btn-submit"><i class="fas fa-search"></i></button>
    </form>
    <div class="vacancy-list">
        <%for(Vacancy v:vacancyList){
            Blob blob = v.getCompanyId().getLogo();
            String pattern = "MMMM dd, yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(v.getExpDate());
            byte[] logoarr = blob.getBytes(1,(int)blob.length());
            byte[] encodeBase64 = Base64.getEncoder().encode(logoarr);
            String vlogo = new String(encodeBase64, "UTF-8");
        %>
        <a class="post" style="margin: 0.9rem 0rem !important;" href="post.jsp?postId=<%=v.getId()%>">
            <img class="company-logo" src="data:image/*;base64,<%=vlogo%>">
            <div class="about-post">
                <span><%=v.getVacancyName()%></span>
                <span><%=v.getCompanyId().getCompanyName()%></span>
                <span><%=v.getSalary()%></span>
            </div>
            <div class="additional-info">
                <span>Workmode:<%=v.getWorkmodeId().getWorkmode()%></span>
                <span>Experience:<%=v.getExperienceId().getExperience()%></span>
                <span>EXP:<%=date%></span>
            </div>
        </a>
        <%}%>
    </div>
</div>


<footer class="footer">
    <img class="logo" src="./images/logo.png" width="200px">
    <div class="contacts">
        <ul>
            <li><a href="https://goo.gl/maps/VjqUNfaoVtquroC19">Baku,Azerbaijan</a></li>
            <li><a href="tel:+99455873716">+994 (55) 873-71-65</a></li>
            <li><a href="mailto:info@ekadr.az">info@ekadr.az</a></li>
        </ul>
    </div>
    <div class="social-media">
        <div class="links">
            <a class="link insta" href="#">
                <i class="fab fa-instagram"></i>
            </a>
            <a class="link whatsapp" href="#">
                <i class="fab fa-whatsapp"></i>
            </a>
            <a class="link telegram" href="#">
                <i class="fab fa-telegram-plane"></i>
            </a>
            <a class="link face" href="#">
                <i class="fab fa-facebook-f"></i>
            </a>
            <a class="link up" href="#navbar">
                <i class="fas fa-chevron-up"></i>
            </a>
        </div>
    </div>
</footer>
<script src="https://kit.fontawesome.com/12df5bbd4f.js" crossorigin="anonymous"></script>
<script src="./javascript/main.js"></script>
</body>
</html>