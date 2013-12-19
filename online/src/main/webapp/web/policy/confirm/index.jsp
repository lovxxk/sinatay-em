<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		
		<title>ȷ�϶�������̩�����̳�</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		<script src="${ctx}/global/js/jquery/jquery.checkbox.js" type="text/javascript"></script>
		
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
		<link href="${ctx}/resources/css/policy/confirm.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/policy/confirm.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/user/alert_insurance_select.css" rel="stylesheet" type="text/css"/>
	</head>

	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="confirm.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/policy/confirm']).send();
			$("#confirm_buy").click(function(){
				_ga.push(['_trackEvent', '�˶Զ���', 'ȷ�Ϲ���']).send();
			});
			$("#return_edit").click(function(){
				_ga.push(['_trackEvent', '�˶Զ���', '�����޸�']).send();
			});
			_hm.push(['_trackPageview','/web/policy/confirm']).send();
		</script>
	</body>
</html>

<script>
var JsonSTR = "${JsonSTR}";
// console.log(JsonSTR);
if(JsonSTR!=null && JsonSTR.length!=0){
	insureFlowDto = eval("("+JsonSTR+")");
}
$(document).ready(function(){
	$('#confirm_buy').click(function(){
		toPay();
	});
	
	initElement();
	initPolicyDisplay();
// 	console.log($("#geInformBookNum").val());
	if($("#geInformBookNum").val() > 0){
		$("#inform").hide();
	}else{
		$("#inform").show();
	}
	
	if(insureFlowDto.quoteMain.productCode != '00115600'){
		$("#lcspan").hide();
		$("#lca").hide();
	}
});

/**
 * �����˱��ȴ���
 */
var test;
var loading = new Sinosoft.LoadingDialog({
	contentStr: '�����ĵȴ�',
	titleStr:'',
	okStr:'',
	noCancel: true,
	closeFunc:function(){
	},
	waitFunc:function(){
		return test;
	}
});

/**
 * ������ʾ��
 */
function alret(parameter,subContentStr){
	Sinosoft.alert({
		contentStr: parameter,
		subContentStr:subContentStr,
		width:480,
		okStr: 'ȷ��',
		cancelStr: 'ȡ��',
		cancelBtnShow:false	
	});
}

