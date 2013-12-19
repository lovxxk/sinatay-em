var timenum = 5;
var intervalProcess;
	function validateIdNumber(identifyNumber) {
		var eighteenCard = /^[\d]{6}(19|20)*[\d]{2}((0[1-9])|(10|11|12))([012][\d]|(30|31))[\d]{3}[xX\d]$/;
		var fifteenCard = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
		if (identifyNumber.length == 15) {
			if (!fifteenCard.test(identifyNumber)) {
				$('.validIdentifyNumber').text("15λ���֤������д���Ϸ�");
				$(".validIdentifyNumberFlag").attr("value", false);
				return false;
			}
			var year = identifyNumber.substring(6, 8);
			var month = identifyNumber.substring(8, 10);
			var day = identifyNumber.substring(10, 12);

			attrBirthday("19"+year, month, day);
			
			$('.validIdentifyNumber').text("");
			$(".validIdentifyNumberFlag").attr("value", true);
			
			//0�У�1Ů
			var sexFlag = identifyNumber.substring(13, 14);
			var sex = $('#sex').val();
			//���֤�У�13579��Ů��02468
			if (sexFlag % 2 == 1 && sex != 0) {
				$('#sex').val(0);
				$('.input_row.sex .input_sex').eq(0).click();
			}
			else if(sexFlag % 2 == 0 && sex != 1){
				$('#sex').val(1);
				$('.input_row.sex .input_sex').eq(1).click();
			}else {
				$('.validIdentifyNumber').text("");
				$(".validIdentifyNumberFlag").attr("value", true);
			}
		} else if (identifyNumber.length == 18) {
			if (!eighteenCard.test(identifyNumber)){
				$('.validIdentifyNumber').text("18λ���֤������д���Ϸ�");
				$(".validIdentifyNumberFlag").attr("value", false);
				return false;
			}
			var year = identifyNumber.substring(6, 10);
			var month = identifyNumber.substring(10, 12);
			var day = identifyNumber.substring(12, 14);
			
			attrBirthday(year, month, day);
			
			$('.validIdentifyNumber').text("");
			$(".validIdentifyNumberFlag").attr("value", true);
			
			//0�У�1Ů
			var sexFlag = identifyNumber.substring(16, 17);
			var sex = $('#sex').val();
			//���֤�У�13579��Ů��02468
			if (sexFlag % 2 == 1 && sex != 0) {
				$('#sex').val(0);
				$('.input_row.sex .input_sex').eq(0).click();
			}
			else if(sexFlag % 2 == 0 && sex != 1){
				$('#sex').val(1);
				$('.input_row.sex .input_sex').eq(1).click();
			} else {
				$('.validIdentifyNumber').text("");
				$(".validIdentifyNumberFlag").attr("value", true);
			}
			
		} else {
			attrBirthday("","","");
			
			$('.validIdentifyNumber').text("���֤������д����ȷ");
			$(".validIdentifyNumberFlag").attr("value", false);
			return false;
		}
	}
	
	function attrBirthday(year,month,day) {
		$('#app_year').data('jSelect').setValue(year);
		$('#app_month').data('jSelect').setValue(month);
		$('#app_day').data('jSelect').setValue(day);
		if (year != "" && month != "" && day != "") {
			$("#appBirthday").attr("value", year + "-" + month + "-" + day);
		}
	}
	
	function fillIdEffecDate() {
		if (s_idEffDate != null && s_idEffDate != "") {
			var year = s_idEffDate.split("-")[0];
			var month = s_idEffDate.split("-")[1];
			var day = s_idEffDate.split("-")[2];
			
			if (s_idEffDate.split("-").length == 1) {
				var identifyEffectiveDate = $("#appIdentifyEffectiveDate").val();
				year = identifyEffectiveDate.split("-")[0];
				month = identifyEffectiveDate.split("-")[1];
				day = identifyEffectiveDate.split("-")[2];
			}
			
			$('#app_validity_year').data('jSelect').setValue(year);
			$('#app_validity_month').data('jSelect').setValue(month);
			$('#app_validity_day').data('jSelect').setValue(day);
		}
	}
	//����ͻ����ղ�Ϊ�գ��Զ������������
	//���֤������Ϊ���֤�������֤�źŲ�Ϊ�գ����Զ������������
	function fillBirthday() {
		if (s_birthday != null && s_birthday != "") {
			var year = s_birthday.split("-")[0];
			var month = s_birthday.split("-")[1];
			var day = s_birthday.split("-")[2];
			
			if (s_idEffDate.split("-").length == 1) {
				var birthday_year = $("#birthday").val();
				year = birthday_year.split("-")[0];
				month = birthday_year.split("-")[1];
				day = birthday_year.split("-")[2];
			}

			$('#birthday_year').data('jSelect').setValue(year);
			$('#birthday_month').data('jSelect').setValue(month);
			$('#birthday_day').data('jSelect').setValue(day);
			if (year != "" && month != "" && day != "") {
				$("#birthday").attr("value", year + "-" + month + "-" + day);
			}
		}
	}
