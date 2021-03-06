<%@ page import="az.ekadr.entites.Company" %>
<%@ page import="java.sql.Blob" %>
<%@ page errorPage="error.jsp"%>
<%@ page import="java.util.Base64" %>
<%@ page import="az.ekadr.dao.impl.CityDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="az.ekadr.entites.City" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Company company = (Company) session.getAttribute("company");
    List<City> cityList = null;
    String base64Encoded = "";
    String fullname = "";
    String msg = "";
    if(company==null){response.sendRedirect("login.jsp");}
    else{
        CityDaoImpl cdi = new CityDaoImpl();
        cityList = cdi.getAllCity();
        Blob blob = company.getLogo();
        byte[] logo = blob.getBytes(1,(int)blob.length());
        byte[] encodeBase64 = Base64.getEncoder().encode(logo);
        base64Encoded = new String(encodeBase64, "UTF-8");
        fullname = company.getName() + " " + company.getSurname();
        String errormessage = (String) session.getAttribute("errorprofile");
        session.removeAttribute("errorprofile");
        if(errormessage!=null){
            msg = errormessage;
        }
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
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/profile.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./desktop-css/profile.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"/>
    <title>Profile</title>
</head>
<body>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle mt-5" width="150px" src="data:image/*;base64,<%=base64Encoded%>">
                <span class="font-weight-bold mt-2"><%=fullname%></span><span class="text-black-50 mt-1"><%=company.getEmail()%></span>
                <form method="post" action="/update_profile" enctype="multipart/form-data">
                <div class="form-row mt-3">
                    <label for="upload">
                        <input type="file" id="upload" accept="image/*" name="logo" style="z-index: -5;width: 0rem;">
                        <div class="btn btn-primary">Upload photo</div>
                    </label>
                    <input class="btn btn-primary" type="submit" value="Save">
                </div>
                </form>
            </div>
        </div>
        <div class="col-md-9 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profile Settings</h4>
                </div>
                <form method="post" action="/profile">
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">Name</label><input type="text" class="form-control" name="name" placeholder="Name" value="<%=company.getName()%>"></div>
                    <div class="col-md-6"><label class="labels">Surname</label><input type="text" class="form-control" name="surname" value="<%=company.getSurname()%>" placeholder="Surname"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-12"><label class="labels">Company name</label>
                        <input type="text" name="company" class="form-control" placeholder="Company name" value="<%=company.getCompanyName()%>">
                    </div>
                    <div class="col-md-12"><label class="labels">City</label>
                        <select name="city" class="form-select">
                            <option value="<%=company.getCityId().getId()%>"><%=company.getCityId().getCity()%></option>
                            <% for(City c:cityList) { %>
                             <%if(c.getId().compareTo(company.getCityId().getId()) != 0) { %>
                                <option value="<%=c.getId()%>"><%=c.getCity()%></option>
                            <% } %>
                            <% } %>
                        </select>
                    </div>

                    <div class="col-md-12"><label class="labels">Phone</label><input type="text" name="phone" class="form-control" placeholder="Phone" value="<%=company.getPhone()%>"></div>
                    <div class="col-md-12"><label class="labels">Website</label><input type="text" name="website" class="form-control" placeholder="Website" value="<%=company.getWebsite()%>"></div>
                    <div class="col-md-12">
                        <label class="labels">About</label>
                        <textarea class="form-control" placeholder="About company" name="about" rows="4"><%=company.getAboutCompany().trim()%></textarea>
                    </div>
                </div>
                <div class="mt-3 text-right">
                    <div class="error-message">
                        <span class="error-txt" id="error"><%=msg%></span>
                    </div>
                    <button class="btn btn-primary profile-button" type="submit">Save Profile</button>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</div
<script src="./javascript/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
