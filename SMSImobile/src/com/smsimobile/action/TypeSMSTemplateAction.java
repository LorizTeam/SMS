package com.smsimobile.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smsimobile.data.TypeSMSTemplateDB;
import com.smsimobile.form.TypeSMSTemplateForm;

public class TypeSMSTemplateAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forwardText = null; 
	 
		TypeSMSTemplateForm typeSMSTemplateForm = (TypeSMSTemplateForm) form;
		String groupID = typeSMSTemplateForm.getGroupID();
		String groupName = new String(typeSMSTemplateForm.getGroupName().getBytes("ISO8859-1"), "UTF-8");
		String save = typeSMSTemplateForm.getSave();
		String update = typeSMSTemplateForm.getUpdate();
		String delete = typeSMSTemplateForm.getDelete();
		
		TypeSMSTemplateDB typeSMS = new TypeSMSTemplateDB();
		
		if(save!=null){
			typeSMS.SaveGroup(groupName);
		}
		if(update!=null){
			typeSMS.UpdateGroup(groupID, groupName);
		}
		if(delete!=null){
			if(request.getParameterValues("chk1")!=null){
			String[] chk1 = request.getParameterValues("chk1");
			
			String grpID = "";
			for(int i = 0; i<chk1.length; i++){
				grpID += "'"+chk1[i]+"',";
			}
			grpID = "("+grpID+")";
			grpID = grpID.replace(",)", ")");
			
			typeSMS.DeleteGroup(grpID);
			}
		}
		List TypeSMSList = typeSMS.GetTpyeSMSList();
		request.setAttribute("TypeSMSList", TypeSMSList);
		
		forwardText = "success";	
	
	   return mapping.findForward(forwardText);
	}
}