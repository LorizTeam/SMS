package com.smsimobile.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.smsimobile.util.DBConnect;
import com.smsimobile.form.SMSTemplateForm;
import com.smsimobile.form.SendSMSForm;


public class SendSMSDB {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	
	public List findScheduleSMS(String sendDate) throws Exception {
		List scheduleList = new ArrayList();
		
		conn = agent.getConnectMYSql();
		try {
		//Find the minute
		String sqlStmt = "SELECT custid, message, sending, datetime, unit, cost, username " +
				         "FROM sms_schedule " +
				         "WHERE LEFT(datetime,16) = LEFT('"+sendDate+"', 16) ";
		
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			 
			while (rs.next()) {
				SendSMSForm sendSMSForm = new SendSMSForm();
				sendSMSForm.setCustID(rs.getString("custid").trim());
				sendSMSForm.setDescription(rs.getString("message").trim());
				sendSMSForm.setSendName(rs.getString("sending").trim());
				sendSMSForm.setSendDateTime(rs.getString("datetime").trim());
				sendSMSForm.setUnit(rs.getInt("unit"));
				sendSMSForm.setCost(rs.getDouble("cost"));
				sendSMSForm.setUserName(rs.getString("username"));
				
				scheduleList.add(sendSMSForm);
			}
		rs.close();
		pStmt.close();
		conn.close();
		} catch (SQLException e) {
			System.out.println("----------------------- data is null -----------------------");
		}
		return scheduleList;
	}
	public List findEveryDaySMS() throws Exception {
		List scheduleList = new ArrayList();
		
		conn = agent.getConnectMYSql();
		try {
		//Find the minute
		String sqlStmt = "SELECT custid, message, sending, unit, cost, username " +
				         "FROM sms_everyday " +
				         "WHERE custid <> '' ";
		
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			 
			while (rs.next()) {
				SendSMSForm sendSMSForm = new SendSMSForm();
				sendSMSForm.setCustID(rs.getString("custid").trim());
				sendSMSForm.setDescription(rs.getString("message").trim());
				sendSMSForm.setSendName(rs.getString("sending").trim());
				sendSMSForm.setUnit(rs.getInt("unit"));
				sendSMSForm.setCost(rs.getDouble("cost"));
				 
				scheduleList.add(sendSMSForm);
			}
		rs.close();
		pStmt.close();
		conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return scheduleList;
	}
	public boolean chkTimeSMS(String time) throws Exception {
		boolean chkTime = false;
		
		conn = agent.getConnectMYSql();
		try {
		//Find the minute
		String sqlStmt = "SELECT time " +
				         "FROM sendsms_time " +
				         "WHERE LEFT(time,5) = LEFT('"+time+"',5) ";
		
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			 
			while (rs.next()) {
				chkTime = true;
			}
		rs.close();
		pStmt.close();
		conn.close();
		} catch (SQLException e) {
		System.out.println("----------------------- data is null -----------------------");
		}
		return chkTime;
	}
	public void AddSMS(String custID, String message, String sending, String dateTime, int unit, double cost, String userName)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "INSERT IGNORE INTO sendsms_history(custid, message, sending, datetime, unit, cost, username) " +
		"VALUES ('"+custID+"', '"+message+"', '"+sending+"', '"+dateTime+"', '"+unit+"', '"+cost+"', '"+userName+"')";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void AddSMSSchedule(String custID, String message, String sending, String dateTime, int unit, double cost, String userName)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "INSERT IGNORE INTO sms_schedule(custid, message, sending, datetime, unit, cost, username) " +
		"VALUES ('"+custID+"', '"+message+"', '"+sending+"', '"+dateTime+"', '"+unit+"', '"+cost+"', '"+userName+"')";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void AddSMSEveryDay(String custID, String message, String sending, int unit, double cost, String userName)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "INSERT IGNORE INTO sms_everyday(custid, message, sending, unit, cost, username) " +
		"VALUES ('"+custID+"', '"+message+"', '"+sending+"', '"+unit+"', '"+cost+"', '"+userName+"')";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void UpdateSMSSchedule(String custID, String message, String sending, String dateTime, int unit, double cost, String userName)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "INSERT IGNORE INTO sms_schedule_temp(custid, message, sending, datetime, unit, cost, username) " +
		"VALUES ('"+custID+"', '"+message+"', '"+sending+"', '"+dateTime+"', '"+unit+"', '"+cost+"', '"+userName+"')";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		
		sqlStmt = "delete from sms_schedule where custid = '"+custID+"' and username = '"+userName+"' ";

		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
}