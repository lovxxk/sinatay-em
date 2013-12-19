<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String nodeID = request.getParameter("nodeID") == null ? "" : request.getParameter("nodeID").trim();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>CMS管理系统</title>
</head>
	<frameset id="fraBODY" rows="27,*" border="0">
		<frame id="fraHEAD" name="fraHEAD" src="head.jsp?nodeID=<%=nodeID%>" frameBorder="0" scrolling="no" noResize>
		<frame id="fraBODYS" name="fraBODYS" src="${ctx}/welcome.jsp" frameBorder="0" scrolling="no" noResize>
	</frameset>
</html>
