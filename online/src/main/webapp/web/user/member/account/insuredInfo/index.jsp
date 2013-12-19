<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>���ñ�������-��Ա����</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/user/member.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/member.js" type="text/javascript"></script>
		
		<link rel="stylesheet" type="text/css"  href="${ctx }/global/css/emailpop.css"></link>
		<script type="text/javascript" src="${ctx }/global/js/jquery/emailpop.js"></script>
		
		<link href="${ctx}/resources/css/user/account_insured.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/account_insured.js" type="text/javascript"></script>
		
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/citySelect.js" type="text/javascript"></script>
		
		<script src="${ctx }/global/js/formvalidator4.1.3/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${ctx }/global/js/formvalidator4.1.3/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="insuredInfo.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/member/account/insuredInfo']).send();
			_hm.push(['_trackPageview','/web/user/member/account/insuredInfo']).send();
		</script>
	</body>
</html>
<script>
$(document).ready(function(){
	$("#email").emailpop();
	fillData();
	
	console.log($("#result").val());
	if($("#result").val() != ''){
		Sinosoft.alert({
			contentStr: "������ʾ",
			subContentStr:$("#result").val(),
			width:480,
			okStr: 'ȷ��',
			cancelStr: 'ȡ��',
			cancelBtnShow:false,
			okFunc:function(){
				window.location.href="${ctx}/insured/addInsured.do";
			}
		});
	}
});

var s_idTypes = "${idTypes}";
var s_idType = '${insured.idType}';
var s_sex = '${insured.gender }';
var s_birthday = '${insured.birthDate }';
var s_relatedToApplicant = "${relatedToApplicants }";
var relatedToApplicant = '${insured.relatedToApplicant }';
var s_phoneNumber = "${insured.phoneNumber }";
var s_province = "${insured.province }";
var s_city = "${insured.city }";
var s_county = "${insured.county }";

$.formValidator.initConfig({formID:"insured_fill",debug:false,submitOnce:true,theme:'Default'});
initCheck(2,"ins","insured_fill");

function addInsuredSubSuccess(){
	var subSuccess = $('<div class="alert_subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="sub_txt">��ӱ������˳ɹ�</div></div></div>');
	return subSuccess;
}

function updateInsuredSubSuccess(){
	var subSuccess = $('<div class="alert_subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="sub_txt">���±���������Ϣ�ɹ�</div></div></div>');
	return subSuccess;
}


function validateInsuredForm() {
	var result = jQuery.formValidator.pageIsValid('1');
	if($("insRelationToApp").val() !='' && $("insRelationToApp").val() != -1){
		if(result){
			var userID = $("#userID").val();
			var serialNo = $("#serialNo").val();
			var name = $("#insName").val();
			var idType = $('#insIdType').data('jSelect').getValue();;
			var idNum = $("#insIdNo").val();
			var editFlag = $("#editFlag").val();
			
			$.ajax({
				type : "POST",
				async : false,
				url : "${ctx }/insured/checkInsured.do",
				dataType : 'text',
				data : {
					"userID" : userID,
					"serialNo" : serialNo,
					"insName" : name,
					"insIdType" : idType,
					"insIdNo" : idNum,
					"editFlag" : editFlag
					},
				success: function(data) {
					if (data == "0") {//��ӱ������˳ɹ�
						//����һ��ȡ�����ĳɹ��ĶԻ���
						new Sinosoft.InteractiveDialog({
							layout : addInsuredSubSuccess(),
							width:410,//�Զ��������-�������������
							cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
							okStr : 'ȷ��',
							cancelStr : 'ȡ��',
							okFunc:function(){
								$("#insured_fill").submit();
							}
						}).open();
					}else if (data == "4") {//���±���������Ϣ�ɹ�
						new Sinosoft.InteractiveDialog({
							layout : updateInsuredSubSuccess(),
							width:410,//�Զ��������-�������������
							cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
							okStr : 'ȷ��',
							cancelStr : 'ȡ��',
							okFunc:function(){
								$("#insured_fill").submit();
							}
						}).open();
					} else if (data == "1") {//��������ظ���������
						alret("������ʾ","��������ظ���������");
					} else if (data == "2") {//��ӳ��ñ�������ʧ�ܣ����ֻ�����10����������
						alret("������ʾ","��ӳ��ñ�������ʧ�ܣ����ֻ�����10����������");
					} else if (data == "3") {//�Ѵ�����ͬ�ı������ˣ��޸�ʧ��
						alret("������ʾ","�Ѵ�����ͬ�ı������ˣ��޸�ʧ��");
					}
				}
			});
				
		}
	}else{
		alret("������ʾ","��ѡ�������Ĺ�ϵ");
	}
}

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

function deleteInsured(serialNo) {
	Sinosoft.alert({
		contentStr: 'ȷ��Ҫɾ���ñ���������',
		okStr: 'ȷ��',
		cancelStr: 'ȡ��',
		cancelBtnShow:true,//�Ƿ���ʾ�رհ�ť
		okFunc:function(){
			window.location.href = "${ctx }/insured/deleteInsured.do?id=" + serialNo;
		}
	});
}
</script>