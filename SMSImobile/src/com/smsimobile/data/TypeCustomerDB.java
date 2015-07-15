package com.smsimobile.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smsimobile.util.DBConnect;
import com.smsimobile.form.TypeCustomerForm;

public class TypeCustomerDB {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	
	public List GetTpyeCustomerList() 
	throws Exception { //30-05-2014
		List TpyeCustomerList = new ArrayList();
		String groupID = "", groupName = "";
 
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT groupid, groupname " +
			"FROM master_group " +
			"WHERE "; 
	 
			sqlStmt = sqlStmt + "groupid <> '' order by groupid ";
 
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				if (rs.getString("groupid") != null) groupID = rs.getString("groupid"); else groupID = ""; 
				if (rs.getString("groupname") != null) groupName = rs.getString("groupname"); else groupName = "";
				
				TpyeCustomerList.add(new TypeCustomerForm(groupID, groupName));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return TpyeCustomerList;
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
	public void SaveGroup(String groupName)  throws Exception{
		conn = agent.getConnectMYSql();

		String sqlStmt = "INSERT IGNORE INTO master_group(groupname) " +
		"VALUES ('"+groupName+"')";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void UpdateGroup(String groupID, String groupName)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "update master_group set groupname = '"+groupName+"' " +
						 "where groupid = '"+groupID+"' ";

		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void DeleteGroup(String groupID)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "delete from master_group where groupid in "+groupID+" ";

		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
}



