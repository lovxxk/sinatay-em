<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<link href="${ctx }/resources/css/product/product_finance.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
<link href="${ctx}/resources/css/service/alert_service.css" rel="stylesheet" type="text/css"/>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>"Ͷ����ơ�����Ͷ�ʣ�1����Ԥ���껯������5.3%����̩���������̳�"</title>
		<meta name="description" content="�������Ķ����������1���ڣ�Ԥ���껯�����ʣ��б��ף��޳�ʼ���á�ȫ�����ң������������⸶�˻���ֵ200%����ԥ������˱���" />
		<meta name="keywords" content="Ͷ����� ��������Ʋ�Ʒ ����Ͷ��  �Ƚ�Ͷ��"/>
</head>
<div class="detail_main detail">
	<div class="bottom_bg">
		<div class="content_bg">
			<div class="main_content">
				<div class="interest_notice">
					<div class="view_interest click_btn"><a style="color: #fff;" href="${ctx}/universal/initUniversalQuery.do" target="_blank">�鿴���ڽ�������</a></div>
					<div class="view_income click_btn" ><a style="color: #fff;" href="javascript:sinosoft_login_dialog('${ctx}/info/initPolicyList.do');">�鿴�����˻�����</a></div>
				</div>
			</div>
		</div>
	</div>
	<div class="finance_detail">
	</div>
</div>
<div class="detail_main buy_notice">
	<div class="bottom_bg">
		<div class="content_bg">
			<div class="main_content">
				<div class="action p1"><a href="http://help.alipay.com/lab/help_detail.htm?help_id=211661" target="_blank">�˽����</a></div>
				<div class="action p3">��������ѡ�������   
					<a href="${ctx }/web/common/insuranceNotice/index.jsp" target="_blank">Ͷ����֪</a>  |  
					<a href="${ctx }/web/common/insuranceTipBook/index.jsp" target="_blank">Ͷ����ʾ��</a>  |  
					<a href="${ctx }/web/common/financialTipBook/index.jsp" target="_blank">��Ʒ˵����</a>  |  
					<a href="${ctx }/web/common/authorize/index.jsp" target="_blank">��Ȩ����</a>  |  
					<a href="javascript:getProductClause('1');">��Ʒ����</a>
<%-- 					<a class="more" href="${ctx}/web/service/problem/login/index.jsp" target="_blank">�������&gt;&gt;</a> --%>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="detail_main policy">
	<div class="bottom_bg">
		<div class="content_bg">
			<div class="main_content">
				<div class="action policy_service">��������ѡ�������   
					<c:if test="${geUserPersonal == null }">
						<a href="${ctx}/web/user/login/index.jsp">������¼</a>  |  
						<a href="${ctx}/web/service/policyQueryGuide/index.jsp" target="_blank">������ѯ</a>  |  
					</c:if>
					<c:if test="${geUserPersonal != null }"><a href="${ctx}/info/initPolicyList.do" target="_blank">������ѯ</a>  |  </c:if>
					<a href="${ctx}/dcenter/downloadCenterInit.do" target="_blank">��֤����</a>
				</div>
				<div class="action get_money">��������ѡ�������  
					<c:if test="${geUserPersonal == null }"><a href="${ctx}/web/user/login/index.jsp">������¼</a>  |  </c:if>
					<a href="${ctx }/web/service/universal/index.jsp" target="_blank">��������ȡ</a>
				</div>
				<div class="action claims_service">��������ѡ�������   
					<c:if test="${geUserPersonal == null }"><a href="${ctx}/web/user/login/index.jsp">������¼</a>  |  </c:if>
					<a href="${ctx}/web/service/claims/index.jsp" target="_blank">��������</a>  |  
					<a href="${ctx}/web/service/serviceClaimsStuff/index.jsp" target="_blank">�������</a>  |  
					<a href="${ctx}/web/service/claimReport/index.jsp" target="_blank">���ⱨ��</a>
				</div>
			</div>
		</div>
	</div>
</div>