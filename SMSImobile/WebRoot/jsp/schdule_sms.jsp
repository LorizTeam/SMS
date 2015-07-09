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
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SMS</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">SMS Service</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">

                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="#"><i class="fa fa-user-md fa-fw"></i> Member<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="excel_import.jsp">Excel Import</a>
                                </li>
                                <li>
                                    <a href="phone_book.jsp">Phone Book</a>
                                </li>
                            </ul>
                            <!-- /.nav-second member-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-envelope-o fa-fw"></i> SMS<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="send_sms.jsp">Send SMS</a>
                                </li>
                                <li>
                                    <a href="schdule_sms.jsp">Schdule SMS</a>
                                </li>
                                <li>
                                    <a href="sms_tempate.jsp">SMS Tempate</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
						<li>
                            <a href="select_centact.jsp"><i class="fa fa-list fa-fw"></i> Select Contact</a>
                        </li>
                        <li>
                            <a href="report_sms.jsp"><i class="fa fa-print fa-fw"></i> Report SMS</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <br/>
            	<div class="thumbnail col-md-12 col-lg-12">
            		<br/>
            		<div class="row">
	    				<div class="col-md-6 col-md-offset-2 col-lg-6 col-lg-offset-2">	
	                    	<label>รายการตั้งเวลาส่ง SMS ล่วงหน้า</label>
	                	</div>
                	</div>
                	<div class="row">
                		<div class="col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 table-responsive">
                			<table class="table table-bordered table-striped table-hover">
                				<thead>
                					<th><center>ลำดับ</center></th>
                					<th><center>วันที่ทำรายการ</center></th>
                					<th><center>วันที่ส่ง SMS</center></th>
                					<th><center>ข้อความ</center></th>
                					<th><center>เบอร์ผู้รับ</center></th>
                					<th><center>จำนวน (ข้อความ)</center></th>
                					<th><center>ลบ SMS</center></th>
                				</thead>
                				<tbody>
                					<tr>
                						<td align="center">1</td>
                						<td align="center">082446278</td>
                						<td align="center">2</td>
                						<td align="right">6</td>
                						<td align="center">082446278</td>
                						<td align="center">2</td>
                						<td align="center">ลบ</td>
                					</tr>
                					<tr>
                						<td align="center">2</td>
                						<td align="center">082446278</td>
                						<td align="center">2</td>
                						<td align="right">6</td>
                						<td align="center">082446278</td>
                						<td align="center">2</td>
                						<td align="center">ลบ</td>
                					</tr>
                				</tbody>
                			</table>
                		</div>
                	</div>
                	<!-- row table -->
            	</div>
            	<!-- thumbnail -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>

