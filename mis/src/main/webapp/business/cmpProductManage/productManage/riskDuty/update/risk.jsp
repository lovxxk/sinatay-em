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

<div style="height: 5px"></div>
<form name="frmInput" id="frmInput" action="${ctx}/productManage/updateRisk.do" method="post">
<table id="diretoryAttributeTable" align="center" style="font-size:9pt;width:95%;padding-top:15px;" cellpadding=0 cellspacing=0 border=0>
	<tr  height="30px">
		<td class="td_head" width="30%" nowrap>�������ƣ�</td>
		<td class="td_body" width="70%">${geProductRisk.productRiskName}</td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>��ʾ˳��</td>
		<td class="td_body" width="70%">${geProductRisk.seqIndex}</td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>�Ƿ���ʾ��Ʒ���Σ�</td>
		<td class="td_body" width="70%">
			<c:if test="${geProductRisk.isshowProductDuty==1}">��</c:if><c:if test="${geProductRisk.isshowProductDuty==0}">��</c:if>
		</td>
	</tr>
	<tr height="30px">
		<td class="td_head" width="30%" nowrap>�Ƿ����ۣ�</td>
		<td class="td_body" width="70%">
			<c:if test="${geProductRisk.saleFlag==1}">��</c:if><c:if test="${geProductRisk.saleFlag==0}">��</c:if>
		</td>
	</tr>
	

</table>

<input type="hidden"  name="geProductRisk.serialNo" value="${geProductRisk.serialNo}">

</form>
</center>
<script type="text/javascript">
var addReslut = "${addReslut}";

if (addReslut == "success") {
	alert("���ֱ༭�ɹ���");
} else if (addReslut == "failure") {
	alert("���ֱ༭ʧ�ܣ�");
}


$(document).ready(function(){
	//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	tt.vf.req.add("geProductRisk.productRiskName");
	tt.vf.req.add("geProductRisk.seqIndex");
	new tt.RV().set(new RegExp("^[0-9]{1,3}$"), "ֻ������������0-999��").add("geProductRisk.seqIndex");
	
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
</script>
</body>
</html>