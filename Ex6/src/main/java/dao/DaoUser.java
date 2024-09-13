package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import utils.DBConnection;

public class DaoUser {
	private Connection conn;
	
	public DaoUser() {
		this.conn =	DBConnection.getConnection(); 
	}
	
	public List<User> getUsers() {
        List<User> userList = new ArrayList<User>();
        String query = "SELECT * FROM users"; 

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));  // Thay đổi cột nếu khác
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }
	
	public User getUserById(int id) {
	    User user = null;
	    String query = "SELECT * FROM users WHERE id = ?";  // Thay 'users' nếu tên bảng khác

	    try (PreparedStatement statement = conn.prepareStatement(query)) {
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            user = new User();
	            user.setId(resultSet.getInt("id"));  // Thay cột nếu khác
	            user.setName(resultSet.getString("name"));
	            user.setEmail(resultSet.getString("email"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return user;
	}
	
	public boolean addUser(User user) {
	    String query = "INSERT INTO users (name, email) VALUES (?, ?)";  

	    try (PreparedStatement statement = conn.prepareStatement(query)) {
	        statement.setString(1, user.getName());
	        statement.setString(2, user.getEmail());
	        
	        int rowsInserted = statement.executeUpdate();
	        return rowsInserted > 0;  

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}

}
