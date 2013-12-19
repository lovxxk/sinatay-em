//初始化手机验证倒计时参数
var intervalProcess;
var timenum = 120;
//绑定手机
function doNoPhone(){
	var parameter='手机号码';
	var receive_input = $('<div class="alert_phone_input">'
			+ '<label class="alert_phone_label">请输入'
			+ parameter
			+ '：</label>'
			+ '<input class="alert_phone_text" type="text" name="emailOrPhone" id="emailOrPhone" maxlength=30/>'
			+ '</div><br>');	
	
	var emailOrPhone = $("#receive_text").val();
	
	new Sinosoft.InteractiveDialog({
		layout : receive_input,
		width:490,
		cancelStr:'确定',
		okBtnShow:false,
		cancelFunc:function(){
			var customerName = $("#emailOrPhone").val();
			if (customerName == ""){
				Sinosoft.alert({
					contentStr : '手机输入错误，操作失败！',
					subContentStr : '<h3>请输入不为空的手机号码！</h3>',
					okStr : '确定',
					cancelBtnShow:false// 是否显示关闭按钮
				});
				
			}
			//判断用户录入的是否是手机号
			var phoneFalg = false;
			if (!isNaN(customerName) && customerName.length == 11 && customerName.indexOf(".") < 0) {
				//11位手机号是否合法
				var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(customerName);
				if (!regmobile) {
					alert("输入手机号不合法");
					return false;
				} else {
					phoneFalg = true;
				}
			}
			
			if (!phoneFalg) {
				Sinosoft.alert({
					contentStr : '手机格式错误，操作失败！',
					subContentStr : '<h3>请输入合法的手机号码！</h3>',
					okStr : '确定',
					cancelBtnShow:false// 是否显示关闭按钮
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
						a_alert('网络错误！');
					}
				}
			});
		}
	}).open();
}

function checkDate(){
	//检测日期格式是否超前
	var startDate=$('#date_start').val();
	var endDate=$('#date_end').val();
	var startDateArr=startDate.split('-');
	var endDateArr=endDate.split('-');
	var v1=new Date(startDateArr[0],startDateArr[1],startDateArr[2]);
	var v2=new Date(endDateArr[0],endDateArr[1],endDateArr[2]);
	if(v2<v1){
		Sinosoft.alert({
			contentStr : '日期格式错误，操作失败！',
			subContentStr : '<h3>开始日期不能大于结束日期！</h3>',
			okStr : '确定',
			cancelBtnShow:false// 是否显示关闭按钮
		});
		return false;
	}
	
	return true;
}
function loadUpdateEmail(emailChangeJsonStr){
	var mobileStr = $('#mobile').text();
	mobileStr=mobileStr.substring(0,3)+"****"+mobileStr.substring(7);
	//做一个手机验证对话框
	var a ='<div>'
		  +'  <div class="send_vali">'
		  +'     <p>请向您预留的手机号<span id="b_appntphone">'+mobileStr+'</span>发送验证码</p>'
		  +'     <p>请将验证码输入下框！</p>'
		  +'  </div>'
		  +'  <div class="vali_input">'
		  +'     <label for="phone_amount_validate">验证码：</label>'
		  +'     <input id="checkNo" type="text"/>'
		  //隐藏判断用户是否点击发送验证码按钮：防止验证码二次利用
		  //隐藏邮箱改变的jsonStr
		  +'     <input id="emailChangeJsonStr" type="text" style="display:none;" value="'+emailChangeJsonStr+'"/>'
		  +'     <input type="button" class="email_phone_vali click_btn getPhonePwd" onclick="getPhoneDynamicNumber();" value="发送验证码"/>'
		  +'  </div>'
		  //错误信息
		  +'  <div class="amount_num" id="phone_amount_valiinfo"></div>'
		  //增加下一步按钮
		  +'  <input class="alert_confirm" style="margin-right:160px;"  onclick="sendPhone();" type="button" value="下一步"/>'
		  +'  <br/>'
		  +'  <br/>'
		  +'</div>';
	return a;
}

