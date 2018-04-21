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
		out.println("username: "+userId);
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
		<form action="index.html" method="get" style="text-align:center;">
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
</html>