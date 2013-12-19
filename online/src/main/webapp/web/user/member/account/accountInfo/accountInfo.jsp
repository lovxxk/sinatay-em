<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<link href="${ctx}/resources/css/user/my_policy_detail.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
var timenum = 0;
var intervalProcess;
function counttime() {
	timenum = $("#num").val();
	if (timenum > 0) {
		$('.time').text(timenum);
		$('#getCode').val('('+timenum+')秒后重新发送');
		
		$('#getCode').valiCodeDisable();
		
		timenum = timenum - 1;
		$("#num").attr("value", timenum);
	} else {
		$('#getCode').val('再次发送');
		$('#getCode').valiCodeEnable();
		clearInterval(intervalProcess);
		$("#num").attr("value", 120);
	}
}
function sendCode() {
	var updateType = $("#updateType").val();
	var parameter = "";
	if (updateType == 'email') {
		parameter = "邮箱地址";
	} else {
		parameter = "手机号码";
	}
	var customerName = $("#emailOrPhone").val();
	if (customerName == "") {
		Sinosoft.alert({
			contentStr: "请输入合法的" + parameter,
			width:480,
			okStr: '确定',
			cancelStr: '取消',
			okFunc:function(){
				doReceive(parameter, "", "");
			}
		});
		return;
	}
	if (updateType == 'mobilePhone') {
		//11位手机号是否合法
		var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(customerName);
		if (!regmobile) {
			Sinosoft.alert({
				contentStr: "请输入合法的" + parameter,
				width:480,
				okStr: '确定',
				cancelStr: '取消',
				okFunc:function() {
					doReceive(parameter, customerName, "");
				}
			});
			return false;
		} else {
			phoneFalg = true;
		}
	} else {
		//判断用户录入的是否是邮箱以及邮箱地址是否合法
		if (customerName.indexOf("@") > 0) {
			//用户录入的邮箱是否合法
			var regemail =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9|_|\_|\.]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(customerName);
			if(!regemail){
				Sinosoft.alert({
					contentStr: '请填写一个正确的' + parameter,
					width:480,
					okStr: '确定',
					cancelStr: '取消',
					okFunc:function(){
						doReceive(parameter, customerName, "");
					}
				});
				return false;
			} else {
				emailFalg = true;
			}
		} else {
			Sinosoft.alert({
				contentStr: '请填写一个正确的' + parameter,
				width:480,
				okStr: '确定',
				cancelStr: '取消',
				okFunc:function(){
					doReceive(parameter, customerName, "");
				}
			});
			return;
		}
	}
	
	//先校验手机号是否存在
	if (updateType == 'mobilePhone') {
		$.ajax({
			type : "POST",
			async : false,
			url : contextRootPath+"/edit/existCustomerByUserAccount.do",
			dataType : 'json',
			data:{
				value : customerName
			},
			success : function(data) {
				console.log(data.count);
				if (data.count != '' && data.count > 0) {
					Sinosoft.alert({
						contentStr : parameter+"已存在，不能绑定",
						width : 480,
						okStr : '确定',
						cancelStr : '取消',
						okFunc : function() {
							$("#num").attr("value", "120");
							clearInterval(intervalProcess);
							doReceive(parameter, customerName, '');
							return false;
						}
					});
				}else{
					$.ajax({
						url : '${ctx}/edit/getUserPersonalPhonePwd.do',
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
								$("#alertMessage").text("验证码已发送到" + mobileStr + ",请注意查收.");
								$("#validateCode").attr("readOnly", false);
								intervalProcess = setInterval('counttime()',1000);
							} else if (data == "limit") {
								Sinosoft.alert({
									contentStr: "非常抱歉，今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。",
									width:480,
									okStr: '确定',
									cancelBtnShow:false,//是否显示关闭按钮
									okFunc:function(){
										doReceive(parameter, customerName, "");
									}
								});
							}else {
								Sinosoft.alert({
									contentStr: "发送过于频繁，请稍后再试！",
									width:480,
									okStr: '确定',
									cancelBtnShow:false,//是否显示关闭按钮
									okFunc:function(){
										
									}
								});
							}
						}
					});
				}
			}
		});
	} else {
		$.ajax({
			type : "POST",
			async : false,
			url : contextRootPath+"/edit/existCustomerByUserAccount.do",
			dataType : 'json',
			data:{
				value : customerName
			},
			success : function(data) {
				console.log(data.count);
				if (data.count != '' && data.count > 0) {
					Sinosoft.alert({
						contentStr : parameter+"已存在，不能绑定",
						width : 480,
						okStr : '确定',
						cancelStr : '取消',
						okFunc : function() {
							doReceive(parameter, customerName, '');
							return false;
						}
					});
				}else{
					$.ajax({
						url : '${ctx}/edit/sendCodeByEmail.do',
						type : 'POST',
						async : false,
						data : "email=" + customerName,
						dataType : "text",
						success : function(data) {
							if (data == "success") {
								$("#alertMessage").text("验证码已发送到" + customerName + ",请注意查收.");
								$("#validateCode").attr("readOnly", false);
								intervalProcess = setInterval('counttime()',1000);
							} else {
								Sinosoft.alert({
									contentStr: "短信发送过于频繁，请稍后再试！",
									width:480,
									okStr: '确定',
									cancelBtnShow:false,//是否显示关闭按钮
									okFunc:function(){
										
									}
								});
							}
						}
					});
				}
			}
		});
	}
	
}
function loadReceiveInput(parameter, param, code) {
	var success = $('<div class="bind_info"><span id="alertMessage" style="color:red;"></span>'
			+ '<div class="bind_input"><label class="bind_label">请输入'
			+ parameter
			+ '：</label><input class="bind_text" type="text" name="emailOrPhone" id="emailOrPhone" maxlength=50 value="'+param+'"/></div>'
			+ '<div class="bind_input"><label class="bind_label">验证码：</label>'
			+ '<input class="bind_text vali" type="text" readonly="true" id="validateCode" maxlength=6 value="'+code+'"/>'
			+ '<input class="send_vali" type="button" id="getCode" onclick="sendCode();" value="发送验证码"/></div>'
			+ '</div>');
	return success;
}

