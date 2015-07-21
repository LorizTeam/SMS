<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%
	String userName = "";
	if(session.getAttribute("userName") != null) {
		userName = (String) session.getAttribute("userName");
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
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<!-- date picker -->
    <link rel="stylesheet" href="css/jquery-ui.css"/>
	
</head>
<body>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            		<!-- /.menu top -->	
                      <%@ include file="/menu_top.jsp" %>  
                    <!-- /.menu top --> 
						
					<!-- /.menu left -->	
                      <%@ include file="/menu_left.jsp" %> 
                    <!-- /.menu left --> 
        </nav>
		<!-- end nav -->
		<html:form action="/smsschedulaReport" method="POST">
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
            	<br/>
            	<div class="thumbnail col-md-12 col-lg-12">
            		<br/>
            		<div class="row">
	    				<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">	
	                    	<label>Schedula SMS Report</label>
	                	</div>
                	</div>
                	<!-- /.row -->
                	<div class="row">
	    				<div class="col-md-3 col-md-offset-3 col-lg-3 col-lg-offset-3">	
	                    	<label>From Date</label>
    						<input id="fromDate" name="fromDate" class="form-control" placeholder="From Date">
	                	</div>
	                	<div class="col-md-3 col-lg-3">	
	                    	<label>To Date</label>
    						<input id="toDate" name="toDate" class="form-control" placeholder="To Date">
	                	</div>
                	</div>
                	<!-- /.row -->
                	<br/>
                	<div class="row">
    					<div class="col-md-3 col-md-offset-3 col-lg-3  col-lg-offset-3">
    						<input type="submit" class="btn btn-primary" id="print" name="print" value="Print">
                        </div>
    				</div>
    				<!-- /.row -->
                	<br/>
            	</div>
             	<!-- thumbnail -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->
</html:form>
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    
     <!-- datet -->
	 <script src="js/jquery-ui.js"></script>
	 
    <script>
    $(function() {
		    $( "#fromDate" ).datepicker({dateFormat: 'dd/mm/yy' });
		  });
	$(function() {
		    $( "#toDate" ).datepicker({dateFormat: 'dd/mm/yy' });
		  });
    </script>
</body>

</html>

