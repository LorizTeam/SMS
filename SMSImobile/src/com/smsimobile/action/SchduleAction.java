package com.smsimobile.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smsimobile.data.SchduleDB;

public class SchduleAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forwardText = null; 
		
		String[] chk1 = request.getParameterValues("chk1");
		
		String phonenumber = "";
		for(int i = 0; i<chk1.length; i++){
			phonenumber += "'"+chk1[i]+"',";
		}
		phonenumber = "("+phonenumber+")";
		phonenumber = phonenumber.replace(",)", ")");
		
		SchduleDB schdule = new SchduleDB();
		
		schdule.DeleteSchedule(phonenumber);
		
		List scheDuleList = schdule.GetScheduleList("");
		request.setAttribute("scheDuleList", scheDuleList);
		
		forwardText = "success";	
	
	   return mapping.findForward(forwardText);
	}
}