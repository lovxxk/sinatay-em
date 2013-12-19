<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>电子商务管理系统系统管理</title>
</head>
<frameset id="fraBODY" cols="192,19,*" border="0" frameBorder="0" frameSpacing="0">
	<frame id="fraLEFT" style="overflow-x:hidden;" name="fraLEFT" src="leftPanel.jsp?headMenu=${param.headMenu }" frameBorder="0" scrolling="auto" noResize>
	<frame id="fraSWITCH" name="fraSWITCH" src="${ctx}/global/ui/switchV.jsp" frameBorder="0" scrolling="no" noResize>
	<frame id="fraMAIN" name="fraMAIN" src="${ctx}/welcome.jsp" frameBorder="0" scrolling="auto" noResize>
</frameset>
</html>
