<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>CMS管理系统</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css">
<script language="javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script language="javascript" src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script language="javascript" src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>


</head>
<body topmargin="0" leftmargin="0">
<div id="leftPanel_main" style="padding-top:5px;">
	<div id="treeboxbox_tree"></div>
</div>
<script language="javascript">
tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_vista/");
tree.enableHighlighting(1);
tree.loadXML("loadTreeType.jsp");
//延迟展开树end
tree.setOnClickHandler(doOnSelect);
function doOnSelect(nodeID){
	parent.document.getElementById("fraMAIN").src="${ctx}/cms/indexBody.jsp?nodeID="+nodeID;
}

</script>
</body>
</html>










