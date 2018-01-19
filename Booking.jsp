<!--# Copyright (c) 2016 Akanksha and Smitha
# This code is available under the "MIT License".
# Please see the file LICENSE in this distribution
# for license terms -->
<%-- 
    Document   : Booking   
    Author     : A
    Design for Booking page
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table td { border: 0;}
        </style>
    </head>
    <body>
         <table>
            <tr>
                <td><h1>Book the Apartment....Meet owner....Decide And Pay....</h1></td>
                <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>  
                <td style="text-align: right" ><a href="login.jsp">logout</a>    </td>
        </tr>
        </table>
        <form action="BookingServlet" method="post">        
        <table border="0">
            <tr><td><b>Apartment Name</b></td><td><input name="aptName" type="text" value="${name}"/></td></tr>
            <tr><td><b>Bed</b></td><td><input name="bed" type="text" value="${bed}"/></td></tr>
            <tr><td><b>Rent</b></td><td><input name="rent" type="text" value="${rent}"/></td></tr>
            <tr><td><b>Contact Person Number</b></td><td><input name="contact" type="text" value="${contact}"/></td></tr>
            <tr><td><b>Enter your Name</b></td><td><input name="Name" type="text"/></td></tr>
            <tr><td><b>Enter your Email</b></td><td><input name="email" type="text"/></td></tr>            
            <tr><td><b>Enter your Address</b></td><td><textarea name="address" rows="10" cols="50"></textarea></td></tr>                       
            <tr><td><b>Enter your Contact Number</b></td><td><input name="urcontact" type="text"</td></tr>
            <tr><td><b>Shifting Date(mm/dd/yyyy)</b></td><td><input name="shiftingdate" type="text"/></td></tr>   
            <tr><td><b>Meeting Owner Date(mm/dd/yyyy)</b></td><td><input name="meetingdate" type="text"/></td></tr>   
            <tr><td colspan="2" align="center"><input type="submit" value="Book"></td></tr>
        </table>
        </form>
    </body>
</html>
