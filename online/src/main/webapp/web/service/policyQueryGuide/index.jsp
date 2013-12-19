<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>保单查询—信泰保险客户服务</title>
	<meta name="description" content="查询您在信泰购买的保单信息、理赔信息、保全记录。实时了解保单价值、保单收益信息。" />
	<meta name="keywords" content="保单查询 保单信息 理赔信息 保全记录 保单价值 保单收益" />
	<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_policy.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_policy.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="policyQueryGuide.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service/policyQueryGuide']).send();
			_hmpush(['_trackPageview','/web/service/policyQueryGuide']).send();
		</script>
	</body>
</html>
