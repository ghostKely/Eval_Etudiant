<%-- 
    Document   : List_Etudiant
    Created on : 27 juil. 2024, 23:07:33
    Author     : ETU1886-Fanirina
--%>

<%@page import="etudiant.Promotion"%>
<%@page import="etudiant.Etudiant"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .modifier {
            background-color: rgb(255 96 0 / 66%);
            border: none;
            color: #FFF;
            transition: .3s;
            border-radius: 30px;
            width: 85%;
            height: 40px;
            margin-left: 70px;
        }
        
        .modifier:hover{
            background-color: #FFF;
            color: rgb(255 96 0 / 66%);
            border: 1px solid rgb(255 96 0 / 66%);
        }
        
        .table {
            margin-top: 25%;
        }
    </style>
    <h2>Liste des étudiants</h2>
    <div class="col-md-10 paginationselect">
        <span><h4>Promotion : </h4></span>
        <span>
            <form id="pageToGo" action="C_EtudiantPromo_F" method="POST">           
                <select class="selectpage" name="promotion" onchange="submitForm()">
                    <% 
                        List<Promotion> listePromotion = (List<Promotion>) request.getAttribute("liste_Promotion");
                        int promotionSelected = (Integer) request.getAttribute("selectedPromo");
                        for (Promotion promotion : listePromotion) {
                            if (promotionSelected == promotion.getIdPromotion()) {
                    %>  
                        <option value="<%= promotion.getIdPromotion() %>" selected="<%= promotion.getNom() %>"><%= promotion.getNom() %></option>
                        <% } else { %>
                        <option value="<%= promotion.getIdPromotion() %>"><%= promotion.getNom() %></option>
                    <% } } %>
                </select>
            </form>
        </span>
        <span>
            <a href="C_Etudiant_L"><button class="modifier">TOUS LES ETUDIANTS</button></a>
        </span>
    </div>
    <form action="C_Search_R" method="POST">   
        <div class="col-md-10 achatMatiere">
            <div class="quantite">
                <h4 class="titleMatiere">Nom : </h4>
                <input type="text" name="nomEtudiant" class="selectMats" value="rate" placeholder="Rakoto">
                <div class="titleMatiere"></div>
            </div>
            <div class="submitButton">
                <input type="submit" value="SEARCH">
            </div>
        </div>
    </form>
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th>ETU</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Promotion</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Etudiant> listeEtudiant = (List<Etudiant>) request.getAttribute("liste_Etudiant");
                for (Etudiant etudiant : listeEtudiant) {
            %>
                <tr class="ligne" data-href="C_EtudiantNote_R?etudiant=<%= etudiant.getEtu() %>">
                    <td><%= etudiant.getEtu() %></td>
                    <td><%= etudiant.getNom() %></td>
                    <td><%= etudiant.getPrenom() %></td>
                    <td><%= etudiant.getPromotion().getNom() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
        
    <script>
        function submitForm() {
            document.getElementById('pageToGo').submit();
        }
        
        document.addEventListener('DOMContentLoaded', function() {
            // Sélectionner toutes les lignes de table avec l'attribut data-href
            var rows = document.querySelectorAll('.ligne[data-href]');

            rows.forEach(function(row) {
                row.addEventListener('click', function() {
                    // Rediriger vers l'URL stockée dans l'attribut data-href
                    window.location.href = this.getAttribute('data-href');
                });
            });
        });
    </script>     
</html>
