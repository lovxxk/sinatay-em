<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%-- <%@ include file="/common/taglibs.jsp"%> --%>
<%-- <script type="text/javascript" src	="${ctx}/js/jquery-1.5.2.js"></script> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信泰首页</title>
</head>
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="${ctx}/web/user/login/css/menu.css" /> --%>
<script type="text/javascript">
	function closeCurrentPage() {
		if(confirm("您确定要关闭本页吗？")) {
			window.opener=null;
			window.open('','_self');
			window.close();
		}
	}
</script>
<body>
	<form action="">
		Email:${customer.email } 
		<br> 
		Mobile:${customer.mobile } 
		<br>
		<c:if test='${customer.email == null || customer.email == ""}'>
			密码已通过短信发送至您的手机:<red>${customer.mobile }</red>，请注意查收。
		<input type="hidden" name="customer.mobile" value="${customer.mobile }">
		</c:if>
		<c:if test='${customer.email != null && customer.email != ""}'>
			密码已通过邮件发送至您的邮箱<red>${customer.email }</red>，请注意查收。
		<input type="hidden" name="customer.email" value="${customer.email }">
		</c:if>
		<br>
		<br>
		<input type="button" name="submit" value="确定" onclick="closeCurrentPage();">
	</form>
</body>
</html>