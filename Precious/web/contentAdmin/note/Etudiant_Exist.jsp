<%-- 
    Document   : Etudiant_Exist
    Created on : 27 juil. 2024, 17:31:36
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        boolean etudiantExist = request.getAttribute("etudiantExist") != null;
        if (etudiantExist == true) { 
            String etu = String.valueOf(request.getAttribute("etu"));
    %>
        <div class="popupUser" id="popupReset">
            <div class="popup-contentEntree" id="popupContent">
                <h3 style="color: #ff3838;">Etudiant avec code ETU <%= etu %> non enregistré dans la base de données!</h3>
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
