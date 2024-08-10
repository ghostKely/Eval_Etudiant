/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import function.Function;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Log extends Function {
    int idUser;
    String userName;
    String pseudo;
    String password;
    boolean isAdmin;
    
    String tableName;
    String colonneUserName;
    String colonnePassword;

    public Log() {
    }

    public Log(String tableName, String colonneUserName, String colonnePassword) {
        this.tableName = tableName;
        this.colonneUserName = colonneUserName;
        this.colonnePassword = colonnePassword;
    }

    public Log(int idUser, String userName, String pseudo, String password, boolean isAdmin) {
        this.idUser = idUser;
        this.userName = userName;
        this.pseudo = pseudo;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Log(String userName, boolean isAdmin) {
        this.userName = userName;
        this.isAdmin = isAdmin;
    }

    public Log(String pseudo, String password, String tableName, String colonneUserName, String colonnePassword) {
        this.userName = pseudo.toLowerCase();
        this.pseudo = pseudo;
        this.password = password;
        this.isAdmin = false;
        this.tableName = tableName;
        this.colonneUserName = colonneUserName;
        this.colonnePassword = colonnePassword;
    }

    public Log(int idUser) {
        this.idUser = idUser;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColonneUserName() {
        return colonneUserName;
    }

    public void setColonneUserName(String colonneUserName) {
        this.colonneUserName = colonneUserName;
    }

    public String getColonnePassword() {
        return colonnePassword;
    }

    public void setColonnePassword(String colonnePassword) {
        this.colonnePassword = colonnePassword;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    
    public Log getLog(ResultSet rs) throws SQLException {
        Log log = new Log(
            Integer.parseInt(rs.getString("idProfil")),
            rs.getString("username"),
            rs.getString("pseudo"),
            rs.getString("password"),
            rs.getBoolean("isAdmin")    
        );
        
        return log;
    }
    
    public Log getLogId(ResultSet rs) throws SQLException {
        Log log = new Log( Integer.parseInt(rs.getString("idProfil")) );
        
        return log;
    }
    
    public boolean saveUser(Connection connection, String userName) throws Exception {
        boolean ifUserExist = this.checkIfUserExist(connection, userName);
        if (ifUserExist == false) {
            boolean saveUser = this.save(connection, this, this.getTableName(), this.insertLog());
            return saveUser;
        } else {
            return false;
        }
    }
    
    public boolean checkIfUserExist(Connection connection, String userName) throws Exception {
        boolean ifExist = false;
        String query = "SELECT * FROM "+this.getTableName()+
                " WHERE "+this.getColonneUserName()+" = '"+userName+"'";
        Object userBase = this.selectWhereOneObject(null, this, this.getTableName(), query, "getLog");
        if (!(userBase instanceof Boolean)) {
            Log userLog = (Log) userBase;
            if (userLog.getUserName().equalsIgnoreCase(userName)) {
                System.out.println("USERNAME FORM : "+userName+"    USERNAME BASE : "+userLog.getUserName());
                return true;
            }
        }
        return ifExist;
    } 
            
    public String insertLog() {
        String hashedPassword = BCrypt.hashpw(this.getPassword(), BCrypt.gensalt());
        return "INSERT INTO Profil (username, pseudo, password, isAdmin) VALUES \n" +
        "('"+this.getUserName()+"', '"+this.getPseudo()+"', '"+hashedPassword+"', "+this.isIsAdmin()+")";
    }
    
    public Object[] checkUserAndPassword(String userName, String password) throws Exception {
        Object[] checkAndUser = new Object[2];
        
        String query = "SELECT * FROM "+this.getTableName()+
                " WHERE "+this.getColonneUserName()+" = '"+userName.toLowerCase()+"'";
        Object userBase = this.selectWhereOneObject(null, this, this.getTableName(), query, "getLog");
        if (userBase instanceof Boolean) {
            checkAndUser[0] = false;
            checkAndUser[1] = userBase;
            return checkAndUser;
        } else {
            Log userLog = (Log) userBase;
            boolean isPasswordValid = BCrypt.checkpw(password, userLog.getPassword());
            checkAndUser[0] = isPasswordValid;
            Log userSession = new Log(userLog.getUserName(), userLog.isIsAdmin());
            checkAndUser[1] = userSession;
        }
        
        return checkAndUser;
    }
    
    public List<String> getListOfUserInfo() {
        List<String> userList = new ArrayList<String>();
        userList.add(String.valueOf(this.getIdUser()));
        userList.add(this.getUserName());
        userList.add(this.getPseudo());
        userList.add(String.valueOf(this.isIsAdmin()));
        return userList;
    }
    
    public List<List<String>> getAllUser(ArrayList<Log> listeFromHtml) {
        List<List<String>> allUser = new ArrayList<List<String>>();
        for (Log object : listeFromHtml) {
            allUser.add(object.getListOfUserInfo());
        }
        return allUser;
    }
}
