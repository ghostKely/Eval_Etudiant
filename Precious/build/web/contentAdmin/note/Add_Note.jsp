<%-- 
    Document   : Add_Note
    Created on : 27 juil. 2024, 13:31:42
    Author     : ETU1886-Fanirina
--%>

<%@page import="matiere.Matiere"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <h2>Ajout d'une note</h2>
    <form action="C_Note_A" method="POST">   
        <div class="col-md-10 achatMatiere">
            <div class="quantite">
                <h4 class="titleMatiere">ETU : </h4>
                <input type="text" name="etu" class="selectMats" placeholder="0000" value="001886">
                <div class="titleMatiere"></div>
            </div>
            <div class="quantite"> 
                <div class="titleMatiere"><h4>Matiere : </h4></div>
                <select class="selectMats" name="idmatiere" onchange="updateUnite()">
                    <%
                        List<Matiere> listeMatiere = (List<Matiere>) request.getAttribute("liste_Matiere"); 
                        for (Matiere matiere : listeMatiere) {
                    %>
                        <option value="<%= matiere.getIdMatiere() %>" selected><%= matiere.getCode() %></option>
                    <% } %>
                </select>
                <div class="titleMatiere"></div>
            </div>
            <div class="quantite">
                <h4 class="titleMatiere">Note /20 :</h4>
                <input type="text" name="note" class="selectMats">
                <div class="titleMatiere"></div>
            </div>
            <div class="submitButton">
                <input type="submit" value="CONFIRMER">
            </div>
        </div>
    </form>
                
    <%@ include file="ChampVide.jsp" %>
    <%@ include file="Etudiant_Exist.jsp" %> 
    <%@ include file="Inserted_Note.jsp" %> 
</html>
