<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>个人资料-会员中心</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/user/member.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/member.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/user/alert_account_info.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/account_info.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/account_info.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="accountInfo.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/member/account/accountInfo']).send();
			_hm.push(['_trackPageview','/web/user/member/account/accountInfo']).send();
		</script>
	</body>
</html>
