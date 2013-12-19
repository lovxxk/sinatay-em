<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title></title>
</head>
<body>
<script type="text/javascript">
	var parentAttrID = "${parentAttrID}";
	
	if ("${resultFlag}" == "success") {
		alert("${message}");
		window.parent.close();
		parent.window.opener.parent.frames[0].doSearch();
	} else if ("${resultFlag}" == "failure") {
		alert("${message}");
		location.href = "${ctx}/business/cmpProductManage/productDirectory/directory/recommendProduct/index.jsp?eId=${eId}";
	} else {
		location.href = "${ctx}/business/cmpProductManage/productDirectory/directory/recommendProduct/index.jsp?eId=${eId}";
	}
</script>
</body>
</html>
