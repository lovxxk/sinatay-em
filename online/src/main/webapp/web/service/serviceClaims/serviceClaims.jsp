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
				<li class="at">理赔时效</li>
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
						<p>理赔时效</p>
					</div>
					<div class="claims_step">
						<div class="step less">
							<p>365×24小时接案</p>
						</div>
						<div class="step less">
							<p>24小时内专职理赔人员与您联系</p>
						</div>
						<div class="step">
							<p>资料齐全、事故责任明确无需调查的案件，5个工作日内结案，对理赔资料齐全但需要理赔调查的案件，在三十日内结案。</p>
						</div>
						<div class="step less">
							<p>结案后，24小时回访客户</p>
						</div>
					</div>
					<div class="clamis_service service1">
						<h4>理赔关爱慰问服务</h4>
						<p>接到客户住院的报案后，公司会派员到医院看望、慰问，同时就理赔相关事项做提醒或说明。</p>
					</div>
					<div class="clamis_service service2">
						<h4>全国通赔，理赔无限制</h4>
						<p>支持跨地域的理赔业务受理，理赔业务受理没有地域限制，客户可以在任何一个运营服务柜面享受同样高效 的理赔标准化服务。</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>