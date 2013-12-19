<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title></title>
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<style type="text/css">
 td{
 	vertical-align:top;
 }
</style>
	<style type="text/css">
		#productTable td{
			vertical-align:top;
		}
		.td_head{
		}
		.periodTable_title{
		    color: #616161;
		    font-weight: bold;
		    height: 23px;
		    text-align:center;
		    background-color:#E9F8FF;
		 }
		 .periodTable_body{
		    color: #616161;
		    height: 23px;
		    text-align:left;
		    padding-left:5px;
		 }
		 #addDutyOperator td{
		 	vertical-align:middle;
		 }
	</style>
</head>
<body>
<center>
<form name="frmInput" id="frmInput" action="${ctx}/productManage/updateDuty.do" method="post">
<table id="diretoryAttributeTable" align="center" style="font-size:9pt;width:95%;padding-top:15px;" cellpadding=0 cellspacing=0 border=0>
	<tr  height="30px">
		<td class="td_head" width="30%" nowrap>�������ƣ�</td>
		<td class="td_body" width="70%"><input style="width: 180px" id="attrName" name="geProductDuty.productDutyName" maxlength="20" value="${geProductDuty.productDutyName}"></td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>��ʾ˳��</td>
		<td class="td_body" width="70%">
		<input style="width: 180px" id="seqIndex" name="geProductDuty.seqIndex" maxlength="3" value="${geProductDuty.seqIndex}"></td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>�Ƿ����ۣ�</td>
		<td class="td_body" width="70%">
			<select name="geProductDuty.saleFlag">
				<option ${geProductDuty.saleFlag==0? 'selected':''} value="0">��</option>
				<option ${geProductDuty.saleFlag==1? 'selected':''} value="1">��</option>
			</select>
		
			<%-- <input name="geProductDuty.saleFlag" ${geProductDuty.saleFlag==1? 'checked':''} type="radio" value="1"/>��&nbsp;<input name="geProductDuty.saleFlag" ${geProductDuty.saleFlag==0? 'checked':''} type="radio" value="0"/>��--%>
		</td>
	</tr>

	<tr height="30px">
		<td class="td_head" width="30%" nowrap>�������ã�</td>
		<td class="td_body" width="70%">
			<div style="padding-top:5px;width:485px;">
					<table border="1" cellspacing="0" cellpadding="0" id="periodTable">
						<tr>
							<td class="periodTable_title" width="48" valign="middle">&nbsp;</td>
							<td class="periodTable_title" width="200" valign="middle">����</td>
							<td class="periodTable_title" width="200" valign="middle">��λ</td>
						</tr>
						<c:forEach items="${geProductDuty.geProDutyAttrAllowVals}"  var="productAttrAllowValue" step="1" varStatus="status">
							<tr height="45" id="periodTableTr_${status.index}">
								<td class="periodTable_body" width="48" valign="top" nowrap>
									<input id="check${status.index}" type="checkbox" style="width:48px;" onclick="checkSingleRow()" name="checkChild" value="periodTableTr_${status.index}" />
								</td>
								<td class="periodTable_body" width="200" valign="top" nowrap>
									<input type="hidden" id="geProductDuty.geProDutyAttrAllowVals[${status.index}].attributeKind" name="geProductDuty.geProDutyAttrAllowVals[${status.index}].attributeKind" value="givePrice" /> 
									<input type="hidden" id="geProductDuty.geProDutyAttrAllowVals[${status.index}].attributeName" name="geProductDuty.geProDutyAttrAllowVals[${status.index}].attributeName" value="����" /> 
									<input type="hidden" id="geProductDuty.geProDutyAttrAllowVals[${status.index}].allowValuesType" name="geProductDuty.geProDutyAttrAllowVals[${status.index}].allowValuesType" value="02" /> 
									<input type="hidden" id="geProductDuty.geProDutyAttrAllowVals[${status.index}].attributeType" name="geProductDuty.geProDutyAttrAllowVals[${status.index}].attributeType" value="givePriceType" />
									<input type="hidden" id="geProductDuty.geProDutyAttrAllowVals[${status.index}].geProductDuty.serialNo" name="geProductDuty.geProDutyAttrAllowVals[${status.index}].geProductDuty.serialNo" value="${geProductDuty.serialNo}" />  
									<input maxlength="25" type="text" id="geProDutyAttrAllowVals${status.index}" name="attributeValue${status.index}" style="width:210px;margin:0px 0px 5px 0px;" value="${productAttrAllowValue.attributeValue}" /> 
								</td>
								<td class="periodTable_body" width="200" valign="top" nowrap>
									<select id="geProductDuty.geProDutyAttrAllowVals[${status.index}].attributeTypeValue" name="geProductDuty.geProDutyAttrAllowVals[${status.index}].attributeTypeValue" style="width:170px;">
										<c:forEach items="${periodTypeList}" var="GeCode_periodType" step="1" varStatus="status">
											<option value="${GeCode_periodType.id.codeCode}" ${productAttrAllowValue.attributeTypeValue == GeCode_periodType.id.codeCode ?"selected":""}>${GeCode_periodType.codeCName}</option>
										</c:forEach>
									</select>
								</td>
						 	 </tr>
						 	 <span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
						</c:forEach>
					</table>
					<div style="padding-top:15px;">
						<table align="right" id="addDutyOperator">
							<tr>
								<td id="addPeriod" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>���</td>
								<td id="delPeriod"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>ɾ��</td>
							</tr>
						</table>
					</div>
				</div>
		</td>
	</tr>
	
		<tr height="30px">
		<td colspan="2">
			<table width="200" align="center">
				<tr>
					<td  id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" style="width: 60px;padding: 5 7 0 0" nowrap>����</td>
					<td  id="updateButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" style="width: 60px;padding: 5 7 0 0" onclick="doClear();" >����</td>
				</tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			</table>
		</td>
	</tr>
