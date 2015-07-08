	var thaiMsgMaxlen1 = 67;
	var thaiMsgMaxlen2 = 137;
	var thaiMsgMaxlen3 = 207;
	var engMsgMaxlen1 = 157;
	var engMsgMaxlen2 = 317;
	var engMsgMaxlen3 = 477;
    
    function msgCheckerOnchange(sender,msg,msglen,msgNumb) {
    	// alert(msglen.value.length);
        var len = msg.value.length + sender.value.length;
       if (haveThai(msg) || haveThai(sender)) {
    	    msgMaxlen=thaiMsgMaxlen3;
            if(len == 0){
                    msgNumb.value = 0;
            }
            else if (len <= thaiMsgMaxlen1){
                    msgNumb.value = 1;
            }
            else if(len <= thaiMsgMaxlen2){
                    msgNumb.value = 2;
            }
            else if(len <= thaiMsgMaxlen3){
                    msgNumb.value = 3;
            }
            else {
                    msgNumb.value = 3;
                    msg.value = msg.value.substring(0,msgMaxlen);
                    sender.value = sender.value.substring(0,msgMaxlen-msg.value.length);
            }
            
	}else{
		msgMaxlen=engMsgMaxlen3;
            if(len == 0){
                    msgNumb.value = 0;
            }
            else if(len <= engMsgMaxlen1){ 
                    msgNumb.value = 1;
            }
            else if(len <= engMsgMaxlen2){
                    msgNumb.value = 2;
            }
            else if(len <= engMsgMaxlen3){
                    msgNumb.value = 3;
            }
            else{
                    msgNumb.value = 3;
                    msg.value = msg.value.substring(0,msgMaxlen);
                    sender.value = sender.value.substring(0,msgMaxlen-msg.value.length);
            }
        }	
        msglen.value = len;
    }
    
    function haveThai(msg){
		var i;
		var aChar;
                
		for (i=0;i<msg.value.length;i++){
			aChar = msg.value.substring(i,i+1); 
			if ( (aChar >= "ก") && (aChar <="๙") )
				return true;
		}
		return false;
    }
    function isNumberKey(evt) {
         var charCode = (evt.which) ? evt.which : evt.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57)) {
             return false;
         } else {
             return true;
         }
	}
    
