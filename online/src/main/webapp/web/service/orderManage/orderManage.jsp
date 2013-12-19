<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%--<jsp:include page="/web/service/common/service_map.jsp"></jsp:include>
				<jsp:include page="/web/service/common/call_service.jsp"></jsp:include>
				<jsp:include page="/web/service/common/interest_notice.jsp"></jsp:include> --%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">��������</li>
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
						<p>��������</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>�������ڹ�������Ĳ�Ʒ�Ķ�����Ϣ���������޸ĺ�֧��������</p>
						</div>
					</div>
					<p class="policy_process">��ͨ�����»����繺��ĸ��˱������������̣�</p>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">��ͨ���˺ŵ�¼��Ա���ģ�Ҳ����ͨ���������˺ŵ�¼��Ա����</p>
									<p class="link">
										<c:if test="${geUserPersonal != null }">
											<a href="${ctx }/memberCenter/homePage.do">��Ա����</a>						
										</c:if>
										<c:if test="${geUserPersonal == null }">
											<a href="${ctx }/web/user/login/index.jsp">������¼</a>									
										</c:if>
									</p>
								</div>
							</div>
						</div>
						<div class="process_step left my_order">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">��¼��Ա���ģ�ѡ���ҵĶ��������鿴δ�µ���δ֧���Ķ�����Ϣ</p>
									<p class="link">
										<a href="${ctx}/order/orders.do">�ҵĶ���</a>
									</p>
								</div>
							</div>
						</div>
						<div class="process_step right pay">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">�޸�Ͷ����Ϣ��ȷ��Ͷ����Ϣ���µ�ȥ����̨֧����</p>
									<p class="link">
										<a href="${ctx}/order/orders.do">����֧��</a>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>