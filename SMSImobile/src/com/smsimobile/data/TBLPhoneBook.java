package com.smsimobile.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import com.smsimobile.util.DBConnect;
import com.smsimobile.form.PhonBookForm;


public class TBLPhoneBook {
	public void entry_phonebook(String name,String phone,String userPhone) throws IOException, Exception{
		
		String sql = null;
		PreparedStatement pstmt  = null;
		Connection connDB=null;
		//callDate GetDateTime = new callDate();
//		String create_date = GetDateTime.currTimeStamp24hr();//���¡ calass call_date
		
	try {
		 	DBConnect agent = new DBConnect();
		 	connDB = agent.getConnectMYSql();	
		 
			 sql = "insert into sms_phonbook (name,phone_a,phone_b) " +
		 	 		"VALUES (?,?,?) ";

		
		 pstmt = connDB.prepareStatement(sql);
		 pstmt.setString(1, name);
	     pstmt.setString(2, phone);
	     pstmt.setString(3, userPhone);
	    
	     pstmt.executeUpdate();
		}

	   catch (SQLException sqle) {
		   sqle.printStackTrace();
		  // System.out.println(sqle.getMessage() );
	       throw new SQLException(sqle.getMessage());
	  
	}
	   finally {
			
			 
			 try{
					if(pstmt!=null){
						pstmt.close();
											   }
					}catch (Exception e) {
					try {
						throw new Exception(e.getMessage());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					try{
						if(connDB!=null){
							connDB.close();
												   }
						}catch (Exception e) {
						try {
							throw new Exception(e.getMessage());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
						
		   }
	   
	   
	}
	public boolean checkphoneRow(String Aphone )throws IOException, Exception{
	 
		String PhoneNum = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		boolean chk = false;
		try{
			
				DBConnect agent = new DBConnect();
			    conn = agent.getConnectMYSql();	
			   
			   String sqlInsert = "select name, phone_b  "+
								  "from sms_phonbook " +
								  "where phone_a = '"+Aphone+"' " +
								  "group by name,phone_a  " ;
				                 // "group by name " ;

			pstmt = conn.prepareStatement(sqlInsert);
			rs=pstmt.executeQuery();
			while (rs.next()){
			//	PhoneNum = rs.getString("phone_a");
				chk = true;
			 	 
				}
		}catch ( Exception e ) {
			System.err.println("SQL Exception occured while accessing the table" );
	        e.printStackTrace();}
		 
		return chk;
	}
	public String checkphone(String Aphone )throws IOException, Exception{
//		--------------------//
		Locale.setDefault(new Locale("en", "US"));
		//--------------------//
		
		
		String PhoneNum = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try{
			
				DBConnect agent = new DBConnect();
			    conn = agent.getConnectMYSql();	
			   
			   String sqlInsert = "select name,phone_a  "+
								  "from sms_phonbook " +
								  "where phone_a = '"+Aphone+"' " +
								  "group by name,phone_a  " ;
				                 // "group by name " ;
			
								
			
			pstmt = conn.prepareStatement(sqlInsert);
			rs=pstmt.executeQuery();
			while (rs.next()){
				PhoneNum = rs.getString("phone_a");
				
			 	 
				}
				
			
			
		}catch ( Exception e ) {
			System.err.println("SQL Exception occured while accessing the table" );
	        e.printStackTrace();}
		 
		return PhoneNum;
	}
	
	public List phonebookList(String phone_a )throws IOException, Exception{
//		--------------------//
		Locale.setDefault(new Locale("en", "US"));
		//--------------------//
		
		List phonebookList = new ArrayList();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try{
			
				DBConnect agent = new DBConnect();
			    conn = agent.getConnectMYSql();	
			   
			   String sqlInsert = "select name, phone_a, phone_b   "+
								  "from sms_phonbook " +
								  "where " ;
				if(!phone_a.equals("")) sqlInsert = sqlInsert + "phone_b = '" +phone_a+"' AND ";       
					sqlInsert = sqlInsert + "phone_b <> '' ";
			   // "group by name " ;
			
								
			
			pstmt = conn.prepareStatement(sqlInsert);
			rs=pstmt.executeQuery();
			while (rs.next()){
				
						
				PhonBookForm phonebookForm = new PhonBookForm();
				phonebookForm.setId(rs.getString("name"));
				phonebookForm.setPhone(rs.getString("phone_b"));
				phonebookForm.setName(rs.getString("phone_a"));
			//	phonebookForm.setPhone(rs.getString("phone_b"));
				phonebookList.add(phonebookForm);
			 	 
				}
				
			
			
		}catch ( Exception e ) {
			System.err.println("SQL Exception occured while accessing the table" );
	        e.printStackTrace();}
		 
		return phonebookList;
	}
public void Editphone_book(String name,String phone_b,String id) throws IOException, Exception{
		
		String sql = null;
		PreparedStatement pstmt  = null;
		Connection connDB=null;
		//callDate GetDateTime = new callDate();
//		String create_date = GetDateTime.currTimeStamp24hr();//���¡ calass call_date
		
	try {
		 	DBConnect agent = new DBConnect();
		 	connDB = agent.getConnectMYSql();	
		 
			 sql = "update sms_phonbook " +
			 		"set name = '" +name+"',phone_b = '" +phone_b+"' " +
			 	//	"  " +
		 	 		"where id = '" +id+"' " ;

		
		 pstmt = connDB.prepareStatement(sql);
		// pstmt.setString(1, name);
	  //   pstmt.setString(2, Aphone);
	  //   pstmt.setString(3, phone);
	    
	     pstmt.executeUpdate();
		}

	   catch (SQLException sqle) {
		   sqle.printStackTrace();
		  // System.out.println(sqle.getMessage() );
	       throw new SQLException(sqle.getMessage());
	  
	}
	   finally {
			
			 
			 try{
					if(pstmt!=null){
						pstmt.close();
											   }
					}catch (Exception e) {
					try {
						throw new Exception(e.getMessage());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					try{
						if(connDB!=null){
							connDB.close();
												   }
						}catch (Exception e) {
						try {
							throw new Exception(e.getMessage());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
						
		   }
	   
	   
	}
public void DeleteMobile(String id)throws IOException, Exception{

//	--------------------//
	Locale.setDefault(new Locale("en", "US"));
	//--------------------//
	
	
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;
	
	
	try{
		
		DBConnect agent = new DBConnect();
		conn = agent.getConnectMYSql();	
		
		 String sqlInsert = "delete from sms_phonbook  "+
		 					"where id = '"+id+"' " ;
		pstmt = conn.prepareStatement(sqlInsert);
		pstmt.executeUpdate();
	
	
	}catch ( Exception e ) {
	System.err.println("SQL Exception occured while accessing the table" );
    e.printStackTrace();
    }
}
}



