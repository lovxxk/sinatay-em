<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<title>�����������ϵͳ-������������</title>
<script type="text/javascript">
$(document).ready(function(){
	// ������д��Ĵ���...
	init();
	tt.vf.req.add("geProductInsuredConfig.inAgeStart","geProductInsuredConfig.inAgeStartAttr","geProductInsuredConfig.inAgeEnd","geProductInsuredConfig.inAgeEndAttr");
	// ����1����֤
	var NRV = new tt.NRV().set(1, '++');
	NRV.add("geProductInsuredConfig.inAgeStart","geProductInsuredConfig.inAgeEnd");
	//������֤
	tt.vf.int.add("geProductInsuredConfig.inAgeStart","geProductInsuredConfig.inAgeEnd");
	
	//alert(document.getElementById("ageScopeCheckBox").checked);
	if(document.getElementById("ageScopeCheckBox").checked==true){
		document.getElementById("geProductInsuredConfig.inAgeFlag").value = "0";
	}else{
		document.getElementById("geProductInsuredConfig.inAgeFlag").value = "1";
	}
	$("input[type='checkbox']").addClass("checkbox_border");
});

function init(){
	// ������������
	// ��˾�绰 
	var insComPhone = "<s:property value='geProductInsuredConfig.insComPhone' />"
	if(insComPhone==2){
		$("input[id='geProductInsuredConfig.insComPhone']").attr("checked",true);
		document.getElementById("geProductInsuredConfig.insComPhone.value").disabled = false;
		$("input[id='geProductInsuredConfig.insComPhone.value']").attr("checked",true);
		$("input[id='geProductInsuredConfig.insComPhone']").attr("value","2");
	}else if(insComPhone==1){
		$("input[id='geProductInsuredConfig.insComPhone']").attr("checked", true);
		document.getElementById("geProductInsuredConfig.insComPhone.value").disabled = false;
		$("input[id='geProductInsuredConfig.insComPhone']").attr("value","1");
	}
	// ��ͥ�绰
	var insHomePhone = "<s:property value='geProductInsuredConfig.insHomePhone' />"
	if(insHomePhone==2){
		$("input[id='geProductInsuredConfig.insHomePhone']").attr("checked",true);
		document.getElementById("geProductInsuredConfig.insHomePhone.value").disabled = false;
		$("input[id='geProductInsuredConfig.insHomePhone.value']").attr("checked",true);
		$("input[id='geProductInsuredConfig.insHomePhone']").attr("value","2");
	}else if(insHomePhone==1){
		$("input[id='geProductInsuredConfig.insHomePhone']").attr("checked", true);
		document.getElementById("geProductInsuredConfig.insHomePhone.value").disabled = false;
		$("input[id='geProductInsuredConfig.insHomePhone']").attr("value","1");
	}
	// �ƶ��绰
	var insMobilePhone = "<s:property value='geProductInsuredConfig.insMobilePhone' />"
	if(insMobilePhone==2){
		$("input[id='geProductInsuredConfig.insMobilePhone']").attr("checked",true);
		document.getElementById("geProductInsuredConfig.insMobilePhone.value").disabled = false;
		$("input[id='geProductInsuredConfig.insMobilePhone.value']").attr("checked",true);
		$("input[id='geProductInsuredConfig.insMobilePhone']").attr("value","2");
	}else if(insMobilePhone==1){
		$("input[id='geProductInsuredConfig.insMobilePhone']").attr("checked", true);
		document.getElementById("geProductInsuredConfig.insMobilePhone.value").disabled = false;
		$("input[id='geProductInsuredConfig.insMobilePhone']").attr("value","1");
	}
	// ��ϵ��ַ
	var insAddress = "<s:property value='geProductInsuredConfig.insAddress' />"
	if(insAddress==2){
		$("input[id='geProductInsuredConfig.insAddress']").attr("checked",true);
		document.getElementById("geProductInsuredConfig.insAddress.value").disabled = false;
		$("input[id='geProductInsuredConfig.insAddress.value']").attr("checked",true);
		$("input[id='geProductInsuredConfig.insAddress']").attr("value","2");
	}else if(insAddress==1){
		$("input[id='geProductInsuredConfig.insAddress']").attr("checked", true);
		document.getElementById("geProductInsuredConfig.insAddress.value").disabled = false;
		$("input[id='geProductInsuredConfig.insAddress']").attr("value","1");
	}
	// ��������
	var insZipCode = "<s:property value='geProductInsuredConfig.insZipCode' />"
	if(insZipCode==2){
		$("input[id='geProductInsuredConfig.insZipCode']").attr("checked",true);
		document.getElementById("geProductInsuredConfig.insZipCode.value").disabled = false;
		$("input[id='geProductInsuredConfig.insZipCode.value']").attr("checked",true);
		$("input[id='geProductInsuredConfig.insZipCode']").attr("value","2");
	}else if(insZipCode==1){
		$("input[id='geProductInsuredConfig.insZipCode']").attr("checked", true);
		document.getElementById("geProductInsuredConfig.insZipCode.value").disabled = false;
		$("input[id='geProductInsuredConfig.insZipCode']").attr("value","1");
	}
	// ��������
	var insEmail = "<s:property value='geProductInsuredConfig.insEmail' />"
	if(insEmail==2){
		$("input[id='geProductInsuredConfig.insEmail']").attr("checked",true);
		document.getElementById("geProductInsuredConfig.insEmail.value").disabled = false;
		$("input[id='geProductInsuredConfig.insEmail.value']").attr("checked",true);
		$("input[id='geProductInsuredConfig.insEmail']").attr("value","2");
	}else if(insEmail==1){
		$("input[id='geProductInsuredConfig.insEmail']").attr("checked", true);
		document.getElementById("geProductInsuredConfig.insEmail.value").disabled = false;
		$("input[id='geProductInsuredConfig.insEmail']").attr("value","1");
	}
	// ְҵ���
	var insOccupation = "<s:property value='geProductInsuredConfig.insOccupation' />"
	if(insOccupation==2){
		$("input[id='geProductInsuredConfig.insOccupation']").attr("checked",true);
		document.getElementById("geProductInsuredConfig.insOccupation.value").disabled = false;
		$("input[id='geProductInsuredConfig.insOccupation.value']").attr("checked",true);
		$("input[id='geProductInsuredConfig.insOccupation']").attr("value","2");
	}else if(insOccupation==1){
		$("input[id='geProductInsuredConfig.insOccupation']").attr("checked", true);
		document.getElementById("geProductInsuredConfig.insOccupation.value").disabled = false;
		$("input[id='geProductInsuredConfig.insOccupation']").attr("value","1");
	}
	// ��Ͷ���˹�ϵ
	var insRelationToApp = "<s:property value='geProductInsuredConfig.insRelationToApp' />"
	if(insRelationToApp==2){
		$("input[id='geProductInsuredConfig.insRelationToApp']").attr("checked",true);
		document.getElementById("geProductInsuredConfig.insRelationToApp.value").disabled = false;
		$("input[id='geProductInsuredConfig.insRelationToApp.value']").attr("checked",true);
		$("input[id='geProductInsuredConfig.insRelationToApp']").attr("value","2");
	}else if(insRelationToApp==1){
		$("input[id='geProductInsuredConfig.insRelationToApp']").attr("checked", true);
		document.getElementById("geProductInsuredConfig.insRelationToApp.value").disabled = false;
		$("input[id='geProductInsuredConfig.insRelationToApp']").attr("value","1");
	}
	// ���������˹�ϵ
	var isMoreIns = "<s:property value='geProductInsuredConfig.isMoreIns' />"
	if(isMoreIns==2){
		$("input[id='geProductInsuredConfig.isMoreIns']").attr("checked",true);
		document.getElementById("geProductInsuredConfig.isMoreIns.value").disabled = false;
		$("input[id='geProductInsuredConfig.isMoreIns.value']").attr("checked",true);
		$("input[id='geProductInsuredConfig.isMoreIns']").attr("value","2");
	}else if(isMoreIns==1){
		$("input[id='geProductInsuredConfig.isMoreIns']").attr("checked", true);
		document.getElementById("geProductInsuredConfig.isMoreIns.value").disabled = false;
		$("input[id='geProductInsuredConfig.isMoreIns']").attr("value","1");
	}
	
	// ְҵ���
	if('${geProductInsuredConfig.insOccupation}' != 0) {
		$("#insOccupationList").show();
	}
	// ��Ͷ���˹�ϵ
	if('${geProductInsuredConfig.insRelationToApp}' != 0) {
		$("#insRelationToAppConfigList").show();
	}
	// ���������˹�ϵ
	if('${geProductInsuredConfig.isMoreIns}' != 0) {
		$("#finsRelationToAppConfigList").show();
	}
	// �뱻���˹�ϵ
	if('${geProductBeneficiaryConfig.benRelationToPIns}' != 0) {
		$("#benRelationToPInsConfigList").show();
	}
	
	// ��������
	var ageScope = "<s:property value='geProductInsuredConfig.inAgeFlag' />"
	if(ageScope==0){
		$("#ageScopeCheckBox").attr("checked",true);
		$("#ageScopeDiv").show();
	}else if(ageScope==1){
		$("#ageScopeCheckBox").attr("checked",false);
		$("#ageScopeDiv").hide();
	}
	
}


