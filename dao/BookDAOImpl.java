package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Author;
import model.Book;
import util.DBUtil;

public class BookDAOImpl implements BookDAO {

	@Override
	public Book insert(Book book) throws SQLException {
		int book_id=-1;
		String insertQuery = "INSERT INTO books(title, author_id, cover_image_path) VALUES(?, ?, ?) RETURNING book_id";
		try (Connection conn = DBUtil.getDataSource().getConnection();
			 PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
			insertStmt.setString(1, book.getTitle());
			insertStmt.setInt(2, book.getAuthor_id());
			insertStmt.setString(3, book.getCover_image_path());
			ResultSet rsInsert = insertStmt.executeQuery();
			if (rsInsert.next()) {
				book_id = rsInsert.getInt("book_id");
				book.setBook_id(book_id);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public Book fetch(Book book) throws SQLException {
		
		int book_id=1;
		String fetchBookQuery = "SELECT book_id FROM books WHERE title = ? AND author_id = ?;\r\n" + "";
		try (Connection conn = DBUtil.getDataSource().getConnection();
			 PreparedStatement checkStmt = conn.prepareStatement(fetchBookQuery);) {
			checkStmt.setString(1, book.getTitle());
			checkStmt.setInt(2, book.getAuthor_id());
			ResultSet rs = checkStmt.executeQuery();

			if (rs.next()) {
				book_id = rs.getInt("book_id");
				book.setBook_id(book_id);
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return book;
	}

	@Override
	public int insertBooks(Book book, Author author) {
			int bookid = -1;
			String insertBookQuery = "INSERT INTO books (title, author_id)\r\n" + "SELECT ?, ?\r\n"
					+ "WHERE NOT EXISTS (\r\n" + "    SELECT 1 FROM books WHERE title = ? AND author_id = ?\r\n" + ");\r\n"
					+ "";
			String fetchBookQuery = "SELECT book_id FROM books WHERE title = ? AND author_id = ?;\r\n" + "";
			try (Connection conn = DBUtil.getDataSource().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(insertBookQuery);) {
				pstmt.setString(1, book.getTitle());
				pstmt.setInt(2, author.getAuthor_id());
				pstmt.setString(3, book.getTitle());
				pstmt.setInt(4, author.getAuthor_id());
				PreparedStatement pstmt1 = conn.prepareStatement(fetchBookQuery);
				pstmt.setString(1, book.getTitle());
				pstmt.setInt(2, author.getAuthor_id());
				int rowsAffected = pstmt.executeUpdate();
				ResultSet rs = pstmt1.executeQuery();
				if (rs.next()) {
					bookid = rs.getInt("book_id");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookid;	
		}

}
