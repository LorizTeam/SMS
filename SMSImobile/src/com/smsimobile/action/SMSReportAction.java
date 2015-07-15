package com.smsimobile.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smsimobile.form.ReportForm;
import com.smsimobile.util.DateUtil;

public class SMSReportAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forwardText = null; 
		DateUtil dateUtil = new DateUtil();
		ReportForm reportForm = (ReportForm) form;
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		
		String fromDate = reportForm.getFromDate();
		String toDate = reportForm.getToDate();
		String print = reportForm.getPrint();
		
		if(!fromDate.equals("")&&!toDate.equals("")){
			fromDate = dateUtil.CnvToYYYYMMDD(fromDate, '-')+" 00:00:00";
			toDate = dateUtil.CnvToYYYYMMDD(toDate, '-')+" 23:59:59";
			request.setAttribute("fromDate", fromDate);
			request.setAttribute("toDate", toDate);
			request.setAttribute("userName", userName);
			
			forwardText = "print";
		}else if(!fromDate.equals("")&&toDate.equals("")){
			fromDate = dateUtil.CnvToYYYYMMDD(fromDate, '-')+" 00:00:00";
			toDate = dateUtil.CnvToYYYYMMDD(fromDate, '-')+" 23:59:59";
			
			request.setAttribute("fromDate", fromDate);
			request.setAttribute("toDate", toDate);
			request.setAttribute("userName", userName);
			
			forwardText = "print";
		}else if(fromDate.equals("")){
			forwardText = "success";	
		}
		
	   return mapping.findForward(forwardText);
	}
}