//����ѡ��
function showRelate(obj){
	if(obj.id=='geProductInsuredConfig.insRelationToApp'){
		if(obj.checked){	 
			$("#insRelationToAppConfigList").show();
		}else{
			$("#insRelationToAppConfigList").hide();
		}
	}
	if(obj.id=='geProductInsuredConfig.isMoreIns'){
		if(obj.checked){
			$("#finsRelationToAppConfigList").show();
		}else{
			$("#finsRelationToAppConfigList").hide();
		}
	}
	if(obj.id=='geProductInsuredConfig.insOccupation'){
		if(obj.checked){
			$("#insOccupationList").show();
		}else{
			$("#insOccupationList").hide();
		}
	}
}

function showRequeriedIterm(obj){
	if(obj.checked == true){
		obj.value = "1";
		document.getElementById(obj.id+".value").disabled = false;
	}else{
		obj.value = "0";
		document.getElementById(obj.id+".value").checked = false;
		document.getElementById(obj.id+".value").disabled = true;
	}
}

function changeValue(obj){
	if(obj.checked == true){
		document.getElementById(obj.id.replace(".value","")).value = "2";
	}else{
		document.getElementById(obj.id.replace(".value","")).value = "1";
	}
}
//��������
function changeState(obj){
	if(obj.checked == false){
		document.getElementById("geProductInsuredConfig.inAgeFlag").value = "1";
		$("#ageScopeCheckBox").attr("checked",false);
		$("#ageScopeDiv").hide();
		tt.vf.req.rm("geProductInsuredConfig.inAgeStart","geProductInsuredConfig.inAgeStartAttr","geProductInsuredConfig.inAgeEnd","geProductInsuredConfig.inAgeEndAttr");
	}else{
		document.getElementById("geProductInsuredConfig.inAgeFlag").value = "0";
		$("#ageScopeCheckBox").attr("checked",true);
		$("#ageScopeDiv").show();
		tt.vf.req.add("geProductInsuredConfig.inAgeStart","geProductInsuredConfig.inAgeStartAttr","geProductInsuredConfig.inAgeEnd","geProductInsuredConfig.inAgeEndAttr");
	}
}