function doAuthenticate(){
	var authTpl = '<ul style="" class="auth"><li>1、您可以网上投保来验证您的身份。<a hrf="">现在就去</a></li><li>2、完善个人信息，关联线下保单。<a href="">现在就去</a></li></ul>';
	new Sinosoft.InteractiveDialog({
		layout : authTpl,
		width : 490,
		cancelStr : '确定',
		okBtnShow : false,
		cancelFunc : function() {
		}
	}).open();
}

function doReceive(parameter, param, code){
		var receive_input = loadReceiveInput(parameter, param, code);
		var updateType = $("#updateType").val();

		var emailOrPhone = $("#receive_text").val();
		new Sinosoft.InteractiveDialog({
			layout : receive_input,
			width : 490,
			cancelStr : '确定',
			okBtnShow : false,
			cancelFunc : function() {
				var customerName = $("#emailOrPhone").val();
				if (customerName == "") {
					Sinosoft.alert({
						contentStr : "请输入合法的" + parameter,
						width : 480,
						okStr : '确定',
						cancelStr : '取消',
						okFunc : function() {
							doReceive(parameter, "", "");
						}
					});
					return;
				}
				if (updateType == 'mobilePhone') {
					//11位手机号是否合法
					var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(customerName);
					if (!regmobile) {
						Sinosoft.alert({
							contentStr : "请输入合法的" + parameter,
							width : 480,
							okStr : '确定',
							cancelStr : '取消',
							okFunc : function() {
								doReceive(parameter, customerName, "");
							}
						});
						return false;
					} else {
						phoneFalg = true;
					}
				} else {
					//判断用户录入的是否是邮箱以及邮箱地址是否合法
					if (customerName.indexOf("@") > 0) {
						//用户录入的邮箱是否合法
						var regemail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9|_|\_|\.]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/
								.test(customerName);
						if (!regemail) {
							Sinosoft.alert({
								contentStr : '请填写一个正确的' + parameter,
								width : 480,
								okStr : '确定',
								cancelStr : '取消',
								okFunc : function() {
										(parameter, customerName, "");
								}
							});
							return false;
						} else {
							emailFalg = true;
						}
					} else {
						Sinosoft.alert({
							contentStr : '请填写一个正确的' + parameter,
							width : 480,
							okStr : '确定',
							cancelStr : '取消',
							okFunc : function() {
								doReceive(parameter, customerName, "");
							}
						});
						return;
					}
				}

				var validateCode = $("#validateCode").val();
				if (validateCode == "") {
					Sinosoft.alert({
						contentStr : "请输入验证码",
						width : 480,
						okStr : '确定',
						cancelStr : '取消',
						okFunc : function() {
							doReceive(parameter, customerName, "");
						}
					});
					return;
				}
				var validateCodeFlag = "";
				$.ajax({
					type : "POST",
					async : false,
					url : "${ctx }/edit/checkEmailValidateCode.do",
					dataType : "text",
					data : {
						pwd : validateCode,
						customerName : customerName
					},
					success : function(data) {
						validateCodeFlag = data;
					}
				});
				if (validateCodeFlag == "paramError") {
					Sinosoft.alert({
						contentStr : "参数错误",
						width : 480,
						okStr : '确定',
						cancelStr : '取消',
						okFunc : function() {
							$("#num").attr("value", "120");
							clearInterval(intervalProcess);
							doReceive(parameter, customerName, "");
							return;
						}
					});
					return;
				} else if (validateCodeFlag == "error") {
					Sinosoft.alert({
						contentStr : "验证码输入错误",
						width : 480,
						okStr : '确定',
						cancelStr : '取消',
						okFunc : function() {
							$("#num").attr("value", "120");
							clearInterval(intervalProcess);
							doReceive(parameter, customerName, "");
							return;
						}
					});
					return;
				} else if (validateCodeFlag == "invalid") {
					Sinosoft.alert({
						contentStr : "验证码已失效，请重新获取验证码",
						width : 480,
						okStr : '确定',
						cancelStr : '取消',
						okFunc : function() {
							$("#num").attr("value", "120");
							clearInterval(intervalProcess);
							doReceive(parameter, customerName, "");
							return;
						}
					});
					return;
				}
				
				 if (updateType == 'mobilePhone') {
					if (!ExistCustomer("1", customerName)) {
						Sinosoft.alert({
							contentStr : "手机号码已存在，不能绑定",
							width : 480,
							okStr : '确定',
							cancelStr : '取消',
							okFunc : function() {
								$("#num").attr("value", "120");
								clearInterval(intervalProcess);
								doReceive(parameter, customerName, "");
								return false;
							}
						});
						return;
					}
				} else {
					if (!ExistCustomer("2", customerName)) {
						Sinosoft.alert({
							contentStr : "邮箱地址已存在，不能绑定",
							width : 480,
							okStr : '确定',
							cancelStr : '取消',
							okFunc : function() {
								$("#num").attr("value", "120");
								clearInterval(intervalProcess);
								doReceive(parameter, customerName, "");
								return false;
							}
						});
						return;
					}
				}
				
				$.ajax({
					url : '${ctx}/edit/supplyUserPersonal.do',
					type : 'POST',
					async : false,
					data : "param=" + customerName,
					dataType : "text",
					success : function(data) {
						if (data == 'success') {
							new Sinosoft.InteractiveDialog({
								layout : bindSuccess(),
								width:410,//自定义面板宽度-根据设计来调整
								cancelBtnShow:false,//是否显示关闭按钮
								okStr : '确认',
								cancelStr : '取消',
								okFunc:function(){
									window.location.href = "${ctx}/edit/accountInfo.do"
								}
							}).open();
						} else if(data == 'existsmobile'){
							Sinosoft.alert({
								contentStr: "该手机号码已被绑定！",
								width:480,
								okStr: '确定',
								cancelBtnShow:false,//是否显示关闭按钮
								okFunc:function(){
									$("#num").attr("value", "120");
									clearInterval(intervalProcess);
									doReceive(parameter, customerName, "");
								}
							});
						} else if (data == "existsEmail") {
							Sinosoft.alert({
								contentStr: "该邮箱地址已被绑定！",
								width:480,
								okStr: '确定',
								cancelBtnShow:false,//是否显示关闭按钮
								okFunc:function(){
									$("#num").attr("value", "120");
									clearInterval(intervalProcess);
									doReceive(parameter, customerName, "");
								}
							});
						} else {
							Sinosoft.alert({
								contentStr: "系统异常,请稍后重试",
								width:480,
								okStr: '确定',
								cancelBtnShow:false,//是否显示关闭按钮
								okFunc:function(){
									$("#num").attr("value", "120");
									clearInterval(intervalProcess);
								}
							});
						}
					}
				});
			}
		}).open();
	}
	

