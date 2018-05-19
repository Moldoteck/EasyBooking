<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700"
	rel="stylesheet">
<title>Register</title>
</head>
<body>
<header>
<nav>
	<div class="navbar" style="display:inline;">
		<form action="login.jsp" method="get">
			<button id="logIn" class="log">Log In</button>
		</form>
	</div>
	<!-- 
	<div class="navbar" style="display:inline;">
		<form action="index.jsp" method="get">
			<button id="signIn" class="log">Sign In</button>
		</form>
	</div>-->
</nav>
</header>
<br><br><br>
<section id="main">
<div class="jumbotron">
	<form action="ServletSignUp.java" method=post enctype="multipart/form-data">
		<div class="inputGroup inputGroup1">
			<label for="files" class="btn"><img src="user.png"></label>
			<input type="file" id="files" name="photo" accept="image/*">
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text" id="first_name"
				class="first_name" maxlength="256" name="first_name" placeholder = "First Name" required/>
			<span class="indicator"></span>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text" id="last_name"
				class="last_name" maxlength="256" name="last_name" placeholder = "Last Name" required/>
			<span class="indicator"></span>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text" id="username"
				class="username" maxlength="256" name="username" placeholder = "Username" required/>
			<span class="indicator"></span>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="password"
				id="password" class="password" name="password" placeholder = "Password" required/>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text" id="email"
				class="email" maxlength="256" name="email" placeholder = "Email" required/>
			<span class="indicator"></span>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text" id="phone_number"
				class="phone_number" maxlength="256" name="phone_number" placeholder = "Phone number" required/>
			<span class="indicator"></span>
		</div>
		<div>
			<button id="signin">Sign in</button>
		</div>
	</form>
</div>
</section>
</body>
</html>