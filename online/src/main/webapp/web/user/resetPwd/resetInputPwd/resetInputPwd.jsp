<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('.pwd').blur(function() {
			var pwd = $('.pwd').val();
			if (pwd != "") {
				var regpassword = /^\w{6,17}$/.test(pwd);
				if (!regpassword ) {
					Sinosoft.alert({
						contentStr: "密码必须是6到17位的字母或数字",
						width:480,
						okStr: '确定',
						cancelBtnShow:false,//是否显示关闭按钮
						okFunc:function(){
							
						}
					});
// 					alert("密码必须是6到17位的字母或数字");
					$("#validatedPwd").attr("value", "false");
					return false;
				} else {
					$("#validatedPwd").attr("value", "true");
				}
			}
		});
		$('.pwd2').blur(function() {
			if ($('.pwd2').val() != $('.pwd').val()) {
				Sinosoft.alert({
					contentStr: "两次密码不一致",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
						
					}
				});
				$("#validatedPwd2").attr("value", "false");
				return false;
			} else {
				$("#validatedPwd2").attr("value", "true");
			}
		});
	});

	function validateResetPwd() {
		if ($("#validatedPwd").val() == 'false') {
			Sinosoft.alert({
				contentStr: "密码输入不合法",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			return false;
		}
		if ($("#validatedPwd2").val() == 'false') {
			Sinosoft.alert({
				contentStr: "两次密码输入不一致",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					
				}
			});
			return false;
		}
		$("#inputPwd_form").submit();
		
		return true;
	}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="retrieve_main">
			<div class="title">
				找回密码
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<c:if test="${!empty validateMessage }">
					<font color="red">${validateMessage }</font>
				</c:if>
			</div>
			<div class="retrieve_nav"></div>
			<form action="${ctx }/reset/updatePwd.do" method="post" id="inputPwd_form">
				<input type="hidden" name="userAccount" id="userAccount" value="${userAccount }">
				<div class="input_area">
				<div class="input_row password">
					<label class="input_label"><span class="required">*</span><span class="name">新密码：</span></label>
					<input class="input_text pwd" id="validateCode" name="pwd" type="password"/>
					<input type="hidden" id="validatedPwd" value="false">
					<div class="tip_label notice">
						<P>密码由6-17位字符（字母、数字）组合而成</P>
					</div>
				</div>
				<div class="input_row repeat_password">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">再次输入：</span></label>
					<input class="input_text pwd2" id="validateCode" type="password"/>
					<input type="hidden" id="validatedPwd2" value="false">
				</div>
			</div>
			</form>
			<div class="action">
				<button class="next click_btn" type="submit" onclick="return validateResetPwd();">提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交</button>
			</div>
		</div>
	</div>
</div>