<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<%
	request.setCharacterEncoding("GBK");
	response.setHeader("Cache-Control", "No-Cache");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</HEAD>
<body>
	<div id="toolbar_DIV">
		<table id="toolbar_main" cellpadding="0" cellspacing="0">
			<tr>
				<td>&nbsp;</td>
				<td onclick="doCreate();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">新增</td>
				<!--
				<td>&nbsp;</td>
				<td onclick="doEdit();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">编辑</td>
				<td>&nbsp;</td>
				<td onclick="doDelete();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">删除</td>
				<td>&nbsp;</td>
				<td onclick="doDetail();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">详细</td>
				<td>&nbsp;</td>
			--></tr>
		</table>
	</div>
	<input type="hidden" name="idStr" id="idStr">
	<input type="hidden" name="count" id="count">
	<input type="hidden" name="status" id="status">
</body>
<script language="javascript"><!--
//初始化toolbar样式
var toolbarTable = document.getElementById("toolbar_main");
var toolbarRow = toolbarTable.rows[0];
toolbarRow.className = "toolbar_bg";
var tds = toolbarRow.cells;
for(var i = 0; i < tds.length; i++){
	if(i % 2 == 0){
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
//var defaultRows = top.document.getElementById("fraTop").rows;
var idStr = "";
var count = 0;
var printID = "";
var emailID = "";
var receiver = "";
var flag = "";
var status = "";

function doSearch(){
	var fraSearchForm = top.frames[0];
	fraSearchForm.doSearch();
}

function getId(){
	try{
		top.frames[2].window.checkSingleRow();
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
//返回3个元素的数组，包含','连接的id，status数组,handle数组
function geIdStatus(){
	var idStatusList = idStr.split(",");
	var ids = "";
	var statusArr = new Array(idStatusList.length-1);
	var handleArr = new Array(idStatusList.length-1);
	for(var i = 0;i < idStatusList.length;i++){
		var idStatus = idStatusList[i].split("|");
		ids += "," + idStatus[0];
		statusArr[i] = idStatus[1];
		handleArr[i] = idStatus[2];
	}
	return new Array(ids.substr(1),statusArr,handleArr);
}

//编辑
function doEdit(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("请选择要编辑的服务网点！");
		return;
	}
	if (count > 1) {
		alert("只能选择一个服务网点进行编辑！");
		return;
	}
	geIdStatus();
	top.document.getElementById("operate").src = "${ctx}/business/businessManage/serviceNetwork/findServiceNetworkForUpdate.do?geStationInfo.obid=" + geIdStatus()[0];
}
//新增
function doCreate(){
	top.document.getElementById("operate").src = "${ctx}/system/departmentManage/serviceNetwork/create/frmCreate.jsp?deptid=${param.deptid}&city=${param.city}&cityName=${param.cityName}&province=${param.province}&provinceName=${param.provinceName}";
}
//删除
function doDelete(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("请选择要删除的服务网点！");
		return;
	}
	if(confirm("确定删除您选中的"+count+"个服务网点吗？")){
		top.document.getElementById("fraHidden").src = "${ctx}/business/businessManage/serviceNetwork/deleteServiceNetwork.do?idStr=" + idStr;
	}
}
//详细
function doDetail(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("请选择要查看的服务网点！");
		return;
	}
	if (count > 1) {
		alert("只能选择一个服务网点进行查看！");
		return;
	}
	geIdStatus();
	top.document.getElementById("operate").src = "${ctx}/business/businessManage/serviceNetwork/findServiceNetworkForDetail.do?geStationInfo.obid=" + geIdStatus()[0];

}
//特殊处理end
</script>
</HTML>
