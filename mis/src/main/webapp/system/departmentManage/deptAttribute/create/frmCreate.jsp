<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<title>新建机构关联</title>
</head>
<body onload="pageValidate();">
<div style="padding-left:15px;padding-right:15px;clear:both;">
	<form action="${ctx}/system/deptAttribute/createDeptAttribute.do" method="post" id="frmInput" target="myFrame">
	<table class="table_style" width="100%" align="center" cellspacing="0" height="95%">
		<tr height="30px;">
			<td class="td_head" nowrap width="100px">机构名称：</td>
			<td class="td_body" >
				${param.deptname}
				<input  type="hidden" name="geDeptAttribute.geDepartment.deptname" value="${param.deptname}" style="width:170px;">
				<input name="geDeptAttribute.geDepartment.deptid" type="hidden" value="${param.deptid}" style="width:170px;">
			</td>
		</tr>
		<tr  height="30px;">
			<td class="td_head"nowrap>属性名称：</td>
			<td class="td_body">
			<input name="geDeptAttribute.geDeptInfo.attrID" type="hidden" id="attrID" value=""  readonly style="width:170px;">
			<input id="attrName" value=""  readonly style="width:170px;">
			<input type="button" onclick="openDeptInfo();" value="关联属性">
			</td>
		</tr>
		<tr  height="30px;">
			<td class="td_head" nowrap>属性值：</td>
			<td class="td_body" ><input name="geDeptAttribute.attrValue" value="" style="width:170px;"></td>
		</tr>
		<tr height=25><td></td></tr> 
		<tr>
			<td colspan=2>
				<table width=180 align="center">
				<tr>
					<td id="createButton" align=right class="btnBigOnFocus"  onclick="doCreate();" nowrap>创建 </td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnFocus" align=right onclick="doClear();" nowrap>重置</td>
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
function doCreate(){
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
	tt.vf.req.add("geStationInfo.obid");
	tt.vf.num.add("geStationInfo.obid");  
	tt.vf.tel.add("geStationInfo.telePhone");  
	tt.vf.email.add("geStationInfo.email"); 
	new tt.RV().set(new RegExp("^[0-9]{6}$"), "只能输入数字,且长度为6位").add("geStationInfo.zipCode");
}
function openDeptInfo(){
	window.open("${ctx}/system/departmentManage/deptAttribute/deptInfo/index.jsp","查询机构属性" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
</script>
</html>