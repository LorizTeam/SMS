/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smsimobile.form;

import org.apache.struts.action.ActionForm;

public class SendSMSForm extends ActionForm {
	
	private String custID;
	private String hdCustID;
	private String descriotion;
	private String hdDescriotion;
	private String sendName;
	private String work;
	private String baht;
	
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getHdCustID() {
		return hdCustID;
	}
	public void setHdCustID(String hdCustID) {
		this.hdCustID = hdCustID;
	}
	public String getDescriotion() {
		return descriotion;
	}
	public void setDescriotion(String descriotion) {
		this.descriotion = descriotion;
	}
	public String getHdDescriotion() {
		return hdDescriotion;
	}
	public void setHdDescriotion(String hdDescriotion) {
		this.hdDescriotion = hdDescriotion;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getBaht() {
		return baht;
	}
	public void setBaht(String baht) {
		this.baht = baht;
	}

} 