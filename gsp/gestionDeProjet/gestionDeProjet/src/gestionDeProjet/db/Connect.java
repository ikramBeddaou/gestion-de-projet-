package gestionDeProjet.db;

import java.sql.*;

public class Connect {
    
    private static Connect instance;
    private Connection con;
    
    private Connect() {
        try {
        	//DriverManager.registerDriver(null);
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gsp", "root", "");
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public static Connect getInstance() {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return this.con;
    }
}
