$(function(){

    var ZCDetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                
				 
                $(".number  li").click(function(){ 
                	var page = $(this).attr("data-id");
                	if(page != null){
                		$("#cpages").val(page);
                    	go2to.submit(); 
                	}
                });
 
            }
        }
    })();
    ZCDetail.init();
	
});