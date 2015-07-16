<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%@ page import="com.smsimobile.form.SendSMSEveryDayForm" %>
<%
	String time = "";
	
	if(request.getAttribute("time") != null) {
		time = (String) request.getAttribute("time");
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
	
	<!-- datetime picker -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="dist/bootstrap-clockpicker.min.css">
	<link rel="stylesheet" type="text/css" href="assets/css/github.min.css">
<style type="text/css">
.navbar h3 {
	color: #f5f5f5;
	margin-top: 14px;
}
.hljs-pre {
	background: #f8f8f8;
	padding: 3px;
}
.footer {
	border-top: 1px solid #eee;
	margin-top: 40px;
	padding: 40px 0;
}
.input-group {
	width: 120px;
	margin-bottom: 0px;
}
.pull-center {
	margin-left: auto;
	margin-right: auto;
}
@media (min-width: 768px) {
  .container {
    max-width: 730px;
  }
}
@media (max-width: 767px) {
  .pull-center {
    float: right;
  }
}
</style>
    <!-- #datetime picker end -->
	
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
<html:form action="/sendSMSEveryDay">
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <br/>
            	<div class="thumbnail col-md-12 col-lg-12">
            		<br/>
            		<div class="row">
	    				<div class="col-md-6 col-md-offset-2 col-lg-6 col-lg-offset-2">	
	                    	<label>รายการส่ง SMS ทุกวัน</label>
	                	</div>
                	</div>
					<div class="row">
	    				<div class="col-md-6 col-md-offset-2 col-lg-6 col-lg-offset-2">
	    					<label>Set Time</label>
	    				</div>
                	</div>
                	<div class="row">
                		<div class="col-md-5 col-md-offset-2 col-lg-5 col-lg-offset-2 form-inline">
	                    	<div class="form-group">	
								<div class="clearfix">
									<div class="input-group clockpicker pull-center" data-placement="right" data-align="top" data-autoclose="true">
										 <input type="text" class="form-control" value="<%=time%>" id="time" name="time" >
										 <span class="input-group-addon">
										 	<span class="glyphicon glyphicon-time"></span>
										 </span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-primary" value="Set Time" id="save" name="save" >
							</div>
	                	</div>
                	</div>
                	<div class="row">
	    				<div class="col-md-2 col-md-offset-10 col-lg-2 col-lg-offset-9">	
	                    	<input type="submit" class="btn btn-primary" value="ลบ">
	                	</div>
                	</div>
                	<div class="row">
                		<div class="col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2">
                			<table class="table table-bordered table-striped table-hover" id="dataTables-everyday">
                				<thead>
                					<th>ลำดับ</th>
                					<th>เบอร์ผู้รับ</th>
                					<th>ข้อความ</th>
                					<th>ผู้ส่ง</th>
                					<th>จำนวน (ข้อความ)</th>
                					<th>ราคา </th>
                					<th>ลบ SMS</th>
                				</thead>
                				<tbody>
                					<%	if (request.getAttribute("SendSMSEverydayList") != null) {
									List SendSMSEverydayList = (List)request.getAttribute("SendSMSEverydayList");
									int x = 0;
									for (Iterator iter = SendSMSEverydayList.iterator(); iter.hasNext();) {
							  			x++;
							  			SendSMSEveryDayForm everyDay = (SendSMSEveryDayForm) iter.next();
					
									%>
                					<tr>
                						<td align="center"><%=x %></td>
                						<td align="center"><a href="javascript:getSchedule('<%=everyDay.getCustid()%>','<%=everyDay.getMessage()%>',
                						'<%=everyDay.getSending()%>','<%=everyDay.getUnit()%>',
                						'<%=everyDay.getCost()%>','<%=everyDay.getUsername()%>');"><%=everyDay.getCustid()%></a>
                						</td>
                						<td align="center"><%=everyDay.getMessage()%></td>
                						<td align="center"><%=everyDay.getSending()%></td>
                						<td align="center"><%=everyDay.getUnit()%></td>
                						<td align="center"><%=everyDay.getCost()%></td>
                						<td align="center">
                						
                							<input type="checkbox" id="chk1" name="chk1" value="<%=everyDay.getCustid()%>">
                				
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

	<!-- datetime -->
<script type="text/javascript" src="assets/js/jquery.min.js"></script>
<script type="text/javascript" src="dist/bootstrap-clockpicker.min.js"></script>
<script type="text/javascript">
$('.clockpicker').clockpicker()
	.find('input').change(function(){
		console.log(this.value);
	});
var input = $('#single-input').clockpicker({
	placement: 'bottom',
	align: 'left',
	autoclose: true,
	'default': 'now'
});

$('.clockpicker-with-callbacks').clockpicker({
		donetext: 'Done',
		init: function() { 
			console.log("colorpicker initiated");
		},
		beforeShow: function() {
			console.log("before show");
		},
		afterShow: function() {
			console.log("after show");
		},
		beforeHide: function() {
			console.log("before hide");
		},
		afterHide: function() {
			console.log("after hide");
		},
		beforeHourSelect: function() {
			console.log("before hour selected");
		},
		afterHourSelect: function() {
			console.log("after hour selected");
		},
		beforeDone: function() {
			console.log("before done");
		},
		afterDone: function() {
			console.log("after done");
		}
	})
	.find('input').change(function(){
		console.log(this.value);
	});

// Manually toggle to the minutes view
$('#check-minutes').click(function(e){
	// Have to stop propagation here
	e.stopPropagation();
	input.clockpicker('show')
			.clockpicker('toggleView', 'minutes');
});
if (/mobile/i.test(navigator.userAgent)) {
	$('input').prop('readOnly', true);
}
</script>
<script type="text/javascript" src="assets/js/highlight.min.js"></script>
<script type="text/javascript">
hljs.configure({tabReplace: '    '});
hljs.initHighlightingOnLoad();
</script>
	 <!-- datetime end -->

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>

	<!-- DataTables JavaScript -->
    <script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"></script>

	<script>
    $(document).ready(function() {
        $('#dataTables-everyday').DataTable({
                responsive: true
        });
    });
    
    </script>

</body>

</html>

