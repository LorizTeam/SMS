<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%@ page import="com.smsimobile.form.CustomerForm" %>
<%@ page import="com.smsimobile.form.TypeCustomerForm" %>
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
	
	<script language="javascript">
	function getCustomer(tcustID, tcustName, tcustType) {
				document.customerForm.custID.value = tcustID;	
				document.customerForm.custName.value = tcustName;	
				document.customerForm.custType.value = tcustType;
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
        <!-- end nav -->
<html:form action="/phoneBook" method="POST">
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
            	<br/>
            	<div class="thumbnail col-md-12 col-lg-12">
            		<br/>
            		<div class="row">
	    				<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">	
	                    	<label>เพิ่มรายชื่อลงสมุดโทรศัพท์</label>
	                	</div>
                	</div>
                	<!-- /.row -->
                	<div class="row">
	    				<div class="col-md-6 col-md-offset-3">	
	                    	<label>Name</label>
    						<input id="custName" name="custName" class="form-control" placeholder="Name">
	                	</div>
                	</div>
                	<!-- /.row -->
                	<div class="row">
	    				<div class="col-md-6 col-md-offset-3">	
	                    	<label>Phone Number</label>
    						<input id="custID" name="custID" class="form-control" placeholder="Phone Number">
	                	</div>
                	</div>
                	<!-- /.row -->
                	<div class="row">
	    				<div class="col-md-3 col-md-offset-3">	
    						<label for="sel1">Select Type Customer</label>
						    <select class="form-control" id="custType" name="custType" >
						    	<% if (request.getAttribute("TpyeCustomerList") != null) {
											List TpyeCustomerList = (List)request.getAttribute("TpyeCustomerList");
					   						for (Iterator iterItem = TpyeCustomerList.iterator(); iterItem.hasNext();) {
					   							TypeCustomerForm grpInfo = (TypeCustomerForm) iterItem.next();
					       			%>
				        			<option value="<%=grpInfo.getGroupID()%>">
				        				<%=grpInfo.getGroupName()%>
				        			</option>
									<% 		} 
										} 
									%>
						    </select>
	                	</div>
                	</div>
                	<!-- /.row -->
                	<br/>
                	<div class="row">
    					<div class="col-md-6 col-md-offset-3">
    						<input type="submit" class="btn btn-primary" id="save" name="save" value="บันทึก">
    						<input type="submit" class="btn btn-primary" id="update" name="update" value="แก้ไข">
                        </div>
    				</div>
    				<!-- /.row -->
    				<br/>
    				<div class="row">
                		<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3 table-responsive">
                			<table class="table table-bordered table-striped table-hover" id="dataTables-customer">
                				<thead>
                					<th>ลำดับ</th>
                					<th>เบอร์โทรศัพท์</th>
                					<th>ชื่อ</th>
                					<th>ประเภท</th>
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
										<td align="center"><a href="javascript:getCustomer('<%=custList.getCustID()%>','<%=custList.getCustName()%>',
										'<%=custList.getCustType()%>');"><%=custList.getCustID()%></a></td>
										<td align="center"><%=custList.getCustName()%></td>
										<td align="center"><%=custList.getTypeName()%></td>
									
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
        $('#dataTables-customer').DataTable({
                responsive: true
        });
    });
    
    </script>
</body>

</html>

