<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>������ϸ��Ϣ</title>
</head>
<body>
<div style="padding:10px 0px 0px 5px;">	
	<table border=0 style="font-size:9pt;width:400px" cellpadding=0 cellspacing=0 align="center">
		<tr>
			<td class="td_head" width="65px;" nowrap>������ţ�</td>
			<td class="td_body" nowrap>
				${geDepartment.deptid}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�������ƣ�</td>
			<td  class="td_body" nowrap>
				${geDepartment.deptname}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>ҵ������</td>
			<td  class="td_body" nowrap>
				${depAreaName}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����������</td>
			<td  class="td_body" nowrap>
				${depTypeName}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��������</td>
			<td  class="td_body" nowrap>
				${geDepartment.gname}
			</td>
		</tr>
	</table>
</div>
</body>
</html>