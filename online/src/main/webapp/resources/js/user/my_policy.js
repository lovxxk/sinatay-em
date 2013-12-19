$(document).ready(function(){
	
	//�����Ч
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
	
	$('.member_menu .item').eq(1).addClass('select');
	$('.member_menu .item').eq(1).siblings().removeClass('select');
	
	Sinosoft.namespace('sinatay.newPolicy');
	
	$('.policy_item.buy_policy .action_icon').click(function(){
		location.href =$('#ctx').val() + "/productsDisplay/onlineShop.do?parentAttrID=&topNum=2&attrID=ROOT";
	});
	
	//����ѡ���¼�
	$('.policy_item').click(function(){
		$('.policy_item').removeClass('select');
		$(this).addClass('select');
		addClose();
	});
	
	//ҳ���ʼ���رհ�ť
	addClose();
	
	$('.add_policy .action_icon').click(function(){
		$('.policy_item').removeClass('select');
		$('.add_policy').hide();
		$('.add_policy_input').addClass('select').show();
		$('.add_policy_input .policy_input').val('');
		$('.add_policy_input .policy_input').focus();
	});

	$('.add_policy_input .add_btn').click(function(){
		var policy_no = $('.policy_input').val();
		
		var reg=/^\d+$/;
		if(policy_no == '' || !reg.test(policy_no)){
			Sinosoft.alert({
				contentStr: '��������ȷ�ı�����',
				subContentStr:'�����º˶���Ϣ�������',
				okStr: 'ȷ��',
				cancelStr: 'ȡ��',
				callback:function(){
					$('.add_policy_input .policy_input').focus();
				}
			});
		}else{
			//�󶨱���
			var params = {'policySerialNumber':policy_no};
			var ctx = $('#ctx').val();
			$.ajax({  
				url:ctx+'/infoJson/bindPolicy.do',
				data: params,
				error:function(){
					a_alert("�����쳣��");
				},
				success:function(data){
					$('.add_policy').show();
					$('.add_policy_input').hide();
					if(data.flag == "Y"){
						if(data.hasPolicy == 'Y'){
							$('.buy_policy').hide();
						}
						if(data.listOtherPolicy.length > 0){
							otherPolicy(data.listPolicyList,data.listOtherPolicy);
						}else{
							//flushPolicy(data.listPolicyList);
							var subSuccess = $('<div class="subscribe">'
									+'<div class="success"></div><div class="main_content">'
									+'<div class="main_txt">�󶨳ɹ�!</div>'
									+'<div class="sub_txt">���ѳɹ��󶨸ñ���</div></div></div>');													
							new Sinosoft.InteractiveDialog({
								layout : subSuccess,
								width:410,//�Զ��������-�������������														
								cancelBtnShow:false,
								okFunc:function(){
									location.reload();
								}
							}).open();
						}
					}else{
						a_alert(data.result);
					}
				}
			});
		}
	});
	
	//����ӱ���
	if($('#hasPolicy').val() == 'Y'){
		$.ajax({
			type : "POST",
			async : true,
			url : contextRootPath + "/memberCenterJson/showUnbindPolicy.do",
			dataType : 'json',
			data : {},
			success : function(data) {
				$('.addPolicy .num').html(data.listOtherPolicy.length);
				$('.addPolicy').click(function(){
					if(data.listOtherPolicy.length > 0){
						otherPolicy(data.listPolicyList,data.listOtherPolicy);
					}
				});
			},
			error: function(data) {
			}
		});	
	}
	
	//ǰ̨���棬��ʼ��ҳ�漰������
	var listPolicy = $('.policy_item.bind_policy');
	totalNum = parseInt((listPolicy.length - 1) / 5) + 1;
	if (listPolicy.length > 5) {
		showPage(1);
	}
	for ( var i = 0; i < totalNum; i++) {
		$('<div class="page page_num"><a>' + (i + 1) + '</a></div>')
				.insertBefore($('.page.next_page'))
	}
	$('.page.page_num:first').addClass('now');
	$('.page.prev_page').hide();
	if(totalNum < 2){
		$('.page.next_page').hide();
	}
	$('.page.page_num:gt(4)').hide();
	
	//��ҳ����¼�
	$('.page.first a').click(function() {
		clickPage($('.page.page_num a:first'));
	});
	//βҳ����¼�
	$('.page.last a').click(function() {
		clickPage($('.page.page_num a:last'));
	});
	
	//ҳ�����¼�
	$('.page.page_num a').click(function() {
		clickPage($(this));
	});
	
	//ǰһҳ����¼�
	$('.page.prev_page').click(function(){
		clickPage($('.page.page_num.now').prev().find('a'));
	});
	
	//��һҳ����¼�
	$('.page.next_page').click(function(){
		clickPage($('.page.page_num.now').next().find('a'));
	});
//	
//	var alertType = getQueryString('alertType');
//	
//	if(alertType == '1'){//��Ա����-�ҵı���-�б���״̬-����±���������ʾ
//		Sinosoft.alert({
//			contentStr: '������ı�����Ϣ��ƥ��',
//			subContentStr:'�����º˶���Ϣ������д�����š�',
//			okStr: 'ȷ��',
//			cancelStr: 'ȡ��'
//		});
//	}else if(alertType == '2'){//��Ա����-�ҵı���-�б���״̬-ɾ��������ʾ
//		Sinosoft.alert({
//			contentStr: '����ҳ������ʾ�˷ݱ���',
//			subContentStr:'����������ʾ�������ֶ���ӱ�����',
//			okStr: 'ȷ��',
//			cancelStr: 'ȡ��'
//		});
//	}else if(alertType == '3'){//���Ӻ���ȡ������
//		console.log(1);
//		Sinosoft.alert({
//			contentStr: '���Ӻ���ȡ������',
//			subContentStr:'�˶�����������������̩�������еĵ��Ӻ������ͺͲ�ѯ���� ���ǽ���<b>ֽ�ʺ�����ʽ</b>Ϊ�������ṩ����',
//			okStr: 'ȷ��',
//			cancelStr: 'ȡ��'
//		});
//	}else if(alertType == '4'){//��Ա����-���Ӻ���-δ����-��ʾ������Ϣ
//		Sinosoft.alert({
//			contentStr: '�𾴵Ŀͻ������ã�',
//			subContentStr:'���Ļ�����Ϣ��������������߲˵����С�<a href="account_personal.html"><font color="#FF0000">�˻���Ϣ</font></a>�����в�ȫ��',
//			okStr: 'ȷ��',
//			cancelStr: 'ȡ��'
//		});
//	}else if(alertType == '5'){//��Ա����-���Ӻ���-δ����2-���ĳɹ�������
//		var subscribeSuccess = new Sinosoft.InteractiveDialog({
//			layout : loadSubscribeSuccess()
//		});
//		subscribeSuccess.open();
//	}else if(alertType == '6'){//��Ա����-���Ӻ���-�Ѷ���-�����޸ĵ�����
//		Sinosoft.alert({
//			contentStr: '������ı�����������ı�����Ϣ',
//			okStr: 'ȷ��',
//			cancelStr: 'ȡ��'
//		});
//	}
	
	//�ж��Ƿ��а󶨱��������򱣵�����
	if($('#hasPolicy').val() == 'Y'){
		$('.buy_policy').hide();
	}
	$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
});

