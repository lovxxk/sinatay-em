$(document).ready(
		function() {
			
			// �����Ч
			$('.click_btn').mousedown(function() {
				$(this).css({
					'top' : '1px',
					'left' : '1px'
				});
			}).mouseup(function() {
				$(this).css({
					'top' : '0',
					'left' : '0'
				});
			});
			
			//���ص�ȡ�����Ľ���
			$('#backSub').click(function() {
				window.location.href = contextRootPath + "/email/myEmailSubscribe.do";

			});
			$('.to_edit').click(function() {
				$(this).hide();
				$(this).next().show();

				var parent = $(this).parents('.content_col');
				var input = parent.find('.edit_input');

				input.show();

				input.each(function() {
					$(this).val($(this).siblings().text());
				});
				input.siblings().hide();
			});

			$('.save_edit').click(function() {
				$(this).hide();
				$(this).prev().show();

				var parent = $(this).parents('.content_col');
				var text = parent.find('.text_show');

				text.show();

				text.each(function() {
					$(this).text($(this).siblings().val());
				
				});

				if (text.siblings().val() == '') {
					$(this).prev().text('����');
				} else {
					$(this).prev().text('�޸�');
				}
				text.siblings().hide();
			});

			$('.content').click(function() {
				if ($(this).hasClass('tip')) {
					return;
				}

				$(this).addClass('select');
				$(this).siblings().removeClass('select');
			});

			Sinosoft.namespace('sinatay.email');

			$('.no_subscribe .subscribe').click(
					function() {
						window.location.href = contextRootPath + "/email/notSubscribe.do";

					});
			//ת����ȡ������ҳ��
			$('#cancle').click(
					function() {
						window.location.href = contextRootPath + "/email/myEmailSubscribe.do";

					});
			$('.content .checkbox').each(function() {
				$(this).jCheckBox();
			});

			// ����
			$('.click_btn').click(
					function() {
						//�����ʽ�Ƿ���ȷ
						
						var isEmail=0;
						
						var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
						var flag=0;
						// ���ı�ǩ
						var subFlag = new Array();
						// ���涩�ĵ�����
						var subJsonStr = "[";
						// ������б�ѡ��Ľڵ�
						$(".checkbox_check").each(function(index) {
							subFlag[index] = $(this).parent().attr('id');
						});
						// Ϊѡ�ж�������
						if (subFlag.length == 0) {
							Sinosoft.alert({
								contentStr : '�Բ��𣬲���ʧ�ܣ�',
								subContentStr : '<h3>δѡ�ж��ĵ��Ӻ�����Ϣ</h3>',
								okStr : 'ȷ��',
								cancelBtnShow:false//�Ƿ���ʾ�رհ�ť
							});
						} else {
							//����ѡ��ı���
							for ( var i = 0; i < subFlag.length; i++) {
								//��ȡѡȡ������ֵ
								var choiceIndex=subFlag[i];
								if ($("#email" + choiceIndex).text() == '') {
									flag=1;
								}
								//��������޸��Ƿ���ȷ
								if (!reg.test($("#email" + choiceIndex).text())) {
									isEmail=1;
								}
								// ���涩�ĵı����� jsonStr
								if (subFlag.length == 1) {
									subJsonStr += "{policyNo:"
											+ $("#policyNo" + choiceIndex).text() + ",email:"+$("#email" + choiceIndex).text()+"}";
								} else {
									subJsonStr += "{policyNo:"
											+ $("#policyNo" + choiceIndex).text() + ",email:"+$("#email" + choiceIndex).text()+"},";
								}

							}
							subJsonStr += "]";
							//���䲻��Ϊ��
							if(flag==1){
								Sinosoft.alert({
									contentStr : '�Բ��𣬲���ʧ�ܣ�',
									subContentStr : '<h3>���䲻��Ϊ��</h3>',
									okStr : 'ȷ��',
									cancelBtnShow:false//�Ƿ���ʾ�رհ�ť
								});
								 return;
							}
							if(isEmail==1){
								Sinosoft.alert({
									contentStr : '�����ʽ���󣬲���ʧ�ܣ�',
									subContentStr : '<h3>��������������������ʽ�Ƿ���ȷ��</h3>',
									okStr : 'ȷ��',
									cancelBtnShow:false//�Ƿ���ʾ�رհ�ť
								});
							 return;
							}
							if(flag==0){
								var test;
								var loading = new Sinosoft.LoadingDialog({
									contentStr: '�����ĵȴ�...',
									titleStr:'����',
									okStr:'',
									noCancel: true,
									closeFunc:function(){
										
									},
									waitFunc:function(){
										return test;
									}
								});
								
								// ����ajax����
								$.ajax({
									url : contextRootPath + "/email/subscribed.do",
									type : "POST",
									data :"subJsonStr="+subJsonStr,
									dataType : 'json',
								    beforeSend : function(){
								    	loading.open();
								    },
									success:function(data, textStatus) {
										loading.close();
										if(data.flag=='0'){
											Sinosoft.alert({
												contentStr : '�Բ��𣬲���ʧ�ܣ�',
												subContentStr : data.desc,
												okStr : 'ȷ��'
											});
										}else{
											//����һ�����ĳɹ��ĶԻ���
											new Sinosoft.InteractiveDialog({
												layout : loadSubSccess(),
												width:410,//�Զ��������-�������������
												cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
												okStr : 'ȷ��',
												cancelStr : 'ȡ��',
												okFunc:function(){
													if(data.notSubCount==0){
														window.location.href = contextRootPath + "/email/myEmailSubscribe.do";
													}else
													//���ȷ����ťִ�з���
													window.location.href = contextRootPath + "/email/notSubscribe.do";
												}
											}).open();
										}
										
									},
									error : function(backData) {
										
										a_alert("�����쳣��");
									}
								});
							}
						
						
						}

					});
		});

function loadSubSccess(){
	var subSuccess = $('<div class="subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="main_txt">���ĳɹ�</div>'
			+'<div class="sub_txt">��л�㶩����̩���յĵ��Ӻ�������</div></div></div>');
	return subSuccess;
}
function loadSubscribePolicy() {


	var num = 5;
	var subscribePolicy = $('<div class="subscribe_policy">'
			+ '<p class="alert_content content_mid">������&nbsp;<font color="#ff4141">'
			+ num
			+ '</font>&nbsp;�ű���δ��ӣ�</p>'
			+ '<div class="subscribe_policy_list">'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20141123114345</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">��̩������ʿ���ϼƻ�</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">����������ȫ���գ��ֺ��ͣ�A��</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">����������ȫ���գ��ֺ��ͣ�A��</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">����������ȫ���գ��ֺ��ͣ�A��</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">����������ȫ���գ��ֺ��ͣ�A��</span></div></div>'
			+ '</div></div>');

	sinatay.email.subOpts = [];

	subscribePolicy.find('.subscribe_check').each(function() {
		sinatay.email.subOpts.push($(this).jCheckBox());
	});

	return subscribePolicy;
}