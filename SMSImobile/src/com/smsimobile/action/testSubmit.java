package com.smsimobile.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsmpp.bean.GSMSpecificFeature;
import org.jsmpp.bean.MessageType;
import org.jsmpp.bean.SubmitSm;
import org.jsmpp.session.SMPPSession;

import org.jsmpp.bean.DataCoding;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.MessageMode;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.OptionalParameter;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.TypeOfNumber;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.component.smpp.SmppConfiguration;
import org.apache.camel.component.smpp.SmppConstants;
import org.apache.camel.component.smpp.SmppUtils;
 

public class testSubmit {
	
/*	 
	public void execute(Exchange exchange) {
		SMPPSession session = new SMPPSession();
		SmppConfiguration config = new SmppConfiguration();
        SubmitSm[] submitSms = createSubmitSm(exchange);
        List<String> messageIDs = new ArrayList<String>(submitSms.length);
       
        for (int i = 0; i < submitSms.length; i++) {
            SubmitSm submitSm = submitSms[i];
            String messageID;
            String message = "";
            
            try {
                messageID = session.submitShortMessage(
                        submitSm.getServiceType(),
                        TypeOfNumber.valueOf(submitSm.getSourceAddrTon()),
                        NumberingPlanIndicator.valueOf(submitSm.getSourceAddrNpi()),
                        submitSm.getSourceAddr(),
                        TypeOfNumber.valueOf(submitSm.getDestAddrTon()),
                        NumberingPlanIndicator.valueOf(submitSm.getDestAddrNpi()),
                        submitSm.getDestAddress(),
                        new ESMClass(submitSm.getEsmClass()),
                        submitSm.getProtocolId(),
                        submitSm.getPriorityFlag(),
                        submitSm.getScheduleDeliveryTime(),
                        submitSm.getValidityPeriod(),
                        new RegisteredDelivery(submitSm.getRegisteredDelivery()),
                        submitSm.getReplaceIfPresent(),
                        DataCoding.newInstance(submitSm.getDataCoding()),
                        (byte) 0,
                        submitSm.getShortMessage(),
                        submitSm.getOptionalParametes());
            } catch (Exception e) {
               
            }
           
            messageIDs.add(messageID);
        }


        Message message = getResponseMessage(exchange);
        message.setHeader(SmppConstants.ID, messageIDs);
        message.setHeader(SmppConstants.SENT_MESSAGE_COUNT, messageIDs.size());
    }

    protected SubmitSm[] createSubmitSm(Exchange exchange) {
        byte[] shortMessage = getShortMessage(exchange.getIn());

        SubmitSm template = createSubmitSmTemplate(exchange);
       SmppSplitter splitter = createSplitter(exchange.getIn());
        byte[][] segments = splitter.split(shortMessage);

        // multipart message
        if (segments.length > 1) {
            template.setEsmClass(new ESMClass(MessageMode.DEFAULT, MessageType.DEFAULT, GSMSpecificFeature.UDHI).value());
        }

        SubmitSm[] submitSms = new SubmitSm[segments.length];
        for (int i = 0; i < segments.length; i++) {
            SubmitSm submitSm = SmppUtils.copySubmitSm(template);
            submitSm.setShortMessage(segments[i]);
            submitSms[i] = submitSm;
        }

        return submitSms;
    }

    protected SubmitSm createSubmitSmTemplate(Exchange exchange) {
        Message in = exchange.getIn();
        SubmitSm submitSm = new SubmitSm();
        SmppConfiguration config = new SmppConfiguration();
        
        if (in.getHeaders().containsKey(SmppConstants.DATA_CODING)) {
            submitSm.setDataCoding(in.getHeader(SmppConstants.DATA_CODING, Byte.class));
        } else if (in.getHeaders().containsKey(SmppConstants.ALPHABET)) {
            submitSm.setDataCoding(in.getHeader(SmppConstants.ALPHABET, Byte.class));
        } else {
            submitSm.setDataCoding(config.getDataCoding());
        }

        if (in.getHeaders().containsKey(SmppConstants.DEST_ADDR)) {
            submitSm.setDestAddress(in.getHeader(SmppConstants.DEST_ADDR, String.class));
        } else {
            submitSm.setDestAddress(config.getDestAddr());
        }

        if (in.getHeaders().containsKey(SmppConstants.DEST_ADDR_TON)) {
            submitSm.setDestAddrTon(in.getHeader(SmppConstants.DEST_ADDR_TON, Byte.class));
        } else {
            submitSm.setDestAddrTon(config.getDestAddrTon());
        }

        if (in.getHeaders().containsKey(SmppConstants.DEST_ADDR_NPI)) {
            submitSm.setDestAddrNpi(in.getHeader(SmppConstants.DEST_ADDR_NPI, Byte.class));
        } else {
            submitSm.setDestAddrNpi(config.getDestAddrNpi());
        }

        if (in.getHeaders().containsKey(SmppConstants.SOURCE_ADDR)) {
            submitSm.setSourceAddr(in.getHeader(SmppConstants.SOURCE_ADDR, String.class));
        } else {
            submitSm.setSourceAddr(config.getSourceAddr());
        }

        if (in.getHeaders().containsKey(SmppConstants.SOURCE_ADDR_TON)) {
            submitSm.setSourceAddrTon(in.getHeader(SmppConstants.SOURCE_ADDR_TON, Byte.class));
        } else {
            submitSm.setSourceAddrTon(config.getSourceAddrTon());
        }

        if (in.getHeaders().containsKey(SmppConstants.SOURCE_ADDR_NPI)) {
            submitSm.setSourceAddrNpi(in.getHeader(SmppConstants.SOURCE_ADDR_NPI, Byte.class));
        } else {
            submitSm.setSourceAddrNpi(config.getSourceAddrNpi());
        }

        if (in.getHeaders().containsKey(SmppConstants.SERVICE_TYPE)) {
            submitSm.setServiceType(in.getHeader(SmppConstants.SERVICE_TYPE, String.class));
        } else {
            submitSm.setServiceType(config.getServiceType());
        }

        if (in.getHeaders().containsKey(SmppConstants.REGISTERED_DELIVERY)) {
            submitSm.setRegisteredDelivery(in.getHeader(SmppConstants.REGISTERED_DELIVERY, Byte.class));
        } else {
            submitSm.setRegisteredDelivery(config.getRegisteredDelivery());
        }

        if (in.getHeaders().containsKey(SmppConstants.PROTOCOL_ID)) {
            submitSm.setProtocolId(in.getHeader(SmppConstants.PROTOCOL_ID, Byte.class));
        } else {
            submitSm.setProtocolId(config.getProtocolId());
        }

        if (in.getHeaders().containsKey(SmppConstants.PRIORITY_FLAG)) {
            submitSm.setPriorityFlag(in.getHeader(SmppConstants.PRIORITY_FLAG, Byte.class));
        } else {
            submitSm.setPriorityFlag(config.getPriorityFlag());
        }

        if (in.getHeaders().containsKey(SmppConstants.SCHEDULE_DELIVERY_TIME)) {
            submitSm.setScheduleDeliveryTime(SmppUtils.formatTime(in.getHeader(SmppConstants.SCHEDULE_DELIVERY_TIME, Date.class)));
        }

        if (in.getHeaders().containsKey(SmppConstants.VALIDITY_PERIOD)) {
            Object validityPeriod = in.getHeader(SmppConstants.VALIDITY_PERIOD);
            if (validityPeriod instanceof String) {
                submitSm.setValidityPeriod((String) validityPeriod);
            } else if (validityPeriod instanceof Date) {
                submitSm.setValidityPeriod(SmppUtils.formatTime((Date) validityPeriod));
            }
        }

        if (in.getHeaders().containsKey(SmppConstants.REPLACE_IF_PRESENT_FLAG)) {
            submitSm.setReplaceIfPresent(in.getHeader(SmppConstants.REPLACE_IF_PRESENT_FLAG, Byte.class));
        } else {
            submitSm.setReplaceIfPresent(config.getReplaceIfPresentFlag());
        }
       
        submitSm.setEsmClass(new ESMClass().value());
       
        submitSm.setOptionalParametes();

        return submitSm;
    }
	*/
}
