<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>发布管理</title>
<%
String nodeID = request.getParameter("nodeID") == null ? "" : request.getParameter("nodeID").trim();
%>
</head>
	<frameset id="fraBODY" rows="50,*" border="0">
		<frame name="fraHEAD" src="head.jsp?nodeID=<%= nodeID %>" frameborder="0" scrolling="no" noresize>
		<frame name="fraBODYS" src="${ctx}/welcome.jsp" noresize>
	</frameset>
</html>
