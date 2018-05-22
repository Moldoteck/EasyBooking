<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css" href="css/indexstyle.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper-utils.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper-utils.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper-utils.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper-utils.min.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper.min.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper-utils.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper-utils.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper-utils.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper-utils.min.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/popper.min.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper-utils.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper-utils.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper-utils.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper-utils.min.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.js.map"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js.map"></script>

<title>Register</title>
</head>
<body>

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
						</a> <a class="nav-link" data-value="become_a_host" href="addHome.jsp">Become
							a host</a>
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

	<%
		if (session.getAttribute("searchedHome") != null) {
	%><br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<%
		out.print(session.getAttribute("searchedHome"));
	%>
	<style>
table {
	border-collapse: collapse;
}
</style>



	<div>
		<form action="BookServlet" method=post enctype="multipart/form-data"
			class="userform"
			style="width: 70%; position: absolute; left: 15%; background-color: #FFF; margin: 0; padding: 2.25em; box-sizing: border-box; border: solid 1px #DDD; border-radius: .5em; font-family: 'Source Sans Pro', sans-serif;">

			<h1 class="heading">Time to book</h1>

			<div class="form-group">
				<label for="check_in" style="color: #217093;">Check-In</label> <input
					type="date" id="check_in" class="form-control" maxlength="256"
					name="check_in" value="2018-05-22" required>
			</div>
			<div class="form-group">
				<label for="check_out" style="color: #217093;">Check-Out</label> <input
					type="date" id="check_out" class="form-control" maxlength="500"
					name="check_out" value="2018-05-22" required>
			</div>
			<div class="form-group">
				<label for="discount" style="color: #217093;">Discount</label> <input
					type="text" id="discount" class="form-control" maxlength="500"
					name="discount" readonly>
			</div>
			<div class="form-group">
				<label for="final_price" style="color: #217093;">Final Price</label>
				<input type="text" id="final_price" class="form-control"
					maxlength="500" name="final_price" readonly>
			</div>
			<div>
				<div class="inputGroup inputGroup3">
					<button class="buton">Book</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function eventFire(el, etype) {
			if (el.fireEvent) {
				el.fireEvent('on' + etype);
			} else {
				var evObj = document.createEvent('Events');
				evObj.initEvent(etype, true, false);
				el.dispatchEvent(evObj);
			}
		}

		var _num1 = document.getElementById('check_in');
		var _num2 = document.getElementById('check_out');
		var _disc = document.getElementById('discount');
		var _finalprice = document.getElementById('final_price');

		_num1.onchange = function() {
			var difference = Math.floor((Date.parse(document
					.getElementById('check_out').value) - Date
					.parse(this.value))
					/ (1000 * 60 * 60 * 24));
			if (difference >= 5)
				_disc.value = 10;
			else
				_disc.value = 0;
			_finalprice.value = (parseFloat(
	<%out.print(session.getAttribute("priceHome"));%>
		) - (parseFloat(_disc.value)
					* parseFloat(
	<%out.print(session.getAttribute("priceHome"));%>
		) / 100));
			eventFire(_disc, "click");
			console.log("first0");
		};
		_num2.onchange = function() {
			var difference = Math.floor((Date.parse(this.value) - Date
					.parse(document.getElementById('check_in').value))
					/ (1000 * 60 * 60 * 24));
			if (difference >= 5)
				_disc.value = 10;
			else
				_disc.value = 0;
			_finalprice.value = (parseFloat(
	<%out.print(session.getAttribute("priceHome"));%>
		) - (parseFloat(_disc.value)
					* parseFloat(
	<%out.print(session.getAttribute("priceHome"));%>
		) / 100));
			eventFire(_disc, 'click');

			console.log("second");
		};
	</script>
	<%
		}
	%>

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src='js/main.js'></script>

</body>
</html>