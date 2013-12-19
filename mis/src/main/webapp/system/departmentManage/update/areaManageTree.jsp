<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
response.setHeader("Cache-Control","No-Cache");//HTTP 1.1
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
<head>
<title>角色权限</title>
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
</head>
<body leftmargin="0" topmargin="0">
<table style="width:100%;height:100%;text-align:left;" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td style="text-align:left;" valign="top">
			<div id="areaManageTree" style="overflow-x:hidden;width:395px;height:100%;"></div>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
var area = '${param.area}';
var deptid = '${param.deptid}';
var tree=new dhtmlXTreeObject("areaManageTree","100%","100%",0); 
tree.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.setOnClickHandler(tonclick);
tree.setXMLAutoLoading("${ctx}/areaAction/findGeAreaActionTree.do");
tree.loadXML("${ctx}/areaAction/findGeAreaActionTree.do?id=0");
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);
function tonclick(id){
	var level = tree.getLevel(id);
	if(level==1){
		alert("根节点不可以选择!");
		return;
	}else{
		var areaname=tree.getItemText(id);
		$("#comChecked",window.parent.document).html("(选中区域:"+areaname+")");
		$("#areaname",window.parent.document).val(areaname);
		$("#areaid",window.parent.document).val(id);
		changeStyle(id);
	}
}

function changeStyle(itemId){
	var idArr = tree.getAllSubItems('0').split(",");
	for(var i = 0; i < idArr.length; i++){
		tree.setItemStyle(idArr[i],"background:none;color:black;font-weight:normal;");
	}
	tree.setItemStyle('0',"background:none;color:black;font-weight:normal;");
	tree.setItemStyle(itemId,"background:#819FF7;color:white;font-weight:bold;");
}

</script>
</html>