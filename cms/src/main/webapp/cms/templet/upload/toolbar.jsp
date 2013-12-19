<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ctx }/global/css/mis_basic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</HEAD>
<body>
<div id="toolbar_DIV">
<form id="frmINPUT">
<table id="toolbar_main" cellpadding="0" cellspacing="0">
	<tr>
		<td onclick="javascript:maximizeGrid(this);">&nbsp;</td>
		<!--  
		<td onclick="doCreate();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">新模板定义</td>
		<td>&nbsp;</td>
		-->
		<td onclick="doEdit();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">模板编辑</td>
		<td>&nbsp;</td>
		<td onclick="doDelete();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">删除</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
</div>
<input type="hidden" name="ID" id="ID">
<input type="hidden" name="IDCount" id="IDCount">
<input type="hidden" name="activeID" id="activeID">
</body>
<script language="javascript">
	//初始化toolbar样式
	var toolbarTable = document.getElementById("toolbar_main");
	var toolbarRow = toolbarTable.rows[0];
	toolbarRow.className = "toolbar_bg";
	var tds = toolbarRow.cells;
	for(var i = 0; i < tds.length; i++){
		if(i == 0){
			tds[i].className = "toolbar_up";
			tds[i].title = "扩大数据表";
		}else if(i % 2 == 0){
			tds[i].className = "toolbar_gap";
		}else{
			tds[i].className = "toolbar_normal";
		}
	}
	//改变toolbar样式
	function changeTD_style(obj){
		if(obj.className == "toolbar_normal"){
			obj.className = "toolbar_hover";
		}else{
			obj.className = "toolbar_normal";
		}
	}
	
	//公用区域start
	var defaultRows = top.frames[1].frames[2].document.getElementById("fraTop").rows;
	var idStr = "";
	var count = 0;
	var printID = "";
	var emailID = "";
	var receiver = "";
	var flag = "";
	var status = "";
	function maximizeGrid(obj){
		var className = obj.className;
		if(className == "toolbar_up"){//上移
			obj.className = "toolbar_down";
			top.frames[1].frames[2].document.getElementById("fraTop").rows='0,30,*,40,0';
			obj.title = "还原数据表";
		}else{//下移
			obj.className = "toolbar_up";
			top.frames[1].frames[2].document.getElementById("fraTop").rows=defaultRows;
			obj.title = "扩大数据表";
		}
	}
	
	function doSearch(){
		var fraSearchForm = top.frames[1].frames[2].frames[0];
		fraSearchForm.document.getElementById("frmInput").submit();
	}
	
	function getId(){
		try{
			top.frames[1].frames[2].frames[2].window.checkSingleRow();
			idStr = document.getElementById("idStr").value;
			count = document.getElementById("count").value;
			status = document.getElementById("status").value;
			return true;
		}catch (e) {
			alert("请选择要操作的记录！");
			return false;
		}
	}
	var top_ = (window.screen.availHeight-750)/2;
	if(top_ < 0){
		top_ = 0;
	}
	var left_ = (window.screen.availWidth-1020)/2;
	if(left_ < 2){
		left_ = 0;
	}
	var config = "width=1020,height=750,toolbar=no,statusbar=no";
	config += ",top="+top_ +",left="+left_;
	//公用区域end
	
	//特殊处理，随功能不同而不同
	//创建
	function doCreate(){
		window.parent.location.href = "${ctx}/cms/templet/upload/frmCreate.jsp";
	}
	
	//编辑
	function doEdit(){
		var IDStr;
		var IDArray;
		
		if (window.ID.value==""){
			alert("请先选择要编辑的样式！");
			return false;
		}	
		if(window.IDCount.value>1){
			alert("一次只能编辑一个样式！");
			return false;
		}
		IDStr=window.ID.value;
		window.ID.value="";
		IDArray=IDStr.split(",");
		parent.document.getElementById("fraSearchList").src="${ctx}/templetManage/toGetCmsTempletForUpdate.do?ID="+IDArray[0];
	}
	
	//删除
	function doDelete(){
	var IDStr;
	var IDArray;
	
	if (window.ID.value==""){
		alert("请先选择要删除的样式！");
		return false;
	}	
	IDStr=window.ID.value;
	IDArray=IDStr.split(",");	
	if (confirm("删除选中的" + window.IDCount.value + "个样式?")){
		window.parent.fraHIDDEN.location.href="${ctx}/templetManage/toDeleteBind.do?ID="+IDStr;
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
</HTML>
