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