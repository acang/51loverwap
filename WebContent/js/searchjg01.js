$(function(){

    var ZCDetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                
                $("#divqs1").click(function(){ 
                		$("#isvcation").val("1");
                		$("#divqs1").addClass("h_n_in");
                		$("#divqs2").removeClass("h_n_in");
                		advsearch.submit(); 
                });
				  
                $("#divqs2").click(function(){ 
            		$("#isvcation").val("0");
            		$("#divqs2").addClass("h_n_in");
            		$("#divqs1").removeClass("h_n_in");
            		advsearch.submit(); 
                });
                
                $(".number  li").click(function(){ 
                	var page = $(this).attr("data-id");
                	if(page != null){
                		$("#cpages").val(page);
                		advsearch.submit(); 
                	}
                });
                
            }
        }
    })();
    ZCDetail.init();
	
});