//pageNum��1��ʼ
function showPage(pageNum){
	$('.policy_item.bind_policy').hide();
	$('.policy_item.bind_policy:lt(' + (pageNum * 5) + ')').show();
	$('.policy_item.bind_policy:lt(' + ((pageNum - 1) * 5) + ')').hide();
	
	/*//����Ӧ����
	$('.member_main .left_menu').css('height',$('.member_main .right_content').height());*/
}

//���ҳ���¼�
function clickPage(now){
	var nowNum = parseInt(now.html());
	//����ǰ����ת����ʾ
	if(nowNum == 1){
		$('.page.prev_page').hide();
	}else{
		$('.page.prev_page').show();
	}
	if(nowNum == totalNum){
		$('.page.next_page').hide();
	}else{
		$('.page.next_page').show();
	}
	
	//����ʾ��ҳ��
	if(totalNum > 5){
		if(nowNum < 3){
			$('.page.page_num:gt(4)').hide();
			$('.page.page_num:lt(4)').show();
		}else if(nowNum > (totalNum - 2)){
			//alert('??');
			$('.page.page_num:lt(' + (totalNum - 5) + ')').hide();
			$('.page.page_num:gt(' + (totalNum - 5) + ')').show();
		}else{
			//alert(nowNum);
			$('.page.page_num').show();
			$('.page.page_num:lt(' + (nowNum - 3) + ')').hide();
			$('.page.page_num:gt(' + (nowNum + 1) + ')').hide();
			//$('.page.page_num:gt(' + (nowNum - 4) + ')').show();
			//$('.page.page_num:lt(' +  + '):gt(' + (nowNum + 2) + ')').show();
		}
	}
	
	$('.page.page_num').removeClass('now');
	now.parent().addClass('now');
	showPage(nowNum);
}

