package com.smsimobile.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smsimobile.data.SendSMSEveryDayDB;

public class SendSMSEveryDayStartAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String forwardText = null; 
		
		SendSMSEveryDayDB everyDay = new SendSMSEveryDayDB();
		
		List SendSMSEverydayList = everyDay.GetSendSMSEverydayList("");
		request.setAttribute("SendSMSEverydayList", SendSMSEverydayList);
		
		String time = "";
		time = everyDay.GetTime();
		
		request.setAttribute("time", time);
		
		forwardText = "success";	
	
	   return mapping.findForward(forwardText);
	}
}