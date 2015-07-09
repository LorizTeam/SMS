package com.smsimobile.util;

public class SeparatedString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] resultArray = new SeparatedString().separatedToStringArray("0814992319, 0850704851, ,,,,,");
		for(int i=0; i<resultArray.length; i++) {
			System.out.println("Mobile No. [" + resultArray[i].trim() + "]");
		}


	}
	
	public String[] separatedToStringArray(String text){
	
		String[] splittArray = null;
		if (text != null || !text.equalsIgnoreCase("")) {
			splittArray = text.split("[\\r\\n,]+");
			
		}
		
		
		//Clear space
		
		int count = 0;
		for(int i=0; i<splittArray.length; i++) {
			if(!splittArray[i].trim().equals("")) {
				count++;
			}
		}
		
		String[] resultArray = new String[count];
		for(int i=0; i<count; i++) {
			if(!splittArray[i].trim().equals("")) {
				resultArray[i] = splittArray[i].trim();
			}
		}
		
		return resultArray;
	}
}
