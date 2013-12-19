$(document).ready(function(){
	$('.detail_tab li').click(function(){
		if(!$(this).hasClass('select')){
			var index = $(this).index();
			$(this).addClass('select');
			$(this).siblings().removeClass('select');
			$('.detail_container .detail_main').eq(index).show();
			$('.detail_container .detail_main').eq(index).siblings().hide();
		}
	});
	
	$('.nav_menu .nav_item').eq(1).addClass('select');
	$('.nav_menu .nav_item').eq(1).siblings().removeClass('select');

	sinatay.isProduct = true;
});
