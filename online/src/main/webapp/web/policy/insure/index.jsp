<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>��д��������̩�����̳�</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script src="${ctx }/global/js/My97DatePicker4.7/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/jquery/jquery.checkbox.js" type="text/javascript"></script>
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/policy/policy.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/policy/policy.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/citySelect.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/policy/insure.js" type="text/javascript"></script>
		<script src="${ctx }/global/js/formvalidator4.1.3/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${ctx }/global/js/formvalidator4.1.3/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/service/alert_service.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="policy.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/policy/insure']).send();
			$("#save_info").click(function(){
				_ga.push(['_trackEvent', '��д����', '������Ϣ']).send();
			});
			$("#next_step").click(function(){
				_ga.push(['_trackEvent', '��д����', '��һ��']).send();
			});
			_hm.push(['_trackPageview','/web/policy/insure']).send();
		</script>
	</body>
</html>
<script>
var JsonSTR = "${JsonSTR}";
// console.log(JsonSTR);
if(JsonSTR!=null && JsonSTR.length!=0){
	insureFlowDto = eval("("+JsonSTR+")");
}

$("#productLink").attr("href",contextRootPath+"/sale/toQuote.do?eid="+insureFlowDto.quoteMain.eid);

/**
 * �����˱��ȴ���
 */
var test;
var loading = new Sinosoft.LoadingDialog({
	contentStr: '��ȴ��˱����',
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
function alretAge(parameter,subContentStr){
	Sinosoft.alert({
		contentStr: parameter,
		subContentStr:subContentStr,
		width:480,
		okStr: '�����޸�',
		cancelStr: 'ȡ��',
		cancelBtnShow:false,
		okFunc:function(){
			window.location.href = contextRootPath+"/sale/toQuote.do?eid="+insureFlowDto.quoteMain.eid;
		}
	});
}

/**��������ϢĬ��չ��*/
$('.click').text('�������');
$('.click_box').text('-');
$('.click_box').css('text-indent','3px');
$('.insured_info .input_area.insured').show();

//���Ԫ��У��
function addCheck(){
	//��ʼ��Ͷ����Ԫ��У��
	initCheck(1,"app","applicant_info");
	//��ʼ��������Ԫ��У��
	initCheck(2,"ins","insured_info");
	//��Ч����
	if($("#policy_fill #effect_start").parent().find("span[class='required']").length > 0){
		$("#policy_fill #effect_start").formValidator().inputValidator({regExp:regexEnum.date,onError: "��ѡ����Ч����"}).functionValidator({fun:function(inceptionDate){
			if(inceptionDate == null || inceptionDate == ''){
				return "��ѡ����Ч����";
			}
		}});
	}
	//�Ƽ��� reference_phone
// 	$("#policy_fill #reference_phone").formValidator().functionValidator({fun:function(referencePhone){
// 		var mobileTest = /^13[0-9]{9}|15[0-9]{9}|18[0-9]{9}|147[0-9]{8}$/;
// 		if(referencePhone != null && referencePhone != ''){
// 			var result = mobileTest.test(referencePhone);
// 			if(!result){
// 				return "��������ȷ���ֻ�����";	
// 			}
// 			if($('#policy_fill #reference_type').val() == null || $('#policy_fill #reference_type').val() == '')
// 				return "��ѡ���Ƽ�������";	
// 			$.ajax({
// 				type: "POST",
// 				async: false,
// 				url:contextRootPath+"/sale/countMobilePhoneNumber.do",
// 				dataType : 'json',
// 				data:{"referencePhone":referencePhone},
// 				success: function(data){
// 					if(data.count < 1) result = false;
// 				},
// 				error:function(XMLHttpRequest, textStatus, errorThrown){
// 					console.log("�����쳣�����Ժ����ԣ�");
// 				}
// 			}); 
// 			if(!result) return "�Ƽ����ֻ��Ų�����";
// 		}
// 	}});
}

var contacts = [];
var references = [];
var saveTopInsureds = [];

/**
 * ��ʼ��Ԫ������
 */
initElementData();

if($("#topInsuredNum").val() < 1){
	$("#topInsList").hide();
}else{
	$("#topInsList").show();
}

/**
 * �ж��Ƿ�Ϊ����Ͷ��������Ͷ����ʼ��Ͷ�������ݣ�����Ͷ����ʼ����������
 */
if(insureFlowDto.insuranceType != "1"){
	initAppInfo();
}else{
	revertInputInsuranceInfo();
}

//����У����
$.formValidator.initConfig({validatorGroup:"1",formID:"policy_fill",debug:false,submitOnce:true,theme:'Default'});
//���Ԫ��У��
addCheck();

/**����Ͷ����ԭ����*/
function revertInputInsuranceInfo(){
	revertAppInfo();
	revertInsured();
	revertBnf($("#isSupportBeneficiary").val());
	/**�Ƽ�����Ϣ*/
	$('#policy_fill #reference_type').val(insureFlowDto.quoteMain.recommendType);
	$('#policy_fill #reference_phone').val(insureFlowDto.quoteMain.recommend);
// 	revertRecommend(insureFlowDto.quoteMain.recommendType);
	initPolicyDisplay();
	checkIdCard('ins','','insured_info');
}

/**��ԭͶ��������*/
function revertAppInfo(){
	$('#policy_fill #appName').val(insureFlowDto.quoteMain.quoteApplicant.fullName);
	$('#policy_fill #appIdType').val(insureFlowDto.quoteMain.quoteApplicant.idType);
	$('#policy_fill #appIdNo').val(insureFlowDto.quoteMain.quoteApplicant.idNumber);
	$('#policy_fill #appBirthday').val(insureFlowDto.quoteMain.quoteApplicant.birthDate);
	$('#policy_fill #appSex').val(insureFlowDto.quoteMain.quoteApplicant.gender);
	$('#policy_fill #appEmail').val(insureFlowDto.quoteMain.quoteApplicant.email);
	$('#policy_fill #appMobilePhone').val(insureFlowDto.quoteMain.quoteApplicant.mobilePhoneNumber);
	$('#policy_fill #appComPhone').val(insureFlowDto.quoteMain.quoteApplicant.officePhoneNumber);
	$('#policy_fill #appHomePhone').val(insureFlowDto.quoteMain.quoteApplicant.phoneNumber);
	$('#policy_fill #appAddress').val(insureFlowDto.quoteMain.quoteApplicant.addressLines);
	$('#policy_fill #appZipCode').val(insureFlowDto.quoteMain.quoteApplicant.postalCode);
	$('#policy_fill #appProvince').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.province);
	$('#policy_fill #appCity').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.city);
	$('#policy_fill #appArea').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.county);
	var isBind = $("#isBind").val();
	
	$('#policy_fill #appIdType').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.idType);
	if(insureFlowDto.quoteMain.quoteApplicant.birthDate && insureFlowDto.quoteMain.quoteApplicant.birthDate.indexOf('-') > 0){
		$('#policy_fill #app_year').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.birthDate.split('-')[0]);
		$('#policy_fill #app_month').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.birthDate.split('-')[1]);
		$('#policy_fill #app_day').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.birthDate.split('-')[2]);
	}
	if(insureFlowDto.quoteMain.quoteApplicant.idExpDate && insureFlowDto.quoteMain.quoteApplicant.idExpDate.indexOf('-') > 0){
		$('#policy_fill #app_validity_year').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.idExpDate.split('-')[0]);
		$('#policy_fill #app_validity_month').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.idExpDate.split('-')[1]);
		$('#policy_fill #app_validity_day').data('jSelect').setValue(insureFlowDto.quoteMain.quoteApplicant.idExpDate.split('-')[2]);
	}
	if(insureFlowDto.quoteMain.quoteApplicant.gender == 0){
		if(!$('#applicant_info #male').hasClass('selected')){
			$('#applicant_info #male').addClass('selected');
			$('#applicant_info #male').siblings().removeClass('selected');
			$('#applicant_info #male').parent().find("input[type='hidden']").attr('value',insureFlowDto.quoteMain.quoteApplicant.gender);
		}
	}else if(insureFlowDto.quoteMain.quoteApplicant.gender == 1){
		if(!$('#applicant_info #female').hasClass('selected')){
			$('#applicant_info #female').addClass('selected');
			$('#applicant_info #female').siblings().removeClass('selected');
			$('#applicant_info #female').parent().find("input[type='hidden']").attr('value',insureFlowDto.quoteMain.quoteApplicant.gender);
		}
	}
	
	if(isBind == 'true'){
		initAppModuleEditable(1,"app","applicant_info");
		var gUserName = "${geUserPersonal.userName}";
		var gIdentifyType = "${geUserPersonal.identifyType}";
		var gIdentifyNumber = "${geUserPersonal.identifyNumber}";
		var gBirthday = "${geUserPersonal.birthday}";
		var gSex = "${geUserPersonal.sex}";
		$('#policy_fill #appName').val(gUserName);
		$('#policy_fill #appIdType').val(gIdentifyType);
		$('#policy_fill #appIdNo').val(gIdentifyNumber);
		$('#policy_fill #appBirthday').val(gBirthday);
		$('#policy_fill #appSex').val(gSex);
		$('#policy_fill #appIdType').data('jSelect').setValue(gIdentifyType);
		if(gBirthday && gBirthday.indexOf('-') > 0){
			$('#policy_fill #app_year').data('jSelect').setValue(gBirthday.split('-')[0]);
			$('#policy_fill #app_month').data('jSelect').setValue(gBirthday.split('-')[1]);
			$('#policy_fill #app_day').data('jSelect').setValue(gBirthday.split('-')[2]);
		}
		if(gSex == 0){
			if(!$('#applicant_info #male').hasClass('selected')){
				$('#applicant_info #male').addClass('selected');
				$('#applicant_info #male').siblings().removeClass('selected');
				$('#applicant_info #male').parent().find("input[type='hidden']").attr('value',gSex);
			}
		}else if(gSex == 1){
			if(!$('#applicant_info #female').hasClass('selected')){
				$('#applicant_info #female').addClass('selected');
				$('#applicant_info #female').siblings().removeClass('selected');
				$('#applicant_info #female').parent().find("input[type='hidden']").attr('value',gSex);
			}
		}
	}
	
	$($('#applicant_info #appName,#appIdType,#appIdNo,#appBirthday,'+
			'#app_year,#app_month,#app_day,#appSex,#male,#female,.label_sex,'+
			'#appEmail,#appMobilePhone,#appComPhone,#appHomePhone,#appAddress,#appZipCode,'+
			'#app_validity_year,#app_validity_month,#app_validity_day,#appCounty'
			)).blur(function(){
	 	autoSave();
	});
}

