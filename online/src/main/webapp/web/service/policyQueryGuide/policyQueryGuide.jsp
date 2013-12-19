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
				<li class="at">保单查询</li>
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
						<p>保单查询</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>查询您在信泰购买的保单信息。</p>
						</div>
					</div>
					<p class="policy_process">您通过业务员或网络购买的个人保单可通过如下操作流程进行查询：</p>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">注册&gt;&gt;您只需一个可正常收到验证号的手机或邮箱</p>
									<p class="instro">登录&gt;&gt;可通过账号登录会员中心，也可以通过第三方账号登录会员中心</p>
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
						<div class="process_step left fill_info">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">登录会员中心后进入个人资料页面，完善个人资料，便于投保</p>
									<p class="link">
										<a href="${ctx}/edit/editUserPersonal.do">个人资料</a>
									</p>
								</div>
							</div>
						</div>
						<div class="process_step right enter_num">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">输入保单号查询保单详细信息</p>
								</div>
							</div>
						</div>
						<div class="process_step left cat_info">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">在保单详情页可查询保单的基本信息、交费、红利、生存金、账户价值、赔付等信息</p>
									<p class="link">
										<a href="${ctx}/info/initPolicyList.do">我的保单</a>
									</p>
								</div>
							</div>
						</div>
					</div>

					<div class="query_list">
						<div class="query_item group">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>团体保单查询</h4>
								<p>适合查询单位为其购买的团体保险。</p>
							</div>
						</div>
						<div class="query_item mid self">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>自助卡查询</h4>
								<p>适合查询信泰自助保险卡。</p>
							</div>
						</div>
						<div class="query_item short">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>短期意外险查询</h4>
								<p>适合查询航意险、旅游险、境外险、小额信贷险等短期意外类险种。</p>
							</div>
						</div>
						<div class="query_item tele">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>电销赠险保单查询</h4>
								<p>适合查询电话赠险个人保单信息。</p>
							</div>
						</div>
						<div class="query_item mid passenger">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>乘客意外险查询</h4>
								<p>适合查询电话赠险个人保单信息。</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>