<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="capstone.model.Post" %>


<!DOCTYPE html>
<html lang=\"en\">
	<head>
		<title>Ask the Grandparents</title>
		<style><%@include file="/views/topnavstylesheet.css"%></style>
		
		<style>
	
			ul {
				list-style-type: none;
				margin: 0;
				padding: 0;
				overflow: hidden;
				border: 1px solid #e7e7e7;
				background-color: #f3f3f3;
			}

			li {
				float: right;
			}

			li a {
				display: block;
				color: DodgerBlue;
				text-align: center;
				padding: 14px 16px;
				text-decoration: none;
			}

			li a:hover:not(.active) {
				background-color: #ddd;
			}

			li a.active {
				color: white;
				background-color: DodgerBlue;
			}
			
			.content {
				border: 5px groove DodgerBlue;
				margin: auto;
				padding: 25px;
				width: 90%;
				margin-top: 25px;
				font-family: Arial, sans-serif;
				font-style: italic;
				color: DodgerBlue;
			}
			.new-post {
				border: 5px groove DodgerBlue;
				margin: auto;
				padding: 25px;
				width: 90%;
				margin-top: 25px;
				font-family: Arial, sans-serif;
				font-style: italic;
				color: DodgerBlue;
			}
			#user-post {
				width: 100%;

			}
			fieldset {
				border-color: white;
				width: 100%;
				padding: 0px;
				border: 0px;
				margin: 0px;
			}
		</style>
	</head>
	<body>
		<div class="topnav">
			<a href="<%= request.getContextPath() %>/logout">Log Out</a>
  			<a href="<%= request.getContextPath() %>/profile"> <%=request.getSession().getAttribute("username")%></a>
 			<a class="active" href="<%= request.getContextPath() %>/newsfeed">Ask The Grandparents</a>
		</div> 	
	
		
<div class="content" id="text-content">
		<table class="table">
			<tbody>
			<h2><%=request.getSession().getAttribute("fName")%> <%=request.getSession().getAttribute("lName")%> </h2>
			</tbody>
	</table>
</div>
	
	<div class="content" id="text-content">
		<h3>Create a Post</h3>
        <form action="<%= request.getContextPath() %>/posts" method="post">
          <input type="hidden" name="id" value="">          
          
          <label for="title">Title:</label>
          <br>
          <textarea id="title" name="title" rows="1" cols="50" required="required" placeholder="Enter A title for your post"></textarea>
          <br><br>
               
          <label for="content">Content:</label><br>
 			<textarea id="content" name="content" rows="10" cols="50" required="required" placeholder="Enter an interesting post"></textarea>
          <input type="submit" value="Post">
        </form>
        
	</div> 
	<br>
	<div class="content" id="text-content">
			<table class="table">
				<tbody>
			    	<% 
                    Map<UUID, Post> posts = (Map)request.getAttribute("userPosts");
                    if (posts != null) {
                    	for (UUID uuid : posts.keySet()) { %>
                      		<tr><td> <%=posts.get(uuid).getTitle()%> </td></tr>
                        	<tr><td> <%=posts.get(uuid).getContent()%> </td></tr>
                        	<tr><td> <a href="posts?id=<%=posts.get(uuid).getId()%>">Edit</a>
									 <a href="posts?id=<%=posts.get(uuid).getId().toString()%>&action=delete">Delete</a></td></tr>
							<tr><td><hr></td></tr>
                      <% } 
                      } %>
				</tbody>
			</table>
		</div>
	
		  

</body>
</html>