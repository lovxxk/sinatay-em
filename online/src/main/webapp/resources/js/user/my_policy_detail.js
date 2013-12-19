//初始化手机验证倒计时参数
var intervalProcess;
var timenum = 120;
//手机验证倒计时
function counttime() {
	if (timenum > 0) {
		$('#getPhonePwd').val('('+timenum+')秒重新发送');
		timenum = timenum - 1;
	} else {
		$('#getPhonePwd').val('点击再次发送');
		$('#getPhonePwd').valiCodeEnable();
		clearInterval(intervalProcess);
	}
}
//pageNum从1开始
function showPage(pageNum){
	$('.edors_item').hide();
	$('.edors_item:lt(' + (pageNum * 6) + ')').show();
	$('.edors_item:lt(' + ((pageNum - 1) * 6) + ')').hide();
	
	//重适应长度
	$('.security_info').css('height',$('.security_info').height());
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
//校验是否能够下载
function check(acceptNo){
	$.ajax({
		type : "post",
		url : contextRootPath+"/myPolicyDetail/check.do",
		data : "acceptNo=" + acceptNo,
		dataType : 'json',
		success : function(data, textStatus) {
			if(data.flag=='0'){
				Sinosoft.alert({
					contentStr : '下载失败！',
					subContentStr : data.desc,
					okStr : '确定'
				});
			}else{
				//获得下载文件流
				$.post('/myPolicyDetail/downloadEdor.do?url='+data.url);
			
			}
			
		},
		error : function(backData) {
			a_alert("网络异常！");
		}
	});
	
	
}

//现金价值、产品条款、险种利益
function productCashBnf(type){
	var showSelect=$("#showSelect").text();
	if(showSelect=='0'){
		Sinosoft.alert({
			contentStr : '无险种信息！',
			subContentStr : '尊敬的用户您好，当前你还没有有效的险种信息！',
			okStr : '确定'
		});
		return false;
	}
	var risksStr=$(".risksStr").html();
	var json_risks = eval("(" + risksStr + ")");
	new Sinosoft.InteractiveDialog(
			{
				layout : loadInsuranceSelect2(json_risks),
				width : 560,
				okStr : '确定',
				cancelStr : '取消',
				okFunc : function() {
					//险种代码
					var riskCode=$(".selected").attr('value');
					//保单号码
					var policyNo=$(".policyNo").attr('value');
					//保单险种号码
					var polNo=$(".selected .polNo").attr('value');
					//如果是产品条款
					if(type == 1 ){
						window.open("http://zizhu.sinatay.com/nss/sys/clause/"+riskCode+".pdf");
					}
					//保险利益
					if(type == 2){
						//执行ajax请求 验证该保选择单险种是由保险利益
						$.ajax({
							type : "post",
							url:contextRootPath+"/myPolicyDetail/checkInBnf.do",
							data : "policyNo=" + policyNo + "&polNo=" + polNo,
							dataType : 'json',
							success : function(data, textStatus) {
								//响应数据
								if(data.flag=='0'){
									Sinosoft.alert({
										contentStr : '无保险利益信息！',
										subContentStr : data.desc,
										okStr : '确定'
									});
								}else{
									//获得
//									window.open(contextRootPath+'/myPolicyDetail/inBnf.do','_blank');
									$('#bnfJsonStr').val(data.bnfJsonStr);
									$('#bnffm').submit();
								}
							},
							error : function(backData) {
								a_alert("网络异常！");
							}
						})
					}
					//现金价值
					if(type == 3){
						//执行ajax请求 验证该保选择单险种是由价值信息
						$.ajax({
							type : "post",
							url:contextRootPath+"/myPolicyDetail/checkCashValue.do",
							data : "policyNo=" + policyNo + "&polNo=" + polNo,
							dataType : 'json',
							success : function(data, textStatus) {
								//响应数据
								if(data.flag=='0'){
									Sinosoft.alert({
										contentStr : '无现金价值信息！',
										subContentStr : data.desc,
										okStr : '确定'
									});
								}else{
									
									//获得价值信息jsonStr
									
//									window.open(contextRootPath+'/myPolicyDetail/cashValue.do','_blank');
									$('#cashValueJsonStr').val(data.jsonCashValueInfoStr);
									$('#cashValuefm').submit();
								}
							},
							error : function(backData) {
								a_alert("网络异常！");
							}
						})
						
					}
				},
				cancelFunc : function() {
					
				}
			}).open();
}
//加载险种列表
function loadInsuranceSelect2(json_risks) {
	
	var str='<div class="insurance_select">'
		+ '<div class="select_title">请选择险种：</div>';
	for(var i=0;i<json_risks.length;i++){
		var json_risk=json_risks[i];
	//隐藏险种号码
		if(i==0){
			str +='<div class="select_item selected" value="'+json_risk.riskCode+'">'
			//隐藏险种保单号
			str +='  <div style="display:none;" class="policyNo" value="'+json_risk.policyNo+'">'+json_risk.policyNo+'</div>';
			str +='  <div style="display:none;" class="polNo" value="'+json_risk.polNo+'">'+json_risk.polNo+'</div>';
			str +='  <div class="select_radio" id="insurance1"></div>'+json_risk.riskName+'</div>';
		}else{
			str +='  <div class="select_item" value="'+json_risk.riskCode+'">'
			str +='  <div style="display:none;" class="polNo" value="'+json_risk.polNo+'">'+json_risk.polNo+'</div>';
			str +='  <div class="select_radio" id="insurance1"></div>'+json_risk.riskName+'</div>';
		}
	}
	
	//隐藏价值信息  通过表单提交
	str = str + '<form id="cashValuefm" method="post" name="fm" action="'+contextRootPath+'/myPolicyDetail/cashValue.do" target="_blank">';
	str = str + '  <input type="text" id="cashValueJsonStr" name="jsonCashValueInfoStr" style="display:none;" />';
	str = str + '</form>';
	
	//隐藏保险利益信息  通过表单提交
	str = str + '<form id="bnffm" method="post" name="fm" action="'+contextRootPath+'/myPolicyDetail/inBnf.do" target="_blank">';
	str = str + '  <input type="text" id="bnfJsonStr" name="bnfJsonStr" style="display:none;"/>';
	str = str + '</form>';
	
	str = str + '</div>';
	var insuranceSelect=$(str);
	//改变样式
	insuranceSelect.find('.select_item').click(function() {
		$(this).addClass('selected');
		$(this).siblings().removeClass('selected');
	});

	
	return insuranceSelect;
}

$(document)
		.ready(
				function() {
					setup();
					// 点击特效
					$('.click_btn').on('mousedown', function() {
						$(this).css({
							'top' : '1px',
							'left' : '1px'
						});
					}).on('mouseup', function() {
						$(this).css({
							'top' : '0',
							'left' : '0'
						});
					});
					
					//前台缓存，初始化页面及各函数
					var edorsItem = $('.edors_item');
					totalNum = parseInt((edorsItem.length - 1) / 6) + 1;
					
					if (edorsItem.length > 6) {
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
						$('.page.page_num:first').hide();
					}
					$('.page.page_num:gt(4)').hide();
					
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
					
					//这些下拉框在原来有值的场合下
					$(".edit_option[name='policy.appnt.marriage']").click(function(){
						if($(this).siblings().text()!=''){
							$(this).find("option[value='']").remove();
						}						
					});
					$(".edit_option[name='policy.appnt.nationality']").click(function(){
						if($(this).siblings().text()!=''){
							$(this).find("option[value='']").remove();
						}		
					});
					$(".edit_option[name='policy.insured.marriage']").click(function(){
						if($(this).siblings().text()!=''){
							$(this).find("option[value='']").remove();
						}		
					});
					$(".edit_option[name='policy.insured.nationality']").click(function(){
						if($(this).siblings().text()!=''){
							$(this).find("option[value='']").remove();
						}		
					});					
					
					$('#to_edit_appnt')
							.click(
									function() {
										$(this).hide();
										$(this).prev().show();	
										$(this).next().show();	
										var parent = $(this).parents(
												'.info_area ');
										var input = parent.find('.edit_input');

										var option = parent
												.find('.edit_option');
										var action = parent.find('.action');

										input.show();
										option.show();
										if(action.val() == 'appntInfoChange.do'){
											$('.selected_job').addClass('selected_job_edit');
											$('.selected_job').click(function() {
												$('.select_panel').show();
											});
											$('#appntJobTemp').val($('.selected_job').html());
											if($('.selected_job').html() == ''){
												$('.selected_job').html('请选择职业');
											}
										}
										
										input.each(function() {
											$(this).val(
													$(this).siblings().text());
										});
										option
												.each(function() {
													for ( var i = 0; i < $(this)
															.get(0).length; i++) {
														if ($(this).get(0).options[i].text == $(
																this)
																.siblings()
																.text()) {
															$(this).get(0).options[i].selected = true;
															break;
														}
													}
													// $(this).val($(this).siblings().text());
												});

										var acity = $('#acity');
										chg(1);
										acity
												.each(function() {
													for ( var i = 0; i < $(this)
															.get(0).length; i++) {
														if ($(this).get(0).options[i].text == $(
																this)
																.siblings()
																.text()) {
															$(this).get(0).options[i].selected = true;
															break;
														}
													}
												});
										var acounty = $('#acounty');
										chg(2);
										acounty.each(function() {
													for ( var i = 0; i < $(this).get(0).length; i++) {
														if ($(this).get(0).options[i].text == $(
																this)
																.siblings()
																.text()) {
															$(this).get(0).options[i].selected = true;
															break;
														}
													}
												
												});
										input.siblings().hide();
										option.siblings().hide();
									});
					//修改被保人
					$('#to_edit_insured').click(function(){
						$(this).hide();
						$(this).prev().show();	
						$(this).next().show();	
						var parent = $(this).parents(
								'.info_area ');
						var input = parent.find('.edit_input');

						var option = parent
								.find('.edit_option');

						input.show();
						option.show();
						
						input.each(function() {
							$(this).val(
									$(this).siblings().text());
						});
						option
								.each(function() {
									for ( var i = 0; i < $(this)
											.get(0).length; i++) {
										if ($(this).get(0).options[i].text == $(
												this)
												.siblings()
												.text()) {
											$(this).get(0).options[i].selected = true;
											break;
										}
									}
									// $(this).val($(this).siblings().text());
								});
						
						
						
						var icity = $('#icity');
						chg2(1);
						icity
								.each(function() {
									for ( var i = 0; i < $(this)
											.get(0).length; i++) {
										if ($(this).get(0).options[i].text == $(
												this)
												.siblings()
												.text()) {
											$(this).get(0).options[i].selected = true;
											break;
										}
									}
								});
						
						var icounty = $('#icounty');
						chg2(2);
						icounty
								.each(function() {
									for ( var i = 0; i < $(this)
											.get(0).length; i++) {
										if ($(this).get(0).options[i].text == $(
												this)
												.siblings()
												.text()) {
											$(this).get(0).options[i].selected = true;
											break;
										}
									}
								});
						input.siblings().hide();
						option.siblings().hide();
						
					});

					$('.save_edit')
							.click(
									function() {
										var thisbtn = $(this);
										var ctx = $('#ctx').val()

										var parent = $(this).parents(
												'.info_area ');
										var text = parent.find('.text_show');
										var action = parent.find('.action').val();
										
										if(action == 'appntInfoChange.do'){
											
										}else{
											
										}

										// 校验输入数据格式
										var email = parent.find('.email').val();
										var mobile = parent.find('.mobile')
												.val();
										var officePhone = parent.find(
												'.officePhone').val();
										var fax = parent.find('.fax').val();
										var homeZipCode = parent.find(
												'.homeZipCode').val();
										var phone = parent.find('.myPhone').val();
										var reg = '';
										//验证是否为网网销的保单
										var scource = $('#scource').val();
										if(scource !='NETS'){
											//非网销验证手机号码
											reg = /^\d{11}$/;
											if (mobile != '' && !reg.test(mobile)) {
												a_alert('请检查您的手机号码是否正确！');
												return;
											}
										}
										//网销验证是否有改变的信息
											var oldMarriage = parent.find('#marriage').text();
											var oldNationality = parent.find('#nationality').text();
											var oldLicenseType = parent.find('#licenseType').text();
											var oldGrpName = parent.find('#grpName').text();
											var oldMobile =  parent.find('#mobile').text();
											var oldJobType = parent.find('#jobTypeHidden').text();
											var oldEmail = parent.find('#email').text();
											var oldProvince = parent.find('#province').text();
											var oldCity = parent.find('#city').text();
											var oldOfficePhone = parent.find('#officePhone').text();
											var oldCounty = parent.find('#county').text();
											var oldFax = parent.find('#fax').text();
											var oldHomeZipCode = parent.find('#homeZipCode').text();
											var oldPhone = parent.find('#phone').text();
											var oldHomeAddress=parent.find('#homeAddress').text();
											
											var newMarriage =  parent.find('.marriage option:selected').text();
											var newNationality =  parent.find('.nationality option:selected').text();
											var newLicenseType =  parent.find('.licenseType option:selected').text();
											var newGrpName = parent.find('.grpName').val();
											var newMobile = parent.find('#mobileInput').val();
											var newJobType =  parent.find('#newJobType').text();
											//需要判断是否是投保人与被人保人的职业类型  1，如果是被保人，职业名称就是不可更改
											if(newJobType == '请选择职业'){
												newJobType='';
											}
										
											var newProvince = parent.find('.province option:selected').text();
											var newCity = parent.find('.city option:selected').text();
											var newCounty = parent.find('.county option:selected').text();
											var newHomeAddress = parent.find('.homeAddress').val();
											var infoType=parent.find('#infoType').text();
											
											if(newMarriage == '无'){
												newMarriage="";
											}
											if(newNationality == '无'){
												newNationality="";
											}
											if(newLicenseType == '无'){
												newLicenseType="";
											}
											if(infoType == '1'){
												//判断是否有变更信息
												if(oldMarriage == newMarriage &&
												   oldNationality == newNationality &&
												   oldLicenseType == newLicenseType &&
												   oldGrpName == newGrpName &&
												   oldMobile == newMobile &&
												   oldEmail == email &&
												   oldProvince == newProvince &&
												   oldCity == newCity &&
												   oldOfficePhone == officePhone &&
												   oldCounty == newCounty &&
												   oldFax == fax &&
												   oldHomeZipCode == homeZipCode &&
												   oldPhone == phone &&
												   oldHomeAddress == newHomeAddress){
														a_alert('无变更信息！');
													return ;
												}
											}
											else{
												//判断是否有变更信息
												if(oldMarriage == newMarriage &&
												   oldNationality == newNationality &&
												   oldLicenseType == newLicenseType &&
												   oldGrpName == newGrpName &&
												   oldMobile == newMobile &&
												   oldJobType == newJobType &&
												   oldEmail == email &&
												   oldProvince == newProvince &&
												   oldCity == newCity &&
												   oldOfficePhone == officePhone &&
												   oldCounty == newCounty &&
												   oldFax == fax &&
												   oldHomeZipCode == homeZipCode &&
												   oldPhone == phone &&
												   oldHomeAddress == newHomeAddress){
													
													a_alert('无变更信息！');
													return ;
												}
											}
											
											reg=/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
										if (email != '' && !reg.test(email)) {
											a_alert('请检查您的电子邮箱是否正确！');
											return;
										}
										
										reg = /^(\d{3,4}-)?[1-9]\d{6,7}$/;
										if (officePhone != ''
												&& !reg.test(officePhone)) {
											a_alert('请检查您的办公电话是否正确！');
											return;
										}
										reg = /^(\d{3,4}-)?[1-9]\d{6,7}$/;
										if (fax != '' && !reg.test(fax)) {
											a_alert('请检查您的传真电话是否正确！');
											return;
										}
										//修改邮政编码不能为空
										if(homeZipCode == ''){
											a_alert('邮政编码不能为空！');
											return;
										}
										//联系地址不能为空
										if(newHomeAddress == ''){
											a_alert('联系地址不能为空！');
											return;
										}
										//移动电话、办公电话、住宅电话以上3项必填其一
										if(newMobile == '' && officePhone == '' && phone == ''){
											a_alert('移动电话、办公电话、住宅电话以上3项必填其一！');
											return;
										}
										
										reg = /^\d{6}$/;
										if (homeZipCode != ''
												&& !reg.test(homeZipCode)) {
											a_alert('请检查您的邮政编码是否正确！');
											return;
										}
										reg = /^(\d{3,4}-)?[1-9]\d{6,7}$/;
										if (phone != '' && !reg.test(phone)) {
											a_alert('请检查您的住宅电话是否正确！');
											return;
										}
										var userMobile = $("#userMobile").val();
										if(userMobile == ''){
											doNoPhone('手机号码');
											return;
										}
										
										showMobileCheckDiv(userMobile,thisbtn);
									});
					
					$('.cancel_edit').click(function() {
						$(this).hide();
						$(this).prev().show();	
						$(this).prev().prev().hide();
						var parent = $(this).parents('.info_area ');
						var input =  parent.find('.edit_input');
						var option = parent.find('.edit_option')
						input.hide();
						option.hide();
						input.siblings().show();	
						option.siblings().show();
						$('.selected_job').removeClass('selected_job_edit');
						$('.selected_job').unbind("click");	
						$('.selected_job').html($('#appntJobTemp').val());
					});
					
					Sinosoft.namespace('sinatay');

					sinatay.receiveAmount = 0;

					$('.receive').click(function() {
						doReceive();
					});

					// 没有收益人的场合，显示法定收益人
					var bnf_parent = $("#first_bnf").parents('.input_area');
					if(bnf_parent.children().size() == 2){
						$("#first_bnf").css("display","block");
					}				
					
					var alertType = getQueryString('alertType');

					if (alertType == '1') {// 操作失败弹出框
						Sinosoft.alert({
							contentStr : '对不起，操作失败！',
							subContentStr : '请您重新操作，或拨打客服电话400-600-8890进行咨询。',
							okStr : '确定',
							cancelStr : '取消'
						});
					} else if (alertType == '2') {// 不能对保单进行操作弹出框
						Sinosoft.alert({
							contentStr : '对不起，目前您不能对该保单进行操作！',
							subContentStr : '原因：ＸＸＸＸＸＸＸＸＸＸＸＸＸＸＸＸＸＸＸＸＸＸ',
							width : 480,
							okStr : '确定',
							cancelStr : '取消'
						});
					} else if (alertType == '3') {// 保单详情页-领取-填写金额-退保确认
						new Sinosoft.InteractiveDialog({
							layout : loadWithdrawConfirm(),
							width : 560,
							okStr : '上一步',
							cancelStr : '确定',
							okFunc : function() {
								doReceive();
							},
							cancelFunc : function() {
								// $('#total_amount').text(parseInt(sinatay.totalAmount
								// - sinatay.receiveAmount -
								// sinatay.receiveAmount * 0.02));
							}
						}).open();
					} else if (alertType == '4') {// 保单详情页-领取-填写金额-退保确认
						new Sinosoft.InteractiveDialog({
							layout : loadInsuranceSelect(),
							okStr : '确认',
							cancelStr : '取消'
						}).open();
					}
				});

function loadReceiveInput() {
	var success = $('<div class="receive_input">'
			+ '<label class="receive_label">请输入领取金额：</label><input class="receive_text" style="color:#000000;" type="text" name="receiveAmount"/><label class="receive_label_tip">元</label>'
			+ '<a href="javascript:prall()">全部领取</a>'
			+ '</div>');
	return success;
}

function prall() {
	$(".receive_text").val($("#total_amount").text());
}

function loadReceiveAmountConfirm() {
	var amountConfirm = $('<div class="amount_confirm">'
			+ '<div class="this_time"><p class="amount">本次您领取金额为：<span id="amount_confirm" class="amount_num"></span>元</p>'
			+ '<p class="instro">本次领取需要支付手续费金额<span id="fee" class="fee_num">256.00</span>元</p>'

			+ '<p class="instro"></p></div>'
			+ '<div class="real_amount"><p class="amount">实际领取金额为：<span id="real_amount_confirm" class="amount_num"></span>元</p></div>'
			+ '<div class="refund_account"><p class="refund_title">您本次领取的金额将退至如下账户：</p>'
			+ '<div class="send_vali"><p>我们已向您预留的手机号<span id="b_appntphone">xxxxxxx</span>发送验证码</p><p>请将验证码输入下框！</p></div>'
			+ '<div class="vali_input"><label for="amount_validate">验证码：</label><input id="amount_validate" name="amount_validate" type="text"/><div class="resend click_btn">重新发送</div></div>'
			+ '</div>');

	// 验证码预留

	return amountConfirm;
}

function loadWithdrawConfirm() {
	var amountConfirm = $('<div class="amount_confirm">'
			+ '<div class="this_time"><p class="amount">本次您领取金额为：<span id="amount_confirm" class="amount_num">100000</span>元</p><p class="instro">本次领取需要支付手续费金额<span id="fee" class="fee_num">256.00</span>元</p><p class="instro">注：按本合同规定，全额领取即被视为退保，您该保单下的其他责任将同步</p></div>'
			+ '<div class="real_amount"><p class="amount">实际领取金额为：<span id="real_amount_confirm" class="amount_num">256.00</span>元</p><p class="instro">实际领取金额包括从最近一次结算日至今的利息收入</p></div>'
			+ '<div class="refund_account"><p class="refund_title">您本次领取的金额将退至如下账户：</p><p class="refund_info"><span class="info_name">户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>豆豆豆</p><p class="refund_info"><span class="info_name">银行名称：</span>中国建设银行</p><p class="refund_info"><span class="info_name">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</span>685 475 412 4411</p></div>'
			+ '<div class="send_vali"><p>我们已向您预留的手机号<span>18628282828</span>发送验证码</p><p>请将验证码输入下框！</p></div>'
			+ '<div class="vali_input"><label for="amount_validate">验证码：</label><input name="amount_validate" type="text"/><div class="resend click_btn">重新发送</div></div>'
			+ '</div>');

	return amountConfirm;
}

function loadInsuranceSelect() {
	var insuranceSelect = $('<div class="insurance_select">'
			+ '<div class="select_title">请选择险种：</div>'
			+ '<div class="select_item selected"><div class="select_radio" id="insurance1"></div>信泰锦绣前程少儿两全保险(分红型)A款条款</div>'
			+ '<div class="select_item"><div class="select_radio" id="insurance1"></div>信泰锦绣前程少儿两全保险(分红型)B款条款</div>'
			+ '</div>');

	insuranceSelect.find('.select_item').click(function() {
		$(this).addClass('selected');
		$(this).siblings().removeClass('selected');
	});

	return insuranceSelect;
}

function downfile(acceptNo) {
	var url = '';
	$.ajax({
		type : "post",
		url : contextRootPath + "/myPolicyDetail/check.do",
		data : {
			"acceptNo" : acceptNo
		},
		dataType : 'json',
		success : function(data, textStatus) {
			if (data.flag != '1') {
				Sinosoft.alert({
					contentStr: '系统访问错误',
					subContentStr : data.desc,
					cancelStr:'确定',
					okBtnShow:false
				});
			} else {
				url = data.url;
				$("#url").val(url);
				$("#down_from").submit();
			}
		},
		error : function(backData) {
			a_alert("网络异常！");
		}
	});	
}

//得到验证码
function getPhoneCheckNo(){
	var phone = $("#userMobile").val();
	$('#getPhonePwd').valiCodeDisable();
	var v_no = '';	
	$.ajax({
		type : "POST",
		async : false,
		url : ctx+"/bq/sendPhoneCheckNo.do",
		dataType : 'text',
		data : {
			"phone" : phone
		},
		success : function(backData) {
			var json = eval ("(" + backData + ")");
			var flag = json.flag;
			if(flag == '1'){
				Sinosoft.alert({
					contentStr : '非常抱歉！',
					subContentStr : '今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。',
					width : 480,
					okStr : '确定',
					cancelStr : '取消',
					okFunc:function(){
						doReceive();
					}
				});
				/*Sinosoft.alert({
					contentStr: "非常抱歉，今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
						doReceive();
					}
				});*/
			}else{
				timenum = 120;
				intervalProcess = setInterval('counttime()',1000);
				$('#phone_amount_validate').removeAttr('readOnly');
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
	sinatay.phoneCheckNo = v_no;
	
}
//检查验证码
function checkPhoneCheckNo(){
	if('' == $("#phone_amount_validate").val()){
		$("#phone_amount_valiinfo").text('请输入验证码！');
		$('#phone_amount_validate').css({ border:"1px solid red"});
		return false;
	}	
	var isSuccess;
	$.ajax({
		url : contextRootPath+'/infoJson/checkPhone.do',
		type : 'POST',
		data : "validPhoneNo=" + $("#phone_amount_validate").val(),
		dataType : "json",
		async: false,
		success : function(data){
			isSuccess=data.checkPhone;
			if(isSuccess=="Y"){
				$("#phone_amount_valiinfo").text('');
				$('#phone_amount_validate').css({ border:"1px solid #d1d1d1"});
			}else{
				$("#phone_amount_valiinfo").text('验证码输入错误！');
				$('#phone_amount_validate').css({ border:"1px solid red"});
			}
		},
		error: function(backData) {
			a_alert('网络错误！');
		}
	});
	if(isSuccess=="Y"){
		return true;
	}else{
		return false;
	}
}
//领取按钮,输入金额
function doReceive(){
	
	// 检查是否有手机号可以发送验证码
	var phone = $("#userMobile").val();
	if(phone == null || phone == ''){
		Sinosoft.alert({
			contentStr: "操作前必须先完善您的手机信息。",
			okFunc:function(){
				doNoPhone('手机号码');
			}
		});		
		return '';
	}
	// 检查领取日期
	if($("#receiveFlag").val() != 'true'){
		Sinosoft.alert({
			contentStr: "亲，保单次日零时生效后才能申请退保和领取哦~",
			cancelStr:'确定',
			okBtnShow:false
		});		
		return '';
	}
	var test;
	var loading = new Sinosoft.LoadingDialog({
		contentStr: '请耐心等待...',
		okStr:'',
		noCancel: true,
		closeFunc:function(){
			
		},
		waitFunc:function(){
			return test;
		}
	});
	
	var receive_input = loadReceiveInput();
	
	new Sinosoft.InteractiveDialog({
		layout : receive_input,
		width:490,
		cancelStr:'下一步',
		okBtnShow:false,
		cancelBtnShow:true,
		closeIconShow:true,
		cancelFunc:function(){
			
			sinatay.receiveAmount = Number($('.receive_input .receive_text').val());
			sinatay.totalAmount = Number($('#total_amount').text());
			var tbflag = sinatay.receiveAmount==sinatay.totalAmount?'1':'0';
			
			if(sinatay.receiveAmount == ''){
				Sinosoft.alert({
					contentStr: '请输入金额',
					subContentStr:'请重新核对信息后继续。',
					okStr: '确定',
					cancelStr: '取消',
					okFunc:function(){
						doReceive();
					}
				});
			}else if(parseFloat(sinatay.receiveAmount) == 0){
				Sinosoft.alert({
					contentStr: '金额必须大于0',
					subContentStr:'请重新核对信息后继续。',
					okStr: '确定',
					cancelStr: '取消',
					okFunc:function(){
						doReceive();
					}
				});
			}else if(isNaN(sinatay.receiveAmount)){
				Sinosoft.alert({
					contentStr: '请输入数字',
					subContentStr:'请重新核对信息后继续。',
					okStr: '确定',
					cancelStr: '取消',
					okFunc:function(){
						doReceive();
					}
				});
			}else if(sinatay.receiveAmount > sinatay.totalAmount){
				Sinosoft.alert({
					contentStr: '输入数字大于账户余额',
					subContentStr:'请重新核对信息后继续。',
					okStr: '确定',
					cancelStr: '取消',
					okFunc:function(){
						doReceive();
					}
				});
			}else{				
				
				$.ajax({
					type : "POST",
					url : ctx+"/bq/PartReceiveCalculate.do",
					dataType : 'text',
					data : {
						"tbFlag" : tbflag,
						"contNo" : $("#cont_no").text(),
						"applyMoney" : $(".receive_text").val()
					},
					beforeSend : function(){
					    loading.open();
					},
					success : function(backData) {
						loading.close();
						var json = eval ("(" + backData + ")");
						if('1' != json.flag){
							Sinosoft.alert({
								contentStr: json.desc,
								cancelStr:'确定',
								okBtnShow:false
							});
						} else {
							sinatay.b_accname = json.acountName;//账户名
							sinatay.b_bankname = json.bankName;//银行名
							sinatay.b_accno = json.acountNo;//账户号
							
							//弹出框内容
							var str = '';							
							//手机号码隐藏
							var um01 = $("#userMobile").val();
							if(um01.length == 11){
								um01 = um01.substr(0,3)+'****'+um01.substr(7,11);
							}			
							if('1'==json.tbFlag){//alert(json.acountDetail);									
									var mingxi = '';
									for(var i = 0; i< json.acountDetail.length;i++){
										if('退费总金额'==json.acountDetail[i][0]){
											sinatay.getMoney = json.acountDetail[i][1];//实际到账金额
										}else{
											mingxi += '<p class="instro">'+json.acountDetail[i][0];
											mingxi += '<span id="fee" class="fee_num">'+json.acountDetail[i][1]+'</span>元</p>';
										}
									}
									str = 
										'<div class="amount_confirm">'
										+ '<div class="this_time"><p class="amount">本次您领取金额为：<span id="b_amount_confirm" style="color:#ff3333;font-size:25px;">'+sinatay.receiveAmount+'</span>元</p>'
										+ mingxi								
										+ '<p class="instro" id="real_amount_zhu">注：按本合同规定，全额领取即被视为退保。您该保单下的其他责任将同步终止.</p></div>'
										+ '<div class="real_amount"><p class="amount">实际领取金额为：<span style="color:#ff3333;font-size:25px;">'+sinatay.getMoney+'</span>元</p></div>'
										+ '<div class="refund_account"><p class="refund_title">您本次领取的金额将退至如下账户：</p>'
										+ '<p class="refund_info"><span class="info_name">户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span><span id="b_accname">'+sinatay.b_accname+'</span></p>'
										+ '<p class="refund_info"><span class="info_name">银行名称：</span><span id="b_bankname">'+sinatay.b_bankname+'</span></p>'
										+ '<p class="refund_info"><span class="info_name">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</span><span id="b_accno">'+sinatay.b_accno+'</span></p></div>'
										+ '<div class="send_vali"><p>我们已向您预留的手机号<span id="b_appntphone">'+um01+'</span>发送验证码</p><p>请将验证码输入下框！</p></div>'
										+ '<div class="vali_input"><label for="phone_amount_validate">验证码：</label><input id="phone_amount_validate" name="phone_amount_validate" type="text" value="" readOnly="true" onblur="checkPhoneCheckNo()" />'
										//手机验证输入框
										+ '<input type="button" id="getPhonePwd" class="resend click_btn" onclick="getPhoneCheckNo();" value="发送验证码"/>'
										+ '</div>'
										+ '<div style="color:#ff0000;margin-top:-36px;float:left;margin-left:75px;" id="phone_amount_valiinfo" ></div>'
										+ '</div>';
										
							}else{
								var mingxi = '';
								if(json.pdSxFee!=null&&json.pdSxFee!=0){
									mingxi += '<p class="instro">手续费金额<span id="fee" class="fee_num">'+json.pdSxFee+'</span>元</p>';
								}									
								if(json.loanBJ!=null&&json.loanBJ!=0){
									mingxi += '<p class="instro">还款金额<span id="fee" class="fee_num">'+json.loanBJ+'</span>元</p>';
								}									
								if(json.loanLX!=null&&json.loanLX!=0){
									mingxi += '<p class="instro">还款利息<span id="fee" class="fee_num">'+json.loanLX+'</span>元</p>';
								}
								
								str = 
									'<div class="amount_confirm">'
									+ '<div class="this_time"><p class="amount">本次您领取金额为：<span id="b_amount_confirm" style="color:#ff3333;font-size:25px;">'+sinatay.receiveAmount+'</span>元</p>'
									+ mingxi								
									+ '<p class="instro" id="real_amount_zhu"></p></div>'
									+ '<div class="real_amount"><p class="amount">实际领取金额为：<span style="color:#ff3333;font-size:25px;">'+json.getMoney+'</span>元</p></div>'
									+ '<div class="refund_account"><p class="refund_title">您本次领取的金额将退至如下账户：</p>'
									+ '<p class="refund_info"><span class="info_name">户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span><span id="b_accname">'+sinatay.b_accname+'</span></p>'
									+ '<p class="refund_info"><span class="info_name">银行名称：</span><span id="b_bankname">'+sinatay.b_bankname+'</span></p>'
									+ '<p class="refund_info"><span class="info_name">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</span><span id="b_accno">'+sinatay.b_accno+'</span></p></div>'
									+ '<div class="send_vali"><p>我们已向您预留的手机号<span id="b_appntphone">'+um01+'</span>发送验证码</p><p>请将验证码输入下框！</p></div>'
									+ '<div class="vali_input"><label for="phone_amount_validate">验证码：</label><input id="phone_amount_validate" name="phone_amount_validate" type="text" value="" readOnly="true" onblur="checkPhoneCheckNo()" />'
									//手机验证输入框
									+ '<input type="button" id="getPhonePwd" class="resend click_btn" onclick="getPhoneCheckNo();" value="发送验证码"/>'
									+ '</div>'
									+ '<div style="color:#ff0000;margin-top:-36px;float:left;margin-left:75px;" id="phone_amount_valiinfo" ></div>'
									+ '</div>';
							}
							new Sinosoft.InteractiveDialog({
								layout : $(str),
								width:560,
								okStr:'上一步',
								cancelStr:'确定',
								okBtnShow:true,
								closeIconShow:true,
								okFunc:function(){
									//初始化倒计时，防止时钟未停止
									//所有的事件清空
									$('#getPhonePwd').val('发送验证码');
									$('#getPhonePwd').valiCodeEnable();
									clearInterval(intervalProcess);
									doReceive();
								},
								cancelFunc:function(){
									//初始化倒计时，防止时钟未停止
									//所有的事件清空
									$('#getPhonePwd').val('发送验证码');
									$('#getPhonePwd').valiCodeEnable();
									clearInterval(intervalProcess);
									if(!checkPhoneCheckNo()){
										return false;
									}else{
										$.ajax({
											type : "POST",
											url : ctx+"/bq/PartReceiveConfirm.do",
											dataType : 'text',
											data : {
												"tbFlag" : tbflag,
												"contNo" : $("#cont_no").text(),
												"applyMoney" : $("#b_amount_confirm").text()
											},
											beforeSend : function(){
											    loading.open();
											},
											success : function(backData) {//alert(backData);
												loading.close();
												var json = eval ("(" + backData + ")")
												if('1' != json.flag){
													Sinosoft.alert({
														contentStr: json.desc,
														cancelStr:'确定',
														okBtnShow:true,

													});
												} else {
													var subSuccess = $('<div class="subscribe">'
															+'<div class="success"></div><div class="main_content">'
															+'<div class="main_txt">交易成功</div>'
															+'<div class="sub_txt">你已成功操作领取服务</div></div></div>');													
													new Sinosoft.InteractiveDialog({
														layout : subSuccess,
														width:410,//自定义面板宽度-根据设计来调整														
														cancelStr:'确定',
														okBtnShow:false,
														cancelFunc:function(){
															//location.reload();
															location.replace(ctx+"/myPolicyDetail/myPolicyDetail.do?policyNo="+$("#cont_no").text());
														}
													}).open();
												}						
											},
											error: function(backData) {
												Sinosoft.alert({
													contentStr: '系统访问错误',
													cancelStr:'确定',
													okBtnShow:false
												});
											}
										});
									}
								},closeFunc:function(){
									
									//初始化倒计时，防止时钟未停止
									//所有的事件清空
									$('#getPhonePwd').val('发送验证码');
									$('#getPhonePwd').valiCodeEnable();
									clearInterval(intervalProcess);
								}
							}).open();
						}						
					},
					error: function(backData) {
						Sinosoft.alert({
							contentStr: '系统访问错误',
							cancelStr:'确定',
							okBtnShow:false
						});
					}
				});				
			}
		}
	}).open();
	
	$('.receive_input .receive_text').focus();
	$('.receive_input .receive_text').val($("#total_amount").text());
}

//绑定手机
function doNoPhone(parameter){
	var receive_input = $('<div class="alert_phone_input">'
			+ '<label class="alert_phone_label">请输入'
			+ parameter
			+ '：</label>'
			+ '<input class="alert_phone_text" type="text" name="emailOrPhone" id="emailOrPhone" maxlength=30/>'
			+ '</div><br>');	
	
	new Sinosoft.InteractiveDialog({
		layout : receive_input,
		width:490,
		cancelStr:'确定',
		okBtnShow:false,
		cancelFunc:function(){
			var customerName = $("#emailOrPhone").val();
			if (customerName == "")
				return;
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
			
			var emailFalg = false;
			//判断用户录入的是否是邮箱以及邮箱地址是否合法
			if (customerName.indexOf("@") > 0) {
				//用户录入的邮箱是否合法
				var regemail =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9|_|\_|\.]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(customerName);
				if(!regemail){
					alert("请填写一个正确的邮箱地址");
					return false;
				} else {
					emailFalg = true;
				}
			}
			
			if (!emailFalg && !phoneFalg) {
				alert("请输入合法的" + parameter);
				return;
			}
			$.ajax({
				url : ctx+'/edit/supplyUserPersonal.do',
				type : 'POST',
				data : "param=" + customerName,
				dataType : "text",
				success : function(data){
					if (data == 'success') {
						//location.reload();
						location.replace(ctx+"/myPolicyDetail/myPolicyDetail.do?policyNo="+$("#cont_no").text());
					} else {
						alert("系统异常,请稍后重试.");
					}
				}
			});
		}
	}).open();
}

function showMobileCheckDiv(userMobile,thisbtn){
	$('#getPhonePwd').val('发送验证码');
	$('#getPhonePwd').valiCodeEnable();
	clearInterval(intervalProcess);
	var showMobile = userMobile;
	if(showMobile.length == 11){
		showMobile = userMobile.substr(0,3)+'****'+userMobile.substr(7,11);
	}
	var layOut = '<div class="mobileCheckDiv">'
		+'<div class="message">已向您的手机<span class="mobile">'+showMobile+'</span>发送手机验证码，请在下方的输入框中输入手机验证码</div>'
		+'<div class="alert_input_row"><div class="input"><label>验证码：</label>'
		+'<input type="text" maxlength="6" id="inputPhoneNo" readOnly="true"/>'
		+'<input class="resendBtn click_btn" id="getPhonePwd" type="button" value="发送验证码"/></div></div></div>';
	new Sinosoft.InteractiveDialog({
	layout : $(layOut),
	okStr:'确认',
	cancelStr:'取消',
	width:510,
	okFunc:function(){
	var isSuccess;
	$.ajax({
	url : contextRootPath+'/infoJson/checkPhone.do',
	type : 'POST',
	data : "validPhoneNo=" + $("#inputPhoneNo").val(),
	dataType : "json",
	async: false,
	success : function(data){
		isSuccess=data.checkPhone;
		if(isSuccess=="Y"){
			submitChange(thisbtn);
		}else{
			a_alert('验证码错误！');
		}
	},
	error: function(backData) {
		a_alert('网络错误！');
	}
	});
	},
	cancelFunc:function(){
	//所有的事件清空
	$('#getPhonePwd').val('发送验证码');
	$('#getPhonePwd').valiCodeEnable();
	clearInterval(intervalProcess);
	
	},closeFunc:function(){
	//所有的事件清空
	$('#getPhonePwd').val('发送验证码');
	$('#getPhonePwd').valiCodeEnable();
	clearInterval(intervalProcess);
	}
	}).open();
	$('.mobileCheckDiv .resendBtn').unbind('click');
	$('.mobileCheckDiv .resendBtn').click(function(){getMobileCHeckNum(userMobile,thisbtn);});
}

function getMobileCHeckNum(mobileNum,thisbtn){
	$('#getPhonePwd').valiCodeDisable();
	var tUrl;
	var checkNum;
	
	tUrl = ctx+"/infoJson/sendPhoneDynamicNumber.do";
	$.ajax({
		type : "POST",
		async : false,
		url : tUrl,
		dataType : 'json',
		data : {mobile:mobileNum},
		error : function(data) {
			checkNum = 0;
		},
		success : function(data) {
			if(data.flag == '1'){
				Sinosoft.alert({
					contentStr : '非常抱歉！',
					subContentStr : '今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。',
					width : 480,
					okStr : '确定',
					cancelStr : '取消',
					okFunc:function(){
						showMobileCheckDiv(mobileNum,thisbtn);
					}
				});
				//a_alert("非常抱歉，今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。");
			}else{
				timenum = 120;
				intervalProcess = setInterval('counttime()',1000);
				$('#inputPhoneNo').removeAttr('readOnly');
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

function submitChange(thisbtn){
	var test;
	var loading = new Sinosoft.LoadingDialog({
		contentStr: '请耐心等待...',
		okStr:'',
		noCancel: true,
		closeFunc:function(){
			
		},
		waitFunc:function(){
			return test;
		}
	});
	var ctx = $('#ctx').val();
	var parent = $(thisbtn).parents('.info_area ');
	var text = parent.find('.text_show');
	var action = parent.find('.action');
	thisbtn.hide();
	thisbtn.next().show();
	thisbtn.next().next().hide();
	text.show();
	text.siblings().hide();
	var params = $('#fm').serialize();
	$.ajax({
		type : "POST",
		url : ctx + '/infoJson/' + action.val(),
		dataType : 'json',
		data : params,
		beforeSend : function(){
		    loading.open();
	    },
		error : function() {
			a_alert("网络异常！");
		},
		success : function(data) {
			loading.close();
			//所有的事件清空
			$('#getPhonePwd').val('发送验证码');
			$('#getPhonePwd').valiCodeEnable();
			clearInterval(intervalProcess);
			if (data.flag == '1') {
				text.each(function() {
					$(this).text($(this).siblings().val());
				});
				parent.find('.edit_option').each(function() {
					var t = $(this).get(0).options[$(this).get(0).selectedIndex].text;
					$(this).siblings().text(t);
				});
				if(action.val() == 'appntInfoChange.do'){
					$('.selected_job').removeClass('selected_job_edit');
					$('.selected_job').unbind('click');
					if($('.selected_job').html() == '请选择职业'){
						$('.selected_job').html('');
					}
				}
				
				var subSuccess = $('<div class="subscribe">'
						+'<div class="success"></div><div class="main_content">'
						+'<div class="main_txt">更变成功!</div>'
						+'<div class="sub_txt">你已成功进行保全操作</div></div></div>');													
				new Sinosoft.InteractiveDialog({
					layout : subSuccess,
					width:410,//自定义面板宽度-根据设计来调整														
					cancelStr:'确定',
					okBtnShow:false,
					cancelFunc:function(){
						location.replace(ctx+"/myPolicyDetail/myPolicyDetail.do?policyNo="+$("#cont_no").text());
					}
				}).open();
			} else {
				if(action.val() == 'appntInfoChange.do'){
					$('.selected_job').removeClass('selected_job_edit');
					$('.selected_job').unbind('click');
					$('.selected_job').html($('#appntJobTemp').val());
				}
				a_alert(data.desc);
			}
		}
	});
}

function cn() {
	this.Items = {};
}
cn.prototype.add = function(id, iArray) {
	this.Items[id] = iArray;
}
cn.prototype.Exi = function(id) {
	if (typeof (this.Items[id]) == "undefined") {
		return false;
	}
	return true;
}

function chg(v) {
	if (v == 3) {
		return;
	}
	var str = "0";
	for (i = 0; i < v; i++) {
		str += ("_" + (document.getElementById(s[i]).selectedIndex));
	}

	var ss = document.getElementById(s[v]);
	with (ss) {
		length = 0;
		if (a_t.Exi(str)) {
			ar = a_t.Items[str];
			for (i = 0; i < ar.length; i = i + 2) {
				options[length] = new Option(ar[i + 1], ar[i]);
				if (ar[i + 1] == o[v]) {
					options[i / 2].selected = true;
				}
			}
		}
		if (++v < s.length) {
			chg(v);
		}
	}
}

function chg2(v) {
	if (v == 3) {
		return;
	}
	var str = "0";
	for (i = 0; i < v; i++) {
		str += ("_" + (document.getElementById(s2[i]).selectedIndex));
	}
	

	var ss = document.getElementById(s2[v]);
	with (ss) {
		length = 0;
		if (a_t.Exi(str)) {
			ar = a_t.Items[str];
			for (i = 0; i < ar.length; i = i + 2) {
				options[length] = new Option(ar[i + 1], ar[i]);
				if (ar[i + 1] == o[v]) {
					options[i / 2].selected = true;
				}
			}
		}
		if (++v < s.length) {
			chg2(v);
		}
	}
}

var s = [ "aprovince", "acity", "acounty" ];
var s2 = [ "iprovince", "icity", "icounty" ];
var o = [ "北京市", "北京市市辖区", "北京市东城区" ];

function setup() {
	$('.selected_job').unbind("click");
	for (i = 1; i <= 3; i++) {
		document.getElementById(s[(i - 1)]).onchange = new Function("chg("
				+ (i) + ")");
		document.getElementById(s2[(i - 1)]).onchange = new Function("chg2("
				+ (i) + ")");
	}
	chg(0);
	chg2(0);
}

var a_t = new cn();

a_t.add('0', [ '810000', '香港特别行政区', '110000', '北京市', '120000', '天津市', '130000',
		'河北省', '370000', '山东省', '230000', '黑龙江省', '210000', '辽宁省', '530000',
		'云南省', '520000', '贵州省', '510000', '四川省', '460000', '海南省', '500000',
		'重庆市', '450000', '广西壮族自治区', '340000', '安徽省', '140000', '山西省', '150000',
		'内蒙古自治区', '330000', '浙江省', '350000', '福建省', '360000', '江西省', '430000',
		'湖南省', '540000', '西藏自治区', '610000', '陕西省', '620000', '甘肃省', '630000',
		'青海省', '420000', '湖北省', '710000', '台湾省', '820000', '澳门特别行政区', '640000',
		'宁夏回族自治区', '410000', '河南省', '310000', '上海市', '320000', '江苏省', '220000',
		'吉林省', '650000', '新疆维吾尔自治区', '440000', '广东省' ]);
a_t.add('0_0', [ '810100', '香港特别行政区市辖区' ]);
a_t.add('0_0_0', [ '810101', '香港特别行政区中西区', '810102', '香港特别行政区东区', '810103',
		'香港特别行政区九龙城区', '810104', '香港特别行政区观塘区', '810105', '香港特别行政区南区', '810106',
		'香港特别行政区深水埗区', '810107', '香港特别行政区黄大仙区', '810108', '香港特别行政区湾仔区',
		'810109', '香港特别行政区油尖旺区', '810110', '香港特别行政区离岛区', '810111',
		'香港特别行政区葵青区', '810112', '香港特别行政区北区', '810113', '香港特别行政区西贡区', '810114',
		'香港特别行政区沙田区', '810115', '香港特别行政区屯门区', '810116', '香港特别行政区大埔区', '810117',
		'香港特别行政区荃湾区', '810118', '香港特别行政区元朗区' ]);
a_t.add('0_1', [ '110100', '北京市市辖区', '110200', '北京市县' ]);
a_t.add('0_1_0', [ '110101', '北京市东城区', '110102', '北京市西城区', '110103', '北京市崇文区',
		'110104', '北京市宣武区', '110105', '北京市朝阳区', '110106', '北京市丰台区', '110107',
		'北京市石景山区', '110108', '北京市海淀区', '110109', '北京市门头沟区', '110111', '北京市房山区',
		'110112', '北京市通州区', '110113', '北京市顺义区', '110114', '北京市昌平区', '110115',
		'北京市大兴区', '110116', '北京市怀柔区', '110117', '北京市平谷区' ]);
a_t.add('0_1_1', [ '110228', '北京市密云县', '110229', '北京市延庆县' ]);
a_t.add('0_2', [ '120100', '天津市市辖区', '120200', '天津市县' ]);
a_t.add('0_2_0', [ '120101', '天津市和平区', '120102', '天津市河东区', '120103', '天津市河西区',
		'120104', '天津市南开区', '120105', '天津市河北区', '120107', '天津市塘沽区', '120109',
		'天津市大港区', '120110', '天津市东丽区', '120112', '天津市津南区', '120113', '天津市北辰区',
		'120115', '天津市宝坻区', '120106', '天津市红桥区', '120108', '天津市汉沽区', '120111',
		'天津市西青区', '120114', '天津市武清区' ]);
a_t.add('0_2_1', [ '120223', '天津市静海县', '120221', '天津市宁河县', '120225', '天津市蓟县' ]);
a_t.add('0_3', [ '130200', '河北省唐山市', '130300', '河北省秦皇岛市', '130400', '河北省邯郸市',
		'130100', '河北省石家庄市', '130500', '河北省邢台市', '130600', '河北省保定市', '131100',
		'河北省衡水市', '130700', '河北省张家口市', '130800', '河北省承德市', '130900', '河北省沧州市',
		'131000', '河北省廊坊市' ]);
a_t.add('0_3_0',
		[ '130201', '河北唐山市辖区', '130203', '河北唐山路北区', '130204', '河北唐山古冶区',
				'130207', '河北唐山丰南区', '130208', '河北唐山丰润区', '130224', '河北唐山滦南县',
				'130225', '河北唐山乐亭县', '130229', '河北唐山玉田县', '130230', '河北唐山唐海县',
				'130283', '河北省迁安市', '130202', '河北唐山路南区', '130205', '河北唐山开平区',
				'130223', '河北唐山滦县', '130227', '河北唐山迁西县', '130281', '河北唐山遵化市' ]);
a_t.add('0_3_1', [ '130302', '河北秦皇岛海港区', '130303', '河北秦皇岛山海关区', '130304',
		'河北秦皇岛北戴河区', '130322', '河北秦皇岛昌黎县', '130323', '河北秦皇岛抚宁县', '130301',
		'河北秦皇岛市辖区', '130321', '河北秦皇岛青龙满族自治县', '130324', '河北秦皇岛卢龙县' ]);
a_t.add('0_3_2', [ '130401', '河北邯郸市辖区', '130403', '河北邯郸丛台区', '130404',
		'河北邯郸复兴区', '130421', '河北邯郸县', '130423', '河北邯郸临漳县', '130425', '河北邯郸大名县',
		'130426', '河北邯郸涉县', '130428', '河北邯郸肥乡县', '130429', '河北邯郸永年县', '130431',
		'河北邯郸鸡泽县', '130432', '河北邯郸广平县', '130434', '河北邯郸魏县', '130435',
		'河北邯郸曲周县', '130481', '河北邯郸武安市', '130402', '河北邯郸邯山区', '130406',
		'河北邯郸峰峰矿区', '130424', '河北邯郸成安县', '130427', '河北邯郸磁县', '130430',
		'河北邯郸邱县', '130433', '河北邯郸馆陶县' ]);
a_t.add('0_3_3', [ '130133', '河北石家庄赵县', '130181', '河北石家庄辛集市', '130183',
		'河北石家庄晋州市', '130184', '河北石家庄新乐市', '130102', '河北石家庄长安区', '130103',
		'河北石家庄桥东区', '130105', '河北石家庄新华区', '130107', '河北石家庄井陉矿区', '130108',
		'河北省石家庄市裕华区', '130123', '河北石家庄正定县', '130124', '河北石家庄栾城县', '130126',
		'河北石家庄灵寿县', '130127', '河北石家庄高邑县', '130129', '河北石家庄赞皇县', '130130',
		'河北石家庄无极县', '130131', '河北石家庄平山县', '130101', '河北石家庄市辖区', '130104',
		'河北石家庄桥西区', '130121', '河北石家庄井陉县', '130125', '河北石家庄行唐县', '130128',
		'河北石家庄深泽县', '130132', '河北石家庄元氏县', '130182', '河北石家庄藁城市', '130185',
		'河北省石家庄鹿泉市' ]);
a_t.add('0_3_4', [ '130501', '河北邢台市辖区', '130502', '河北邢台桥东区', '130503',
		'河北邢台桥西区', '130521', '河北邢台县', '130522', '河北邢台临城县', '130523', '河北邢台内丘县',
		'130524', '河北邢台柏乡县', '130525', '河北邢台隆尧县', '130526', '河北邢台任县', '130527',
		'河北邢台南和县', '130528', '河北邢台宁晋县', '130529', '河北邢台巨鹿县', '130530',
		'河北邢台新河县', '130531', '河北邢台广宗县', '130532', '河北邢台平乡县', '130533',
		'河北邢台威县', '130534', '河北邢台清河县', '130535', '河北邢台临西县', '130581',
		'河北邢台南宫市', '130582', '河北邢台沙河市' ]);
a_t.add('0_3_5', [ '130601', '河北保定市辖区', '130602', '河北保定新市区', '130603',
		'河北保定北市区', '130604', '河北保定南市区', '130621', '河北保定满城县', '130622',
		'河北保定清苑县', '130623', '河北省保定市涞水县', '130624', '河北省保定市阜平县', '130626',
		'河北省保定市定兴县', '130627', '河北省保定市唐县', '130628', '河北省保定市高阳县', '130630',
		'河北省保定市涞源县', '130631', '河北省保定市望都县', '130633', '河北省保定市易县', '130634',
		'河北省保定市曲阳县', '130635', '河北省保定市蠡县', '130637', '河北省保定市博野县', '130638',
		'河北省保定市雄县', '130682', '河北省保定市定州市', '130683', '河北省保定市安国市', '130684',
		'河北省保定市高碑店市', '130625', '河北省保定市徐水县', '130629', '河北省保定市容城县', '130632',
		'河北省保定市安新县', '130636', '河北省保定市顺平县', '130681', '河北省保定市涿州市' ]);
a_t.add('0_3_6', [ '131127', '河北省衡水市景县', '131128', '河北省衡水市阜城县', '131181',
		'河北省衡水市冀州市', '131182', '河北省衡水市深州市', '131101', '河北省衡水市市辖区', '131102',
		'河北省衡水市桃城区', '131121', '河北省衡水市枣强县', '131122', '河北省衡水市武邑县', '131123',
		'河北省衡水市武强县', '131124', '河北省衡水市饶阳县', '131125', '河北省衡水市安平县', '131126',
		'河北省衡水市故城县' ]);
a_t.add('0_3_7', [ '130701', '河北张家口市辖区', '130702', '河北张家口桥东区', '130705',
		'河北张家口宣化区', '130706', '河北张家口下花园区', '130722', '河北张家口张北县', '130723',
		'河北张家口康保县', '130725', '河北张家口尚义县', '130726', '河北张家口蔚县', '130728',
		'河北张家口怀安县', '130729', '河北张家口万全县', '130730', '河北张家口怀来县', '130732',
		'河北张家口赤城县', '130733', '河北张家口崇礼县', '130703', '河北张家口桥西区', '130721',
		'河北张家口宣化县', '130724', '河北张家口沽源县', '130727', '河北张家口阳原县', '130731',
		'河北张家口涿鹿县' ]);
a_t.add('0_3_8', [ '130801', '河北承德市辖区', '130802', '河北承德双桥区', '130804',
		'河北承德鹰手营子矿区', '130821', '河北承德县', '130823', '河北承德平泉县', '130824',
		'河北承德滦平县', '130826', '河北承德丰宁满族自治县', '130827', '河北承德宽城满族自治县', '130828',
		'河北承德围场满族蒙古族自治县', '130803', '河北承德双滦区', '130822', '河北承德兴隆县', '130825',
		'河北承德隆化县' ]);
a_t.add('0_3_9', [ '130901', '河北沧州市辖区', '130902', '河北沧州新华区', '130921',
		'河北沧州沧县', '130922', '河北沧州青县', '130924', '河北沧州海兴县', '130925', '河北沧州盐山县',
		'130927', '河北沧州南皮县', '130928', '河北沧州吴桥县', '130930', '河北沧州孟村回族自治县',
		'130981', '河北沧州泊头市', '130983', '河北沧州黄骅市', '130984', '河北沧州河间市',
		'130903', '河北沧州运河区', '130923', '河北沧州东光县', '130926', '河北沧州肃宁县',
		'130929', '河北沧州献县', '130982', '河北沧州任丘市' ]);
a_t.add('0_3_10', [ '131001', '河北廊坊市辖区', '131002', '河北廊坊安次区', '131022',
		'河北廊坊固安县', '131023', '河北廊坊永清县', '131025', '河北廊坊大城县', '131026',
		'河北廊坊文安县', '131081', '河北廊坊霸州市', '131082', '河北廊坊三河市', '131003',
		'河北省廊坊市广阳区', '131024', '河北廊坊香河县', '131028', '河北廊坊大厂回族自治县' ]);
a_t.add('0_4', [ '371400', '山东省德州市', '370100', '山东省济南市', '370200', '山东省青岛市',
		'371000', '山东省威海市', '371100', '山东省日照市', '371200', '山东省莱芜市', '370300',
		'山东省淄博市', '370400', '山东省枣庄市', '370500', '山东省东营市', '370600', '山东省烟台市',
		'370700', '山东省潍坊市', '370800', '山东省济宁市', '370900', '山东省泰安市', '371300',
		'山东省临沂市', '371600', '山东省滨州市', '371700', '山东省荷泽市', '371500', '山东省聊城市' ]);
a_t.add('0_4_0', [ '371401', '山东省德州市市辖区', '371421', '山东省德州市陵县', '371402',
		'山东省德州市德城区', '371422', '山东省德州市宁津县', '371423', '山东省德州市庆云县', '371424',
		'山东省德州市临邑县', '371425', '山东省德州市齐河县', '371426', '山东省德州市平原县', '371427',
		'山东省德州市夏津县', '371428', '山东省德州市武城县', '371481', '山东省乐陵市', '371482',
		'山东省禹城市' ]);
a_t.add('0_4_1', [ '370124', '山东济南平阴县', '370126', '山东济南商河县', '370181',
		'山东济南章丘市', '370101', '山东济南市辖区', '370102', '山东济南历下区', '370104',
		'山东济南槐荫区', '370105', '山东济南天桥区', '370113', '山东省济南市长清区', '370103',
		'山东济南市中区', '370112', '山东济南历城区', '370125', '山东济南济阳县' ]);
a_t.add('0_4_2', [ '370201', '山东青岛市辖区', '370202', '山东青岛市南区', '370205',
		'山东青岛四方区', '370211', '山东青岛黄岛区', '370213', '山东省青岛市李沧区', '370214',
		'山东省青岛市城阳区', '370282', '山东青岛即墨市', '370283', '山东青岛平度市', '370203',
		'山东青岛市北区', '370212', '山东青岛崂山区', '370281', '山东青岛胶州市', '370284',
		'山东青岛胶南市', '370285', '山东青岛莱西市' ]);
a_t.add('0_4_3', [ '371081', '山东威海文登市', '371001', '山东威海市辖区', '371002',
		'山东威海环翠区', '371082', '山东威海荣成市', '371083', '山东威海乳山市' ]);
a_t.add('0_4_4', [ '371121', '山东日照五莲县', '371101', '山东日照市辖区', '371102',
		'山东日照东港区', '371103', '山东省日照市岚山区', '371122', '山东日照莒县' ]);
a_t.add('0_4_5', [ '371203', '山东莱芜钢城区', '371201', '山东莱芜市辖区', '371202',
		'山东莱芜莱城区' ]);
a_t.add('0_4_6', [ '370301', '山东淄博市辖区', '370302', '山东淄博淄川区', '370303',
		'山东淄博张店区', '370304', '山东淄博博山区', '370305', '山东淄博临淄区', '370306',
		'山东淄博周村区', '370321', '山东淄博桓台县', '370322', '山东淄博高青县', '370323',
		'山东淄博沂源县' ]);
a_t.add('0_4_7', [ '370401', '山东枣庄市辖区', '370402', '山东枣庄市中区', '370403',
		'山东枣庄薛城区', '370404', '山东枣庄峄城区', '370405', '山东枣庄台儿庄区', '370406',
		'山东枣庄山亭区', '370481', '山东枣庄滕州市' ]);
a_t.add('0_4_8', [ '370501', '山东东营市辖区', '370502', '山东东营区', '370503', '山东东营河口区',
		'370521', '山东东营垦利县', '370522', '山东东营利津县', '370523', '山东东营广饶县' ]);
a_t.add('0_4_9', [ '370611', '山东烟台福山区', '370634', '山东烟台长岛县', '370683',
		'山东烟台莱州市', '370686', '山东省栖霞市', '370601', '山东烟台市辖区', '370602',
		'山东烟台芝罘区', '370612', '山东省烟台市牟平区', '370613', '山东省烟台市莱山区', '370681',
		'山东烟台龙口市', '370682', '山东烟台莱阳市', '370684', '山东烟台蓬莱市', '370685',
		'山东烟台招远市', '370687', '山东省海阳市' ]);
a_t.add('0_4_10', [ '370701', '山东潍坊市辖区', '370704', '山东潍坊坊子区', '370725',
		'山东潍坊昌乐县', '370783', '山东潍坊寿光市', '370786', '山东省昌邑市', '370702',
		'山东潍坊潍城区', '370703', '山东潍坊寒亭区', '370705', '山东省潍坊市奎文区', '370724',
		'山东潍坊临朐县', '370781', '山东潍坊青州市', '370782', '山东潍坊诸城市', '370784',
		'山东省安丘市', '370785', '山东省高密市' ]);
a_t.add('0_4_11', [ '370801', '山东济宁市辖区', '370826', '山东济宁微山县', '370829',
		'山东济宁嘉祥县', '370832', '山东济宁梁山县', '370883', '山东济宁邹城市', '370802',
		'山东济宁市中区', '370811', '山东济宁任城区', '370827', '山东济宁鱼台县', '370828',
		'山东济宁金乡县', '370830', '山东济宁汶上县', '370831', '山东济宁泗水县', '370881',
		'山东济宁曲阜市', '370882', '山东济宁兖州市' ]);
a_t.add('0_4_12', [ '370902', '山东泰安泰山区', '370982', '山东泰安新泰市', '370901',
		'山东泰安市辖区', '370903', '山东省泰安市岱岳区', '370921', '山东泰安宁阳县', '370923',
		'山东泰安东平县', '370983', '山东泰安肥城市' ]);
a_t.add('0_4_13', [ '371312', '山东省临沂市河东区', '371321', '山东省临沂市沂南县', '371323',
		'山东省临沂市沂水县', '371324', '山东省临沂市苍山县', '371325', '山东省临沂市费县', '371327',
		'山东省临沂市莒南县', '371328', '山东省临沂市蒙阴县', '371311', '山东省临沂市罗庄区', '371322',
		'山东省临沂市郯城县', '371326', '山东省临沂市平邑县', '371329', '山东省临沂市临沭县', '371301',
		'山东省临沂市市辖区', '371302', '山东省临沂市兰山区' ]);
a_t.add('0_4_14', [ '371601', '山东省滨州市市辖区', '371602', '山东省滨州市滨城区', '371621',
		'山东省滨州市惠民县', '371622', '山东省滨州市阳信县', '371623', '山东省滨州市无棣县', '371624',
		'山东省滨州市沾化县', '371625', '山东省滨州市博兴县', '371626', '山东省滨州市邹平县' ]);
a_t.add('0_4_15', [ '371724', '山东省荷泽市巨野县', '371727', '山东省荷泽市定陶县', '371701',
		'山东省荷泽市市辖区', '371702', '山东省荷泽市牡丹区', '371721', '山东省荷泽市曹县', '371722',
		'山东省荷泽市单县', '371723', '山东省荷泽市成武县', '371725', '山东省荷泽市郓城县', '371726',
		'山东省荷泽市鄄城县', '371728', '山东省荷泽市东明县' ]);
a_t.add('0_4_16', [ '371581', '山东省临清市', '371501', '山东省聊城市市辖区', '371502',
		'山东省聊城市东昌府区', '371521', '山东省聊城市阳谷县', '371522', '山东省聊城市莘县', '371523',
		'山东省聊城市茌平县', '371524', '山东省聊城市东阿县', '371525', '山东省聊城市冠县', '371526',
		'山东省聊城市高唐县' ]);
a_t.add('0_5', [ '230400', '黑龙江省鹤岗市', '230500', '黑龙江省双鸭山市', '230600',
		'黑龙江省大庆市', '230700', '黑龙江省伊春市', '230900', '黑龙江省七台河市', '231000',
		'黑龙江省牡丹江市', '231200', '黑龙江省绥化市', '230200', '黑龙江省齐齐哈尔市', '232700',
		'黑龙江省大兴安岭地区', '230100', '黑龙江省哈尔滨市', '230300', '黑龙江省鸡西市', '230800',
		'黑龙江省佳木斯市', '231100', '黑龙江省黑河市' ]);
a_t.add('0_5_0', [ '230401', '黑龙江鹤岗市辖区', '230402', '黑龙江鹤岗向阳区', '230403',
		'黑龙江鹤岗工农区', '230404', '黑龙江鹤岗南山区', '230405', '黑龙江鹤岗兴安区', '230406',
		'黑龙江鹤岗东山区', '230407', '黑龙江鹤岗兴山区', '230421', '黑龙江鹤岗萝北县', '230422',
		'黑龙江鹤岗绥滨县' ]);
a_t.add('0_5_1', [ '230501', '黑龙江双鸭山市辖区', '230502', '黑龙江双鸭山尖山区', '230503',
		'黑龙江双鸭山岭东区', '230505', '黑龙江双鸭山四方台区', '230506', '黑龙江双鸭山宝山区', '230521',
		'黑龙江双鸭山集贤县', '230522', '黑龙江双鸭山友谊县', '230523', '黑龙江双鸭山宝清县', '230524',
		'黑龙江双鸭山饶河县' ]);
a_t.add('0_5_2', [ '230601', '黑龙江大庆市辖区', '230602', '黑龙江大庆萨尔图区', '230603',
		'黑龙江大庆龙凤区', '230604', '黑龙江大庆让胡路区', '230605', '黑龙江大庆红岗区', '230606',
		'黑龙江大庆大同区', '230621', '黑龙江大庆肇州县', '230622', '黑龙江大庆肇源县', '230624',
		'黑龙江大庆杜尔伯特蒙古族自治县', '230623', '黑龙江大庆林甸县' ]);
a_t.add('0_5_3', [ '230701', '黑龙江伊春市辖区', '230703', '黑龙江伊春南岔区', '230704',
		'黑龙江伊春友好区', '230706', '黑龙江伊春翠峦区', '230707', '黑龙江伊春新青区', '230708',
		'黑龙江伊春美溪区', '230710', '黑龙江伊春五营区', '230711', '黑龙江伊春乌马河区', '230713',
		'黑龙江伊春带岭区', '230714', '黑龙江伊春乌伊岭区', '230716', '黑龙江伊春上甘岭区', '230722',
		'黑龙江伊春嘉荫县', '230781', '黑龙江伊春铁力市', '230702', '黑龙江伊春伊春区', '230705',
		'黑龙江伊春西林区', '230709', '黑龙江伊春金山屯区', '230712', '黑龙江伊春汤旺河区', '230715',
		'黑龙江伊春红星区' ]);
a_t.add('0_5_4', [ '230902', '黑龙江七台河新兴区', '230903', '黑龙江七台河桃山区', '230921',
		'黑龙江七台河勃利县', '230901', '黑龙江七台河市辖区', '230904', '黑龙江七台河茄子河区' ]);
a_t.add('0_5_5', [ '231001', '黑龙江牡丹江市辖区', '231003', '黑龙江牡丹江阳明区', '231004',
		'黑龙江牡丹江爱民区', '231024', '黑龙江牡丹江东宁县', '231025', '黑龙江牡丹江林口县', '231081',
		'黑龙江牡丹江绥芬河市', '231084', '黑龙江牡丹江宁安市', '231085', '黑龙江省穆棱市', '231002',
		'黑龙江牡丹江东安区', '231005', '黑龙江牡丹江西安区', '231083', '黑龙江牡丹江海林市' ]);
a_t.add('0_5_6', [ '231201', '黑龙江省绥化市市辖区', '231221', '黑龙江省绥化市望奎县', '231222',
		'黑龙江省绥化市兰西县', '231223', '黑龙江省绥化市青冈县', '231225', '黑龙江省绥化市明水县', '231226',
		'黑龙江省绥化市绥棱县', '231281', '黑龙江省安达市', '231283', '黑龙江省海伦市', '231202',
		'黑龙江省绥化市北林区', '231224', '黑龙江省绥化市庆安县', '231282', '黑龙江省肇东市' ]);
a_t.add('0_5_7', [ '230201', '黑龙江齐齐哈尔市辖区', '230203', '黑龙江齐齐哈尔建华区', '230204',
		'黑龙江齐齐哈尔铁锋区', '230205', '黑龙江齐齐哈尔昂昂溪区', '230207', '黑龙江齐齐哈尔碾子山区',
		'230208', '黑龙江齐齐哈尔梅里斯达斡尔族区', '230221', '黑龙江齐齐哈尔龙江县', '230223',
		'黑龙江齐齐哈尔依安县', '230225', '黑龙江齐齐哈尔甘南县', '230227', '黑龙江齐齐哈尔富裕县', '230230',
		'黑龙江齐齐哈尔克东县', '230231', '黑龙江齐齐哈尔拜泉县', '230281', '黑龙江齐齐哈尔讷河市', '230202',
		'黑龙江齐齐哈尔龙沙区', '230206', '黑龙江齐齐哈尔富拉尔基区', '230224', '黑龙江齐齐哈尔泰来县',
		'230229', '黑龙江齐齐哈尔克山县' ]);
a_t.add('0_5_8', [ '232721', '黑龙江大兴安岭呼玛县', '232722', '黑龙江大兴安岭塔河县', '232723',
		'黑龙江大兴安岭漠河县' ]);
a_t.add('0_5_9', [ '230101', '黑龙江哈尔滨市辖区', '230102', '黑龙江哈尔滨道里区', '230103',
		'黑龙江哈尔滨南岗区', '230106', '黑龙江哈尔滨香坊区', '230107', '黑龙江哈尔滨动力区', '230109',
		'黑龙江省哈尔滨市松北区', '230111', '黑龙江省哈尔滨市呼兰区', '230123', '黑龙江哈尔滨依兰县',
		'230125', '黑龙江哈尔滨宾县', '230126', '黑龙江省哈尔滨市巴彦县', '230127', '黑龙江省哈尔滨市木兰县',
		'230129', '黑龙江省哈尔滨市延寿县', '230181', '黑龙江哈尔滨阿城市', '230182',
		'黑龙江省哈尔滨市双城市', '230184', '黑龙江省哈尔滨市五常市', '230104', '黑龙江哈尔滨道外区',
		'230108', '黑龙江哈尔滨平房区', '230124', '黑龙江哈尔滨方正县', '230128', '黑龙江省哈尔滨市通河县',
		'230183', '黑龙江省哈尔滨市尚志市' ]);
a_t.add('0_5_10', [ '230306', '黑龙江鸡西城子河区', '230307', '黑龙江鸡西麻山区', '230321',
		'黑龙江鸡西鸡东县', '230381', '黑龙江省虎林市', '230382', '黑龙江省密山市', '230301',
		'黑龙江鸡西市辖区', '230302', '黑龙江鸡西鸡冠区', '230304', '黑龙江鸡西滴道区', '230305',
		'黑龙江鸡西梨树区', '230303', '黑龙江鸡西恒山区' ]);
a_t.add('0_5_11', [ '230801', '黑龙江佳木斯市辖区', '230802', '黑龙江佳木斯永红区', '230804',
		'黑龙江佳木斯前进区', '230805', '黑龙江佳木斯东风区', '230811', '黑龙江佳木斯郊区', '230826',
		'黑龙江佳木斯桦川县', '230828', '黑龙江佳木斯汤原县', '230881', '黑龙江佳木斯同江市', '230882',
		'黑龙江佳木斯富锦市', '230803', '黑龙江佳木斯向阳区', '230822', '黑龙江佳木斯桦南县', '230833',
		'黑龙江佳木斯抚远县' ]);
a_t.add('0_5_12', [ '231101', '黑龙江黑河市辖区', '231102', '黑龙江黑河爱辉区', '231123',
		'黑龙江黑河逊克县', '231124', '黑龙江黑河孙吴县', '231181', '黑龙江黑河北安市', '231121',
		'黑龙江黑河嫩江县', '231182', '黑龙江黑河五大连池市' ]);
a_t.add('0_6', [ '210400', '辽宁省抚顺市', '210500', '辽宁省本溪市', '210600', '辽宁省丹东市',
		'210700', '辽宁省锦州市', '210800', '辽宁省营口市', '210900', '辽宁省阜新市', '211300',
		'辽宁省朝阳市', '210100', '辽宁省沈阳市', '210200', '辽宁省大连市', '210300', '辽宁省鞍山市',
		'211000', '辽宁省辽阳市', '211100', '辽宁省盘锦市', '211200', '辽宁省铁岭市', '211400',
		'辽宁省葫芦岛市' ]);
a_t.add('0_6_0', [ '210401', '辽宁抚顺市辖区', '210402', '辽宁抚顺新抚区', '210403',
		'辽宁抚顺东洲区', '210404', '辽宁抚顺望花区', '210411', '辽宁抚顺顺城区', '210421', '辽宁抚顺县',
		'210422', '辽宁抚顺新宾满族自治县', '210423', '辽宁抚顺清原满族自治县' ]);
a_t.add('0_6_1', [ '210501', '辽宁本溪市辖区', '210502', '辽宁本溪平山区', '210503',
		'辽宁本溪溪湖区', '210504', '辽宁本溪明山区', '210505', '辽宁省本溪市南芬区', '210521',
		'辽宁本溪满族自治县', '210522', '辽宁本溪桓仁满族自治县' ]);
a_t.add('0_6_2', [ '210601', '辽宁丹东市辖区', '210602', '辽宁丹东元宝区', '210603',
		'辽宁丹东振兴区', '210604', '辽宁丹东振安区', '210624', '辽宁丹东宽甸满族自治县', '210681',
		'辽宁丹东东港市', '210682', '辽宁省凤城市' ]);
a_t.add('0_6_3', [ '210701', '辽宁锦州市辖区', '210702', '辽宁锦州古塔区', '210703',
		'辽宁锦州凌河区', '210711', '辽宁锦州太和区', '210727', '辽宁锦州义县', '210782', '辽宁省北宁市',
		'210726', '辽宁锦州黑山县', '210781', '辽宁锦州凌海市' ]);
a_t.add('0_6_4', [ '210802', '辽宁营口站前区', '210803', '辽宁营口西市区', '210811',
		'辽宁营口老边区', '210881', '辽宁营口盖州市', '210801', '辽宁营口市辖区', '210804',
		'辽宁营口鲅鱼圈区', '210882', '辽宁营口大石桥市' ]);
a_t.add('0_6_5', [ '210901', '辽宁阜新市辖区', '210903', '辽宁阜新新邱区', '210904',
		'辽宁阜新太平区', '210911', '辽宁阜新细河区', '210921', '辽宁阜新蒙古族自治县', '210922',
		'辽宁阜新彰武县', '210902', '辽宁阜新海州区', '210905', '辽宁阜新清河门区' ]);
a_t.add('0_6_6', [ '211301', '辽宁朝阳市辖区', '211303', '辽宁朝阳龙城区', '211321', '辽宁朝阳县',
		'211324', '辽宁朝阳喀喇沁左翼蒙古自治县', '211381', '辽宁朝阳北票市', '211382', '辽宁朝阳凌源市',
		'211302', '辽宁朝阳双塔区', '211322', '辽宁朝阳建平县' ]);
a_t.add('0_6_7', [ '210102', '辽宁沈阳和平区', '210103', '辽宁沈阳沈河区', '210105',
		'辽宁沈阳皇姑区', '210106', '辽宁沈阳铁西区', '210112', '辽宁沈阳东陵区', '210113',
		'辽宁沈阳新城子区', '210122', '辽宁沈阳辽中县', '210123', '辽宁沈阳康平县', '210181',
		'辽宁沈阳新民市', '210101', '辽宁沈阳市辖区', '210104', '辽宁沈阳大东区', '210111',
		'辽宁沈阳苏家屯区', '210114', '辽宁沈阳于洪区', '210124', '辽宁沈阳法库县' ]);
a_t.add('0_6_8', [ '210202', '辽宁大连中山区', '210203', '辽宁大连西岗区', '210211',
		'辽宁大连甘井子区', '210212', '辽宁大连旅顺口区', '210224', '辽宁大连长海县', '210281',
		'辽宁大连瓦房店市', '210283', '辽宁大连庄河市', '210201', '辽宁大连市辖区', '210204',
		'辽宁大连沙河口区', '210213', '辽宁大连金州区', '210282', '辽宁大连普兰店市' ]);
a_t.add('0_6_9', [ '210311', '辽宁鞍山千山区', '210321', '辽宁鞍山台安县', '210323',
		'辽宁鞍山岫岩满族自治县', '210381', '辽宁鞍山海城市', '210302', '辽宁鞍山铁东区', '210303',
		'辽宁鞍山铁西区', '210301', '辽宁鞍山市辖市', '210304', '辽宁鞍山立山区' ]);
a_t.add('0_6_10', [ '211001', '辽宁辽阳市辖区', '211002', '辽宁辽阳白塔区', '211004',
		'辽宁辽阳宏伟区', '211005', '辽宁辽阳弓长岭区', '211021', '辽宁辽阳县', '211081', '辽宁省灯塔市',
		'211003', '辽宁辽阳文圣区', '211011', '辽宁辽阳太子河区' ]);
a_t.add('0_6_11', [ '211101', '辽宁盘锦市辖区', '211102', '辽宁盘锦双台子区', '211121',
		'辽宁盘锦大洼县', '211122', '辽宁盘锦盘山县', '211103', '辽宁盘锦兴隆台区' ]);
a_t.add('0_6_12', [ '211201', '辽宁铁岭市辖区', '211202', '辽宁铁岭银州区', '211221',
		'辽宁铁岭县', '211224', '辽宁铁岭昌图县', '211281', '辽宁铁岭调兵山市', '211204',
		'辽宁铁岭清河区', '211223', '辽宁铁岭西丰县', '211282', '辽宁铁岭开原市' ]);
a_t.add('0_6_13', [ '211401', '辽宁葫芦岛市辖区', '211402', '辽宁葫芦岛连山区', '211404',
		'辽宁葫芦岛南票区', '211421', '辽宁葫芦岛绥中县', '211481', '辽宁葫芦岛兴城市', '211403',
		'辽宁葫芦岛龙港区', '211422', '辽宁葫芦岛建昌县' ]);
a_t.add('0_7', [ '530100', '云南省昆明市', '530300', '云南省曲靖市', '530700', '云南省丽江市',
		'530800', '云南省思茅市', '530900', '云南省临沧市', '530400', '云南省玉溪市', '530500',
		'云南省保山市', '530600', '云南省昭通市', '532300', '云南省楚雄彝族自治州', '532900',
		'云南省大理白族自治州', '533100', '云南省德宏傣族景颇族自治州', '533400', '云南省迪庆藏族自治州',
		'532500', '云南省红河哈尼族彝族自治州', '532600', '云南省文山壮族苗族自治州', '532800',
		'云南省西双版纳傣族自治州', '533300', '云南省怒江傈僳族自治州' ]);
a_t.add('0_7_0', [ '530101', '云南昆明市辖区', '530102', '云南昆明五华区', '530103',
		'云南昆明盘龙区', '530111', '云南昆明官渡区', '530112', '云南昆明西山区', '530113',
		'云南省昆明市东川区', '530121', '云南昆明呈贡县', '530122', '云南昆明晋宁县', '530124',
		'云南昆明富民县', '530125', '云南昆明宜良县', '530126', '云南昆明石林彝族自治县', '530127',
		'云南昆明嵩明县', '530128', '云南昆明禄劝彝族苗族自治县', '530129', '云南省昆明市寻甸回族彝族自治县',
		'530181', '云南省安宁市' ]);
a_t.add('0_7_1', [ '530301', '云南省曲靖市市辖区', '530302', '云南省曲靖市麒麟区', '530321',
		'云南省曲靖市马龙县', '530323', '云南省曲靖市师宗县', '530324', '云南省曲靖市罗平县', '530326',
		'云南省曲靖市会泽县', '530328', '云南省曲靖市沾益县', '530381', '云南省宣威市', '530322',
		'云南省曲靖市陆良县', '530325', '云南省曲靖市富源县' ]);
a_t.add('0_7_2', [ '530702', '云南省丽江市古城区', '530721', '云南省丽江市玉龙纳西族自治县', '530722',
		'云南省丽江市永胜县', '530724', '云南省丽江市宁蒗彝族自治县', '530701', '云南省丽江市市辖区',
		'530723', '云南省丽江市华坪县' ]);
a_t.add('0_7_3', [ '530801', '云南省思茅市市辖区', '530821', '云南省思茅市普洱哈尼族彝族自治县',
		'530822', '云南省思茅市墨江哈尼族自治县', '530823', '云南省思茅市景东彝族自治县', '530824',
		'云南省思茅市景谷傣族彝族自治县', '530825', '云南省思茅市镇沅彝族哈尼族拉祜族自治县', '530827',
		'云南省思茅市孟连傣族拉祜族佤族自治县', '530828', '云南省思茅市澜沧拉祜族自治县', '530829',
		'云南省思茅市西盟佤族自治县', '530802', '云南省思茅市翠云区', '530826', '云南省思茅市江城哈尼族彝族自治县' ]);
a_t.add('0_7_4', [ '530902', '云南省临沧市临翔区', '530921', '云南省临沧市凤庆县', '530922',
		'云南省临沧市云县', '530924', '云南省临沧市镇康县', '530925', '云南省临沧市双江拉祜族佤族布朗族傣族自治县',
		'530926', '云南省临沧市耿马傣族佤族自治县', '530927', '云南省临沧市沧源佤族自治县', '530901',
		'云南省临沧市市辖区', '530923', '云南省临沧市永德县' ]);
a_t.add('0_7_5', [ '530401', '云南省玉溪市市辖区', '530402', '云南省玉溪市红塔区', '530422',
		'云南省玉溪市澄江县', '530423', '云南省玉溪市通海县', '530425', '云南省玉溪市易门县', '530426',
		'云南省玉溪市峨山彝族自治县', '530427', '云南省玉溪市新平彝族傣族自治县', '530428',
		'云南省玉溪市元江哈尼族彝族傣族自治县', '530421', '云南省玉溪市江川县', '530424', '云南省玉溪市华宁县' ]);
a_t.add('0_7_6', [ '530501', '云南省保山市市辖区', '530502', '云南省保山市隆阳区', '530521',
		'云南省保山市施甸县', '530523', '云南省保山市龙陵县', '530524', '云南省保山市昌宁县', '530522',
		'云南省保山市腾冲县' ]);
a_t.add('0_7_7', [ '530601', '云南省昭通市市辖区', '530602', '云南省昭通市昭阳区', '530621',
		'云南省昭通市鲁甸县', '530623', '云南省昭通市盐津县', '530624', '云南省昭通市大关县', '530626',
		'云南省昭通市绥江县', '530627', '云南省昭通市镇雄县', '530628', '云南省昭通市彝良县', '530630',
		'云南省昭通市水富县', '530622', '云南省昭通市巧家县', '530625', '云南省昭通市永善县', '530629',
		'云南省昭通市威信县' ]);
a_t.add('0_7_8', [ '532301', '云南楚雄市', '532322', '云南楚雄双柏县', '532323', '云南楚雄牟定县',
		'532324', '云南楚雄南华县', '532325', '云南楚雄姚安县', '532326', '云南楚雄大姚县',
		'532327', '云南楚雄永仁县', '532328', '云南楚雄元谋县', '532329', '云南楚雄武定县',
		'532331', '云南楚雄禄丰县' ]);
a_t.add('0_7_9', [ '532923', '云南大理祥云县', '532927', '云南大理巍山彝族回族自治县', '532930',
		'云南大理洱源县', '532901', '云南大理市', '532922', '云南大理漾濞彝族自治县', '532924',
		'云南大理宾川县', '532925', '云南大理弥渡县', '532926', '云南大理南涧彝族自治县', '532928',
		'云南大理永平县', '532929', '云南大理云龙县', '532931', '云南大理剑川县', '532932',
		'云南大理鹤庆县' ]);
a_t.add('0_7_10', [ '533122', '云南德宏梁河县', '533102', '云南德宏瑞丽市', '533103',
		'云南省潞西市', '533123', '云南德宏盈江县', '533124', '云南德宏陇川县' ]);
a_t.add('0_7_11', [ '533421', '云南迪庆香格里拉县', '533422', '云南迪庆德钦县', '533423',
		'云南迪庆　维西傈僳族自治县' ]);
a_t.add('0_7_12', [ '532501', '云南红河个旧市', '532502', '云南红河开远市', '532522',
		'云南红河蒙自县', '532523', '云南红河屏边苗族自治县', '532524', '云南红河建水县', '532525',
		'云南红河石屏县', '532526', '云南红河弥勒县', '532527', '云南红河泸西县', '532528',
		'云南红河元阳县', '532529', '云南红河县', '532530', '云南红河金平苗族瑶族傣族自治县', '532531',
		'云南红河绿春县', '532532', '云南红河河口瑶族自治县' ]);
a_t.add('0_7_13', [ '532621', '云南文山县', '532622', '云南文山砚山县', '532623',
		'云南文山西畴县', '532624', '云南文山麻栗坡县', '532625', '云南文山马关县', '532626',
		'云南文山丘北县', '532627', '云南文山广南县', '532628', '云南文山富宁县' ]);
a_t.add('0_7_14', [ '532801', '云南西双版纳景洪市', '532822', '云南西双版纳勐海县', '532823',
		'云南西双版纳勐腊县' ]);
a_t.add('0_7_15', [ '533321', '云南怒江傈泸水县', '533323', '云南怒江傈福贡县', '533324',
		'云南怒江傈贡山独龙族怒族自治县', '533325', '云南怒江傈兰坪白族普米族自治县' ]);
a_t.add('0_8', [ '520100', '贵州省贵阳市', '520200', '贵州省六盘水市', '520300', '贵州省遵义市',
		'520400', '贵州省安顺市', '522200', '贵州省安顺市铜仁地区', '522300',
		'贵州省安顺市黔西南布依族苗族自治州', '522400', '贵州省安顺市毕节地区', '522700',
		'贵州省安顺市黔南布依族苗族自治州', '522600', '贵州省安顺市黔东南苗族侗族自治州' ]);
a_t.add('0_8_0', [ '520101', '贵州贵阳市辖区', '520102', '贵州贵阳南明区', '520103',
		'贵州贵阳云岩区', '520111', '贵州贵阳花溪区', '520112', '贵州贵阳乌当区', '520113',
		'贵州贵阳白云区', '520114', '贵州省贵阳市小河区', '520121', '贵州省贵阳市开阳县', '520122',
		'贵州省贵阳市息烽县', '520123', '贵州省贵阳市修文县', '520181', '贵州省清镇市' ]);
a_t.add('0_8_1', [ '520203', '贵州六盘水六枝特区', '520221', '贵州六盘水水城县', '520201',
		'贵州六盘水钟山区', '520222', '贵州省六盘水市盘县' ]);
a_t.add('0_8_2', [ '520301', '贵州省遵义市市辖区', '520303', '贵州省遵义市汇川区', '520321',
		'贵州省遵义市遵义县', '520322', '贵州省遵义市桐梓县', '520324', '贵州省遵义市正安县', '520325',
		'贵州省遵义市道真仡佬族苗族自治县', '520326', '贵州省遵义市务川仡佬族苗族自治县', '520327',
		'贵州省遵义市凤冈县', '520329', '贵州省遵义市余庆县', '520330', '贵州省遵义市习水县', '520382',
		'贵州省仁怀市', '520302', '贵州省遵义市红花岗区', '520323', '贵州省遵义市绥阳县', '520328',
		'贵州省遵义市湄潭县', '520381', '贵州省赤水市' ]);
a_t.add('0_8_3',
		[ '520402', '贵州省安顺市西秀区', '520421', '贵州省安顺市平坝县', '520422', '贵州省安顺市普定县',
				'520424', '贵州省安顺市关岭布依族苗族自治县', '520425', '贵州省安顺市紫云苗族布依族自治县',
				'520401', '贵州省安顺市市辖区', '520423', '贵州省安顺市镇宁布依族苗族自治县' ]);
a_t.add('0_8_4', [ '522201', '贵州铜仁市', '522223', '贵州铜仁玉屏侗族自治县', '522224',
		'贵州铜仁石阡县', '522226', '贵州铜仁印江土家族苗族自治县', '522227', '贵州铜仁德江县', '522228',
		'贵州铜仁沿河土家族自治县', '522230', '贵州铜仁万山特区', '522222', '贵州铜仁江口县', '522225',
		'贵州铜仁思南县', '522229', '贵州铜仁松桃苗族自治县' ]);
a_t.add('0_8_5', [ '522301', '贵州黔西兴义市', '522323', '贵州黔西普安县', '522324',
		'贵州黔西睛隆县', '522326', '贵州黔西望谟县', '522327', '贵州黔西册亨县', '522322',
		'贵州黔西兴仁县', '522325', '贵州黔西贞丰县', '522328', '贵州黔西安龙县' ]);
a_t.add('0_8_6', [ '522401', '贵州毕节市', '522423', '贵州毕节黔西县', '522424', '贵州毕节金沙县',
		'522426', '贵州毕节纳雍县', '522427', '贵州毕节威宁彝族回族苗族自治县', '522428', '贵州毕节赫章县',
		'522422', '贵州毕节大方县', '522425', '贵州毕节织金县' ]);
a_t.add('0_8_7', [ '522701', '贵州黔南都匀市', '522702', '贵州省福泉市', '522722',
		'贵州黔南荔波县', '522723', '贵州黔南贵定县', '522725', '贵州黔南瓮安县', '522726',
		'贵州黔南独山县', '522727', '贵州黔南平塘县', '522728', '贵州黔南罗甸县', '522729',
		'贵州黔南长顺县', '522730', '贵州黔南龙里县', '522731', '贵州黔南惠水县', '522732',
		'贵州黔南三都水族自治县' ]);
a_t.add('0_8_8', [ '522601', '贵州黔东凯里市', '522622', '贵州黔东黄平县', '522624',
		'贵州黔东三穗县', '522625', '贵州黔东镇远县', '522627', '贵州黔东天柱县', '522628',
		'贵州黔东锦屏县', '522630', '贵州黔东台江县', '522631', '贵州黔东黎平县', '522633',
		'贵州黔东从江县', '522634', '贵州黔东雷山县', '522636', '贵州黔东丹寨县', '522623',
		'贵州黔东施秉县', '522626', '贵州黔东岑巩县', '522629', '贵州黔东剑河县', '522632',
		'贵州黔东榕江县', '522635', '贵州黔东麻江县' ]);
a_t.add('0_9', [ '513400', '凉山彝族自治州', '511300', '南充市', '511400', '眉山市',
		'511500', '宜宾市', '511600', '广安市', '511700', '达州市', '511800', '万源市雅安市',
		'511900', '巴中市', '512000', '资阳市', '513300', '甘孜藏族自治州', '510100',
		'四川省成都市', '510500', '泸州市', '510600', '德阳市', '510700', '绵阳市', '511000',
		'内江市', '510300', '自贡市', '510400', '攀枝花市', '510800', '广元市', '510900',
		'遂宁市', '511100', '乐山市', '513200', '阿坝藏族羌族自治州' ]);
a_t.add('0_9_0', [ '513401', '四川凉山西昌市', '513422', '四川凉山木里藏族自治县', '513423',
		'四川凉山盐源县', '513424', '四川凉山德昌县', '513425', '四川凉山会理县', '513426',
		'四川凉山会东县', '513427', '四川凉山宁南县', '513428', '四川凉山普格县', '513429',
		'四川凉山布拖县', '513430', '四川凉山金阳县', '513431', '四川凉山昭觉县', '513432',
		'四川凉山喜德县', '513433', '四川凉山冕宁县', '513434', '四川凉山越西县', '513435',
		'四川凉山甘洛县', '513436', '四川凉山美姑县', '513437', '四川凉山雷波县' ]);
a_t.add('0_9_1', [ '511301', '四川南充市辖区', '511302', '四川南充顺庆区', '511303',
		'四川南充高坪区', '511304', '四川南充嘉陵区', '511321', '四川南充南部县', '511322',
		'四川南充营山县', '511323', '四川南充蓬安县', '511324', '四川南充仪陇县', '511325',
		'四川南充西充县', '511381', '四川南充阆中市' ]);
a_t.add('0_9_2', [ '511401', '眉山市市辖区', '511402', '眉山市东坡区', '511421', '眉山市仁寿县',
		'511422', '眉山市彭山县', '511423', '眉山市洪雅县', '511424', '眉山市丹棱县', '511425',
		'眉山市青神县' ]);
a_t.add('0_9_3', [ '511501', '宜宾市市辖区', '511521', '宜宾市宜宾县', '511522', '宜宾市南溪县',
		'511524', '宜宾市长宁县', '511526', '宜宾市珙县', '511527', '宜宾市筠连县', '511529',
		'宜宾市屏山县', '511502', '宜宾市翠屏区', '511523', '宜宾市江安县', '511525', '宜宾市高县',
		'511528', '宜宾市兴文县' ]);
a_t.add('0_9_4', [ '511602', '广安市广安区', '511622', '广安市武胜县', '511623', '广安市邻水县',
		'511601', '广安市市辖区', '511621', '广安市岳池县', '511681', '华蓥市' ]);
a_t.add('0_9_5', [ '511701', '达州市市辖区', '511721', '达州市达县', '511723', '达州市开江县',
		'511724', '达州市大竹县', '511781', '万源市', '511702', '达州市通川区', '511722',
		'达州市宣汉县', '511725', '达州市渠县' ]);
a_t.add('0_9_6', [ '511802', '万源市雨城区', '511822', '万源市荥经县', '511823', '万源市汉源县',
		'511825', '万源市天全县', '511826', '万源市芦山县', '511801', '万源市市辖区', '511821',
		'万源市名山县', '511824', '万源市石棉县', '511827', '万源市宝兴县' ]);
a_t.add('0_9_7', [ '511901', '巴中市市辖区', '511921', '巴中市通江县', '511923', '巴中市平昌县',
		'511902', '巴中市巴州区', '511922', '巴中市南江县' ]);
a_t.add('0_9_8', [ '512002', '资阳市雁江区', '512021', '资阳市安岳县', '512081', '简阳市',
		'512001', '资阳市市辖区', '512022', '资阳市乐至县' ]);
a_t.add('0_9_9', [ '513322', '四川甘孜泸定县', '513323', '四川甘孜丹巴县', '513325',
		'四川甘孜雅江县', '513326', '四川甘孜道孚县', '513328', '四川甘孜县', '513329', '四川甘孜新龙县',
		'513331', '四川甘孜白玉县', '513332', '四川甘孜石渠县', '513334', '四川甘孜理塘县',
		'513335', '四川甘孜巴塘县', '513337', '四川甘孜稻城县', '513338', '四川甘孜得荣县',
		'513321', '四川甘孜康定县', '513324', '四川甘孜九龙县', '513327', '四川甘孜炉霍县',
		'513330', '四川甘孜德格县', '513333', '四川甘孜色达县', '513336', '四川甘孜乡城县' ]);
a_t.add('0_9_10', [ '510101', '四川成都市辖区', '510104', '四川成都锦江区', '510105',
		'四川成都青羊区', '510106', '四川成都金牛区', '510107', '四川成都武侯区', '510112',
		'四川成都龙泉驿区', '510113', '四川成都青白江区', '510115', '成都市温江区', '510121',
		'四川成都金堂县', '510124', '四川成都郫县', '510129', '四川成都大邑县', '510132',
		'四川成都新津县', '510181', '四川成都都江堰市', '510183', '邛崃市', '510184', '崇州市',
		'510108', '四川成都成华区', '510114', '成都市新都区', '510122', '四川成都双流县', '510131',
		'四川成都浦江县', '510182', '四川成都彭州市' ]);
a_t.add('0_9_11', [ '510501', '四川泸州市辖区', '510503', '泸州市纳溪区', '510504',
		'泸州市龙马潭区', '510522', '四川泸州合江县', '510524', '四川泸州叙永县', '510502',
		'四川泸州市江阳区', '510521', '四川泸州泸县', '510525', '四川泸州古蔺县' ]);
a_t.add('0_9_12', [ '510601', '四川德阳市辖区', '510623', '四川德阳中江县', '510626',
		'德阳市罗江县', '510682', '什邡市', '510603', '德阳市旌阳区', '510681', '四川德阳广汉市',
		'510683', '绵竹市' ]);
a_t.add('0_9_13', [ '510701', '四川绵阳市辖区', '510704', '四川绵阳游仙区', '510722',
		'四川绵阳三台县', '510724', '四川绵阳安县', '510725', '四川绵阳梓潼县', '510727',
		'四川绵阳平武县', '510781', '四川绵阳江油市', '510703', '四川绵阳涪城区', '510723',
		'四川绵阳盐亭县', '510726', '四川绵阳北川羌族自治县' ]);
a_t.add('0_9_14', [ '511002', '四川内江市中区', '511011', '四川内江东兴区', '511025',
		'四川内江资中县', '511028', '四川内江隆昌县', '511001', '四川内江市辖区', '511024',
		'四川内江威远县' ]);
a_t.add('0_9_15', [ '510301', '四川自贡市辖区', '510303', '四川自贡贡井区', '510304',
		'四川自贡大安区', '510321', '四川自贡荣县', '510322', '四川自贡富顺县', '510302',
		'四川自贡自流井区', '510311', '四川自贡沿滩区' ]);
a_t.add('0_9_16', [ '510401', '四川攀枝花市辖区', '510402', '四川攀枝花东区', '510411',
		'四川攀枝花仁和区', '510421', '四川攀枝花米易县', '510403', '四川攀枝花西区', '510422',
		'四川攀枝花盐边县' ]);
a_t.add('0_9_17', [ '510801', '四川广元市辖区', '510802', '四川广元市中区', '510812',
		'四川广元朝天区', '510821', '四川广元旺苍县', '510823', '四川广元剑阁县', '510824',
		'四川广元苍溪县', '510811', '四川广元元坝区', '510822', '四川广元青川县' ]);
a_t.add('0_9_18',
		[ '510901', '四川遂宁市辖区', '510904', '遂宁市安居区', '510921', '四川遂宁蓬溪县',
				'510923', '遂宁市大英县', '510903', '遂宁市船山区', '510922', '四川遂宁射洪县' ]);
a_t.add('0_9_19', [ '511101', '四川乐山市辖区', '511102', '四川乐山市中区', '511111',
		'四川乐山沙湾区', '511112', '四川乐山五通桥区', '511113', '四川乐山金口河区', '511123',
		'四川乐山犍为县', '511124', '四川乐山井研县', '511126', '四川乐山夹江县', '511129',
		'四川乐山沐川县', '511132', '四川乐山峨边彝族自治县', '511133', '四川乐山马边彝族自治县', '511181',
		'四川乐山峨眉山市' ]);
a_t.add('0_9_20', [ '513221', '四川阿坝汶川县', '513222', '四川阿坝理县', '513224',
		'四川阿坝松潘县', '513225', '四川阿坝九寨沟县', '513227', '四川阿坝小金县', '513228',
		'四川阿坝黑水县', '513230', '四川阿坝壤塘县', '513231', '四川阿坝县', '513233', '四川阿坝红原县',
		'513223', '四川阿坝茂县', '513226', '四川阿坝金川县', '513229', '四川阿坝马尔康县',
		'513232', '四川阿坝若尔盖县' ]);
a_t.add('0_10', [ '460100', '海南省海口市', '460200', '海南省三亚市', '469000',
		'海南省省直辖县级行政单位' ]);
a_t.add('0_10_0', [ '460105', '海南省海口市秀英区', '460106', '海南省海口市龙华区', '460107',
		'海南省海口市琼山区', '460101', '海南海口市辖区', '460108', '海南省海口市美兰区' ]);
a_t.add('0_10_1', [ '460201', '海南三亚市辖区' ]);
a_t.add('0_10_2', [ '469001', '海南省五指山市', '469002', '海南省琼海市', '469005',
		'海南省文昌市', '469006', '海南省万宁市', '469025', '海南省定安县', '469026', '海南省屯昌县',
		'469028', '海南省临高县', '469030', '海南省白沙黎族自治县', '469031', '海南省昌江黎族自治县',
		'469033', '海南省乐东黎族自治县', '469035', '海南省保亭黎族苗族自治县', '469036',
		'海南省琼中黎族苗族自治县', '469037', '海南省西沙群岛', '469039', '海南省中沙群岛的岛礁及其海域',
		'469003', '海南省儋州市', '469007', '海南省东方市', '469027', '海南省澄迈县', '469034',
		'海南省陵水黎族自治县', '469038', '海南省南沙群岛' ]);
a_t.add('0_11', [ '500300', '重庆市市', '500200', '重庆市县', '500100', '重庆市市辖区' ]);
a_t.add('0_11_0', [ '500381', '重庆市江津市', '500382', '重庆市合川市', '500383', '重庆市永川市',
		'500384', '重庆市南川市' ]);
a_t.add('0_11_1', [ '500224', '重庆市铜梁县', '500225', '重庆市大足县', '500226', '重庆市荣昌县',
		'500227', '重庆市璧山县', '500228', '重庆市梁平县', '500229', '重庆市城口县', '500230',
		'重庆市丰都县', '500231', '重庆市垫江县', '500232', '重庆市武隆县', '500233', '重庆市忠县',
		'500234', '重庆市开县', '500235', '重庆市云阳县', '500236', '重庆市奉节县', '500237',
		'重庆市巫山县', '500238', '重庆市巫溪县', '500240', '重庆市石柱土家族自治县', '500241',
		'重庆市秀山土家族苗族自治县', '500242', '重庆市酉阳土家族苗族自治县', '500243', '重庆市彭水苗族土家族自治县',
		'500223', '重庆市潼南县', '500222', '重庆市綦江县' ]);
a_t.add('0_11_2', [ '500101', '重庆市万州区', '500102', '重庆市涪陵区', '500104',
		'重庆市大渡口区', '500105', '重庆市江北区', '500107', '重庆市九龙坡区', '500108', '重庆市南岸区',
		'500110', '重庆市万盛区', '500111', '重庆市双桥区', '500113', '重庆市巴南区', '500114',
		'重庆市黔江区', '500103', '重庆市渝中区', '500106', '重庆市沙坪坝区', '500109', '重庆市北碚区',
		'500112', '重庆市渝北区', '500115', '重庆市长寿区' ]);
a_t.add('0_12',
		[ '451000', '广西壮族自治区百色市', '451100', '广西壮族自治区贺州市', '451200',
				'广西壮族自治区河池市', '451300', '广西壮族自治区来宾市', '451400', '广西壮族自治区崇左市',
				'450100', '广西壮族自治区南宁市', '450200', '广西壮族自治区柳州市', '450300',
				'广西壮族自治区桂林市', '450400', '广西壮族自治区梧州市', '450500', '广西壮族自治区北海市',
				'450600', '广西壮族自治区防城港市', '450700', '广西壮族自治区钦州市', '450800',
				'广西壮族自治区贵港市', '450900', '广西壮族自治区玉林市' ]);
a_t.add('0_12_0', [ '451001', '广西壮族自治区百色市市辖区', '451002', '广西壮族自治区百色市右江区',
		'451021', '广西壮族自治区百色市田阳县', '451022', '广西壮族自治区百色市田东县', '451023',
		'广西壮族自治区百色市平果县', '451024', '广西壮族自治区百色市德保县', '451025', '广西壮族自治区百色市靖西县',
		'451026', '广西壮族自治区百色市那坡县', '451027', '广西壮族自治区百色市凌云县', '451028',
		'广西壮族自治区百色市乐业县', '451029', '广西壮族自治区百色市田林县', '451030', '广西壮族自治区百色市西林县',
		'451031', '广西壮族自治区百色市隆林各族自治县' ]);
a_t.add('0_12_1', [ '451101', '广西壮族自治区贺州市市辖区', '451102', '广西壮族自治区贺州市八步区',
		'451121', '广西壮族自治区贺州市昭平县', '451122', '广西壮族自治区贺州市钟山县', '451123',
		'广西壮族自治区贺州市富川瑶族自治县' ]);
a_t.add('0_12_2', [ '451201', '广西壮族自治区河池市市辖区', '451202', '广西壮族自治区河池市金城江区',
		'451221', '广西壮族自治区河池市南丹县', '451222', '广西壮族自治区河池市天峨县', '451223',
		'广西壮族自治区河池市凤山县', '451224', '广西壮族自治区河池市东兰县', '451225',
		'广西壮族自治区河池市罗城仫佬族自治县', '451226', '广西壮族自治区河池市环江毛南族自治县', '451227',
		'广西壮族自治区河池市巴马瑶族自治县', '451228', '广西壮族自治区河池市都安瑶族自治县', '451229',
		'广西壮族自治区河池市大化瑶族自治县', '451281', '广西壮族自治区宜州市' ]);
a_t.add('0_12_3',
		[ '451301', '广西壮族自治区来宾市市辖区', '451302', '广西壮族自治区来宾市兴宾区', '451321',
				'广西壮族自治区来宾市忻城县', '451322', '广西壮族自治区来宾市象州县', '451323',
				'广西壮族自治区来宾市武宣县', '451324', '广西壮族自治区来宾市金秀瑶族自治县', '451381',
				'广西壮族自治区合山市' ]);
a_t.add('0_12_4', [ '451401', '广西壮族自治区崇左市市辖区', '451402', '广西壮族自治区崇左市江洲区',
		'451421', '广西壮族自治区崇左市扶绥县', '451422', '广西壮族自治区崇左市宁明县', '451423',
		'广西壮族自治区崇左市龙州县', '451424', '广西壮族自治区崇左市大新县', '451425', '广西壮族自治区崇左市天等县',
		'451481', '广西壮族自治区凭祥市' ]);
a_t.add('0_12_5', [ '450101', '广西南宁市辖区', '450102', '广西南宁兴宁区', '450103',
		'广西南宁青秀区', '450105', '广西南宁江南区', '450107', '广西壮族自治区南宁市西乡塘区', '450108',
		'广西壮族自治区南宁市良庆区', '450109', '广西壮族自治区南宁市邕宁区', '450122', '广西南宁武鸣县',
		'450123', '广西壮族自治区南宁市隆安县', '450124', '广西壮族自治区南宁市马山县', '450125',
		'广西壮族自治区南宁市上林县', '450126', '广西壮族自治区南宁市宾阳县', '450127', '广西壮族自治区南宁市横县' ]);
a_t.add('0_12_6', [ '450201', '广西柳州市辖区', '450202', '广西柳州城中区', '450203',
		'广西柳州鱼峰区', '450205', '广西柳州柳北区', '450221', '广西柳州柳江县', '450223',
		'广西壮族自治区柳州市鹿寨县', '450224', '广西壮族自治区柳州市融安县', '450225',
		'广西壮族自治区柳州市融水苗族自治县', '450226', '广西壮族自治区柳州市三江侗族自治县', '450204',
		'广西柳州柳南区', '450222', '广西柳州柳城县' ]);
a_t.add('0_12_7', [ '450302', '广西桂林秀峰区', '450303', '广西桂林叠彩区', '450305',
		'广西桂林七星区', '450311', '广西桂林市雁山区', '450322', '广西桂林临桂县', '450323',
		'广西壮族自治区桂林市灵川县', '450324', '广西壮族自治区桂林市全州县', '450326', '广西壮族自治区桂林市永福县',
		'450327', '广西壮族自治区桂林市灌阳县', '450328', '广西壮族自治区桂林市龙胜各族自治县', '450329',
		'广西壮族自治区桂林市资源县', '450330', '广西壮族自治区桂林市平乐县', '450332',
		'广西壮族自治区桂林市恭城瑶族自治县', '450301', '广西桂林市辖区', '450304', '广西桂林象山区',
		'450321', '广西桂林阳朔县', '450325', '广西壮族自治区桂林市兴安县', '450331',
		'广西壮族自治区桂林市荔蒲县' ]);
a_t.add('0_12_8', [ '450401', '广西梧州市辖区', '450404', '广西梧州蝶山区', '450405',
		'广西壮族自治区梧州市长洲区', '450422', '广西壮族自治区梧州市藤县', '450423', '广西壮族自治区梧州市蒙山县',
		'450481', '广西壮族自治区岑溪市', '450403', '广西梧州万秀区', '450421', '广西梧州苍梧县' ]);
a_t.add('0_12_9', [ '450502', '广西北海海城区', '450503', '广西壮族自治区北海市银海区', '450512',
		'广西壮族自治区北海市铁山港区', '450501', '广西北海市辖区', '450521', '广西北海合浦县' ]);
a_t.add('0_12_10', [ '450601', '广西防城港市辖区', '450603', '广西防城港防城区', '450621',
		'广西防城港上思县', '450602', '广西防城港港口区', '450681', '广西壮族自治区东兴市' ]);
a_t.add('0_12_11', [ '450701', '广西壮族自治区钦州市市辖区', '450702', '广西壮族自治区钦州市钦南区',
		'450703', '广西壮族自治区钦州市钦北区', '450722', '广西壮族自治区钦州市浦北县', '450721',
		'广西壮族自治区钦州市灵山县' ]);
a_t.add('0_12_12', [ '450801', '广西壮族自治区贵港市市辖区', '450802', '广西壮族自治区贵港市港北区',
		'450804', '广西壮族自治区贵港市覃塘区', '450821', '广西壮族自治区贵港市平南县', '450881',
		'广西壮族自治区桂平市', '450803', '广西壮族自治区贵港市港南区' ]);
a_t
		.add('0_12_13', [ '450924', '广西壮族自治区玉林市兴业县', '450981', '广西壮族自治区北流市',
				'450902', '广西壮族自治区玉林市玉州区', '450921', '广西壮族自治区玉林市容县', '450922',
				'广西壮族自治区玉林市陆川县', '450901', '广西壮族自治区玉林市市辖区', '450923',
				'广西壮族自治区玉林市博白县' ]);
a_t.add('0_13', [ '341900', '安徽省池州市', '341000', '安徽省黄山市', '341100', '安徽省滁州市',
		'341200', '安徽省阜阳市', '341600', '安徽省亳州市', '341700', '安徽省池州市', '341800',
		'安徽省宣城市', '340300', '安徽省蚌埠市', '340400', '安徽省淮南市', '340500', '安徽省马鞍山市',
		'340700', '安徽省铜陵市', '340800', '安徽省安庆市', '340100', '安徽省合肥市', '340200',
		'安徽省芜湖市', '340600', '安徽省淮北市', '341300', '安徽省宿州市', '341400', '安徽省巢湖市',
		'341500', '安徽省六安市' ]);
a_t.add('0_13_1', [ '341001', '安徽黄山市辖区', '341002', '安徽黄山屯溪区', '341003',
		'安徽黄山区', '341004', '安徽黄山徽州区', '341021', '安徽黄山歙县', '341022', '安徽黄山休宁县',
		'341023', '安徽黄山黟县', '341024', '安徽黄山祁门县' ]);
a_t.add('0_13_2',
		[ '341101', '安徽滁州市辖区', '341102', '安徽滁州琅琊区', '341103', '安徽滁州南谯区',
				'341122', '安徽滁州来安县', '341124', '安徽滁州全椒县', '341125', '安徽滁州定远县',
				'341126', '安徽滁州凤阳县', '341181', '安徽滁州天长市', '341182', '安徽省明光市' ]);
a_t.add('0_13_3', [ '341201', '安徽省阜阳市市辖区', '341202', '安徽省阜阳市颍州区', '341203',
		'安徽省阜阳市颍东区', '341204', '安徽省阜阳市颍泉区', '341221', '安徽省阜阳市临泉县', '341222',
		'安徽省阜阳市太和县', '341226', '安徽省阜阳市颍上县', '341282', '安徽省界首市', '341225',
		'安徽省阜阳市阜南县' ]);
a_t.add('0_13_4', [ '341602', '安徽省亳州市谯城区', '341621', '安徽省亳州市涡阳县', '341623',
		'安徽省亳州市利辛县', '341601', '安徽省亳州市市辖区', '341622', '安徽省亳州市蒙城县' ]);
a_t.add('0_13_5', [ '341702', '安徽省池州市贵池区', '341721', '安徽省池州市东至县', '341722',
		'安徽省池州市石台县', '341701', '安徽省池州市市辖区', '341723', '安徽省池州市青阳县' ]);
a_t.add('0_13_6', [ '341801', '安徽省宣城市市辖区', '341821', '安徽省宣城市郎溪县', '341822',
		'安徽省宣城市广德县', '341823', '安徽省宣城市泾县', '341825', '安徽省宣城市旌德县', '341881',
		'安徽省宁国市', '341802', '安徽省宣城市宣州区', '341824', '安徽省宣城市绩溪县' ]);
a_t.add('0_13_7', [ '340302', '安徽蚌埠龙子湖区', '340303', '安徽蚌埠蚌山区', '340311',
		'安徽蚌埠淮上区', '340321', '安徽蚌埠怀远县', '340323', '安徽蚌埠固镇县', '340301',
		'安徽蚌埠市辖区', '340304', '安徽蚌埠禹会区', '340322', '安徽蚌埠五河县' ]);
a_t.add('0_13_8', [ '340402', '安徽淮南大通区', '340403', '安徽淮南田家庵区', '340405',
		'安徽淮南八公山区', '340406', '安徽淮南潘集区', '340401', '安徽淮南市辖区', '340404',
		'安徽淮南谢家集区', '340421', '安徽淮南凤台县' ]);
a_t.add('0_13_9', [ '340501', '安徽马鞍山市辖区', '340503', '安徽马鞍山花山区', '340504',
		'安徽马鞍山雨山区', '340521', '安徽马鞍山当涂县', '340502', '安徽马鞍山金家庄区' ]);
a_t.add('0_13_10', [ '340701', '安徽铜陵市辖区', '340703', '安徽铜陵狮子山区', '340711',
		'安徽铜陵郊区', '340702', '安徽铜陵铜官山区', '340721', '安徽铜陵县' ]);
a_t.add('0_13_11', [ '340826', '安徽安庆宿松县', '340827', '安徽安庆望江县', '340828',
		'安徽安庆岳西县', '340881', '安徽省桐城市', '340801', '安徽安庆市辖区', '340803',
		'安徽安庆大观区', '340811', '安徽安庆宜秀区', '340823', '安徽安庆枞阳县', '340824',
		'安徽安庆潜山县', '340802', '安徽安庆迎江区', '340822', '安徽安庆怀宁县', '340825',
		'安徽安庆太湖县' ]);
a_t.add('0_13_12', [ '340101', '安徽合肥市辖区', '340102', '安徽合肥瑶海区', '340104',
		'安徽合肥蜀山区', '340111', '安徽合肥包河区', '340122', '安徽合肥肥东县', '340123',
		'安徽合肥肥西县', '340103', '安徽合肥庐阳区', '340121', '安徽合肥长丰县' ]);
a_t.add('0_13_13', [ '340201', '安徽芜湖市辖区', '340202', '安徽芜湖镜湖区', '340207',
		'安徽芜湖鸠江区', '340221', '安徽芜湖县', '340223', '安徽芜湖南陵县', '340203', '安徽芜湖弋江区',
		'340222', '安徽芜湖繁昌县' ]);
a_t.add('0_13_14', [ '340601', '安徽淮北市辖区', '340603', '安徽淮北相山区', '340604',
		'安徽淮北烈山区', '340602', '安徽淮北杜集区', '340621', '安徽淮北濉溪县' ]);
a_t.add('0_13_15', [ '341301', '安徽省宿州市市辖区', '341302', '安徽省宿州市埇桥区', '341321',
		'安徽省宿州市砀山县', '341323', '安徽省宿州市灵璧县', '341324', '安徽省宿州市泗县', '341322',
		'安徽省宿州市萧县' ]);
a_t.add('0_13_16', [ '341401', '安徽省巢湖市市辖区', '341402', '安徽省巢湖市居巢区', '341422',
		'安徽省巢湖市无为县', '341423', '安徽省巢湖市含山县', '341424', '安徽省巢湖市和县', '341421',
		'安徽省巢湖市庐江县' ]);
a_t.add('0_13_17', [ '341501', '安徽省六安市市辖区', '341502', '安徽省六安市金安区', '341521',
		'安徽省六安市寿县', '341522', '安徽省六安市霍邱县', '341524', '安徽省六安市金寨县', '341525',
		'安徽省六安市霍山县', '341503', '安徽省六安市裕安区', '341523', '安徽省六安市舒城县' ]);
a_t.add('0_14', [ '140100', '山西省太原市', '140200', '山西省大同市', '140400', '山西省长治市',
		'140500', '山西省晋城市', '140800', '山西省运城市', '140900', '山西省忻州市', '141000',
		'山西省临汾市', '141100', '山西省吕梁市', '140600', '山西省朔州市', '140700', '山西省晋中市',
		'140300', '山西省阳泉市' ]);
a_t.add('0_14_0', [ '140101', '山西太原市辖区', '140105', '山西省太原市小店区', '140106',
		'山西省太原市迎泽区', '140107', '山西省太原市杏花岭区', '140108', '山西省太原市尖草坪区', '140109',
		'山西省太原市万柏林区', '140110', '山西省太原市晋源区', '140121', '山西太原清徐县', '140122',
		'山西太原阳曲县', '140123', '山西太原娄烦县', '140181', '山西太原古交市' ]);
a_t.add('0_14_1', [ '140201', '山西大同市辖区', '140202', '山西大同城区', '140203',
		'山西大同矿区', '140211', '山西大同南郊区', '140221', '山西大同阳高县', '140222',
		'山西大同天镇县', '140224', '山西大同灵丘县', '140225', '山西大同浑源县', '140227', '山西大同县',
		'140212', '山西大同新荣区', '140223', '山西大同广灵县', '140226', '山西大同左云县' ]);
a_t.add('0_14_2', [ '140401', '山西长治市辖区', '140411', '山西长治郊区', '140421', '山西长治县',
		'140424', '山西长治屯留县', '140425', '山西长治平顺县', '140427', '山西长治壶关县',
		'140428', '山西长治长子县', '140430', '山西长治沁县', '140431', '山西长治泌源县', '140402',
		'山西长治城区', '140423', '山西长治襄垣县', '140426', '山西长治黎城县', '140429',
		'山西长治武乡县', '140481', '山西省潞城市' ]);
a_t.add('0_14_3', [ '140502', '山西晋城城区', '140521', '山西晋城沁水县', '140524',
		'山西晋城陵川县', '140525', '山西省晋城市泽州县', '140581', '山西晋城高平市', '140501',
		'山西晋城市辖区', '140522', '山西晋城阳城县' ]);
a_t.add('0_14_4', [ '140802', '山西省运城市盐湖区', '140821', '山西省运城市临猗县', '140822',
		'山西省运城市万荣县', '140824', '山西省运城市稷山县', '140825', '山西省运城市新绛县', '140827',
		'山西省运城市垣曲县', '140828', '山西省运城市夏县', '140830', '山西省运城市芮城县', '140881',
		'山西省永济市', '140801', '山西省运城市市辖区', '140823', '山西省运城市闻喜县', '140826',
		'山西省运城市绛县', '140829', '山西省运城市平陆县', '140882', '山西省河津市' ]);
a_t.add('0_14_5', [ '140901', '山西省忻州市市辖区', '140902', '山西省忻州市忻府区', '140921',
		'山西省忻州市定襄县', '140922', '山西省忻州市五台县', '140923', '山西省忻州市代县', '140924',
		'山西省忻州市繁峙县', '140925', '山西省忻州市宁武县', '140926', '山西省忻州市静乐县', '140927',
		'山西省忻州市神池县', '140928', '山西省忻州市五寨县', '140929', '山西省忻州市岢岚县', '140930',
		'山西省忻州市河曲县', '140931', '山西省忻州市保德县', '140932', '山西省忻州市偏关县', '140981',
		'山西省原平市' ]);
a_t.add('0_14_6', [ '141001', '山西省临汾市市辖区', '141002', '山西省临汾市尧都区', '141021',
		'山西省临汾市曲沃县', '141022', '山西省临汾市翼城县', '141023', '山西省临汾市襄汾县', '141024',
		'山西省临汾市洪洞县', '141025', '山西省临汾市古县', '141026', '山西省临汾市安泽县', '141027',
		'山西省临汾市浮山县', '141028', '山西省临汾市吉县', '141029', '山西省临汾市乡宁县', '141030',
		'山西省临汾市大宁县', '141031', '山西省临汾市隰县', '141032', '山西省临汾市永和县', '141033',
		'山西省临汾市蒲县', '141034', '山西省临汾市汾西县', '141081', '山西省侯马市', '141082',
		'山西省霍州市' ]);
a_t.add('0_14_7', [ '141101', '山西省吕梁市市辖区', '141102', '山西省吕梁市离石区', '141122',
		'山西省吕梁市交城县', '141123', '山西省吕梁市兴县', '141125', '山西省吕梁市柳林县', '141126',
		'山西省吕梁市石楼县', '141128', '山西省吕梁市方山县', '141129', '山西省吕梁市中阳县', '141130',
		'山西省吕梁市交口县', '141182', '山西省汾阳市', '141121', '山西省吕梁市文水县', '141124',
		'山西省吕梁市临县', '141127', '山西省吕梁市岚县', '141181', '山西省吕梁市孝义市' ]);
a_t.add('0_14_8', [ '140601', '山西朔州市辖区', '140603', '山西朔州平鲁区', '140621',
		'山西朔州山阴县', '140623', '山西朔州右玉县', '140624', '山西朔州怀仁县', '140602',
		'山西朔州朔城区', '140622', '山西朔州应县' ]);
a_t.add('0_14_9', [ '140701', '山西省晋中市市辖区', '140702', '山西省晋中市榆次区', '140721',
		'山西省晋中市榆社县', '140723', '山西省晋中市和顺县', '140724', '山西省晋中市昔阳县', '140726',
		'山西省晋中市太谷县', '140727', '山西省晋中市祁县', '140728', '山西省晋中市平遥县', '140781',
		'山西省晋中市介休市', '140722', '山西省晋中市左权县', '140725', '山西省晋中市寿阳县', '140729',
		'山西省晋中市灵石县' ]);
a_t
		.add('0_14_10', [ '140301', '山西阳泉市辖区', '140302', '山西阳泉城区', '140311',
				'山西阳泉郊区', '140321', '山西阳泉平定县', '140322', '山西阳泉盂县', '140303',
				'山西阳泉矿区' ]);
a_t.add('0_15', [ '150700', '内蒙古自治区呼伦贝尔市', '150800', '内蒙古自治区巴彦淖尔市', '150900',
		'内蒙古自治区乌兰察布市', '152200', '内蒙古自治区丰镇市兴安盟', '152500', '内蒙古自治区丰镇市锡林郭勒盟',
		'152900', '内蒙古自治区丰镇市阿拉善盟', '150200', '内蒙古自治区包头市', '150300',
		'内蒙古自治区乌海市', '150400', '内蒙古自治区赤峰市', '150500', '内蒙古自治区通辽市', '150600',
		'内蒙古自治区鄂尔多斯市', '150100', '内蒙古自治区呼和浩特市' ]);
a_t.add('0_15_0',
		[ '150701', '内蒙古自治区呼伦贝尔市市辖区', '150702', '内蒙古自治区呼伦贝尔市海拉尔区', '150721',
				'内蒙古自治区呼伦贝尔市阿荣旗', '150722', '内蒙古自治区呼伦贝尔市莫力达瓦达斡尔族自治旗', '150723',
				'内蒙古自治区呼伦贝尔市鄂伦春自治旗', '150724', '内蒙古自治区呼伦贝尔市鄂温克族自治旗', '150725',
				'内蒙古自治区呼伦贝尔市陈巴尔虎旗', '150726', '内蒙古自治区呼伦贝尔市新巴尔虎左旗', '150727',
				'内蒙古自治区呼伦贝尔市新巴尔虎右旗', '150781', '内蒙古自治区满洲里市', '150782',
				'内蒙古自治区牙克石市', '150783', '内蒙古自治区扎兰屯市', '150784', '内蒙古自治区额尔古纳市',
				'150785', '内蒙古自治区根河市' ]);
a_t.add('0_15_1', [ '150801', '内蒙古自治区巴彦淖尔市市辖区', '150802', '内蒙古自治区巴彦淖尔市临河区',
		'150821', '内蒙古自治区巴彦淖尔市五原县', '150822', '内蒙古自治区巴彦淖尔市磴口县', '150823',
		'内蒙古自治区巴彦淖尔市乌拉特前旗', '150824', '内蒙古自治区巴彦淖尔市乌拉特中旗', '150825',
		'内蒙古自治区巴彦淖尔市乌拉特后旗', '150826', '内蒙古自治区巴彦淖尔市杭锦后旗' ]);
a_t.add('0_15_2', [ '150901', '内蒙古自治区乌兰察布市市辖区', '150902', '内蒙古自治区乌兰察布市集宁区',
		'150921', '内蒙古自治区乌兰察布市卓资县', '150922', '内蒙古自治区乌兰察布市化德县', '150923',
		'内蒙古自治区乌兰察布市商都县', '150924', '内蒙古自治区乌兰察布市兴和县', '150925',
		'内蒙古自治区乌兰察布市凉城县', '150926', '内蒙古自治区乌兰察布市察哈尔右翼前旗', '150927',
		'内蒙古自治区乌兰察布市察哈尔右翼中旗', '150928', '内蒙古自治区乌兰察布市察哈尔右翼后旗', '150929',
		'内蒙古自治区乌兰察布市四子王旗', '150981', '内蒙古自治区丰镇市' ]);
a_t.add('0_15_3', [ '152201', '内蒙兴安盟乌兰浩特市', '152202', '内蒙古自治区丰镇市阿尔山市',
		'152221', '内蒙兴安盟科尔沁右翼前旗', '152222', '内蒙兴安盟科尔沁右翼中旗', '152223',
		'内蒙兴安盟扎赉特旗', '152224', '内蒙兴安盟突泉县' ]);
a_t.add('0_15_4', [ '152501', '内蒙锡林郭勒盟二连浩特市', '152502', '内蒙锡林郭勒盟锡林浩特市',
		'152522', '内蒙锡林郭勒盟阿巴嘎旗', '152523', '内蒙锡林郭勒盟苏尼特左旗', '152524',
		'内蒙锡林郭勒盟苏尼特右旗', '152525', '锡林郭勒盟东乌珠穆沁旗', '152526', '锡林郭勒盟西乌珠穆沁旗',
		'152527', '内蒙锡林郭勒盟太仆寺旗', '152528', '内蒙锡林郭勒盟镶黄旗', '152529',
		'内蒙锡林郭勒盟正镶白旗', '152530', '内蒙锡林郭勒盟正蓝旗', '152531', '内蒙锡林郭勒盟多伦县' ]);
a_t.add('0_15_5', [ '152921', '内蒙阿拉善盟阿拉善左旗', '152922', '内蒙阿拉善盟阿拉善右旗', '152923',
		'内蒙阿拉善盟额济纳旗' ]);
a_t.add('0_15_6', [ '150201', '内蒙包头市辖区', '150203', '内蒙包头昆都仑区', '150204',
		'内蒙包头青山区', '150206', '内蒙包头白云矿区', '150207', '内蒙包九原区', '150222',
		'内蒙包头固阳县', '150223', '内蒙古自治区包头市达尔罕茂明安联合旗', '150202', '内蒙包头东河区',
		'150205', '内蒙包头石拐区', '150221', '内蒙包头土默特右旗' ]);
a_t.add('0_15_7', [ '150302', '内蒙乌海海勃湾区', '150303', '内蒙乌海海南区', '150301',
		'内蒙乌海市辖区', '150304', '内蒙乌海乌达区' ]);
a_t.add('0_15_8', [ '150401', '内蒙赤峰市辖区', '150403', '内蒙赤峰元宝山区', '150404',
		'内蒙赤峰松山区', '150421', '内蒙赤峰阿鲁科尔沁旗', '150423', '内蒙赤峰巴林右旗', '150424',
		'内蒙赤峰林西县', '150426', '内蒙赤峰翁牛特旗', '150428', '内蒙赤峰喀喇沁旗', '150430',
		'内蒙赤峰敖汉旗', '150402', '内蒙赤峰红山区', '150422', '内蒙赤峰巴林左旗', '150425',
		'内蒙赤峰克什克腾旗', '150429', '内蒙赤峰宁城县' ]);
a_t.add('0_15_9', [ '150501', '内蒙古自治区通辽市市辖区', '150521', '内蒙古自治区通辽市科尔沁左翼中旗',
		'150522', '内蒙古自治区通辽市科尔沁左翼后旗', '150523', '内蒙古自治区通辽市开鲁县', '150524',
		'内蒙古自治区通辽市库伦旗', '150526', '内蒙古自治区通辽市扎鲁特旗', '150581', '内蒙古自治区霍林郭勒市',
		'150502', '内蒙古自治区通辽市科尔沁区', '150525', '内蒙古自治区通辽市奈曼旗' ]);
a_t.add('0_15_10', [ '150624', '内蒙古自治区鄂尔多斯市鄂托克旗', '150625', '内蒙古自治区鄂尔多斯市杭锦旗',
		'150626', '内蒙古自治区鄂尔多斯市乌审旗', '150627', '内蒙古自治区鄂尔多斯市伊金霍洛旗', '150602',
		'内蒙古自治区鄂尔多斯市东胜区', '150621', '内蒙古自治区鄂尔多斯市达拉特旗', '150623',
		'内蒙古自治区鄂尔多斯市鄂托克前旗', '150622', '内蒙古自治区鄂尔多斯市准格尔旗' ]);
a_t.add('0_15_11', [ '150101', '内蒙呼和浩特市辖区', '150102', '内蒙呼和浩特新城区', '150103',
		'内蒙呼和浩特回民区', '150105', '内蒙呼和浩特赛罕区', '150121', '内蒙呼和浩特土默特左旗', '150123',
		'内蒙古自治区呼和浩特市和林格尔县', '150124', '内蒙古自治区呼和浩特市清水河县', '150125',
		'内蒙古自治区呼和浩特市武川县', '150104', '内蒙呼和浩特玉泉区', '150122', '内蒙呼和浩特托克托县' ]);
a_t.add('0_16', [ '330700', '浙江省金华市', '330800', '浙江省衢州市', '330900', '浙江省舟山市',
		'331000', '浙江省台州市', '331100', '浙江省丽水市', '330100', '浙江省杭州市', '330200',
		'浙江省宁波市', '330300', '浙江省温州市', '330400', '浙江省嘉兴市', '330500', '浙江省湖州市',
		'330600', '浙江省绍兴市' ]);
a_t.add('0_16_0', [ '330701', '浙江金华市辖区', '330702', '浙江金华婺城区', '330703',
		'浙江省金华市金东区', '330723', '浙江金华武义县', '330726', '浙江金华浦江县', '330727',
		'浙江金华磐安县', '330781', '浙江金华兰溪市', '330782', '浙江金华义乌市', '330783',
		'浙江金华东阳市', '330784', '浙江金华永康市' ]);
a_t.add('0_16_1', [ '330801', '浙江衢州市辖区', '330802', '浙江衢州柯城区', '330803',
		'浙江省衢州市衢江区', '330822', '浙江衢州常山县', '330824', '浙江衢州开化县', '330825',
		'浙江衢州龙游县', '330881', '浙江衢州江山市' ]);
a_t.add('0_16_2', [ '330901', '浙江舟山市辖区', '330902', '浙江舟山定海区', '330903',
		'浙江舟山普陀区', '330921', '浙江舟山岱山县', '330922', '浙江舟山嵊泗县' ]);
a_t.add('0_16_3', [ '331001', '浙江省台州市市辖区', '331002', '浙江省台州市椒江区', '331004',
		'浙江省台州市路桥区', '331021', '浙江省台州市玉环县', '331022', '浙江省台州市三门县', '331024',
		'浙江省台州市仙居县', '331081', '浙江省温岭市', '331003', '浙江省台州市黄岩区', '331023',
		'浙江省台州市天台县', '331082', '浙江省临海市' ]);
a_t.add('0_16_4', [ '331101', '浙江省丽水市市辖区', '331121', '浙江省丽水市青田县', '331122',
		'浙江省丽水市缙云县', '331123', '浙江省丽水市遂昌县', '331125', '浙江省丽水市云和县', '331126',
		'浙江省丽水市庆元县', '331127', '浙江省丽水市景宁畲族自治县', '331102', '浙江省丽水市莲都区',
		'331124', '浙江省丽水市松阳县', '331181', '浙江省龙泉市' ]);
a_t.add('0_16_5', [ '330102', '浙江杭州上城区', '330103', '浙江杭州下城区', '330105',
		'浙江杭州拱墅区', '330106', '浙江杭州西湖区', '330109', '浙江省杭州市萧山区', '330110',
		'浙江省杭州市余杭区', '330122', '浙江杭州桐庐县', '330182', '浙江杭州建德市', '330183',
		'浙江省富阳市', '330101', '浙江杭州市辖区', '330104', '浙江杭州江干区', '330108',
		'浙江省杭州市滨江区', '330127', '浙江杭州淳安县', '330185', '浙江省临安市' ]);
a_t.add('0_16_6', [ '330203', '浙江宁波海曙区', '330204', '浙江宁波江东区', '330206',
		'浙江宁波北仑区', '330211', '浙江宁波镇海区', '330212', '浙江省宁波市鄞州区', '330226',
		'浙江宁波宁海县', '330281', '浙江宁波余姚市', '330283', '浙江宁波奉化市', '330201',
		'浙江宁波市辖区', '330205', '浙江宁波江北区', '330225', '浙江宁波象山县', '330282',
		'浙江宁波慈溪市' ]);
a_t.add('0_16_7', [ '330302', '浙江温州鹿城区', '330303', '浙江温州龙湾区', '330322',
		'浙江温州洞头县', '330324', '浙江温州永嘉县', '330327', '浙江温州苍南县', '330328',
		'浙江温州文成县', '330381', '浙江温州瑞安市', '330382', '浙江温州乐清市', '330301',
		'浙江温州市辖区', '330304', '浙江温州瓯海区', '330326', '浙江温州平阳县', '330329',
		'浙江温州泰顺县' ]);
a_t.add('0_16_8', [ '330401', '浙江嘉兴市辖区', '330402', '浙江嘉兴秀城区', '330424',
		'浙江嘉兴海盐县', '330483', '浙江嘉兴桐乡市', '330411', '浙江嘉兴秀洲区', '330421',
		'浙江嘉兴嘉善县', '330481', '浙江嘉兴海宁市', '330482', '浙江嘉兴平湖市' ]);
a_t.add('0_16_9', [ '330503', '浙江省湖州市南浔区', '330523', '浙江湖州安吉县', '330501',
		'浙江湖州市辖区', '330502', '浙江省湖州市吴兴区', '330521', '浙江湖州德清县', '330522',
		'浙江湖州长兴县' ]);
a_t.add('0_16_10', [ '330682', '浙江绍兴上虞市', '330683', '浙江省嵊州市', '330602',
		'浙江绍兴越城区', '330624', '浙江绍兴新昌县', '330601', '浙江绍兴市辖区', '330621', '浙江绍兴县',
		'330681', '浙江绍兴诸暨市' ]);
a_t.add('0_17', [ '350500', '福建省泉州市', '350600', '福建省漳州市', '350700', '福建省南平市',
		'350900', '福建省宁德市', '350100', '福建省福州市', '350300', '福建省莆田市', '350400',
		'福建省三明市', '350200', '福建省厦门市', '350800', '福建省龙岩市' ]);
a_t.add('0_17_0', [ '350501', '福建泉州市辖区', '350502', '福建泉州鲤城区', '350503',
		'福建省泉州市丰泽区', '350504', '福建省泉州市洛江区', '350505', '福建省泉州市泉港区', '350521',
		'福建泉州惠安县', '350524', '福建泉州安溪县', '350525', '福建泉州永春县', '350526',
		'福建泉州德化县', '350527', '福建泉州金门县', '350581', '福建泉州石狮市', '350582',
		'福建泉州晋江市', '350583', '福建泉州南安市' ]);
a_t.add('0_17_1', [ '350601', '福建漳州市辖区', '350602', '福建漳州芗城区', '350603',
		'福建省漳州市龙文区', '350622', '福建漳州云霄县', '350623', '福建漳州漳浦县', '350625',
		'福建漳州长泰县', '350626', '福建漳州东山县', '350628', '福建漳州平和县', '350629',
		'福建漳州华安县', '350624', '福建漳州诏安县', '350627', '福建漳州南靖县', '350681',
		'福建漳州龙海市' ]);
a_t.add('0_17_2', [ '350701', '福建省南平市市辖区', '350721', '福建省南平市顺昌县', '350722',
		'福建省南平市浦城县', '350723', '福建省南平市光泽县', '350725', '福建省南平市政和县', '350781',
		'福建省邵武市', '350783', '福建省建瓯市', '350784', '福建省建阳市', '350702',
		'福建省南平市延平区', '350724', '福建省南平市松溪县', '350782', '福建省武夷山市' ]);
a_t.add('0_17_3', [ '350902', '福建省宁德市蕉城区', '350921', '福建省宁德市霞浦县', '350923',
		'福建省宁德市屏南县', '350924', '福建省宁德市寿宁县', '350925', '福建省宁德市周宁县', '350981',
		'福建省福安市', '350982', '福建省福鼎市', '350901', '福建省宁德市市辖区', '350922',
		'福建省宁德市古田县', '350926', '福建省宁德市柘荣县' ]);
a_t.add('0_17_4', [ '350102', '福建福州鼓楼区', '350103', '福建福州台江区', '350105',
		'福建福州马尾区', '350111', '福建福州晋安区', '350122', '福建福州连江县', '350123',
		'福建福州罗源县', '350125', '福建福州永泰县', '350128', '福建福州平潭县', '350182',
		'福建省长乐市', '350101', '福建福州市辖区', '350104', '福建福州仓山区', '350121',
		'福建福州闽侯县', '350124', '福建福州闽清县', '350181', '福建福州福清市' ]);
a_t.add('0_17_5', [ '350301', '福建莆田市辖区', '350303', '福建莆田涵江区', '350304',
		'福建省莆田市荔城区', '350322', '福建莆田仙游县', '350302', '福建莆田城厢区', '350305',
		'福建省莆田市秀屿区' ]);
a_t.add('0_17_6', [ '350402', '福建三明梅列区', '350403', '福建三明三元区', '350421',
		'福建三明明溪县', '350423', '福建三明清流县', '350424', '福建三明宁化县', '350425',
		'福建三明大田县', '350426', '福建三明尤溪县', '350427', '福建三明沙县', '350428',
		'福建三明将乐县', '350429', '福建三明泰宁县', '350430', '福建三明建宁县', '350481',
		'福建三明永安市', '350401', '福建三明市辖区' ]);
a_t.add('0_17_7', [ '350201', '福建厦门市辖区', '350203', '福建厦门思明区', '350206',
		'福建厦门湖里区', '350211', '福建厦门集美区', '350212', '福建省厦门市同安区', '350205',
		'福建厦门海沧区', '350213', '福建省厦门市翔安区' ]);
a_t.add('0_17_8', [ '350801', '福建省龙岩市市辖区', '350802', '福建省龙岩市新罗区', '350822',
		'福建省龙岩市永定县', '350823', '福建省龙岩市上杭县', '350824', '福建省龙岩市武平县', '350881',
		'福建省漳平市', '350821', '福建省龙岩市长汀县', '350825', '福建省龙岩市连城县' ]);
a_t.add('0_18', [ '360500', '江西省新余市', '360100', '江西省南昌市', '360200', '江西省景德镇市',
		'360300', '江西省萍乡市', '360400', '江西省九江市', '360600', '江西省鹰潭市', '360700',
		'江西省赣州市', '360800', '江西省吉安市', '361000', '江西省抚州市', '361100', '江西省上饶市',
		'360900', '江西省宜春市' ]);
a_t.add('0_18_0', [ '360501', '江西新余市辖区', '360502', '江西新余渝水区', '360521',
		'江西新余分宜县' ]);
a_t.add('0_18_1', [ '360102', '江西南昌东湖区', '360103', '江西南昌西湖区', '360105',
		'江西南昌湾里区', '360111', '江西南昌青山湖区', '360122', '江西南昌新建县', '360123',
		'江西南昌安义县', '360101', '江西南昌市辖区', '360104', '江西南昌青云谱区', '360121',
		'江西南昌县', '360124', '江西南昌进贤县' ]);
a_t.add('0_18_2', [ '360201', '江西景德镇市辖区', '360203', '江西景德镇珠山区', '360222',
		'江西景德镇浮梁县', '360202', '江西景德镇昌江区', '360281', '江西景德镇乐平市' ]);
a_t.add('0_18_3', [ '360301', '江西萍乡市辖区', '360313', '江西萍乡湘东区', '360321',
		'江西萍乡莲花县', '360323', '江西省萍乡市芦溪县', '360302', '江西萍乡安源区', '360322',
		'江西省萍乡市上栗县' ]);
a_t.add('0_18_4', [ '360402', '江西九江庐山区', '360403', '江西九江浔阳区', '360423',
		'江西九江武宁县', '360424', '江西九江修水县', '360426', '江西九江德安县', '360427',
		'江西九江星子县', '360429', '江西九江湖口县', '360430', '江西九江彭泽县', '360401',
		'江西九江市辖区', '360421', '江西九江县', '360425', '江西九江永修县', '360428', '江西九江都昌县',
		'360481', '江西九江瑞昌市' ]);
a_t.add('0_18_5', [ '360601', '江西鹰潭市辖区', '360602', '江西鹰潭月湖区', '360622',
		'江西鹰潭余江县', '360681', '江西省贵溪市' ]);
a_t.add('0_18_6', [ '360701', '江西省赣州市市辖区', '360702', '江西省赣州市章贡区', '360721',
		'江西省赣州市赣县', '360722', '江西省赣州市信丰县', '360723', '江西省赣州市大余县', '360724',
		'江西省赣州市上犹县', '360725', '江西省赣州市崇义县', '360726', '江西省赣州市安远县', '360727',
		'江西省赣州市龙南县', '360728', '江西省赣州市定南县', '360729', '江西省赣州市全南县', '360730',
		'江西省赣州市宁都县', '360731', '江西省赣州市于都县', '360732', '江西省赣州市兴国县', '360733',
		'江西省赣州市会昌县', '360734', '江西省赣州市寻乌县', '360735', '江西省赣州市石城县', '360781',
		'江西省赣州市瑞金市', '360782', '江西省赣州市南康市' ]);
a_t.add('0_18_7', [ '360801', '江西省吉安市市辖区', '360802', '江西省吉安市吉州区', '360803',
		'江西省吉安市青原区', '360821', '江西省吉安市吉安县', '360823', '江西省吉安市峡江县', '360824',
		'江西省吉安市新干县', '360826', '江西省吉安市泰和县', '360827', '江西省吉安市遂川县', '360828',
		'江西省吉安市万安县', '360830', '江西省吉安市永新县', '360881', '江西省井冈山市', '360822',
		'江西省吉安市吉水县', '360825', '江西省吉安市永丰县', '360829', '江西省吉安市安福县' ]);
a_t.add('0_18_8', [ '361002', '江西省抚州市临川区', '361021', '江西省抚州市南城县', '361022',
		'江西省抚州市黎川县', '361024', '江西省抚州市崇仁县', '361025', '江西省抚州市乐安县', '361027',
		'江西省抚州市金溪县', '361028', '江西省抚州市资溪县', '361029', '江西省抚州市东乡县', '361001',
		'江西省抚州市市辖区', '361023', '江西省抚州市南丰县', '361026', '江西省抚州市宜黄县', '361030',
		'江西省抚州市广昌县' ]);
a_t.add('0_18_9', [ '361101', '江西省上饶市市辖区', '361121', '江西省上饶市上饶县', '361122',
		'江西省上饶市广丰县', '361124', '江西省上饶市铅山县', '361125', '江西省上饶市横峰县', '361126',
		'江西省上饶市弋阳县', '361128', '江西省上饶市鄱阳县', '361129', '江西省上饶市万年县', '361181',
		'江西省德兴市', '361102', '江西省上饶市信州区', '361123', '江西省上饶市玉山县', '361127',
		'江西省上饶市余干县', '361130', '江西省上饶市婺源县' ]);
a_t.add('0_18_10', [ '360901', '江西省宜春市市辖区', '360902', '江西省宜春市袁州区', '360922',
		'江西省宜春市万载县', '360923', '江西省宜春市上高县', '360924', '江西省宜春市宜丰县', '360926',
		'江西省宜春市铜鼓县', '360981', '江西省丰城市', '360983', '江西省高安市', '360921',
		'江西省宜春市奉新县', '360925', '江西省宜春市靖安县', '360982', '江西省樟树市' ]);
a_t.add('0_19', [ '430100', '湖南省长沙市', '430600', '湖南省岳阳市', '430200', '湖南省株洲市',
		'430300', '湖南省湘潭市', '430400', '湖南省衡阳市', '430500', '湖南省邵阳市', '430700',
		'湖南省常德市', '430800', '湖南省张家界市', '430900', '湖南省益阳市', '431000', '湖南省郴州市',
		'431100', '湖南省永州市', '431200', '湖南省怀化市', '431300', '湖南省娄底市', '433100',
		'湖南省湘西土家族苗族自治州' ]);
a_t.add('0_19_0', [ '430103', '湖南长沙天心区', '430111', '湖南长沙雨花区', '430124',
		'湖南长沙宁乡县', '430101', '湖南长沙市辖区', '430102', '湖南长沙芙蓉区', '430104',
		'湖南长沙岳麓区', '430105', '湖南长沙开福区', '430121', '湖南长沙县', '430122', '湖南长沙望城县',
		'430181', '湖南长沙浏阳市' ]);
a_t.add('0_19_1', [ '430603', '湖南岳阳云溪区', '430623', '湖南岳阳华容县', '430681',
		'湖南岳阳汨罗市', '430601', '湖南岳阳市辖区', '430602', '湖南岳阳楼区', '430611',
		'湖南岳阳君山区', '430621', '湖南岳阳县', '430624', '湖南岳阳湘阴县', '430626', '湖南岳阳平江县',
		'430682', '湖南岳阳临湘市' ]);
a_t.add('0_19_2', [ '430201', '湖南株洲市辖区', '430204', '湖南株洲石峰区', '430223',
		'湖南株洲攸县', '430281', '湖南株洲醴陵市', '430202', '湖南株洲荷塘区', '430203',
		'湖南株洲芦淞区', '430211', '湖南株洲天元区', '430221', '湖南株洲县', '430224', '湖南株洲茶陵县',
		'430225', '湖南株洲炎陵县' ]);
a_t.add('0_19_3',
		[ '430302', '湖南湘潭雨湖区', '430381', '湖南湘潭湘乡市', '430301', '湖南湘潭市辖区',
				'430304', '湖南湘潭岳塘区', '430321', '湖南湘潭县', '430382', '湖南湘潭韶山市' ]);
a_t.add('0_19_4', [ '430401', '湖南衡阳市辖区', '430407', '湖南省衡阳市石鼓区', '430421',
		'湖南衡阳县', '430424', '湖南衡阳衡东县', '430482', '湖南省常宁市', '430405',
		'湖南省衡阳市珠晖区', '430406', '湖南省衡阳市雁峰区', '430408', '湖南省衡阳市蒸湘区', '430412',
		'湖南衡阳南岳区', '430422', '湖南衡阳衡南县', '430423', '湖南衡阳衡山县', '430426',
		'湖南衡阳祁东县', '430481', '湖南衡阳耒阳市' ]);
a_t.add('0_19_5', [ '430502', '湖南邵阳双清区', '430521', '湖南邵阳邵东县', '430524',
		'湖南邵阳隆回县', '430528', '湖南邵阳新宁县', '430501', '湖南邵阳市辖区', '430503',
		'湖南邵阳大祥区', '430511', '湖南邵阳北塔区', '430522', '湖南邵阳新邵县', '430523', '湖南邵阳县',
		'430525', '湖南邵阳洞口县', '430527', '湖南邵阳绥宁县', '430529', '湖南邵阳城步苗族自治县',
		'430581', '湖南省武冈市' ]);
a_t.add('0_19_6', [ '430701', '湖南常德市辖区', '430721', '湖南常德安乡县', '430723',
		'湖南常德澧县', '430702', '湖南常德武陵区', '430703', '湖南常德鼎城区', '430722',
		'湖南常德汉寿县', '430724', '湖南常德临澧县', '430725', '湖南常德桃源县', '430726',
		'湖南常德石门县', '430781', '湖南常德津市市' ]);
a_t.add('0_19_7', [ '430801', '湖南大庸市辖区', '430802', '湖南大庸永定区', '430811',
		'湖南大庸武陵源区', '430821', '湖南大庸慈利县', '430822', '湖南大庸桑植县' ]);
a_t.add('0_19_8', [ '430901', '湖南省益阳市市辖区', '430902', '湖南省益阳市资阳区', '430903',
		'湖南省益阳市赫山区', '430921', '湖南省益阳市南县', '430922', '湖南省益阳市桃江县', '430923',
		'湖南省益阳市安化县', '430981', '湖南省沅江市' ]);
a_t.add('0_19_9', [ '431001', '湖南省郴州市市辖区', '431002', '湖南省郴州市北湖区', '431003',
		'湖南省郴州市苏仙区', '431021', '湖南省郴州市桂阳县', '431022', '湖南省郴州市宜章县', '431023',
		'湖南省郴州市永兴县', '431024', '湖南省郴州市嘉禾县', '431025', '湖南省郴州市临武县', '431026',
		'湖南省郴州市汝城县', '431027', '湖南省郴州市桂东县', '431028', '湖南省郴州市安仁县', '431081',
		'湖南省资兴市' ]);
a_t.add('0_19_10', [ '431103', '湖南省永州市冷水滩区', '431124', '湖南省永州市道县', '431127',
		'湖南省永州市蓝山县', '431101', '湖南省永州市市辖区', '431102', '湖南省永州市零陵区', '431121',
		'湖南省永州市祁阳县', '431122', '湖南省永州市东安县', '431123', '湖南省永州市双牌县', '431125',
		'湖南省永州市江永县', '431126', '湖南省永州市宁远县', '431128', '湖南省永州市新田县', '431129',
		'湖南省永州市江华瑶族自治县' ]);
a_t.add('0_19_11', [ '431201', '湖南省怀化市市辖区', '431222', '湖南省怀化市沅陵县', '431226',
		'湖南省怀化市麻阳苗族自治县', '431281', '湖南省洪江市', '431202', '湖南省怀化市鹤城区', '431221',
		'湖南省怀化市中方县', '431223', '湖南省怀化市辰溪县', '431224', '湖南省怀化市溆浦县', '431225',
		'湖南省怀化市会同县', '431227', '湖南省怀化市新晃侗族自治县', '431228', '湖南省怀化市芷江侗族自治县',
		'431229', '湖南省怀化市靖州苗族侗族自治县', '431230', '湖南省怀化市通道侗族自治县' ]);
a_t.add('0_19_12', [ '431302', '湖南省娄底市娄星区', '431382', '湖南省涟源市', '431301',
		'湖南省娄底市市辖区', '431321', '湖南省娄底市双峰县', '431322', '湖南省娄底市新化县', '431381',
		'湖南省冷水江市' ]);
a_t.add('0_19_13', [ '433122', '湖南湘西泸溪县', '433125', '湖南湘西保靖县', '433130',
		'湖南湘西龙山县', '433101', '湖南湘西吉首市', '433123', '湖南湘西凤凰县', '433124',
		'湖南湘西花垣县', '433126', '湖南湘西古丈县', '433127', '湖南湘西永顺县' ]);
a_t.add('0_20', [ '542100', '西藏自治区昌都地区', '542300', '西藏自治区日喀则地区', '542600',
		'西藏自治区林芝地区', '540100', '西藏自治区拉萨市', '542200', '西藏自治区山南地区', '542400',
		'西藏自治区那曲地区', '542500', '西藏自治区阿里地区' ]);
a_t.add('0_20_0', [ '542123', '西藏昌都贡觉县', '542126', '西藏昌都察雅县', '542129',
		'西藏昌都芒康县', '542121', '西藏昌都县', '542122', '西藏昌都江达县', '542124',
		'西藏昌都类乌齐县', '542125', '西藏昌都丁青县', '542127', '西藏昌都八宿县', '542128',
		'西藏昌都左贡县', '542132', '西藏昌都洛隆县', '542133', '西藏昌都边坝县' ]);
a_t.add('0_20_1', [ '542327', '西藏日喀则昂仁县', '542328', '西藏日喀则谢通门县', '542329',
		'西藏日喀则白朗县', '542323', '西藏日喀则江孜县', '542326', '西藏日喀则拉孜县', '542301',
		'西藏日喀则日喀则市', '542322', '西藏日喀则南木林县', '542324', '西藏日喀则定日县', '542325',
		'西藏日喀则萨迦县', '542330', '西藏日喀则仁布县', '542331', '西藏日喀则康马县', '542332',
		'西藏日喀则定结县', '542333', '西藏日喀则仲巴县', '542334', '西藏日喀则亚东县', '542335',
		'西藏日喀则吉隆县', '542336', '西藏日喀则聂拉木县', '542337', '西藏日喀则萨嘎县', '542338',
		'西藏日喀则岗巴县' ]);
a_t.add('0_20_2', [ '542625', '西藏林芝波密县', '542621', '西藏林芝县', '542622',
		'西藏林芝工布江达县', '542623', '西藏林芝米林县', '542624', '西藏林芝墨脱县', '542626',
		'西藏林芝察隅县', '542627', '西藏林芝朗县' ]);
a_t.add('0_20_3', [ '540101', '西藏拉萨市辖区', '540122', '西藏拉萨当雄县', '540125',
		'西藏拉萨堆龙德庆县', '540102', '西藏拉萨城关区', '540121', '西藏拉萨林周县', '540123',
		'西藏拉萨尼木县', '540124', '西藏拉萨曲水县', '540126', '西藏拉萨达孜县', '540127',
		'西藏拉萨墨竹工卡县' ]);
a_t.add('0_20_4', [ '542221', '西藏山南乃东县', '542224', '西藏山南桑日县', '542227',
		'西藏山南措美县', '542231', '西藏山南隆子县', '542222', '西藏山南扎襄县', '542223',
		'西藏山南贡嘎县', '542225', '西藏山南琼结县', '542226', '西藏山南曲松县', '542228',
		'西藏山南洛扎县', '542229', '西藏山南加查县', '542232', '西藏山南错那县', '542233',
		'西藏山南浪卡子县' ]);
a_t.add('0_20_5', [ '542421', '西藏那曲县', '542422', '西藏那曲嘉黎县', '542423',
		'西藏那曲比如县', '542424', '西藏那曲聂荣县', '542425', '西藏那曲安多县', '542426',
		'西藏那曲申扎县', '542427', '西藏那曲索县', '542428', '西藏那曲班戈县', '542429',
		'西藏那曲巴青县', '542430', '西藏那曲尼玛县' ]);
a_t.add('0_20_6', [ '542521', '西藏阿里普兰县', '542522', '西藏阿里札达县', '542523',
		'西藏阿里噶尔县', '542524', '西藏阿里日土县', '542525', '西藏阿里革吉县', '542526',
		'西藏阿里改则县', '542527', '西藏阿里措勤县' ]);
a_t.add('0_21', [ '610100', '陕西省西安市西安市', '610200', '陕西省铜川市', '610300',
		'陕西省宝鸡市', '610400', '陕西省咸阳市', '610500', '陕西省渭南市', '610600', '陕西省延安市',
		'610700', '陕西省汉中市', '610800', '陕西省榆林市', '610900', '陕西省安康市', '611000',
		'陕西省商洛市' ]);
a_t.add('0_21_0', [ '610101', '陕西西安市辖区', '610104', '陕西西安莲湖区', '610113',
		'陕西西安雁塔区', '610116', '陕西省西安市长安区', '610125', '陕西西安户县', '610102',
		'陕西西安新城区', '610103', '陕西西安碑林区', '610111', '陕西西安灞桥区', '610112',
		'陕西西安未央区', '610114', '陕西西安阎良区', '610115', '陕西省西安市临潼区', '610122',
		'陕西西安蓝田县', '610124', '陕西西安周至县', '610126', '陕西西安高陵县' ]);
a_t.add('0_21_1', [ '610201', '陕西铜川市辖区', '610204', '陕西省铜川市耀州区', '610202',
		'陕西铜川王益区', '610203', '陕西铜川印台区', '610222', '陕西铜川宜君县' ]);
a_t.add('0_21_2', [ '610301', '陕西宝鸡市辖区', '610304', '陕西省宝鸡市陈仓区', '610324',
		'陕西宝鸡扶风县', '610328', '陕西宝鸡千阳县', '610331', '陕西宝鸡太白县', '610302',
		'陕西宝鸡渭滨区', '610303', '陕西宝鸡金台区', '610322', '陕西宝鸡凤翔县', '610323',
		'陕西宝鸡岐山县', '610326', '陕西宝鸡眉县', '610327', '陕西宝鸡陇县', '610329', '陕西宝鸡麟游县',
		'610330', '陕西宝鸡凤县' ]);
a_t.add('0_21_3', [ '610402', '陕西咸阳秦都区', '610422', '陕西咸阳三原县', '610425',
		'陕西咸阳礼泉县', '610428', '陕西咸阳长武县', '610431', '陕西咸阳武功县', '610401',
		'陕西咸阳市辖区', '610403', '陕西咸阳杨陵区', '610404', '陕西咸阳渭城区', '610423',
		'陕西咸阳泾阳县', '610424', '陕西咸阳乾县', '610426', '陕西咸阳永寿县', '610427', '陕西咸阳彬县',
		'610429', '陕西咸阳旬邑县', '610430', '陕西咸阳淳化县', '610481', '陕西咸阳兴平市' ]);
a_t.add('0_21_4', [ '610501', '陕西省渭南市市辖区', '610522', '陕西省渭南市潼关县', '610525',
		'陕西省渭南市澄城县', '610581', '陕西省韩城市', '610502', '陕西省渭南市临渭区', '610521',
		'陕西省渭南市华县', '610523', '陕西省渭南市大荔县', '610524', '陕西省渭南市合阳县', '610526',
		'陕西省渭南市蒲城县', '610527', '陕西省渭南市白水县', '610528', '陕西省渭南市富平县', '610582',
		'陕西省华阴市' ]);
a_t.add('0_21_5', [ '610601', '陕西省延安市市辖区', '610622', '陕西省延安市延川县', '610626',
		'陕西省延安市吴起县', '610602', '陕西省延安市宝塔区', '610621', '陕西省延安市延长县', '610623',
		'陕西省延安市子长县', '610624', '陕西省延安市安塞县', '610625', '陕西省延安市志丹县', '610627',
		'陕西省延安市甘泉县', '610628', '陕西省延安市富县', '610629', '陕西省延安市洛川县', '610630',
		'陕西省延安市宜川县', '610631', '陕西省延安市黄龙县', '610632', '陕西省延安市黄陵县' ]);
a_t.add('0_21_6', [ '610701', '陕西省汉中市市辖区', '610702', '陕西省汉中市汉台区', '610721',
		'陕西省汉中市南郑县', '610722', '陕西省汉中市城固县', '610723', '陕西省汉中市洋县', '610724',
		'陕西省汉中市西乡县', '610725', '陕西省汉中市勉县', '610726', '陕西省汉中市宁强县', '610727',
		'陕西省汉中市略阳县', '610728', '陕西省汉中市镇巴县', '610729', '陕西省汉中市留坝县', '610730',
		'陕西省汉中市佛坪县' ]);
a_t.add('0_21_7', [ '610801', '陕西省榆林市市辖区', '610802', '陕西省榆林市榆阳区', '610821',
		'陕西省榆林市神木县', '610822', '陕西省榆林市府谷县', '610823', '陕西省榆林市横山县', '610824',
		'陕西省榆林市靖边县', '610825', '陕西省榆林市定边县', '610826', '陕西省榆林市绥德县', '610827',
		'陕西省榆林市米脂县', '610828', '陕西省榆林市佳县', '610829', '陕西省榆林市吴堡县', '610830',
		'陕西省榆林市清涧县', '610831', '陕西省榆林市子洲县' ]);
a_t.add('0_21_8', [ '610901', '陕西省安康市市辖区', '610922', '陕西省安康市石泉县', '610926',
		'陕西省安康市平利县', '610929', '陕西省安康市白河县', '610902', '陕西省安康市汉滨区', '610921',
		'陕西省安康市汉阴县', '610923', '陕西省安康市宁陕县', '610924', '陕西省安康市紫阳县', '610925',
		'陕西省安康市岚皋县', '610927', '陕西省安康市镇坪县', '610928', '陕西省安康市旬阳县' ]);
a_t.add('0_21_9', [ '611021', '陕西省商洛市洛南县', '611024', '陕西省商洛市山阳县', '611001',
		'陕西省商洛市市辖区', '611002', '陕西省商洛市商州区', '611022', '陕西省商洛市丹凤县', '611023',
		'陕西省商洛市商南县', '611025', '陕西省商洛市镇安县', '611026', '陕西省商洛市柞水县' ]);
a_t.add('0_22', [ '620400', '甘肃省白银市', '620600', '甘肃省武威市', '620700', '甘肃省张掖市',
		'620800', '甘肃省平凉市', '623000', '甘肃省甘南藏族自治州', '620900', '甘肃省酒泉市',
		'621000', '甘肃省庆阳市', '621100', '甘肃省定西市', '621200', '甘肃省陇南市', '622900',
		'甘肃省临夏回族自治州', '620100', '甘肃省兰州市', '620200', '甘肃省嘉峪关市', '620300',
		'甘肃省金昌市', '620500', '甘肃省天水市' ]);
a_t.add('0_22_0',
		[ '620403', '甘肃白银平川区', '620423', '甘肃白银景泰县', '620401', '甘肃白银市辖区',
				'620402', '甘肃白银区', '620421', '甘肃白银靖远县', '620422', '甘肃白银会宁县' ]);
a_t.add('0_22_1', [ '620621', '甘肃省武威市民勤县', '620601', '甘肃省武威市市辖区', '620602',
		'甘肃省武威市凉州区', '620622', '甘肃省武威市古浪县', '620623', '甘肃省武威市天祝藏族自治县' ]);
a_t.add('0_22_2', [ '620722', '甘肃省张掖市民乐县', '620701', '甘肃省张掖市市辖区', '620702',
		'甘肃省张掖市甘州区', '620721', '甘肃省张掖市肃南裕固族自治县', '620723', '甘肃省张掖市临泽县',
		'620724', '甘肃省张掖市高台县', '620725', '甘肃省张掖市山丹县' ]);
a_t.add('0_22_3', [ '620821', '甘肃省平凉市泾川县', '620825', '甘肃省平凉市庄浪县', '620826',
		'甘肃省平凉市静宁县', '620801', '甘肃省平凉市市辖区', '620802', '甘肃省平凉市崆峒区', '620822',
		'甘肃省平凉市灵台县', '620823', '甘肃省平凉市崇信县', '620824', '甘肃省平凉市华亭县' ]);
a_t.add('0_22_4', [ '623022', '甘肃甘南卓尼县', '623025', '甘肃甘南玛曲县', '623001',
		'甘肃省合作市', '623021', '甘肃甘南临潭县', '623023', '甘肃甘南舟曲县', '623024',
		'甘肃甘南迭部县', '623026', '甘肃甘南碌曲县', '623027', '甘肃甘南夏河县' ]);
a_t.add('0_22_5', [ '620901', '甘肃省酒泉市市辖区', '620902', '甘肃省酒泉市肃州区', '620921',
		'甘肃省酒泉市金塔县', '620922', '甘肃省酒泉市安西县', '620923', '甘肃省酒泉市肃北蒙古族自治县',
		'620924', '甘肃省酒泉市阿克塞哈萨克族自治县', '620981', '甘肃省玉门市', '620982', '甘肃省敦煌市' ]);
a_t.add('0_22_6', [ '621001', '甘肃省庆阳市市辖区', '621002', '甘肃省庆阳市西峰区', '621021',
		'甘肃省庆阳市庆城县', '621022', '甘肃省庆阳市环县', '621023', '甘肃省庆阳市华池县', '621024',
		'甘肃省庆阳市合水县', '621025', '甘肃省庆阳市正宁县', '621026', '甘肃省庆阳市宁县', '621027',
		'甘肃省庆阳市镇原县' ]);
a_t.add('0_22_7', [ '621101', '甘肃省定西市市辖区', '621102', '甘肃省定西市安定区', '621121',
		'甘肃省定西市通渭县', '621122', '甘肃省定西市陇西县', '621123', '甘肃省定西市渭源县', '621124',
		'甘肃省定西市临洮县', '621125', '甘肃省定西市漳县', '621126', '甘肃省定西市岷县' ]);
a_t.add('0_22_8', [ '621224', '甘肃省陇南市康县', '621228', '甘肃省陇南市两当县', '621201',
		'甘肃省陇南市市辖区', '621202', '甘肃省陇南市武都区', '621221', '甘肃省陇南市成县', '621222',
		'甘肃省陇南市文县', '621223', '甘肃省陇南市宕昌县', '621225', '甘肃省陇南市西和县', '621226',
		'甘肃省陇南市礼县', '621227', '甘肃省陇南市徽县' ]);
a_t.add('0_22_9', [ '622921', '甘肃临夏县', '622924', '甘肃临夏广河县', '622901', '甘肃临夏市',
		'622922', '甘肃临夏康乐县', '622923', '甘肃临夏永靖县', '622925', '甘肃临夏和政县',
		'622926', '甘肃临夏东乡族自治县', '622927', '甘肃临夏积石山保安族东乡族撒拉族自治县' ]);
a_t.add('0_22_10', [ '620101', '甘肃兰州市辖区', '620105', '甘肃兰州安宁区', '620122',
		'甘肃兰州皋兰县', '620102', '甘肃兰州城关区', '620103', '甘肃兰州七里河区', '620104',
		'甘肃兰州西固区', '620111', '甘肃兰州红古区', '620121', '甘肃兰州永登县', '620123',
		'甘肃兰州榆中县' ]);
a_t.add('0_22_11', [ '620201', '甘肃嘉峪关市辖区' ]);
a_t.add('0_22_12', [ '620302', '甘肃金昌金川区', '620301', '甘肃金昌市辖区', '620321',
		'甘肃金昌永昌县' ]);
a_t.add('0_22_13', [ '620502', '甘肃天水秦城区', '620522', '甘肃天水秦安县', '620501',
		'甘肃天水市辖区', '620503', '甘肃天水北道区', '620521', '甘肃天水清水县', '620523',
		'甘肃天水甘谷县', '620524', '甘肃天水武山县', '620525', '甘肃天水张家川回族自治县' ]);
a_t.add('0_23', [ '630100', '青海省西宁市', '632100', '青海省海东地区', '632200',
		'青海省海北藏族自治州', '632300', '青海省黄南藏族自治州', '632500', '青海省海南藏族自治州', '632600',
		'青海省果洛藏族自治州', '632700', '青海省玉树藏族自治州', '632800', '青海省海西蒙古族藏族自治州' ]);
a_t.add('0_23_0', [ '630101', '青海西宁市辖区', '630104', '青海西宁城西区', '630122',
		'青海省西宁市湟中县', '630102', '青海西宁城东区', '630103', '青海西宁城中区', '630105',
		'青海西宁城北区', '630121', '青海西宁大通回族土族自治县', '630123', '青海省西宁市湟源县' ]);
a_t.add('0_23_1', [ '632122', '青海海东民和回族土族自治县', '632128', '青海海东循化撒拉族自治县',
		'632121', '青海海东平安县', '632123', '青海海东乐都县', '632126', '青海海东互助土族自治县',
		'632127', '青海海东化隆回族自治县' ]);
a_t.add('0_23_2', [ '632222', '青海海北祁连县', '632221', '青海海北门源回族自治县', '632223',
		'青海海北海晏县', '632224', '青海海北刚察县' ]);
a_t.add('0_23_3', [ '632321', '青海黄南同仁县', '632324', '青海黄南河南蒙古族自治县', '632322',
		'青海黄南尖扎县', '632323', '青海黄南泽库县' ]);
a_t.add('0_23_4', [ '632522', '青海海南同德县', '632525', '青海海南贵南县', '632521',
		'青海海南共和县', '632523', '青海海南贵德县', '632524', '青海海南兴海县' ]);
a_t.add('0_23_5', [ '632623', '青海果洛甘德县', '632626', '青海果洛玛多县', '632621',
		'青海果洛玛沁县', '632622', '青海果洛班玛县', '632624', '青海果洛达日县', '632625',
		'青海果洛久治县' ]);
a_t
		.add('0_23_6', [ '632722', '青海玉树杂多县', '632725', '青海玉树囊谦县', '632721',
				'青海玉树县', '632723', '青海玉树称多县', '632724', '青海玉树治多县', '632726',
				'青海玉树曲麻莱县' ]);
a_t.add('0_23_7', [ '632801', '青海海西格尔木市', '632802', '青海海西德令哈市', '632821',
		'青海海西乌兰县', '632822', '青海海西都兰县', '632823', '青海海西天峻县' ]);
a_t.add('0_24', [ '420200', '湖北省黄石市', '420300', '湖北省十堰市', '420500', '湖北省宜昌市',
		'420600', '湖北省襄樊市', '421200', '湖北省咸宁市', '421300', '湖北省随州市', '422800',
		'湖北省恩施土家族苗族自治州', '429000', '湖北省省直辖行政单位', '420100', '湖北省武汉市', '421000',
		'湖北省荆州市', '421100', '湖北省黄冈市', '420700', '湖北省鄂州市', '420800', '湖北省荆门市',
		'420900', '湖北省孝感市' ]);
a_t.add('0_24_0', [ '420201', '湖北黄石市辖区', '420202', '湖北黄石港区', '420203',
		'湖北黄石西塞山区', '420205', '湖北黄石铁山区', '420222', '湖北省黄石市阳新县', '420204',
		'湖北黄石下陆区', '420281', '湖北省大冶市' ]);
a_t.add('0_24_1', [ '420301', '湖北十堰市辖区', '420303', '湖北十堰张湾区', '420321',
		'湖北省十堰市郧县', '420323', '湖北省十堰市竹山县', '420324', '湖北省十堰市竹溪县', '420381',
		'湖北省丹江口市', '420302', '湖北十堰茅箭区', '420322', '湖北省十堰市郧西县', '420325',
		'湖北省十堰市房县' ]);
a_t.add('0_24_2', [ '420502', '湖北宜昌西陵区', '420503', '湖北宜昌伍家岗区', '420505',
		'湖北省宜昌市猇亭区', '420506', '湖北省宜昌市夷陵区', '420525', '湖北宜昌远安县', '420501',
		'湖北宜昌市辖区', '420504', '湖北宜昌点军区', '420526', '湖北宜昌兴山县', '420529',
		'湖北宜昌五峰土家族自治县', '420527', '湖北宜昌秭归县', '420528', '湖北宜昌长阳土家族自治县',
		'420581', '湖北宜昌宜都市', '420582', '湖北宜昌当阳市', '420583', '湖北省宜昌市枝江市' ]);
a_t.add('0_24_3', [ '420606', '湖北省襄樊市樊城区', '420625', '湖北襄樊谷城县', '420683',
		'湖北襄樊枣阳市', '420601', '湖北襄樊市辖区', '420602', '湖北襄樊襄城区', '420607',
		'湖北省襄樊市襄阳区', '420624', '湖北襄樊南漳县', '420626', '湖北襄樊保康县', '420682',
		'湖北襄樊老河口市', '420684', '湖北省宜城市' ]);
a_t.add('0_24_4', [ '421201', '湖北省咸宁市市辖区', '421202', '湖北省咸宁市咸安区', '421221',
		'湖北省咸宁市嘉鱼县', '421222', '湖北省咸宁市通城县', '421223', '湖北省咸宁市崇阳县', '421224',
		'湖北省咸宁市通山县', '421281', '湖北省赤壁市' ]);
a_t.add('0_24_5', [ '421301', '湖北省随州市市辖区', '421302', '湖北省随州市曾都区', '421381',
		'湖北省广水市' ]);
a_t.add('0_24_6', [ '422801', '湖北恩施市', '422802', '湖北恩施利川市', '422822',
		'湖北恩施建始县', '422823', '湖北恩施巴东县', '422825', '湖北恩施宣恩县', '422826',
		'湖北恩施咸丰县', '422827', '湖北恩施来凤县', '422828', '湖北恩施鹤峰县' ]);
a_t.add('0_24_7', [ '429004', '湖北省仙桃市', '429005', '湖北省潜江市', '429006', '湖北省天门市',
		'429021', '湖北省神农架林区' ]);
a_t.add('0_24_8', [ '420104', '湖北武汉硚口区', '420105', '湖北武汉汉阳区', '420106',
		'湖北武汉武昌区', '420107', '湖北武汉青山区', '420111', '湖北武汉洪山区', '420112',
		'湖北武汉东西湖区', '420113', '湖北武汉汉南区', '420114', '湖北武汉蔡甸区', '420115',
		'湖北省武汉市江夏区', '420116', '湖北省武汉市黄陂区', '420117', '湖北省武汉市新洲区', '420101',
		'湖北武汉市辖区', '420102', '湖北武汉江岸区', '420103', '湖北武汉江汉区' ]);
a_t.add('0_24_9', [ '421001', '湖北省荆州市市辖区', '421023', '湖北省荆州市监利县', '421083',
		'湖北省洪湖市', '421002', '湖北省荆州市沙市区', '421003', '湖北省荆州市荆州区', '421022',
		'湖北省荆州市公安县', '421024', '湖北省荆州市江陵县', '421081', '湖北省石首市', '421087',
		'湖北省松滋市' ]);
a_t.add('0_24_10', [ '421101', '湖北省黄冈市市辖区', '421122', '湖北省黄冈市红安县', '421123',
		'湖北省黄冈市罗田县', '421124', '湖北省黄冈市英山县', '421125', '湖北省黄冈市浠水县', '421126',
		'湖北省黄冈市蕲春县', '421127', '湖北省黄冈市黄梅县', '421181', '湖北省麻城市', '421182',
		'湖北省武穴市', '421102', '湖北省黄冈市黄州区', '421121', '湖北省黄冈市团风县' ]);
a_t.add('0_24_11', [ '420701', '湖北鄂州市辖区', '420704', '湖北鄂州鄂城区', '420702',
		'湖北鄂州梁子湖区', '420703', '湖北鄂州华容区' ]);
a_t.add('0_24_12', [ '420802', '湖北荆门东宝区', '420822', '湖北省荆门市沙洋县', '420801',
		'湖北荆门市辖区', '420804', '湖北省荆门市掇刀区', '420821', '湖北省荆门市京山县', '420881',
		'湖北省钟祥市' ]);
a_t.add('0_24_13', [ '420901', '湖北孝感市辖区', '420922', '湖北孝感大悟县', '420982',
		'湖北孝感安陆市', '420902', '湖北孝感孝南区', '420921', '湖北孝感孝昌县', '420923',
		'湖北孝感云梦县', '420981', '湖北孝感应城市', '420984', '湖北省汉川市' ]);
a_t.add('0_25', [ '710100', '台湾省台北市', '710200', '台湾省高雄市', '710300', '台湾省基隆市',
		'710400', '台湾省台中市', '710500', '台湾省台南市', '710600', '台湾省新竹市', '710700',
		'台湾省嘉义市', '710800', '台湾省台北县(板桥市)　', '710900', '台湾省宜兰县(宜兰市)', '711000',
		'台湾省新竹县(竹北市)', '711100', '台湾省桃园县(桃园市)', '711200', '台湾省苗栗县(苗栗市)',
		'711300', '台湾省台中县(丰原市)', '711400', '台湾省彰化县(彰化市)', '711500',
		'台湾省南投县(南投市)', '711600', '台湾省嘉义县(太保市)', '711700', '台湾省云林县(斗六市)',
		'711800', '台湾省台南县(新营市)', '711900', '台湾省高雄县(凤山市)', '712000',
		'台湾省屏东县(屏东市)', '712100', '台湾省台东县(台东市)　', '712200', '台湾省花莲县(花莲市)',
		'712300', '台湾省澎湖县(马公市)' ]);
a_t.add('0_25_0', [ '710101', '台湾台北中正区　', '710102', '台湾台北大同区', '710103',
		'台湾台北中山区', '710104', '台湾台北松山区', '710105', '台湾台北大安区', '710106',
		'台湾台北万华区', '710107', '台湾台北信义区', '710108', '台湾台北士林区', '710109',
		'台湾台北北投区', '710110', '台湾台北内湖区', '710111', '台湾台北南港区', '710112',
		'台湾台北文山区' ]);
a_t.add('0_25_1', [ '710201', '台湾高雄新兴区', '710202', '台湾高雄前金区', '710203',
		'台湾高雄芩雅区', '710204', '台湾高雄盐埕区', '710205', '台湾高雄鼓山区', '710206',
		'台湾高雄旗津区', '710207', '台湾高雄前镇区', '710208', '台湾高雄三民区', '710209',
		'台湾高雄左营区', '710210', '台湾高雄楠梓区', '710211', '台湾高雄小港区' ]);
a_t.add('0_25_2', [ '710301', '台湾基隆仁爱区', '710302', '台湾基隆信义区', '710303',
		'台湾基隆中正区', '710304', '台湾基隆中山区', '710305', '台湾基隆安乐区', '710306',
		'台湾基隆暖暖区', '710307', '台湾基隆七堵区' ]);
a_t.add('0_25_3', [ '710401', '台湾台中中区', '710402', '台湾台中东区', '710403', '台湾台中南区',
		'710404', '台湾台中西区', '710405', '台湾台中北区', '710406', '台湾台中北屯区', '710407',
		'台湾台中西屯区', '710408', '台湾台中南屯区' ]);
a_t.add('0_25_4',
		[ '710501', '台湾台南中西区', '710502', '台湾台南东区', '710503', '台湾台南南区',
				'710504', '台湾台南北区', '710505', '台湾台南安平区', '710506', '台湾台南安南区' ]);
a_t.add('0_25_5',
		[ '710601', '台湾新竹东区', '710602', '台湾新竹北区', '710603', '台湾新竹香山区' ]);
a_t.add('0_25_6', [ '710701', '台湾嘉义东区', '710702', '台湾嘉义西区' ]);
a_t.add('0_25_7', [ '710801', '台湾省台北县(板桥市)　' ]);
a_t.add('0_25_8', [ '710901', '台湾省宜兰县(宜兰市)' ]);
a_t.add('0_25_9', [ '711001', '台湾省新竹县(竹北市)' ]);
a_t.add('0_25_10', [ '711101', '台湾省桃园县(桃园市)' ]);
a_t.add('0_25_11', [ '711201', '台湾省苗栗县(苗栗市)' ]);
a_t.add('0_25_12', [ '711301', '台湾省台中县(丰原市)' ]);
a_t.add('0_25_13', [ '711401', '台湾省彰化县(彰化市)' ]);
a_t.add('0_25_14', [ '711501', '台湾省南投县(南投市)' ]);
a_t.add('0_25_15', [ '711601', '台湾省嘉义县(太保市)' ]);
a_t.add('0_25_16', [ '711701', '台湾省云林县(斗六市)' ]);
a_t.add('0_25_17', [ '711801', '台湾省台南县(新营市)' ]);
a_t.add('0_25_18', [ '711901', '台湾省高雄县(凤山市)' ]);
a_t.add('0_25_19', [ '712001', '台湾省屏东县(屏东市)' ]);
a_t.add('0_25_20', [ '712101', '台湾省台东县(台东市)　' ]);
a_t.add('0_25_21', [ '712201', '台湾省花莲县(花莲市)' ]);
a_t.add('0_25_22', [ '712301', '台湾省澎湖县(马公市)' ]);
a_t.add('0_27', [ '640100', '宁夏回族自治区银川市', '640200', '宁夏回族自治区石嘴山市', '640300',
		'宁夏回族自治区吴忠市', '640400', '宁夏回族自治区固原市', '640500', '宁夏回族自治区中卫市' ]);
a_t.add('0_27_0', [ '640101', '宁夏银川市辖区', '640104', '宁夏回族自治区银川市兴庆区', '640105',
		'宁夏回族自治区银川市西夏区', '640106', '宁夏回族自治区银川市金凤区', '640121', '宁夏银川永宁县',
		'640122', '宁夏银川贺兰县', '640181', '宁夏回族自治区灵武市' ]);
a_t.add('0_27_1', [ '640201', '宁夏石嘴山市辖区', '640202', '宁夏石嘴山大武口区', '640205',
		'宁夏回族自治区石嘴山市惠农区', '640221', '宁夏石嘴山平罗县' ]);
a_t.add('0_27_2', [ '640301', '宁夏回族自治区吴忠市市辖区', '640302', '宁夏回族自治区吴忠市利通区',
		'640323', '宁夏回族自治区吴忠市盐池县', '640324', '宁夏回族自治区吴忠市同心县', '640381',
		'宁夏回族自治区青铜峡市' ]);
a_t.add('0_27_3', [ '640401', '宁夏回族自治区固原市市辖区', '640402', '宁夏回族自治区固原市原州区',
		'640422', '宁夏回族自治区固原市西吉县', '640423', '宁夏回族自治区固原市隆德县', '640424',
		'宁夏回族自治区固原市泾源县', '640425', '宁夏回族自治区固原市彭阳县' ]);
a_t.add('0_27_4', [ '640501', '宁夏回族自治区中卫市市辖区', '640502', '宁夏回族自治区中卫市沙坡头区',
		'640521', '宁夏回族自治区中卫市中宁县', '640522', '宁夏回族自治区中卫市海原县' ]);
a_t
		.add('0_28', [ '410100', '河南省郑州市', '410500', '河南省安阳市', '410900',
				'河南省濮阳市', '411300', '河南省南阳市', '410600', '河南省鹤壁市', '411700',
				'河南省驻马店市', '410200', '河南省开封市', '410300', '河南省洛阳市', '410400',
				'河南省平顶山市', '410700', '河南省新乡市', '410800', '河南省焦作市', '411000',
				'河南省许昌市', '411100', '河南省漯河市', '411200', '河南省三门峡市', '411400',
				'河南省商丘市', '411500', '河南省信阳市', '411600', '河南省周口市' ]);
a_t.add('0_28_0', [ '410103', '河南郑州二七区', '410106', '河南郑州上街区', '410181',
		'河南郑州巩义市', '410184', '河南省新郑市', '410101', '河南郑州市辖区', '410102',
		'河南郑州中原区', '410104', '河南郑州管城回族区', '410105', '河南郑州金水区', '410108',
		'河南郑州惠济区', '410122', '河南郑州中牟县', '410182', '河南省荥阳市', '410183', '河南省新密市',
		'410185', '河南省登封市' ]);
a_t.add('0_28_1', [ '410503', '河南安阳北关区', '410522', '河南安阳县', '410527',
		'河南安阳内黄县', '410502', '河南安阳文峰区', '410505', '河南省安阳市殷都区', '410506',
		'河南省安阳市龙安区', '410523', '河南安阳汤阴县', '410526', '河南安阳滑县', '410581',
		'河南省林州市', '410501', '河南安阳市辖区' ]);
a_t.add('0_28_2', [ '410922', '河南濮阳清丰县', '410927', '河南濮阳台前县', '410901',
		'河南濮阳市辖区', '410902', '河南濮阳华龙区', '410923', '河南濮阳南乐县', '410926',
		'河南濮阳范县', '410928', '河南濮阳县' ]);
a_t.add('0_28_3', [ '411303', '河南省南阳市卧龙区', '411324', '河南省南阳市镇平县', '411327',
		'河南省南阳市社旗县', '411381', '河南省邓州市', '411301', '河南省南阳市市辖区', '411302',
		'河南省南阳市宛城区', '411321', '河南省南阳市南召县', '411322', '河南省南阳市方城县', '411323',
		'河南省南阳市西峡县', '411325', '河南省南阳市内乡县', '411326', '河南省南阳市淅川县', '411328',
		'河南省南阳市唐河县', '411329', '河南省南阳市新野县', '411330', '河南省南阳市桐柏县' ]);
a_t.add('0_28_4',
		[ '410601', '河南鹤壁市辖区', '410602', '河南鹤壁鹤山区', '410603', '河南鹤壁山城区',
				'410611', '河南鹤壁淇滨区', '410621', '河南鹤壁浚县', '410622', '河南鹤壁淇县' ]);
a_t.add('0_28_5', [ '411701', '河南省驻马店市市辖区', '411702', '河南省驻马店市驿城区', '411721',
		'河南省驻马店市西平县', '411722', '河南省驻马店市上蔡县', '411723', '河南省驻马店市平舆县', '411724',
		'河南省驻马店市正阳县', '411725', '河南省驻马店市确山县', '411726', '河南省驻马店市泌阳县', '411727',
		'河南省驻马店市汝南县', '411728', '河南省驻马店市遂平县', '411729', '河南省驻马店市新蔡县' ]);
a_t.add('0_28_6', [ '410201', '河南开封市辖区', '410204', '河南开封鼓楼区', '410221',
		'河南开封杞县', '410224', '河南开封县', '410202', '河南开封龙亭区', '410203',
		'河南开封顺河回族区', '410205', '河南开封禹王台区', '410211', '河南开封金明区', '410222',
		'河南开封通许县', '410223', '河南开封尉氏县', '410225', '河南开封兰考县' ]);
a_t.add('0_28_7', [ '410301', '河南洛阳市辖区', '410304', '河南省洛阳市瀍河回族区', '410307',
		'河南省洛阳市洛龙区', '410324', '河南洛阳栾川县', '410327', '河南洛阳宜阳县', '410381',
		'河南洛阳偃师市', '410302', '河南洛阳老城区', '410303', '河南洛阳西工区', '410305',
		'河南洛阳涧西区', '410306', '河南洛阳吉利区', '410322', '河南洛阳孟津县', '410323',
		'河南洛阳新安县', '410325', '河南洛阳嵩县', '410326', '河南洛阳汝阳县', '410328',
		'河南洛阳洛宁县', '410329', '河南洛阳伊川县' ]);
a_t.add('0_28_8', [ '410402', '河南平顶山新华区', '410421', '河南平顶山宝丰县', '410425',
		'河南平顶山郏县', '410401', '河南平顶山市辖区', '410403', '河南平顶山卫东区', '410404',
		'河南省平顶山市石龙区', '410411', '河南平顶山湛河区', '410422', '河南平顶山叶县', '410423',
		'河南平顶山鲁山县', '410481', '河南平顶山舞钢市', '410482', '河南平顶山汝州市' ]);
a_t.add('0_28_9', [ '410701', '河南新乡市辖区', '410702', '河南新乡红旗区', '410703',
		'河南新乡卫滨区', '410704', '河南新乡凤泉区', '410711', '河南新乡牧野区', '410721', '河南新乡县',
		'410724', '河南新乡获嘉县', '410725', '河南新乡原阳县', '410726', '河南新乡延津县',
		'410727', '河南新乡封丘县', '410728', '河南新乡长垣县', '410781', '河南新乡卫辉市',
		'410782', '河南新乡辉县市' ]);
a_t.add('0_28_10',
		[ '410801', '河南焦作市辖区', '410802', '河南焦作解放区', '410803', '河南焦作中站区',
				'410804', '河南焦作马村区', '410811', '河南焦作山阳区', '410821', '河南焦作修武县',
				'410822', '河南焦作博爱县', '410823', '河南焦作武陟县', '410825', '河南焦作温县',
				'410881', '河南焦作济源市', '410882', '河南焦作沁阳市', '410883', '河南省孟州市' ]);
a_t.add('0_28_11', [ '411001', '河南许昌市辖区', '411024', '河南许昌鄢陵县', '411082',
		'河南许昌长葛市', '411002', '河南许昌魏都区', '411023', '河南许昌县', '411025',
		'河南省许昌市襄城县', '411081', '河南许昌禹州市' ]);
a_t.add('0_28_12', [ '411102', '河南漯河源汇区', '411121', '河南漯河舞阳县', '411101',
		'河南漯河市辖区', '411103', '河南省漯河市郾城区', '411104', '河南省漯河市召陵区', '411122',
		'河南漯河临颖县' ]);
a_t.add('0_28_13', [ '411201', '河南三门峡市辖区', '411224', '河南三门峡卢氏县', '411202',
		'河南三门峡湖滨区', '411221', '河南三门峡渑池县', '411222', '河南三门峡陕县', '411281',
		'河南三门峡义马市', '411282', '河南三门峡灵宝市' ]);
a_t.add('0_28_14', [ '411402', '河南省商丘市梁园区', '411422', '河南省商丘市睢县', '411426',
		'河南省商丘市夏邑县', '411401', '河南省商丘市市辖区', '411403', '河南省商丘市睢阳区', '411421',
		'河南省商丘市民权县', '411423', '河南省商丘市宁陵县', '411424', '河南省商丘市柘城县', '411425',
		'河南省商丘市虞城县', '411481', '河南省永城市' ]);
a_t.add('0_28_15', [ '411501', '河南省信阳市市辖区', '411521', '河南省信阳市罗山县', '411525',
		'河南省信阳市固始县', '411528', '河南省信阳市息县', '411502', '河南省信阳市浉河区', '411503',
		'河南省信阳市平桥区', '411522', '河南省信阳市光山县', '411523', '河南省信阳市新县', '411524',
		'河南省信阳市商城县', '411526', '河南省信阳市潢川县', '411527', '河南省信阳市淮滨县' ]);
a_t.add('0_28_16', [ '411602', '河南省周口市川汇区', '411624', '河南省周口市沈丘县', '411627',
		'河南省周口市太康县', '411628', '河南省周口市鹿邑县', '411681', '河南省项城市', '411601',
		'河南省周口市市辖区', '411621', '河南省周口市扶沟县', '411622', '河南省周口市西华县', '411623',
		'河南省周口市商水县', '411625', '河南省周口市郸城县', '411626', '河南省周口市淮阳县' ]);
a_t.add('0_29', [ '310100', '上海市市辖区', '310200', '上海市县' ]);
a_t.add('0_29_0', [ '310101', '上海市黄浦区', '310103', '上海市卢湾区', '310104', '上海市徐汇区',
		'310105', '上海市长宁区', '310106', '上海市静安区', '310107', '上海市普陀区', '310108',
		'上海市闸北区', '310109', '上海市虹口区', '310110', '上海市杨浦区', '310112', '上海市闵行区',
		'310113', '上海市宝山区', '310114', '上海市嘉定区', '310115', '上海市浦东新区', '310116',
		'上海市金山区', '310117', '上海市松江区', '310118', '上海市青浦区', '310119', '上海市南汇区',
		'310120', '上海市奉贤区' ]);
a_t.add('0_29_1', [ '310230', '上海市崇明县' ]);
a_t.add('0_30', [ '320700', '江苏省连云港市', '320800', '江苏省淮安市', '320900', '江苏省盐城市',
		'321000', '江苏省扬州市', '321100', '江苏省镇江市', '321300', '江苏省宿迁市', '321200',
		'江苏省泰州市', '320100', '江苏省南京市', '320200', '江苏省无锡市', '320300', '江苏省徐州市',
		'320400', '江苏省常州市', '320500', '江苏省苏州市', '320600', '江苏省南通市' ]);
a_t.add('0_30_0', [ '320701', '江苏连云港市辖区', '320705', '江苏连云港新浦区', '320706',
		'江苏连云港海州区', '320722', '江苏连云港东海县', '320723', '江苏连云港灌云县', '320724',
		'江苏省连云港市灌南县', '320703', '江苏连云港连云区', '320721', '江苏连云港赣榆县' ]);
a_t.add('0_30_1', [ '320801', '江苏淮安市辖区', '320803', '江苏省淮安市楚州区', '320804',
		'江苏省淮安市淮阴区', '320811', '江苏淮安清浦区', '320826', '江苏淮安涟水县', '320829',
		'江苏淮安洪泽县', '320830', '江苏淮安盱眙县', '320831', '江苏淮安金湖县', '320802',
		'江苏淮安清河区' ]);
a_t.add('0_30_2', [ '320901', '江苏盐城市辖区', '320902', '江苏盐城亭湖区', '320903',
		'江苏省盐城市盐都区', '320921', '江苏盐城响水县', '320922', '江苏盐城滨海县', '320923',
		'江苏盐城阜宁县', '320924', '江苏盐城射阳县', '320925', '江苏盐城建湖县', '320981',
		'江苏盐城东台市', '320982', '江苏省大丰市' ]);
a_t.add('0_30_3', [ '321001', '江苏扬州市辖区', '321002', '江苏扬州广陵区', '321003',
		'江苏省扬州市邗江区', '321011', '江苏扬州维扬区', '321023', '江苏扬州宝应县', '321081',
		'江苏扬州仪征市', '321084', '江苏扬州高邮市', '321088', '江苏省江都市' ]);
a_t.add('0_30_4', [ '321101', '江苏镇江市辖区', '321102', '江苏镇江京口区', '321111',
		'江苏镇江润州区', '321112', '江苏省镇江市丹徒区', '321181', '江苏镇江丹阳市', '321182',
		'江苏省扬中市', '321183', '江苏省句容市' ]);
a_t.add('0_30_5', [ '321302', '江苏省宿迁市宿城区', '321311', '江苏省宿迁市宿豫区', '321323',
		'江苏省宿迁市泗阳县', '321324', '江苏省宿迁市泗洪县', '321301', '江苏省宿迁市市辖区', '321322',
		'江苏省宿迁市沭阳县' ]);
a_t.add('0_30_6', [ '321201', '江苏省泰州市市辖区', '321202', '江苏省泰州市海陵区', '321281',
		'江苏省兴化市', '321282', '江苏省靖江市', '321284', '江苏省姜堰市', '321203',
		'江苏省泰州市高港区', '321283', '江苏省泰兴市' ]);
a_t.add('0_30_7', [ '320101', '江苏南京市辖区', '320103', '江苏南京白下区', '320104',
		'江苏南京秦淮区', '320106', '江苏南京鼓楼区', '320107', '江苏南京下关区', '320113',
		'江苏南京栖霞区', '320114', '江苏南京雨花台区', '320116', '江苏省南京市六合区', '320124',
		'江苏南京溧水县', '320102', '江苏南京玄武区', '320105', '江苏南京建邺区', '320111',
		'江苏南京浦口区', '320115', '江苏省南京市江宁区', '320125', '江苏南京高淳县' ]);
a_t.add('0_30_8', [ '320201', '江苏无锡市辖区', '320203', '江苏无锡南长区', '320204',
		'江苏无锡北塘区', '320206', '江苏省无锡市惠山区', '320211', '江苏无锡滨湖区', '320282',
		'江苏无锡宜兴市', '320202', '江苏无锡崇安区', '320205', '江苏省无锡市锡山区', '320281',
		'江苏无锡江阴市' ]);
a_t.add('0_30_9',
		[ '320302', '江苏徐州鼓楼区', '320303', '江苏徐州云龙区', '320305', '江苏徐州贾汪区',
				'320311', '江苏徐州泉山区', '320322', '江苏徐州沛县', '320323', '江苏徐州铜山县',
				'320381', '江苏徐州新沂市', '320382', '江苏徐州邳州市', '320301', '江苏徐州市辖区',
				'320304', '江苏徐州九里区', '320321', '江苏徐州丰县', '320324', '江苏徐州睢宁县' ]);
a_t.add('0_30_10', [ '320401', '江苏常州市辖区', '320402', '江苏常州天宁区', '320405',
		'江苏常州戚墅堰区', '320411', '江苏常州新北区', '320481', '江苏常州溧阳市', '320482',
		'江苏常州金坛市', '320404', '江苏常州钟楼区', '320412', '江苏省常州市武进区' ]);
a_t.add('0_30_11', [ '320584', '江苏苏州吴江市', '320585', '江苏苏州太仓市', '320501',
		'江苏苏州市辖区', '320502', '江苏苏州沧浪区', '320504', '江苏苏州金阊区', '320505',
		'江苏省苏州市虎丘区', '320507', '江苏省苏州市相城区', '320581', '江苏苏州常熟市', '320582',
		'江苏苏州张家港市', '320503', '江苏苏州平江区', '320506', '江苏省苏州市吴中区', '320583',
		'江苏苏州昆山市' ]);
a_t.add('0_30_12',
		[ '320601', '江苏南通市辖区', '320602', '江苏南通崇川区', '320621', '江苏南通海安县',
				'320623', '江苏南通如东县', '320682', '江苏南通如皋市', '320683', '江苏南通通州市',
				'320611', '江苏南通港闸区', '320681', '江苏南通启东市', '320684', '江苏省海门市' ]);
a_t.add('0_31', [ '220300', '吉林省四平市', '220400', '吉林省辽源市', '220500', '吉林省通化市',
		'220600', '吉林省白山市', '220800', '吉林省白城市', '222400', '吉林省延边朝鲜族自治州',
		'220100', '吉林省长春市', '220200', '吉林省吉林市', '220700', '吉林省松原市' ]);
a_t.add('0_31_0', [ '220301', '吉林四平市辖区', '220302', '吉林四平铁西区', '220303',
		'吉林四平铁东区', '220322', '吉林四平梨树县', '220323', '吉林四平伊通满族自治县', '220381',
		'吉林四平公主岭市', '220382', '吉林省双辽市' ]);
a_t.add('0_31_1', [ '220401', '吉林辽源市辖区', '220402', '吉林辽源龙山区', '220403',
		'吉林辽源西安区', '220421', '吉林辽源东丰县', '220422', '吉林辽源东辽县' ]);
a_t.add('0_31_2', [ '220501', '吉林通化市辖区', '220502', '吉林通化东昌区', '220503',
		'吉林通化二道江区', '220521', '吉林通化县', '220523', '吉林通化辉南县', '220524',
		'吉林通化柳河县', '220582', '吉林通化集安市', '220581', '吉林通化梅河口市' ]);
a_t.add('0_31_3', [ '220602', '吉林白山八道江区', '220621', '吉林白山抚松县', '220623',
		'吉林白山长白朝鲜族自治县', '220625', '吉林省白山市江源县', '220681', '吉林白山临江市', '220601',
		'吉林白山市辖区', '220622', '吉林白山靖宇县' ]);
a_t.add('0_31_4', [ '220801', '吉林白城市辖区', '220821', '吉林白城镇赉县', '220822',
		'吉林白城通榆县', '220882', '吉林白城大安市', '220802', '吉林白城洮北区', '220881',
		'吉林白城洮南市' ]);
a_t.add('0_31_5', [ '222402', '吉林延边图们市', '222403', '吉林延边敦化市', '222405',
		'吉林延边龙井市', '222406', '吉林延边和龙市', '222426', '吉林延边安图县', '222424',
		'吉林延边汪清县', '222401', '吉林延边延吉市', '222404', '吉林延边珲春市' ]);
a_t.add('0_31_6', [ '220101', '吉林长春市辖区', '220103', '吉林长春宽城区', '220104',
		'吉林长春朝阳区', '220106', '吉林省长春市绿园区', '220112', '吉林省长春市双阳区', '220181',
		'吉林长春九台市', '220182', '吉林长春榆树市', '220102', '吉林长春南关区', '220105',
		'吉林长春二道区', '220122', '吉林长春农安县', '220183', '吉林省德惠市' ]);
a_t.add('0_31_7', [ '220203', '吉林龙潭区', '220204', '吉林船营区', '220211', '吉林丰满区',
		'220221', '吉林永吉县', '220281', '吉林蛟河市', '220282', '吉林桦甸市', '220283',
		'吉林舒兰市', '220284', '吉林省磐石市', '220201', '吉林市辖市', '220202', '吉林昌邑区' ]);
a_t.add('0_31_8', [ '220701', '吉林松原市辖区', '220721', '吉林松原前郭尔罗斯蒙古族自治县', '220722',
		'吉林松原长岭县', '220723', '吉林松原乾安县', '220702', '吉林松原宁江区', '220724',
		'吉林省松原市扶余县' ]);
a_t.add('0_32', [ '653100', '新疆维吾尔自治区喀什地区', '653200', '新疆维吾尔自治区和田地区', '654000',
		'新疆维吾尔自治区伊犁哈萨克自治州', '654200', '新疆维吾尔自治区塔城地区', '654300',
		'新疆维吾尔自治区阿勒泰地区', '659000', '新疆维吾尔自治区省直辖行政单位', '650100',
		'新疆维吾尔自治区乌鲁木齐市', '650200', '新疆维吾尔自治区克拉玛依市', '652100', '新疆维吾尔自治区吐鲁番地区',
		'652200', '新疆维吾尔自治区哈密地区', '652300', '新疆维吾尔自治区昌吉回族自治州', '652700',
		'新疆维吾尔自治区博尔塔拉蒙古自治州', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州', '652900',
		'新疆维吾尔自治区阿克苏地区', '653000', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州' ]);
a_t.add('0_32_0', [ '653122', '新疆喀什疏勒县', '653123', '新疆喀什英吉沙县', '653124',
		'新疆喀什泽普县', '653125', '新疆喀什莎车县', '653126', '新疆喀什叶城县', '653127',
		'新疆喀什麦盖提县', '653128', '新疆喀什岳普湖县', '653129', '新疆喀什伽师县', '653130',
		'新疆喀什巴楚县', '653131', '塔什库尔干塔吉克自治县', '653101', '新疆喀什市', '653121',
		'新疆喀什疏附县' ]);
a_t.add('0_32_1', [ '653201', '新疆和田市', '653221', '新疆和田县', '653222', '新疆和田墨玉县',
		'653223', '新疆和田皮山县', '653224', '新疆和田洛浦县', '653225', '新疆和田策勒县',
		'653226', '新疆和田于田县', '653227', '新疆和田民丰县' ]);
a_t.add('0_32_2', [ '654002', '新疆维吾尔自治区伊宁市', '654003', '新疆维吾尔自治区奎屯市', '654021',
		'新疆维吾尔自治区奎屯市伊宁县', '654022', '新疆维吾尔自治区奎屯市察布查尔锡伯自治县', '654023',
		'新疆维吾尔自治区奎屯市霍城县', '654024', '新疆维吾尔自治区奎屯市巩留县', '654025',
		'新疆维吾尔自治区奎屯市新源县', '654026', '新疆维吾尔自治区奎屯市昭苏县', '654027',
		'新疆维吾尔自治区奎屯市特克斯县', '654028', '新疆维吾尔自治区奎屯市尼勒克县' ]);
a_t.add('0_32_3', [ '654226', '塔城和布克赛尔蒙古自治县', '654201', '新疆塔城市', '654202',
		'新疆维吾尔自治区乌苏市', '654221', '新疆塔城额敏县', '654223', '新疆塔城沙湾县', '654224',
		'新疆塔城托里县', '654225', '新疆塔城裕民县' ]);
a_t.add('0_32_4', [ '654322', '新疆阿勒泰富蕴县', '654325', '新疆阿勒泰青河县', '654301',
		'新疆阿勒泰阿勒泰市', '654321', '新疆阿勒泰布尔津县', '654323', '新疆阿勒泰福海县', '654324',
		'新疆阿勒泰哈巴河县', '654326', '新疆阿勒泰吉木乃县' ]);
a_t.add('0_32_5', [ '659002', '新疆维吾尔自治区阿拉尔市', '659001', '新疆石河子市', '659003',
		'新疆维吾尔自治区图木舒克市', '659004', '新疆维吾尔自治区五家渠市' ]);
a_t.add('0_32_6', [ '650103', '新疆乌鲁木齐沙依巴克区', '650107', '新疆乌鲁木齐达坂城区', '650101',
		'新疆乌鲁木齐市辖区', '650102', '新疆乌鲁木齐天山区', '650104', '新疆乌鲁木齐新市区', '650105',
		'新疆乌鲁木齐水磨沟区', '650106', '新疆乌鲁木齐头屯河区', '650108', '新疆乌鲁木齐东山区', '650121',
		'新疆乌鲁木齐乌鲁木齐县' ]);
a_t.add('0_32_7', [ '650201', '新疆克拉玛依市辖区', '650205', '新疆克拉玛依乌尔禾区', '650202',
		'新疆克拉玛依独山子区', '650203', '新疆克拉玛依克拉玛依区', '650204', '新疆克拉玛依白碱滩区' ]);
a_t.add('0_32_8', [ '652123', '新疆吐鲁番托克逊县', '652101', '新疆吐鲁番吐鲁番市', '652122',
		'新疆吐鲁番鄯善县' ]);
a_t.add('0_32_9', [ '652222', '哈密巴里坤哈萨克自治县', '652201', '新疆哈密市', '652223',
		'新疆哈密伊吾县' ]);
a_t.add('0_32_10', [ '652302', '新疆昌吉阜康市', '652324', '新疆昌吉玛纳斯县', '652301',
		'新疆昌吉市', '652303', '新疆维吾尔自治区米泉市', '652323', '新疆昌吉呼图壁县', '652325',
		'新疆昌吉奇台县', '652327', '新疆昌吉吉木萨尔县', '652328', '新疆昌吉木垒哈萨克自治县' ]);
a_t.add('0_32_11', [ '652701', '新疆博尔塔拉博乐市', '652722', '新疆博尔塔拉精河县', '652723',
		'新疆博尔塔拉温泉县' ]);
a_t.add('0_32_12', [ '652801', '新疆巴音郭楞库尔勒市', '652824', '新疆巴音郭楞若羌县', '652828',
		'新疆巴音郭楞和硕县', '652822', '新疆巴音郭楞轮台县', '652823', '新疆巴音郭楞尉犁县', '652825',
		'新疆巴音郭楞且末县', '652826', '巴音郭楞焉耆回族自治县', '652827', '新疆巴音郭楞和静县', '652829',
		'新疆巴音郭楞博湖县' ]);
a_t.add('0_32_13', [ '652925', '新疆阿克苏新和县', '652928', '新疆阿克苏阿瓦提县', '652922',
		'新疆阿克苏温宿县', '652901', '新疆阿克苏阿克苏市', '652923', '新疆阿克苏库车县', '652924',
		'新疆阿克苏沙雅县', '652926', '新疆阿克苏拜城县', '652927', '新疆阿克苏乌什县', '652929',
		'新疆阿克苏柯坪县' ]);
a_t.add('0_32_14', [ '653022', '新疆阿克陶县', '653001', '新疆阿图什市', '653023',
		'新疆阿合奇县', '653024', '新疆乌恰县' ]);
a_t.add('0_33', [ '445200', '广东省揭阳市', '445300', '广东省云浮市', '440900', '广东省茂名市',
		'441300', '广东省惠州市', '441600', '广东省河源市', '442000', '广东省中山市', '440300',
		'广东省深圳市', '440400', '广东省珠海市', '440500', '广东省汕头市', '440600', '广东省佛山市',
		'440700', '广东省江门市', '440800', '广东省湛江市', '441200', '广东省肇庆市', '441400',
		'广东省梅州市', '441500', '广东省汕尾市', '441700', '广东省阳江市', '441800', '广东省清远市',
		'441900', '广东省东莞市', '445100', '广东省潮州市', '440100', '广东省广州市', '440200',
		'广东省韶关市' ]);
a_t.add('0_33_0', [ '445201', '广东揭阳市辖区', '445202', '广东揭阳榕城区', '445221',
		'广东揭阳揭东县', '445222', '广东揭阳揭西县', '445224', '广东揭阳惠来县', '445281',
		'广东揭阳普宁市' ]);
a_t.add('0_33_1', [ '445301', '广东省云浮市市辖区', '445302', '广东省云浮市云城区', '445321',
		'广东省云浮市新兴县', '445322', '广东省云浮市郁南县', '445323', '广东省云浮市云安县', '445381',
		'广东省罗定市' ]);
a_t.add('0_33_2', [ '440903', '广东省茂名市茂港区', '440982', '广东省化州市', '440901',
		'广东茂名市辖区', '440902', '广东茂名茂南区', '440923', '广东茂名电白县', '440981',
		'广东茂名高州市', '440983', '广东省信宜市' ]);
a_t.add('0_33_3', [ '441303', '广东省惠州市惠阳区', '441324', '广东惠州龙门县', '441301',
		'广东惠州市辖区', '441302', '广东惠州惠城区', '441322', '广东惠州博罗县', '441323',
		'广东惠州惠东县' ]);
a_t.add('0_33_4', [ '441621', '广东河源紫金县', '441624', '广东河源和平县', '441601',
		'广东河源市辖区', '441602', '广东河源源城区', '441622', '广东河源龙川县', '441623',
		'广东河源连平县', '441625', '广东河源东源县' ]);
a_t.add('0_33_5', [ '442010', '广东省中山市' ]);
a_t.add('0_33_6', [ '440303', '广东深圳罗湖区', '440306', '广东深圳宝安区', '440301',
		'广东深圳市辖区', '440304', '广东深圳福田区', '440305', '广东深圳南山区', '440307',
		'广东深圳龙岗区', '440308', '广东省深圳市盐田区' ]);
a_t.add('0_33_7', [ '440401', '广东珠海市辖区', '440402', '广东珠海香洲区', '440403',
		'广东省珠海市斗门区', '440404', '广东省珠海市金湾区' ]);
a_t.add('0_33_8', [ '440501', '广东汕头市辖区', '440507', '广东汕头龙湖区', '440511',
		'广东省汕头市金平区', '440512', '广东省汕头市濠江区', '440513', '广东省汕头市潮阳区', '440514',
		'广东省汕头市潮南区', '440515', '广东省汕头市澄海区', '440523', '广东汕头南澳县' ]);
a_t.add('0_33_9', [ '440601', '广东佛山市辖区', '440604', '广东省佛山市禅城区', '440605',
		'广东省佛山市南海区', '440606', '广东省佛山市顺德区', '440607', '广东省佛山市三水区', '440608',
		'广东省佛山市高明区' ]);
a_t.add('0_33_10', [ '440701', '广东江门市辖区', '440703', '广东省江门市蓬江区', '440704',
		'广东省江门市江海区', '440705', '广东省江门市新会区', '440781', '广东江门台山市', '440783',
		'广东江门开平市', '440784', '广东江门鹤山市', '440785', '广东省恩平市' ]);
a_t.add('0_33_11', [ '440802', '广东湛江赤坎区', '440811', '广东湛江麻章区', '440881',
		'广东湛江廉江市', '440801', '广东湛江市辖区', '440803', '广东湛江霞山区', '440804',
		'广东湛江坡头区', '440823', '广东湛江遂溪县', '440825', '广东湛江徐闻县', '440882',
		'广东省雷州市', '440883', '广东省吴川市' ]);
a_t.add('0_33_12', [ '441201', '广东肇庆市辖区', '441223', '广东肇庆广宁县', '441226',
		'广东肇庆德庆县', '441202', '广东肇庆端州区', '441203', '广东肇庆鼎湖区', '441224',
		'广东肇庆怀集县', '441225', '广东肇庆封开县', '441283', '广东肇庆高要市', '441284',
		'广东肇庆四会市' ]);
a_t.add('0_33_13',
		[ '441402', '广东梅州梅江区', '441423', '广东梅州丰顺县', '441427', '广东梅州蕉岭县',
				'441401', '广东梅州市辖区', '441421', '广东梅州梅县', '441422', '广东梅州大埔县',
				'441424', '广东梅州五华县', '441426', '广东梅州平远县', '441481', '广东省兴宁市' ]);
a_t.add('0_33_14', [ '441501', '广东汕尾市辖区', '441521', '广东汕尾海丰县', '441502',
		'广东汕尾城区', '441523', '广东汕尾陆河县', '441581', '广东省陆丰市' ]);
a_t.add('0_33_15', [ '441701', '广东阳江市辖区', '441723', '广东阳江阳东县', '441702',
		'广东阳江江城区', '441721', '广东阳江阳西县', '441781', '广东省阳春市' ]);
a_t.add('0_33_16', [ '441801', '广东清远市辖区', '441823', '广东清远阳山县', '441881',
		'广东省英德市', '441802', '广东清远清城区', '441821', '广东清远佛冈县', '441825',
		'广东清远连山壮族瑶族自治县', '441826', '广东清远连南瑶族自治县', '441827', '广东清远清新县',
		'441882', '广东省连州市' ]);
a_t.add('0_33_17', [ '441910', '广东省东莞市' ]);
a_t.add('0_33_18', [ '445121', '广东潮州潮安县', '445122', '广东潮州饶平县', '445101',
		'广东潮州市辖区', '445102', '广东潮州湘桥区' ]);
a_t.add('0_33_19', [ '440101', '广东广州市辖区', '440105', '广东广州海珠区', '440112',
		'广东广州黄埔区', '440115', '广东省广州市南沙区', '440184', '广东省从化市', '440103',
		'广东广州荔湾区', '440104', '广东广州越秀区', '440106', '广东广州天河区', '440111',
		'广东广州白云区', '440113', '广东省广州市番禺区', '440114', '广东省广州市花都区', '440116',
		'广东省广州市萝岗区', '440183', '广东广州增城市' ]);
a_t.add('0_33_20', [ '440203', '广东韶关武江区', '440222', '广东韶关始兴县', '440233',
		'广东韶关新丰县', '440282', '广东省南雄市', '440281', '广东省乐昌市', '440201', '广东韶关市辖区',
		'440204', '广东韶关浈江区', '440205', '广东省韶关市曲江区', '440224', '广东韶关仁化县',
		'440229', '广东韶关翁源县', '440232', '广东韶关乳源瑶族自治县' ]);