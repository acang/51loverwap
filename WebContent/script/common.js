function isNull(str) {
if(str==null || str=="") return true;
return false;
}

function isBlank(str) {
    if(str==null || str=="") return true;
    var i=0;
    while(str.charAt(i)==" " || str.charAt(i)=="　") i+=1;
    str=str.substring(i);
    i=str.length-1;
    while(str.charAt(i)==" " || str.charAt(i)=="　") i-=1;
    str=str.substring(0,i+1);
    if(str==null || str=="") return true;
    return false;
}

/**
 * 联动下拉框，
* far为父框，
* son为显示的子框，
* hidson为隐藏的子框，
* name为和父框value对应的option属性值，
* first_text为指定的第一个option的text
* 例：参见ajcl中的xxdchdj.jsp，跟页面中的元素有关系
 */
function makesonselect(far,son,hidson,name,first_text){
son.innerHTML="";
if(getLen(far)==0 || (getLen(hidson)==0))
	return;
else{
  	var optionobj = document.createElement("option");

      	if(first_text != null && first_text != "")
    	{
        	optionobj.value="-1";
        	optionobj.text=first_text;
        	son.add(optionobj);
        }
	for(var i = 0;i<getLen(hidson);i++){
		if(far.options[far.selectedIndex].value==eval('hidson.options[i].'+name)){
			optionobj = document.createElement("option");
			optionobj.value = hidson.options[i].value;
			optionobj.text = hidson.options[i].text;
			son.add(optionobj);
			}
		}
	}
}

//去掉全部空格
function trimAll(string){
var temp = "";
for(var i = 0 ;i<string.length;i++)  {
if(string.charAt(i) != ' ')
temp = temp +   string.charAt(i) ;
}
return temp;
}

//去掉头尾的空格
function trim(s)
{
    if (s == null)
    {
        return s;
    }

    var i;
    var beginIndex = 0;
    var endIndex = s.length - 1;

    for (i=0; i<s.length; i++)
    {
        if (s.charAt(i) == ' ' || s.charAt(i) == '　')
        {
            beginIndex++;
        }
        else
        {
            break;
        }
    }

    for (i = s.length - 1; i >= 0; i--)
    {
        if (s.charAt(i) == ' ' || s.charAt(i) == '　')
        {
            endIndex--;
        }
        else
        {
            break;
        }
    }

    if (endIndex < beginIndex)
    {
        return "";
    }

    return s.substring(beginIndex, endIndex + 1);
}

/**
*翻页（form名：turnPage）
*/
function goPage(str,pageURL){
var form=document.forms["turnPage"];
if(str=="next"){
form.cpage.value=parseInt(form.cpage.value)+1;
}
if(str=="prev"){
form.cpage.value=parseInt(form.cpage.value)-1;
}
if(str=="first"){
form.cpage.value=1;
}
if(str=="last"){
form.cpage.value=form.j_TotalPages.value;
}
form.action=pageURL;
form.submit();
}

function doConfirm(act,count){
var totals=0;
document.all["j_ID"].value="";
document.all["j_Flag"].value="";
if(document.all["cbID"]==null)return ;
if(count == 1){
if(document.all["cbID"].checked && document.all["flag"+document.all["cbID"].value].value=="0"){
totals=1;
document.all["j_ID"].value +=document.all["cbID"].value+",";
document.all["j_Flag"].value +="1,";
}
if(document.all["cbID"].checked && document.all["flag"+document.all["cbID"].value].value=="1"){
totals=1;
document.all["j_ID"].value+=document.all["cbID"].value+",";
document.all["j_Flag"].value+="0,";
}
}
for(var i=0 ;i<document.all["cbID"].length;i++){
if(document.all["cbID"][i].checked && document.all["flag"+document.all["cbID"][i].value].value=="0"){
totals=1;
document.all["j_ID"].value +=document.all["cbID"][i].value+",";
document.all["j_Flag"].value +="1,";
}
if(document.all["cbID"][i].checked && document.all["flag"+document.all["cbID"][i].value].value=="1"){
totals=1;
document.all["j_ID"].value+=document.all["cbID"][i].value+",";
document.all["j_Flag"].value+="0,";
}
}
if(totals==0){
alert("请选择后操作！");
return;
}
showWait();
document.admin.operation.value=act;
document.admin.submit();
}

