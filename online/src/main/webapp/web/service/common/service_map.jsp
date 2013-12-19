<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/alert_service.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
<div class="service_map">
	<div class="category query">
		<div class="cate_name"><div></div>查询服务</div>
		<div class="link_list">
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/info/initPolicyList.do">保单查询</a>									
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="${ctx}/web/service/policyQueryGuide/index.jsp">保单查询</a>									
			</c:if>
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/order/orders.do">订单查询</a>											
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="${ctx}/web/service/orderManage/index.jsp">订单查询</a>											
			</c:if>
			<a href="${ctx}/web/service/websiteQuery/index.jsp">网点信息</a>
			<a href="${ctx}/web/service/hospitalQuery/index.jsp">定点医院</a>
			<!-- <a href="#">理赔进度</a>  -->
			<!-- <a href="#">常见问题</a>  -->
		</div>
	</div>
	<div class="category business">
		<div class="cate_name"><div></div>业务办理</div>
		<div class="link_list">
			
			<a href="${ctx}/web/service/changeInfo/index.jsp">变更信息</a>											
			
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/order/orders.do">订单管理</a>											
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="${ctx}/web/service/orderManage/index.jsp">订单管理</a>											
			</c:if>
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/email/myEmailSubscribe.do">电子函件</a>											
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="${ctx }/web/service/email/index.jsp">电子函件</a>											
			</c:if>
			
			<a href="${ctx }/web/service/universal/index.jsp">万能险领取</a>			
	
		</div>
	</div>
	<div class="category claims">
		<div class="cate_name"><div></div>理赔服务</div>
		<div class="link_list">
			<a href="${ctx}/web/service/claimReport/index.jsp">理赔报案</a>
			<c:if test="${geUserPersonal != null }">
				<a href="${ctx}/claims/initClaimProcess.do">理赔进度</a>
			</c:if>
			<c:if test="${geUserPersonal == null }">
				<a href="#" onclick="sinosoft_login_dialog('${ctx}/claims/initClaimProcess.do');">理赔进度</a>
				
			</c:if>
			
			<a href="${ctx}/web/service/claims/index.jsp">理赔流程</a>
			<a href="${ctx}/web/service/serviceClaims/index.jsp">理赔时效</a>
		</div>
	</div>
	<div class="category download">
		<div class="cate_name"><div></div>下载中心</div>
		<div class="link_list">
			<a href="${ctx}/dcenter/downloadCenterInit.do">单证下载</a>							
			<a href="${ctx}/web/service/epolicy/index.jsp">电子保单</a>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	//如果未登录点击会员中心的理赔进度,那么进入会员中心,打开登录弹出层
	if("${returnUrl}" !="")
		sinosoft_login_dialog("${returnUrl}");
})
</script>



