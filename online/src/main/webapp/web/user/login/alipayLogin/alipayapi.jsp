<%@page import="cn.com.sinosoft.ebusiness.service.user.personal.service.spring.GeUserPersonalServiceSpringImpl"%>
<%/* *
*���ܣ���ݵ�¼�ӿڽ���ҳ
*�汾��3.3
*���ڣ�2012-08-14
*˵����
*���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
*�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���

*************************ע��*****************
*������ڽӿڼ��ɹ������������⣬���԰��������;�������
*1���̻��������ģ�https://b.alipay.com/support/helperApply.htm?action=consultationApply�����ύ���뼯��Э�������ǻ���רҵ�ļ�������ʦ������ϵ��Э�����
*2���̻��������ģ�http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&info_type=9��
*3��֧������̳��http://club.alipay.com/read-htm-tid-8681712.html��
*�������ʹ����չ���������չ���ܲ�������ֵ��
**********************************************
*/%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="cn.com.sinosoft.ebusiness.ali.util.*"%>
<%@ page import="java.util.Map"%>
<%-- <%@ include file="/common/taglibs.jsp"%> --%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>֧������ݵ�¼�ӿ�</title>
	</head>
	<%
		Map<String, String> sParaTemp = GeUserPersonalServiceSpringImpl.generateAlipayLoginParamter();
		//��������
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "ȷ��");
		System.out.println(sHtmlText);
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
