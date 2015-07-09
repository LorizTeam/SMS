<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%@ page import="com.smsimobile.form.CustomerForm" %>
<%@ page import="com.smsimobile.form.SMSTemplateForm" %>
<%@ page import="com.smsimobile.form.SendSMSForm" %>
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
	<script type="text/JavaScript" src="js/CheckSendSMS.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	
    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	
   
	<script language="javascript">
	function getCustomer(tcustID) {
			if(document.sendSMSForm.custID.value!=""){
				var valueCust = document.sendSMSForm.custID.value+","+tcustID;	
				document.sendSMSForm.custID.value = valueCust;	
				document.sendSMSForm.hdCustID.value = valueCust;
			}else{
				var valueCust = tcustID;	
				document.sendSMSForm.custID.value = valueCust;
				document.sendSMSForm.hdCustID.value = valueCust;
			}
	}
	function getTel() {
			var valueCust = document.sendSMSForm.hdCustID.value;
			document.sendSMSForm.custID.value = valueCust;
	}
	function getTel1() {
			var valueCust = document.sendSMSForm.custID.value;
			document.sendSMSForm.hdCustID.value = valueCust;
	}
	
	function getSMSTemplate(tDescription) {
				var valueDes = tDescription;	
				document.sendSMSForm.description.value = valueDes;	
				document.sendSMSForm.hdDescription.value = valueDes;
	}
	function getTemp() {
			var valueDes = document.sendSMSForm.hdDescription.value;
			document.sendSMSForm.description.value = valueDes;
	}
	function getTemp1() {
			var valueDes = document.sendSMSForm.description.value;
			document.sendSMSForm.hdDescription.value = valueDes;
	}
	
/*	function countWord(){
	var countDes 		= document.getElementById('hdDescription').value;
	var countSendName 	= document.getElementById('sendName').value;
	var valueDes 		= countDes.length;
	var valueSendName 	= countSendName.length;
	
	var valueWord = valueDes+valueSendName;

	document.sendSMSForm.word.value = valueWord;
	}  */
	</script>
	<script type="text/javascript">
	$('#telNo').on('shown.bs.modal', function () {
  	$('#myInput').focus()
	})
	
	$('#smsTemp').on('shown.bs.modal', function () {
  	$('#myInput').focus()
	})   
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
		
	<html:form action="/sendSMS" method="POST">
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
            	<br/>
            	<div class="thumbnail col-md-12 col-lg-12">
            		<div><br/></div>
            		<div class="row">
    					<div class="col-md-6 col-md-offset-3">	
                        	<label>ขั้นตอนที่ 1 - กรุณาระบุเลขหมายมือถือ 10 หลักของผู้รับ</label>
                        </div>
    				</div>
            		<div class="row">
            			
    					<div class="col-md-6 col-md-offset-3">	
                        	<label>Phone Number.</label>
                        	<textarea class="form-control" rows="3" id="custID" name="custID" onkeyup="getTel1();"></textarea>
                        </div>
                       
    				</div>
    				<div class="row">
    					<div class="col-md-3">	
                        </div>
    					<div class="col-md-6 text-left">
    						<br/>	
                        	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#telNo">ค้นเบอร์จากสมุดโทรศัพท์</button>
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
    						<input class="form-control" placeholder="Name" id=sendName name="sendName" 
    						onkeyup="
							msgCheckerOnchange(document.sendSMSForm.sendName,document.sendSMSForm.description,
							document.sendSMSForm.word,document.sendSMSForm.baht);">
    					</div>
    				</div>
    				<div class="row">
    					<div class="col-md-6 col-md-offset-3">	
                        	<label>Message.</label>
                        	<textarea class="form-control" rows="3" id="description" name="description" 
                        	onkeyup="getTemp1();
							msgCheckerOnchange(document.sendSMSForm.sendName,document.sendSMSForm.description,
							document.sendSMSForm.word,document.sendSMSForm.baht);" ></textarea>
                    	</div>
                    	<div class="col-md-1">
                    		<br/>
                    		<input class="form-control" id="word" name="word" type="text" placeholder="Word" disabled>
                    		<input class="form-control" id="baht" name="baht" type="text" placeholder="Baht" disabled>
                    	</div>
    				</div>
    				
    				<div class="row">
    					<div class="col-md-3">	
                        </div>
    					<div class="col-md-3 text-left">
    						<br/>	
                        	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#smsTemp">SMS Template</button>
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
                        	<input type="submit" class="btn btn-primary btn-lg btn-block" value="Send SMS">
                        </div>
    				</div>
    				
            	</div>
            	<!-- #thumbnail -->
            </div>
            <!-- /.container-fluid -->
            
          <!-- Modal Phone Book -->
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
		        		<textarea class="form-control" rows="3" id="hdCustID" name="hdCustID" onkeyup="getTel();"></textarea>
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
		          <button type="button" class="btn btn-default" data-dismiss="modal"  onkeyup="getTemp();">Close</button>
		        </div>
		      </div>
		    </div>
		  </div>
		<!--closs Modal Phone Book -->
		
		<!-- Modal SMS Template -->
		  <div class="modal fade" id="smsTemp" role="dialog">
		    <div class="modal-dialog modal-lg">
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">List SMS Template</h4>
		        </div>
		        <div class="modal-body">
		        	<div class="row">
		        	<div  class="col-md-10 col-md-offset-1">
		        		<textarea class="form-control" rows="3" id="hdDescription" name="hdDescription" onkeyup="getTemp();
							msgCheckerOnchange(document.sendSMSForm.sendName,document.sendSMSForm.description,
							document.sendSMSForm.word,document.sendSMSForm.baht);"></textarea>
                     </div>
		        	</div>
		        	<div><br/></div>
			         <div class="row">
						<div class="col-md-10 col-md-offset-1">
							<table class="table table-bordered table-hover table-striped" id="dataTables-smsTemplate">
								<thead>
									<tr>
										<th class="text-center">Number</th>
										<th class="text-center">Description</th>
									</tr>
								</thead>
								<tbody>
									<%	if (request.getAttribute("smsTemplateList") != null) {
									List smsTemplateList = (List)request.getAttribute("smsTemplateList");
									int x = 0;
									for (Iterator iter = smsTemplateList.iterator(); iter.hasNext();) {
							  			x++;
							  			SMSTemplateForm smstempList = (SMSTemplateForm) iter.next();
					
									%>
									<tr>
										<td align="center"><%=x%> </td>
										<td align="center"><a href="javascript:getSMSTemplate('<%=smstempList.getDescription()%>');" 
										onclick="
										msgCheckerOnchange(document.sendSMSForm.sendName,document.sendSMSForm.description,
										document.sendSMSForm.word,document.sendSMSForm.baht);"><%=smstempList.getDescription()%></a></td>
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
		          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="getTel();
							msgCheckerOnchange(document.sendSMSForm.sendName,document.sendSMSForm.description,
							document.sendSMSForm.word,document.sendSMSForm.baht);">Close</button>
		        </div>
		      </div>
		    </div>
		  </div>
		<!--closs Modal SMS Template -->
          
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
    $(document).ready(function() {
        $('#dataTables-smsTemplate').DataTable({
                responsive: true
        });
    });
    </script>

</body>

</html>

