$(function(){

    var ZCDetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                
				 
                $("#lxfs").click(function(){ 
                	var userid = $(this).attr('data-id');
                	location.href = "lxfs-id-"+userid+".htm";
                });
                
                $("#yj2 img").click(function(){ 
                	var bigImg = $(this).attr('bigImg');
                	var imgs="<img src='"+bigImg+"' id='showImg' />";
                //	$("#bigImg").html(imgs)
                //	$("#bigImg").show();
                //	$("#header").hide();
                //	$("#UIDropMenu").hide();
                	
                });
 
                $("#photos").click(function(){ 
                	var isAddFavorite = $("#isAddFavorite").val();
                	if( isAddFavorite!=1){
                		$("#isAddFavorite").val("1");
                		addFavorite2();
                	}
                	
                });
                
                $("#bigImg").click(function(){ 
                //	$("#bigImg").hide();
                //	$("#header").show();
                //	$("#UIDropMenu").hide();
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
      alert("请将本站加入您的浏览器书签！");
  }else if (ua.indexOf("msie 8") > -1) {
      window.external.AddToFavoritesBar(url, title); //IE8
  }else if (document.all) {
		try{
	   		window.external.addFavorite(url, title);
	  	}catch(e){
	   		alert('请将本站加入您的浏览器书签！');
	  	}
  }else if (window.sidebar) {
      window.sidebar.addPanel(title, url, "");
  }else {
		alert('请将本站加入您的浏览器书签！');
 }
//   isAdd=1;
  addFavoriteform.submit();
  
//   }
}