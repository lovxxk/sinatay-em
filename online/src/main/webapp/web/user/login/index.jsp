<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>��¼��Ա���ġ�����̩���������̳�</title>
		<meta name="Description" content="��¼��̩��Ա���ģ����ж���֧����������ѯ��������Ȳ�ѯ����������ȷ�����̩��Ա����Ϊ���ṩ���ݡ��ȫ���˻�����" />
		<meta name="Keywords" content="��Ա���� ����֧��,������ѯ,��������,������Ȳ�ѯ,�˻�����" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
		<link href="${ctx }/resources/css/user/login.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/user/login.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="login.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/login']).send();
			$(".logo").click(function(){
				_ga.push(['_trackEvent', '��Ա��¼', '��̩����logo']).send();
			});
			$("#remeberMe").click(function(){
				_ga.push(['_trackEvent', '��Ա��¼', '��ס��']).send();
			});
			$(".forget_password").click(function(){
				_ga.push(['_trackEvent', '��Ա��¼', '�������룿']).send();
			});
			$(".reg").click(function(){
				_ga.push(['_trackEvent', '��Ա��¼', '����ע��']).send();
			});
			$(".login_submit button").click(function(){
				_ga.push(['_trackEvent', '��Ա��¼', '������¼']).send();
			});
			$(".alipay").click(function(){
				_ga.push(['_trackEvent', '��Ա��¼', '֧������¼']).send();
			});
			$(".weibo").click(function(){
				_ga.push(['_trackEvent', '��Ա��¼', '΢����¼']).send();
			});
			$(".tencent").click(function(){
				_ga.push(['_trackEvent', '��Ա��¼', 'QQ��¼']).send();
			});
			_hm.push(['_trackPageview','/web/user/login']).send();
		</script>
	</body>
</html>
