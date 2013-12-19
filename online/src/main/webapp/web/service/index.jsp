<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>投保服务、理赔服务、查询服务、自助服务——信泰保险客户服务</title>
	<meta name="description" content="为您提供网上投保服务、理赔服务、查询服务、在线自助服务，网上操作更便捷、节省您的宝贵时间！" />
	<meta name="keywords" content="投保服务、理赔服务、查询服务、自助服务、服务帮助、投保流程" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>信泰保险-客服中心</title>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/alert_service.css" rel="stylesheet" type="text/css"/>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_center.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_center.js" type="text/javascript"></script>
		
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="serviceCenter.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service']).send();
			$(".login_info .login").click(function(){
				_ga.push(['_trackEvent', '客服中心', '登录']).send();
			});
			$.each($(".quick_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '客服中心', $(this).text()]).send();
				});
			});
			$(".search button").click(function(){
				_ga.push(['_trackEvent', '客服中心', '查询']).send();
			});
			$(".logo").click(function(){
				_ga.push(['_trackEvent', '客服中心', '信泰保险logo']).send();
			});
			$.each($(".service_map a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '客服中心', '服务类别栏: '+$(this).text()]).send();
				});
			});
			$.each($(".dynamic_list a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '客服中心', '产品销售排行栏: '+$(this).text()]).send();
				});
			});
			$.each($(".service_body .service"), function(i,n){
				var text = $(n).find("p:first-child").text();
				$.each($(n).find("a"), function(i,a){
					$(a).click(function(){
						_ga.push(['_trackEvent', '客服中心', text + ': '+$(this).text()]).send();
					});
				});
			});
			$.each($(".activity_list .activity"), function(i,n){
				var text = $(n).find("p").text();
				$.each($(n).find("a"), function(i,a){
					$(a).click(function(){
						_ga.push(['_trackEvent', '客服中心', '活动推荐栏: '+ text]).send();
					});
				});
			});
			_hm.push(['_trackPageview','/web/service']).send();
		</script>
	</body>
</html>
