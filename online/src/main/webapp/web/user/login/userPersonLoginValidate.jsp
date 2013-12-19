<%@page import="cn.com.sinosoft.ebusiness.service.user.personal.service.spring.UpdateLoginServiceSpringImpl"%>
<%@page import="cn.com.sinosoft.ebusiness.sys.permission.service.spring.LoginUserException"%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/web/user/login/taglibs.jsp"%>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.authentication.UsernamePasswordAuthenticationToken" %>

<c:if test="${not empty param.error}">
<%
AuthenticationException authenticationException = (AuthenticationException)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
if(authenticationException != null){
	//用户名密码有误
	if (authenticationException.getAuthentication() instanceof UsernamePasswordAuthenticationToken ){
		String userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		if (authenticationException.getMessage().equals("Bad credentials")) {
			request.setAttribute("loginMessage", "密码错误，请重新输入");
			
			UpdateLoginServiceSpringImpl.updateUserLoginFailedCount(userAccName);
			
		} else {
			request.setAttribute("loginMessage", new String(authenticationException.getMessage()));
		}
		request.setAttribute("userAccName",userAccName);
		
		
		//还原用户名
		userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		request.setAttribute("userAccName",userAccName);
	}
	
	
	//用户自定义登录异常
	if(authenticationException instanceof LoginUserException){
		request.setAttribute("loginMessage","该用户已经为无效用户,无法登录!");
		
		//还原用户名
		String userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		request.setAttribute("userAccName",userAccName);
	}
}
%>
</c:if>

