<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<title>�����������ϵͳ-Ͷ����������</title>
<script type="text/javascript">
$(document).ready(function(){
	// ������д��Ĵ���...
	$("input[type='checkbox']").addClass("checkbox_border");
	init();
	tt.vf.req.add("geProductApplicantConfig.appAgeStart","geProductApplicantConfig.appAgeStartAttr","geProductApplicantConfig.appAgeEnd","geProductApplicantConfig.appAgeEndAttr");
	// ����1����֤
	var NRV = new tt.NRV().set(1, '++');
	NRV.add("geProductApplicantConfig.appAgeStart","geProductApplicantConfig.appAgeEnd");
	//������֤
	tt.vf.int.add("geProductApplicantConfig.appAgeStart","geProductApplicantConfig.appAgeEnd");
	
	//alert(document.getElementById("ageScopeCheckBox").checked);
	if(document.getElementById("ageScopeCheckBox").checked==true){
		document.getElementById("geProductApplicantConfig.appAgeFlag").value = "0";
	}else{
		document.getElementById("geProductApplicantConfig.appAgeFlag").value = "1";
	}
	
});

function init(){
	// Ͷ����������
	// ��˾�绰 
	var appComPhone = "<s:property value='geProductApplicantConfig.appComPhone' />"
	if(appComPhone==2){
		$("input[id='geProductApplicantConfig.appComPhone']").attr("checked",true);
		document.getElementById("geProductApplicantConfig.appComPhone.value").disabled = false;
		$("input[id='geProductApplicantConfig.appComPhone.value']").attr("checked",true);
		$("input[id='geProductApplicantConfig.appComPhone']").attr("value","2");
	}else if(appComPhone==1){
		$("input[id='geProductApplicantConfig.appComPhone']").attr("checked", true);
		document.getElementById("geProductApplicantConfig.appComPhone.value").disabled = false;
		$("input[id='geProductApplicantConfig.appComPhone']").attr("value","1");
	}
	// ��ͥ�绰
	var appHomePhone = "<s:property value='geProductApplicantConfig.appHomePhone' />"
	if(appHomePhone==2){
		$("input[id='geProductApplicantConfig.appHomePhone']").attr("checked",true);
		document.getElementById("geProductApplicantConfig.appHomePhone.value").disabled = false;
		$("input[id='geProductApplicantConfig.appHomePhone.value']").attr("checked",true);
		$("input[id='geProductApplicantConfig.appHomePhone']").attr("value","2");
	}else if(appHomePhone==1){
		$("input[id='geProductApplicantConfig.appHomePhone']").attr("checked", true);
		document.getElementById("geProductApplicantConfig.appHomePhone.value").disabled = false;
		$("input[id='geProductApplicantConfig.appHomePhone']").attr("value","1");
	}
	// �ƶ��绰
	var appMobilePhone = "<s:property value='geProductApplicantConfig.appMobilePhone' />"
	if(appMobilePhone==2){
		$("input[id='geProductApplicantConfig.appMobilePhone']").attr("checked",true);
		document.getElementById("geProductApplicantConfig.appMobilePhone.value").disabled = false;
		$("input[id='geProductApplicantConfig.appMobilePhone.value']").attr("checked",true);
		$("input[id='geProductApplicantConfig.appMobilePhone']").attr("value","2");
	}else if(appMobilePhone==1){
		$("input[id='geProductApplicantConfig.appMobilePhone']").attr("checked", true);
		document.getElementById("geProductApplicantConfig.appMobilePhone.value").disabled = false;
		$("input[id='geProductApplicantConfig.appMobilePhone']").attr("value","1");
	}
	// ��ϵ��ַ
	var appAddress = "<s:property value='geProductApplicantConfig.appAddress' />"
	if(appAddress==2){
		$("input[id='geProductApplicantConfig.appAddress']").attr("checked",true);
		document.getElementById("geProductApplicantConfig.appAddress.value").disabled = false;
		$("input[id='geProductApplicantConfig.appAddress.value']").attr("checked",true);
		$("input[id='geProductApplicantConfig.appAddress']").attr("value","2");
	}else if(appAddress==1){
		$("input[id='geProductApplicantConfig.appAddress']").attr("checked", true);
		document.getElementById("geProductApplicantConfig.appAddress.value").disabled = false;
		$("input[id='geProductApplicantConfig.appAddress']").attr("value","1");
	}
	// ��������
	var appZipCode = "<s:property value='geProductApplicantConfig.appZipCode' />"
	if(appZipCode==2){
		$("input[id='geProductApplicantConfig.appZipCode']").attr("checked",true);
		document.getElementById("geProductApplicantConfig.appZipCode.value").disabled = false;
		$("input[id='geProductApplicantConfig.appZipCode.value']").attr("checked",true);
		$("input[id='geProductApplicantConfig.appZipCode']").attr("value","2");
	}else if(appZipCode==1){
		$("input[id='geProductApplicantConfig.appZipCode']").attr("checked", true);
		document.getElementById("geProductApplicantConfig.appZipCode.value").disabled = false;
		$("input[id='geProductApplicantConfig.appZipCode']").attr("value","1");
	}
	// ��������
	var appEmail = "<s:property value='geProductApplicantConfig.appEmail' />"
	if(appEmail==2){
		$("input[id='geProductApplicantConfig.appEmail']").attr("checked",true);
		document.getElementById("geProductApplicantConfig.appEmail.value").disabled = false;
		$("input[id='geProductApplicantConfig.appEmail.value']").attr("checked",true);
		$("input[id='geProductApplicantConfig.appEmail']").attr("value","2");
	}else if(appEmail==1){
		$("input[id='geProductApplicantConfig.appEmail']").attr("checked", true);
		document.getElementById("geProductApplicantConfig.appEmail.value").disabled = false;
		$("input[id='geProductApplicantConfig.appEmail']").attr("value","1");
	}
	// ��������
	var ageScope = "<s:property value='geProductApplicantConfig.appAgeFlag' />"
	if(ageScope==0){
		$("#ageScopeCheckBox").attr("checked",true);
		$("#ageScopeDiv").show();
	}else if(ageScope==1){
		$("#ageScopeCheckBox").attr("checked",false);
		$("#ageScopeDiv").hide();
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
		document.getElementById("geProductApplicantConfig.appAgeFlag").value = "1";
		$("#ageScopeCheckBox").attr("checked",false);
		$("#ageScopeDiv").hide();
		tt.vf.req.rm("geProductApplicantConfig.appAgeStart","geProductApplicantConfig.appAgeStartAttr","geProductApplicantConfig.appAgeEnd","geProductApplicantConfig.appAgeEndAttr");
	}else{
		document.getElementById("geProductApplicantConfig.appAgeFlag").value = "0";
		$("#ageScopeCheckBox").attr("checked",true);
		$("#ageScopeDiv").show();
		tt.vf.req.add("geProductApplicantConfig.appAgeStart","geProductApplicantConfig.appAgeStartAttr","geProductApplicantConfig.appAgeEnd","geProductApplicantConfig.appAgeEndAttr");
	}
}
</script>
</head>
<body>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/productManage/saveOrUpdateApplicantConfig.do" target="myFrame">
<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode' />">
<input type="hidden" id="geProductApplicantConfig.appAgeFlag" name="geProductApplicantConfig.appAgeFlag" value="<s:property value='geProductApplicantConfig.appAgeFlag' />">
		<div style=" width: 550px;margin: 0 auto;">
		<!-- Ͷ����������  -->
			<table>
				<tr>
					<td width="130px"><input type="checkbox" id="geProductApplicantConfig.appName" name="geProductApplicantConfig.appName" value="2" onclick="return false;" checked>����&nbsp;&nbsp;</td>
					<td width="130px">��� <input type="checkbox" id="geProductApplicantConfig.appName.value" onclick="return false;" checked></td>
					<td width="30">&nbsp;</td>
					<td width="130px"><input type="checkbox" id="geProductApplicantConfig.appSex" name="geProductApplicantConfig.appSex" value="2" onclick="return false;" checked>�Ա�&nbsp;&nbsp;</td>
					<td width="130px">��� <input type="checkbox" id="geProductApplicantConfig.appSex.value" onclick="return false;" checked></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appIdType" name="geProductApplicantConfig.appIdType" value="2" onclick="return false;" checked>֤������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductApplicantConfig.appIdType.value" onclick="return false;" checked></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductApplicantConfig.appIdNo" name="geProductApplicantConfig.appIdNo" value="2" onclick="return false;" checked>֤������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductApplicantConfig.appIdNo.value" onclick="return false;" checked></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appBirthday" name="geProductApplicantConfig.appBirthday" value="2" onclick="return false;" checked>��������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductApplicantConfig.appBirthday.value" onclick="return false;" checked></td>
					<td>&nbsp;</td>
					<td><input type="checkbox" id="geProductApplicantConfig.appComPhone" name="geProductApplicantConfig.appComPhone" value="0" onclick="showRequeriedIterm(this);" >��˾�绰&nbsp;&nbsp;</td>
					<td>��� <input type="checkbox" id="geProductApplicantConfig.appComPhone.value" onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appHomePhone" name="geProductApplicantConfig.appHomePhone" value="0" onclick="showRequeriedIterm(this);">��ͥ�绰&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductApplicantConfig.appHomePhone.value" onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductApplicantConfig.appMobilePhone" name="geProductApplicantConfig.appMobilePhone" value="0" onclick="showRequeriedIterm(this);">�ƶ��绰&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductApplicantConfig.appMobilePhone.value" onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appAddress" name="geProductApplicantConfig.appAddress" value="0" onclick="showRequeriedIterm(this);">��ϵ��ַ&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductApplicantConfig.appAddress.value" onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductApplicantConfig.appZipCode" name="geProductApplicantConfig.appZipCode" value="0" onclick="showRequeriedIterm(this);">��������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductApplicantConfig.appZipCode.value"onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appEmail" name="geProductApplicantConfig.appEmail" value="0" onclick="showRequeriedIterm(this);">��������&nbsp;&nbsp;</td>
					<td>��� <input type=checkbox id="geProductApplicantConfig.appEmail.value" onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5">
					<fieldset>
					<legend>������������</legend>
						<div style="height: 25px;">
							ָ����������<input type=checkbox id="ageScopeCheckBox" onclick="changeState(this);" ">
						</div>
						<div id="ageScopeDiv" style="display:none">
							��ʼʱ�䣺<input type="text" id="appAgeStart" name="geProductApplicantConfig.appAgeStart" value="${geProductApplicantConfig.appAgeStart}" style="width:30px;" onblur="checkStartEndFun();"/>
							<select id="appAgeStartAttr" name="geProductApplicantConfig.appAgeStartAttr" style="width:70px;" onblur="checkStartEndFun();">
								<option value="">��ѡ��</option>
								<c:forEach items="${periodTypeList}" var="GeCode_periodType" step="1" varStatus="status">
									<option value="${GeCode_periodType.id.codeCode}" ${geProductApplicantConfig.appAgeStartAttr == GeCode_periodType.id.codeCode ?"selected":""}>${GeCode_periodType.codeCName}</option>
								</c:forEach>
							</select>
							��ֹʱ�䣺<input type="text" id="appAgeEnd" name="geProductApplicantConfig.appAgeEnd" value="${geProductApplicantConfig.appAgeEnd}" style="width:30px;" onblur="checkStartEndFun();"/>
							<select id="appAgeEndAttr" name="geProductApplicantConfig.appAgeEndAttr" style="width:70px;" onblur="checkStartEndFun();">
								<option value="">��ѡ��</option>
								<c:forEach items="${periodTypeList}" var="GeCode_periodType" step="1" varStatus="status">
									<option value="${GeCode_periodType.id.codeCode}" ${geProductApplicantConfig.appAgeEndAttr == GeCode_periodType.id.codeCode ?"selected":""}>${GeCode_periodType.codeCName}</option>
								</c:forEach>
							</select>
						</div>
						<legend>
							<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
						</legend>
					</fieldset>
					</td>
				</tr>
				<tr></tr>
				<tr id="appSexCodeList">
					<td colspan="5">
					<fieldset>
					<legend>�Ա�����</legend>
					<c:if test="${not empty appSexConfig}">
						<c:forEach items="${SexCode}" var="SexCode" varStatus="stas">
							<c:set var="flg" value="true" />
							<c:forEach items="${appSexConfig}" var="appSexConfig" varStatus="status">
								<c:if test="${appSexConfig eq SexCode.id.codeCode}">
									<input type="checkbox" name="geProductApplicantConfig.appSexConfig" value="${SexCode.id.codeCode}" checked="checked"/>${SexCode.codeCName}
									<c:set var="flg" value="false" />
								</c:if>
							</c:forEach>
							<c:if test="${flg eq 'true'}">
								<input type="checkbox" name="geProductApplicantConfig.appSexConfig" value="${SexCode.id.codeCode}"/>${SexCode.codeCName}
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty appSexConfig}">
						<c:forEach items="${SexCode}" var="SexCode" varStatus="stas">
							<input type="checkbox" name="geProductApplicantConfig.appSexConfig" value="${SexCode.id.codeCode}" checked="checked"/>${SexCode.codeCName}
						</c:forEach>
					</c:if>
					</fieldset>
					</td>
				</tr>
				<tr id="appIdTypeList">
					<td colspan="5">
					<fieldset>
					<legend>֤����������</legend>
					
					<c:if test="${not empty appIdTypeConfig}">
						<table>
							<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode1" varStatus="stas" step="5">
								<tr>
									<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode2"  begin="${stas.index}" end="${stas.index + 4}" step="1">
										<td>
											<c:set var="flg" value="true" />
											<c:forEach items="${appIdTypeConfig}" var="appIdTypeConfig" varStatus="status">
												<c:if test="${appIdTypeConfig eq IdentifyTypeCode2.id.codeCode}">
													<input type="checkbox" name="geProductApplicantConfig.appIdTypeConfig" value="${IdentifyTypeCode2.id.codeCode}" checked="checked"/>${IdentifyTypeCode2.codeCName}
													<c:set var="flg" value="false" />
												</c:if>
											</c:forEach>
											<c:if test="${flg eq 'true'}">
												<input type="checkbox" name="geProductApplicantConfig.appIdTypeConfig" value="${IdentifyTypeCode2.id.codeCode}"/>${IdentifyTypeCode2.codeCName}
											</c:if>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty appIdTypeConfig}">
					<table>
						<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode1" varStatus="stas" step="5">
							<tr>
								<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode2"  begin="${stas.index}" end="${stas.index + 4}" step="1">
									<td>
										<input type="checkbox" name="geProductApplicantConfig.appIdTypeConfig" value="${IdentifyTypeCode2.id.codeCode}" checked="checked"/>${IdentifyTypeCode2.codeCName}
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
					</c:if>
					</fieldset>
					</td>
				</tr>
			</table>
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
	function doCreate(){
		if(tt.validate()==false){
			alert("���������������ֵ");
		}else{
			document.getElementById("frmInput").submit();
		}
	}
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	
	//���´�����֤����������������ֹʱ��������ʼʱ��
	function checkStartEndFun(){
		var appAgeStart = $("#appAgeStart").val();
		var appAgeEnd = $("#appAgeEnd").val();
		var appAgeStartAttr = $("#appAgeStartAttr").val();
		var appAgeEndAttr = $("#appAgeEndAttr").val();
		//ͳһ��appAgeStartAttr��appAgeEndAttrת��ΪD
		if(appAgeStartAttr != "D" || appAgeEndAttr != "D"){
			if(appAgeStartAttr == "Y"){
				appAgeStart = 12*30*appAgeStart;
			}
			if(appAgeStartAttr == "M"){
				appAgeStart = 30*appAgeStart;
			}
			if(appAgeEndAttr == "Y"){
				appAgeEnd = 12*30*appAgeEnd;
			}
			if(appAgeEndAttr == "M"){
				appAgeEnd = 30*appAgeEnd;
			}
		}
		if(appAgeStart != "" && appAgeEnd != "" && appAgeStartAttr != "" && appAgeEndAttr != "" && appAgeStart >= appAgeEnd){
			$("#appAgeStart").val("");
			$("#appAgeEnd").val("");
			$("#appAgeStartAttr").val("");
			$("#appAgeEndAttr").val("");
			$("#checkContent").remove();
			$("#comChecked").parent().append("<span id='checkContent'><span class='talentErrMsg'>��ֹʱ��Ӧ������ʼʱ�䣡</span></span>")
		}
		$("#appAgeStart").focus(function(){
			$("#checkContent").remove();
		});
		$("#appAgeEnd").focus(function(){
			$("#checkContent").remove();
		});
		$("#appAgeStartAttr").focus(function(){
			$("#checkContent").remove();
		});
		$("#appAgeEndAttr").focus(function(){
			$("#checkContent").remove();
		});
	}
</script>
</html>
