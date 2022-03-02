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
    <link rel="stylesheet" media="screen and (min-width: 768px)" href="./desktop-css/profile.css">
    <link rel="stylesheet" media="screen and (max-width: 767px)" href="./mobile-css/profile.css">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <div class="sidebar">
            <button class="menu" onclick="mod('./dashboard.html')">Dashboard</button>
            <button class="menu" onclick="mod('./myvacancies.html')">My vacancies</button>
            <button class="menu" onclick="mod('./deposite.html')">Deposite</button>
        </div>
        <iframe src="dashboard.jsp" id="iframe" class="content">

        </iframe>
    </div>
    <script>
        function mod(mod){
            document.getElementById('iframe').setAttribute("src",mod);
        }
    </script>
</body>
</html>