/**��ԭ����������*/
function revertInsured(){
	var insuredLength = insureFlowDto.quoteMain.quoteInsureds.length;
	if(insuredLength > 0){
		$('.click').text('�������');
		$('.click_box').text('-');
		$('.click_box').css('text-indent','3px');
		$('.insured_info .input_area.insured').show();
		
		for(var i in contacts){
			if(contacts[i].getProperty() == insureFlowDto.quoteMain.quoteInsureds[0].topInsuredId){
				contacts[i].check(true);
			}
		}
		$("#policy_fill #topinsId").val(insureFlowDto.quoteMain.quoteInsureds[0].topInsuredId);
		$('#policy_fill #insRelationToApp').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].relatedToApplicant);
		$('#policy_fill #insName').val(insureFlowDto.quoteMain.quoteInsureds[0].fullName);
		$('#policy_fill #insIdType').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].idType);
		$('#policy_fill #insIdNo').val(insureFlowDto.quoteMain.quoteInsureds[0].idNumber);
		$('#policy_fill #insSex').val(insureFlowDto.quoteMain.quoteInsureds[0].gender);
		$('#policy_fill #insBirthday').val(insureFlowDto.quoteMain.quoteInsureds[0].birthDate);
		$('#policy_fill #insMobilePhone').val(insureFlowDto.quoteMain.quoteInsureds[0].mobilePhoneNumber);
		$('#policy_fill #insHomePhone').val(insureFlowDto.quoteMain.quoteInsureds[0].phoneNumber);
		$('#policy_fill #insComPhone').val(insureFlowDto.quoteMain.quoteInsureds[0].officePhoneNumber);
		$('#policy_fill #insEmail').val(insureFlowDto.quoteMain.quoteInsureds[0].email);
		$('#policy_fill #insAddress').val(insureFlowDto.quoteMain.quoteInsureds[0].addressLines);
		$('#policy_fill #insZipCode').val(insureFlowDto.quoteMain.quoteInsureds[0].postalCode);
		$('#policy_fill #insOccupation').val(insureFlowDto.quoteMain.quoteInsureds[0].occupationCode);
		$('#insured_info #selected_job').html(insureFlowDto.quoteMain.quoteInsureds[0].occupationDescription);
		$('#policy_fill #insProvince').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].province);
		$('#policy_fill #insCity').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].city);
		$('#policy_fill #insArea').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].county);
		
		if(insureFlowDto.quoteMain.quoteInsureds[0].birthDate && insureFlowDto.quoteMain.quoteInsureds[0].birthDate.indexOf('-') > 0){
			$('#policy_fill #ins_year').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].birthDate.split('-')[0]);
			$('#policy_fill #ins_month').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].birthDate.split('-')[1]);
			$('#policy_fill #ins_day').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].birthDate.split('-')[2]);
		}
		if(insureFlowDto.quoteMain.quoteInsureds[0].idExpDate && insureFlowDto.quoteMain.quoteInsureds[0].idExpDate.indexOf('-') > 0){
			$('#policy_fill #ins_validity_year').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].idExpDate.split('-')[0]);
			$('#policy_fill #ins_validity_month').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].idExpDate.split('-')[1]);
			$('#policy_fill #ins_validity_day').data('jSelect').setValue(insureFlowDto.quoteMain.quoteInsureds[0].idExpDate.split('-')[2]);
		}
		if(insureFlowDto.quoteMain.quoteInsureds[0].gender == 0){
			if(!$('#insured_info #male').hasClass('selected')){
				$('#insured_info #male').addClass('selected');
				$('#insured_info #male').siblings().removeClass('selected');
				$('#insured_info #male').parent().find("input[type='hidden']").attr('value',insureFlowDto.quoteMain.quoteInsureds[0].gender);
			}
		}else if(insureFlowDto.quoteMain.quoteInsureds[0].gender == 1){
			if(!$('#insured_info #female').hasClass('selected')){
				$('#insured_info #female').addClass('selected');
				$('#insured_info #female').siblings().removeClass('selected');
				$('#insured_info #female').parent().find("input[type='hidden']").attr('value',insureFlowDto.quoteMain.quoteInsureds[0].gender);
			}
		}
		checkInsRelationToApp($('#policy_fill #insRelationToApp'),'0');
	}
}

