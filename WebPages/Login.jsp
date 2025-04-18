<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="menustyle.css">
<script type="text/javascript" src="validate.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/@supabase/supabase-js@2"></script>
<script src="supabase.js" type="module"></script> -->
</head>
<body>
    <%@include file="Menu.jsp" %>
	<div class="form-container">
    <form action="LoginServlet" method="post" name="loginForm">
        <h1>Login</h1>
        <input class="username-input text-input" type="text" id="username" name="username" placeholder="username">
        <span id="validUsername" class="error-message"></span>
        <input class="token-input text-input" type="text" id="token" name="token" placeholder="Enter token no">
        <% 
        if (request.getAttribute("error") != null) 
        
        { %>
        
        <span><%= request.getAttribute("error") %></span>
        
     <% }
        
        %>
        <span id="validToken" class="error-message"></span>
        <input class="button" type="button" onclick="validateLogin()" id="login" value="Login">
    </form>
    </div>
</body>
</html>
