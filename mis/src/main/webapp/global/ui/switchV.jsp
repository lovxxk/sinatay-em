<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>电子商务管理系统-切换页</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
</head>
<body bgcolor="#C0C0C0" background="${ctx}/global/images/switchV_bg.jpg" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
<table id="qq2" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td valign="middle">
		<img id="point" src="${ctx}/global/images/switchV_L.jpg" onclick="javascript:panelSwitch();" style="cursor:pointer;" alt="隐藏">
	</td>
</tr>
</table>
</body>
<script type="text/javascript">
var qq2 = document.getElementById("qq2");
qq2.style.height = document.body.clientHeight;
var panelStatus="open";
function panelSwitch(){
	if (panelStatus=="open")
		closePanel();
	else
		openPanel();
}
function closePanel(){
	panelStatus="close";
	top.frames[1].document.getElementById("fraBODY").cols='0,19,*';
	top.frames[0].document.getElementById("focus_name").style.display="none";
	document.getElementById("point").src="${ctx}/global/images/switchV_R.jpg";
	document.getElementById("point").alt="展开";
}
function openPanel(){
	panelStatus="open";
	top.frames[0].document.getElementById("focus_name").style.display="";
	top.frames[1].document.getElementById("fraBODY").cols='260,19,*';
	document.getElementById("point").src="${ctx}/global/images/switchV_L.jpg";
	document.getElementById("point").alt="隐藏";
}
</script>
</html>