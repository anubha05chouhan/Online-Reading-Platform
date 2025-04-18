package model;

public class User {
	private String email;
	private String username;
	private String token;
	public User()
	{}
	public User(String email, String username, String token) 
	{
		this.email = email;
		this.username = username;
		this.token = token;
	}
	public User(String username, String token) 
	{
		this.email = null;
		this.username = username;
		this.token = token;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getToken() 
	{
		return token;
	}
	public void setToken(String token) 
	{
		this.token = token;
	}
}
