$(function(){

    var ZCDetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                var selectPic ="<img src='images/doton.png'/>";
            	var noSelectPic ="<img src='images/dotin.png'/>";
				  
                $(".sihy").click(function(){ 
                	 $(".select").each(function(index, element) {
   						var $select=$(this);
   						$select.html(noSelectPic);
                     });
                	 var money = $(this).find(".select").attr("data-val");
                 	var product = $(this).find(".select").attr("data-id"); 
                 	$(this).find(".select").html(selectPic);
                	$("#transamt").val(money); 
                	$("#product").val(product); 
                });
                
                
                $(".sj_n_in").click(function(){ 
                	var hasuser = $("#hasuser").val();
                	if(hasuser == "0"){
	               		 alert("您是游客，请先注册或登陆！");
	               		 location.href = "reg.jsp?url=paymoney.jsp";
	               		 return ;
	               	 }
                	var paymodetype = $(this).attr("data-id");
                	$("#paymodetype").val(paymodetype);  
                	 var product = $("#product").val();
                	 if(product == ""){
                		 alert("请选择服务项目");
                		 return ;
                	 }
                	 payform.submit(); 
                });
                
                $(".sj_n_on").click(function(){ 
                	var hasuser = $("#hasuser").val();
                	if(hasuser == "0"){
	               		 alert("您是游客，请先注册或登陆！");
	               		location.href = "reg.jsp?url=paymoney.jsp";
	               		 return ;
	               	 }
                	var paymodetype = $(this).attr("data-id");
                	$("#paymodetype").val(paymodetype);  
                	var product = $("#product").val();
               	 	if(product == ""){
                		 alert("请选择服务项目");
                		 return ;
                	 }
                	 payform.submit(); 
                });
				
                $(document).ready(function(){
            	    var flag = isWeiXin();//WeixinApi.openInWeixin();  
	            	 	if (flag) {    
	            	 		 $("#weixin").show();
	            	 		$("#weixin2").show();
	            	 		 $("#zhifubao").hide();
	            	 	}
                   });
            }
        }
    })();
    ZCDetail.init();
	
});

function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}