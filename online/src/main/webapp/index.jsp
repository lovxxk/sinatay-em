<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- QQ��ݵ�¼��֤�� -->
		<meta property="qc:admins" content="242574675514454146316141163757764165" />
		<!-- ����΢����ݵ�¼��֤�� -->
		<meta property="wb:webmaster" content="b7dc8546ff1a7778" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>��̩���������̳ǡ������Ϲ������ղ�Ʒ����ȫ�������ף�����ݡ����Żݣ�</title>
		<meta name="description" content="��̩���������̳�Ϊ���ṩ�����ʡ��ȫ�����ϱ��շ������ϲ�Ʒ��������ա��ٶ��ա��������ա������յȣ����ϱ��ղ�Ʒֱ�����۸�����ˣ��������ݣ��������רҵ�����ϱ���ƽ̨��" />
		<meta name="keywords" content="��̩���գ���̩���٣���̩���գ����ٱ��գ�����Ͷ�������ϱ���"/>
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/resources/css/index/index.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/index/index.js" type="text/javascript"></script>
	</head>
	<body onkeypress="return onReturn(event);" >
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="middle.jsp"></jsp:include>
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/online']).send();
			$(".login_info .login").click(function(){
				_ga.push(['_trackEvent', '��ҳ', '��¼']).send();
			});
			$.each($(".quick_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��ҳ', $(this).text()]).send();
				});
			});
			$.each($(".nav_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��ҳ', '�����˵���: '+$(this).text()]).send();
				});
			});
			$("#remeberMe").click(function(){
				_ga.push(['_trackEvent', '��ҳ', '��ס��']).send();
			});
			$(".forget_password").click(function(){
				_ga.push(['_trackEvent', '��ҳ', '�������룿']).send();
			});
			$(".reg").click(function(){
				_ga.push(['_trackEvent', '��ҳ', '����ע��']).send();
			});
			$(".login_submit button").click(function(){
				_ga.push(['_trackEvent', '��ҳ', '������¼']).send();
			});
			/*
			$.each($(".age_product"), function(i,n){
				var p = $(n).find("p");
				var text = $(p[0]).text();
				$.each($(n).find(".click_btn"), function(i,a){
					$(a).click(function(){
						_ga.push(['_trackEvent', '��ҳ', 'һ�����: '+ text]).send();
					});
				});
			});
			*/
			$.each($(".suit_product .product"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��ҳ', '�ʺ����Ĳ�Ʒ: '+ $(this).text()]).send();
				});
			});
			$.each($(".area_main .service_channel"), function(i,n){
				$.each($(n).find("a"), function(i,a){
					$(a).click(function(){
						_ga.push(['_trackEvent', '��ҳ', '���ٷ���ͨ��: '+ $(this).text()]).send();
					});
				});
			}); 
			_hm.push(['_trackPageview','/online']).send();
		</script>
	</body>
</html>
