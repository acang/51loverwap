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
                		 alert("请准确填写您的邮箱，以便收到回复！"); 
                           return;
                	}
                	if(ntext ==""){
               		 	alert("请输入留言内容"); 
                          return;
                	}
                	if(email.indexOf("@") <= 0){
               		 alert("请准确填写您的邮箱，以便收到回复！"); 
                          return;
                	}
                	 submitform.submit(); 
                });
                
            }
        }
    })();
    BBSetail.init();
	
});