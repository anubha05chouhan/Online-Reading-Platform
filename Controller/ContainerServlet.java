package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookGenreDAOImpl;
import dao.GenreDAOImpl;
import model.Book;
import model.Genre;

@WebServlet("/ContainerServlet")
public class ContainerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
		GenreDAOImpl genreDao = new GenreDAOImpl();
		ArrayList<Genre> list = (ArrayList<Genre>) genreDao.fetchAll();
		BookGenreDAOImpl bookGenreDao = new BookGenreDAOImpl();
		Map<String, List<Book>> genreBooksMap = bookGenreDao.fetchGenreAndBooks();
		request.setAttribute("genreBooksMap", genreBooksMap);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
        dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}

