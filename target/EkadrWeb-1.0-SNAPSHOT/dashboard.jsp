<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div class="content">
          <form class="register-form" method="post">
              <div class="user-info">
                <input class="input" type="text" placeholder="Name"> 
                <input class="input" type="text" placeholder="Surname">   
                <input class="input" type="text" placeholder="Company name">       
                <input class="input" type="text" placeholder="Phone">
                <select name="city" class="input" id="input">
                    <option disabled selected>City</option>
                    <option>Baku</option>
                </select>
                <input class="input" type="url" placeholder="Website (Optional)">
                <input class="input" type="password" minlength="8" placeholder="Password">              
                <label for="upload" class="input wrapper" id="wrapper" data-text="Select logo">
                    <input id="upload" onchange="checkfile()" type="file" class="field" accept="image/*">
                </label>
              </div>
              <textarea class="text" placeholder="About the company" rows="8"></textarea>
              <input class="input btn" type="submit" value="Save changes">
          </form>
    </div>

<script src="./javascript/main.js"></script>
<script src="https://kit.fontawesome.com/12df5bbd4f.js" crossorigin="anonymous"></script>    
</body>
</html>