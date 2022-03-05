<%@ page import="az.ekadr.entites.Company" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.util.Base64" %>
<%@ page import="az.ekadr.entites.Vacancy" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="az.ekadr.servlet.MyVacanyServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Company company = (Company) session.getAttribute("company");
  List<Vacancy> vacancyList = (List<Vacancy>) session.getAttribute("vacancies");
  String base64Encoded = "";
  String fullname = "";
  String msg = "";
  if(company==null){response.sendRedirect("login.jsp");}
  else{
    Blob blob = company.getLogo();
    byte[] logo = blob.getBytes(1,(int)blob.length());
    byte[] encodeBase64 = Base64.getEncoder().encode(logo);
    base64Encoded = new String(encodeBase64, "UTF-8");
    fullname = company.getName() + " " + company.getSurname();
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
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"/>
  <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/myvacancies.css">
  <link rel="stylesheet" media="screen and (max-width: 767px)" href="./desktop-css/myvacancies.css">
  <title>My vacancies</title>
</head>
<body>
<div class="container rounded bg-white mt-5 mb-5">
  <div class="row">
    <div class="col-md-3 border-right">
      <div class="d-flex flex-column align-items-center text-center p-3 py-5">
        <img class="rounded-circle mt-5" width="150px" src="data:image/*;base64,<%=base64Encoded%>">
        <span class="font-weight-bold"><%=fullname%></span>
        <span class="text-black-50"><%=company.getEmail()%></span>
      </div>
    </div>
    <div class="col-md-9 border-right" id="container">
      <div class="p-3 py-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
          <h4 class="text-right">Edit vacancies</h4>
        </div>
        <div class="form-row" id="content">
        <%for (Vacancy v:vacancyList){%>
          <div class="card text-center mt-3 mb-3">
            <div class="card-header">
              <ul class="nav nav-pills card-header-pills">
                <li class="nav-item">
                  <a class="btn btn-info mr-2" href="post.jsp?postId=<%=v.getId()%>">Show</a>
                </li>
                <li class="nav-item">
                  <form method="get" action="/edit">
                    <button type="submit" value="<%=v.getId()%>" name="editId" class="btn btn-secondary mr-2">Edit</button>
                  </form>
                </li>
                <li class="nav-item">
                  <button class="btn btn-danger mr-2" onclick="setId('<%=v.getId()%>')" data-bs-toggle="modal" data-bs-target="#exampleModal" >Delete</button>
                </li>
              </ul>
            </div>
            <div class="card-body">
              <h5 class="card-title"style="color: #8a3ab9;"><%=v.getVacancyName()%></h5>
              <div class="form-row justify-content-between mt-4" style="display: flex;">
                <div class="form-group">
                  <%
                    String pattern = "MMMM dd, yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String expdate = simpleDateFormat.format(v.getDataDate());
                    String postdate = simpleDateFormat.format(v.getExpDate());
                  %>
                  <p class="card-text text-left" >Post Date:<span style="color: #0EBBDA;"><%=postdate%></span></p>
                  <p class="card-text text-left" >Exp Date:<span style="color: #0EBBDA;"><%=expdate%></span></p>
                </div>
                <div class="form-group">
                  <p class="card-text text-left" >Education:<span style="color: #0EBBDA;"><%=v.getEducationId().getEducation()%></span></p>
                  <p class="card-text text-left" >Experience:<span style="color: #0EBBDA;"><%=v.getExperienceId().getExperience()%></span></p>
                </div>
                <div class="form-group">
                  <p class="card-text text-left" >Workmode:<span style="color: #0EBBDA;"><%=v.getWorkmodeId().getWorkmode()%></span></p>
                  <p class="card-text text-left" >City:<span style="color: #0EBBDA;"><%=company.getCityId().getCity()%></span></p>
                </div>
              </div>
            </div>
          </div>
          <%}%>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</div>

<!-- Button trigger modal -->

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete vacancy</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" style="border: none;font-size: 1.5rem;outline: none; background: transparent;" aria-label="Close">&#10005;</button>
      </div>
      <div class="modal-body">
        Are you sure?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <form method="post" action="/my_vacancies">
          <input type="hidden" id="del-id" name="delete">
          <button type="submit" class="btn btn-danger">Delete</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script>
  function setId(id){
    var delinput = document.getElementById('del-id');
    delinput.value = id;
  }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
