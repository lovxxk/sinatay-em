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
	
	$('.member_menu .item').eq(5).addClass('select');
	$('.member_menu .item').eq(5).siblings().removeClass('select');
	$('.member_menu .item .account_item').eq(1).addClass('item_select');
	$('.member_menu .item .account_item').eq(1).siblings().removeClass('item_select');
	
});