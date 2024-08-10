/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import etudiant.Etudiant;
import function.Function;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ETU1886-Fanirina
 */
public class ProfilUser extends Function {
    Etudiant etudiant;

    public ProfilUser() {
    }

    public ProfilUser(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public ProfilUser(String etu) {
        Etudiant etudiant = new Etudiant();
            etudiant.setEtu(etu);
        this.setEtudiant(etudiant);
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    
/* USE AT getThings : 
    RECUP LES ETU DANS Etudiant*/
    public ProfilUser getProfilUser(ResultSet rs) throws SQLException {
        Etudiant etudiant = new Etudiant();
            etudiant.setEtu(rs.getString("etu"));
                    
        ProfilUser profilUser = new ProfilUser(etudiant);
        
        return profilUser;
    }
    
/* FONCTION POUR CHECKER ETU AU LOGIN */
    public boolean checkEtudiant(Connection connection, String etu) throws Exception {
        boolean doesUserExist = true;
        String request = "SELECT etu FROM etudiant WHERE etu ='"+etu+"'";
        Object etudiantBase = this.selectWhereOneObject(connection, this, "etudiant", request, "getProfilUser");
        if (etudiantBase instanceof Boolean) {
            doesUserExist = false;
            return doesUserExist;
        } else {
            ProfilUser profilEtudiant = (ProfilUser) etudiantBase;
            boolean checkEtu = true;
            if (etu.equalsIgnoreCase(profilEtudiant.getEtudiant().getEtu()) == false) {
                checkEtu = false;
                return checkEtu;
            } else {
                return checkEtu;
            }
        }
    }
}
