<%-- 
    Document   : Home_User
    Created on : 28 juil. 2024, 16:03:33
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@ include file="../component/styleLink/CssLink.jsp" %>
    </head>

    <body> 
        <div class="col-md-12 row">
            <% 
                String navbarAdress = String.valueOf(session.getAttribute("navbar")); 
                String contentPage = String.valueOf(request.getAttribute("content")); 
            %>
            <jsp:include page="<%= navbarAdress %>" />	

            <div class="col-md-10 content">
                <div class="col-md-10 box">
                    <jsp:include page="<%= contentPage %>" />	
                </div>
            </div>
        </div>
        <%@ include file="../component/styleLink/JsLink.jsp" %>
    </body>
</html>
