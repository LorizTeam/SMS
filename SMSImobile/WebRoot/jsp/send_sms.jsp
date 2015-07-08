<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%@ page import="com.smsimobile.form.CustomerForm" %>
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
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script language="javascript">
	function getCustomer(tcustID) {
			if(document.customerForm.custID.value!=""){
				var valueCust = document.customerForm.custID.value+","+tcustID;	
				document.customerForm.custID.value = valueCust;	
				document.customerForm.hdCustID.value = valueCust;
			}else{
				var valueCust = tcustID;	
				document.customerForm.custID.value = valueCust;
				document.customerForm.hdCustID.value = valueCust;
			}
	}
	</script>
	<script type="text/javascript">
	$('#telNo').on('shown.bs.modal', function () {
  	$('#myInput').focus()
	})
	</script>
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
                                    <a href="importExcelStart.do">Excel Import</a>
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
                                    <a href="sendSMSStart.do">Send SMS</a>
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
		
	<html:form action="/sendSMS" method="POST">
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container">
            	<br/>
            	<div class="thumbnail">
            		<div><br/></div>
            		<div class="row">
    					<div class="col-md-6 col-md-offset-3">	
                        	<label>ขั้นตอนที่ 1 - กรุณาระบุเลขหมายมือถือ 10 หลักของผู้รับ</label>
                        </div>
    				</div>
            		<div class="row">
            			
    					<div class="col-md-6 col-md-offset-3">	
                        	<label>Phone Number.</label>
                        	<textarea class="form-control" rows="3" id="custID" name="custID" ></textarea>
                        </div>
                       
    				</div>
    				<div class="row">
    					<div class="col-md-3">	
                        </div>
    					<div class="col-md-3 text-right">
    						<br/>	
                        	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#telNo">ค้นเบอร์จากสมุดโทรศัพท์</button>
                        </div>
                        <div class="col-md-3">	
                        	<br/>
                        	<button type="button" class="btn btn-primary">เพิ่มรายชื่อลงสมุดโทรศัพท์</button>
                        </div>
    				</div>
    				<div class="row">
    					<div class="col-md-6 col-md-offset-3">
    						<hr/>
    					</div>
    				</div>
    				<div class="row">
    					<div class="col-md-6 col-md-offset-3">	
                        	<label>ขั้นตอนที่ 2 - กรุณาใส่ข้อความ (1 บาท / ข้อความ)</label>
                        </div>
    				</div>
            		<div class="row">
    					<div class="col-md-6 col-md-offset-3">
    						<label>Name Sending.</label>
    						<input class="form-control" placeholder="Name">
    					</div>
    				</div>
    				<div class="row">
    					<div class="col-md-6 col-md-offset-3">	
                        	<label>Message.</label>
                        	<textarea class="form-control" rows="3"></textarea>
                    	</div>
                    	<div class="col-md-1">
                    		<br/>
                    		<input class="form-control" id="disabledInput" type="text" placeholder="Word" disabled>
                    		<input class="form-control" id="disabledInput" type="text" placeholder="Message" disabled>
                    	</div>
    				</div>
    				<div class="row">
    					<div class="col-md-6 col-md-offset-3">	
    						<hr/>
    					</div>
    				</div>
    				<div class="row">
    					<div class="col-md-6 col-md-offset-3">	
                        	<label>ขั้นตอนที่ 3 - เลือกประเภทการส่ง</label>
                        </div>
    				</div>
    				<div class="row">
    					<div class="col-md-6 col-md-offset-3">	
                        	<label>Send Type.</label>
                        </div>
                        <div class="col-md-6 col-md-offset-3">	
                        	<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked> Send New.
                        </div>
                        <div class="col-md-6 col-md-offset-3 form-inline">	
                        	<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked> Set Date Time :
                            <input class="form-control" placeholder="Date Time">
                        </div>
    				</div>
    				<div><br/></div>
    				<div class="row">
    					<div class="col-md-4 col-md-offset-4">
                        	<button type="button" class="btn btn-primary btn-lg btn-block">Send SMS</button>
                        </div>
    				</div>
    				
            	</div>
            	<!-- #thumbnail -->
            </div>
            <!-- /.container-fluid -->
            
            	<!-- Modal -->
		  <div class="modal fade" id="telNo" role="dialog">
		    <div class="modal-dialog modal-lg">
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">List Customer Phone Book</h4>
		        </div>
		        <div class="modal-body">
		        	<div class="row">
		        	<div  class="col-md-10 col-md-offset-1">
		        		<textarea class="form-control" rows="3" id="hdCustID" name="hdCustID" ></textarea>
                     </div>
		        	</div>
		        	<div><br/></div>
			         <div class="row">
						<div class="col-md-10 col-md-offset-1">
							<table class="table table-bordered table-hover table-striped" id="dataTables-example">
								<thead>
									<tr>
										<th class="text-center">Number</th>
										<th class="text-center">Phone Number</th>
										<th class="text-center">Name</th>
									</tr>
								</thead>
								<tbody>
									<%	if (request.getAttribute("customerList") != null) {
									List customerList = (List)request.getAttribute("customerList");
									List sentList = new ArrayList();
									int x = 0;
									for (Iterator iter = customerList.iterator(); iter.hasNext();) {
							  			x++;
							  			CustomerForm custList = (CustomerForm) iter.next();
					
									%>
									<tr>
										<td align="center"><%=x%> </td>
										<td align="center"><a href="javascript:getCustomer('<%=custList.getCustID()%>');"><%=custList.getCustID()%></a></td>
										<td align="center"><%=custList.getCustName()%></td>
									</tr>
									<%		}
							 			} else {
									%>
									<tr><td align="center" colspan="7">No Data Found</td></tr>
									<%	} %>
								</tbody>
							</table>
						</div>
					</div>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		      </div>
		    </div>
		  </div>
		<!--closs Modal -->
          
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

    <!-- DataTables JavaScript -->
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

