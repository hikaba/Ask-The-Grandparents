<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title>Ask the Grandparents</title>
		<style><%@include file="/views/topnavstylesheet.css"%></style>
		<style>
			p {
			  display: block;
			  margin-top: 50px;
			  display: block;
			  color: DodgerBlue;
			  text-align: center;
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

<p>${noResults} "<%=request.getAttribute("usernameDNE")%>"</p>


</body>
</html>