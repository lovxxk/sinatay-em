$(document).ready(function(){
	$('.head_mid .main_title').html('会员中心');
	
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
	
	$('.member_main .left_menu').css('height',$('.member_main .right_content').height());
	
	$('.member_menu li').each(function(){
		cleanWhitespace(this);
	});
});