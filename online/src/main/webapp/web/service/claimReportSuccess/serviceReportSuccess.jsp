<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">理赔报案</li>
			</ul>
		</div>
		<div class="success_main">
			<div class="top"></div>
			<div class="success_result">
				<div class="tip_left"></div>
				<div class="info">
					<p class="info_top">您的报案已经提交成功，我们会尽快为您处理。</p>
					<p>
						理赔信息可登录<a href="${ctx}/memberCenter/homePage.do">会员中心</a>后在我的理赔中查看。
					</p>
					<p>
						登录后可在客服中心的<a href="${ctx}/claims/initClaimProcess.do">理赔进度</a>查询中查看理赔进度。
					</p>
					<div class="return_index">
						<a href="#" class="click_btn">返回首页</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>