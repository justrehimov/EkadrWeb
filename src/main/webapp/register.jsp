<%@ page import="java.util.List" %>
<%@ page import="az.ekadr.entites.City" %>
<%@ page import="az.ekadr.dao.impl.CityDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage = "error.jsp" %>
<%
    List<City> cityList = new CityDaoImpl().getAllCity();
    String msg = "";
    Object error = session.getAttribute("errorregister");
    session.removeAttribute("errorregister");
    if(error==null){
        msg = "";
    }
    else{
        msg = String.valueOf(error);
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
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/register.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./mobile-css/register.css">
    <title>Vacancies 2022</title>
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
        <li><a href="/index">Home</a></li>
        <li><a href="/vacancies">Vacancies</a></li>
        <li><a href="/new_post">Add vacancy</a></li>
        <li><a href="/login">Login</a></li>
    </ul>
</nav>
<div class="top-bar" id="topbar">
    <ul class="topbar-menu">
        <li><a href="/index">Home</a></li>
        <li><a href="/vacancies">Vacancies</a></li>
        <li><a href="/new_post">Add vacancy</a></li>
        <li><a href="/register">Register</a></li>
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
          <h1>Register page</h1>
          <form class="register-form" method="post" action="/register" enctype="multipart/form-data">
              <div class="user-info">
                <input class="input" onclick="clearerror()" name="name" type="text" placeholder="Name">
                <input class="input" onclick="clearerror()"  name="surname" type="text" placeholder="Surname">
                <input class="input" onclick="clearerror()"  name="companyname" type="text" placeholder="Company name">
                <input class="input" onclick="clearerror()"  name="email" type="email" placeholder="Email">
                <input class="input" onclick="clearerror()"  name="phone" type="tel" placeholder="Phone">
                <select name="city" onclick="clearerror()"  class="input" id="input">
                    <option value="0">City</option>
                    <%for(City c:cityList){%>
                    <option value="<%=c.getId()%>"><%=c.getCity()%></option>
                    <%}%>
                </select>
                <input class="input" onclick="clearerror()"  name="website" type="url" placeholder="Website(Optional)">
                <input class="input" onclick="clearerror()"  name="password" type="password" minlength="8" placeholder="Password">
                <label for="upload" class="input wrapper" id="wrapper" data-text="Select logo">
                    <input id="upload" onclick="clearerror()"  name="logo" onchange="checkfile()" type="file" class="field" accept="image/*">
                </label>
              </div>
              <textarea class="text" onclick="clearerror()"  name="about" placeholder="About the company" rows="5"></textarea>
              <div class="register-footer">
                  <div class="error-message">
                      <span class="error-txt" id="error"><%=msg%></span>
                  </div>
                  <input class="input btn" name="registerbtn" type="submit" value="Register">
              </div>
          </form>
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
<script src="./javascript/main.js"></script>
<script src="https://kit.fontawesome.com/12df5bbd4f.js" crossorigin="anonymous"></script>    
</body>
</html>