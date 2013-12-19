<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>订单管理—信泰保险客户服务</title>
	<meta name="description" content="登录会员中心管理您在网上商城购买的产品订单信息，继续投保或完成支付。" />
	<meta name="keywords" content="订单信息 会员中心 继续投保 完成支付。" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>信泰保险-客服中心</title>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_order.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_order.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="orderManage.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service/orderManage']).send();
			_hmpush(['_trackPageview','/web/service/orderManage']).send();
		</script>
	</body>
</html>
