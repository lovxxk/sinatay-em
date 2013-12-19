<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
	$(function(){
		$(".userAccount").emailpop();
	});
	var recommobilereg = /^(1[3|5|8|4][0-9]\d{8})$/;
	var timenum = 120;
	var intervalProcess;
	$(document).ready(function(){
		$('.refresh_captcha').on("click",function(){
			$('#captchaImg').hide().attr('src','<s:url value="/jcaptcha.jpg"/>' + '?' + Math.floor(Math.random() * 100)).fadeIn();
		});
		 $("dt").toggle(
			  function () {
				  $('dd').hide();
				  $(this).next().show();
			  },
			  function () {
				  $('dd').hide();
				  $(this).next().hide();
			  }
		 );
		$('.userAccount').blur(function(){
			var customerName = $('.userAccount').val();
			var regmobile =  /^(1[3|5|8|4][0-9]\d{8})$/.test(customerName);
			var regemail = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(customerName);
			$(".hiddenUserAccount").attr("value", customerName);
			if(regmobile){
				$('#entImg_div').hide();
				$('#phoneValidateDiv').show();
				$('.registeredName').text("");
				$("#customerNameValidate").attr("value","true");
				//校验手机号码是否已注册
				//true，已注册;false，未注册
				$.ajax({
					type : "POST",
					url : contextRootPath+"/register/existCustomerByUserAccount.do",
					dataType : "json",
					data:{
						value : customerName
					},
					success : function(data) {
						if (data.count != '' && data.count > 0) {
							$('#phoneValidateDiv').hide();
							$('.registeredName').text("该手机号已被使用");
							$("#customerNameValidate").attr("value","false");
							return false;
						}else{
							$('.registeredName').text("");
							$("#customerNameValidate").attr("value","true");
							$('#phoneValidateDiv').show();
							phoneFlag = true;
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
					}
				});
				
			}else if(regemail){
				$('#entImg_div').show();
				$('#phoneValidateDiv').hide();
				$('.registeredName').text("");
				$("#customerNameValidate").attr("value","true");
				$.ajax({
					type : "POST",
					url : contextRootPath+"/register/existCustomerByUserAccount.do",
					dataType : 'json',
					data:{
						value : customerName
					},
					success : function(data) {
						if (data.count != '' && data.count > 0) {
							$('#phoneValidateDiv').hide();
							$('.registeredName').text("该邮箱地址已被使用");
							$("#customerNameValidate").attr("value","false");
							return false;
						}else{
							$('.registeredName').text("");
							$("#customerNameValidate").attr("value","true");
							emailFlag = true;
						}
					}
				});
			
			}else{
				$('#entImg_div').show();
				$('#phoneValidateDiv').hide();
				
				if(customerName.indexOf('@') > 0){
					$('.registeredName').text("请输入正确的邮箱！");
				}else{
					$('.registeredName').text("请输入正确的手机号或邮箱！");
				}
			}
		});
		$('.password').blur(function() {
			var regpassword = /^\w{6,17}$/.test($('.password').val());
			if (!regpassword ) {
				$('.validPassword').text("密码必须是6到17位的字母或数字");
				return false;
			} else {
				$('.validPassword').text("");
			}
		});
		$('.password2').blur(function() {
			if ($('.password2').val() != $('.password').val()) {
				$('.validPassword2').text("两次密码不一致");
				return false;
			} else {
				$('.validPassword2').text("");
			}
		});
		$('.phonePwd').focus(function(){
			$('.validphonePwd').text("");
		});
		if ($('.userAccount').val() != "") {
			var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test($('.userAccount').val());
			if (regmobile) {
				$('#phoneValidateDiv').show();
			}
		}
	});

		
	function getpwd() {
		var customerName = $('.userAccount').val();
		var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(customerName); 
		if (!regmobile) {
			Sinosoft.alert({
				contentStr: "手机号码格式不正确",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
				}
			});
			return false;
		}
		$('.getPhonePwd').valiCodeDisable();
		$('.userAccount').attr('disabled','disabled');
		$.ajax({
			url : '${ctx}/register/getUserPersonalPhonePwd.do',
			type : 'POST',
			async : false,
			data : "phoneNum=" + customerName,
			dataType : "text",
			success : function(data){
				if (data == "success") {
					var mobileStr = '';
					if(customerName != '' && customerName.length == 11){
						mobileStr = customerName.substring(0,3)+"****"+customerName.substring(7,customerName.length);
					}
					doReceive("手机动态密码已发送到" + mobileStr);
					timenum = 120;
					intervalProcess = setInterval('counttime()',1000);
				} else if(data =="limit") {
					Sinosoft.alert({
						contentStr: "非常抱歉，今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。",
						width:480,
						okStr: '确定',
						cancelBtnShow:false,//是否显示关闭按钮
						okFunc:function(){
							
						}
					});
					$('.getPhonePwd').valiCodeEnable();
					$('.userAccount').attr('disabled',false);
				} else {
					Sinosoft.alert({
						contentStr: "发送失败",
						width:480,
						okStr: '确定',
						cancelBtnShow:false,//是否显示关闭按钮
						okFunc:function(){
							
						}
					});
					$('.getPhonePwd').valiCodeEnable();
					$('.userAccount').attr('disabled',false);
				}
			}
		});
	}
	function counttime() {
		if (timenum > 0) {
			$('.getPhonePwd').val('('+timenum+')秒后重新发送');
			timenum = timenum - 1;
		} else {
			$('.getPhonePwd').val('点击再次发送');
			$('.getPhonePwd').valiCodeEnable();
			$('.userAccount').attr('disabled',false);
			clearInterval(intervalProcess);
		}
	}
	function changeImage(obj){
		$("#"+obj).attr("src","${ctx}/web/user/register/image.jsp?id="+Math.random(100)*10);
	}
	
 	/**ajax验证验证码**/
	function checkImage() {
	 	var dataFlag = false;
		var inputCode = $("#erand").val();
		$.ajax({
			type : "POST",
			async : false,
			url : "${ctx}/register/checkAjaxAction.do",
			dataType : 'text',
			data : {checkCode : inputCode },
			success: function(data) {
				if (data == "success") {
					dataFlag = true;
				} else {
					dataFlag = false;
				}
			}
		});
		return dataFlag;
	}

	function validateForm() {
		var regpassword = /^\w{6,17}$/.test($('.password').val());
		var regemail =  /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test($('#userAccount').val());
		var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test($('#userAccount').val()); 
		
		var customerName = $('.userAccount').val();
		/* if (customerName == "") {
			 Sinosoft.alert({
				contentStr: "请输入用户名",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			}); 
			$('.registeredName').text("请输入用户名!");
			return false;
		} */
		if (!regmobile && !regemail) {
			 Sinosoft.alert({
					contentStr: "请输入正确的手机号或邮箱！",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
						
					}
				}); 
			return false;
		}

		if ($('.customerNameValidate').val() == "false") {
			Sinosoft.alert({
				contentStr: $(".registeredName").text(),
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			return false;
		}

		if(!regpassword ) {
			$('.validPassword').text("密码必须是6到17位的字母或数字");
			Sinosoft.alert({
				contentStr: "请正确填写注册信息（密码必须是6到17位的字母或数字）！",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			$('.password').focus();
			return false;
		}
		if($('.password2').val() != $('.password').val()){
			$('.validPassword2').text("请正确填写注册信息（两次密码不一致）！");
			Sinosoft.alert({
				contentStr: "请正确填写注册信息（两次密码不一致）！",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			$('.password2').focus();
			return false;
		}
		
		var inputCode = $("#erand").val();
		if(regemail){
			if (inputCode == "") {
				Sinosoft.alert({
					contentStr: "请输入验证码",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
						
					}
				});
				return false;
			}
			if (!checkImage()) {
				Sinosoft.alert({
					contentStr: "验证码输入错误",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
						
					}
				});
				myReload();
				$("#erand").val("");
				return false;
			}
		}

		//同意信泰注册协议
		if(!navService()){
			return false;
		}
		//只有当用户的注册方式是手机号时才需要验证手机动态密码
		if (regmobile) {
			var phonePwd = $('.phoneValidateCode').val();
			if (phonePwd == "") {
				Sinosoft.alert({
					contentStr: "请输入验证码",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
						
					}
				});
				return false;
			}

			var code = "";
			$.ajax({
				type : "POST",
				async : false,
				url : "${ctx }/register/registerCheckPwd.do",
				dataType : "text",
				data:{
					"phonePwd" : phonePwd,
					"customerName" : customerName
				},
				success : function(data) {
					code = data;
				}
			});
			if (code == "error") {
				Sinosoft.alert({
					contentStr: "手机验证码输入错误",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
						
					}
				});
				return false;
			} else if (code == "invalid") {
				Sinosoft.alert({
					contentStr: "验证码已失效，请重新获取验证码",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
						
					}
				});
				return false;
			}
		}
		return true;
	}
	
	function navService() {
		var id_checkbox = document.getElementById("agreeprocl");
		if (id_checkbox.checked) {
			return true;
		} else {
			id_checkbox.focus();
			Sinosoft.alert({
				contentStr: "请确认页面下方声明内容！",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			return false;
		}
	}
	function toLogin() {
		window.location.href="${ctx }/web/user/login/index.jsp";
	}
	function myReload(){
         $("#entImg")[0].src=$("#entImg")[0].src + "?nocache="+new Date().getTime();
    }
	
	function loadReceiveInput(parameter) {
		var success = $('<div class="receive_input">'+parameter+'</div>');
		return success;
	}


	//领取按钮
	function doReceive(parameter){
		var receive_input = loadReceiveInput(parameter);
		Sinosoft.alert({
			contentStr: parameter,
			subContentStr:'请注意查收',
			width:480,
			okStr: '确定',
			cancelStr: '取消',
			cancelBtnShow:false
		});

	}
	
</script>
		<div class="middle">
			<div class="h_layout">
				<div class="register_main">
					<p class="reg_title">免费注册</p>
					<div class="main_frame">
						<div class="reg_frame">
							<form id="register" class="register" action="${ctx}/register/saveUserPersonal.do" method="post">
								<input type="hidden" id="registerType"/>
								<div class="reg_form">
									<div class="input_field">
										<label><span class="required">*</span><span class="name">用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名：</span></label>
										<input type="text" name="cuserAccount" id="userAccount" class="userAccount" maxlength="40"/>
										<input type="hidden" name="customer.userAccount" class="hiddenUserAccount">
										<span class="errorMessag registeredName"></span>
										<input type="hidden" id="customerNameValidate" class="customerNameValidate"/>
										<input type="hidden" id="customerNameExist" class="customerNameExist"/>
									</div>
									<div class="input_field">
										<label><span class="required">*</span><span class="name">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</span></label>
										<input name="customer.pwd" type="password" id="customer.password" class="password"  style="_width:148px;" maxlength="16"/>
										<span class="errorMessag validPassword"></span>
									</div>
									<div class="input_field">
										<label><span class="required">*</span><span class="name">确&nbsp;认&nbsp;密&nbsp;码：</span></label>
										<input name="password2" type="password" id="password2"  class="password2"  style="_width:148px;" maxlength="16"/>
										<span class="errorMessag validPassword2"></span>
									</div>
									<div class="input_field" id="entImg_div">
										<label><span class="required">*</span><span class="name">验&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;码：</span></label>
										<input type="text" id="erand" name="erand" maxlength="4" class="input6-yz" />
										<div class="vali_img">
											<img style="cursor: pointer;" id="entImg" name="entImg"
												src="${ctx }/PictureCheckCode.jpeg" alt="验证码" title="点击刷新验证码"
												onclick="myReload()" height="18" align="middle"/>
										</div>
									</div>
									<div class="input_field" id="phoneValidateDiv" style="display: none;">
										<label><span class="required">*</span><span class="name">手机验证码：</span></label>
										<input class="phoneValidateCode" type="text" max="6"/>
										<input type="button" class="phone_vali click_btn getPhonePwd" onclick="getpwd();" value="发送验证码"/>
											<input type="hidden" class="inputPwd" id="inputPwd"/>
									</div>
									<div class="agree">
										<input type="checkbox" id="agreeprocl"/>
										我已阅读并且同意<a href="${ctx}/web/user/register/agreement/index.jsp" target="_blank">《信泰保险会员注册协议》</a>
									</div>
									<div class="reg_btn">
										<input class="click_btn" id="reg_btn" type="submit" onclick="return validateForm();" value="注&nbsp;&nbsp;&nbsp;册"/>
									</div>
								</div>
							</form>
						</div>
						<div class="login_frame">
							<p class="has_account">已有会员帐号：</p>
							<div class="reg_login">
								<button class="click_btn" onclick="toLogin();">直接登录</button>
							</div>
							<p class="other_login">您还可以使用以下账号登录：</p>
							<p class="login_type alipay"><a href="${ctx}/web/user/login/alipayLogin/alipayapi.jsp">支付宝登录</a></p>
							<p class="login_type weibo"><a href="${ctx}/login/sinaLogin.do">微博登录</a></p>
							<p class="login_type tencent"><a href="${ctx }/oauth/qqoauth">QQ登录</a></p>
						</div>
					</div>
				</div>
			</div>
		</div>
