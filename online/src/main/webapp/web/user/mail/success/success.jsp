<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>

<script type="text/javascript" src="${ctx}/global/js/jquery/jquery-1.5.2.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
<script type="text/javascript">
var intervalProcess = setInterval('counttime()',1000);
var timenum = 120;
function counttime() {
	if (timenum > 0) {
		$('.time').text(timenum);
		timenum = timenum - 1;
		document.getElementById("resetSend").disabled = true;
	} else {
		clearInterval(intervalProcess);
		document.getElementById("resetSend").disabled = false;
	}
}
function resetSendEmail() {
	var userAccount = $("#userAccount").val();
	$.ajax({
		url : '${ctx}/register/resetSendEmail.do',
		type : 'POST',
		async : false,
		data : {userAccount : userAccount },
		dataType : "text",
		success : function(data){
			if (data == "success") {
				intervalProcess = setInterval('counttime()',1000);
				Sinosoft.alert({
					contentStr: "激活邮件已重新发送，请注意查收",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
					}
				});
// 				alert("激活邮件已重新发送，请注意查收");
			} else {
				Sinosoft.alert({
					contentStr: "服务器繁忙，请稍后重试",
					width:480,
					okStr: '确定',
					cancelBtnShow:false,//是否显示关闭按钮
					okFunc:function(){
					}
				});
// 				alert("服务器繁忙，请稍后重试");
			}
		}
	});
}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">首页</a><span> &gt;</span></li>
				<li class="at">邮件发送</li>
			</ul>
		</div>
		<div class="success_main">
			<div class="top"></div>
			<div class="success_result">
				<div class="tip_left"></div>
				<div class="info">
					<input type="hidden" value="${customer.userAccount }" id="userAccount"/>
					<p class="info_top">
						验证邮件已发送到您的邮箱<span>${customer.userAccount }</span>，请注意查收！
					</p>
					<p>1.您可立即登录您的电子邮箱查收验证邮件。</p>
					<p>2.请在24小时内根据验证邮箱中的提示激活您注册的账号，若超过24小时仍未激活账号，请您再次进行注册。</p>
					<p>3.若未收到验证邮件，您可在<span class="time"></span>秒后点击<button class="return_index_mail click_btn" id="resetSend" disabled="disabled" onclick="resetSendEmail();">重新发送验证邮件</button>再次发送验证邮件。</p>
					<p>4.若您一直接收不到激活邮件，建议您稍后再试或更换其他电子邮箱进行注册。</p>
					<div class="return_index">
						<c:set var="email_login" value="${fn:substringAfter(customer.email,'@')}"></c:set>
						<button class="click_btn" onclick="javascript:window.open('http://mail.${fn:replace(email_login,'gmail','google')}')">去邮箱验证</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>