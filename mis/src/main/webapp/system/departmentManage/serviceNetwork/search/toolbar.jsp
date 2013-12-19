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
				<td onclick="doCreate();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">����</td>
				<!--
				<td>&nbsp;</td>
				<td onclick="doEdit();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">�༭</td>
				<td>&nbsp;</td>
				<td onclick="doDelete();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">ɾ��</td>
				<td>&nbsp;</td>
				<td onclick="doDetail();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">��ϸ</td>
				<td>&nbsp;</td>
			--></tr>
		</table>
	</div>
	<input type="hidden" name="idStr" id="idStr">
	<input type="hidden" name="count" id="count">
	<input type="hidden" name="status" id="status">
</body>
<script language="javascript"><!--
//��ʼ��toolbar��ʽ
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
//�ı�toolbar��ʽ
function changeTD_style(obj){
	if(obj.className == "toolbar_normal"){
		obj.className = "toolbar_hover";
	}else{
		obj.className = "toolbar_normal";
	}
}

//��������start
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
		alert("��ѡ��Ҫ�����ļ�¼��");
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
//��������end

//���⴦���湦�ܲ�ͬ����ͬ
//����3��Ԫ�ص����飬����','���ӵ�id��status����,handle����
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

//�༭
function doEdit(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("��ѡ��Ҫ�༭�ķ������㣡");
		return;
	}
	if (count > 1) {
		alert("ֻ��ѡ��һ������������б༭��");
		return;
	}
	geIdStatus();
	top.document.getElementById("operate").src = "${ctx}/business/businessManage/serviceNetwork/findServiceNetworkForUpdate.do?geStationInfo.obid=" + geIdStatus()[0];
}
//����
function doCreate(){
	top.document.getElementById("operate").src = "${ctx}/system/departmentManage/serviceNetwork/create/frmCreate.jsp?deptid=${param.deptid}&city=${param.city}&cityName=${param.cityName}&province=${param.province}&provinceName=${param.provinceName}";
}
//ɾ��
function doDelete(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("��ѡ��Ҫɾ���ķ������㣡");
		return;
	}
	if(confirm("ȷ��ɾ����ѡ�е�"+count+"������������")){
		top.document.getElementById("fraHidden").src = "${ctx}/business/businessManage/serviceNetwork/deleteServiceNetwork.do?idStr=" + idStr;
	}
}
//��ϸ
function doDetail(){
	if(!getId()){
		return;
	}
	if (count == 0) {
		alert("��ѡ��Ҫ�鿴�ķ������㣡");
		return;
	}
	if (count > 1) {
		alert("ֻ��ѡ��һ������������в鿴��");
		return;
	}
	geIdStatus();
	top.document.getElementById("operate").src = "${ctx}/business/businessManage/serviceNetwork/findServiceNetworkForDetail.do?geStationInfo.obid=" + geIdStatus()[0];

}
//���⴦��end
</script>
</HTML>
