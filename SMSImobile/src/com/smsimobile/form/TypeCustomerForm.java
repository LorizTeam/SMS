/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smsimobile.form;
import org.apache.struts.action.ActionForm;

public class TypeCustomerForm extends ActionForm {
	
	private String groupID;
	private String groupName;
	
	private String save;
	private String update;
	private String delete;
	
	public TypeCustomerForm (){}
	public TypeCustomerForm(String groupID, String groupName){
		super();
			this.groupID 	= groupID;
			this.groupName 	= groupName;
 
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSave() {
		return save;
	}
	public void setSave(String save) {
		this.save = save;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	 
} 