<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<title>����վ</title>
</head>
<body>
<table width="100%" cellpadding="0" cellspacing="0" border="0" class="table_Show">
	<tr>
		<td class="td_head" width="50" nowrap>���</td>
		<td class="td_head" width="50" nowrap><input type="checkbox" id="checkAll" title="ȫ��ѡ��" onclick="javascript:checkAllorders();"></td>
		<td class="td_head"  nowrap>��ʾ����</td>
		<td class="td_head"  nowrap>��Ŀ����</td>
		<td class="td_head" width="100" nowrap>��Ŀ����</td>
		<td class="td_head" width="50" nowrap>����</td>
	</tr>
	<tr id="tr" >
		<td class="td_head"></td>
		<td class="td_head" ><input type="checkbox" id="check" onclick="window.parent.fraToolbar.checkID('',',this.checked);"></td>
		<td class="td_body" nowrap>&nbsp;</td>
		<td class="td_body" nowrap>&nbsp;</td>
		<td class="td_body" nowrap>&nbsp;</td>
		<td class="td_body" nowrap ><input type="button" value="��ԭ" onclick="doRestore()">&nbsp;</td>
	</tr>
</table>
</body>
</html>
