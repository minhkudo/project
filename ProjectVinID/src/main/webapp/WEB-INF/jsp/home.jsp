<%-- 
    Document   : home
    Created on : Dec 24, 2019, 4:35:21 PM
    Author     : MinhKudo
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
   <head>
      <meta charset="UTF-8"/>
      <title>Home</title>
      <style>
        th, td {
          padding: 5px;
        }
        table  {
           border-collapse: collapse;
        }
       
      </style>
   </head>
    
   <body>
       
      <h3>Using Publisher-DB</h3>
       
      <table border="1">
        <tr>
           <th>ID</th>
           <th>Name</th>
        </tr>
        <c:forEach items="${student}" var="student">
            <tr>
               <td >${student.id}</td>
               <td >${student.name}</td>
            </tr>
        </c:forEach>
      </table>
 
       
   </body>
    
</html>