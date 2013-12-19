$(document).ready(function(){
	//µã»÷ÌØÐ§
	$('.click_btn').on('mousedown',function(){
		$(this).css({
			'top':'1px',
			'left':'1px'
		});
	}).on('mouseup',function(){
		$(this).css({
			'top':'0',
			'left':'0'
		});
	});
	
	$('.to_edit').click(function(){
		$(this).hide();
		$(this).prev().show();
		
		var parent = $(this).parents('.info_area ');
		var input = parent.find('.edit_input');
		
		input.show();
		
		input.each(function(){
			$(this).val($(this).siblings().text());
		});
		input.siblings().hide();
	});
	
	$('.save_edit').click(function(){
		$(this).hide();
		$(this).next().show();
		
		var parent = $(this).parents('.info_area ');
		var text = parent.find('.text_show');
		
		text.show();
		
		text.each(function(){
			$(this).text($(this).siblings().val());
		});
		text.siblings().hide();
	});
	$('#effect_start,#effect_end').click(function(){
		WdatePicker({
			startDate:'%y-%M-%d',
			dateFmt:'yyyy/MM/dd'
		});
	});
});
