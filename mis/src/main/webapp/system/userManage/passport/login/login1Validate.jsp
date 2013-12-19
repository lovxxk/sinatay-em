<%@page import="cn.com.sinosoft.ebusiness.sys.permission.service.spring.LoginUserException"%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.authentication.UsernamePasswordAuthenticationToken" %>

<c:if test="${not empty param.error}">
<%
AuthenticationException authenticationException = (AuthenticationException)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
if(authenticationException != null){
	/**�û�����������*/
	if (authenticationException.getAuthentication() instanceof UsernamePasswordAuthenticationToken ){
		request.setAttribute("loginMessage","�û��������벻��ȷ!");
		
		/**��ԭ�û���*/
		String userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		request.setAttribute("userAccName",userAccName);
	}
	
	/**�û��Զ����¼�쳣*/
	if(authenticationException instanceof LoginUserException){
		request.setAttribute("loginMessage","���û��Ѿ�Ϊ��Ч�û����޷���¼!");
		
		/**��ԭ�û���*/
		String userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		request.setAttribute("userAccName",userAccName);
	}
}
%>
</c:if>
		
