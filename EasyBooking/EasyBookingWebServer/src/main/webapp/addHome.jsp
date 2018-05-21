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
<body class="background_ram">
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

 <br><br><br>
<div>
<form action="TestRentalAndHome" method=post enctype="multipart/form-data" class="userform"
	style="width:70%; position:absolute; left:15%; background-color: #FFF;
  margin: 0;
  padding: 2.25em;
  box-sizing: border-box;
  border: solid 1px #DDD;
  border-radius: .5em;
  font-family: 'Source Sans Pro', sans-serif;">

	<h1 class="heading">Become a host</h1>
	
	<div class="form-group">
    	<label for="name" style="color:#217093;">Name</label>
    	<input type="text" id="name"
				class="form-control" maxlength="256" name="name" placeholder = "Name" required>
  	</div>
  	<div class="form-group">
    	<label for="description" style="color:#217093;">Description</label>
    	<input type="text" id="description"
				class="form-control" maxlength="500" name="description" placeholder = "Description" required>
  	</div>
  	  <div class="form-group">
    	<label for="price" style="color:#217093;">Price</label>
    	<input type="text" id="price"
				class="form-control" maxlength="500" name="price" placeholder = "Price" required>
  	</div>
    <div class="form-group">
    	<label for="stars" style="color:#217093;">Stars</label>
    	<input type="text" id="stars"
				class="form-control" maxlength="500" name="stars" placeholder = "Stars" required>
  	</div>
  	<div class="form-group">
    	<label for="nr_review" style="color:#217093;">Nr review</label>
    	<input type="text" id="nr_review"
				class="form-control" maxlength="500" name="nr_review" placeholder = "Nr review" required>
  	</div>
  	 <div class="form-group">
    	<label for="image_path" style="color:#217093;">Image path</label>
    	<input type="text" id="image_path"
				class="form-control" maxlength="500" name="image_path" placeholder = "Image path" required>
  	</div>
  	<div>
		<div class="inputGroup inputGroup3">
			<button class="buton" >Add announcement</button>
		</div>
		</div>		
</form>
</div>
</body>
</html>