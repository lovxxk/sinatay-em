$(document).ready(function(){
	
	$('.nav_menu .nav_item').eq(2).addClass('select');
	$('.nav_menu .nav_item').eq(2).siblings().removeClass('select');
	
	//µã»÷ÌØÐ§
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
	
	
	$('.problem_cate .cate').click(function(){
		$(this).addClass('select');
		$(this).siblings().removeClass('select');
		var index = $(this).index();
		$('.problem_main .problem_list').eq(index).show();
		$('.problem_main .problem_list').eq(index).siblings().hide();
		$('.problem_main .problem_list').eq(index).find('.problem').removeClass('open');
	});
	
	//$('.problem_cate .cate').eq(0).click();
	
	$('.problem').click(function(){
		if($(this).hasClass('open')){
			$(this).removeClass('open');
		}else{
			$(this).addClass('open');
		}
	});
});