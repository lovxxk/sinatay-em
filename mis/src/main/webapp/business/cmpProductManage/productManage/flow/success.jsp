<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
	if(confirm("流程定制配置成功!")){
		flushParentPage();
		//window.parent.close();
	}
	function flushParentPage(){
		window.parent.opener.parent.frames[0].document.getElementById("frmInput").submit();
	}
</script>
</head>
<body>
</body>
</html>