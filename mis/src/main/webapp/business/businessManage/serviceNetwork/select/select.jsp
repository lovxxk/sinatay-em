<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<title>����������ϸ��Ϣ</title>
</head>
<body>
<div style="padding-top:15px;padding-left:15px;width:100%;font-size:14px;font-weight:bold;text-align:left;clear:both;">&gt;&gt;&nbsp;����������ϸ��Ϣ</div>
<div style="padding-left:15px;clear:both;">
	<table class="table_style" width="600px;">
		<tr>
			<td class="td_head" nowrap>����</td>
			<td class="td_body" >
				${geStationInfo.type == 'SX'?"����":(geStationInfo.type == 'CX'?"����":(geStationInfo.type == 'JG'?"������":""))}
			</td>
		</tr>
		<tr>
			<td class="td_head" width="120px;" nowrap>������������</td>
			<td class="td_body">${geStationInfo.unitName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�����������</td>
			<td class="td_body" >${geStationInfo.obid}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>������λ</td>
			<td class="td_body" >${geStationInfo.corpName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>���������ַ</td>
			<td class="td_body" >${geStationInfo.address}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�ʱ�</td>
			<td class="td_body" >${geStationInfo.zipCode}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��������</td>
			<td class="td_body" >${geStationInfo.telePhone}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����</td>
			<td class="td_body" >${geStationInfo.fax}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��������</td>
			<td class="td_body" >${geStationInfo.email}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��Ҫ</td>
			<td class="td_body" >${geStationInfo.principal}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>ʡ/��</td>
			<td class="td_body" >${geStationInfo.provinceName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��/��</td>
			<td class="td_body" >${geStationInfo.cityName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��</td>
			<td class="td_body" >${geStationInfo.townName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����ʱ��</td>
			<td class="td_body" >${geStationInfo.businessTime}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����Χ</td>
			<td class="td_body" >${geStationInfo.businessRange}</td>
		</tr>
		<tr height=25><td></td></tr> 
	</table> 
</div>
</body>
</html>