/**
* 打开一个新窗口
*/
function modalWindow(url,width,height){
var option="Height="+height+"px,scrollbars=yes,Width="+width+"px,status=0,top="+(screen.height-height)/2+",left="+(screen.width-width)/2;
window.open(url,"",option);
}

///////////////////////////////////////////////////////////////////////////////
//  checking a character or string.
///////////////////////////////////////////////////////////////////////////////

// Check a character is whether a digit or a alphabet.
function isDigitOrAlphabetChar(c) {
return ((c>='0' && c<='9') || (c>='a' && c<='z') || (c>='A' && c<='Z'));
}

function isNumber(str) {
for(var i=0;i<str.length;i++)
if(str.charCodeAt(i)<0x0030 || str.charCodeAt(i)>0x0039) return false;
return true;
}

// Check a string whether consists of digits and alphabets.
function isNumberOrAlphabet(str) {
for(var i=0;i<str.length;i++) {
var c=str.charCodeAt(i);
if(c<0x0030 || (c>0x0039 && c<0x0041) || (c>0x005A && c<0x0061) || c>0x007A) return false;
}
return true;
}

function openLink(theURL){
   window.open(theURL,"_blank",getPosition(800,600,true)+"height=500,width=800,status=yes,toolbar=yes,menubar=yes,location=yes,scrollbars=yes,resizable=yes")
}

function openRelation(theURL,height,width){
   window.open(theURL,"_blank",getPosition(800,600,true)+"height="+height+",width="+width+",status=yes,toolbar=yes,menubar=yes,location=yes,scrollbars=yes,resizable=yes")
}

function openBrief(theURL,height,width){
   window.open(theURL,"_blank",getPosition(800,600,true)+"height="+height+",width="+width+",status=no,toolbar=no,menubar=no,location=no,scrollbars=no,resizable=yes")
}

function popInfo(theURL,height,width){
   window.open(theURL,"_blank",getPosition(800,600,true)+"height="+height+",width="+width+",status=no,toolbar=no,menubar=no,location=no,scrollbars=no")
}

function popModalDialog(theURL,width,height)
{
    var Parameters = new Array();
    window.showModalDialog(theURL,Parameters,"dialogWidth:498;dialogHeight:187;status:0");
}

//去掉两边的空格
function trimSide(string){
    var temp = "";
    var bl=false;
    //去掉前面部分的空格
    for(var i=0;i<string.length;i++){
        if(bl==true||string.charAt(i) != ' '){
            temp = temp +   string.charAt(i) ;
            bl=true;
        }
    }
    string =  temp;
    temp="";
    bl=false;
    //去掉后面的空格
    for(var i=string.length;i>0;i--) {
        if(bl==true||string.charAt(i-1) != ' ') {
            temp =string.charAt(i-1)+ temp;
            bl=true;
        }
     }
     return temp;
}

function  modalWindow(url,width,height){
    var option="Height="+height+"px,scrollbars=0,Width="+width+"px,status=0,top="+(screen.height-height)/2+",left="+(screen.width-width)/2;
    window.open(url,"",option);
}

function isNumber(str){
    if(isNaN(str) || str == "") return false;
    else return true;
}

