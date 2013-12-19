<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%@ include file="/web/user/login/userPersonLoginValidate.jsp"%>
<%@ include file="/web/user/login/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>

<link rel="stylesheet" type="text/css"  href="${ctx }/global/css/emailpop.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery/emailpop.js"></script>
		
<script type="text/javascript">
var s_userName = '${geUserPersonal.userAccount }';
$(function(){
	$("#user_name").emailpop();
	
	$("#user_name").blur(function(){
		checkUserName();
	});
});
function showErrorMessage(){
	$("#message").html("${loginMessage}");
}

function showIdAccount(){
	$('.id_account').show();
}

function hideIdAccount(){
	$('.id_account').hide();
}
function checkUserName(){
	var userName = $("#user_name").val();
	if(userName == "" || userName == "����/�ֻ�/���֤"){
		$("#message").html("�������¼�˺�!");
		return false;
	}
	var regmobile =  /^(1[3|5|8|4][0-9]\d{8})$/.test(userName);
	var regemail = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(userName);
	var card = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	var eighteenCard = /^[\d]{6}(19|20)*[\d]{2}((0[1-9])|(10|11|12))([012][\d]|(30|31))[\d]{3}[xX\d]$/.test(userName);
	var fifteenCard = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(userName);
	var card = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(userName);
	if(regmobile||regemail){
		hideIdAccount();
	}else{
		if((userName.length == 15&& fifteenCard) || (userName.length == 18 && eighteenCard)){
			var result = "";
			$.ajax({
				type : "POST",
				async : false,
				url : "${ctx}/login/checkUserName.do",
				dataType : 'text',
				data : {userName : userName },
				success: function(data) {
					result = data;
				}
			});
			if(result.indexOf("%") > 0){
				var accounts = result.split("%%%");
				var accs = '';
				for (var i = 0;i < accounts.length; i ++) {
					var account = accounts[i];
					if(accounts[i].indexOf("@") < 0){
						account = account.substring(0,3)+"****"+account.substring(7,account.length);
					}
					accs = accs + '<div class="account_item"><input type="radio" name="account1" value="'+accounts[i]+'">'+account+'</input><br></div>';
				}
				$(".account_item").remove();
				$("#_account").append(accs);
				$('.id_account').show();
			}else{
				hideIdAccount();
			}
		}else if(card){
			Sinosoft.alert({
				contentStr: "֤���Ŵ���",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
				}
			});
			return false;
		}
	}
}

