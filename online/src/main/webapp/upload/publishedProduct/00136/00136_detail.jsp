<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<link href="${ctx }/resources/css/product/product_traffic.css" rel="stylesheet" type="text/css"/>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>"��ͨ���������ա����Ƿɻ����𳵣����������������������ͳ����ִ����Լݳ������ա���̩�����̳�"</title>
		<meta name="description" content="������С����¶ȼ١��ؼ�̽����ѡ������ѡ�񣬸����ˣ����ͼۣ���ȫ���У���̩��顣" />
		<meta name="keywords" content="��ͨ���� ������ ������� �ؼ�̽�� ���¶ȼ� �ɻ� ��"/>
</head>
<div class="detail_main detail">
	<div class="bottom_bg">
		<div class="content_bg">
			<div class="main_content">
			</div>
		</div>
	</div>
</div>
<div class="detail_main buy_notice">
	<div class="bottom_bg">
		<div class="content_bg">
			<div class="main_content">
				<div class="action p1"><a href="http://home.alipay.com/individual/tutorial.htm" target="_blank">֧����ʹ�ð���</a></div>
				<div class="action p2">���ֿ��ִ�������֧���޶�˽���࣬�뿴�����<a href="http://help.alipay.com/lab/help_detail.htm?help_id=211661" target="_blank">�����е���������֧���޶��ܱ�</a></div>
				<div class="action p3">��������ѡ�������   
					<a href="${ctx }/web/common/accidentInsuranceNotice/index.jsp" target="_blank">Ͷ����֪</a>  |  
					<a href="${ctx }/web/common/insuranceTipBook/index.jsp" target="_blank">Ͷ����ʾ��</a>  |  
					<a href="${ctx }/web/common/authorize/index.jsp" target="_blank">��Ȩ����</a>  |  
					<a href="javascript:getProductClause('1');">��Ʒ����</a>
<%-- 					<a class="more" href="${ctx}/web/service/problem/login/index.jsp">�������&gt;&gt;</a> --%>
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