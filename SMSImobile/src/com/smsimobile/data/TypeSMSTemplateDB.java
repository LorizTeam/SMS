package com.smsimobile.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smsimobile.util.DBConnect;
import com.smsimobile.form.TypeSMSTemplateForm;

public class TypeSMSTemplateDB {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	
	public List GetTpyeSMSList() 
	throws Exception { //30-05-2014
		List TypeSMSList = new ArrayList();
		String groupID = "", groupName = "";
 
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT groupid, groupname " +
			"FROM master_type " +
			"WHERE "; 
	 
			sqlStmt = sqlStmt + "groupid <> '' order by groupid ";
 
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				if (rs.getString("groupid") != null) groupID = rs.getString("groupid"); else groupID = ""; 
				if (rs.getString("groupname") != null) groupName = rs.getString("groupname"); else groupName = "";
				
				TypeSMSList.add(new TypeSMSTemplateForm(groupID, groupName));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return TypeSMSList;
	 }
	public void SaveGroup(String groupName)  throws Exception{
		conn = agent.getConnectMYSql();

		String sqlStmt = "INSERT IGNORE INTO master_type(groupname) " +
		"VALUES ('"+groupName+"')";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void UpdateGroup(String groupID, String groupName)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "update master_type set groupname = '"+groupName+"' " +
						 "where groupid = '"+groupID+"' ";

		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void DeleteGroup(String groupID)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "delete from master_type where groupid in "+groupID+" ";

		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
}