function login(){
	var userName = $("#user_name").val();
	if(userName == "" || userName == "����/�ֻ�/���֤"){
		$("#message").html("�������¼�˺�!");
		return false;
	}
	
	var password = $("#password").val();
	if(password == "" || password == "����������"){
		$("#message").html("�������¼����!");
		return false;
	}
	
	var flag = document.getElementById('remeberMe').checked;
	$.ajax({
		type : "POST",
		async : false,
		url : "${ctx}/login/remeber.do",
		dataType : 'text',
		data : {remeber : flag },
		success: function(data) {}
	});
	var result = "";
	$.ajax({
		type : "POST",
		async : false,
		url : "${ctx}/login/checkSubmitLoginForm.do",
		dataType : 'text',
		data : {userName : userName, password : password },
		success: function(data) {
			result = data;
		}
	});
	if (result == "paramError") {
		Sinosoft.alert({
			contentStr: "��������",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function() {
			}
		});
		return false;
	} else if (result == "accountLogin") {//����ͨ�����֤�ŵ�¼��,�ύ����
		$("#login_form").submit();
	} else if (result == "idNumError") {
		Sinosoft.alert({
			contentStr: "֤���Ż��������",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
				$("#password").attr("value", "");
			}
		});
		return false;
	}else if (result == "accountLock"){//�ʺ��ѱ�����
		Sinosoft.alert({
			contentStr: "������������ѳ���3�Σ����˻��ѱ�������������2Сʱ���ٵ�½",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
				$("#user_name").attr("value", "");
				$("#password").attr("value", "");
			}
		});
		return false;
	} else if (result == "bindFalse") {
		Sinosoft.alert({
			contentStr: "����δ�������������δ���й������󶨣�����ͨ�����֤�ŵ�¼",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
				$("#user_name").attr("value", "");
				$("#password").attr("value", "");
			}
		});
		return false;
	} else if (result == "idNumLogin") {//�����֤��������ֻ��Ӧһ���ʺ�
		var idLoginFlag = "";
		$.ajax({
			type : "POST",
			async : false,
			url : "${ctx}/login/idNumLogin.do",
			dataType : 'text',
			data : {userAccount : userName,pwd : password },
			success : function(data) {
				idLoginFlag = data;
			}
		});
		if (idLoginFlag == "accountNull") {
			Sinosoft.alert({
				contentStr: "�û�������Ϊ��",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
				}
			});
		} else if (idLoginFlag == "customerNull") {
			Sinosoft.alert({
				contentStr: "�û��������ڣ���ȷ��",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
				}
			});
		} else if (idLoginFlag == "lock") {
			Sinosoft.alert({
				contentStr: "������������ѳ���3�Σ����˻��ѱ�������������2Сʱ���ٵ�½",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
				}
			});
		} else if (idLoginFlag == "pwdNull") {
			Sinosoft.alert({
				contentStr: "���벻��Ϊ��",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
				}
			});
		} else if (idLoginFlag == "pwdError") {
			Sinosoft.alert({
				contentStr: "���������ȷ�����������Ƿ���ȷ",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					$("#password").attr("value", "");
				}
			});
		} else if (idLoginFlag == "bindFalse") {
			Sinosoft.alert({
				contentStr: "����δ�������������δ���й������󶨣�������ͨ�����֤�ŵ�¼",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
		}else if (idLoginFlag == "success") {
			window.location.href= "${ctx }/login/userPersonalLogin.do";
		}
		return false;
	}else {
		var accounts = result.split("%%%");
		var account = $('input[name="account1"]:checked').val();
		openLoginWindow(accounts);
		return false;
	}
}
function openLoginWindow(accounts) {
	var account = $('input[name="account1"]:checked').val();
	if (account == null || account == "") {
		Sinosoft.alert({
			contentStr: "��ѡ����Ҫ��¼���ʺ�",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
			}
		});
		return false;
	}
	identifyNumLogin(accounts, account);
}
	