</table>
<input type="hidden"  name="geProductDuty.serialNo" value="${geProductDuty.serialNo}">
<input type="hidden" name="idStr" id="idStr" />
<input type="hidden" name="count" id="count" />

</form>
</center>
<script type="text/javascript">
var addReslut = "${addReslut}";

if (addReslut == "success") {
	alert("���α༭�ɹ���");
} else if (addReslut == "failure") {
	alert("���α༭ʧ�ܣ�");
}

var curr_count = 0;

$(document).ready(function(){
	//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	tt.vf.req.add("geProductDuty.productDutyName");
	tt.vf.req.add("geProductDuty.seqIndex");
	
	new tt.RV().set(new RegExp("^[0-9]{1,3}$"), "ֻ������������0-999��").add("geProductDuty.seqIndex");
	
	$("attrName").blur(function(){
		validateValue(this);
	});
	$("seqIndex").blur(function(){
		validateValue(this);
	});
	
	//���ύ
	$("#addButtonSubmit").click(function(){
		if(!tt.validate()){
			return false;
		}else{
			if(curr_count==0) curr_count = $("#periodTable tr").length - 1;
			for (var i = 0; i < curr_count; i++) {
				if(document.getElementById("geProDutyAttrAllowVals"+i)!=null){
					document.getElementById("geProDutyAttrAllowVals"+i).name = "geProductDuty.geProDutyAttrAllowVals[" + i + "].attributeValue";

				}
			}
			$("#frmInput").submit();
		}
	});

});

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

function validateValue(obj) {
	var patn = /(^\s)|(\s$)/;
	if (patn.test(obj.value))
		obj.value = obj.value.trim();
}

$(document).ready(function () {
	var productAttrAllowValueListSize = "${productAttrAllowValueListSize}"; 
	if (productAttrAllowValueListSize == 0) {
		$("#addPeriod").trigger("click");
	}
	// �����֤
	var valCount = "${productAttrAllowValueListSize}"; 
	var NRVattributeValue = new tt.NRV().set(0, '++');
	if(valCount==0) valCount = $("#periodTable tr").length - 1;
	for(var i = 0;i<valCount ;i++ ){
		var curr_id = "attributeValue"+i;
		tt.vf.req.add(curr_id);
		//new tt.RV().set(new RegExp("^\\d+$"), "ֻ����������").add(curr_id);
		NRVattributeValue.add(curr_id);//����ı���������0������
	}
});

