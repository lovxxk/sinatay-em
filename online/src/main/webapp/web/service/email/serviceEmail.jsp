<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
<!-- 				<li class="at">���Ӻ���</li> -->
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
						<p>���Ӻ���</p>
					</div>
					<div class="service_question">
						<div class="whatis">
							<div class="question">ʲô�ǵ��Ӻ�����</div>
							<div class="answer">���������еĵ����˵������ǽ��ͻ���ʮ����֪ͨ�ຯ����������̩�����ϣ��ͻ�����ʱ��½������Ա�����˽����¼���ʷ������Ϣ��ͬʱ��̩���ջ���ͨ�������ʼ���ʽ���ͺ����ĵ��Ӱ���ͻ���һ�ֱ�����Ϣ����</div>
						</div>
						<div class="benefit">
							<div class="question">���Ӻ�����ʲô�ô���</div>
							<div class="answer">
								<p class="p1">��һʱ���˽⺯����Ϣ���������غ�</p>
								<p class="p2">��ʱ������ϲ�ѯ���¼���ʷ������Ϣ������ ����ʷ�����ط����Լ�������</p>
								<p class="p3">���Ӻ�����ɾ�����£���ʱ���ģ����ɴ��� ������ֽ���ź������˶���𿴵ķ���</p>
								<p class="p4">���Ӻ������ֽ���ź����ʼģ����Խ�ʡֽ�ţ�Ϊ�������������������Ȼ������һ����</p>
							</div>
						</div>
					</div>
					<p class="service_topic">��ο�ͨ���Ӻ�����</p>
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
						<div class="process_step right subscribe">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">��¼��Ա�����ڡ����Ӻ��������������ĵ������䣬�����������ȷ�����ĵ��Ӻ�����</p>
									<p class="link">
										<a href="${ctx }/email/myEmailSubscribe.do">���Ӻ���</a>
									</p>
								</div>
							</div>
						</div>
					</div>
					<p class="service_topic">֧����Щ���Ӻ������ͣ�</p>
					<p class="service_answer">�ֺ�֪ͨ�顢���汣�ս���ȡ֪ͨ�顢�����ո�����ȱ��桢���������ȡ֪ͨ�顢�Ե�֪ͨ�顢������Ԥ��ֹ֪ͨ�顣</p>
					
				</div>
			</div>
		</div>
	</div>
</div>