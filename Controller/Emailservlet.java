package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Email;
import model.User;

@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User user = (User) request.getAttribute("user");
		String token = user.getToken();
		String message="Your token to login in the learning platform is = "+token+" dont share this with anyone";
	    String subject="Learning Platform Confirmation";
	    String to=user.getEmail();
	    String from="aadarshpatel901@gmail.com";
	    
	    Email email = new Email(message,subject,to,from);
        if(email.sendEmail())
        response.sendRedirect("Login.jsp");
	}
}

