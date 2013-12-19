<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>信泰保险-客服中心-单证下载</title>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx }/global/css/global.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx }/resources/css/frame.css" rel="stylesheet" type="text/css"/>
		
		<script src="${ctx }/global/js/jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
		<script src="${ctx }/global/js/common/main.js" type="text/javascript"></script>
		<link href="${ctx }/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<script src="${ctx }/resources/js/frame.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		
		<link href="${ctx }/resources/css/service/service_download.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/service/service_download.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="service_download.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service/download']).send();
			_hm.push(['_trackPageview','/web/service/download']).send();
		</script>
	</body>
</html>
