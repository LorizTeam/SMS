<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%@ page import="com.smsimobile.form.SMSTemplateForm" %>
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
	function getSMSTemplate(tdesID, tType) {
				document.smstemplateForm.description.value = tdesID;		
				document.smstemplateForm.type.value = tType;
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

        <!-- Page Content -->
         <div id="page-wrapper">
            <div class="container-fluid">
            	<br/>
            	<div class="thumbnail col-md-12 col-lg-12">
            		<br/>
            		<div class="row">
	    				<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">	
	                    	<label>เพิ่มข้อความในโอกาสพิเศษ</label>
	                	</div>
                	</div>
                	<!-- /.row -->
                	<div class="row">
	    				<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">	
	                    	<label>Message</label>
    						<textarea class="form-control" rows="3" cols="" id="description" name="description"></textarea>
	                	</div>
                	</div>
                	<!-- /.row -->
                	<div class="row">
	    				<div class="col-md-2 col-md-offset-3 col-lg-2 col-lg-offset-3">	
    						<label for="sel1">Message Type</label>
						    <select class="form-control" id="custType" name="custType" >
						    	<option value="Z">Select</option>
						    	<option value="A">วันปีใหม่</option>
						        <option value="B">วันเกิด</option>
						        <option value="C">วันพ่อ</option>
						        <option value="D">วันแม่</option>
						        <option value="E">วันสงการณ์</option>
						        <option value="F">วันตรุษจีน</option>
						    </select>
	                	</div>
                	</div>
                	<!-- /.row -->
                	<br/>
                	<div class="row">
    					<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
    						<input type="submit" class="btn btn-primary" value="บันทึก">
                        	<button type="button" class="btn btn-primary">แก้ไข</button>
                        </div>
    				</div>
    				<!-- /.row -->
    				<br/>
    				<div class="row">
                		<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3 table-responsive">
                			<table class="table table-bordered table-striped table-hover" id="dataTables-customer">
                				<thead>
                					<th><center>ลำดับ</center></th>
                					<th>ข้อความ</th>
                					<th>ประเภท</th>
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
										<td align="left"><a href="javascript:getSMSTemplate('<%=smstempList.getDescription()%>','<%=smstempList.getType()%>');"><%=smstempList.getDescription()%></a></td>
										<%if(smstempList.getType().equals("A")) { %>
											<td align="left">วันปีใหม่</td>
										<%}else if(smstempList.getType().equals("B")){%>
											<td align="left">วันเกิด</td>
										<%}else if(smstempList.getType().equals("C")){%>
											<td align="left">วันพ่อ</td>
										<%}else if(smstempList.getType().equals("D")){%>
											<td align="left">วันแม่</td>
										<%}else if(smstempList.getType().equals("E")){%>
											<td align="left">วันสงการณ์</td>
										<%}else if(smstempList.getType().equals("F")){%>
											<td align="left">วันตรุษจีน</td>
										<%}else{ %>
											<td align="left">Select</td>
										<%} %>
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
                	<!-- row table -->
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
    <script src="dist/js/sb-admin-2.js"></script>
    
    <script>
    $(document).ready(function() {
        $('#dataTables-customer').DataTable({
                responsive: true
        });
    });
    
    </script>

</body>

</html>

