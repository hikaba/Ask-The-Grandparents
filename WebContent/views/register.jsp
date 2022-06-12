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
				<form action="<%= request.getContextPath() %>/register" method="post">
					<p><font color = "red">${errorMessage}</font></p>
					<p><font color = "red">${errorMessage2}</font></p>
					<fieldset class="sign">
						<legend> Sign Up </legend>
						<ul>
							<li>
								<label for="first_name"> First Name </label>
								<input type="text" id="first_name" name="first_name" required="required" placeholder="Enter your first name"/>
							</li>
                            <br>
							<li>
								<label for="last_name"> Last Name </label>
								<input type="text" id="last_name" name="last_name" required="required" placeholder="Enter your last name"/>
							</li>
                            <br>
                            <li>
								<label for="user_name"> Username </label>
								<input type="text" id="username" name="username" required="required" placeholder="Enter your Username"/>
							</li>
                            <br>
							<li>
								<label for="password"> Password </label>
								<input type="password" id="password" name="password" required="required" placeholder="Enter a password"/>
							</li>
                            <br>
							<li>
								<label for="email"> Email </label>
								<input type="email" id="email" name="email" required="required" placeholder="Enter your email"/>
							</li>
                            <br>
							<li>
								<label for="birthdate">Birthdate </label>
								<input type="date" id="birthdate" name="birthdate" required="required">
							</li>

						</ul>
						<input type="submit" value="Sign Up"/>
					</fieldset>
				</form>
			</div>
		</body>
	</html>