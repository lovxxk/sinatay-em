$(document).ready(function(){
	$('.head_mid .main_title').html('��Ա��¼');
	$('.main_nav').addClass('main_nav_user');

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
	
	var username = document.getElementById('user_name');

	setInputHint(username,'����/�ֻ�/���֤');
});