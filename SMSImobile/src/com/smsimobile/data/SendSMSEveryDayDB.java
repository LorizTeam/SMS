package com.smsimobile.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smsimobile.util.DBConnect;
import com.smsimobile.form.SendSMSEveryDayForm;


public class SendSMSEveryDayDB {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	
	public List GetSendSMSEverydayList(String custid) 
	throws Exception { //30-05-2014
		List SendSMSEverydayList = new ArrayList();
		String message = "";
		String sending = "";
		String unit = "";
		String cost = "";
		String username = "";
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT custid, message, sending, unit, cost, username " +
			"FROM sms_everyday " +
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
				unit = rs.getString("unit"); 
				cost = rs.getString("cost"); 
				username = rs.getString("username"); 
				
				SendSMSEverydayList.add(new SendSMSEveryDayForm(forwhat, custid, message, sending, unit, cost, username));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return SendSMSEverydayList;
	 }
	public String GetTime() 
	throws Exception { //14-07-2015
		String time = "";
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT time " +
			"FROM sendsms_time "; 
			
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				time = rs.getString("time");
			}
			time = time.substring(0,5);
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return time;
	 }
	public void DeleteEveryDay(String custID)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "delete from sms_everyday where custid in "+custID+" ";

		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void UpdateTime(String time)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "update sendsms_time set time = '"+time+"' ";

		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
}



