<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
<script type="text/javascript">
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
		console.log($("#emailOrPhoneValidate").val());
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
		
		var inputCode = $("#inputCode").val();
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
		
		var flag = "";
		var inputCode = $("#inputCode").val();
		var userAccount = $("#userAccount").val();
		$.ajax({
			url : '${ctx }/reset/checkValidateCode.do',
			type : 'POST',
			async:false,
			data : {
				"inputCode" : inputCode,
				"userAccount" :��userAccount
			},
			dataType : "text",
			success : function(data) {
				flag = data;
			}
		});
		if (flag == "paramError") {
			Sinosoft.alert({
				contentStr: "��������",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		} else if (flag == "error") {
			Sinosoft.alert({
				contentStr: "��֤���������",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					
				}
			});
			return false;
		} else if (flag == "timeout") {
			Sinosoft.alert({
				contentStr: "��֤���ѳ�ʱ�������»�ȡ",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
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
			$('.getValidateCode').attr('value', '(' + timenum + ')������·���');
			timenum = timenum - 1;
			$(".getValidateCode").attr("disabled", true);
		} else {
			$('.getValidateCode').attr('value', '����ٴη���');
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
							contentStr : "��֤���ѷ�������������" + userAccount
									+ "����ע�����.",
							width : 480,
							okStr : 'ȷ��',
							cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
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
							contentStr : "��֤���ѷ����������ֻ�" + mobileStr + "����ע�����.",
							width : 480,
							okStr : 'ȷ��',
							cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
							okFunc : function() {
								timenum = 120;
								intervalProcess = setInterval('counttime()', 1000);
								$("#inputCode").attr("readonly", false);
							}
						});
					}
				} else if (data == "limit") {
					Sinosoft.alert({
						contentStr : "�ǳ���Ǹ�����������������վ��������֤�뷢�����ۼƳ���50�Σ������������ԡ�",
						width : 480,
						okStr : 'ȷ��',
						cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
						okFunc : function() {
							// 						timenum = 120;
							// 						intervalProcess = setInterval('counttime()',1000);
						}
					});
				} else {
					Sinosoft.alert({
						contentStr : "��������æ�����Ժ�����",
						width : 480,
						okStr : 'ȷ��',
						cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
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
			<div class="title">�һ�����</div>
			<div class="retrieve_nav"></div>
			<form action="${ctx }/reset/inputNewPwd.do" method="post" id="validateCode_form">
				<input type="hidden" id="resetPwdType" value="${resetPwdType }">
				<input type="hidden" id="userAccount" name="userAccount" value="${userAccount }">
				<div class="input_area">
					<div class="input_row user_name">
						<label class="input_label">
							<span class="required">*</span><span class="name">
								<c:if test="${resetPwdType == 'email' }">
									���������ַΪ��
								</c:if>
								<c:if test="${resetPwdType == 'phone' }">
									�����ֻ����룺
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
						<input type="button" class="resend click_btn getValidateCode" style="cursor: hand; font-size:12px;" onclick="return sendCode();" value="��ѻ�ȡ��֤��">
					</div>
					<div class="input_row send_validate">
						<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">��֤�룺</span></label>
						<input class="input_text" id="inputCode" type="text" maxlength="6" readonly="true"/>
					</div>
				</div>
			</form>
			<div class="action">
				<button class="next click_btn" type="submit" onclick="return validateResetPwd();">��&nbsp;һ&nbsp;��</button>
			</div>
		</div>
	</div>
</div>