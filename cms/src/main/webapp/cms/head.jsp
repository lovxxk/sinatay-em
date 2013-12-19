<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
String nodeID = request.getParameter("nodeID") == null ? "" : request.getParameter("nodeID").trim();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
	<title>CMS管理系统</title>
	<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script language="javascript">
	function doColumns(){
		parent.document.getElementById("fraBODYS").src="columnsManage/index.jsp?nodeID=<%=nodeID%>";
	}
	function doArticle(){
		parent.document.getElementById("fraBODYS").src="article/index.jsp?nodeID=<%=nodeID%>";
	}
	function doDistribute(){
		parent.document.getElementById("fraBODYS").src="distributeManage/index.jsp?nodeID=<%=nodeID%>";
	}
	function doTemplet(){
		parent.document.getElementById("fraBODYS").src="templet/index.jsp?nodeID=<%=nodeID%>";
	}		

	function doPrcs() {
		parent.document.getElementById("fraBODYS").src="prcs/index.jsp";
	}	
	</script>
</head>
<body topmargin="0" leftmargin="0">
	<div class="select_header_top_bg">
		<div class="select_header_top_left1"></div>
		<div class="select_header_top_center" onclick="doColumns()">栏目管理</div>
		<div class="select_header_top_right1"></div>
		<div class="select_header_top_center" onclick="doArticle()">文章管理</div>
		<div class="select_header_top_right1"></div>
		<div class="select_header_top_center" onclick="doTemplet()">样式管理</div>
		<div class="select_header_top_right1"></div>
		<div class="select_header_top_center" onclick="doDistribute()">发布管理</div>
		<div class="select_header_top_right1"></div>
	</div>
</body>
</html>
