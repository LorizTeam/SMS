package com.smsimobile.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smsimobile.util.DBConnect;
import com.smsimobile.form.SMSTemplateForm;


public class SMSTemplateDB {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	Statement pStmt1 	= null;
	ResultSet rs		= null;
	
	public List GetSMSTemplateList(String description) 
	throws Exception { //30-05-2014
		List smsTemplateList = new ArrayList();
		String type = "";
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT description, type " +
			"FROM sms_template " +
			"WHERE "; 
			if(!description.equals("")) sqlStmt = sqlStmt+ "description like '"+description+"%' AND ";
			
			sqlStmt = sqlStmt + "description <> '' order by description ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				if (rs.getString("description") != null) description = rs.getString("description"); else description = "";
				type = rs.getString("type"); 
				
				smsTemplateList.add(new SMSTemplateForm(description,type));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return smsTemplateList;
	 }
}



