<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="right_content">
	<jsp:include page="common/service_head.jsp"></jsp:include>
	<div class="customers">
		<div class="customer_tab">
			<div class="tab_item select">���˿ͻ�</div>
		</div>
		<div class="customer_main">
			<div class="service_body personal">
				<div class="service query">
					<div class="icon"></div>
					<div class="tip">
						<p class="p1">��ѯ����</p>
						<p class="p2">һ����ѯ��������ȷ</p>
					</div>
					<div class="link_list">
						<c:if test="${geUserPersonal != null }">
							<a href="${ctx}/info/initPolicyList.do">������ѯ</a>
						</c:if>
						<c:if test="${geUserPersonal == null }">
							<a href="${ctx}/web/service/policyQueryGuide/index.jsp">������ѯ</a>
						</c:if>
						|<a href="${ctx}/claims/initClaimProcess.do">�������</a> |<a
							href="${ctx}/web/service/websiteQuery/index.jsp">������Ϣ</a> |<a
							href="${ctx}/web/service/hospitalQuery/index.jsp">����ҽԺ��ѯ</a>
					</div>
				</div>
				<div class="service business right">
					<div class="icon"></div>
					<div class="tip">
						<p class="p1">ҵ�����</p>
						<p class="p2">���߲������򵥱��</p>
					</div>
					<div class="link_list">
						<a href="${ctx}/web/service/changeInfo/index.jsp">���������Ϣ</a>

						<c:if test="${geUserPersonal != null }">
										|<a href="${ctx}/email/myEmailSubscribe.do">���Ӻ���</a>
						</c:if>
						<c:if test="${geUserPersonal == null }">
										|<a href="${ctx}/web/service/email/index.jsp">���Ӻ���</a>
						</c:if>

						|<a href="${ctx}/web/service/universal/index.jsp">��������ȡ</a>

					</div>
				</div>
				<div class="service claims">
					<div class="icon"></div>
					<div class="tip">
						<p class="p1">�������</p>
						<p class="p2">�������⣬Ч������</p>
					</div>
					<div class="link_list">
						<a href="${ctx}/web/service/claims/index.jsp">��������</a> |<a
							href="${ctx}/web/service/serviceClaimsStuff/index.jsp">�������һ����</a>
						|<a href="${ctx}/claims/initClaimProcess.do">������Ȳ�ѯ</a> |<a
							href="${ctx}/web/service/hotline/index.jsp">����绰</a> |<a href="${ctx}/dcenter/downloadCenterInit.do">��֤����</a>
					</div>
				</div>
				<div class="service care right">
					<div class="icon"></div>
					<div class="tip">
						<p class="p1">Ͷ������</p>
						<p class="p2">����Ͷ��,�������</p>
					</div>
					<div class="link_list">
						<c:if test="${geUserPersonal != null }">
							<a href="${ctx}/order/orders.do">��������</a>
						</c:if>
						<c:if test="${geUserPersonal == null }">
							<a href="${ctx}/web/service/orderManage/index.jsp">��������</a>
						</c:if>
						|<a href="${ctx }/web/service/serviceInsurance/index.jsp">Ͷ������</a> |<a href="${ctx }/web/service/servicePayment/index.jsp">֧����ʽ</a> |<a
							href="${ctx}/web/service/epolicy/index.jsp">���ӱ���</a>
					</div>
				</div>
			</div>
			<div class="service_body group"></div>
		</div>
	</div>
	<div class="service_area activity_main">
		<div class="title">
			<p>������Ʒ�Ƽ�</p>
		</div>
		<div class="activity_list">
			<div class="activity left">
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank"><img
					src="${ctx}/resources/image/service/activity1.jpg"></a>			
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank"><b>
					�������-˽�ҳ������ر�ѡ��
				</b>
				</a>	
			</div>
			<div class="activity">
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank"><img
					src="${ctx}/resources/image/service/activity2.jpg"></a>
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank"><b>
					��̩��ͨ���������˺�����
				</b>
				</a>
			</div>
			<div class="activity right">
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><img
					src="${ctx}/resources/image/service/activity3.jpg">	</a>			
				<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><b>
					��̩������Ʊ�-Ԥ���껯������5.3%
				</b>
				</a>
			</div>
		</div>
	</div>
	<div class="service_area problem_main">
		<div class="title">
			<p>��������</p>
			<div class="more">
				<a href="${ctx}/web/service/problem/login/index.jsp">����&gt;&gt;</a>
			</div>
		</div>
		<div class="problem_list">
			<div class="problem">
				<ul>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=12">��λ���ҵĵ��ӱ���ƾ֤��</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=2">������α����</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=3">���������</a>
					</li>
				</ul>
			</div>
			<div class="problem">
				<ul>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=4">�Ƿ�����˱�����ΰ���</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=10">��ΰ�����ط���ͻ�ýɷѷ�Ʊ��</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/account/index.jsp?docindex=4">���Ӻ�����ʲô�ô���</a>
					</li>
				</ul>
			</div>
			<div class="problem">
				<ul>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=7">������⣿</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=8">���ӱ����Ƿ���кϷ��ķ���Ч����</a>
					</li>
					<li><span></span><a href="${ctx}/web/service/problem/policy/index.jsp?docindex=9">�����֤���ӱ�������α��</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>