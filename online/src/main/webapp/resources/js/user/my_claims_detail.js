$(document).ready(function(){
	
	$('.info_area .sub_title .fold').click(function(){
		var open_str = 'open',
			close_str = 'close';
		
		if($(this).hasClass(open_str)){
			$(this).removeClass(open_str).addClass(close_str);
			$(this).parents('.info_area').find('.fold_body').slideDown(200);
		}else if($(this).hasClass(close_str)){
			$(this).removeClass(close_str).addClass(open_str);
			$(this).parents('.info_area').find('.fold_body').slideUp(200);
		}
	});
});