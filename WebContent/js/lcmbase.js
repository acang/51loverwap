$(document).ready(function() {
	$(".cm_search .cm_block01 .block01 .tab_name span").hover(
		function(){
			$(this).addClass("current");
			$(this).siblings().removeClass("current");
			var dangqian=$(".cm_search .cm_block01 .block01 ul").eq($(".cm_search .cm_block01 .block01 .tab_name span").index(this));
			dangqian.addClass("show_ul");
			dangqian.siblings().removeClass("show_ul");
			}
	);
	
//	$(".top_login_btn").click(
//		function(){
//			$(".login_bg").show();
//			$(".login_box").show();
//			}
//	);
//	$(".login_box .close_btn,.login_bg").click(
//		function(){
//			$(".login_bg").hide();
//			$(".login_box").hide();
//			}
//	);
//
//	$(".login_box .block01 .tab span").hover(
//		function(){
//			$(this).addClass("current");
//			$(this).siblings().removeClass("current");
//			var dangqian=$(".login_box .block01 table").eq($(".login_box .block01 .tab span").index(this));
//			dangqian.addClass("show");
//			dangqian.siblings().removeClass("show");
//			},
//		function(){
//
//			}
//		);
		
	$(".cm_grzx .cm_block01 .block01 .box02 .tab span").hover(
		function(){
			$(this).addClass("current");
			$(this).siblings().removeClass("current");
			var dangqian=$(".cm_grzx .cm_block01 .block01 .box02 .mingdan").eq($(".cm_grzx .cm_block01 .block01 .box02 .tab span").index(this));
			dangqian.addClass("show");
			dangqian.siblings().removeClass("show");
			},
		function(){
			
			}
		);
	
	$(".cm_bbs .cm_block01 .block01 .box04 ul.hyjl_list li").hover(
		function(){
			$(this).find("p").fadeOut();
					},
		function(){
			$(this).find("p").fadeIn();
			});
	
	
	
});
