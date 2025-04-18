package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String token = UUID.randomUUID().toString().substring(0, 6);
		User user = new User(email,username,token);
		UserDAOImpl userDao = new UserDAOImpl();
		try
		{
			if(userDao.insert(user)!=null)
		    {
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/EmailServlet");
				dispatcher.forward(request, response);
			}
			else 
			{
                request.setAttribute("error", "Email already registered!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.jsp");
                dispatcher.forward(request, response);
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.sendRedirect("Registration.jsp");
		}
	}

}

