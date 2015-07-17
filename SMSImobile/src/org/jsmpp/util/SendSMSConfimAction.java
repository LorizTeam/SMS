/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
 
package org.jsmpp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.camel.component.smpp.SmppProducer;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.PDUStringException;
import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.Command;
import org.jsmpp.bean.DataCoding;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.MessageClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.OptionalParameter;
import org.jsmpp.bean.OptionalParameters;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.SubmitSm;
import org.jsmpp.bean.SubmitSmResp;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
 
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.util.StringParameter;
import org.jsmpp.util.StringValidator;

import com.smsimobile.data.SMSTemplateDB;
import com.smsimobile.data.SendSMSDB;
import com.smsimobile.data.TBLCustomer;
import com.smsimobile.form.SendSMSForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendSMSConfimAction extends Action {
	
	private static final transient Logger LOG = LoggerFactory.getLogger(SmppProducer.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws PDUException,
            ResponseTimeoutException, InvalidResponseException,
            NegativeResponseException, IOException {

		SendSMSForm sendSMSForm = (SendSMSForm) form;
		String confim 	= sendSMSForm.getConfim();
		String edit		= sendSMSForm.getEdit();
		
		if(confim!=null){
		HttpSession session = request.getSession();
		List recipientList = new ArrayList();
		String userName = session.getAttribute("userName").toString();
		String sender = session.getAttribute("sender").toString();
		String message = session.getAttribute("message").toString();
		String sendDate = session.getAttribute("sendDate").toString();
		int unit = Integer.parseInt(session.getAttribute("unit").toString());
		double cost = Double.parseDouble(session.getAttribute("cost").toString());
		
		String serviceName = "SMS Service";
		String alertMessage = null;
		System.out.println("----------------------- SEND SMS -----------------------");
		
		String systemHost = "127.0.0.1"; //127.0.0.1
		int systemPort = 10002;//your PORT 10002
		String systemId = "quppee"; //quppee
		String systemPass = "quppee"; //quppee
		SMPPSession session1 = new SMPPSession();
		try {
			session1.connectAndBind(systemHost, systemPort, new BindParameter(BindType.BIND_TX, systemId, systemPass, null, 
            		TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null));
        } catch (IOException e) {
            System.err.println("Failed connect and bind to host");
            e.printStackTrace();
            System.exit(-1);
        }
        
        System.out.println("connected");   
		
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
		SendSMSDB sendSMSDB = new SendSMSDB();
		
		recipientList = (List) session.getAttribute("recipientList");
		List recipientStatusList = new ArrayList();
		
		for(int i=0; i<recipientList.size(); i++) {
            SendSMSForm smsForm = (SendSMSForm) recipientList.get(i);
            String status = null;
            String recipient = smsForm.getCustID();
            try {
            	//----- SEND SMS -----
 
            	//	sendFinal.sendSMS(message, sender, recipient);
            	
            		try {
            
                   /* 	String messageId = session1.submitShortMessage(
                    			null,
            					TypeOfNumber.NATIONAL,
            					NumberingPlanIndicator.ISDN, sender,
            					TypeOfNumber.INTERNATIONAL,
            					NumberingPlanIndicator.ISDN, recipient,
            					new ESMClass(), (byte) 0, (byte) 1,
            					period, null,
            					registeredDelivery.setSMSCDeliveryReceipt(SMSCDeliveryReceipt.SUCCESS_FAILURE), (byte) 0, dataCoding,
            					(byte) 0, data);  */
                    	SubmitSm requestSM = new SubmitSm();
                    	requestSM.setServiceType(systemHost);
                    	requestSM.setSourceAddr(sender);
                    	requestSM.setDestAddress(recipient);
                    	requestSM.setShortMessage(data);
                    	
                    	String response1 = session1.submitShortMessage(null, TypeOfNumber.NATIONAL, 
                    			NumberingPlanIndicator.ISDN, 
                    			requestSM.getSourceAddr(), 
                    			TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.ISDN, requestSM.getDestAddress(), 
                    			new ESMClass(), (byte) 0, (byte) 1, 
                    			null, null, registeredDelivery.setSMSCDeliveryReceipt(SMSCDeliveryReceipt.SUCCESS_FAILURE),
                    			(byte) 0, dataCoding, 
                    			(byte) 0, requestSM.getShortMessage());
                    	 
                   // 	SubmitSmResp resp = (requestSM);
                   // 	submitSm(data);
                    	
                    	LOG.debug("Service '"+ requestSM.getServiceType() + "'...");
                    	LOG.debug("sourceAddr '"+ requestSM.getSourceAddr() + "'...");
                    	LOG.debug("destAddr '"+ requestSM.getDestAddress() + "'...");
                    	LOG.debug("message '"+ requestSM.getShortMessage() + "'...");
                    	
                        System.out.println("Message submitted, message_id is " + response1);
                        
                    //    sendSMSDB.AddSMS(recipient, message, sender, sendDate, unit, cost, userName);
           //  Request Status ///
                        
                   
           // /////            
                        
                    } catch (PDUException e) {
                        // Invalid PDU parameter
                        System.err.println("Invalid PDU parameter");
                        alertMessage = "Invalid PDU parameter";
                        e.printStackTrace();
                    } catch (ResponseTimeoutException e) {
                        // Response timeout
                        System.err.println("Response timeout");
                        alertMessage = "Response timeout";
                        e.printStackTrace();
                    } catch (InvalidResponseException e) {
                        // Invalid response
                        System.err.println("Receive invalid respose");
                        alertMessage = "Receive invalid respose";
                        e.printStackTrace();
                    } catch (NegativeResponseException e) {
                        // Receiving negative response (non-zero command_status)
                        System.err.println("Receive negative response");
                        alertMessage = "Receive negative response";
                        e.printStackTrace();
                    } catch (IOException e) {
                        System.err.println("IO error occur");
                        alertMessage = "IO error occur";
                        e.printStackTrace();
                    }
            		
			} catch (Exception e) {
				e.printStackTrace();
			}
            
		}
		TBLCustomer tblcustomer = new TBLCustomer();
		SMSTemplateDB smstemplateDB = new SMSTemplateDB();
		List customerList; List smsTemplateList;
		try {
			customerList = tblcustomer.GetCustomerList("", "");
			request.setAttribute("customerList", customerList);
			smsTemplateList= smstemplateDB.GetSMSTemplateList("");
			request.setAttribute("smsTemplateList", smsTemplateList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session1.unbindAndClose();
		
		request.setAttribute("alertMessage", alertMessage);
		}
		if(edit!=null){
			HttpSession session = request.getSession();
			String recipient = session.getAttribute("recipient").toString();
			String sender = session.getAttribute("sender").toString();
			String message = session.getAttribute("message").toString();
			String unit = session.getAttribute("unit").toString();
			String cost = session.getAttribute("cost").toString();
			cost = cost.replace(".0", "");
			
			request.setAttribute("recipient", recipient);
			request.setAttribute("sender", sender);
			request.setAttribute("message", message);
			request.setAttribute("unit", unit);
			request.setAttribute("cost", cost);
			
			TBLCustomer tblcustomer = new TBLCustomer();
			SMSTemplateDB smstemplateDB = new SMSTemplateDB();
			List customerList; List smsTemplateList;
			try {
				customerList = tblcustomer.GetCustomerList("", "");
				request.setAttribute("customerList", customerList);
				smsTemplateList= smstemplateDB.GetSMSTemplateList("");
				request.setAttribute("smsTemplateList", smsTemplateList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return mapping.findForward("success");
	}

	public SubmitSm submitSm(byte[] b) throws PDUStringException {
        SubmitSm req = new SubmitSm();
        SequentialBytesReader reader = new SequentialBytesReader(b);
       
        assignHeader(req, reader);
        req.setServiceType(reader.readCString());
        StringValidator.validateString(req.getServiceType(),
                StringParameter.SERVICE_TYPE);

        req.setSourceAddrTon(reader.readByte());
        req.setSourceAddrNpi(reader.readByte());
        req.setSourceAddr(reader.readCString());
        StringValidator.validateString(req.getSourceAddr(),
                StringParameter.SOURCE_ADDR);

        req.setDestAddrTon(reader.readByte());
     //   req.setDestAddrNpi(reader.readByte());
     //   req.setDestAddress(reader.readCString());
        StringValidator.validateString(req.getDestAddress(),
                StringParameter.DESTINATION_ADDR);

     //   req.setEsmClass(reader.readByte());
     //   req.setProtocolId(reader.readByte());
     //   req.setPriorityFlag(reader.readByte());
    //    req.setScheduleDeliveryTime(reader.readCString());
    //    StringValidator.validateString(req.getScheduleDeliveryTime(),
    //            StringParameter.SCHEDULE_DELIVERY_TIME);
      //  req.setValidityPeriod(reader.readCString());
    //    StringValidator.validateString(req.getValidityPeriod(),
     //           StringParameter.VALIDITY_PERIOD);
     //   req.setRegisteredDelivery(reader.readByte());
     //   req.setReplaceIfPresent(reader.readByte());
     //   req.setDataCoding(reader.readByte());
    //    req.setSmDefaultMsgId(reader.readByte());
     //   byte smLength = reader.readByte();
        // req.setShortMessage(reader.readString(req.getSmLength()));
    //    req.setShortMessage(reader.readBytes(smLength));
        StringValidator.validateString(req.getShortMessage(),
                StringParameter.SHORT_MESSAGE);
//       req.setOptionalParameters(readOptionalParameters(reader));
        return req;
    }
	private OptionalParameter[] readOptionalParameters(
            SequentialBytesReader reader) {
        if (!reader.hasMoreBytes())
            return null;
        List<OptionalParameter> params = new ArrayList<OptionalParameter>();
        while (reader.hasMoreBytes()) {
            short tag = reader.readShort();
            short length = reader.readShort();
            byte[] content = reader.readBytes(length);
            params.add(OptionalParameters.deserialize(tag, content));
        }
        return params.toArray(new OptionalParameter[params.size()]);
    }

    private static void assignHeader(Command pdu,
            SequentialBytesReader seqBytesReader) {
        int commandLength = seqBytesReader.readInt();
       
        pdu.setCommandLength(commandLength);
        pdu.setCommandId(seqBytesReader.readInt());
        pdu.setCommandStatus(seqBytesReader.readInt());
        pdu.setSequenceNumber(seqBytesReader.readInt());
    }

    private static void assignHeader(Command pdu, byte[] bytes) {
        assignHeader(pdu, new SequentialBytesReader(bytes));
    }

}