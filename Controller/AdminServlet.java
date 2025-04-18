package controller;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dao.AuthorDAOImpl;
import dao.BookDAOImpl;
import dao.BookGenreDAOImpl;
import dao.GenreDAOImpl;
import model.Author;
import model.Book;
import model.BookGenre;
import model.Genre;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			
		String folderPath=getServletContext().getRealPath("/books");
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		
		for(File file : files)
		{
			if(file.isDirectory())
			{
				Genre genre = new Genre(file.getName());
				GenreDAOImpl genreDao = new GenreDAOImpl();
				if(genreDao.fetch(genre).getGenre_id()==0)
				{
					genreDao.insert(genre);
				}
				File books[] = file.listFiles();
				
				for(File book : books)
				{
					String fileName=book.getName();
					int dotIndex = fileName.lastIndexOf('.');
					fileName = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
					String title=fileName;
					System.out.print(title);
					String author_name=getAuthorName(title);					
					Author author = new Author(author_name);
					AuthorDAOImpl authorDao = new AuthorDAOImpl();
					if(authorDao.fetch(author).getAuthor_id()==0)
					{
						authorDao.insert(author);
					}
					Book book1 = new Book(title,author.getAuthor_id());
					setCoverImagePath(book1);
					BookDAOImpl bookDao = new BookDAOImpl();
					if(bookDao.fetch(book1).getBook_id()==0)
					{
						bookDao.insert(book1);
						BookGenre bookGenre = new BookGenre(book1.getBook_id(), genre.getGenre_id());
						BookGenreDAOImpl bookGenreDao = new BookGenreDAOImpl();
						bookGenreDao.insert(bookGenre);
					}
				}
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private String getAuthorName(String title)
	{
		String author_name="";
		try 
		{					
		String urlString = "https://www.googleapis.com/books/v1/volumes?q=intitle:\"" + URLEncoder.encode(title, "UTF-8") + "\"";
		System.out.println(urlString);
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();

		int responseCode = conn.getResponseCode();
		if (responseCode != 200) {
		    throw new RuntimeException("HttpResponseCode: " + responseCode);
		} else {
		    StringBuilder inline = new StringBuilder();
		    Scanner scanner = new Scanner(url.openStream());

		    while (scanner.hasNext()) {
		        inline.append(scanner.nextLine());
		    }
		    scanner.close();

		    JSONParser parser = new JSONParser();
		    JSONObject data_obj = (JSONObject) parser.parse(inline.toString());
		    JSONArray items = (JSONArray) data_obj.get("items");
		    JSONObject item = (JSONObject) items.get(0);
		    JSONObject volumeInfo = (JSONObject) item.get("volumeInfo");
		    JSONArray authorsArray = (JSONArray)volumeInfo.get("authors");
		    author_name = (String) authorsArray.get(0);
		}
    	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return author_name;
	}
	
	private void setCoverImagePath(Book book)
	{
		try 
		{
					
		String bookTitle = book.getTitle();
		String urlString = "https://www.googleapis.com/books/v1/volumes?q=intitle:\"" + URLEncoder.encode(bookTitle, "UTF-8") + "\"";
		System.out.println(urlString);
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();

		int responseCode = conn.getResponseCode();
		if (responseCode != 200) {
		    throw new RuntimeException("HttpResponseCode: " + responseCode);
		} else {
		    StringBuilder inline = new StringBuilder();
		    Scanner scanner = new Scanner(url.openStream());

		    while (scanner.hasNext()) {
		        inline.append(scanner.nextLine());
		    }
		    scanner.close();

		    JSONParser parser = new JSONParser();
		    JSONObject data_obj = (JSONObject) parser.parse(inline.toString());
		    JSONArray items = (JSONArray) data_obj.get("items");
		    JSONObject item = (JSONObject) items.get(0);
		    JSONObject volumeInfo = (JSONObject) item.get("volumeInfo");
		    JSONObject imageLinks = (JSONObject) volumeInfo.get("imageLinks");
		    String imageUrl = (String) imageLinks.get("thumbnail");
		    book.setCover_image_path(imageUrl);
		    System.out.print(imageUrl);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}

