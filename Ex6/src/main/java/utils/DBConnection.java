package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public DBConnection() {
		super();
	}

	public static Connection getConnection() {
			Connection connection = null;
			String jdbcURL = "jdbc:mariadb://localhost:3306/users";
			String jdbcUsername = "root";
			String jdbcPassword = "password";
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return connection;
	}
	public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // Test the connection by executing a simple query
                Statement stmt = conn.createStatement();
                stmt.executeQuery("SELECT 1");
                System.out.println("Connection successful!");
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
