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

	//�½�һ��smartUpload����  
	SmartUpload smartUpload = new SmartUpload();
	//��ʼ��  
	smartUpload.initialize(this.getServletConfig(), request, response);
	//�趨contentDispositionΪnull�Խ�ֹ������Զ����ļ�  
	//��֤�������Ӻ��������ļ���  
	smartUpload.setContentDisposition(null);
	//�����ļ�  
	try {
		smartUpload.downloadFile(filepath);
	} catch (SmartUploadException e) {
		e.printStackTrace();
	} 
%>