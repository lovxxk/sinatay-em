<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">��������ȡ</li>
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
						<p>��������ȡ</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>ͨ����������ı�������������Ʊ�����Ʒ����ֱ���ڹ�����ȡ�������档</p>
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
									<p class="instro">������ʹ���˺ţ��ֻ������䣩�����֤���ߵ�������֧������QQ��΢������¼��Ա����</p>
									<p class="link">
										<c:if test="${geUserPersonal == null }">
											<a href="${ctx }/web/user/login/index.jsp">������¼</a>
										</c:if>
										<c:if test="${geUserPersonal != null }">
											<a href="${ctx }/memberCenter/homePage.do">��Ա����</a>
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
									<p class="instro">��¼��Ա���ģ�ѡ���ҵı�������ѡ��������������Ʊ�������</p>
									<p class="link">
										<a href="${ctx }/info/initPolicyList.do">�ҵı���</a>
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
									<p class="instro">����Ҫ��ȡ�Ľ�ȷ�������ѵ���Ϣ��ȷ���ύ</p>
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
									<p class="instro">��ȡ�Ľ�����������ʱʹ�õ����п���������д���˱�/�����˺���</p>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
</div>