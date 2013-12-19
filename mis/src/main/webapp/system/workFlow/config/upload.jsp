<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body style="padding-top:50px;">
<form enctype="multipart/form-data" action="${ctx }/workFlow/getWorkFlowConfig.do" method="post" id="frmInput">
	流程配置文件：
	<input type="file" name="file" id="file" style="height:23px;" />
	<input type="button" value="上传"  onclick="doCreate()" style="height:23px;"/>
</form>
<script>
function doCreate(){
	var flag=true;
	if($("#file").val()==null||$("#file").val()== ""){
		flag=false;
		alert('不能为空');	
	}
	if(flag){			
		$("#frmInput").submit();
	}	
}
</script>
</body>
</html>