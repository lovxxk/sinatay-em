<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
var intervalProcess = setInterval('counttime()',1000);
var timenum = 5;
function counttime() {
	if (timenum > 0) {
		$('.time').text(timenum);
		timenum = timenum - 1;
	} else {
		clearInterval(intervalProcess);
		window.location.href='${ctx }/web/user/login/index.jsp?userAccount=${customer.userAccount}';
	}
}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li class="at">激活成功</li>
			</ul>
		</div>

		<div class="success_main">
			<div class="top"></div>
			<div class="success_result">
				<div class="tip_left"></div>
				<div class="info">
					<p class="info_top">恭喜你邮件激活成功！</p>
					<p>欢迎您成为信泰人寿的尊贵会员！</p>
					<p>
						您的用户名：<span>${customer.userAccount }</span>，请妥善保管好您的帐户资料，不要随意泄漏给他人，谨防上当受骗。
					</p>
					<p>
						系统在<span class="time"></span>秒后自动转至登录页面。
					</p>
					<div class="return_index">
						<button class="click_btn" onclick="javascript:window.location.href='${ctx }/web/user/login/index.jsp?userAccount=${customer.userAccount }'">立即登录</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>