<%-- 
    Document   : Navbar_Admin
    Created on : 25 juil. 2024, 19:37:00
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
            <a href="C_HomeAdmin_R">
                <div class="boutonnavbar">
                    <span class="glyphicon glyphicon-home icon" aria-hidden="true"></span>
                    <span class="link-text">Home</span>
                </div>
            </a>
            
            <div class="boutonnavbar" id="note">
                <span class="glyphicon glyphicon-book icon" aria-hidden="true"></span>
                <span class="link-text">Note</span>
                <span class="glyphicon glyphicon-menu-down down" aria-hidden="true"></span>
            </div>
                <div id="dropdownMenunote" class="dropdown-content">
                    <a href="C_Note_R">
                        <div class="boutonnavbar">
                            <span class="glyphicon glyphicon-plus-sign icon" aria-hidden="true"></span>
                            <span class="link-text">Ajout</span>
                        </div>
                    </a>
                </div>
            
            <div class="boutonnavbar" id="etudiant">
                <span class="glyphicon glyphicon-user icon" aria-hidden="true"></span>
                <span class="link-text">Etudiant</span>
                <span class="glyphicon glyphicon-menu-down down" aria-hidden="true"></span>
            </div>
                <div id="dropdownMenuetudiant" class="dropdown-content">
                    <a href="C_Etudiant_L">
                        <div class="boutonnavbar">
                            <span class="glyphicon glyphicon-list icon" aria-hidden="true"></span>
                            <span class="link-text">Liste</span>
                        </div>
                    </a>
                    <a href="C_TableauBord_R">
                        <div class="boutonnavbar">
                            <span class="glyphicon glyphicon-stats icon" aria-hidden="true"></span>
                            <span class="link-text">Tableau de bord</span>
                        </div>
                    </a>
                    <a href="C_EtudiantListeAll_L">
                        <div class="boutonnavbar">
                            <span class="glyphicon glyphicon-user icon" aria-hidden="true"></span>
                            <span class="link-text">Etudiants</span>
                        </div>
                    </a>
                    <a href="C_EtudiantLicence_R?licence=1">
                        <div class="boutonnavbar">
                            <span class="glyphicon glyphicon-education icon" aria-hidden="true"></span>
                            <span class="link-text">Succeed</span>
                        </div>
                    </a>
                    <a href="C_EtudiantLicence_R?licence=0">
                        <div class="boutonnavbar">
                            <span class="glyphicon glyphicon-unchecked icon" aria-hidden="true"></span>
                            <span class="link-text">Failed</span>
                        </div>
                    </a>
                </div>
            
            

            <a href="C_Import_R">
                <div class="boutonnavbar">
                    <span class="glyphicon glyphicon-open-file icon" aria-hidden="true"></span>
                    <span class="link-text">Import Data</span>
                </div>
            </a>
            <a href="C_ResetBase_R" id="reset-link">
                <div class="boutonnavbar">
                    <span class="glyphicon glyphicon-trash icon" aria-hidden="true"></span>
                    <span class="link-text">Reset Base</span>
                </div>
            </a>
        </div>
    </div>
    
<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('note').addEventListener('click', function(event) {
            event.preventDefault();
            document.getElementById('dropdownMenunote').classList.toggle('show');
        });

        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
            if (!event.target.closest('#note')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
            }
        }
    });
    
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('etudiant').addEventListener('click', function(event) {
            event.preventDefault();
            document.getElementById('dropdownMenuetudiant').classList.toggle('show');
        });

        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
            if (!event.target.closest('#etudiant')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
            }
        }
    });
</script>

    <div class="popup" id="popup">
        <div class="popup-content">
            <div class="popup-message"><h2>Êtes-vous sûr de vouloir réinitialiser la base de données ?</h2></div>
            <button class="popup-button" id="confirmButton">CONFIRMER</button>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById('reset-link').addEventListener('click', function(event) {
                event.preventDefault(); // Empêche la redirection immédiate
                document.getElementById('popup').style.display = 'flex'; // Affiche le popup
            });

            document.getElementById('confirmButton').addEventListener('click', function() {
                window.location.href = document.getElementById('reset-link').getAttribute('href'); // Redirige vers le lien
            });

            // Masquer le popup si on clique en dehors du contenu
            document.getElementById('popup').addEventListener('click', function(event) {
                if (event.target === this) {
                    this.style.display = 'none'; // Masque le popup
                }
            });
        });
    </script>
    
<!--            <a href="C_Notification_L">
                <div class="notification">
                    <span class="glyphicon glyphicon-bell notificationicon" aria-hidden="true"></span>
                    <span class="notificationlink-text">Notification</span>
                    <span class="notificationbadge"></span>
                </div>
            </a>-->
</html>
