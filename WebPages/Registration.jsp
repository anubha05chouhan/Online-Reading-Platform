<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="menustyle.css">
<script type="text/javascript" src="validate.js"></script>   
<!-- <script src="https://cdn.jsdelivr.net/npm/@supabase/supabase-js@2"></script>
<script src="supabase.js" type="module"></script> -->
</head>
<body>
    <%@include file="Menu.jsp" %>
	<div class="form-container">
    <form action="RegistrationServlet" method="post" name="regisForm">
        <h1>Registration</h1>
        <input class="username-input text-input" type="text" id="username" name="username" placeholder="username">
        <span id="validUsername" class="error-message"></span>
        <input class="email-input text-input" type="text" id="email" name="email" placeholder="email">
        <% 
        if (request.getAttribute("error") != null) 
        
        { %>
        
        <span><%= request.getAttribute("error") %></span>
        
     <% }
        
        %>
        <span id="validEmail" class="error-message"></span>
        <input type="button" class="button" onclick="validateRegis()" id="register" value="Register">
    </form>
    </div>
</body>
</html>
