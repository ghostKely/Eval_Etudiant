<%-- 
    Document   : List_EtudiantAll
    Created on : 30 juil. 2024, 15:42:19
    Author     : ETU1886-Fanirina
--%>

<%@page import="etudiant.Etudiant"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
    </style>
    <h2>Liste des étudiants</h2>
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
                <tr class="ligne" data-href="C_EtudiantAnneeAllR_R?etudiant=<%= etudiant.getEtu() %>">
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
