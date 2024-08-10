<%-- 
    Document   : Tableau_Bord
    Created on : 30 juil. 2024, 14:25:08
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <style>
        .info {
            font-size: 16px;
            display: flex;
            justify-content: space-between;
            align-items: baseline;
            margin-top: 10px;
        }
        
        .data {
            width: 30%;
        }
        
        .point {
            width: 30%;
            margin-left: -70px;
        }
    </style>
    <% 
        int passedNumber = (Integer) request.getAttribute("passedNumber");
        int failedNumber = (Integer) request.getAttribute("failedNumber");
        int total = passedNumber + failedNumber;
    %>
    <div class="col-md-9" id="dataToExport">
        <div>
            <h2>Nombre des étudiants</h2>
            <div class="info">
                <div class="data">Passed Licence :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= passedNumber %></div>
            </div>
            <div class="info">
                <div class="data">Moyenne générale :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= failedNumber %></div>
            </div>
            <div class="info">
                <div class="data">Total :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= total %></div>
            </div>
        </div>
    </div>
</html>
