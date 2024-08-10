/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matiere;

import function.Function;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import semestre.Semestre;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Matiere extends Function {
    int idMatiere;
    String code;
    double credit;
    String nom;
    boolean optionnelle;
    Semestre semestre;
    
    String statut;
    double creditAzo;
    
    public Matiere() {
    }

    public Matiere(int idMatiere, String code, double credit, String nom, boolean optionnelle, Semestre semestre) {
        this.idMatiere = idMatiere;
        this.code = code;
        this.credit = credit;
        this.nom = nom;
        this.optionnelle = optionnelle;
        this.semestre = semestre;
    }
    
    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public double getCreditAzo() {
        return creditAzo;
    }

    public void setCreditAzo(double creditAzo) {
        this.creditAzo = creditAzo;
    }

    public boolean isOptionnelle() {
        return optionnelle;
    }

    public void setOptionnelle(boolean optionnelle) {
        this.optionnelle = optionnelle;
    }
    
/* USE AT getThings : 
    RECUP LES MATIERES DANS matiere*/
    public Matiere getMatiere(ResultSet rs) throws SQLException {
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
                    
        return matiere;
    } 
  
/* USE AT getListMatiereForAddNote : 
    RECUP LES MATIERES DANS matiere*/
    public Matiere getMatiereForAddNote(ResultSet rs) throws SQLException {
        Semestre semestre = new Semestre(
            Integer.parseInt(rs.getString("idSemestre")), 
            rs.getString("semestrenom")
        );
        
        Matiere matiere = new Matiere();
            matiere.setIdMatiere(Integer.parseInt(rs.getString("idMatiere")));
            matiere.setCode(rs.getString("code"));
            matiere.setNom(rs.getString("matierenom"));
            matiere.setSemestre(semestre);
                    
        return matiere;
    } 
    
/* FONCTION POUR AVOIR LA LISTE DES MATIERES DANS LA TABLE matiere */
    public List<Matiere> getListMatiereForAddNote(Connection connection) throws Exception {
        String request = "SELECT idMatiere, code, matierenom, idSemestre, semestrenom FROM v_matiere ORDER BY code";
        ArrayList<Object> listeMatiereBase = this.selectWhere(connection, this, "matiere", request, "getMatiereForAddNote");
        List<Matiere> listeMatiere = new ArrayList<Matiere>();
        for (Object object : listeMatiereBase) {
            listeMatiere.add((Matiere) object);
        }
        return listeMatiere;
    }
}
