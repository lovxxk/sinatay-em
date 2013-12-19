<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<title>服务网点详细信息</title>
</head>
<body>
<div style="padding-top:15px;padding-left:15px;width:100%;font-size:14px;font-weight:bold;text-align:left;clear:both;">&gt;&gt;&nbsp;服务网点详细信息</div>
<div style="padding-left:15px;clear:both;">
	<table class="table_style" width="600px;">
		<tr>
			<td class="td_head" nowrap>类型</td>
			<td class="td_body" >
				${geStationInfo.type == 'SX'?"寿险":(geStationInfo.type == 'CX'?"财险":(geStationInfo.type == 'JG'?"养老险":""))}
			</td>
		</tr>
		<tr>
			<td class="td_head" width="120px;" nowrap>服务网点名称</td>
			<td class="td_body">${geStationInfo.unitName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务网点代码</td>
			<td class="td_body" >${geStationInfo.obid}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>所属单位</td>
			<td class="td_body" >${geStationInfo.corpName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务网点地址</td>
			<td class="td_body" >${geStationInfo.address}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>邮编</td>
			<td class="td_body" >${geStationInfo.zipCode}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务热线</td>
			<td class="td_body" >${geStationInfo.telePhone}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>传真</td>
			<td class="td_body" >${geStationInfo.fax}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>电子邮箱</td>
			<td class="td_body" >${geStationInfo.email}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>主要</td>
			<td class="td_body" >${geStationInfo.principal}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>省/市</td>
			<td class="td_body" >${geStationInfo.provinceName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>市/区</td>
			<td class="td_body" >${geStationInfo.cityName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>县</td>
			<td class="td_body" >${geStationInfo.townName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务时间</td>
			<td class="td_body" >${geStationInfo.businessTime}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务范围</td>
			<td class="td_body" >${geStationInfo.businessRange}</td>
		</tr>
		<tr height=25><td></td></tr> 
	</table> 
</div>
</body>
</html>