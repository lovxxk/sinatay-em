<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title></title>
</head>
<body>
<script type="text/javascript">
alert(" Ù–‘ÃÌº”${message}");
parent.location.href = "${ctx}/productDirectory/directoryAttributeTree.do?treeId=0&openTreeNode=${parentAttrID}&attrId=${attrId}";
</script>
</body>
</html>
