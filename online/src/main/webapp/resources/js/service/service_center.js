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
	
	$('.customer_tab .tab_item').click(function(){
		$(this).addClass('select');
		$(this).siblings().removeClass('select');
		
		var index = $(this).index('.tab_item');
		
		$('.customer_main .service_body').hide();
		$('.customer_main .service_body').eq(index).show();
	});
	
	
});