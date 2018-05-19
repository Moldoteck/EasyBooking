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
	
	<div class="navbar" style="display:inline;">
		<form action="register.jsp" method="get">
			<button id="signIn" class="log">Sign In</button>
		</form>
	</div>
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
</html>