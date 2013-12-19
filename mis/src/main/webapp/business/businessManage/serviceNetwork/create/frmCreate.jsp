<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<title>�½���������</title>
</head>
<body onload="pageValidate();">
<div style="padding-top:15px;padding-left:15px;width:100%;font-size:14px;font-weight:bold;text-align:left;clear:both;">&gt;&gt;&nbsp;�½���������</div>
<div style="padding-left:15px;clear:both;">
	<form action="${ctx}/business/businessManage/serviceNetwork/saveServiceNetwork.do" method="post" id="frmInput" target="myFrame">
	<table class="table_style" width="600px;">
		<tr>
			<td class="td_head" nowrap>����</td>
			<td class="td_body" >
				${param.businessArea == 'SX'?"����":(param.businessArea == 'CX'?"����":(param.businessArea == 'JG'?"������":""))}
			</td>
		</tr>
		<tr>
			<td class="td_head" width="120px;" nowrap>������������</td>
			<td class="td_body"><input name="geStationInfo.unitName"  value="${geStationInfo.unitName}" style="width:170px;"></td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�����������</td>
			<td class="td_body" ><input name="geStationInfo.obid" value="${id}" style="width:170px;"></td>
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
			<td class="td_body" ><input name="geStationInfo.zipCode" id="zipCode" maxlength="6" value="${geStationInfo.zipCode}" style="width:170px;"></td>
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
		
		<!--<c:if test="${empty geStationProvinceCico2}">
		<tr>
			<td class="td_head" nowrap>ʡ/��</td>
			<td class="td_body" ><input name="geStationInfo.provinceName" value="${geStationProvinceCico1.name}" style="width:170px;" readonly>
								 <input type="hidden" name="geStationInfo.province" value="${geStationProvinceCico1.obid}" style="width:170px;" readonly>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��/��</td>
			<td class="td_body" ><input name="geStationInfo.cityName" value="${geStationProvinceCico.name}" style="width:170px;" readonly>
								 <input type="hidden" name="geStationInfo.city" value="${geStationProvinceCico.obid}" style="width:170px;" readonly>
			</td>
		</tr>
		</c:if>
		<c:if test="${!empty geStationProvinceCico2}">
		<tr>
			<td class="td_head" nowrap>ʡ/��</td>
			<td class="td_body" ><input name="geStationInfo.provinceName" value="${geStationProvinceCico2.name}" style="width:170px;" readonly>
								 <input type="text" name="geStationInfo.province" value="${geStationProvinceCico2.obid}" style="width:170px;" readonly>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��/��</td>
			<td class="td_body" ><input name="geStationInfo.cityName" value="${geStationProvinceCico1.name}" style="width:170px;" readonly>
								 <input type="text" name="geStationInfo.city" value="${geStationProvinceCico1.obid}" style="width:170px;" readonly>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>��</td>
			<td class="td_body" ><input name="geStationInfo.townName" value="${geStationProvinceCico.name}" style="width:170px;" readonly>
								 <input type="text" name="geStationInfo.town" value="${geStationProvinceCico.obid}" style="width:170px;" readonly>
			</td>
		</tr>
		</c:if>
		
		--><tr>
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
					<td id="createButton" align=right class="btnBigOnFocus"  onclick="doCreate();" nowrap>���� </td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnFocus" align=right onclick="doClear();" nowrap>����</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" name="geStationInfo.town" value="${param.town}">
	<input type="hidden" name="geStationInfo.city" value="${param.city}">
	<input type="hidden" name="geStationInfo.province" value="${param.province}">
	<input type="hidden" name="geStationInfo.type" value="${param.businessArea}" style="width:170px;" />
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

function getGeCodeType(obj){
	window.open("${ctx}/system/configManage/dataDictionary/create/selectType/index.jsp","��ѯ��������" ,"top=100, left=100, width=900,height=600,toolbar=no");		
}

function pageValidate(){
	tt.vf.req.add("geStationInfo.obid","geStationInfo.unitName");
	tt.vf.num.add("geStationInfo.obid");  
	tt.vf.tel.add("geStationInfo.telePhone");  
	tt.vf.email.add("geStationInfo.email"); 
	new tt.RV().set(new RegExp("^[0-9]{6}$"), "ֻ����������,�ҳ���Ϊ6λ").add("geStationInfo.zipCode");
}
</script>
</html>