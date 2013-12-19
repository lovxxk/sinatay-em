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
				//У���ֻ������Ƿ���ע��
				//true����ע��;false��δע��
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
							$('.registeredName').text("���ֻ����ѱ�ʹ��");
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
							$('.registeredName').text("�������ַ�ѱ�ʹ��");
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
					$('.registeredName').text("��������ȷ�����䣡");
				}else{
					$('.registeredName').text("��������ȷ���ֻ��Ż����䣡");
				}
			}
		});
		$('.password').blur(function() {
			var regpassword = /^\w{6,17}$/.test($('.password').val());
			if (!regpassword ) {
				$('.validPassword').text("���������6��17λ����ĸ������");
				return false;
			} else {
				$('.validPassword').text("");
			}
		});
		$('.password2').blur(function() {
			if ($('.password2').val() != $('.password').val()) {
				$('.validPassword2').text("�������벻һ��");
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
				contentStr: "�ֻ������ʽ����ȷ",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
					doReceive("�ֻ���̬�����ѷ��͵�" + mobileStr);
					timenum = 120;
					intervalProcess = setInterval('counttime()',1000);
				} else if(data =="limit") {
					Sinosoft.alert({
						contentStr: "�ǳ���Ǹ�����������������վ��������֤�뷢�����ۼƳ���50�Σ������������ԡ�",
						width:480,
						okStr: 'ȷ��',
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
						okFunc:function(){
							
						}
					});
					$('.getPhonePwd').valiCodeEnable();
					$('.userAccount').attr('disabled',false);
				} else {
					Sinosoft.alert({
						contentStr: "����ʧ��",
						width:480,
						okStr: 'ȷ��',
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
			$('.getPhonePwd').val('('+timenum+')������·���');
			timenum = timenum - 1;
		} else {
			$('.getPhonePwd').val('����ٴη���');
			$('.getPhonePwd').valiCodeEnable();
			$('.userAccount').attr('disabled',false);
			clearInterval(intervalProcess);
		}
	}
	function changeImage(obj){
		$("#"+obj).attr("src","${ctx}/web/user/register/image.jsp?id="+Math.random(100)*10);
	}
	
 	/**ajax��֤��֤��**/
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
				contentStr: "�������û���",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			}); 
			$('.registeredName').text("�������û���!");
			return false;
		} */
		if (!regmobile && !regemail) {
			 Sinosoft.alert({
					contentStr: "��������ȷ���ֻ��Ż����䣡",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					okFunc:function(){
						
					}
				}); 
			return false;
		}

		if ($('.customerNameValidate').val() == "false") {
			Sinosoft.alert({
				contentStr: $(".registeredName").text(),
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		}

		if(!regpassword ) {
			$('.validPassword').text("���������6��17λ����ĸ������");
			Sinosoft.alert({
				contentStr: "����ȷ��дע����Ϣ�����������6��17λ����ĸ�����֣���",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			$('.password').focus();
			return false;
		}
		if($('.password2').val() != $('.password').val()){
			$('.validPassword2').text("����ȷ��дע����Ϣ���������벻һ�£���");
			Sinosoft.alert({
				contentStr: "����ȷ��дע����Ϣ���������벻һ�£���",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
					contentStr: "��������֤��",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					okFunc:function(){
						
					}
				});
				return false;
			}
			if (!checkImage()) {
				Sinosoft.alert({
					contentStr: "��֤���������",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					okFunc:function(){
						
					}
				});
				myReload();
				$("#erand").val("");
				return false;
			}
		}

		//ͬ����̩ע��Э��
		if(!navService()){
			return false;
		}
		//ֻ�е��û���ע�᷽ʽ���ֻ���ʱ����Ҫ��֤�ֻ���̬����
		if (regmobile) {
			var phonePwd = $('.phoneValidateCode').val();
			if (phonePwd == "") {
				Sinosoft.alert({
					contentStr: "��������֤��",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
					contentStr: "�ֻ���֤���������",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					okFunc:function(){
						
					}
				});
				return false;
			} else if (code == "invalid") {
				Sinosoft.alert({
					contentStr: "��֤����ʧЧ�������»�ȡ��֤��",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
				contentStr: "��ȷ��ҳ���·��������ݣ�",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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


	//��ȡ��ť
	function doReceive(parameter){
		var receive_input = loadReceiveInput(parameter);
		Sinosoft.alert({
			contentStr: parameter,
			subContentStr:'��ע�����',
			width:480,
			okStr: 'ȷ��',
			cancelStr: 'ȡ��',
			cancelBtnShow:false
		});

	}
	
</script>
		<div class="middle">
			<div class="h_layout">
				<div class="register_main">
					<p class="reg_title">���ע��</p>
					<div class="main_frame">
						<div class="reg_frame">
							<form id="register" class="register" action="${ctx}/register/saveUserPersonal.do" method="post">
								<input type="hidden" id="registerType"/>
								<div class="reg_form">
									<div class="input_field">
										<label><span class="required">*</span><span class="name">��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;����</span></label>
										<input type="text" name="cuserAccount" id="userAccount" class="userAccount" maxlength="40"/>
										<input type="hidden" name="customer.userAccount" class="hiddenUserAccount">
										<span class="errorMessag registeredName"></span>
										<input type="hidden" id="customerNameValidate" class="customerNameValidate"/>
										<input type="hidden" id="customerNameExist" class="customerNameExist"/>
									</div>
									<div class="input_field">
										<label><span class="required">*</span><span class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�룺</span></label>
										<input name="customer.pwd" type="password" id="customer.password" class="password"  style="_width:148px;" maxlength="16"/>
										<span class="errorMessag validPassword"></span>
									</div>
									<div class="input_field">
										<label><span class="required">*</span><span class="name">ȷ&nbsp;��&nbsp;��&nbsp;�룺</span></label>
										<input name="password2" type="password" id="password2"  class="password2"  style="_width:148px;" maxlength="16"/>
										<span class="errorMessag validPassword2"></span>
									</div>
									<div class="input_field" id="entImg_div">
										<label><span class="required">*</span><span class="name">��&nbsp;&nbsp;&nbsp;֤&nbsp;&nbsp;&nbsp;�룺</span></label>
										<input type="text" id="erand" name="erand" maxlength="4" class="input6-yz" />
										<div class="vali_img">
											<img style="cursor: pointer;" id="entImg" name="entImg"
												src="${ctx }/PictureCheckCode.jpeg" alt="��֤��" title="���ˢ����֤��"
												onclick="myReload()" height="18" align="middle"/>
										</div>
									</div>
									<div class="input_field" id="phoneValidateDiv" style="display: none;">
										<label><span class="required">*</span><span class="name">�ֻ���֤�룺</span></label>
										<input class="phoneValidateCode" type="text" max="6"/>
										<input type="button" class="phone_vali click_btn getPhonePwd" onclick="getpwd();" value="������֤��"/>
											<input type="hidden" class="inputPwd" id="inputPwd"/>
									</div>
									<div class="agree">
										<input type="checkbox" id="agreeprocl"/>
										�����Ķ�����ͬ��<a href="${ctx}/web/user/register/agreement/index.jsp" target="_blank">����̩���ջ�Աע��Э�顷</a>
									</div>
									<div class="reg_btn">
										<input class="click_btn" id="reg_btn" type="submit" onclick="return validateForm();" value="ע&nbsp;&nbsp;&nbsp;��"/>
									</div>
								</div>
							</form>
						</div>
						<div class="login_frame">
							<p class="has_account">���л�Ա�ʺţ�</p>
							<div class="reg_login">
								<button class="click_btn" onclick="toLogin();">ֱ�ӵ�¼</button>
							</div>
							<p class="other_login">��������ʹ�������˺ŵ�¼��</p>
							<p class="login_type alipay"><a href="${ctx}/web/user/login/alipayLogin/alipayapi.jsp">֧������¼</a></p>
							<p class="login_type weibo"><a href="${ctx}/login/sinaLogin.do">΢����¼</a></p>
							<p class="login_type tencent"><a href="${ctx }/oauth/qqoauth">QQ��¼</a></p>
						</div>
					</div>
				</div>
			</div>
		</div>
