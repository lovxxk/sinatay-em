<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>发布</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<FRAMESET name="fsSearch" rows="28,*,0" border="0" frameborder="0" FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0">
<!--  
	<FRAME name="frmSearch" src="frmSearch.jsp" FRAMEBORDER="0" BORDER="0" scrolling="no" noresize>
-->
	<FRAME name="fraToolbar" id="fraToolbar" src="${ctx}/cms/distributeManage/pub/toolbar.jsp" FRAMEBORDER="0" BORDER="0" scrolling="no" noresize>
	<FRAME name="fraMain" src="${ctx}/distributeManage/goSearch.do?nodeID=${nodeID}" FRAMEBORDER="0" BORDER="0" scrolling="auto" noresize></FRAME>
	<FRAME name="fraHIDDEN" src="" noresize></FRAME>
</FRAMESET>
</html>
