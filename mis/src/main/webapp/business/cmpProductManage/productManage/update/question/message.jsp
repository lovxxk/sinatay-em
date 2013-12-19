<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<title>上传消息页</title>
</head>
<body>
 	<script type="text/javascript">
		var re = "${message}";
 		if(re="success")
 			re = "调查问卷上传成功！";
 		else if(re = "nullFile")
 			re = "不能上传空的文件！";
 		else
 			re = "上传失败！";
 		alert(re);
 		var productCode = '<s:property value='geProductMain.coreProductCode'/>';
 		document.location.href=contextRootPath+"/productManage/toConfigQuestion.do?coreProductCode="+productCode;
		
 		
 		
 	</script>
</body>
</html>