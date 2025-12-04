package finals.proj;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String USER = "root";
    private static final String PASS = ""; 
    private static final String BASE_URL = "jdbc:mysql://localhost:3306/"; 

    public static Connection getConnection(String dbName) {
        Connection con = null;
        String fullUrl = BASE_URL + dbName;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(fullUrl, USER, PASS);
        } catch (Exception e) {
            System.err.println("Database connection error for DB: " + dbName);
            System.err.println("Error details: " + e.getMessage());
        }
        return con;
    }
    
    public static Connection getEventsConnection() {
        return getConnection("events");
    }
    
    public static Connection getAddStudentConnection() {
        return getConnection("students");
    }
}