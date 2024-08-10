/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichier;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Dispatch_NoteEtudiant extends NoteEtudiant {

    public Dispatch_NoteEtudiant() {
    }
    
/* FONCTION POUR AVOIR LA REQUEST DE DISPATCH POUR LA TABLE PROMOTION */
    public String dispatchPromotion() {
        String insertPromotion = "INSERT INTO Promotion (nom) \n" +
            "SELECT DISTINCT promotion \n" +
            "FROM noteEtudiant";
        return insertPromotion;
    }
    
/* FONCTION POUR AVOIR LA REQUEST DE DISPATCH POUR LA TABLE GENRE */
    public String dispatchGenre() {
        String insertGenre = "INSERT INTO Genre (nom) \n" +
            "SELECT DISTINCT genre\n" +
            "FROM noteEtudiant";
        return insertGenre;
    }
    
/* FONCTION POUR AVOIR LA REQUEST DE DISPATCH POUR LA TABLE ETUDIANT */
    public String dispatchEtudiant() {
        String insertEtudiant = "INSERT INTO Etudiant (etu, nom, prenom, dtn, idgenre, idpromotion)\n" +
            "SELECT DISTINCT\n" +
            "    ne.numetu,\n" +
            "    ne.nom,\n" +
            "    ne.prenom,\n" +
            "    ne.dateNaissance,\n" +
            "    g.idGenre,\n" +
            "    p.idPromotion\n" +
            "FROM noteetudiant ne\n" +
            "LEFT JOIN Genre g ON ne.genre = g.nom\n" +
            "LEFT JOIN Promotion p ON ne.promotion = p.nom\n" +
            "ORDER BY ne.numetu";
        return insertEtudiant;
    }

/* FONCTION POUR AVOIR LA REQUEST DE DISPATCH POUR LA TABLE NOTE */
    public String dispatchNote() {
        String insertEtudiant = "INSERT INTO Note (note, idmatiere, etu) \n" +
            "SELECT\n" +
            "    ne.note,\n" +
            "    mat.idMatiere,\n" +
            "    etu.etu\n" +
            "FROM noteEtudiant ne\n" +
            "LEFT JOIN Matiere mat ON ne.codematiere = mat.code\n" +
            "LEFT JOIN Etudiant etu ON ne.numetu = etu.etu\n" +
            "ORDER BY etu, idMatiere";
        return insertEtudiant;
    }

/* FONCTION POUR AVOIR LA LISTE DES DISPATCH A FAIRE DANS LES TABLES */
    public List<String> getDispatchListe() {
        String insertPromotion = this.dispatchPromotion();
        String insertGenre = this.dispatchGenre();
        String insertEtudiant = this.dispatchEtudiant();
        String insertNote = this.dispatchNote();
        
        List<String> listeDispatch = new ArrayList<String>();
            listeDispatch.add(insertPromotion);
            listeDispatch.add(insertGenre);
            listeDispatch.add(insertEtudiant);
            listeDispatch.add(insertNote);
            
        return listeDispatch;
    }

/* FONCTION POUR CHECK SI LES DISPATCHS SONT DONE */
    public boolean checkDispatchNoteEtudiant(Connection connection) throws Exception {
        boolean checkDispatch = true;
        
        List<String> listeDispatch = this.getDispatchListe();
        for (String request : listeDispatch) {
            boolean check = this.executeQuery(connection, request);
            if (check == false) {
                checkDispatch = false;
                return checkDispatch;
            }
        }
        return checkDispatch;
    }
}
