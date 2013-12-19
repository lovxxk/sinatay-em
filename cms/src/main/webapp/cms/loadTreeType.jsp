<%@page import="cn.com.sinosoft.ebusiness.cms.service.facade.TreeBoxService"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page language="java" contentType="text/xml;charset=UTF-8" %>
<%
WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletConfig().getServletContext());
TreeBoxService treeBoxService = (TreeBoxService)wc.getBean("treeBoxService");
String xml = treeBoxService.makeTreeBoxXml();
out.clear();
out.print(xml);
%>