/**�����޸�*/
function returnEdit(){
	$("#return_edit").attr({disabled:"disabled"});
	$.ajax({
		type: "POST",
		url:contextRootPath+"/sale/toReturnEditInsuranceInfo.do",
		dataType : 'json',
		data:{"insureFlowDto":insureFlowDto},
		success: function(data){
			if(data == "false"){
// 				alert("�����쳣�����Ժ�����");
				$("#return_edit").removeAttr("disabled");
			}else{
				insureFlowDto = data;
				$("#return_edit").removeAttr("disabled");
				$("#quoteUrlFlag").val("inputInsure");
				$("#quoteJsonSTR").val(JSON.stringify(insureFlowDto));
				$("#productCode").val(data.productCode);
				$("#policy_confirm").attr("action","${ctx}/sale/obtainDataForInput.do?productCode="+data.productCode);
				$("#policy_confirm").submit();
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
// 			alert("�����쳣�����Ժ�����");
			Sinosoft.alert({
				contentStr: "�����쳣�����Ժ�����",
				width:480,
				okStr: 'ȷ��',
				okFunc:function(){
					
				}
			});
			$("#return_edit").removeAttr("disabled");
		}
	}); 
}

/**��ʼ��ҳ����Ϣ*/
function initElement(){
	/** update app */
	$('#policy_confirm #cfm_appName').html(insureFlowDto.quoteMain.quoteApplicant.fullName);
	var idType="",gender="";
	switch(insureFlowDto.quoteMain.quoteApplicant.idType){
		case 0:idType="���֤";break;
		case 1:idType="����";break;
		case 2:idType="����֤";break;
		case 8:idType="����";break;
	}
	switch(insureFlowDto.quoteMain.quoteApplicant.gender){
		case 0:gender="��";break;
		case 1:gender="Ů";break;
	}
	$('#policy_confirm #cfm_appIdType').html(idType);
	$('#policy_confirm #cfm_appIdNo').html(insureFlowDto.quoteMain.quoteApplicant.idNumber);
	$('#policy_confirm #cfm_appSex').html(gender);
	$('#policy_confirm #cfm_appBirthday').html(insureFlowDto.quoteMain.quoteApplicant.birthDate);
	$('#policy_confirm #cfm_appMobilePhone').html(insureFlowDto.quoteMain.quoteApplicant.mobilePhoneNumber);
	$('#policy_confirm #cfm_appHomePhone').html(insureFlowDto.quoteMain.quoteApplicant.phoneNumber);
	$('#policy_confirm #cfm_appComPhone').html(insureFlowDto.quoteMain.quoteApplicant.officePhoneNumber);
	$('#policy_confirm #cfm_appEmail').html(insureFlowDto.quoteMain.quoteApplicant.email);
	$('#policy_confirm #cfm_appAddress').html(insureFlowDto.quoteMain.quoteApplicant.addressLines);
	$('#policy_confirm #cfm_appZipCode').html(insureFlowDto.quoteMain.quoteApplicant.postalCode);
	$('#policy_confirm #cfm_appProvince').html(insureFlowDto.quoteMain.quoteApplicant.provinceName);
	$('#policy_confirm #cfm_appCity').html(insureFlowDto.quoteMain.quoteApplicant.cityName);
	$('#policy_confirm #cfm_appArea').html(insureFlowDto.quoteMain.quoteApplicant.countyName);
	$('#policy_confirm #cfm_appIDEndDate').html(insureFlowDto.quoteMain.quoteApplicant.idExpDate);
	
	/** add insured */
	var insuredLength = insureFlowDto.quoteMain.quoteInsureds.length;
	re = new RegExp("#index", "g");
	for(var i = 0; i < insuredLength; i++){
		$("#policy_confirm .inslicant_info").append($("#confirmInsuredDemo").html().replace(re, i));
	}
	for(var i=0; i<insuredLength; i++){
		var relatedToApplicant="";
		switch(insureFlowDto.quoteMain.quoteInsureds[i].idType){
			case 0:idType="���֤";break;
			case 1:idType="����";break;
			case 2:idType="����֤";break;
			case 8:idType="����";break;
		}
		switch(insureFlowDto.quoteMain.quoteInsureds[i].gender){
			case 0:gender="��";break;
			case 1:gender="Ů";break;
		}
		switch(insureFlowDto.quoteMain.quoteInsureds[i].relatedToApplicant){
			case 0:relatedToApplicant="����";break;
			case 24:relatedToApplicant="��ĸ";break;
			case 7:relatedToApplicant="����";break;
			case 25:relatedToApplicant="��Ů";break;
			case 5:relatedToApplicant="����";break;
		}
		$('#policy_confirm #cfm_insRelationToApp'+i).html(relatedToApplicant);
		$('#policy_confirm #cfm_insName'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].fullName);
		$('#policy_confirm #cfm_insIdType'+i).html(idType);
		$('#policy_confirm #cfm_insIdNo'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].idNumber);
		$('#policy_confirm #cfm_insSex'+i).html(gender);
		$('#policy_confirm #cfm_insBirthday'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].birthDate);
		$('#policy_confirm #cfm_insMobilePhone'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].mobilePhoneNumber);
		$('#policy_confirm #cfm_insHomePhone'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].phoneNumber);
		$('#policy_confirm #cfm_insComPhone'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].officePhoneNumber);
		$('#policy_confirm #cfm_insEmail'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].email);
		$('#policy_confirm #cfm_insAddress'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].addressLines);
		$('#policy_confirm #cfm_insZipCode'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].postalCode);
		$('#policy_confirm #cfm_insOccupation'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].occupationDescription);
		$('#policy_confirm #cfm_insProvince'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].provinceName);
		$('#policy_confirm #cfm_insCity'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].cityName);
		$('#policy_confirm #cfm_insArea'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].countyName);
		$('#policy_confirm #cfm_insIDEndDate'+i).html(insureFlowDto.quoteMain.quoteInsureds[i].idExpDate);
	}
	
	/** add bnf */
	if("${geSaleProduct.isSupportBeneficiary}" == '1'){
		var bnfLength = insureFlowDto.quoteMain.quoteBeneficiaries.length;
		for(var i = 0; i < bnfLength; i++){
			$("#policy_confirm .bnflicant_info").append($("#confirmbnfDemo").html().replace(re, i));
		}
		for(var i = 0; i < bnfLength; i++){
			var relatedToInsured;
			switch(insureFlowDto.quoteMain.quoteBeneficiaries[i].idType){
				case 0:idType="���֤";break;
				case 1:idType="����";break;
				case 2:idType="����֤";break;
				case 8:idType="����";break;
			}
			switch(insureFlowDto.quoteMain.quoteBeneficiaries[i].gender){
				case 0:gender="��";break;
				case 1:gender="Ů";break;
			}
			switch(insureFlowDto.quoteMain.quoteBeneficiaries[i].relatedToInsured){
				case 0:relatedToInsured="����";break;
				case 24:relatedToInsured="��ĸ";break;
				case 7:relatedToInsured="����";break;
				case 25:relatedToInsured="��Ů";break;
				case 5:relatedToInsured="����";break;
			}
			$('#policy_confirm #cfm_bnfRelationToPIns'+i).html(relatedToInsured);
			$('#policy_confirm #cfm_bnfName'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].fullName);
			$('#policy_confirm #cfm_bnfIdType'+i).html(idType);
			$('#policy_confirm #cfm_bnfIdNo'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].idNumber);
			$('#policy_confirm #cfm_bnfSex'+i).html(gender);
			$('#policy_confirm #cfm_bnfBirthday'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].birthDate);
			$('#policy_confirm #cfm_bnfMobilePhone'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].mobilePhoneNumber);
			$('#policy_confirm #cfm_bnfHomePhone'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].phoneNumber);
			$('#policy_confirm #cfm_bnfComPhone'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].officePhoneNumber);
			$('#policy_confirm #cfm_bnfEmail'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].email);
			$('#policy_confirm #cfm_bnfAddress'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].addressLines);
			$('#policy_confirm #cfm_bnfZipCode'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].postalCode);
			$('#policy_confirm #cfm_bnfOrder'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].beneficiaryOrder);
			$('#policy_confirm #cfm_bnfRate'+i).html(insureFlowDto.quoteMain.quoteBeneficiaries[i].interestPercent+"%");
		}
	}else{
		$("#policy_confirm .bnflicant_info").append("<div class='title'>��������Ϣ</div><div class='info_row'><div class='info_col'><div class='name'>�뱻���˹�ϵ��</div><div class='value' id='cfm_bnfRelationToPIns'>����</div></div></div>");
	}
	
	$('#productName').val(insureFlowDto.quoteMain.productName);
	$('#grossPremium').val(insureFlowDto.quoteMain.productName);
}

