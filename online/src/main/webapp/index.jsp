<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- QQ快捷登录验证码 -->
		<meta property="qc:admins" content="242574675514454146316141163757764165" />
		<!-- 新浪微博快捷登录验证码 -->
		<meta property="wb:webmaster" content="b7dc8546ff1a7778" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>信泰保险网上商城——网上购买寿险产品更安全，更容易，更便捷、更优惠！</title>
		<meta name="description" content="信泰保险网上商城为您提供最优质、最安全的网上保险服务。网上产品涵盖理财险、少儿险、人寿寿险、意外险等；网上保险产品直销，价格更便宜，服务更快捷！您身边最专业的网上保险平台。" />
		<meta name="keywords" content="信泰保险，信泰人寿，信泰寿险，人寿保险，网上投保，网上保险"/>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/resources/css/index/index.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/index/index.js" type="text/javascript"></script>
	</head>
	<body onkeypress="return onReturn(event);" >
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="middle.jsp"></jsp:include>
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/online']).send();
			$(".login_info .login").click(function(){
				_ga.push(['_trackEvent', '首页', '登录']).send();
			});
			$.each($(".quick_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '首页', $(this).text()]).send();
				});
			});
			$.each($(".nav_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '首页', '导航菜单栏: '+$(this).text()]).send();
				});
			});
			$("#remeberMe").click(function(){
				_ga.push(['_trackEvent', '首页', '记住我']).send();
			});
			$(".forget_password").click(function(){
				_ga.push(['_trackEvent', '首页', '忘记密码？']).send();
			});
			$(".reg").click(function(){
				_ga.push(['_trackEvent', '首页', '立即注册']).send();
			});
			$(".login_submit button").click(function(){
				_ga.push(['_trackEvent', '首页', '立即登录']).send();
			});
			/*
			$.each($(".age_product"), function(i,n){
				var p = $(n).find("p");
				var text = $(p[0]).text();
				$.each($(n).find(".click_btn"), function(i,a){
					$(a).click(function(){
						_ga.push(['_trackEvent', '首页', '一生相伴: '+ text]).send();
					});
				});
			});
			*/
			$.each($(".suit_product .product"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '首页', '适合您的产品: '+ $(this).text()]).send();
				});
			});
			$.each($(".area_main .service_channel"), function(i,n){
				$.each($(n).find("a"), function(i,a){
					$(a).click(function(){
						_ga.push(['_trackEvent', '首页', '快速服务通道: '+ $(this).text()]).send();
					});
				});
			}); 
			_hm.push(['_trackPageview','/online']).send();
		</script>
	</body>
</html>
