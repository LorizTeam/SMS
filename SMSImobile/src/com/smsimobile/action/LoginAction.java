/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smsimobile.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smsimobile.data.LoginDB;
import com.smsimobile.form.LoginForm;
import com.smsimobile.util.DateUtil;

/** 
 * MyEclipse Struts
 * Creation date: 09-28-2009
 * 
 * XDoclet definition:
 * @struts.action path="/login" name="loginForm" scope="request" validate="true"
 */
public class LoginAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginForm loginForm = (LoginForm) form;// TODO Auto-generated method stub
		LoginDB loginDB = new LoginDB();
		HttpSession session = request.getSession();
		String userName = loginForm.getUserName();
		String passWord = loginForm.getPassWord();
		
		String forwardText = null;
	//	System.out.println("ipAddress : "+ipAddress);
		
		if(userName != "" || passWord != "") {
			loginForm = loginDB.checkLogin(userName, passWord);
			if(loginForm.isTrue()) {
					
					session.setAttribute("userName", userName);
					session.setAttribute("passWord", passWord);
					session.setAttribute("name", loginForm.getName());
					session.setAttribute("type", loginForm.getType());
					 
					session.setAttribute("name", loginForm.getName());
					String login = "1";
					session.setAttribute("login", login);
				//	session.setAttribute("actionDisabled", loginForm.isActionDisabled());
				//	session.setAttribute("actionDisabled", "true");
					String type = loginForm.getType();
					
					if(type.equals("adm")){
						forwardText = "success_amd";
					}else if(type.equals("acc")){
						forwardText = "success_acc";
					}else if(type.equals("agt")){
						forwardText = "success_agt";
					}
					
				//	genaratePeriod(request, userName);
					
					List memberList = loginDB.MemberList("", "", "");
					request.setAttribute("memberList", memberList);
					
					DateUtil dateUtil = new DateUtil();
					String date 	= dateUtil.curDate();
					date = dateUtil.CnvToYYYYMMDD(date, '-');
					
					loginForm.reset();
			
			} else {
				request.setAttribute("error", "Username หรือ Password ไม่ถู�?ต้อง ");
				String login = "0";
				session.setAttribute("login", login);
				forwardText = "error";
			}
		} else {
			request.setAttribute("error", "โปรดระบุ Username �?ละ Password");
			String login = "0";
			session.setAttribute("login", login);
			forwardText = "error";
		}
		return mapping.findForward(forwardText);
	}

}