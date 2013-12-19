<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>登录会员中心——信泰保险网上商城</title>
		<meta name="Description" content="登录信泰会员中心，进行订单支付、保单查询、理赔进度查询、自助服务等服务，信泰会员中心为您提供最便捷、最安全的账户管理。" />
		<meta name="Keywords" content="会员中心 订单支付,保单查询,自助服务,理赔进度查询,账户管理" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
		<link href="${ctx }/resources/css/user/login.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/user/login.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="login.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/login']).send();
			$(".logo").click(function(){
				_ga.push(['_trackEvent', '会员登录', '信泰保险logo']).send();
			});
			$("#remeberMe").click(function(){
				_ga.push(['_trackEvent', '会员登录', '记住我']).send();
			});
			$(".forget_password").click(function(){
				_ga.push(['_trackEvent', '会员登录', '忘记密码？']).send();
			});
			$(".reg").click(function(){
				_ga.push(['_trackEvent', '会员登录', '立即注册']).send();
			});
			$(".login_submit button").click(function(){
				_ga.push(['_trackEvent', '会员登录', '立即登录']).send();
			});
			$(".alipay").click(function(){
				_ga.push(['_trackEvent', '会员登录', '支付宝登录']).send();
			});
			$(".weibo").click(function(){
				_ga.push(['_trackEvent', '会员登录', '微博登录']).send();
			});
			$(".tencent").click(function(){
				_ga.push(['_trackEvent', '会员登录', 'QQ登录']).send();
			});
			_hm.push(['_trackPageview','/web/user/login']).send();
		</script>
	</body>
</html>
