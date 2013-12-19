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
				<td onclick="doEditFlow();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">流程定制</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_CRPR_X">
				<td onclick="doEditDetail();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">详细定义</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_CRPR_H">
				<td onclick="doAudit();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">审核</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_CRPR_F">
				<td onclick="doPublish();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">发布</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_CRPR_D">
				<td onclick="doDelete();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">删除</td>
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

	//初始化toolbar样式
	var toolbarTable = document.getElementById("toolbar_main");
	var toolbarRow = toolbarTable.rows[0];
	toolbarRow.className = "toolbar_bg";
	var tds = toolbarRow.cells;
	for(var i = 0; i < tds.length; i++){
		if(i == 0){
			if("welcome" != fm){
				tds[i].className = "toolbar_up";
				tds[i].title = "扩大数据表";
			}else{
				tds[i].className = "toolbar_normal_2";
			}
			
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
				alert("请选择要操作的记录！");
				return false;
			}
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
	//编辑流程
	function doEditFlow(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要定制流程的产品！");
			return;
		}
		if (count > 1) {
			alert("只能对单条信息进行操作！");
			return;
		}
		if(status=='03'){
			alert("产品审核中,不能再次定义流程！");
			return;
		}
		if(status=='04'){
			alert("产品已经审核,不能再次定义流程！");
			return;
		}
		var windowWidth = window.screen.width;
		var windowHeight = window.screen.height;
		window.open("${ctx}/productManage/findProductWebFlow.do?coreProductCode=" + idStr,"定义产品销售流程" ,"top=0, left=0,scrollbars,resizable=yes, width=" + windowWidth + " ,height = " + windowHeight + ",toolbar=no");
	}
	
	// 详细定义
	function doEditDetail(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要详细配置的产品！");
			return;
		}
		if (count > 1) {
			alert("只能对单条信息进行操作！");
			return;
		}
		if(status=='03'){
			alert("产品审核中,不能再次定义产品明细！");
			return;
		}
		if(status=='04'){
			alert("产品已经审核,不能再次定义产品明细！");
			return;
		}
		if (status == "02" || status=='05') {
			var windowWidth = window.screen.width;
			var windowHeight = window.screen.height;
			window.open("${ctx}/productManage/findProductWebFlowElement.do?coreProductCode=" + idStr, "产品详细定义", "top=0, left=0, resizable=yes, width=" + windowWidth + " ,height = " + windowHeight + "");
		} else {
			alert("请先定义产品流程！");
			return;
		}
	}
	
	// 审核
	function doAudit(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要审核的产品！");
			return;
		}
		if (count > 1) {
			alert("只能对单条信息进行操作！");
			return;
		}
		
		if(status=='04'||status=='05'){
			alert("产品已经审核或者已经发布,不能再次定义进行审核！");
			return;
		}
		if(status=='03'){
			// 查询的是审核、发布状态才可以操作
			var search_ststus = parent.frames[2].document.getElementById("curr_search_status").value;
			window.open("${ctx}/productManage/toAudit.do?geProductMain.coreProductCode=" + idStr+"&taskID="+$("#taskID").val()+"&workFlowID="+$("#workFlowID").val(),"产品详细","top=100, left=100,width=1000,,height=750,toolbar=no,scrollbars=yes");
// 			if(search_ststus=='03'){
// 			}else{
// 				alert("请查询详细定义状态之后再做此操作！");
// 			}
		}else{
			alert("请先详细定义产品");
			return;	
		}
	}
	
	function doPublish(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要发布的产品！");
			return;
		}
		if (count > 1) {
			alert("只能对单条信息进行操作！");
			return;
		}
		if(status=='05'){
			alert("产品已经发布到产品目录！");
			return;
		}
		if(status=='04'){
			// 查询的是审核、发布状态才可以操作
			var search_ststus = parent.frames[2].document.getElementById("curr_search_status").value;
			window.open("${ctx}/productManage/toPublish.do?geProductMain.coreProductCode=" + idStr +"&taskID="+$("#taskID").val()+"&workFlowID="+$("#workFlowID").val(),"产品详细","top=100, left=100,width=1000,,height=750,toolbar=no,scrollbars=yes");
// 			if(search_ststus=='04'){
// 			}else{
// 				alert("请查询审核状态之后再做此操作！");
// 			}
		}else{
			alert("请先审核产品");
			return;
		}
	}
	
	//删除（并从工作流中移除）
	function doDelete(){
		if(!getId()){
			return;
		}
		if(count == 0) {
			alert("请选择要删除的产品！");
			return;
		}
		if (count > 1) {
			alert("只能对单条信息进行操作！");
			return;
		}
		if(status=='04' || status=="05"){
			alert("产品已经审核或者已经发布,不能删除！");
			return;
		}
		if(confirm("确定删除您选中的此产品吗？")){
			$.ajax({
				url:contextRootPath+"/productManage/deleteOneProduct.do?taskID="+$("#taskID").val(),
				data:"geProductMain.coreProductCode=" + idStr,
				async:false,
				dataType:"text",
				success:function(data){
					alert("删除成功！");
					document.getElementById("count").value = 0;
					window.parent.frames[0].document.getElementById("frmInput").submit();
				}
			});
			
		}
	}
	
	//特殊处理end
</script>
</html>
