/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichier;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import function.Function;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Fichier extends Function{
    String nomTable;
    List<String> champColonne;
    List<String[]> data;

    public Fichier() {
    }

    public Fichier(Part filePart, String nomTable) throws Exception {
        this.setNomTable(nomTable.trim());
        this.setFichier(filePart);
    }

    public Fichier(String nomTable) {
        this.nomTable = nomTable;
    }
    
    public List<String> getChampColonne() {
        return champColonne;
    }

    public void setChampColonne(List<String> champColonne) {
        this.champColonne = champColonne;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }

    public String getNomTable() {
        return nomTable;
    }

    public void setNomTable(String nomTable) {
        this.nomTable = nomTable;
    }

/* FONCTION POUR AVOIR UN FICHIER AYANT LES NOMS DES COLONNES ET LES DATAS */
    public Fichier setFichier(Part filePart) throws Exception { //PART DANS SERVLET
        String fileName = this.getFileName(filePart);   //PREND LE NOM DU FICHIER
        InputStream fileContent = filePart.getInputStream();
        Fichier fi = new Fichier(); //INITIE FICHIER 
        
        if (fileName.endsWith(".csv")) {    //TESTE SI C'EST BIEN UN FICHIER CSV
           fi = this.fichierCsv(fileContent);   //RETURN FICHIER CONTENANT LES 
           this.setChampColonne(fi.getChampColonne());  //SET LES COLONNES DANS LE FICHIER INITIER
           this.setData(fi.getData());  //SET LES DATAS DANS LE FICHIER INITIER 
        }
        
        return fi;  
    }

/* FONCTION POUR AVOIR LE NOM D'UN FICHIER */
    public String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

/* FONCTION QUI PREND TOUTES LES VARIABLES DANS LE CSV 
    1re LIGNE : NOMS COLONNES 
    RESTE : DATA*/
    public Fichier fichierCsv(InputStream fileContent) throws IOException, CsvException {
        Fichier fichier = new Fichier();
            
        InputStreamReader isr = new InputStreamReader(fileContent);
        char separator = ',';
        CSVReader reader = new CSVReaderBuilder(isr).withSkipLines(0).withCSVParser(new CSVParserBuilder().withSeparator(separator).build()).build();   //SEPARE LES DATA PAR SEPARATEUR

        List<String[]> data = reader.readAll();
        
        List<String> champColonne = new ArrayList<String>();
        String[] attribut = data.get(0);    //AVOIR LES COLONNES DES DATAS
        for (int i = 0; i < attribut.length; i++) {
            champColonne.add(attribut[i]);
        }
        fichier.setChampColonne(champColonne);  //SET COLONNE
       
        List<String[]> donnee = new ArrayList<String[]>();  //PREND LES DATAS
        for (int i = 1; i < data.size(); i++) {
            donnee.add(data.get(i));
        }
        fichier.setData(donnee);    // SET LES DATAS
        
        return fichier;
    }
    
/* FONCTION POUR CHECK IMPORT SI REUSSI OR NOT */
    public boolean checkImport(Connection connection, Fichier fichierNoteEtudiant, Fichier fichierConfigNote) throws Exception {
        NoteEtudiant note = new NoteEtudiant();
        boolean dispatchNoteEtudiant = note.dispatchNoteEtudiant(connection, fichierNoteEtudiant);
        
        ConfigNote configNote = new ConfigNote();
        boolean dispatchConfigNote = configNote.dispatchConfigNote(connection, fichierConfigNote);
        
        boolean checkImport = true;
        if (dispatchNoteEtudiant == false || dispatchConfigNote == false) {
            checkImport = false;
            return checkImport;
        }
        return checkImport;
    }
}


