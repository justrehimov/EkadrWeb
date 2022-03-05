<%@ page import="java.sql.Blob" %>
<%@ page import="java.util.Base64" %>
<%@ page import="az.ekadr.dao.impl.*" %>
<%@ page import="java.util.List" %>
<%@ page errorPage="error.jsp" %>
<%@ page import="az.ekadr.entites.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Company company = (Company) session.getAttribute("company");
    String base64Encoded = "";
    String fullname = "";
    String msg = "";
    Vacancy v = null;
    List<Age> ageList = null;
    List<Category> categoryList = null;
    List<Workmode> workmodeList = null;
    List<Education> educationList = null;
    List<Experience> experienceList = null;
    if(company==null){response.sendRedirect("login.jsp");}
    else{
        v = (Vacancy) session.getAttribute("editvacancy");
        ExperienceDaoImpl edi = new ExperienceDaoImpl();
        EducationDaoImpl edui = new EducationDaoImpl();
        WorkmodeDaoImpl wdi = new WorkmodeDaoImpl();
        CategoryDaoImpl cadi = new CategoryDaoImpl();
        AgeDaoImpl adi = new AgeDaoImpl();
        ageList = adi.getAllList();
        categoryList = cadi.getAllCategory();
        workmodeList = wdi.getAllWorkmode();
        educationList = edui.getAllEducation();
        experienceList = edi.getAllExperience();
        Blob blob = company.getLogo();
        byte[] logo = blob.getBytes(1,(int)blob.length());
        byte[] encodeBase64 = Base64.getEncoder().encode(logo);
        base64Encoded = new String(encodeBase64, "UTF-8");
        fullname = company.getName() + " " + company.getSurname();
        String errormessage = (String) session.getAttribute("erroredit");
        session.removeAttribute("erroredit");
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"/>
    <script src="https://cdn.ckeditor.com/ckeditor5/32.0.0/classic/ckeditor.js" charset="UTF-8"></script>
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/edit.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./desktop-css/edit.css">
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
            <form method="post" action="/edit">
                <div class="row m-1 mt-4">
                    <div class="col">
                        <label for="vacancy" class="form-label">Vacancy name</label>
                        <input type="text" name="vacancyname" value="<%=v.getVacancyName()%>" class="form-control" id="vacancy" placeholder="Vacancy name">
                    </div>
                    <div class="col">
                        <label for="salary" class="form-label">Salary</label>
                        <input type="text" name="salary" value="<%=v.getSalary()%>" class="form-control" id="salary" placeholder="Salary">
                    </div>
                </div>

                <div class="row m-1 mt-4">
                    <div class="col">
                        <label for="category" class="form-label">Category</label>
                        <select id="category" name="category" class="form-control">
                            <option value="<%=v.getCategoryId().getId()%>"><%=v.getCategoryId().getCategory()%></option>
                            <% for(Category c:categoryList) { %>
                            <%if(v.getCategoryId().getId().compareTo(c.getId())!=0) { %>
                            <option value="<%=c.getId()%>"><%=c.getCategory()%></option>
                            <% } %>
                            <% } %>
                        </select>
                    </div>
                    <div class="col">
                        <label for="workmode" class="form-label">Work mode</label>
                        <select id="workmode" name="workmode" class="form-control">
                            <option value="<%=v.getWorkmodeId().getId()%>"><%=v.getWorkmodeId().getWorkmode()%></option>
                            <% for(Workmode w:workmodeList) { %>
                            <%if(v.getWorkmodeId().getId().compareTo(w.getId())!=0) { %>
                            <option value="<%=w.getId()%>"><%=w.getWorkmode()%></option>
                            <% } %>
                            <% } %>
                        </select>
                    </div>

                </div>

                <div class="row m-1 mt-4">
                    <div class="col">
                        <label for="education" class="form-label">Education</label>
                        <select id="education" name="education" class="form-control">
                            <option value="<%=v.getEducationId().getId()%>"><%=v.getEducationId().getEducation()%></option>
                            <% for(Education e:educationList) { %>
                            <%if(v.getEducationId().getId().compareTo(e.getId())!=0) { %>
                            <option value="<%=e.getId()%>"><%=e.getEducation()%></option>
                            <% } %>
                            <% } %>
                        </select>
                    </div>
                    <div class="col">
                        <label for="experience" class="form-label">Experience</label>
                        <select id="experience" name="experience" class="form-control">
                            <option value="<%=v.getExperienceId().getId()%>"><%=v.getExperienceId().getExperience()%></option>
                            <% for(Experience ex:experienceList) { %>
                            <%if(v.getExperienceId().getId().compareTo(ex.getId())!=0) { %>
                            <option value="<%=ex.getId()%>"><%=ex.getExperience()%></option>
                            <% } %>
                            <% } %>
                        </select>
                    </div>
                </div>

                <div class="row m-1 mt-4">

                    <div class="col">
                        <label for="age" class="form-label">Age</label>
                        <select id="age" name="age" class="form-control">
                            <option value="<%=v.getAgeId().getId()%>"><%=v.getAgeId().getAge()%></option>
                            <% for(Age a:ageList) { %>
                            <%if(v.getAgeId().getId().compareTo(a.getId())!=0) { %>
                            <option value="<%=a.getId()%>"><%=a.getAge()%></option>
                            <% } %>
                            <% } %>
                        </select>
                    </div>

                    <div class="col">
                        <label for="expdate" class="form-label">Exp date</label>
                        <input type="date" value="<%=v.getExpDate()%>" name="expdate" class="form-control" id="expdate">
                    </div>
                </div>

                <div class="form-group m-3 mt-4">
                    <label for="editor">Information</label>
                    <div id="editor"></div>
                </div>

                <div class="form-group m-3 mt-4">
                    <label for="editor2">Requirements</label>
                    <div id="editor2"></div>
                </div>

                <div class="form-group m-3 mt-4">
                    <label for="address">Address</label>
                    <textarea id="address"  name="address" placeholder="Address" class="form-control" rows="3"><%=v.getAddress().trim()%></textarea>
                </div>
                <div class="form-group m-3 justify-content-between">
                    <input type="hidden" name="information" id="information-input">
                    <input type="hidden" name="requirements" id="requirements-input">
                    <input type="hidden" name="editId" value="<%=v.getId()%>">
                    <div class="row">
                        <div class="col">
                            <button type="submit" onclick="setEditorsData(myeditor,myeditor2,'information-input','requirements-input')" class="btn btn-primary">Save changes</button>
                        </div>
                        <div class="col text-right text-danger">
                            <span class="error-txt" id="error"><%=msg%></span>
                        </div>
                    </div>

                </div>

            </form>
        </div>


    </div>
</div>
</div>
</div>

<!-- Button trigger modal -->

<script>
    function setId(id){
        var delinput = document.getElementById('del-id');
        delinput.value = id;
    }
    var myeditor;
    var myeditor2;
    ClassicEditor
        .create( document.querySelector( '#editor' ),{
            toolbar: [ 'bold','link','bulletedList','undo', 'redo' ]
        } )
        .then( editor => {
            myeditor = editor;
            editor.setData('<%=v.getInformation()%>');
            console.log( editor );
        } )
        .catch( error => {
            console.error( error );
        } );
    ClassicEditor
        .create( document.querySelector( '#editor2' ),{
            toolbar: [ 'bold','link','bulletedList','undo', 'redo' ]
        })
        .then( editor2 => {
            myeditor2 = editor2;
            editor2.setData('<%=v.getRequirements()%>');
            console.log( editor2 );
        } )
        .catch( error => {
            console.error( error );
        } );

</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./javascript/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