function identifyNumLogin(accounts,account) {
	var idLoginFlag= "";
	var password = $("#password").val();
	$.ajax({
		type : "POST",
		async : false,
		url : "${ctx}/login/checkLogin.do",
		dataType : 'text',
		data : {userName : account,password : password, loginType : "idNum" },
		success : function(data) {
			idLoginFlag = data;
		}
	});
	if (idLoginFlag == "accountNull") {
		Sinosoft.alert({
			contentStr: "�û�������Ϊ��",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
			}
		});
	} else if (idLoginFlag == "customerNull") {
		Sinosoft.alert({
			contentStr: "�û��������ڣ���ȷ��",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
				$("#user_name").attr("value", "");
				$("#password").attr("value", "");
			}
		});
	} else if (idLoginFlag == "lock") {
		Sinosoft.alert({
			contentStr: "������������ѳ���3�Σ����˻��ѱ�������������2Сʱ���ٵ�½",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
				$("#user_name").attr("value", "");
				$("#password").attr("value", "");
			}
		});
	} else if (idLoginFlag == "pwdNull") {
		Sinosoft.alert({
			contentStr: "���벻��Ϊ��",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
			}
		});
	} else if (idLoginFlag == "pwdError") {
		Sinosoft.alert({
			contentStr: "���������ȷ�����������Ƿ���ȷ",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
				$("#password").attr("value", "");
			}
		});
	}  else if (idLoginFlag == "bindFalse") {
		Sinosoft.alert({
			contentStr: "����δ���������������ʹ�����֤�ŵ�¼",
			width:480,
			okStr: 'ȷ��',
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okFunc:function(){
				$("#user_name").attr("value", "");
				$("#password").attr("value", "");
			}
		});
	} else if (idLoginFlag == "success") {
		window.location.href= "${ctx }/login/userPersonalLogin.do";
	}
}
function loginByIdNum(accounts){
	var subSuccess = '<div class="alert_subscribe">��ѡ����Ҫ��¼���˺�:<div class="success"></div><div class="main_content">';
	for (var i = 0; i < accounts.length; i ++) {
		subSuccess += '<div class="sub_txt"><input type="radio" name="loginAccount" value="'+accounts[i]+'">'+accounts[i]+'</input><br></div>';
	}
	subSuccess += '</div></div>';
	
	subSuccess = $(subSuccess);
	return subSuccess;
}
function onReturn(evt) {
	var currKey=evt.keyCode||evt.which||evt.charCode; 
	if (currKey==13) {
		login();
	}
}
</script>
	<body onkeypress="return onReturn(event);" onload="showErrorMessage();">
		<div class="middle">
			<div class="h_layout">
				<div class="login_main">
					<div class="login_form">
						<c:if test="${geUserPersonal != null }">
							<c:if test="${empty message }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ŀǰ���ѵ�¼�ʺţ�${geUserPersonal.userAccount }
							</c:if>
							<c:if test="${!empty message }">
								Ŀǰ���ѵ�¼�ʺţ�${geUserPersonal.userAccount }
							</c:if>
							<br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ϊ�������˻���ȫ����ȷ�����룺
							<br>
							<br>
						</c:if>
						<form action="${ctx}/j_spring_security_check" id="login_form" method="post">
						
						<c:choose>
							<c:when test="${geUserPersonal != null }">
								<input class="input_field" type="hidden" name="j_username" id="user_name" value=""/>
							</c:when>
							<c:otherwise>
								<label for="user_name" class="input_label">��Ա����<span id="message" escapeXml="false"></span></label>
								<input class="input_field" type="text" name="j_username" id="user_name" value="<%
									if (request.getAttribute("userAccName") != null && request.getAttribute("userAccName") != "null") {
										%><%=request.getAttribute("userAccName") %><% 
									}
								%>"/>
								<div class="id_account">
									<div class="top_arr"></div>
									<div class="account_list">
										<div class="account_choose">��ѡ���õ�¼�ʺţ�</div>
										<div id="_account"></div>
										<!-- <div class="account_item"><input type="radio" name="account1"><label>dfds@1sina.com</label></div>
										<div class="account_item"><input type="radio" name="account1"><label>135****1111</label></div> -->
									</div>
								</div>
							</c:otherwise>
						</c:choose>
						<c:if test="${geUserPersonal == null }">
							<label for="password"  class="input_label">���룺</label>
						</c:if>
							<input class="input_field" type="password" name="j_password" id="password"/>
							<div class="action">
								<input name="remeberMe" id="remeberMe" type="checkbox"/>
								<label for="remember">��ס��</label>
								<a class="forget_password" href="${ctx }/web/user/resetPwd/index.jsp">�������룿</a>
								<a class="reg" href="${ctx }/web/user/register/index.jsp">����ע��</a>
							</div>
							<div class="login_submit">
								<button type="submit" class="login" onclick="return login();">������¼</button>
							</div>
							<div class="other_login">
								<p class="login_type alipay"><a href="${ctx}/web/user/login/alipayLogin/alipayapi.jsp">֧������¼</a></p>
								<p class="login_type weibo"><a href="${ctx}/login/sinaLogin.do">΢����¼</a></p>
								<p class="login_type tencent"><a href="${ctx}/oauth/qqoauth">QQ��¼</a></p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