</script>
</head>
<body>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/productManage/saveOrUpdateInsuredConfig.do" target="myFrame">
<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode' />">
<input type="hidden" id="geProductInsuredConfig.inAgeFlag" name="geProductInsuredConfig.inAgeFlag" value="<s:property value='geProductInsuredConfig.inAgeFlag' />">
	<div style=" width: 550px;margin: 0 auto;">
		<!-- ������������  -->
			<table>
				<tr>
					<td width="130px"><input type=checkbox id="geProductInsuredConfig.insName" name="geProductInsuredConfig.insName" value="2" onclick="return false;" checked>����&nbsp;&nbsp;</td>
					<td width="130px">��� <input type=checkbox id="geProductInsuredConfig.insName.value"onclick="return false;" checked></td>
					<td width="30">&nbsp;</td>
					<td width="130px"><input type=checkbox id="geProductInsuredConfig.insSex" name="geProductInsuredConfig.insSex" value="2" onclick="return false;" checked>�Ա�&nbsp;&nbsp;</td>
					<td width="130px">��� <input type=checkbox id="geProductInsuredConfig.insSex.value" onclick="return false;" checked></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductInsuredConfig.insIdType" name="geProductInsuredConfig.insIdType"value="2" onclick="return false;" checked>֤������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insIdType.value"onclick="return false;" checked></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductInsuredConfig.insIdNo" name="geProductInsuredConfig.insIdNo"value="2" onclick="return false;" checked>֤������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insIdNo.value"onclick="return false;" checked></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductInsuredConfig.insBirthday" name="geProductInsuredConfig.insBirthday" value="2" onclick="return false;" checked>��������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insBirthday.value"onclick="return false;" checked></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductInsuredConfig.insComPhone" name="geProductInsuredConfig.insComPhone"value="0" onclick="showRequeriedIterm(this);">��˾�绰&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insComPhone.value"onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductInsuredConfig.insHomePhone"name="geProductInsuredConfig.insHomePhone" value="0" onclick="showRequeriedIterm(this);">��ͥ�绰&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insHomePhone.value"onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductInsuredConfig.insMobilePhone"name="geProductInsuredConfig.insMobilePhone" value="0"onclick="showRequeriedIterm(this);">�ƶ��绰&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insMobilePhone.value"onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductInsuredConfig.insAddress" name="geProductInsuredConfig.insAddress"value="0" onclick="showRequeriedIterm(this);">��ϵ��ַ&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insAddress.value"onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductInsuredConfig.insZipCode" name="geProductInsuredConfig.insZipCode"value="0" onclick="showRequeriedIterm(this);">��������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insZipCode.value"onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductInsuredConfig.insEmail" name="geProductInsuredConfig.insEmail"value="0" onclick="showRequeriedIterm(this);">��������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insEmail.value"onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductInsuredConfig.insOccupation"name="geProductInsuredConfig.insOccupation" value="0"onclick="showRelate(this);showRequeriedIterm(this);">ְҵ���&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insOccupation.value"onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type="checkbox" id="geProductInsuredConfig.insRelationToApp" name="geProductInsuredConfig.insRelationToApp" value="0" onclick="showRelate(this);showRequeriedIterm(this);">��Ͷ���˹�ϵ&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.insRelationToApp.value"onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
					<c:if test="${geProductMain.isSupportPIns=='01' }">
					<td><input type=checkbox id="geProductInsuredConfig.isMoreIns" name="geProductInsuredConfig.isMoreIns" value="0" onclick="showRelate(this);showRequeriedIterm(this);">���������˹�ϵ&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductInsuredConfig.isMoreIns.value"onclick="changeValue(this);" disabled></td>
					</c:if>
				</tr>
				<tr>
					<td colspan="5">
					<fieldset>
					<legend>������������</legend>
						<div style="height: 25px;">
							ָ����������<input type=checkbox id="ageScopeCheckBox" onclick="changeState(this);" ">
						</div>
						<div id="ageScopeDiv" style="display:none">
							��ʼʱ�䣺<input type="text" id="inAgeStart" name="geProductInsuredConfig.inAgeStart" value="${geProductInsuredConfig.inAgeStart}" style="width:30px;" onblur="checkStartEndFun();"/>
							<select id="inAgeStartAttr" name="geProductInsuredConfig.inAgeStartAttr" style="width:70px;" onblur="checkStartEndFun();">
								<option value="">��ѡ��</option>
								<c:forEach items="${periodTypeList}" var="GeCode_periodType" step="1" varStatus="status">
									<option value="${GeCode_periodType.id.codeCode}" ${geProductInsuredConfig.inAgeStartAttr == GeCode_periodType.id.codeCode ?"selected":""}>${GeCode_periodType.codeCName}</option>
								</c:forEach>
							</select>
							��ֹʱ�䣺<input type="text" id="inAgeEnd" name="geProductInsuredConfig.inAgeEnd" value="${geProductInsuredConfig.inAgeEnd}" style="width:30px;" onblur="checkStartEndFun();"/>
							<select id="inAgeEndAttr" name="geProductInsuredConfig.inAgeEndAttr" style="width:70px;" onblur="checkStartEndFun();">
								<option value="">��ѡ��</option>
								<c:forEach items="${periodTypeList}" var="GeCode_periodType" step="1" varStatus="status">
									<option value="${GeCode_periodType.id.codeCode}" ${geProductInsuredConfig.inAgeEndAttr == GeCode_periodType.id.codeCode ?"selected":""}>${GeCode_periodType.codeCName}</option>
								</c:forEach>
							</select>
						</div>
						<legend>
							<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
						</legend>
					</fieldset>
					</td>
				</tr>
				<tr id="insSexCodeList">
					<td colspan="5">
					<fieldset>
					<legend>�Ա�����</legend>
					
					<c:if test="${not empty insSexConfig}">
						<c:forEach items="${SexCode}" var="SexCode" varStatus="stas">
							<c:set var="flg" value="true" />
							<c:forEach items="${insSexConfig}" var="insSexConfig" varStatus="status">
								<c:if test="${insSexConfig eq SexCode.id.codeCode}">
									<input type="checkbox" name="geProductInsuredConfig.insSexConfig" value="${SexCode.id.codeCode}" checked="checked"/>${SexCode.codeCName}
									<c:set var="flg" value="false" />
								</c:if>
							</c:forEach>
							<c:if test="${flg eq 'true'}">
								<input type="checkbox" name="geProductInsuredConfig.insSexConfig" value="${SexCode.id.codeCode}"/>${SexCode.codeCName}
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty insSexConfig}">
						<c:forEach items="${SexCode}" var="SexCode" varStatus="stas">
							<input type="checkbox" name="geProductInsuredConfig.insSexConfig" value="${SexCode.id.codeCode}" checked="checked"/>${SexCode.codeCName}
						</c:forEach>
					</c:if>
					
					
					</fieldset>
					</td>
				</tr>
				<tr id="insIdTypeList">
					<td colspan="5">
					<fieldset>
					<legend>֤����������</legend>
					
					<c:if test="${not empty insIdTypeConfig}">
						<table>
							<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode1" varStatus="stas" step="5">
								<tr>
									<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode2" begin="${stas.index}" end="${stas.index + 4}" step="1">
										<td>
											<c:set var="flg" value="true" />
											<c:forEach items="${insIdTypeConfig}" var="insIdTypeConfig" varStatus="status">
												<c:if test="${insIdTypeConfig eq IdentifyTypeCode2.id.codeCode}">
													<input type="checkbox" name="geProductInsuredConfig.insIdTypeConfig" value="${IdentifyTypeCode2.id.codeCode}" checked="checked"/>${IdentifyTypeCode2.codeCName}
													<c:set var="flg" value="false" />
												</c:if>
											</c:forEach>
											<c:if test="${flg eq 'true'}">
												<input type="checkbox" name="geProductInsuredConfig.insIdTypeConfig" value="${IdentifyTypeCode2.id.codeCode}"/>${IdentifyTypeCode2.codeCName}
											</c:if>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty insIdTypeConfig}">
						<table>
							<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode1" varStatus="stas" step="5">
								<tr>
									<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode2" begin="${stas.index}" end="${stas.index + 4}" step="1">
										<td>
											<input type="checkbox" name="geProductInsuredConfig.insIdTypeConfig" value="${IdentifyTypeCode2.id.codeCode}" checked="checked"/>${IdentifyTypeCode2.codeCName}
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					</fieldset>
					</td>
				</tr>
				
				<tr id="insOccupationList" style="display:none">
					<td colspan="5">
						<div style="padding-top:15px;">
							<fieldset>
								<legend>ְҵ�������</legend>
								<div id="insuredOccupationTree" style="overflow-x:hidden;"></div>
							</fieldset>
						</div>
					</td>
				</tr>
				<tr id="insRelationToAppConfigList" style="display:none">
					<td colspan="5">
					<fieldset>
					<legend>��Ͷ���˹�ϵ������</legend>
					<c:forEach items="${code}" var="code" varStatus="stas">
						<c:set var="flg" value="true" />
						<c:forEach items="${insRelaToApp}" var="relaToApp" varStatus="status">
							<c:if test="${relaToApp eq code.id.codeCode}">
								<input type="checkbox" name="geProductInsuredConfig.insRelationToAppConfig" value="${code.id.codeCode}" checked="checked"/>${code.codeCName}
								<c:set var="flg" value="false" />
							</c:if>
						</c:forEach>
						<c:if test="${flg eq 'true'}">
							<input type="checkbox" name="geProductInsuredConfig.insRelationToAppConfig" value="${code.id.codeCode}"/>${code.codeCName}
						</c:if>
					</c:forEach>
					
					</fieldset>
					</td>
				</tr>
				<tr id="finsRelationToAppConfigList" style="display: none">
					<td colspan="5">
					<fieldset>
					<legend>���������˹�ϵ������</legend>
					<c:forEach items="${code}" var="code" varStatus="stas">
						<c:set var="flg" value="true" />
						<c:forEach items="${finsRelaToApp}" var="finsRelaToApp" varStatus="status">
							<c:if test="${finsRelaToApp eq code.id.codeCode}">
								<input type="checkbox" name="geProductInsuredConfig.finsRelationToAppConfig" value="${code.id.codeCode}" checked="checked"/>${code.codeCName}
								<c:set var="flg" value="false" />
							</c:if>
						</c:forEach>
						<c:if test="${flg ne 'false' }">
							<input type="checkbox" name="geProductInsuredConfig.finsRelationToAppConfig" value="${code.id.codeCode}"/>${code.codeCName}
						</c:if>
					</c:forEach>
					
					</fieldset>
					</td>
				</tr>
			</table>
			
		<div id="insuredOccupationList">
		</div>
		<div>
			<table style="align:center;margin: 0 auto;margin-top: 15px;">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>����</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.parent.close();" nowrap >�ر�</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>

