$(function(){

    var BBSetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                
                $("#boxSubmit").click(function(){ 
                	var email = $("#email").val();
                	var ntext = $("#ntext").val();
                	if(email ==""){
                		 alert("��׼ȷ��д�������䣬�Ա��յ��ظ���"); 
                           return;
                	}
                	if(ntext ==""){
               		 	alert("��������������"); 
                          return;
                	}
                	if(email.indexOf("@") <= 0){
               		 alert("��׼ȷ��д�������䣬�Ա��յ��ظ���"); 
                          return;
                	}
                	 submitform.submit(); 
                });
                
            }
        }
    })();
    BBSetail.init();
	
});