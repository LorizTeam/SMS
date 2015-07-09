package com.smsimobile.util;

public class VerifySMS {
	
	public boolean isThaiMsg(String message) {
		//Check message [TH] or [EN]
		boolean isThaiMsg = false;
		int msgLen = message.length();
		
		for (int i=0;i<msgLen;i++){
			char aChar = message.substring(i,i+1).charAt(0);
			//System.out.println("MSG : " + message.substring(i,i+1).charAt(0));
			if ( (aChar >= 'ก') && (aChar <='ฮ') ) {
				//thai
				isThaiMsg = true;
				break;
			}
		}
		//System.out.println("isThaiMsg:"+isThaiMsg);
		return isThaiMsg;
	}
	public boolean isMobileNo(String mobileNo) {
		boolean result = false;
		if(mobileNo != "") {
			if(mobileNo.length() == 10){
				if(mobileNo.substring(0, 2).equals("08")||mobileNo.substring(0, 2).equals("09")) {
					for(int i=0; i<mobileNo.length(); i++) {
						char tmp = mobileNo.charAt(i);
						if (tmp > 31 && (tmp < 48 || tmp > 57)) {
							return false;
						} else {
							return true;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	public boolean isInterMobileNo(String mobileNo) {
		boolean result = false;
		if(mobileNo != "") {
			for(int i=0; i<mobileNo.length(); i++) {
				char tmp = mobileNo.charAt(i);
				if (tmp > 31 && (tmp < 48 || tmp > 57)) {
					return false;
				} else {
					return true;
				}
			}
		}
		
		return result;
	}
	
	public int findUnitByMessage(String message) {
	    int msgLen = message.length();
		int countMsgLen1 = 0;
		int countMsgLen2 = 0;
		int countMsgLen3 = 0;
	    int unit = 0;
	    
	    VerifySMS verifySMS = new VerifySMS();
	    
	    if(verifySMS.isThaiMsg(message)) {
	    	if(msgLen > 70) {
	    		countMsgLen2 = 134;
	    		countMsgLen3 = 201;
	    	} else {
	    		countMsgLen1 = 70;
	    	}
	    } else {
	    	if(msgLen > 160) {
	    		countMsgLen2 = 306;
	    		countMsgLen3 = 459;
	    	} else {
	    		countMsgLen1 = 153;
	    	}	
	    }
	    
	    if(msgLen <= countMsgLen1) {
	    	unit = 1;
		} else if(msgLen <= countMsgLen2) {
			unit = 2;
		} else if(msgLen <= countMsgLen3) {
			unit = 3;
		}
		
		return unit;
	}	
}
