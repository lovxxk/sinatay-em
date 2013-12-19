<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.jspsmart.upload.*"%>
<%@ page
	import="cn.com.sinosoft.ebusiness.online.biz.dcenter.web.FileMngAction"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
</body>
</html>

<%
	String fileName = request.getParameter("down_filename");
	String fileType = request.getParameter("fileType");
	fileName = FileMngAction.decryption(fileName);
	System.out.println(fileName);
	System.out.println(fileType);

	String tt = "BQ".equals(fileType) ? application.getInitParameter("bqfilepath")
			: application.getInitParameter("lpfilepath");

	String filepath = tt + "/" + fileName;
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


