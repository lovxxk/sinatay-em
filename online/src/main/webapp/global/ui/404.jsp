<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
		
		<script src="${ctx}/global/js/jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/main.js" type="text/javascript"></script>
		<link href="${ctx}/global/css/global.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/util.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/error/404.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<div class="main_404">
			<div class="sorry"></div>
			<div class="note_info">
				<p class="en">ERROR... PAGE NOT FOUND</p>
				<p class="cn">您要查看的网址可能已被删、名称已被更改，或者暂时不可用</p>
				<p class="link">点击以下链接断续浏览网站</p>
				<p class="link"><a href="#">&gt;&gt; 返回上一页面</a></p>
				<p class="link"><a href="${ctx}/index.jsp">&gt;&gt; 返回网站首页</a></p>
			</div>
			<div class="warning_bg"></div>
		</div>
	</body>
</html>
