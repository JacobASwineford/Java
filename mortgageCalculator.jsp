<%-- 
    Document   : mortgageCalculator
    Created on : Nov 20, 2019, 10:17:21 AM
    Author     : jas47367
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="ExceptionHandler.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
          double p=Double.parseDouble(request.getParameter("principal"));
          double ar=Double.parseDouble(request.getParameter("annualRate")); 
          int n = Integer.parseInt(request.getParameter("life"));
          double mr = ar/100/12;
          
        %>
        <h1>Hello, here is your mortgage information:</h1>
        <strong>Principal </strong><%=p%><br/>
        <strong>Annual Interest Rate: </strong> <%=ar%><br/>
        <strong>Life in years:</strong> <%=n%> years
        <strong>Monthly Payment</strong>
        <%=(p*mr)/(1-Math.pow(1+mr, -12*n))%>
    </body>
</html>
