<%-- 
    Document   : Navbar_User
    Created on : 28 juil. 2024, 15:06:37
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <div class="col-md-1 url">
        <div class="logodiv"><img class="logo" src="assets/image/logo.jpg"></div>
        <div class="scroll-container">
            <a href="C_Index_R">
                <div class="boutonnavbar">
                    <span class="glyphicon glyphicon-th-large icon" aria-hidden="true"></span>
                    <span class="link-text">Index</span>
                </div>
            </a>
            <a href="C_HomeUser_R">
                <div class="boutonnavbar">
                    <span class="glyphicon glyphicon-home icon" aria-hidden="true"></span>
                    <span class="link-text">Home</span>
                </div>
            </a>
            
            <div class="boutonnavbar" id="semestre">
                <span class="glyphicon glyphicon-user icon" aria-hidden="true"></span>
                <span class="link-text">Semestre</span>
                <span class="glyphicon glyphicon-menu-down down" aria-hidden="true"></span>
            </div>
                <div id="dropdownMenusemestre" class="dropdown-content">
                    <a href="C_Semestre_L">
                        <div class="boutonnavbar">
                            <span class="glyphicon glyphicon-list icon" aria-hidden="true"></span>
                            <span class="link-text">Liste</span>
                        </div>
                    </a>
                </div>
            
            
            <a href="C_EtudiantAnnee_R">
                <div class="boutonnavbar">
                    <span class="glyphicon glyphicon-list icon" aria-hidden="true"></span>
                    <span class="link-text">Liste Annee</span>
                </div>
            </a>
        </div>
    </div>
    
<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('semestre').addEventListener('click', function(event) {
            event.preventDefault();
            document.getElementById('dropdownMenusemestre').classList.toggle('show');
        });

        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
            if (!event.target.closest('#semestre')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
            }
        }
    });
</script>
</html>
