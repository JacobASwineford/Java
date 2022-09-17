<%-- 
    Collects the infor about a mortgage from a form,
    Creates a bean and calculates the monthly payment and report
    back
--%>

<%@page import="java.text.NumberFormat"%>
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
          NumberFormat nf = NumberFormat.getNumberInstance();
          nf.setMaximumFractionDigits(2);
         %>
         <strong>Your Monthly Payment is <%=nf.format(myBean.getMpt())%></strong><br/>
        <jsp:getProperty name="myBean" property="mpt"/>
        <jsp:include page="MortgageForm.html"/>
    </body>
</html>
