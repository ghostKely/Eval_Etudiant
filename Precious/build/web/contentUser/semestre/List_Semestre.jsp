<%-- 
    Document   : List_Semestre
    Created on : 28 juil. 2024, 16:37:29
    Author     : ETU1886-Fanirina
--%>

<%@page import="etudiant.Etudiant"%>
<%@page import="log.ProfilUser"%>
<%@page import="semestre.Semestre"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .semestremoyenne {
            margin-top: 30%;
        }
    </style>
    <h2>Liste des semestres</h2>
    <form action="C_EtudiantNote_R" method="POST">   
        <div class="col-md-10 achatMatiere">
            <div class="quantite"> 
                <div class="titleMatiere"><h4>Semestre : </h4></div>
                <select class="selectMats" name="semestre">
                <% 
                    ProfilUser profilUser = (ProfilUser) session.getAttribute("userLogged");
                    List<Semestre> listeSemestre = (List<Semestre>) request.getAttribute("liste_Semestre");
                    for (Semestre semestre : listeSemestre) {
                %>
                        <option value="<%= semestre.getIdSemestre() %>" selected><%= semestre.getNom() %></option>
                    <% } %>
                </select>
                <div class="titleMatiere"></div>
            </div>
                <input type="hidden" name="etudiant" value="<%= profilUser.getEtudiant().getEtu() %>">
            <div class="submitButton">
                <input type="submit" value="CONFIRMER">
            </div>
        </div>
    </form>
    
    <div class="semestremoyenne">
        <h2>Liste des étudiants Licence</h2>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th>Semestre</th>
                    <th>Moyenne</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
                        for (Semestre semestreEtudiantPass : etudiant.getListeSemestre()) {
                %>
                        <tr class="ligne">
                            <td><%= semestreEtudiantPass.getNom() %></td>
                            <td><%= semestreEtudiantPass.getMoyenneSemestre() %></td>
                        </tr>
                    <% } %>
            </tbody>
        </table>       
    </div>
      
</html>
