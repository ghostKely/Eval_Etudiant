<%-- 
    Document   : Import
    Created on : 25 juil. 2024, 23:20:52
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <h2>Importation de données</h2>
    <form action="C_Import_A" method="POST" enctype="multipart/form-data">   
        <div class="col-md-10 achatMatiere">
            <div class="quantite">
                <h4 class="titleMatiere">Fichier : </h4>
                <input type="file" name="fichierNoteEtudiant" class="selectMats">
                <div class="uniteMatiere"><h4 id="unite"></h4></div>
            </div>
            <div class="quantite">
                <h4 class="titleMatiere">Table : </h4>
                <input type="text" name="tablenameNoteEtudiant" class="selectMats">
                <div class="uniteMatiere"><h4 id="unite"></h4></div>
            </div>
            
            
            <div class="quantite">
                <h4 class="titleMatiere">Fichier : </h4>
                <input type="file" name="fichierConfigNote" class="selectMats">
                <div class="uniteMatiere"><h4 id="unite"></h4></div>
            </div>
            <div class="quantite">
                <h4 class="titleMatiere">Table : </h4>
                <input type="text" name="tablenameConfigNote" class="selectMats">
                <div class="uniteMatiere"><h4 id="unite"></h4></div>
            </div>
            <div class="submitButton">
                <input type="submit" value="IMPORTER">
            </div>
        </div>
    </form>
    <%@ include file="Inserted_Import.jsp" %> 
</html>
