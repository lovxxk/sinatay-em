$(document).ready(function(){
	
	$('.nav_menu .nav_item').eq(2).addClass('select');
	$('.nav_menu .nav_item').eq(2).siblings().removeClass('select');
	
	//�����Ч
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
	
	$('.display_nav div').mouseover(function(){
		if(!$(this).hasClass('active')){
			changeImg($(this).index());
		}
	});

	$('.service_head').mouseenter(function(){
		pasuePlay();
	}).mouseleave(function(){
		startPlay();
	});
	
	startPlay();
	
//	
//	new Sinosoft.LoginDialog({
//		layout : loadReLogin(),
//		okStr:'������¼',
//		rightLink:{
//			name:'��������',
//			link:'#',
//			unimportant:true
//		},
//		leftLink:{
//			name:'ʹ�������˺ŵ�¼',
//			link:'#'
//		},
//		okFunc:function(){
//		}
//	}).open();
	
	
//	new Sinosoft.LoginDialog({
//		layout : loadClickLogin(),
//		okStr:'������¼',
//		rightLink:{
//			name:'���ע��',
//			link:'#'
//		},
//		okFunc:function(){
//			new Sinosoft.LoginDialog({
//				layout : loadLoginForm(),
//				okStr:'������¼',
//				rightLink:{
//					name:'���ע��',
//					link:'#'
//				},
//				okFunc:function(){
//				}
//			}).open();
//		}
//	}).open();
});


function loadClickLogin(){
	var clickLogin = $('<div class="login_frame">'+ 
			'<label class="input_label">��Ա��¼</label>'+
			'<p class="alert_content notice_login">��Ҫ��¼����ܲ鿴��</p>'+
			'</div>');
	return clickLogin;
}

function loadLoginForm(){
	var clickForm = $('<div class="login_frame">'+
			'<label class="input_label">��Ա��¼<span id="message" style=""></span></label>'+
			'<input class="input_field user_name" id ="sinosoft_login_dialog_userName" type="text" name=""/>'+
			'<input class="input_field password"  id ="sinosoft_login_dialog_password" type="password" name=""/>'+
			'<div class="login_operation">'+
			'<input name="remeberMe" id="remeberMe" type="checkbox"/>'+
			'<label for="remember">��ס��</label>'+
			'<a class="forget_password" href="'+contextRootPath+'/web/user/resetPwd/index.jsp">�������룿</a>'+
			'</div>'+
			'</div>');
	
	return clickForm;
}

function loadReLogin(){
	var reLogin = $('<div class="login_frame">'+
			'<label class="input_label">��Ա��¼</label>'+
			'<label class="info_label1">��Ŀǰ�Ѿ���¼�˻���<span class="account">ihangzhou@126.com</span></label>'+
			'<label class="info_label2">Ϊ�������˻���ȫ����ȷ�����룺</label>'+
			'<input class="input_field password" type="password" name=""/>');
	
	return reLogin;
}

function sinosoft_login_dialog(url) {
	new Sinosoft.LoginDialog({
		layout : loadClickLogin(),
		okStr : '������¼',
//		rightLink : {
//			name : '���ע��',
//			link : contextRootPath+'/web/user/register/index.jsp'
//		},
		okFunc : function() {
			new Sinosoft.LoginDialog({
				layout : loadLoginForm(),
				okStr : '������¼',
				rightLink : {
					name : '���ע��',
					link : contextRootPath+'/web/user/register/index.jsp'
				},
				okFunc : function() {
					$.ajax({
						type : "POST",
						async : false,
						url : contextRootPath+"/login/serviceLogin.do",
						dataType : 'text',
						data : {
							userName : $("#sinosoft_login_dialog_userName").val(),
							password : $("#sinosoft_login_dialog_password").val()
						},
						success : function(data) {
							if (data == "success") {
								 window.location.href=url; 
							} else {
								$('.login_frame #message').text('�û������������');
							}
						}
					});
					return false;
				}
			}).open();
			//����
			_ga.push(['_trackPageview','/web/user/login']).send();
			_hm.push(['_trackPageview','/web/user/login']).send();
		}
	}).open();
}