function loadSubscribeSuccess(){
	var success = $('<div class="subscribe">'
			+ '<div class="success"></div>'
			+ '<div class="main_content"><p class="main_txt">���ĳɹ�</p><p class="sub_txt">��л��������̩���յĵ��Ӻ�������</p></div>'
			+ '</div>');
	return success;
}

//�����ɰ󶨱�����ʾҳ���ʼ��
function loadNewPolicy(otherList){
	var newPolicyStr = '<div class="new_policy">'
			+ '<p class="alert_content content_mid">�����»��� <font color="#ff3333">' + otherList.length +'</font> �ű��������</p>'
			+ '<div class="new_policy_list">';

	for(var i = 0; i < otherList.length; i++){
		newPolicyStr += '<div class="new_policy_item"><div class="new_check"></div><div class="number"><span class="name">�����ţ�</span><span class="value">' + otherList[i].policySerialNumber + '</span></div><div class="insurance_name"><span class="name">�������ƣ�</span><span class="value">' + otherList[i].mainRiskName + '</span></div></div>';
	}
	newPolicyStr += '</div></div>';
	
	var newPolicy = $(newPolicyStr);
	
	
	var check = newPolicy.find('.new_check');
	sinatay.newPolicy.checkOpts = [];
	for(var i=0 ; i< check.length ; i++){
		sinatay.newPolicy.checkOpts.push(check.eq(i).jCheckBox());
	}
	return newPolicy;
}

//��ɾ������
function addClose(){
	$('.policy_list .select .close').click(function(){
		Sinosoft.alert({
			contentStr:'���ȷ���������.���������ͨ��"��ӱ���"���°󶨸ñ���',
			okStr:'ȷ��',
			cancelStr:'ȡ��',
			width:510,
			okFunc:function(){
				var ctx = $('#ctx').val();
				var delPolicySerialNumber = $('.select .pNo').html();
				var params = {'delPolicySerialNumber':delPolicySerialNumber};
				$.ajax({  
					url:ctx+'/infoJson/delPolicy.do',
					data: params,
					error:function(){
						a_alert("�����쳣��");
					},  
					success:function(data){
						var subSuccess = $('<div class="subscribe">'
								+'<div class="success"></div><div class="main_content">'
								+'<div class="main_txt">���ɹ�!</div>'
								+'<div class="sub_txt">���ѳɹ�����ñ�����</div></div></div>');													
						new Sinosoft.InteractiveDialog({
							layout : subSuccess,
							width:410,//�Զ��������-�������������														
							cancelBtnShow:false,
							okFunc:function(){
								location.reload();
							}
						}).open();
//						if(data.flag == "Y"){
//							$('.policy_list .select').remove();
//							$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
//						}
					}  
				});  
			},
			cancelFunc:function(){
				
			}
		});
//		new Sinosoft.InteractiveDialog({
//			layout : '<p class="alert_content content_mid">���ȷ���������.���������ͨ��"��ӱ���"���°󶨸ñ���</p>',
//			okStr:'ȷ��',
//			cancelStr:'ȡ��',
//			width:510,
//			okFunc:function(){
//				var ctx = $('#ctx').val();
//				var delPolicySerialNumber = $('.select .pNo').html();
//				var params = {'delPolicySerialNumber':delPolicySerialNumber};
//				$.ajax({  
//					url:ctx+'/infoJson/delPolicy.do',
//					data: params,
//					error:function(){
//						a_alert("�����쳣��");
//					},  
//					success:function(data){
//						var subSuccess = $('<div class="subscribe">'
//								+'<div class="success"></div><div class="main_content">'
//								+'<div class="main_txt">���ɹ�!</div>'
//								+'<div class="sub_txt">���ѳɹ�����ñ�����</div></div></div>');													
//						new Sinosoft.InteractiveDialog({
//							layout : subSuccess,
//							width:410,//�Զ��������-�������������														
//							cancelBtnShow:false,
//							okFunc:function(){
//								location.reload();
//							}
//						}).open();
////						if(data.flag == "Y"){
////							$('.policy_list .select').remove();
////							$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
////						}
//					}  
//				});  
//			},
//			cancelFunc:function(){
//				
//			}
//		}).open();
	});
}

