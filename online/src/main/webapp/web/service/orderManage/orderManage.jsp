<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%--<jsp:include page="/web/service/common/service_map.jsp"></jsp:include>
				<jsp:include page="/web/service/common/call_service.jsp"></jsp:include>
				<jsp:include page="/web/service/common/interest_notice.jsp"></jsp:include> --%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">订单管理</li>
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
						<p>订单管理</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>管理您在官网购买的产品的订单信息，方便您修改和支付订单。</p>
						</div>
					</div>
					<p class="policy_process">您通过线下或网络购买的个人保单，操作流程：</p>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">可通过账号登录会员中心，也可以通过第三方账号登录会员中心</p>
									<p class="link">
										<c:if test="${geUserPersonal != null }">
											<a href="${ctx }/memberCenter/homePage.do">会员中心</a>						
										</c:if>
										<c:if test="${geUserPersonal == null }">
											<a href="${ctx }/web/user/login/index.jsp">立即登录</a>									
										</c:if>
									</p>
								</div>
							</div>
						</div>
						<div class="process_step left my_order">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">登录会员中心，选择“我的订单”，查看未下单和未支付的订单信息</p>
									<p class="link">
										<a href="${ctx}/order/orders.do">我的订单</a>
									</p>
								</div>
							</div>
						</div>
						<div class="process_step right pay">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">修改投保信息，确认投保信息，下单去收银台支付。</p>
									<p class="link">
										<a href="${ctx}/order/orders.do">立即支付</a>
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