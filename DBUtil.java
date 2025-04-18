package util;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class DBUtil 
{
	private static HikariDataSource dataSource;
	static 
    { 
		Properties properties = new Properties();
		InputStream inputStream;
		try 
		{
		    inputStream = DBUtil.class.getClassLoader().getResourceAsStream("database.properties");
			properties.load(inputStream);
		} catch (Exception e) 
		{	
			e.printStackTrace();
		}
		// Database connection parameters
	    final String driverClassName =properties.getProperty("driverClassName");
	    //final String URL = "jdbc:postgresql://aws-0-ap-south-1.pooler.supabase.com:5432/postgres";
	    final String URL = properties.getProperty("URL");
	    final String USERNAME = properties.getProperty("USERNAME");
	    final String PASSWORD = properties.getProperty("PASSWORD");
	    
	    dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(150);
        dataSource.setMinimumIdle(5);
    }
    // Static method to establish a database connection
    public static DataSource getDataSource() throws SQLException 
    {    
    	return dataSource;
    }
}
