$(function(){

    var ZCDetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                
                $(document).ready(function(){
                	addFavorite2();
              });  
                
            }
        }
    })();
    ZCDetail.init();
	
});

//var isAdd=0;
function addFavorite2() {
//if(isAdd == 0){

	var url = window.location;
    var title = document.title;
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf("360se") > -1) {
        alert("�뽫��վ���������������ǩ��");
    }else if (ua.indexOf("msie 8") > -1) {
        window.external.AddToFavoritesBar(url, title); //IE8
    }else if (document.all) {
		try{
	   		window.external.addFavorite(url, title);
	  	}catch(e){
	   		alert('�뽫��վ���������������ǩ��');
	  	}
    }else if (window.sidebar) {
        window.sidebar.addPanel(title, url, "");
    }else {
  		alert('�뽫��վ���������������ǩ��');
   }
 //   isAdd=1;
    addFavoriteform.submit();
    
 //   }
}
 