$(document).ready(function() {
	 
    jQuery.jqtab = function(tabtit,tabcon,inClass) {
        $(tabcon).hide();
        $(tabtit+" div:first").addClass(inClass).show();
        $(tabcon+":first").show();
    
        $(tabtit+" div").click(function() {
            $(tabtit+" div").removeClass(inClass);
            $(this).addClass(inClass);
            $(tabcon).hide();
            var activeTab = $(this).find("li").attr("tab");
			if(activeTab=='divqs3'){
				$(this).addClass("bodo_r");
			}else{
				$("#tab3").removeClass("bodo_r");
			}
            $("#"+activeTab).fadeIn();
            return false;
        });
        
    };
    
    
});