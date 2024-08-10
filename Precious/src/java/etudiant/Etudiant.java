/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etudiant;

import function.Function;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import matiere.Barem;
import matiere.Compensation;
import matiere.Matiere;
import note.Note;
import semestre.Semestre;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Etudiant extends Function {
    int idEtudiant;
    String etu;
    String nom;
    String prenom;
    LocalDate dtn;
    Promotion promotion;
    Semestre semestre;
    
    Barem barem;
    Compensation compensation;
    
    List<Note> noteEtudiant;
    double moyenne;
    String statutEtudiant;
    boolean etudiantPassSemestre;
    double totalCredit;
    
    boolean statutLicence;
    List<Semestre> listeSemestre;
    
    String[] color;
    double moyenneAnnee;
    
    public Etudiant() {
    }

    public Etudiant(int idEtudiant, String etu, String nom, String prenom, LocalDate dtn, Promotion promotion) {
        this.idEtudiant = idEtudiant;
        this.etu = etu;
        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
        this.promotion = promotion;
    }
    
    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getEtu() {
        return etu;
    }

    public void setEtu(String etu) {
        this.etu = etu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDtn() {
        return dtn;
    }

    public void setDtn(LocalDate dtn) {
        this.dtn = dtn;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public List<Note> getNoteEtudiant() {
        return noteEtudiant;
    }

    public void setNoteEtudiant(List<Note> noteEtudiant) {
        this.noteEtudiant = noteEtudiant;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public String getStatutEtudiant() {
        return statutEtudiant;
    }

    public void setStatutEtudiant(String statutEtudiant) {
        this.statutEtudiant = statutEtudiant;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Barem getBarem() {
        return barem;
    }

    public void setBarem(Barem barem) {
        this.barem = barem;
    }

    public Compensation getCompensation() {
        return compensation;
    }

    public void setCompensation(Compensation compensation) {
        this.compensation = compensation;
    }

    public boolean isStatutLicence() {
        return statutLicence;
    }

    public void setStatutLicence(boolean statutLicence) {
        this.statutLicence = statutLicence;
    }

    public List<Semestre> getListeSemestre() {
        return listeSemestre;
    }

    public void setListeSemestre(List<Semestre> listeSemestre) {
        this.listeSemestre = listeSemestre;
    }

    public boolean isEtudiantPassSemestre() {
        return etudiantPassSemestre;
    }

    public void setEtudiantPassSemestre(boolean etudiantPassSemestre) {
        this.etudiantPassSemestre = etudiantPassSemestre;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public double getMoyenneAnnee() {
        return moyenneAnnee;
    }

    public void setMoyenneAnnee(double moyenneAnnee) {
        this.moyenneAnnee = moyenneAnnee;
    }
    
/* USE AT getThings : 
    RECUP LES ETUDIANTS DANS Promotion*/
    public Etudiant getEtudiant(ResultSet rs) throws SQLException, ParseException {
        Promotion promotion = new Promotion(
            Integer.parseInt(rs.getString("idPromotion")), 
            rs.getString("promotionnom")
        );
        
        LocalDate dtn = (LocalDate) this.stringToDate(rs.getString("dtn"), null, null, null);
        Etudiant etudiant = new Etudiant(
            Integer.parseInt(rs.getString("idEtudiant")),
            rs.getString("etu"),
            rs.getString("etudiantnom"),
            rs.getString("prenom"),
            dtn,
            promotion
        );
                    
        return etudiant;
    }

/* FONCTION POUR AVOIR LA LISTE DES ETUDIANTS DANS LA VIEW v_etudiant */
    public List<Etudiant> getListeEtudiant(Connection connection) throws Exception {
        Object[] listeEtudiantBase = this.getThings(connection, this, "v_etudiant", "etu", "getEtudiant");
        List<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
        for (Object object : listeEtudiantBase) {
            listeEtudiant.add((Etudiant) object);
        }
        return listeEtudiant;
    }
    
/* USE AT checkEtudiant : 
    RECUP ETU DE ETUDIANT DANS etudiant BY etu*/
    public Etudiant getEtudiantByEtu(ResultSet rs) throws SQLException, ParseException {
        Etudiant etudiant = new Etudiant();
            etudiant.setEtu(rs.getString("etu"));
        return etudiant;
    }   
    
/* FONCTION POUR CHECKER SI L'ETUDIANT A INSERER DANS LA TABLE NOTE EXISTE */
    public boolean checkEtudiant(Connection connection, String etu) throws Exception {
        boolean ifExist = true;
        
        String request = "SELECT etu FROM etudiant WHERE etu = 'ETU"+etu+"'";
        Object etudiant = this.selectWhereOneObject(connection, this, "etudiant", request, "getEtudiantByEtu");
        if (etudiant instanceof Boolean) {
            ifExist = false;
        }
        return ifExist;
    }

/* FONCTION POUR AVOIR LA LISTE DES ETUDIANTS SELON LA PROMOTION */
    public List<Etudiant> getEtudiantByPromotion(Connection connection) throws Exception {
        String request = "SELECT * FROM v_etudiant "+
            "WHERE idPromotion = "+this.getPromotion().getIdPromotion();
        ArrayList<Object> listeEtudiantByPromotionBase  = this.selectWhere(connection, this, "v_etudiant", request, "getEtudiant");
        List<Etudiant> listeEtudiantByPromotion = new ArrayList<Etudiant>();
        for (Object object : listeEtudiantByPromotionBase) {
            listeEtudiantByPromotion.add((Etudiant) object);
        }
        return listeEtudiantByPromotion;
    }
   
/* FONCTION POUR AVOIR LES INFOS D'UN ETUDIANT SELON SON ETU
    UTILSE POUR VOIR SES NOTES AVEC BAREM ET COMPENSATION */
    public Etudiant getEtudiantByEtuForNote(Connection connection, String etu) throws Exception {
        String request = "SELECT * FROM v_etudiant WHERE etu = '"+etu+"'";
        Etudiant etudiant = (Etudiant) this.selectWhereOneObject(connection, this, "v_etudiant", request, "getEtudiant");
        
        Barem barem = new Barem();
            barem = barem.getLastBarem(connection);
        
        Compensation compensation = new Compensation();
            compensation = compensation.getLastCompensation(connection);
        
        etudiant.setBarem(barem);
        etudiant.setCompensation(compensation);
            
        return etudiant;
    }

/* FONCTION POUR FAIRE LE CALCUL DE LA MOYENNE GENERAL */
    public double getMoyenneGeneral(List<Note> noteEtudiant) {
        double totalNote = 0;
        double totalCredit = 0;
        for (Note note : noteEtudiant) {
            double noteMatiere = note.getMatiere().getCredit() * note.getNote();
            totalNote = totalNote + noteMatiere;
            totalCredit = totalCredit + note.getMatiere().getCredit();
        }
        
        double moyenne = totalNote / totalCredit;
        return moyenne;
    }
    
/* FONCTION POUR CHECK SI IL Y A UNE MATIERE INFERIEURE A 6 
    SET APPRECIATION DES MATIERES */
    public boolean checkMatiereDownBarem(Barem barem, double moyenneMatiere, List<Note> listeNote, String[] appreciation) {
        boolean checkDown = false;
        for (Note note : listeNote) {
            if (note.getNote() < barem.getNoteEliminatoire()) {
                checkDown = true;
                Matiere noteMatiere = note.getMatiere();
                    noteMatiere.setStatut(appreciation[0]);     //AJOURNEE
            } else if (note.getNote() < moyenneMatiere) {
                Matiere noteMatiere = note.getMatiere();
                    noteMatiere.setStatut(appreciation[0]);     //AJOURNEE
            } else {
                Matiere noteMatiere = note.getMatiere();
                    noteMatiere.setStatut(appreciation[1]);     //PASSED
            }
        }
        return checkDown;
    }

/* FONCTION POUR SAVOIR COMBIEN DE MATIERE A COMPENSER */
    public boolean checkMatiereCompense(Compensation compensation, double moyenneMatiere, List<Note> listeNote) {
        boolean checkMatiereCompense = true;
        int counter = 0;
        for (Note note : listeNote) {
            if (note.getNote() < moyenneMatiere) {
                counter++;
            }
            if (counter > compensation.getNombreMatiere()) {
                checkMatiereCompense = false;
                return checkMatiereCompense;
            }
        }
        return checkMatiereCompense;
    }
            
/* FONCTION SETTER :
    - LES NOTES
    - LA MOYENNE GENERAL
    - CREDIT TOTAL
    - CREDIT VALIDE PAR MATIERE
        DE L'ETUDIANT */
    public Etudiant setMoyenneEtudiant(Connection connection, Etudiant etudiant, int idSemestre, String[] appreciation) throws Exception {
        Note noteEtudiant = new Note();
        List<Note> listeNoteEtudiant = noteEtudiant.getNoteByEtudiantAndSemestre(connection, etudiant.getEtu(), idSemestre);
        
        double moyenne = this.getMoyenneGeneral(listeNoteEtudiant);
        etudiant.setMoyenne(moyenne);
        if (moyenne < 10) {
            etudiant.setStatutEtudiant(appreciation[0]);
            etudiant.setEtudiantPassSemestre(false);
            Semestre semestre = listeNoteEtudiant.get(0).getMatiere().getSemestre();
            etudiant.setSemestre(semestre);
                Semestre semestreEtudiant = etudiant.getSemestre();
                    semestreEtudiant.setStatutSemestre(appreciation[0]);
        }
        
        double totalCredit = 0;
        for (Note note : listeNoteEtudiant) {
            Matiere matiere = note.getMatiere();
                if (note.getNote() >= 10) { // POUR SETTER OE ON A COMBIEN DE CREDIT POUR LE SEMESTRE
                    matiere.setCreditAzo(matiere.getCredit());
                    totalCredit = totalCredit + matiere.getCreditAzo();
                }
        }
        etudiant.setTotalCredit(totalCredit);
        etudiant.setNoteEtudiant(listeNoteEtudiant);
        return etudiant;
    }

/* FONCTION POUR SAVOIR SI ETUDIANT COMPENSER OU AJOURNEE OU PASS */
    public Etudiant setAppreciationMatiere(Etudiant etudiant, double moyenneMatiere, double moyenneGeneralNeeded, String[] appreciation) {
//        String[] appreciation = { "Ajourné", "Pass", "Compensé" };
        boolean checkDownMatiere = this.checkMatiereDownBarem(etudiant.getBarem(), moyenneMatiere, etudiant.getNoteEtudiant(), appreciation);
        
        if (checkDownMatiere == true) {
            //UNE MATIERE MOINS DE 6 -> AJOURNEE
            this.setStatutEtudiant(appreciation[0]);
                this.setEtudiantPassSemestre(false);
        } else {
            boolean checkMatiereCompense = this.checkMatiereCompense(etudiant.getCompensation(), moyenneMatiere, etudiant.getNoteEtudiant());
            if (checkMatiereCompense == false) {
                //AJOURNE DIRECT FA TS COMPENSEE
                this.setStatutEtudiant(appreciation[0]);
                this.setEtudiantPassSemestre(false);
            } else { //COMPENSER MAIS POSSIBLE TS MAHAZO MOYENNE GENERALE
                if (etudiant.getMoyenne() < moyenneGeneralNeeded ) {    //SI MOYENNE < 10 => TS AFAKA
                    this.setStatutEtudiant(appreciation[0]);
                    this.setEtudiantPassSemestre(false);
                } else {    //SI MOYENNE >= 10 => AFAKA
                    this.setStatutEtudiant(appreciation[1]);
                    this.setEtudiantPassSemestre(true);
                }
            }
        }
        
        return etudiant;
    }
    
/* FONCTION POUR FAIRE LA LISTE DES SEMESTRES D'UN ETUDIANT AVEC LA MOYENNE PAR SEMESTRE ET SI LES SEMESTRE EST VALIDE*/
    public Etudiant getListSemestreEtudiantWithMoyenne(Connection connection, Etudiant etudiant, String[] appreciation, double moyenneMatiere10, double moyenneGeneralNeeded) throws Exception {
        Semestre semestre = new Semestre();
        List<Semestre> listeSemestre = semestre.getSemestreEtudiantFromNote(connection, etudiant.getEtu());
        
        for (Semestre semestreBase : listeSemestre) {
            etudiant = etudiant.getEtudiantByEtuForNote(connection, etudiant.getEtu());
            etudiant = etudiant.setMoyenneEtudiant(connection, etudiant, semestreBase.getIdSemestre(), appreciation);
            etudiant = etudiant.setAppreciationMatiere(etudiant, moyenneMatiere10, moyenneGeneralNeeded, appreciation);
            semestreBase.setStatutSemestre(etudiant.getStatutEtudiant());
            semestreBase.setSemestrePassed(etudiant.isEtudiantPassSemestre());
        }
        etudiant.setListeSemestre(listeSemestre);
        return etudiant;
    }

/* FONCTION POUR SAVOIR SI L'ETUDIANT A PASSER SA LICENCE */
    public Etudiant checkLicence(Connection connection, Etudiant etudiant, String[] appreciation, double moyenneMatiere10, double moyenneGeneralNeeded) throws Exception {
        Etudiant etudiantPassLicence = etudiant.getListSemestreEtudiantWithMoyenne(connection, etudiant, appreciation, moyenneMatiere10, moyenneGeneralNeeded);
        boolean passed = true;
        etudiantPassLicence.setStatutLicence(passed);
        for (Semestre semestre : etudiantPassLicence.getListeSemestre()) {
            if (semestre.isSemestrePassed() == false) {
                passed = false;
                etudiantPassLicence.setStatutLicence(passed);
                return etudiantPassLicence;
            }
        }
        return etudiantPassLicence;
    }
    
/* FONCTION POUR AVOIR LA LISTE DES ETUDIANTS QUI ONT LEUR LICENCE */
    public List<Etudiant> getEtudiantWithLicence(Connection connection, List<Etudiant> allEtudiant, String[] appreciation, double moyennePass, double moyenneGeneralNeeded) throws Exception {
        List<Etudiant> etudiantWithLicence = new ArrayList<Etudiant>();
        for (Etudiant etudiant : allEtudiant) {
            etudiant = etudiant.checkLicence(connection, etudiant, appreciation, moyennePass, moyenneGeneralNeeded);
            if (etudiant.isStatutLicence() == true) {
                etudiantWithLicence.add(etudiant);
            }
        }
        return etudiantWithLicence;
    }
    
/* FONCTION POUR AVOIR LA LISTE DES ETUDIANTS QUI N'ONT PAS LEUR LICENCE */
    public List<Etudiant> getEtudiantWithoutLicence(Connection connection, List<Etudiant> allEtudiant, String[] appreciation, double moyennePass, double moyenneGeneralNeeded) throws Exception {
        List<Etudiant> etudiantWithoutLicence = new ArrayList<Etudiant>();
        for (Etudiant etudiant : allEtudiant) {
            etudiant = etudiant.checkLicence(connection, etudiant, appreciation, moyennePass, moyenneGeneralNeeded);
            if (etudiant.isStatutLicence() == false) {
                etudiantWithoutLicence.add(etudiant);
            }
        }
        return etudiantWithoutLicence;
    }
    
/* FONCTION POUR FAIRE LA LISTE DES SEMESTRES D'UN ETUDIANT AVEC LA MOYENNE PAR SEMESTRE ET SI LES SEMESTRE EST VALIDE
    DANS USER ===================*/
    public Etudiant getListSemestreUser(Connection connection, String etu, List<Semestre> listeSemestre, String[] appreciation, double moyenneMatiere10, double moyenneGeneralNeeded) throws Exception {
        Semestre semestre = new Semestre();
        
        Etudiant etudiant = new Etudiant();
        for (Semestre semestreBase : listeSemestre) {
            etudiant = etudiant.getEtudiantByEtuForNote(connection, etu);
            etudiant = etudiant.setMoyenneEtudiant(connection, etudiant, semestreBase.getIdSemestre(), appreciation);
            etudiant = etudiant.setAppreciationMatiere(etudiant, moyenneMatiere10, moyenneGeneralNeeded, appreciation);
            semestreBase.setStatutSemestre(etudiant.getStatutEtudiant());
            semestreBase.setSemestrePassed(etudiant.isEtudiantPassSemestre());
            semestreBase.setMoyenneSemestre(etudiant.getMoyenne());
        }
        etudiant.setListeSemestre(listeSemestre);
        return etudiant;
    }

/* FONCTION POUR EFFECTUER LA RECHERCHE PAR NOM */
    public List<Etudiant> searchEtudiantByNom(List<Etudiant> listeEtudiant, String objetRecherche) {
        List<Etudiant> etudiantCible = new ArrayList<Etudiant>();
        for (Etudiant etudiant : listeEtudiant) {
            String nomEtudiant = etudiant.getNom().toLowerCase();
            String recherche = objetRecherche.toLowerCase();
            if (nomEtudiant.contains(recherche) == true) {
                etudiantCible.add(etudiant);
            }
        }
        return etudiantCible;
    }
    
    public List<Etudiant> getEtudiantAnnee(Connection connection, String etu, int idSemestre1, int idSemestre2) throws Exception {
        List<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
        
            double moyenneMatiere = 10;
            double moyenneGeneralNeeded = 10;
            String[] appreciation = { "Ajourné", "Pass", "Compensé" };
            Etudiant etudiant1 = new Etudiant();
                etudiant1 = etudiant1.getEtudiantByEtuForNote(connection, etu);
                etudiant1 = etudiant1.setMoyenneEtudiant(connection, etudiant1, idSemestre1, appreciation);
                etudiant1 = etudiant1.setAppreciationMatiere(etudiant1, moyenneMatiere, moyenneGeneralNeeded,  appreciation);
                
            Etudiant etudiant2 = new Etudiant();
                etudiant2 = etudiant2.getEtudiantByEtuForNote(connection, etu);
                etudiant2 = etudiant2.setMoyenneEtudiant(connection, etudiant2, idSemestre2, appreciation);
                etudiant2 = etudiant2.setAppreciationMatiere(etudiant2, moyenneMatiere, moyenneGeneralNeeded,  appreciation);
            
            listeEtudiant.add(etudiant1);
            listeEtudiant.add(etudiant2);
            return listeEtudiant;
    }
    
    public double getMoyenneAnneeCraft(Etudiant etudiant1, Etudiant etudiant2) {
        double moyenne1 = etudiant1.getMoyenne();
        double moyenne2 = etudiant2.getMoyenne();
        
        double moyenneAnnee = (moyenne1 + moyenne2) / 2;
        return moyenneAnnee;
    }
    
    public String[] getAppreciationAnnee(Etudiant etudiant1, Etudiant etudiant2) {
        double moyenneAnnee = this.getMoyenneAnneeCraft(etudiant1, etudiant2);
        String[] appreciationColor = new String[2];
        if (moyenneAnnee < 10) {
            appreciationColor[0] = "Ajourné";
            appreciationColor[1] = "red";
        } else {
            appreciationColor[0] = "Pass";
            appreciationColor[1] = "green";
        }
        
        return appreciationColor;
    }
    
    public Etudiant getMix(Etudiant etudiant1, Etudiant etudiant2) {
        Etudiant etudiant = new Etudiant();
            etudiant.setColor(this.getAppreciationAnnee(etudiant1, etudiant2));
            etudiant.setMoyenneAnnee(this.getMoyenneAnneeCraft(etudiant1, etudiant2));
            return etudiant;
    }
}
