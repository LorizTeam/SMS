<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%
	String name = "";
	
	if(session.getAttribute("name") != null) {
		name = (String) session.getAttribute("name");
	}
 
 %>
<!DOCTYPE html>
<html>
	<head>
    <title>Main</title>
		<link rel="stylesheet" href="css/bootstrap.css" />
		<link rel="stylesheet" href="css/bootstrap-theme.css" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/jquery-ui.css"/>
    	<link href="css/simple-sidebar.css" rel="stylesheet">
    	
	<style>
			body{
				padding-top:52px;
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
  	<div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        Start Bootstrap
                    </a>
                </li>
                <li>
                    <a href="index.jsp">Dashboard</a>
                </li>
                <li>
                    <a href="#">Shortcuts</a>
                </li>
                <li>
                    <a href="#">Overview</a>
                </li>
                <li>
                    <a href="#">Events</a>
                </li>
                <li>
                    <a href="#">About</a>
                </li>
                <li>
                    <a href="#">Services</a>
                </li>
                <li>
                    <a href="#">Contact</a>
                </li>
            </ul>
        </div>
    <div class="container-fuild">
			<nav class="navbar navbar-fixed-top navbar-inverse">
	      		<div class="container-fuild">
	        		<div class="navbar-header">
	          			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            			<span class="sr-only">Toggle navigation</span>
	            			<span class="icon-bar"></span>
	            			<span class="icon-bar"></span>
	            			<span class="icon-bar"></span>
	          			</button>
	          			<a class="navbar-brand" href="jsp/main.jsp"><img src="img/logo.png" height="27" width="50"/></a>
	          			<a class="navbar-brand" href="#">SMS imobile</a>
	        		</div>
	        		<div id="navbar" class="collapse navbar-collapse">
	          			<ul class="nav navbar-nav navbar-right">
	            			<li align="right"><a href="#contact">Login Name : <%=name%> </a></li>
	            			<li align="right"><a href="/SMSImobile/login.jsp">Log Out</a></li>
	          			</ul>
	        		</div><!-- /.nav-collapse -->
	      		</div><!-- /.container -->
    		</nav>
    		
    		<div class="row">
				<div class=""></div>
				<div class="col-md-6">
				<div class="panel panel-primary">
				<div class="panel-heading"><h3 class="panel-title">Member Detail</h3></div>
				<div class="panel-heading"><h3 class="panel-title">Pattern Message Call</h3></div>
				</div>
				<div class="panel-body">
			
								<div class="row">
									<div class="col-md-12">
										<div class="panel panel-danger">
											<div class="panel-heading"><h3 class="panel-title">Pattern Message Call</h3></div>
											<div class="panel-body">
												สวัสดีค่ะบริษัท.... บริการโทรเตือนการชำระเงินค่าผ่อนรถยนต์<br/>
												ซึ้งต้องจ่ายภายในวันที่....<br/>
												เป็นจำนวนเงินทั้งหมด....<br/>
												ขอบคุณค่ะ ...<br/>
											</div>
										</div>
									</div>
								</div>
    		</div>
    		</div>
    		</div>
	    	<div class="col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading"><h3 class="panel-title">Member List</h3></div>
								<div class="panel-body">
								<div class="row">
										<div class=""></div>
										<div class="col-md-3">
										
										</div>						
			</div>
			</div>						
			</div>	
			</div>		
			 			
    </div> <!-- /.container-fuild -->
    </div>
  </body>
</html>
