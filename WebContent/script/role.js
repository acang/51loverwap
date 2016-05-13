function doAddRole(){
   var form=document.getElementById("roleAddFrm");
  if(isBlank(form.name.value+"")){
      alert("请填写角色名称");
       form.name.focus();
       return;
  }
  form.submit();
}

function doUpdateRole(){
   var form=document.getElementById("roleUpdateFrm");
  if(isBlank(form.name.value+"")){
      alert("请填写角色名称");
       form.name.focus();
       return;
  }
  form.submit();
}

function doShowHide(tblName){
     var objFunctionTbl=document.getElementById("functionTbl");
     var privilegesTbl=document.getElementById("privilegesTbl");
     var privilegesTitle=document.getElementById("privilegesTitle");
     var functionTitle=document.getElementById("functionTitle");
     if(tblName=="functionTbl"){
          if(objFunctionTbl.style.display=="none"){
                  objFunctionTbl.style.display="block";
                  functionTitle.innerHTML=">>>栏目管理模块";
          }else{
                   objFunctionTbl.style.display="none";
                   functionTitle.innerHTML="<<<栏目管理模块";
          }
     }
      if(tblName=="privilegesTbl"){
          if(privilegesTbl.style.display=="none"){
                  privilegesTbl.style.display="block";
                  privilegesTitle.innerHTML=">>>权限管理模块";
          }else{
                   privilegesTbl.style.display="none";
                   privilegesTitle.innerHTML="<<<权限管理模块";
          }
     }
}

function doUpdatePrivileges(){
  var result=getCheckedID("actionid");
  var form=document.getElementById("privilegedFrm");
  form.actionidlist.value=result[1];
  form.submit();
}

function goPage(pageNum){
   var form=document.getElementById("turnFrm");
     if(pageNum+""==""){
       alert("请输入页码数");
       return;
     }
     if(!isNumber(pageNum)){
         alert("请正确输入页码数");
         return;
     }
 form.submit();
 }

function doDelRole(roleid){
    if(confirm("您是否真的确定要删除？")){
         window.location="role.do?action=del&roleid="+roleid;
    }
}
 function selectAll2(parentName){
      var objParent=document.getElementById(parentName);
      var checked=true;
      if(objParent.checked){
            checked=true;
      }else{
            checked=false;
      }
     for(var i=0;i<document.all.length;i++){
            var box=document.all[i];
            if(box.name==parentName)continue;
            if((box.id+"").indexOf(parentName+"")>=0)box.checked=checked;
     }
}
