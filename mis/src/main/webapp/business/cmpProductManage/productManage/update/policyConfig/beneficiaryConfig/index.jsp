<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>电子商务管理系统-受益人配置项</title>
<script type="text/javascript">
$(document).ready(function(){
	  // 在这里写你的代码...
	  init();
});

function init(){
	// 受益人配置项
	// 公司电话 
	var benComPhone = "<s:property value='geProductBeneficiaryConfig.benComPhone' />"
	if(benComPhone==2){
		$("input[id='geProductBeneficiaryConfig.benComPhone']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benComPhone.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benComPhone.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benComPhone']").attr("value","2");
	}else if(benComPhone==1){
		$("input[id='geProductBeneficiaryConfig.benComPhone']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benComPhone.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benComPhone']").attr("value","1");
	}
	// 家庭电话
	var benHomePhone = "<s:property value='geProductBeneficiaryConfig.benHomePhone' />"
	if(benHomePhone==2){
		$("input[id='geProductBeneficiaryConfig.benHomePhone']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benHomePhone.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benHomePhone.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benHomePhone']").attr("value","2");
	}else if(benHomePhone==1){
		$("input[id='geProductBeneficiaryConfig.benHomePhone']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benHomePhone.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benHomePhone']").attr("value","1");
	}
	// 移动电话
	var benMobilePhone = "<s:property value='geProductBeneficiaryConfig.benMobilePhone' />"
	if(benMobilePhone==2){
		$("input[id='geProductBeneficiaryConfig.benMobilePhone']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benMobilePhone.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benMobilePhone.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benMobilePhone']").attr("value","2");
	}else if(benMobilePhone==1){
		$("input[id='geProductBeneficiaryConfig.benMobilePhone']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benMobilePhone.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benMobilePhone']").attr("value","1");
	}
	// 联系地址
	var benAddress = "<s:property value='geProductBeneficiaryConfig.benAddress' />"
	if(benAddress==2){
		$("input[id='geProductBeneficiaryConfig.benAddress']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benAddress.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benAddress.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benAddress']").attr("value","2");
	}else if(benAddress==1){
		$("input[id='geProductBeneficiaryConfig.benAddress']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benAddress.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benAddress']").attr("value","1");
	}
	// 邮政编码
	var benZipCode = "<s:property value='geProductBeneficiaryConfig.benZipCode' />"
	if(benZipCode==2){
		$("input[id='geProductBeneficiaryConfig.benZipCode']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benZipCode.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benZipCode.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benZipCode']").attr("value","2");
	}else if(benZipCode==1){
		$("input[id='geProductBeneficiaryConfig.benZipCode']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benZipCode.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benZipCode']").attr("value","1");
	}
	// 电子邮箱
	var benEmail = "<s:property value='geProductBeneficiaryConfig.benEmail' />"
	if(benEmail==2){
		$("input[id='geProductBeneficiaryConfig.benEmail']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benEmail.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benEmail.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benEmail']").attr("value","2");
	}else if(benEmail==1){
		$("input[id='geProductBeneficiaryConfig.benEmail']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benEmail.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benEmail']").attr("value","1");
	}
	// 受益类别
	var benType = "<s:property value='geProductBeneficiaryConfig.benType' />"
	if(benType==2){
		$("input[id='geProductBeneficiaryConfig.benType']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benType.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benType.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benType']").attr("value","2");
	}else if(benType==1){
		$("input[id='geProductBeneficiaryConfig.benType']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benType.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benType']").attr("value","1");
	}
	// 受益顺序
	var benOrder = "<s:property value='geProductBeneficiaryConfig.benOrder' />"
	if(benOrder==2){
		$("input[id='geProductBeneficiaryConfig.benOrder']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benOrder.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benOrder.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benOrder']").attr("value","2");
	}else if(benOrder==1){
		$("input[id='geProductBeneficiaryConfig.benOrder']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benOrder.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benOrder']").attr("value","1");
	}
	// 受益比例
	var benRate = "<s:property value='geProductBeneficiaryConfig.benRate' />"
	if(benRate==2){
		$("input[id='geProductBeneficiaryConfig.benRate']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benRate.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benRate.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benRate']").attr("value","2");
	}else if(benRate==1){
		$("input[id='geProductBeneficiaryConfig.benRate']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benRate.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benRate']").attr("value","1");
	}
	// 与被保人关系
	var benRelationToPIns = "<s:property value='geProductBeneficiaryConfig.benRelationToPIns' />"
	if(benRelationToPIns==2){
		$("input[id='geProductBeneficiaryConfig.benRelationToPIns']").attr("checked",true);
		document.getElementById("geProductBeneficiaryConfig.benRelationToPIns.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benRelationToPIns.value']").attr("checked",true);
		$("input[id='geProductBeneficiaryConfig.benRelationToPIns']").attr("value","2");
	}else if(benRelationToPIns==1){
		$("input[id='geProductBeneficiaryConfig.benRelationToPIns']").attr("checked", true);
		document.getElementById("geProductBeneficiaryConfig.benRelationToPIns.value").disabled = false;
		$("input[id='geProductBeneficiaryConfig.benRelationToPIns']").attr("value","1");
	}
	
	// 与被保人关系
	if('${geProductBeneficiaryConfig.benRelationToPIns}' != 0) {
		$("#benRelationToPInsConfigList").show();
	}
	
	
}


