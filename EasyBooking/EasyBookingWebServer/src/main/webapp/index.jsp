<%@page import="java.util.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Proiect Web Test</title>
<style>
table, th, td {
    border: 1px solid #333;
    border-collapse: collapse;
    text-align: right;
    padding: 3px;
}
</style>
</head>
<body>
<h1>TI-P Test Website</h1>
<%
		// Verifică dacă a fost resetată sesiunea prin GET sau POST de resetare a sesiunii (vezi butonul de mai jos)
		if (request.getParameter("reset") != null) {
		    session.invalidate();
		    out.print("<strong>Sesiunea a fost invalidată/resetată</strong>");
		    return;
		}

		// Exemplul cu sesiuni a fost adaptat după cel de la adresa https://www.tutorialspoint.com/jsp/jsp_session_tracking.htm
		// Data creării sesiunii
		Date createTime = new Date(session.getCreationTime());
		// Data ultimei accesări a paginii
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		String title = "Bine ai REVENIT!";
		Integer visitCount = 0;
		String userId = "utilizator";
		
		// Verifică dacă este o sesiune nouă
		// Dacă este prima dată de la deschiderea browser-ului când se accesează pagina
		if (session.isNew()){
		   title = "Bine ai venit!";
		   session.setAttribute("visitCount",  visitCount);
		   session.setAttribute("userId", userId);
		}
		visitCount = (Integer) session.getAttribute("visitCount");
		visitCount = visitCount + 1;
		session.setAttribute("visitCount",  visitCount);

		// Verifică dacă a fost făcută o cerere prin GET sau POST de schimbare a numelui utilizatorului
		String username = request.getParameter("username");
		if (username != null) {
		    session.setAttribute("userId", username);
		}
		userId = (String) session.getAttribute("userId");
		
		
	%>
<section>
	<h2>Managementul sesiunilor - <% out.print(title); %></h2>
	<table> 
		<tr>
		   <th>Nume</th>
		   <th>Valoare</th>
		</tr> 
		<tr>
		   <td>Id sesiune</td>
		   <td><% out.print(session.getId()); %></td>
		</tr> 
		<tr>
		   <td>Data creării</td>
		   <td><% out.print(createTime); %></td>
		</tr> 
		<tr>
		   <td>Data ultimei accesări</td>
		   <td><% out.print(lastAccessTime); %></td>
		</tr> 
		<tr>
		   <td>Id utilizator</td>
		   <td><% out.print(userId); %></td>
		</tr> 
		<tr>
		   <td>Număr de vizite</td>
		   <td><% out.print(visitCount); %></td>
		</tr> 
</table> 
</section>
<section>
	<h2>Schimbare nume utilizator</h2>
	<form method="get" action="">
		<input type="text" name="username" placeholder="<%= userId %>"/>
		<input type="submit" value="Schimbă">
	</form>
	<%
		if (username != null) {
		    out.print("<strong>Numele utilizatorului a fost schimbat în " + username + "</strong>");
		}
	%>
</section>
<section>
	<h2>Resetare sesiune</h2>
	<form method="post" action="">
		<input type="hidden" name="reset" value="true"/>
		<input type="submit" value="Reset">
	</form>
</section>
<section>
	<h2>Calcul matematic</h2>
	<table>
		<thead>
			<tr>
				<th>x</th>
				<th>x<sup>2</sup></th>
				<th>x<sup>3</sup></th>
				<th>log(x)</th>
				<th>e<sup>x</sup></th>
			</tr>
		</thead>
		<tbody>
			<%
				DecimalFormat numberFormatter = new DecimalFormat("#0.00");
			    for (int i = 1; i <= 10; ++i) {
					%><tr><td><%=i%></td><%
					%><td><%=i*i%></td><%
					%><td><%=i*i*i%></td><%
					%><td><%=numberFormatter.format(Math.log10(i))%></td><%
					%><td><%=numberFormatter.format(Math.exp(i))%></td></tr><%
			    }
			%>
		</tbody>
	</table>
</section>
</body>
</html>