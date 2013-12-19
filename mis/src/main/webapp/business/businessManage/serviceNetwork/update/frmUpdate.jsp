<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>�༭��������</title>
</head>
<body onload="pageValidate();">
<div style="padding-top:15px;padding-left:15px;width:100%;font-size:14px;font-weight:bold;text-align:left;clear:both;">&gt;&gt;&nbsp;�༭��������</div>
<div style="padding-left:15px;clear:both;">
	<form action="${ctx}/business/businessManage/serviceNetwork/updateServiceNetwork.do" method="post" id="frmInput" target="myFrame">
	<table class="table_style" width="600px;">
		<tr>
			<td class="td_head" nowrap>����</td>
			<td class="td_body" >
				${geStationInfo.type == 'SX'?"����":(geStationInfo.type == 'CX'?"����":(geStationInfo.type == 'JG'?"������":""))}
			</td>
		</tr>
		<tr>
			<td class="td_head" width="120px;" nowrap>������������</td>
			<td class="td_body"><input name="geStationInfo.unitName"  value="${geStationInfo.unitName}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�����������</td>
			<td class="td_body" ><input name="geStationInfo.obid" value="${geStationInfo.obid}" style="width:170px;" readonly></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>������λ</td>
			<td class="td_body" ><input name="geStationInfo.corpName" value="${geStationInfo.corpName}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>���������ַ</td>
			<td class="td_body" ><input name="geStationInfo.address" value="${geStationInfo.address}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�ʱ�</td>
			<td class="td_body" ><input name="geStationInfo.zipCode" id="zipCode" value="${geStationInfo.zipCode}" style="width:170px;" maxlength=6></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��������</td>
			<td class="td_body" ><input name="geStationInfo.telePhone" id="telePhone" value="${geStationInfo.telePhone}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����</td>
			<td class="td_body" ><input name="geStationInfo.fax" id="fax" value="${geStationInfo.fax}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��������</td>
			<td class="td_body" ><input name="geStationInfo.email" id="email" value="${geStationInfo.email}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��Ҫ</td>
			<td class="td_body" ><input name="geStationInfo.principal" id="principal" value="${geStationInfo.principal}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>ʡ/��</td>
			<td class="td_body" ><input name="geStationInfo.provinceName" value="${geStationInfo.provinceName}" style="width:170px;" readonly></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��/��</td>
			<td class="td_body" ><input name="geStationInfo.cityName" value="${geStationInfo.cityName}" style="width:170px;" readonly></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��</td>
			<td class="td_body" ><input name="geStationInfo.townName" value="${geStationInfo.townName}" style="width:170px;" readonly></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����ʱ��</td>
			<td class="td_body" >
				<textarea name="geStationInfo.businessTime" id="businessTime" style="width:470px;height:60px;">${geStationInfo.businessTime}</textarea>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>����Χ</td>
			<td class="td_body" >
				<textarea name="geStationInfo.businessRange" id="businessRange" style="width:470px;height:60px;">${geStationInfo.businessRange}</textarea>
			</td>
		</tr>
		<tr height=25><td></td></tr> 
		<tr>
			<td colspan=2>
				<table width=200 align="center">
				<tr>
					<td id="createButton" align=right class="btnBigOnFocus"  onclick="doCreate();" nowrap>�޸� </td>
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
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
</body>
<script type="text/javascript">
function doCreate(){
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