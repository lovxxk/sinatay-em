<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信泰首页</title>
</head>
<body>
	<s:form action="#">
		激活失败!${errorMessage }
		<a href="${ctx }/index.jsp">去首页</a>
		<a href="${ctx}/web/user/login/index.jsp">直接登录</a>
	</s:form>
</body>
</html>