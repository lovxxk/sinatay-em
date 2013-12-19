<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>保单查询—信泰保险客户服务</title>
		<meta name="description" content="查询您在信泰购买的保单信息、理赔信息、保全记录。实时了解保单价值、保单收益信息。" />
		<meta name="keywords" content="保单查询,保单信息,理赔信息,保全记录,保单价值,保单收益" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>我的保单-会员中心</title>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/user/alert_subscribe_success.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_new_policy.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_receive.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_amount_confirm.css" rel="stylesheet" type="text/css"/>
		
		<script src="${ctx}/global/js/jquery/jquery.checkbox.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/user/member.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/member.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/user/my_policy.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/my_policy.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="myPolicy.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/member/policy/myPolicy']).send();
			_hm.push(['_trackPageview','/web/user/member/policy/myPolicy']).send();
		</script>
	</body>
</html>
