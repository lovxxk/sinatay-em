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
				$('#new_tip').html('密码长度不得小于6位！');
			}
		});
		
		if($("#changeResult").val() == 'Y'){
			Sinosoft.alert({
				contentStr: "操作提示",
				subContentStr:"密码修改成功！请记住您的新密码。",
				width:480,
				okStr: '确定',
				cancelStr: '取消',
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
			document.getElementById('new_tip').innerHTML="密码长度不得小于6位！";
			document.getElementById('new_tip').style.display="block";
		    return false;
		}
		if(old_pass == new_pass){
			document.getElementById('new_tip').innerHTML="新密码不能与原密码相同！";
			document.getElementById('new_tip').style.display="block";
			return false;
		}
		if(new_pass!=confirm_pass){
			document.getElementById('confirm_tip').innerHTML="两次密码输入不一致！";
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
				<li><a href="${ctx}/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx}/edit/editUserPersonal.do">帐号信息</a><span> &gt;</span></li>
				<li class="at">修改密码</li>
			</ul>
		</div>
		<div class="member_main"><!-- item account -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>修改密码</p>
				</div>
				<div class="member_area">
					<form action="${ctx }/edit/changePassword.do" method="post" id="personalInfoForm" onsubmit="return validate_form(this)">
						<div class="input_area">
							<div class="input_row">
								<label class="input_label"><span class="required">*</span><span class="name">原密码：</span></label>
								<input class="input_text" id="oldPassword" type="password" maxlength="20" name="oldPassword"/>
								<span id="old_tip" class="errror_msg">
									<c:if test="${param.changeResult == E}">
										<c:out value="原密码输入错误!"></c:out>
									</c:if>
								</span>
							</div>
							<div class="input_row">
								<label class="input_label"><span class="required">*</span><span class="name">新密码：</span></label>
								<input class="input_text" id="newPassword" type="password" maxlength="20" name="newPassword"/>
								<span id="new_tip" class="errror_msg">
									<c:if test="${param.changeResult == N}">
										<c:out value="新密码不能与原密码相同!"></c:out>
									</c:if>
								</span>
							</div>
							<div class="input_row">
								<label class="input_label"><span class="required">*</span><span class="name">确认密码：</span></label>
								<input class="input_text" id="confirmPassword" type="password" max="20" name="confirmPassword"/>
								<span id="confirm_tip" class="errror_msg">
								</span>
							</div>
						</div>
						<div class="action">
							<input type="submit" class="next_step click_btn" value="确认修改">
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