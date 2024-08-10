<%-- 
    Document   : index
    Created on : 25 juil. 2024, 14:17:00
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>It_University Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/Style.css">
        <link rel="stylesheet" href="assets/css/Dropdown.css">
    </head>

    <body> 
    <div class="col-md-12 row">
        <%@ include file="component/navbar/Navbar_Index.jsp" %>
        
        <div class="col-md-10 content">
            <div class="col-md-10 box">
                <span class="homewelcome">
                    <h3 class="welcome">Welcome to the</h3>
                    <h1 class="welcomingtitle">IT_University</h1>
                </span>    
            </div>
        </div>
    </div>
        
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
