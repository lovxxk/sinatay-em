<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>注册成为信泰会员--注册协议——信泰保险网上商城</title>
		<meta name="Description" content="注册协议，注册成为信泰会员，网上投保、保单查询、自助服务、理赔查询体验一站式保险服务。" />
		<meta name="Keywords" content="注册协议，注册,信泰会员,网上投保,保单查询,自助服务,理赔查询" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/resources/css/user/register_agree.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="agreement.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/register/agreement']).send();
			_hm.push(['_trackPageview','/web/user/register/agreement']).send();
		</script>
	</body>
</html>
