<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>���ղ�Ʒѡ�������ٱ��գ�Ͷ����ƣ��������գ������ա���̩���������̳�</title>
		<meta name="description" content="����������������ѡ���ղ�Ʒ������Ϊ���ṩͶ����ơ��������ա������յȲ�Ʒ��ѡ����" />
		<meta name="keywords" content="���ٱ��գ�Ͷ����ƣ��������գ������ա���ͨ���������գ������ϱ��ա�"/>
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
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/product']).send();
			$(".login_info .login").click(function(){
				_ga.push(['_trackEvent', '��Ʒ����', '��¼']).send();
			});
			$.each($(".quick_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', $(this).text()]).send();
				});
			});
			$(".logo").click(function(){
				_ga.push(['_trackEvent', '��Ʒ����', '��̩����logo']).send();
			});
			$.each($(".nav_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', '�����˵���: '+$(this).text()]).send();
				});
			});
			$(".nav_index").click(function(){
				_ga.push(['_trackEvent', '��Ʒ����', '������']).send();
			});
			$.each($(".product_map a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', '��Ʒ�����: '+$(this).text()]).send();
				});
			});
			$.each($(".product_container a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', '��Ʒ�Ƽ���: '+$(this).text()]).send();
				});
			});
			/*
			$.each($(".discount_recommend a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', '�Żݲ�Ʒ�Ƽ���: '+$(this).text()]).send();
				});
			});
			$.each($(".combo_recommend .combo_buy"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', '�ײ��Ƽ�����������']).send();
				});
			});
			*/
			$.each($(".product_list_main .buy"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', '������������������']).send();
				});
			});
			$.each($(".quote_tab .tab_item"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', '����ѡ�: '+$(this).text()]).send();
				});
			});  
			$(".action button").click(function(){
				_ga.push(['_trackEvent', '��Ʒ����', '������������']).send();
			});
			$(".product_display").click(function(){
				_ga.push(['_trackEvent', '��Ʒ����', '��Ʒ���λ']).send();
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