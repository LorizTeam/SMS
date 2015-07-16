<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%@ page import="com.smsimobile.form.SchduleForm" %>
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
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<script language="javascript">
	function getSchedule(tcustid, tmessage, tsending, tdatetime, tunit, tcost, tusername) {
				document.schduleForm.custid.value = tcustid;		
				document.schduleForm.message.value = tmessage;
				document.schduleForm.sending.value = tsending;
				document.schduleForm.datetime.value = tdatetime;
				document.schduleForm.unit.value = tunit;
				document.schduleForm.cost.value = tcost;
				document.schduleForm.username.value = tusername;
	}
	</script>
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
<html:form action="/schdule">
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
	    				<div class="col-md-2 col-md-offset-2 col-lg-2 col-lg-offset-2">	
	                    	<input type="submit" class="btn btn-primary" value="ลบ">
	                	</div>
                	</div>
                	<div class="row">
	    				<br/>
                	</div>
                	<div class="row">
                		<div class="col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 table-responsive">
                			<table class="table table-bordered table-striped table-hover" id="dataTables-schedule">
                				<thead>
                					<th><center>ลำดับ</center></th>
                					<th><center>เบอร์ผู้รับ</center></th>
                					<th><center>ข้อความ</center></th>
                					<th><center>เวลาทำรายการ</center></th>
                					<th><center>ผู้ส่ง</center></th>
                					<th><center>จำนวน (ข้อความ)</center></th>
                					<th><center>ราคา </center></th>
                					<th><center>ลบ SMS</center></th>
                				</thead>
                				<tbody>
                					<%	if (request.getAttribute("scheDuleList") != null) {
									List scheDuleList = (List)request.getAttribute("scheDuleList");
									int x = 0;
									for (Iterator iter = scheDuleList.iterator(); iter.hasNext();) {
							  			x++;
							  			SchduleForm scheduleList = (SchduleForm) iter.next();
					
									%>
                					<tr>
                						<td align="center"><%=x %></td>
                						<td align="center"><a href="javascript:getSchedule('<%=scheduleList.getCustid()%>','<%=scheduleList.getMessage()%>',
                						'<%=scheduleList.getSending()%>','<%=scheduleList.getDatetime()%>','<%=scheduleList.getUnit()%>',
                						'<%=scheduleList.getCost()%>','<%=scheduleList.getUsername()%>');"><%=scheduleList.getCustid()%></a>
                						</td>
                						<td align="center"><%=scheduleList.getMessage()%></td>
                						<td align="center"><%=scheduleList.getDatetime()%></td>
                						<td align="center"><%=scheduleList.getSending()%></td>
                						<td align="center"><%=scheduleList.getUnit()%></td>
                						<td align="center"><%=scheduleList.getCost()%></td>
                						<td align="center">
                						
                							<input type="checkbox" id="chk1" name="chk1" value="<%=scheduleList.getCustid()%>">
                				
                						</td>
                					</tr>
                					<% 	}
                						} else {
                					 %>
                					<tr><td align="center" colspan="7">No Data Found</td></tr>
									<%	} %>
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

	<script>
    $(document).ready(function() {
        $('#dataTables-schedule').DataTable({
                responsive: true
        });
    });
    
    </script>

</body>

</html>

