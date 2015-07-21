<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.*" %>
<%@ page import="com.smsimobile.util.DBConnect" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
           
<%
	Connection conn = null;
    try {
		DBConnect agent = new DBConnect();
  	  	conn = agent.getConnectMYSql();
	
		String userName = "";
		if (request.getAttribute("userName") != null) userName = (String) request.getAttribute("userName");
		
		String fromDate = "";
		if (request.getAttribute("fromDate") != null) fromDate = (String) request.getAttribute("fromDate");
		
		String toDate = "";
		if (request.getAttribute("toDate") != null) toDate = (String) request.getAttribute("toDate");
		
		String fileName = "";
	 	 fileName = "report/sendSMSScheduleReport.jasper";
	 		 
      	File reportFile = new File(application.getRealPath(fileName));
	    if (!reportFile.exists()) throw new JRRuntimeException("File not found. The report design must be compiled first.");

		Map parm = new HashMap();
		parm.put("prmusername", userName);
		parm.put("prmfromdate", fromDate);
		parm.put("prmtodate", toDate);
        
		byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parm, conn);
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();
    } catch (Exception e) {
	    throw new Exception(e.getMessage());
    } finally {
		try {
           	if (conn != null)  conn.close();
		} catch (SQLException e) {
    	   	throw new Exception(e.getMessage());
		}
	}
%>