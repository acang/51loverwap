$(function(){

    var ZCDetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
               

                $(".detail-bottommenu-support").click(function(){
                    var $thisBtn=$(this);
                  
                    if(!$thisBtn.hasClass("detail-bottommenu-erro")){
                        root.sectionSlide.goIndex(1);
                    }
                });

                
				

				 
                $(".detail-bottommenu-share a").click(function(){ 
                	$(".shareBox").toggle();
                });
                
				
            }
        }
    })();
    ZCDetail.init();


	
});