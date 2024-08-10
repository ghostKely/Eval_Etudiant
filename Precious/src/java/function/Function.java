/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Function {
/* COMPTE LE NOMBRE DE MOIS POUR CALCULER LES MOIS DE LOYER A PAYER */
    public int getNombreMoisEntre2Date(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        // Ensemble pour stocker les mois uniques
        Set<Month> monthsBetween = new HashSet<>();

        // Ajout des mois de début et de fin
        monthsBetween.add(startDateTime.getMonth());
        monthsBetween.add(endDateTime.getMonth());

        // Variables temporaires pour le calcul
        LocalDateTime tempDateTime = startDateTime;

        // Boucle pour parcourir chaque mois entre les deux dates
        while (tempDateTime.isBefore(endDateTime)) {
            tempDateTime = tempDateTime.plusMonths(1);
            monthsBetween.add(tempDateTime.getMonth());
        }
        
        //Nombre de mois est la taille du set car on y compte mois
        int nombreMois = monthsBetween.size();
        
        return nombreMois;
    }    

/* fonction pour transformer une date en String
    ARGUMENT : la date a transformer
*/
    public String dateToString (LocalDate date, LocalDateTime dateTime) {
        String dateReturn = "";
        
        if (date == null) {
            DateTimeFormatter formatterPostgres = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dateReturn = dateTime.format(formatterPostgres);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateReturn = date.format(formatter);
        }
        
        return dateReturn;
    }   
 /* fonction pour transformer un string en Date
    ARGUMENT : la date (en format String) a transformer en variable Date
 */   
    public Object stringToDate (String date, String dateTimeFront, String dateTimeBase, String dateImport) throws ParseException{
        Object dateCasted = null;
        if (date != null) {
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date.trim(), formatterDate);        
            dateCasted = localDate;
        } if (dateTimeFront != null) {
            DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime localDateTimeFront = LocalDateTime.parse(dateTimeFront, formatterDateTime);
            dateCasted = localDateTimeFront;
        } if (dateTimeBase != null) {
            DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTimeBase = LocalDateTime.parse(dateTimeBase, formatterDateTime);
            dateCasted = localDateTimeBase;
        } if (dateImport != null) {
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(dateImport.trim(), formatterDate);        
            dateCasted = localDate;
        }
        return dateCasted;
    }

//LE NOM RETOUR DANS LA REQUETE DOIT MATH AVEC LE NOM RETOUR ICI
    public int checkIfTableNotNull(Connection connection, String tableName, String nomRetour, String request) throws Exception {
        boolean isOpen = false;
        Connect co = new Connect();
        if (connection == null) {
            connection = co.toConnect();
        } else {
            isOpen = true;
        }

        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            stmt = connection.createStatement();
            if (request.isEmpty() == true) { 
                String sql = "SELECT COUNT(*) AS "+nomRetour+" FROM "+tableName;
                request = sql;
            }
            System.out.println(request);
            rs = stmt.executeQuery(request);
            while (rs.next()) { //LE NOM RETOUR DANS LA REQUETE DOIT MATH AVEC LE NOM RETOUR ICI
                count = Integer.parseInt(rs.getString(nomRetour));
            }
        } catch (SQLException e) {
            e.getErrorCode();
        } finally {
            if (isOpen == false) {
                connection.close();
            }
        }
        return count;
    }
    
/*  fonction pour compter les elements d'un tableau
 *  ARGUMENT : 
 *      - Connection (null) : pour acceder a la bdd
 *      - String colonneToCount : colonne a compter
        - String tableToCount : table où on doit compter la colonne
        - String nomRetour : nom de la colonne oe count(truc) as chose -> chose 
 *          ex : select count(identree) as retour from entree;
*/
    public int counter(Connection connection, String colonneToCount, String tableToCount, String nomRetour) throws Exception {
        boolean isOpen = false;
        Connect co = new Connect();
        if (connection == null) {
            connection = co.toConnect();
        } else {
            isOpen = true;
        }

        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            stmt = connection.createStatement();
            String sql = "select count("+colonneToCount+") from "+tableToCount+";";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                count = Integer.parseInt(rs.getString(nomRetour));    //attention au nom de la colonne
            }
        } catch (SQLException e) {
            e.getErrorCode();
        } finally {
            if (isOpen == false) {
                connection.close();
            }
        }
        return count;
    }
    