//校验手机号码是否已注册
//true，已注册;false，未注册
function ExistCustomer(index, customerName) {
	var msg = "邮箱";
	if(index == 1) 
		msg = "手机号";
	var flag = true;
	$.ajax({
		type : "POST",
		async : false,
		url : contextRootPath+"/edit/existCustomerByUserAccount.do",
		dataType : 'json',
		data:{
			value : customerName
		},
		success : function(data) {
			console.log(data.count);
			if (data.count != '' && data.count > 0) {
				Sinosoft.alert({
					contentStr : msg+"已存在，不能绑定",
					width : 480,
					okStr : '确定',
					cancelStr : '取消',
					okFunc : function() {
						$("#num").attr("value", "120");
						clearInterval(intervalProcess);
						doReceive(parameter, customerName, '');
						flag = true;
					}
				});
			}
		}
	});
	return flag;
}

function ExistCustomerEmail(customerName) {
	var flag = false;
	UserPersonalService.checkUserPersonalName(customerName, customerNameCallBack);
	function customerNameCallBack(data) {
		if (data) {
			flag = true;
		} else {
			flag = false;
		}
	}
	return flag;
}

function bindSuccess(){
	var subSuccess = $('<div class="alert_subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="sub_txt">绑定成功</div></div></div>');
	return subSuccess;
}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">首页</a><span> &gt;</span></li>
				<li><a href="#">会员中心</a><span> &gt;</span></li>
				<li class="at">帐号信息</li>
			</ul>
		</div>
		<div class="member_main">
			<input type="hidden" id="num" value="120">
			<input type="hidden" class="inputPwd"/>
			<c:if test="${fn:indexOf(customer.userAccount, '@') == -1}">
				<input type="hidden" id="updateType" value="email">
			</c:if>
			<c:if test="${fn:indexOf(customer.userAccount, '@') != -1}">
				<input type="hidden" id="updateType" value="mobilePhone">
			</c:if>
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<input type="hidden" name="id" id="id" value="${customer.userID }">
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>您的基础信息</p>
				</div>
				<div class="base_info">
					<p>
						<c:if test="${!empty customer.email }">
							<span class="info">
								<span class="label">登录邮箱：</span>
								<span class="value">${customer.email }</span>
							</span>
							<span>
								<c:if test="${fn:indexOf(customer.userAccount, '@') != -1}">
									<span class="bindEmail">
										注册名称不可修改
									</span>
								</c:if>
								<c:if test="${fn:indexOf(customer.userAccount, '@') == -1}">
									<span class="bindEmail">
										<a href="#" onclick="doReceive('邮箱地址', '', '');">修改邮箱</a>
									</span>
								</c:if>
							</span>
						</c:if>
						<c:if test="${empty customer.email }">
							<span class="info">
								<span class="label">邮箱地址：</span>
								<span class="none">暂无</span>
							</span>
							<span class="bindEmail">
								<a href="#" onclick="doReceive('邮箱地址', '', '');">绑定邮箱</a>
							</span>
						</c:if>
					</p>
					<p>
						<c:if test="${!empty customer.mobilePhone }">
							<span class="info">
								<span class="label">手机号码：</span>
								<span class="value">${customer.mobilePhone }</span>
							</span>
							<c:if test="${fn:indexOf(customer.userAccount, '@') != -1}">
								<span class="bindPhone">
									<a href="#" onclick="doReceive('手机号码','', '');">修改手机号</a>
								</span>
							</c:if>
							<c:if test="${fn:indexOf(customer.userAccount, '@') == -1}">
								<span class="bindPhone">
									注册帐号不可修改
								</span>
							</c:if>
						</c:if>
						<c:if test="${empty customer.mobilePhone }">
							<span class="info">
								<span class="label">手机号码：</span>
								<span class="none">暂无</span>
							</span>
							<span class="bindPhone">
								<a href="#" onclick="doReceive('手机号码', '', '');">绑定手机</a>
							</span>
						</c:if>
					</p>
					<p>
						<span class="info">
							<span class="label">上次登录：</span>
							<span class="value">
								<c:if test="${empty customer.lastLoginTime}">
									<fmt:formatDate value="${customer.currentLogintime }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</c:if>
								<c:if test="${!empty customer.lastLoginTime }">
									<fmt:formatDate value="${customer.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</c:if>
							</span>
						</span>
					</p>
				</div>
				<div class="title">
					<div class="block"></div>
					<p>您的安全服务</p>
				</div>
				<div class="security">
					<div class="security_item identity">
						<c:if test="${bindPolicy == true }">
							<div class="status finished">
								已完成
							</div>
						</c:if>
						<c:if test="${bindPolicy == false }">
							<div class="status unfinish">
								未完成
							</div>
						</c:if>
						<div class="name">身份认证</div>
						<div class="description">用于提升账号的安全性和信任级别。便于您网上投保和办理各项服务，
							认证后将不能修改认证信息</div>
						<div class="operation">
							<c:if test="${bindPolicy == false }">
								<a href="#" onclick="doAuthenticate();">认证</a>
							</c:if>
						</div>
					</div>
					<div class="security_item password">
							<span class="intensity">
							<c:choose>
								<c:when test="${customer.passwordGrade == '0' }">
									<div class="status low">
										强度：<font color="red">低</font>
									</div>
								</c:when>
								<c:when test="${customer.passwordGrade == '2' }">
									<div class="status high">
										强度：<font color="red">高</font>
									</div>
								</c:when>
								<c:otherwise>
									<div class="status mid">
										强度：<font color="red">中</font>
									</div>
								</c:otherwise>
							</c:choose>
						</span>
						<div class="name">登录密码</div>
						<div class="description">安全性高的密码可以使账号更安全。建议您定期更换密码，且设置一个
							包含数字和字母，并长度超过6位以上的密码。</div>
						<div class="operation">
							<a href="${ctx }/web/user/member/account/changePassword/index.jsp">修改</a>
						</div>
					</div>
					<div class="security_item cellphone">
						<c:if test="${empty customer.mobilePhone }">
							<div class="status unbind">
								未绑定
							</div>
						</c:if>
						<c:if test="${!empty customer.mobilePhone }">
							<div class="status bind">
								已绑定
							</div>
						</c:if>
						<div class="name">手机绑定</div>
						<div class="description">绑定手机后，您即可享受信泰丰富的手机服务，如手机登录、手机找回
							密码、手机验证等。</div>
						<div class="operation">
							<c:set var="userAccount" value="${customer.userAccount}"></c:set>
							<c:if test="${empty customer.mobilePhone}">
								<a href="#" onclick="doReceive('手机号码', '', '');">绑定</a>
							</c:if>
							<c:if test="${not fn:contains(userAccount,'@')}">
								<a href="#" onclick="">已绑定</a>
							</c:if> 
							
							<c:if test="${fn:contains(userAccount,'@') and  not empty customer.mobilePhone}">
								<a href="#" onclick="doReceive('手机号码', '', '');">修改</a>
							</c:if>
							
						</div>
					</div>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