//增加下一个点击事件
function sendPhone(){
	//检测输入的值是否相等
	//输入值
	var inputNo = $("#checkNo").val();
	//从后台数据库里面取并通过后台判断  Y成功 N失败
	
	var isSuccess;

	var emailChangeJsonStr=$("#emailChangeJsonStr").val();
	if (inputNo==null||inputNo==''){
		$("#phone_amount_valiinfo").show();
		$("#phone_amount_valiinfo").text('请输入正确的手机验证码！');
		//边框颜色变红
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
					a_alert('系统错误！');
				}
				
			},
			error: function(backData) {
				a_alert('网络错误！');
			}
		});
		//系统错误
		if(isSuccess =='ERROR'){
			a_alert('系统错误！');
		}
		//检测手机验证码输入错误
		if(isSuccess == 'N'){
			$("#phone_amount_valiinfo").show();
			$("#phone_amount_valiinfo").text('手机验证码输入错误！');
			//边框颜色变红
			$("#checkNo").css('border-color','red');
			return;
		}
		//ajax执行修改邮箱
		$("#phone_amount_valiinfo").hide();
		$("#checkNo").css('border-color','');
		var test;
		var loading = new Sinosoft.LoadingDialog({
			contentStr: '请耐心等待...',
			titleStr:'邮箱修改',
			okStr:'',
			noCancel: true,
			closeFunc:function(){
				
			},
			waitFunc:function(){
				return test;
			}
		});
		//修改邮箱
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
						contentStr : '对不起，操作失败！',
						subContentStr : backData.desc,
						okStr : '确定'
					});
				}else{
					//弹出一个邮箱修改成功的对话框
					new Sinosoft.InteractiveDialog({
						layout : loadChangeEmailSccess(),
						width:410,//自定义面板宽度-根据设计来调整
						cancelBtnShow:false,//是否显示关闭按钮
						okStr : '确认',
						cancelStr : '取消',
						okFunc:function(){
							//点击确定按钮执行方法
							location.href=contextRootPath + "/email/myEmailSubscribe.do";
						}
					}).open();
				}
				
			},
			error: function(backData) {
				a_alert('网络错误！');
			}
		});

	}
}
//手机验证倒计时
function counttime() {
	if (timenum > 0) {
		$('.getPhonePwd').val('('+timenum+')秒后重新发送');
		timenum = timenum - 1;
	} else {
		$('.getPhonePwd').val('点击再次发送');
		$('.getPhonePwd').valiCodeEnable();
		clearInterval(intervalProcess);
	}
}
//获得手机动态验证码
function getPhoneDynamicNumber(){
	$('.getPhonePwd').valiCodeDisable();
	//ajax获得数据
	$.ajax({
		type : "POST",
		async : false,
		url:contextRootPath+"/email/sendPhoneDynamicNumber.do",
		dataType : 'json',
		success : function(backData) {
			if(backData.flag==1){
				$("#phone_amount_valiinfo").show();
				$("#phone_amount_valiinfo").text(backData.desc);
				//边框颜色变红
				$("#checkNo").css('border-color','red');
			}else{
				timenum = 120;
				intervalProcess = setInterval('counttime()',1000);
			}
		},
		error: function(backData) {
			Sinosoft.alert({
				contentStr: "发送失败",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
				}
			});
		}
	});
}
$(document).ready(function(){
	// 点击特效
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
		//邮箱修改Json字符串
		var emailChangeJsonStr="";
		var parent = $(this).parents('.content_col');
		var text = parent.find('.text_show');
		//之前的邮箱号
		var oldEmail=text.text();
		//保单号
		var policyNo = parent.find('#policyNo').val();
		//用户手机号码
		var mobile = $('#mobile').text();
		//邮箱
		var email = text.siblings().val();
		//订阅类型
		var subType= parent.find('#subType').val();
		// 展示文本框
		text.show();
		text.each(function(){
			//更新邮箱的值
			$(this).text($(this).siblings().val()); 
			var emailDiv=$(this);
			//验证用户是否绑定手机号码
			if(mobile==null||mobile==''){
				doNoPhone();
				return false;
			}
			// 是否邮箱格式正确
			if(!reg.test($(this).siblings().val())){
				Sinosoft.alert({
					contentStr : '邮箱格式错误，操作失败！',
					subContentStr : '<h3>请检查您的输入电子邮箱格式是否正确！</h3>',
					okStr : '确定',
					cancelBtnShow:false// 是否显示关闭按钮
				});
				$(this).text(oldEmail);
			 return;
			}
			else if($(this).siblings().val()==''){
				Sinosoft.alert({
					contentStr : '对不起，操作失败！',
					subContentStr : '<h3>邮箱不能为空!</h3>',
					okStr : '确定',
					cancelBtnShow:false// 是否显示关闭按钮
				});
				$(this).text(oldEmail);
				 return;
			}else if($(this).siblings().val() == oldEmail){
				Sinosoft.alert({
					contentStr : '对不起，操作失败！',
					subContentStr : '<h3>邮箱信息与原邮箱信息相同!</h3>',
					okStr : '确定',
					cancelBtnShow:false// 是否显示关闭按钮
				});
				 return;
				
			}else{
				//拼接json字符串
				emailChangeJsonStr='{policyNo : '+policyNo+',email :'+email+',subType :'+subType+'}';
				
				Sinosoft.alert({
					contentStr : '此邮箱的变更，将更新你的保单信息！',
					okStr : '确定',
					cancelStr:'取消',//取消按钮显示文字
					okBtnShow:true,//是否显示确定按钮
					cancelBtnShow:true,
					okFunc:function(){
						//执行手机验证
						Sinosoft.alert({
							contentStr : '邮箱变更！',
							subContentStr : loadUpdateEmail(emailChangeJsonStr),
							width:580,
							okStr : '确 定',
							okBtnShow:false,//是否显示确定按钮
							cancelBtnShow:false
							
						});
					},
					cancelFunc:function(){
						//点击取消按钮执行方法
						//点击关闭图标执行方法
						emailDiv.text(oldEmail);
					},
					closeFunc:function(){
						emailDiv.text(oldEmail);
						$('.getPhonePwd').val('发送验证码');
						$('.getPhonePwd').valiCodeEnable();
						clearInterval(intervalProcess);
					}
					
				});

			}
			// 检测修改的邮箱是否正确
		});
		
		// 隐藏当前其他元素
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
	
	// 转发未订阅界面
	$('.subscribe').click(function(){
		window.location.href = contextRootPath + "/email/notSubscribe.do";

	});
	// 展示复选框
	$('.content .checkbox').each(function() {
		$(this).jCheckBox();
	});
	
	// 日期初始化
	$('#date_start,#date_end').click(function(){
		WdatePicker({
			startDate:'%y-%M-%d',
			dateFmt:'yyyy-MM-dd'
		});
	});
	
	// 取消订阅
	$('.operation').click(function() {
		
		var input = $(this).siblings('.content_col');
		var text = input.find('.text_show');
		//取消订阅json对象
		var cancleSubJsonStr='';
		//保单号
		var policyNo = input.find('#policyNo').val();
		//邮箱
		var email = text.text();
		//订阅类型
		var subType= input.find('#subType').val();
		//既订阅纸质函件也订阅电子函件 提示用户是否取消纸质函件  是：取消纸质函件 subtype：04  否 ：02 保存纸质函件
			Sinosoft.alert({
				contentStr : '电子函件取消订阅！',
				subContentStr : loadcancleSub(),
				width:510,
				okStr : '确定',
				cancelStr:'取消',//取消按钮显示文字
				okFunc:function(){
					//点击确定按钮执行方法
					cancleSubJsonStr='{policyNo : '+policyNo+',email :'+email+',subType :'+02+'}';
					var test;
					var loading = new Sinosoft.LoadingDialog({
						contentStr: '请耐心等待...',
						titleStr:'取消订阅',
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
									contentStr : '对不起，操作失败！',
									subContentStr : backData.desc,
									okStr : '确定'
								});
							}else{
								//弹出一个取消订阅成功的对话框
								new Sinosoft.InteractiveDialog({
									layout : loadCalSubSccess(),
									width:410,//自定义面板宽度-根据设计来调整
									cancelBtnShow:false,//是否显示关闭按钮
									okStr : '确认',
									cancelStr : '取消',
									okFunc:function(){
										//点击确定按钮执行方法
										location.href=contextRootPath + "/email/myEmailSubscribe.do";
									}
								}).open();
							}
							
						},
						error: function(backData) {
							a_alert('网络错误！');
						}
					});
				}
			});
		
		
				
	});
});




