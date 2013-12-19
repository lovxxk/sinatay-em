<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>信泰保险-邮件发送成功</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
		<link href="${ctx}/resources/css/policy/paysuccess.css" rel="stylesheet" type="text/css"/>
        <link href="${ctx}/resources/css/user/mailsuccess.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/policy/paysuccess.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="success.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/mail/success']).send();
			_hm.push(['_trackPageview','/web/user/mail/success']).send();
		</script>
	</body>
</html>
