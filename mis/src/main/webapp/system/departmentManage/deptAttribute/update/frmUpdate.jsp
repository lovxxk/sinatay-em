<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>编辑服务网点</title>
</head>
<body onload="pageValidate();">
<div style="padding-left:15px;padding-right:15px;clear:both;">
	<form action="${ctx}/system/deptAttribute/updateDeptAttribute.do" method="post" id="frmInput" target="myFrame">
	<table class="table_style " width="100%;" align="center" cellspacing="0" height="90%">
		<tr>
			<td class="td_head" width="100px;" nowrap>机构名称：</td>
			<td class="td_body">
				${geDeptAttribute.geDepartment.deptname}
				<input name="geDeptAttribute.deptattrid" type="hidden" value="${geDeptAttribute.deptattrid}" style="width:170px;">
				<input name="geDeptAttribute.geDepartment.deptid" type="hidden" value="${geDeptAttribute.geDepartment.deptid}" style="width:170px;">
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>属性名称：</td>
			<td class="td_body" >
				<input name="geDeptAttribute.geDeptInfo.attrID" id="attrID" type="hidden"  value="${geDeptAttribute.geDeptInfo.attrID}" style="width:170px;" readonly>
				<input id="attrName" value="${geDeptAttribute.geDeptInfo.attrName}"  readonly style="width:170px;">
				<input type="button" onclick="openDeptInfo();" value="关联属性">
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>属性值：</td>
			<td class="td_body" ><input name="geDeptAttribute.attrValue" value="${geDeptAttribute.attrValue}" style="width:170px;"></td>
		</tr>
		<tr>
			<td colspan=2>
				<table width=100 align="center">
				<tr>
					<td id="createButton" align=right class="btnBigOnFocus"  onclick="doUpdate();" nowrap>修改 </td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnFocus" align=right onclick="doClear();" nowrap>重置</td>
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
function doUpdate(){
	if($("#attrID").val()==""){
		alert("请选择关联属性！");
		return;
	}
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function pageValidate(){
	tt.vf.req.add("geStationInfo.unitName");
	tt.vf.postcode.add("geStationInfo.zipCode");   
	tt.vf.tel.add("geStationInfo.telePhone");  
	tt.vf.email.add("geStationInfo.email"); 
	new tt.RV().set(new RegExp("^[0-9]{6}$"), "只能输入数字,且长度为6位").add("geStationInfo.zipCode");
	//new tt.RV().set(new RegExp("^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$"),"请输入正确的传真号码").add("geStationInfo.fax");

}
function openDeptInfo(){
	window.open("${ctx}/system/departmentManage/deptAttribute/deptInfo/index.jsp","查询机构属性" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
</script>
</html>