var periodTableTrId = $("#periodTable tr").length - 1;
$("#addPeriod").click(function() {
	//var addNumber = $("#periodTable tr").length - 1;
	var addNumber = periodTableTrId;
	curr_count = addNumber +1;
	var NRVattributeValueAdd = new tt.NRV().set(0, '++');
	var dutyStr = "<tr height=\"45\" id=\"periodTableTr_" + addNumber + "\">" + 
					"<td class=\"periodTable_body\" width=\"48\" valign=\"top\" nowrap><input id=\"check" + addNumber + "\" type=\"checkbox\" style=\"width:48px;\" onclick=\"checkSingleRow()\" name=\"checkChild\" value=\"periodTableTr_" + addNumber + "\" /></td>" +
					"<td class=\"periodTable_body\" width=\"200\" valign=\"top\" nowrap>" +
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeKind\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeKind\" value=\"givePrice\" /> " + 
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeName\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeName\" value=\"����\" /> " +
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].allowValuesType\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].allowValuesType\" value=\"02\" /> " +
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeType\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeType\" value=\"givePriceType\" /> " +
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].geProductDuty.serialNo\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].geProductDuty.serialNo\" value=\"${geProductDuty.serialNo}\" /> " +
					"<input type=\"text\" id=\"geProDutyAttrAllowVals" + addNumber + "\" style=\"width:210px;margin:0px 0px 5px 0px;\" name=\"attributeValue" + addNumber + "\" value=\"\" /> " + 
					"</td>" +
					"<td class=\"periodTable_body\" width=\"200\" valign=\"top\" nowrap>" + 
					"<select id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeTypeValue\" style=\"width:170px;\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber+ "].attributeTypeValue\">" +
					"</select>" + 
					"</td>" +
				  "</tr>"
	$("#periodTable").append(dutyStr);
	getDataFromDic("geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeTypeValue", "InsuredAmountUnit");
	
	// �����֤
	var curr_id = "attributeValue"+addNumber;
	tt.vf.req.add(curr_id);
	//new tt.RV().set(new RegExp("^\\d+$"), "ֻ����������").add(curr_id);
	NRVattributeValueAdd.add(curr_id);//����ı�������Ǵ���0������
	periodTableTrId += 1;
});
$("#delPeriod").click(function() {
	var dutyNumber = $("#periodTable tr").length -1 ;
	var checkedCount = $("#count").val();
	if (dutyNumber == checkedCount) {
		alert("����ɾ�����еı������ã�");
	} else {
		var idStr = $("#idStr").val();
		var delDuties = idStr.split(",");
		for (var i = 0 ; i < delDuties.length; i++) {
			$("#periodTable").contents().find("#" + delDuties[i]).remove();	
		}
		var duties = $("#periodTable tr");
		for (var i = 1; i < duties.length; i++) {
			/*
			duties[i].id="periodTableTr_"+(i - 1);
			
			var duty = $(duties[i]).find("input");
			var dutySelect = $(duties[i]).find("select");
			for (var j = 0; j < duty.length; j++) {
				if (duty[j].type == "text" || duty[j].type == "hidden" ) {
					var oldId = duty[j].id;
					var oldName = duty[j].name;
					
					if(""==oldId.substring(0, oldId.indexOf("[") + 1)){
						duty[j].id = 'geProDutyAttrAllowVals' + (i - 1);
						duty[j].name =  'attributeValue' + (i - 1);
					}else{
						duty[j].id = oldId.substring(0, oldId.indexOf("[") + 1) + (i - 1) +  oldId.substring(oldId.indexOf("]"));
						duty[j].name =  oldName.substring(0, oldName.indexOf("[") + 1) + (i - 1) +  oldName.substring(oldName.indexOf("]"));
					}
					
					
				} else if (duty[j].type == "checkbox") {
					duty[j].id = "check" +  ( i - 1);
					duty[j].value = "periodTableTr_" +  (i - 1);
				}
			}
			
			for (var k = 0; k < dutySelect.length; k++) {
				var oldId = dutySelect[k].id;
				var oldName = dutySelect[k].name;
				dutySelect[k].id = oldId.substring(0, oldId.indexOf("[") + 1) + (i - 1) +  oldId.substring(oldId.indexOf("]"));
				dutySelect[k].name =  oldName.substring(0, oldName.indexOf("[") + 1) + (i - 1) +  oldName.substring(oldName.indexOf("]"));
			}
			*/
		}
	}
});

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

// ���ֵ��õ���λ
function getDataFromDic(codeSelect, codeType){
	$.ajax({
		   cache :false,
		   type: "POST",
		   url: '${ctx}/productManage/findDataFromDic.do',
		   data: {
				"codeType":codeType
			},//�������
		   dataType:"json",
		   success: function(data){
			   var dd = document.getElementById(codeSelect);
			   $(dd).empty();
			   //$(dd).append("<option value=''>--��ѡ��--</option>");
		    	for(var i = 0; i < data.mapList.length; i++){
		    		var baObj = data.mapList[i];
		    		$(dd).append("<option value='" + baObj.codeCode + "'>" + baObj.codeName + "</option>");
		    	}
			},
		   error:function(XMLHttpRequest, textStatus, errorThrown){
			   
		   }
	});
}
</script>
</body>
</html>