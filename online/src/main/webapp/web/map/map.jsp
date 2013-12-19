<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">	
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">首页</a><span>&gt;</span></li>
				<li class="at">网站地图</li>
			</ul>
		</div>
		<div class="agree_body">
			<dl>
				<dt class="homepage">首页</dt>
				<dd>
					<div class="outter">
						<ul>
							<li><a href="${ctx}/memberCenter/homePage.do" target="_blank">会员中心</a></li>
							<li><a href="${ctx}/web/map/index.jsp" target="_blank">网站地图</a></li>
						</ul>
					</div>
					<div class="outter">
						<ul>
							<li><a href="http://www.sinatay.com/highlink/dxwt.jsp" target="_blank">代理人专区</a></li>
							<li style="display:none"><a href="">友情链接</a></li>
						</ul>
					</div>
					<div class="outter">
						<ul>
							<li><a href="http://www.sinatay.com/selfservice/contactus.jsp" target="_blank">联系我们</a></li>
							<li><a href="http://www.sinatay.com/highlink/laws.jsp" target="_blank">法律声明</a></li>
						</ul>
					</div>
					<div class="outter">
						<ul>
							<li><a href="http://www.sinatay.com/intro/introduce.jsp" target="_blank">关于信泰</a></li>
							<li><a href="http://special.zhaopin.com/pagepublish/14658906/index.html" target="_blank">加盟信泰</a></li>
						</ul>
					</div>
				</dd>
				
				<dt class="product_buy">产品选购</dt>
				<dd>
					<div class="">
						<ul>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">理财险</a></li>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">懒人理财宝</a></li>
						</ul>
					</div>
					<div class="">
						<ul>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">交通险</a></li>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">航空意外伤害险</a></li>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">客运汽车意外险</a></li>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">轨道交通意外险</a></li>
						</ul>
					</div>
					<div class="">
						<ul><li class="long"><a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank">百万身驾私家车主意外保险</a></li></ul>
					</div>
					<div class="">
						<ul>
							<li><a href="" target="_blank">少儿险</a></li>
							<li><a href="" target="_blank">健康无忧险</a></li>
						</ul>
					</div>
				</dd>
				
				<dt class="service_center">服务中心</dt>
				<dd>
					<div class="">
						<ul>
							<li>
								<c:if test="${geUserPersonal != null }">
									<a href="${ctx}/info/initPolicyList.do" target="_blank">保单查询</a>									
								</c:if>
								<c:if test="${geUserPersonal == null }">
									<a href="${ctx}/web/service/policyQueryGuide/index.jsp" target="_blank">保单查询</a>									
								</c:if>
							<li>
								<c:if test="${geUserPersonal != null }">
									<a href="${ctx}/order/orders.do" target="_blank">订单查询</a>											
								</c:if>
								<c:if test="${geUserPersonal == null }">
									<a href="${ctx}/web/service/orderManage/index.jsp" target="_blank">订单查询</a>											
								</c:if></li>
							<li><a href="${ctx}/web/service/websiteQuery/index.jsp" target="_blank">网点信息</a></li>
							<li><a href="${ctx}/web/service/hospitalQuery/index.jsp" target="_blank">定点医院</a></li>
							<li><a href="${ctx}/universal/initUniversalQuery.do" target="_blank">利率查询</a></li>
							<li><a href="${ctx }/web/service/problem/login/index.jsp" target="_blank">常见问题</a></li>
						</ul>
					</div>
					<div class="">
						<ul>
							<li><a href="${ctx}/web/service/changeInfo/index.jsp" target="_blank">变更信息</a></li>
							<li><a href="${ctx}/dcenter/downloadCenterInit.do" target="_blank">单证下载</a></li>
							<li>
								<c:if test="${geUserPersonal != null }">
									<a href="${ctx}/email/myEmailSubscribe.do" target="_blank">电子函件</a>											
								</c:if>
								<c:if test="${geUserPersonal == null }">
									<a href="${ctx }/web/service/email/index.jsp" target="_blank">电子函件</a>											
								</c:if>
							</li>
							<li><a href="${ctx }/web/service/universal/index.jsp" target="_blank">万能险领取</a></li>
						</ul>
					</div>
					<div class="">
						<ul>
							<li><a href="${ctx}/web/service/claimReport/index.jsp" target="_blank">理赔报案</a></li>
							<li>
								<c:if test="${geUserPersonal != null }">
									<a href="${ctx}/claims/initClaimProcess.do" target="_blank">理赔进度</a>
								</c:if>
								<c:if test="${geUserPersonal == null }">
									<a href="#" onclick="sinosoft_login_dialog('${ctx}/claims/initClaimProcess.do');">理赔进度</a>
									
								</c:if>
							</li>
							<li><a href="${ctx}/web/service/claims/index.jsp" target="_blank">理赔流程</a></li>
							<li><a href="${ctx}/web/service/serviceClaimsStuff/index.jsp" target="_blank">理赔材料</a></li>
							<li><a href="${ctx}/web/service/hotline/index.jsp" target="_blank">报案热线</a></li>
						</ul>
					</div>
					<div class="">
						<ul>
							<li><a href="${ctx}/order/orders.do" target="_blank">订单支付</a></li>
							<li><a href="${ctx}/web/service/epolicy/index.jsp" target="_blank">电子保单</a></li>
							<li><a href="${ctx }/web/service/serviceInsurance/index.jsp" target="_blank">投保流程</a></li>
							<li><a href="${ctx }/web/service/servicePayment/index.jsp" target="_blank">支付方式</a></li>
						</ul>
					</div>
				</dd>
			</dl>
		</div>
	</div>
</div>