<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<%@page import="cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator"%>
<%
	GeOperator geOperator=(GeOperator)request.getSession().getAttribute("geOperator");
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
				<td onclick="doL();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">关联机构</td>
				<td>&nbsp;</td>
				<!--  
				<acc:showView source="ROLE_S_ROLE_U">
				<td onclick="doEdit();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">编辑</td>
				<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_S_ROLE_D">
				<td onclick="doDelete();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">删除</td>
				<td>&nbsp;</td>
				</acc:showView>
				<td onclick="doDetail();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">详细</td>
				<td>&nbsp;</td> 
				-->
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
	var defaultRows = top.document.getElementById("fraTop").rows;
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
			top.frames[2].window.checkSingleRow();
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
	function geIdStatus(){
		var idStatusList = idStr.split(",");
		var ids = "";
		var ruleIdString=new Array(idStatusList.length-1);
		var statusArr = new Array(idStatusList.length-1);
		for(var i = 0;i < idStatusList.length;i++){
			var idStatus = idStatusList[i].split("|");
			ids += "," + idStatus[0];
			statusArr[i] = idStatus[1];
			ruleIdString[i]= idStatus[2];
		}
		return new Array(ids.substr(1),statusArr,ruleIdString);
	}

	//特殊处理end
	function doL(){
		getId();
		var valArr = idStr.split(",");
		if("确定关联该属性?"){
			top.opener.document.getElementById("attrID").value = valArr[0];
			top.opener.document.getElementById("attrName").value = valArr[1];
			top.close();
		}
	}
</script>
</HTML>
