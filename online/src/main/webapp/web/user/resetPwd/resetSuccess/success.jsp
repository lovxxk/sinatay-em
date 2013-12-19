<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
var intervalProcess = setInterval('counttime()',1000);
var timenum = 10;
function counttime() {
	if (timenum > 0) {
		$('.time').text(timenum);
		timenum = timenum - 1;
	} else {
		clearInterval(intervalProcess);
		window.location.href='${ctx }/web/user/login/index.jsp';
	}
}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li class="at">重置密码成功</li>
			</ul>
		</div>

		<div class="success_main">
			<div class="top"></div>
			<div class="success_result">
				<div class="tip_left"></div>
				<div class="info">
					<p class="info_top">恭喜您密码重置成功！</p>
					<p>请妥善保管好您的密码，不要随意泄漏给他人，谨防上当受骗。  系统在<span class="time"></span> 秒后自动跳转至登陆页面。</p>
					<div class="return_index">
						<button class="click_btn" onclick="javascript:window.location.href='${ctx }/web/user/login/index.jsp'">立即登录</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>