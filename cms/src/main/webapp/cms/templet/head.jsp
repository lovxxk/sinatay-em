<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%
String nodeID = request.getParameter("nodeID") == null ? "" : request.getParameter("nodeID").trim();
%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<title>CMS管理系统</title>
<script>
	function doTemplet(){
		parent.document.getElementById("fraBODYS").src="${ctx}/templetManage/toGetAllTempInfo.do?nodeID=<%=nodeID %>";
	}
	function doUpload(){
		parent.document.getElementById("fraBODYS").src="upload/index.jsp?nodeID=<%=nodeID %>";
	}
	function doCreate(){
		parent.document.getElementById("fraBODYS").src="${ctx}/templetManage/toCreateTpl.do?nodeID=<%=nodeID %>";
	}
</script>
</head>
<body topmargin="0" leftmargin="0">
<div class="cms_header_top_bg">
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick="doTemplet()">栏目样式</div>
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick="doUpload()">管理模板</div>
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick="doCreate()">上传模板</div>
<div class="cms_header_top_space"></div>
</div>
</body>
</html>
