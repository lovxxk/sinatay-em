<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<link href="${ctx }/resources/css/product/product_finance.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
<link href="${ctx}/resources/css/service/alert_service.css" rel="stylesheet" type="text/css"/>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>"投资理财—短期投资，1年期预期年化收益率5.3%—信泰保险网上商城"</title>
		<meta name="description" content="满足您的短期理财需求，1年期，预期年化收益率，有保底，无初始费用。全网独家：意外身故最高赔付账户价值200%，犹豫期免费退保。" />
		<meta name="keywords" content="投资理财 高收益理财产品 短期投资  稳健投资"/>
</head>
<div class="detail_main detail">
	<div class="bottom_bg">
		<div class="content_bg">
			<div class="main_content">
				<div class="interest_notice">
					<div class="view_interest click_btn"><a style="color: #fff;" href="${ctx}/universal/initUniversalQuery.do" target="_blank">查看往期结算利率</a></div>
					<div class="view_income click_btn" ><a style="color: #fff;" href="javascript:sinosoft_login_dialog('${ctx}/info/initPolicyList.do');">查看个人账户收益</a></div>
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
				<div class="action p1"><a href="http://help.alipay.com/lab/help_detail.htm?help_id=211661" target="_blank">了解更多</a></div>
				<div class="action p3">您还可以选择操作：   
					<a href="${ctx }/web/common/insuranceNotice/index.jsp" target="_blank">投保须知</a>  |  
					<a href="${ctx }/web/common/insuranceTipBook/index.jsp" target="_blank">投保提示书</a>  |  
					<a href="${ctx }/web/common/financialTipBook/index.jsp" target="_blank">产品说明书</a>  |  
					<a href="${ctx }/web/common/authorize/index.jsp" target="_blank">授权声明</a>  |  
					<a href="javascript:getProductClause('1');">产品条款</a>
<%-- 					<a class="more" href="${ctx}/web/service/problem/login/index.jsp" target="_blank">更多帮助&gt;&gt;</a> --%>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="detail_main policy">
	<div class="bottom_bg">
		<div class="content_bg">
			<div class="main_content">
				<div class="action policy_service">您还可以选择操作：   
					<c:if test="${geUserPersonal == null }">
						<a href="${ctx}/web/user/login/index.jsp">立即登录</a>  |  
						<a href="${ctx}/web/service/policyQueryGuide/index.jsp" target="_blank">保单查询</a>  |  
					</c:if>
					<c:if test="${geUserPersonal != null }"><a href="${ctx}/info/initPolicyList.do" target="_blank">保单查询</a>  |  </c:if>
					<a href="${ctx}/dcenter/downloadCenterInit.do" target="_blank">单证下载</a>
				</div>
				<div class="action get_money">您还可以选择操作：  
					<c:if test="${geUserPersonal == null }"><a href="${ctx}/web/user/login/index.jsp">立即登录</a>  |  </c:if>
					<a href="${ctx }/web/service/universal/index.jsp" target="_blank">万能险领取</a>
				</div>
				<div class="action claims_service">您还可以选择操作：   
					<c:if test="${geUserPersonal == null }"><a href="${ctx}/web/user/login/index.jsp">立即登录</a>  |  </c:if>
					<a href="${ctx}/web/service/claims/index.jsp" target="_blank">理赔流程</a>  |  
					<a href="${ctx}/web/service/serviceClaimsStuff/index.jsp" target="_blank">理赔材料</a>  |  
					<a href="${ctx}/web/service/claimReport/index.jsp" target="_blank">理赔报案</a>
				</div>
			</div>
		</div>
	</div>
</div>