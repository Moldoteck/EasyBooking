<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/formsstyle.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700"
	rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Register</title>
</head>
<body>
<nav class="navbar navbar-expand-lg fixed-top ">

	<a class="navbar-brand" href="index.jsp"><img src="images/home.png" width="40" height="40"></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>

	  <div class="collapse navbar-collapse " id="navbarSupportedContent">
	    <ul class="navbar-nav mr-4">
	      
	      <li class="nav-item">
	        <a class="nav-link" data-value="become_a_host" href="addHome.jsp">Become a host</a>
	      </li>
			<li class="nav-item">
	        <a class="nav-link " data-value="login" href="login.jsp">Log in</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link " data-value="signup" href="register.jsp">Sign Up</a>
	      </li>

	    </ul>
	    
	  </div>

</nav>
 <br><br><br>
<div>
	<form action="ServletSignUp" method=post enctype="multipart/form-data">
		<div class="inputGroup inputGroup1">
			<label for="files" class="btn"><img src="images/userR.png"></label>
			<input type="text" id="files" name="photo" placeholder="Image Link">
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
<!-- add JavaScript file from js file -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src='js/main.js'></script>
</body>
</html>