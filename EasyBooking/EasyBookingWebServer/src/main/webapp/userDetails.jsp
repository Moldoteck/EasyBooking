<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700"
	rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Details</title>
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
<form action="ServletSignUp.java" method=post enctype="multipart/form-data" class="userform"
	style="width:70%; position:absolute; left:15%; background-color: #FFF;
  margin: 0;
  padding: 2.25em;
  box-sizing: border-box;
  border: solid 1px #DDD;
  border-radius: .5em;
  font-family: 'Source Sans Pro', sans-serif;">

	<h1 class="heading">Hi, <%session.getAttribute("userId");%></h1>
	
	<div class="form-group">
    	<label for="image" style="color:#217093;"><img src="images/userR.png"></label>
    	<input type="text" id="image"
				class="form-control" name="image" placeholder="Change image">
  	</div>
  	<div class="form-group">
    	<label for="first_name" style="color:#217093;">First Name</label>
    	<input type="text" id="first_name"
				class="form-control" maxlength="256" name="first_name" placeholder = "First Name" required>
  	</div>
  	<div class="form-group">
    	<label for="last_name" style="color:#217093;">Last Name</label>
    	<input type="text" id="last_name"
				class="form-control" maxlength="256" name="last_name" placeholder = "Last Name" required>
  	</div>
  	<div class="form-group">
    	<label for="exampleInputEmail1" style="color:#217093;">Email Address</label>
    	<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email">
  	</div>
    <div class="form-group">
    	<label for="phone_number" style="color:#217093;">Phone Number</label>
    	<input type="text" class="form-control" id="exampleInputEmail1" placeholder="Phone Number">
  	</div>
</form>
</div>
<!-- add JavaScript file from js file -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src='js/main.js'></script>
</body>
</html>