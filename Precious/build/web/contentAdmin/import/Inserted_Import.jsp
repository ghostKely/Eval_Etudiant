<%-- 
    Document   : Inserted_Import
    Created on : 25 juil. 2024, 23:21:10
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        boolean validation = request.getAttribute("validationImportation") != null;
        if (validation == true) { %>
        <div class="popupUser" id="popupImportation">
            <div class="popup-contentEntree" id="popupContent">
    <%        boolean validationImport = (Boolean) request.getAttribute("validationImportation");
            if (validationImport == true) { %>
                <h3>Importation de donnée réussie !</h3>
            <% } else { %>
                <h3 style="color: #ff3333;">Echec de l'imporatation !</h3>
            <% } %> 
                <div class="infoUser">
                    <button class="popup-buttonAccount" id="continueButton">CONTINUER</button>
                </div>
            </div>
        </div> 
    <%} %>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Récupérer les valeurs des attributs passés par la servlet

                document.getElementById('popupImportation').style.display = 'flex';
                // Ajouter un événement au bouton "CONTINUER"
                document.getElementById('continueButton').addEventListener('click', function() {
                    // Masquer le popup
                    document.getElementById('popupImportation').style.display = 'none';
                });
        });
    </script>
</html>
