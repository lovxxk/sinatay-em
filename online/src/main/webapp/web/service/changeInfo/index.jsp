<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>变更信息—信泰保险客户服务</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<meta name="description" content="登录会员中心变更投保人、被保险人基本信息。" />
		<meta name="keywords" content="变更信息 会员中心 投保人基本信息 被保险人基本信息" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		
		<link href="${ctx}/global/css/global.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_changeinfo.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_changeinfo.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
	
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="serviceChangeInfo.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service/changeInfo']).send();
			_hm.push(['_trackPageview','/web/service/changeInfo']).send();
		</script>
	</body>
</html>
