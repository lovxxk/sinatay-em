<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>信泰保险-客服中心-${channel.channelName}</title>
		<meta name="description" content="为您提供网上投保服务、理赔服务、查询服务、在线自助服务，网上操作更便捷、节省您的宝贵时间！" />
		<meta name="keywords" content="投保服务,理赔服务,查询服务,自助服务,服务帮助,投保流程" />
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/resources/css/service/service_universal_note.css" rel="stylesheet" type="text/css"/>
	</head>
<body>
<!-- 页头 开始-->
<jsp:include page="/header.jsp"></jsp:include>
<!-- 页头结束 -->

<!-- 中间内容start -->
<div class="middle">	
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">${channel.channelName}</li>
			</ul>
		</div>
		<div class="agree_body">
			<h1>${document.docName}</h1>
			<h2>发布时间：${document.pubTime}</h2>
			${document.docContent}
		</div>
	</div>
</div>
<!-- 中间内容内容end -->

<!-- 页尾开始 -->
<jsp:include page="/footer.jsp"></jsp:include>
<!-- 页尾结束 -->


</body>
</html>