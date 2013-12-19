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
	</style>
</head>
<body>
<center>
<div style="height: 5px"></div>
<form name="frmInput" id="frmInput" action="${ctx}/productManage/updateDuty.do" method="post">
<table id="diretoryAttributeTable" align="center" style="font-size:9pt;width:95%;padding-top:15px;" cellpadding=0 cellspacing=0 border=0>
	
	<tr  height="30px">
		<td class="td_head" width="30%" nowrap>责任名称：</td>
		<td class="td_body" width="70%">${geProductDuty.productDutyName}</td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>显示顺序：</td>
		<td class="td_body" width="70%">${geProductDuty.seqIndex}</td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>是否销售：</td>
		<td class="td_body" width="70%">
			<c:if test="${geProductDuty.saleFlag==1}">是</c:if><c:if test="${geProductDuty.saleFlag==0}">否</c:if>
		</td>
	</tr>

	<tr height="30px">
		<td class="td_head" width="30%" nowrap>保额配置：</td>
		<td class="td_body" width="70%">
			<c:if test="${allowValueSize >0}">
			<div style="padding-top:5px;width:485px;">
					<table border="1" cellspacing="0" cellpadding="0" id="periodTable">
						<tr>
							<td class="periodTable_title" width="200" valign="middle">保额</td>
							<td class="periodTable_title" width="200" valign="middle">单位</td>
						</tr>
						<c:forEach items="${geProductDuty.geProDutyAttrAllowVals}"  var="productAttrAllowValue" step="1" varStatus="status">
							<tr height="25">
								<td style="text-align: center" class="periodTable_body" width="200"  nowrap>
									${productAttrAllowValue.attributeValue}
								</td>
								<td style="text-align: center" class="periodTable_body" width="200"  nowrap>
									${productAttrAllowValue.attributeTypeValue}
								</td>
						 	 </tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
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
	alert("责任编辑成功！");
} else if (addReslut == "failure") {
	alert("责任编辑失败！");
}


$(document).ready(function(){
	//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
	tt.vf.req.add("geProductDuty.productDutyName");
	tt.vf.req.add("geProductDuty.seqIndex");
	new tt.RV().set(new RegExp("^[0-9]{1,3}$"), "只能输入整数（0-999）").add("geProductDuty.seqIndex");
	
	$("attrName").blur(function(){
		validateValue(this);
	});
	$("seqIndex").blur(function(){
		validateValue(this);
	});
	
	//表单提交
	$("#addButtonSubmit").click(function(){
		if(!tt.validate()){
			return false;
		}else{
			$("#frmInput").submit();
		}
	});

});

function doClear(){
	parent.document.getElementById("directoryAttribute").src = parent.document.getElementById("directoryAttribute").src;
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
});
$("#addPeriod").click(function() {
	var addNumber = $("#periodTable tr").length - 1;
	var dutyStr = "<tr height=\"25\" id=\"periodTableTr_" + addNumber + "\">" + 
					"<td class=\"periodTable_body\" width=\"48\" valign=\"top\" nowrap><input id=\"check" + addNumber + "\" type=\"checkbox\" style=\"width:48px;\" onclick=\"checkSingleRow()\" name=\"checkChild\" value=\"periodTableTr_" + addNumber + "\" /></td>" +
					"<td class=\"periodTable_body\" width=\"200\" valign=\"top\" nowrap>" +
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeKind\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeKind\" value=\"givePrice\" /> " + 
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeName\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeName\" value=\"保额\" /> " +
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].allowValuesType\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].allowValuesType\" value=\"02\" /> " +
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeType\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeType\" value=\"givePriceType\" /> " +
					"<input type=\"hidden\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].geProductDuty.serialNo\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].geProductDuty.serialNo\" value=\"${geProductDuty.serialNo}\" /> " +
					"<input type=\"text\" id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeValue\" style=\"width:170px;\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeValue\" value=\"\" /> " + 
					"</td>" +
					"<td class=\"periodTable_body\" width=\"200\" valign=\"top\" nowrap>" + 
					"<select id=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeTypeValue\" style=\"width:170px;\" name=\"geProductDuty.geProDutyAttrAllowVals[" + addNumber+ "].attributeTypeValue\">" +
					"</select>" + 
					"</td>" +
				  "</tr>"
	$("#periodTable").append(dutyStr);
	getDataFromDic("geProductDuty.geProDutyAttrAllowVals[" + addNumber + "].attributeTypeValue", "InsuredAmountUnit");
});
$("#delPeriod").click(function() {
	var dutyNumber = $("#periodTable tr").length -1 ;
	var checkedCount = $("#count").val();
	if (dutyNumber == checkedCount) {
		alert("不能删除所有的保险期间配置！");
	} else {
		var idStr = $("#idStr").val();
		var delDuties = idStr.split(",");
		for (var i = 0 ; i < delDuties.length; i++) {
			$("#periodTable").contents().find("#" + delDuties[i]).remove();	
		}
		var duties = $("#periodTable tr");
		for (var i = 1; i < duties.length; i++) {
			var duty = $(duties[i]).find("input");
			var dutySelect = $(duties[i]).find("select");
			for (var j = 0; j < duty.length; j++) {
				if (duty[j].type == "text" || duty[j].type == "hidden" ) {
					var oldId = duty[j].id;
					var oldName = duty[j].name;
					duty[j].id = oldId.substring(0, oldId.indexOf("[") + 1) + (i - 1) +  oldId.substring(oldId.indexOf("]"));
					duty[j].name =  oldName.substring(0, oldName.indexOf("[") + 1) + (i - 1) +  oldName.substring(oldName.indexOf("]"));
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

// 从字典表得到单位
function getDataFromDic(codeSelect, codeType){
		$.ajax({
			   cache :false,
			   type: "POST",
			   url: '${ctx}/productManage/findDataFromDic.do',
			   data: {
					"codeType":codeType,
				},//传入参数
			   dataType:"json",
			   success: function(data){
				   var dd = document.getElementById(codeSelect);
				   $(dd).empty();
				   $(dd).append("<option value=''>--请选择--</option>");
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