/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tienda.persistencia;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author fs20165
 */
public class ConnectionPostgres {
    
    private final String url = "jdbc:postgresql://localhost:5432/tienda";
    private final String user = "postgres";
    private final String password = "9410ger";
    
     public Connection connect() throws ClassNotFoundException, IllegalAccessException {
        Connection conn = null;
       
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
    
}
