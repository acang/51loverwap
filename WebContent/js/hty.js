$(function(){

    var ZCDetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                
				 
                $(".h_pl_txt  .h_plt_fb").click(function(){ 
                	form1.submit(); 
                });
 
                $(".number  li").click(function(){ 
                	var page = $(this).attr("data-id");
                	if(page != null){
                		$("#cpages").val(page);
                    	go2to.submit(); 
                	}
                });
                
                $(".hnul_r").click(function(){ 
                	var bbsReId = $(this).attr("data-id1");
                	var userId = $(this).attr("data-id2");
                	var selfId = $(this).attr("data-id3");
                	if(selfId==0){
                        alert("您是游客，请先注册或登陆！");
                        location.href = "../reg.jsp";
                          return;
                      }
                		$("#bbsReId").val(bbsReId);
                		$("#userId").val(userId);
                		$("#selfId").val(selfId);
                		zanForm.submit(); 
                });
                
                $("#content").click(function(){ 
                	  $(".detail-bottommenu").hide();
                });
                
              
                 
                $(document).ready(function(){
                	  $(document).mousemove(function(e){
                		  $(".detail-bottommenu").show();
                	  });
                	});
                
                 
            }
        }
    })();
    ZCDetail.init();
	
});