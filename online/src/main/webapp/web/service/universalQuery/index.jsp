<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>���ʹ��桪��̩���տͻ�����</title>
		<meta name="description" content="ʵʱ��ѯ��̩���������ս���������Ϣ���˽�����˻����������" />
	<meta name="keywords" content="������ �������� ������Ʊ� ���ʲ�ѯ��" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script src="${ctx}/global/js/jquery/jquery.checkbox.js" type="text/javascript"></script>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/My97DatePicker4.7/WdatePicker.js" type="text/javascript"></script>
		
		
		<link href="${ctx}/resources/css/service/service_universal_query.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_universal_query.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/service/service_frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/service/service_frame.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="serviceUniversalQuery.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/service/universalQuery']).send();
			_hm.push(['_trackPageview','/web/service/universalQuery']).send();
		</script>
	</body>
</html>
