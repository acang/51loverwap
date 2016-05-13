$(function(){

    var ZCDetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                
                $("#dl1  .tjan").click(function(){ 
                	pt.submit(); 
                });
            }
        }
    })();
    ZCDetail.init();


	
});