package dao;

import java.sql.SQLException;
import java.util.List;
import model.Genre;

public interface GenreDAO extends DAO<Genre> 
{
	List<Genre> fetchAll() throws SQLException;
}
