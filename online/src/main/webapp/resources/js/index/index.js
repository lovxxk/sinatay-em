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
	
	var username = document.getElementById('user_name'),
		password = document.getElementById('password');
	
	if(username && password){
		setInputHint(username,'请输入您的邮箱/手机/身份证');
//		setInputHint(password,'请输入密码','password');
	}
	
	/*$('.online_service').css('left',$('.index_area').position().left+1025);*/
	
	$('.age_tab_main li.active').mouseover(function(){
		if(!$(this).hasClass('select')){
			var index = $(this).index();
			$(this).addClass('select');
			$(this).siblings().removeClass('select');
			
			$('.age_tab_container .content').eq(index).show();
			$('.age_tab_container .content').eq(index).siblings().hide();
		}
	});
	
	$('.product_list .product').mouseover(function(){
		$(this).find('.product_info').fadeIn(200);
	}).mouseleave(function(){
		$(this).find('.product_info').fadeOut(200);
	});
	
	var timeoutaccount = null,
		autoPlaySeed = null,
		play = false,
		play_index = 0;
	
	function startPlay(){
		if(!play){
			autoPlaySeed = setInterval(function(){
				var i = play_index;
				
				if(++i >= $('.head_action .action_layout').length){
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
				img = $('.head_action .action_layout').eq(index);
			
			block.addClass('active');
			block.siblings().removeClass('active');
			
			img.css('z-index','2');
			img.siblings().css('z-index','1');
			img.fadeIn(400,function(){
				img.siblings().hide();
			});
		}, 400);
	}
	
	$('.index_head_layout').mouseenter(function(){
		pasuePlay();
	}).mouseleave(function(){
		startPlay();
	});
	
	$('.display_nav div').mouseover(function(){
		if(!$(this).hasClass('active')){
			changeImg($(this).index());
		}
	});
	
	startPlay();
});