/*  fonction maka sequence
    ARGUMENT : 
        - String seqName
*/
    public int sequence(Connection connection, String seqenceName) throws Exception, SQLException {
        boolean isOpen = false;
        Connect co = new Connect();
        if (connection == null) {
            connection = co.toConnect();
        } else {
            isOpen = true;
        }
        // String request = "select "+seqName+".nextval from dual"; POUR ORACLE
        String request = "select nextval('" + seqenceName + "')";
//        System.out.println(request);
        int sequence = 0;
        try {
            Statement state = connection.createStatement();
            ResultSet result = state.executeQuery(request);
            if (result.next()) {
                sequence = result.getInt(1);
            }
            if (isOpen == false) {
                // connection.commit();
                connection.close();
            }
            return sequence;
        } catch (Exception e) {
            // connection.rollback();
            throw new Exception("Tsy azo le sequence");
        } finally {
            if (isOpen == false) {
                connection.close();
            }
        }
    }
    
/*  fonction manao miset id pour toutes les classes manana id String
    AGUMENT : 
        - tableName = nom de la table a compter     
*/
    public String doId(String sequenceName, String prefixe) throws Exception {
        int count = this.sequence(null, sequenceName);
        count = count +1;
        String id = prefixe + count;
        
        return id;
    }

    public boolean executeQuery(Connection connection, String request) throws Exception, SQLException {
        boolean isOpen = false;
        Connect co = new Connect();
        if(connection == null){
            connection = co.toConnect();   
        }
        else{
            isOpen = true;
        }
        
        boolean isDone = false;
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(request);
            System.out.println(request);
            int rowsAffected = preparedStatement.executeUpdate();
                
                /* POUR LE DELETE C'EST L'INVERSE CAR ON N'INSERE RIEN DONC ROWS AFFECTED = 0 */
                if (rowsAffected > 0) {
                    System.out.println("Insertion réussie !");
                    isDone = true;
                } else {
                    System.out.println("Insertion échouée.");
                    isDone = false;
                }         
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(isOpen == false) {
                connection.close();
            }
        }
        return isDone;
    }
    
/*  fonction manao insertion anaty BDD
    ARGUMENT : 
        - Object objet : objet ho inserena
        - String tableName : nom de la table hanaovana anle insertion
    RQ : tableName = null raha te hampiasa anle tablename par defaut = object.getClass.getCimpleName
*/
    public boolean save(Connection connection, Object objet, String tableName, String request) throws Exception, SQLException {
        boolean isInserted = false;
        boolean isOpen = false;
        Connect co = new Connect();
        if(connection == null){
            connection = co.toConnect();   
        }
        else{
            isOpen = true;
        }
        
        String insertTo = "";
        if (request.isEmpty() == false) { 
            insertTo = request;
        }
        else {
            Method fonctionGetObject = objet.getClass().getMethod("insert"+objet.getClass().getSimpleName());
            insertTo = (String) fonctionGetObject.invoke(objet); 
        }
            System.out.println(insertTo);  
        
        PreparedStatement preparedStatement = null;    
            
        try {
            preparedStatement = connection.prepareStatement(insertTo);
            int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Insertion réussie !");
                    isInserted = true;
                } else {
                    System.out.println("Insertion échouée.");
                    isInserted = false;
                }
        }
        catch (SQLException  e) {
            e.printStackTrace();
        }
        finally {
            if(isOpen == false) {
                preparedStatement.close();
                connection.close();
            }
        }
        return isInserted;
    }  
    
