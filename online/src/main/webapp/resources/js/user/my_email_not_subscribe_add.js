$(document).ready(
		function() {
			
			// 点击特效
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
			
			//返回到取消订阅界面
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
					$(this).prev().text('补充');
				} else {
					$(this).prev().text('修改');
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
			//转发到取消订阅页面
			$('#cancle').click(
					function() {
						window.location.href = contextRootPath + "/email/myEmailSubscribe.do";

					});
			$('.content .checkbox').each(function() {
				$(this).jCheckBox();
			});

			// 订阅
			$('.click_btn').click(
					function() {
						//邮箱格式是否正确
						
						var isEmail=0;
						
						var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
						var flag=0;
						// 订阅标签
						var subFlag = new Array();
						// 保存订阅的数据
						var subJsonStr = "[";
						// 获得所有被选择的节点
						$(".checkbox_check").each(function(index) {
							subFlag[index] = $(this).parent().attr('id');
						});
						// 为选中订阅条件
						if (subFlag.length == 0) {
							Sinosoft.alert({
								contentStr : '对不起，操作失败！',
								subContentStr : '<h3>未选中订阅电子函件信息</h3>',
								okStr : '确定',
								cancelBtnShow:false//是否显示关闭按钮
							});
						} else {
							//遍历选择的保单
							for ( var i = 0; i < subFlag.length; i++) {
								//获取选取索引的值
								var choiceIndex=subFlag[i];
								if ($("#email" + choiceIndex).text() == '') {
									flag=1;
								}
								//检测邮箱修改是否正确
								if (!reg.test($("#email" + choiceIndex).text())) {
									isEmail=1;
								}
								// 保存订阅的保单号 jsonStr
								if (subFlag.length == 1) {
									subJsonStr += "{policyNo:"
											+ $("#policyNo" + choiceIndex).text() + ",email:"+$("#email" + choiceIndex).text()+"}";
								} else {
									subJsonStr += "{policyNo:"
											+ $("#policyNo" + choiceIndex).text() + ",email:"+$("#email" + choiceIndex).text()+"},";
								}

							}
							subJsonStr += "]";
							//邮箱不能为空
							if(flag==1){
								Sinosoft.alert({
									contentStr : '对不起，操作失败！',
									subContentStr : '<h3>邮箱不能为空</h3>',
									okStr : '确定',
									cancelBtnShow:false//是否显示关闭按钮
								});
								 return;
							}
							if(isEmail==1){
								Sinosoft.alert({
									contentStr : '邮箱格式错误，操作失败！',
									subContentStr : '<h3>请检查您的输入电子邮箱格式是否正确！</h3>',
									okStr : '确定',
									cancelBtnShow:false//是否显示关闭按钮
								});
							 return;
							}
							if(flag==0){
								var test;
								var loading = new Sinosoft.LoadingDialog({
									contentStr: '请耐心等待...',
									titleStr:'订阅',
									okStr:'',
									noCancel: true,
									closeFunc:function(){
										
									},
									waitFunc:function(){
										return test;
									}
								});
								
								// 发生ajax请求
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
												contentStr : '对不起，操作失败！',
												subContentStr : data.desc,
												okStr : '确定'
											});
										}else{
											//弹出一个订阅成功的对话框
											new Sinosoft.InteractiveDialog({
												layout : loadSubSccess(),
												width:410,//自定义面板宽度-根据设计来调整
												cancelBtnShow:false,//是否显示关闭按钮
												okStr : '确认',
												cancelStr : '取消',
												okFunc:function(){
													if(data.notSubCount==0){
														window.location.href = contextRootPath + "/email/myEmailSubscribe.do";
													}else
													//点击确定按钮执行方法
													window.location.href = contextRootPath + "/email/notSubscribe.do";
												}
											}).open();
										}
										
									},
									error : function(backData) {
										
										a_alert("网络异常！");
									}
								});
							}
						
						
						}

					});
		});

function loadSubSccess(){
	var subSuccess = $('<div class="subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="main_txt">订阅成功</div>'
			+'<div class="sub_txt">感谢你订阅信泰保险的电子函件服务</div></div></div>');
	return subSuccess;
}
function loadSubscribePolicy() {


	var num = 5;
	var subscribePolicy = $('<div class="subscribe_policy">'
			+ '<p class="alert_content content_mid">您还有&nbsp;<font color="#ff4141">'
			+ num
			+ '</font>&nbsp;张保单未添加！</p>'
			+ '<div class="subscribe_policy_list">'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20141123114345</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">信泰健康卫士保障计划</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">富贵人生两全保险（分红型）A款</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">富贵人生两全保险（分红型）A款</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">富贵人生两全保险（分红型）A款</span></div></div>'
			+ '<div class="subscribe_policy_item"><div class="subscribe_check"></div><div class="number"><span class="name">保单号：</span><span class="value">20150820548793</span></div><div class="insurance_name"><span class="name">主险名称：</span><span class="value">富贵人生两全保险（分红型）A款</span></div></div>'
			+ '</div></div>');

	sinatay.email.subOpts = [];

	subscribePolicy.find('.subscribe_check').each(function() {
		sinatay.email.subOpts.push($(this).jCheckBox());
	});

	return subscribePolicy;
}