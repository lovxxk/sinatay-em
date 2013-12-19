<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title></title>
</head>
<body>
<script type="text/javascript">
	var parentAttrID = "${parentAttrID}";
	function reloadTest () {
		parent.frames["directoryAttributeTree"].location.reload();
		return;
	}
	if(true) {
		reloadTest();
	}
	alert("属性添加成功！");
	parent.frames["directoryAttributeTree"].openTreeItem(parent.frames["directoryAttributeTree"].tree,parentAttrID);
</script>
</body>
</html>
