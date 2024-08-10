/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package note;

import etudiant.Etudiant;
import etudiant.Promotion;
import function.Function;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import matiere.Barem;
import matiere.Matiere;
import semestre.Semestre;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Note extends Function {
    int idNote;
    double note;
    Matiere matiere;
    Etudiant etudiant;
    
    public Note() {
    }

    public Note(int idNote, double note, Matiere matiere, Etudiant etudiant) {
        this.idNote = idNote;
        this.note = note;
        this.matiere = matiere;
        this.etudiant = etudiant;
    }

    public Note(double note, Matiere matiere, Etudiant etudiant) {
        this.note = note;
        this.matiere = matiere;
        this.etudiant = etudiant;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public double getNote() {
        return note;
    }
    
    public void setNote(double note) {
        this.note = note;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    
/* FONCTION POUR AVOIR LA REQUETE D'INSERTION D'UNE ENTREE DE NOTE */ 
    public String insertNote() {
        String request = "INSERT INTO note (note, idMatiere, etu) VALUES \n" +
            "("+this.getNote()+", "+this.getMatiere().getIdMatiere()+", 'ETU"+this.getEtudiant().getEtu()+"')";
        return request;
    }

/* FONCTON POUR ENREGISTRER LA NOTE DANS LA BASE */
    public boolean saveNote(Connection connection, double note, int idMatiere, String etu) throws Exception {
        Matiere matiere = new Matiere();
            matiere.setIdMatiere(idMatiere);
        
        Etudiant etudiant = new Etudiant();
            etudiant.setEtu(etu);
        
        Note noteInsert = new Note();
            noteInsert.setNote(note);
            noteInsert.setMatiere(matiere);
            noteInsert.setEtudiant(etudiant);
            
        boolean save = this.save(connection, note, "note", noteInsert.insertNote());
        return save;
    }

/* Use at getNoteByEtudiantAndSemestre :
    FONCTION POUR AVOIR LA LISTE DES NOTES D'UN ETUDIANT DANS LA VIEW v_note */    
    public Note getNote(ResultSet rs) throws SQLException {
        Semestre semestre = new Semestre(
            Integer.parseInt(rs.getString("idSemestre")), 
            rs.getString("semestrenom")
        );
        
        Matiere matiere = new Matiere(
            Integer.parseInt(rs.getString("idMatiere")), 
            rs.getString("code"), 
            Double.parseDouble(rs.getString("credit")), 
            rs.getString("matierenom"),
            rs.getBoolean("optionnelle"),
            semestre
        );
        
        Etudiant etudiant = new Etudiant();
            etudiant.setIdEtudiant(Integer.parseInt(rs.getString("idetudiant")));
            etudiant.setEtu(rs.getString("etu"));
        
        Note note = new Note(
            Double.parseDouble(rs.getString("note")), 
            matiere, 
            etudiant
        );
        
        return note;
    }
    
/* FONCTION POUR SETTER LES STATUS DE CHAQUE MATIERE */ 
    public Note setStatutMatiere(double moyenne, Note note) {
            if (note.getNote() >= 16) {
                String appreciation = "Très bien";
                Matiere matiere = note.getMatiere();
                    matiere.setStatut(appreciation);
            } else if (note.getNote() >= 14 && note.getNote() < 16) {
                String appreciation = "Bien";
                Matiere matiere = note.getMatiere();
                    matiere.setStatut(appreciation);
            } else if (note.getNote() >= 13 && note.getNote() < 14) {
                String appreciation = "Assez Bien";
                Matiere matiere = note.getMatiere();
                    matiere.setStatut(appreciation);
            } else if (note.getNote() >= 10 && note.getNote() < 13) {
                String appreciation = "Passable";
                Matiere matiere = note.getMatiere();
                    matiere.setStatut(appreciation);
            }
            
        if (moyenne < 10) {
            if (note.getNote() < 10) {
                String appreciation = "Ajourné";
                Matiere matiere = note.getMatiere();
                    matiere.setStatut(appreciation);
            }
        } else if (moyenne >= 10) {
            if (note.getNote() <= 6) {
                String appreciation = "Ajourné";
                Matiere matiere = note.getMatiere();
                    matiere.setStatut(appreciation);
            } else if (note.getNote() > 6 && note.getNote() < 10) {
                String appreciation = "Passable";
                Matiere matiere = note.getMatiere();
                    matiere.setStatut(appreciation);
            }
        }
        return note;
    }
    
/* FONCTION POUR AVOIR LA NOTE MAX DANS LES MATIERES OPTIONNELLES */
    public Note getNoteMaxOptionnelle(Connection connection, String etu, int idSemestre, String codeMatiere) throws Exception {
        String request = "SELECT *\n" +
            "FROM v_note\n" +
            "WHERE\n" +
            "    idsemestre = "+idSemestre+" AND\n" +
            "    etu = '"+etu+"' AND\n" +
            "    optionnelle = TRUE AND\n" +
            "    code LIKE '%"+codeMatiere+"%' \n" +
            "ORDER BY note DESC\n" +
            "LIMIT 1";
        Object noteMaxBase = this.selectWhereOneObject(connection, this, "v_note", request, "getNote");
        Note noteMax = new Note();
        if (!(noteMaxBase instanceof Boolean)) {
            noteMax = (Note) noteMaxBase;
        }
        
        //SI VIDE C'EST QU'IL N'Y A PAS DE MATIERE OPTIONNELLE
        return noteMax;
    }

/* FOCNTION POUR AVOIR LES NOTES D'UN ETUDIANT SELON SON ETU ET LE SEMESTRE*/
    public List<Note> getNoteOptionnelle(Connection connection, String etu, int idSemestre, String[] codeMatiere) throws Exception {
        List<Note> listeNote = new ArrayList<Note>();
        for (String code : codeMatiere) {
            Note noteMaxOptionnelle = this.getNoteMaxOptionnelle(connection, etu, idSemestre, code);
            listeNote.add(noteMaxOptionnelle);
        }
        return listeNote;
    }

/* FOCNTION POUR AVOIR LES NOTES D'UN ETUDIANT SELON SON ETU ET LE SEMESTRE */
    public List<Note> getNoteByEtudiantAndSemestre(Connection connection, String etu, int idSemestre) throws Exception {
        String request = "SELECT\n" +
            "    idMatiere,\n" +
            "    code,\n" +
            "    credit,\n" +
            "    matierenom,\n" +
            "    optionnelle,\n" +
            "    MAX(note) AS note,\n" +
            "    idsemestre,\n" +
            "    semestrenom,\n" +
            "    idetudiant,\n" +
            "    etu,\n" +
            "    idpromotion,\n" +
            "    promotionnom\n" +
            "FROM v_note\n" +
            "WHERE \n" +
            "    etu = '"+etu+"' AND \n" +
            "    idsemestre = "+idSemestre+" AND \n" +
            "    optionnelle = FALSE\n" +
            "GROUP BY \n" +
            "    idMatiere,\n" +
            "    code,\n" +
            "    credit,\n" +
            "    matierenom,\n" +
            "    optionnelle,\n" +
            "    idsemestre,\n" +
            "    semestrenom,\n" +
            "    idetudiant,\n" +
            "    etu,\n" +
            "    idpromotion,\n" +
            "    promotionnom\n" +
            "ORDER BY idMatiere";
        ArrayList<Object> listeNoteBase = this.selectWhere(connection, this, "v_note", request, "getNote");
        List<Note> listeNote = new ArrayList<Note>();
        for (Object object : listeNoteBase) {
            listeNote.add((Note) object);
        }
        
        //LES MATIERES OPTIONNELLES 
        List<Note> noteMatiereOptionnelle = new ArrayList<Note>();
        if (idSemestre == 4) {
            String[] codeS4 = { "INF", "MTH" };
            noteMatiereOptionnelle = this.getNoteOptionnelle(connection, etu, idSemestre, codeS4);
        } else if (idSemestre == 6) {
            String[] codeS6 = { "INF" };
            noteMatiereOptionnelle = this.getNoteOptionnelle(connection, etu, idSemestre, codeS6);
        }
        
        if (noteMatiereOptionnelle.isEmpty() == false) {
            for (Note note : noteMatiereOptionnelle) {
                listeNote.add(note);
            }
        }
        
        return listeNote;
    }
    
}

