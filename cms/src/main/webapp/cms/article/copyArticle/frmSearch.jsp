<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${ctx}/cms/global/css/GUI.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<title>查找文章</title>
</head>
<body topmargin=0 leftmargin=0>
<center>
<table border="0" style="font-size:9pt;" width=99% cellpadding="0" cellspacing="0" class="table_Show">
	<tr>
		<td class="tdleft" width=10% nowrap height=30 align="center">文档类型</td>
		<td class="tdright" width=20%>
			<select name="docType" id="docType">
				<option value="">请选择</option>
				<option value="0">普通文章</option>
				<option value="1">链接文章</option>
				<option value="2">附件文章</option>
			</select></td>
		<td class="tdleft" width=10% nowrap align="center">文章标题</td>
		<td class = "tdright" width=20%><input name="docTitle" size=20></td>
		<td class="tdleft" width=10% nowrap align="center">文章关键字</td>
		<td class = "tdright" width=20%><input name="docKey" size=20></td>
	</tr>
	<tr>
		<td height=20 colspan=5></td>
	</tr>
</table>
<table border=0 style="font-size:9pt;" width=90% cellpadding=2 cellspacing=2 >
	<tr>
		<td width=70%></td>
		<td nowrap><input type="hidden" name="pageIndex" id="pageIndex" value="1">每页显示<input size=3 value=20 id=pageSize name=pageSize >项</td>
		<td class="btnBigOnFocus" onclick="javascript:doSearch();" nowrap>查找</td>
		<td class="btnBigOnFocus" align=right onclick="javascript:doClear();" nowrap>清空</td>
	</tr>
<input id="reset" type="reset" style="display:none;">
<input type=hidden name="nodeID" id="nodeID" value="">
</table>
</center>
</body>
</html>
