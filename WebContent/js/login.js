$(document).ready(function() {
    jQuery.jqtab = function(tabtit,tabcon,inClass,showNum) {
    	
    	if(showNum == 1){
    		$(tabcon).hide();
            $("#login2").addClass(inClass).show();
            $("#dl2").show();
    	}if(showNum == 2){
    		$(tabcon).hide();
            $("#login3").addClass(inClass).show();
            $("#dl3").show();
    	}else{
    		$(tabcon).hide();
            $(tabtit+" div:first").addClass("h_n_in").show();
            $(tabcon+":first").show();
    	}
        
    
        $(tabtit+" div").click(function() {
            $(tabtit+" div").removeClass("h_n_in");
            $(this).addClass("h_n_in");
            $(tabcon).hide();
            var activeTab = $(this).find("li").attr("tab");
            $("#"+activeTab).fadeIn();
            return false;
        });
    };
});

function validate(){
	if (checkspace(document.pt.username.value)){
		alert('����д�����û���!');
		document.pt.username.focus();
		return false;
	}
	if (checkspace(document.pt.password.value)){
		alert('����д��������!');
		document.pt.password.focus();
		return false;
	}
	return true;
}

function validatesj()
{
	if (checkspace(document.sj.sjtel.value)){
		alert('����д�����ֻ����롣�ƶ�����ͨ�������ֻ�������ѻ�ȡУ����!');
		document.sj.sjtel.focus();
		return false;
	}

	var reg = /^1[3|4|5|7|8]\d{9}$/;
	if(!reg.test(document.sj.sjtel.value))
	{
		alert("�ֻ����벻��ȷ!");
		return false;
	}
	
	if (checkspace(document.sj.verycode.value)){
		alert('����д���Ķ�����֤��');
		document.sj.verycode.focus();
		return false;
	}
	return true;
}

function checkspace(checkstr) {
	var str = '';
	for(i = 0; i < checkstr.length; i++) {
		str = str + ' ';
	}
	return (str == checkstr);
}

function sendSms()
{
	if (checkspace(document.sj.sjtel.value)){
		alert('����д�����ֻ����롣�ƶ�����ͨ�������ֻ�������ѻ�ȡУ����!');
		document.sj.sjtel.focus();
		return;
	}

	var reg = /^1[3|4|5|7|8]\d{9}$/;
	if(!reg.test(document.sj.sjtel.value))
	{
		alert("�ֻ����벻��ȷ!");
		return;
	}else{
		document.csendsmsval.sjtel.value =  document.sj.sjtel.value;
		document.csendsmsval.submit();
	}
}

function callback(msg){
	if (msg="33"){
		document.csendsms.sjtel.value =  document.sj.sjtel.value;
		document.csendsms.submit();
	}
}

function selectTag(showContent,selfObj){
	// ������ǩ
	var tag = document.getElementsByName("loginTab");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.className = "current";
	// ��������
	for(var i=0; i<2; i++){
		j=document.getElementById("tagContent"+i);
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
}