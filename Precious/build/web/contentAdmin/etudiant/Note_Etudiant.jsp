<%-- 
    Document   : Note_Etudiant
    Created on : 28 juil. 2024, 11:16:51
    Author     : ETU1886-Fanirina
--%>

<%@page import="semestre.Semestre"%>
<%@page import="etudiant.Etudiant"%>
<%@page import="java.util.List"%>
<%@page import="note.Note"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .info {
            font-size: 16px;
            display: flex;
            justify-content: space-between;
            align-items: baseline;
            margin-top: 10px;
        }
        
        .data {
            width: 30%;
        }
        
        .point {
            width: 30%;
            margin-left: -70px;
        }
    </style>
    <% 
        Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
        List<Note> listeNote = etudiant.getNoteEtudiant();
        Semestre semestre = listeNote.get(0).getMatiere().getSemestre();
    %>
    <div class="col-md-9" id="dataToExport">
        <div>
            <h2>Détail des notes</h2>
        <%
            boolean isAdmin = (Boolean) session.getAttribute("admin"); 
            if (isAdmin == true) {
        %>    
            <div class="info">
                <div class="col-md-10 paginationselect">
                    <span><h4>Semestre : </h4></span>
                    <span>
                        <% int semestreSelected = (Integer) request.getAttribute("semestreSelected"); %>
                        <form id="pageToGo" action="C_EtudiantNote_R" method="POST">           
                            <select class="selectpage" name="semestre" onchange="submitForm()">
                                <% 
                                    List<Semestre> listeSemestre = (List<Semestre>) request.getAttribute("liste_Semestre");
                                    for (Semestre oneSemestre : listeSemestre) {
                                        if (oneSemestre.getIdSemestre() == semestreSelected) {
                                %>
                                    <option value="<%= oneSemestre.getIdSemestre() %>" selected><%= oneSemestre.getNom() %></option>
                                    <% } else { %>
                                    <option value="<%= oneSemestre.getIdSemestre() %>"><%= oneSemestre.getNom() %></option>
                                <% } } %>
                            </select>
                            <input type="hidden" value="<%= etudiant.getEtu() %>" name="etudiant">
                        </form>
                    </span>
                </div>
            </div>
        <% } %>

            <div class="info">
                <div class="data">Nom :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant.getNom() %></div>
            </div>
            <div class="info">
                <div class="data">Prénom :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant.getPrenom() %></div>
            </div>
            <div class="info">
                <div class="data">Né(e) le :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant.getDtn() %></div>
            </div>
            <div class="info">
                <div class="data">Numéro d'inscription :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant.getEtu() %></div>
            </div>
            <div class="info">
                <h4>Notes du semestre : <%= semestre.getNom() %></h4>
            </div>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th>Crédits de base</th>
                        <th>UE</th>
                        <th>Intitulé</th>
                        <th>Crédits Azo</th>
                        <th>Note/20</th>
                        <th>Résultat</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Note note : listeNote) { %>
                        <tr class="ligne">
                            <td><%= note.getMatiere().getCredit() %></td>
                            <td><%= note.getMatiere().getCode() %></td>
                            <td><%= note.getMatiere().getNom() %></td>
                            <td><%= note.getMatiere().getCreditAzo()%></td>
                            <td><%= note.getNote() %></td>
                            <td><%= note.getMatiere().getStatut() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
            <div class="info">
                <h4>Résultats</h4>
            </div>
            <div class="info">
                <div class="data">Crédits :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant.getTotalCredit() %></div>
            </div>
            <div class="info">
                <div class="data">Moyenne générale :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant.getMoyenne() %></div>
            </div>
            <div class="info">
                <div class="data">Statut :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant.getStatutEtudiant() %></div>
            </div>
        </div>
    </div>
            
    <script>
        function submitForm() {
            document.getElementById('pageToGo').submit();
        }
    </script>
</html>
