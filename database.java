package online_learning_platform;
import java.sql.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class database {

	public static void main(String[] args)throws Exception 
	{
		 String s1 = "ram@gmail.com";
	        String s2 = "ram123";

	        // Setup HikariCP configuration
	        HikariConfig config = new HikariConfig();
	        config.setJdbcUrl("jdbc:postgresql://aws-0-ap-south-1.pooler.supabase.com:5432/postgres");
	        config.setUsername("postgres.lzyowiasbdsftpdbpmnj");
	        config.setPassword("tKwpMiNB$T6%RLV"); // Replace [YOUR-PASSWORD] with your actual password

	        // Create the DataSource
	        HikariDataSource dataSource = new HikariDataSource(config);

	        try (Connection conn = dataSource.getConnection()) {
	            // Create table if not exists
	            String createTableQuery = "CREATE TABLE IF NOT EXISTS Credentials (email VARCHAR(255), username VARCHAR(255))";
	            try (PreparedStatement createTableStmt = conn.prepareStatement(createTableQuery)) {
	                createTableStmt.executeUpdate();
	            } catch (SQLException e) {
	                System.out.println("Error creating table: " + e.getMessage());
	            }

	            // Insert data into the table
	            String insertQuery = "INSERT INTO Credentials(email, username) VALUES (?, ?)";
	            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
	                pstmt.setString(1, s1);
	                pstmt.setString(2, s2);
	                int rowsInserted = pstmt.executeUpdate();
	                System.out.println("Data inserted");
	            } catch (SQLException e) {
	                System.out.println("SQL Exception: " + e.getMessage());
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: " + e.getMessage());
	        }


	        // Close the DataSource
	        dataSource.close();

	}

}
