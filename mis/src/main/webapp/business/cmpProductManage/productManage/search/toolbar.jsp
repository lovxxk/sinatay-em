<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	
	<style type="text/css">
		.toolbar_normal_2{height:27px;color:#536c94;font-weight:bold;text-align:center;padding-left:0px;padding-right:0px;}
	</style>
</head>
<body> 
	<div id="toolbar_DIV">
		<table id="toolbar_main" cellpadding="0" cellspacing="0">
			<tr>
				<c:if test="${'welcome' != param.fm}">
					<td onclick="javascript:maximizeGrid(this);">&nbsp;</td>
				</c:if>
				<c:if test="${'welcome' == param.fm}">
					<td id="rem">&nbsp;</td>
				</c:if>
				
				
				<acc:showView source="ROLE_B_CRPR_L">
				<td onclick="doEditFlow();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">���̶���</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_CRPR_X">
				<td onclick="doEditDetail();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">��ϸ����</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_CRPR_H">
				<td onclick="doAudit();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">���</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_CRPR_F">
				<td onclick="doPublish();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">����</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_CRPR_D">
				<td onclick="doDelete();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">ɾ��</td>
				<td>&nbsp;</td>
				</acc:showView>
			</tr>
		</table>
	</div>
	
	<input type="hidden" name="taskID" id="taskID">
	<input type="hidden" name="workFlowID" id="workFlowID">
	
	<input type="hidden" name="idStr" id="idStr" />
	<input type="hidden" name="count" id="count" />
	<input type="hidden" name="status" id="status" />
	<input type="hidden" name="geProductMain.coreProductCode" />
	<input type="hidden" name="geProductMain.productName" />
	<input type="hidden" name="geProductMain.businessArea" />
</body>
<script type="text/javascript">
var fm = "${param.fm}";

	//��ʼ��toolbar��ʽ
	var toolbarTable = document.getElementById("toolbar_main");
	var toolbarRow = toolbarTable.rows[0];
	toolbarRow.className = "toolbar_bg";
	var tds = toolbarRow.cells;
	for(var i = 0; i < tds.length; i++){
		if(i == 0){
			if("welcome" != fm){
				tds[i].className = "toolbar_up";
				tds[i].title = "�������ݱ�";
			}else{
				tds[i].className = "toolbar_normal_2";
			}
			
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
	if("welcome" != fm){
		//$("#rem").removeClass("toolbar_up");
		
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
			//top.frames[1].frames[2].frames[2].window.checkSingleRow();
			idStr = document.getElementById("idStr").value;
			count = document.getElementById("count").value;
			status = document.getElementById("status").value;
			
			if(count<=0){
				alert("��ѡ��Ҫ�����ļ�¼��");
				return false;
			}
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
	//�༭����
	function doEditFlow(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�������̵Ĳ�Ʒ��");
			return;
		}
		if (count > 1) {
			alert("ֻ�ܶԵ�����Ϣ���в�����");
			return;
		}
		if(status=='03'){
			alert("��Ʒ�����,�����ٴζ������̣�");
			return;
		}
		if(status=='04'){
			alert("��Ʒ�Ѿ����,�����ٴζ������̣�");
			return;
		}
		var windowWidth = window.screen.width;
		var windowHeight = window.screen.height;
		window.open("${ctx}/productManage/findProductWebFlow.do?coreProductCode=" + idStr,"�����Ʒ��������" ,"top=0, left=0,scrollbars,resizable=yes, width=" + windowWidth + " ,height = " + windowHeight + ",toolbar=no");
	}
	
	// ��ϸ����
	function doEditDetail(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ��ϸ���õĲ�Ʒ��");
			return;
		}
		if (count > 1) {
			alert("ֻ�ܶԵ�����Ϣ���в�����");
			return;
		}
		if(status=='03'){
			alert("��Ʒ�����,�����ٴζ����Ʒ��ϸ��");
			return;
		}
		if(status=='04'){
			alert("��Ʒ�Ѿ����,�����ٴζ����Ʒ��ϸ��");
			return;
		}
		if (status == "02" || status=='05') {
			var windowWidth = window.screen.width;
			var windowHeight = window.screen.height;
			window.open("${ctx}/productManage/findProductWebFlowElement.do?coreProductCode=" + idStr, "��Ʒ��ϸ����", "top=0, left=0, resizable=yes, width=" + windowWidth + " ,height = " + windowHeight + "");
		} else {
			alert("���ȶ����Ʒ���̣�");
			return;
		}
	}
	
	// ���
	function doAudit(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ��˵Ĳ�Ʒ��");
			return;
		}
		if (count > 1) {
			alert("ֻ�ܶԵ�����Ϣ���в�����");
			return;
		}
		
		if(status=='04'||status=='05'){
			alert("��Ʒ�Ѿ���˻����Ѿ�����,�����ٴζ��������ˣ�");
			return;
		}
		if(status=='03'){
			// ��ѯ������ˡ�����״̬�ſ��Բ���
			var search_ststus = parent.frames[2].document.getElementById("curr_search_status").value;
			window.open("${ctx}/productManage/toAudit.do?geProductMain.coreProductCode=" + idStr+"&taskID="+$("#taskID").val()+"&workFlowID="+$("#workFlowID").val(),"��Ʒ��ϸ","top=100, left=100,width=1000,,height=750,toolbar=no,scrollbars=yes");
// 			if(search_ststus=='03'){
// 			}else{
// 				alert("���ѯ��ϸ����״̬֮�������˲�����");
// 			}
		}else{
			alert("������ϸ�����Ʒ");
			return;	
		}
	}
	
	function doPublish(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�����Ĳ�Ʒ��");
			return;
		}
		if (count > 1) {
			alert("ֻ�ܶԵ�����Ϣ���в�����");
			return;
		}
		if(status=='05'){
			alert("��Ʒ�Ѿ���������ƷĿ¼��");
			return;
		}
		if(status=='04'){
			// ��ѯ������ˡ�����״̬�ſ��Բ���
			var search_ststus = parent.frames[2].document.getElementById("curr_search_status").value;
			window.open("${ctx}/productManage/toPublish.do?geProductMain.coreProductCode=" + idStr +"&taskID="+$("#taskID").val()+"&workFlowID="+$("#workFlowID").val(),"��Ʒ��ϸ","top=100, left=100,width=1000,,height=750,toolbar=no,scrollbars=yes");
// 			if(search_ststus=='04'){
// 			}else{
// 				alert("���ѯ���״̬֮�������˲�����");
// 			}
		}else{
			alert("������˲�Ʒ");
			return;
		}
	}
	
	//ɾ�������ӹ��������Ƴ���
	function doDelete(){
		if(!getId()){
			return;
		}
		if(count == 0) {
			alert("��ѡ��Ҫɾ���Ĳ�Ʒ��");
			return;
		}
		if (count > 1) {
			alert("ֻ�ܶԵ�����Ϣ���в�����");
			return;
		}
		if(status=='04' || status=="05"){
			alert("��Ʒ�Ѿ���˻����Ѿ�����,����ɾ����");
			return;
		}
		if(confirm("ȷ��ɾ����ѡ�еĴ˲�Ʒ��")){
			$.ajax({
				url:contextRootPath+"/productManage/deleteOneProduct.do?taskID="+$("#taskID").val(),
				data:"geProductMain.coreProductCode=" + idStr,
				async:false,
				dataType:"text",
				success:function(data){
					alert("ɾ���ɹ���");
					document.getElementById("count").value = 0;
					window.parent.frames[0].document.getElementById("frmInput").submit();
				}
			});
			
		}
	}
	
	//���⴦��end
</script>
</html>
