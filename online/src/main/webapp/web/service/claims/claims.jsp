<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
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
					<div class="claims_process">
						<div class="left_img"></div>
						<div class="process_list">
							<div class="process tip">�¹ʷ����������뱣���ֳ�����ȡ��Ҫ�Ľ���ʩ�ȴ�ʩ��Ȼ���밴���²���������⣺</div>
							<div class="process p1">
								<p>�����¹ʺ������̲�ȡ���·���������</p>
								<p>1������24Сʱȫ���ͷ�����4006008890��<a href="${ctx}/web/service/hotline/index.jsp" class="high_light" target="_blank">�������ⱨ���绰��</a></p>
								<p>2��������˾���ػ����ͻ��������ı�����</p>
								<p>3����ϵ����Ӫ��Ա��ͨ������Ӫ��Ա��˾������</p>
								<p>4���ڱ���˾��������<a href="${ctx}/web/service/claimReport/index.jsp" class="high_light" target="_blank">���ⱨ����</a></p>
							</div>
							<div class="process p2">
								<p>��д���������飬<a href="${ctx}/dcenter/downloadCenterInit.do" class="high_light" target="_blank">�������������أ�</a></p>
								<p>׼��������ϣ�<a href="${ctx}/web/service/serviceClaimsStuff/index.jsp" class="high_light" target="_blank">����Ӧ������һ����</a></p>
								<p>�������������׼����ȫ�󣬿ɵ����ؿͷ������������⡣</p>
							</div>
							<div class="process p3">�����ύ�����������ʵ��ȫ������£��ҹ�˾���ݱ��պ�ͬ��Լ������صķ��ɷ�����б�������׼ȷ������ⰸ���ڲ���˹����������ݱ��պ�ͬ�������������</div>
							<div class="process p4">��������������뾭�˶����ڱ������εģ��ҹ�˾�����������˴�ɸ������ս��Э���ʮ���ڣ����и������ս�����Ϊʹ���������֧��˳�������ṩ��������/�����˱��˵��˺źͿ�������ϸ���ơ�</div>
							<div class="process p5">����᰸�󣬹�˾����24Сʱ���Զ��Ż�绰����ʽ֪ͨ�ͻ�������������Ա����᰸��ͬʱ���ڰ����������������ͻ��طã���ȡ�ͻ�������ͽ��飬��ȷ�����ս�ʱ�����֧����</div>
						</div>
					</div>
					<div class="service_promise">
						<div class="promise_label">
							<div class="p_block"></div>
							<div class="p_label">�����ŵ</div>
						</div>
						<div class="promise_item">
							<div class="promise pro1">24Сʱ����绰����</div>
							<div class="promise pro2">����ذ�ο�ʷ���</div>
							<div class="promise pro3">����ʱЧ��ŵ</div>
							<div class="promise pro4">����������</div>
							<div class="promise pro5">�᰸֪ͨ���ط÷���</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>