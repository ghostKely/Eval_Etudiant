/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestre;

import function.Function;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Semestre extends Function {
    int idSemestre;
    String nom;
    
    String statutSemestre;
    boolean semestrePassed;
    double moyenneSemestre;
    
    public Semestre() {
    }

    public Semestre(int idSemestre, String nom) {
        this.idSemestre = idSemestre;
        this.nom = nom;
    }

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatutSemestre() {
        return statutSemestre;
    }

    public void setStatutSemestre(String statutSemestre) {
        this.statutSemestre = statutSemestre;
    }

    public double getMoyenneSemestre() {
        return moyenneSemestre;
    }

    public void setMoyenneSemestre(double moyenneSemestre) {
        this.moyenneSemestre = moyenneSemestre;
    }

    public boolean isSemestrePassed() {
        return semestrePassed;
    }

    public void setSemestrePassed(boolean semestrePassed) {
        this.semestrePassed = semestrePassed;
    }

/* USE AT getThings : 
    RECUP LE SEMESTRE DES MATIERES DANS Semestre*/
    public Semestre getSemestre(ResultSet rs) throws SQLException {
        Semestre semestre = new Semestre(
            Integer.parseInt(rs.getString("idSemestre")), 
            rs.getString("nom")
        );
                    
        return semestre;
    }
    
/* USE AT getSemestreEtudiantFromNote : 
    RECUP LE SEMESTRE DES MATIERES DANS v_note*/
    public Semestre getSemestreNote(ResultSet rs) throws SQLException {
        Semestre semestre = new Semestre(
            Integer.parseInt(rs.getString("idSemestre")), 
            rs.getString("semestrenom")
        );
                    
        return semestre;
    }
    
/* FONCTION POUR AVOIR LES SEMESTRES DANS LESQUELLES L'ETUDIANT A DES NOTES */
    public List<Semestre> getSemestreEtudiantFromNote(Connection connection, String etu) throws Exception {
        String request = "SELECT DISTINCT idsemestre, semestrenom FROM v_note WHERE etu = '"+etu+"' ORDER BY idSemestre";
        ArrayList<Object> listeSemestreBase = this.selectWhere(connection, this, "v_note", request, "getSemestreNote");
        List<Semestre> listeSemestre = new ArrayList<Semestre>();
        for (Object object : listeSemestreBase) {
            listeSemestre.add((Semestre) object);
        }
        return listeSemestre;
    }
}
