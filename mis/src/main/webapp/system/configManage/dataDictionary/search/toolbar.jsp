<%@ page language="java" contentType="text/html;charset=GBK"%>
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
				<!--<acc:showView source="ROLE_S_USER_U">
				<td onclick="doEdit();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">编辑</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_S_USER_D">
				<td onclick="doDelete();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">删除</td>
				<td>&nbsp;</td>
				</acc:showView>
				<td onclick="doDetail();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">详细</td>
				<td>&nbsp;</td>
			--></tr>
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
			alert("请选择要编辑的用户！");
			return;
		}
		if (count > 1) {
			alert("只能选择一个用户进行操作！");
			return;
		}
		window.open("<%=request.getContextPath()%>/system/userManage/queryGeOperatorForUpdate.do?geOperator.operatorid=" + idStr,"编辑用户" ,"top=100, left=100, width=900,height=600,toolbar=no");		
	}
	
	//删除
	function doDelete(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要删除的用户！");
			return;
		}
		if(confirm("确定删除您选中的"+count+"个用户吗？")){
			window.parent.frames[4].location.href = "<%=request.getContextPath() %>/system/userManage/deleteGeOperator.do?idStr=" + idStr;
		}
	}
	
	//详细
	function doDetail(){
		if(!getId()){
			return;
		}
		if (count == 0) {
			alert("请选择要查看的用户！");
			return;
		}
		if (count > 1) {
			alert("只能选择一个用户进行操作！");
			return;
		}
		window.open("<%=request.getContextPath()%>/system/userManage/queryGeOperatorForDetail.do?geOperator.operatorid=" + idStr,"查看用户" ,"top=100, left=100, width=800,height=550,toolbar=no,scrollbars=yes");		
	}
	//特殊处理end
</script>
</HTML>
