<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade.GeStationProvinceCicoService" %>
<% 
WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
GeStationProvinceCicoService geStationProvinceCicoService = (GeStationProvinceCicoService)wc.getBean("geStationProvinceCicoService");
String id = "".equals(request.getParameter("id"))?"0":request.getParameter("id");
String treeXml = geStationProvinceCicoService.getServiceNetworkXMLTree(id);
response.setContentType("text/xml;charset=GBK");
out.clear();
out.println(treeXml);
%>
