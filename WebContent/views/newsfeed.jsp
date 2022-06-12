<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
  
 
  <div class="searchbar">
    <form action="<%= request.getContextPath() %>/search" method = "get">
      <input type="text" placeholder="Search for a User.." id="searchquery" name="searchquery">
      <button type="submit">Enter</button>
    </form>
  </div>

</div>  

</body>
</html>