package com.smsimobile.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smsimobile.util.DBConnect;
import com.smsimobile.util.DateUtil;
import com.smsimobile.form.SchduleForm;


public class SchduleDB {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	DateUtil dateUtil = new DateUtil();
	
	public List GetScheduleList(String custid) 
	throws Exception { //30-05-2014
		List scheDuleList = new ArrayList();
		String message = "";
		String sending = "";
		String datetime = "";
		String unit = "";
		String cost = "";
		String username = "";
		String date = "";
		String time = "";
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT custid, message, sending, datetime, unit, cost, username " +
			"FROM sms_schedule " +
			"WHERE "; 
			if(!custid.equals("")) sqlStmt = sqlStmt+ "custid like '"+custid+"%' AND ";
			
			sqlStmt = sqlStmt + "custid <> '' order by custid ";
			
			//System.out.println(sqlStmt);
			String forwhat = "hot";
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				if (rs.getString("custid") != null) custid = rs.getString("custid"); else custid = "";
				message = rs.getString("message");
				sending = rs.getString("sending"); 
				datetime = rs.getString("datetime"); 
				unit = rs.getString("unit"); 
				cost = rs.getString("cost"); 
				username = rs.getString("username"); 
				
				date = datetime.substring(0, 10);
				date = dateUtil.CnvToDDMMYYYY(date);
				time = datetime.substring(10, 19);
				datetime = date+" "+time;
				
				scheDuleList.add(new SchduleForm(forwhat, custid, message, sending, datetime, unit, cost, username));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return scheDuleList;
	 }
	public void DeleteSchedule(String custID)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "delete from sms_schedule where custid in "+custID+" ";

		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
}