/**��ԭ������������*/
function revertBnf(isExsitBnf){
	/** add bnf */
	re = new RegExp("#index", "g");
	$("#policy_fill #isExsitBnf").val(isExsitBnf);
	if(isExsitBnf == "1" && insureFlowDto.quoteMain.quoteBeneficiaries.length != 0){
		//��������˴��ڣ�ָ���ĵ�ѡ��ť��ѡ��
		$('#legal').removeClass("selected");
		$('#assign').addClass("selected");
		for(var i = 0; i < insureFlowDto.quoteMain.quoteBeneficiaries.length; i++){
			$(".beneficiary_info").append(addDemoIndex("inputBnfDemo","bnfIndex").replace(re, i));
			initBnfData(i);
		}
		if(isExsitBnf == 0){
			$(".beneficiary_info").find("div[tag='inputBnf']").each(function(i){
				$(this).hide();
				$('#add_beneficiary').hide();
			});
		}else if(isExsitBnf == 1){
			$(".beneficiary_info").find("div[tag='inputBnf']").each(function(i){
				$(this).show();
				$('#add_beneficiary').show();
			});
		}
		for(var i = 0; i < insureFlowDto.quoteMain.quoteBeneficiaries.length; i++){
			$('#policy_fill #benRate'+i).val(insureFlowDto.quoteMain.quoteBeneficiaries[i].interestPercent);
			$('#policy_fill #benName'+i).val(insureFlowDto.quoteMain.quoteBeneficiaries[i].fullName);
			$('#policy_fill #benIdNo'+i).val(insureFlowDto.quoteMain.quoteBeneficiaries[i].idNumber);
			$('#policy_fill #benSex'+i).val(insureFlowDto.quoteMain.quoteBeneficiaries[i].gender);
			$('#policy_fill #benOrder'+i).data('jSelect').setValue(insureFlowDto.quoteMain.quoteBeneficiaries[i].beneficiaryOrder);
			$('#policy_fill #benIdType'+i).data('jSelect').setValue(insureFlowDto.quoteMain.quoteBeneficiaries[i].idType);
			$('#policy_fill #benRelationToPIns'+i).data('jSelect').setValue(insureFlowDto.quoteMain.quoteBeneficiaries[i].relatedToInsured);
			$('#policy_fill #benBirthday'+i).val(insureFlowDto.quoteMain.quoteBeneficiaries[i].birthDate);
			if(insureFlowDto.quoteMain.quoteBeneficiaries[i].birthDate && insureFlowDto.quoteMain.quoteBeneficiaries[i].birthDate.indexOf('-') > 0){
				$('#policy_fill #ben_year'+i).data('jSelect').setValue(insureFlowDto.quoteMain.quoteBeneficiaries[i].birthDate.split("-")[0]);
				$('#policy_fill #ben_month'+i).data('jSelect').setValue(insureFlowDto.quoteMain.quoteBeneficiaries[i].birthDate.split("-")[1]);
				$('#policy_fill #ben_day'+i).data('jSelect').setValue(insureFlowDto.quoteMain.quoteBeneficiaries[i].birthDate.split("-")[2]);
			}
			if(insureFlowDto.quoteMain.quoteBeneficiaries[i].gender == 0){
				if(!$('#policy_fill #male'+i).hasClass('selected')){
					$('#policy_fill #male'+i).addClass('selected');
					$('#policy_fill #male'+i).siblings().removeClass('selected');
					$('#policy_fill #male'+i).parent().find("input[type='hidden']").attr('value',insureFlowDto.quoteMain.quoteBeneficiaries[i].gender);
				}
			}else if(insureFlowDto.quoteMain.quoteBeneficiaries[i].gender == 1){
				if(!$('#policy_fill #female'+i).hasClass('selected')){
					$('#policy_fill #female'+i).addClass('selected');
					$('#policy_fill #female'+i).siblings().removeClass('selected');
					$('#policy_fill #female'+i).parent().find("input[type='hidden']").attr('value',insureFlowDto.quoteMain.quoteBeneficiaries[i].gender);
				}
			}
			checkIdCard("ben",i,"beneficiary_info"+i);
		}
	}
}

/**��ԭ�Ƽ�������*/
function revertRecommend(type){
//  	console.log("revertRecommend: "+type);
	if(type != null && type != '' && type == 1){
		references[0].check(true);
		references[1].check(false);
	}else if(type != null && type != '' && type == 0){
		references[0].check(false);
		references[1].check(true);
	}
}

/**��ʼ������չʾ*/
function initPolicyDisplay(){
	$("#productName").text(insureFlowDto.quoteMain.productName);
	$("#faceAmount").text(insureFlowDto.quoteMain.faceAmount);
	$("#grossPremium").text("��"+insureFlowDto.quoteMain.grossPremium);
	$("#specifyStartDate").text(insureFlowDto.quoteMain.inceptionDate);
	$("#comboName").text(insureFlowDto.quoteMain.comboName);
	$("#campaignName").text(insureFlowDto.quoteMain.campaignName);
	$("#unitCount").text(insureFlowDto.quoteMain.unitCount+" ��");
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
	$('#effect_start').val(insureFlowDto.quoteMain.inceptionDate);
}

/**Ͷ������Ϣ*/
function initAppInfo(){
	var gUserName = "${geUserPersonal.userName}";
	var gIdentifyType = "${geUserPersonal.identifyType}";
	var gIdentifyNumber = "${geUserPersonal.identifyNumber}";
	var gBirthday = "${geUserPersonal.birthday}";
	var gSex = "${geUserPersonal.sex}";
	var gEmail = "${geUserPersonal.email}";
	var gMobliePhone = "${geUserPersonal.mobilePhone}";
	var gOfficePhone = "${geUserPersonal.officePhone}";
	var gHomePhone = "${geUserPersonal.homePhone}";
	var gContactAddress = "${geUserPersonal.contactAddress}";
	var gZipCode = "${geUserPersonal.zipCode}";
	var identifyEffectiveDate = "${geUserPersonal.identifyEffectiveDate}";
	var userRank = "${geUserPersonal.userRank}";
	var income = "${geUserPersonal.income}";
	var provinces = "${geUserPersonal.provinces}";
	var city = "${geUserPersonal.city}";
	var area = "${geUserPersonal.area}";
	var status = "${geUserPersonal.status}";
	var integral = "${geUserPersonal.integral}";
	var alias = "${geUserPersonal.alias}";
	var isBind = "${isBind}";
// 	console.log("gUserName: "+gUserName+", gIdentifyType: "+gIdentifyType+", gIdentifyNumber: "+gIdentifyNumber+", gBirthday: "+gBirthday+", gSex: "+gSex+", gEmail: "+gEmail+", gMobliePhone: "+gMobliePhone);
	
	$('#policy_fill #appName').val(gUserName);
	$('#policy_fill #appIdType').val(gIdentifyType);
	$('#policy_fill #appIdNo').val(gIdentifyNumber);
	$('#policy_fill #appBirthday').val(gBirthday);
	$('#policy_fill #appSex').val(gSex);
	$('#policy_fill #appEmail').val(gEmail);
	$('#policy_fill #appMobilePhone').val(gMobliePhone);
	$('#policy_fill #appComPhone').val(gOfficePhone);
	$('#policy_fill #appHomePhone').val(gHomePhone);
	$('#policy_fill #appAddress').val(gContactAddress);
	$('#policy_fill #appZipCode').val(gZipCode);
	$('#policy_fill #appProvince').data('jSelect').setValue(provinces);
	$('#policy_fill #appCity').data('jSelect').setValue(city);
	$('#policy_fill #appArea').data('jSelect').setValue(area);
	
	if(isBind == 'true'){
		initAppModuleEditable(1,"app","applicant_info");
	}
	
	$('#policy_fill #appIdType').data('jSelect').setValue(gIdentifyType);
	gBirthday = new Date(gBirthday).format('yyyy-MM-dd');
	if(gBirthday.indexOf('-') > 0){
		$('#policy_fill #app_year').data('jSelect').setValue(gBirthday.split('-')[0]);
		$('#policy_fill #app_month').data('jSelect').setValue(gBirthday.split('-')[1]);
		$('#policy_fill #app_day').data('jSelect').setValue(gBirthday.split('-')[2]);
	}
	if(identifyEffectiveDate == null && identifyEffectiveDate == ''){
		var tempDate = new Date();
		tempDate.setYear(tempDate.getFullYear()+100);
// 		console.log(tempDate.format('yyyy-MM-dd'));
		identifyEffectiveDate = tempDate.format('yyyy-MM-dd');
	}
	identifyEffectiveDate = new Date(identifyEffectiveDate).format('yyyy-MM-dd');
	if(identifyEffectiveDate.indexOf('-') > 0){
		$('#policy_fill #app_validity_year').data('jSelect').setValue(identifyEffectiveDate.split('-')[0]);
		$('#policy_fill #app_validity_month').data('jSelect').setValue(identifyEffectiveDate.split('-')[1]);
		$('#policy_fill #app_validity_day').data('jSelect').setValue(identifyEffectiveDate.split('-')[2]);
	}
	if(gSex == 0){
		if(!$('#applicant_info #male').hasClass('selected')){
			$('#applicant_info #male').addClass('selected');
			$('#applicant_info #male').siblings().removeClass('selected');
			$('#applicant_info #male').parent().find("input[type='hidden']").attr('value',gSex);
		}
	}else if(gSex == 1){
		if(!$('#applicant_info #female').hasClass('selected')){
			$('#applicant_info #female').addClass('selected');
			$('#applicant_info #female').siblings().removeClass('selected');
			$('#applicant_info #female').parent().find("input[type='hidden']").attr('value',gSex);
		}
	}
	
	$($('#applicant_info #appName,#appIdType,#appIdNo,#appBirthday,'+
			'#app_year,#app_month,#app_day,#appSex,#male,#female,.label_sex,'+
			'#appEmail,#appMobilePhone,#appComPhone,#appHomePhone,#appAddress,#appZipCode,'+
			'#app_validity_year,#app_validity_month,#app_validity_day,#appArea'
			)).blur(function(){
	 	autoSave();
	});
	checkInsRelationToApp($('#policy_fill #insRelationToApp'),'1');
}
	
