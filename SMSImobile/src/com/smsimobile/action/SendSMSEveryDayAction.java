package com.smsimobile.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smsimobile.data.SendSMSEveryDayDB;
import com.smsimobile.form.SendSMSEveryDayForm;

public class SendSMSEveryDayAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forwardText = null; 
	 
		SendSMSEveryDayForm sendSMSEveryDayForm = (SendSMSEveryDayForm) form;
		String time = sendSMSEveryDayForm.getTime()+":00";
		String save = sendSMSEveryDayForm.getSave();
		
		SendSMSEveryDayDB everyDay = new SendSMSEveryDayDB();
		
		if(save!=null){
			everyDay.UpdateTime(time);
			time = time.substring(0, 5);
			request.setAttribute("time", time);
		}
		
		if(request.getParameterValues("chk1")!=null){
		
		String[] chk1 = request.getParameterValues("chk1");
		
		String phonenumber = "";
		for(int i = 0; i<chk1.length; i++){
			phonenumber += "'"+chk1[i]+"',";
		}
		phonenumber = "("+phonenumber+")";
		phonenumber = phonenumber.replace(",)", ")");
		
		everyDay.DeleteEveryDay(phonenumber);
		}
		
		List SendSMSEverydayList = everyDay.GetSendSMSEverydayList("");
		request.setAttribute("SendSMSEverydayList", SendSMSEverydayList);
		
		forwardText = "success";	
	
	   return mapping.findForward(forwardText);
	}
}