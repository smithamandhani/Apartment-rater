<!--# Copyright (c) 2016 Akanksha and Smitha
# This code is available under the "MIT License".
# Please see the file LICENSE in this distribution
# for license terms -->
<%-- 
    Document   : login
    Author     : A
    Design for login page
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="loginServlet" method="post">
<table>
<tr>
<td>User name :</td>
<td><input type="text" name="id"/></td>
</tr>
<tr>
<td>Password :</td>
<td><input type="password" name="password"/></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="Login"></td>
</tr>
</table>
</form>
    </body>
</html>
