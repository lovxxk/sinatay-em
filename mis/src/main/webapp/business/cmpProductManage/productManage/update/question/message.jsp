<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<title>�ϴ���Ϣҳ</title>
</head>
<body>
 	<script type="text/javascript">
		var re = "${message}";
 		if(re="success")
 			re = "�����ʾ��ϴ��ɹ���";
 		else if(re = "nullFile")
 			re = "�����ϴ��յ��ļ���";
 		else
 			re = "�ϴ�ʧ�ܣ�";
 		alert(re);
 		var productCode = '<s:property value='geProductMain.coreProductCode'/>';
 		document.location.href=contextRootPath+"/productManage/toConfigQuestion.do?coreProductCode="+productCode;
		
 		
 		
 	</script>
</body>
</html>