/**
 * ��ʼ������һ��Ͷ���ˡ������ˡ���������Ϣ
 * @param demoName
 * @param type
 * @returns
 */
var appIndex = 0;var insuredIndex = 0;var bnfIndex = 0;
function addDemoIndex(demoName,type){
	var paramIndex;
	var demoClone = $("#"+demoName).clone();
	if(type == "appIndex"){
		appIndex += 1;
		paramIndex = appIndex;
	}else if (type == "insuredIndex"){
		insuredIndex += 1;
		paramIndex = insuredIndex;
	}else if(type == "bnfIndex"){
		bnfIndex += 1;
		paramIndex = bnfIndex;
	}
	
	return demoClone.html();
}

function changeRadioName(obj,className,radioName,userIndex){
	obj.find("."+className).each(function(){
		var parentObj = $(this).parent();
		var parentHtml = $(parentObj).html();
		var newName = radioName+userIndex;
		parentHtml = myReplaceAll(parentHtml,radioName,newName,false);
		$(parentObj).html(parentHtml);
	});
}


/**
 * ��ʼ��������Ԫ��
 */
function initBnfData(index){
	var benIdType_opts = $("#benIdType_opts").val();
	var benRelaToInss_opts = $("#benRelaToInss_opts").val();
	var year_opts = getYearOfnum(80,1);
	var validity_year_opts = getYearOfnum(100,2);
	var benOrder = [];
	for(var i=1;i<=$("#maxBeneficiaryNum").val();i++){
		benOrder.push({name:i,value:i});
	}
	var	month_opts = [];
	var day_opts = [];
	for(var i=1;i<=12;i++){
		if(i<10){
			month_opts.push({name:"0"+i,value:"0"+i});
		}else{
			month_opts.push({name:i,value:i});
		}
	}

	$('#policy_fill').find("div[tag='input_sex"+index+"']").click(function(){
		if(!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).siblings().removeClass('selected');
			$(this).parent().find("input[type='hidden']").attr('value',$(this).attr('val'));
			checkIdCard($(this).attr("prefix"),index,$(this).attr("prefixTag"));
		}
	});
	
	//��ʼ���������뱻���˹�ϵ
	$('#policy_fill #benRelationToPIns'+index).jSelect({
		options:eval(benRelaToInss_opts),
		onSelect:function(){
		}
	});
	
	$('#policy_fill #ben_year'+index).jSelect({
		options:year_opts,
		onSelect:function(){
			updateDaySelector($('#policy_fill #ben_year'+index),$('#policy_fill #ben_month'+index),$('#policy_fill #ben_day'+index));
			setHiddenInput($('#policy_fill #ben_year'+index),$('#policy_fill #ben_month'+index),$('#policy_fill #ben_day'+index));
		}
	});
	
	$('#policy_fill #ben_month'+index).jSelect({
		options:month_opts,
		onSelect:function(){
			updateDaySelector($('#policy_fill #ben_year'+index),$('#policy_fill #ben_month'+index),$('#policy_fill #ben_day'+index));
			setHiddenInput($('#policy_fill #ben_year'+index),$('#policy_fill #ben_month'+index),$('#policy_fill #ben_day'+index));
		}
	});
	
	$('#policy_fill #ben_day'+index).jSelect({
		options:day_opts,
		onSelect:function(){
			setHiddenInput($('#policy_fill #ben_year'+index),$('#policy_fill #ben_month'+index),$('#policy_fill #ben_day'+index));
		}
	});
	
	//��ʼ��������֤������
	$('#policy_fill #benIdType'+index).jSelect({
		options:eval(benIdType_opts),
		onSelect:function(){
		}
	});
	
	//��ʼ��������˳��
	$('#policy_fill #benOrder'+index).jSelect({
		options:eval(benOrder),
		onSelect:function(){
			
		}
	});

	$('#policy_fill #benIdType'+index).data('jSelect').setValue('-1');
	$('#policy_fill #benRelationToPIns'+index).data('jSelect').setValue('-1');
	
	//��ʼ��������У��
	$.formValidator.initConfig({validatorGroup:10+index+"",formID:"benForm"+index,debug:false,submitOnce:true,theme:'Default'});
	initCheckBnf(3,"ben","policy_fill",index,1);
}

