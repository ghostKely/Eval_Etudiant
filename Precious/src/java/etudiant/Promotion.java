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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Promotion extends Function {
    int idPromotion;
    String nom;

    public Promotion() {
    }

    public Promotion(int idPromotion, String nom) {
        this.idPromotion = idPromotion;
        this.nom = nom;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
/* USE AT getThings : 
    RECUP LES PORMOTIONS DANS Promotion*/
    public Promotion getPromotion(ResultSet rs) throws SQLException {
        Promotion promotion = new Promotion(
            Integer.parseInt(rs.getString("idPromotion")), 
            rs.getString("nom")
        );
                    
        return promotion;
    }

/* FONCTION POUR AVOIR LA LISTE DES PROMOTIONS EXISTANTE POUR LE FILTRAGE */    
    public List<Promotion> getListePromotion(Connection connection) throws Exception {
        Object[] listePromotionBase = this.getThings(connection, this, "promotion", "idPromotion", "getPromotion");
        List<Promotion> listePromotion = new ArrayList<Promotion>();
        for (Object object : listePromotionBase) {
            listePromotion.add((Promotion) object);
        }
        return listePromotion;
    }
}
