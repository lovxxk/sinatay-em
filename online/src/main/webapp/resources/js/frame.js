$(document).ready(function(){
	
	//验证码按钮禁用
	$.fn.valiCodeDisable = function(){
		this.each(function(){
			$(this).css({
				'background':'#ccc',
				'width':'auto',
				'border-radius':'2px',
				'cursor':'default',
				'padding':'0 10px'
			});
			$(this).attr('disabled','disabled');
		});
	};

	//验证码按钮激活
	$.fn.valiCodeEnable = function(){
		this.each(function(){
			$(this).removeAttr('disabled');
			$(this).removeAttr('style');
		});
	};
	
	$('input#search').click(function(){
		$('#search_label').css("display","none");
		$('#search_icon').css("display","none");
	}).blur(function(){
		$('#search_label').css("display","inline");
		$('#search_icon').css("display","block");
	});
	
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
	
	$(".nav_menu .product_buy")
		.mouseover(showProduct)
		.mouseout(hideProduct);
	
	$(".nav_menu .product_buy_select")
		.mouseover(showProduct)
		.mouseout(hideProduct);
	
	Sinosoft.namespace('sinatay');
	
	sinatay.isProduct = false;
	
	var timeoutaccount = null;
	
	function showProduct(){
		if (timeoutaccount){
			clearTimeout(timeoutaccount);
		}
		timeoutaccount = setTimeout(function() {
			timeoutaccount = null;
			$('.nav_menu .product_buy_select').slideDown();
			if(!sinatay.isProduct){
				$('.nav_menu .product_buy').addClass('select');
			}
		}, 100);
	}
	
	function hideProduct(){
		if (timeoutaccount){
			clearTimeout(timeoutaccount);
		}
		timeoutaccount = setTimeout(function() {
			timeoutaccount = null;
			$('.nav_menu .product_buy_select').slideUp(function(){
				if(!sinatay.isProduct){
					$('.nav_menu .product_buy').removeClass('select');	
				}
			});
		}, 100);
	}
});

$(window).load(function(){
	if(isIe6()){
		var noticeIE = getCookie('noticeIE');
		
		if((noticeIE == null || noticeIE == '')){
			$('.notice').animate({height:29},'slow');
			
			$('.notice_close').click(function(){
				$('.notice').animate({height:0},'slow');
				setCookie('noticeIE',false);
			});
		}
	}
});
