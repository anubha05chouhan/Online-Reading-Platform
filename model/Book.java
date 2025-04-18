package model;

public class Book 
{
	private String title;
    private int author_id;
    private int book_id;
    private String cover_image_path; 
	
	public Book()
	{
		
	}
    public Book(String title, int author_id) 
	{
		this.title = title;
		this.author_id = author_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public int getBook_id() 
	{
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getCover_image_path() 
	{
		return cover_image_path;
	}
	public void setCover_image_path(String cover_image_path) 
	{
		this.cover_image_path = cover_image_path;
	}
	
}
