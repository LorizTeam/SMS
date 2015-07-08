/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smsimobile.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import jxl.Cell;
import jxl.JXLException;
import jxl.Workbook;

import com.smsimobile.data.TBLPhoneBook;
import com.smsimobile.form.ImportForm;
import org.apache.commons.codec.net.URLCodec;
/** 

/** 
 * MyEclipse Struts
 * Creation date: 09-30-2009
 * 
 * XDoclet definition:
 * @struts.action path="/import" name="importForm" scope="request" validate="true"
 */
public class ImportAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		ImportForm importForm = (ImportForm) form;// TODO Auto-generated method stub
		String forwardText = null; 
        FormFile myFile = importForm.getTheFile();
    //    String loginUsername = importForm.getLoginUsername();
        String contentType = myFile.getContentType();
        String fileName = new URLCodec("UTF-8").decode(myFile.getFileName()); 
        String alertMas = "", alertMas1 = "";
     //   String fileName    = myFile.getFileName();
        int fileSize       = myFile.getFileSize();
        System.out.println("contentType: " + contentType);
        System.out.println("File Name: " + fileName);
        System.out.println("File Size: " + fileSize);	
        System.out.println("Type: " + new URLCodec("UTF-8").decode(importForm.getType()));
        try {
		 InputStream is =  myFile.getInputStream();
	     
		 String[] field = new String[1024];
		 
	     byte[] junk = new byte[1024];
	    
	     int bytesRead = 0;
	     
	     String[] sheetName = null;
	     
	     // the first four lines are request junk
	     //bytesRead = is.read(junk, 0, junk.length);
	     //bytesRead = is.read(junk, 0, junk.length);
	     //bytesRead = is.read(junk, 0, junk.length);
	     //bytesRead = is.read(junk, 0, junk.length);
	     
	     Workbook workbook = Workbook.getWorkbook(is);
	     
	     // Do stuff with the workbook
	     
	     String sheetsName = workbook.getSheetNames()[0];
	     
	     int row = workbook.getSheet(sheetsName).getRows();
	     int column = workbook.getSheet(sheetsName).getColumns();

	     
	     int i = 1;
	     int j = 0;
	     int r = 1;
	     String id = null;
	     String name = null;
	     String phone = null;
	   //  String loginUsername = null;
	   //  String dateEnd = null;
	     TBLPhoneBook TBLPhoneBook = new TBLPhoneBook();
	    
	     while(i<row) {
	    	 while(j<column) {
	    		 Cell cell = workbook.getSheet(sheetsName).getCell(j,i);
	    		 field[j] = cell.getContents();
	    		 j++;
	    	 }
	    	 
	    	 name = field[0];
	    	 if(field[1].length()==9){
	    		 phone = "0"+field[1];
	    	 }else{
	    		 phone = field[1];
	    	 }
	    	 
	    	 System.out.println("field[0]: " + field[0]);
	    	 System.out.println("field[1]: " + field[1]);
	  
	    	 
	    	 try {
	    		 boolean chk = false;
	    		 
	    		 chk = TBLPhoneBook.checkphoneRow(phone);
	    		 if(chk == true){
	    			 
	    			 alertMas =  alertMas1+phone+" : Row"+r+", ";
	    			 
	    		 }else{
	    			 TBLPhoneBook.entry_phonebook(name, phone, "");
	    		 }
	    		/* switch(typeInt) {
	    		 case 1 : learningDB.importTrainingData(id, name, code, dateStart, dateEnd);
	    			 break;
	    		 case 2 : learningDB.importELearningData(id, name, code, dateStart, dateEnd);
	    			 break;
	    		 case 3 : learningDB.importBookData(id, name, code);
	    			 break;
	    		 case 4 : //Other
	    			 break;
	    		 }*/
	    		 alertMas1 = alertMas;
				 forwardText = "success";
				 request.setAttribute("report", "Import successful !");
			  } catch (SQLException e) {
				 forwardText = "error";
				 request.setAttribute("error", e.getMessage());
			  }
	    	 
	    	 i++;
	    	 j=0;
	    	 r++;
	     }
	     if(!alertMas.equals("")){
	     alertMas = "ข้อมูลซ้ำ ("+alertMas+")";
	     alertMas = alertMas.replace(", )", ")");
	     request.setAttribute("alertMas", alertMas);
	     }
	   } catch (JXLException e) {
		   e.printStackTrace();
	   }
	   return mapping.findForward(forwardText);
	}
}