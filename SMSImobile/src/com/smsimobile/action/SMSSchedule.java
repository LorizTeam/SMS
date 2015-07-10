package com.smsimobile.action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	public void sendSMS(HttpServletRequest request) throws Exception {
		
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
        HttpSession session1 = request.getSession();
        
        String userName = session1.getAttribute("userName").toString();
        String dateTime = dateUtil.CnvToYYYYMMDD(dateUtil.curDate(), '-');
        SendSMSDB sendSMSDB = new SendSMSDB();
        List scheduleList = sendSMSDB.findScheduleSMS(dateTime, userName);
        
        String custID = null;
		String message = null;
		String sending = null;
		int unit = 0;
		double cost = 0;
        
		RegisteredDelivery registeredDelivery = new RegisteredDelivery();
		registeredDelivery.setSMSCDeliveryReceipt(SMSCDeliveryReceipt.DEFAULT);
		
        for(int i=0; i<scheduleList.size(); i++) {
        	SendSMSForm sendSMSForm = (SendSMSForm) scheduleList.get(i);
        	custID 	= sendSMSForm.getCustID();
        	message = sendSMSForm.getDescription();
        	sending = sendSMSForm.getSendName();
        	unit 	= sendSMSForm.getUnit();
        	cost	= sendSMSForm.getCost();
        	
        	DataCoding dataCoding = null;
			byte[] data = null;
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
            					NumberingPlanIndicator.UNKNOWN, sending,
            					TypeOfNumber.INTERNATIONAL,
            					NumberingPlanIndicator.UNKNOWN, custID,
            					new ESMClass(), (byte) 0, (byte) 1,
            					null, null,
            					registeredDelivery, (byte) 0, dataCoding,
            					(byte) 0, data);
    	    			
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
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
        date2pm.setSeconds(00);
        
       timer.schedule(new TimerTask() {
            @Override
            public void run() {
               ex.sendSMS();
            }
        }, date2pm, 60000);  
		;
		
	}   
	public void contextDestroyed(ServletContextEvent sce) {
		 
		 
	}
	
	private void sendSMS(){
		
		
	}
 
}

