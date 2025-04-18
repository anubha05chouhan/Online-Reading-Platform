package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Book;
import model.BookGenre;
import model.Genre;
import util.DBUtil;

public class BookGenreDAOImpl implements BookGenreDAO {

	@Override
	public BookGenre insert(BookGenre bookGenre) throws SQLException {
		String insertQuery = "insert into book_genres(book_id, genre_id) values(?, ?)";
		try(Connection conn = DBUtil.getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(insertQuery);)
		{
			pstmt.setInt(1, bookGenre.getBook_id());
			pstmt.setInt(2, bookGenre.getGenre_id());
			int rowsAffected = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bookGenre;
	}

	@Override
	public BookGenre fetch(BookGenre bookGenre) throws SQLException {
		String fetchQuery = "SELECT book_id FROM book_genres WHERE genre_id = ?";
		try (Connection conn = DBUtil.getDataSource().getConnection();
				PreparedStatement checkStmt = conn.prepareStatement(fetchQuery);)
		{
			checkStmt.setInt(1, bookGenre.getGenre_id());
			ResultSet rs = checkStmt.executeQuery();
		}
		return bookGenre;
	}
	@Override
	public Map<String, List<Book>> fetchGenreAndBooks() throws SQLException {
		String checkQuery = "SELECT g.genre_name, b.book_id, b.title, b.cover_image_path\r\n"
				+ "FROM genres g\r\n"
				+ "JOIN book_genres bg ON g.genre_id = bg.genre_id\r\n"
				+ "JOIN books b ON bg.book_id = b.book_id;\r\n"
				+ "";
		String genre_name="";
		int book_id=-1;
		String title="";
		String cover_image_path="";
		Map<String, List<Book>> genreBooksMap = new HashMap<>();
		try (Connection conn = DBUtil.getDataSource().getConnection();
				 PreparedStatement checkStmt = conn.prepareStatement(checkQuery);) 
			{
				ResultSet rs = checkStmt.executeQuery();
				while(rs.next())
				{
					Book book = new Book();
					title=rs.getString("title");
					book.setTitle(title);
					book_id=rs.getInt("book_id");
					book.setBook_id(book_id);
					cover_image_path=rs.getString("cover_image_path");
					book.setCover_image_path(cover_image_path);
					genre_name = rs.getString("genre_name");
					if (genreBooksMap.containsKey(genre_name)) {
			            genreBooksMap.get(genre_name).add(book);
			        } else {
			            List<Book> newList = new ArrayList<>();
			            newList.add(book);
			            genreBooksMap.put(genre_name, newList);
			        }
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return genreBooksMap;
	}

}
