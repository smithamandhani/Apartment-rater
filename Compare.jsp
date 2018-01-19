<!--# Copyright (c) 2016 Akanksha and Smitha
# This code is available under the "MIT License".
# Please see the file LICENSE in this distribution
# for license terms -->
<%-- 
    Document   : Compare    
    Author     : A
    Design for Compare Select Box page
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function validate()
        {
            var selectChoose = document.getElementById('Multi');
            var maxOptions = 2;
            var optionCount = 0;
            for (var i = 0; i < selectChoose.length; i++) {
                if (selectChoose[i].selected) {
                    optionCount++;
                    if (optionCount > maxOptions) {
                        alert("validation failed, not submitting,Select only 2");
                        //window.location="Compare.jsp";                        
                        //window.location = self.location.reload(true); 
                        return false;
                    }
                }
            }
            return true;
        }
        </script>
    </head>
    <body>        
        <form action="CompareServlet" method="post" >            
            <h1>Select 2 to compare</h1>
            <select multiple="multiple" name="Multi" id="Multi">                
    <c:forEach var="item" items="${AptName}">
                                <option>${item}</option>
                            </c:forEach>
            </select>
            <input type="submit" value="Look" onclick="return validate();"/>
        </form>
    </body>
</html>
