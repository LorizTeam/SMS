package com.smsimobile.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smsimobile.util.DBConnect;
import com.smsimobile.form.MasterGroupForm;


public class MasterGroupDB {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	ResultSet rs		= null;
	
	public List GetMasterGrpList() 
	throws Exception { //30-05-2014
		List masterGrpList = new ArrayList();
		String groupID = "", groupName = "";
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT groupid, groupname " +
			"FROM master_group " +
			"WHERE "; 
			if(!groupID.equals("")) sqlStmt = sqlStmt+ "groupid like '"+groupID+"%' AND ";
			
			sqlStmt = sqlStmt + "groupid <> '' group by groupid order by groupid ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				if (rs.getString("groupid") != null) groupID = rs.getString("groupid"); else groupID = "";
				if (rs.getString("groupname") != null) groupName = rs.getString("groupname"); else groupName = "";
				 
				masterGrpList.add(new MasterGroupForm(groupID, groupName));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return masterGrpList;
	 }
	public void AddCustomer(String custID, String custName, String custType, String userName)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "INSERT IGNORE INTO sms_phonbook(phone_a, name, custtype, phone_b) " +
		"VALUES ('"+custID+"', '"+custName+"', '"+custType+"', '"+userName+"')";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
	public void UpdateCustomer(String custID, String custName, String custType, String userName)  throws Exception{
		conn = agent.getConnectMYSql();
		
		String sqlStmt = "UPDATE sms_phonbook set name = '"+custName+"', custtype = '"+custType+"' " +
		"WHERE phone_b = '"+userName+"' and phone_a = '"+custID+"'";
		//System.out.println(sqlStmt);
		pStmt = conn.createStatement();
		pStmt.executeUpdate(sqlStmt);
		pStmt.close();
		conn.close();
	}
}
