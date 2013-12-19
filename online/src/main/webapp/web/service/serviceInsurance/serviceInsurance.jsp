<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">网上投保流程</li>
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
						<p>网上商城投保流程</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>只需四步，轻松学会网上投保。  </p>
						</div>
					</div>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">您只需一个手机号码或邮箱地址即可完成注册，也可以通过QQ账号、支付宝、微博快捷登陆方式。</p>
									<p class="link"><a href="${ctx }/memberCenter/homePage.do">会员中心</a></p>
								</div>
							</div>
						</div>
						<div class="process_step left choose">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">通过产品选购，选择适合自己的产品；<br/>产品问题，可通过实时与在线客服进行交流与沟通。</p>
									<p class="link"><a href="${ctx }/productsDisplay/onlineShop.do?parentAttrID=&topNum=2&attrID=ROOT">产品选购</a></p>
								</div>
							</div>
						</div>
						<div class="process_step right fill">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">点击“立即投保”，进入填写投保信息页面；<br/>填写并确认投保人、被保险人及受益人信息。 </p>
								</div>
							</div>
						</div>
						<div class="process_step left pay">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">支持网上银行、支付宝快捷支付等多种方式；<br/>进入会员中心查看“我的保单”，可查询保单的详细情况。</p>
									<p class="link"><a href="${ctx}/info/initPolicyList.do">我的保单</a></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>