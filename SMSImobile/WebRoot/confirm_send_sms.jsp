<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%@ page import="com.smsimobile.form.SendSMSForm" %>
<%
	String errorMsg = null;
	String message = null;
	String sender = null;
	List recipientList = new ArrayList();
	
	String name = "";
	if(session.getAttribute("name") != null) {
		name = (String) session.getAttribute("name");
	}
	if(session.getAttribute("recipientList") != null) {
		recipientList = (List) session.getAttribute("recipientList");
	}
 	if(session.getAttribute("sender") != null) {
		sender = (String) session.getAttribute("sender");
	}
	if(session.getAttribute("message") != null) {
		message = (String) session.getAttribute("message");
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
            		<!-- /.menu top -->	
                      <%@ include file="/menu_top.jsp" %>  
                    <!-- /.menu top --> 
						
					<!-- /.menu left -->	
                      <%@ include file="/menu_left.jsp" %> 
                    <!-- /.menu left --> 
        </nav>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
            	<br/>
            	<div class="thumbnail col-md-12 col-lg-12">
            		<br/>
            		<div class="row">
	    				<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">	
	                    	<label>ยืนยันการส่ง SMS</label>
	                	</div>
                	</div>
                	<div class="row">
	    				<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">	
	                    	<label>กรุณาตรวจสอบข้อความ และยืนยันการส่ง SMS</label>
	                	</div>
                	</div>
                	<div class="row">
                		<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
                			<label>Message</label>
	                    	<textarea rows="3" cols="" class="form-control" id="custID" name="custID" disabled><%=message %></textarea>
	                	</div>
                	</div>
                	<div><br/></div>
                	<div class="row">
                		<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">	
                			<label>ชื่อผู้ส่ง</label>	
	                    	<input class="form-control" id="word" name="word" type="text" value="<%=sender %>" disabled>
	                	</div>
                	</div>
                	<div><br/></div>
                	<div class="row">
                		<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3 table-responsive">
                			<table class="table table-bordered table-striped table-hover" id="dataTables-example">
                				<thead>
                					<th>ลำดับ</th>
                					<th>เบอร์โทรปลายทาง</th>
                					<th>จำนวน (ข้อความ)</th>
                					<th>ราคา (บาท)</th>
                				</thead>
                				<tbody>
                					<% for(int i=0; i<recipientList.size(); i++) { 
                                               SendSMSForm smsForm = (SendSMSForm) recipientList.get(i);
                                               String recipient = smsForm.getCustID();
                                               int unit = smsForm.getUnit();
                                               double cost = smsForm.getCost();
                                     %>
                					<tr>
                						<td align="center"><%=(i+1) %></td>
                						<td align="center"><%=recipient %></td>
                						<td align="center"><%=unit %></td>
                						<td align="center"><%=cost %></td>
                					</tr>
                					<% } %>
                				</tbody>
                			</table>
                		</div>
                	</div>
                	<!-- row table -->
                	<div class="row">
                		<div class="col-md-6 col-md-offset-5 col-lg-6 col-lg-offset-5">
                			<button type="button" class="btn btn-primary">Edit SMS</button>
                			<button type="button" class="btn btn-primary">Send SMS</button>
                		</div>
                	</div>
                	<br/>
            	</div>
             	<!-- thumbnail -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"><!-- DataTables JavaScript --></script>
    <script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	 <script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"></script>
    

	<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
                responsive: true
        });
    });
    </script>
</body>

</html>

