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
public class ConfigNote extends Fichier {
    int configNote;
    String code;
    String config;
    double valeur;

    public ConfigNote() {
    }

    public ConfigNote(int configNote, String code, String config, double valeur) {
        this.configNote = configNote;
        this.code = code;
        this.config = config;
        this.valeur = valeur;
    }

    public ConfigNote(String code, String config, double valeur) {
        this.code = code;
        this.config = config;
        this.valeur = valeur;
    }

    public int getConfigNote() {
        return configNote;
    }

    public void setConfigNote(int configNote) {
        this.configNote = configNote;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    
 /* FONCTION POUR AVOIR LA REQUETE POUR INSERTION DE DATA IMPORT */
    public String insertConfigNote() {
        String request = "INSERT INTO configurationNote (code, config, valeur) VALUES \n" +
            "('"+this.getCode()+"', '"+this.getConfig()+"', "+this.getValeur()+") ";
        return request;
    }
    
/* CRAFT LES ConfigNoteS DE L'IMPORT POUR LES INSERER DANS LA BASE */
    public List<ConfigNote> craftConfigNote(Fichier fichier) {
        List<ConfigNote> listeConfigNote = new ArrayList<ConfigNote>();
        
        for (String[] data : fichier.getData()) {
            String code = data[0].trim();
            String config = data[1].trim();
            double valeur = Double.parseDouble(data[2].trim().replace(",", "."));
            ConfigNote configNote = new ConfigNote(code, config, valeur);
            listeConfigNote.add(configNote);
        }
        return listeConfigNote;
    }

/* FONCTION POUR CHECKER LES INSERTIONS DE LA TABLE ConfigurationNote */
    public boolean checkInsertConfigNote(Connection connection, Fichier fichier) throws Exception {
        boolean check = true;
        List<ConfigNote> listeConfigNote = this.craftConfigNote(fichier);
        for (ConfigNote configNote : listeConfigNote) {
            boolean checkInsert = configNote.save(connection, configNote, "configurationNote", configNote.insertConfigNote());
            if (checkInsert == false) {
                check = false;
                return check;
            }
        }
        return check;
    }
    
/* FONCTION POUR EFFECTUER LES DISPATCHS DE NOTE ETUDIANT */    
    public boolean dispatchConfigNote(Connection connection, Fichier fichier) throws Exception {
        boolean isDispatched = true;
        
        boolean checkInsertConfigNote = this.checkInsertConfigNote(connection, fichier);
        if (checkInsertConfigNote == true) {
            Dispatch_ConfigNote dispatchConfigNote = new Dispatch_ConfigNote();
                boolean checkDispatch = dispatchConfigNote.checkDispatchConfigNote(connection);
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
