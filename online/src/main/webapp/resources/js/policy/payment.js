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
	
	$('.nav_menu .nav_item').eq(1).addClass('select');
	$('.nav_menu .nav_item').eq(1).siblings().removeClass('select');
	$('.nav_menu .nav_item').eq(1).prev().addClass('m_select_p');
	
	$('.pay_box .type').click(function(){
		$(this).prev().click();
	});
	
	var bank_opts = [
			         {name:'�й�ũҵ����',value:'103'},
			         {name:'�й�����',value:'104'},
			         {name:'�й���������',value:'105'},
			         {name:'�������',value:'303'},
			         {name:'�㶫��չ����',value:'306'},
			         {name:'��������',value:'305'},
			         {name:'��������',value:'102'},
			         {name:'�й�������������',value:'403'}
			];
	
	$('#withholding_check').jCheckBox({
		label:'withholding_check',
		onCheckChanged:function(label,check){
			
		}
	});
	
	$('#renewal_check').jCheckBox({
		label:'withholding_check',
		onCheckChanged:function(label,check){
			
		}
	});
	
	$('.renewal_area .alipay_bank_deposit .input_text').jSelect({
		options:bank_opts
	});
	
	$('.pay_tab_bar .tab_item').click(function(){
		$(this).addClass('select');
		$(this).siblings().removeClass('select');
		
		var index = $(this).index('.tab_item');
		
		$('.payment_type').hide();
		$('.payment_type').eq(index).show();
		
		if(index == 2){
			$('#alipay_bank_deposit').jSelect({
				options:bank_opts,
				onSelect:function(){
				}
			});
			$('.renewal_area').hide();
		}else{
			$('#renewal_bank_deposit').jSelect({
				options:bank_opts,
				onSelect:function(){
				}
			});
			$('.renewal_area').show();
		}
	});
	
	if (financialRisk) {
		//Ĭ������
		$('.pay_tab_bar .tab_item').eq(0).click();
	} else {
		//Ĭ��֧����
		$('.pay_tab_bar .tab_item').eq(1).click();
	}
	

	$('#click_btn').click(function(){
		var orderStatus = $("#orderStatus").val();
		var productCode = $("#productCode").val();
		if (orderStatus != 82) {
			Sinosoft.alert({
				contentStr: "����״̬�Ƿ�,����֧��",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				closeIconShow:false,
				okFunc:function(){
					
				}
			});
			return;
		}
		//��ȡ�û�ѡ���֧����ʽ
		var bankFlag = false;
		var radio = $('input[name="payment"]').filter(':checked');
		if(radio.length) {//��ѡ��֧����ʽ
			bankFlag = true;
		} else {
			bankFlag = false;
		}
		
		if (bankFlag == false) {
			Sinosoft.alert({
				contentStr: "��ѡ��֧����ʽ",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				closeIconShow:false,
				okFunc:function(){
					
				}
			});
			return;
		}
		var alipay_bank_deposit = "";
		var alipayPaymentAccount = "";
		var bankProvince = "";
		var bankCity = "";
		var uri = "";
		var param = "";
		var paymentType = radio.val();
		/*���������ڶ����ޣ�����Ҫ¼�뿪�����м��ʺ�*/
		if (productCode != "00136") {
			//���ڽ��ѡ��˱��ʻ�
			alipay_bank_deposit = $("#renewal_bank_deposit").data('jSelect').getValue();
			//���ڽ��ѡ��˱��ʺ�
			alipayPaymentAccount = $("#paymentAccount").val();
			bankProvince = $('#bankProvince').val();
			bankCity = $('#bankCity').val();
			if (alipay_bank_deposit == "") {
				Sinosoft.alert({
					contentStr: "��ѡ���˱���������",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					closeIconShow:false,
					okFunc:function(){
						
					}
				});
				return;
			}
			if (alipayPaymentAccount == "") {
				Sinosoft.alert({
					contentStr: "�������˱������˻�",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					closeIconShow:false,
					okFunc:function(){
						
					}
				});
				return;
			}
			if (isNaN(alipayPaymentAccount) || alipayPaymentAccount.indexOf(".") > 0 || alipayPaymentAccount.length < 10 || alipayPaymentAccount.length > 20) {
				Sinosoft.alert({
					contentStr: "�����ʺŲ��Ϸ�",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					closeIconShow:false,
					okFunc:function(){
						
					}
				});
				return;
			}
			if (bankProvince == "" || bankCity == "") {
				Sinosoft.alert({
					contentStr: "��ѡ��ʡ�ݳ���",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					closeIconShow:false,
					okFunc:function(){
						
					}
				});
				return;
			}
		}
		if (paymentType == "alipay") {
			param = "?proposalSID=" + $('#proposalSID').val() 
					+ "&orderId=" + $("#orderId").val() 
					+ "&paymentType=" + paymentType
					+ "&bankType=" + alipay_bank_deposit
					+ "&bankProvince=" + bankProvince
					+ "&bankCity=" + bankCity
					+ "&paymentAccount=" + alipayPaymentAccount;
			
			uri = "/payment/toAliPay.do";
		} else {
			param = "?proposalSID=" + $('#proposalSID').val() 
					+ "&orderId=" + $("#orderId").val() 
					+ "&paymentType=" + paymentType
					+ "&bankType=" + alipay_bank_deposit
					+ "&bankProvince=" + bankProvince
					+ "&bankCity=" + bankCity
					+ "&paymentAccount=" + alipayPaymentAccount;
			uri = "/payment/paymentToBank.do";
		}
		

		var url = contextRootPath + uri + param;
		console.log(url);
		var props=null;
		props=window.open(url,'poppage', 'toolbars=0,scrollbars=0,location=0,statusbars=0,menubars=0,resizable=1,left=75,top=50');
		if (props!=null){
			props.focus();
		}
		new Sinosoft.InteractiveDialog({
			layout : loadWaitConfirm(),
			width:420,
			okStr:'����ѡ��֧����ʽ',
			cancelStr:'����ɸ���',
			closeIconShow:false,
			okFunc:function(){
				//����
				_ga.push(['_trackEvent', '֧������', '����ѡ��֧����ʽ']).send();
				$("#confirmProposalSIDForm").submit();
			},
			cancelFunc:function(){
				//����
				_ga.push(['_trackEvent', '֧������', '����ɸ���']).send();
//				window.location.href = contextRootPath+"/web/user/login/index.jsp";
				window.location.href = contextRootPath+"/payment/paymentSuccess.do?id=" + $("#orderId").val();
			}
		}).open();
	});
});

function loadWaitConfirm(){
	var waitConfirm = $('<div class="wait_confirm">'
			+ '<p class="wait">�������´򿪵�ҳ���� ��ɸ��</p>'
			+ '</div>');
	
	return waitConfirm;
}