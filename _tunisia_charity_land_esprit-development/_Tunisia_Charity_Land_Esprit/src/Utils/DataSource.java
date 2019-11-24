/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author oflcad
 */
public class DataSource {
    private static DataSource instance;
    private Connection connection;
    private String urlString = "jdbc:mysql://localhost:3306/esprit";
    private String username = "root";
    private String password = "";

    private DataSource() throws SQLException {
        try {
            
            this.connection = DriverManager.getConnection(urlString, username, password);
            System.out.println("We have a database Connection");
        } catch (SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }
    
    

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataSource();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataSource();
        }

        return instance;
    }
}

