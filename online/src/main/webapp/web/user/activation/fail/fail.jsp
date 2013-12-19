<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li class="at">激活失败</li>
			</ul>
		</div>

		<div class="success_main">
			<div class="top"></div>
			<div class="success_result">
				<div class="tip_left"></div>
				<div class="info">
					<p class="info_top">注册账号激活失败！</p>
					<p>失败原因如下：</p>
					<p>
						1.您在24小时内未根据验证邮箱中的提示激活您注册的账号，您可以选择<span><a href="#">重新发送</a></span>
					</p>
					<p>2.您填写的收件邮箱地址错误，导致无法正常接收邮件，建议您更换其他电子邮箱进行注册。</p>
					<div class="return_index">
						<button class="click_btn">重新发送</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>