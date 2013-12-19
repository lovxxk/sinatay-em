<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>机构详细信息</title>
</head>
<body>
<div style="padding-top:10px;clear:both;">
	<table border=0 style="font-size:9pt;width:300px" cellpadding=0 cellspacing=0 align="center">
		<tr>
			<td class="td_head" width="65px;" nowrap>区域编号：</td>
			<td class="td_body" nowrap>
				${geAreaAuthority.gid}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>区域名称：</td>
			<td  class="td_body" nowrap>
				${geAreaAuthority.gname}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>区域等级：</td>
			<td  class="td_body" nowrap>
				${geAreaAuthority.glevel}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>备注信息：</td>
			<td  class="td_body" nowrap>
				${geAreaAuthority.note}
			</td>
		</tr>
	</table>
</div>
</body>
</html>