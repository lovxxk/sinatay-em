//��ʼ���ֻ���֤����ʱ����
var intervalProcess;
var timenum = 120;
//�ֻ���֤����ʱ
function counttime() {
	if (timenum > 0) {
		$('#getPhonePwd').val('('+timenum+')�����·���');
		timenum = timenum - 1;
	} else {
		$('#getPhonePwd').val('����ٴη���');
		$('#getPhonePwd').valiCodeEnable();
		clearInterval(intervalProcess);
	}
}
//pageNum��1��ʼ
function showPage(pageNum){
	$('.edors_item').hide();
	$('.edors_item:lt(' + (pageNum * 6) + ')').show();
	$('.edors_item:lt(' + ((pageNum - 1) * 6) + ')').hide();
	
	//����Ӧ����
	$('.security_info').css('height',$('.security_info').height());
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
//У���Ƿ��ܹ�����
function check(acceptNo){
	$.ajax({
		type : "post",
		url : contextRootPath+"/myPolicyDetail/check.do",
		data : "acceptNo=" + acceptNo,
		dataType : 'json',
		success : function(data, textStatus) {
			if(data.flag=='0'){
				Sinosoft.alert({
					contentStr : '����ʧ�ܣ�',
					subContentStr : data.desc,
					okStr : 'ȷ��'
				});
			}else{
				//��������ļ���
				$.post('/myPolicyDetail/downloadEdor.do?url='+data.url);
			
			}
			
		},
		error : function(backData) {
			a_alert("�����쳣��");
		}
	});
	
	
}

//�ֽ��ֵ����Ʒ�����������
function productCashBnf(type){
	var showSelect=$("#showSelect").text();
	if(showSelect=='0'){
		Sinosoft.alert({
			contentStr : '��������Ϣ��',
			subContentStr : '�𾴵��û����ã���ǰ�㻹û����Ч��������Ϣ��',
			okStr : 'ȷ��'
		});
		return false;
	}
	var risksStr=$(".risksStr").html();
	var json_risks = eval("(" + risksStr + ")");
	new Sinosoft.InteractiveDialog(
			{
				layout : loadInsuranceSelect2(json_risks),
				width : 560,
				okStr : 'ȷ��',
				cancelStr : 'ȡ��',
				okFunc : function() {
					//���ִ���
					var riskCode=$(".selected").attr('value');
					//��������
					var policyNo=$(".policyNo").attr('value');
					//�������ֺ���
					var polNo=$(".selected .polNo").attr('value');
					//����ǲ�Ʒ����
					if(type == 1 ){
						window.open("http://zizhu.sinatay.com/nss/sys/clause/"+riskCode+".pdf");
					}
					//��������
					if(type == 2){
						//ִ��ajax���� ��֤�ñ�ѡ���������ɱ�������
						$.ajax({
							type : "post",
							url:contextRootPath+"/myPolicyDetail/checkInBnf.do",
							data : "policyNo=" + policyNo + "&polNo=" + polNo,
							dataType : 'json',
							success : function(data, textStatus) {
								//��Ӧ����
								if(data.flag=='0'){
									Sinosoft.alert({
										contentStr : '�ޱ���������Ϣ��',
										subContentStr : data.desc,
										okStr : 'ȷ��'
									});
								}else{
									//���
//									window.open(contextRootPath+'/myPolicyDetail/inBnf.do','_blank');
									$('#bnfJsonStr').val(data.bnfJsonStr);
									$('#bnffm').submit();
								}
							},
							error : function(backData) {
								a_alert("�����쳣��");
							}
						})
					}
					//�ֽ��ֵ
					if(type == 3){
						//ִ��ajax���� ��֤�ñ�ѡ���������ɼ�ֵ��Ϣ
						$.ajax({
							type : "post",
							url:contextRootPath+"/myPolicyDetail/checkCashValue.do",
							data : "policyNo=" + policyNo + "&polNo=" + polNo,
							dataType : 'json',
							success : function(data, textStatus) {
								//��Ӧ����
								if(data.flag=='0'){
									Sinosoft.alert({
										contentStr : '���ֽ��ֵ��Ϣ��',
										subContentStr : data.desc,
										okStr : 'ȷ��'
									});
								}else{
									
									//��ü�ֵ��ϢjsonStr
									
//									window.open(contextRootPath+'/myPolicyDetail/cashValue.do','_blank');
									$('#cashValueJsonStr').val(data.jsonCashValueInfoStr);
									$('#cashValuefm').submit();
								}
							},
							error : function(backData) {
								a_alert("�����쳣��");
							}
						})
						
					}
				},
				cancelFunc : function() {
					
				}
			}).open();
}
//���������б�
function loadInsuranceSelect2(json_risks) {
	
	var str='<div class="insurance_select">'
		+ '<div class="select_title">��ѡ�����֣�</div>';
	for(var i=0;i<json_risks.length;i++){
		var json_risk=json_risks[i];
	//�������ֺ���
		if(i==0){
			str +='<div class="select_item selected" value="'+json_risk.riskCode+'">'
			//�������ֱ�����
			str +='  <div style="display:none;" class="policyNo" value="'+json_risk.policyNo+'">'+json_risk.policyNo+'</div>';
			str +='  <div style="display:none;" class="polNo" value="'+json_risk.polNo+'">'+json_risk.polNo+'</div>';
			str +='  <div class="select_radio" id="insurance1"></div>'+json_risk.riskName+'</div>';
		}else{
			str +='  <div class="select_item" value="'+json_risk.riskCode+'">'
			str +='  <div style="display:none;" class="polNo" value="'+json_risk.polNo+'">'+json_risk.polNo+'</div>';
			str +='  <div class="select_radio" id="insurance1"></div>'+json_risk.riskName+'</div>';
		}
	}
	
	//���ؼ�ֵ��Ϣ  ͨ�����ύ
	str = str + '<form id="cashValuefm" method="post" name="fm" action="'+contextRootPath+'/myPolicyDetail/cashValue.do" target="_blank">';
	str = str + '  <input type="text" id="cashValueJsonStr" name="jsonCashValueInfoStr" style="display:none;" />';
	str = str + '</form>';
	
	//���ر���������Ϣ  ͨ�����ύ
	str = str + '<form id="bnffm" method="post" name="fm" action="'+contextRootPath+'/myPolicyDetail/inBnf.do" target="_blank">';
	str = str + '  <input type="text" id="bnfJsonStr" name="bnfJsonStr" style="display:none;"/>';
	str = str + '</form>';
	
	str = str + '</div>';
	var insuranceSelect=$(str);
	//�ı���ʽ
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
					// �����Ч
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
					
					//ǰ̨���棬��ʼ��ҳ�漰������
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
					
					//��Щ��������ԭ����ֵ�ĳ�����
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
												$('.selected_job').html('��ѡ��ְҵ');
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
					//�޸ı�����
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

										// У���������ݸ�ʽ
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
										//��֤�Ƿ�Ϊ�������ı���
										var scource = $('#scource').val();
										if(scource !='NETS'){
											//��������֤�ֻ�����
											reg = /^\d{11}$/;
											if (mobile != '' && !reg.test(mobile)) {
												a_alert('���������ֻ������Ƿ���ȷ��');
												return;
											}
										}
										//������֤�Ƿ��иı����Ϣ
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
											//��Ҫ�ж��Ƿ���Ͷ�����뱻�˱��˵�ְҵ����  1������Ǳ����ˣ�ְҵ���ƾ��ǲ��ɸ���
											if(newJobType == '��ѡ��ְҵ'){
												newJobType='';
											}
										
											var newProvince = parent.find('.province option:selected').text();
											var newCity = parent.find('.city option:selected').text();
											var newCounty = parent.find('.county option:selected').text();
											var newHomeAddress = parent.find('.homeAddress').val();
											var infoType=parent.find('#infoType').text();
											
											if(newMarriage == '��'){
												newMarriage="";
											}
											if(newNationality == '��'){
												newNationality="";
											}
											if(newLicenseType == '��'){
												newLicenseType="";
											}
											if(infoType == '1'){
												//�ж��Ƿ��б����Ϣ
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
														a_alert('�ޱ����Ϣ��');
													return ;
												}
											}
											else{
												//�ж��Ƿ��б����Ϣ
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
													
													a_alert('�ޱ����Ϣ��');
													return ;
												}
											}
											
											reg=/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
										if (email != '' && !reg.test(email)) {
											a_alert('�������ĵ��������Ƿ���ȷ��');
											return;
										}
										
										reg = /^(\d{3,4}-)?[1-9]\d{6,7}$/;
										if (officePhone != ''
												&& !reg.test(officePhone)) {
											a_alert('�������İ칫�绰�Ƿ���ȷ��');
											return;
										}
										reg = /^(\d{3,4}-)?[1-9]\d{6,7}$/;
										if (fax != '' && !reg.test(fax)) {
											a_alert('�������Ĵ���绰�Ƿ���ȷ��');
											return;
										}
										//�޸��������벻��Ϊ��
										if(homeZipCode == ''){
											a_alert('�������벻��Ϊ�գ�');
											return;
										}
										//��ϵ��ַ����Ϊ��
										if(newHomeAddress == ''){
											a_alert('��ϵ��ַ����Ϊ�գ�');
											return;
										}
										//�ƶ��绰���칫�绰��סլ�绰����3�������һ
										if(newMobile == '' && officePhone == '' && phone == ''){
											a_alert('�ƶ��绰���칫�绰��סլ�绰����3�������һ��');
											return;
										}
										
										reg = /^\d{6}$/;
										if (homeZipCode != ''
												&& !reg.test(homeZipCode)) {
											a_alert('�����������������Ƿ���ȷ��');
											return;
										}
										reg = /^(\d{3,4}-)?[1-9]\d{6,7}$/;
										if (phone != '' && !reg.test(phone)) {
											a_alert('��������סլ�绰�Ƿ���ȷ��');
											return;
										}
										var userMobile = $("#userMobile").val();
										if(userMobile == ''){
											doNoPhone('�ֻ�����');
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

					// û�������˵ĳ��ϣ���ʾ����������
					var bnf_parent = $("#first_bnf").parents('.input_area');
					if(bnf_parent.children().size() == 2){
						$("#first_bnf").css("display","block");
					}				
					
					var alertType = getQueryString('alertType');

					if (alertType == '1') {// ����ʧ�ܵ�����
						Sinosoft.alert({
							contentStr : '�Բ��𣬲���ʧ�ܣ�',
							subContentStr : '�������²������򲦴�ͷ��绰400-600-8890������ѯ��',
							okStr : 'ȷ��',
							cancelStr : 'ȡ��'
						});
					} else if (alertType == '2') {// ���ܶԱ������в���������
						Sinosoft.alert({
							contentStr : '�Բ���Ŀǰ�����ܶԸñ������в�����',
							subContentStr : 'ԭ�򣺣أأأأأأأأأأأأأأأأأأأأأ�',
							width : 480,
							okStr : 'ȷ��',
							cancelStr : 'ȡ��'
						});
					} else if (alertType == '3') {// ��������ҳ-��ȡ-��д���-�˱�ȷ��
						new Sinosoft.InteractiveDialog({
							layout : loadWithdrawConfirm(),
							width : 560,
							okStr : '��һ��',
							cancelStr : 'ȷ��',
							okFunc : function() {
								doReceive();
							},
							cancelFunc : function() {
								// $('#total_amount').text(parseInt(sinatay.totalAmount
								// - sinatay.receiveAmount -
								// sinatay.receiveAmount * 0.02));
							}
						}).open();
					} else if (alertType == '4') {// ��������ҳ-��ȡ-��д���-�˱�ȷ��
						new Sinosoft.InteractiveDialog({
							layout : loadInsuranceSelect(),
							okStr : 'ȷ��',
							cancelStr : 'ȡ��'
						}).open();
					}
				});

function loadReceiveInput() {
	var success = $('<div class="receive_input">'
			+ '<label class="receive_label">��������ȡ��</label><input class="receive_text" style="color:#000000;" type="text" name="receiveAmount"/><label class="receive_label_tip">Ԫ</label>'
			+ '<a href="javascript:prall()">ȫ����ȡ</a>'
			+ '</div>');
	return success;
}

function prall() {
	$(".receive_text").val($("#total_amount").text());
}

function loadReceiveAmountConfirm() {
	var amountConfirm = $('<div class="amount_confirm">'
			+ '<div class="this_time"><p class="amount">��������ȡ���Ϊ��<span id="amount_confirm" class="amount_num"></span>Ԫ</p>'
			+ '<p class="instro">������ȡ��Ҫ֧�������ѽ��<span id="fee" class="fee_num">256.00</span>Ԫ</p>'

			+ '<p class="instro"></p></div>'
			+ '<div class="real_amount"><p class="amount">ʵ����ȡ���Ϊ��<span id="real_amount_confirm" class="amount_num"></span>Ԫ</p></div>'
			+ '<div class="refund_account"><p class="refund_title">��������ȡ�Ľ����������˻���</p>'
			+ '<div class="send_vali"><p>����������Ԥ�����ֻ���<span id="b_appntphone">xxxxxxx</span>������֤��</p><p>�뽫��֤�������¿�</p></div>'
			+ '<div class="vali_input"><label for="amount_validate">��֤�룺</label><input id="amount_validate" name="amount_validate" type="text"/><div class="resend click_btn">���·���</div></div>'
			+ '</div>');

	// ��֤��Ԥ��

	return amountConfirm;
}

function loadWithdrawConfirm() {
	var amountConfirm = $('<div class="amount_confirm">'
			+ '<div class="this_time"><p class="amount">��������ȡ���Ϊ��<span id="amount_confirm" class="amount_num">100000</span>Ԫ</p><p class="instro">������ȡ��Ҫ֧�������ѽ��<span id="fee" class="fee_num">256.00</span>Ԫ</p><p class="instro">ע��������ͬ�涨��ȫ����ȡ������Ϊ�˱������ñ����µ��������ν�ͬ��</p></div>'
			+ '<div class="real_amount"><p class="amount">ʵ����ȡ���Ϊ��<span id="real_amount_confirm" class="amount_num">256.00</span>Ԫ</p><p class="instro">ʵ����ȡ�����������һ�ν������������Ϣ����</p></div>'
			+ '<div class="refund_account"><p class="refund_title">��������ȡ�Ľ����������˻���</p><p class="refund_info"><span class="info_name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</span>������</p><p class="refund_info"><span class="info_name">�������ƣ�</span>�й���������</p><p class="refund_info"><span class="info_name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ţ�</span>685 475 412 4411</p></div>'
			+ '<div class="send_vali"><p>����������Ԥ�����ֻ���<span>18628282828</span>������֤��</p><p>�뽫��֤�������¿�</p></div>'
			+ '<div class="vali_input"><label for="amount_validate">��֤�룺</label><input name="amount_validate" type="text"/><div class="resend click_btn">���·���</div></div>'
			+ '</div>');

	return amountConfirm;
}

function loadInsuranceSelect() {
	var insuranceSelect = $('<div class="insurance_select">'
			+ '<div class="select_title">��ѡ�����֣�</div>'
			+ '<div class="select_item selected"><div class="select_radio" id="insurance1"></div>��̩����ǰ���ٶ���ȫ����(�ֺ���)A������</div>'
			+ '<div class="select_item"><div class="select_radio" id="insurance1"></div>��̩����ǰ���ٶ���ȫ����(�ֺ���)B������</div>'
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
					contentStr: 'ϵͳ���ʴ���',
					subContentStr : data.desc,
					cancelStr:'ȷ��',
					okBtnShow:false
				});
			} else {
				url = data.url;
				$("#url").val(url);
				$("#down_from").submit();
			}
		},
		error : function(backData) {
			a_alert("�����쳣��");
		}
	});	
}

