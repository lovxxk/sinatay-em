<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>�༭��������</title>
</head>
<body onload="pageValidate();">
<div style="padding-left:15px;padding-right:15px;clear:both;">
	<form action="${ctx}/business/businessManage/serviceNetwork/updateServiceNetwork.do" method="post" id="frmInput" target="myFrame">
	<table class="table_style " width="100%;" align="center" cellspacing="0" height="90%">
		<tr>
			<td class="td_head" nowrap>���ͣ�</td>
			<td class="td_body" >
				${geStationInfo.type == 'SX'?"����":(geStationInfo.type == 'CX'?"����":(geStationInfo.type == 'JG'?"������":""))}
			</td>
		</tr>
		<tr>
			<td class="td_head" width="100px;" nowrap>�����������ƣ�</td>
			<td class="td_body"><input name="geStationInfo.unitName"  value="${geStationInfo.unitName}" style="width:170px;"></td>
		</tr>
		<tr style="display: none;">
			<td class="td_head" nowrap>����������룺</td>
			<td class="td_body" ><input name="geStationInfo.obid" value="${geStationInfo.obid}" style="width:170px;" readonly></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>������λ��</td>
			<td class="td_body" ><input name="geStationInfo.corpName" value="${geStationInfo.corpName}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>���������ַ��</td>
			<td class="td_body" ><input name="geStationInfo.address" value="${geStationInfo.address}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�ʱࣺ</td>
			<td class="td_body" ><input name="geStationInfo.zipCode" id="zipCode" value="${geStationInfo.zipCode}" style="width:170px;" maxlength=6></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�������ߣ�</td>
			<td class="td_body" ><input name="geStationInfo.telePhone" id="telePhone" value="${geStationInfo.telePhone}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>���棺</td>
			<td class="td_body" ><input name="geStationInfo.fax" id="fax" value="${geStationInfo.fax}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�������䣺</td>
			<td class="td_body" ><input name="geStationInfo.email" id="email" value="${geStationInfo.email}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��Ҫ��</td>
			<td class="td_body" ><input name="geStationInfo.principal" id="principal" value="${geStationInfo.principal}" style="width:170px;"></td>
		</tr>
		<tr style="display:none;">
			<td class="td_head" nowrap>ʡ/�У�</td>
			<td class="td_body" ><input name="geStationInfo.provinceName" value="${geStationInfo.provinceName}" style="width:170px;" readonly></td>
		</tr>
		<tr style="display:none;">
			<td class="td_head" nowrap>��/����</td>
			<td class="td_body" ><input name="geStationInfo.cityName" value="${geStationInfo.cityName}" style="width:170px;" readonly></td>
		</tr>
		<tr style="display:none;">
			<td class="td_head" nowrap>�أ�</td>
			<td class="td_body" ><input name="geStationInfo.townName" value="${geStationInfo.townName}" style="width:170px;" readonly></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����ʱ�䣺</td>
			<td class="td_body" >
				<textarea name="geStationInfo.businessTime" id="businessTime" style="width:300px;height:60px;">${geStationInfo.businessTime}</textarea>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����Χ��</td>
			<td class="td_body" >
				<textarea name="geStationInfo.businessRange" id="businessRange" style="width:300px;height:60px;">${geStationInfo.businessRange}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan=2>
				<table width=100 align="center">
				<tr>
					<td id="createButton" align=right class="btnBigOnFocus"  onclick="doUpdate();" nowrap>�޸� </td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnFocus" align=right onclick="doClear();" nowrap>����</td>
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
	new tt.RV().set(new RegExp("^[0-9]{6}$"), "ֻ����������,�ҳ���Ϊ6λ").add("geStationInfo.zipCode");
	//new tt.RV().set(new RegExp("^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$"),"��������ȷ�Ĵ������").add("geStationInfo.fax");

}
</script>
</html>