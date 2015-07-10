package com.smsimobile.action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.DataCoding;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.MessageClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;

import com.samart.struts.data.DBConnect;
import com.samart.struts.action.LoginAction;
import com.smsimobile.util.DateUtil;
 

public class SMSSchedule implements javax.servlet.ServletContextListener {
	
	public String sendSMS() {
		
		String systemHost = "127.0.0.1";
		int systemPort = 10002;//your PORT
		String systemId = "quppee";
		String systemPass = "quppee";
		SMPPSession session = new SMPPSession();
	 
        try {
            session.connectAndBind(systemHost, systemPort, new BindParameter(BindType.BIND_TX, systemId, systemPass, null, 
            		TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null));
        } catch (IOException e) {
            System.err.println("Failed connect and bind to host");
            e.printStackTrace();
            System.exit(-1);
        }
        
        System.out.println("connected");
		
        DateUtil dateUtil = new DateUtil();
        String dateTime = dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-');
        
        
		RegisteredDelivery registeredDelivery = new RegisteredDelivery();
		registeredDelivery.setSMSCDeliveryReceipt(SMSCDeliveryReceipt.DEFAULT);
		
		DataCoding dataCoding = null;
		byte[] data = null;
		String period = null; // set period to null
		try {
			dataCoding = new GeneralDataCoding(false,
					true, MessageClass.CLASS1, Alphabet.ALPHA_UCS2);
				data = message.getBytes("UTF-16BE");
				 	
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
        try {
        	String messageId = session.submitShortMessage(
					null,
					TypeOfNumber.ALPHANUMERIC,
					NumberingPlanIndicator.UNKNOWN, sender,
					TypeOfNumber.INTERNATIONAL,
					NumberingPlanIndicator.UNKNOWN, ani,
					new ESMClass(), (byte) 0, (byte) 1,
					period, null,
					registeredDelivery, (byte) 0, dataCoding,
					(byte) 0, data);
        		
            System.out.println("Message submitted, message_id is " + messageId);
            
        } catch (PDUException e) {
            // Invalid PDU parameter
            System.err.println("Invalid PDU parameter");
            e.printStackTrace();
        } catch (ResponseTimeoutException e) {
            // Response timeout
            System.err.println("Response timeout");
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            // Invalid response
            System.err.println("Receive invalid respose");
            e.printStackTrace();
        } catch (NegativeResponseException e) {
            // Receiving negative response (non-zero command_status)
            System.err.println("Receive negative response");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO error occur");
            e.printStackTrace();
        }
        
        session.unbindAndClose();
		
		String sendstatus = "";
		return sendstatus;
	}
	 
	 public void doTask() {
	        System.out.println("Hello - " + new Date());
	 }
	 
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext ctx = sce.getServletContext();
		final LoginAction ex = new LoginAction();
        Timer timer = new Timer();
      	
        Date date2pm = new java.util.Date();
        date2pm.setHours(15);
        date2pm.setMinutes(51);
        
       timer.schedule(new TimerTask() {
            @Override
            public void run() {
         //       ex.doTask();
            	PreparedStatement pStmst = null;
        		ResultSet rs = null;
        		Connection conn = null;
				try {					
					conn = new DBConnect().getConnectMYSql();
					String test = "";
	            	String sqlStmt = "SELECT name, phone_a, phone_b " +
			         "FROM sms_phonbook ";
			         
	            	pStmst = conn.prepareStatement(sqlStmt);
	            	rs = pStmst.executeQuery();
	            	int i = 0, j = 1; 
	    
	    			while (rs.next()) {
	    				String[] testAR = new String[j];
	    				testAR[i] =	rs.getString("name");
	    				test = test+","+testAR[i];
	    				i++;j++;
	    			}
	    			rs.close();
	    			pStmst.close();
	    			
	    			System.out.println(test);
	    			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }
        }, 0, 18000000);  
		;
		
	}   
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext ctx = sce.getServletContext(); 
		DBConnect dbConnect = (DBConnect) ctx.getAttribute("DBConnect");
		dbConnect.closeConnection();
    	System.out.println("Database connection closed for Application.");
	}
	// @Override
}

