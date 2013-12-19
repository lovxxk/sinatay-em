<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="right_content">
	<jsp:include page="common/service_head.jsp"></jsp:include>
	<div class="customers">
		<div class="customer_tab">
			<div class="tab_item select">个人客户</div>
		</div>
		<div class="customer_main">
			<div class="service_body personal">
				<div class="service query">
					<div class="icon"></div>
					<div class="tip">
						<p class="p1">查询服务</p>
						<p class="p2">一键查询，清晰明确</p>
					</div>
					<div class="link_list">
						<c:if test="${geUserPersonal != null }">
							<a href="${ctx}/info/initPolicyList.do">保单查询</a>
						</c:if>
						<c:if test="${geUserPersonal == null }">
							<a href="${ctx}/web/service/policyQueryGuide/index.jsp">保单查询</a>
						</c:if>
						|<a href="${ctx}/claims/initClaimProcess.do">理赔进度</a> |<a
							href="${ctx}/web/service/websiteQuery/index.jsp">网点信息</a> |<a
							href="${ctx}/web/service/hospitalQuery/index.jsp">定点医院查询</a>
					</div>
				</div>
				<div class="service business right">
					<div class="icon"></div>
					<div class="tip">
						<p class="p1">业务办理</p>
						<p class="p2">在线操作，简单便捷</p>
					</div>
					<div class="link_list">
						<a href="${ctx}/web/service/changeInfo/index.jsp">变更个人信息</a>

						<c:if test="${geUserPersonal != null }">
										|<a href="${ctx}/email/myEmailSubscribe.do">电子函件</a>
						</c:if>
						<c:if test="${geUserPersonal == null }">
										|<a href="${ctx}/web/service/email/index.jsp">电子函件</a>
						</c:if>

						|<a href="${ctx}/web/service/universal/index.jsp">万能险领取</a>

					</div>
				</div>
				<div class="service claims">
					<div class="icon"></div>
					<div class="tip">
						<p class="p1">理赔服务</p>
						<p class="p2">自助理赔，效率提升</p>
					</div>
					<div class="link_list">
						<a href="${ctx}/web/service/claims/index.jsp">理赔流程</a> |<a
							href="${ctx}/web/service/serviceClaimsStuff/index.jsp">理赔材料一览表</a>
						|<a href="${ctx}/claims/initClaimProcess.do">理赔进度查询</a> |<a
							href="${ctx}/web/service/hotline/index.jsp">理赔电话</a> |<a href="${ctx}/dcenter/downloadCenterInit.do">单证下载</a>
					</div>
				</div>
				<div class="service care right">
					<div class="icon"></div>
					<div class="tip">
						<p class="p1">投保服务</p>
						<p class="p2">在线投保,轻松理财</p>
					</div>
					<div class="link_list">
						<c:if test="${geUserPersonal != null }">
							<a href="${ctx}/order/orders.do">订单管理</a>
						</c:if>
						<c:if test="${geUserPersonal == null }">
							<a href="${ctx}/web/service/orderManage/index.jsp">订单管理</a>
						</c:if>
						|<a href="${ctx }/web/service/serviceInsurance/index.jsp">投保流程</a> |<a href="${ctx }/web/service/servicePayment/index.jsp">支付方式</a> |<a
							href="${ctx}/web/service/epolicy/index.jsp">电子保单</a>
					</div>
				</div>
			</div>
			<div class="service_body group"></div>
		</div>
	</div>
	<div class="service_area activity_main">
		<div class="title">
			<p>热销商品推荐</p>
		</div>
		<div class="activity_list">
			<div class="activity left">
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank"><img
					src="${ctx}/resources/image/service/activity1.jpg"></a>			
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank"><b>
					百万身驾-私家车车主必备选择
				</b>
				</a>	
			</div>
			<div class="activity">
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank"><img
					src="${ctx}/resources/image/service/activity2.jpg"></a>
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank"><b>
					信泰交通工具意外伤害保险
				</b>
				</a>
			</div>
			<div class="activity right">
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><img
					src="${ctx}/resources/image/service/activity3.jpg">	</a>			
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><b>
					信泰懒人理财宝-预期年化收益率5.3%
				</b>
				</a>
			</div>
		</div>
	</div>
	<div class="service_area problem_main">
		<div class="title">
			<p>常见问题</p>
			<div class="more">
				<a href="${ctx}/web/service/problem/login/index.jsp">更多&gt;&gt;</a>
			</div>
		</div>
		<div class="problem_list">
			<div class="problem">
				<ul>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=12">如何获得我的电子保险凭证？</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=2">保单如何变更？</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=3">如何续保？</a>
					</li>
				</ul>
			</div>
			<div class="problem">
				<ul>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=4">是否可以退保？如何办理？</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=10">如何办理相关服务和获得缴费发票？</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/account/index.jsp?docindex=4">电子函件有什么好处？</a>
					</li>
				</ul>
			</div>
			<div class="problem">
				<ul>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=7">如何理赔？</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=8">电子保单是否具有合法的法律效力？</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=9">如何验证电子保单的真伪？</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>