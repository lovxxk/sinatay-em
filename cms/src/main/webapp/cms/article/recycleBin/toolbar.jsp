<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link href="${ctx}/cms/global/css/GUI.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/table.css">
<title>toolbar.jsp</title>
</head>
<body bgcolor="ButtonFace" leftmargin="0" topmargin="0">
<form id="frmINPUT">
<table id="toolTable" cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:9pt;" background="../../global/images/toolbar_bg.jpg">
	<tr align=center>
		<td onclick="javascript:maximizeGrid()" nowrap title="�������ݱ�" style="CURSOR: hand;"><img id="point" src="../../global/images/toolbar_ico_up.jpg" border="0"></td>
		<td><img src="../../global/images/1trans.gif" width=2></td>
		<td id="tabindex1" tabindex="1" width=75 onmouseover="this.className='toolbarOnFocus'" onmouseout="this.className='toolbarOnBlur'" onclick="javascript:doDelete();" class="toolbarOnBlur" nowrap>ɾ��</td>
		<td width=8><img src="../../global/images/right_top_se.jpg"></td>
		<td id="tabindex2" tabindex="2" width=75 onmouseover="this.className='toolbarOnFocus'" onmouseout="this.className='toolbarOnBlur'" onclick="javascript:doRestore();" class="toolbarOnBlur" nowrap>��ԭ</td>
		<td width=8><img src="../../global/images/right_top_se.jpg"></td>
		<td width="70%" nowrap align=right></td><td width="30%"><div id="divPage"></div></td>
	</tr>
</table>
</form>
<input type=hidden name="ID" id="ID">
<input type=hidden name="IDCount" id="IDCount">
<input type=hidden name="activeID" id="activeID">
</BODY>
</HTML>
