<%-- 
    Document   : Note_Annee
    Created on : 30 juil. 2024, 15:18:19
    Author     : ETU1886-Fanirina
--%>

<%@page import="semestre.Semestre"%>
<%@page import="note.Note"%>
<%@page import="etudiant.Etudiant"%>
<%@page import="java.util.List"%>
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
        List<Etudiant> listeEtudiant = (List<Etudiant>) request.getAttribute("liste_Etudiant");
        Etudiant etudiant1 = listeEtudiant.get(0);
            List<Note> listeNote1 = etudiant1.getNoteEtudiant();
            Semestre semestre1 = listeNote1.get(0).getMatiere().getSemestre();
            
        Etudiant etudiant2 = listeEtudiant.get(1);
            List<Note> listeNote2 = etudiant2.getNoteEtudiant();
            Semestre semestre2 = listeNote2.get(0).getMatiere().getSemestre();
    %>
    <div class="col-md-9">
        <div>
            <h2>Détail des notes</h2>
            <div class="info">
                <div class="data">Nom :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant1.getNom() %></div>
            </div>
            <div class="info">
                <div class="data">Prénom :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant1.getPrenom() %></div>
            </div>
            <div class="info">
                <div class="data">Né(e) le :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant1.getDtn() %></div>
            </div>
            <div class="info">
                <div class="data">Numéro d'inscription :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant1.getEtu() %></div>
            </div>
            
            <div class="info">
                <h4>Notes du semestre : <%= semestre1.getNom() %></h4>
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
                    <% for (Note note : listeNote1) { %>
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
                <h4>Résultats <%= semestre1.getNom() %></</h4>
            </div>
            <div class="info">
                <div class="data">Crédits :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant1.getTotalCredit() %></div>
            </div>
            <div class="info">
                <div class="data">Moyenne générale :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant1.getMoyenne() %></div>
            </div>
            <div class="info">
                <div class="data">Statut :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant1.getStatutEtudiant() %></div>
            </div>
            
            
            <div class="info">
                <h4>Notes du semestre : <%= semestre2.getNom() %></h4>
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
                    <% for (Note note : listeNote2) { %>
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
                <h4>Résultats <%= semestre2.getNom() %></</h4>
            </div>
            <div class="info">
                <div class="data">Crédits :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant2.getTotalCredit() %></div>
            </div>
            <div class="info">
                <div class="data">Moyenne générale :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant2.getMoyenne() %></div>
            </div>
            <div class="info">
                <div class="data">Statut :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= etudiant2.getStatutEtudiant() %></div>
            </div>
            
                <% Etudiant mix = etudiant1.getMix(etudiant1, etudiant2); 
                String[] colors = mix.getColor(); %>
            
            <div class="info">
                <h4>Résultats Globale</</h4>
            </div>
            <div class="info">
                <div class="data">Moyenne globale :  </div>
                <div class="point">................................................</div>
                <div class="data"><%= mix.getMoyenneAnnee() %></div>
            </div>
            <div class="info">
                <div class="data">Statut :  </div>
                <div class="point">................................................</div>
                <div class="data" style="color: <%= colors[1] %>"><%= colors[0] %></div>
            </div>
        </div>
    </div>
            
    <script>
        function submitForm() {
            document.getElementById('pageToGo').submit();
        }
    </script>
</html>