/*  fonction pour avoir la liste des panneaux avec les batterie
 *  ARGUMENT :
 *      - Connection (null) : pour acceder a la bdd
 *      - Object ojectToGet : pour avoir le nom de la table qu'il faut snipe
 *      - tablName : pour plus de flexibilite (null par defaut => mande fona) 
        - colonneToCount : le nom de la colonne à compter pour allouer le tableau d'objet
*/
    public Object[] getThings(Connection connection, Object objectToGet, String tableName, String colonneToCount, String function) throws Exception {
        boolean isOpen = false;
        Connect co = new Connect();
        
        if (connection == null) {
            connection = co.toConnect();
        } else {
            isOpen = true;
        }

        Statement stmt = null;
        ResultSet rs = null;
        if (tableName.isEmpty() == true) {  //value par defaut nom table null
            tableName = objectToGet.getClass().getSimpleName();
        }
        int count = this.counter(connection, colonneToCount, tableName, "count");      //size tableau de Object
        
        Object[] listeStock = new Object[count];

        try {
            stmt = connection.createStatement();
            String sql = "select * from " + tableName + ";";     //requete pour acceder a la vue
             System.out.println(sql);
            rs = stmt.executeQuery(sql);
            int i = 0;
            
            while (rs.next()) {
                Object object = objectToGet.getClass().getConstructor().newInstance();
//                System.out.println("get"+object.getClass().getSimpleName());
                Method fonctionGetObject = object.getClass().getMethod(function, ResultSet.class);
                object = fonctionGetObject.invoke(object, rs);
                        
                listeStock[i] = object;     //ajout object
                // System.out.println(i);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (isOpen == false) {
                stmt.close();
                rs.close();
                connection.close();
            }
        }
        return listeStock;
    }  
    
/*  fonction pour avoir le pointage d'un secteur
 *  ARGUMENT :
 *      - Connection (null) : pour acceder a la bdd
 *      - Object ojectToGet : pour avoir le nom de la table qu'il faut snipe
 *      - tablName : pour plus de flexibilite (null par defaut => mande fona) 
 *      - request : requete chelou
        - function : nom de la fonction à appeler dans la classe
*/
    public ArrayList<Object> selectWhere(Connection connection, Object objectToGet, String tableName, String requete, String function) throws Exception {
        boolean isOpen = false;
        Connect co = new Connect();
        if (connection == null) {
            connection = co.toConnect();
        } else {
            isOpen = true;
        }

        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Object> liste = new ArrayList<Object>();     //Array piske allocation impossible

        try {
            stmt = connection.createStatement();
            String sql = requete;
              System.out.println(sql);
            rs = stmt.executeQuery(sql);
            
            if (tableName.isEmpty() == true) {
                tableName = objectToGet.getClass().getSimpleName();
            }
            
            while (rs.next()) {
                Object object = objectToGet.getClass().getConstructor().newInstance();
                Method fonctionGetObject = object.getClass().getMethod(function, ResultSet.class);  //appele de la fonction requise
                object = fonctionGetObject.invoke(object, rs);
                liste.add(object);
            }
        } catch (SQLException e) {
            e.getErrorCode();
        } finally {
            if (isOpen == false) {
                stmt.close();
                rs.close();
                connection.close();
            }
        }
        return liste;
    }
    
/*  fonction pour avoir un objet en select where
 *  ARGUMENT :
 *      - Connection (null) : pour acceder a la bdd
 *      - Object ojectToGet : pour avoir le nom de la table qu'il faut snipe
 *      - tablName : pour plus de flexibilite (null par defaut => mande fona) 
 *      - request : requete chelou
        - function : nom de la fonction à appeler dans la classe
*/
    public Object selectWhereOneObject(Connection connection, Object objectToGet, String tableName, String requete, String function) throws Exception {
        boolean isOpen = false;
        Connect co = new Connect();
        if (connection == null) {
            connection = co.toConnect();
        } else {
            isOpen = true;
        }

        Statement stmt = null;
        ResultSet rs = null;
        Object toReturn = new Object();     //Array piske allocation impossible
            
            if (tableName.isEmpty() == true) {
                tableName = objectToGet.getClass().getSimpleName();
            }
        
        try {
            stmt = connection.createStatement();
            String sql = requete;
              System.out.println(sql);
            rs = stmt.executeQuery(sql);
            
            if (rs.next() == false) {
                return false;
            } else {
                Object object = objectToGet.getClass().getConstructor().newInstance();
                Method fonctionGetObject = object.getClass().getMethod(function, ResultSet.class);  //appele de la fonction requise
                object = fonctionGetObject.invoke(object, rs);
                toReturn = object;
            }
        } catch (SQLException e) {
            e.printStackTrace();
//            e.getErrorCode();
//            throw new Exception("Tsy misy le object");
        } finally {
            if (isOpen == false) {
                stmt.close();
                rs.close();
                connection.close();
            }
        }
        
        return toReturn;
    } 
}
