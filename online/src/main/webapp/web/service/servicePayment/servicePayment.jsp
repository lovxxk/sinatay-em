<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">支付方式</li>
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
						<p>支付方式</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>我们为您提供了多种在线支付方式供您选择，方便您完成订单支付。 </p>
						</div>
					</div>
					<div class="payment_area">
						<div class="payment_type alipay">
							<div class="payment_title"></div>
							<div class="payment_instro">
								<p class="p1">推荐淘宝用户使用</p>
								<div class="alipay_content">
									<img src="${ctx }/resources/image/service/alipay_icon.png">
									<p>如果您已经拥有支付宝账户，可选择支付宝进行付款。</p>
									<p>如果您还不知道如何使用支付宝，请看看这里：<a href="http://home.alipay.com/individual/tutorial.htm" target="_blank">支付宝使用帮助</a></p>
								</div>
							</div>
						</div>
						<div class="payment_type bank">
							<div class="payment_title"></div>
							<div class="payment_instro">
								<p class="bank_content">选择网银支付，您的银行卡需要开通相应的在线业务。因各地银行政策不同，建议您在网上支付前拨打所在地银行电话，咨询该行可供网上支付的银行卡种类及开通手续。</p>
								<p class="p1">网银支付平台目前所支持的银行卡种列表如下：</p>
								<div class="bank_list">
									<img src="${ctx }/resources/image/bank/payment_boc.png">
									<img src="${ctx }/resources/image/bank/payment_icbc.png">
									<img src="${ctx }/resources/image/bank/payment_cmb.png">
									<img src="${ctx }/resources/image/bank/payment_ccb.png">
									<img src="${ctx }/resources/image/bank/payment_abc.png">
									<img src="${ctx }/resources/image/bank/payment_spdb.png">
									<img src="${ctx }/resources/image/bank/payment_cib.png">
									<img src="${ctx }/resources/image/bank/payment_cgb.png">
									<img src="${ctx }/resources/image/bank/payment_cmbc.png">
									<img src="${ctx }/resources/image/bank/payment_citic.png">
									<img src="${ctx }/resources/image/bank/payment_hzcb.png">
									<img src="${ctx }/resources/image/bank/payment_cebbank.png">
									<img src="${ctx }/resources/image/bank/payment_shbank.png">
									<img src="${ctx }/resources/image/bank/payment_nbbank.png">
									<img src="${ctx }/resources/image/bank/payment_spabank.png">
									<img src="${ctx }/resources/image/bank/payment_bjrcb.png">
									<img src="${ctx }/resources/image/bank/payment_fdb.png">
									<img src="${ctx }/resources/image/bank/payment_postgc.png">
								</div>
								<p class="p1">部分卡种存在在线支付限额，了解更多，请看看这里：<a href="http://help.alipay.com/lab/help_detail.htm?help_id=211661" target="_blank">各银行的网上银行支付限额总表</a></p>
							</div>
						</div>
					</div>
					<div class="payment_notice">
						<p><a href="http://help.alipay.com/lab/help_detail.htm?help_id=211661" target="_blank">了解更多</a></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>