// 	/**ajax��֤��֤��**/
	function checkImage() {
	 	var dataFlag = false;
		var inputCode = $("#erand").val();
		$.ajax({
			type : "POST",
			async : false,
			url : "${ctx}/register/checkAjaxAction.do",
			dataType : 'text',
			data : {checkCode : inputCode },
			success: function(data) {
				if (data == "success") {
					dataFlag = true;
				} else {
					dataFlag = false;
				}
			}
		});
		return dataFlag;
	}
	
	function validatePhoneDynamicPwd(mobile, phonePwd) {
	 	var dataFlag = false;
		var inputCode = $("#erand").val();
		$.ajax({
			type : "POST",
			async : false,
			url : "${ctx}/update/validatePhoneDynamicPwd.do",
			dataType : 'text',
			data : {mobile : mobile,phonePwd : phonePwd },
			success: function(data) {
				if (data == "success") {
					dataFlag = true;
				} else {
					dataFlag = false;
				}
			}
		});
		return dataFlag;
	}
	
	function getPhoneDynaPwd() {
		var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test($('.mobile').val()); 
		if (!regmobile) {
			Sinosoft.alert({
				contentStr: "�ֻ������ʽ����ȷ��",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		}
		$('.getPhonePwd').attr('disabled','disabled');
		$('.getpwd').attr('disabled','disabled');
		$.ajax({
			url : '${ctx}/update/getPhoneDynamicNumber.do',
			type : 'POST',
			data : "phoneNum="+$('input[name="customer.mobile"]').val(),
			dataType : "json",
			success : function(data){
				var falg = data.resultFlag;
				//����̨���ص���֤�븳ֵ��������
				if (data.phonePwd != "")
					$("#inputPwd").attr("value", data.phonePwd);
				if (falg == "T") {
					var mobile = $("input[name='customer.mobile']").val();
					var mobileStr = '';
					if(mobile != '' && mobile.length == 11){
						mobileStr = mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length);
					}
					Sinosoft.alert({
						contentStr: "�ֻ���̬�����ѷ��͵�"+mobileStr+",��ע����ա�",
						width:480,
						okStr: 'ȷ��',
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
						okFunc:function(){
							
						}
					});
					timenum = 8;
					intervalProcess = setInterval('counttime()',1000);
				} else if(falg =="F") {
					Sinosoft.alert({
						contentStr: "���ŷ��͹���Ƶ�������Ժ����ԣ�",
						width:480,
						okStr: 'ȷ��',
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
						okFunc:function(){
							
						}
					});
					$('.getPhonePwd').attr('disabled',false);
					$('.getpwd').attr('disabled','false');
				} else {
					Sinosoft.alert({
						contentStr: "����ʧ��!",
						width:480,
						okStr: 'ȷ��',
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
						okFunc:function(){
							
						}
					});
					$('.getPhonePwd').attr('disabled',false);
					$('.getpwd').attr('disabled','false');
				}
			}
		});
	}
	function counttime() {
		if (timenum > 0) {
			$('.getPhonePwd').val('('+timenum+')������·���');
			timenum = timenum - 1;
		} else {
			$('.getPhonePwd').val('����ٴη���');
			$('.getPhonePwd').attr('disabled',false);
			clearInterval(intervalProcess);
		}
	}
	function changeImage(obj){
		$("#"+obj).attr("src","${ctx}/web/user/register/image.jsp?id="+Math.random(100)*10);
	}
	function validatePersonalInfo_bak() {
		if ($(".userName").val() == "") {
			Sinosoft.alert({
				contentStr: "������������ʵ����",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		}
		if ($("#birthday_year").val() == "" ||  $("#birthday_month").val() == "" || $("#birthday_day").val() == "") {
			Sinosoft.alert({
				contentStr: "����������",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		} else {
			$("#birthday").val($("#birthday_year").val() + "-" + $("#birthday_month").val() + "-" + $("#birthday_day").val());
		}
		
		if ($("#validity_year").val() != "" &&  $("#validity_month").val() != "" && $("#validity_day").val() != "") {
			$("#identifyEffectiveDate").val($("#validity_year").val() + "-" + $("#validity_month").val() + "-" + $("#validity_day").val());
		} 
		if ($(".validIdentifyNumberFlag").val() == 'false') {
			Sinosoft.alert({
				contentStr: "֤��������������",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		}
		
		if ($(".identifyType").val() == 0) {
			var birthday = $("#birthday").val();
			var identifyNumber = $("#identifyNumber").val();
			var birth = "";
			if (identifyNumber.length == 18) {
				birth = identifyNumber.substring(6, 14);
			} else {
				birth = "19" + identifyNumber.substring(6, 12);
			}
			var str = birthday.replace('-','').replace('-','');
			if (str != birth) {
				Sinosoft.alert({
					contentStr: "���֤������������ڲ���",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					okFunc:function(){
						
					}
				});
				return false;
			}
		}
		
		if (!checkUserPersonal()) {
			Sinosoft.alert({
				contentStr: '���Ѱ���Ӧ����,�����޸���ʵ�������Ա𣬳������ڣ�֤��������֤������',
				width:480,
				okStr: 'ȷ��'
			});
			return false;
		}
		
		
		//����һ��ȡ�����ĳɹ��ĶԻ���
		new Sinosoft.InteractiveDialog({
			layout : loadCalSubSccess(),
			width:410,//�Զ��������-�������������
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okStr : 'ȷ��',
			cancelStr : 'ȡ��',
			okFunc:function(){
				$("#personalInfoForm").submit();
				return true;
			}
		}).open();
		return false;
	}
	
	function checkUserPersonal() {
		var userId = $("#userID").val();
		var userName = $("#appName").val();
		var identifyType = $("#appIdType").val();
		var identifyNumber = $("#appIdNo").val();
		var sex = $("#appSex").val();
		var birthday = $("#appBirthday").val();
		var dataFlag = false;
		$.ajax({
			type : "POST",
			async : false,
			url : contextRootPath+ '/edit/checkBindPolicy.do',
			dataType : 'text',
			data : {
				userId : userId,
				userName : userName,
				identifyType : identifyType,
				identifyNumber : identifyNumber,
				sex : sex,
				birthday : birthday
			},
			success: function(data) {
				if (data == "success") {
					dataFlag = true;
				} else {
					dataFlag = false;
				}
			}
		});
		return dataFlag;
	}
	
	function loadCalSubSccess(){
		var subSuccess = $('<div class="alert_subscribe">'
				+'<div class="success"></div><div class="main_content">'
				+'<div class="main_txt">�޸ĳɹ�</div>'
				+'<div class="sub_txt">���ѳɹ��޸ĸ��˻�������</div></div></div>');
		return subSuccess;
	}
	
	//Ѫ����Ĭ��ֵ
	function fillBloodType() {
		$('#bloodType').data('jSelect').setValue(s_bloodType);
	}
	function fillIdType() {
		$('#appIdType').data('jSelect').setValue(s_idType);
	}