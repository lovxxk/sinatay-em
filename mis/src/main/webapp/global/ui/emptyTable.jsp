<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>电子商务管理系统-空白表格</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
</head>
<body topmargin="5" leftmargin="2" bgcolor="WHITE">
<table width="100%" cellpadding="0" cellspacing="0" border="0" class="table_Show">
<c:forEach begin="0" end="9" step="1" varStatus="status">
	<tr class="${status.index%2 == 0?'':'search_tr_ou'}">
		<c:forEach begin="0" end="7">
			<td class="search_body">&nbsp;</td>
		</c:forEach>
	</tr>
</c:forEach>
</table>
</body>
</html>