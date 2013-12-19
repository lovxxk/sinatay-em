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
	<table class="table_style" width="100%" align="left" cellspacing="0" height="90%">
		<tr>
			<td class="td_head" width="150px;" nowrap>机构名称：</td>
			<td class="td_body" >${geDeptAttribute.geDepartment.deptname}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>属性名称：</td>
			<td class="td_body">${geDeptAttribute.geDeptInfo.attrName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>属性值：</td>
			<td class="td_body" >${geDeptAttribute.attrValue}</td>
		</tr>
			<tr height=5><td></td></tr> 
		<tr>
			<td colspan=2>
				<table width=180 align="center">
				<tr>
					<!--<td id="createButton" align=right class="btnBigOnFocus"  onclick="doCreate();" nowrap>新增 </td>
					<td width=5>&nbsp;</td>
					--><td id="resetButton" class="btnBigOnFocus" align=right onclick="doEdit('${geDeptAttribute.deptattrid}');" nowrap>编辑</td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnFocus" align=right onclick="doDelete('${geDeptAttribute.deptattrid}');" nowrap>删除</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
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
	top.document.getElementById("operate").src = "${ctx}/system/deptAttribute/findDeptAttributeForUpdate.do?geDeptAttribute.deptattrid=" + obid;
}
//删除
function doDelete(obid){
	if(confirm("确定删除您该属性吗？")){
		top.document.getElementById("fraHidden").src = "${ctx}/system/deptAttribute/deleteDeptAttribute.do?deptname=${geDeptAttribute.geDepartment.deptname}&deptid=${geDeptAttribute.geDepartment.deptid}&geDeptAttribute.deptattrid=" + obid;
	}
}
</script>
</html>