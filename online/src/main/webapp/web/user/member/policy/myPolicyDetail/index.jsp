<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>我的保单-会员中心</title>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx}/global/css/global.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/my_policy_detail.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_receive.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_insurance_select.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_amount_confirm.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_subscribe_success.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_input_phone.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript">
			var ctx = '${ctx}';
		</script>
		
		<script type="text/javascript" src="${ctx}/resources/js/user/my_policy_detail.js"></script>
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="myPolicyDetail.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/member/policy/myPolicyDetail']).send();
			_hm.push(['_trackPageview','/web/user/member/policy/myPolicyDetail']).send();
		</script>
	</body>
</html>
