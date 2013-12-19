<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>权限详细信息</title>
</head>
<body>
<div style="padding:10px 0px 0px 5px">
	<table align="center" style="font-size:9pt;width:400px;" cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td class="td_head" width="65px" nowrap>权限编号：</td>
			<td class="td_body">
				${geEnterpriseAuthority.authorityID}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>权限名称：</td>
			<td  class="td_body">
				${geEnterpriseAuthority.authorityName}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>链接类型：</td>
			<td  class="td_body">
				${geEnterpriseAuthority.authorityType=="02"?"链接":"非连接"}
			</td>
		</tr>
		<c:if test="${geEnterpriseAuthority.authorityType=='02'}">
		<tr>
			<td class="td_head" nowrap>链接地址：</td>
			<td  class="td_body">
				${geEnterpriseAuthority.authorityLink}
			</td>
		</tr>
		</c:if>
		<tr>
			<td class="td_head" nowrap>权限描述：</td>
			<td  class="td_body">
				${geEnterpriseAuthority.authorityDesp}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>权限序号：</td>
			<td  class="td_body">
				${geEnterpriseAuthority.authorityorder}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>权限层级：</td>
			<td  class="td_body">
				${geEnterpriseAuthority.authorityLevel}
			</td>
		</tr>
	</table>
</div>
</body>
</html>