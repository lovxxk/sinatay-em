<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/table.css">
<title>search.jsp</title>
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" cellpadding="0" cellspacing="0" border="0" class="table_Show">
	<tr>
		<td class="td_head" width="50" align="center" nowrap>序号</td>
		<td class="td_head" width="25" align=center><input type="checkbox" id="checkAll" title="全部选中" onclick="javascript:checkAllorders();"></td>
		<td class="td_head" nowrap>文章标题</td>
		<td class="td_head" width="200" nowrap>文章关键字</td>		
		<td class="td_head" width="100" nowrap>文章类型</td>
		<td class="td_head" width="100" nowrap>文章状态</td>
		<td class="td_head" width="100" nowrap>文章创建日期</td>
		<td class="td_head" width="100" nowrap>文章撰写时间</td>
	</tr>
</table>
</body>
</html>