//检测手机验证码是否真确
function checkPhoneCheckNo(phoneDynamicNumber){
	if(phoneDynamicNumber != $("#phone_amount_validate").val()){
		//弹出手机验证码错误对话框
		
		return false;
	}
}

function loadCalSubSccess(){
	var subSuccess = $('<div class="subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="main_txt">退订成功</div>'
			+'<div class="sub_txt">你已成功取消订阅信泰保险的电子函件服务</div></div></div>');
	return subSuccess;
}
//邮箱修改成功
function loadChangeEmailSccess(){
	var changeSuccess = $('<div class="subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="main_txt">修改成功</div>'
			+'<div class="sub_txt">你已修改邮箱地址成功！</div></div></div>');
	return changeSuccess;
}
//取消订阅电子函件
function loadcancleSub(){
	var cancaleSub='退订后您将不能享受信泰保险所有的电子函件发送与查询服务！我们将以<b>纸质函件形式</b>继续为您提供服务！';
	return cancaleSub;
}
////取消订阅电子函件
//function loadcancleSub2(){
//	var cancaleSub='退订后你将不能享受信泰保险公司提供为该保单提供电子函件发送与查询服务！<br/>'
//		            +'目前，你已订阅<font color="red">电子函件与纸质函件</font>，'
//		            +'<b>是否保留纸质函件形式！</b>'
//	return cancaleSub;
//}
function loadSubscribePolicy(){
	var num = 4;
	var subscribePolicy = $('<div class="subscribe_policy">'
			+ '<p class="alert_content content_mid">您还有&nbsp;<font color="#ff4141">'+num+'</font>&nbsp;张保单未添加！</p>'
			+ '<div class="subscribe_policy_list">'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20141123114345</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">信泰健康卫士保障计划</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">富贵人生两全保险（分红型）A款</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">富贵人生两全保险（分红型）A款</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">富贵人生两全保险（分红型）A款</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">富贵人生两全保险（分红型）A款</span></div></div>'
			+ '</div></div>');
	
	sinatay.email.subOpts = [];
	
	subscribePolicy.find('.subscribe_check').each(function(){
		sinatay.email.subOpts.push($(this).jCheckBox());
	});
	
	return subscribePolicy;
}