function getFormatedDoubleStr(str,fractionLength)          //获得指定格式(小数位数)的double的String类型
{
    var numstrs = str.split(".");
    var fraction = "";
    if(numstrs.length == 2)
    {
        fraction = numstrs[1];   //小数部分
        if(fractionLength > 0)   //保留fractionLength位小数
        {
            if(fraction.length <= fractionLength)
            {
                var range = fractionLength - fraction.length;
                for(var i = 0;i < range;i ++)
                {
                    fraction = fraction + "0";
                }
                return numstrs[0] + "." + fraction;                       //补足少位返回 end
            }
            else               //小数部分超过需要长度，从末尾开始四舍五入
            {
                var tail = fraction.substring(fractionLength);  //超出部分 （位数大于0）
                var remain = fraction.substring(0,fractionLength);  //保留部分 (位数大于0)
                var toadd = getRoundNum(tail);   //对tail四舍五入的结果
                var originLength = remain.length;
                var remain = new Number(remain) + toadd + "";
                var currLength = remain.length;
                if(currLength < originLength)
                {
                    for(var i = 0;i < originLength - currLength;i ++)
                        remain = "0" + remain;
                }
                else if(currLength > originLength)
                {
                    numstrs[0] = new Number(numstrs[0]) + 1 + "";
                    remain = remain.substring(1);
                }
                return numstrs[0] + "." + remain;
            }
        }
        else   //取零位小数
        {
           return new Number(numstrs[0]) + getRoundNum(numstrs[1]) + "";
        }
    }
    else         //小数位为fractionLength个０
    {
        if(fractionLength > 0)
        {
            for(var i = 0;i < fractionLength;i ++)
                fraction += "0";
            return numstrs[0] + "." + fraction;
        }
        else return numstrs[0];
    }
}

function getRoundNum(str)       //对目标字符串判断四舍五入返回0还是1
{
    var firstdigit = str.charAt(0);
    if(firstdigit > 4) return 1;
    else if(firstdigit < 4) return 0;
    else
    {
        for(var i = 1;i < str.length;i ++)
        {
            var current = str.charAt(i);
            if(current < 4) return 0;
            else if(current > 4) return 1;
        }
        return 0;
     }
}

function isNumberStr(str)
{
    if(str == null || str.length == 0) return false;
    for(var i=0;i<str.length;i++){
        if(str.charCodeAt(i)<0x0030 || str.charCodeAt(i)>0x0039) return false;
    }
    return true;
}

function isEmpty(str){
    if(str==null || str=="") return true;
    return false;
}

/**
*在后台管理中，得到选中的记录的id
*/
function getCheckedID(activexName){
    var result = new Array();
    result[0]=0;//被选中的checkbox个数
    result[1]="";//被选中的checkbox的value列表，以逗号格开
    var defaultActivexName="cbID";
    if (activexName!=null&&activexName!=""){
        defaultActivexName=activexName;
    }
    var allElement=document.getElementsByName(defaultActivexName);
    for (var i=0;i<allElement.length;i++){
        if (allElement[i].checked){
            if (result[1]=="") {
                result[1]=allElement[i].value;
            } else{
                 result[1]=result[1]+","+allElement[i].value;
            }
            result[0]++;
         }
    }
    if (result[0]<=0) {
        alert("请选择一条记录!");
    }
    return result;
}

/**
*true：全选择，false：全取消
*/
function selectAll(m){
    for(var i=0;i<document.all.length;i++){
        var box = document.all[i];
        if(box.type+""=="checkbox")box.checked=m;
    }
}

function isDate(str, separator){
    var strYear ="";
    var strMonth="";
    var strDay  ="";
    var iSeparatorCount =0;
    for(var i=0;i<str.length;i++){
        var c   =str.charAt(i);
        var cd  =str.charCodeAt(i);
        if(cd>=0x0030 && cd<=0x0039){
            switch(iSeparatorCount){
                case 0:
                  strYear+=c;
                  break;
                case 1:
                  strMonth+=c;
                  break;
                case 2:
                  strDay+=c;
                  break;
                default:
                  return false;
            }
        }else if(c==separator) {
             iSeparatorCount++;
             if(iSeparatorCount>2) return false;
        }else return false;
    }
    if(strYear.length==0 || strMonth.length==0 || strDay.length==0)return false;
    var iYear   =parseInt(eval(strYear));
    var iMonth  =parseInt(eval(strMonth));
    var iDay    =parseInt(eval(strDay));
    if(iYear<1900 || iYear>2100) return false;
    if(iMonth<1 || iMonth>12) return false;
    if(iDay<1) return false;
    var iFebDays    =28;
    if((iYear%400==0) || ((iYear%4==0) && (iYear%100!=0))) iFebDays=29;
    switch(iMonth) {
        case 2:
            return (iDay<=iFebDays);
        case 4: case 6: case 9: case 11:
            return (iDay<=30);
        default:
            return (iDay<=31);
    }
}

