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

/**
 *
 * @author ETU1886-Fanirina
 */
public class Compensation extends Function {
    int idComprensation;
    String codeConfig;
    String nomConfig;
    int nombreMatiere;

    public Compensation() {
    }

    public Compensation(int idComprensation, String codeConfig, String nomConfig, int nombreMatiere) {
        this.idComprensation = idComprensation;
        this.codeConfig = codeConfig;
        this.nomConfig = nomConfig;
        this.nombreMatiere = nombreMatiere;
    }

    public int getIdComprensation() {
        return idComprensation;
    }

    public void setIdComprensation(int idComprensation) {
        this.idComprensation = idComprensation;
    }

    public String getCodeConfig() {
        return codeConfig;
    }

    public void setCodeConfig(String codeConfig) {
        this.codeConfig = codeConfig;
    }

    public String getNomConfig() {
        return nomConfig;
    }

    public void setNomConfig(String nomConfig) {
        this.nomConfig = nomConfig;
    }

    public int getNombreMatiere() {
        return nombreMatiere;
    }

    public void setNombreMatiere(int nombreMatiere) {
        this.nombreMatiere = nombreMatiere;
    }
    
/* USE AT getThings : 
    RECUP LE NOMBRE DE MATIERES COMPENSABLE DANS Compensation*/
    public Compensation getCompensation(ResultSet rs) throws SQLException {
        Compensation compensation = new Compensation(
            Integer.parseInt(rs.getString("idCompensation")),
            rs.getString("codeConfig"),
            rs.getString("nomConfig"),
            Integer.parseInt(rs.getString("nombrematiere"))
        );
                    
        return compensation;
    }
    
/* FONCTION POUR AVOIR LA DERNIERE COMPENSATION DANS LA TABLE Compensation */
    public Compensation getLastCompensation(Connection connection) throws Exception {
        String request = "SELECT * FROM compensation ORDER BY idcompensation DESC LIMIT 1";
        Compensation lastCompensation = new Compensation();
        Object object = this.selectWhereOneObject(connection, this, "compensation", request, "getCompensation");
        if (!(object instanceof Boolean)) {
            lastCompensation = (Compensation) object;
        }
        
        //SI VIDE ALORS ON N'A PAS EU LA DERNIERE COMPENSATION
        return lastCompensation;
    }
}
