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
});
function accountInfo(){
	var ctx = $('#ctx').val();
	var userID=$('#userID').val();
	window.location.href = ctx +"/edit/editUserPersonal.do";

}
//��֤�û���Ϣ����
function checkerUserInfoComplte(){
	var ctx = $('#ctx').val();
	$.ajax({
		type : "post",
		url : ctx+"/email/checkerUserInfoComplete.do",
		dataType : 'json',
		success : function(data, textStatus) {
			//��Ϣ��ȫ ת����δ�����ް󶨱���ҳ��
			if(data.isInfoComplete){
				window.location.href = ctx +"/web/user/member/email/myEmail/notSubscribed/notAdd/index.jsp";
			}else{
				//��Ϣ����ȫ
				Sinosoft.alert({
					contentStr : '�𾴵Ŀͻ�����ã�',
					subContentStr : '��Ļ�����Ϣ��������������߲˵�����"<span style="color:#ff3333;margin:0 3px;cursor: pointer;" onclick="accountInfo();">�˻���Ϣ</span>"���в�ȫ',
					okStr : 'ȷ��',
					cancelStr : 'ȡ��',
					okFunc:function(){
						//���ȷ����ťִ�з���
						//�������˻���Ϣ���ƽ���
						window.location.href = ctx +"/edit/editUserPersonal.do";
					}
				});
			
			}
		},
		error : function(backData) {
			a_alert("�����쳣��");
		}
	});
}
function loadUserInfo(){
	var insuranceSelect =$('<div class="title"><p>�𾴵Ŀͷ���ã�</p></div>');
	return insuranceSelect;
}