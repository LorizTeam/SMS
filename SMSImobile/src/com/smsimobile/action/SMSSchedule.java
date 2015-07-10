package com.smsimobile.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
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

import com.smsimobile.data.SendSMSDB;
import com.smsimobile.form.SendSMSForm;
import com.smsimobile.util.DateUtil;
 

public class SMSSchedule implements javax.servlet.ServletContextListener {
	
	public void sendSMS() throws Exception {
		
        DateUtil dateUtil = new DateUtil();
        
        String date = dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-');
        String time = dateUtil.curTime();
        String dateTime = date+" "+time;
        
        SendSMSDB sendSMSDB = new SendSMSDB();
        List scheduleList = sendSMSDB.findScheduleSMS(dateTime);
        
        if(scheduleList.size() > 0){
        
        String custID = null;
 		String message = null;
 		String sending = null;
 		int unit = 0;
 		double cost = 0;
        
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
        
        RegisteredDelivery registeredDelivery = new RegisteredDelivery();
 		registeredDelivery.setSMSCDeliveryReceipt(SMSCDeliveryReceipt.DEFAULT);
        
        for(int i=0; i<scheduleList.size(); i++) {
        	
        	SendSMSForm sendSMSForm = (SendSMSForm) scheduleList.get(i);
        	custID 	= sendSMSForm.getCustID();
        	message = sendSMSForm.getDescription().toString();
        	sending = sendSMSForm.getSendName().toString();
        	unit 	= sendSMSForm.getUnit();
        	cost	= sendSMSForm.getCost();
         	
         	DataCoding dataCoding = null;
 			byte[] data = null;
 			String period = null;
 			try {
 				dataCoding = new GeneralDataCoding(false,
 						true, MessageClass.CLASS1, Alphabet.ALPHA_UCS2);
 					data = message.getBytes("UTF-16BE");
 			} catch (UnsupportedEncodingException e) {
 				e.printStackTrace();
 			}
        	
			  try {
    				try {					
    		
    					String messageId = session.submitShortMessage(
            					null,
            					TypeOfNumber.ALPHANUMERIC,
            					NumberingPlanIndicator.UNKNOWN, sending,
            					TypeOfNumber.INTERNATIONAL,
            					NumberingPlanIndicator.UNKNOWN, custID,
            					new ESMClass(), (byte) 0, (byte) 1,
            					period, null,
            					registeredDelivery, (byte) 0, dataCoding,
            					(byte) 0, data);
    					// Request response
    			
    	    			//
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
			  } catch (Exception e) {
					e.printStackTrace();
				} 	         
	}
        session.unbindAndClose();
   }
}	 
	 public void doTask() {
	        System.out.println("Hello - " + new Date());
	 }
	 
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext ctx = sce.getServletContext();
		final SMSSchedule ex = new SMSSchedule();
        Timer timer = new Timer();
      	
        Date date2pm = new java.util.Date();
      //  date2pm.setHours(15);
      //  date2pm.setMinutes(51);
      //  date2pm.setSeconds(00);
        
       timer.schedule(new TimerTask() {
            
            public void run() {
              
				try {
					ex.sendSMS();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
            }
        }, 0, 60000);  
		;
		
	}   
	public void contextDestroyed(ServletContextEvent sce) {
		 
		 
	}
}

