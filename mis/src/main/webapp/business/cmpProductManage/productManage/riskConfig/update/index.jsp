<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title>�༭����</title>
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<style type="text/css">
 td{
 	vertical-align:top;
 }
 input, select {
 	width:200px;
 }
 #submitTable {
 	margin-top: 20px;
 }
 #submitTable td {
 	width:85px;
 	vertical-align:middle;
 }
 #frmInput {
 	padding-top:10px;
 }
 
</style>
</head>
<body>
<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				�༭����
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
<center>
	<form name="frmInput" id="frmInput" action="${ctx}/productManage/updateGeRiskConfig.do" method="post" enctype="multipart/form-data" target="myFrame">
		<table id="riskTable" align="center" class="table_style" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td class="td_head" nowrap>���ִ��룺 </td>
				<td class="td_body" width="400px"><input type="hidden" id="riskCode" name="geRiskConfig.riskCode"  value="${geRiskConfig.riskCode}" />${geRiskConfig.riskCode}</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>�������ƣ�</td>
				<td class="td_body" >
					<input type="text" id="riskName" name="geRiskConfig.riskName"  value="${geRiskConfig.riskName}" />
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>���ּ�ƣ�</td>
				<td class="td_body" >
					<input type="text" id="riskSimpleName" name="geRiskConfig.riskSimpleName"  value="${geRiskConfig.riskSimpleName}" />
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>�������ִ��룺 </td>
				<td class="td_body" >
					<input type="text" id="coreRiskCode" name="geRiskConfig.coreRiskCode"  value="${geRiskConfig.coreRiskCode}" />
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>�����ձ�־��</td>
				<td class="td_body" >
					<select id="subRiskFlag" name="geRiskConfig.subRiskFlag">
							<option value="">--��ѡ��--</option>
							<option value="01" ${geRiskConfig.subRiskFlag == "01" ? "selected" : ""}>��</option>
							<option value="02" ${geRiskConfig.subRiskFlag == "02" ? "selected" : ""}>��</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>ҵ������</td>
				<td class="td_body" >
					<select id="businessArea" name="geRiskConfig.businessArea">
						<option value="" selected>--��ѡ��--</option>
						<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
							<option value="${GeCode_businessArea.id.codeCode}" ${geRiskConfig.businessArea == GeCode_businessArea.id.codeCode ?"selected":""}>${GeCode_businessArea.codeCName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" >
					<div class="frmCreate_kuang" style="margin-left:10px;width:670px;margin-top:15px;">
						<div class="frmCreate_kuang_header">�����������<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
						<div style="padding-left:3px; padding-top:15px;">
							<table border="0" cellspacing="0" cellpadding="0" id="addDutyTable" class="table_Show">
								<tr>
									<td class="search_head" width="50"  nowrap>&nbsp;</td>
									<td class="search_head" width="150"  nowrap>���δ���</td>
									<td class="search_head" width="210"  nowrap>��������</td>
									<td class="search_head" width="210"  nowrap>���μ��</td>
								</tr>
								<c:forEach items="${geRiskConfig.geDutyConfigs}" var="geDutyConfig" step="1" varStatus="status">
									<tr height="40" id ="addDutyTableTr_${status.index}">
										<td class="search_body" width="50"  nowrap>
											<input id="check${status.index}" type="checkbox" name="checkChild" style="width:50px; border: 0px" onclick="checkSingleRow()" value="addDutyTableTr_${status.index}"/>
										</td>
										<td class="search_body" width="150"  nowrap>
											<input type="hidden" id="serialNo${status.index}"style="width:210px;" name="serialNo${status.index}" value="${geDutyConfig.serialNo}" />
											<input type="text" id="dutyCode${status.index}" style="width:150px;" name="dutyCode${status.index}" value="${geDutyConfig.dutyCode}" />
										</td>
										<td class="search_body" width="210"  nowrap>
											<input type="text" id="dutyName${status.index}" style="width:210px;" name="dutyName${status.index}" value="${geDutyConfig.dutyName}" />
										</td>
										<td class="search_body" width="210"  nowrap>
											<input type="text" id="simpleName${status.index}"style="width:210px;" name="simpleName${status.index}" value="${geDutyConfig.simpleName}" />
										</td>
								 	 </tr>
							 	 </c:forEach>
							</table>
							<div id="dutyInput">
							</div>
							<div style="padding-top:15px;">
								<table align="right" id="addDutyOperator">
									<tr>
										<td id="addDuty" class="btnBigOnBlur" style="line-height: 23px;" onmouseover="this.className='btnBigOnFocus'" 
											onmouseout="this.className='btnBigOnBlur'" nowrap>���</td>
										<td id="delDuty" class="btnBigOnBlur" style="line-height: 23px;" onmouseover="this.className='btnBigOnFocus'" 
											onmouseout="this.className='btnBigOnBlur'" onclick="" nowrap>ɾ��</td>
										
									</tr>
								</table>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<table align="center" id="submitTable">
						<tr>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='riskDetail.do?riskCode=${geRiskConfig.riskCode}';">����</td>
							<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" nowrap>�޸�</td>
							<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClearReset('${geRiskConfig.riskCode}');" nowrap>����</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >�ر�</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<input type="hidden" name="idStr" id="idStr" />
	<input type="hidden" name="count" id="count" />
	<input type="hidden" id="saleChannel" name="geRiskConfig.saleChannel"  value="${geRiskConfig.saleChannel}" />
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</center>
<script type="text/javascript">

var addReslut = "${addReslut}";

if (addReslut == "success") {
	alert("����������ӳɹ���");
} else if (addReslut == "failure") {
	alert("�����������ʧ�ܣ�");
}

$(document).ready(function(){
	//������һ�д���Ϊ����5�����޸��ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	tt.vf.req.add("geRiskConfig.riskName","geRiskConfig.riskSimpleName","geRiskConfig.coreRiskCode","geRiskConfig.subRiskFlag","geRiskConfig.businessArea");
	new tt.RV().set(new RegExp("^[\\w_-]{0,32}$"), "ֻ��������ĸ,����,�м���,�»���").add("geRiskConfig.coreRiskCode");
	//������һ�д���Ϊ��������3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	var addReslut = "${geRiskConfig.geDutyConfigs}";
	for(var i =0;i<addReslut.split(",").length;i++){
		tt.vf.req.add("dutyCode"+i,"dutyName"+i,"simpleName"+i);
		new tt.RV().set(new RegExp("^[\\w_-]{0,32}$"), "ֻ��������ĸ,����,�м���,�»���").add("geRiskConfig.geDutyConfigs["+i+"].dutyCode");
		new tt.RV().set(new RegExp("^[\\w_-]{0,32}$"), "������ĸ/0-9/-/_").add("dutyCode" + i);
	}
	
	var addDutyTableTrId = $("#addDutyTable tr").length - 1;

	for(var i = 0; i < addDutyTableTrId; i++ ){
		checkDutyCode("dutyCode" + i );	
	}
	
	$("#addDuty").click(function() {
		var addNumber = addDutyTableTrId;
		var dutyStr = "<tr height=\"45\" id=\"addDutyTableTr_" + addNumber + "\">" + 
						"<td class=\"search_body\" width=\"50\" valign=\"middle\"><input id=\"check" + addNumber + "\" type=\"checkbox\" style=\"width:50px; border: 0px\" onclick=\"checkSingleRow()\" name=\"checkChild\" value=\"addDutyTableTr_" + addNumber + "\" /></td>" +
						"<td class=\"search_body\" width=\"150\" valign=\"top\" align=\"left\">" +
						"<input type=\"hidden\" id=\"serialNo" + addNumber + "\" style=\"width:210px;\" name=\"serialNo" + addNumber + "\" value=\"\" />" +
						"<input type=\"text\" id=\"dutyCode" + addNumber + "\" style=\"width:150px;margin:0px 0px 5px 0px;\" name=\"dutyCode" + addNumber + "\" value=\"\" /></td>" +
						"<td class=\"search_body\" width=\"210\" valign=\"top\" align=\"left\"><input type=\"text\" id=\"dutyName" + addNumber + "\" style=\"width:210px;margin:0px 0px 5px 0px;\" name=\"dutyName" + addNumber + "\" value=\"\" /></td>" +
						"<td class=\"search_body\"  width=\"210\" valign=\"top\" align=\"left\"><input type=\"text\" id=\"simpleName" + addNumber + "\"style=\"width:210px;margin:0px 0px 5px 0px;\" name=\"simpleName" + addNumber + "\" value=\"\" /></td>" +
					  "</tr>"
		$("#addDutyTable").append(dutyStr);
		tt.vf.req.add("dutyCode" + addNumber, "dutyName" + addNumber, "simpleName" + addNumber);
		new tt.RV().set(new RegExp("^[\\w_-]{0,32}$"), "������ĸ/0-9/-/_").add("dutyCode" + addNumber);
		addDutyTableTrId += 1;
		checkDutyCode("dutyCode" + addNumber);
	});
	
	$("#delDuty").click(function() {
		var checkedCount = $("#count").val();
		var dutyNumber = $("#addDutyTable tr").length -1 ;
		if(checkedCount == 0){
			alert("��ѡ��Ҫɾ�����������Σ�");
			return;						
		}
		if (dutyNumber == checkedCount) {
			alert("����ɾ���������Σ�");
			return;
		} else {
			var idStr = $("#idStr").val();
			var delDuties = idStr.split(",");
			for (var i = 0 ; i < delDuties.length; i++) {
				$("#addDutyTable").contents().find("#" + delDuties[i]).remove();	
			}
			var duties = $("#addDutyTable tr");
			for (var i = 1; i < duties.length; i++) {
			}
		}
	});
	
	$("textarea").blur(function(){
		validateValue(this);
	});
	
	//���ύ
	$("#addButtonSubmit").click(function(){
		if(!tt.validate()){
			return false;
		}else{
			addDuty();
			$("#frmInput").submit();
		}
	});
	
});

/**
 * ������� 
 */
function addDuty() {
	$("#dutyInput").html("");
	var duties = $("#addDutyTable tr");
	var dutyStr = "";
	for (var i = 1; i < duties.length; i++) {
		var duty = $(duties[i]).find("input");
		if (duty[1].value != "") {
			dutyStr += "<input type=\"hidden\" id=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].serialNo\" name=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].serialNo\" value=\"" + duty[1].value + "\" />";
		}
		dutyStr += "<input type=\"hidden\" id=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].dutyCode\" name=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].dutyCode\" value=\"" + duty[2].value + "\" />" +
			"<input type=\"hidden\" id=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].dutyName\"  name=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].dutyName\" value=\"" + duty[3].value + "\" />" +
			"<input type=\"hidden\" id=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].simpleName\" name=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].simpleName\" value=\"" + duty[4].value + "\" />";
	}
	$("#dutyInput").append(dutyStr);
}

function doClear(){
	document.getElementById("frmInput").reset();
}
var y = document.getElementsByTagName("input");
for (var i=0; i < y.length; i++){
	if(y[i].type == 'text'){
		y[i].onkeyup = showMyStatus;
	}
}
function showMyStatus(evnt){
	var obj,errorCode;
	if (isIE()) {
		obj = event.srcElement;
	}else {
		obj = evnt.target;
	}
	validateValue(obj);
}



function isIE() {
	if(document.all) return true;
	return false;
}

String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.replaceAll = function(regex, replaceWith, ignoreCase) {  
	 
��	 if (!RegExp.prototype.isPrototypeOf(regex)) {  
 
        return this.replace(new RegExp(regex, (ignoreCase ? "gi": "g")), replaceWith);  
 
    } else {  
 
        return this.replace(regex, replaceWith);  
 
    }  
 
}  

function validateValue(obj) {
	var patn = /(^\s)|(\s$)/;
	if (patn.test(obj.value))
		obj.value = obj.value.trim();
}

function checkSingleRow() {
	var idStr = "";
	var count = 0;
	var checkArray = document.getElementsByName("checkChild");
	var tr_selected;
	var value_checked;
	for (var i = 0; i < checkArray.length; i++){
		tr_selected = document.getElementById("tr_" + checkArray[i].id);
		if(checkArray[i].checked){
			value_checked = checkArray[i].value;
			if(idStr == ""){
				idStr = value_checked;
			}else {
				idStr = idStr + "," + value_checked;
			}
			count++;
		}else{
		}
	}
	document.getElementById("idStr").value = idStr;
	document.getElementById("count").value = count;
}

//���ð�ť�¼�
function doClearReset(idStr){
	location.href = "${ctx}/productManage/findGeRiskConfigByRiskCode.do?riskCode=" + idStr;		
}

//���´�����֤ͬһҵ���������δ���dutyCode��Ψһ��
function checkDutyCode(dutyCodeN){
	$("#" + dutyCodeN).blur(function(){
		//����������δ���ʱҲҪУ�����δ����Ψһ��
		var addDutyTableTrIdCnt = $("#addDutyTable tr").length - 1;
		var addDutyTableDutyCodeIds = "";
		for (var i = 0; i < addDutyTableTrIdCnt; i++){
			var eachDutyCode = $("#addDutyTableTr_"+i).contents().find("#dutyCode"+i);
			addDutyTableDutyCodeIds += eachDutyCode.val() + ",";
		}
		addDutyTableDutyCodeIds = addDutyTableDutyCodeIds.split(",");
		for(var i = 0; i < addDutyTableDutyCodeIds.length-1; i++){
			for(var j = i+1; j < addDutyTableDutyCodeIds.length-1; j++){
				if(addDutyTableDutyCodeIds[i] == addDutyTableDutyCodeIds[j]){
					$("#" + dutyCodeN).val("");
					$("#dutyCodeContent").remove();
					$("#comChecked").parent().append("<span id='dutyCodeContent'><span class='talentErrMsg'>ͬһ���ֲ���������ͬ�����δ��룡</span></span>")
					return;
				}
			}
		}
		var dutyCodeValue = $("#" + dutyCodeN).val();
		var businessArea = $("#businessArea").val();
		$.ajax({
			url : "${ctx}/productManage/isExistDutyCode.do",
			data : {
				"dutyCode0" : dutyCodeValue,
				"businessArea" : businessArea
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#" + dutyCodeN).val("");
					$("#dutyCodeContent").remove();
					$("#comChecked").parent().append("<span id='dutyCodeContent'><span class='talentErrMsg'>ͬһҵ���������δ����ظ������޸�ҵ����������δ��룡</span></span>")
				} else {
					$("#dutyCodeContent").remove();
				}
			}
		});
	});
	$("#" + dutyCodeN).focus(function(){
		$("#dutyCodeContent").remove();
	});
}
</script>
</body>
</html>