$(document).ready(function(){
	$('.quote_tab .tab_item').click(function(){
		$(this).addClass('select');
		$(this).siblings().removeClass('select');
		
		var index = $(this).index('.tab_item');
		
		$('.quote_body').hide();
		$('.quote_body').eq(index).show();
	});
	
	var timeoutaccount = null,
		autoPlaySeed = null,
		play = false,
		play_index = 0;
	
	function startPlay(){
		if(!play){
			autoPlaySeed = setInterval(function(){
				var i = play_index;
				
				if(++i >= $('.product_display a').length){
					i = 0;
				}
				changeImg(i);
			}, 5000);
		}
	}
	
	function pasuePlay(){
		if(!play){
			clearInterval(autoPlaySeed);
		}
	}
	
	function changeImg(index){

		play_index = index;
		
		if (timeoutaccount){
			clearTimeout(timeoutaccount);
		}
		timeoutaccount = setTimeout(function() {
			timeoutaccount = null;
			var block = $('.display_nav div').eq(index),
				img = $('.product_display a').eq(index);
			block.addClass('active');
			block.siblings().removeClass('active');
			
			img.css('z-index','2');
			img.siblings('a').css('z-index','1');
			fade = true;
			img.fadeIn(400,function(){
				img.siblings('a').hide();
			});
		}, 300);
	}
	
	$('.display_nav div').mouseover(function(){
		if(!$(this).hasClass('active')){
			changeImg($(this).index());
		}
	});
	
	$('.product_display').mouseenter(function(){
		pasuePlay();
	}).mouseleave(function(){
		startPlay();
	});
	
	startPlay();
	
	$('.quote_body .input_field ul li').click(function(){
		if(!$(this).hasClass('select')){
			var index = $(this).index();
			$(this).addClass('select');
			if($(this).attr("tag") == "type"){
				$("#type").val(index);
			}else if($(this).attr("tag") == "day"){
				$("#day").val(index);
			}
			$(this).siblings().removeClass('select');
		}
	});
	
	$('.nav_menu .nav_item').eq(1).addClass('select');
	$('.nav_menu .nav_item').eq(1).siblings().removeClass('select');
	
	var page_index = 0;
	
	$('.page.page_num').click(function(){
		if(!$(this).hasClass('now')){
			
			var index = $(this).index('.page.page_num');
			
			$(this).addClass('now');
			
			$(this).siblings('.page.page_num').removeClass('now');
			
			$('.product_list_main.page' + (index+1)).show();
			$('.product_list_main.page' + (page_index+1)).hide();
			
			page_index = index;
			
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	});
	
	$('.page.prev_page').click(function(){
		if(!$('.page.page_num').eq(0).hasClass('now')){
			$('.page.page_num').eq(0).addClass('now');
			$('.page.page_num').eq(1).removeClass('now');
			$('.product_list_main.page' + (page_index+1)).hide();
			page_index = 0;
			$('.product_list_main.page' + (page_index+1)).show();

			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	});
	
	$('.page.next_page').click(function(){
		if(!$('.page.page_num').eq(1).hasClass('now')){
			$('.page.page_num').eq(1).addClass('now');
			$('.page.page_num').eq(0).removeClass('now');
			$('.product_list_main.page' + (page_index+1)).hide();
			page_index = 1;
			$('.product_list_main.page' + (page_index+1)).show();
			
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	});
	
	sinatay.isProduct = true;
});