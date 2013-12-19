<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
     <script type="text/javascript" language="JavaScript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
	<div id="toolbar_DIV">
		<table id="toolbar_main" cellpadding="0" cellspacing="0">
			<tr>
				<!--<td onclick="javascript:maximizeGrid(this);">&nbsp;</td>
                <acc:showView source="ROLE_BU_P_CARD_X">
				   <td onclick="doView();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">�鿴</td>
				   <td>&nbsp;</td>
                </acc:showView>
                <acc:showView source="ROLE_BU_P_CARD_D">
                   <td onclick="doUpate();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">�༭</td>
                   <td>&nbsp;</td>
                </acc:showView>
                <acc:showView source="ROLE_BU_P_CARD_XX">
      				<td onclick="doDelete();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">ɾ��</td>
      				<td>&nbsp;</td>
                </acc:showView>
			</tr>
		-->
		<td onclick="javascript:maximizeGrid(this);">
					&nbsp;&nbsp;
		</td>
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
		fraSearchForm.doSearch();
	}
	
	function getId(){
		try{
			//top.frames[1].frames[2].frames[2].window.checkSingleRow();
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
	//ɾ��
	function doDelete(){
		if(!getId()){
			return;
		}
		if(count == 0) {
			alert("��ѡ��Ҫɾ�������֣�");
			return;
		}
		if(confirm("ȷ��ɾ����ѡ�е�������")){
			$.ajax({
				url:contextRootPath+"/risk/deleteGeRiskList.do",
				async:false,
				dataType:"json",
				data:{"riskCode": idStr 
				     },
				type:"POST",
				success:function(data){
					alert("ɾ���ɹ���");
					window.parent.frames[0].document.getElementById("frmInput").submit();
				}
			});
		}
	}
	//�鿴
	function doView(){
		
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�鿴�����֣�");
			return;
		}
		if (count > 1) {
			alert("ֻ��ѡ��һ�����֣�");
			return;
		}
	//	alert("�鿴�Ĵ��룺"+idStr);
		window.open("${ctx}/risk/viewGeRisk.do?geRisk.riskCode=" + idStr,"�鿴����" ,"top=100, left=100, width=900,height=600,toolbar=no");
	}
	function doUpate(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�༭�����֣�");
			return;
		}
		if (count > 1) {
			alert("ֻ��ѡ��һ�����֣�");
			return;
		}
	//	alert("���µĴ��룺"+idStr);
		window.open("${ctx}/risk/prepareUpdateGeRisk.do?geRisk.riskCode="+idStr,"�鿴����" ,"top=100, left=100, width=900,height=600,toolbar=no");
	}
	
	//���⴦��end
</script>
</html>