</body>
<script type="text/javascript">
	tree = new dhtmlXTreeObject("insuredOccupationTree", "100%", "100%", 0);
	tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableThreeStateCheckboxes(true);
	tree.enableCheckBoxes(true, true);
	tree.loadXMLString("${insuredOccupationTree}");
	function doCreate(){
		if(tt.validate()==false){
			alert("���������������ֵ");
		}else{
			getInsuredOccupation();
			document.getElementById("frmInput").submit();
		}
	}

	function doClear(){
		document.getElementById("frmInput").reset();
	}
	function getInsuredOccupation() {
		var  insuredOccupationStr = tree.getAllCheckedBranches();
		var insuredOccupationList = insuredOccupationStr.split(",");
		$("#insuredOccupationList").html("");
		var insuredOccupationListHtml = "";
		for (var i = 0; i < insuredOccupationList.length; i++) {
			var insuredOccupationTreeId = insuredOccupationList[i];
			if (insuredOccupationTreeId != "") {
				insuredOccupationListHtml += "<input type=\"hidden\" id=\"geProductInsuredConfig.geProInsuredOccupations[" + i + "].occupationCode\" name=\"geProductInsuredConfig.geProInsuredOccupations[" + i + "].occupationCode\" value=\"" + tree.getItemAttribute(insuredOccupationTreeId, "occupationCode") + "\" />";
			}
		}
		$("#insuredOccupationList").append(insuredOccupationListHtml);
	}
	//���´�����֤����������������ֹʱ��������ʼʱ��
	function checkStartEndFun(){
		var inAgeStart = $("#inAgeStart").val();
		var inAgeEnd = $("#inAgeEnd").val();
		var inAgeStartAttr = $("#inAgeStartAttr").val();
		var inAgeEndAttr = $("#inAgeEndAttr").val();
		//ͳһ��appAgeStartAttr��appAgeEndAttrת��ΪD
		if(inAgeStartAttr != "D" || inAgeEndAttr != "D"){
			if(inAgeStartAttr == "Y"){
				inAgeStart = 12*30*inAgeStart;
			}
			if(inAgeStartAttr == "M"){
				inAgeStart = 30*inAgeStart;
			}
			if(inAgeEndAttr == "Y"){
				inAgeEnd = 12*30*inAgeEnd;
			}
			if(inAgeEndAttr == "M"){
				inAgeEnd = 30*inAgeEnd;
			}
		}
		if(inAgeStart != "" && inAgeEnd != "" && inAgeStartAttr != "" && inAgeEndAttr != "" && inAgeStart >= inAgeEnd){
			$("#inAgeStart").val("");
			$("#inAgeEnd").val("");
			$("#inAgeStartAttr").val("");
			$("#inAgeEndAttr").val("");
			$("#checkContent").remove();
			$("#comChecked").parent().append("<span id='checkContent'><span class='talentErrMsg'>��ֹʱ��Ӧ������ʼʱ�䣡</span></span>")
		}
		$("#inAgeStart").focus(function(){
			$("#checkContent").remove();
		});
		$("#inAgeEnd").focus(function(){
			$("#checkContent").remove();
		});
		$("#inAgeStartAttr").focus(function(){
			$("#checkContent").remove();
		});
		$("#inAgeEndAttr").focus(function(){
			$("#checkContent").remove();
		});
	}
</script>
</html>
