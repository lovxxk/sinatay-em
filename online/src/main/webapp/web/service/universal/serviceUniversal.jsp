<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">万能险领取</li>
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
						<p>万能险领取</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>通过官网购买的宝利来（懒人理财宝）产品，可直接在官网领取保单收益。</p>
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
									<p class="instro">登录会员中心，选择“我的保单”，选择宝利来（懒人理财宝）保单</p>
									<p class="link">
										<a href="${ctx }/info/initPolicyList.do">我的保单</a>
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
									<p class="instro">输入要领取的金额，确认手续费等信息，确认提交</p>
								</div>
							</div>
						</div>
						<div class="process_step left query_money">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">领取的金额将打入您购买时使用的银行卡或者您填写的退保/续期账号里</p>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
</div>