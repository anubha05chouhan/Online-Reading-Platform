<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./BookPageStyle.css">
    <script defer>var pagesContent = <%= new org.json.JSONArray((String[])request.getAttribute("Book")).toString() %>;</script>
    <script src="./BookPageScript.js" defer></script>
</head>
<body>
    
    <div id="book" class="book">

    </div>
    <div class="buttons">

        <!-- Previous Button -->
        <input type="button" id="prev-btn" class="button" value="prev"/>
        
        <!-- Next Button -->
        <input type="button" id="next-btn" class="button" value="next"/>
    
    </div>
</body>
</html>
