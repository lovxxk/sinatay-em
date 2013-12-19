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
});
function accountInfo(){
	var ctx = $('#ctx').val();
	var userID=$('#userID').val();
	window.location.href = ctx +"/edit/editUserPersonal.do";

}
//验证用户信息完善
function checkerUserInfoComplte(){
	var ctx = $('#ctx').val();
	$.ajax({
		type : "post",
		url : ctx+"/email/checkerUserInfoComplete.do",
		dataType : 'json',
		success : function(data, textStatus) {
			//信息完全 转发到未订阅无绑定保单页面
			if(data.isInfoComplete){
				window.location.href = ctx +"/web/user/member/email/myEmail/notSubscribed/notAdd/index.jsp";
			}else{
				//信息不完全
				Sinosoft.alert({
					contentStr : '尊敬的客户，你好！',
					subContentStr : '你的基本信息不完整，请点击左边菜单栏中"<span style="color:#ff3333;margin:0 3px;cursor: pointer;" onclick="accountInfo();">账户信息</span>"进行补全',
					okStr : '确定',
					cancelStr : '取消',
					okFunc:function(){
						//点击确定按钮执行方法
						//弹出到账户信息完善界面
						window.location.href = ctx +"/edit/editUserPersonal.do";
					}
				});
			
			}
		},
		error : function(backData) {
			a_alert("网络异常！");
		}
	});
}
function loadUserInfo(){
	var insuranceSelect =$('<div class="title"><p>尊敬的客服你好！</p></div>');
	return insuranceSelect;
}