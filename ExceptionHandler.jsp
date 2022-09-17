<%-- 
    Document   : ExceptionHandler
    Created on : Dec 2, 2019, 9:44:40 AM
    Author     : jas47367
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exception Handler</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%=exception.getMessage()%>
    </body>
</html>
