<%-- 
    Document   : List_AnneeAll
    Created on : 10 août 2024, 15:00:42
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% String etudiant = (String) request.getAttribute("etudiant"); %>
    <h2>Liste des années d'étudiants</h2>
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th>Annee</th>
            </tr>
        </thead>
        <tbody>
            <tr class="ligne" data-href="C_EtudiantAnneeAll_A?etudiant=<%= etudiant %>&semestre1=1&semestre2=2">
                <td>L1</td>
            </tr>
            <tr class="ligne" data-href="C_EtudiantAnneeAll_A?etudiant=<%= etudiant %>&semestre1=3&semestre2=4">
                <td>L2</td>
            </tr>
            <tr class="ligne" data-href="C_EtudiantAnneeAll_A?etudiant=<%= etudiant %>&semestre1=5&semestre2=6">
                <td>L3</td>
            </tr>
        </tbody>
    </table>
        
    <script>
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
