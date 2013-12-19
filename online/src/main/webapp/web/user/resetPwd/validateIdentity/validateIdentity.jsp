<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
<script type="text/javascript">
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
		console.log($("#emailOrPhoneValidate").val());
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
		
		var inputCode = $("#inputCode").val();
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
		
		var flag = "";
		var inputCode = $("#inputCode").val();
		var userAccount = $("#userAccount").val();
		$.ajax({
			url : '${ctx }/reset/checkValidateCode.do',
			type : 'POST',
			async:false,
			data : {
				"inputCode" : inputCode,
				"userAccount" :　userAccount
			},
			dataType : "text",
			success : function(data) {
				flag = data;
			}
		});
		if (flag == "paramError") {
			Sinosoft.alert({
				contentStr: "参数错误",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			return false;
		} else if (flag == "error") {
			Sinosoft.alert({
				contentStr: "验证码输入错误",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			return false;
		} else if (flag == "timeout") {
			Sinosoft.alert({
				contentStr: "验证码已超时，请重新获取",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			return false;
		}
				
		$("#validateCode_form").submit();
		return true;
	}

	function myReload() {
		$("#entImg")[0].src = $("#entImg")[0].src + "?nocache="
				+ new Date().getTime();
	}
	var intervalProcess;
	var timenum = 120;
	function counttime() {
		if (timenum > 0) {
			$('.getValidateCode').attr('value', '(' + timenum + ')秒后重新发送');
			timenum = timenum - 1;
			$(".getValidateCode").attr("disabled", true);
		} else {
			$('.getValidateCode').attr('value', '点击再次发送');
			$(".getValidateCode").attr("disabled", false);
			clearInterval(intervalProcess);
		}
	}
	function sendCode() {
		var resetPwdType = $("#resetPwdType").val();
		var userAccount = $("#userAccount").val();
		$.ajax({
			url : '${ctx}/reset/findUserAccount.do',
			type : 'POST',
			async : false,
			data : {
				'userAccount' : userAccount
			},
			dataType : "text",
			success : function(data) {
				userAccount = data;
			}
		});

		$.ajax({
			url : '${ctx}/reset/resetPwdValidateCode.do',
			type : 'POST',
			async : false,
			data : {
				'userAccount' : userAccount
			},
			dataType : "text",
			success : function(data) {
				if (data != null && data == "success") {
					if (resetPwdType == 'email') {
						Sinosoft.alert({
							contentStr : "验证码已发送至您的邮箱" + userAccount
									+ "，请注意查收.",
							width : 480,
							okStr : '确定',
							cancelBtnShow : false,//是否显示关闭按钮
							okFunc : function() {
								timenum = 120;
								intervalProcess = setInterval('counttime()', 1000);
								$("#inputCode").attr("readonly", false);
							}
						});
					} else {
						var mobileStr = '';
						if (userAccount != '' && userAccount.length == 11) {
							mobileStr = userAccount.substring(0, 3) + "****" + userAccount.substring(7, userAccount.length);
						}
						Sinosoft.alert({
							contentStr : "验证码已发送至您的手机" + mobileStr + "，请注意查收.",
							width : 480,
							okStr : '确定',
							cancelBtnShow : false,//是否显示关闭按钮
							okFunc : function() {
								timenum = 120;
								intervalProcess = setInterval('counttime()', 1000);
								$("#inputCode").attr("readonly", false);
							}
						});
					}
				} else if (data == "limit") {
					Sinosoft.alert({
						contentStr : "非常抱歉，今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。",
						width : 480,
						okStr : '确定',
						cancelBtnShow : false,//是否显示关闭按钮
						okFunc : function() {
							// 						timenum = 120;
							// 						intervalProcess = setInterval('counttime()',1000);
						}
					});
				} else {
					Sinosoft.alert({
						contentStr : "服务器繁忙，请稍后重试",
						width : 480,
						okStr : '确定',
						cancelBtnShow : false,//是否显示关闭按钮
						okFunc : function() {

						}
					});
				}
			}
		});
		return false;
	}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="retrieve_main">
			<div class="title">找回密码</div>
			<div class="retrieve_nav"></div>
			<form action="${ctx }/reset/inputNewPwd.do" method="post" id="validateCode_form">
				<input type="hidden" id="resetPwdType" value="${resetPwdType }">
				<input type="hidden" id="userAccount" name="userAccount" value="${userAccount }">
				<div class="input_area">
					<div class="input_row user_name">
						<label class="input_label">
							<span class="required">*</span><span class="name">
								<c:if test="${resetPwdType == 'email' }">
									您的邮箱地址为：
								</c:if>
								<c:if test="${resetPwdType == 'phone' }">
									您的手机号码：
								</c:if>
							</span>
						</label>
						<label class="tip_label id">
							<c:if test="${resetPwdType == 'email' }">
								${userPersonal.email }
							</c:if>
							<c:if test="${resetPwdType == 'phone' }">
								${userPersonal.mobilePhone }
							</c:if>
						</label>
						<input type="button" class="resend click_btn getValidateCode" style="cursor: hand; font-size:12px;" onclick="return sendCode();" value="免费获取验证码">
					</div>
					<div class="input_row send_validate">
						<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">验证码：</span></label>
						<input class="input_text" id="inputCode" type="text" maxlength="6" readonly="true"/>
					</div>
				</div>
			</form>
			<div class="action">
				<button class="next click_btn" type="submit" onclick="return validateResetPwd();">下&nbsp;一&nbsp;步</button>
			</div>
		</div>
	</div>
</div>