//判断申报所属期是否合法
function isSb_ym(sb_ym) {
	if (sb_ym.length!=6) {
		alert("申报所属期不合法！");
		return false;
	}
	var year=sb_ym.substring(0,4);
	var month=sb_ym.substring(5,6);
	if (isNaN(year)||isNaN(month)) {
		alert("申报所属期不合法！");
		return false;
	}
	return true;
}

//根据弹出窗口的大小，获得窗口的居中位置
function getPosition(width,height)
{
	var leftPosition=0;
	var screenWidth=screen.availWidth;
	if(screenWidth>width)
	{
		leftPosition=(screenWidth-width)/2;
	}
	var topPosition = 0;
	var screenHeight = screen.availHeight;
	if(screenHeight > height)
	{
		 topPosition = (screenHeight - height)/2;
	}
	var returnValue = null;
	{
		returnValue = "top=" + topPosition + ",left=" + leftPosition + ",width=" + width + ",height=" + height;
	}
	return returnValue;
}

function selTime(it)
{
	var returnVal = null;
	var strTmp = it.value;
	returnVal = window.showModalDialog("selecttime.jsp?date=" + strTmp,null,"dialogHeight:270px;dialogWidth:300px;status:0;help:0;center:1");
	if (returnVal != null)
		it.value = returnVal;
}

function calculate(je)
{
    var returnVal = null;
    var tempje = je.value;
    returnVal = window.showModalDialog("calculator.jsp?param=" + tempje,je,"dialogHeight:156px;dialogWidth:111px;status:0;help:0;center:1");
    if(returnVal != null) je.value = returnVal;
}

function isFormatedNumber(fnumStr)
{
    if(fnumStr == null) return false;
    if(!isNumber(getNoCommaNumber(fnumStr))) return false;
    var numstrs = fnumStr.split(".");
    if(numstrs.length == 2)
    {
        if(numstrs[1].indexOf(",") >= 0) return false;
    }
    var intnumstrs = numstrs[0].split(",");
    if(intnumstrs.length > 1)
    {
        for(var i = 1;i < intnumstrs.length;i ++)
        {
            if(intnumstrs[i].length != 3) return false;
        }
    }
    return true;
}

function getNoCommaNumber(fnumStr)
{
    var normalNum = fnumStr;
    var firstcomma = normalNum.indexOf(",");
    while(firstcomma > 0)
    {
        normalNum = normalNum.substring(0,firstcomma) + normalNum.substring(firstcomma + 1);
        firstcomma = normalNum.indexOf(",");
    }
    return normalNum;
}

//将标准格式的数字转化为指定小数部分长度的科学计数表现方式
function formatDoubleToScienceString(numStr,radixlength)
{
    if(isNaN(numStr) || numStr == "") return "";
    var roundednum = getFormatedDoubleStr(numStr,radixlength);
    var startindex = roundednum.indexOf(".");
    var result = "";
    if(startindex < 0) startindex = roundednum.length - 1;
    else
    {
        result = roundednum.substring(startindex);
        startindex --;
    }
    while(startindex > 2)
    {
        result = "," + roundednum.substring(startindex - 2,startindex + 1) + result;
        startindex -= 3;
    }
    result = roundednum.substring(0,startindex + 1) + result;
    return result;
}

function getPrecision(normalNumber,precision)
{   var baseNumber = Math.pow(10,precision);
	normalNumber = Math.round(normalNumber * baseNumber);
	return  normalNumber / baseNumber;
}

// Check a character is whether a 汉字.
function isHanziChar(c) {
var x=c.charCodeAt(0);
return (x>=0x4E00 && x<=0x9FFF);
}

// Check a string whether consists of 汉字.
function isHanzi(str) {
for(var i=0;i<str.length;i++)
if(!isHanziChar(str.charAt(i))) return false;
return true;
}

