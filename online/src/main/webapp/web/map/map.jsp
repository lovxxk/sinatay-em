<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">	
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">��ҳ</a><span>&gt;</span></li>
				<li class="at">��վ��ͼ</li>
			</ul>
		</div>
		<div class="agree_body">
			<dl>
				<dt class="homepage">��ҳ</dt>
				<dd>
					<div class="outter">
						<ul>
							<li><a href="${ctx}/memberCenter/homePage.do" target="_blank">��Ա����</a></li>
							<li><a href="${ctx}/web/map/index.jsp" target="_blank">��վ��ͼ</a></li>
						</ul>
					</div>
					<div class="outter">
						<ul>
							<li><a href="http://www.sinatay.com/highlink/dxwt.jsp" target="_blank">������ר��</a></li>
							<li style="display:none"><a href="">��������</a></li>
						</ul>
					</div>
					<div class="outter">
						<ul>
							<li><a href="http://www.sinatay.com/selfservice/contactus.jsp" target="_blank">��ϵ����</a></li>
							<li><a href="http://www.sinatay.com/highlink/laws.jsp" target="_blank">��������</a></li>
						</ul>
					</div>
					<div class="outter">
						<ul>
							<li><a href="http://www.sinatay.com/intro/introduce.jsp" target="_blank">������̩</a></li>
							<li><a href="http://special.zhaopin.com/pagepublish/14658906/index.html" target="_blank">������̩</a></li>
						</ul>
					</div>
				</dd>
				
				<dt class="product_buy">��Ʒѡ��</dt>
				<dd>
					<div class="">
						<ul>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">�����</a></li>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">������Ʊ�</a></li>
						</ul>
					</div>
					<div class="">
						<ul>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">��ͨ��</a></li>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">���������˺���</a></li>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">��������������</a></li>
							<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">�����ͨ������</a></li>
						</ul>
					</div>
					<div class="">
						<ul><li class="long"><a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank">�������˽�ҳ������Ᵽ��</a></li></ul>
					</div>
					<div class="">
						<ul>
							<li><a href="" target="_blank">�ٶ���</a></li>
							<li><a href="" target="_blank">����������</a></li>
						</ul>
					</div>
				</dd>
				
				<dt class="service_center">��������</dt>
				<dd>
					<div class="">
						<ul>
							<li>
								<c:if test="${geUserPersonal != null }">
									<a href="${ctx}/info/initPolicyList.do" target="_blank">������ѯ</a>									
								</c:if>
								<c:if test="${geUserPersonal == null }">
									<a href="${ctx}/web/service/policyQueryGuide/index.jsp" target="_blank">������ѯ</a>									
								</c:if>
							<li>
								<c:if test="${geUserPersonal != null }">
									<a href="${ctx}/order/orders.do" target="_blank">������ѯ</a>											
								</c:if>
								<c:if test="${geUserPersonal == null }">
									<a href="${ctx}/web/service/orderManage/index.jsp" target="_blank">������ѯ</a>											
								</c:if></li>
							<li><a href="${ctx}/web/service/websiteQuery/index.jsp" target="_blank">������Ϣ</a></li>
							<li><a href="${ctx}/web/service/hospitalQuery/index.jsp" target="_blank">����ҽԺ</a></li>
							<li><a href="${ctx}/universal/initUniversalQuery.do" target="_blank">���ʲ�ѯ</a></li>
							<li><a href="${ctx }/web/service/problem/login/index.jsp" target="_blank">��������</a></li>
						</ul>
					</div>
					<div class="">
						<ul>
							<li><a href="${ctx}/web/service/changeInfo/index.jsp" target="_blank">�����Ϣ</a></li>
							<li><a href="${ctx}/dcenter/downloadCenterInit.do" target="_blank">��֤����</a></li>
							<li>
								<c:if test="${geUserPersonal != null }">
									<a href="${ctx}/email/myEmailSubscribe.do" target="_blank">���Ӻ���</a>											
								</c:if>
								<c:if test="${geUserPersonal == null }">
									<a href="${ctx }/web/service/email/index.jsp" target="_blank">���Ӻ���</a>											
								</c:if>
							</li>
							<li><a href="${ctx }/web/service/universal/index.jsp" target="_blank">��������ȡ</a></li>
						</ul>
					</div>
					<div class="">
						<ul>
							<li><a href="${ctx}/web/service/claimReport/index.jsp" target="_blank">���ⱨ��</a></li>
							<li>
								<c:if test="${geUserPersonal != null }">
									<a href="${ctx}/claims/initClaimProcess.do" target="_blank">�������</a>
								</c:if>
								<c:if test="${geUserPersonal == null }">
									<a href="#" onclick="sinosoft_login_dialog('${ctx}/claims/initClaimProcess.do');">�������</a>
									
								</c:if>
							</li>
							<li><a href="${ctx}/web/service/claims/index.jsp" target="_blank">��������</a></li>
							<li><a href="${ctx}/web/service/serviceClaimsStuff/index.jsp" target="_blank">�������</a></li>
							<li><a href="${ctx}/web/service/hotline/index.jsp" target="_blank">��������</a></li>
						</ul>
					</div>
					<div class="">
						<ul>
							<li><a href="${ctx}/order/orders.do" target="_blank">����֧��</a></li>
							<li><a href="${ctx}/web/service/epolicy/index.jsp" target="_blank">���ӱ���</a></li>
							<li><a href="${ctx }/web/service/serviceInsurance/index.jsp" target="_blank">Ͷ������</a></li>
							<li><a href="${ctx }/web/service/servicePayment/index.jsp" target="_blank">֧����ʽ</a></li>
						</ul>
					</div>
				</dd>
			</dl>
		</div>
	</div>
</div>