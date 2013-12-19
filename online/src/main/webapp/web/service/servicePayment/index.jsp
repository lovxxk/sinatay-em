<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>投保服务、理赔服务、查询服务、自助服务——信泰保险客户服务</title>
	<meta name="description" content="为您提供网上投保服务、理赔服务、查询服务、在线自助服务，网上操作更便捷、节省您的宝贵时间！" />
	<meta name="keywords" content="投保服务,理赔服务,查询服务,自助服务,服务帮助,投保流程" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_payment.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_payment.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="servicePayment.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service/servicePayment']).send();
			_hmpush(['_trackPageview','/web/service/servicePayment']).send();
		</script>
	</body>
</html>
