<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>����������ϸ</title>
</head>
<body onload="pageValidate();">
<div style="padding-left:15px;padding-right:15px;clear:both;">
	<form action="${ctx}/business/businessManage/serviceNetwork/updateServiceNetwork.do" method="post" id="frmInput" target="myFrame">
	<table class="table_style" width="100%" align="left" cellspacing="0" height="90%">
		<tr>
			<td class="td_head" width="150px;" nowrap>�������ƣ�</td>
			<td class="td_body" >${geDeptAttribute.geDepartment.deptname}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�������ƣ�</td>
			<td class="td_body">${geDeptAttribute.geDeptInfo.attrName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����ֵ��</td>
			<td class="td_body" >${geDeptAttribute.attrValue}</td>
		</tr>
			<tr height=5><td></td></tr> 
		<tr>
			<td colspan=2>
				<table width=180 align="center">
				<tr>
					<!--<td id="createButton" align=right class="btnBigOnFocus"  onclick="doCreate();" nowrap>���� </td>
					<td width=5>&nbsp;</td>
					--><td id="resetButton" class="btnBigOnFocus" align=right onclick="doEdit('${geDeptAttribute.deptattrid}');" nowrap>�༭</td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnFocus" align=right onclick="doDelete('${geDeptAttribute.deptattrid}');" nowrap>ɾ��</td>
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
	new tt.RV().set(new RegExp("^[0-9]{6}$"), "ֻ����������,�ҳ���Ϊ6λ").add("geStationInfo.zipCode");
	//new tt.RV().set(new RegExp("^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$"),"��������ȷ�Ĵ������").add("geStationInfo.fax");

}
//����
function doCreate(){
	top.document.getElementById("operate").src = "${ctx}/system/departmentManage/serviceNetwork/create/frmCreate.jsp?deptid=${geStationInfo.deptid}&city=${geStationInfo.city}&cityName=${geStationInfo.cityName}&province=${geStationInfo.province}&provinceName=${geStationInfo.provinceName}";
}
//�༭
function doEdit(obid){
	top.document.getElementById("operate").src = "${ctx}/system/deptAttribute/findDeptAttributeForUpdate.do?geDeptAttribute.deptattrid=" + obid;
}
//ɾ��
function doDelete(obid){
	if(confirm("ȷ��ɾ������������")){
		top.document.getElementById("fraHidden").src = "${ctx}/system/deptAttribute/deleteDeptAttribute.do?deptname=${geDeptAttribute.geDepartment.deptname}&deptid=${geDeptAttribute.geDepartment.deptid}&geDeptAttribute.deptattrid=" + obid;
	}
}
</script>
</html>