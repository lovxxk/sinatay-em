<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>支付方式—信泰保险客户服务</title>
	<meta name="description" content="了解信泰网上商城支持的支付方式、银行以及支付限额等信息。" />
	<meta name="keywords" content="支付方式 网上银行 银行列表 支付限额" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_insurance.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_insurance.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="serviceInsurance.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service/serviceInsurance']).send();
			_hmpush(['_trackPageview','/web/service/serviceInsurance']).send();
		</script>
	</body>
</html>
