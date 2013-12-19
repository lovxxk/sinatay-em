<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>CMS管理系统</title>
</head>
<frameset id="fraBODY" cols="170,19,*" border="0" frameBorder="0" frameSpacing="0">
	<frame id="fraLEFT" name="fraLEFT" src="leftPanel.jsp" frameBorder="0" scrolling="yes" noResize>
	<frame id="fraSWITCH" name="fraSWITCH" src="${ctx}/global/ui/switchV.jsp" frameBorder="0" scrolling="no" noResize>
	<frame id="fraMAIN" name="fraMAIN" src="${ctx}/welcome.jsp" frameBorder="0" scrolling="auto" noResize>
</frameset>
</html>
