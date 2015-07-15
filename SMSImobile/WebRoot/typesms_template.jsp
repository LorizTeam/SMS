<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import ="javax.servlet.http.HttpServletRequest.*"%>
<%@ page import ="javax.servlet.http.HttpServletResponse.*"%>
<%@ page import ="javax.servlet.http.HttpSession.*"%>
<%@ page import="com.smsimobile.form.TypeSMSTemplateForm" %>
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
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<script language="javascript">
	function getTypeSMS(ttypeID, ttypeName) {
				document.typeSMSTemplateForm.groupID.value = ttypeID;	
				document.typeSMSTemplateForm.groupName.value = ttypeName;	
	}
	</script>
	
</head>
<body>

    <div id="wrapper">
        <br><!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            		<!-- /.menu top -->	
                      <%@ include file="/menu_top.jsp" %>  
                    <!-- /.menu top --> 
						
					<!-- /.menu left -->	
                      <%@ include file="/menu_left.jsp" %> 
                    <!-- /.menu left --> 
        </nav>
<html:form action="/typeSMSTemplate" method="POST">
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
            	<br/>
            	<div class="thumbnail col-md-12 col-lg-12">
            		<br/>
            		<div class="row">
	    				<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">	
	                    	<label>เพิ่มกลุ่ม ข้อความ</label>
	                	</div>
                	</div>
                	<!-- /.row -->
                	<div class="row">
	    				<div class="col-md-6 col-md-offset-3">	
	                    	<label>รายละเอียด</label>
    						<input type="text" id="groupName" name="groupName" class="form-control" placeholder="รายละเอียด">
    						<input type="hidden" id="groupID" name="groupID" > 
	                	</div>
                	</div>
                	<!-- /.row -->
                	<br/>
                	<div class="row">
    					<div class="col-md-6 col-md-offset-3">
    						<input type="submit" class="btn btn-primary" id="save" name="save" value="บันทึก">
    						<input type="submit" class="btn btn-primary" id="update" name="update" value="แก้ไข">
    						<input type="submit" class="btn btn-primary" id="delete" name="delete" value="ลบ">
                        </div>
    				</div>
    				<!-- /.row -->
    				<br/>
    				<div class="row">
                		<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3 table-responsive">
                			<table class="table table-bordered table-striped table-hover" id="dataTables-type">
                				<thead>
                					<th>ลำดับ</th>
                					<th>รายละเอียด</th>
                					<th>ลบ</th>
                				</thead>
                				<tbody>
                					<%	if (request.getAttribute("TypeSMSList") != null) {
									List TypeSMSList = (List)request.getAttribute("TypeSMSList");
									int x = 0;
									for (Iterator iter = TypeSMSList.iterator(); iter.hasNext();) {
							  			x++;
							  			TypeSMSTemplateForm typeList = (TypeSMSTemplateForm) iter.next();
					
									%>
									<tr>
										<td align="center"><%=x%></td>
										<td align="center"><a href="javascript:getTypeSMS('<%=typeList.getGroupID()%>','<%=typeList.getGroupName()%>');"><%=typeList.getGroupName()%></a></td>
										<td align="center"><input type="checkbox" id="chk1" name="chk1" value="<%=typeList.getGroupID()%>"></td>
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
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>
    
	<script>
    $(document).ready(function() {
        $('#dataTables-type').DataTable({
                responsive: true
        });
    });
    
    </script>
</body>

</html>

