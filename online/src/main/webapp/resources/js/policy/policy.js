
$(document).ready(function(){
	
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
	
	$('.nav_menu .nav_item').eq(1).addClass('select');
	$('.nav_menu .nav_item').eq(1).siblings().removeClass('select');
	
	sinatay.isProduct = true;
	
	$('.input_row .input_sex').click(function(){
		if(!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).siblings().removeClass('selected');
			$(this).parent().find("input[type='hidden']").attr('value',$(this).attr('val'));
			autoSave();
			checkIdCard($(this).attr("prefix"),'',$(this).attr("prefixTag"));
		}
	});
	
//	$('.input_row .label_sex').click(function(){
//		var input = $(this).prev();
//		if(!input.hasClass('selected')){
//			input.addClass('selected');
//			input.siblings().removeClass('selected');
//			$(this).parent().find("input[type='hidden']").attr('value',$(this).attr('val'));
//			autoSave();
//		}
//	});
	
	$(".beneficiary_info .input_beneficiary").each(function(i){
		if($(this).attr('id') == "legal" && $(this).hasClass('selected')){
			$('#add_beneficiary').hide();
			$(".beneficiary_info").find("div[tag='inputBnf']").each(function(i){
				$('#beneficiary_info'+i).hide();
			});
		}else if($(this).attr('id') == "assign" && $(this).hasClass('selected')){
			$('#add_beneficiary').show();
			$(".beneficiary_info").find("div[tag='inputBnf']").each(function(i){
				$('#beneficiary_info'+i).show();
			});
		}
	});
	
	$('.input_row .input_beneficiary').click(function(){
		if(!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).siblings().removeClass('selected');
			$(this).parent().find("input[type='hidden']").attr('value',$(this).attr('val'));
			if($(this).attr('id') == "legal"){
				$('#add_beneficiary').hide();
				$(".beneficiary_info").find("div[tag='inputBnf']").each(function(i){
					$(this).remove();
				});
			}else if($(this).attr('id') == "assign"){
				$('#add_beneficiary').show();
				add_beneficiary();
			}
		}
	});
	
//	$('.input_row .label_beneficiary').click(function(){
//		var input = $(this).prev();
//		if(!input.hasClass('selected')){
//			input.addClass('selected');
//			input.siblings().removeClass('selected');
//			$(this).parent().find("input[type='hidden']").attr('value',$(this).attr('val'));
//			if(input.attr('id') == "legal"){
//				$('.beneficiary_info .input_area').hide();
//			}else if(input.attr('id') == "assign"){
//				$('.beneficiary_info .input_area').show();
//			}
//		}
//	});
	
	$('.click,.click_box').click(function(){
		if($('.click_box').text() == "+"){
			$('.click').text('点击收起');
			$('.click_box').text('-');
			$('.click_box').css('text-indent','3px');
			$('.insured_info .input_area.insured').show();
		}else{
			$('.click').text('点击展开');
			$('.click_box').text('+');
			$('.click_box').css('text-indent','0');
			$('.insured_info .input_area.insured').hide();
		}
	});
	
	$('#effect_start').click(function(){
		initEffectStartDate();
	});
	
	$('.add_beneficiary div.click_btn').click(function(){
		add_beneficiary();
	});
	
	$('.beneficiary_info .input_area .delete').live('click',function(){
		var minBeneficiaryNum = $("#minBeneficiaryNum").val();
		var index = $(".beneficiary_info").find("div[tag='inputBnf']").length;
		if(index > minBeneficiaryNum){
			$(this).parents('.input_area').remove();
		}
	});
});

//添加受益人元素
function add_beneficiary(){
	var maxBeneficiaryNum = $("#maxBeneficiaryNum").val();
	re = new RegExp("#index", "g");
	var availableIndexArr = [0,1,2];
	var $inputBnf = $(".beneficiary_info").find("div[tag='inputBnf']");
	$inputBnf.each(function(){
		var c_index = /\d/.exec($(this).attr("id"));
		availableIndexArr.splice(parseInt(c_index),1,-1);
	});
	var avaliableIndex;
	for(var i in availableIndexArr){
		if(availableIndexArr[i] != -1){
			avaliableIndex = availableIndexArr[i];
			break;
		}
	}
	var index = $inputBnf.length;
	if(index < maxBeneficiaryNum){
		$(".beneficiary_info").append(addDemoIndex("inputBnfDemo","bnfIndex").replace(re,avaliableIndex));
		initBnfData(avaliableIndex);
	}
}

function updateDaySelector(year,month,day){
	var year_str = year.data('jSelect').getValue();
	var month_str = month.data('jSelect').getValue();
	if(year_str && month_str){
		var date = DayNumOfMonth(year_str,month_str);
		var days = [];
		for(var i=0 ; i< date ; i++){
			var dayStr = i+1;
			if(dayStr < 10){
				dayStr = "0"+dayStr;
			}
			days.push({
				name:dayStr,
				value:dayStr
			});
		}
		day.data('jSelect').update(days);
	}
}