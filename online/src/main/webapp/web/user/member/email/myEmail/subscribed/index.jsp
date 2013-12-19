<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>

<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>我的保单-会员中心</title>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
	
		<link href="${ctx}/global/css/global.css" rel="stylesheet" type="text/css"/>
		
		
		<script src="${ctx}/global/js/jquery/jquery.checkbox.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/user/member.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/member.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/user/my_email_subscribed.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/my_email_subscribed.js" type="text/javascript"></script>
		
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/user/alert_subscribe_success.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_input_phone.css" rel="stylesheet" type="text/css"/>
		
		<script src="${ctx}/global/js/My97DatePicker4.7/WdatePicker.js" type="text/javascript"></script>
		
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="subscribed.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/member/email/myEmail/subscribed']).send();
			_hm.push(['_trackPageview','/web/user/member/email/myEmail/subscribed']).send();
		</script>
	</body>
</html>
