$(document).ready(function(){
	$('.head_mid .main_title').html('会员中心');
	$('.member_menu .item').eq(5).addClass('select');
	$('.member_menu .item').eq(5).siblings().removeClass('select');
	$('.member_menu .item .account_item').eq(3).addClass('item_select');
	$('.member_menu .item .account_item').eq(3).siblings().removeClass('item_select');
});