// Check a character is whether a half-corner character.
function isHalfCornerChar(c) {
var x=c.charCodeAt(0);
return ((x<=0x009F) || (x>=0x00E0 && x<=0x01FF && x!=0x00F7) || (x>=0xE000 && x<=0xE7CF));
}

// Check a string whether consists of half-corner characters.
function isHalfCorner(str) {
for(var i=0;i<str.length;i++) {
if(!isHalfCornerChar(str.charAt(i))) return false;
}
return true;
}

//检查日期格式是否正确（格式：2002-1-30）
function checkDateFormat(value) {
	var pos1,pos2;
	var year,month,day;
	if (countSubStr("-",value)!=2) {
		alert("请正确填写日期，格式：year-month-day");
		return false;
	}
	pos1=value.indexOf("-");
	pos2=value.lastIndexOf("-");
	year=value.substring(0,pos1);
	if (year.length!=4) {
		alert("请正确填写年份");
		return false;
	}
	if (isNaN(year)) {
		alert("年份必须为数字格式"+year);
		return false;
	}
	month=value.substring(pos1+1,pos2);
	if (isNaN(month)) {
		alert("月份必须为数字格式");
		return false;
	}
	if (month>12) {
		alert("输入的月份太大");
		return false;
	}
	month=value.substring(pos1+1,pos2);
	if (month<1) {
		alert("输入的月份太小");
		return false;
	}

	day=value.substr(pos2+1);
	if (isNaN(day)) {
		alert("日期必须为数字格式");
		return false;
	}
	if (day>31) {
		alert("输入的日期太大");
		return false;
	}
	if (day<1) {
		alert("输入的日期太小");
		return false;
	}
	return true;
}
//计算一个字符（字符串）在另一个字符串中出现的次数
function countSubStr(subStr,sourceStr) {
	var pos=-1;
	temp=sourceStr;
	for (var i=0;(pos=sourceStr.indexOf(subStr,pos+1))!=-1;i++);
	return i;
}

// Check a string separated by specified character is whether a valid date.
// Such date contains three parts: year, month and day.
function isDate(str, separator) {
var strYear ="";
var strMonth="";
var strDay  ="";
var iSeparatorCount =0;
for(var i=0;i<str.length;i++){
var c   =str.charAt(i);
var cd  =str.charCodeAt(i);
if(cd>=0x0030 && cd<=0x0039){
switch(iSeparatorCount){
case 0:
   strYear+=c;
   break;
case 1:
   strMonth+=c;
   break;
case 2:
   strDay+=c;
   break;
default:
   return false;
}
}else if(c==separator){
iSeparatorCount++;
if(iSeparatorCount>2) return false;
}else
return false;
}
if(strYear.length==0 || strMonth.length==0 || strDay.length==0)return false;
var iYear   =parseInt(eval(strYear));
var iMonth  =parseInt(eval(strMonth));
var iDay    =parseInt(eval(strDay));
if(iYear<1900 || iYear>2100) return false;
if(iMonth<1 || iMonth>12) return false;
if(iDay<1) return false;
var iFebDays    =28;
if((iYear%400==0) || ((iYear%4==0) && (iYear%100!=0))) iFebDays=29;
switch(iMonth){
case 2:
return (iDay<=iFebDays);
case 4: case 6: case 9: case 11:
return (iDay<=30);
default:
return (iDay<=31);
}
}

// check a string is whether a E-mail address.
function isValidEmail(str) {
if(str.indexOf("@")==-1 || str.indexOf(".")==-1) return false;
if(str.indexOf("@")!=str.lastIndexOf("@")) return false;
var strDeny='()<>@\\,;:"[] ';
var strLeft=str.substring(0,str.indexOf ("@"));
for(var i=0;i<strLeft.length;i++) {
if(strDeny.indexOf(strLeft.charAt(i))>-1) return false;
}
var strRight=str.substring(str.indexOf("@")+1);
for(var i=0;i<strRight.length;i++)
if("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-.".indexOf(strRight.charAt(i))==-1) return false;
if(strRight.indexOf(".")==0 || strRight.indexOf("-")==0) return false;
if(strRight.indexOf("-.")>-1 || strRight.indexOf(".-")>-1) return false;
if(strRight.lastIndexOf(".")==strRight.length-1) return false;
if(strRight.lastIndexOf("-")==strRight.length-1) return false;
return true;
}

