/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import etudiant.Etudiant;
import java.util.List;
import matiere.Barem;
import matiere.Compensation;
import note.Note;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            double moyenneMatiere = 10;
            double moyenneGeneralNeeded = 10;
            String[] appreciation = { "Ajourné", "Pass", "Compensé" };
            Etudiant etudiant = new Etudiant();
            List<Etudiant> listeEtudiant = etudiant.getListeEtudiant(null);
                listeEtudiant = etudiant.getEtudiantWithoutLicence(null, listeEtudiant, appreciation, moyenneMatiere, moyenneGeneralNeeded);
                for (Etudiant etudiant1 : listeEtudiant) {
                    System.out.println(etudiant1.isStatutLicence());
                }
//            Compensation compensation = new Compensation();
//            compensation = compensation.getLastCompensation(null);
//            
//            Barem barem = new Barem();
//            barem = barem.getLastBarem(null);
//            
//            String etu = "ETU026";
//            int idSemestre = 1;
//            Etudiant etudiant = new Etudiant();
//                etudiant.setBarem(barem);
//                etudiant.setCompensation(compensation);
//                etudiant = etudiant.getEtudiantByEtuForNote(null, etu);
////                etudiant = etudiant.setMoyenneEtudiant(null, etudiant, idSemestre);
//            System.out.println(etudiant.getMoyenne());
//            System.out.println(etudiant.getStatutEtudiant());
//            System.out.println(etudiant.getTotalCredit());
//            for (Note note : etudiant.getNoteEtudiant()) {
//                System.out.println(note.getMatiere().getCode()+" -------- "+note.getMatiere().getStatut());
//            }
            
//            for (int i = 0; i < fichierNoteEtudiant.getData().size(); i++) {
//                String[] get = fichierNoteEtudiant.getData().get(i);
//                out.println(i+" ----------------------------------------");
//                for (int j = 0; j < get.length; j++) {
//                    String string = get[j];
//                    out.println(string);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}

