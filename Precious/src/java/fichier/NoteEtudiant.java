/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichier;

import function.Function;
import java.sql.Connection;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ETU1886-Fanirina
 */
public class NoteEtudiant extends Fichier {
    int idNoteEtudiant;
    String numETU;
    String nom;
    String prenom;
    String genre;
    LocalDate dateNaissance;
    String promotion;
    String codeMatiere;
    String semestre;
    double note;

    public NoteEtudiant() {
    }

    public NoteEtudiant(int idNoteEtudiant, String numETU, String nom, String prenom, String genre, LocalDate dateNaissance, String promotion, String codeMatiere, String semestre, double note) {
        this.idNoteEtudiant = idNoteEtudiant;
        this.numETU = numETU;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.promotion = promotion;
        this.codeMatiere = codeMatiere;
        this.semestre = semestre;
        this.note = note;
    }

    public NoteEtudiant(String numETU, String nom, String prenom, String genre, LocalDate dateNaissance, String promotion, String codeMatiere, String semestre, double note) {
        this.numETU = numETU;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.promotion = promotion;
        this.codeMatiere = codeMatiere;
        this.semestre = semestre;
        this.note = note;
    }

    public int getIdNoteEtudiant() {
        return idNoteEtudiant;
    }

    public void setIdNoteEtudiant(int idNoteEtudiant) {
        this.idNoteEtudiant = idNoteEtudiant;
    }

    public String getNumETU() {
        return numETU;
    }

    public void setNumETU(String numETU) {
        this.numETU = numETU;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
 /* FONCTION POUR AVOIR LA REQUETE POUR INSERTION DE DATA IMPORT */
    public String insertNoteEtudiant() {
        String dateNaissance = this.dateToString(this.getDateNaissance(), null);
        String request = "INSERT INTO NoteEtudiant (numETU, nom, prenom, genre, dateNaissance, promotion, codeMatiere, semestre, note) VALUES \n" +
            "('"+this.getNumETU()+"', \n" +
            "'"+this.getNom()+"', \n" +
            "'"+this.getPrenom()+"', \n" +
            "'"+this.getGenre()+"', \n" +
            "'"+dateNaissance+"', \n" +
            "'"+this.getPromotion()+"', \n" +
            "'"+this.getCodeMatiere()+"', \n" +
            "'"+this.getSemestre()+"', \n" +
            this.getNote()+" \n" +
            ")";
        
        return request;
    }
    
/* CRAFT LES NOTEETUDIANTS DE L'IMPORT POUR LES INSERER DANS LA BASE */
    public List<NoteEtudiant> craftNoteEtudiant(Fichier fichier) throws ParseException {
        List<NoteEtudiant> listeEtudiant = new ArrayList<NoteEtudiant>();
        
        for (String[] data : fichier.getData()) {
            String numETU = data[0].trim();
            String nom = data[1].trim();
            String prenom = data[2].trim();
            String genre = data[3].trim();
            LocalDate dateNaissance = (LocalDate) this.stringToDate(null, null, null, data[4].trim());
            String promotion = data[5].trim();
            String codeMatiere = data[6].trim();
            String semestre = data[7].trim();
            double note = Double.parseDouble(data[8].trim().replace(",", "."));
            NoteEtudiant noteEtudiant = new NoteEtudiant(numETU, nom, prenom, genre, dateNaissance, promotion, codeMatiere, semestre, note);
            listeEtudiant.add(noteEtudiant);
        }
        return listeEtudiant;
    }
    
/* FONCTION POUR CHECKER LES INSERTIONS DE LA TABLE ConfigurationNote */
    public boolean checkInsertNoteEtudiant(Connection connection, Fichier fichier) throws Exception {
        boolean check = true;
        List<NoteEtudiant> listeNoteEtudiant = this.craftNoteEtudiant(fichier);
        for (NoteEtudiant noteEtudiant : listeNoteEtudiant) {
            boolean checkInsert = noteEtudiant.save(connection, noteEtudiant, "noteEtudiant", noteEtudiant.insertNoteEtudiant());
            if (checkInsert == false) {
                check = false;
                return check;
            }
        }
        return check;
    }

/* FONCTION POUR EFFECTUER LES DISPATCHS DE NOTE ETUDIANT */    
    public boolean dispatchNoteEtudiant(Connection connection, Fichier fichier) throws Exception {
        boolean isDispatched = true;
        
        boolean checkInsertNoteEtudiant = this.checkInsertNoteEtudiant(connection, fichier);
        if (checkInsertNoteEtudiant == true) {
            Dispatch_NoteEtudiant dispatchNoteEtudiant = new Dispatch_NoteEtudiant();
                boolean checkDispatch = dispatchNoteEtudiant.checkDispatchNoteEtudiant(connection);
                if (checkDispatch == false) {
                    isDispatched = false;
                    return isDispatched;
                }
        } else {
            return false;
        }
        return isDispatched;
    }
}