function IsValidString(checkStr){
var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
var allValid = true;
if (checkStr.length<1)  allValid=false;
for (i=0;i<checkStr.length;i++){
ch=checkStr.charAt(i);
for(j=0;j<checkOK.length;j++)
if(ch==checkOK.charAt(j))   break;
if(j==checkOK.length){
allValid = false;
break;
}
}
return allValid;
}

function   submitIframe(frameName,formName){
document.frames(frameName).document.getElementById(formName).submit()  ;
}
function submitForm(formName){
document.getElementById(formName).submit()  ;
}
function modalDialog(url,dialogWidth,dialogHeight){
var parameter="dialogWidth:"+dialogWidth+"em;dialogHeight:"+dialogHeight+"em;status:0";
returnValue= window.showModalDialog(url,modalDialog,parameter);
return  returnValue
}

//判断是否  视频 文件
function isVideo(path)
 {
     var suffix= ".RM,.AVI,.MPG,.MPEG,.WMV,.WAV,.MP3,.RMVB,.MIDI";
     if(path ==null) return false;
     path = trim(path);
     var len = path.length;
     var tempSuffix="";
     for(var i=len;i>0;i--)
     {
        var c = path.charAt(i-1);
        if(c=='.')  break;
        else tempSuffix = c + tempSuffix;
     }
     tempSuffix = "."+tempSuffix.toUpperCase();
     if(suffix.indexOf(tempSuffix)>-1) return true;
     else return false;
 }
//判断是否  图片文件
function isPicture(path)
 {
     var suffix= ".JPG,.GIF,.BMP,.TIF,.TIFF,.JPEG";
     if(path ==null) return false;
     path = trim(path);
     var len = path.length;
     var tempSuffix="";
     for(var i=len;i>0;i--)
     {
        var c = path.charAt(i-1);
        if(c=='.')  break;
        else tempSuffix = c + tempSuffix;
     }
     tempSuffix = "."+tempSuffix.toUpperCase();
     if(suffix.indexOf(tempSuffix)>-1) return true;
     else return false;
 }


 //判断是否有效的数字
 function IsValidNumber(checkStr){
    checkStr = trimSide(checkStr);
    if(checkStr=="") return false;
    var checkOK = "0123456789.";
    var allValid = true;
    if (checkStr.length<1)  allValid=false;
    for (i=0;i<checkStr.length;i++){
    ch=checkStr.charAt(i);
    for(j=0;j<checkOK.length;j++)
    if(ch==checkOK.charAt(j))   break;
    if(j==checkOK.length){
    allValid = false;
    break;
    }
}
return allValid;
}

function selBranch()
{
    var theURL = "../common/branch.jsp";
    var reValue=window.open(theURL,"_blank",getPosition(800,600,true)+"height=400,width=300,scrollbars=yes,resizable=yes")   ;
    //alert(reValue);
}
 function selBranch2(branchId)
{
    var theURL = "../common/branch.jsp?branchId="+branchId;
    var reValue=window.open(theURL,"_blank",getPosition(800,600,true)+"height=400,width=300,scrollbars=yes,resizable=yes")   ;
    //alert(reValue);
}

function newDialog(mypage,myparam,w,h){
	settings = 'help:no;scroll:yes;status:no;dialogWidth:'+w +'px;dialogHeight:'+h+'px';
	return window.showModalDialog(mypage,myparam,settings);
}

function getLen(obj){
		if (obj==null)
			return 0;
		if (obj.length==null){
			obj[0]=obj;
			return 1;
		}
		return obj.length;
	}

//document.onkeydown=handler;
function handler(){
if(window.event.keyCode=="13")
	window.event.keyCode="9";
}

function setShort(selectobj,textobj){
  textobj.value = selectobj.options[selectobj.selectedIndex].text;
  }
