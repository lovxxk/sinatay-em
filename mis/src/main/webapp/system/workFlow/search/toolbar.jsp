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
				<td onclick="javascript:maximizeGrid(this);">
					&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="idStr" id="idStr">
	<input type="hidden" name="count" id="count">
	<input type="hidden" name="status" id="status">
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
	fraSearchForm.doSearch();
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

//放弃处理
function doNoExecute(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("请选择要放弃处理在线报案信息！");
		return;
	}
	if (count > 1) {
		alert("只能选择一个在线报案信息进行操作！");
		return;
	}
	if(geIdStatus()[1][0] != "1"){
		alert("请选择“未处理”状态的记录");
		return;
	}
	window.open("${ctx}/business/businessManage/claimService/findRegeist.do?geGegist.registNo=" + geIdStatus()[0],"编辑用户" ,"top=100, left=100, width=900,height=600,toolbar=no");
}

//处理完成
function doFinish(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("请选择要处理的投诉信息！");
		return;
	}
	var statusArr = geIdStatus()[1];
	for(var i = 0;i < statusArr.length;i++){
		if(statusArr[i] != "1"){
			alert("请选择“未处理”状态的记录");
			return;
		}
	}
	if(confirm("确定处理完成您选中的"+count+"个用户吗？")){
		window.parent.frames[4].location.href = "${ctx}/business/businessManage/claimService/finishRegeist.do?idStr=" + geIdStatus()[0];
	}
}
//重新分发
function doRedistribute(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("请选择要重新分发的投诉信息！");
		return;
	}
	if (count > 1) {
		alert("只能选择一个投诉信息进行操作！");
		return;
	}
	if(geIdStatus()[1][0] != "2"){
		alert("请选择“退回”状态的记录");
		return;
	}
	
	window.open("${ctx}/business/businessManage/claimService/queryRegeistForRedistribute.do?geGegist.registNo=" + geIdStatus()[0],"编辑用户" ,"top=100, left=100, width=900,height=600,toolbar=no");		
}

//回退
function doRollback(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("请选择要回退的投诉信息！");
		return;
	}
	
	var statusArr = geIdStatus()[1];
	var handleArr = geIdStatus()[2];
	for(var i = 0;i < statusArr.length;i++){
		if(statusArr[i] != "1"){
			alert("请选择“未处理”状态的记录");
			return;
		}
		if(handleArr[i] == "1999999"){
			alert("不可以进行回退操作！");
			return;
		}
	}
	if(confirm("确定回退您选中的"+count+"个用户吗？")){
		window.parent.frames[4].location.href = "${ctx}/business/businessManage/claimService/regeistForRollback.do?idStr=" + geIdStatus()[0];
	}
}


//特殊处理end
</script>
</HTML>
