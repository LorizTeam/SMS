package com.smsimobile.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smsimobile.util.DBConnect;
import com.smsimobile.form.CustomerForm;


public class TBLCustomer {
	DBConnect agent 	= new DBConnect();
	Connection conn		= null;
	Statement pStmt 	= null;
	Statement pStmt1 	= null;
	ResultSet rs		= null;
	
	public List GetCustomerList(String custID, String custName) 
	throws Exception { //30-05-2014
		List customerList = new ArrayList();
		String custType = "", typeName = "";
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT name, phone_a, custtype, phone_b, groupname " +
			"FROM sms_phonbook inner join master_group on(groupid = custtype) " +
			"WHERE "; 
			if(!custID.equals("")) sqlStmt = sqlStmt+ "phone_a like '"+custID+"%' AND ";
			
			sqlStmt = sqlStmt + "phone_a <> '' group by phone_a order by phone_a ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				if (rs.getString("name") != null) custName = rs.getString("name"); else custName = "";
				if (rs.getString("phone_a") != null) custID = rs.getString("phone_a"); else custID = "";
				if (rs.getString("custtype") != null) custType = rs.getString("custtype"); else custID = "";
				if (rs.getString("groupname") != null) typeName = rs.getString("groupname"); else typeName = "";
				
				customerList.add(new CustomerForm(custID, custName, custType, typeName));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return customerList;
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
