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
				<li class="at">����ʱЧ</li>
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
						<p>����ʱЧ</p>
					</div>
					<div class="claims_step">
						<div class="step less">
							<p>365��24Сʱ�Ӱ�</p>
						</div>
						<div class="step less">
							<p>24Сʱ��רְ������Ա������ϵ</p>
						</div>
						<div class="step">
							<p>������ȫ���¹�������ȷ�������İ�����5���������ڽ᰸��������������ȫ����Ҫ�������İ���������ʮ���ڽ᰸��</p>
						</div>
						<div class="step less">
							<p>�᰸��24Сʱ�طÿͻ�</p>
						</div>
					</div>
					<div class="clamis_service service1">
						<h4>����ذ�ο�ʷ���</h4>
						<p>�ӵ��ͻ�סԺ�ı����󣬹�˾����Ա��ҽԺ������ο�ʣ�ͬʱ������������������ѻ�˵����</p>
					</div>
					<div class="clamis_service service2">
						<h4>ȫ��ͨ�⣬����������</h4>
						<p>֧�ֿ���������ҵ����������ҵ������û�е������ƣ��ͻ��������κ�һ����Ӫ�����������ͬ����Ч �������׼������</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>