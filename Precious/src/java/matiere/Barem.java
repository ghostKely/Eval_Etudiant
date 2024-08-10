/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matiere;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Barem extends Matiere {
    int idBarem;
    String codeConfig;
    String nomConfig;
    double noteEliminatoire;

    public Barem() {
    }

    public Barem(int idBarem, String codeConfig, String nomConfig, double noteEliminatoire) {
        this.idBarem = idBarem;
        this.codeConfig = codeConfig;
        this.nomConfig = nomConfig;
        this.noteEliminatoire = noteEliminatoire;
    }

    public int getIdBarem() {
        return idBarem;
    }

    public void setIdBarem(int idBarem) {
        this.idBarem = idBarem;
    }

    public double getNoteEliminatoire() {
        return noteEliminatoire;
    }

    public void setNoteEliminatoire(double noteEliminatoire) {
        this.noteEliminatoire = noteEliminatoire;
    }
    
/* USE AT getThings : 
    RECUP LE BAREM DES MATIERES DANS barem*/
    public Barem getBarem(ResultSet rs) throws SQLException {
        Barem barem = new Barem(
            Integer.parseInt(rs.getString("idBarem")),
            rs.getString("codeConfig"),
            rs.getString("nomConfig"),
            Double.parseDouble(rs.getString("noteeliminatoire"))
        );
                    
        return barem;
    } 
 
/* FONCTION POUR AVOIR LA DERNIER BAREM DANS LA TABLE Barem */
    public Barem getLastBarem(Connection connection) throws Exception {
        String request = "SELECT * FROM barem ORDER BY idbarem DESC LIMIT 1";
        Barem lastBarem = new Barem();
        Object object = this.selectWhereOneObject(connection, this, "barem", request, "getBarem");
        if (!(object instanceof Boolean)) {
            lastBarem = (Barem) object;
        }
        
        //SI VIDE ALORS ON N'A PAS EU LA DERNIER BAREM
        return lastBarem;
    }
}
