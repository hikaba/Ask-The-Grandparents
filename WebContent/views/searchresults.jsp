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
			  color: DodgerBlue;
			  text-align: left;
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

<%int status = (int)request.getAttribute("status"); %>
<div class="content" id="text-content">
		<table class="table">
			<tbody>
			
				<td style="padding:10px">
           	    	<h2><%=request.getAttribute("fName")%> <%=request.getAttribute("lName")%> </h2>
           	    </td>
                <td style="padding:10px">
                	<h2><a href="<%= request.getContextPath() %>/userProfile?id=${friendId}">${username}</a></h2>
                </td>
                <!-- not friends -->
                <%if(status == 2){ %>
	                <td>
	                	<h2><a class = "button"href="<%= request.getContextPath() %>/friendRequest?id=${friendId} action = add">Add Friend</a></h2>
	                </td>
                <%} %>
                <!-- friends -->
                <%if(status == 1){ %>
	                <td>
	                	<h2><a href="<%= request.getContextPath() %>/friendRequest?id=${friendId} action = remove">Unfriend</a></h2>

	                </td>
                <%} %>
                <!-- requested -->
                <%if(status == 0) {%>
	                <td>
	                	<h2><a class = "button"href="<%= request.getContextPath() %>/friendRequest?id=${friendId}">Requested</a></h2>
		            </td>
                <%} %>
  
           	    
			</tbody>
	</table>
</div>



</body>
</html>