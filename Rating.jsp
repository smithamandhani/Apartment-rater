<!--# Copyright (c) 2016 Akanksha and Smitha
# This code is available under the "MIT License".
# Please see the file LICENSE in this distribution
# for license terms -->
<%-- 
    Document   : Rating  
    Author     : A
    Design for Rating page
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td><h1>Rate the Apartment...</h1></td>
                <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>  
                <td style="text-align: right" ><a href="login.jsp">logout</a>    </td>
        </tr>
        </table>
        <form action="RatingServlet" method="post">        
        <table border="0">
            <tr><td><b>Apartment Name</b></td><td><input name="aptName" type="text" value="${name}"/></td></tr>
            <tr><td><b>Bed</b></td><td><input name="bed" type="text" value="${bed}"/></td></tr>
            <tr><td><b>Rent</b></td><td><input name="rent" type="text" value="${rent}"/></td></tr>
            <tr><td><b>Contact Person Number</b></td><td><input name="contact" type="text" value="${contact}"/></td></tr>            
            <tr><td><b>Apartment ID</b></td><td><input name="id" type="text" value="${Id}" /></td></tr>            
            <tr><td><input type="text" name="Rate"></input>Stars</td><td><input type="submit" value="Rate"></td></tr>
        </table>
        </form>
    </body>
</html>
