<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>ע���Ϊ��̩��Ա������̩���������̳�</title>
		<meta name="Description" content="ע���Ϊ��̩��Ա������Ͷ����������ѯ���������������ѯ����һվʽ���շ���" />
		<meta name="Keywords" content="ע��,��̩��Ա,����Ͷ��,������ѯ,��������,�����ѯ" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
		<script type="text/javascript" src="${ctx }/global/js/jquery/emailpop.js"></script>
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
		<link href="${ctx}/resources/css/user/register.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/register.js" type="text/javascript"></script>

		<link rel="stylesheet" type="text/css"  href="${ctx }/global/css/emailpop.css"></link>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="register.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/register']).send();
			$(".logo").click(function(){
				_ga.push(['_trackEvent', '��Աע��', '��̩����logo']).send();
			});
			$("#reg_btn").click(function(){
				_ga.push(['_trackEvent', '��Աע��', 'ע��']).send();
			});
			$(".reg_login button").click(function(){
				_ga.push(['_trackEvent', '��Աע��', 'ֱ�ӵ�¼']).send();
			});
			$(".alipay").click(function(){
				_ga.push(['_trackEvent', '��Աע��', '֧������¼']).send();
			});
			$(".weibo").click(function(){
				_ga.push(['_trackEvent', '��Աע��', '΢����¼']).send();
			});
			$(".tencent").click(function(){
				_ga.push(['_trackEvent', '��Աע��', 'QQ��¼']).send();
			});
			_hm.push(['_trackPageview','/web/user/register']).send();
		</script>
	</body>
</html>
