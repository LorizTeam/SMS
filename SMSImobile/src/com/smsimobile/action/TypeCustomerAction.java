package com.smsimobile.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smsimobile.data.TypeCustomerDB;
import com.smsimobile.form.TypeCustomerForm;

public class TypeCustomerAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String forwardText = null; 
	 
		TypeCustomerForm typeCustomerForm = (TypeCustomerForm) form;
		String groupID = typeCustomerForm.getGroupID();
		String groupName = new String(typeCustomerForm.getGroupName().getBytes("ISO8859-1"), "UTF-8");
		String save = typeCustomerForm.getSave();
		String update = typeCustomerForm.getUpdate();
		String delete = typeCustomerForm.getDelete();
		
		TypeCustomerDB typeCustmer = new TypeCustomerDB();
		
		if(save!=null){
			typeCustmer.SaveGroup(groupName);
		}
		if(update!=null){
			typeCustmer.UpdateGroup(groupID, groupName);
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
			
			typeCustmer.DeleteGroup(grpID);
			}
		}
		List TpyeCustomerList = typeCustmer.GetTpyeCustomerList();
		request.setAttribute("TpyeCustomerList", TpyeCustomerList);
		
		forwardText = "success";	
	
	   return mapping.findForward(forwardText);
	}
}