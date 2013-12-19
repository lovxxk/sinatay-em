<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>��̩����-��Ա����</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/global/css/global.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/jquery/jquery.checkbox.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/main.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/util.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/frame.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/jquery/jquery.checkbox.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/user/alert_subscribe_success.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_new_policy.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_receive.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_amount_confirm.css" rel="stylesheet" type="text/css"/>
		
		
		<link href="${ctx}/resources/css/user/member.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/member.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/user/my_center.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/my_center.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="memberCenter.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/member']).send();
			$(".login_info .login").click(function(){
				_ga.push(['_trackEvent', '��Ա����', '��¼']).send();
			});
			$.each($(".quick_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ա����', $(this).text()]).send();
				});
			});
			$(".search button").click(function(){
				_ga.push(['_trackEvent', '��Ա����', '��ѯ']).send();
			});
			$(".logo").click(function(){
				_ga.push(['_trackEvent', '��Ա����', '��̩����logo']).send();
			});
			$.each($(".nav_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ա����', '�����˵���: '+$(this).text()]).send();
				});
			});
			$(".nav_index").click(function(){
				_ga.push(['_trackEvent', '��Ա����', '������']).send();
			});
			$(".fill_info").click(function(){
				_ga.push(['_trackEvent', '��Ա����', '���������Ϣ']).send();
			});
			$.each($(".events a"), function(i,n){
				$(n).click(function(){
					var text = $(this).text();
					text = text.substring(0,text.length-1);
					_ga.push(['_trackEvent', '��Ա����', text]).send();
				});
			});
			$.each($(".order_list .order_btn"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ա����', '�ҵĶ���: �鿴����']).send();
				});
			});
			$.each($(".policy_item .policy_bottom"), function(i,n){
				$.each($(n).find("a"), function(i,a){
					$(a).click(function(){
						_ga.push(['_trackEvent', '��Ա����', '�ҵı���: ' + $(this).text()]).send();
					});
				});
			});
			_hm.push(['_trackPageview','/web/user/member']).send();
		</script>
	</body>
</html>