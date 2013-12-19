<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#emailOrPhone').blur(function(){
			var emailOrPhone = $('#emailOrPhone').val();
			if (emailOrPhone != "") {
				var phoneFlag = false;
				//判断用户录入的是否是手机号
				if (!isNaN(emailOrPhone) && emailOrPhone.length == 11 && emailOrPhone.indexOf(".") < 0) {
					//11位手机号是否合法
					var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(emailOrPhone);
					if (!regmobile) {
						Sinosoft.alert({
							contentStr: "输入手机号不合法",
							width:480,
							okStr: '确定',
							cancelBtnShow:false,//是否显示关闭按钮
							okFunc:function(){
								
							}
						});
						$("#emailOrPhoneValidate").attr("value","false");
						return false;
					}
					$("#emailOrPhoneValidate").attr("value","true");
					phoneFlag = true;
				}
				//判断用户录入的是否是邮箱以及邮箱地址是否合法
				var emailFlag = false;
				if (emailOrPhone.indexOf("@") > 0) {
					//用户录入的邮箱是否合法
					var regemail =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9|_|\_|\.]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(emailOrPhone);
					if(!regemail){
// 						$('.emailOrPhoneMessage').text("请填写一个正确的邮箱地址");
// 						alert("请填写一个正确的邮箱地址");
						Sinosoft.alert({
							contentStr: "请填写一个正确的邮箱地址",
							width:480,
							okStr: '确定',
							cancelBtnShow:false,//是否显示关闭按钮
							okFunc:function(){
								
							}
						});
						$("#emailOrPhoneValidate").attr("value","false");
						return false;
					}
					$('.emailOrPhoneMessage').text("");
					$("#emailOrPhoneValidate").attr("value","true");
					emailFlag = true;
				}
				if (!phoneFlag && !emailFlag) {
					$('#registerType').attr("value", "email");
// 					$('.emailOrPhoneMessage').text("请输入合法的手机号或邮箱地址");
// 					alert("请输入合法的手机号或邮箱地址");
					Sinosoft.alert({
						contentStr: "请输入合法的手机号或邮箱地址",
						width:480,
						okStr: '确定',
						cancelBtnShow:false,//是否显示关闭按钮
						okFunc:function(){
							
						}
					});
					$('#emailOrPhone').val("");
					$("#emailOrPhoneValidate").attr("value","false");
					return false;
				}
			}
		});
	});

	function validateResetPwd() {
		var emailOrPhone = $("#emailOrPhone").val();
		if (emailOrPhone == "") {
			Sinosoft.alert({
				contentStr: "请输入您的手机号码或邮箱地址",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			return false;
		}
		if ($("#emailOrPhoneValidate").val() == "false") {
			Sinosoft.alert({
				contentStr: "手机号码或邮箱输入有误",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			return false;
		}
		
		var inputCode = $("#validateCode").val();
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
		$("#input_form").submit();
		return true;
	}
	// 	/**ajax验证验证码**/
	function checkImage() {
	 	var dataFlag = false;
		var inputCode = $("#validateCode").val();
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
	function myReload(){
         $("#entImg")[0].src=$("#entImg")[0].src + "?nocache="+new Date().getTime();
   }  
</script>
<div class="middle">
	<div class="h_layout">
		<div class="retrieve_main">
			<div class="title">找回密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${validateMessage }</font></div>
			<div class="retrieve_nav"></div>
			<form action="${ctx }/reset/inputEmailOrPhone.do" method="post" id="input_form">
				<div class="input_area">
				<div class="input_row user_name">
					<label class="input_label" for="name"><span class="required">*</span><span class="name">输入您的邮箱或手机号码：</span></label>
					<input class="input_text" id="emailOrPhone" name="emailOrPhone" type="text" maxlength="50"/>
					<span class="emailOrPhoneMessage" style="color: red;"></span>
					<input type="hidden" id="emailOrPhoneValidate">
					<label class="tip_label forget">忘记密码？就使用手机号或邮箱吧！</label>
				</div>
				<div class="input_row validate">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">验证码：</span></label>
					<input class="input_text" id="validateCode" type="text" maxlength="6"/>
					<div class="tip_label validate_img">
						<img style="cursor: pointer;" id="entImg" name="entImg"
							src="${ctx }/PictureCheckCode.jpeg" alt="验证码" title="点击刷新验证码"
							onclick="myReload()" height="20"/>
					</div>
<!-- 					<div class="tip_label change_img">看不清楚，换一张</div> -->
				</div>
			</div>
			</form>
			<div class="action">
				<button class="next click_btn" type="submit" onclick="return validateResetPwd();">下&nbsp;一&nbsp;步</button>
			</div>
		</div>
	</div>
</div>