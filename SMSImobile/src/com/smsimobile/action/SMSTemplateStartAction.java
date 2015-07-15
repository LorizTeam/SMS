package com.smsimobile.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smsimobile.data.SMSTemplateDB;
import com.smsimobile.data.TypeSMSTemplateDB;

public class SMSTemplateStartAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forwardText = null; 
		
		SMSTemplateDB smstemplate = new SMSTemplateDB();
		List smsTemplateList = smstemplate.GetSMSTemplateList("");
		request.setAttribute("smsTemplateList", smsTemplateList);
		
		TypeSMSTemplateDB typeSMSTemplateDB = new TypeSMSTemplateDB();
		List TypeSMSList = typeSMSTemplateDB.GetTpyeSMSList();
		request.setAttribute("TypeSMSList", TypeSMSList);
		
		forwardText = "success";	
	
	   return mapping.findForward(forwardText);
	}
}