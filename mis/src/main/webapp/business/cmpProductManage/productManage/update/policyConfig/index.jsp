<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx }/global/js/util/util.js" defer="defer"></script>
<title>电子商务管理系统-保单配置项</title>
<style type="text/css">
	 #policyConfig {
	 	width: 100%;
	 }
	 #policyConfigLeft {
	 	width:150px;
	 	display:inline;
	 	float:left;
	 }
	 .firstPolicyConfigItems {
	 	border:1px #DCDDDE solid;
	 	border-right:none;
	 	line-height:30px;
	 	vertical-align:middle;
	 	height:30px;
	 	width:150px;
	 }
	 .otherPolicyConfigItems {
		border:1px #DCDDDE solid;
		border-top:none;
	 	border-right:none;
	 	line-height:30px;
	 	vertical-align:middle;
	 	height:30px;
	 	width:150px;
	 	
	 }
	 #policyConfigRight {
	 	border:1px #DCDDDE solid;
	 	height:450px;
	 	float:right;
	 }
</style>
</head>
<body>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/productManage/saveOrUpdatePolicyConfig.do" target="myFrame">
<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode' />">
	<div id="policyConfig" style="cursor:pointer;">
		<div id="policyConfigLeft" style="widht:150px;">
			<div class="firstPolicyConfigItems" id="configPolicy" onclick="changeBackGround(this.id);policyConfig('${ctx}/productManage/toConfigPolicyDisplay.do', 'coreProductCode=${geProductMain.coreProductCode}');">保单展示配置项</div>
			<div class="firstPolicyConfigItems" id="configApplicant" onclick="changeBackGround(this.id);policyConfig('${ctx}/productManage/toConfigApplicant.do', 'coreProductCode=${geProductMain.coreProductCode}');">投保人配置项</div>
			<div class="otherPolicyConfigItems" id="configInsured" onclick="changeBackGround(this.id);policyConfig('${ctx}/productManage/toConfigInsured.do', 'coreProductCode=${geProductMain.coreProductCode}');">被保人配置项</div>
			<s:if test="geProductMain.isSupportBeneficiary==\"1\" ">
				<div class="otherPolicyConfigItems" id="configBeneficiary" onclick="changeBackGround(this.id);policyConfig('${ctx}/productManage/toConfigBeneficiary.do', 'coreProductCode=${geProductMain.coreProductCode}');">受益人配置项</div>
			</s:if>
			<c:if test="${geProductMain.isSupportEmergency eq '01'}">
				<div class="otherPolicyConfigItems" id="configEmergency" onclick="changeBackGround(this.id);policyConfig('${ctx}/productManage/toConfigEmergency.do', 'coreProductCode=${geProductMain.coreProductCode}');">紧急联系人配置项</div>
			</c:if>
			<s:if test="geProductMain.isPaper==\"1\" || geProductMain.isInvoice=='02'">
				<div class="otherPolicyConfigItems" id="configAddressee" onclick="changeBackGround(this.id);policyConfig('${ctx}/productManage/toConfigAddressee.do', 'coreProductCode=${geProductMain.coreProductCode}');">收件人配置项</div>
			</s:if>
		</div>
		<div id="policyConfigRight">
			<iframe id="policyConfigItem"  style="ovherflow-x:hidden;height:100%;width:100%;" name="policyConfigItem"  src="" frameborder="0" scrolling="auto"></iframe>
		</div>
	</div>
</form>
</div>
</body>
<script type="text/javascript">
$(document).ready(function (){
	$(".firstPolicyConfigItems").click();
	if (client.browser.ie > 0) {
		$("#policyConfigRight").css("width",($("#policyConfig").width() - 150));	
	} else if (client.browser.firefox > 0) {
		$("#policyConfigRight").css("width",($("#policyConfig").width() - 153));
	}
	
});
function policyConfig(url, param){
	document.getElementById("policyConfigItem").src = url + "?" +  param;
}
function changeBackGround(obj){
	$("#policyConfigLeft div").each(function (){
		if (this.id == obj) {
			$(this).css("background-color", "#E9F8FF");
		} else {
			$(this).css("background-color", "#FFFFFF");
		}
	});
}
</script>
</html>
