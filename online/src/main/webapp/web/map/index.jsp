<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>网站地图——信泰网上商城</title>
		<meta name="Description" content="网站地图——信泰网上商城" />
		<meta name="Keywords" content="网站地图——信泰网上商城" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/resources/css/map/map.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="map.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/map']).send();
			_hm.push(['_trackPageview','/web/map']).send();
		</script>
	</body>
</html>
