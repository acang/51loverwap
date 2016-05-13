function doAddUser(){
  var form=document.getElementById("userAddFrm");
  if(isBlank(form.userName.value+"")){
      alert("请填写邮件地址");
       form.userName.focus();
       return;
  }
  form.email.value=form.userName.value+"@"+form.domain.value;
    if(!isValidEmail(form.email.value+"")){
      alert("请正确填写邮件地址");
       form.userName.focus();
       return;
  }
   if(isBlank(form.name.value+"")){
      alert("请填写用户真实姓名");
       form.name.focus();
       return;
  }
  var objType=form.usertypeid;
  if(objType.options[objType.selectedIndex].value==-1){
       alert("请选择用户类型。");
       objType.focus();
       return;
   }
   var objSexType=form.sex;
   if(objSexType.options[objSexType.selectedIndex].value==-1){
       alert("请选择用户性别。");
       objSexType.focus();
       return;
   }
    if(isBlank(form.idcardno.value+"")){
      alert("请填写身份证号");
       form.idcardno.focus();
       return;
  }
    if(!((form.idcardno.value+"").length==15||(form.idcardno.value+"").length==18)){
      alert("请填写15位或18位的身份证号");
       form.idcardno.focus();
       return;
  }

      if(isValidIdenNo(form.idcardno.value) == false){
       alert("请正确输入身份证号！");
       form.idcardno.focus();
       return;
       }
  form.submit();
}

    /**
     * 验证身份证号是否合法
     *
     * @param idenNo
     * @return 如果合法返回true,否则返回false
     */
    function isValidIdenNo( idenNo) {
        var strValidateNumber = "0123456789";
        var strValidateChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //15位全为数字
        if(idenNo.length == 15){
             if( isNumber(idenNo)==false)
                 return false;
   }
        if(idenNo.length == 18){
            for (i=0;i<idenNo.length-1;i++)
            {
                j=strValidateNumber.indexOf(idenNo.charAt(i));

                if (j == -1)
                {
                    return false;
                }
            }
             if (strValidateChar.indexOf(idenNo.charAt(i))==-1)
             {
                 return false;
             }

        }

        return true;
    }

  function doQuery(){
      var form=document.getElementById("queryFrm");
      if(form.userName.value+""!=""){
            form.email.value=form.userName.value+"@"+form.domain.value;
      }
      form.submit();
  }

 function goNextPage(nextPage){
      var form=document.getElementById("queryFrm");
      if(form.userName.value+""!=""){
            form.email.value=form.userName.value+"@"+form.domain.value;
      }
      form.pageNum.value=nextPage;
      form.submit();
 }
 function goPage(pageNum){
     if(pageNum+""==""){
       alert("请输入页码数");
       return;
     }
     if(!isNumber(pageNum)){
         alert("请正确输入页码数");
         return;
     }
     goNextPage(pageNum);
 }

 function doDisable(){
   var result=getCheckedID("userid");
   if(result[0]=="0"){
         alert("请选择要禁用的对象！");
         return;
    }
   var form=document.getElementById("queryFrm");
   form.action="User.do?action=disable";
   form.method="post";
   form.userids.value=result[1];
   doQuery();
 }
 function doDel(){
       var result=getCheckedID("userid");
      if(result[0]=="0"){
         alert("请选择要删除的对象！");
         return;
      }
    if(confirm("您是否真的确定要删除？")){
         var form=document.getElementById("queryFrm");
         form.action="User.do?action=del";
         form.method="post";
        form.userids.value=result[1];
        doQuery();
 }

 }

 function doEnable(){
   var result=getCheckedID("userid");
         if(result[0]=="0"){
         alert("请选择要开通的对象！");
         return;
      }
   var form=document.getElementById("queryFrm");
   form.action="User.do?action=enable";
   form.method="post";
   form.userids.value=result[1];
   doQuery();
 }

 function doUpdateUserRoles(){
    var form=document.getElementById("userrolefrm");
    var result=getCheckedID("roleid");
    form.roleidlist.value=result[1];
    form.submit();
 }
 function doInitPwd(){

 }
