<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>信泰保险-产品列表</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx }/resources/css/product/product_frame.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx }/resources/css/product/product_list.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/product/product_list.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="productList.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
		/*
			_ga.push(['_trackPageview','/web/product/onlineShop']).send();
			$(".login_info .login").click(function(){
				_ga.push(['_trackEvent', '产品列表', '登录']).send();
			});
			$.each($(".quick_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品列表', $(this).text()]).send();
				});
			});
			$(".product_content .buy").click(function(){
				_ga.push(['_trackEvent', '产品列表', '立即购买']).send();
			});
			$.each($(".product_map a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品列表', '产品类别栏: '+$(this).text()]).send();
				});
			});
			$.each($(".product_container a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品列表', '产品推荐栏: '+$(this).text()]).send();
				});
			});
			$.each($(".dynamic_list a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品列表', '产品销售排行栏: '+$(this).text()]).send();
				});
			});
			$(".display").click(function(){
				_ga.push(['_trackEvent', '产品列表', '广告位']).send();
			});
			_hm.push(['_trackPageview','/web/product/onlineShop']).send();
			*/
		</script>
	</body>
</html>
<script>
</script>