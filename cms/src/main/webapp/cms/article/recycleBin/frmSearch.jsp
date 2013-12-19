<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${ctx}/cms/global/css/GUI.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/form.css">
<title>frmSearch.jsp</title>
</head>
<body bgcolor="#dadada" topmargin=0>
<input id="reset" type="reset" style="display:none;">
	<table border=0 cellpadding=0 width=100% cellspacing=2 style="font-size:9pt;line-height:16pt;">
		<input name="nodeID" value="" type="hidden">
		<tr>
			<td width=50%>
				
				<table width=100% border=0 style="font-size:9pt;">
					<tr>
						<td width="80%"></td>
						<td nowrap><input type="hidden" name="pageIndex"  id="pageIndex" value="1">√ø“≥œ‘ æ<input size=3 value="20" id="pageSize" name="pageSize">œÓ</td>
						<td align=left class="btnBigOnFocus" onclick="javascript:doSearch();" nowrap>GO</td>
					</tr>
				</table>
		</tr>
	</table>
	<input type="hidden" name="status" id="status">
	<input id="reset" type="reset" style="display:none;">
	<input id="submit" type="submit" style="display:none;">

<iframe style="display:none" width=200 height=200 name="hiddenFrame">

</body>
</html>
