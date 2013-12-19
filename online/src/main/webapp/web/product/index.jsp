<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>保险产品选购—人寿保险，投资理财，车主保险，意外险—信泰保险网上商城</title>
		<meta name="description" content="根据您的需求自主选择保险产品，我们为您提供投资理财、车主保险、意外险等产品的选购。" />
		<meta name="keywords" content="人寿保险，投资理财，车主保险，意外险、交通工具意外险，”网上保险“"/>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx }/resources/css/product/product_frame.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx }/resources/css/product/product_center.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/product/product_center.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="productCenter.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/product']).send();
			$(".login_info .login").click(function(){
				_ga.push(['_trackEvent', '产品中心', '登录']).send();
			});
			$.each($(".quick_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品中心', $(this).text()]).send();
				});
			});
			$(".logo").click(function(){
				_ga.push(['_trackEvent', '产品中心', '信泰保险logo']).send();
			});
			$.each($(".nav_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品中心', '导航菜单栏: '+$(this).text()]).send();
				});
			});
			$(".nav_index").click(function(){
				_ga.push(['_trackEvent', '产品中心', '导航栏']).send();
			});
			$.each($(".product_map a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品中心', '产品类别栏: '+$(this).text()]).send();
				});
			});
			$.each($(".product_container a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品中心', '产品推荐栏: '+$(this).text()]).send();
				});
			});
			/*
			$.each($(".discount_recommend a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品中心', '优惠产品推荐栏: '+$(this).text()]).send();
				});
			});
			$.each($(".combo_recommend .combo_buy"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品中心', '套餐推荐栏立即购买']).send();
				});
			});
			*/
			$.each($(".product_list_main .buy"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品中心', '热销排行栏立即购买']).send();
				});
			});
			$.each($(".quote_tab .tab_item"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '产品中心', '试算选项卡: '+$(this).text()]).send();
				});
			});  
			$(".action button").click(function(){
				_ga.push(['_trackEvent', '产品中心', '试算立即购买']).send();
			});
			$(".product_display").click(function(){
				_ga.push(['_trackEvent', '产品中心', '产品广告位']).send();
			});
			_hm.push(['_trackPageview','/web/product']).send();
		</script>
	</body>
</html>
<script>
$('.click_btn').click(function(){
	window.location.href = contextRootPath+"/sale/toQuote.do?eid=G120130902152737017&type="+$("#type").val()+"&day="+$("#day").val();
});
</script>