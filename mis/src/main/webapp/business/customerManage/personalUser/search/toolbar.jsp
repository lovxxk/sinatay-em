<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
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
	//编辑
	function doEdit(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要编辑的客户！");
			return;
		}
		if (count > 1) {
			alert("只能选择一个客户进行操作！");
			return;
		}
		window.open("${ctx }/business/customerManage/personalUser/queryGeUserPersonalForUpdateOrShow.do?handle=update&geUserPersonal.userID=" + idStr.split(',')[0],"编辑客户" ,"top=50, left=100, width=800,height=600,toolbar=no,scrollbars=yes");		
	}
	
	//保单解绑
	function doUnBoundPolicy(){
		
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要解绑保单的客户！");
			return;
		}
		if (count > 1) {
			alert("只能选择一个客户进行操作！");
			return;
		}
		window.open("${ctx}/business/customerManage/personalUser/unbound/proOrPen/index.jsp?userID=" + idStr.split(',')[0] + "&userName=" + idStr.split(',')[1],"保单解绑" ,"top=100, left=100, width=900,height=600,toolbar=no");
	}
	
	//服务卡解绑
	function doUnBoundCard(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要解绑服务卡的客户！");
			return;
		}
		if (count > 1) {
			alert("只能选择一个客户进行操作！");
			return;
		}
		alert("服务卡解绑成功！");
		//window.open("${ctx }/business/customerManage/personalUser/queryGeUserPersonalForUpdate.do?geUserPersonal.userID=" + idStr,"编辑客户" ,"top=50, left=100, width=850,height=600,toolbar=no");		
	}
	//特殊处理end
</script>
</HTML>
