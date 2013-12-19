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
	<link href="<%=request.getContextPath()%>/global/css/misBasic.css" rel="stylesheet" type="text/css">
</HEAD>
<body>
	<div id="toolbar_DIV">
		<table id="toolbar_main" cellpadding="0" cellspacing="0">
			<tr>
				<td onclick="javascript:maximizeGrid(this);">
					&nbsp;
				</td>
				<!--  
				<acc:showView source="ROLE_S_ROLE_U">
				<td onclick="doEdit();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">�༭</td>
				<td>&nbsp;</td>
				</acc:showView>
				<td onclick="showDetail();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">��ϸ</td>
				<td>&nbsp;</td>
				<acc:showView source="ROLE_S_GROUP_M_US">
				<td onclick="userSet();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">�û�����</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_S_GROUP_M_AS">
				<td onclick="authoritySet();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">Ȩ������</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_S_ROLE_U">
					<td onclick="doDelete();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">ɾ��</td>
				<td>&nbsp;</td>
				</acc:showView>
				-->
			</tr>
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
	var tds = toolbarRow.cells;
	for(var i = 0; i < tds.length; i++){
		if(i == 0){
			tds[i].className = "toolbar_up";
			tds[i].title = "�������ݱ�";
		}else if(i % 2 == 0){
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
		if(className == "toolbar_up"){//����
			obj.className = "toolbar_down";
			top.frames[1].frames[2].document.getElementById("fraTop").rows='0,30,*,40,0';
			obj.title = "��ԭ���ݱ�";
		}else{//����
			obj.className = "toolbar_up";
			top.frames[1].frames[2].document.getElementById("fraTop").rows=defaultRows;
			obj.title = "�������ݱ�";
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
			alert("��ѡ��Ҫ�����ļ�¼��");
			return false;
		}
	}
	
	function getIdStatus(){
		var idStatusList = idStr.split(",");
		var ids = "";
		var deptid = new Array(idStatusList.length-1);
		for(var i = 0;i < idStatusList.length;i++){
			var idStatus = idStatusList[i].split("|");
			ids += "," + idStatus[0];
			deptid[i] = idStatus[1];
		}
		return new Array(ids.substr(1),deptid);
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
	function userSet(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�༭���û��飡");
			return;
		}
		if (count > 1) {
			alert("ֻ��ѡ��һ���û�����в�����");
			return;
		}
		if(getIdStatus()[1]!=${geOperator.deptid}){
			alert("�����ԶԸ��û�������û����ã�");
			return;
		}
		window.open("${ctx}/system/groupManage/queryGeGroupOperatorsForUpdate.do?id="+getIdStatus()[0],"�û�����" ,"top=100, left=300, width=900,height=600,toolbar=no,scrollbars");	
	}
	//��ɫ��������
	function authoritySet(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�༭���û��飡");
			return;
		}
		if (count > 1) {
			alert("ֻ��ѡ��һ���û�����в�����");
			return;
		}
		if(getIdStatus()[1]!=${geOperator.deptid}){
			alert("�����ԶԸ��û������Ȩ�����ã�");
			return;
		}
		window.open("${ctx}/system/groupManage/queryGeGroupAuthorityForUpdate.do?odeptid="+${geOperator.deptid}+"&id="+getIdStatus()[0],"Ȩ������" ,"top=100, left=200, width=950,height=600,toolbar=no,scrollbars");		
	}

	//�༭
	function doEdit(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�༭���û��飡");
			return;
		}
		if (count > 1) {
			alert("ֻ��ѡ��һ���û�����в�����");
			return;
		}
		if(getIdStatus()[1]!=${geOperator.deptid}){
			alert("�����Ա༭���û��飡");
			return;
		}
		window.open("${ctx}/system/groupManage/queryGeGroupForUpdate.do?deptid=${geOperator.deptid}&id=" + getIdStatus()[0],"�༭�û���" ,"top=100, left=100, width=900,height=600,toolbar=no");		
	}
	//�鿴��ϸ
	function showDetail(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�鿴���û��飡");
			return;
		}
		if (count > 1) {
			alert("ֻ��ѡ��һ���û�����в�����");
			return;
		}
		window.open("${ctx}/system/groupManage/queryGeGroupDetail.do?id=" + getIdStatus()[0],"�鿴�û���" ,"top=100, left=100, width=900,height=600,toolbar=no,scrollbars=yes");		
	}
	
	//ɾ��
	function doDelete(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫɾ�����û��飡");
			return;
		}
		for(var i = 0;i < getIdStatus()[1].length;i++){
			if(getIdStatus()[1][i]!=${geOperator.deptid}){
				alert("������ɾ����ѡ���û��飡");
				return;
			}
		}
		if(confirm("ȷ��ɾ����ѡ�е�"+count+"���û�����")){
			window.parent.frames[4].location.href = "${ctx}/system/groupManage/delete.do?idStr=" + getIdStatus()[0];
		}
	}
	//���⴦��end
--></script>
</HTML>
