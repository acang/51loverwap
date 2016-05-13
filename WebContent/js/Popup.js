var gcToggle = "#ffff00";
var gcBG = "#CCCCFF";
function chg_page(form_name,p){
form_name.page.value=p;
form_name.Submit.click();
}
function IOFFICE_GetSelected(aCell)
{
	window.returnValue = aCell.innerText;
	window.close();
}


function fPopUpDlg(endtarget,ctl,WINname,WINwidth,WINheight,X,Y){
	if(X=="" ||Y=="")
	{
	showx =  WINwidth + 200  ; // + deltaX;
	showy = WINheight ; // + deltaY;
	}
	else
	{
		showx =  X -WINwidth; // + deltaX;
		showy =WINheight + 30 ; // + deltaY;
	}
	newWINwidth = WINwidth + 4 + 18;
	var features =
		'dialogWidth:'  + newWINwidth  + 'px;' +
		'dialogHeight:' + WINheight + 'px;' +
		'dialogLeft:'   + showx     + 'px;' +
		'dialogTop:'    + showy     + 'px;' +
		'directories:no; localtion:no; menubar:no; status=no; toolbar=no;scrollbars:yes;Resizeable=no';
	
	retval = window.showModalDialog(endtarget, WINname , features );
	if( retval != null ){
		ctl.value = retval;
	}else{
		//alert("canceled");
	}
}

function fPopUpCalendarDlg(ctrlobj)
{
	showx = event.screenX - event.offsetX - 4 - 210 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;

	retval = window.showModalDialog("../masterref/CalendarDlg.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}else{
		//alert("canceled");
	}
}

function getMsg(endtarget,ctl,WINname,WINwidth,WINheight,X,Y){
	if(X=="" ||Y=="")
	{
	showx =  WINwidth + 200  ; // + deltaX;
	showy = WINheight ; // + deltaY;
	}
	else
	{
		showx =  X -WINwidth; // + deltaX;
		showy =WINheight + 30 ; // + deltaY;
	}
	newWINwidth = WINwidth + 4 + 18;
	var features =
		'dialogWidth:'  + newWINwidth  + 'px;' +
		'dialogHeight:' + WINheight + 'px;' +
		'dialogLeft:'   + showx     + 'px;' +
		'dialogTop:'    + showy     + 'px;' +
		'directories:no; localtion:no; menubar:no; status=no; toolbar=no;scrollbars:yes;Resizeable=no';
	
	retval = window.showModalDialog(endtarget, WINname , features );
	if( retval != null ){
		ctl.InnerHTML = retval;
	}else{
		//alert("canceled");
	}
}
