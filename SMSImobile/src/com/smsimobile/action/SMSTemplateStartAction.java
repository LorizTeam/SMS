package com.smsimobile.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smsimobile.data.TBLCustomer;

public class SMSTemplateStartAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forwardText = null; 
		
		TBLCustomer tblcustomer = new TBLCustomer();
		List customerList = tblcustomer.GetCustomerList("", "");
		request.setAttribute("customerList", customerList);
		
		forwardText = "success";	
	
	   return mapping.findForward(forwardText);
	}
}