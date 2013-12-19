<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
response.setHeader("Cache-Control","No-Cache");//HTTP 1.1
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
<head>
<title>╫ги╚х╗оч</title>
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
</head>
<body leftmargin="0" topmargin="0">
<table style="height:100%;width:100%;text-align:left;" cellspacing="0" cellpadding="0" border="0" align="left">
	<tr>
		<td style="text-align: left;" valign="top">
			<div id="authorityTree" style="overflow: no; width:100%;height:100%;"></div>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
var tree=new dhtmlXTreeObject("authorityTree","100%","100%",0); 
tree.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.loadXML("${ctx}/system/roleManage/findRoleAuthorityDetail.do?roleID=${param.roleID}",loadOver);
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);

function loadOver(){
	$("#showListLoading",window.parent.document).hide();
}
</script>
</html>