/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Connect {
    public Connection toConnect() throws Exception, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"
                + "etudiant",
                "postgres",
                "30Precious");
        return connect;
    }
}
