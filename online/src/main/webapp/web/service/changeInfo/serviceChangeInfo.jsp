<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">变更信息</li>
			</ul>
		</div>
		<div class="service_main">
			<div class="left_dynamic">
				<jsp:include page="/web/service/common/service_map.jsp"></jsp:include>
				<jsp:include page="/web/service/common/call_service.jsp"></jsp:include>
				<jsp:include page="/web/service/common/interest_notice.jsp"></jsp:include>
				<jsp:include page="/web/product/common/product_rank_left.jsp"></jsp:include>
			</div>
			<div class="right_content">
				<jsp:include page="../common/service_head.jsp"></jsp:include>
				<div class="service_area">
					<div class="title">
						<p>变更信息</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>您可以通过官网变更投、被保险人的基本信息。</p>
						</div>
					</div>
					<p class="policy_process">线上变更保单信息，3步完成：</p>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">您可以使用账号（手机、邮箱）、身份证或者第三方（支付宝、QQ、微博）登录会员中心</p>
									<p class="link">
										<c:if test="${geUserPersonal == null }">
											<a href="${ctx }/web/user/login/index.jsp">立即登录</a>
										</c:if>
										<c:if test="${geUserPersonal != null }">
											<a href="${ctx }/memberCenter/homePage.do">会员中心</a>
										</c:if>										
									</p>
								</div>
							</div>
						</div>
						<div class="process_step left my_policy">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">登录会员中心，选择“我的保单”，选择要修改信息的保单</p>
									<p class="link">
										<a href="${ctx }/info/initPolicyList.do">我的保单</a>
									</p>
								</div>
							</div>
						</div>
						<div class="process_step right change">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">可修改投保人、被保险人婚姻、国籍、驾照类型、职业、地址、手机、电话、邮箱等信息</p>
									<p class="instro extra">
										其他信息变更需要到<a href="${ctx}/web/service/websiteQuery/index.jsp">信泰人寿公司柜面</a>办理
									</p>
								</div>
							</div>
						</div>
					</div>

					
				</div>
			</div>
		</div>
	</div>
</div>