function initElementData(){
	//��ʼ��Ͷ����֤������
	var appIdType_opts = $("#appIdType_opts").val();
	var insRelationToApp_opts = $("#insRelationToApp_opts").val();
	var insIdType_opts = $("#insIdType_opts").val();
	
	Sinosoft.initCitySelector($('#policy_fill #appProvince'),$('#policy_fill #appCity'),$('#policy_fill #appArea'));
	Sinosoft.initCitySelector($('#policy_fill #insProvince'),$('#policy_fill #insCity'),$('#policy_fill #insArea'));
	
	$('#policy_fill #appIdType').jSelect({
		options:eval(appIdType_opts),
		onSelect:function(){
			autoSave();
		}
	});

	//��ʼ����������Ͷ���˹�ϵ
	$('#policy_fill #insRelationToApp').jSelect({
		options:eval(insRelationToApp_opts),
		onSelect:function(){
			checkInsRelationToApp($('#policy_fill #insRelationToApp'),'1');
		}
	});
	
	//��ʼ��������֤������
	$('#policy_fill #insIdType').jSelect({
		options:eval(insIdType_opts),
		onSelect:function(){
		}
	});
	
	var app_year_opts = getYearOfnum(80,1);
	var ins_year_opts = getYearOfnum(80,1);
	
	var validity_year_opts = getYearOfnum(100,2);
	var	month_opts = [];
	var day_opts = [];
	for(var i=1;i<=12;i++){
		if(i<10){
			month_opts.push({name:"0"+i,value:"0"+i});
		}else{
			month_opts.push({name:i,value:i});
		}
	}
	
	$('#policy_fill #app_year').jSelect({
		options:app_year_opts,
		onSelect:function(){
			$('#appBirthday').val($('#policy_fill #app_year').val()+"-"+$('#policy_fill #app_month').val()+"-"+$('#policy_fill #app_day').val());
			$('#appBirthday').trigger("change");
			autoSave();
			updateDaySelector($('#policy_fill #app_year'),$('#policy_fill #app_month'),$('#policy_fill #app_day'));
			checkAppAgeByQuote($('#policy_fill #app_year'),$('#policy_fill #app_month'),$('#policy_fill #app_day'));
		}
	});
	
	$('#policy_fill #app_month').jSelect({
		options:month_opts,
		onSelect:function(){
			$('#appBirthday').val($('#policy_fill #app_year').val()+"-"+$('#policy_fill #app_month').val()+"-"+$('#policy_fill #app_day').val());
			$('#appBirthday').trigger("change");
			autoSave();
			updateDaySelector($('#policy_fill #app_year'),$('#policy_fill #app_month'),$('#policy_fill #app_day'));
			checkAppAgeByQuote($('#policy_fill #app_year'),$('#policy_fill #app_month'),$('#policy_fill #app_day'));
		}
	});
	
	$('#policy_fill #app_day').jSelect({
		options:day_opts,
		onSelect:function(){
			$('#appBirthday').val($('#policy_fill #app_year').val()+"-"+$('#policy_fill #app_month').val()+"-"+$('#policy_fill #app_day').val());
			$('#appBirthday').trigger("change");
			autoSave();
			checkAppAgeByQuote($('#policy_fill #app_year'),$('#policy_fill #app_month'),$('#policy_fill #app_day'));
		}
	});
	
	$('#policy_fill #ins_year').jSelect({
		options:ins_year_opts,
		onSelect:function(){
			$('#insBirthday').val($('#policy_fill #ins_year').val()+"-"+$('#policy_fill #ins_month').val()+"-"+$('#policy_fill #ins_day').val());
			$('#insBirthday').trigger("change");
			updateDaySelector($('#policy_fill #ins_year'),$('#policy_fill #ins_month'),$('#policy_fill #ins_day'));
		}
	});
	
	$('#policy_fill #ins_month').jSelect({
		options:month_opts,
		onSelect:function(){
			$('#insBirthday').val($('#policy_fill #ins_year').val()+"-"+$('#policy_fill #ins_month').val()+"-"+$('#policy_fill #ins_day').val());
			$('#insBirthday').trigger("change");
			updateDaySelector($('#policy_fill #ins_year'),$('#policy_fill #ins_month'),$('#policy_fill #ins_day'));
		}
	});
	
	$('#policy_fill #ins_day').jSelect({
		options:day_opts,
		onSelect:function(){
			$('#insBirthday').val($('#policy_fill #ins_year').val()+"-"+$('#policy_fill #ins_month').val()+"-"+$('#policy_fill #ins_day').val());
			$('#insBirthday').trigger("change");
		}
	});
	
	$('#policy_fill #app_validity_year').jSelect({
		options:validity_year_opts,
		onSelect:function(){
			$('#appIdentifyEffectiveDate').val($('#policy_fill #app_validity_year').val()+"-"+$('#policy_fill #app_validity_month').val()+"-"+$('#policy_fill #app_validity_day').val());
			$('#appIdentifyEffectiveDate').trigger("change");
			autoSave();
			updateDaySelector($('#policy_fill #app_validity_year'),$('#policy_fill #app_validity_month'),$('#policy_fill #app_validity_day'));
		}
	});
	
	$('#policy_fill #app_validity_month').jSelect({
		options:month_opts,
		onSelect:function(){
			$('#appIdentifyEffectiveDate').val($('#policy_fill #app_validity_year').val()+"-"+$('#policy_fill #app_validity_month').val()+"-"+$('#policy_fill #app_validity_day').val());
			$('#appIdentifyEffectiveDate').trigger("change");
			autoSave();
			updateDaySelector($('#policy_fill #app_validity_year'),$('#policy_fill #app_validity_month'),$('#policy_fill #app_validity_day'));
		}
	});
	
	$('#policy_fill #app_validity_day').jSelect({
		options:day_opts,
		onSelect:function(){
			$('#appIdentifyEffectiveDate').val($('#policy_fill #app_validity_year').val()+"-"+$('#policy_fill #app_validity_month').val()+"-"+$('#policy_fill #app_validity_day').val());
			$('#appIdentifyEffectiveDate').trigger("change");
			autoSave();
		}
	});
	
	$('#policy_fill #ins_validity_year').jSelect({
		options:validity_year_opts,
		onSelect:function(){
			$('#insIdentifyEffectiveDate').val($('#policy_fill #ins_validity_year').val()+"-"+$('#policy_fill #ins_validity_month').val()+"-"+$('#policy_fill #ins_validity_day').val());
			$('#insIdentifyEffectiveDate').trigger("change");
			updateDaySelector($('#policy_fill #ins_validity_year'),$('#policy_fill #ins_validity_month'),$('#policy_fill #ins_validity_day'));
		}
	});
	
	$('#policy_fill #ins_validity_month').jSelect({
		options:month_opts,
		onSelect:function(){
			$('#insIdentifyEffectiveDate').val($('#policy_fill #ins_validity_year').val()+"-"+$('#policy_fill #ins_validity_month').val()+"-"+$('#policy_fill #ins_validity_day').val());
			$('#insIdentifyEffectiveDate').trigger("change");
			updateDaySelector($('#policy_fill #ins_validity_year'),$('#policy_fill #ins_validity_month'),$('#policy_fill #ins_validity_day'));
		}
	});
	
	$('#policy_fill #ins_validity_day').jSelect({
		options:day_opts,
		onSelect:function(){
			$('#insIdentifyEffectiveDate').val($('#policy_fill #ins_validity_year').val()+"-"+$('#policy_fill #ins_validity_month').val()+"-"+$('#policy_fill #ins_validity_day').val());
			$('#insIdentifyEffectiveDate').trigger("change");
		}
	});
	
	$('#policy_fill .selected_job').click(function(){
		$('.select_panel').show();
	});

	var save_topInsured = $('#policy_fill .save_check').jCheckBox({
		label:'save_check',
		onCheckChanged:function(label,check){
			if(check){
				$("#save_topInsured").val("1");
			}else{
				$("#save_topInsured").val("0");
			}
		}
	});
	saveTopInsureds.push(save_topInsured);
	saveTopInsureds[0].check(true);
	$("#save_topInsured").val("1");
	
	for(var i=0 ; i< $('.reference_check').length ; i++){
		var reference = $('.reference_check').eq(i).jCheckBox({
			label:i,
			onCheckChanged:function(label,check){
				if(check){
					$('.reference_check').eq(label).parent().parent().find("input[type='hidden']").attr('value',$('.reference_check').eq(label).attr('type'));
					for(var i=0 ; i< references.length ; i++){
						if(i != parseInt(label)){
							references[i].check(false);
						}
					}
				}else{
					$('.reference_check').eq(label).parent().parent().find("input[type='hidden']").attr('value','');
				}
			}
		});
		references.push(reference);
	}
	

	for(var i=0 ; i< $('.contact_check').length ; i++){
		var contact = $('.contact_check').eq(i).jCheckBox({
			label:i,
			property:$('.contact_check').eq(i).attr('topId'),
			onCheckChanged:function(label,check){
				if(check){
					for(var i=0 ; i< contacts.length ; i++){
						if(i != parseInt(label)){
							contacts[i].check(false);
						}
					}
					initInsDateByTopIns(1,$('.contact_check').eq(label).attr("topId"),"ins","insured_info");
				}else{
					initInsDateByTopIns(2,$('.contact_check').eq(label).attr("topId"),"ins","insured_info");
				}
			}
		});
		contacts.push(contact);
	}
	
	$('#policy_fill #appIdType').data('jSelect').setValue('-1');
	$('#policy_fill #insIdType').data('jSelect').setValue('-1');
	$('#policy_fill #insRelationToApp').data('jSelect').setValue('-1');
	
	if($('#autoIns').val() == '1'){
		$('#insured_info #insRelationToApp').data('jSelect').setValue('0');
		initParameter(1,"app","ins","applicant_info","insured_info");
		$('#topInsList').attr('style','display: none;');
		$('#insured_info .save_contact').attr('style','display: none;');
		saveTopInsureds[0].check(false);
		$("#save_topInsured").val("0");
	}
}

checkIdCard('app','','applicant_info');

$('#effect_start').blur(function(){
	$('#specifyStartDate').text($(this).val());
});

//����ǰ��Ʒ��Ч����
var effectDateT = $("#effectDateT").val();
var inceptionDate = $("#inceptionDate").val();
var minInceptionDate = inceptionDate;
var maxInceptionDate = inceptionDate;

if(effectDateT == '01'){
	$('#effect_start').val(inceptionDate);
	$('#effect_start').attr("disabled","disabled");
	$('#specifyStartDate').text(inceptionDate);
}
if(effectDateT == '03'){
	minInceptionDate = inceptionDate.split("|")[0];
	maxInceptionDate = inceptionDate.split("|")[1];
}

function initEffectStartDate(){
	WdatePicker({
		el:'effect_start',
		//startDate:'%y-%M-%d',
		minDate:minInceptionDate,
		maxDate:maxInceptionDate,
		dateFmt:'yyyy-MM-dd'
	});
}

