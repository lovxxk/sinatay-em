<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>注册成为信泰会员——信泰保险网上商城</title>
		<meta name="Description" content="注册成为信泰会员，网上投保、保单查询、自助服务、理赔查询体验一站式保险服务。" />
		<meta name="Keywords" content="注册,信泰会员,网上投保,保单查询,自助服务,理赔查询" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
		<script type="text/javascript" src="${ctx }/global/js/jquery/emailpop.js"></script>
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
		<link href="${ctx}/resources/css/user/register.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/register.js" type="text/javascript"></script>

		<link rel="stylesheet" type="text/css"  href="${ctx }/global/css/emailpop.css"></link>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="register.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/register']).send();
			$(".logo").click(function(){
				_ga.push(['_trackEvent', '会员注册', '信泰保险logo']).send();
			});
			$("#reg_btn").click(function(){
				_ga.push(['_trackEvent', '会员注册', '注册']).send();
			});
			$(".reg_login button").click(function(){
				_ga.push(['_trackEvent', '会员注册', '直接登录']).send();
			});
			$(".alipay").click(function(){
				_ga.push(['_trackEvent', '会员注册', '支付宝登录']).send();
			});
			$(".weibo").click(function(){
				_ga.push(['_trackEvent', '会员注册', '微博登录']).send();
			});
			$(".tencent").click(function(){
				_ga.push(['_trackEvent', '会员注册', 'QQ登录']).send();
			});
			_hm.push(['_trackPageview','/web/user/register']).send();
		</script>
	</body>
</html>
