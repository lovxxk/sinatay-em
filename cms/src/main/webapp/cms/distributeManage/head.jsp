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
<script type="text/javascript">
function doPub(){
	parent.document.getElementById("fraBODYS").src="${ctx}/distributeManage/goPub.do?nodeID=<%= nodeID %>";
}

function doPubAll(){
	alert("发布该栏目");
		//document.all.getAPFrame.src="createD/publicAll.jsp?ID";
	}
</script>
</head>
<body topmargin="0" leftmargin="0">
<div class="cms_header_top_bg">
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick = "doPub();">发布该栏目</div>
<div class="cms_header_top_space"></div>
<!--  
<div class="cms_header_top_button" onclick = "doPubAll();">发布该栏目和子栏目</div>
<div class="cms_header_top_space"></div>
-->
</div>
</body>

</html>