//��������ҳ��
function toDetail(pNo){
	$('#policyNo').val(pNo);
	$('#fm').submit();
}

//���±���
function otherPolicy(policyList,otherList) {
	new Sinosoft.InteractiveDialog({
		layout : loadNewPolicy(otherList),
		okStr : 'ȷ��',
		cancelStr : 'ȡ��',
		width : 510,
		okFunc : function() {
			var bindOtherPolicy =[];
//			bindOtherPolicy.push({'name':'a','value':'b'});
			var params = {};
			for ( var i = 0,j = 0; i < sinatay.newPolicy.checkOpts.length; i++) {
				if (sinatay.newPolicy.checkOpts[i].check()) {
					params['bindOtherPolicy[' + j + ']'] = $('.new_policy .new_policy_item').eq(i).find('.number .value').text();
					j++;
				}
			}
			var ctx = $('#ctx').val();
			$.ajax({  
				url:ctx+'/infoJson/bindOtherPolicy.do',
				data: params,
				error:function(){
					a_alert("�����쳣��");
				},  
				success:function(data){
					//flushPolicy(data.listPolicyList);
					location.reload();
				}  
			}); 
		},
		cancelFunc : function() {
			//flushPolicy(policyList);
			$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
			location.reload();
		}
	}).open();

}
function flushPolicy(policyList){
	var policyListStr = '';
	for(var i = 0; i < policyList.length; i++){
		policyListStr += '<div class="policy_item bind_policy">'
			+ '<div class="policy_row"><div class="name">�������룺</div><div class="value pNo">' + policyList[i].policySerialNumber + '</div></div>'
			+ '<div class="policy_row"><div class="name">�������ƣ�</div><div class="value">' + policyList[i].mainRiskName + '</div></div>'
			+ '<div class="policy_row"><div class="name">���ս�</div><div class="value">' + policyList[i].amount + '</div></div>'
			+ '<div class="policy_row"><div class="name">��Ч���ڣ�</div><div class="value">' + policyList[i].inceptionDate + '</div></div>'
			+ '<div class="policy_row last"><div class="name">����״̬��</div><div class="value">' + policyList[i].policyStatus + '</div></div>'
			+ '<div class="policy_bottom operation"><a href="#" onclick="toDetail(\'' + policyList[i].policySerialNumber + '\')">����</a>&nbsp;|&nbsp;<a href="' + policyList[i].downloadString + '">���ر���</a>&nbsp;|&nbsp;<a href="#" onclick="toDetail(\'' + policyList[i].policySerialNumber + '\')">����</a></div>'
			+ '<div class="close"></div></div>';
	}
	$('.policy_item.bind_policy').remove();
	$('.policy_list').prepend(policyListStr);
	
	$('.policy_item.bind_policy').click(function(){
		$('.policy_item').removeClass('select');
		$(this).addClass('select');
		addClose();
	});
	$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
}