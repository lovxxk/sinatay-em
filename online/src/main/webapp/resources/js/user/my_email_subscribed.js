//��ʼ���ֻ���֤����ʱ����
var intervalProcess;
var timenum = 120;
//���ֻ�
function doNoPhone(){
	var parameter='�ֻ�����';
	var receive_input = $('<div class="alert_phone_input">'
			+ '<label class="alert_phone_label">������'
			+ parameter
			+ '��</label>'
			+ '<input class="alert_phone_text" type="text" name="emailOrPhone" id="emailOrPhone" maxlength=30/>'
			+ '</div><br>');	
	
	var emailOrPhone = $("#receive_text").val();
	
	new Sinosoft.InteractiveDialog({
		layout : receive_input,
		width:490,
		cancelStr:'ȷ��',
		okBtnShow:false,
		cancelFunc:function(){
			var customerName = $("#emailOrPhone").val();
			if (customerName == ""){
				Sinosoft.alert({
					contentStr : '�ֻ�������󣬲���ʧ�ܣ�',
					subContentStr : '<h3>�����벻Ϊ�յ��ֻ����룡</h3>',
					okStr : 'ȷ��',
					cancelBtnShow:false// �Ƿ���ʾ�رհ�ť
				});
				
			}
			//�ж��û�¼����Ƿ����ֻ���
			var phoneFalg = false;
			if (!isNaN(customerName) && customerName.length == 11 && customerName.indexOf(".") < 0) {
				//11λ�ֻ����Ƿ�Ϸ�
				var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(customerName);
				if (!regmobile) {
					alert("�����ֻ��Ų��Ϸ�");
					return false;
				} else {
					phoneFalg = true;
				}
			}
			
			if (!phoneFalg) {
				Sinosoft.alert({
					contentStr : '�ֻ���ʽ���󣬲���ʧ�ܣ�',
					subContentStr : '<h3>������Ϸ����ֻ����룡</h3>',
					okStr : 'ȷ��',
					cancelBtnShow:false// �Ƿ���ʾ�رհ�ť
				});
				return false;
			}
			$.ajax({
				url : contextRootPath+'/edit/supplyUserPersonal.do',
				type : 'POST',
				data : "param=" + customerName,
				dataType : "text",
				success : function(data){
					if (data == 'success') {
						location.reload();
					} else {
						a_alert('�������');
					}
				}
			});
		}
	}).open();
}

function checkDate(){
	//������ڸ�ʽ�Ƿ�ǰ
	var startDate=$('#date_start').val();
	var endDate=$('#date_end').val();
	var startDateArr=startDate.split('-');
	var endDateArr=endDate.split('-');
	var v1=new Date(startDateArr[0],startDateArr[1],startDateArr[2]);
	var v2=new Date(endDateArr[0],endDateArr[1],endDateArr[2]);
	if(v2<v1){
		Sinosoft.alert({
			contentStr : '���ڸ�ʽ���󣬲���ʧ�ܣ�',
			subContentStr : '<h3>��ʼ���ڲ��ܴ��ڽ������ڣ�</h3>',
			okStr : 'ȷ��',
			cancelBtnShow:false// �Ƿ���ʾ�رհ�ť
		});
		return false;
	}
	
	return true;
}
function loadUpdateEmail(emailChangeJsonStr){
	var mobileStr = $('#mobile').text();
	mobileStr=mobileStr.substring(0,3)+"****"+mobileStr.substring(7);
	//��һ���ֻ���֤�Ի���
	var a ='<div>'
		  +'  <div class="send_vali">'
		  +'     <p>������Ԥ�����ֻ���<span id="b_appntphone">'+mobileStr+'</span>������֤��</p>'
		  +'     <p>�뽫��֤�������¿�</p>'
		  +'  </div>'
		  +'  <div class="vali_input">'
		  +'     <label for="phone_amount_validate">��֤�룺</label>'
		  +'     <input id="checkNo" type="text"/>'
		  //�����ж��û��Ƿ���������֤�밴ť����ֹ��֤���������
		  //��������ı��jsonStr
		  +'     <input id="emailChangeJsonStr" type="text" style="display:none;" value="'+emailChangeJsonStr+'"/>'
		  +'     <input type="button" class="email_phone_vali click_btn getPhonePwd" onclick="getPhoneDynamicNumber();" value="������֤��"/>'
		  +'  </div>'
		  //������Ϣ
		  +'  <div class="amount_num" id="phone_amount_valiinfo"></div>'
		  //������һ����ť
		  +'  <input class="alert_confirm" style="margin-right:160px;"  onclick="sendPhone();" type="button" value="��һ��"/>'
		  +'  <br/>'
		  +'  <br/>'
		  +'</div>';
	return a;
}

