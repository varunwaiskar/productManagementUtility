<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User</title>
<%@include file="components/common_css_js.jsp"%>
</head>
<body>

	<%@include file="components/navbar.jsp"%>
	<div class="row mt-5">
		<div class="col-md-4 offset-md-4">

			<div class="card">
				<%@include file="components/message.jsp"%>
				<div class="card-body px-5">
					<h3 class="text-center my-3">Sign Up here</h3>
					<form action="RegisterServlet" method="post">
						<div class="form-group">
							<label for="user">User Name</label> <input type="text"
								name="user_name" class="form-control" id="user"
								placeholder="Enter name here">
						</div>
						<div class="form-group">
							<label for="email">Email address</label> <input type="email"
								name="user_email" class="form-control" id="email"
								placeholder="Enter email here">
						</div>
						<div class="form-group">
							<label for="password">User Password</label> <input
								type="password" name="user_password" class="form-control"
								id="password" placeholder="Enter password here">
						</div>
						<div class="form-group">
							<label for="type">User type</label> <input type="text"
								name="user_type" class="form-control" id="type"
								placeholder="Enter User Type here">
						</div>
						<div class="container text-center">
							<button type="submit" class="btn btn-outline-success">Submit</button>
							<button type="reset" value="clear"
								class="btn btn-outline-warning">Reset</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>