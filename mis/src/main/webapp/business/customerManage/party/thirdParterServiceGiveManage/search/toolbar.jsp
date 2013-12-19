<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
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
				<td onclick="javascript:maximizeGrid(this);">&nbsp;</td>
				<acc:showView source="ROLE_B_TPSG_M_Z">
					<td  onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);" onclick="doSend();">����</td>
					<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_TPSG_M_Z">
					<td onclick="doBack();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">����</td>
					<td>&nbsp;</td>
				</acc:showView>
			</tr>
		</table>
	</div>
	<input type="hidden" name="customerState" id="idCustomerState">
	<input type="hidden" name="idStr" id="idStr">
	<input type="hidden" name="count" id="count">
	<input type="hidden" name="status" id="status">
	<input type="hidden" name="operatorCount" id="operatorCountId">
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
	//�Զ���
	var customerState = "";
	var operatorCount = "";
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
	//�鿴
	function doSend(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ���͵���Ʒ��");
			return;
		}
		//���N�Ѿ�ע��������Ϊ��Ч״̬����ȥ����ѡ
		var result="";
		var temps = idStr.split(",");
		var customerID = "";
		
		for(var i=0;i<temps.length;i++){
			var objs = temps[i].split("@");
			customerID = customerID+objs[0]+",";
			if(objs[1]=="1"){//�Ѿ���Ч״̬
				result=result+objs[2]+",";
			}
		}
		if(result!==""){
			//�����һ��","ȥ��
			result = result.substring(0,result.lastIndexOf(","))
			var results = result.split(",");
			var display = "";
			for(var i=0;i<results.length;i++){
				display = display+" ��� "+results[i]+" ";
			}
			alert(display+"�Ѿ����͹�����ȥ����ѡ");
			return null;
		}
		
		if(confirm("ȷ��������ѡ�е�"+count+"����Ʒ��¼��")){
			customerID =customerID.substring(0, customerID.lastIndexOf(","));
			window.parent.frames[4].location.href = "${ctx }/party/updateGeThirdParterSerialNumberValidInd.do?searialNo=" + customerID;
		}
	}
	
	function doBack(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ���˵���Ʒ��");
			return;
		}
		//���N�Ѿ�ע��������Ϊ��Ч״̬����ȥ����ѡ
		var result="";
		var temps = idStr.split(",");
		var customerID = "";
		
		for(var i=0;i<temps.length;i++){
			var objs = temps[i].split("@");
			customerID = customerID+objs[0]+",";
			if(objs[1]=="0"){//�Ѿ���Ч״̬
				result=result+objs[2]+",";
			}
		}
		if(result!==""){
			//�����һ��","ȥ��
			result = result.substring(0,result.lastIndexOf(","))
			var results = result.split(",");
			var display = "";
			for(var i=0;i<results.length;i++){
				display = display+" ��� "+results[i]+" ";
			}
			alert(display+"�Ѿ���Ч����ȥ����ѡ");
			return null;
		}
		
		if(confirm("ȷ��������ѡ�е�"+count+"����Ʒ��¼��")){
			customerID =customerID.substring(0, customerID.lastIndexOf(","));
			window.parent.frames[4].location.href = "${ctx }/party/updateGeThirdParterSerialNumberInValidInd.do?searialNo=" + customerID;
		}
	}
	/*
	//ɾ��
	function doDelete(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("��ѡ��Ҫ�Ĳ�Ʒ��");
			return;
		}
		if(confirm("ȷ��ɾ����ѡ�е�"+count+"����������¼��")){
			window.parent.frames[4].location.href = "${ctx }/business/customerManage/blacklist/delete.do?idStr=" + idStr;
		}
	}
	*/ 
	//���⴦��end
</script>
</HTML>
