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
	//�û�����������
	if (authenticationException.getAuthentication() instanceof UsernamePasswordAuthenticationToken ){
		String userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		if (authenticationException.getMessage().equals("Bad credentials")) {
			request.setAttribute("loginMessage", "�����������������");
			
			UpdateLoginServiceSpringImpl.updateUserLoginFailedCount(userAccName);
			
		} else {
			request.setAttribute("loginMessage", new String(authenticationException.getMessage()));
		}
		request.setAttribute("userAccName",userAccName);
		
		
		//��ԭ�û���
		userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		request.setAttribute("userAccName",userAccName);
	}
	
	
	//�û��Զ����¼�쳣
	if(authenticationException instanceof LoginUserException){
		request.setAttribute("loginMessage","���û��Ѿ�Ϊ��Ч�û�,�޷���¼!");
		
		//��ԭ�û���
		String userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		request.setAttribute("userAccName",userAccName);
	}
}
%>
</c:if>

