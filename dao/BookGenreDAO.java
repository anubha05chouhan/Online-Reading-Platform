package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import model.Book;
import model.BookGenre;

public interface BookGenreDAO extends DAO<BookGenre> 
{
	Map<String, List<Book>> fetchGenreAndBooks() throws SQLException;
}
