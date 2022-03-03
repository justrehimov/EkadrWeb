
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage = "true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error 404</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, Helvetica, sans-serif;
        }
        body{
            width: 100vw;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: center;
            padding: 10rem;
        }
        h1{
            font-size: 5rem;
            color: rgb(26, 173, 241);
            margin-bottom: 2rem;
        }
        body span{
            letter-spacing:1px;
            display: flex;
        }
    </style>
</head>
<body>
<h1>Error 404</h1>
<span><span>Something went wrong.Back to<pre> </pre></span><a href="/index"> home page</a></span>
</body>
</html>