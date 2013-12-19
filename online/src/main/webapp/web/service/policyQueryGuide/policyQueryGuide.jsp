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
				<li class="at">������ѯ</li>
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
						<p>������ѯ</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>��ѯ������̩����ı�����Ϣ��</p>
						</div>
					</div>
					<p class="policy_process">��ͨ��ҵ��Ա�����繺��ĸ��˱�����ͨ�����²������̽��в�ѯ��</p>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">ע��&gt;&gt;��ֻ��һ���������յ���֤�ŵ��ֻ�������</p>
									<p class="instro">��¼&gt;&gt;��ͨ���˺ŵ�¼��Ա���ģ�Ҳ����ͨ���������˺ŵ�¼��Ա����</p>
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
						<div class="process_step left fill_info">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">��¼��Ա���ĺ�����������ҳ�棬���Ƹ������ϣ�����Ͷ��</p>
									<p class="link">
										<a href="${ctx}/edit/editUserPersonal.do">��������</a>
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
									<p class="instro">���뱣���Ų�ѯ������ϸ��Ϣ</p>
								</div>
							</div>
						</div>
						<div class="process_step left cat_info">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">�ڱ�������ҳ�ɲ�ѯ�����Ļ�����Ϣ�����ѡ�������������˻���ֵ���⸶����Ϣ</p>
									<p class="link">
										<a href="${ctx}/info/initPolicyList.do">�ҵı���</a>
									</p>
								</div>
							</div>
						</div>
					</div>

					<div class="query_list">
						<div class="query_item group">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>���屣����ѯ</h4>
								<p>�ʺϲ�ѯ��λΪ�乺������屣�ա�</p>
							</div>
						</div>
						<div class="query_item mid self">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>��������ѯ</h4>
								<p>�ʺϲ�ѯ��̩�������տ���</p>
							</div>
						</div>
						<div class="query_item short">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>���������ղ�ѯ</h4>
								<p>�ʺϲ�ѯ�����ա������ա������ա�С���Ŵ��յȶ������������֡�</p>
							</div>
						</div>
						<div class="query_item tele">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>�������ձ�����ѯ</h4>
								<p>�ʺϲ�ѯ�绰���ո��˱�����Ϣ��</p>
							</div>
						</div>
						<div class="query_item mid passenger">
							<div class="query_icon"></div>
							<div class="query_content">
								<h4>�˿������ղ�ѯ</h4>
								<p>�ʺϲ�ѯ�绰���ո��˱�����Ϣ��</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>