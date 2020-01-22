/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myserver.myDatabase.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pc
 */
public class ConnectionDDBB {

    public static Connection getConnection() {

        Connection conn;

        try {

            //Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String sUrl = "jdbc:mysql://localhost:3306/chat?userTimezone=true&serverTimezone=GMT";
            conn = DriverManager.getConnection(sUrl, "root", "elrincon1920");

        } catch (SQLException ex) {
            conn = null;
            throw new RuntimeException("Error con la conexión : " + ex.getMessage());
        }
        return conn;

    }

}
