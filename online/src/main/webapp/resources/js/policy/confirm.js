$(document).ready(function(){
	
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
	
	$('.nav_menu .nav_item').eq(1).addClass('select');
	$('.nav_menu .nav_item').eq(1).siblings().removeClass('select');
	$('.nav_menu .nav_item').eq(1).prev().addClass('m_select_p');
	
	
	$('.declaration .agree').jCheckBox({
		label:'agree',
		onCheckChanged:function(label,check){
			if(check){
				$('#legalNotice').attr('value',1);
			}else{
				$('#legalNotice').attr('value',0);
			}
//			console.log($('#legalNotice').val());
		}
	});
});