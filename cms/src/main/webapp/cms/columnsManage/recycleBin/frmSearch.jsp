<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<title>回收站</title>
</head>
<body>
<table width=100% border=0 style="font-size:9pt;">
	<tr>
		<td width="80%"></td>
		<td nowrap><input type="hidden" name="pageIndex"  id="pageIndex" value="1">每页显示<input size=3 value="20" id="pageSize" name="pageSize">项</td>
		<td align=left class="btnBigOnFocus" onclick="javascript:doSearch();" nowrap>GO</td>
	</tr>
</table>
</body>
</html>
