<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>������ϸ��Ϣ</title>
</head>
<body>
<div style="padding-top:10px;clear:both;">
	<table border=0 style="font-size:9pt;width:300px" cellpadding=0 cellspacing=0 align="center">
		<tr>
			<td class="td_head" width="65px;" nowrap>�����ţ�</td>
			<td class="td_body" nowrap>
				${geAreaAuthority.gid}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�������ƣ�</td>
			<td  class="td_body" nowrap>
				${geAreaAuthority.gname}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����ȼ���</td>
			<td  class="td_body" nowrap>
				${geAreaAuthority.glevel}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��ע��Ϣ��</td>
			<td  class="td_body" nowrap>
				${geAreaAuthority.note}
			</td>
		</tr>
	</table>
</div>
</body>
</html>