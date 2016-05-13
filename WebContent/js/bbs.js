$(function(){

    var BBSetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                 
                
                $(".h_pl_txt  .h_plt_fb").click(function(){ 
                	form1.submit(); 
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
                
                $(".number  li").click(function(){ 
                	var page = $(this).attr("data-id");
                	if(page != null){
                		$("#cpages").val(page);
                    	go2to.submit(); 
                	}
                });
                
              
                $("#content").click(function(){ 
                	  $(".detail-bottommenu").hide();
                });
                
              
                 
                $(document).ready(function(){
                	 $(".hty_txt img").each(function(index, element) {
 						var $img=$(this);
 						var newWidth=$(".hty_txt").width();
 						var oldWidth=$img.width();
 							if(oldWidth>newWidth){
 								 var oldheight = $img.height();
 								var percent =  newWidth/oldWidth;	 
 								 var newHeight = parseInt(oldheight*percent); 
 								 $img.width(newWidth);
 								 $img.height(newHeight);
 								
 							}
                       });
                	 $(".hty_txt").find("*").each(function(index, element) {
  						var $txt=$(this);
  						var newWidth=$(".hty_txt").width();
  						var oldWidth=$txt.width();
  							if(oldWidth>newWidth){
  								$txt.width(newWidth);
  							}
                        });
                	  $(document).mousemove(function(e){
                		  $(".detail-bottommenu").show();
                	  });
                	});
                
                 
                
            }
        }
    })();
    BBSetail.init();
	
});