$(document).ready(function() {
    jQuery.jqtab = function(tabtit,tabcon) {
        $(tabcon).hide();
        $(tabtit+" div:first").addClass("q_n_in").show();
        $(tabcon+":first").show();
    
        $(tabtit+" div").click(function() {
            $(tabtit+" div").removeClass("q_n_in");
            $(this).addClass("q_n_in");
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
    $.jqtab("#qsxl_nav",".qsxl_txt");
});