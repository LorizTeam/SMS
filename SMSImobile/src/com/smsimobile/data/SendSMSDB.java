package com.smsimobile.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smsimobile.util.DBConnect;
import com.smsimobile.form.SMSTemplateForm;
import com.smsimobile.form.SendSMSForm;


public class SendSMSDB {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	Statement pStmt1 	= null;
	ResultSet rs		= null;
	
	public List findScheduleSMS(String sendDate) throws Exception {
		List scheduleList = new ArrayList();
		
		PreparedStatement pStmst = null;
		ResultSet rs = null;
		
		//Find the minute
		String sqlStmt = "SELECT id, username, recipient, cost, service_name, send_date,  message " +
				         "FROM schedule_sending " +
				         "WHERE LEFT(send_date,16) = LEFT(?, 16) " +
				         "AND sended = 0 ";
		
		try {
			
			pStmst = conn.prepareStatement(sqlStmt);
			pStmst.setString(1, sendDate);
			rs = pStmst.executeQuery();
			
			while (rs.next()) {
				SendSMSForm sendSMSForm = new SendSMSForm();
				schedulForm.setScheduleId(rs.getInt("id"));
				schedulForm.setUsername(rs.getString("username").trim());
				schedulForm.setRecipient(rs.getString("recipient").trim());
				schedulForm.setCost(rs.getDouble("cost"));
				schedulForm.setServiceName(rs.getString("service_name").trim());
				schedulForm.setSendDate(rs.getTimestamp("send_date"));
				System.out.println("Send date : " + rs.getTimestamp("send_date"));
				schedulForm.setMessage(rs.getString("message").trim());
				scheduleList.add(schedulForm);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
		return scheduleList;
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
	
}