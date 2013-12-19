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
					<td  onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);" onclick="doSend();">赠送</td>
					<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_TPSG_M_Z">
					<td onclick="doBack();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">回退</td>
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
	//自定义
	var customerState = "";
	var operatorCount = "";
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
	//查看
	function doSend(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要赠送的商品！");
			return;
		}
		//序号N已经注销过，成为无效状态，请去除勾选
		var result="";
		var temps = idStr.split(",");
		var customerID = "";
		
		for(var i=0;i<temps.length;i++){
			var objs = temps[i].split("@");
			customerID = customerID+objs[0]+",";
			if(objs[1]=="1"){//已经有效状态
				result=result+objs[2]+",";
			}
		}
		if(result!==""){
			//将最后一个","去掉
			result = result.substring(0,result.lastIndexOf(","))
			var results = result.split(",");
			var display = "";
			for(var i=0;i<results.length;i++){
				display = display+" 序号 "+results[i]+" ";
			}
			alert(display+"已经赠送过，请去除勾选");
			return null;
		}
		
		if(confirm("确定赠送您选中的"+count+"个商品记录吗？")){
			customerID =customerID.substring(0, customerID.lastIndexOf(","));
			window.parent.frames[4].location.href = "${ctx }/party/updateGeThirdParterSerialNumberValidInd.do?searialNo=" + customerID;
		}
	}
	
	function doBack(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要回退的商品！");
			return;
		}
		//序号N已经注销过，成为无效状态，请去除勾选
		var result="";
		var temps = idStr.split(",");
		var customerID = "";
		
		for(var i=0;i<temps.length;i++){
			var objs = temps[i].split("@");
			customerID = customerID+objs[0]+",";
			if(objs[1]=="0"){//已经无效状态
				result=result+objs[2]+",";
			}
		}
		if(result!==""){
			//将最后一个","去掉
			result = result.substring(0,result.lastIndexOf(","))
			var results = result.split(",");
			var display = "";
			for(var i=0;i<results.length;i++){
				display = display+" 序号 "+results[i]+" ";
			}
			alert(display+"已经无效，请去除勾选");
			return null;
		}
		
		if(confirm("确定回退您选中的"+count+"个商品记录吗？")){
			customerID =customerID.substring(0, customerID.lastIndexOf(","));
			window.parent.frames[4].location.href = "${ctx }/party/updateGeThirdParterSerialNumberInValidInd.do?searialNo=" + customerID;
		}
	}
	/*
	//删除
	function doDelete(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要的产品！");
			return;
		}
		if(confirm("确定删除您选中的"+count+"个黑名单记录吗？")){
			window.parent.frames[4].location.href = "${ctx }/business/customerManage/blacklist/delete.do?idStr=" + idStr;
		}
	}
	*/ 
	//特殊处理end
</script>
</HTML>
