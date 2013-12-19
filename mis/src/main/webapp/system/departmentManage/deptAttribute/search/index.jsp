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
<div class="public_fun_title">机构属性配置</div>
<div style="height:10px;clear:both;">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" style="width:98%;height:75%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:250px;text-align:center;">机构属性维护</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">机构属性操作</td>
	</tr>
	<tr>
		<td style="border-right:1px solid #dcdcdc;padding:0px;margin:0px;width:55%" valign="top">
			<iframe style="width:100%;height: 60px;" frameborder="0" name="fraSearchForm" src="frmSearch.jsp?deptid=${param.deptid}" frameborder="0" scrolling="no" noresize></iframe>
			<iframe style="width:100%;height: 30px;" frameborder="0" name="fraToolbar" src="toolbar.jsp?deptname=${param.deptname}&deptid=${param.deptid}" frameborder="0" scrolling="no" noresize></iframe>
			<iframe style="width:100%;height: 360px;" frameborder="0" name="fraSearchList" src="${ctx}/global/ui/emptyTable.jsp" scrolling="auto" noresize></iframe>
			<iframe style="width:100%;height: 60px;" frameborder="0" name="frapage" src="<%=request.getContextPath()%>/global/inc/page.jsp" frameborder="0" scrolling="no" noresize></iframe>
			<iframe style="width:100%;height: 0px;" frameborder="0" name="fraHidden" id="fraHidden" src=""></iframe>
		</td>
		<td width="45%">
			<iframe name="operate" style="height:95%;width:100%;" frameborder="0" scrolling="no" id="operate" src="${ctx}/system/departmentManage/deptAttribute/create/frmCreate.jsp?deptname=${param.deptname}&deptid=${param.deptid}"></iframe>
		</td>
	</tr>
</table>
</body>
