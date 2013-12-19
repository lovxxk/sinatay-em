<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>定点医院—信泰保险客户服务</title>
		<meta name="description" content="查询信泰指定的理赔定点医院信息。" />
		<meta name="keywords" content="定点医院,理赔" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/user/alert_subscribe_success.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_new_policy.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_receive.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_amount_confirm.css" rel="stylesheet" type="text/css"/>
		
		<script src="${ctx}/global/js/jquery/jquery.checkbox.js" type="text/javascript"></script>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_hospital_query.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_hospital_query.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="serviceHospitalQuery.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service/hospitalQuery']).send();
			_hm.push(['_trackPageview','/web/service/hospitalQuery']).send();
		</script>
	</body>
</html>
