package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.DBUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public User insert(User user) throws SQLException {
		try (Connection conn = DBUtil.getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (username, email, token) VALUES (?, ?, ?)");) 
		{
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getToken());
			int rowsInserted = pstmt.executeUpdate();
			System.out.println("data inserted");
			conn.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public User fetch(User user) throws SQLException {
		String sql = "SELECT * FROM users WHERE token = ? and username = ?";
		try (Connection conn = DBUtil.getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);)
		{
			pstmt.setString(1, user.getToken());
			pstmt.setString(2, user.getUsername());
			try (ResultSet rs = pstmt.executeQuery()) {
				return user;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

}
