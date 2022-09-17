<%-- 
    Java Server Pages--JSP
A JSP Document is a one that contains regular Html code and embeds
java code in it to make the page dynamic
    Three kinds of JSP elements:
    1. JSP Constructs: inserts java code into the resultant servlet directly
        Three kinds of JSP constructs:
            1. JSP expression
               Syntax: <%= Java expression %>
               It is eqilvalent to the statement in servlet:
                    out.println("Some string here" + (SomeJavaExpression))
            2. JSP sriptlet
               Syntax: <% Java Statements %>
               It inserts the java statements into the resultant servlet
            3. JSP declaration
               Syntax: <%! declaration %>
               It declares variables or methods in the resultant servlet
    2. JSP Directives
        A JSP directive is a JSP element that provides information for the
        JSP engine
            Syntax: <%@directiveName attributeName="value"%>
        Two important directives:

       1.  page directive:
            Attributes:
            1). import: it is used to import a java class into this page from
                the library so it's value may be one class name such as
                <%@page import="java.util.Scanner"%>
                or a group of classes separated by commas such as
                <%@page import="java.util.*, java.io.*%>
            2). errorPage: it is used to specify a page that will be processed
                when an exception is thrown in this page
                <%@page errorPage="ExceptionHandler"%>
            3). isErrorPage: its value is true or false. If its value is true,
                the predefined variable exception can be used in this page and
                this page can be used ti handle exception
                <%@page isErrorPage="true"%>
                
        2. include directive:
            It is used to include a page into the current page when the resultant
            servlet is compiled
            attribute: file -- it's value is the file path of a page
            inlcude method of the RequestDispatcher class in Servlet
            <%@include file="filepath"%>

    3. JSP Actions
            JSP actions allow different pages to share object (JavaBean)
            of java classes
            To have a JavaBean, a class must satify the following properties:
            1). it must be a public class
            2). it must have a constructor that does not have parameters
                (This one is satified when a class does not have any constructor.
                 If a class has any explicit constructor, you have to define a
                 constructor without parameter)
            3). (optional) There is a getXxx and SetXxx method for each attribute
                if you have an int type atribute called number, Your methods
                should be
                int getNumber()
                void setNumber(int n)

            JSP action syntax:
                <jsp:actionName attributeName="value"/>
            Three JSP actions:
               1. useBean--to get the java bean from the given scope if one exists,
                         creates an object if it does not exist in this scope
                         Three attributes of the useBean action
                            1). id -- declares a variable for the bean created
                                      or obtained
                            2). scope -- its value may be session or page
                            3). class -- its value is the class that is used
                                to create the bean
                <jsp:useBean id="myBean" scope="session" class="className"/>
                If there is no bean wit hthe name in the specified scope, a bean of
                the given class is created and assigned to the variable defined by
                the id attribute.
                If a bean of the given class with the given variable exists in
                the given scope, it obtains the bean
                or
                <jsp:useBean id="myBean" scope="session" class="className">
                jsp elements
                </jsp:useBean>
               2. setProperty--changes the value of this attribute to the given value
                    Three / two attributes:
                        name
                        property
                        value
                <jsp:setProperty name="beanName" property="AttributeName" value="v"/>

                <jsp:setProperty name="beanName" property="*"/>
                It gets all the values from the form that activates this pae and
                use them to initialize all the corresonding values?


               3. getProperty--sget the value of this attribute of the bean 
                             and send it to the output
                             name
                             property
               4. include--include a file into this page 
               5. forward--forward to another page
                    page--its value is the page to be included or forwarded to
            


Predefined variables:
    request--it represents an object of the HTTPServletRequest class
    response--it represents an object of the HTTPServletRespnse class
    out--it represents an object of the PrintWriter class that can be used to
         display information
    session--it represents on object of the HttpSession class that is used
             for session tracking

Example: Write a JSP page that calculates the factorial of integers from 1 to 10

Example: Write a website that collects information about a mortage, calculates
         the corresponding mothly payment and display the results 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%int n=Integer.parseInt(request.getParameter("number"));%>
        <h1>Hello these are the values of factorial of 1 to 10</h1>
        <% 
            
        for (int i = 1; i <= n; i++)
        {
         out.println("<strong>The factorial of " + i + " is " + fac(i) + "</strong><br/>");
        }
        
        %>
        
        <%--Declare method for calculating the factorial of n--%>
        <%!
            
        public long fac(int n)
        {
            if (n == 1) return 1;
            return n * fac(n - 1);
        }

        %>
    </body>
</html>
