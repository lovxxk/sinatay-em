<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">理赔流程</li>
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
						<p>理赔流程</p>
					</div>
					<div class="claims_process">
						<div class="left_img"></div>
						<div class="process_list">
							<div class="process tip">事故发生后，首先请保护现场并采取必要的紧急施救措施，然后请按以下步骤进行理赔：</div>
							<div class="process p1">
								<p>发生事故后请立刻采取以下方法报案：</p>
								<p>1）拨打24小时全国客服热线4006008890或<a href="${ctx}/web/service/hotline/index.jsp" class="high_light" target="_blank">当地理赔报案电话；</a></p>
								<p>2）到本公司当地机构客户服务中心报案；</p>
								<p>3）联系保险营销员，通过保险营销员向公司报案；</p>
								<p>4）在本公司官网在线<a href="${ctx}/web/service/claimReport/index.jsp" class="high_light" target="_blank">理赔报案。</a></p>
							</div>
							<div class="process p2">
								<p>填写理赔申请书，<a href="${ctx}/dcenter/downloadCenterInit.do" class="high_light" target="_blank">理赔申请书下载；</a></p>
								<p>准备相关资料，<a href="${ctx}/web/service/serviceClaimsStuff/index.jsp" class="high_light" target="_blank">申请应备材料一览表；</a></p>
								<p>待您将相关资料准备齐全后，可到当地客服中心申请理赔。</p>
							</div>
							<div class="process p3">在您提交的索赔材料真实齐全的情况下，我公司根据保险合同的约定和相关的法律法规进行保险赔款的准确计算和赔案的内部审核工作，并依据保险合同做出理赔决定。</div>
							<div class="process p4">如果您的索赔申请经核定属于保险责任的，我公司会在与受益人达成给付保险金的协议后十日内，履行给付保险金义务。为使您的理赔款支付顺利，请提供被保险人/受益人本人的账号和开户行详细名称。</div>
							<div class="process p5">理赔结案后，公司会在24小时内以短信或电话的形式通知客户或其他服务人员办理结案。同时会在案件给付后进行受益客户回访，听取客户的意见和建议，并确保保险金及时、足额支付。</div>
						</div>
					</div>
					<div class="service_promise">
						<div class="promise_label">
							<div class="p_block"></div>
							<div class="p_label">服务承诺</div>
						</div>
						<div class="promise_item">
							<div class="promise pro1">24小时受理电话报案</div>
							<div class="promise pro2">理赔关爱慰问服务</div>
							<div class="promise pro3">理赔时效承诺</div>
							<div class="promise pro4">异地理赔服务</div>
							<div class="promise pro5">结案通知及回访服务</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>