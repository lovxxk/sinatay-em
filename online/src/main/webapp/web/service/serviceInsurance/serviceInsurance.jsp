<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">����Ͷ������</li>
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
						<p>�����̳�Ͷ������</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>ֻ���Ĳ�������ѧ������Ͷ����  </p>
						</div>
					</div>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">��ֻ��һ���ֻ�����������ַ�������ע�ᣬҲ����ͨ��QQ�˺š�֧������΢����ݵ�½��ʽ��</p>
									<p class="link"><a href="${ctx }/memberCenter/homePage.do">��Ա����</a></p>
								</div>
							</div>
						</div>
						<div class="process_step left choose">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">ͨ����Ʒѡ����ѡ���ʺ��Լ��Ĳ�Ʒ��<br/>��Ʒ���⣬��ͨ��ʵʱ�����߿ͷ����н����빵ͨ��</p>
									<p class="link"><a href="${ctx }/productsDisplay/onlineShop.do?parentAttrID=&topNum=2&attrID=ROOT">��Ʒѡ��</a></p>
								</div>
							</div>
						</div>
						<div class="process_step right fill">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">���������Ͷ������������дͶ����Ϣҳ�棻<br/>��д��ȷ��Ͷ���ˡ��������˼���������Ϣ�� </p>
								</div>
							</div>
						</div>
						<div class="process_step left pay">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">֧���������С�֧�������֧���ȶ��ַ�ʽ��<br/>�����Ա���Ĳ鿴���ҵı��������ɲ�ѯ��������ϸ�����</p>
									<p class="link"><a href="${ctx}/info/initPolicyList.do">�ҵı���</a></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>