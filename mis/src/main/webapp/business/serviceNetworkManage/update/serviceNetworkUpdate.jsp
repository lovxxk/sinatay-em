<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:set var="geFunctionSwitch"
	value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet"
	href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript"
	src="${ctx}/global/js/validate/talent-validate-all-min.js"
	charset="UTF-8"></script>
<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/global/js/jquery-1.6.2.min.js" charset="utf-8"></script>
<title>���������̨����ϵͳ-�༭��������</title>
<style type="text/css">
span.talentErrMsg {
	padding-left: 17px;
}
</style>
</head>
<body>
	<
	<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">�༭��������</div>
		</div>
		<div class="header_title_gap1"></div>
	</div>
	<div class="table_content">
		<div style="clear: both;"></div>
		<form action="${ctx}/serviceNetwork/updateServiceNetwork.do"
			id="networkFm" method="post">
			<table class="table_style" align="center" style="width: 500px"
				id="networkTable">
				<tr>
					<td height=10>&nbsp;</td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>ʡ��</td>
					<td class="td_body" width="390px"><input type="hidden"
						id="serialNo" name="network.serialNo" maxlength="32"
						value="${network.serialNo}" style="width: 160px;" />
						${network.province} <input id="province" name="network.province"
						maxlength="32" value="${network.province}"
						style="display: none; width: 160px;" /></td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>�У�</td>
					<td class="td_body" width="320px">${network.city} <input
						id="city" name="network.city" type="text" value="${network.city}"
						style="display: none; width: 215px;" />
					</td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>����������룺</td>
					<td class="td_body" width="280px"><input id="networkManagecom"
						name="network.manageCom" type="text" value="${network.manageCom}"
						style="width: 240px;" maxlength=100 /></td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>����������ƣ�</td>
					<td class="td_body" width="380px"><input
						id="networkManageName" name="network.manageName" type="text"
						value="${network.manageName}" style="width: 240px;" maxlength=100 /></td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>���ȼ���</td>
					<td class="td_body" width="380px"><input id="networkSort"
						name="network.sort" type="text" value="${network.sort}"
						style="width: 240px;" maxlength=100 /></td>
				</tr>

				<tr>
					<td class="td_head" width="120px" nowrap>��ַ��</td>
					<td class="td_body" width="380px"><input id="networkAddress"
						name="network.address" type="text" value="${network.address}"
						style="width: 240px;" maxlength=200 /></td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>�绰��</td>
					<td class="td_body" width="380px"><input id="networkPhone"
						name="network.phone" type="text" value="${network.phone}"
						style="width: 240px;" maxlength=200 /></td>
				</tr>

				<tr>
					<td class="td_head" width="120px" nowrap>�������ͣ�</td>
					<td class="td_body" width="380px"><select name="network.type"
						id="networkType" class="input_text">
							<option value="1" style="width: 120px;" maxlength=380>1-�ͷ���������</option>
					</select></td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>�����Ǽ���</td>
					<td class="td_body" width="380px"><select name="network.grade"
						style="width: 115px;" maxlength=380 id="networkGrade"
						class="input_text">
							<option value="${network.grade }" id="formerType">--��ѡ��--</option>
							<option value="1">һ��</option>
							<option value="2">����</option>
							<option value="3">����</option>
							<option value="4">����</option>
							<option value="5">����</option>
					</select></td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>����ʱ�䣺</td>
					<td class="td_body" width="380px"><input id="networkWorktime"
						name="network.workTime" type="text" value="${network.workTime}"
						style="width: 240px;" maxlength=200 /></td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>γ�ȣ�</td>
					<td class="td_body" width="380px"><input id="networkLongItude"
						name="network.longItude" type="text" value="${network.longItude}"
						style="width: 240px;" maxlength=200 /></td>
				</tr>
				<tr>
					<td class="td_head" width="120px" nowrap>���ȣ�</td>
					<td class="td_body" width="380px"><input id="networkLatItude"
						name="network.latItude" type="text" value="${network.latItude}"
						style="width: 240px;" maxlength=200 /></td>
				</tr>
				<tr height=35>
					<td></td>
				</tr>
				<tr>
					<td colspan=2>
						<table width=200 align="center">
							<tr>
								<td class="btnBigOnBlur"
									onmouseover="this.className='btnBigOnFocus'"
									onmouseout="this.className='btnBigOnBlur'"
									onclick="goToView();" nowrap>����</td>

								<td class="btnBigOnBlur"
									onmouseover="this.className='btnBigOnFocus'"
									onmouseout="this.className='btnBigOnBlur'"
									onclick="networkFmSubmit();" nowrap>�޸�</td>
								<td id="updateButton" align=right class="btnBigOnBlur"
									onclick="javascript:doClear();" nowrap>����</td>
								<td class="btnBigOnBlur"
									onmouseover="this.className='btnBigOnFocus'"
									onmouseout="this.className='btnBigOnBlur'"
									onclick="javascript:window.close();" nowrap>�ر�</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
				new tt.LV().set(0, 32).add("network.province");
				new tt.LV().set(0, 32).add("network.city");
				new tt.LV().set(0, 100).add("network.manageCom");
				new tt.LV().set(0, 100).add("network.manageName");
				new tt.LV().set(0, 200).add("network.address");
				new tt.LV().set(0, 100).add("network.sort");

				tt.vf.req.add("network.province");
				tt.vf.req.add("network.city");
				tt.vf.req.add("network.manageName");
				tt.vf.req.add("network.manageCom");
				tt.vf.req.add("network.sort");
				
				var testProvince = new tt.RV().set(new RegExp(
						"^[\u4E00-\u9FA5]*$"), "�Բ���ֻ�����뺺�֣�");
				testProvince.add("network.province");
				var testCity = new tt.RV().set(
						new RegExp("^[\u4E00-\u9FA5]*$"), "�Բ���ֻ�����뺺�֣�");
				testCity.add("network.city");
				var sortVal = new tt.RV().set(new RegExp("^\\d+$"), "�Բ���ֻ������Ǹ�����");
				sortVal.add("network.sort");
			
				var manageComVal = new tt.RV().set(new RegExp("^\\d+$"), "�Բ���ֻ������Ǹ�����");
				manageComVal.add("network.manageCom");
				
				//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.eid");
				//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.productQuoteCode");
				
				 var grade=$("#formerType").val();
				 if(grade=="1"){
					 $("#formerType").text("һ��");
				 }else if(grade=="2"){
					 $("#formerType").text("����"); 
				 }else if(grade=="3"){
					 $("#formerType").text("����"); 
				 }else if(grade=="4"){
					 $("#formerType").text("����");
				 }else{
					 $("#formerType").text("����");
				 }
			
			})
	function networkFmSubmit() {
		if (tt.validate()) {
			document.getElementById("networkFm").submit();
		}
	}

	function doClear() {
		document.getElementById("networkFm").reset();
	}

	function goToView() {
		window.location.href = "${ctx}/serviceNetwork/viewServiceNetwork.do?network.serialNo="
				+ '${network.serialNo}';

	}
</script>
</html>
