<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>Ȩ����ϸ��Ϣ</title>
</head>
<body>
<div style="padding:10px 0px 0px 5px;">
	<table align="center" style="font-size:9pt;width:400px;" cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td class="td_head" width="65px" nowrap>Ȩ�ޱ�ţ�</td>
			<td class="td_body" nowrap>
				${geUserAuthority.authorityID}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>Ȩ�����ƣ�</td>
			<td  class="td_body" nowrap>
				${geUserAuthority.authorityName}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�������ͣ�</td>
			<td  class="td_body" nowrap>
				${geUserAuthority.authorityType=="02"?"����":"������"}
			</td>
		</tr>
		<c:if test="${geUserAuthority.authorityType=='02'}">
		<tr>
			<td class="td_head" nowrap>���ӵ�ַ��</td>
			<td  class="td_body" nowrap>
				${geUserAuthority.authorityLink}
			</td>
		</tr>
		</c:if>
		<tr>
			<td class="td_head" nowrap>Ȩ��������</td>
			<td  class="td_body" nowrap>
				${geUserAuthority.authorityDesp}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>Ȩ����ţ�</td>
			<td  class="td_body" nowrap>
				${geUserAuthority.authorityorder}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>Ȩ�޲㼶��</td>
			<td  class="td_body" nowrap>
				${geUserAuthority.authorityLevel}
			</td>
		</tr>
	</table>
</div>
</body>
</html>