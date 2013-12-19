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
<title>电子商务管理系统-投保人配置项</title>
<script type="text/javascript">
$(document).ready(function(){
	// 在这里写你的代码...
	$("input[type='checkbox']").addClass("checkbox_border");
	init();
	tt.vf.req.add("geProductApplicantConfig.appAgeStart","geProductApplicantConfig.appAgeStartAttr","geProductApplicantConfig.appAgeEnd","geProductApplicantConfig.appAgeEndAttr");
	// 大于1的验证
	var NRV = new tt.NRV().set(1, '++');
	NRV.add("geProductApplicantConfig.appAgeStart","geProductApplicantConfig.appAgeEnd");
	//整数验证
	tt.vf.int.add("geProductApplicantConfig.appAgeStart","geProductApplicantConfig.appAgeEnd");
	
	//alert(document.getElementById("ageScopeCheckBox").checked);
	if(document.getElementById("ageScopeCheckBox").checked==true){
		document.getElementById("geProductApplicantConfig.appAgeFlag").value = "0";
	}else{
		document.getElementById("geProductApplicantConfig.appAgeFlag").value = "1";
	}
	
});

function init(){
	// 投保人配置项
	// 公司电话 
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
	// 家庭电话
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
	// 移动电话
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
	// 联系地址
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
	// 邮政编码
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
	// 电子邮箱
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
	// 年龄区间
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
//年龄区间
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
		<!-- 投保人配置项  -->
			<table>
				<tr>
					<td width="130px"><input type="checkbox" id="geProductApplicantConfig.appName" name="geProductApplicantConfig.appName" value="2" onclick="return false;" checked>姓名&nbsp;&nbsp;</td>
					<td width="130px">必填： <input type="checkbox" id="geProductApplicantConfig.appName.value" onclick="return false;" checked></td>
					<td width="30">&nbsp;</td>
					<td width="130px"><input type="checkbox" id="geProductApplicantConfig.appSex" name="geProductApplicantConfig.appSex" value="2" onclick="return false;" checked>性别&nbsp;&nbsp;</td>
					<td width="130px">必填： <input type="checkbox" id="geProductApplicantConfig.appSex.value" onclick="return false;" checked></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appIdType" name="geProductApplicantConfig.appIdType" value="2" onclick="return false;" checked>证件类型&nbsp;&nbsp;</td>
					<td>必填： <input type=checkbox id="geProductApplicantConfig.appIdType.value" onclick="return false;" checked></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductApplicantConfig.appIdNo" name="geProductApplicantConfig.appIdNo" value="2" onclick="return false;" checked>证件号码&nbsp;&nbsp;</td>
					<td>必填： <input type=checkbox id="geProductApplicantConfig.appIdNo.value" onclick="return false;" checked></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appBirthday" name="geProductApplicantConfig.appBirthday" value="2" onclick="return false;" checked>出生日期&nbsp;&nbsp;</td>
					<td>必填： <input type=checkbox id="geProductApplicantConfig.appBirthday.value" onclick="return false;" checked></td>
					<td>&nbsp;</td>
					<td><input type="checkbox" id="geProductApplicantConfig.appComPhone" name="geProductApplicantConfig.appComPhone" value="0" onclick="showRequeriedIterm(this);" >公司电话&nbsp;&nbsp;</td>
					<td>必填： <input type="checkbox" id="geProductApplicantConfig.appComPhone.value" onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appHomePhone" name="geProductApplicantConfig.appHomePhone" value="0" onclick="showRequeriedIterm(this);">家庭电话&nbsp;&nbsp;</td>
					<td>必填： <input type=checkbox id="geProductApplicantConfig.appHomePhone.value" onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductApplicantConfig.appMobilePhone" name="geProductApplicantConfig.appMobilePhone" value="0" onclick="showRequeriedIterm(this);">移动电话&nbsp;&nbsp;</td>
					<td>必填： <input type=checkbox id="geProductApplicantConfig.appMobilePhone.value" onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appAddress" name="geProductApplicantConfig.appAddress" value="0" onclick="showRequeriedIterm(this);">联系地址&nbsp;&nbsp;</td>
					<td>必填： <input type=checkbox id="geProductApplicantConfig.appAddress.value" onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
					<td><input type=checkbox id="geProductApplicantConfig.appZipCode" name="geProductApplicantConfig.appZipCode" value="0" onclick="showRequeriedIterm(this);">邮政编码&nbsp;&nbsp;</td>
					<td>必填： <input type=checkbox id="geProductApplicantConfig.appZipCode.value"onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductApplicantConfig.appEmail" name="geProductApplicantConfig.appEmail" value="0" onclick="showRequeriedIterm(this);">电子邮箱&nbsp;&nbsp;</td>
					<td>必填： <input type=checkbox id="geProductApplicantConfig.appEmail.value" onclick="changeValue(this);" disabled></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5">
					<fieldset>
					<legend>年龄区间配置</legend>
						<div style="height: 25px;">
							指定年龄区间<input type=checkbox id="ageScopeCheckBox" onclick="changeState(this);" ">
						</div>
						<div id="ageScopeDiv" style="display:none">
							起始时间：<input type="text" id="appAgeStart" name="geProductApplicantConfig.appAgeStart" value="${geProductApplicantConfig.appAgeStart}" style="width:30px;" onblur="checkStartEndFun();"/>
							<select id="appAgeStartAttr" name="geProductApplicantConfig.appAgeStartAttr" style="width:70px;" onblur="checkStartEndFun();">
								<option value="">请选择</option>
								<c:forEach items="${periodTypeList}" var="GeCode_periodType" step="1" varStatus="status">
									<option value="${GeCode_periodType.id.codeCode}" ${geProductApplicantConfig.appAgeStartAttr == GeCode_periodType.id.codeCode ?"selected":""}>${GeCode_periodType.codeCName}</option>
								</c:forEach>
							</select>
							终止时间：<input type="text" id="appAgeEnd" name="geProductApplicantConfig.appAgeEnd" value="${geProductApplicantConfig.appAgeEnd}" style="width:30px;" onblur="checkStartEndFun();"/>
							<select id="appAgeEndAttr" name="geProductApplicantConfig.appAgeEndAttr" style="width:70px;" onblur="checkStartEndFun();">
								<option value="">请选择</option>
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
					<legend>性别配置</legend>
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
					<legend>证件类型配置</legend>
					
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
		if(tt.validate()==false){
			alert("请输入符合条件的值");
		}else{
			document.getElementById("frmInput").submit();
		}
	}
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	
	//以下代码验证年龄区间配置中终止时间晚于起始时间
	function checkStartEndFun(){
		var appAgeStart = $("#appAgeStart").val();
		var appAgeEnd = $("#appAgeEnd").val();
		var appAgeStartAttr = $("#appAgeStartAttr").val();
		var appAgeEndAttr = $("#appAgeEndAttr").val();
		//统一把appAgeStartAttr和appAgeEndAttr转化为D
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
			$("#comChecked").parent().append("<span id='checkContent'><span class='talentErrMsg'>终止时间应大于起始时间！</span></span>")
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
