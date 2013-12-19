<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/alert_service.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
<div class="service_map">
	<div class="category query">
		<div class="cate_name"><div></div>��ѯ����</div>
		<div class="link_list">
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/info/initPolicyList.do">������ѯ</a>									
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="${ctx}/web/service/policyQueryGuide/index.jsp">������ѯ</a>									
			</c:if>
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/order/orders.do">������ѯ</a>											
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="${ctx}/web/service/orderManage/index.jsp">������ѯ</a>											
			</c:if>
			<a href="${ctx}/web/service/websiteQuery/index.jsp">������Ϣ</a>
			<a href="${ctx}/web/service/hospitalQuery/index.jsp">����ҽԺ</a>
			<!-- <a href="#">�������</a>  -->
			<!-- <a href="#">��������</a>  -->
		</div>
	</div>
	<div class="category business">
		<div class="cate_name"><div></div>ҵ�����</div>
		<div class="link_list">
			
			<a href="${ctx}/web/service/changeInfo/index.jsp">�����Ϣ</a>											
			
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/order/orders.do">��������</a>											
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="${ctx}/web/service/orderManage/index.jsp">��������</a>											
			</c:if>
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/email/myEmailSubscribe.do">���Ӻ���</a>											
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="${ctx }/web/service/email/index.jsp">���Ӻ���</a>											
			</c:if>
			
			<a href="${ctx }/web/service/universal/index.jsp">��������ȡ</a>			
	
		</div>
	</div>
	<div class="category claims">
		<div class="cate_name"><div></div>�������</div>
		<div class="link_list">
			<a href="${ctx}/web/service/claimReport/index.jsp">���ⱨ��</a>
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/claims/initClaimProcess.do">�������</a>
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="#" onclick="sinosoft_login_dialog('${ctx}/claims/initClaimProcess.do');">�������</a>
				
			</c:if>
			
			<a href="${ctx}/web/service/claims/index.jsp">��������</a>
			<a href="${ctx}/web/service/serviceClaims/index.jsp">����ʱЧ</a>
		</div>
	</div>
	<div class="category download">
		<div class="cate_name"><div></div>��������</div>
		<div class="link_list">
			<a href="${ctx}/dcenter/downloadCenterInit.do">��֤����</a>							
			<a href="${ctx}/web/service/epolicy/index.jsp">���ӱ���</a>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	//���δ��¼�����Ա���ĵ��������,��ô�����Ա����,�򿪵�¼������
	if("${returnUrl}" !="")
		sinosoft_login_dialog("${returnUrl}");
})
</script>



