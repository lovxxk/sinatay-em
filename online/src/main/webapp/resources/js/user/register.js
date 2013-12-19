$(document).ready(function(){
	//页头初始化
	$('.head_mid .main_title').html('会员注册');
	$('.main_nav').addClass('main_nav_user');
	
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
	var username = document.getElementById('userAccount');

	setInputHint(username,'手机号/邮箱');
});