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
	
	$('.member_menu .item').eq(0).addClass('select');
	$('.member_menu .item').eq(0).siblings().removeClass('select');
	
	Sinosoft.namespace('sinatay.newPolicy');
	
	//ֻ�е������֤���Ϊtrueʱ���ſ�����ӱ���
	if (bindPolicy == 'true') {
		$('.policy_list .load_gif').show();
		$.ajax({
			type : "POST",
			async : true,
			url : contextRootPath + "/memberCenterJson/centerFindPolicy.do",
			dataType : 'json',
			data : {},
			success : function(data) {
				$('.policy_list .load_gif').hide();
				//�����б���
				var tStr;
				var policyList = data.listPolicyList;
				for(var i = 0; i < policyList.length; i++){
					tStr = '';
					if(policyList[i].downloadString != null){
						var tStr =  '<a href="javascript:downfile(' + policyList[i].downloadString + ')">���ر���</a>&nbsp;|&nbsp;';
					}
					$('.policy_list').append('<div class="policy_item bind_policy">'
							+ '<div class="policy_row"><div class="name">�������룺</div><div class="value pNo">' + policyList[i].policySerialNumber + '</div></div>'
							+ '<div class="policy_row"><div class="name">�������ƣ�</div><div class="value">' + policyList[i].mainRiskName + '</div></div>'
							+ '<div class="policy_row" style="display: none"><div class="name">���ս�</div><div class="value">' + policyList[i].amount + '</div></div>'
							+ '<div class="policy_row"><div class="name">��Ч���ڣ�</div><div class="value">' + policyList[i].inceptionDate + '</div></div>'
							+ '<div class="policy_row last"><div class="name">����״̬��</div><div class="value">' + policyList[i].policyStatus + '</div></div>'
							+ '<div class="policy_bottom operation"><a href="javascript:toDetail(' + policyList[i].policySerialNumber + ')">����</a>&nbsp;|&nbsp;'
							+ tStr
							+ '<a href="javascript:toDetail(' + policyList[i].policySerialNumber + ')">����</a></div></div>');
				}
				$('.policy_item').click(function(){
					$(this).addClass('select');
					$(this).siblings().removeClass('select');
				});
				
				//�ɰ󶨱�������
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
});

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

function otherPolicy(policyList,otherList) {
	new Sinosoft.InteractiveDialog({
		layout : loadNewPolicy(otherList),
		okStr : 'ȷ��',
		cancelStr : 'ȡ��',
		width : 510,
		okFunc : function() {
			var bindOtherPolicy =[];
			var params = {};
			for ( var i = 0,j = 0; i < sinatay.newPolicy.checkOpts.length; i++) {
				if (sinatay.newPolicy.checkOpts[i].check()) {
					params['bindOtherPolicy[' + j + ']'] = $('.new_policy .new_policy_item').eq(i).find('.number .value').text();
					j++;
				}
			}
			var ctx = $('#ctx').val();
			$.ajax({
				type : "POST",
				url:contextRootPath+'/memberCenterJson/centerBindPolicy.do',
				data: params,
				error:function(){
					a_alert("�����쳣��");
				},  
				success:function(data){
					location.reload();
					/*$('.addPolicy .num').html(data.listOtherPolicy.length);
					$('.addPolicy').unbind('click');
					$('.addPolicy').click(function(){
						if(data.listOtherPolicy.length > 0){
							otherPolicy(data.listPolicyList,data.listOtherPolicy);
						}
					});*/
				}
			}); 
		},
		cancelFunc : function() {
		}
	}).open();

}