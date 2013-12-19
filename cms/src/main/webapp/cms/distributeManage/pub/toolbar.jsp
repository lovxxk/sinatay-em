<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<link href="${ctx}/cms/global/css/GUI.css" rel="stylesheet" type="text/css">
<title>toolbar.jsp</title>
<script type="text/javascript">
function doDelete(){
	var IDStr;
	var IDArray;
	
	if (window.ID.value==""){
		alert("请先选择要发布的文章！");
		return false;
	}	
	IDStr=window.ID.value;
	//alert(IDStr);
	IDArray=IDStr.split(",");	
	if (confirm("发布选中的" + window.IDCount.value + "篇文章?")){
		window.parent.fraHIDDEN.location.href="${ctx}/distributeManage/goPub.do?ID="+IDStr;
	}
}

function checkID(count,ID,IsChecked){
	if (IsChecked){
		//eval("window.parent.fraSearchList.tr" + count + ".className='TableGrid_checked';");
		addID(ID);}
	else{
		//eval("window.parent.fraSearchList.tr" + count + ".className='';");
		delID(ID);}
}

function addID(ID){
	var IDStr;
	var IDArray;
	var i;
	
	IDStr=window.ID.value;
	IDArray=IDStr.split(",");
	
	for (i=0;i<(IDArray.length-1);i++){
		if (ID==IDArray[i])
			return false;
	}
			
	window.ID.value=window.ID.value + ID + ",";
	window.activeID.value=ID;
	window.IDCount.value=IDArray.length;

}

function delID(ID){
	var IDStr;
	var i;
	
	IDStr=window.ID.value;
	IDArray=IDStr.split(",");
	
	IDStr="";
	for (i=0;i<(IDArray.length-1);i++){
		if (ID!=IDArray[i])
			IDStr=IDStr + IDArray[i] + ",";
	}
	window.ID.value= IDStr;
	IDArray=IDStr.split(",");
	window.IDCount.value=IDArray.length-1;
	window.activeID.value="";
}
</script>
</head>
<body background="../../../global/images/toolbar_bg.jpg" leftmargin="0" topmargin="0">
<form id="frmINPUT">

<table id="toolTable" cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:9pt;">
<tr align=center>
	<td><img src="../../../global/images/1trans.gif" width=2></td>	
	<td onclick="javascript:maximizeGrid()" nowrap title="扩大数据表" style="CURSOR: hand;"><img id="point" src="../../../global/images/toolbar_ico_up.jpg" border="0"></td>
	<td id="tabindex2" tabindex="2" width=75 onmouseover="this.className='toolbarOnFocus'" onmouseout="this.className='toolbarOnBlur'" onclick="javascript:doDelete();" class="toolbarOnBlur" nowrap>发布</td>
	<td width=8><img src="../../../global/images/right_top_se.jpg"></td>
	
	<td width="100%" nowrap align=right><div id="divPage"></div></td>
</tr>
</table>
</form>
<input type=hidden name="ID" id="ID">
<input type=hidden name="IDCount" id="IDCount">
<input type=hidden name="activeID" id="activeID">
</BODY>
</HTML>
