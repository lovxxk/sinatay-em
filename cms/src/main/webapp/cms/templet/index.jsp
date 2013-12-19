<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
String nodeID = request.getParameter("nodeID") == null ? "" : request.getParameter("nodeID").trim();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>样式管理</title>
</head>
<frameset id="fsTOP" rows="50,*" border="0" frameBorder="0" frameSpacing="0">
	<frame id="fraHEAD" name="fraHEAD" src="${ctx}/templetManage/toHead.do?nodeID=<%=nodeID %>" frameBorder="0" scrolling="no" noResize>
	<frame id="fraBODYS" name="fraBODYS" src="${ctx}/welcome.jsp" frameBorder="0" scrolling="yes" noResize>
</frameset>
</html>
