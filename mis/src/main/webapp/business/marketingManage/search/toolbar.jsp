<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</HEAD>
<body>
	<div id="toolbar_DIV">
		<table id="toolbar_main" cellpadding="0" cellspacing="0">
			<tr>
				<c:if test="${'welcome' != param.fm}">
					<td onclick="javascript:maximizeGrid(this);">
					&nbsp;&nbsp;
					</td>
				</c:if>
			</tr>
		</table>
	</div>
	<input type="hidden" name="idStr" id="idStr">
	<input type="hidden" name="count" id="count">
	<input type="hidden" name="status" id="status">
</body>
<script language="javascript">
	//��ʼ��toolbar��ʽ
	var toolbarTable = document.getElementById("toolbar_main");
	var toolbarRow = toolbarTable.rows[0];
	toolbarRow.className = "toolbar_bg";
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
	if(top.frames[1].frames[2]!=undefined){
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
	//�༭
	function doEdit(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�༭�Ļ��");
			return;
		}
		if (count > 1) {
			alert("ֻ��ѡ��һ������в�����");
			return;
		}
		//window.open("${ctx}/marketing/prepareUpdateAddGeAddServiceActivity.do?activityId=" + idStr,"�༭�û�","top=100, left=100, width=1100,height=600,toolbar=no,menubar=no,scrollbars=yes");
		window.open("${ctx}/marketing/prepareUpdateAddGeAddServiceActivity.do?activityId=" + idStr);
		
	}
	function doView(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ��ѯ�Ļ��");
			return;
		}
		if (count > 1) {
			alert("ֻ��ѡ��һ������в�����");
			return;
		}
		window.open("${ctx}/marketing/view.do?activityId=" + idStr,"��ѯ�","top=100, left=100, width=1100,height=600,toolbar=no,menubar=no,scrollbars=yes");
	}
	//ɾ��
	function doDelete(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫɾ���Ļ��");
			return;
		}
		if(confirm("ȷ��ɾ����ѡ�е�"+count+"�����¼��")){
			window.parent.frames[4].location.href = "${ctx}/marketing/deleteAddGeAddServiceActivity.do?activityId=" + idStr;
		}
	}
	//���⴦��end
</script>
</HTML>