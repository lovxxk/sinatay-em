<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		$('#newPassword').blur(function(){
			var new_pass = $(this).val();
			
			if(new_pass.length >= 6){
				$('#new_tip').hide();
				$('#new_tip').html('');
			}else{
				$('#new_tip').show();
				$('#new_tip').html('���볤�Ȳ���С��6λ��');
			}
		});
		
		if($("#changeResult").val() == 'Y'){
			Sinosoft.alert({
				contentStr: "������ʾ",
				subContentStr:"�����޸ĳɹ������ס���������롣",
				width:480,
				okStr: 'ȷ��',
				cancelStr: 'ȡ��',
				cancelBtnShow:false,
				okFunc:function(){
					window.location.href="${ctx}/edit/editUserPersonal.do";
				}
			});
		}
	});

	function validate_form(thisform){
		
		var old_pass = thisform.oldPassword.value;
		var new_pass = thisform.newPassword.value;
		var confirm_pass = thisform.confirmPassword.value;
		
		if(new_pass.length < 6){
			document.getElementById('new_tip').innerHTML="���볤�Ȳ���С��6λ��";
			document.getElementById('new_tip').style.display="block";
		    return false;
		}
		if(old_pass == new_pass){
			document.getElementById('new_tip').innerHTML="�����벻����ԭ������ͬ��";
			document.getElementById('new_tip').style.display="block";
			return false;
		}
		if(new_pass!=confirm_pass){
			document.getElementById('confirm_tip').innerHTML="�����������벻һ�£�";
			document.getElementById('confirm_tip').style.display="block";
			return false;
		}
	}
	
</script>
<body>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx}/edit/editUserPersonal.do">�ʺ���Ϣ</a><span> &gt;</span></li>
				<li class="at">�޸�����</li>
			</ul>
		</div>
		<div class="member_main"><!-- item account -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>�޸�����</p>
				</div>
				<div class="member_area">
					<form action="${ctx }/edit/changePassword.do" method="post" id="personalInfoForm" onsubmit="return validate_form(this)">
						<div class="input_area">
							<div class="input_row">
								<label class="input_label"><span class="required">*</span><span class="name">ԭ���룺</span></label>
								<input class="input_text" id="oldPassword" type="password" maxlength="20" name="oldPassword"/>
								<span id="old_tip" class="errror_msg">
									<c:if test="${param.changeResult == E}">
										<c:out value="ԭ�����������!"></c:out>
									</c:if>
								</span>
							</div>
							<div class="input_row">
								<label class="input_label"><span class="required">*</span><span class="name">�����룺</span></label>
								<input class="input_text" id="newPassword" type="password" maxlength="20" name="newPassword"/>
								<span id="new_tip" class="errror_msg">
									<c:if test="${param.changeResult == N}">
										<c:out value="�����벻����ԭ������ͬ!"></c:out>
									</c:if>
								</span>
							</div>
							<div class="input_row">
								<label class="input_label"><span class="required">*</span><span class="name">ȷ�����룺</span></label>
								<input class="input_text" id="confirmPassword" type="password" max="20" name="confirmPassword"/>
								<span id="confirm_tip" class="errror_msg">
								</span>
							</div>
						</div>
						<div class="action">
							<input type="submit" class="next_step click_btn" value="ȷ���޸�">
							<input type="hidden" id="changeResult" name="changeResult" value="${param.changeResult}">
						</div>
					</form>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
</body>