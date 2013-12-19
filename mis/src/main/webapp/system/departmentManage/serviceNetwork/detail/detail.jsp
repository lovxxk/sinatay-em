<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>服务网点详细</title>
</head>
<body onload="pageValidate();">
<div style="padding-left:15px;padding-right:15px;clear:both;">
	<form action="${ctx}/business/businessManage/serviceNetwork/updateServiceNetwork.do" method="post" id="frmInput" target="myFrame">
	<table class="table_style" width="100%" align="center" cellspacing="0" height="90%">
		<tr>
			<td class="td_head" width="100px;" nowrap>类型：</td>
			<td class="td_body" >
				${geStationInfo.type == 'SX'?"寿险":(geStationInfo.type == 'CX'?"财险":(geStationInfo.type == 'JG'?"养老险":""))}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务网点代码：</td>
			<td class="td_body" >${geStationInfo.obid}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务网点名称：</td>
			<td class="td_body">${geStationInfo.unitName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>所属单位：</td>
			<td class="td_body" >${geStationInfo.corpName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务网点地址：</td>
			<td class="td_body" >${geStationInfo.address}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>邮编：</td>
			<td class="td_body" >${geStationInfo.zipCode}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务热线：</td>
			<td class="td_body" >${geStationInfo.telePhone}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>传真：</td>
			<td class="td_body" >${geStationInfo.fax}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>电子邮箱：</td>
			<td class="td_body" >${geStationInfo.email}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>主要：</td>
			<td class="td_body" >${geStationInfo.principal}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>省/市：</td>
			<td class="td_body" >${geStationInfo.provinceName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>市/区：</td>
			<td class="td_body" >${geStationInfo.cityName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>县：</td>
			<td class="td_body" >${geStationInfo.townName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务时间：</td>
			<td class="td_body" >
				<textarea name="geStationInfo.businessTime" id="businessTime" style="width:300px;height:60px;" readonly="readonly">${geStationInfo.businessTime}</textarea>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>服务范围：</td>
			<td class="td_body" >
				<textarea name="geStationInfo.businessRange" id="businessRange" style="width:300px;height:60px;" readonly="readonly">${geStationInfo.businessRange}</textarea>
			</td>
		</tr>
			<tr height=25><td></td></tr> 
		<tr>
			<td colspan=2>
				<table width=180 align="center">
				<tr>
					<!--<td id="createButton" align=right class="btnBigOnFocus"  onclick="doCreate();" nowrap>新增 </td>
					<td width=5>&nbsp;</td>
					--><td id="resetButton" class="btnBigOnFocus" align=right onclick="doEdit('${geStationInfo.obid}');" nowrap>编辑</td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnFocus" align=right onclick="doDelete('${geStationInfo.obid}');" nowrap>删除</td>
				</tr>
				</table>
			</td>
		</tr>
	</table> 
	<input type="hidden" name="geStationInfo.province" value="${geStationInfo.province}" style="width:170px;"/>
	<input type="hidden" name="geStationInfo.city" value="${geStationInfo.city}" style="width:170px;" />
	<input type="hidden" name="geStationInfo.town" value="${geStationInfo.town}" style="width:170px;" />
	<input type="hidden" name="geStationInfo.type" value="${geStationInfo.type}" style="width:170px;" />
	<input type="hidden" name="geStationInfo.deptid"  value="${geStationInfo.deptid}" style="width:170px;">
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
</body>
<script type="text/javascript">

function pageValidate(){
	tt.vf.req.add("geStationInfo.unitName");
	tt.vf.postcode.add("geStationInfo.zipCode");   
	tt.vf.tel.add("geStationInfo.telePhone");  
	tt.vf.email.add("geStationInfo.email"); 
	new tt.RV().set(new RegExp("^[0-9]{6}$"), "只能输入数字,且长度为6位").add("geStationInfo.zipCode");
	//new tt.RV().set(new RegExp("^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$"),"请输入正确的传真号码").add("geStationInfo.fax");

}
//新增
function doCreate(){
	top.document.getElementById("operate").src = "${ctx}/system/departmentManage/serviceNetwork/create/frmCreate.jsp?deptid=${geStationInfo.deptid}&city=${geStationInfo.city}&cityName=${geStationInfo.cityName}&province=${geStationInfo.province}&provinceName=${geStationInfo.provinceName}";
}
//编辑
function doEdit(obid){
	top.document.getElementById("operate").src = "${ctx}/business/businessManage/serviceNetwork/findServiceNetworkForUpdate.do?geStationInfo.obid=" + obid;
}
//删除
function doDelete(obid){
	if(confirm("确定删除您该服务网点吗？")){
		top.document.getElementById("fraHidden").src = "${ctx}/business/businessManage/serviceNetwork/deleteServiceNetwork.do?deptid=${geStationInfo.deptid}&city=${geStationInfo.city}&cityName=${geStationInfo.cityName}&province=${geStationInfo.province}&provinceName=${geStationInfo.provinceName}&idStr=" + obid;
	}
}
</script>
</html>