<%@ page import="az.ekadr.entites.Company" %>
<%@ page import="java.sql.Blob" %>
<%@ page errorPage = "error.jsp" %>
<%@ page import="java.util.Base64" %>
<%@ page import="az.ekadr.dao.impl.VacancyDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    Company company = (Company) session.getAttribute("company");
    VacancyDaoImpl vdi = new VacancyDaoImpl();
    int vacancy_count = vdi.getVacancySize();
    String login = "display:inline !important;";
    String profile = "display:none !important;";
    byte[] logo = null;
    int companyvacancies = 0;
    int adcount = 0;
    String packetname = "N/A";
    String email = "";
    String fullname = "";
    Float balance = 0F;
    String base64Encoded = "";
    if(company!=null) {
        if(company.getPacketId()!=null){packetname = company.getPacketId().getPacket_name();}
        companyvacancies = vdi.getVacancySizeByCompanyId(company.getId());
        adcount = company.getCount_ad();
        login = "display:none !important;";
        profile = "display:inline-block !important;";
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
    <meta http-equiv="X-UA-Compatible" content="IE=chrome">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./images/icon.png" type="image/icon type">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/generally.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./mobile-css/generally.css">
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/index.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./mobile-css/index.css">
    <title>2022 Vacancies</title>
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
        <li><a href="/vacancies">Vacancies</a></li>
        <li>
        </li>
        <li><a href="/new_post">Add vacancy</a></li>
        <li><a href="/buy_packet">Packets</a></li>
        <li><a href="/login" style="<%=login%>">Login</a></li>
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
                        <a href="/profile"><span>My Account</span><i class="fas fa-user"></i></a>
                        <a href="/balance"><span>Balance</span><span><%=balance%> &#8380;</span></a>
                        <a href="/my_vacancies"><span>My vacancies</span><span><%=companyvacancies%></span></a>
                        <a href="/buy_packet"><span>Packet</span><span><%=packetname + " "%><%=adcount%></span></a>
                        <a href="/login"><span>Logout</span><i class="fas fa-sign-out-alt"></i></a>
                    </div>
                </div>
            </div>
        </li>
    </ul>
</nav>
<div class="top-bar" id="topbar">
    <ul class="topbar-menu">
        <li><a href="/vacancies">Vacancies</a></li>
        <li><a href="/new_post">Add vacancy</a></li>
        <li><a href="/login">Login</a></li>
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
    <header class="header">
        <h1>Professional business world</h1>
        <span>You can search and share all kinds of vacancies on this site</span>
    </header>
    <div class="category">
        <h1>Categories</h1>
        <span><%=vacancy_count%> active vacancies</span>
        <div class="categories">
            <span class="cards">
                <i class="fas fa-code"></i>
                <a href="vacancylist.jsp?categoryId=1" >Information technologies</a>
            </span>
            <span class="cards">
                <i class="fab fa-uikit"></i>
                <a href="vacancylist.jsp?categoryId=5">Design and creativity</a>
            </span>
            <span class="cards">
                <i class="fas fa-stethoscope"></i>
                <a href="vacancylist.jsp?categoryId=3">Medicine and pharmacy</a>
            </span>
            <span class="cards">
                <i class="fas fa-user-graduate"></i>
                <a href="vacancylist.jsp?categoryId=4">Training and education</a>
            </span>
        </div>
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