//check��������Ͷ���˹�ϵ�������������Ͷ���˹�ϵΪ���ˣ��Զ�copyͶ������Ϣ�������ˣ�ͬʱ��������Ϣ�����޸�
function checkInsRelationToApp(relation,flag){
	var relationVal = relation.data('jSelect').getValue();
// 	console.log("relationVal: "+relationVal);
	if(relationVal != '' && (relationVal == 0 || relationVal == '0')){
		initModuleEditable(1,"ins","insured_info");
		initParameter(1,"app","ins","applicant_info","insured_info");
		$('#insured_info .save_contact').attr('style','display: none;');
		saveTopInsureds[0].check(false);
		$("#save_topInsured").val("0");
		for(var i in contacts){
			contacts[i].check(false);
		}
	}else{
		if(relationVal == '-1'){
			$('#insured_info .save_contact').attr('style','display: none;');
			$("#save_topInsured").val("0");
		}else{
			initModuleEditable(0,"ins","insured_info");
			if(flag == 1){
				initParameter(0,"app","ins","applicant_info","insured_info");
			}
			$('#insured_info .save_contact').removeAttr('style');
			saveTopInsureds[0].check(true);
			$("#save_topInsured").val("1");
		}
	}
}

//�����������Ͷ���˹�ϵΪ���ˣ��Զ���Ͷ������Ϣ�Ķ�ͬ����������
function autoSave(){
	var relationVal = $('#insured_info #insRelationToApp').data('jSelect').getValue();
	if(relationVal != '' && (relationVal == 0 || relationVal == '0')){
		initParameter(1,"app","ins","applicant_info","insured_info");
	}
}

