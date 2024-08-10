<%-- 
    Document   : Etudiant_Licence
    Created on : 30 juil. 2024, 03:22:59
    Author     : ETU1886-Fanirina
--%>

<%@page import="etudiant.Etudiant"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .eleve {
                width: 45%;
                margin-bottom: 25px;
        }
    </style>
    <h2>Liste des étudiants Licence</h2>
    
    <% List<Etudiant> listeEtudiant = (List<Etudiant>) request.getAttribute("liste_Etudiant"); %>
    <div class="col-md-10 achatMatiere">
        <div class="quantite">
            <% boolean licencePassed = (Boolean) request.getAttribute("licencePassed"); 
                if (licencePassed == true) { %>
                <h4 class="eleve">Nombre d'élève Licence Passed : <%= listeEtudiant.size() %></h4>
                <% } else { %>
                <h4 class="eleve">Nombre d'élève Licence Failed : <%= listeEtudiant.size() %></h4>
                <% } %>
        </div>
    </div>
    
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
                if (listeEtudiant.isEmpty() == false) {
                    for (Etudiant etudiant : listeEtudiant) {
            %>
                    <tr class="ligne">
                        <td><%= etudiant.getEtu() %></td>
                        <td><%= etudiant.getNom() %></td>
                        <td><%= etudiant.getPrenom() %></td>
                        <td><%= etudiant.getPromotion().getNom() %></td>
                    </tr>
                <% } } else { %>
                    <h3>Aucun</h3>
                <% } %>
        </tbody>
    </table>   
</html>
