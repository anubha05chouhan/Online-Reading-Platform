package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String token=request.getParameter("token");
		String username=request.getParameter("username");
		
		User user = new User(username,token);
		UserDAOImpl userDao = new UserDAOImpl();
		try 
		{			
			if (userDao.fetch(user)!=null) 
			{
	            // Redirect to success page
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
	            //response.sendRedirect("ContainerServlet");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("ContainerServlet");
	            dispatcher.forward(request, response);
	        } 
			else 
			{
	            // Redirect to error page
				request.setAttribute("error", "invalid token!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
                dispatcher.forward(request, response);
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			response.sendRedirect("Home.jsp");
		}
	}
}