/**�����뿪*/
function saveInfo(type){
	var otherResult = true;
	
	var result = jQuery.formValidator.pageIsValid('1');
	var bnfsResult = true;
	$(".beneficiary_info").find("div[tag='inputBnf']").each(function(i){
// 		console.log($(this).find("form").attr("id")+", "+jQuery.formValidator.pageIsValid((10+parseInt($(this).attr("index")))+""));
		if(!jQuery.formValidator.pageIsValid((10+parseInt($(this).attr("index")))+"")){
			bnfsResult = false;
			return;
		}
	});
	
	if($('#policy_fill #insRelationToApp').val() == '0'){
// 		if(checkInsisApp(1,"app","ins","applicant_info","insured_info")){
// 			alret("Ͷ��������ʾ","Ͷ��������Ϣ��һ��");
// 			otherResult = false;
// 		}
	}
	if(checkAppAge()){
		alret("Ͷ��������ʾ",$("#appAgeDesc").val());
		otherResult = false;
	}
	if(checkInsuredAge()){
		alret("Ͷ��������ʾ",$("#insuredAgeDesc").val());
		otherResult = false;
	}
	if(checkOccupation()){
		alret("Ͷ��������ʾ","��ѡ��ְҵ");
		otherResult = false;
	}
	if(result && bnfsResult){
		if(otherResult){
			$("#save_info").attr({disabled:"disabled"});
			copySecondStep();
// 			console.log(insureFlowDto);
			var quoteNo = $("#quoteNo").val();
			var proposalSID = $("#proposalSID").val();
	// 		console.log("quoteNo: "+quoteNo+", proposalSID: "+proposalSID);
			$.ajax({
				type: "POST",
				url:contextRootPath+"/sale/saveQuoteAndExit.do?step=2",
				dataType : 'json',
				data:{"insureFlowDto":insureFlowDto,"save_topInsured":$("#save_topInsured").val(),"quoteNo":quoteNo,"proposalSID":proposalSID,"proposalContNo":$("#proposalContNo").val()},
				async:false,
				success: function(data){
					if(data != 'success'){
						alret("�ݴ�ʧ��",data);
						$("#save_info").removeAttr("disabled");
					}else{
						window.location = "${ctx }/memberCenter/homePage.do";
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alret("�����쳣�����Ժ����ԣ�","");
				}
			}); 
		}
	}
}

//����Ͷ���ˡ������ˡ���������Ϣ
function copySecondStep(){
	//�ж���Ϣ�Ƿ����޸�
	var newAppName = $('#policy_fill #appName').val();
	var newAppIdType = $('#policy_fill #appIdType').val();
	var newAppIdNumber = $('#policy_fill #appIdNo').val();
	var newInsName = $('#policy_fill #insName').val();
	var newInsIdType = $('#policy_fill #insIdType').val();
	var newIdNumber = $('#policy_fill #insIdNo').val();
	
	var oldAppName = insureFlowDto.quoteMain.quoteApplicant.fullName;
	var oldAppIdType = insureFlowDto.quoteMain.quoteApplicant.idType;
	var oldAppIdNumber = insureFlowDto.quoteMain.quoteApplicant.idNumber;
	var oldInsName = insureFlowDto.quoteMain.quoteInsureds[0].fullName;
	var oldInsIdType = insureFlowDto.quoteMain.quoteInsureds[0].idType;
	var oldIdNumber = insureFlowDto.quoteMain.quoteInsureds[0].idNumber;
	if(newAppName != oldAppName || newAppIdType != oldAppIdType || newAppIdNumber != oldAppIdNumber
			|| newInsName != oldInsName || newInsIdType != oldInsIdType || newIdNumber != oldIdNumber){
		$("#proposalContNo").val("");
	}
	
	/**clear parties*/
	insureFlowDto.quoteMain.quoteInsureds =[];
	insureFlowDto.quoteMain.quoteBeneficiaries = [];
	
	/**��Ч����*/
	insureFlowDto.quoteMain.inceptionDate = $('#policy_fill #effect_start').val();
	/**Ͷ����*/
	var occupationCode,occupationDescription;
	if($('#policy_fill #insRelationToApp').val() == '0'){
		occupationCode = $('#policy_fill #insOccupation').val();//==null||$('#policy_fill #insOccupation').val()==''?"1050102":$('#policy_fill #insOccupation').val();
		occupationDescription = $('#insured_info #selected_job').html() == null?"":$('#insured_info #selected_job').html();
	}else{
		occupationCode = $('#policy_fill #appOccupation').val();//==null||$('#policy_fill #appOccupation').val()==''?"1050102":$('#policy_fill #appOccupation').val();;
		occupationDescription = $('#applicant_info #selected_job').html() == null?"":$('#applicant_info #selected_job').html();
	}
	var relation = $('#policy_fill #insRelationToApp').val();
	if(relation == 25){
		relation = 24;
	}else if(relation == 24){
		relation = 25;
	}
	insureFlowDto.quoteMain.quoteApplicant = {
			fullName:$('#policy_fill #appName').val(),
			idType:$('#policy_fill #appIdType').val(),
			idNumber:$('#policy_fill #appIdNo').val(),
			gender:$('#policy_fill #appSex').val(),
			birthDate:$('#policy_fill #appBirthday').val(),
			idExpDate:$('#policy_fill #appIdentifyEffectiveDate').val(),
			mobilePhoneNumber:$('#policy_fill #appMobilePhone').val(),
			phoneNumber:$('#policy_fill #appHomePhone').val()==''?"":$('#policy_fill #appHomePhone').val(),
			officePhoneNumber:$('#policy_fill #appComPhone').val()==''?"":$('#policy_fill #appComPhone').val(),
			email:$('#policy_fill #appEmail').val(),
			addressLines:$('#policy_fill #appAddress').val(),
			postalCode:$('#policy_fill #appZipCode').val(),
			province:$('#policy_fill #appProvince').val(),
			city:$('#policy_fill #appCity').val(),
			county:$('#policy_fill #appArea').val(),
			provinceName:$('#policy_fill #appProvince').data('jSelect').getName(),
			cityName:$('#policy_fill #appCity').data('jSelect').getName(),
			countyName:$('#policy_fill #appArea').data('jSelect').getName(),
			occupationCode:occupationCode,
			occupationDescription:occupationDescription,
			relatedToInsured:relation
		};
	
	/**��������*/
// 	console.log("topinsId: "+$("#policy_fill #topinsId").val());
	insureFlowDto.quoteMain.quoteInsureds[0] = {
			topInsuredId:$("#policy_fill #topinsId").val(),
			relatedToApplicant:$('#policy_fill #insRelationToApp').val(),
			fullName:$('#policy_fill #insName').val(),
			idType:$('#policy_fill #insIdType').val(),
			idNumber:$('#policy_fill #insIdNo').val(),
			gender:$('#policy_fill #insSex').val(),
			birthDate:$('#policy_fill #insBirthday').val(),
			idExpDate:$('#policy_fill #insIdentifyEffectiveDate').val(),
			mobilePhoneNumber:$('#policy_fill #insMobilePhone').val(),
			phoneNumber:$('#policy_fill #insHomePhone').val()==''?"":$('#policy_fill #insHomePhone').val(),
			officePhoneNumber:$('#policy_fill #insComPhone').val()==''?"":$('#policy_fill #insComPhone').val(),
			email:$('#policy_fill #insEmail').val(),
			addressLines:$('#policy_fill #insAddress').val(),
			postalCode:$('#policy_fill #insZipCode').val(),
			province:$('#policy_fill #insProvince').val(),
			city:$('#policy_fill #insCity').val(),
			county:$('#policy_fill #insArea').val(),
			provinceName:$('#policy_fill #insProvince').data('jSelect').getName(),
			cityName:$('#policy_fill #insCity').data('jSelect').getName(),
			countyName:$('#policy_fill #insArea').data('jSelect').getName(),
			occupationCode:$('#policy_fill #insOccupation').val(),//==null||$('#policy_fill #insOccupation').val()==''?"1050102":$('#policy_fill #insOccupation').val(),
			occupationDescription:$('#insured_info #selected_job').html() == null?"":$('#insured_info #selected_job').html()
		};
	/**�������Ƿ񷨶�*/
	insureFlowDto.isExsitBnf = $('#policy_fill #isExsitBnf').val();
	/**��������Ϣ*/
	if(insureFlowDto.isExsitBnf == 1){
		$inputBnf = $(".beneficiary_info").find("div[tag='inputBnf']");
		//var index = $(".beneficiary_info").find("div[tag='inputBnf']").length;
		$inputBnf.each(function(i){
			insureFlowDto.quoteMain.quoteBeneficiaries[i] = {
				beneficiaryType:"3401",
				relatedToInsured:$(this).find('input[name="benRelationToPIns"]').val(),
				birthDate:$(this).find('input[name="birth_year"]').val(),
				beneficiaryOrder:$(this).find('input[name="benOrder"]').val(),
				interestPercent:$(this).find('input[name="benRate"]').val(),
				fullName:$(this).find('input[name="benName"]').val(),
				idType:$(this).find('input[name="benIdType"]').val(),
				idNumber:$(this).find('input[name="benIdNo"]').val(),
				gender:$(this).find('input[name="benSex"]').val()
				};
		});
		
		
	}
// 	console.log(insureFlowDto.quoteMain.quoteBeneficiaries.length);
	/**�Ƽ�����Ϣ*/
	insureFlowDto.quoteMain.recommendType = $('#policy_fill #reference_type').val();
	insureFlowDto.quoteMain.recommend = $('#policy_fill #reference_phone').val();
	
}

/**У��Ͷ��������*/
function checkAppAge(){
// 	console.log("checkAppAge()...");
	if($("#isLimitAppAge").val() != null && $("#isLimitAppAge").val() != '' && $("#isLimitAppAge").val() == '0'){
		var appBirthday = $("#appBirthday").val();
		var minDate = $("#appMinBirthday").val();
		var maxDate = $("#appMaxBirthday").val();
// 		console.log("appBirthday: "+appBirthday+", minDate: "+minDate+", maxDate: "+maxDate);
		if(dateCompare(appBirthday,minDate) < 0 || dateCompare(maxDate,appBirthday) < 0){
			return true;
		}
	}
	return false;
}

/**У�鱻��������*/
function checkInsuredAge(){
// 	console.log("checkInsuredAge()...");
	if($("#isLimitInsuredAge").val() != null && $("#isLimitInsuredAge").val() != '' && $("#isLimitInsuredAge").val() == '0'){
		var insBirthday = $("#insBirthday").val();
		var minDate = $("#insuredMinBirthday").val();
		var maxDate = $("#insuredMaxBirthday").val();
// 		console.log("insBirthday: "+insBirthday+", minDate: "+minDate+", maxDate: "+maxDate);
		if(dateCompare(insBirthday,minDate) < 0 || dateCompare(maxDate,insBirthday) < 0){
			return true;
		}
	}
	return false;
}
/**У�鱻����ְҵ*/
function checkOccupation(){
	if($("#policy_fill #insOccupation").val() == null || $("#policy_fill #insOccupation").val() == ''){
		return true;
	}
	return false;
}

/**У�齡����֪ */
function checkInform(){
	var informCount = $(".kuang1 .kuang_radio").length;
	var count = 0;
	$(".kuang1 .kuang_radio :input").each(function(i, item){ 
// 		console.log("i: "+i+", item: "+$(this).attr('act')+", checked: "+$(this).attr('checked'));
		if($(this).attr('act') == 'n' && $(this).attr('checked') == 'checked'){
			count ++ ;
		}
	});
	if(count < informCount)
		return true;
	else
		return false;
}
/**����Ͷ����ȷ��ҳ��*/
function toConfirmInsurance(){
	var otherResult = true;
	
	if(checkInform()){
		alret("Ͷ��������ʾ","��ѡ�񽡿���֪");
		otherResult = false;
		return false;
	}
	var $inputBnf = $(".beneficiary_info").find("div[tag='inputBnf']");
	if($inputBnf.length > 0){
		//var relationVal = $('#benOrder0').data('jSelect').getValue();
		
	/* 	$inputBnf.each(function(){
			alert("dd");
			console.log($(this).find('input[name="benOrder"]').val());
			$inputBnf.each(function(){
				
			})
			//
		}); */
		/* for(var i = $inputBnf.lenght-1;i>=0;i--){
			var order = $inputBnf[i].value();
			for(var j=$inputBnf.lenght-1;j>=0;j--){
				if( (j > 0 && j !=i)  && order!=1){
					if($inputBnf[j] < order && $inputBnf[j]< 1 ){
						validbnf = true;
						break;
					}
				}
			}
			if(!validbnf){
				alert("����˳�����");
				break;
			}
		} */
		
		var validBnf = true;
		var findMinOrder = false;
		var bnfTip=""
		if($inputBnf.length == 1 && $('#benOrder0').val()!= 1){
			bnfTip = "�Ҳ�������˳��Ϊ1�������ˣ�";
			validBnf =false;
		}
		if($inputBnf.length == 2){
			$inputBnf.each(function(){
				if($(this).find('input[name="benOrder"]').val() == 3){
				/* 	Sinosoft.alert({
						contentStr: "��ֻ��д�����������ˣ�����˳����Ϊ3��",
						width:480,
						okStr: 'ȷ��',
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
						okFunc:function(){
							
						}
					}); */
					bnfTip = "��ֻ��д�����������ˣ�����˳����Ϊ3��"
					validBnf = false;
				}
				if($(this).find('input[name="benOrder"]').val() == '1'){
					findMinOrder = true;
				}
				
			});
			if(validBnf && !findMinOrder){
				validBnf = false;
				bnfTip = "�Ҳ�������˳��Ϊ1�������ˣ�"
				/* Sinosoft.alert({
					contentStr: "�Ҳ�������˳��Ϊ1�������ˣ�",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					okFunc:function(){
						
					}
				});
				return false; */
			}
		}
		var findOrder2 = false;
		var exitsOrder3 = false;
		if($inputBnf.length == 3){
			
			$inputBnf.each(function(){
				
				if($(this).find('input[name="benOrder"]').val() == 3){
					 exitsOrder3 = true;
				}
				if($(this).find('input[name="benOrder"]').val() == 2){
					 findOrder2 = true; 
				}
				if($(this).find('input[name="benOrder"]').val() == 1){
					findMinOrder = true;
				}
				
				
			});
			if(!findMinOrder){
				validBnf = false;
				bnfTip = "�Ҳ�������˳��Ϊ1�������ˣ�"
			}else if(exitsOrder3 && !findOrder2){
				/* Sinosoft.alert({
					contentStr: "����д������˳��3�����Ҳ�������˳��Ϊ2�������ˣ�",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					okFunc:function(){
						
					}
				});
				return false; */
				validBnf = false;
				bnfTip = "����д������˳��3�����Ҳ�������˳��Ϊ2�������ˣ�"
			}
		}
		if(!validBnf){
			Sinosoft.alert({
				contentStr: bnfTip,
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		}
		
	}
// 	console.log("toConfirmInsurance()...");
	var result = jQuery.formValidator.pageIsValid('1');
	var bnfsResult = true;
	$(".beneficiary_info").find("div[tag='inputBnf']").each(function(i){
// 		console.log($(this).find("form").attr("id")+", "+jQuery.formValidator.pageIsValid((10+parseInt($(this).attr("index")))+""));
		if(!jQuery.formValidator.pageIsValid((10+parseInt($(this).attr("index")))+"")){
			bnfsResult = false;
			return;
		}
	});
	if($('#policy_fill #insRelationToApp').val() == '0'){
// 		if(checkInsisApp(1,"app","ins","applicant_info","insured_info")){
// 			alret("Ͷ��������ʾ","Ͷ��������Ϣ��һ��");
// 			otherResult = false;
// 		}
	}
	var checkAppAgeByQuoteDesc = $("#checkAppAgeByQuoteDesc").val();
	if(checkAppAgeByQuoteDesc != ''){
		alret("Ͷ��������ʾ",checkAppAgeByQuoteDesc);
		otherResult = false;
		return false;
	}
	if(checkAppAge()){
		alret("Ͷ��������ʾ",$("#appAgeDesc").val());
		otherResult = false;
		return false;
	}
	if(checkInsuredAge()){
		alret("Ͷ��������ʾ",$("#insuredAgeDesc").val());
		otherResult = false;
		return false;
	}
	if(checkOccupation()){
		alret("Ͷ��������ʾ","��ѡ��ְҵ");
		otherResult = false;
		return false;
	}
	if(result && bnfsResult){
		if(otherResult){
			loading.open();
			copySecondStep();
// 			console.log(insureFlowDto);
// 			console.log("save_topInsured: "+$("#save_topInsured").val());
			$.ajax({
				type: "POST",
				url:contextRootPath+"/sale/underwriting.do",
				dataType : 'json',
				data:{"insureFlowDto":insureFlowDto,"save_topInsured":$("#save_topInsured").val(),"quoteNo":$("#inputQuoteNo").val(),"proposalSID":$("#inputProposalSID").val(),"proposalContNo":$("#proposalContNo").val()},
				success: function(data){
					if(data.regFlag == "-1" || data.regFlag == "0"){
						//������½��
						sinosoft_login_dialog();
						return false;
					}
// 					console.log("data: "+data.quoteNo+", proposalSID: "+data.proposalSID+", proposalContNo: "+data.proposalContNo);
					insureFlowDto.quoteMain.quoteNo = data.quoteNo;
					insureFlowDto.quoteMain.proposalSID = data.proposalSID;
					insureFlowDto.quoteMain.hxssProposalNo = data.proposalContNo;
					$("#inputQuoteNo").val(data.quoteNo);
					$("#inputProposalSID").val(data.proposalSID);
					$("#proposalContNo").val(data.proposalContNo);
					if(data.regFlag == "1"){
// 						alret("ϵͳ��Ϊ���Զ�ע��,�����ѷ�������������!");
					}
					
					if(data.result != "success"){
						loading.close();
						alret("�˱�ʧ��",data.result);
					}else{
						var JsonSTR = data.JsonSTR;
// 						console.log(JsonSTR);
						insureFlowDto = data.JsonSTR;
						console.log(insureFlowDto);
						$("#toConfimUrlFlag").val("confirmInsure");
						$("#toConfimJsonSTR").val(JSON.stringify(insureFlowDto));
						$("#policy_fill").attr("action","${ctx}/sale/obtainDataForConfirm.do?productCode=" + insureFlowDto.productCode);
						$("#policy_fill").submit();
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					loading.close();
					if(XMLHttpRequest.status==500){
						alret("�����쳣�����Ժ����ԣ�","");
					}else{
						alret("�����쳣�����Ժ����ԣ�",XMLHttpRequest.status);
					}
				}
			});
		}
	}
}

function loadClickLogin(){
	var clickLogin = $('<div class="login_frame">'+ 
			'<label class="input_label">��Ա��¼</label>'+
			'<p class="alert_content notice_login">������ע��ͻ������¼�������</p>'+
			'</div>');
	return clickLogin;
}

function loadLoginForm(name){
	var clickForm = $('<div class="login_frame">'+
			'<label class="input_label">��Ա��¼<span id="message" style=""></span></label>'+
			'<input class="input_field user_name" id ="sinosoft_login_dialog_userName" type="text" name=""/>'+
			'<input class="input_field password"  id ="sinosoft_login_dialog_password" type="password" name=""/>'+
			'<div class="login_operation">'+
			'<input name="remeberMe" id="remeberMe" type="checkbox"/>'+
			'<label for="remember">��ס��</label>'+
			'<a class="forget_password" href="'+contextRootPath+'/web/user/resetPwd/index.jsp">�������룿</a>'+
			'</div>'+
			'</div>');
	clickForm.find('.user_name').val(name);
	return clickForm;
}

function sinosoft_login_dialog(name) {
	copySecondStep();
	new Sinosoft.LoginDialog({
		layout : loadClickLogin(),
		okStr : '������¼',
		closeIconShow:false,
		okFunc : function() {
			new Sinosoft.LoginDialog({
				layout : loadLoginForm(name),
				okStr : '������¼',
				okFunc : function() {
					$.ajax({
						type : "POST",
						async : false,
						url : contextRootPath+"/login/serviceLogin.do",
						dataType : 'text',
						data : {
							userName : $("#sinosoft_login_dialog_userName").val(),
							password : $("#sinosoft_login_dialog_password").val()
						},
						success : function(data) {
							if (data == "success") {
								insureFlowDto.insuranceType = "1";
								$("#_subQuoteUrlFlag").val("inputInsure");
								$("#_subQuoteJsonSTR").val(JSON.stringify(insureFlowDto));
								$("#policy_sub").attr("action",contextRootPath+"/sale/obtainDataForInput.do?productCode="+insureFlowDto.quoteMain.productCode);
								$("#policy_sub").submit();	
							} else {
								$('.login_frame #message').text('�û������������');
							}
						}
					});
					return false;
				}
			}).open();
		}
	}).open();
}

//У��Ͷ���������Ƿ�������ҳһ��
function checkAppAgeByQuote(appYear,appMonth,appDay){
// 	console.log("checkAppAge("+appYear.val()+", "+appMonth.val()+", "+appDay.val()+")...ageInterval: "+insureFlowDto.quoteMain.ageInterval);
	if(insureFlowDto.quoteMain.ageInterval != '' && appYear.val() != '' && appMonth.val() != '' && appDay.val() != ''){
		$.ajax({
			type : "POST",
			url : contextRootPath+"/sale/checkAppAgeByQuote.do",
			dataType : 'json',
			data:{
				ageInterval : insureFlowDto.quoteMain.ageInterval,
				appBirthday : appYear.val()+"-"+appMonth.val()+"-"+appDay.val()
			},
			success : function(data) {
// 				console.log(data.result+", "+data.age+", "+data.minAge+", "+data.maxAge);
				if (data.result != "success") {
					var checkAppAgeByQuoteDesc = "����д������������ҳ��ѡ����������䲻һ�£����޸�";
					$("#checkAppAgeByQuoteDesc").val(checkAppAgeByQuoteDesc);
					alretAge("Ͷ��������ʾ",checkAppAgeByQuoteDesc);
				}else{
					$("#checkAppAgeByQuoteDesc").val("");
				}
			}
		});
	}
}

//У���Ƿ�����ע��ͻ�
function checkIsCustomer(flagName,prefix,prefixTag){
	//'Email','app','applicant_info'
	//'MobilePhone','app','applicant_info'
// 	console.log("checkIsCustomer("+flagName+","+prefix+","+prefixTag+")...");
	var name = $("#"+prefixTag+" #"+prefix+flagName).val();
	console.log("data: "+name);
	if(name != ''){
		$.ajax({
			type : "POST",
			url : contextRootPath+"/sale/checkIsCustomer.do",
			dataType : 'json',
			data:{
				data : name
			},
			success : function(data) {
				console.log(data.count);
				if (data.count != '' && data.count > 0) {
					sinosoft_login_dialog(name);
					return false;
				}
			}
		});
	}
}

</script>