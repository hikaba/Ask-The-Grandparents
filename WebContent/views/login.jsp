<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title> Ask the Grandparents </title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<style>
			p {
				text-align: center;
			}
		
			header {
				background-color: DodgerBlue;
				font-family: Arial, sans-serif;
				font-style: italic;
				font-size: 25px;
				margin: 25px;
				padding: 25px;
				overflow: hidden;				
				color: white;
				text-align: center;
			}
			
			.sign {
				border: 5px groove DodgerBlue;
				margin: auto;
				padding: 25px;
				width: 300px;
				margin-top: 25px
			}
			
			fieldset {
				font-family: Arial, sans-serif;
				font-style: italic;
				color: DodgerBlue;
			}
			
			ul {
				list-style-type: none;
			}
		</style>

	</head>
	<body>
		<header>
			Ask the Grandparents
		</header>
			<div id="data" >
				<form action="<%= request.getContextPath() %>/login" method="post">
				<p><font color = "red">${errorMessage}</font></p>
				<p><font color = "green">${logoutMessage}</font></p>
					<fieldset class="sign">
						<legend> Login </legend>
						<ul>
							<li>
								<label for="username"> Username </label>
								<input type="text" id="username" name="username" required="required" placeholder="Enter your username"/>
							</li>
							<br>
							<li>
								<label for="password"> Password </label>
								<input type="password" id="password" name="password" required="required" placeholder="Enter your password"/>
							</li>

						</ul>
						<input type="submit" value="login"/>
					</fieldset>
				</form>
			</div>
           
            <form action="<%= request.getContextPath() %>/register" method="get">
            	<fieldset class="sign">
						<legend> Don't have an account? </legend>
						
						<input type="submit" value="SignUp">
					</fieldset>
                
            </form>
		</body>
	</html>