/**��ʼ������չʾ*/
function initPolicyDisplay(){
	$("#productName").text(insureFlowDto.quoteMain.productName);
	$("#faceAmount").text("��"+(insureFlowDto.quoteMain.faceAmount==null?"0":insureFlowDto.quoteMain.faceAmount));
	$("#grossPremium").text("��"+(insureFlowDto.quoteMain.grossPremium==null?insureFlowDto.quoteMain.premium:insureFlowDto.quoteMain.grossPremium));
	$("#specifyStartDate").text(insureFlowDto.quoteMain.inceptionDate);
	$("#comboName").text(insureFlowDto.quoteMain.comboName);
	$("#campaignName").text(insureFlowDto.quoteMain.campaignName);
	$("#unitCount").text((insureFlowDto.quoteMain.unitCount==null?"1":insureFlowDto.quoteMain.unitCount)+" ��");
	var tag = "";
	switch(insureFlowDto.quoteMain.benefitPeriodType){
		case 9:tag="����";break;
		case 42:tag="��";break;
		case 41:tag="��";break;
		case 40:tag="��";break;
		case 30:tag="��";break;
		case 20:tag="��";break;
		case 18:tag="����";break;
	}
	$("#benefitPeriod").text(insureFlowDto.quoteMain.benefitPeriod+" "+tag);
	switch(insureFlowDto.quoteMain.paymentDurationMode){
		case 1:tag="��";break;
		case 2:tag="��";break;
		case 3:tag="��";break;
		case 4:tag="����";break;
		case 5:tag="�����ɷ�";break;
		case 6:tag="���ⷽʽ";break;
		case 7:tag="����/����";break;
		case 8:tag="�����/��";break;
	}
	$("#paymentDuration").text(insureFlowDto.quoteMain.paymentDuration+" "+tag);
}

