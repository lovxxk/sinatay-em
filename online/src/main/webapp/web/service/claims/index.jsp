<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>理赔流程—信泰保险客户服务</title>
		<meta name="description" content="未您提供从出险报案、提交申请、理赔审核、理赔给付到理赔结案全流程信泰理赔流程指引。" />
		<meta name="keywords" content="理赔流程 理赔指引" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_claims_process.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_claims_process.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="claims.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service/claims']).send();
			_hm.push(['_trackPageview','/web/service/claims']).send();
		</script>
	</body>
</html>
