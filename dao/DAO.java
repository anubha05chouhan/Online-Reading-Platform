package dao;

import java.sql.SQLException;

public interface DAO<T> 
{
	T insert(T t) throws SQLException;
	
	T fetch(T t) throws SQLException;
}
