package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Author;
import util.DBUtil;

public class AuthorDAOImpl implements AuthorDAO {

	@Override
	public Author insert(Author author) throws SQLException {
		int author_id=-1;
		String insertQuery = "INSERT INTO authors(author_name) VALUES(?) RETURNING author_id";
		try (Connection conn = DBUtil.getDataSource().getConnection();
			 PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
			insertStmt.setString(1, author.getAuthor_name());
			ResultSet rsInsert = insertStmt.executeQuery();
			if (rsInsert.next()) {
				author_id = rsInsert.getInt("author_id");
				author.setAuthor_id(author_id);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return author;
	}

	@Override
	public Author fetch(Author author) throws SQLException {
		int author_id=1;
		String checkQuery = "SELECT author_id FROM authors WHERE author_name = ?";
		try (Connection conn = DBUtil.getDataSource().getConnection();
			 PreparedStatement checkStmt = conn.prepareStatement(checkQuery);) {
			checkStmt.setString(1, author.getAuthor_name());
			ResultSet rs = checkStmt.executeQuery();

			if (rs.next()) {
				author_id = rs.getInt("author_id");
				author.setAuthor_id(author_id);
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return author;
	}

}
