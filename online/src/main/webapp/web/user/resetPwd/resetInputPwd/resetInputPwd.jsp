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
						contentStr: "���������6��17λ����ĸ������",
						width:480,
						okStr: 'ȷ��',
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
						okFunc:function(){
							
						}
					});
// 					alert("���������6��17λ����ĸ������");
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
					contentStr: "�������벻һ��",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
				contentStr: "�������벻�Ϸ�",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		}
		if ($("#validatedPwd2").val() == 'false') {
			Sinosoft.alert({
				contentStr: "�����������벻һ��",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
				�һ�����
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
					<label class="input_label"><span class="required">*</span><span class="name">�����룺</span></label>
					<input class="input_text pwd" id="validateCode" name="pwd" type="password"/>
					<input type="hidden" id="validatedPwd" value="false">
					<div class="tip_label notice">
						<P>������6-17λ�ַ�����ĸ�����֣���϶���</P>
					</div>
				</div>
				<div class="input_row repeat_password">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">�ٴ����룺</span></label>
					<input class="input_text pwd2" id="validateCode" type="password"/>
					<input type="hidden" id="validatedPwd2" value="false">
				</div>
			</div>
			</form>
			<div class="action">
				<button class="next click_btn" type="submit" onclick="return validateResetPwd();">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</button>
			</div>
		</div>
	</div>
</div>