<%-- 
    Document   : ChampVide
    Created on : 30 juil. 2024, 10:32:48
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        boolean champVide = request.getAttribute("champVide") != null;
        if (champVide == true) { 
    %>
        <div class="popupUser" id="popupReset">
            <div class="popup-contentEntree" id="popupContent">
                <h3 style="color: #ff3838;">Veuillez remplir tous les champs!</h3>
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
