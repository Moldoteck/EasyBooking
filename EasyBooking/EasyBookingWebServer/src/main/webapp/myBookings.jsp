<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Bookings</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css" href="css/indexstyle.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body >
	<nav
		class="navbar navbar-expand-lg navbar-expand-xs navbar-expand-md navbar-expand-sm fixed-top ">

		<a class="navbar-brand" href="index.jsp"><img
			src="images/home.png" width="40" height="40"></a>


		<div class="collapse navbar-collapse " id="navbarSupportedContent">
			<ul class="navbar-nav mr-4">

				<%
					if (session.getAttribute("userId") != null) {
				%>
				<li class="nav-item">
					<div class="navbar" style="display: inline;">

						<li><img style="margin-left: 0px; padding-left: 0px;"
							src="images/user.png" alt="user img" width="50" height="50"></li>
						<a id="user_details" class="nav-link" href="userDetails.jsp">
							<%
								out.print(session.getAttribute("userId"));
							%>
						</a> 
						<a class="nav-link" data-value="become_a_host" href="addHome.jsp">Become
							a host</a>
						<form action="myBookingsServlet" method="get"
							enctype="multipart/form-data">
							<button class=" btn nav-link btn-link">My Bookings</button>
						</form>
						<form action="LogOutServlet" method="get"
							enctype="multipart/form-data">
							<button class=" btn nav-link btn-link">Log Out</button>
						</form>
					</div>
				</li>
				<%
					} else {
				%>
				<li class="nav-item"><a class="nav-link " data-value="login"
					href="login.jsp">Log in</a></li>
				<li class="nav-item"><a class="nav-link " data-value="signup"
					href="register.jsp">Sign Up</a></li>
				<%
					}
				%>
				</li>
			</ul>

		</div>

	</nav>

<div>

<%if(session.getAttribute("bookingResult")!=null)
	{
	%><br/><br/><br/><br/><br/><br/><% 
		out.print(session.getAttribute("bookingResult"));
	}
	else
	{
		%> <p> ERROOOOOR</p>
	<% }
%>
</div>
</body>
</html>