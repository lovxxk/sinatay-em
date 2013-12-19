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
	
	$('.member_menu .item').eq(1).addClass('select');
	$('.member_menu .item').eq(1).siblings().removeClass('select');
	
	Sinosoft.namespace('sinatay.newPolicy');
	
	$('.policy_item.buy_policy .action_icon').click(function(){
		location.href =$('#ctx').val() + "/productsDisplay/onlineShop.do?parentAttrID=&topNum=2&attrID=ROOT";
	});
	
	//保单选中事件
	$('.policy_item').click(function(){
		$('.policy_item').removeClass('select');
		$(this).addClass('select');
		addClose();
	});
	
	//页面初始化关闭按钮
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
				contentStr: '请输入正确的保单号',
				subContentStr:'请重新核对信息后继续。',
				okStr: '确定',
				cancelStr: '取消',
				callback:function(){
					$('.add_policy_input .policy_input').focus();
				}
			});
		}else{
			//绑定保单
			var params = {'policySerialNumber':policy_no};
			var ctx = $('#ctx').val();
			$.ajax({  
				url:ctx+'/infoJson/bindPolicy.do',
				data: params,
				error:function(){
					a_alert("网络异常！");
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
									+'<div class="main_txt">绑定成功!</div>'
									+'<div class="sub_txt">你已成功绑定该保单</div></div></div>');													
							new Sinosoft.InteractiveDialog({
								layout : subSuccess,
								width:410,//自定义面板宽度-根据设计来调整														
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
	
	//待添加保单
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
	
	//前台缓存，初始化页面及各函数
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
	
	//首页点击事件
	$('.page.first a').click(function() {
		clickPage($('.page.page_num a:first'));
	});
	//尾页点击事件
	$('.page.last a').click(function() {
		clickPage($('.page.page_num a:last'));
	});
	
	//页码点击事件
	$('.page.page_num a').click(function() {
		clickPage($(this));
	});
	
	//前一页点击事件
	$('.page.prev_page').click(function(){
		clickPage($('.page.page_num.now').prev().find('a'));
	});
	
	//后一页点击事件
	$('.page.next_page').click(function(){
		clickPage($('.page.page_num.now').next().find('a'));
	});
//	
//	var alertType = getQueryString('alertType');
//	
//	if(alertType == '1'){//会员中心-我的保单-有保单状态-添加新保单错误提示
//		Sinosoft.alert({
//			contentStr: '您输入的保单信息不匹配',
//			subContentStr:'请重新核对信息后，再填写保单号。',
//			okStr: '确定',
//			cancelStr: '取消'
//		});
//	}else if(alertType == '2'){//会员中心-我的保单-有保单状态-删除订单提示
//		Sinosoft.alert({
//			contentStr: '不在页面上显示此份保单',
//			subContentStr:'如需重新显示，请您手动添加保单。',
//			okStr: '确定',
//			cancelStr: '取消'
//		});
//	}else if(alertType == '3'){//电子函件取消订阅
//		console.log(1);
//		Sinosoft.alert({
//			contentStr: '电子函件取消订阅',
//			subContentStr:'退订后您将不能享受信泰保险所有的电子函件发送和查询服务！ 我们将以<b>纸质函件形式</b>为您继续提供服务。',
//			okStr: '确定',
//			cancelStr: '取消'
//		});
//	}else if(alertType == '4'){//会员中心-电子函件-未订阅-提示完善信息
//		Sinosoft.alert({
//			contentStr: '尊敬的客户，您好！',
//			subContentStr:'您的基本信息不完整，请点击左边菜单栏中“<a href="account_personal.html"><font color="#FF0000">账户信息</font></a>”进行补全。',
//			okStr: '确定',
//			cancelStr: '取消'
//		});
//	}else if(alertType == '5'){//会员中心-电子函件-未订阅2-订阅成功弹出框
//		var subscribeSuccess = new Sinosoft.InteractiveDialog({
//			layout : loadSubscribeSuccess()
//		});
//		subscribeSuccess.open();
//	}else if(alertType == '6'){//会员中心-电子函件-已订阅-邮箱修改弹出框
//		Sinosoft.alert({
//			contentStr: '此邮箱的变更将更新您的保单信息',
//			okStr: '确定',
//			cancelStr: '取消'
//		});
//	}
	
	//判断是否有绑定保单，购买保单开关
	if($('#hasPolicy').val() == 'Y'){
		$('.buy_policy').hide();
	}
	$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
});

//pageNum从1开始
function showPage(pageNum){
	$('.policy_item.bind_policy').hide();
	$('.policy_item.bind_policy:lt(' + (pageNum * 5) + ')').show();
	$('.policy_item.bind_policy:lt(' + ((pageNum - 1) * 5) + ')').hide();
	
	/*//重适应长度
	$('.member_main .left_menu').css('height',$('.member_main .right_content').height());*/
}

//点击页码事件
function clickPage(now){
	var nowNum = parseInt(now.html());
	//控制前后跳转的显示
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
	
	//可显示的页码
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
			+ '<div class="main_content"><p class="main_txt">订阅成功</p><p class="sub_txt">感谢您订阅信泰保险的电子函件服务。</p></div>'
			+ '</div>');
	return success;
}

//其他可绑定保单显示页面初始化
function loadNewPolicy(otherList){
	var newPolicyStr = '<div class="new_policy">'
			+ '<p class="alert_content content_mid">您名下还有 <font color="#ff3333">' + otherList.length +'</font> 张保单待添加</p>'
			+ '<div class="new_policy_list">';

	for(var i = 0; i < otherList.length; i++){
		newPolicyStr += '<div class="new_policy_item"><div class="new_check"></div><div class="number"><span class="name">保单号：</span><span class="value">' + otherList[i].policySerialNumber + '</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">' + otherList[i].mainRiskName + '</span></div></div>';
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

//绑定删除操作
function addClose(){
	$('.policy_list .select .close').click(function(){
		Sinosoft.alert({
			contentStr:'点击确定将解除绑定.解除后您可通过"添加保单"重新绑定该保单',
			okStr:'确认',
			cancelStr:'取消',
			width:510,
			okFunc:function(){
				var ctx = $('#ctx').val();
				var delPolicySerialNumber = $('.select .pNo').html();
				var params = {'delPolicySerialNumber':delPolicySerialNumber};
				$.ajax({  
					url:ctx+'/infoJson/delPolicy.do',
					data: params,
					error:function(){
						a_alert("网络异常！");
					},  
					success:function(data){
						var subSuccess = $('<div class="subscribe">'
								+'<div class="success"></div><div class="main_content">'
								+'<div class="main_txt">解绑成功!</div>'
								+'<div class="sub_txt">你已成功解除该保单绑定</div></div></div>');													
						new Sinosoft.InteractiveDialog({
							layout : subSuccess,
							width:410,//自定义面板宽度-根据设计来调整														
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
//			layout : '<p class="alert_content content_mid">点击确定将解除绑定.解除后您可通过"添加保单"重新绑定该保单</p>',
//			okStr:'确认',
//			cancelStr:'取消',
//			width:510,
//			okFunc:function(){
//				var ctx = $('#ctx').val();
//				var delPolicySerialNumber = $('.select .pNo').html();
//				var params = {'delPolicySerialNumber':delPolicySerialNumber};
//				$.ajax({  
//					url:ctx+'/infoJson/delPolicy.do',
//					data: params,
//					error:function(){
//						a_alert("网络异常！");
//					},  
//					success:function(data){
//						var subSuccess = $('<div class="subscribe">'
//								+'<div class="success"></div><div class="main_content">'
//								+'<div class="main_txt">解绑成功!</div>'
//								+'<div class="sub_txt">你已成功解除该保单绑定</div></div></div>');													
//						new Sinosoft.InteractiveDialog({
//							layout : subSuccess,
//							width:410,//自定义面板宽度-根据设计来调整														
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

//进入详情页面
function toDetail(pNo){
	$('#policyNo').val(pNo);
	$('#fm').submit();
}

//线下保单
function otherPolicy(policyList,otherList) {
	new Sinosoft.InteractiveDialog({
		layout : loadNewPolicy(otherList),
		okStr : '确认',
		cancelStr : '取消',
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
					a_alert("网络异常！");
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
			+ '<div class="policy_row"><div class="name">保单号码：</div><div class="value pNo">' + policyList[i].policySerialNumber + '</div></div>'
			+ '<div class="policy_row"><div class="name">主险名称：</div><div class="value">' + policyList[i].mainRiskName + '</div></div>'
			+ '<div class="policy_row"><div class="name">保险金额：</div><div class="value">' + policyList[i].amount + '</div></div>'
			+ '<div class="policy_row"><div class="name">生效日期：</div><div class="value">' + policyList[i].inceptionDate + '</div></div>'
			+ '<div class="policy_row last"><div class="name">保单状态：</div><div class="value">' + policyList[i].policyStatus + '</div></div>'
			+ '<div class="policy_bottom operation"><a href="#" onclick="toDetail(\'' + policyList[i].policySerialNumber + '\')">详情</a>&nbsp;|&nbsp;<a href="' + policyList[i].downloadString + '">下载保单</a>&nbsp;|&nbsp;<a href="#" onclick="toDetail(\'' + policyList[i].policySerialNumber + '\')">更多</a></div>'
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