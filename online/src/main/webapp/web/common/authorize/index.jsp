<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title></title>
		<meta name="Description" content="" />
		<meta name="Keywords" content="" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/resources/css/common/agreement.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="authorize.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/common/authorize']).send();
			_hm.push(['_trackPageview','/web/common/authorize']).send();
		</script>
	</body>
</html>
<script>
$('.nav_menu .nav_item').eq(1).addClass('select');
$('.nav_menu .nav_item').eq(1).siblings().removeClass('select');
</script>