/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smsimobile.form;
import org.apache.struts.action.ActionForm;

public class SendSMSEveryDayForm extends ActionForm {
	
	private String custid;
	private String message;
	private String sending;
	private String unit;
	private String cost;
	private String username;
	private String time;
	private String save;
	
	public SendSMSEveryDayForm (){}
	public SendSMSEveryDayForm(String forwhat, String custid2, String message2, String sending2, String unit2, String cost2, String username2){
		super();
		if(forwhat == "hot")
		{
			this.custid 	= custid2;
			this.message 	= message2;
			this.sending 	= sending2;
			this.unit 	= unit2;
			this.cost 	= cost2;
			this.username 	= username2;
		}
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSending() {
		return sending;
	}
	public void setSending(String sending) {
		this.sending = sending;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSave() {
		return save;
	}
	public void setSave(String save) {
		this.save = save;
	}

} 