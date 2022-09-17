<%-- 
    Verify if the passcode is correct. If it is, forward to the page to
    do calculation. If it is incorrect, include the information page for
    another try
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="myBean" scope="session" class="BeanPackage.MortgageBean">
    <jsp:setProperty name="myBean" property="*"/>
</jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            if(myBean.getPasscode().equals("12345"))
        {%>
         <jsp:forward page="mortgageCalculator2.jsp"/>
        <%} 
          else{
          %>
          <strong>The passcode is incorrect. Try again please</strong><br/>
          <jsp:include page="MortgageForm.html"/>
    </body>
</html>
