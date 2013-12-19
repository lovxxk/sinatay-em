$(document).ready(function(){
	
	//点击特效
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
			         {name:'中国农业银行',value:'103'},
			         {name:'中国银行',value:'104'},
			         {name:'中国建设银行',value:'105'},
			         {name:'光大银行',value:'303'},
			         {name:'广东发展银行',value:'306'},
			         {name:'民生银行',value:'305'},
			         {name:'工商银行',value:'102'},
			         {name:'中国邮政储蓄银行',value:'403'}
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
		//默认网银
		$('.pay_tab_bar .tab_item').eq(0).click();
	} else {
		//默认支付宝
		$('.pay_tab_bar .tab_item').eq(1).click();
	}
	

	$('#click_btn').click(function(){
		var orderStatus = $("#orderStatus").val();
		var productCode = $("#productCode").val();
		if (orderStatus != 82) {
			Sinosoft.alert({
				contentStr: "订单状态非法,不可支付",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				closeIconShow:false,
				okFunc:function(){
					
				}
			});
			return;
		}
		//获取用户选择的支付方式
		var bankFlag = false;
		var radio = $('input[name="payment"]').filter(':checked');
		if(radio.length) {//已选择支付方式
			bankFlag = true;
		} else {
			bankFlag = false;
		}
		
		if (bankFlag == false) {
			Sinosoft.alert({
				contentStr: "请选择支付方式",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
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
		/*意外险属于短期限，不需要录入开户银行及帐号*/
		if (productCode != "00136") {
			//续期交费、退保帐户
			alipay_bank_deposit = $("#renewal_bank_deposit").data('jSelect').getValue();
			//续期交费、退保帐号
			alipayPaymentAccount = $("#paymentAccount").val();
			bankProvince = $('#bankProvince').val();
			bankCity = $('#bankCity').val();
			if (alipay_bank_deposit == "") {
				Sinosoft.alert({
					contentStr: "请选择退保开户银行",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					closeIconShow:false,
					okFunc:function(){
						
					}
				});
				return;
			}
			if (alipayPaymentAccount == "") {
				Sinosoft.alert({
					contentStr: "请输入退保银行账户",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					closeIconShow:false,
					okFunc:function(){
						
					}
				});
				return;
			}
			if (isNaN(alipayPaymentAccount) || alipayPaymentAccount.indexOf(".") > 0 || alipayPaymentAccount.length < 10 || alipayPaymentAccount.length > 20) {
				Sinosoft.alert({
					contentStr: "银行帐号不合法",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					closeIconShow:false,
					okFunc:function(){
						
					}
				});
				return;
			}
			if (bankProvince == "" || bankCity == "") {
				Sinosoft.alert({
					contentStr: "请选择省份城市",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
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
			okStr:'重新选择支付方式',
			cancelStr:'已完成付款',
			closeIconShow:false,
			okFunc:function(){
				//插码
				_ga.push(['_trackEvent', '支付订单', '重新选择支付方式']).send();
				$("#confirmProposalSIDForm").submit();
			},
			cancelFunc:function(){
				//插码
				_ga.push(['_trackEvent', '支付订单', '已完成付款']).send();
//				window.location.href = contextRootPath+"/web/user/login/index.jsp";
				window.location.href = contextRootPath+"/payment/paymentSuccess.do?id=" + $("#orderId").val();
			}
		}).open();
	});
});

function loadWaitConfirm(){
	var waitConfirm = $('<div class="wait_confirm">'
			+ '<p class="wait">请您在新打开的页面上 完成付款。</p>'
			+ '</div>');
	
	return waitConfirm;
}