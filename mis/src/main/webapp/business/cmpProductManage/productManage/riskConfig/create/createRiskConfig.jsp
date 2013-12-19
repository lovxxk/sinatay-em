<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title>�½�����</title>
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<style type="text/css">
 td{
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
 .table_style{
 border-collapse:collapse;
 }
.table_Show{
line-height: 15px;
}
 
</style>
</head>
<body>
<div class="public_fun_title">�½�����</div>
<center>
	<form name="frmInput" id="frmInput" action="${ctx}/productManage/addGeRiskConfig.do" method="post" enctype="multipart/form-data">
		<table id="dutyKindTable" align="center" class="table_style" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td class="td_head" nowrap>���ִ��룺 </td>
				<td class="td_body" width="450px" valign="top">
					<input type="text" id="riskCode" name="geRiskConfig.riskCode"  value="" />
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>�������ƣ�</td>
				<td class="td_body" valign="top">
					<input type="text" id="riskName" name="geRiskConfig.riskName"  value="" />
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>���ּ�ƣ�</td>
				<td class="td_body" valign="top">
					<input type="text" id="riskSimpleName" name="geRiskConfig.riskSimpleName"  value="" />
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>�������ִ��룺 </td>
				<td class="td_body" valign="top">
					<input type="text" id="coreRiskCode" name="geRiskConfig.coreRiskCode"  value="" />
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>�����ձ�־��</td>
				<td class="td_body" valign="top">
					<select id="subRiskFlag" name="geRiskConfig.subRiskFlag">
						<option value="" selected="selected">--��ѡ��--</option>
						<option value="01">��</option>
						<option value="02">��</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>ҵ������</td>
				<td class="td_body" valign="top">
					<select id="businessArea" name="geRiskConfig.businessArea">
						<option value="" selected>--��ѡ��--</option>
						<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
						<c:if test="${GeCode_businessArea.id.codeCode!='1'}">
								<!--���˼���-->
							<option value="${GeCode_businessArea.id.codeCode}">${GeCode_businessArea.codeCName}</option>
						</c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div class="frmCreate_kuang" style="margin-left:10px;width:690px;margin-top:15px;">
						<div class="frmCreate_kuang_header">�����������<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
						<div style="padding-left:10px; padding-top:10px;">
							<table border="0" cellspacing="0" class="table_Show" cellpadding="0" id="addDutyTable" >
								<tr>
									<td class="search_head">&nbsp;</td>
									<td class="search_head">���δ���</td>
									<td class="search_head">��������</td>
									<td class="search_head">���μ��</td>
								</tr>
								<tr height="45" id ="addDutyTableTr_0">
									<td class="search_body" align="center" valign="top" width="50">
										<input id="check0" type="checkbox" name="checkChild" style="width:50px; border: 0px" onclick="checkSingleRow()" value="addDutyTableTr_0"/>
									</td>
									<td class="search_body" valign="top" width="150" align="left">
										<input type="text" id="dutyCode0" style="width:150px;margin:0px 0px 5px 0px;" name="dutyCode0" value="" />
									</td>
									<td class="search_body" valign="top" width="210" align="left">
										<input type="text" id="dutyName0" style="width:210px;margin:0px 0px 5px 0px;" name="dutyName0" value="" />
									</td>
									<td class="search_body" valign="top" width="210" align="left">
										<input type="text" id="simpleName0"style="width:210px;margin:0px 0px 5px 0px;" name="simpleName0" value="" />
									</td>
							 	 </tr>
							</table>
							<div id="dutyInput">
							</div>
							<div style="padding-top:15px;">
								<table align="right" id="addDutyOperator">
									<tr>
										<td id="addDuty" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
											onmouseout="this.className='btnBigOnBlur'" nowrap>���</td>
										<td id="delDuty"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
											onmouseout="this.className='btnBigOnBlur'" nowrap>ɾ��</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<table id="submitTable">
						<tr>
							<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" nowrap>����</td>
							<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<input type="hidden" name="idStr" id="idStr" />
	<input type="hidden" name="count" id="count" />
	<input type="hidden" id="saleChannel" name="geRiskConfig.saleChannel" value="01"/>
</center>
<script type="text/javascript">

var addResult = "${addResult}";
if (addResult == "success") {
	alert("����������ӳɹ���");
}else if (addResult == "failure") {
	alert("�����������ʧ�ܣ�");
}

$(document).ready(function(){
	//������һ�д���Ϊ����6���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	tt.vf.req.add("geRiskConfig.riskCode","geRiskConfig.riskName","geRiskConfig.riskSimpleName","geRiskConfig.coreRiskCode","geRiskConfig.subRiskFlag","geRiskConfig.businessArea");
	new tt.RV().set(new RegExp("^[A-Za-z0-9]{1,10}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-10").add("geRiskConfig.riskCode");
	new tt.RV().set(new RegExp("^[\\w_-]{0,32}$"), "��ĸ,0-9,-,_").add("geRiskConfig.coreRiskCode");
	//������һ�д���Ϊ��������3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	tt.vf.req.add("dutyCode0", "dutyName0", "simpleName0");
	new tt.RV().set(new RegExp("^[\\w_-]{0,32}$"), "������ĸ/0-9/-/_").add("dutyCode0");
	//���´�����֤���ִ���riskCode��Ψһ��
	$("#riskCode").blur(function(){
		var riskCode = $("#riskCode").val();
		$.ajax({
			url : "${ctx}/productManage/isExistRiskCode.do",
			data : {
				"riskCode" : riskCode
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#riskCode").val("");
					$("#riskCodeContent").remove();
					$("#riskCode").parent().append("<span id='riskCodeContent'><span class='talentErrMsg'>���ִ����Ѿ����ڣ���������ִ��룡</span></span>")
				} else {
					$("#riskCodeContent").remove();
				}
			}
		});
	});
	$("#riskCode").focus(function(){
		$("#riskCodeContent").remove();
	});
	
	checkDutyCode("dutyCode0");
	
	var addDutyTableTrId = $("#addDutyTable tr").length - 1;
	$("#addDuty").click(function() {
		var addNumber = addDutyTableTrId;
		var dutyStr = "<tr height=\"45\" id=\"addDutyTableTr_" + addNumber + "\">" + 
						"<td class=\"search_body\" width=\"50\"  valign=\"top\" valign=\"middle\"><input id=\"check" + addNumber + "\" type=\"checkbox\" style=\"width:50px;border:0px\" onclick=\"checkSingleRow()\" name=\"checkChild\" value=\"addDutyTableTr_" + addNumber + "\" /></td>" +
						"<td class=\"search_body\" width=\"150\" valign=\"top\" align=\"left\"><input type=\"text\" id=\"dutyCode" + addNumber + "\" style=\"width:150px;margin:0px 0px 5px 0px;\" name=\"dutyCode" + addNumber + "\" value=\"\" /></td>" +
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
		} else {
			addDuty();
			$("#frmInput").submit();
		}
	});
	
});

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