//�õ���֤��
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
					contentStr : '�ǳ���Ǹ��',
					subContentStr : '���������������վ��������֤�뷢�����ۼƳ���50�Σ������������ԡ�',
					width : 480,
					okStr : 'ȷ��',
					cancelStr : 'ȡ��',
					okFunc:function(){
						doReceive();
					}
				});
				/*Sinosoft.alert({
					contentStr: "�ǳ���Ǹ�����������������վ��������֤�뷢�����ۼƳ���50�Σ������������ԡ�",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
				contentStr: "����ʧ��",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
				}
			});
		}
	});
	sinatay.phoneCheckNo = v_no;
	
}
//�����֤��
function checkPhoneCheckNo(){
	if('' == $("#phone_amount_validate").val()){
		$("#phone_amount_valiinfo").text('��������֤�룡');
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
				$("#phone_amount_valiinfo").text('��֤���������');
				$('#phone_amount_validate').css({ border:"1px solid red"});
			}
		},
		error: function(backData) {
			a_alert('�������');
		}
	});
	if(isSuccess=="Y"){
		return true;
	}else{
		return false;
	}
}
//��ȡ��ť,������
function doReceive(){
	
	// ����Ƿ����ֻ��ſ��Է�����֤��
	var phone = $("#userMobile").val();
	if(phone == null || phone == ''){
		Sinosoft.alert({
			contentStr: "����ǰ���������������ֻ���Ϣ��",
			okFunc:function(){
				doNoPhone('�ֻ�����');
			}
		});		
		return '';
	}
	// �����ȡ����
	if($("#receiveFlag").val() != 'true'){
		Sinosoft.alert({
			contentStr: "�ף�����������ʱ��Ч����������˱�����ȡŶ~",
			cancelStr:'ȷ��',
			okBtnShow:false
		});		
		return '';
	}
	var test;
	var loading = new Sinosoft.LoadingDialog({
		contentStr: '�����ĵȴ�...',
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
		cancelStr:'��һ��',
		okBtnShow:false,
		cancelBtnShow:true,
		closeIconShow:true,
		cancelFunc:function(){
			
			sinatay.receiveAmount = Number($('.receive_input .receive_text').val());
			sinatay.totalAmount = Number($('#total_amount').text());
			var tbflag = sinatay.receiveAmount==sinatay.totalAmount?'1':'0';
			
			if(sinatay.receiveAmount == ''){
				Sinosoft.alert({
					contentStr: '��������',
					subContentStr:'�����º˶���Ϣ�������',
					okStr: 'ȷ��',
					cancelStr: 'ȡ��',
					okFunc:function(){
						doReceive();
					}
				});
			}else if(parseFloat(sinatay.receiveAmount) == 0){
				Sinosoft.alert({
					contentStr: '���������0',
					subContentStr:'�����º˶���Ϣ�������',
					okStr: 'ȷ��',
					cancelStr: 'ȡ��',
					okFunc:function(){
						doReceive();
					}
				});
			}else if(isNaN(sinatay.receiveAmount)){
				Sinosoft.alert({
					contentStr: '����������',
					subContentStr:'�����º˶���Ϣ�������',
					okStr: 'ȷ��',
					cancelStr: 'ȡ��',
					okFunc:function(){
						doReceive();
					}
				});
			}else if(sinatay.receiveAmount > sinatay.totalAmount){
				Sinosoft.alert({
					contentStr: '�������ִ����˻����',
					subContentStr:'�����º˶���Ϣ�������',
					okStr: 'ȷ��',
					cancelStr: 'ȡ��',
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
								cancelStr:'ȷ��',
								okBtnShow:false
							});
						} else {
							sinatay.b_accname = json.acountName;//�˻���
							sinatay.b_bankname = json.bankName;//������
							sinatay.b_accno = json.acountNo;//�˻���
							
							//����������
							var str = '';							
							//�ֻ���������
							var um01 = $("#userMobile").val();
							if(um01.length == 11){
								um01 = um01.substr(0,3)+'****'+um01.substr(7,11);
							}			
							if('1'==json.tbFlag){//alert(json.acountDetail);									
									var mingxi = '';
									for(var i = 0; i< json.acountDetail.length;i++){
										if('�˷��ܽ��'==json.acountDetail[i][0]){
											sinatay.getMoney = json.acountDetail[i][1];//ʵ�ʵ��˽��
										}else{
											mingxi += '<p class="instro">'+json.acountDetail[i][0];
											mingxi += '<span id="fee" class="fee_num">'+json.acountDetail[i][1]+'</span>Ԫ</p>';
										}
									}
									str = 
										'<div class="amount_confirm">'
										+ '<div class="this_time"><p class="amount">��������ȡ���Ϊ��<span id="b_amount_confirm" style="color:#ff3333;font-size:25px;">'+sinatay.receiveAmount+'</span>Ԫ</p>'
										+ mingxi								
										+ '<p class="instro" id="real_amount_zhu">ע��������ͬ�涨��ȫ����ȡ������Ϊ�˱������ñ����µ��������ν�ͬ����ֹ.</p></div>'
										+ '<div class="real_amount"><p class="amount">ʵ����ȡ���Ϊ��<span style="color:#ff3333;font-size:25px;">'+sinatay.getMoney+'</span>Ԫ</p></div>'
										+ '<div class="refund_account"><p class="refund_title">��������ȡ�Ľ����������˻���</p>'
										+ '<p class="refund_info"><span class="info_name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</span><span id="b_accname">'+sinatay.b_accname+'</span></p>'
										+ '<p class="refund_info"><span class="info_name">�������ƣ�</span><span id="b_bankname">'+sinatay.b_bankname+'</span></p>'
										+ '<p class="refund_info"><span class="info_name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ţ�</span><span id="b_accno">'+sinatay.b_accno+'</span></p></div>'
										+ '<div class="send_vali"><p>����������Ԥ�����ֻ���<span id="b_appntphone">'+um01+'</span>������֤��</p><p>�뽫��֤�������¿�</p></div>'
										+ '<div class="vali_input"><label for="phone_amount_validate">��֤�룺</label><input id="phone_amount_validate" name="phone_amount_validate" type="text" value="" readOnly="true" onblur="checkPhoneCheckNo()" />'
										//�ֻ���֤�����
										+ '<input type="button" id="getPhonePwd" class="resend click_btn" onclick="getPhoneCheckNo();" value="������֤��"/>'
										+ '</div>'
										+ '<div style="color:#ff0000;margin-top:-36px;float:left;margin-left:75px;" id="phone_amount_valiinfo" ></div>'
										+ '</div>';
										
							}else{
								var mingxi = '';
								if(json.pdSxFee!=null&&json.pdSxFee!=0){
									mingxi += '<p class="instro">�����ѽ��<span id="fee" class="fee_num">'+json.pdSxFee+'</span>Ԫ</p>';
								}									
								if(json.loanBJ!=null&&json.loanBJ!=0){
									mingxi += '<p class="instro">������<span id="fee" class="fee_num">'+json.loanBJ+'</span>Ԫ</p>';
								}									
								if(json.loanLX!=null&&json.loanLX!=0){
									mingxi += '<p class="instro">������Ϣ<span id="fee" class="fee_num">'+json.loanLX+'</span>Ԫ</p>';
								}
								
								str = 
									'<div class="amount_confirm">'
									+ '<div class="this_time"><p class="amount">��������ȡ���Ϊ��<span id="b_amount_confirm" style="color:#ff3333;font-size:25px;">'+sinatay.receiveAmount+'</span>Ԫ</p>'
									+ mingxi								
									+ '<p class="instro" id="real_amount_zhu"></p></div>'
									+ '<div class="real_amount"><p class="amount">ʵ����ȡ���Ϊ��<span style="color:#ff3333;font-size:25px;">'+json.getMoney+'</span>Ԫ</p></div>'
									+ '<div class="refund_account"><p class="refund_title">��������ȡ�Ľ����������˻���</p>'
									+ '<p class="refund_info"><span class="info_name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</span><span id="b_accname">'+sinatay.b_accname+'</span></p>'
									+ '<p class="refund_info"><span class="info_name">�������ƣ�</span><span id="b_bankname">'+sinatay.b_bankname+'</span></p>'
									+ '<p class="refund_info"><span class="info_name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ţ�</span><span id="b_accno">'+sinatay.b_accno+'</span></p></div>'
									+ '<div class="send_vali"><p>����������Ԥ�����ֻ���<span id="b_appntphone">'+um01+'</span>������֤��</p><p>�뽫��֤�������¿�</p></div>'
									+ '<div class="vali_input"><label for="phone_amount_validate">��֤�룺</label><input id="phone_amount_validate" name="phone_amount_validate" type="text" value="" readOnly="true" onblur="checkPhoneCheckNo()" />'
									//�ֻ���֤�����
									+ '<input type="button" id="getPhonePwd" class="resend click_btn" onclick="getPhoneCheckNo();" value="������֤��"/>'
									+ '</div>'
									+ '<div style="color:#ff0000;margin-top:-36px;float:left;margin-left:75px;" id="phone_amount_valiinfo" ></div>'
									+ '</div>';
							}
							new Sinosoft.InteractiveDialog({
								layout : $(str),
								width:560,
								okStr:'��һ��',
								cancelStr:'ȷ��',
								okBtnShow:true,
								closeIconShow:true,
								okFunc:function(){
									//��ʼ������ʱ����ֹʱ��δֹͣ
									//���е��¼����
									$('#getPhonePwd').val('������֤��');
									$('#getPhonePwd').valiCodeEnable();
									clearInterval(intervalProcess);
									doReceive();
								},
								cancelFunc:function(){
									//��ʼ������ʱ����ֹʱ��δֹͣ
									//���е��¼����
									$('#getPhonePwd').val('������֤��');
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
														cancelStr:'ȷ��',
														okBtnShow:true,

													});
												} else {
													var subSuccess = $('<div class="subscribe">'
															+'<div class="success"></div><div class="main_content">'
															+'<div class="main_txt">���׳ɹ�</div>'
															+'<div class="sub_txt">���ѳɹ�������ȡ����</div></div></div>');													
													new Sinosoft.InteractiveDialog({
														layout : subSuccess,
														width:410,//�Զ��������-�������������														
														cancelStr:'ȷ��',
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
													contentStr: 'ϵͳ���ʴ���',
													cancelStr:'ȷ��',
													okBtnShow:false
												});
											}
										});
									}
								},closeFunc:function(){
									
									//��ʼ������ʱ����ֹʱ��δֹͣ
									//���е��¼����
									$('#getPhonePwd').val('������֤��');
									$('#getPhonePwd').valiCodeEnable();
									clearInterval(intervalProcess);
								}
							}).open();
						}						
					},
					error: function(backData) {
						Sinosoft.alert({
							contentStr: 'ϵͳ���ʴ���',
							cancelStr:'ȷ��',
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

//���ֻ�
function doNoPhone(parameter){
	var receive_input = $('<div class="alert_phone_input">'
			+ '<label class="alert_phone_label">������'
			+ parameter
			+ '��</label>'
			+ '<input class="alert_phone_text" type="text" name="emailOrPhone" id="emailOrPhone" maxlength=30/>'
			+ '</div><br>');	
	
	new Sinosoft.InteractiveDialog({
		layout : receive_input,
		width:490,
		cancelStr:'ȷ��',
		okBtnShow:false,
		cancelFunc:function(){
			var customerName = $("#emailOrPhone").val();
			if (customerName == "")
				return;
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
			
			var emailFalg = false;
			//�ж��û�¼����Ƿ��������Լ������ַ�Ƿ�Ϸ�
			if (customerName.indexOf("@") > 0) {
				//�û�¼��������Ƿ�Ϸ�
				var regemail =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9|_|\_|\.]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(customerName);
				if(!regemail){
					alert("����дһ����ȷ�������ַ");
					return false;
				} else {
					emailFalg = true;
				}
			}
			
			if (!emailFalg && !phoneFalg) {
				alert("������Ϸ���" + parameter);
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
						alert("ϵͳ�쳣,���Ժ�����.");
					}
				}
			});
		}
	}).open();
}

function showMobileCheckDiv(userMobile,thisbtn){
	$('#getPhonePwd').val('������֤��');
	$('#getPhonePwd').valiCodeEnable();
	clearInterval(intervalProcess);
	var showMobile = userMobile;
	if(showMobile.length == 11){
		showMobile = userMobile.substr(0,3)+'****'+userMobile.substr(7,11);
	}
	var layOut = '<div class="mobileCheckDiv">'
		+'<div class="message">���������ֻ�<span class="mobile">'+showMobile+'</span>�����ֻ���֤�룬�����·���������������ֻ���֤��</div>'
		+'<div class="alert_input_row"><div class="input"><label>��֤�룺</label>'
		+'<input type="text" maxlength="6" id="inputPhoneNo" readOnly="true"/>'
		+'<input class="resendBtn click_btn" id="getPhonePwd" type="button" value="������֤��"/></div></div></div>';
	new Sinosoft.InteractiveDialog({
	layout : $(layOut),
	okStr:'ȷ��',
	cancelStr:'ȡ��',
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
			a_alert('��֤�����');
		}
	},
	error: function(backData) {
		a_alert('�������');
	}
	});
	},
	cancelFunc:function(){
	//���е��¼����
	$('#getPhonePwd').val('������֤��');
	$('#getPhonePwd').valiCodeEnable();
	clearInterval(intervalProcess);
	
	},closeFunc:function(){
	//���е��¼����
	$('#getPhonePwd').val('������֤��');
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
					contentStr : '�ǳ���Ǹ��',
					subContentStr : '���������������վ��������֤�뷢�����ۼƳ���50�Σ������������ԡ�',
					width : 480,
					okStr : 'ȷ��',
					cancelStr : 'ȡ��',
					okFunc:function(){
						showMobileCheckDiv(mobileNum,thisbtn);
					}
				});
				//a_alert("�ǳ���Ǹ�����������������վ��������֤�뷢�����ۼƳ���50�Σ������������ԡ�");
			}else{
				timenum = 120;
				intervalProcess = setInterval('counttime()',1000);
				$('#inputPhoneNo').removeAttr('readOnly');
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

function submitChange(thisbtn){
	var test;
	var loading = new Sinosoft.LoadingDialog({
		contentStr: '�����ĵȴ�...',
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
			a_alert("�����쳣��");
		},
		success : function(data) {
			loading.close();
			//���е��¼����
			$('#getPhonePwd').val('������֤��');
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
					if($('.selected_job').html() == '��ѡ��ְҵ'){
						$('.selected_job').html('');
					}
				}
				
				var subSuccess = $('<div class="subscribe">'
						+'<div class="success"></div><div class="main_content">'
						+'<div class="main_txt">����ɹ�!</div>'
						+'<div class="sub_txt">���ѳɹ����б�ȫ����</div></div></div>');													
				new Sinosoft.InteractiveDialog({
					layout : subSuccess,
					width:410,//�Զ��������-�������������														
					cancelStr:'ȷ��',
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
var o = [ "������", "��������Ͻ��", "�����ж�����" ];

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

a_t.add('0', [ '810000', '����ر�������', '110000', '������', '120000', '�����', '130000',
		'�ӱ�ʡ', '370000', 'ɽ��ʡ', '230000', '������ʡ', '210000', '����ʡ', '530000',
		'����ʡ', '520000', '����ʡ', '510000', '�Ĵ�ʡ', '460000', '����ʡ', '500000',
		'������', '450000', '����׳��������', '340000', '����ʡ', '140000', 'ɽ��ʡ', '150000',
		'���ɹ�������', '330000', '�㽭ʡ', '350000', '����ʡ', '360000', '����ʡ', '430000',
		'����ʡ', '540000', '����������', '610000', '����ʡ', '620000', '����ʡ', '630000',
		'�ຣʡ', '420000', '����ʡ', '710000', '̨��ʡ', '820000', '�����ر�������', '640000',
		'���Ļ���������', '410000', '����ʡ', '310000', '�Ϻ���', '320000', '����ʡ', '220000',
		'����ʡ', '650000', '�½�ά���������', '440000', '�㶫ʡ' ]);
a_t.add('0_0', [ '810100', '����ر���������Ͻ��' ]);
a_t.add('0_0_0', [ '810101', '����ر�������������', '810102', '����ر�����������', '810103',
		'����ر���������������', '810104', '����ر�������������', '810105', '����ر�����������', '810106',
		'����ر���������ˮ����', '810107', '����ر��������ƴ�����', '810108', '����ر�������������',
		'810109', '����ر��������ͼ�����', '810110', '����ر��������뵺��', '810111',
		'����ر�������������', '810112', '����ر�����������', '810113', '����ر�������������', '810114',
		'����ر�������ɳ����', '810115', '����ر�������������', '810116', '����ر�������������', '810117',
		'����ر�������������', '810118', '����ر�������Ԫ����' ]);
a_t.add('0_1', [ '110100', '��������Ͻ��', '110200', '��������' ]);
a_t.add('0_1_0', [ '110101', '�����ж�����', '110102', '������������', '110103', '�����г�����',
		'110104', '������������', '110105', '�����г�����', '110106', '�����з�̨��', '110107',
		'������ʯ��ɽ��', '110108', '�����к�����', '110109', '��������ͷ����', '110111', '�����з�ɽ��',
		'110112', '������ͨ����', '110113', '������˳����', '110114', '�����в�ƽ��', '110115',
		'�����д�����', '110116', '�����л�����', '110117', '������ƽ����' ]);
a_t.add('0_1_1', [ '110228', '������������', '110229', '������������' ]);
a_t.add('0_2', [ '120100', '�������Ͻ��', '120200', '�������' ]);
a_t.add('0_2_0', [ '120101', '����к�ƽ��', '120102', '����кӶ���', '120103', '����к�����',
		'120104', '������Ͽ���', '120105', '����кӱ���', '120107', '�����������', '120109',
		'����д����', '120110', '����ж�����', '120112', '����н�����', '120113', '����б�����',
		'120115', '����б�����', '120106', '����к�����', '120108', '����к�����', '120111',
		'�����������', '120114', '�����������' ]);
a_t.add('0_2_1', [ '120223', '����о�����', '120221', '�����������', '120225', '����м���' ]);
a_t.add('0_3', [ '130200', '�ӱ�ʡ��ɽ��', '130300', '�ӱ�ʡ�ػʵ���', '130400', '�ӱ�ʡ������',
		'130100', '�ӱ�ʡʯ��ׯ��', '130500', '�ӱ�ʡ��̨��', '130600', '�ӱ�ʡ������', '131100',
		'�ӱ�ʡ��ˮ��', '130700', '�ӱ�ʡ�żҿ���', '130800', '�ӱ�ʡ�е���', '130900', '�ӱ�ʡ������',
		'131000', '�ӱ�ʡ�ȷ���' ]);
a_t.add('0_3_0',
		[ '130201', '�ӱ���ɽ��Ͻ��', '130203', '�ӱ���ɽ·����', '130204', '�ӱ���ɽ��ұ��',
				'130207', '�ӱ���ɽ������', '130208', '�ӱ���ɽ������', '130224', '�ӱ���ɽ������',
				'130225', '�ӱ���ɽ��ͤ��', '130229', '�ӱ���ɽ������', '130230', '�ӱ���ɽ�ƺ���',
				'130283', '�ӱ�ʡǨ����', '130202', '�ӱ���ɽ·����', '130205', '�ӱ���ɽ��ƽ��',
				'130223', '�ӱ���ɽ����', '130227', '�ӱ���ɽǨ����', '130281', '�ӱ���ɽ����' ]);
a_t.add('0_3_1', [ '130302', '�ӱ��ػʵ�������', '130303', '�ӱ��ػʵ�ɽ������', '130304',
		'�ӱ��ػʵ���������', '130322', '�ӱ��ػʵ�������', '130323', '�ӱ��ػʵ�������', '130301',
		'�ӱ��ػʵ���Ͻ��', '130321', '�ӱ��ػʵ���������������', '130324', '�ӱ��ػʵ�¬����' ]);
a_t.add('0_3_2', [ '130401', '�ӱ�������Ͻ��', '130403', '�ӱ�������̨��', '130404',
		'�ӱ�����������', '130421', '�ӱ�������', '130423', '�ӱ�����������', '130425', '�ӱ�����������',
		'130426', '�ӱ���������', '130428', '�ӱ�����������', '130429', '�ӱ�����������', '130431',
		'�ӱ�����������', '130432', '�ӱ�������ƽ��', '130434', '�ӱ�����κ��', '130435',
		'�ӱ�����������', '130481', '�ӱ������䰲��', '130402', '�ӱ�������ɽ��', '130406',
		'�ӱ�����������', '130424', '�ӱ������ɰ���', '130427', '�ӱ���������', '130430',
		'�ӱ���������', '130433', '�ӱ�����������' ]);
a_t.add('0_3_3', [ '130133', '�ӱ�ʯ��ׯ����', '130181', '�ӱ�ʯ��ׯ������', '130183',
		'�ӱ�ʯ��ׯ������', '130184', '�ӱ�ʯ��ׯ������', '130102', '�ӱ�ʯ��ׯ������', '130103',
		'�ӱ�ʯ��ׯ�Ŷ���', '130105', '�ӱ�ʯ��ׯ�»���', '130107', '�ӱ�ʯ��ׯ�������', '130108',
		'�ӱ�ʡʯ��ׯ��ԣ����', '130123', '�ӱ�ʯ��ׯ������', '130124', '�ӱ�ʯ��ׯ�����', '130126',
		'�ӱ�ʯ��ׯ������', '130127', '�ӱ�ʯ��ׯ������', '130129', '�ӱ�ʯ��ׯ�޻���', '130130',
		'�ӱ�ʯ��ׯ�޼���', '130131', '�ӱ�ʯ��ׯƽɽ��', '130101', '�ӱ�ʯ��ׯ��Ͻ��', '130104',
		'�ӱ�ʯ��ׯ������', '130121', '�ӱ�ʯ��ׯ������', '130125', '�ӱ�ʯ��ׯ������', '130128',
		'�ӱ�ʯ��ׯ������', '130132', '�ӱ�ʯ��ׯԪ����', '130182', '�ӱ�ʯ��ׯ޻����', '130185',
		'�ӱ�ʡʯ��ׯ¹Ȫ��' ]);
a_t.add('0_3_4', [ '130501', '�ӱ���̨��Ͻ��', '130502', '�ӱ���̨�Ŷ���', '130503',
		'�ӱ���̨������', '130521', '�ӱ���̨��', '130522', '�ӱ���̨�ٳ���', '130523', '�ӱ���̨������',
		'130524', '�ӱ���̨������', '130525', '�ӱ���̨¡Ң��', '130526', '�ӱ���̨����', '130527',
		'�ӱ���̨�Ϻ���', '130528', '�ӱ���̨������', '130529', '�ӱ���̨��¹��', '130530',
		'�ӱ���̨�º���', '130531', '�ӱ���̨������', '130532', '�ӱ���̨ƽ����', '130533',
		'�ӱ���̨����', '130534', '�ӱ���̨�����', '130535', '�ӱ���̨������', '130581',
		'�ӱ���̨�Ϲ���', '130582', '�ӱ���̨ɳ����' ]);
a_t.add('0_3_5', [ '130601', '�ӱ�������Ͻ��', '130602', '�ӱ�����������', '130603',
		'�ӱ�����������', '130604', '�ӱ�����������', '130621', '�ӱ�����������', '130622',
		'�ӱ�������Է��', '130623', '�ӱ�ʡ�������ˮ��', '130624', '�ӱ�ʡ�����и�ƽ��', '130626',
		'�ӱ�ʡ�����ж�����', '130627', '�ӱ�ʡ����������', '130628', '�ӱ�ʡ�����и�����', '130630',
		'�ӱ�ʡ�������Դ��', '130631', '�ӱ�ʡ������������', '130633', '�ӱ�ʡ����������', '130634',
		'�ӱ�ʡ������������', '130635', '�ӱ�ʡ���������', '130637', '�ӱ�ʡ�����в�Ұ��', '130638',
		'�ӱ�ʡ����������', '130682', '�ӱ�ʡ�����ж�����', '130683', '�ӱ�ʡ�����а�����', '130684',
		'�ӱ�ʡ�����и߱�����', '130625', '�ӱ�ʡ��������ˮ��', '130629', '�ӱ�ʡ�������ݳ���', '130632',
		'�ӱ�ʡ�����а�����', '130636', '�ӱ�ʡ������˳ƽ��', '130681', '�ӱ�ʡ������������' ]);
a_t.add('0_3_6', [ '131127', '�ӱ�ʡ��ˮ�о���', '131128', '�ӱ�ʡ��ˮ�и�����', '131181',
		'�ӱ�ʡ��ˮ�м�����', '131182', '�ӱ�ʡ��ˮ��������', '131101', '�ӱ�ʡ��ˮ����Ͻ��', '131102',
		'�ӱ�ʡ��ˮ���ҳ���', '131121', '�ӱ�ʡ��ˮ����ǿ��', '131122', '�ӱ�ʡ��ˮ��������', '131123',
		'�ӱ�ʡ��ˮ����ǿ��', '131124', '�ӱ�ʡ��ˮ��������', '131125', '�ӱ�ʡ��ˮ�а�ƽ��', '131126',
		'�ӱ�ʡ��ˮ�йʳ���' ]);
a_t.add('0_3_7', [ '130701', '�ӱ��żҿ���Ͻ��', '130702', '�ӱ��żҿ��Ŷ���', '130705',
		'�ӱ��żҿ�������', '130706', '�ӱ��żҿ��»�԰��', '130722', '�ӱ��żҿ��ű���', '130723',
		'�ӱ��żҿڿ�����', '130725', '�ӱ��żҿ�������', '130726', '�ӱ��żҿ�ε��', '130728',
		'�ӱ��żҿڻ�����', '130729', '�ӱ��żҿ���ȫ��', '130730', '�ӱ��żҿڻ�����', '130732',
		'�ӱ��żҿڳ����', '130733', '�ӱ��żҿڳ�����', '130703', '�ӱ��żҿ�������', '130721',
		'�ӱ��żҿ�������', '130724', '�ӱ��żҿڹ�Դ��', '130727', '�ӱ��żҿ���ԭ��', '130731',
		'�ӱ��żҿ���¹��' ]);
a_t.add('0_3_8', [ '130801', '�ӱ��е���Ͻ��', '130802', '�ӱ��е�˫����', '130804',
		'�ӱ��е�ӥ��Ӫ�ӿ���', '130821', '�ӱ��е���', '130823', '�ӱ��е�ƽȪ��', '130824',
		'�ӱ��е���ƽ��', '130826', '�ӱ��е·�������������', '130827', '�ӱ��е¿������������', '130828',
		'�ӱ��е�Χ�������ɹ���������', '130803', '�ӱ��е�˫����', '130822', '�ӱ��е���¡��', '130825',
		'�ӱ��е�¡����' ]);
a_t.add('0_3_9', [ '130901', '�ӱ�������Ͻ��', '130902', '�ӱ������»���', '130921',
		'�ӱ����ݲ���', '130922', '�ӱ���������', '130924', '�ӱ����ݺ�����', '130925', '�ӱ�������ɽ��',
		'130927', '�ӱ�������Ƥ��', '130928', '�ӱ�����������', '130930', '�ӱ������ϴ����������',
		'130981', '�ӱ����ݲ�ͷ��', '130983', '�ӱ����ݻ�����', '130984', '�ӱ����ݺӼ���',
		'130903', '�ӱ������˺���', '130923', '�ӱ����ݶ�����', '130926', '�ӱ�����������',
		'130929', '�ӱ���������', '130982', '�ӱ�����������' ]);
a_t.add('0_3_10', [ '131001', '�ӱ��ȷ���Ͻ��', '131002', '�ӱ��ȷ�������', '131022',
		'�ӱ��ȷ��̰���', '131023', '�ӱ��ȷ�������', '131025', '�ӱ��ȷ������', '131026',
		'�ӱ��ȷ��İ���', '131081', '�ӱ��ȷ�������', '131082', '�ӱ��ȷ�������', '131003',
		'�ӱ�ʡ�ȷ��й�����', '131024', '�ӱ��ȷ������', '131028', '�ӱ��ȷ��󳧻���������' ]);
a_t.add('0_4', [ '371400', 'ɽ��ʡ������', '370100', 'ɽ��ʡ������', '370200', 'ɽ��ʡ�ൺ��',
		'371000', 'ɽ��ʡ������', '371100', 'ɽ��ʡ������', '371200', 'ɽ��ʡ������', '370300',
		'ɽ��ʡ�Ͳ���', '370400', 'ɽ��ʡ��ׯ��', '370500', 'ɽ��ʡ��Ӫ��', '370600', 'ɽ��ʡ��̨��',
		'370700', 'ɽ��ʡΫ����', '370800', 'ɽ��ʡ������', '370900', 'ɽ��ʡ̩����', '371300',
		'ɽ��ʡ������', '371600', 'ɽ��ʡ������', '371700', 'ɽ��ʡ������', '371500', 'ɽ��ʡ�ĳ���' ]);
a_t.add('0_4_0', [ '371401', 'ɽ��ʡ��������Ͻ��', '371421', 'ɽ��ʡ����������', '371402',
		'ɽ��ʡ�����е³���', '371422', 'ɽ��ʡ������������', '371423', 'ɽ��ʡ������������', '371424',
		'ɽ��ʡ������������', '371425', 'ɽ��ʡ�����������', '371426', 'ɽ��ʡ������ƽԭ��', '371427',
		'ɽ��ʡ�������Ľ���', '371428', 'ɽ��ʡ�����������', '371481', 'ɽ��ʡ������', '371482',
		'ɽ��ʡ�����' ]);
a_t.add('0_4_1', [ '370124', 'ɽ������ƽ����', '370126', 'ɽ�������̺���', '370181',
		'ɽ������������', '370101', 'ɽ��������Ͻ��', '370102', 'ɽ������������', '370104',
		'ɽ�����ϻ�����', '370105', 'ɽ������������', '370113', 'ɽ��ʡ�����г�����', '370103',
		'ɽ������������', '370112', 'ɽ������������', '370125', 'ɽ�����ϼ�����' ]);
a_t.add('0_4_2', [ '370201', 'ɽ���ൺ��Ͻ��', '370202', 'ɽ���ൺ������', '370205',
		'ɽ���ൺ�ķ���', '370211', 'ɽ���ൺ�Ƶ���', '370213', 'ɽ��ʡ�ൺ�������', '370214',
		'ɽ��ʡ�ൺ�г�����', '370282', 'ɽ���ൺ��ī��', '370283', 'ɽ���ൺƽ����', '370203',
		'ɽ���ൺ�б���', '370212', 'ɽ���ൺ��ɽ��', '370281', 'ɽ���ൺ������', '370284',
		'ɽ���ൺ������', '370285', 'ɽ���ൺ������' ]);
a_t.add('0_4_3', [ '371081', 'ɽ�������ĵ���', '371001', 'ɽ��������Ͻ��', '371002',
		'ɽ������������', '371082', 'ɽ�������ٳ���', '371083', 'ɽ��������ɽ��' ]);
a_t.add('0_4_4', [ '371121', 'ɽ������������', '371101', 'ɽ��������Ͻ��', '371102',
		'ɽ�����ն�����', '371103', 'ɽ��ʡ�������ɽ��', '371122', 'ɽ����������' ]);
a_t.add('0_4_5', [ '371203', 'ɽ�����߸ֳ���', '371201', 'ɽ��������Ͻ��', '371202',
		'ɽ������������' ]);
a_t.add('0_4_6', [ '370301', 'ɽ���Ͳ���Ͻ��', '370302', 'ɽ���Ͳ��ʹ���', '370303',
		'ɽ���Ͳ��ŵ���', '370304', 'ɽ���Ͳ���ɽ��', '370305', 'ɽ���Ͳ�������', '370306',
		'ɽ���Ͳ��ܴ���', '370321', 'ɽ���Ͳ���̨��', '370322', 'ɽ���Ͳ�������', '370323',
		'ɽ���Ͳ���Դ��' ]);
a_t.add('0_4_7', [ '370401', 'ɽ����ׯ��Ͻ��', '370402', 'ɽ����ׯ������', '370403',
		'ɽ����ׯѦ����', '370404', 'ɽ����ׯỳ���', '370405', 'ɽ����ׯ̨��ׯ��', '370406',
		'ɽ����ׯɽͤ��', '370481', 'ɽ����ׯ������' ]);
a_t.add('0_4_8', [ '370501', 'ɽ����Ӫ��Ͻ��', '370502', 'ɽ����Ӫ��', '370503', 'ɽ����Ӫ�ӿ���',
		'370521', 'ɽ����Ӫ������', '370522', 'ɽ����Ӫ������', '370523', 'ɽ����Ӫ������' ]);
a_t.add('0_4_9', [ '370611', 'ɽ����̨��ɽ��', '370634', 'ɽ����̨������', '370683',
		'ɽ����̨������', '370686', 'ɽ��ʡ��ϼ��', '370601', 'ɽ����̨��Ͻ��', '370602',
		'ɽ����̨֥���', '370612', 'ɽ��ʡ��̨��Ĳƽ��', '370613', 'ɽ��ʡ��̨����ɽ��', '370681',
		'ɽ����̨������', '370682', 'ɽ����̨������', '370684', 'ɽ����̨������', '370685',
		'ɽ����̨��Զ��', '370687', 'ɽ��ʡ������' ]);
a_t.add('0_4_10', [ '370701', 'ɽ��Ϋ����Ͻ��', '370704', 'ɽ��Ϋ��������', '370725',
		'ɽ��Ϋ��������', '370783', 'ɽ��Ϋ���ٹ���', '370786', 'ɽ��ʡ������', '370702',
		'ɽ��Ϋ��Ϋ����', '370703', 'ɽ��Ϋ����ͤ��', '370705', 'ɽ��ʡΫ���п�����', '370724',
		'ɽ��Ϋ��������', '370781', 'ɽ��Ϋ��������', '370782', 'ɽ��Ϋ�������', '370784',
		'ɽ��ʡ������', '370785', 'ɽ��ʡ������' ]);
a_t.add('0_4_11', [ '370801', 'ɽ��������Ͻ��', '370826', 'ɽ������΢ɽ��', '370829',
		'ɽ������������', '370832', 'ɽ��������ɽ��', '370883', 'ɽ�������޳���', '370802',
		'ɽ������������', '370811', 'ɽ�������γ���', '370827', 'ɽ��������̨��', '370828',
		'ɽ������������', '370830', 'ɽ������������', '370831', 'ɽ��������ˮ��', '370881',
		'ɽ������������', '370882', 'ɽ������������' ]);
a_t.add('0_4_12', [ '370902', 'ɽ��̩��̩ɽ��', '370982', 'ɽ��̩����̩��', '370901',
		'ɽ��̩����Ͻ��', '370903', 'ɽ��ʡ̩���������', '370921', 'ɽ��̩��������', '370923',
		'ɽ��̩����ƽ��', '370983', 'ɽ��̩���ʳ���' ]);
a_t.add('0_4_13', [ '371312', 'ɽ��ʡ�����кӶ���', '371321', 'ɽ��ʡ������������', '371323',
		'ɽ��ʡ��������ˮ��', '371324', 'ɽ��ʡ�����в�ɽ��', '371325', 'ɽ��ʡ�����з���', '371327',
		'ɽ��ʡ������������', '371328', 'ɽ��ʡ������������', '371311', 'ɽ��ʡ��������ׯ��', '371322',
		'ɽ��ʡ������۰����', '371326', 'ɽ��ʡ������ƽ����', '371329', 'ɽ��ʡ������������', '371301',
		'ɽ��ʡ��������Ͻ��', '371302', 'ɽ��ʡ��������ɽ��' ]);
a_t.add('0_4_14', [ '371601', 'ɽ��ʡ��������Ͻ��', '371602', 'ɽ��ʡ�����б�����', '371621',
		'ɽ��ʡ�����л�����', '371622', 'ɽ��ʡ������������', '371623', 'ɽ��ʡ�����������', '371624',
		'ɽ��ʡ������մ����', '371625', 'ɽ��ʡ�����в�����', '371626', 'ɽ��ʡ��������ƽ��' ]);
a_t.add('0_4_15', [ '371724', 'ɽ��ʡ�����о�Ұ��', '371727', 'ɽ��ʡ�����ж�����', '371701',
		'ɽ��ʡ��������Ͻ��', '371702', 'ɽ��ʡ������ĵ����', '371721', 'ɽ��ʡ�����в���', '371722',
		'ɽ��ʡ�����е���', '371723', 'ɽ��ʡ�����г�����', '371725', 'ɽ��ʡ������۩����', '371726',
		'ɽ��ʡ������۲����', '371728', 'ɽ��ʡ�����ж�����' ]);
a_t.add('0_4_16', [ '371581', 'ɽ��ʡ������', '371501', 'ɽ��ʡ�ĳ�����Ͻ��', '371502',
		'ɽ��ʡ�ĳ��ж�������', '371521', 'ɽ��ʡ�ĳ���������', '371522', 'ɽ��ʡ�ĳ���ݷ��', '371523',
		'ɽ��ʡ�ĳ�����ƽ��', '371524', 'ɽ��ʡ�ĳ��ж�����', '371525', 'ɽ��ʡ�ĳ��й���', '371526',
		'ɽ��ʡ�ĳ��и�����' ]);
a_t.add('0_5', [ '230400', '������ʡ�׸���', '230500', '������ʡ˫Ѽɽ��', '230600',
		'������ʡ������', '230700', '������ʡ������', '230900', '������ʡ��̨����', '231000',
		'������ʡĵ������', '231200', '������ʡ�绯��', '230200', '������ʡ���������', '232700',
		'������ʡ���˰������', '230100', '������ʡ��������', '230300', '������ʡ������', '230800',
		'������ʡ��ľ˹��', '231100', '������ʡ�ں���' ]);
a_t.add('0_5_0', [ '230401', '�������׸���Ͻ��', '230402', '�������׸�������', '230403',
		'�������׸ڹ�ũ��', '230404', '�������׸���ɽ��', '230405', '�������׸��˰���', '230406',
		'�������׸ڶ�ɽ��', '230407', '�������׸���ɽ��', '230421', '�������׸��ܱ���', '230422',
		'�������׸������' ]);
a_t.add('0_5_1', [ '230501', '������˫Ѽɽ��Ͻ��', '230502', '������˫Ѽɽ��ɽ��', '230503',
		'������˫Ѽɽ�붫��', '230505', '������˫Ѽɽ�ķ�̨��', '230506', '������˫Ѽɽ��ɽ��', '230521',
		'������˫Ѽɽ������', '230522', '������˫Ѽɽ������', '230523', '������˫Ѽɽ������', '230524',
		'������˫Ѽɽ�ĺ���' ]);
a_t.add('0_5_2', [ '230601', '������������Ͻ��', '230602', '��������������ͼ��', '230603',
		'����������������', '230604', '�����������ú�·��', '230605', '��������������', '230606',
		'�����������ͬ��', '230621', '����������������', '230622', '������������Դ��', '230624',
		'����������Ŷ������ɹ���������', '230623', '�����������ֵ���' ]);
a_t.add('0_5_3', [ '230701', '������������Ͻ��', '230703', '�����������ϲ���', '230704',
		'�����������Ѻ���', '230706', '����������������', '230707', '����������������', '230708',
		'������������Ϫ��', '230710', '������������Ӫ��', '230711', '�����������������', '230713',
		'����������������', '230714', '������������������', '230716', '�����������ϸ�����', '230722',
		'����������������', '230781', '����������������', '230702', '����������������', '230705',
		'����������������', '230709', '������������ɽ����', '230712', '������������������', '230715',
		'����������������' ]);
a_t.add('0_5_4', [ '230902', '��������̨��������', '230903', '��������̨����ɽ��', '230921',
		'��������̨�Ӳ�����', '230901', '��������̨����Ͻ��', '230904', '��������̨�����Ӻ���' ]);
a_t.add('0_5_5', [ '231001', '������ĵ������Ͻ��', '231003', '������ĵ����������', '231004',
		'������ĵ����������', '231024', '������ĵ����������', '231025', '������ĵ�����ֿ���', '231081',
		'������ĵ������Һ���', '231084', '������ĵ����������', '231085', '������ʡ������', '231002',
		'������ĵ����������', '231005', '������ĵ����������', '231083', '������ĵ����������' ]);
a_t.add('0_5_6', [ '231201', '������ʡ�绯����Ͻ��', '231221', '������ʡ�绯��������', '231222',
		'������ʡ�绯��������', '231223', '������ʡ�绯�������', '231225', '������ʡ�绯����ˮ��', '231226',
		'������ʡ�绯��������', '231281', '������ʡ������', '231283', '������ʡ������', '231202',
		'������ʡ�绯�б�����', '231224', '������ʡ�绯���찲��', '231282', '������ʡ�ض���' ]);
a_t.add('0_5_7', [ '230201', '���������������Ͻ��', '230203', '�������������������', '230204',
		'�������������������', '230205', '�����������������Ϫ��', '230207', '�����������������ɽ��',
		'230208', '�������������÷��˹���Ӷ�����', '230221', '�������������������', '230223',
		'�������������������', '230225', '�������������������', '230227', '���������������ԣ��', '230230',
		'��������������˶���', '230231', '���������������Ȫ��', '230281', '�������������ګ����', '230202',
		'���������������ɳ��', '230206', '�����������������������', '230224', '�������������̩����',
		'230229', '���������������ɽ��' ]);
a_t.add('0_5_8', [ '232721', '���������˰��������', '232722', '���������˰���������', '232723',
		'���������˰���Į����' ]);
a_t.add('0_5_9', [ '230101', '��������������Ͻ��', '230102', '������������������', '230103',
		'�������������ϸ���', '230106', '�������������㷻��', '230107', '������������������', '230109',
		'������ʡ���������ɱ���', '230111', '������ʡ�������к�����', '230123', '������������������',
		'230125', '����������������', '230126', '������ʡ�������а�����', '230127', '������ʡ��������ľ����',
		'230129', '������ʡ��������������', '230181', '������������������', '230182',
		'������ʡ��������˫����', '230184', '������ʡ���������峣��', '230104', '������������������',
		'230108', '������������ƽ����', '230124', '������������������', '230128', '������ʡ��������ͨ����',
		'230183', '������ʡ����������־��' ]);
a_t.add('0_5_10', [ '230306', '�������������Ӻ���', '230307', '������������ɽ��', '230321',
		'����������������', '230381', '������ʡ������', '230382', '������ʡ��ɽ��', '230301',
		'������������Ͻ��', '230302', '����������������', '230304', '�����������ε���', '230305',
		'����������������', '230303', '������������ɽ��' ]);
a_t.add('0_5_11', [ '230801', '��������ľ˹��Ͻ��', '230802', '��������ľ˹������', '230804',
		'��������ľ˹ǰ����', '230805', '��������ľ˹������', '230811', '��������ľ˹����', '230826',
		'��������ľ˹�봨��', '230828', '��������ľ˹��ԭ��', '230881', '��������ľ˹ͬ����', '230882',
		'��������ľ˹������', '230803', '��������ľ˹������', '230822', '��������ľ˹������', '230833',
		'��������ľ˹��Զ��' ]);
a_t.add('0_5_12', [ '231101', '�������ں���Ͻ��', '231102', '�������ںӰ�����', '231123',
		'�������ں�ѷ����', '231124', '�������ں�������', '231181', '�������ںӱ�����', '231121',
		'�������ں��۽���', '231182', '�������ں����������' ]);
a_t.add('0_6', [ '210400', '����ʡ��˳��', '210500', '����ʡ��Ϫ��', '210600', '����ʡ������',
		'210700', '����ʡ������', '210800', '����ʡӪ����', '210900', '����ʡ������', '211300',
		'����ʡ������', '210100', '����ʡ������', '210200', '����ʡ������', '210300', '����ʡ��ɽ��',
		'211000', '����ʡ������', '211100', '����ʡ�̽���', '211200', '����ʡ������', '211400',
		'����ʡ��«����' ]);
a_t.add('0_6_0', [ '210401', '������˳��Ͻ��', '210402', '������˳�¸���', '210403',
		'������˳������', '210404', '������˳������', '210411', '������˳˳����', '210421', '������˳��',
		'210422', '������˳�±�����������', '210423', '������˳��ԭ����������' ]);
a_t.add('0_6_1', [ '210501', '������Ϫ��Ͻ��', '210502', '������Ϫƽɽ��', '210503',
		'������ϪϪ����', '210504', '������Ϫ��ɽ��', '210505', '����ʡ��Ϫ���Ϸ���', '210521',
		'������Ϫ����������', '210522', '������Ϫ��������������' ]);
a_t.add('0_6_2', [ '210601', '����������Ͻ��', '210602', '��������Ԫ����', '210603',
		'��������������', '210604', '������������', '210624', '���������������������', '210681',
		'��������������', '210682', '����ʡ�����' ]);
a_t.add('0_6_3', [ '210701', '����������Ͻ��', '210702', '�������ݹ�����', '210703',
		'�������������', '210711', '��������̫����', '210727', '������������', '210782', '����ʡ������',
		'210726', '�������ݺ�ɽ��', '210781', '���������躣��' ]);
a_t.add('0_6_4', [ '210802', '����Ӫ��վǰ��', '210803', '����Ӫ��������', '210811',
		'����Ӫ���ϱ���', '210881', '����Ӫ�ڸ�����', '210801', '����Ӫ����Ͻ��', '210804',
		'����Ӫ������Ȧ��', '210882', '����Ӫ�ڴ�ʯ����' ]);
a_t.add('0_6_5', [ '210901', '����������Ͻ��', '210903', '��������������', '210904',
		'��������̫ƽ��', '210911', '��������ϸ����', '210921', '���������ɹ���������', '210922',
		'��������������', '210902', '�������º�����', '210905', '���������������' ]);
a_t.add('0_6_6', [ '211301', '����������Ͻ��', '211303', '��������������', '211321', '����������',
		'211324', '�������������������ɹ�������', '211381', '����������Ʊ��', '211382', '����������Դ��',
		'211302', '��������˫����', '211322', '����������ƽ��' ]);
a_t.add('0_6_7', [ '210102', '����������ƽ��', '210103', '�������������', '210105',
		'���������ʹ���', '210106', '��������������', '210112', '��������������', '210113',
		'���������³�����', '210122', '��������������', '210123', '����������ƽ��', '210181',
		'��������������', '210101', '����������Ͻ��', '210104', '������������', '210111',
		'���������ռ�����', '210114', '���������ں���', '210124', '��������������' ]);
a_t.add('0_6_8', [ '210202', '����������ɽ��', '210203', '��������������', '210211',
		'���������ʾ�����', '210212', '����������˳����', '210224', '��������������', '210281',
		'���������߷�����', '210283', '��������ׯ����', '210201', '����������Ͻ��', '210204',
		'��������ɳ�ӿ���', '210213', '��������������', '210282', '����������������' ]);
a_t.add('0_6_9', [ '210311', '������ɽǧɽ��', '210321', '������ɽ̨����', '210323',
		'������ɽ�������������', '210381', '������ɽ������', '210302', '������ɽ������', '210303',
		'������ɽ������', '210301', '������ɽ��Ͻ��', '210304', '������ɽ��ɽ��' ]);
a_t.add('0_6_10', [ '211001', '����������Ͻ��', '211002', '��������������', '211004',
		'����������ΰ��', '211005', '����������������', '211021', '����������', '211081', '����ʡ������',
		'211003', '����������ʥ��', '211011', '��������̫�Ӻ���' ]);
a_t.add('0_6_11', [ '211101', '�����̽���Ͻ��', '211102', '�����̽�˫̨����', '211121',
		'�����̽�������', '211122', '�����̽���ɽ��', '211103', '�����̽���¡̨��' ]);
a_t.add('0_6_12', [ '211201', '����������Ͻ��', '211202', '��������������', '211221',
		'����������', '211224', '���������ͼ��', '211281', '�����������ɽ��', '211204',
		'�������������', '211223', '��������������', '211282', '�������뿪ԭ��' ]);
a_t.add('0_6_13', [ '211401', '������«����Ͻ��', '211402', '������«����ɽ��', '211404',
		'������«����Ʊ��', '211421', '������«��������', '211481', '������«���˳���', '211403',
		'������«��������', '211422', '������«��������' ]);
a_t.add('0_7', [ '530100', '����ʡ������', '530300', '����ʡ������', '530700', '����ʡ������',
		'530800', '����ʡ˼é��', '530900', '����ʡ�ٲ���', '530400', '����ʡ��Ϫ��', '530500',
		'����ʡ��ɽ��', '530600', '����ʡ��ͨ��', '532300', '����ʡ��������������', '532900',
		'����ʡ�������������', '533100', '����ʡ�º���徰����������', '533400', '����ʡ�������������',
		'532500', '����ʡ��ӹ���������������', '532600', '����ʡ��ɽ׳������������', '532800',
		'����ʡ��˫���ɴ���������', '533300', '����ʡŭ��������������' ]);
a_t.add('0_7_0', [ '530101', '����������Ͻ��', '530102', '���������廪��', '530103',
		'��������������', '530111', '���������ٶ���', '530112', '����������ɽ��', '530113',
		'����ʡ�����ж�����', '530121', '���������ʹ���', '530122', '��������������', '530124',
		'��������������', '530125', '��������������', '530126', '��������ʯ������������', '530127',
		'��������������', '530128', '��������»Ȱ��������������', '530129', '����ʡ������Ѱ���������������',
		'530181', '����ʡ������' ]);
a_t.add('0_7_1', [ '530301', '����ʡ��������Ͻ��', '530302', '����ʡ������������', '530321',
		'����ʡ������������', '530323', '����ʡ������ʦ����', '530324', '����ʡ��������ƽ��', '530326',
		'����ʡ�����л�����', '530328', '����ʡ������մ����', '530381', '����ʡ������', '530322',
		'����ʡ������½����', '530325', '����ʡ�����и�Դ��' ]);
a_t.add('0_7_2', [ '530702', '����ʡ�����йų���', '530721', '����ʡ����������������������', '530722',
		'����ʡ��������ʤ��', '530724', '����ʡ��������������������', '530701', '����ʡ��������Ͻ��',
		'530723', '����ʡ�����л�ƺ��' ]);
a_t.add('0_7_3', [ '530801', '����ʡ˼é����Ͻ��', '530821', '����ʡ˼é���ն�����������������',
		'530822', '����ʡ˼é��ī��������������', '530823', '����ʡ˼é�о�������������', '530824',
		'����ʡ˼é�о��ȴ�������������', '530825', '����ʡ˼é���������������������������', '530827',
		'����ʡ˼é��������������������������', '530828', '����ʡ˼é������������������', '530829',
		'����ʡ˼é����������������', '530802', '����ʡ˼é�д�����', '530826', '����ʡ˼é�н��ǹ���������������' ]);
a_t.add('0_7_4', [ '530902', '����ʡ�ٲ���������', '530921', '����ʡ�ٲ��з�����', '530922',
		'����ʡ�ٲ�������', '530924', '����ʡ�ٲ�������', '530925', '����ʡ�ٲ���˫�����������岼�������������',
		'530926', '����ʡ�ٲ��й����������������', '530927', '����ʡ�ٲ��в�Դ����������', '530901',
		'����ʡ�ٲ�����Ͻ��', '530923', '����ʡ�ٲ���������' ]);
a_t.add('0_7_5', [ '530401', '����ʡ��Ϫ����Ͻ��', '530402', '����ʡ��Ϫ�к�����', '530422',
		'����ʡ��Ϫ�гν���', '530423', '����ʡ��Ϫ��ͨ����', '530425', '����ʡ��Ϫ��������', '530426',
		'����ʡ��Ϫ�ж�ɽ����������', '530427', '����ʡ��Ϫ����ƽ�������������', '530428',
		'����ʡ��Ϫ��Ԫ���������������������', '530421', '����ʡ��Ϫ�н�����', '530424', '����ʡ��Ϫ�л�����' ]);
a_t.add('0_7_6', [ '530501', '����ʡ��ɽ����Ͻ��', '530502', '����ʡ��ɽ��¡����', '530521',
		'����ʡ��ɽ��ʩ����', '530523', '����ʡ��ɽ��������', '530524', '����ʡ��ɽ�в�����', '530522',
		'����ʡ��ɽ���ڳ���' ]);
a_t.add('0_7_7', [ '530601', '����ʡ��ͨ����Ͻ��', '530602', '����ʡ��ͨ��������', '530621',
		'����ʡ��ͨ��³����', '530623', '����ʡ��ͨ���ν���', '530624', '����ʡ��ͨ�д����', '530626',
		'����ʡ��ͨ���罭��', '530627', '����ʡ��ͨ��������', '530628', '����ʡ��ͨ��������', '530630',
		'����ʡ��ͨ��ˮ����', '530622', '����ʡ��ͨ���ɼ���', '530625', '����ʡ��ͨ��������', '530629',
		'����ʡ��ͨ��������' ]);
a_t.add('0_7_8', [ '532301', '���ϳ�����', '532322', '���ϳ���˫����', '532323', '���ϳ���Ĳ����',
		'532324', '���ϳ����ϻ���', '532325', '���ϳ���Ҧ����', '532326', '���ϳ��۴�Ҧ��',
		'532327', '���ϳ���������', '532328', '���ϳ���Ԫı��', '532329', '���ϳ����䶨��',
		'532331', '���ϳ���»����' ]);
a_t.add('0_7_9', [ '532923', '���ϴ���������', '532927', '���ϴ���Ρɽ�������������', '532930',
		'���ϴ����Դ��', '532901', '���ϴ�����', '532922', '���ϴ����������������', '532924',
		'���ϴ��������', '532925', '���ϴ����ֶ���', '532926', '���ϴ����Ͻ�����������', '532928',
		'���ϴ�����ƽ��', '532929', '���ϴ���������', '532931', '���ϴ�������', '532932',
		'���ϴ��������' ]);
a_t.add('0_7_10', [ '533122', '���ϵº�������', '533102', '���ϵº�������', '533103',
		'����ʡº����', '533123', '���ϵº�ӯ����', '533124', '���ϵº�¤����' ]);
a_t.add('0_7_11', [ '533421', '���ϵ������������', '533422', '���ϵ��������', '533423',
		'���ϵ��졡ά��������������' ]);
a_t.add('0_7_12', [ '532501', '���Ϻ�Ӹ�����', '532502', '���Ϻ�ӿ�Զ��', '532522',
		'���Ϻ��������', '532523', '���Ϻ����������������', '532524', '���Ϻ�ӽ�ˮ��', '532525',
		'���Ϻ��ʯ����', '532526', '���Ϻ��������', '532527', '���Ϻ��������', '532528',
		'���Ϻ��Ԫ����', '532529', '���Ϻ����', '532530', '���Ϻ�ӽ�ƽ�����������������', '532531',
		'���Ϻ���̴���', '532532', '���Ϻ�Ӻӿ�����������' ]);
a_t.add('0_7_13', [ '532621', '������ɽ��', '532622', '������ɽ��ɽ��', '532623',
		'������ɽ������', '532624', '������ɽ��������', '532625', '������ɽ�����', '532626',
		'������ɽ����', '532627', '������ɽ������', '532628', '������ɽ������' ]);
a_t.add('0_7_14', [ '532801', '������˫���ɾ�����', '532822', '������˫�����º���', '532823',
		'������˫����������' ]);
a_t.add('0_7_15', [ '533321', '����ŭ������ˮ��', '533323', '����ŭ����������', '533324',
		'����ŭ������ɽ������ŭ��������', '533325', '����ŭ������ƺ����������������' ]);
a_t.add('0_8', [ '520100', '����ʡ������', '520200', '����ʡ����ˮ��', '520300', '����ʡ������',
		'520400', '����ʡ��˳��', '522200', '����ʡ��˳��ͭ�ʵ���', '522300',
		'����ʡ��˳��ǭ���ϲ���������������', '522400', '����ʡ��˳�бϽڵ���', '522700',
		'����ʡ��˳��ǭ�ϲ���������������', '522600', '����ʡ��˳��ǭ�������嶱��������' ]);
a_t.add('0_8_0', [ '520101', '���ݹ�����Ͻ��', '520102', '���ݹ���������', '520103',
		'���ݹ���������', '520111', '���ݹ�����Ϫ��', '520112', '���ݹ����ڵ���', '520113',
		'���ݹ���������', '520114', '����ʡ������С����', '520121', '����ʡ�����п�����', '520122',
		'����ʡ������Ϣ����', '520123', '����ʡ������������', '520181', '����ʡ������' ]);
a_t.add('0_8_1', [ '520203', '��������ˮ��֦����', '520221', '��������ˮˮ����', '520201',
		'��������ˮ��ɽ��', '520222', '����ʡ����ˮ������' ]);
a_t.add('0_8_2', [ '520301', '����ʡ��������Ͻ��', '520303', '����ʡ�����л㴨��', '520321',
		'����ʡ������������', '520322', '����ʡ������ͩ����', '520324', '����ʡ������������', '520325',
		'����ʡ�����е�������������������', '520326', '����ʡ������������������������', '520327',
		'����ʡ�����з����', '520329', '����ʡ������������', '520330', '����ʡ������ϰˮ��', '520382',
		'����ʡ�ʻ���', '520302', '����ʡ�����к컨����', '520323', '����ʡ������������', '520328',
		'����ʡ��������̶��', '520381', '����ʡ��ˮ��' ]);
a_t.add('0_8_3',
		[ '520402', '����ʡ��˳��������', '520421', '����ʡ��˳��ƽ����', '520422', '����ʡ��˳���ն���',
				'520424', '����ʡ��˳�й��벼��������������', '520425', '����ʡ��˳���������岼����������',
				'520401', '����ʡ��˳����Ͻ��', '520423', '����ʡ��˳����������������������' ]);
a_t.add('0_8_4', [ '522201', '����ͭ����', '522223', '����ͭ����������������', '522224',
		'����ͭ��ʯ����', '522226', '����ͭ��ӡ������������������', '522227', '����ͭ�ʵ½���', '522228',
		'����ͭ���غ�������������', '522230', '����ͭ����ɽ����', '522222', '����ͭ�ʽ�����', '522225',
		'����ͭ��˼����', '522229', '����ͭ����������������' ]);
a_t.add('0_8_5', [ '522301', '����ǭ��������', '522323', '����ǭ���հ���', '522324',
		'����ǭ����¡��', '522326', '����ǭ��������', '522327', '����ǭ�������', '522322',
		'����ǭ��������', '522325', '����ǭ�������', '522328', '����ǭ��������' ]);
a_t.add('0_8_6', [ '522401', '���ݱϽ���', '522423', '���ݱϽ�ǭ����', '522424', '���ݱϽڽ�ɳ��',
		'522426', '���ݱϽ���Ӻ��', '522427', '���ݱϽ����������������������', '522428', '���ݱϽں�����',
		'522422', '���ݱϽڴ���', '522425', '���ݱϽ�֯����' ]);
a_t.add('0_8_7', [ '522701', '����ǭ�϶�����', '522702', '����ʡ��Ȫ��', '522722',
		'����ǭ������', '522723', '����ǭ�Ϲ���', '522725', '����ǭ���Ͱ���', '522726',
		'����ǭ�϶�ɽ��', '522727', '����ǭ��ƽ����', '522728', '����ǭ���޵���', '522729',
		'����ǭ�ϳ�˳��', '522730', '����ǭ��������', '522731', '����ǭ�ϻ�ˮ��', '522732',
		'����ǭ������ˮ��������' ]);
a_t.add('0_8_8', [ '522601', '����ǭ��������', '522622', '����ǭ����ƽ��', '522624',
		'����ǭ��������', '522625', '����ǭ����Զ��', '522627', '����ǭ��������', '522628',
		'����ǭ��������', '522630', '����ǭ��̨����', '522631', '����ǭ����ƽ��', '522633',
		'����ǭ���ӽ���', '522634', '����ǭ����ɽ��', '522636', '����ǭ����կ��', '522623',
		'����ǭ��ʩ����', '522626', '����ǭ��᯹���', '522629', '����ǭ��������', '522632',
		'����ǭ���Ž���', '522635', '����ǭ���齭��' ]);
a_t.add('0_9', [ '513400', '��ɽ����������', '511300', '�ϳ���', '511400', 'üɽ��',
		'511500', '�˱���', '511600', '�㰲��', '511700', '������', '511800', '��Դ���Ű���',
		'511900', '������', '512000', '������', '513300', '���β���������', '510100',
		'�Ĵ�ʡ�ɶ���', '510500', '������', '510600', '������', '510700', '������', '511000',
		'�ڽ���', '510300', '�Թ���', '510400', '��֦����', '510800', '��Ԫ��', '510900',
		'������', '511100', '��ɽ��', '513200', '���Ӳ���Ǽ��������' ]);
a_t.add('0_9_0', [ '513401', '�Ĵ���ɽ������', '513422', '�Ĵ���ɽľ�����������', '513423',
		'�Ĵ���ɽ��Դ��', '513424', '�Ĵ���ɽ�²���', '513425', '�Ĵ���ɽ������', '513426',
		'�Ĵ���ɽ�ᶫ��', '513427', '�Ĵ���ɽ������', '513428', '�Ĵ���ɽ�ո���', '513429',
		'�Ĵ���ɽ������', '513430', '�Ĵ���ɽ������', '513431', '�Ĵ���ɽ�Ѿ���', '513432',
		'�Ĵ���ɽϲ����', '513433', '�Ĵ���ɽ������', '513434', '�Ĵ���ɽԽ����', '513435',
		'�Ĵ���ɽ������', '513436', '�Ĵ���ɽ������', '513437', '�Ĵ���ɽ�ײ���' ]);
a_t.add('0_9_1', [ '511301', '�Ĵ��ϳ���Ͻ��', '511302', '�Ĵ��ϳ�˳����', '511303',
		'�Ĵ��ϳ��ƺ��', '511304', '�Ĵ��ϳ������', '511321', '�Ĵ��ϳ��ϲ���', '511322',
		'�Ĵ��ϳ�Ӫɽ��', '511323', '�Ĵ��ϳ����', '511324', '�Ĵ��ϳ���¤��', '511325',
		'�Ĵ��ϳ�������', '511381', '�Ĵ��ϳ�������' ]);
a_t.add('0_9_2', [ '511401', 'üɽ����Ͻ��', '511402', 'üɽ�ж�����', '511421', 'üɽ��������',
		'511422', 'üɽ����ɽ��', '511423', 'üɽ�к�����', '511424', 'üɽ�е�����', '511425',
		'üɽ��������' ]);
a_t.add('0_9_3', [ '511501', '�˱�����Ͻ��', '511521', '�˱����˱���', '511522', '�˱�����Ϫ��',
		'511524', '�˱��г�����', '511526', '�˱�������', '511527', '�˱���������', '511529',
		'�˱�����ɽ��', '511502', '�˱��д�����', '511523', '�˱��н�����', '511525', '�˱��и���',
		'511528', '�˱���������' ]);
a_t.add('0_9_4', [ '511602', '�㰲�й㰲��', '511622', '�㰲����ʤ��', '511623', '�㰲����ˮ��',
		'511601', '�㰲����Ͻ��', '511621', '�㰲��������', '511681', '������' ]);
a_t.add('0_9_5', [ '511701', '��������Ͻ��', '511721', '�����д���', '511723', '�����п�����',
		'511724', '�����д�����', '511781', '��Դ��', '511702', '������ͨ����', '511722',
		'������������', '511725', '����������' ]);
a_t.add('0_9_6', [ '511802', '��Դ�������', '511822', '��Դ��������', '511823', '��Դ�к�Դ��',
		'511825', '��Դ����ȫ��', '511826', '��Դ��«ɽ��', '511801', '��Դ����Ͻ��', '511821',
		'��Դ����ɽ��', '511824', '��Դ��ʯ����', '511827', '��Դ�б�����' ]);
a_t.add('0_9_7', [ '511901', '��������Ͻ��', '511921', '������ͨ����', '511923', '������ƽ����',
		'511902', '�����а�����', '511922', '�������Ͻ���' ]);
a_t.add('0_9_8', [ '512002', '�������㽭��', '512021', '�����а�����', '512081', '������',
		'512001', '��������Ͻ��', '512022', '������������' ]);
a_t.add('0_9_9', [ '513322', '�Ĵ���������', '513323', '�Ĵ����ε�����', '513325',
		'�Ĵ������Ž���', '513326', '�Ĵ����ε�����', '513328', '�Ĵ�������', '513329', '�Ĵ�����������',
		'513331', '�Ĵ����ΰ�����', '513332', '�Ĵ�����ʯ����', '513334', '�Ĵ�����������',
		'513335', '�Ĵ����ΰ�����', '513337', '�Ĵ����ε�����', '513338', '�Ĵ����ε�����',
		'513321', '�Ĵ����ο�����', '513324', '�Ĵ����ξ�����', '513327', '�Ĵ�����¯����',
		'513330', '�Ĵ����ε¸���', '513333', '�Ĵ�����ɫ����', '513336', '�Ĵ����������' ]);
a_t.add('0_9_10', [ '510101', '�Ĵ��ɶ���Ͻ��', '510104', '�Ĵ��ɶ�������', '510105',
		'�Ĵ��ɶ�������', '510106', '�Ĵ��ɶ���ţ��', '510107', '�Ĵ��ɶ������', '510112',
		'�Ĵ��ɶ���Ȫ����', '510113', '�Ĵ��ɶ���׽���', '510115', '�ɶ����½���', '510121',
		'�Ĵ��ɶ�������', '510124', '�Ĵ��ɶ�ۯ��', '510129', '�Ĵ��ɶ�������', '510132',
		'�Ĵ��ɶ��½���', '510181', '�Ĵ��ɶ���������', '510183', '������', '510184', '������',
		'510108', '�Ĵ��ɶ��ɻ���', '510114', '�ɶ����¶���', '510122', '�Ĵ��ɶ�˫����', '510131',
		'�Ĵ��ɶ��ֽ���', '510182', '�Ĵ��ɶ�������' ]);
a_t.add('0_9_11', [ '510501', '�Ĵ�������Ͻ��', '510503', '��������Ϫ��', '510504',
		'����������̶��', '510522', '�Ĵ����ݺϽ���', '510524', '�Ĵ�����������', '510502',
		'�Ĵ������н�����', '510521', '�Ĵ���������', '510525', '�Ĵ����ݹ�����' ]);
a_t.add('0_9_12', [ '510601', '�Ĵ�������Ͻ��', '510623', '�Ĵ������н���', '510626',
		'�������޽���', '510682', 'ʲ����', '510603', '�����������', '510681', '�Ĵ������㺺��',
		'510683', '������' ]);
a_t.add('0_9_13', [ '510701', '�Ĵ�������Ͻ��', '510704', '�Ĵ�����������', '510722',
		'�Ĵ�������̨��', '510724', '�Ĵ���������', '510725', '�Ĵ�����������', '510727',
		'�Ĵ�����ƽ����', '510781', '�Ĵ�����������', '510703', '�Ĵ�����������', '510723',
		'�Ĵ�������ͤ��', '510726', '�Ĵ���������Ǽ��������' ]);
a_t.add('0_9_14', [ '511002', '�Ĵ��ڽ�������', '511011', '�Ĵ��ڽ�������', '511025',
		'�Ĵ��ڽ�������', '511028', '�Ĵ��ڽ�¡����', '511001', '�Ĵ��ڽ���Ͻ��', '511024',
		'�Ĵ��ڽ���Զ��' ]);
a_t.add('0_9_15', [ '510301', '�Ĵ��Թ���Ͻ��', '510303', '�Ĵ��Թ�������', '510304',
		'�Ĵ��Թ�����', '510321', '�Ĵ��Թ�����', '510322', '�Ĵ��Թ���˳��', '510302',
		'�Ĵ��Թ���������', '510311', '�Ĵ��Թ���̲��' ]);
a_t.add('0_9_16', [ '510401', '�Ĵ���֦����Ͻ��', '510402', '�Ĵ���֦������', '510411',
		'�Ĵ���֦���ʺ���', '510421', '�Ĵ���֦��������', '510403', '�Ĵ���֦������', '510422',
		'�Ĵ���֦���α���' ]);
a_t.add('0_9_17', [ '510801', '�Ĵ���Ԫ��Ͻ��', '510802', '�Ĵ���Ԫ������', '510812',
		'�Ĵ���Ԫ������', '510821', '�Ĵ���Ԫ������', '510823', '�Ĵ���Ԫ������', '510824',
		'�Ĵ���Ԫ��Ϫ��', '510811', '�Ĵ���ԪԪ����', '510822', '�Ĵ���Ԫ�ന��' ]);
a_t.add('0_9_18',
		[ '510901', '�Ĵ�������Ͻ��', '510904', '�����а�����', '510921', '�Ĵ�������Ϫ��',
				'510923', '�����д�Ӣ��', '510903', '�����д�ɽ��', '510922', '�Ĵ����������' ]);
a_t.add('0_9_19', [ '511101', '�Ĵ���ɽ��Ͻ��', '511102', '�Ĵ���ɽ������', '511111',
		'�Ĵ���ɽɳ����', '511112', '�Ĵ���ɽ��ͨ����', '511113', '�Ĵ���ɽ��ں���', '511123',
		'�Ĵ���ɽ��Ϊ��', '511124', '�Ĵ���ɽ������', '511126', '�Ĵ���ɽ�н���', '511129',
		'�Ĵ���ɽ�崨��', '511132', '�Ĵ���ɽ�������������', '511133', '�Ĵ���ɽ�������������', '511181',
		'�Ĵ���ɽ��üɽ��' ]);
a_t.add('0_9_20', [ '513221', '�Ĵ������봨��', '513222', '�Ĵ���������', '513224',
		'�Ĵ�����������', '513225', '�Ĵ����Ӿ�կ����', '513227', '�Ĵ�����С����', '513228',
		'�Ĵ����Ӻ�ˮ��', '513230', '�Ĵ�����������', '513231', '�Ĵ�������', '513233', '�Ĵ����Ӻ�ԭ��',
		'513223', '�Ĵ�����ï��', '513226', '�Ĵ����ӽ���', '513229', '�Ĵ������������',
		'513232', '�Ĵ�������������' ]);
a_t.add('0_10', [ '460100', '����ʡ������', '460200', '����ʡ������', '469000',
		'����ʡʡֱϽ�ؼ�������λ' ]);
a_t.add('0_10_0', [ '460105', '����ʡ��������Ӣ��', '460106', '����ʡ������������', '460107',
		'����ʡ��������ɽ��', '460101', '���Ϻ�����Ͻ��', '460108', '����ʡ������������' ]);
a_t.add('0_10_1', [ '460201', '����������Ͻ��' ]);
a_t.add('0_10_2', [ '469001', '����ʡ��ָɽ��', '469002', '����ʡ����', '469005',
		'����ʡ�Ĳ���', '469006', '����ʡ������', '469025', '����ʡ������', '469026', '����ʡ�Ͳ���',
		'469028', '����ʡ�ٸ���', '469030', '����ʡ��ɳ����������', '469031', '����ʡ��������������',
		'469033', '����ʡ�ֶ�����������', '469035', '����ʡ��ͤ��������������', '469036',
		'����ʡ������������������', '469037', '����ʡ��ɳȺ��', '469039', '����ʡ��ɳȺ���ĵ������亣��',
		'469003', '����ʡ������', '469007', '����ʡ������', '469027', '����ʡ������', '469034',
		'����ʡ��ˮ����������', '469038', '����ʡ��ɳȺ��' ]);
a_t.add('0_11', [ '500300', '��������', '500200', '��������', '500100', '��������Ͻ��' ]);
a_t.add('0_11_0', [ '500381', '�����н�����', '500382', '�����кϴ���', '500383', '������������',
		'500384', '�������ϴ���' ]);
a_t.add('0_11_1', [ '500224', '������ͭ����', '500225', '�����д�����', '500226', '�������ٲ���',
		'500227', '�������ɽ��', '500228', '��������ƽ��', '500229', '�����гǿ���', '500230',
		'�����зᶼ��', '500231', '�����е潭��', '500232', '��������¡��', '500233', '����������',
		'500234', '�����п���', '500235', '������������', '500236', '�����з����', '500237',
		'��������ɽ��', '500238', '��������Ϫ��', '500240', '������ʯ��������������', '500241',
		'��������ɽ����������������', '500242', '��������������������������', '500243', '��������ˮ����������������',
		'500223', '������������', '500222', '�������뽭��' ]);
a_t.add('0_11_2', [ '500101', '������������', '500102', '�����и�����', '500104',
		'�����д�ɿ���', '500105', '�����н�����', '500107', '�����о�������', '500108', '�������ϰ���',
		'500110', '��������ʢ��', '500111', '������˫����', '500113', '�����а�����', '500114',
		'������ǭ����', '500103', '������������', '500106', '������ɳƺ����', '500109', '�����б�����',
		'500112', '�������山��', '500115', '�����г�����' ]);
a_t.add('0_12',
		[ '451000', '����׳����������ɫ��', '451100', '����׳��������������', '451200',
				'����׳���������ӳ���', '451300', '����׳��������������', '451400', '����׳��������������',
				'450100', '����׳��������������', '450200', '����׳��������������', '450300',
				'����׳��������������', '450400', '����׳��������������', '450500', '����׳��������������',
				'450600', '����׳�����������Ǹ���', '450700', '����׳��������������', '450800',
				'����׳�������������', '450900', '����׳��������������' ]);
a_t.add('0_12_0', [ '451001', '����׳����������ɫ����Ͻ��', '451002', '����׳����������ɫ���ҽ���',
		'451021', '����׳����������ɫ��������', '451022', '����׳����������ɫ���ﶫ��', '451023',
		'����׳����������ɫ��ƽ����', '451024', '����׳����������ɫ�е±���', '451025', '����׳����������ɫ�о�����',
		'451026', '����׳����������ɫ��������', '451027', '����׳����������ɫ��������', '451028',
		'����׳����������ɫ����ҵ��', '451029', '����׳����������ɫ��������', '451030', '����׳����������ɫ��������',
		'451031', '����׳����������ɫ��¡�ָ���������' ]);
a_t.add('0_12_1', [ '451101', '����׳����������������Ͻ��', '451102', '����׳�������������а˲���',
		'451121', '����׳����������������ƽ��', '451122', '����׳����������������ɽ��', '451123',
		'����׳�������������и�������������' ]);
a_t.add('0_12_2', [ '451201', '����׳���������ӳ�����Ͻ��', '451202', '����׳���������ӳ��н�ǽ���',
		'451221', '����׳���������ӳ����ϵ���', '451222', '����׳���������ӳ��������', '451223',
		'����׳���������ӳ��з�ɽ��', '451224', '����׳���������ӳ��ж�����', '451225',
		'����׳���������ӳ����޳�������������', '451226', '����׳���������ӳ��л���ë����������', '451227',
		'����׳���������ӳ��а�������������', '451228', '����׳���������ӳ��ж�������������', '451229',
		'����׳���������ӳ��д�����������', '451281', '����׳��������������' ]);
a_t.add('0_12_3',
		[ '451301', '����׳����������������Ͻ��', '451302', '����׳���������������˱���', '451321',
				'����׳���������������ó���', '451322', '����׳��������������������', '451323',
				'����׳��������������������', '451324', '����׳�������������н�������������', '451381',
				'����׳����������ɽ��' ]);
a_t.add('0_12_4', [ '451401', '����׳����������������Ͻ��', '451402', '����׳�������������н�����',
		'451421', '����׳�������������з�����', '451422', '����׳��������������������', '451423',
		'����׳��������������������', '451424', '����׳�������������д�����', '451425', '����׳�������������������',
		'451481', '����׳��������ƾ����' ]);
a_t.add('0_12_5', [ '450101', '����������Ͻ��', '450102', '��������������', '450103',
		'��������������', '450105', '��������������', '450107', '����׳����������������������', '450108',
		'����׳��������������������', '450109', '����׳��������������������', '450122', '��������������',
		'450123', '����׳��������������¡����', '450124', '����׳����������������ɽ��', '450125',
		'����׳��������������������', '450126', '����׳�������������б�����', '450127', '����׳�������������к���' ]);
a_t.add('0_12_6', [ '450201', '����������Ͻ��', '450202', '�������ݳ�����', '450203',
		'�������������', '450205', '��������������', '450221', '��������������', '450223',
		'����׳��������������¹կ��', '450224', '����׳���������������ڰ���', '450225',
		'����׳����������������ˮ����������', '450226', '����׳����������������������������', '450204',
		'��������������', '450222', '��������������' ]);
a_t.add('0_12_7', [ '450302', '�������������', '450303', '�������ֵ�����', '450305',
		'��������������', '450311', '������������ɽ��', '450322', '���������ٹ���', '450323',
		'����׳���������������鴨��', '450324', '����׳��������������ȫ����', '450326', '����׳��������������������',
		'450327', '����׳�������������й�����', '450328', '����׳����������������ʤ����������', '450329',
		'����׳����������������Դ��', '450330', '����׳��������������ƽ����', '450332',
		'����׳�������������й�������������', '450301', '����������Ͻ��', '450304', '����������ɽ��',
		'450321', '����������˷��', '450325', '����׳���������������˰���', '450331',
		'����׳��������������������' ]);
a_t.add('0_12_8', [ '450401', '����������Ͻ��', '450404', '�������ݵ�ɽ��', '450405',
		'����׳�������������г�����', '450422', '����׳������������������', '450423', '����׳����������������ɽ��',
		'450481', '����׳���������Ϫ��', '450403', '��������������', '450421', '�������ݲ�����' ]);
a_t.add('0_12_9', [ '450502', '��������������', '450503', '����׳��������������������', '450512',
		'����׳����������������ɽ����', '450501', '����������Ͻ��', '450521', '��������������' ]);
a_t.add('0_12_10', [ '450601', '�������Ǹ���Ͻ��', '450603', '�������Ǹ۷�����', '450621',
		'�������Ǹ���˼��', '450602', '�������Ǹ۸ۿ���', '450681', '����׳��������������' ]);
a_t.add('0_12_11', [ '450701', '����׳����������������Ͻ��', '450702', '����׳��������������������',
		'450703', '����׳���������������ձ���', '450722', '����׳���������������ֱ���', '450721',
		'����׳����������������ɽ��' ]);
a_t.add('0_12_12', [ '450801', '����׳���������������Ͻ��', '450802', '����׳������������и۱���',
		'450804', '����׳�������������������', '450821', '����׳�������������ƽ����', '450881',
		'����׳����������ƽ��', '450803', '����׳������������и�����' ]);
a_t
		.add('0_12_13', [ '450924', '����׳����������������ҵ��', '450981', '����׳��������������',
				'450902', '����׳��������������������', '450921', '����׳������������������', '450922',
				'����׳��������������½����', '450901', '����׳����������������Ͻ��', '450923',
				'����׳�������������в�����' ]);
a_t.add('0_13', [ '341900', '����ʡ������', '341000', '����ʡ��ɽ��', '341100', '����ʡ������',
		'341200', '����ʡ������', '341600', '����ʡ������', '341700', '����ʡ������', '341800',
		'����ʡ������', '340300', '����ʡ������', '340400', '����ʡ������', '340500', '����ʡ��ɽ��',
		'340700', '����ʡͭ����', '340800', '����ʡ������', '340100', '����ʡ�Ϸ���', '340200',
		'����ʡ�ߺ���', '340600', '����ʡ������', '341300', '����ʡ������', '341400', '����ʡ������',
		'341500', '����ʡ������' ]);
a_t.add('0_13_1', [ '341001', '���ջ�ɽ��Ͻ��', '341002', '���ջ�ɽ��Ϫ��', '341003',
		'���ջ�ɽ��', '341004', '���ջ�ɽ������', '341021', '���ջ�ɽ���', '341022', '���ջ�ɽ������',
		'341023', '���ջ�ɽ����', '341024', '���ջ�ɽ������' ]);
a_t.add('0_13_2',
		[ '341101', '���ճ�����Ͻ��', '341102', '���ճ���������', '341103', '���ճ���������',
				'341122', '���ճ���������', '341124', '���ճ���ȫ����', '341125', '���ճ��ݶ�Զ��',
				'341126', '���ճ��ݷ�����', '341181', '���ճ����쳤��', '341182', '����ʡ������' ]);
a_t.add('0_13_3', [ '341201', '����ʡ��������Ͻ��', '341202', '����ʡ�����������', '341203',
		'����ʡ������򣶫��', '341204', '����ʡ�������Ȫ��', '341221', '����ʡ��������Ȫ��', '341222',
		'����ʡ������̫����', '341226', '����ʡ�����������', '341282', '����ʡ������', '341225',
		'����ʡ�����и�����' ]);
a_t.add('0_13_4', [ '341602', '����ʡ�������۳���', '341621', '����ʡ������������', '341623',
		'����ʡ������������', '341601', '����ʡ��������Ͻ��', '341622', '����ʡ�������ɳ���' ]);
a_t.add('0_13_5', [ '341702', '����ʡ�����й����', '341721', '����ʡ�����ж�����', '341722',
		'����ʡ������ʯ̨��', '341701', '����ʡ��������Ͻ��', '341723', '����ʡ������������' ]);
a_t.add('0_13_6', [ '341801', '����ʡ��������Ͻ��', '341821', '����ʡ��������Ϫ��', '341822',
		'����ʡ�����й����', '341823', '����ʡ����������', '341825', '����ʡ������캵���', '341881',
		'����ʡ������', '341802', '����ʡ������������', '341824', '����ʡ�����м�Ϫ��' ]);
a_t.add('0_13_7', [ '340302', '���հ������Ӻ���', '340303', '���հ�����ɽ��', '340311',
		'���հ���������', '340321', '���հ�����Զ��', '340323', '���հ���������', '340301',
		'���հ�����Ͻ��', '340304', '���հ��������', '340322', '���հ��������' ]);
a_t.add('0_13_8', [ '340402', '���ջ��ϴ�ͨ��', '340403', '���ջ����������', '340405',
		'���ջ��ϰ˹�ɽ��', '340406', '���ջ����˼���', '340401', '���ջ�����Ͻ��', '340404',
		'���ջ���л�Ҽ���', '340421', '���ջ��Ϸ�̨��' ]);
a_t.add('0_13_9', [ '340501', '������ɽ��Ͻ��', '340503', '������ɽ��ɽ��', '340504',
		'������ɽ��ɽ��', '340521', '������ɽ��Ϳ��', '340502', '������ɽ���ׯ��' ]);
a_t.add('0_13_10', [ '340701', '����ͭ����Ͻ��', '340703', '����ͭ��ʨ��ɽ��', '340711',
		'����ͭ�꽼��', '340702', '����ͭ��ͭ��ɽ��', '340721', '����ͭ����' ]);
a_t.add('0_13_11', [ '340826', '���հ���������', '340827', '���հ���������', '340828',
		'���հ���������', '340881', '����ʡͩ����', '340801', '���հ�����Ͻ��', '340803',
		'���հ�������', '340811', '���հ���������', '340823', '���հ���������', '340824',
		'���հ���Ǳɽ��', '340802', '���հ���ӭ����', '340822', '���հ��컳����', '340825',
		'���հ���̫����' ]);
a_t.add('0_13_12', [ '340101', '���պϷ���Ͻ��', '340102', '���պϷ�������', '340104',
		'���պϷ���ɽ��', '340111', '���պϷʰ�����', '340122', '���պϷʷʶ���', '340123',
		'���պϷʷ�����', '340103', '���պϷ�®����', '340121', '���պϷʳ�����' ]);
a_t.add('0_13_13', [ '340201', '�����ߺ���Ͻ��', '340202', '�����ߺ�������', '340207',
		'�����ߺ�𯽭��', '340221', '�����ߺ���', '340223', '�����ߺ�������', '340203', '�����ߺ�߮����',
		'340222', '�����ߺ�������' ]);
a_t.add('0_13_14', [ '340601', '���ջ�����Ͻ��', '340603', '���ջ�����ɽ��', '340604',
		'���ջ�����ɽ��', '340602', '���ջ����ż���', '340621', '���ջ����Ϫ��' ]);
a_t.add('0_13_15', [ '341301', '����ʡ��������Ͻ��', '341302', '����ʡ�����Ј�����', '341321',
		'����ʡ�������ɽ��', '341323', '����ʡ�����������', '341324', '����ʡ����������', '341322',
		'����ʡ����������' ]);
a_t.add('0_13_16', [ '341401', '����ʡ��������Ͻ��', '341402', '����ʡ�����оӳ���', '341422',
		'����ʡ��������Ϊ��', '341423', '����ʡ�����к�ɽ��', '341424', '����ʡ�����к���', '341421',
		'����ʡ������®����' ]);
a_t.add('0_13_17', [ '341501', '����ʡ��������Ͻ��', '341502', '����ʡ�����н���', '341521',
		'����ʡ����������', '341522', '����ʡ�����л�����', '341524', '����ʡ�����н�կ��', '341525',
		'����ʡ�����л�ɽ��', '341503', '����ʡ������ԣ����', '341523', '����ʡ�����������' ]);
a_t.add('0_14', [ '140100', 'ɽ��ʡ̫ԭ��', '140200', 'ɽ��ʡ��ͬ��', '140400', 'ɽ��ʡ������',
		'140500', 'ɽ��ʡ������', '140800', 'ɽ��ʡ�˳���', '140900', 'ɽ��ʡ������', '141000',
		'ɽ��ʡ�ٷ���', '141100', 'ɽ��ʡ������', '140600', 'ɽ��ʡ˷����', '140700', 'ɽ��ʡ������',
		'140300', 'ɽ��ʡ��Ȫ��' ]);
a_t.add('0_14_0', [ '140101', 'ɽ��̫ԭ��Ͻ��', '140105', 'ɽ��ʡ̫ԭ��С����', '140106',
		'ɽ��ʡ̫ԭ��ӭ����', '140107', 'ɽ��ʡ̫ԭ���ӻ�����', '140108', 'ɽ��ʡ̫ԭ�м��ƺ��', '140109',
		'ɽ��ʡ̫ԭ���������', '140110', 'ɽ��ʡ̫ԭ�н�Դ��', '140121', 'ɽ��̫ԭ������', '140122',
		'ɽ��̫ԭ������', '140123', 'ɽ��̫ԭ¦����', '140181', 'ɽ��̫ԭ�Ž���' ]);
a_t.add('0_14_1', [ '140201', 'ɽ����ͬ��Ͻ��', '140202', 'ɽ����ͬ����', '140203',
		'ɽ����ͬ����', '140211', 'ɽ����ͬ�Ͻ���', '140221', 'ɽ����ͬ������', '140222',
		'ɽ����ͬ������', '140224', 'ɽ����ͬ������', '140225', 'ɽ����ͬ��Դ��', '140227', 'ɽ����ͬ��',
		'140212', 'ɽ����ͬ������', '140223', 'ɽ����ͬ������', '140226', 'ɽ����ͬ������' ]);
a_t.add('0_14_2', [ '140401', 'ɽ��������Ͻ��', '140411', 'ɽ�����ν���', '140421', 'ɽ��������',
		'140424', 'ɽ������������', '140425', 'ɽ������ƽ˳��', '140427', 'ɽ�����κ�����',
		'140428', 'ɽ�����γ�����', '140430', 'ɽ����������', '140431', 'ɽ��������Դ��', '140402',
		'ɽ�����γ���', '140423', 'ɽ��������ԫ��', '140426', 'ɽ�����������', '140429',
		'ɽ������������', '140481', 'ɽ��ʡº����' ]);
a_t.add('0_14_3', [ '140502', 'ɽ�����ǳ���', '140521', 'ɽ��������ˮ��', '140524',
		'ɽ�������괨��', '140525', 'ɽ��ʡ������������', '140581', 'ɽ�����Ǹ�ƽ��', '140501',
		'ɽ��������Ͻ��', '140522', 'ɽ������������' ]);
a_t.add('0_14_4', [ '140802', 'ɽ��ʡ�˳����κ���', '140821', 'ɽ��ʡ�˳��������', '140822',
		'ɽ��ʡ�˳���������', '140824', 'ɽ��ʡ�˳����ɽ��', '140825', 'ɽ��ʡ�˳��������', '140827',
		'ɽ��ʡ�˳���ԫ����', '140828', 'ɽ��ʡ�˳�������', '140830', 'ɽ��ʡ�˳����ǳ���', '140881',
		'ɽ��ʡ������', '140801', 'ɽ��ʡ�˳�����Ͻ��', '140823', 'ɽ��ʡ�˳�����ϲ��', '140826',
		'ɽ��ʡ�˳������', '140829', 'ɽ��ʡ�˳���ƽ½��', '140882', 'ɽ��ʡ�ӽ���' ]);
a_t.add('0_14_5', [ '140901', 'ɽ��ʡ��������Ͻ��', '140902', 'ɽ��ʡ�������ø���', '140921',
		'ɽ��ʡ�����ж�����', '140922', 'ɽ��ʡ��������̨��', '140923', 'ɽ��ʡ�����д���', '140924',
		'ɽ��ʡ�����з�����', '140925', 'ɽ��ʡ������������', '140926', 'ɽ��ʡ�����о�����', '140927',
		'ɽ��ʡ�����������', '140928', 'ɽ��ʡ��������կ��', '140929', 'ɽ��ʡ����������', '140930',
		'ɽ��ʡ�����к�����', '140931', 'ɽ��ʡ�����б�����', '140932', 'ɽ��ʡ������ƫ����', '140981',
		'ɽ��ʡԭƽ��' ]);
a_t.add('0_14_6', [ '141001', 'ɽ��ʡ�ٷ�����Ͻ��', '141002', 'ɽ��ʡ�ٷ���Ң����', '141021',
		'ɽ��ʡ�ٷ���������', '141022', 'ɽ��ʡ�ٷ��������', '141023', 'ɽ��ʡ�ٷ��������', '141024',
		'ɽ��ʡ�ٷ��к鶴��', '141025', 'ɽ��ʡ�ٷ��й���', '141026', 'ɽ��ʡ�ٷ��а�����', '141027',
		'ɽ��ʡ�ٷ��и�ɽ��', '141028', 'ɽ��ʡ�ٷ��м���', '141029', 'ɽ��ʡ�ٷ���������', '141030',
		'ɽ��ʡ�ٷ��д�����', '141031', 'ɽ��ʡ�ٷ�������', '141032', 'ɽ��ʡ�ٷ���������', '141033',
		'ɽ��ʡ�ٷ�������', '141034', 'ɽ��ʡ�ٷ��з�����', '141081', 'ɽ��ʡ������', '141082',
		'ɽ��ʡ������' ]);
a_t.add('0_14_7', [ '141101', 'ɽ��ʡ��������Ͻ��', '141102', 'ɽ��ʡ��������ʯ��', '141122',
		'ɽ��ʡ�����н�����', '141123', 'ɽ��ʡ����������', '141125', 'ɽ��ʡ������������', '141126',
		'ɽ��ʡ������ʯ¥��', '141128', 'ɽ��ʡ�����з�ɽ��', '141129', 'ɽ��ʡ������������', '141130',
		'ɽ��ʡ�����н�����', '141182', 'ɽ��ʡ������', '141121', 'ɽ��ʡ��������ˮ��', '141124',
		'ɽ��ʡ����������', '141127', 'ɽ��ʡ���������', '141181', 'ɽ��ʡ������Т����' ]);
a_t.add('0_14_8', [ '140601', 'ɽ��˷����Ͻ��', '140603', 'ɽ��˷��ƽ³��', '140621',
		'ɽ��˷��ɽ����', '140623', 'ɽ��˷��������', '140624', 'ɽ��˷�ݻ�����', '140602',
		'ɽ��˷��˷����', '140622', 'ɽ��˷��Ӧ��' ]);
a_t.add('0_14_9', [ '140701', 'ɽ��ʡ��������Ͻ��', '140702', 'ɽ��ʡ�������ܴ���', '140721',
		'ɽ��ʡ������������', '140723', 'ɽ��ʡ�����к�˳��', '140724', 'ɽ��ʡ������������', '140726',
		'ɽ��ʡ������̫����', '140727', 'ɽ��ʡ����������', '140728', 'ɽ��ʡ������ƽң��', '140781',
		'ɽ��ʡ�����н�����', '140722', 'ɽ��ʡ��������Ȩ��', '140725', 'ɽ��ʡ������������', '140729',
		'ɽ��ʡ��������ʯ��' ]);
a_t
		.add('0_14_10', [ '140301', 'ɽ����Ȫ��Ͻ��', '140302', 'ɽ����Ȫ����', '140311',
				'ɽ����Ȫ����', '140321', 'ɽ����Ȫƽ����', '140322', 'ɽ����Ȫ����', '140303',
				'ɽ����Ȫ����' ]);
a_t.add('0_15', [ '150700', '���ɹ����������ױ�����', '150800', '���ɹ������������׶���', '150900',
		'���ɹ������������첼��', '152200', '���ɹ��������������˰���', '152500', '���ɹ����������������ֹ�����',
		'152900', '���ɹ������������а�������', '150200', '���ɹ���������ͷ��', '150300',
		'���ɹ��������ں���', '150400', '���ɹ������������', '150500', '���ɹ�������ͨ����', '150600',
		'���ɹ�������������˹��', '150100', '���ɹ����������ͺ�����' ]);
a_t.add('0_15_0',
		[ '150701', '���ɹ����������ױ�������Ͻ��', '150702', '���ɹ����������ױ����к�������', '150721',
				'���ɹ����������ױ����а�����', '150722', '���ɹ����������ױ�����Ī�����ߴ��Ӷ���������', '150723',
				'���ɹ����������ױ����ж��״�������', '150724', '���ɹ����������ױ����ж��¿���������', '150725',
				'���ɹ����������ױ����г°Ͷ�����', '150726', '���ɹ����������ױ������°Ͷ�������', '150727',
				'���ɹ����������ױ������°Ͷ�������', '150781', '���ɹ���������������', '150782',
				'���ɹ�����������ʯ��', '150783', '���ɹ���������������', '150784', '���ɹ����������������',
				'150785', '���ɹ�������������' ]);
a_t.add('0_15_1', [ '150801', '���ɹ������������׶�����Ͻ��', '150802', '���ɹ������������׶����ٺ���',
		'150821', '���ɹ������������׶�����ԭ��', '150822', '���ɹ������������׶��������', '150823',
		'���ɹ������������׶���������ǰ��', '150824', '���ɹ������������׶�������������', '150825',
		'���ɹ������������׶��������غ���', '150826', '���ɹ������������׶��к�������' ]);
a_t.add('0_15_2', [ '150901', '���ɹ������������첼����Ͻ��', '150902', '���ɹ������������첼�м�����',
		'150921', '���ɹ������������첼��׿����', '150922', '���ɹ������������첼�л�����', '150923',
		'���ɹ������������첼���̶���', '150924', '���ɹ������������첼���˺���', '150925',
		'���ɹ������������첼��������', '150926', '���ɹ������������첼�в��������ǰ��', '150927',
		'���ɹ������������첼�в������������', '150928', '���ɹ������������첼�в�����������', '150929',
		'���ɹ������������첼����������', '150981', '���ɹ�������������' ]);
a_t.add('0_15_3', [ '152201', '�����˰�������������', '152202', '���ɹ������������а���ɽ��',
		'152221', '�����˰��˿ƶ�������ǰ��', '152222', '�����˰��˿ƶ�����������', '152223',
		'�����˰�����������', '152224', '�����˰���ͻȪ��' ]);
a_t.add('0_15_4', [ '152501', '�������ֹ����˶���������', '152502', '�������ֹ��������ֺ�����',
		'152522', '�������ֹ����˰��͸���', '152523', '�������ֹ���������������', '152524',
		'�������ֹ���������������', '152525', '���ֹ����˶�����������', '152526', '���ֹ�����������������',
		'152527', '�������ֹ�����̫������', '152528', '�������ֹ����������', '152529',
		'�������ֹ������������', '152530', '�������ֹ�����������', '152531', '�������ֹ����˶�����' ]);
a_t.add('0_15_5', [ '152921', '���ɰ������˰���������', '152922', '���ɰ������˰���������', '152923',
		'���ɰ������˶������' ]);
a_t.add('0_15_6', [ '150201', '���ɰ�ͷ��Ͻ��', '150203', '���ɰ�ͷ��������', '150204',
		'���ɰ�ͷ��ɽ��', '150206', '���ɰ�ͷ���ƿ���', '150207', '���ɰ���ԭ��', '150222',
		'���ɰ�ͷ������', '150223', '���ɹ���������ͷ�д����ï����������', '150202', '���ɰ�ͷ������',
		'150205', '���ɰ�ͷʯ����', '150221', '���ɰ�ͷ��Ĭ������' ]);
a_t.add('0_15_7', [ '150302', '�����ں���������', '150303', '�����ں�������', '150301',
		'�����ں���Ͻ��', '150304', '�����ں��ڴ���' ]);
a_t.add('0_15_8', [ '150401', '���ɳ����Ͻ��', '150403', '���ɳ��Ԫ��ɽ��', '150404',
		'���ɳ����ɽ��', '150421', '���ɳ�尢³�ƶ�����', '150423', '���ɳ���������', '150424',
		'���ɳ��������', '150426', '���ɳ����ţ����', '150428', '���ɳ�忦������', '150430',
		'���ɳ�尽����', '150402', '���ɳ���ɽ��', '150422', '���ɳ���������', '150425',
		'���ɳ���ʲ������', '150429', '���ɳ��������' ]);
a_t.add('0_15_9', [ '150501', '���ɹ�������ͨ������Ͻ��', '150521', '���ɹ�������ͨ���пƶ�����������',
		'150522', '���ɹ�������ͨ���пƶ����������', '150523', '���ɹ�������ͨ���п�³��', '150524',
		'���ɹ�������ͨ���п�����', '150526', '���ɹ�������ͨ������³����', '150581', '���ɹ����������ֹ�����',
		'150502', '���ɹ�������ͨ���пƶ�����', '150525', '���ɹ�������ͨ����������' ]);
a_t.add('0_15_10', [ '150624', '���ɹ�������������˹�ж��п���', '150625', '���ɹ�������������˹�к�����',
		'150626', '���ɹ�������������˹��������', '150627', '���ɹ�������������˹�����������', '150602',
		'���ɹ�������������˹�ж�ʤ��', '150621', '���ɹ�������������˹�д�������', '150623',
		'���ɹ�������������˹�ж��п�ǰ��', '150622', '���ɹ�������������˹��׼�����' ]);
a_t.add('0_15_11', [ '150101', '���ɺ��ͺ�����Ͻ��', '150102', '���ɺ��ͺ����³���', '150103',
		'���ɺ��ͺ��ػ�����', '150105', '���ɺ��ͺ���������', '150121', '���ɺ��ͺ�����Ĭ������', '150123',
		'���ɹ����������ͺ����к��ָ����', '150124', '���ɹ����������ͺ�������ˮ����', '150125',
		'���ɹ����������ͺ������䴨��', '150104', '���ɺ��ͺ�����Ȫ��', '150122', '���ɺ��ͺ����п�����' ]);
a_t.add('0_16', [ '330700', '�㽭ʡ����', '330800', '�㽭ʡ������', '330900', '�㽭ʡ��ɽ��',
		'331000', '�㽭ʡ̨����', '331100', '�㽭ʡ��ˮ��', '330100', '�㽭ʡ������', '330200',
		'�㽭ʡ������', '330300', '�㽭ʡ������', '330400', '�㽭ʡ������', '330500', '�㽭ʡ������',
		'330600', '�㽭ʡ������' ]);
a_t.add('0_16_0', [ '330701', '�㽭����Ͻ��', '330702', '�㽭���ĳ���', '330703',
		'�㽭ʡ���н���', '330723', '�㽭��������', '330726', '�㽭���ֽ���', '330727',
		'�㽭���Ͱ���', '330781', '�㽭����Ϫ��', '330782', '�㽭��������', '330783',
		'�㽭�𻪶�����', '330784', '�㽭��������' ]);
a_t.add('0_16_1', [ '330801', '�㽭������Ͻ��', '330802', '�㽭���ݿ³���', '330803',
		'�㽭ʡ�������齭��', '330822', '�㽭���ݳ�ɽ��', '330824', '�㽭���ݿ�����', '330825',
		'�㽭����������', '330881', '�㽭���ݽ�ɽ��' ]);
a_t.add('0_16_2', [ '330901', '�㽭��ɽ��Ͻ��', '330902', '�㽭��ɽ������', '330903',
		'�㽭��ɽ������', '330921', '�㽭��ɽ�ɽ��', '330922', '�㽭��ɽ������' ]);
a_t.add('0_16_3', [ '331001', '�㽭ʡ̨������Ͻ��', '331002', '�㽭ʡ̨���н�����', '331004',
		'�㽭ʡ̨����·����', '331021', '�㽭ʡ̨��������', '331022', '�㽭ʡ̨����������', '331024',
		'�㽭ʡ̨�����ɾ���', '331081', '�㽭ʡ������', '331003', '�㽭ʡ̨���л�����', '331023',
		'�㽭ʡ̨������̨��', '331082', '�㽭ʡ�ٺ���' ]);
a_t.add('0_16_4', [ '331101', '�㽭ʡ��ˮ����Ͻ��', '331121', '�㽭ʡ��ˮ��������', '331122',
		'�㽭ʡ��ˮ��������', '331123', '�㽭ʡ��ˮ�������', '331125', '�㽭ʡ��ˮ���ƺ���', '331126',
		'�㽭ʡ��ˮ����Ԫ��', '331127', '�㽭ʡ��ˮ�о������������', '331102', '�㽭ʡ��ˮ��������',
		'331124', '�㽭ʡ��ˮ��������', '331181', '�㽭ʡ��Ȫ��' ]);
a_t.add('0_16_5', [ '330102', '�㽭�����ϳ���', '330103', '�㽭�����³���', '330105',
		'�㽭���ݹ�����', '330106', '�㽭����������', '330109', '�㽭ʡ��������ɽ��', '330110',
		'�㽭ʡ�������ຼ��', '330122', '�㽭����ͩ®��', '330182', '�㽭���ݽ�����', '330183',
		'�㽭ʡ������', '330101', '�㽭������Ͻ��', '330104', '�㽭���ݽ�����', '330108',
		'�㽭ʡ�����б�����', '330127', '�㽭���ݴ�����', '330185', '�㽭ʡ�ٰ���' ]);
a_t.add('0_16_6', [ '330203', '�㽭����������', '330204', '�㽭����������', '330206',
		'�㽭����������', '330211', '�㽭��������', '330212', '�㽭ʡ������۴����', '330226',
		'�㽭����������', '330281', '�㽭������Ҧ��', '330283', '�㽭�������', '330201',
		'�㽭������Ͻ��', '330205', '�㽭����������', '330225', '�㽭������ɽ��', '330282',
		'�㽭������Ϫ��' ]);
a_t.add('0_16_7', [ '330302', '�㽭����¹����', '330303', '�㽭����������', '330322',
		'�㽭���ݶ�ͷ��', '330324', '�㽭����������', '330327', '�㽭���ݲ�����', '330328',
		'�㽭�����ĳ���', '330381', '�㽭��������', '330382', '�㽭����������', '330301',
		'�㽭������Ͻ��', '330304', '�㽭����걺���', '330326', '�㽭����ƽ����', '330329',
		'�㽭����̩˳��' ]);
a_t.add('0_16_8', [ '330401', '�㽭������Ͻ��', '330402', '�㽭���������', '330424',
		'�㽭���˺�����', '330483', '�㽭����ͩ����', '330411', '�㽭����������', '330421',
		'�㽭���˼�����', '330481', '�㽭���˺�����', '330482', '�㽭����ƽ����' ]);
a_t.add('0_16_9', [ '330503', '�㽭ʡ�����������', '330523', '�㽭���ݰ�����', '330501',
		'�㽭������Ͻ��', '330502', '�㽭ʡ������������', '330521', '�㽭���ݵ�����', '330522',
		'�㽭���ݳ�����' ]);
a_t.add('0_16_10', [ '330682', '�㽭����������', '330683', '�㽭ʡ������', '330602',
		'�㽭����Խ����', '330624', '�㽭�����²���', '330601', '�㽭������Ͻ��', '330621', '�㽭������',
		'330681', '�㽭����������' ]);
a_t.add('0_17', [ '350500', '����ʡȪ����', '350600', '����ʡ������', '350700', '����ʡ��ƽ��',
		'350900', '����ʡ������', '350100', '����ʡ������', '350300', '����ʡ������', '350400',
		'����ʡ������', '350200', '����ʡ������', '350800', '����ʡ������' ]);
a_t.add('0_17_0', [ '350501', '����Ȫ����Ͻ��', '350502', '����Ȫ�������', '350503',
		'����ʡȪ���з�����', '350504', '����ʡȪ�����彭��', '350505', '����ʡȪ����Ȫ����', '350521',
		'����Ȫ�ݻݰ���', '350524', '����Ȫ�ݰ�Ϫ��', '350525', '����Ȫ��������', '350526',
		'����Ȫ�ݵ»���', '350527', '����Ȫ�ݽ�����', '350581', '����Ȫ��ʯʨ��', '350582',
		'����Ȫ�ݽ�����', '350583', '����Ȫ���ϰ���' ]);
a_t.add('0_17_1', [ '350601', '����������Ͻ��', '350602', '��������ܼ����', '350603',
		'����ʡ������������', '350622', '��������������', '350623', '��������������', '350625',
		'�������ݳ�̩��', '350626', '�������ݶ�ɽ��', '350628', '��������ƽ����', '350629',
		'�������ݻ�����', '350624', '��������گ����', '350627', '���������Ͼ���', '350681',
		'��������������' ]);
a_t.add('0_17_2', [ '350701', '����ʡ��ƽ����Ͻ��', '350721', '����ʡ��ƽ��˳����', '350722',
		'����ʡ��ƽ���ֳ���', '350723', '����ʡ��ƽ�й�����', '350725', '����ʡ��ƽ��������', '350781',
		'����ʡ������', '350783', '����ʡ�����', '350784', '����ʡ������', '350702',
		'����ʡ��ƽ����ƽ��', '350724', '����ʡ��ƽ����Ϫ��', '350782', '����ʡ����ɽ��' ]);
a_t.add('0_17_3', [ '350902', '����ʡ�����н�����', '350921', '����ʡ������ϼ����', '350923',
		'����ʡ������������', '350924', '����ʡ������������', '350925', '����ʡ������������', '350981',
		'����ʡ������', '350982', '����ʡ������', '350901', '����ʡ��������Ͻ��', '350922',
		'����ʡ�����й�����', '350926', '����ʡ������������' ]);
a_t.add('0_17_4', [ '350102', '�������ݹ�¥��', '350103', '��������̨����', '350105',
		'����������β��', '350111', '�������ݽ�����', '350122', '��������������', '350123',
		'����������Դ��', '350125', '����������̩��', '350128', '��������ƽ̶��', '350182',
		'����ʡ������', '350101', '����������Ͻ��', '350104', '�������ݲ�ɽ��', '350121',
		'��������������', '350124', '��������������', '350181', '�������ݸ�����' ]);
a_t.add('0_17_5', [ '350301', '����������Ͻ��', '350303', '�������ﺭ����', '350304',
		'����ʡ�����������', '350322', '��������������', '350302', '�������������', '350305',
		'����ʡ������������' ]);
a_t.add('0_17_6', [ '350402', '��������÷����', '350403', '����������Ԫ��', '350421',
		'����������Ϫ��', '350423', '��������������', '350424', '��������������', '350425',
		'��������������', '350426', '����������Ϫ��', '350427', '��������ɳ��', '350428',
		'��������������', '350429', '��������̩����', '350430', '��������������', '350481',
		'��������������', '350401', '����������Ͻ��' ]);
a_t.add('0_17_7', [ '350201', '����������Ͻ��', '350203', '��������˼����', '350206',
		'�������ź�����', '350211', '�������ż�����', '350212', '����ʡ������ͬ����', '350205',
		'�������ź�����', '350213', '����ʡ�������谲��' ]);
a_t.add('0_17_8', [ '350801', '����ʡ��������Ͻ��', '350802', '����ʡ������������', '350822',
		'����ʡ������������', '350823', '����ʡ�������Ϻ���', '350824', '����ʡ��������ƽ��', '350881',
		'����ʡ��ƽ��', '350821', '����ʡ�����г�͡��', '350825', '����ʡ������������' ]);
a_t.add('0_18', [ '360500', '����ʡ������', '360100', '����ʡ�ϲ���', '360200', '����ʡ��������',
		'360300', '����ʡƼ����', '360400', '����ʡ�Ž���', '360600', '����ʡӥ̶��', '360700',
		'����ʡ������', '360800', '����ʡ������', '361000', '����ʡ������', '361100', '����ʡ������',
		'360900', '����ʡ�˴���' ]);
a_t.add('0_18_0', [ '360501', '����������Ͻ��', '360502', '����������ˮ��', '360521',
		'�������������' ]);
a_t.add('0_18_1', [ '360102', '�����ϲ�������', '360103', '�����ϲ�������', '360105',
		'�����ϲ�������', '360111', '�����ϲ���ɽ����', '360122', '�����ϲ��½���', '360123',
		'�����ϲ�������', '360101', '�����ϲ���Ͻ��', '360104', '�����ϲ���������', '360121',
		'�����ϲ���', '360124', '�����ϲ�������' ]);
a_t.add('0_18_2', [ '360201', '������������Ͻ��', '360203', '������������ɽ��', '360222',
		'��������������', '360202', '���������������', '360281', '������������ƽ��' ]);
a_t.add('0_18_3', [ '360301', '����Ƽ����Ͻ��', '360313', '����Ƽ���涫��', '360321',
		'����Ƽ��������', '360323', '����ʡƼ����«Ϫ��', '360302', '����Ƽ�簲Դ��', '360322',
		'����ʡƼ����������' ]);
a_t.add('0_18_4', [ '360402', '�����Ž�®ɽ��', '360403', '�����Ž������', '360423',
		'�����Ž�������', '360424', '�����Ž���ˮ��', '360426', '�����Ž��°���', '360427',
		'�����Ž�������', '360429', '�����Ž�������', '360430', '�����Ž�������', '360401',
		'�����Ž���Ͻ��', '360421', '�����Ž���', '360425', '�����Ž�������', '360428', '�����Ž�������',
		'360481', '�����Ž������' ]);
a_t.add('0_18_5', [ '360601', '����ӥ̶��Ͻ��', '360602', '����ӥ̶�º���', '360622',
		'����ӥ̶�཭��', '360681', '����ʡ��Ϫ��' ]);
a_t.add('0_18_6', [ '360701', '����ʡ��������Ͻ��', '360702', '����ʡ�������¹���', '360721',
		'����ʡ�����и���', '360722', '����ʡ�������ŷ���', '360723', '����ʡ�����д�����', '360724',
		'����ʡ������������', '360725', '����ʡ�����г�����', '360726', '����ʡ�����а�Զ��', '360727',
		'����ʡ������������', '360728', '����ʡ�����ж�����', '360729', '����ʡ������ȫ����', '360730',
		'����ʡ������������', '360731', '����ʡ�������ڶ���', '360732', '����ʡ�������˹���', '360733',
		'����ʡ�����л����', '360734', '����ʡ������Ѱ����', '360735', '����ʡ������ʯ����', '360781',
		'����ʡ�����������', '360782', '����ʡ�������Ͽ���' ]);
a_t.add('0_18_7', [ '360801', '����ʡ��������Ͻ��', '360802', '����ʡ�����м�����', '360803',
		'����ʡ��������ԭ��', '360821', '����ʡ�����м�����', '360823', '����ʡ������Ͽ����', '360824',
		'����ʡ�������¸���', '360826', '����ʡ������̩����', '360827', '����ʡ�������촨��', '360828',
		'����ʡ����������', '360830', '����ʡ������������', '360881', '����ʡ����ɽ��', '360822',
		'����ʡ�����м�ˮ��', '360825', '����ʡ������������', '360829', '����ʡ�����а�����' ]);
a_t.add('0_18_8', [ '361002', '����ʡ�������ٴ���', '361021', '����ʡ�������ϳ���', '361022',
		'����ʡ�������质��', '361024', '����ʡ�����г�����', '361025', '����ʡ�������ְ���', '361027',
		'����ʡ�����н�Ϫ��', '361028', '����ʡ��������Ϫ��', '361029', '����ʡ�����ж�����', '361001',
		'����ʡ��������Ͻ��', '361023', '����ʡ�������Ϸ���', '361026', '����ʡ�������˻���', '361030',
		'����ʡ�����й����' ]);
a_t.add('0_18_9', [ '361101', '����ʡ��������Ͻ��', '361121', '����ʡ������������', '361122',
		'����ʡ�����й����', '361124', '����ʡ������Ǧɽ��', '361125', '����ʡ�����к����', '361126',
		'����ʡ������߮����', '361128', '����ʡ������۶����', '361129', '����ʡ������������', '361181',
		'����ʡ������', '361102', '����ʡ������������', '361123', '����ʡ��������ɽ��', '361127',
		'����ʡ�����������', '361130', '����ʡ��������Դ��' ]);
a_t.add('0_18_10', [ '360901', '����ʡ�˴�����Ͻ��', '360902', '����ʡ�˴���Ԭ����', '360922',
		'����ʡ�˴���������', '360923', '����ʡ�˴����ϸ���', '360924', '����ʡ�˴����˷���', '360926',
		'����ʡ�˴���ͭ����', '360981', '����ʡ�����', '360983', '����ʡ�߰���', '360921',
		'����ʡ�˴��з�����', '360925', '����ʡ�˴��о�����', '360982', '����ʡ������' ]);
a_t.add('0_19', [ '430100', '����ʡ��ɳ��', '430600', '����ʡ������', '430200', '����ʡ������',
		'430300', '����ʡ��̶��', '430400', '����ʡ������', '430500', '����ʡ������', '430700',
		'����ʡ������', '430800', '����ʡ�żҽ���', '430900', '����ʡ������', '431000', '����ʡ������',
		'431100', '����ʡ������', '431200', '����ʡ������', '431300', '����ʡ¦����', '433100',
		'����ʡ��������������������' ]);
a_t.add('0_19_0', [ '430103', '���ϳ�ɳ������', '430111', '���ϳ�ɳ�껨��', '430124',
		'���ϳ�ɳ������', '430101', '���ϳ�ɳ��Ͻ��', '430102', '���ϳ�ɳܽ����', '430104',
		'���ϳ�ɳ��´��', '430105', '���ϳ�ɳ������', '430121', '���ϳ�ɳ��', '430122', '���ϳ�ɳ������',
		'430181', '���ϳ�ɳ�����' ]);
a_t.add('0_19_1', [ '430603', '����������Ϫ��', '430623', '��������������', '430681',
		'��������������', '430601', '����������Ͻ��', '430602', '��������¥��', '430611',
		'����������ɽ��', '430621', '����������', '430624', '��������������', '430626', '��������ƽ����',
		'430682', '��������������' ]);
a_t.add('0_19_2', [ '430201', '����������Ͻ��', '430204', '��������ʯ����', '430223',
		'������������', '430281', '��������������', '430202', '�������޺�����', '430203',
		'��������«����', '430211', '����������Ԫ��', '430221', '����������', '430224', '�������޲�����',
		'430225', '��������������' ]);
a_t.add('0_19_3',
		[ '430302', '������̶�����', '430381', '������̶������', '430301', '������̶��Ͻ��',
				'430304', '������̶������', '430321', '������̶��', '430382', '������̶��ɽ��' ]);
a_t.add('0_19_4', [ '430401', '���Ϻ�����Ͻ��', '430407', '����ʡ������ʯ����', '430421',
		'���Ϻ�����', '430424', '���Ϻ����ⶫ��', '430482', '����ʡ������', '430405',
		'����ʡ������������', '430406', '����ʡ�����������', '430408', '����ʡ������������', '430412',
		'���Ϻ���������', '430422', '���Ϻ���������', '430423', '���Ϻ�����ɽ��', '430426',
		'���Ϻ������', '430481', '���Ϻ���������' ]);
a_t.add('0_19_5', [ '430502', '��������˫����', '430521', '���������۶���', '430524',
		'��������¡����', '430528', '��������������', '430501', '����������Ͻ��', '430503',
		'��������������', '430511', '��������������', '430522', '��������������', '430523', '����������',
		'430525', '��������������', '430527', '��������������', '430529', '���������ǲ�����������',
		'430581', '����ʡ�����' ]);
a_t.add('0_19_6', [ '430701', '���ϳ�����Ͻ��', '430721', '���ϳ��°�����', '430723',
		'���ϳ������', '430702', '���ϳ���������', '430703', '���ϳ��¶�����', '430722',
		'���ϳ��º�����', '430724', '���ϳ��������', '430725', '���ϳ�����Դ��', '430726',
		'���ϳ���ʯ����', '430781', '���ϳ��½�����' ]);
a_t.add('0_19_7', [ '430801', '���ϴ�ӹ��Ͻ��', '430802', '���ϴ�ӹ������', '430811',
		'���ϴ�ӹ����Դ��', '430821', '���ϴ�ӹ������', '430822', '���ϴ�ӹɣֲ��' ]);
a_t.add('0_19_8', [ '430901', '����ʡ��������Ͻ��', '430902', '����ʡ������������', '430903',
		'����ʡ�����к�ɽ��', '430921', '����ʡ����������', '430922', '����ʡ�������ҽ���', '430923',
		'����ʡ�����а�����', '430981', '����ʡ�佭��' ]);
a_t.add('0_19_9', [ '431001', '����ʡ��������Ͻ��', '431002', '����ʡ�����б�����', '431003',
		'����ʡ������������', '431021', '����ʡ�����й�����', '431022', '����ʡ������������', '431023',
		'����ʡ������������', '431024', '����ʡ�����мκ���', '431025', '����ʡ������������', '431026',
		'����ʡ�����������', '431027', '����ʡ�����й���', '431028', '����ʡ�����а�����', '431081',
		'����ʡ������' ]);
a_t.add('0_19_10', [ '431103', '����ʡ��������ˮ̲��', '431124', '����ʡ�����е���', '431127',
		'����ʡ��������ɽ��', '431101', '����ʡ��������Ͻ��', '431102', '����ʡ������������', '431121',
		'����ʡ������������', '431122', '����ʡ�����ж�����', '431123', '����ʡ������˫����', '431125',
		'����ʡ�����н�����', '431126', '����ʡ��������Զ��', '431128', '����ʡ������������', '431129',
		'����ʡ�����н�������������' ]);
a_t.add('0_19_11', [ '431201', '����ʡ��������Ͻ��', '431222', '����ʡ������������', '431226',
		'����ʡ��������������������', '431281', '����ʡ�齭��', '431202', '����ʡ�����к׳���', '431221',
		'����ʡ�������з���', '431223', '����ʡ�����г�Ϫ��', '431224', '����ʡ������������', '431225',
		'����ʡ�����л�ͬ��', '431227', '����ʡ�������»ζ���������', '431228', '����ʡ�������ƽ�����������',
		'431229', '����ʡ�����о������嶱��������', '431230', '����ʡ������ͨ������������' ]);
a_t.add('0_19_12', [ '431302', '����ʡ¦����¦����', '431382', '����ʡ��Դ��', '431301',
		'����ʡ¦������Ͻ��', '431321', '����ʡ¦����˫����', '431322', '����ʡ¦�����»���', '431381',
		'����ʡ��ˮ����' ]);
a_t.add('0_19_13', [ '433122', '����������Ϫ��', '433125', '��������������', '433130',
		'����������ɽ��', '433101', '��������������', '433123', '�������������', '433124',
		'����������ԫ��', '433126', '��������������', '433127', '����������˳��' ]);
a_t.add('0_20', [ '542100', '������������������', '542300', '�����������տ������', '542600',
		'������������֥����', '540100', '����������������', '542200', '����������ɽ�ϵ���', '542400',
		'������������������', '542500', '�����������������' ]);
a_t.add('0_20_0', [ '542123', '���ز���������', '542126', '���ز���������', '542129',
		'���ز���â����', '542121', '���ز�����', '542122', '���ز���������', '542124',
		'���ز�����������', '542125', '���ز���������', '542127', '���ز���������', '542128',
		'���ز�������', '542132', '���ز�����¡��', '542133', '���ز����߰���' ]);
a_t.add('0_20_1', [ '542327', '�����տ�������', '542328', '�����տ���лͨ����', '542329',
		'�����տ��������', '542323', '�����տ�������', '542326', '�����տ���������', '542301',
		'�����տ����տ�����', '542322', '�����տ�����ľ����', '542324', '�����տ�������', '542325',
		'�����տ���������', '542330', '�����տ����ʲ���', '542331', '�����տ�������', '542332',
		'�����տ��򶨽���', '542333', '�����տ����ٰ���', '542334', '�����տ����Ƕ���', '542335',
		'�����տ���¡��', '542336', '�����տ�������ľ��', '542337', '�����տ���������', '542338',
		'�����տ���ڰ���' ]);
a_t.add('0_20_2', [ '542625', '������֥������', '542621', '������֥��', '542622',
		'������֥����������', '542623', '������֥������', '542624', '������֥ī����', '542626',
		'������֥������', '542627', '������֥����' ]);
a_t.add('0_20_3', [ '540101', '����������Ͻ��', '540122', '��������������', '540125',
		'������������������', '540102', '���������ǹ���', '540121', '��������������', '540123',
		'����������ľ��', '540124', '����������ˮ��', '540126', '��������������', '540127',
		'��������ī�񹤿���' ]);
a_t.add('0_20_4', [ '542221', '����ɽ���˶���', '542224', '����ɽ��ɣ����', '542227',
		'����ɽ�ϴ�����', '542231', '����ɽ��¡����', '542222', '����ɽ��������', '542223',
		'����ɽ�Ϲ�����', '542225', '����ɽ�������', '542226', '����ɽ��������', '542228',
		'����ɽ��������', '542229', '����ɽ�ϼӲ���', '542232', '����ɽ�ϴ�����', '542233',
		'����ɽ���˿�����' ]);
a_t.add('0_20_5', [ '542421', '����������', '542422', '��������������', '542423',
		'��������������', '542424', '��������������', '542425', '��������������', '542426',
		'��������������', '542427', '������������', '542428', '�������������', '542429',
		'��������������', '542430', '��������������' ]);
a_t.add('0_20_6', [ '542521', '���ذ���������', '542522', '���ذ���������', '542523',
		'���ذ��������', '542524', '���ذ���������', '542525', '���ذ���Ｊ��', '542526',
		'���ذ��������', '542527', '���ذ��������' ]);
a_t.add('0_21', [ '610100', '����ʡ������������', '610200', '����ʡͭ����', '610300',
		'����ʡ������', '610400', '����ʡ������', '610500', '����ʡμ����', '610600', '����ʡ�Ӱ���',
		'610700', '����ʡ������', '610800', '����ʡ������', '610900', '����ʡ������', '611000',
		'����ʡ������' ]);
a_t.add('0_21_0', [ '610101', '����������Ͻ��', '610104', '��������������', '610113',
		'��������������', '610116', '����ʡ�����г�����', '610125', '������������', '610102',
		'���������³���', '610103', '��������������', '610111', '�������������', '610112',
		'��������δ����', '610114', '��������������', '610115', '����ʡ������������', '610122',
		'��������������', '610124', '��������������', '610126', '��������������' ]);
a_t.add('0_21_1', [ '610201', '����ͭ����Ͻ��', '610204', '����ʡͭ����ҫ����', '610202',
		'����ͭ��������', '610203', '����ͭ��ӡ̨��', '610222', '����ͭ���˾���' ]);
a_t.add('0_21_2', [ '610301', '����������Ͻ��', '610304', '����ʡ�����г²���', '610324',
		'��������������', '610328', '��������ǧ����', '610331', '��������̫����', '610302',
		'��������μ����', '610303', '����������̨��', '610322', '��������������', '610323',
		'���������ɽ��', '610326', '��������ü��', '610327', '��������¤��', '610329', '��������������',
		'610330', '������������' ]);
a_t.add('0_21_3', [ '610402', '���������ض���', '610422', '����������ԭ��', '610425',
		'����������Ȫ��', '610428', '��������������', '610431', '���������书��', '610401',
		'����������Ͻ��', '610403', '��������������', '610404', '��������μ����', '610423',
		'��������������', '610424', '��������Ǭ��', '610426', '��������������', '610427', '������������',
		'610429', '��������Ѯ����', '610430', '��������������', '610481', '����������ƽ��' ]);
a_t.add('0_21_4', [ '610501', '����ʡμ������Ͻ��', '610522', '����ʡμ����������', '610525',
		'����ʡμ���гγ���', '610581', '����ʡ������', '610502', '����ʡμ������μ��', '610521',
		'����ʡμ���л���', '610523', '����ʡμ���д�����', '610524', '����ʡμ���к�����', '610526',
		'����ʡμ�����ѳ���', '610527', '����ʡμ���а�ˮ��', '610528', '����ʡμ���и�ƽ��', '610582',
		'����ʡ������' ]);
a_t.add('0_21_5', [ '610601', '����ʡ�Ӱ�����Ͻ��', '610622', '����ʡ�Ӱ����Ӵ���', '610626',
		'����ʡ�Ӱ���������', '610602', '����ʡ�Ӱ��б�����', '610621', '����ʡ�Ӱ����ӳ���', '610623',
		'����ʡ�Ӱ����ӳ���', '610624', '����ʡ�Ӱ��а�����', '610625', '����ʡ�Ӱ���־����', '610627',
		'����ʡ�Ӱ��и�Ȫ��', '610628', '����ʡ�Ӱ��и���', '610629', '����ʡ�Ӱ����崨��', '610630',
		'����ʡ�Ӱ����˴���', '610631', '����ʡ�Ӱ��л�����', '610632', '����ʡ�Ӱ��л�����' ]);
a_t.add('0_21_6', [ '610701', '����ʡ��������Ͻ��', '610702', '����ʡ�����к�̨��', '610721',
		'����ʡ��������֣��', '610722', '����ʡ�����гǹ���', '610723', '����ʡ����������', '610724',
		'����ʡ������������', '610725', '����ʡ����������', '610726', '����ʡ��������ǿ��', '610727',
		'����ʡ������������', '610728', '����ʡ�����������', '610729', '����ʡ������������', '610730',
		'����ʡ�����з�ƺ��' ]);
a_t.add('0_21_7', [ '610801', '����ʡ��������Ͻ��', '610802', '����ʡ������������', '610821',
		'����ʡ��������ľ��', '610822', '����ʡ�����и�����', '610823', '����ʡ�����к�ɽ��', '610824',
		'����ʡ�����о�����', '610825', '����ʡ�����ж�����', '610826', '����ʡ�����������', '610827',
		'����ʡ��������֬��', '610828', '����ʡ�����м���', '610829', '����ʡ�������Ɽ��', '610830',
		'����ʡ�������彧��', '610831', '����ʡ������������' ]);
a_t.add('0_21_8', [ '610901', '����ʡ��������Ͻ��', '610922', '����ʡ������ʯȪ��', '610926',
		'����ʡ������ƽ����', '610929', '����ʡ�����а׺���', '610902', '����ʡ�����к�����', '610921',
		'����ʡ�����к�����', '610923', '����ʡ������������', '610924', '����ʡ������������', '610925',
		'����ʡ������᰸���', '610927', '����ʡ��������ƺ��', '610928', '����ʡ������Ѯ����' ]);
a_t.add('0_21_9', [ '611021', '����ʡ������������', '611024', '����ʡ������ɽ����', '611001',
		'����ʡ��������Ͻ��', '611002', '����ʡ������������', '611022', '����ʡ�����е�����', '611023',
		'����ʡ������������', '611025', '����ʡ����������', '611026', '����ʡ��������ˮ��' ]);
a_t.add('0_22', [ '620400', '����ʡ������', '620600', '����ʡ������', '620700', '����ʡ��Ҵ��',
		'620800', '����ʡƽ����', '623000', '����ʡ���ϲ���������', '620900', '����ʡ��Ȫ��',
		'621000', '����ʡ������', '621100', '����ʡ������', '621200', '����ʡ¤����', '622900',
		'����ʡ���Ļ���������', '620100', '����ʡ������', '620200', '����ʡ��������', '620300',
		'����ʡ�����', '620500', '����ʡ��ˮ��' ]);
a_t.add('0_22_0',
		[ '620403', '�������ƽ����', '620423', '���������̩��', '620401', '���������Ͻ��',
				'620402', '���������', '620421', '���������Զ��', '620422', '�������������' ]);
a_t.add('0_22_1', [ '620621', '����ʡ������������', '620601', '����ʡ��������Ͻ��', '620602',
		'����ʡ������������', '620622', '����ʡ�����й�����', '620623', '����ʡ��������ף����������' ]);
a_t.add('0_22_2', [ '620722', '����ʡ��Ҵ��������', '620701', '����ʡ��Ҵ����Ͻ��', '620702',
		'����ʡ��Ҵ�и�����', '620721', '����ʡ��Ҵ������ԣ����������', '620723', '����ʡ��Ҵ��������',
		'620724', '����ʡ��Ҵ�и�̨��', '620725', '����ʡ��Ҵ��ɽ����' ]);
a_t.add('0_22_3', [ '620821', '����ʡƽ����������', '620825', '����ʡƽ����ׯ����', '620826',
		'����ʡƽ���о�����', '620801', '����ʡƽ������Ͻ��', '620802', '����ʡƽ���������', '620822',
		'����ʡƽ������̨��', '620823', '����ʡƽ���г�����', '620824', '����ʡƽ���л�ͤ��' ]);
a_t.add('0_22_4', [ '623022', '�������׿����', '623025', '�������������', '623001',
		'����ʡ������', '623021', '���������̶��', '623023', '�������������', '623024',
		'������ϵ�����', '623026', '�������µ����', '623027', '��������ĺ���' ]);
a_t.add('0_22_5', [ '620901', '����ʡ��Ȫ����Ͻ��', '620902', '����ʡ��Ȫ��������', '620921',
		'����ʡ��Ȫ�н�����', '620922', '����ʡ��Ȫ�а�����', '620923', '����ʡ��Ȫ���౱�ɹ���������',
		'620924', '����ʡ��Ȫ�а�������������������', '620981', '����ʡ������', '620982', '����ʡ�ػ���' ]);
a_t.add('0_22_6', [ '621001', '����ʡ��������Ͻ��', '621002', '����ʡ������������', '621021',
		'����ʡ�����������', '621022', '����ʡ�����л���', '621023', '����ʡ�����л�����', '621024',
		'����ʡ�����к�ˮ��', '621025', '����ʡ������������', '621026', '����ʡ����������', '621027',
		'����ʡ��������ԭ��' ]);
a_t.add('0_22_7', [ '621101', '����ʡ��������Ͻ��', '621102', '����ʡ�����а�����', '621121',
		'����ʡ������ͨμ��', '621122', '����ʡ������¤����', '621123', '����ʡ������μԴ��', '621124',
		'����ʡ�����������', '621125', '����ʡ����������', '621126', '����ʡ���������' ]);
a_t.add('0_22_8', [ '621224', '����ʡ¤���п���', '621228', '����ʡ¤����������', '621201',
		'����ʡ¤������Ͻ��', '621202', '����ʡ¤�����䶼��', '621221', '����ʡ¤���г���', '621222',
		'����ʡ¤��������', '621223', '����ʡ¤����崲���', '621225', '����ʡ¤����������', '621226',
		'����ʡ¤��������', '621227', '����ʡ¤���л���' ]);
a_t.add('0_22_9', [ '622921', '����������', '622924', '�������Ĺ����', '622901', '����������',
		'622922', '�������Ŀ�����', '622923', '��������������', '622925', '�������ĺ�����',
		'622926', '�������Ķ�����������', '622927', '�������Ļ�ʯɽ�����嶫����������������' ]);
a_t.add('0_22_10', [ '620101', '����������Ͻ��', '620105', '�������ݰ�����', '620122',
		'�������ݸ�����', '620102', '�������ݳǹ���', '620103', '���������������', '620104',
		'��������������', '620111', '�������ݺ����', '620121', '��������������', '620123',
		'��������������' ]);
a_t.add('0_22_11', [ '620201', '�����������Ͻ��' ]);
a_t.add('0_22_12', [ '620302', '����������', '620301', '��������Ͻ��', '620321',
		'������������' ]);
a_t.add('0_22_13', [ '620502', '������ˮ�س���', '620522', '������ˮ�ذ���', '620501',
		'������ˮ��Ͻ��', '620503', '������ˮ������', '620521', '������ˮ��ˮ��', '620523',
		'������ˮ�ʹ���', '620524', '������ˮ��ɽ��', '620525', '������ˮ�żҴ�����������' ]);
a_t.add('0_23', [ '630100', '�ຣʡ������', '632100', '�ຣʡ��������', '632200',
		'�ຣʡ��������������', '632300', '�ຣʡ���ϲ���������', '632500', '�ຣʡ���ϲ���������', '632600',
		'�ຣʡ�������������', '632700', '�ຣʡ��������������', '632800', '�ຣʡ�����ɹ������������' ]);
a_t.add('0_23_0', [ '630101', '�ຣ������Ͻ��', '630104', '�ຣ����������', '630122',
		'�ຣʡ������������', '630102', '�ຣ�����Ƕ���', '630103', '�ຣ����������', '630105',
		'�ຣ�����Ǳ���', '630121', '�ຣ������ͨ��������������', '630123', '�ຣʡ��������Դ��' ]);
a_t.add('0_23_1', [ '632122', '�ຣ������ͻ�������������', '632128', '�ຣ����ѭ��������������',
		'632121', '�ຣ����ƽ����', '632123', '�ຣ�����ֶ���', '632126', '�ຣ������������������',
		'632127', '�ຣ������¡����������' ]);
a_t.add('0_23_2', [ '632222', '�ຣ����������', '632221', '�ຣ������Դ����������', '632223',
		'�ຣ����������', '632224', '�ຣ�����ղ���' ]);
a_t.add('0_23_3', [ '632321', '�ຣ����ͬ����', '632324', '�ຣ���Ϻ����ɹ���������', '632322',
		'�ຣ���ϼ�����', '632323', '�ຣ���������' ]);
a_t.add('0_23_4', [ '632522', '�ຣ����ͬ����', '632525', '�ຣ���Ϲ�����', '632521',
		'�ຣ���Ϲ�����', '632523', '�ຣ���Ϲ����', '632524', '�ຣ�����˺���' ]);
a_t.add('0_23_5', [ '632623', '�ຣ����ʵ���', '632626', '�ຣ���������', '632621',
		'�ຣ����������', '632622', '�ຣ���������', '632624', '�ຣ���������', '632625',
		'�ຣ���������' ]);
a_t
		.add('0_23_6', [ '632722', '�ຣ�����Ӷ���', '632725', '�ຣ������ǫ��', '632721',
				'�ຣ������', '632723', '�ຣ�����ƶ���', '632724', '�ຣ�����ζ���', '632726',
				'�ຣ������������' ]);
a_t.add('0_23_7', [ '632801', '�ຣ�������ľ��', '632802', '�ຣ�����������', '632821',
		'�ຣ����������', '632822', '�ຣ����������', '632823', '�ຣ���������' ]);
a_t.add('0_24', [ '420200', '����ʡ��ʯ��', '420300', '����ʡʮ����', '420500', '����ʡ�˲���',
		'420600', '����ʡ�差��', '421200', '����ʡ������', '421300', '����ʡ������', '422800',
		'����ʡ��ʩ����������������', '429000', '����ʡʡֱϽ������λ', '420100', '����ʡ�人��', '421000',
		'����ʡ������', '421100', '����ʡ�Ƹ���', '420700', '����ʡ������', '420800', '����ʡ������',
		'420900', '����ʡТ����' ]);
a_t.add('0_24_0', [ '420201', '������ʯ��Ͻ��', '420202', '������ʯ����', '420203',
		'������ʯ����ɽ��', '420205', '������ʯ��ɽ��', '420222', '����ʡ��ʯ��������', '420204',
		'������ʯ��½��', '420281', '����ʡ��ұ��' ]);
a_t.add('0_24_1', [ '420301', '����ʮ����Ͻ��', '420303', '����ʮ��������', '420321',
		'����ʡʮ��������', '420323', '����ʡʮ������ɽ��', '420324', '����ʡʮ������Ϫ��', '420381',
		'����ʡ��������', '420302', '����ʮ��é����', '420322', '����ʡʮ����������', '420325',
		'����ʡʮ���з���' ]);
a_t.add('0_24_2', [ '420502', '�����˲�������', '420503', '�����˲���Ҹ���', '420505',
		'����ʡ�˲��ЪVͤ��', '420506', '����ʡ�˲���������', '420525', '�����˲�Զ����', '420501',
		'�����˲���Ͻ��', '420504', '�����˲������', '420526', '�����˲���ɽ��', '420529',
		'�����˲����������������', '420527', '�����˲�������', '420528', '�����˲�����������������',
		'420581', '�����˲��˶���', '420582', '�����˲�������', '420583', '����ʡ�˲���֦����' ]);
a_t.add('0_24_3', [ '420606', '����ʡ�差�з�����', '420625', '�����差�ȳ���', '420683',
		'�����差������', '420601', '�����差��Ͻ��', '420602', '�����差�����', '420607',
		'����ʡ�差��������', '420624', '�����差������', '420626', '�����差������', '420682',
		'�����差�Ϻӿ���', '420684', '����ʡ�˳���' ]);
a_t.add('0_24_4', [ '421201', '����ʡ��������Ͻ��', '421202', '����ʡ�������̰���', '421221',
		'����ʡ�����м�����', '421222', '����ʡ������ͨ����', '421223', '����ʡ�����г�����', '421224',
		'����ʡ������ͨɽ��', '421281', '����ʡ�����' ]);
a_t.add('0_24_5', [ '421301', '����ʡ��������Ͻ��', '421302', '����ʡ������������', '421381',
		'����ʡ��ˮ��' ]);
a_t.add('0_24_6', [ '422801', '������ʩ��', '422802', '������ʩ������', '422822',
		'������ʩ��ʼ��', '422823', '������ʩ�Ͷ���', '422825', '������ʩ������', '422826',
		'������ʩ�̷���', '422827', '������ʩ������', '422828', '������ʩ�׷���' ]);
a_t.add('0_24_7', [ '429004', '����ʡ������', '429005', '����ʡǱ����', '429006', '����ʡ������',
		'429021', '����ʡ��ũ������' ]);
a_t.add('0_24_8', [ '420104', '�����人�~����', '420105', '�����人������', '420106',
		'�����人�����', '420107', '�����人��ɽ��', '420111', '�����人��ɽ��', '420112',
		'�����人��������', '420113', '�����人������', '420114', '�����人�̵���', '420115',
		'����ʡ�人�н�����', '420116', '����ʡ�人�л�����', '420117', '����ʡ�人��������', '420101',
		'�����人��Ͻ��', '420102', '�����人������', '420103', '�����人������' ]);
a_t.add('0_24_9', [ '421001', '����ʡ��������Ͻ��', '421023', '����ʡ�����м�����', '421083',
		'����ʡ�����', '421002', '����ʡ������ɳ����', '421003', '����ʡ�����о�����', '421022',
		'����ʡ�����й�����', '421024', '����ʡ�����н�����', '421081', '����ʡʯ����', '421087',
		'����ʡ������' ]);
a_t.add('0_24_10', [ '421101', '����ʡ�Ƹ�����Ͻ��', '421122', '����ʡ�Ƹ��к찲��', '421123',
		'����ʡ�Ƹ���������', '421124', '����ʡ�Ƹ���Ӣɽ��', '421125', '����ʡ�Ƹ����ˮ��', '421126',
		'����ʡ�Ƹ���ޭ����', '421127', '����ʡ�Ƹ��л�÷��', '421181', '����ʡ�����', '421182',
		'����ʡ��Ѩ��', '421102', '����ʡ�Ƹ��л�����', '421121', '����ʡ�Ƹ����ŷ���' ]);
a_t.add('0_24_11', [ '420701', '����������Ͻ��', '420704', '�������ݶ�����', '420702',
		'�����������Ӻ���', '420703', '�������ݻ�����' ]);
a_t.add('0_24_12', [ '420802', '�������Ŷ�����', '420822', '����ʡ������ɳ����', '420801',
		'����������Ͻ��', '420804', '����ʡ�����ж޵���', '420821', '����ʡ�����о�ɽ��', '420881',
		'����ʡ������' ]);
a_t.add('0_24_13', [ '420901', '����Т����Ͻ��', '420922', '����Т�д�����', '420982',
		'����Т�а�½��', '420902', '����Т��Т����', '420921', '����Т��Т����', '420923',
		'����Т��������', '420981', '����Т��Ӧ����', '420984', '����ʡ������' ]);
a_t.add('0_25', [ '710100', '̨��ʡ̨����', '710200', '̨��ʡ������', '710300', '̨��ʡ��¡��',
		'710400', '̨��ʡ̨����', '710500', '̨��ʡ̨����', '710600', '̨��ʡ������', '710700',
		'̨��ʡ������', '710800', '̨��ʡ̨����(������)��', '710900', '̨��ʡ������(������)', '711000',
		'̨��ʡ������(����)', '711100', '̨��ʡ��԰��(��԰��)', '711200', '̨��ʡ������(������)',
		'711300', '̨��ʡ̨����(��ԭ��)', '711400', '̨��ʡ�û���(�û���)', '711500',
		'̨��ʡ��Ͷ��(��Ͷ��)', '711600', '̨��ʡ������(̫����)', '711700', '̨��ʡ������(������)',
		'711800', '̨��ʡ̨����(��Ӫ��)', '711900', '̨��ʡ������(��ɽ��)', '712000',
		'̨��ʡ������(������)', '712100', '̨��ʡ̨����(̨����)��', '712200', '̨��ʡ������(������)',
		'712300', '̨��ʡ�����(����)' ]);
a_t.add('0_25_0', [ '710101', '̨��̨����������', '710102', '̨��̨����ͬ��', '710103',
		'̨��̨����ɽ��', '710104', '̨��̨����ɽ��', '710105', '̨��̨������', '710106',
		'̨��̨������', '710107', '̨��̨��������', '710108', '̨��̨��ʿ����', '710109',
		'̨��̨����Ͷ��', '710110', '̨��̨���ں���', '710111', '̨��̨���ϸ���', '710112',
		'̨��̨����ɽ��' ]);
a_t.add('0_25_1', [ '710201', '̨�����������', '710202', '̨�����ǰ����', '710203',
		'̨�����������', '710204', '̨�����������', '710205', '̨����۹�ɽ��', '710206',
		'̨����������', '710207', '̨�����ǰ����', '710208', '̨�����������', '710209',
		'̨�������Ӫ��', '710210', '̨����������', '710211', '̨�����С����' ]);
a_t.add('0_25_2', [ '710301', '̨���¡�ʰ���', '710302', '̨���¡������', '710303',
		'̨���¡������', '710304', '̨���¡��ɽ��', '710305', '̨���¡������', '710306',
		'̨���¡ůů��', '710307', '̨���¡�߶���' ]);
a_t.add('0_25_3', [ '710401', '̨��̨������', '710402', '̨��̨�ж���', '710403', '̨��̨������',
		'710404', '̨��̨������', '710405', '̨��̨�б���', '710406', '̨��̨�б�����', '710407',
		'̨��̨��������', '710408', '̨��̨��������' ]);
a_t.add('0_25_4',
		[ '710501', '̨��̨��������', '710502', '̨��̨�϶���', '710503', '̨��̨������',
				'710504', '̨��̨�ϱ���', '710505', '̨��̨�ϰ�ƽ��', '710506', '̨��̨�ϰ�����' ]);
a_t.add('0_25_5',
		[ '710601', '̨��������', '710602', '̨��������', '710603', '̨��������ɽ��' ]);
a_t.add('0_25_6', [ '710701', '̨����嶫��', '710702', '̨���������' ]);
a_t.add('0_25_7', [ '710801', '̨��ʡ̨����(������)��' ]);
a_t.add('0_25_8', [ '710901', '̨��ʡ������(������)' ]);
a_t.add('0_25_9', [ '711001', '̨��ʡ������(����)' ]);
a_t.add('0_25_10', [ '711101', '̨��ʡ��԰��(��԰��)' ]);
a_t.add('0_25_11', [ '711201', '̨��ʡ������(������)' ]);
a_t.add('0_25_12', [ '711301', '̨��ʡ̨����(��ԭ��)' ]);
a_t.add('0_25_13', [ '711401', '̨��ʡ�û���(�û���)' ]);
a_t.add('0_25_14', [ '711501', '̨��ʡ��Ͷ��(��Ͷ��)' ]);
a_t.add('0_25_15', [ '711601', '̨��ʡ������(̫����)' ]);
a_t.add('0_25_16', [ '711701', '̨��ʡ������(������)' ]);
a_t.add('0_25_17', [ '711801', '̨��ʡ̨����(��Ӫ��)' ]);
a_t.add('0_25_18', [ '711901', '̨��ʡ������(��ɽ��)' ]);
a_t.add('0_25_19', [ '712001', '̨��ʡ������(������)' ]);
a_t.add('0_25_20', [ '712101', '̨��ʡ̨����(̨����)��' ]);
a_t.add('0_25_21', [ '712201', '̨��ʡ������(������)' ]);
a_t.add('0_25_22', [ '712301', '̨��ʡ�����(����)' ]);
a_t.add('0_27', [ '640100', '���Ļ���������������', '640200', '���Ļ���������ʯ��ɽ��', '640300',
		'���Ļ���������������', '640400', '���Ļ�����������ԭ��', '640500', '���Ļ���������������' ]);
a_t.add('0_27_0', [ '640101', '����������Ͻ��', '640104', '���Ļ���������������������', '640105',
		'���Ļ���������������������', '640106', '���Ļ��������������н����', '640121', '��������������',
		'640122', '��������������', '640181', '���Ļ���������������' ]);
a_t.add('0_27_1', [ '640201', '����ʯ��ɽ��Ͻ��', '640202', '����ʯ��ɽ�������', '640205',
		'���Ļ���������ʯ��ɽ�л�ũ��', '640221', '����ʯ��ɽƽ����' ]);
a_t.add('0_27_2', [ '640301', '���Ļ�����������������Ͻ��', '640302', '���Ļ�����������������ͨ��',
		'640323', '���Ļ����������������γ���', '640324', '���Ļ���������������ͬ����', '640381',
		'���Ļ�����������ͭϿ��' ]);
a_t.add('0_27_3', [ '640401', '���Ļ�����������ԭ����Ͻ��', '640402', '���Ļ�����������ԭ��ԭ����',
		'640422', '���Ļ�����������ԭ��������', '640423', '���Ļ�����������ԭ��¡����', '640424',
		'���Ļ�����������ԭ����Դ��', '640425', '���Ļ�����������ԭ��������' ]);
a_t.add('0_27_4', [ '640501', '���Ļ�����������������Ͻ��', '640502', '���Ļ���������������ɳ��ͷ��',
		'640521', '���Ļ���������������������', '640522', '���Ļ��������������к�ԭ��' ]);
a_t
		.add('0_28', [ '410100', '����ʡ֣����', '410500', '����ʡ������', '410900',
				'����ʡ�����', '411300', '����ʡ������', '410600', '����ʡ�ױ���', '411700',
				'����ʡפ�����', '410200', '����ʡ������', '410300', '����ʡ������', '410400',
				'����ʡƽ��ɽ��', '410700', '����ʡ������', '410800', '����ʡ������', '411000',
				'����ʡ�����', '411100', '����ʡ�����', '411200', '����ʡ����Ͽ��', '411400',
				'����ʡ������', '411500', '����ʡ������', '411600', '����ʡ�ܿ���' ]);
a_t.add('0_28_0', [ '410103', '����֣�ݶ�����', '410106', '����֣���Ͻ���', '410181',
		'����֣�ݹ�����', '410184', '����ʡ��֣��', '410101', '����֣����Ͻ��', '410102',
		'����֣����ԭ��', '410104', '����֣�ݹܳǻ�����', '410105', '����֣�ݽ�ˮ��', '410108',
		'����֣�ݻݼ���', '410122', '����֣����Ĳ��', '410182', '����ʡ������', '410183', '����ʡ������',
		'410185', '����ʡ�Ƿ���' ]);
a_t.add('0_28_1', [ '410503', '���ϰ���������', '410522', '���ϰ�����', '410527',
		'���ϰ����ڻ���', '410502', '���ϰ����ķ���', '410505', '����ʡ����������', '410506',
		'����ʡ������������', '410523', '���ϰ���������', '410526', '���ϰ�������', '410581',
		'����ʡ������', '410501', '���ϰ�����Ͻ��' ]);
a_t.add('0_28_2', [ '410922', '������������', '410927', '�������̨ǰ��', '410901',
		'���������Ͻ��', '410902', '�������������', '410923', '�������������', '410926',
		'�����������', '410928', '���������' ]);
a_t.add('0_28_3', [ '411303', '����ʡ������������', '411324', '����ʡ��������ƽ��', '411327',
		'����ʡ������������', '411381', '����ʡ������', '411301', '����ʡ��������Ͻ��', '411302',
		'����ʡ�����������', '411321', '����ʡ������������', '411322', '����ʡ�����з�����', '411323',
		'����ʡ��������Ͽ��', '411325', '����ʡ������������', '411326', '����ʡ������������', '411328',
		'����ʡ�������ƺ���', '411329', '����ʡ��������Ұ��', '411330', '����ʡ������ͩ����' ]);
a_t.add('0_28_4',
		[ '410601', '���Ϻױ���Ͻ��', '410602', '���Ϻױں�ɽ��', '410603', '���Ϻױ�ɽ����',
				'410611', '���Ϻױ�俱���', '410621', '���Ϻױڿ���', '410622', '���Ϻױ����' ]);
a_t.add('0_28_5', [ '411701', '����ʡפ�������Ͻ��', '411702', '����ʡפ����������', '411721',
		'����ʡפ�������ƽ��', '411722', '����ʡפ������ϲ���', '411723', '����ʡפ�����ƽ����', '411724',
		'����ʡפ�����������', '411725', '����ʡפ�����ȷɽ��', '411726', '����ʡפ�����������', '411727',
		'����ʡפ�����������', '411728', '����ʡפ�������ƽ��', '411729', '����ʡפ������²���' ]);
a_t.add('0_28_6', [ '410201', '���Ͽ�����Ͻ��', '410204', '���Ͽ����¥��', '410221',
		'���Ͽ������', '410224', '���Ͽ�����', '410202', '���Ͽ�����ͤ��', '410203',
		'���Ͽ���˳�ӻ�����', '410205', '���Ͽ�������̨��', '410211', '���Ͽ��������', '410222',
		'���Ͽ���ͨ����', '410223', '���Ͽ���ξ����', '410225', '���Ͽ���������' ]);
a_t.add('0_28_7', [ '410301', '����������Ͻ��', '410304', '����ʡ�����Оe�ӻ�����', '410307',
		'����ʡ������������', '410324', '���������ﴨ��', '410327', '��������������', '410381',
		'����������ʦ��', '410302', '���������ϳ���', '410303', '��������������', '410305',
		'��������������', '410306', '��������������', '410322', '���������Ͻ���', '410323',
		'���������°���', '410325', '������������', '410326', '��������������', '410328',
		'��������������', '410329', '��������������' ]);
a_t.add('0_28_8', [ '410402', '����ƽ��ɽ�»���', '410421', '����ƽ��ɽ������', '410425',
		'����ƽ��ɽۣ��', '410401', '����ƽ��ɽ��Ͻ��', '410403', '����ƽ��ɽ������', '410404',
		'����ʡƽ��ɽ��ʯ����', '410411', '����ƽ��ɽտ����', '410422', '����ƽ��ɽҶ��', '410423',
		'����ƽ��ɽ³ɽ��', '410481', '����ƽ��ɽ�����', '410482', '����ƽ��ɽ������' ]);
a_t.add('0_28_9', [ '410701', '����������Ͻ��', '410702', '�������������', '410703',
		'��������������', '410704', '���������Ȫ��', '410711', '����������Ұ��', '410721', '����������',
		'410724', '������������', '410725', '��������ԭ����', '410726', '���������ӽ���',
		'410727', '�������������', '410728', '�������糤ԫ��', '410781', '��������������',
		'410782', '�������������' ]);
a_t.add('0_28_10',
		[ '410801', '���Ͻ�����Ͻ��', '410802', '���Ͻ��������', '410803', '���Ͻ�����վ��',
				'410804', '���Ͻ��������', '410811', '���Ͻ���ɽ����', '410821', '���Ͻ���������',
				'410822', '���Ͻ���������', '410823', '���Ͻ���������', '410825', '���Ͻ�������',
				'410881', '���Ͻ�����Դ��', '410882', '���Ͻ���������', '410883', '����ʡ������' ]);
a_t.add('0_28_11', [ '411001', '���������Ͻ��', '411024', '�������۳����', '411082',
		'�������������', '411002', '�������κ����', '411023', '���������', '411025',
		'����ʡ����������', '411081', '�������������' ]);
a_t.add('0_28_12', [ '411102', '�������Դ����', '411121', '�������������', '411101',
		'���������Ͻ��', '411103', '����ʡ�����۱����', '411104', '����ʡ�����������', '411122',
		'���������ӱ��' ]);
a_t.add('0_28_13', [ '411201', '��������Ͽ��Ͻ��', '411224', '��������Ͽ¬����', '411202',
		'��������Ͽ������', '411221', '��������Ͽ�ų���', '411222', '��������Ͽ����', '411281',
		'��������Ͽ������', '411282', '��������Ͽ�鱦��' ]);
a_t.add('0_28_14', [ '411402', '����ʡ��������԰��', '411422', '����ʡ���������', '411426',
		'����ʡ������������', '411401', '����ʡ��������Ͻ��', '411403', '����ʡ�����������', '411421',
		'����ʡ��������Ȩ��', '411423', '����ʡ������������', '411424', '����ʡ�������ϳ���', '411425',
		'����ʡ�������ݳ���', '411481', '����ʡ������' ]);
a_t.add('0_28_15', [ '411501', '����ʡ��������Ͻ��', '411521', '����ʡ��������ɽ��', '411525',
		'����ʡ�����й�ʼ��', '411528', '����ʡ������Ϣ��', '411502', '����ʡ�����Л�����', '411503',
		'����ʡ������ƽ����', '411522', '����ʡ�����й�ɽ��', '411523', '����ʡ����������', '411524',
		'����ʡ�������̳���', '411526', '����ʡ�������괨��', '411527', '����ʡ�����л�����' ]);
a_t.add('0_28_16', [ '411602', '����ʡ�ܿ��д�����', '411624', '����ʡ�ܿ���������', '411627',
		'����ʡ�ܿ���̫����', '411628', '����ʡ�ܿ���¹����', '411681', '����ʡ�����', '411601',
		'����ʡ�ܿ�����Ͻ��', '411621', '����ʡ�ܿ��з�����', '411622', '����ʡ�ܿ���������', '411623',
		'����ʡ�ܿ�����ˮ��', '411625', '����ʡ�ܿ��е�����', '411626', '����ʡ�ܿ��л�����' ]);
a_t.add('0_29', [ '310100', '�Ϻ�����Ͻ��', '310200', '�Ϻ�����' ]);
a_t.add('0_29_0', [ '310101', '�Ϻ��л�����', '310103', '�Ϻ���¬����', '310104', '�Ϻ��������',
		'310105', '�Ϻ��г�����', '310106', '�Ϻ��о�����', '310107', '�Ϻ���������', '310108',
		'�Ϻ���բ����', '310109', '�Ϻ��к����', '310110', '�Ϻ���������', '310112', '�Ϻ���������',
		'310113', '�Ϻ��б�ɽ��', '310114', '�Ϻ��мζ���', '310115', '�Ϻ����ֶ�����', '310116',
		'�Ϻ��н�ɽ��', '310117', '�Ϻ����ɽ���', '310118', '�Ϻ���������', '310119', '�Ϻ����ϻ���',
		'310120', '�Ϻ��з�����' ]);
a_t.add('0_29_1', [ '310230', '�Ϻ��г�����' ]);
a_t.add('0_30', [ '320700', '����ʡ���Ƹ���', '320800', '����ʡ������', '320900', '����ʡ�γ���',
		'321000', '����ʡ������', '321100', '����ʡ����', '321300', '����ʡ��Ǩ��', '321200',
		'����ʡ̩����', '320100', '����ʡ�Ͼ���', '320200', '����ʡ������', '320300', '����ʡ������',
		'320400', '����ʡ������', '320500', '����ʡ������', '320600', '����ʡ��ͨ��' ]);
a_t.add('0_30_0', [ '320701', '�������Ƹ���Ͻ��', '320705', '�������Ƹ�������', '320706',
		'�������Ƹۺ�����', '320722', '�������Ƹ۶�����', '320723', '�������Ƹ۹�����', '320724',
		'����ʡ���Ƹ��й�����', '320703', '�������Ƹ�������', '320721', '�������Ƹ۸�����' ]);
a_t.add('0_30_1', [ '320801', '���ջ�����Ͻ��', '320803', '����ʡ�����г�����', '320804',
		'����ʡ�����л�����', '320811', '���ջ���������', '320826', '���ջ�����ˮ��', '320829',
		'���ջ���������', '320830', '���ջ���������', '320831', '���ջ��������', '320802',
		'���ջ��������' ]);
a_t.add('0_30_2', [ '320901', '�����γ���Ͻ��', '320902', '�����γ�ͤ����', '320903',
		'����ʡ�γ����ζ���', '320921', '�����γ���ˮ��', '320922', '�����γǱ�����', '320923',
		'�����γǸ�����', '320924', '�����γ�������', '320925', '�����γǽ�����', '320981',
		'�����γǶ�̨��', '320982', '����ʡ�����' ]);
a_t.add('0_30_3', [ '321001', '����������Ͻ��', '321002', '�������ݹ�����', '321003',
		'����ʡ������������', '321011', '��������ά����', '321023', '�������ݱ�Ӧ��', '321081',
		'��������������', '321084', '�������ݸ�����', '321088', '����ʡ������' ]);
a_t.add('0_30_4', [ '321101', '��������Ͻ��', '321102', '�����򽭾�����', '321111',
		'������������', '321112', '����ʡ���е�ͽ��', '321181', '�����򽭵�����', '321182',
		'����ʡ������', '321183', '����ʡ������' ]);
a_t.add('0_30_5', [ '321302', '����ʡ��Ǩ���޳���', '321311', '����ʡ��Ǩ����ԥ��', '321323',
		'����ʡ��Ǩ��������', '321324', '����ʡ��Ǩ��������', '321301', '����ʡ��Ǩ����Ͻ��', '321322',
		'����ʡ��Ǩ��������' ]);
a_t.add('0_30_6', [ '321201', '����ʡ̩������Ͻ��', '321202', '����ʡ̩���к�����', '321281',
		'����ʡ�˻���', '321282', '����ʡ������', '321284', '����ʡ������', '321203',
		'����ʡ̩���и߸���', '321283', '����ʡ̩����' ]);
a_t.add('0_30_7', [ '320101', '�����Ͼ���Ͻ��', '320103', '�����Ͼ�������', '320104',
		'�����Ͼ��ػ���', '320106', '�����Ͼ���¥��', '320107', '�����Ͼ��¹���', '320113',
		'�����Ͼ���ϼ��', '320114', '�����Ͼ��껨̨��', '320116', '����ʡ�Ͼ���������', '320124',
		'�����Ͼ���ˮ��', '320102', '�����Ͼ�������', '320105', '�����Ͼ�������', '320111',
		'�����Ͼ��ֿ���', '320115', '����ʡ�Ͼ��н�����', '320125', '�����Ͼ��ߴ���' ]);
a_t.add('0_30_8', [ '320201', '����������Ͻ��', '320203', '���������ϳ���', '320204',
		'��������������', '320206', '����ʡ�����л�ɽ��', '320211', '��������������', '320282',
		'��������������', '320202', '���������簲��', '320205', '����ʡ��������ɽ��', '320281',
		'��������������' ]);
a_t.add('0_30_9',
		[ '320302', '�������ݹ�¥��', '320303', '��������������', '320305', '�������ݼ�����',
				'320311', '��������Ȫɽ��', '320322', '������������', '320323', '��������ͭɽ��',
				'320381', '��������������', '320382', '��������������', '320301', '����������Ͻ��',
				'320304', '�������ݾ�����', '320321', '�������ݷ���', '320324', '�������������' ]);
a_t.add('0_30_10', [ '320401', '���ճ�����Ͻ��', '320402', '���ճ���������', '320405',
		'���ճ�����������', '320411', '���ճ����±���', '320481', '���ճ���������', '320482',
		'���ճ��ݽ�̳��', '320404', '���ճ�����¥��', '320412', '����ʡ�����������' ]);
a_t.add('0_30_11', [ '320584', '���������⽭��', '320585', '��������̫����', '320501',
		'����������Ͻ��', '320502', '�������ݲ�����', '320504', '�������ݽ�����', '320505',
		'����ʡ�����л�����', '320507', '����ʡ�����������', '320581', '�������ݳ�����', '320582',
		'���������żҸ���', '320503', '��������ƽ����', '320506', '����ʡ������������', '320583',
		'����������ɽ��' ]);
a_t.add('0_30_12',
		[ '320601', '������ͨ��Ͻ��', '320602', '������ͨ�紨��', '320621', '������ͨ������',
				'320623', '������ͨ�綫��', '320682', '������ͨ�����', '320683', '������ͨͨ����',
				'320611', '������ͨ��բ��', '320681', '������ͨ������', '320684', '����ʡ������' ]);
a_t.add('0_31', [ '220300', '����ʡ��ƽ��', '220400', '����ʡ��Դ��', '220500', '����ʡͨ����',
		'220600', '����ʡ��ɽ��', '220800', '����ʡ�׳���', '222400', '����ʡ�ӱ߳�����������',
		'220100', '����ʡ������', '220200', '����ʡ������', '220700', '����ʡ��ԭ��' ]);
a_t.add('0_31_0', [ '220301', '������ƽ��Ͻ��', '220302', '������ƽ������', '220303',
		'������ƽ������', '220322', '������ƽ������', '220323', '������ƽ��ͨ����������', '220381',
		'������ƽ��������', '220382', '����ʡ˫����' ]);
a_t.add('0_31_1', [ '220401', '������Դ��Ͻ��', '220402', '������Դ��ɽ��', '220403',
		'������Դ������', '220421', '������Դ������', '220422', '������Դ������' ]);
a_t.add('0_31_2', [ '220501', '����ͨ����Ͻ��', '220502', '����ͨ��������', '220503',
		'����ͨ����������', '220521', '����ͨ����', '220523', '����ͨ��������', '220524',
		'����ͨ��������', '220582', '����ͨ��������', '220581', '����ͨ��÷�ӿ���' ]);
a_t.add('0_31_3', [ '220602', '���ְ�ɽ�˵�����', '220621', '���ְ�ɽ������', '220623',
		'���ְ�ɽ���׳�����������', '220625', '����ʡ��ɽ�н�Դ��', '220681', '���ְ�ɽ�ٽ���', '220601',
		'���ְ�ɽ��Ͻ��', '220622', '���ְ�ɽ������' ]);
a_t.add('0_31_4', [ '220801', '���ְ׳���Ͻ��', '220821', '���ְ׳�������', '220822',
		'���ְ׳�ͨ����', '220882', '���ְ׳Ǵ���', '220802', '���ְ׳�䬱���', '220881',
		'���ְ׳������' ]);
a_t.add('0_31_5', [ '222402', '�����ӱ�ͼ����', '222403', '�����ӱ߶ػ���', '222405',
		'�����ӱ�������', '222406', '�����ӱߺ�����', '222426', '�����ӱ߰�ͼ��', '222424',
		'�����ӱ�������', '222401', '�����ӱ��Ӽ���', '222404', '�����ӱ�������' ]);
a_t.add('0_31_6', [ '220101', '���ֳ�����Ͻ��', '220103', '���ֳ��������', '220104',
		'���ֳ���������', '220106', '����ʡ��������԰��', '220112', '����ʡ������˫����', '220181',
		'���ֳ�����̨��', '220182', '���ֳ���������', '220102', '���ֳ����Ϲ���', '220105',
		'���ֳ���������', '220122', '���ֳ���ũ����', '220183', '����ʡ�»���' ]);
a_t.add('0_31_7', [ '220203', '������̶��', '220204', '���ִ�Ӫ��', '220211', '���ַ�����',
		'220221', '����������', '220281', '�����Ժ���', '220282', '���������', '220283',
		'����������', '220284', '����ʡ��ʯ��', '220201', '������Ͻ��', '220202', '���ֲ�����' ]);
a_t.add('0_31_8', [ '220701', '������ԭ��Ͻ��', '220721', '������ԭǰ������˹�ɹ���������', '220722',
		'������ԭ������', '220723', '������ԭǬ����', '220702', '������ԭ������', '220724',
		'����ʡ��ԭ�з�����' ]);
a_t.add('0_32', [ '653100', '�½�ά�����������ʲ����', '653200', '�½�ά����������������', '654000',
		'�½�ά������������������������', '654200', '�½�ά������������ǵ���', '654300',
		'�½�ά�������������̩����', '659000', '�½�ά���������ʡֱϽ������λ', '650100',
		'�½�ά�����������³ľ����', '650200', '�½�ά�������������������', '652100', '�½�ά�����������³������',
		'652200', '�½�ά������������ܵ���', '652300', '�½�ά�����������������������', '652700',
		'�½�ά������������������ɹ�������', '652800', '�½�ά������������������ɹ�������', '652900',
		'�½�ά��������������յ���', '653000', '�½�ά����������������տ¶�����������' ]);
a_t.add('0_32_0', [ '653122', '�½���ʲ������', '653123', '�½���ʲӢ��ɳ��', '653124',
		'�½���ʲ������', '653125', '�½���ʲɯ����', '653126', '�½���ʲҶ����', '653127',
		'�½���ʲ�������', '653128', '�½���ʲ���պ���', '653129', '�½���ʲ٤ʦ��', '653130',
		'�½���ʲ�ͳ���', '653131', '��ʲ�����������������', '653101', '�½���ʲ��', '653121',
		'�½���ʲ�踽��' ]);
a_t.add('0_32_1', [ '653201', '�½�������', '653221', '�½�������', '653222', '�½�����ī����',
		'653223', '�½�����Ƥɽ��', '653224', '�½�����������', '653225', '�½����������',
		'653226', '�½�����������', '653227', '�½����������' ]);
a_t.add('0_32_2', [ '654002', '�½�ά���������������', '654003', '�½�ά���������������', '654021',
		'�½�ά���������������������', '654022', '�½�ά��������������в첼�������������', '654023',
		'�½�ά��������������л�����', '654024', '�½�ά��������������й�����', '654025',
		'�½�ά�����������������Դ��', '654026', '�½�ά���������������������', '654027',
		'�½�ά����������������ؿ�˹��', '654028', '�½�ά������������������տ���' ]);
a_t.add('0_32_3', [ '654226', '���ǺͲ��������ɹ�������', '654201', '�½�������', '654202',
		'�½�ά���������������', '654221', '�½����Ƕ�����', '654223', '�½�����ɳ����', '654224',
		'�½�����������', '654225', '�½�����ԣ����' ]);
a_t.add('0_32_4', [ '654322', '�½�����̩������', '654325', '�½�����̩�����', '654301',
		'�½�����̩����̩��', '654321', '�½�����̩��������', '654323', '�½�����̩������', '654324',
		'�½�����̩���ͺ���', '654326', '�½�����̩��ľ����' ]);
a_t.add('0_32_5', [ '659002', '�½�ά�����������������', '659001', '�½�ʯ������', '659003',
		'�½�ά���������ͼľ�����', '659004', '�½�ά����������������' ]);
a_t.add('0_32_6', [ '650103', '�½���³ľ��ɳ���Ϳ���', '650107', '�½���³ľ��������', '650101',
		'�½���³ľ����Ͻ��', '650102', '�½���³ľ����ɽ��', '650104', '�½���³ľ��������', '650105',
		'�½���³ľ��ˮĥ����', '650106', '�½���³ľ��ͷ�ͺ���', '650108', '�½���³ľ�붫ɽ��', '650121',
		'�½���³ľ����³ľ����' ]);
a_t.add('0_32_7', [ '650201', '�½�����������Ͻ��', '650205', '�½����������ڶ�����', '650202',
		'�½�����������ɽ����', '650203', '�½�������������������', '650204', '�½����������׼�̲��' ]);
a_t.add('0_32_8', [ '652123', '�½���³���п�ѷ��', '652101', '�½���³����³����', '652122',
		'�½���³��۷����' ]);
a_t.add('0_32_9', [ '652222', '���ܰ�����������������', '652201', '�½�������', '652223',
		'�½�����������' ]);
a_t.add('0_32_10', [ '652302', '�½�����������', '652324', '�½���������˹��', '652301',
		'�½�������', '652303', '�½�ά�����������Ȫ��', '652323', '�½�������ͼ����', '652325',
		'�½�������̨��', '652327', '�½�������ľ������', '652328', '�½�����ľ�ݹ�����������' ]);
a_t.add('0_32_11', [ '652701', '�½���������������', '652722', '�½���������������', '652723',
		'�½�����������Ȫ��' ]);
a_t.add('0_32_12', [ '652801', '�½���������������', '652824', '�½�����������Ǽ��', '652828',
		'�½����������˶��', '652822', '�½�����������̨��', '652823', '�½���������ξ����', '652825',
		'�½�����������ĩ��', '652826', '�����������Ȼ���������', '652827', '�½���������;���', '652829',
		'�½��������㲩����' ]);
a_t.add('0_32_13', [ '652925', '�½��������º���', '652928', '�½������հ�������', '652922',
		'�½�������������', '652901', '�½������հ�������', '652923', '�½������տ⳵��', '652924',
		'�½�������ɳ����', '652926', '�½������հݳ���', '652927', '�½���������ʲ��', '652929',
		'�½������տ�ƺ��' ]);
a_t.add('0_32_14', [ '653022', '�½���������', '653001', '�½���ͼʲ��', '653023',
		'�½���������', '653024', '�½���ǡ��' ]);
a_t.add('0_33', [ '445200', '�㶫ʡ������', '445300', '�㶫ʡ�Ƹ���', '440900', '�㶫ʡï����',
		'441300', '�㶫ʡ������', '441600', '�㶫ʡ��Դ��', '442000', '�㶫ʡ��ɽ��', '440300',
		'�㶫ʡ������', '440400', '�㶫ʡ�麣��', '440500', '�㶫ʡ��ͷ��', '440600', '�㶫ʡ��ɽ��',
		'440700', '�㶫ʡ������', '440800', '�㶫ʡտ����', '441200', '�㶫ʡ������', '441400',
		'�㶫ʡ÷����', '441500', '�㶫ʡ��β��', '441700', '�㶫ʡ������', '441800', '�㶫ʡ��Զ��',
		'441900', '�㶫ʡ��ݸ��', '445100', '�㶫ʡ������', '440100', '�㶫ʡ������', '440200',
		'�㶫ʡ�ع���' ]);
a_t.add('0_33_0', [ '445201', '�㶫������Ͻ��', '445202', '�㶫�����ų���', '445221',
		'�㶫�����Ҷ���', '445222', '�㶫����������', '445224', '�㶫����������', '445281',
		'�㶫����������' ]);
a_t.add('0_33_1', [ '445301', '�㶫ʡ�Ƹ�����Ͻ��', '445302', '�㶫ʡ�Ƹ����Ƴ���', '445321',
		'�㶫ʡ�Ƹ���������', '445322', '�㶫ʡ�Ƹ���������', '445323', '�㶫ʡ�Ƹ����ư���', '445381',
		'�㶫ʡ�޶���' ]);
a_t.add('0_33_2', [ '440903', '�㶫ʡï����ï����', '440982', '�㶫ʡ������', '440901',
		'�㶫ï����Ͻ��', '440902', '�㶫ï��ï����', '440923', '�㶫ï�������', '440981',
		'�㶫ï��������', '440983', '�㶫ʡ������' ]);
a_t.add('0_33_3', [ '441303', '�㶫ʡ�����л�����', '441324', '�㶫����������', '441301',
		'�㶫������Ͻ��', '441302', '�㶫���ݻݳ���', '441322', '�㶫���ݲ�����', '441323',
		'�㶫���ݻݶ���' ]);
a_t.add('0_33_4', [ '441621', '�㶫��Դ�Ͻ���', '441624', '�㶫��Դ��ƽ��', '441601',
		'�㶫��Դ��Ͻ��', '441602', '�㶫��ԴԴ����', '441622', '�㶫��Դ������', '441623',
		'�㶫��Դ��ƽ��', '441625', '�㶫��Դ��Դ��' ]);
a_t.add('0_33_5', [ '442010', '�㶫ʡ��ɽ��' ]);
a_t.add('0_33_6', [ '440303', '�㶫�����޺���', '440306', '�㶫���ڱ�����', '440301',
		'�㶫������Ͻ��', '440304', '�㶫���ڸ�����', '440305', '�㶫������ɽ��', '440307',
		'�㶫����������', '440308', '�㶫ʡ������������' ]);
a_t.add('0_33_7', [ '440401', '�㶫�麣��Ͻ��', '440402', '�㶫�麣������', '440403',
		'�㶫ʡ�麣�ж�����', '440404', '�㶫ʡ�麣�н�����' ]);
a_t.add('0_33_8', [ '440501', '�㶫��ͷ��Ͻ��', '440507', '�㶫��ͷ������', '440511',
		'�㶫ʡ��ͷ�н�ƽ��', '440512', '�㶫ʡ��ͷ��婽���', '440513', '�㶫ʡ��ͷ�г�����', '440514',
		'�㶫ʡ��ͷ�г�����', '440515', '�㶫ʡ��ͷ�гκ���', '440523', '�㶫��ͷ�ϰ���' ]);
a_t.add('0_33_9', [ '440601', '�㶫��ɽ��Ͻ��', '440604', '�㶫ʡ��ɽ��������', '440605',
		'�㶫ʡ��ɽ���Ϻ���', '440606', '�㶫ʡ��ɽ��˳����', '440607', '�㶫ʡ��ɽ����ˮ��', '440608',
		'�㶫ʡ��ɽ�и�����' ]);
a_t.add('0_33_10', [ '440701', '�㶫������Ͻ��', '440703', '�㶫ʡ���������', '440704',
		'�㶫ʡ�����н�����', '440705', '�㶫ʡ�������»���', '440781', '�㶫����̨ɽ��', '440783',
		'�㶫���ſ�ƽ��', '440784', '�㶫���ź�ɽ��', '440785', '�㶫ʡ��ƽ��' ]);
a_t.add('0_33_11', [ '440802', '�㶫տ���࿲��', '440811', '�㶫տ��������', '440881',
		'�㶫տ��������', '440801', '�㶫տ����Ͻ��', '440803', '�㶫տ��ϼɽ��', '440804',
		'�㶫տ����ͷ��', '440823', '�㶫տ����Ϫ��', '440825', '�㶫տ��������', '440882',
		'�㶫ʡ������', '440883', '�㶫ʡ�⴨��' ]);
a_t.add('0_33_12', [ '441201', '�㶫������Ͻ��', '441223', '�㶫���������', '441226',
		'�㶫���������', '441202', '�㶫���������', '441203', '�㶫���춦����', '441224',
		'�㶫���컳����', '441225', '�㶫����⿪��', '441283', '�㶫�����Ҫ��', '441284',
		'�㶫�����Ļ���' ]);
a_t.add('0_33_13',
		[ '441402', '�㶫÷��÷����', '441423', '�㶫÷�ݷ�˳��', '441427', '�㶫÷�ݽ�����',
				'441401', '�㶫÷����Ͻ��', '441421', '�㶫÷��÷��', '441422', '�㶫÷�ݴ�����',
				'441424', '�㶫÷���廪��', '441426', '�㶫÷��ƽԶ��', '441481', '�㶫ʡ������' ]);
a_t.add('0_33_14', [ '441501', '�㶫��β��Ͻ��', '441521', '�㶫��β������', '441502',
		'�㶫��β����', '441523', '�㶫��β½����', '441581', '�㶫ʡ½����' ]);
a_t.add('0_33_15', [ '441701', '�㶫������Ͻ��', '441723', '�㶫����������', '441702',
		'�㶫����������', '441721', '�㶫����������', '441781', '�㶫ʡ������' ]);
a_t.add('0_33_16', [ '441801', '�㶫��Զ��Ͻ��', '441823', '�㶫��Զ��ɽ��', '441881',
		'�㶫ʡӢ����', '441802', '�㶫��Զ�����', '441821', '�㶫��Զ�����', '441825',
		'�㶫��Զ��ɽ׳������������', '441826', '�㶫��Զ��������������', '441827', '�㶫��Զ������',
		'441882', '�㶫ʡ������' ]);
a_t.add('0_33_17', [ '441910', '�㶫ʡ��ݸ��' ]);
a_t.add('0_33_18', [ '445121', '�㶫���ݳ�����', '445122', '�㶫������ƽ��', '445101',
		'�㶫������Ͻ��', '445102', '�㶫����������' ]);
a_t.add('0_33_19', [ '440101', '�㶫������Ͻ��', '440105', '�㶫���ݺ�����', '440112',
		'�㶫���ݻ�����', '440115', '�㶫ʡ��������ɳ��', '440184', '�㶫ʡ�ӻ���', '440103',
		'�㶫����������', '440104', '�㶫����Խ����', '440106', '�㶫���������', '440111',
		'�㶫���ݰ�����', '440113', '�㶫ʡ�����з�خ��', '440114', '�㶫ʡ�����л�����', '440116',
		'�㶫ʡ�������ܸ���', '440183', '�㶫����������' ]);
a_t.add('0_33_20', [ '440203', '�㶫�ع��佭��', '440222', '�㶫�ع�ʼ����', '440233',
		'�㶫�ع��·���', '440282', '�㶫ʡ������', '440281', '�㶫ʡ�ֲ���', '440201', '�㶫�ع���Ͻ��',
		'440204', '�㶫�ع�䥽���', '440205', '�㶫ʡ�ع���������', '440224', '�㶫�ع��ʻ���',
		'440229', '�㶫�ع���Դ��', '440232', '�㶫�ع���Դ����������' ]);