<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String nodeID = request.getParameter("nodeID") == null ? "" : request.getParameter("nodeID");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>管理模板</title>
</head>
<FRAMESET name="fsSearch" rows="100,28,*,0" border="0" frameborder="0" FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0">
	<FRAME name="fraSearchForm" src="frmSearch.jsp?nodeID=<%=nodeID %>" FRAMEBORDER="0" BORDER="0" scrolling="no" noresize>
	<FRAME name="fraToolbar" src="toolbar.jsp" FRAMEBORDER="0" BORDER="0" scrolling="no" noresize>
	<FRAME id="fraSearchList" name="fraSearchList" src="search.jsp?nodeID=<%=nodeID %>" FRAMEBORDER="0" BORDER="0" scrolling="auto" noresize></FRAME>
	<FRAME name="fraHIDDEN" src="" noresize></FRAME>
</FRAMESET>
</html>
