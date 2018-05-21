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
<nav class="navbar navbar-expand-lg navbar-expand-xs navbar-expand-md navbar-expand-sm fixed-top ">

		<a class="navbar-brand" href="index.jsp"><img src="images/home.png"
			width="40" height="40"></a>
	

		<div class="collapse navbar-collapse " id="navbarSupportedContent">
			<ul class="navbar-nav mr-4">

				<li class="nav-item"><a class="nav-link" 
				data-value="become_a_host" href="addHome.jsp">Become a host</a></li>

				<%	
				if(session.getAttribute("userId")!=null){
		  %>
				 <li class="nav-item">
			<div class="navbar" style="display:inline;">
			<li><img style="margin-left:0px; padding-left:0px;" src="images/user.png" alt="user img" width="50" height="50"></li>			
			<a id="user_details" class="nav-link" href="userDetails.jsp"> <%out.print(session.getAttribute("userId")); %></a>
			<form action="LogOutServlet" method="get" enctype="multipart/form-data">
				<button class=" btn nav-link btn-link">Log Out</button>
			</form>
			</div>
		</li>
				<% } else {%>
				<li class="nav-item"><a class="nav-link " data-value="login"
					href="login.jsp">Log in</a></li>
				<li class="nav-item"><a class="nav-link " data-value="signup"
					href="register.jsp">Sign Up</a></li>
				<% } %>
				</li>
			</ul>

		</div>

	</nav>
<div class="jumbotron">
	<form action="TestRentalAndHome" method=post enctype="multipart/form-data">
		<div class="inputGroup inputGroup1">
			<input type="text" id="name"
				class="first_name" maxlength="256" name="name" placeholder = "Name" required/>
			<span class="indicator"></span>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text" id="description"
				class="description" maxlength="256" name="description" placeholder = "Description" required/>
			<span class="indicator"></span>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text"
				id="price" class="price" name="price" placeholder = "Price" required/>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text"
				id="stars" class="password" name="stars" placeholder = "Stars" required/>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text" id="nr_review"
				class="nr_review" maxlength="256" name="nr_review" placeholder = "Nr_review" required/>
			<span class="indicator"></span>
		</div>
		<div class="inputGroup inputGroup1">
			<input type="text" id="path_img"
				class="path_img" maxlength="256" name="path_img" placeholder = "Path image" required/>
			<span class="indicator"></span>
		</div>
		<div>
			<button id="signin">See it</button>
		</div>
	</form>
</div>
</body>
</html>