/**
 * ������� 
 */
function addDuty() {
	$("#dutyInput").html("");
	var duties = $("#addDutyTable tr");
	var dutyStr = "";
	for (var i = 1; i < duties.length; i++) {
		var duty = $(duties[i]).find("input");
		dutyStr += "<input type=\"hidden\" id=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].dutyCode\" name=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].dutyCode\" value=\"" + duty[1].value + "\" />" +
			"<input type=\"hidden\" id=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].dutyName\"  name=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].dutyName\" value=\"" + duty[2].value + "\" />" +
			"<input type=\"hidden\" id=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].simpleName\" name=\"geRiskConfig.geDutyConfigs[" + (i - 1) + "].simpleName\" value=\"" + duty[3].value + "\" />";
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

$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
var ids = ['des'];
    	var contents = ['˵��:������Ϊγ�ȴ�������������Ϣ��<br/>'
    	               + 'ʹ����Ⱥ:��������������Ա<br/>'
    	               + '��������:<br/>'
    	               + 'ע������:ÿ��������������Ҫ��һ������<br/>'];
        	for ( var i = 0 ; i < ids.length ; i++ ){
    			$('#' + ids[i]).qtip({ 
    				content:contents[i], 
    				position: { 
						corner: { 
						tooltip: 'topMiddle',
						target: 'bottomMiddle'
						} ,
						adjust: { 
							screen: true 
							}
					}, 
					 style: {
							border: { 
								width: 1,
								radius: 1,
								color: '#00739f'
								},
								width:450,
								textAlign: 'left',
								background: '#e0f2ff', 
								tip:true,//����������Ƿ����
								padding :10
							}
						});
        	}
</script>
</body>
</html>
