<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<link href="${ctx }/resources/css/product/product_traffic.css" rel="stylesheet" type="text/css"/>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>"交通工具意外险—涵盖飞机、火车（动车、高铁、地铁）、客车、轮船、自驾车意外险—信泰网上商城"</title>
		<meta name="description" content="商务出行、蜜月度假、回家探亲首选。按需选择，更便宜，更低价，安全出行，信泰相伴。" />
		<meta name="keywords" content="交通工具 意外险 商务出行 回家探亲 蜜月度假 飞机 火车"/>
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
				<div class="action p1"><a href="http://home.alipay.com/individual/tutorial.htm" target="_blank">支付宝使用帮助</a></div>
				<div class="action p2">部分卡种存在在线支付限额，了解更多，请看看这里：<a href="http://help.alipay.com/lab/help_detail.htm?help_id=211661" target="_blank">各银行的网上银行支付限额总表</a></div>
				<div class="action p3">您还可以选择操作：   
					<a href="${ctx }/web/common/accidentInsuranceNotice/index.jsp" target="_blank">投保须知</a>  |  
					<a href="${ctx }/web/common/insuranceTipBook/index.jsp" target="_blank">投保提示书</a>  |  
					<a href="${ctx }/web/common/authorize/index.jsp" target="_blank">授权声明</a>  |  
					<a href="javascript:getProductClause('1');">产品条款</a>
<%-- 					<a class="more" href="${ctx}/web/service/problem/login/index.jsp">更多帮助&gt;&gt;</a> --%>
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