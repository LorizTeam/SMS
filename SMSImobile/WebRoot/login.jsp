<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Welcome</title>
		<link rel="stylesheet" href="css/bootstrap.css" />
		<link rel="stylesheet" href="css/bootstrap-theme.css" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		
		<style>
			body{
				padding-top:200px;
				background-color:#f5f5f5;
			}
			
			.profile-img {
				width: 96px;
				height: 96px;
				margin: 0 auto 10px;
				display: block;
				-moz-border-radius: 50%;
				-webkit-border-radius: 50%;
				border-radius: 50%;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-offset-4">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<strong>Sign in</strong>
						</div>
						<div class="panel-body">
							<div class="row" align="center">
								<div class="center-block">
									<img class="profile-img" src="img/Team-Members.png"/>
								</div>
							</div>
							<%String login = (String) session.getAttribute("login");
							if(login =="0"){%>
							<div align="center"><font color="red">Username and Password Incorrect</font></div>
							<%
							} %>
							<html:form action="/login" method="POST">
								<div class="form-group">
									<label>Username</label>
									<input class="form-control" placeholder="Username" name="userName" type="text" />
								</div>
								<div class="form-group">
									<label>Password</label>
									<input class="form-control" placeholder="Password" name="passWord" type="password"/>
								</div>
								<input class="btn btn-md btn-primary btn-block" type="submit" value="Login" />
							</html:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script src="js/bootstrap.js"></script>
		<script src="js/jquery-1.11.3.min.js"></script>
	</body>
</html>