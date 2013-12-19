<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<title>frmCreate.jsp</title>
</head>
<body leftmargin="0" topmargin="0">
<div class="public_fun_title">权限信息</div>
<div style="height:10px;clear:both;">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" style="width:98%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:290px;text-align:center;">权限树信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">权限详细及操作</td>
	</tr>
	<tr>
		<td style="width:250px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
			<div id="misAuthorityManageTree" style="overflow-x:hidden;width:250px;"></div>
		</td>
		<td>
			<iframe id="frmMisAuthorityManage"  style="ovherflow-x:hidden;height:100%;width:100%;" name="frmMisAuthorityManage"  src="" frameborder="0" scrolling="auto"></iframe>
		</td>
	</tr>
</table>
<form id="frmInput" action="" target="">
	<input type="hidden" id="authorityID" name="geAuthority.authorityID">
	<input type="hidden" id="parentID" name="geAuthority.parentID">
	<input type="hidden" id="authorityLevel" name="geAuthority.authorityLevel">
	<input type="hidden" id="systype" name="geAuthority.systype"/>
	<iframe style="display:none;" id="myFrame" name="myFrame"></iframe>
</form>
</body>