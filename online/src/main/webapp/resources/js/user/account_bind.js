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
	
});