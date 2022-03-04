<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage = "true" %>
<%
    int errorcode = 404;
    String msg = "Something went wrong";
    String errormessage = (String) session.getAttribute("errorbuypacket");
    session.removeAttribute("errorbuypacket");
    if(errormessage!=null){
        msg = errormessage;
        errorcode = 504;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error <%=errorcode%></title>
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
            color: #f80e0e;
            margin-bottom: 2rem;
        }
        body span{
            letter-spacing:1px;
            display: flex;
        }
    </style>
</head>
<body>
<h1>Error <%=errorcode%></h1>
<span><span><%=msg%>.Back to<pre> </pre></span><a href="/index"> home page</a></span>
</body>
</html>