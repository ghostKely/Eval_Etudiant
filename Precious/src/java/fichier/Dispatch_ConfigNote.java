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
public class Dispatch_ConfigNote extends ConfigNote {

    public Dispatch_ConfigNote() {
    }
    
/* FONCTION POUR AVOIR LA REQUEST DE DISPATCH POUR LA TABLE BAREM */    
    public String dispatchBarem() {
        String insertBarem = "INSERT INTO barem (codeConfig, nomconfig, noteEliminatoire) \n" +
            "SELECT \n" +
            "    conf.code,\n" +
            "    conf.config,\n" +
            "    conf.valeur\n" +
            "FROM configurationNote conf\n" +
            "WHERE code = 'CONF1'";
        return insertBarem;
    }
    
/* FONCTION POUR AVOIR LA REQUEST DE DISPATCH POUR LA TABLE COMPENSATION */    
    public String dispatchCompensation() {
        String insertBarem = "INSERT INTO Compensation (codeConfig, nomconfig, nombreMatiere) \n" +
            "SELECT \n" +
            "    conf.code,\n" +
            "    conf.config,\n" +
            "    conf.valeur\n" +
            "FROM configurationNote conf\n" +
            "WHERE code = 'CONF2'";
        return insertBarem;
    }
    
/* FONCTION POUR AVOIR LA LISTE DES DISPATCH A FAIRE DANS LES TABLES */
    public List<String> getDispatchListe() {
        String insertBarem = this.dispatchBarem();
        String insertCompensation = this.dispatchCompensation();
        
        List<String> listeDispatch = new ArrayList<String>();
            listeDispatch.add(insertBarem);
            listeDispatch.add(insertCompensation);
            
        return listeDispatch;
    }

/* FONCTION POUR CHECK SI LES DISPATCHS SONT DONE */
    public boolean checkDispatchConfigNote(Connection connection) throws Exception {
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
