<html>
   <head>
      <title>Using GET and POST Method to Read Form Data</title>
   </head>

   <body>
      <center>
      <h1>Using POST Method to Read Form Data</h1>

      <ul>
         <li><p><b>First Name:</b>
            <%= request.getParameter("fname")%>
         </p></li>
         <li><p><b>Last  Name:</b>
            <%= request.getParameter("lname")%>
         </p></li>
      </ul>
      </center>

   </body>
</html>