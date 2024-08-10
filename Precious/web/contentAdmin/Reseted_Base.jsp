<%-- 
    Document   : Reseted_Base
    Created on : 25 juil. 2024, 19:25:50
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        boolean validation = request.getAttribute("validationReset") != null;
        if (validation == true) { %>
        <div class="popupUser" id="popupReset">
            <div class="popup-contentEntree" id="popupContent">
    <%        boolean validationImport = (Boolean) request.getAttribute("validationReset");
            if (validationImport == false) { %>
                <h3>Reset de donnée réussie !</h3>
    <% } else { %>
                <h3 style="color: #ff3333;">Echec du reset !</h3>
        <% } %> 
                <div class="infoUser">
                    <button class="popup-buttonAccount" id="continueButton">CONTINUER</button>
                </div>
            </div>
        </div> 
    <%} %>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var popupReset = document.getElementById('popupReset');
            if (popupReset) {
                // Afficher le popup
                popupReset.style.display = 'flex';
                // Ajouter un événement au bouton "CONTINUER"
                document.getElementById('continueButton').addEventListener('click', function() {
                    // Masquer le popup
                    popupReset.style.display = 'none';
                });
            }
        });
    </script>
</html>
