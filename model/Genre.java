package model;


public class Genre 
{
	private String genre_name;
	private int genre_id;	
	
	public Genre()
	{
		
	}
	public Genre(String genre_name) 
	{
		this.genre_name = genre_name;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	public int getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(int genre_id) 
	{
		this.genre_id=genre_id;
//		return UserDAO.getOrInsertGenre(genre_name);
	}	
}
