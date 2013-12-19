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
				//�ж��û�¼����Ƿ����ֻ���
				if (!isNaN(emailOrPhone) && emailOrPhone.length == 11 && emailOrPhone.indexOf(".") < 0) {
					//11λ�ֻ����Ƿ�Ϸ�
					var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(emailOrPhone);
					if (!regmobile) {
						Sinosoft.alert({
							contentStr: "�����ֻ��Ų��Ϸ�",
							width:480,
							okStr: 'ȷ��',
							cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
							okFunc:function(){
								
							}
						});
						$("#emailOrPhoneValidate").attr("value","false");
						return false;
					}
					$("#emailOrPhoneValidate").attr("value","true");
					phoneFlag = true;
				}
				//�ж��û�¼����Ƿ��������Լ������ַ�Ƿ�Ϸ�
				var emailFlag = false;
				if (emailOrPhone.indexOf("@") > 0) {
					//�û�¼��������Ƿ�Ϸ�
					var regemail =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9|_|\_|\.]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(emailOrPhone);
					if(!regemail){
// 						$('.emailOrPhoneMessage').text("����дһ����ȷ�������ַ");
// 						alert("����дһ����ȷ�������ַ");
						Sinosoft.alert({
							contentStr: "����дһ����ȷ�������ַ",
							width:480,
							okStr: 'ȷ��',
							cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
// 					$('.emailOrPhoneMessage').text("������Ϸ����ֻ��Ż������ַ");
// 					alert("������Ϸ����ֻ��Ż������ַ");
					Sinosoft.alert({
						contentStr: "������Ϸ����ֻ��Ż������ַ",
						width:480,
						okStr: 'ȷ��',
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
				contentStr: "�����������ֻ�����������ַ",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		}
		if ($("#emailOrPhoneValidate").val() == "false") {
			Sinosoft.alert({
				contentStr: "�ֻ������������������",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		}
		
		var inputCode = $("#validateCode").val();
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
		$("#input_form").submit();
		return true;
	}
	// 	/**ajax��֤��֤��**/
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
			<div class="title">�һ�����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${validateMessage }</font></div>
			<div class="retrieve_nav"></div>
			<form action="${ctx }/reset/inputEmailOrPhone.do" method="post" id="input_form">
				<div class="input_area">
				<div class="input_row user_name">
					<label class="input_label" for="name"><span class="required">*</span><span class="name">��������������ֻ����룺</span></label>
					<input class="input_text" id="emailOrPhone" name="emailOrPhone" type="text" maxlength="50"/>
					<span class="emailOrPhoneMessage" style="color: red;"></span>
					<input type="hidden" id="emailOrPhoneValidate">
					<label class="tip_label forget">�������룿��ʹ���ֻ��Ż�����ɣ�</label>
				</div>
				<div class="input_row validate">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">��֤�룺</span></label>
					<input class="input_text" id="validateCode" type="text" maxlength="6"/>
					<div class="tip_label validate_img">
						<img style="cursor: pointer;" id="entImg" name="entImg"
							src="${ctx }/PictureCheckCode.jpeg" alt="��֤��" title="���ˢ����֤��"
							onclick="myReload()" height="20"/>
					</div>
<!-- 					<div class="tip_label change_img">�����������һ��</div> -->
				</div>
			</div>
			</form>
			<div class="action">
				<button class="next click_btn" type="submit" onclick="return validateResetPwd();">��&nbsp;һ&nbsp;��</button>
			</div>
		</div>
	</div>
</div>