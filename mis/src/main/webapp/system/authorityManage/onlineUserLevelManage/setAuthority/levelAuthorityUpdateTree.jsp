<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
response.setHeader("Cache-Control","No-Cache");//HTTP 1.1
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
<head>
<title>前台个人客户级别权限设置</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body leftmargin="0" topmargin="0" style="background:url(${ctx}/global/images/face/left_bg.jpg) repeat-x;" >
<table cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td style="border:1px solid #BEBEBE;">
			<div style="overflow-y:auto;overflow-x:hidden;">
				<div id="authorityTree" style="width: 400px;height:450px; "></div>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border:1px solid #BEBEBE;height:50px;">
			<table align="center">
				<tr>
					<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" id="sub" nowrap>确认</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<form id="frmInput" action="${ctx}/authorityManage/updateLevelAuthoritys.do" method="post" target="myFrame">
	<input type="hidden" id="authoritys" name="authoritys">
	<input type="hidden" id="userLevel" name="userLevel" value="${param.userLevel}">
</form>
<iframe style="display:none;" name="myFrame"></iframe>
</body>
<script type="text/javascript">
var tree=new dhtmlXTreeObject("authorityTree","100%","100%",0); 
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(true);
tree.enableThreeStateCheckboxes(true);
tree.loadXML("${ctx}/authorityManage/findTreeData.do?userLevel=${param.userLevel}");
var children=tree.getAllCheckedBranches();

$("#sub").click(function(){
	$("#authoritys").val(tree.getAllCheckedBranches());
	$("#frmInput").submit();
});
</script>
</html>