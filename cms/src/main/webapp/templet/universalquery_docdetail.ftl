<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>��̩����-�ͷ�����-${channel.channelName}</title>
		<meta name="description" content="Ϊ���ṩ����Ͷ������������񡢲�ѯ�������������������ϲ�������ݡ���ʡ���ı���ʱ�䣡" />
		<meta name="keywords" content="Ͷ������,�������,��ѯ����,��������,�������,Ͷ������" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/resources/css/service/service_universal_note.css" rel="stylesheet" type="text/css"/>
	</head>
<body>
<!-- ҳͷ ��ʼ-->
<jsp:include page="/header.jsp"></jsp:include>
<!-- ҳͷ���� -->

<!-- �м�����start -->
<div class="middle">	
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">${channel.channelName}</li>
			</ul>
		</div>
		<div class="agree_body">
			<h1>${document.docName}</h1>
			<h2>����ʱ�䣺${document.pubTime}</h2>
			${document.docContent}
		</div>
	</div>
</div>
<!-- �м���������end -->

<!-- ҳβ��ʼ -->
<jsp:include page="/footer.jsp"></jsp:include>
<!-- ҳβ���� -->


</body>
</html>