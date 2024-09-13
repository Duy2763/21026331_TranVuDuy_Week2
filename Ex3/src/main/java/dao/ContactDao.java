package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseConnection.DBConnection;
import entities.Contact;

public class ContactDao implements AutoCloseable{
	private Connection conn;

    public ContactDao() throws SQLException {
        this.conn = DBConnection.getConnection(); 
    }
	
    public int insertContact(Contact contact) throws SQLException {
        String sqlInsert = "INSERT INTO contacts (first_name, last_name, photo) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sqlInsert);
        statement.setString(1, contact.getFirstName());
        statement.setString(2, contact.getLastName());
        statement.setBlob(3, contact.getPhoto());
        return statement.executeUpdate();
    }
    
    public Blob getPhotoByContactName(String firstName, String lastName) throws SQLException {
        String sqlSelect = "SELECT photo FROM contacts WHERE first_name=? AND last_name=?";
        PreparedStatement statement = conn.prepareStatement(sqlSelect);
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getBlob("photo");
        }

        return null;
    }
    
    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
