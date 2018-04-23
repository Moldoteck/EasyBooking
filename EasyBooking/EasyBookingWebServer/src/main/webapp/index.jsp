<<<<<<< HEAD
<%@page import="java.util.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Log In</title>
<style>
table, th, td {
    border: 1px solid #333;
    border-collapse: collapse;
    text-align: right;
    padding: 3px;
}
.section1{
	text-align:center;
	float:center;
	width:80%;
	margin:10px;
	padding:5px 5px;
	position:relative;
	left:100px;
}
</style>
</head>
<body>

<%
		String userId = null;
		
		// Verifică dacă este o sesiune nouă
		// Dacă este prima dată de la deschiderea browser-ului când se accesează pagina
		if (session.isNew()){
		   session.setAttribute("userId", userId);
		}

		// Verifică dacă a fost făcută o cerere prin GET sau POST de schimbare a numelui utilizatorului
		String username = request.getParameter("username");
		if (username != null) {
		    session.setAttribute("userId", username);
		}
		userId = (String) session.getAttribute("userId");
	%> 
<section class="section1">
	<%--
	după ce introduci datele de logare când dai click pe login să se ducă la servlet. 
	Acolo verifici dacă username-ul și parola sunt corecte și dacă sunt redirectezi 
	spre pagina de detalii user, dacă nu, redirectezi spre pagina de login
	 --%>
	 <%--Mă gândesc să facem și pagina de Sign Up, așa am avea și al treilea task --%>
	<br>
	<%
	if(userId!=null){
		out.println("You are Already Logged In");
	%>
		<br>
	<% 
		out.println("username: "+(String) session.getAttribute("userId"));
	%>
		<br>
		<form action="index.html" method="post">
		<input type="Submit" value="Log Out" onclick="<%session.invalidate();%>"/>
		</form>
	<%
	}else{
	%>
	<div style="text-align:center;  border: 2px solid #0f4159; width:35%; margin:auto;">
		<h1 style="text-align:center;"> Log In </h1>
		<form action="TestServlet" method="post" style="text-align:center;">
			<input type="text" id="user" name="username" placeholder="username"/>
			<br><br>
			<input type="password" id="pass" name="pass" placeholder="password"/>
			<br><br>
			<input type="Submit" value="Log In"/>
			<br><br>
		</form>
	</div>
	<%} 
	%>
</section>
</body>
=======
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/indexstyle.css">
<title>Easy Booking</title>
</head>
<%
	String userId = null;

	// Verifică dacă este o sesiune nouă
	// Dacă este prima dată de la deschiderea browser-ului când se accesează pagina
	if (session.isNew()) {
		session.setAttribute("userId", userId);
	}

	// Verifică dacă a fost făcută o cerere prin GET sau POST de schimbare a numelui utilizatorului
	String username = request.getParameter("uname");
	if (username != null) {
		session.setAttribute("userId", username);
	}
	userId = (String) session.getAttribute("userId");
	%>
	
<body>
<header>
<nav>
<%	
	if(userId!=null){
%>
	<div class="navbar" style="display:inline;">
	<p><em>Hi, <%out.print(session.getAttribute("userId")); %></em></p>
		<form action="index.jsp" method="get">
			<button id="logout" class="log" onclick="<%session.invalidate();%>">Log Out</button>
		</form>
	</div>
<%
	}
	else{
%>
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
	</div> -->
<%	} 
%>
</nav>
</header>
<br><br><br>
<section id="main">
	<br><br><br><br><br><br><br><br>
	<form action="" method="post">
	<input type="text" placeholder="Search for a country or a city" style="float:center; width:40%; padding:10px; box-sizing:border-box; border:2px solid #0f4159;"/>
	<input type="submit" id="buton" value="Search" style="width:10%; padding:10px; font-weight:bold; background:#0f4159; color:white;"/>
	</form>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br>
</section>
</body>
>>>>>>> Login_JSP
</html>