<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" rel="stylesheet">
<title>Easy Booking</title>
</head>
<body>
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
	
	if(userId!=null){
%>
		<br>
		<form action="index.jsp" method="post">
			<label for="logout">Hi, <%out.println(userId);%></label>
		<div class="inputGroup inputGroup3">
			<button id="logout" onclick="<%session.invalidate();%>">Log out</button>
		</div>
		</form>
<%
	}
	else{
%>
	<form action="login.jsp" method="get">
		<div class="inputGroup inputGroup3">
			<button id="login">Log in</button>
		</div>
	</form>
<%	} 
%>
</body>
</html>