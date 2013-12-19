<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>流程进度跟踪</title>
</head>
<body>
 <div>  
    <img src="${pageContext.request.contextPath}/workFlow/getPic.do?procDefId=${procDefId }" style="position:absolute; left:0px; top:0px;">  
 	<!-- 给执行的节点加框 -->  
 	<div style="position:absolute; border:2px solid red;left:${position.x-1 }px;top:${position.y-1 }px;width:${position.width }px;height:${position.height }px;"></div>  
</div>  

</body>
</html>