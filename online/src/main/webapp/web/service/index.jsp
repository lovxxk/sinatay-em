<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>Ͷ������������񡢲�ѯ�����������񡪡���̩���տͻ�����</title>
	<meta name="description" content="Ϊ���ṩ����Ͷ������������񡢲�ѯ�������������������ϲ�������ݡ���ʡ���ı���ʱ�䣡" />
	<meta name="keywords" content="Ͷ������������񡢲�ѯ�����������񡢷��������Ͷ������" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>��̩����-�ͷ�����</title>
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
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service']).send();
			$(".login_info .login").click(function(){
				_ga.push(['_trackEvent', '�ͷ�����', '��¼']).send();
			});
			$.each($(".quick_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '�ͷ�����', $(this).text()]).send();
				});
			});
			$(".search button").click(function(){
				_ga.push(['_trackEvent', '�ͷ�����', '��ѯ']).send();
			});
			$(".logo").click(function(){
				_ga.push(['_trackEvent', '�ͷ�����', '��̩����logo']).send();
			});
			$.each($(".service_map a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '�ͷ�����', '���������: '+$(this).text()]).send();
				});
			});
			$.each($(".dynamic_list a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '�ͷ�����', '��Ʒ����������: '+$(this).text()]).send();
				});
			});
			$.each($(".service_body .service"), function(i,n){
				var text = $(n).find("p:first-child").text();
				$.each($(n).find("a"), function(i,a){
					$(a).click(function(){
						_ga.push(['_trackEvent', '�ͷ�����', text + ': '+$(this).text()]).send();
					});
				});
			});
			$.each($(".activity_list .activity"), function(i,n){
				var text = $(n).find("p").text();
				$.each($(n).find("a"), function(i,a){
					$(a).click(function(){
						_ga.push(['_trackEvent', '�ͷ�����', '��Ƽ���: '+ text]).send();
					});
				});
			});
			_hm.push(['_trackPageview','/web/service']).send();
		</script>
	</body>
</html>
