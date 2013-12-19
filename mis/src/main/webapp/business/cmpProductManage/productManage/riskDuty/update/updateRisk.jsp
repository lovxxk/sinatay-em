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
</head>
<body>
<center>
<form name="frmInput" id="frmInput" action="${ctx}/productManage/updateRisk.do" method="post">
<table id="diretoryAttributeTable" align="center" style="font-size:9pt;width:95%;padding-top:15px;" cellpadding=0 cellspacing=0 border=0>
	<tr  height="30px">
		<td class="td_head" width="30%" nowrap>险种名称：</td>
		<td class="td_body" width="70%"><input style="width: 180px" type="text" id="attrName" name="geProductRisk.productRiskName" maxlength="20" value="${geProductRisk.productRiskName}"></td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>显示顺序：</td>
		<td class="td_body" width="70%"><input style="width: 180px" type="text" id="seqIndex" name="geProductRisk.seqIndex" maxlength="3" value="${geProductRisk.seqIndex}"></td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>是否显示产品责任：</td>
		<td class="td_body" width="70%">
			<select name="geProductRisk.isshowProductDuty">
				<option value="">--请选择--</option>
				<option ${geProductRisk.isshowProductDuty==1? 'selected':''} value="1">是</option>
				<option ${geProductRisk.isshowProductDuty==0? 'selected':''} value="0">否</option>
			</select>
			
			<%--<input name="geProductRisk.isshowProductDuty" ${geProductRisk.isshowProductDuty==1? 'checked':''} type="radio" value="1"/>是&nbsp;<input name="geProductRisk.isshowProductDuty" ${geProductRisk.isshowProductDuty==0? 'checked':''} type="radio" value="0"/>否 --%>
		</td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>是否销售：</td>
		<td class="td_body" width="70%">
			<select name="geProductRisk.saleFlag">
				<option value="">--请选择--</option>
				<option ${geProductRisk.saleFlag==1? 'selected':''} value="1">是</option>
				<option ${geProductRisk.saleFlag==0? 'selected':''} value="0">否</option>
			</select>
			
			<%--<input name="geProductRisk.saleFlag" ${geProductRisk.saleFlag==1? 'checked':''} type="radio" value="1"/>是&nbsp;<input name="geProductRisk.saleFlag" ${geProductRisk.saleFlag==0? 'checked':''} type="radio" value="0"/>否 --%>
		</td>
	</tr>
	
	<tr height="30px">
		<td colspan="2">
			<table width="200" align="center">
				<tr>
					<td  id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" style="width: 60px;padding: 5 7 0 0" nowrap>保存</td>
					<td  id="updateButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" style="width: 60px;padding: 5 7 0 0" onclick="doClear();" >重置</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<input type="hidden"  name="geProductRisk.serialNo" value="${geProductRisk.serialNo}">

</form>
</center>
<script type="text/javascript">
var addReslut = "${addReslut}";

if (addReslut == "success") {
	alert("险种编辑成功！");
} else if (addReslut == "failure") {
	alert("险种编辑失败！");
}


$(document).ready(function(){
	//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
	tt.vf.req.add("geProductRisk.productRiskName");
	tt.vf.req.add("geProductRisk.seqIndex");
	new tt.RV().set(new RegExp("^[0-9]{1,3}$"), "只能输入整数（0-999）").add("geProductRisk.seqIndex");
	
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
	document.getElementById("frmInput").reset();
	//parent.document.getElementById("directoryAttribute").src = parent.document.getElementById("directoryAttribute").src;
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
</script>
</body>
</html>