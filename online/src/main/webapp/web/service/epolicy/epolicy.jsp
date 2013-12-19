<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<% 
System.out.println(request.getContextPath());

%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx}/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">���ӱ���</li>
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
						<p>���ӱ���</p>
					</div>
					<div class="service_question">
						<div class="whatis">
							<div class="question">ʲô�ǵ��ӱ�����</div>
							<div class="answer">
								<p>��ͨ�����繺���ҹ�˾�Ĳ�Ʒ������Ϊ���ṩ���ӱ��������ӱ�����ֽ�ʱ�������ͬ�ȵķ���Ч����</p>
								<p>���ӱ�����Ȩ��������������֤������������������֤�ɷ����޹�˾��BJCA���ṩ����ǩ������������ǩ���ĵ��ӱ�������Ψһ�ԡ�����α����ص㣬���ҿ��Է�ֹ�������ݱ��Ƿ��۸ġ�</p></div>
						</div>
						<div class="benefit">
							<div class="question">���ӱ�������ʲô���ԣ�</div>
							<div class="answer">
							</div>
						</div>
					</div>
					<p class="service_topic">��λ���ҵĵ��ӱ����� </p>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">����ɹ������ǻᷢ��Ͷ���ɹ����ʼ����ʼ��а������ӱ��������ص�ַ</p>
									
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
									<p class="instro">��¼��Ա�����ڡ��ҵı������в鿴������������ص��ӱ���</p>
									<p class="link"><a href="${ctx}/info/initPolicyList.do">�ҵı���</a></p>
								</div>
							</div>
						</div>
					</div>
					<p class="service_topic">���ӱ��������֤? </p>
					<p class="service_answer">ͨ����վ <a href="http://etrust.bjca.org.cn/tdmm" target="_blank">http://etrust.bjca.org.cn/tdmm</a> ��֤���ӱ�������α����¼����վ���ϴ����ӱ�����������ɵ��ӱ�������֤��</p>
				</div>
			</div>
		</div>
	</div>
</div>
		