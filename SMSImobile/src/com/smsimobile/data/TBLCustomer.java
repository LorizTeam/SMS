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
	
		try {
		
			conn = agent.getConnectMYSql();
			
			String sqlStmt = "SELECT name, phone_a, phone_b " +
			"FROM sms_phonbook " +
			"WHERE "; 
			if(!custID.equals("")) sqlStmt = sqlStmt+ "phone_a like '"+custID+"%' AND ";
			
			sqlStmt = sqlStmt + "phone_a <> '' group by phone_a order by phone_a ";
			
			//System.out.println(sqlStmt);				
			pStmt = conn.createStatement();
			rs = pStmt.executeQuery(sqlStmt);	
			while (rs.next()) {
				if (rs.getString("name") != null) custName = rs.getString("name"); else custName = "";
				if (rs.getString("phone_a") != null) custID = rs.getString("phone_a"); else custID = "";
				
				customerList.add(new CustomerForm(custID, custName));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
		    throw new Exception(e.getMessage());
		}
		return customerList;
	 }
}


