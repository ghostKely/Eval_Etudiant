<%-- 
    Document   : Add
    Created on : 25 juil. 2024, 23:25:57
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <h2>Acheter une matiere premiere</h2>
    <form action="" method="POST">   
        <div class="col-md-10 achatMatiere">
            <div class="quantite"> 
                <div class="titleMatiere"><h4>Nom : </h4></div>
                <select class="selectMats" name="idmatiere1re" onchange="updateUnite()">
                    <option value="" selected>1</option>
                </select>
                <div class="titleMatiere"></div>
            </div>
            <div class="quantite">
                <h4 class="titleMatiere">Quantite : </h4>
                <input type="text" name="quantite" class="selectMats">
                <div class="uniteMatiere"><h4 id="unite"></h4></div>
            </div>
            <div class="quantite">
                <h4 class="titleMatiere">Date d'achat : </h4>
                <input type="datetime-local" name="dateAchat" class="selectMats">
                <div class="titleMatiere"></div>
            </div>
            <div class="quantite">
                <h4 class="titleMatiere">Montant : </h4>
                <input type="montant" name="montant" class="selectMats">
                <div class="uniteMatiere"><h4 style="margin-left: -50%;">Ar</h4></div>
            </div>
            <div class="submitButton">
                <input type="submit" value="CONFIRMER">
            </div>
        </div>
    </form>
</html>
