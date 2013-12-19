$(document).ready(function(){
	$('.head_mid .main_title').html('会员登录');
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
	
	var username = document.getElementById('user_name');

	setInputHint(username,'邮箱/手机/身份证');
});