//关联选项
function showRelate(obj){
	if(obj.id=='geProductBeneficiaryConfig.benRelationToPIns'){
		if(obj.checked){	 
			$("#benRelationToPInsConfigList").show();
		}else{
			$("#benRelationToPInsConfigList").hide();
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

</script>
</head>
<body>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/productManage/saveOrUpdateBeneficiaryConfig.do" target="myFrame">
<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode' />">
	<div style=" width: 550px;margin: 0 auto;">
		<!-- 受益人配置项  -->
			<s:if test="geProductMain.isSupportBeneficiary==\"1\" "><!-- 非法定受益人 -->
				<table>
						<tr>
							<td width="130px"><input type=checkbox id="geProductBeneficiaryConfig.benName"name="geProductBeneficiaryConfig.benName" value="2" onclick="return false;" checked>姓名&nbsp;&nbsp;</td>
							<td width="130px">必填： <input type=checkbox id="geProductBeneficiaryConfig.benName.value" onclick="return false;"  checked></td>
							<td width="30">&nbsp;</td>
							<td width="130px"><input type=checkbox id="geProductBeneficiaryConfig.benGender"name="geProductBeneficiaryConfig.benGender" value="2" onclick="return false;" checked>性别&nbsp;&nbsp;</td>
							<td width="130px">必填： <input type=checkbox id="geProductBeneficiaryConfig.benGender.value" onclick="return false;" checked></td>
						</tr>
						<tr>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benIdType" name="geProductBeneficiaryConfig.benIdType"value="2" onclick="return false;" checked>证件类型&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benIdType.value"onclick="return false;" checked></td>
							<td>&nbsp;</td>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benIdNumber" name="geProductBeneficiaryConfig.benIdNumber" value="2"onclick="return false;" checked>证件号码&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benIdNumber.value"onclick="return false;" checked></td>
						</tr>
						<tr>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benBirthday" name="geProductBeneficiaryConfig.benBirthday" value="2" onclick="return false;" checked>出生日期&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benBirthday.value"onclick="return false;" checked></td>
							<td>&nbsp;</td>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benComPhone" name="geProductBeneficiaryConfig.benComPhone"value="0" onclick="showRequeriedIterm(this);">公司电话&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benComPhone.value"onclick="changeValue(this);" disabled></td>
						</tr>
						<tr>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benHomePhone"name="geProductBeneficiaryConfig.benHomePhone" value="0" onclick="showRequeriedIterm(this);">家庭电话&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benHomePhone.value"onclick="changeValue(this);" disabled></td>
							<td>&nbsp;</td>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benMobilePhone"name="geProductBeneficiaryConfig.benMobilePhone" value="0"onclick="showRequeriedIterm(this);">移动电话&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benMobilePhone.value"onclick="changeValue(this);" disabled></td>
						</tr>
						<tr>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benAddress" name="geProductBeneficiaryConfig.benAddress"value="0" onclick="showRequeriedIterm(this);">联系地址&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benAddress.value"onclick="changeValue(this);" disabled></td>
							<td>&nbsp;</td>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benZipCode" name="geProductBeneficiaryConfig.benZipCode"value="0" onclick="showRequeriedIterm(this);">邮政编码&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benZipCode.value"onclick="changeValue(this);" disabled></td>
						</tr>
						<tr>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benEmail" name="geProductBeneficiaryConfig.benEmail"value="0" onclick="showRequeriedIterm(this);">电子邮箱&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benEmail.value"onclick="changeValue(this);" disabled></td>
							<td>&nbsp;</td>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benType" name="geProductBeneficiaryConfig.benType" value="0"onclick="showRequeriedIterm(this);">受益类别&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benType.value"onclick="changeValue(this);" disabled></td>
						</tr>
						<s:if test="geProductMain.maxBeneficiaryNum>1">
						<tr>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benOrder" name="geProductBeneficiaryConfig.benOrder" value="2" onclick="return false;" checked="checked">受益顺序&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benOrder.value"onclick="return false;"  checked="checked"></td>
							<td>&nbsp;</td>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benRate" name="geProductBeneficiaryConfig.benRate" value="2" onclick="return false;" checked="checked">受益比例&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benRate.value" onclick="return false;"  checked="checked"></td>
						</tr>
						</s:if>
						<tr>
							<td><input type=checkbox id="geProductBeneficiaryConfig.benRelationToPIns"name="geProductBeneficiaryConfig.benRelationToPIns" value="0"onclick="showRelate(this);showRequeriedIterm(this);">与被保人关系&nbsp;&nbsp;</td>
							<td>必填： <input type=checkbox id="geProductBeneficiaryConfig.benRelationToPIns.value"onclick="changeValue(this);" disabled></td>
							<td colspan=3>&nbsp;</td>
						</tr>
						<tr id="benSexCodeList">
							<td colspan="5">
							<fieldset>
							<legend>性别配置</legend>
							<c:if test="${not empty benGenderConfig}">
								<c:forEach items="${SexCode}" var="SexCode" varStatus="stas">
									<c:set var="flg" value="true" />
									<c:forEach items="${benGenderConfig}" var="benGenderConfig" varStatus="status">
										<c:if test="${benGenderConfig eq SexCode.id.codeCode}">
											<input type="checkbox" name="geProductBeneficiaryConfig.benGenderConfig" value="${SexCode.id.codeCode}" checked="checked"/>${SexCode.codeCName}
											<c:set var="flg" value="false" />
										</c:if>
									</c:forEach>
									<c:if test="${flg eq 'true'}">
										<input type="checkbox" name="geProductBeneficiaryConfig.benGenderConfig" value="${SexCode.id.codeCode}"/>${SexCode.codeCName}
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty benGenderConfig}">
								<c:forEach items="${SexCode}" var="SexCode" varStatus="stas">
									<input type="checkbox" name="geProductBeneficiaryConfig.benGenderConfig" value="${SexCode.id.codeCode}" checked="checked"/>${SexCode.codeCName}
								</c:forEach>
							</c:if>
							</fieldset>
							</td>
						</tr>
						<tr id="benIdTypeList">
							<td colspan="5">
							<fieldset>
							<legend>证件类型配置</legend>
							<c:if test="${not empty bendTypeConfig}">
								<table>
									<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode1" varStatus="stas" step="5">
										<tr>
											<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode2" begin="${stas.index}" end="${stas.index+4}">
												<td>
													<c:set var="flg" value="true" />
													<c:forEach items="${bendTypeConfig}" var="bendTypeConfig" varStatus="status">
														<c:if test="${bendTypeConfig eq IdentifyTypeCode2.id.codeCode}">
															<input type="checkbox" name="geProductBeneficiaryConfig.bendTypeConfig" value="${IdentifyTypeCode2.id.codeCode}" checked="checked"/>${IdentifyTypeCode2.codeCName}
															<c:set var="flg" value="false" />
														</c:if>
													</c:forEach>
													<c:if test="${flg eq 'true'}">
														<input type="checkbox" name="geProductBeneficiaryConfig.bendTypeConfig" value="${IdentifyTypeCode2.id.codeCode}"/>${IdentifyTypeCode2.codeCName}
													</c:if>
												</td>
											</c:forEach>
										</tr>
									</c:forEach>
								</table>
							</c:if>
							<c:if test="${empty bendTypeConfig}">
								<table>
									<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode1" varStatus="stas" step="5">
										<tr>
											<c:forEach items="${IdentifyTypeCode}" var="IdentifyTypeCode2" begin="${stas.index}" end="${stas.index+4}">
												<td>
													<input type="checkbox" name="geProductBeneficiaryConfig.bendTypeConfig" value="${IdentifyTypeCode2.id.codeCode}" checked="checked"/>${IdentifyTypeCode2.codeCName}
												</td>
											</c:forEach>
										</tr>
									</c:forEach>
								</table>
							</c:if>
							</fieldset>
							</td>
						</tr>
						<tr id="benRelationToPInsConfigList" style="display: none">
							<td colspan="5">
							<fieldset>
							<legend>与被保人关系配置项</legend>
							<c:forEach items="${code}" var="code" varStatus="stas">
								<c:set var="flg" value="true" />
								<c:forEach items="${benRelaToIns}" var="benRelaToIns" varStatus="status">
									<c:if test="${benRelaToIns eq code.id.codeCode}">
										<input type="checkbox" name="geProductBeneficiaryConfig.benRelationToPInsConfig" value="${code.id.codeCode}" checked="checked"/>${code.codeCName}
										<c:set var="flg" value="false" />
									</c:if>
								</c:forEach>
								<c:if test="${flg ne 'false'}">
									<input type="checkbox" name="geProductBeneficiaryConfig.benRelationToPInsConfig" value="${code.id.codeCode}"/>${code.codeCName}
								</c:if>
							</c:forEach>
							
							</fieldset>
							</td>
						</tr>
					</table>
			</s:if>
		<div>
			<table style="align:center;margin: 0 auto;margin-top: 15px;">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>保存</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.parent.close();" nowrap >关闭</td>
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
		document.getElementById("frmInput").submit();
	}
	function doClear(){
		document.getElementById("frmInput").reset();
	}
</script>
</html>