//������һ������¼�
function sendPhone(){
	//��������ֵ�Ƿ����
	//����ֵ
	var inputNo = $("#checkNo").val();
	//�Ӻ�̨���ݿ�����ȡ��ͨ����̨�ж�  Y�ɹ� Nʧ��
	
	var isSuccess;

	var emailChangeJsonStr=$("#emailChangeJsonStr").val();
	if (inputNo==null||inputNo==''){
		$("#phone_amount_valiinfo").show();
		$("#phone_amount_valiinfo").text('��������ȷ���ֻ���֤�룡');
		//�߿���ɫ���
		$("#checkNo").css('border-color','red');
		return;
	}else{
		$.ajax({
			url : contextRootPath+'/email/checkPhone.do',
			type : 'POST',
			data : "validPhoneNo=" + inputNo,
			dataType : "json",
			async: false,
			success : function(data){
				isSuccess=data.checkPhone;
				if(isSuccess==''){
					a_alert('ϵͳ����');
				}
				
			},
			error: function(backData) {
				a_alert('�������');
			}
		});
		//ϵͳ����
		if(isSuccess =='ERROR'){
			a_alert('ϵͳ����');
		}
		//����ֻ���֤���������
		if(isSuccess == 'N'){
			$("#phone_amount_valiinfo").show();
			$("#phone_amount_valiinfo").text('�ֻ���֤���������');
			//�߿���ɫ���
			$("#checkNo").css('border-color','red');
			return;
		}
		//ajaxִ���޸�����
		$("#phone_amount_valiinfo").hide();
		$("#checkNo").css('border-color','');
		var test;
		var loading = new Sinosoft.LoadingDialog({
			contentStr: '�����ĵȴ�...',
			titleStr:'�����޸�',
			okStr:'',
			noCancel: true,
			closeFunc:function(){
				
			},
			waitFunc:function(){
				return test;
			}
		});
		//�޸�����
		$.ajax({
			type : "POST",
			url:contextRootPath+"/email/changeEmail.do",
		    data:'changeEmailJsonStr='+emailChangeJsonStr,
		    dataType : 'json',
		    beforeSend : function(){
		    	loading.open();
		    },
			success : function(backData) {
				loading.close();
				if(backData.flag=='0'){
					Sinosoft.alert({
						contentStr : '�Բ��𣬲���ʧ�ܣ�',
						subContentStr : backData.desc,
						okStr : 'ȷ��'
					});
				}else{
					//����һ�������޸ĳɹ��ĶԻ���
					new Sinosoft.InteractiveDialog({
						layout : loadChangeEmailSccess(),
						width:410,//�Զ��������-�������������
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
						okStr : 'ȷ��',
						cancelStr : 'ȡ��',
						okFunc:function(){
							//���ȷ����ťִ�з���
							location.href=contextRootPath + "/email/myEmailSubscribe.do";
						}
					}).open();
				}
				
			},
			error: function(backData) {
				a_alert('�������');
			}
		});

	}
}
//�ֻ���֤����ʱ
function counttime() {
	if (timenum > 0) {
		$('.getPhonePwd').val('('+timenum+')������·���');
		timenum = timenum - 1;
	} else {
		$('.getPhonePwd').val('����ٴη���');
		$('.getPhonePwd').valiCodeEnable();
		clearInterval(intervalProcess);
	}
}
//����ֻ���̬��֤��
function getPhoneDynamicNumber(){
	$('.getPhonePwd').valiCodeDisable();
	//ajax�������
	$.ajax({
		type : "POST",
		async : false,
		url:contextRootPath+"/email/sendPhoneDynamicNumber.do",
		dataType : 'json',
		success : function(backData) {
			if(backData.flag==1){
				$("#phone_amount_valiinfo").show();
				$("#phone_amount_valiinfo").text(backData.desc);
				//�߿���ɫ���
				$("#checkNo").css('border-color','red');
			}else{
				timenum = 120;
				intervalProcess = setInterval('counttime()',1000);
			}
		},
		error: function(backData) {
			Sinosoft.alert({
				contentStr: "����ʧ��",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
				}
			});
		}
	});
}
$(document).ready(function(){
	// �����Ч
	$('.click_btn').mousedown(function(){
		$(this).css({
			'top':'1px',
			'left':'1px'
		});
	}).mouseup(function(){
		$(this).css({
			'top':'0',
			'left':'0'
		});
	});
	
	$('.member_menu .item').eq(4).addClass('select');
	$('.member_menu .item').eq(4).siblings().removeClass('select');
	
	$('.to_edit').click(function(){
		$(this).hide();
		$(this).next().show();
		
		var parent = $(this).parents('.content_col');
		var input = parent.find('.edit_input');
		
		input.show();
		
		input.each(function(){
			$(this).val($(this).siblings().text());
		});
		input.siblings().hide();
	});
	
	$('.save_edit').click(function(){
		
		var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
		$(this).hide();
		$(this).prev().show();
		//�����޸�Json�ַ���
		var emailChangeJsonStr="";
		var parent = $(this).parents('.content_col');
		var text = parent.find('.text_show');
		//֮ǰ�������
		var oldEmail=text.text();
		//������
		var policyNo = parent.find('#policyNo').val();
		//�û��ֻ�����
		var mobile = $('#mobile').text();
		//����
		var email = text.siblings().val();
		//��������
		var subType= parent.find('#subType').val();
		// չʾ�ı���
		text.show();
		text.each(function(){
			//���������ֵ
			$(this).text($(this).siblings().val()); 
			var emailDiv=$(this);
			//��֤�û��Ƿ���ֻ�����
			if(mobile==null||mobile==''){
				doNoPhone();
				return false;
			}
			// �Ƿ������ʽ��ȷ
			if(!reg.test($(this).siblings().val())){
				Sinosoft.alert({
					contentStr : '�����ʽ���󣬲���ʧ�ܣ�',
					subContentStr : '<h3>��������������������ʽ�Ƿ���ȷ��</h3>',
					okStr : 'ȷ��',
					cancelBtnShow:false// �Ƿ���ʾ�رհ�ť
				});
				$(this).text(oldEmail);
			 return;
			}
			else if($(this).siblings().val()==''){
				Sinosoft.alert({
					contentStr : '�Բ��𣬲���ʧ�ܣ�',
					subContentStr : '<h3>���䲻��Ϊ��!</h3>',
					okStr : 'ȷ��',
					cancelBtnShow:false// �Ƿ���ʾ�رհ�ť
				});
				$(this).text(oldEmail);
				 return;
			}else if($(this).siblings().val() == oldEmail){
				Sinosoft.alert({
					contentStr : '�Բ��𣬲���ʧ�ܣ�',
					subContentStr : '<h3>������Ϣ��ԭ������Ϣ��ͬ!</h3>',
					okStr : 'ȷ��',
					cancelBtnShow:false// �Ƿ���ʾ�رհ�ť
				});
				 return;
				
			}else{
				//ƴ��json�ַ���
				emailChangeJsonStr='{policyNo : '+policyNo+',email :'+email+',subType :'+subType+'}';
				
				Sinosoft.alert({
					contentStr : '������ı������������ı�����Ϣ��',
					okStr : 'ȷ��',
					cancelStr:'ȡ��',//ȡ����ť��ʾ����
					okBtnShow:true,//�Ƿ���ʾȷ����ť
					cancelBtnShow:true,
					okFunc:function(){
						//ִ���ֻ���֤
						Sinosoft.alert({
							contentStr : '��������',
							subContentStr : loadUpdateEmail(emailChangeJsonStr),
							width:580,
							okStr : 'ȷ ��',
							okBtnShow:false,//�Ƿ���ʾȷ����ť
							cancelBtnShow:false
							
						});
					},
					cancelFunc:function(){
						//���ȡ����ťִ�з���
						//����ر�ͼ��ִ�з���
						emailDiv.text(oldEmail);
					},
					closeFunc:function(){
						emailDiv.text(oldEmail);
						$('.getPhonePwd').val('������֤��');
						$('.getPhonePwd').valiCodeEnable();
						clearInterval(intervalProcess);
					}
					
				});

			}
			// ����޸ĵ������Ƿ���ȷ
		});
		
		// ���ص�ǰ����Ԫ��
		text.siblings().hide();
	});
	
	$('.content').click(function(){
		if($(this).hasClass('tip')){
			return;
		}
		
		$(this).addClass('select');
		$(this).siblings().removeClass('select');
	});
	
	Sinosoft.namespace('sinatay.email');
	
	// ת��δ���Ľ���
	$('.subscribe').click(function(){
		window.location.href = contextRootPath + "/email/notSubscribe.do";

	});
	// չʾ��ѡ��
	$('.content .checkbox').each(function() {
		$(this).jCheckBox();
	});
	
	// ���ڳ�ʼ��
	$('#date_start,#date_end').click(function(){
		WdatePicker({
			startDate:'%y-%M-%d',
			dateFmt:'yyyy-MM-dd'
		});
	});
	
	// ȡ������
	$('.operation').click(function() {
		
		var input = $(this).siblings('.content_col');
		var text = input.find('.text_show');
		//ȡ������json����
		var cancleSubJsonStr='';
		//������
		var policyNo = input.find('#policyNo').val();
		//����
		var email = text.text();
		//��������
		var subType= input.find('#subType').val();
		//�ȶ���ֽ�ʺ���Ҳ���ĵ��Ӻ��� ��ʾ�û��Ƿ�ȡ��ֽ�ʺ���  �ǣ�ȡ��ֽ�ʺ��� subtype��04  �� ��02 ����ֽ�ʺ���
			Sinosoft.alert({
				contentStr : '���Ӻ���ȡ�����ģ�',
				subContentStr : loadcancleSub(),
				width:510,
				okStr : 'ȷ��',
				cancelStr:'ȡ��',//ȡ����ť��ʾ����
				okFunc:function(){
					//���ȷ����ťִ�з���
					cancleSubJsonStr='{policyNo : '+policyNo+',email :'+email+',subType :'+02+'}';
					var test;
					var loading = new Sinosoft.LoadingDialog({
						contentStr: '�����ĵȴ�...',
						titleStr:'ȡ������',
						okStr:'',
						noCancel: true,
						closeFunc:function(){
							
						},
						waitFunc:function(){
							return test;
						}
					});
					$.ajax({
						type : "POST",
						url : contextRootPath+"/email/cancelEmail.do",
						dataType : 'json',
					    data:'cancleSubJsonStr='+cancleSubJsonStr,
					    beforeSend : function(){
					    	loading.open();
					    },
						success : function(backData) {
							loading.close();
							if(backData.flag=='0'){
								Sinosoft.alert({
									contentStr : '�Բ��𣬲���ʧ�ܣ�',
									subContentStr : backData.desc,
									okStr : 'ȷ��'
								});
							}else{
								//����һ��ȡ�����ĳɹ��ĶԻ���
								new Sinosoft.InteractiveDialog({
									layout : loadCalSubSccess(),
									width:410,//�Զ��������-�������������
									cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
									okStr : 'ȷ��',
									cancelStr : 'ȡ��',
									okFunc:function(){
										//���ȷ����ťִ�з���
										location.href=contextRootPath + "/email/myEmailSubscribe.do";
									}
								}).open();
							}
							
						},
						error: function(backData) {
							a_alert('�������');
						}
					});
				}
			});
		
		
				
	});
});




