$(function(){

    var LYtail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                 
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
    LYtail.init();
	
});