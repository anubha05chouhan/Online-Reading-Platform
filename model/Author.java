package model;

public class Author 
{
	private String author_name;
	private int author_id;
	
	public Author(String author_name) 
	{
		this.author_name = author_name;
	}
	
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id=author_id;
//		return UserDAO.getOrInsertAuthor(author_name);
	}
	
}