//����ֻ���֤���Ƿ���ȷ
function checkPhoneCheckNo(phoneDynamicNumber){
	if(phoneDynamicNumber != $("#phone_amount_validate").val()){
		//�����ֻ���֤�����Ի���
		
		return false;
	}
}

function loadCalSubSccess(){
	var subSuccess = $('<div class="subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="main_txt">�˶��ɹ�</div>'
			+'<div class="sub_txt">���ѳɹ�ȡ��������̩���յĵ��Ӻ�������</div></div></div>');
	return subSuccess;
}
//�����޸ĳɹ�
function loadChangeEmailSccess(){
	var changeSuccess = $('<div class="subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="main_txt">�޸ĳɹ�</div>'
			+'<div class="sub_txt">�����޸������ַ�ɹ���</div></div></div>');
	return changeSuccess;
}
//ȡ�����ĵ��Ӻ���
function loadcancleSub(){
	var cancaleSub='�˶�����������������̩�������еĵ��Ӻ����������ѯ�������ǽ���<b>ֽ�ʺ�����ʽ</b>����Ϊ���ṩ����';
	return cancaleSub;
}
////ȡ�����ĵ��Ӻ���
//function loadcancleSub2(){
//	var cancaleSub='�˶����㽫����������̩���չ�˾�ṩΪ�ñ����ṩ���Ӻ����������ѯ����<br/>'
//		            +'Ŀǰ�����Ѷ���<font color="red">���Ӻ�����ֽ�ʺ���</font>��'
//		            +'<b>�Ƿ���ֽ�ʺ�����ʽ��</b>'
//	return cancaleSub;
//}
function loadSubscribePolicy(){
	var num = 4;
	var subscribePolicy = $('<div class="subscribe_policy">'
			+ '<p class="alert_content content_mid">������&nbsp;<font color="#ff4141">'+num+'</font>&nbsp;�ű���δ��ӣ�</p>'
			+ '<div class="subscribe_policy_list">'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20141123114345</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">��̩������ʿ���ϼƻ�</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">����������ȫ���գ��ֺ��ͣ�A��</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">����������ȫ���գ��ֺ��ͣ�A��</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">����������ȫ���գ��ֺ��ͣ�A��</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">����������ȫ���գ��ֺ��ͣ�A��</span></div></div>'
			+ '</div></div>');
	
	sinatay.email.subOpts = [];
	
	subscribePolicy.find('.subscribe_check').each(function(){
		sinatay.email.subOpts.push($(this).jCheckBox());
	});
	
	return subscribePolicy;
}


