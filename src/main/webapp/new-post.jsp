<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.util.List" %>
<%@ page import="az.ekadr.entites.*" %>
<%@ page import="az.ekadr.dao.impl.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Company company = (Company) session.getAttribute("company");
    List<Category> categoryList = new CategoryDaoImpl().getAllCategory();
    List<City> cityList = new CityDaoImpl().getAllCity();
    List<Experience> experienceList = new ExperienceDaoImpl().getAllExperience();
    List<Workmode> workmodeList = new WorkmodeDaoImpl().getAllWorkmode();
    List<Education> educationList = new EducationDaoImpl().getAllEducation();
    List<Age> ageList = new AgeDaoImpl().getAllList();
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
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/generallynew.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./mobile-css/generallynew.css">
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/new-post.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./mobile-css/new-post.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <!--  <script src="https://cdn.ckeditor.com/ckeditor5/32.0.0/classic/ckeditor.js"></script> -->
    <title>New post</title>
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
        <li><a href="./index.html">Home</a></li>
        <li><a href="./vacancylist.html">Vacancies</a></li>
        <li><a href="./new-post.html">Add vacancy</a></li>
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
<div class="container mt-4 mb-4 " style="border: 1px solid #0EBBDA;border-radius: 10px;padding: 2rem;">
    <form method="post" action="/new_post">
        <div class="form-group mt-3">
            <label for="inputName">Vacancy name</label>
            <input type="text" name="vacancyname" class="form-control" id="inputName" placeholder="Java Backend Developer">
        </div>
        <div class="form-group">
            <label for="editor">Detailed information about the vacancy</label>
            <textarea id="editor" class="form-control" rows="8" name="information">

            </textarea>
        </div>
        <div class="form-group">
            <label for="editor2">Requirements for vacancies</label>
            <textarea id="editor2" class="form-control" rows="8" name="requirements">

            </textarea>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCategory">Category</label>
                <select  name="category" class="custom-select" id="inputCategory">
                    <option disabled selected>Category</option>
                    <%for(Category c:categoryList){%>
                    <option value="<%=c.getId()%>"><%=c.getCategory()%></option>
                    <%}%>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label for="inputWorkmode">Work mode</label>
                <select name="workmode" class="custom-select" id="inputWorkmode">
                    <option  disabled selected>Work mode</option>
                    <%for(Workmode w:workmodeList){%>
                    <option value="<%=w.getId()%>"><%=w.getWorkmode()%></option>
                    <%}%>
                </select>
            </div>
        </div>

        <div class="form-row">

            <div class="form-group col-md-6">
                <label for="inputExperience">Experience</label>
                <select name="experience" class="custom-select" id="inputExperience">
                    <option disabled selected>Experience</option>
                    <%for(Experience e:experienceList){%>
                    <option value="<%=e.getId()%>"><%=e.getExperience()%></option>
                    <%}%>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label for="inputAge">Age</label>
                <select name="age" class="custom-select" id="inputAge">
                    <option disabled selected>Age</option>
                    <%for(Age a:ageList){%>
                    <option value="<%=a.getId()%>"><%=a.getAge()%></option>
                    <%}%>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label for="inputSalary">Salary</label>
                <input type="text" name="salary" class="form-control" id="inputSalary" placeholder="1000-2000 or by agreement">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEducation">Education</label>
                <select name="education" class="custom-select" id="inputEducation">
                    <option  disabled selected>Education</option>
                    <%for(Education e:educationList){%>
                    <option value="<%=e.getId()%>"><%=e.getEducation()%></option>
                    <%}%>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label for="inputExpdate">Exp date</label>
                <input name="expdate" type="date" class="form-control" id="inputExpdate">
            </div>
        </div>

        <div class="form-group">
            <label for="textareaAdress">Adress</label>
            <textarea name="address" class="form-control" id="textareaAdress" rows="4"></textarea>
        </div>
        <div class="form-row">
            <input type="hidden" name="companyid" value="<%=company.getId()%>">
            <input type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter" value="Add vacancy">
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
<script src="https://kit.fontawesome.com/12df5bbd4f.js" crossorigin="anonymous"></script>
<!--<script>
    ClassicEditor
        .create( document.querySelector( '#editor' ) )
        .then( editor => {
            console.log( editor );
        } )
        .catch( error => {
            console.error( error );
        } );

    ClassicEditor
        .create( document.querySelector( '#editor2' ) )
        .then( editor => {
            console.log( editor );
        } )
        .catch( error => {
            console.error( error );
        } );
</script> -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./javascript/main.js"></script>
</body>
</html>
