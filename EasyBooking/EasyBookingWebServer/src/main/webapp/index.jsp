<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Easy Booking</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css" href="css/indexstyle.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper-utils.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper-utils.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper-utils.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper-utils.min.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper.min.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper-utils.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper-utils.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper-utils.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper-utils.min.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper.min.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper-utils.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper-utils.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper-utils.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper-utils.min.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.js.map"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js.map"></script>


</head>
<%
	String userId = null;

	// Verifică dacă este o sesiune nouă
	// Dacă este prima dată de la deschiderea browser-ului când se accesează pagina
	if (session.isNew()) {
		session.setAttribute("userId", userId);
	}

	userId = (String) session.getAttribute("userId");
%>
	
<body>

<nav class="navbar navbar-expand-lg fixed-top ">

	<a class="navbar-brand" href="#"><img src="images/home.png" width="40" height="40"></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon">
	    </span>
	    
	  </button>

	  <div class="collapse navbar-collapse " id="navbarSupportedContent">
	    <ul class="navbar-nav mr-4">
	      
	      <li class="nav-item">
	        <a class="nav-link" data-value="become_a_host" href="home.jsp">Become a host</a>
	      </li>
	      
	      <%	
			if(userId!=null){
				session.setAttribute("direction", "userDetails.jsp");
				request.setAttribute("direction", "userDetails.jsp");
		  %>
		  <li class="nav-item">
			<div class="navbar" style="display:inline;">
			<li><img src="images/user.png" alt="user img" width="50" height="50"></li>
			<form action="index.jsp" method="get">
			<a id="user_details" class="nav-link" href="userDetails.jsp"> <%out.print(session.getAttribute("userId")); %> Details</a>
			<button class="nav-link" onclick="LogOutServlet">Log Out</button>
			</form>
			</div>
		</li>
		<% } else {%>
			<li class="nav-item">
	        <a class="nav-link " data-value="login" href="login.jsp">Log in</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link " data-value="signup" href="register.jsp">Sign Up</a>
	      </li>
	      <% } %>
		</li>
	    </ul>
	    
	  </div>

</nav>

<header class="header ">
<br><br><br><br>            <br><br><br><br>

 <div class="overlay"></div>
   <div class="container"  style="text-align: center;">
	<div class="row">
        <div class="col-md-6">
            <div id="custom-search-input"  style="text-align: center;">

                <div class="input-group col-md-12">
                    <input type="text" class="form-control input-lg" placeholder="Search" />
                    <div class="input-group-btn">
                        <button class="btn btn-info btn-lg" type="button">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>
</header>

<!-- portfolio -->
<div class="portfolio" id="portfolio">
     <h1 class="text-center">Homes around the world</h1>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12">
				<img src="images/portfolio/port13.png" class="img-fluid">
			</div>
		</div>
	</div>
</div>



<!-- Team section -->
<div class="team" id="team">
	<div class="container">
	   <h1 class="text-center">Our Team</h1>
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-12 item" >
				<img src="images/gina.jpg" class="img-fluid" alt="team">
				 <div class="des">
				 	Ungureanu Elena-Georgiana
				 </div>
				<span class="text-muted">Team Member</span>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-12 item">
				<img src="images/magda.jpg" class="img-fluid" alt="team">
				<div class="des">
				 	 B&#259;rbieru Magda
				 </div>
				<span class="text-muted">Team Member</span>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-12 item">
				<img src="images/carmencita.jpg" class="img-fluid" alt="team">
				<div class="des">
				 	Bogli&#537; Carmen 
				 </div>
				<span class="text-muted">Team Member</span>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-12 item">
				<img src="images/cristi.jpg" class="img-fluid" alt="team">
				 <div class="des">
				 	P&#259;dureac Cristian
				 </div>
				<span class="text-muted">Team Member</span>
			</div>
			
			<div class="col-lg-3 col-md-3 col-sm-12 item" style="width:800px; margin:0 auto;">
				<img src="images/ramona.jpg" class="img-fluid" alt="team">
				<div class="des">
				 	Bodron Ramona-Elena
				 </div>
				<span class="text-muted">Team Leader</span>
			</div>
		</div>
	</div>
</div>

<!-- Contact form -->
<div class="contact-form" id="contact">
	<div class="container">
		<form>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-12">
				  <h1>Get in Touch</h1>	
				</div>
				<div class="col-lg-8 col-md-8 col-sm-12 right">
				   <div class="form-group">
				   	 <input type="text" class="form-control form-control-lg" placeholder="Your Name" name="">
				   </div>
				   <div class="form-group">
				   	 <input type="email" class="form-control form-control-lg" placeholder="YourEmail@email.com" name="email">
				   </div>
				   <div class="form-group">
				   	 <textarea class="form-control form-control-lg">
				   	 	
				   	 </textarea>
				   </div>
				   <input type="submit" class="btn btn-secondary btn-block" value="Send" name="">
				</div>
			</div>
		</form>
	</div>
</div>
<!-- add Javasscript file from js file -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src='js/main.js'></script>
</body>
</html>