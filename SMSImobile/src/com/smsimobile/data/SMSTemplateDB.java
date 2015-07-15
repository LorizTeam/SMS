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
		String type = "", typeName = "";
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT description, type, groupname " +
			"FROM sms_template inner join master_type on(groupid = type) " +
			"WHERE "; 
			if(!description.equals("")) sqlStmt = sqlStmt+ "description like '"+description+"%' AND ";
			
			sqlStmt = sqlStmt + "type <> '' order by type ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				if (rs.getString("description") != null) description = rs.getString("description"); else description = "";
				type = rs.getString("type");
				typeName = rs.getString("groupname");
				
				smsTemplateList.add(new SMSTemplateForm(description, type, typeName));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return smsTemplateList;
	 }
	public void AddTemplate(String description, String type)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "INSERT IGNORE INTO sms_template(description, type) " +
		"VALUES ('"+description+"', '"+type+"')";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void UpdateTemplate(String description, String type, String descriptionHD, String typeHD)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "UPDATE sms_template set description = '"+description+"', type = '"+type+"' " +
		"WHERE description = '"+descriptionHD+"' and type = '"+typeHD+"'";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
}
