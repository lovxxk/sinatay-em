<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.jspsmart.upload.*"%>
<%@ page
	import="cn.com.sinosoft.ebusiness.infomanage.action.PolicyListAction"%>
<%
//	String path = application.getInitParameter("contfilepath");
	
	String fileName = request.getParameter("down_filename");
	fileName = PolicyListAction.decryption(fileName);

	String filepath = fileName;
	System.out.println(filepath);

	//新建一个smartUpload对象  
	SmartUpload smartUpload = new SmartUpload();
	//初始化  
	smartUpload.initialize(this.getServletConfig(), request, response);
	//设定contentDisposition为null以禁止浏览器自动打开文件  
	//保证单击链接后是下载文件。  
	smartUpload.setContentDisposition(null);
	//下载文件  
	try {
		smartUpload.downloadFile(filepath);
	} catch (SmartUploadException e) {
		e.printStackTrace();
	} 
%>