/**ȷ��֧��*/
function toPay(){
	/**read legalNotice*/
	var readOver = true;
	if($("#legalNotice").val() == undefined || $("#legalNotice").val() != '1'){
		readOver = false;
	}
	if(readOver){
		exit();
	}else{
		Sinosoft.alert({
			contentStr: "����ȷ�����Ķ�Ͷ��������Ϣ",
			width:480,
			okStr: 'ȷ��',
			okFunc:function(){
				
			}
		});
// 		alert("����ȷ�����Ķ�Ͷ��������Ϣ");
	}
	
}

/**�������㵥*/
function exit(){
	loading.open();
	$.ajax({
		type: "POST",
		url:contextRootPath+"/sale/updateQuoteStep.do?step=3&quoteNo="+insureFlowDto.quoteMain.quoteNo,
		dataType : 'json',
		data:{"insureFlowDto":insureFlowDto},
		success: function(data){
			if(data.result != 'success'){
				loading.close();
				alret("������ʾ",data.result);
			}else{
				$("#eId").val(insureFlowDto.quoteMain.eid);
				$("#confirmProposalSID").val(insureFlowDto.quoteMain.proposalSID);
				$("#confirmProposalSIDForm").submit();
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			loading.close();
			alret("������ʾ","�����쳣�����Ժ����ԣ�");
		}
	}); 
}

function getAuthorize(type){
	if(type == 1 && insureFlowDto.quoteMain.productCode == "00136"){ 
		window.open("${ctx }/web/common/accidentInsuranceNotice/index.jsp");
	}else if(type == 1 && insureFlowDto.quoteMain.productCode != "00136"){
		window.open("${ctx }/web/common/insuranceNotice/index.jsp");
	}
	if(type == 2) window.open("${ctx }/web/common/authorize/index.jsp");
	if(type == 3) window.open("${ctx }/web/common/insuranceTipBook/index.jsp");
	if(type == 4) window.open("${ctx }/web/common/financialTipBook/index.jsp");
}
function getProductClause(type){
// 	console.log("getProductClause()...");
	var productRisks = $("#productRisks").html();
	var json_risks = eval("(" + productRisks + ")");
// 	console.log("json_risks: "+json_risks);
	new Sinosoft.InteractiveDialog(
		{
			layout : loadInsuranceSelect(json_risks),
			width : 560,
			okStr : 'ȷ��',
			cancelStr : 'ȡ��',
			okFunc : function() {
				var riskCode=$(".selected").attr('value');
				if(type == 1){
					window.open("${ctx}/web/common/clauseRead.jsp?productCode=${productCode}&riskCode="+riskCode);
				}
			},
			cancelFunc : function() {
			}
		}).open();
}

//���������б�
function loadInsuranceSelect(json_risks) {
	var str='<div class="insurance_select">'
		+ '<div class="select_title">��ѡ�����֣�</div>';
	for(var i=0;i<json_risks.length;i++){
		var json_risk=json_risks[i];
		//�������ֺ���
		if(i==0){
			str +='<div class="select_item selected" value="'+json_risk.productRiskCode+'">'
			//�������ֱ�����
			str +='  <div class="select_radio" id="insurance1"></div>'+json_risk.productRiskName+'</div>';
		}else{
			str +='  <div class="select_item" value="'+json_risk.productRiskCode+'">'
			str +='  <div class="select_radio" id="insurance1"></div>'+json_risk.productRiskName+'</div>';
		}
	}
	str = str + '</div>';
	var insuranceSelect=$(str);
	//�ı���ʽ
	insuranceSelect.find('.select_item').click(function() {
		$(this).addClass('selected');
		$(this).siblings().removeClass('selected');
	});
	return insuranceSelect;
}
</script>