<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String nodeID = request.getParameter("nodeID") == null ? "" : request.getParameter("nodeID");
//request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${ctx}/cms/global/css/GUI.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<title>查找模板</title>
<script type="text/javascript">
function doClear(){
	window.frmInput.reset.click();
}
function doSearch(){
	//window.parent.fraToolbar.clearID();
	//window.parent.fraToolbar.divPage.innerHTML="";
	//window.parent.fraSearchForm.frmInput.pageIndex.value="1";	
	window.frmInput.submit();
}
</script>
</head>
<body topmargin=0 leftmargin=0>
<center>
<form action="${ctx}/templetManage/toGetTempletForSearch.do" target="fraSearchList" id="frmInput" method="post">
<table border="0" style="font-size:9pt;" width=99% cellpadding="0" cellspacing="0" class="table_Show">
	<tr>
		<td class="tdleft" width=10% nowrap height=30 align="center">模板类型</td>
		<td class="tdright" width=20%>
			<select name="templetType" id="templetType">
				<option value="">所有</option>
				<option value="文章列表">文章列表</option>
				<option value="文章明细">文章明细</option>
				<option value="单篇文章">单篇文章</option>
				<option value="特殊定制">特殊定制</option>
			</select>
		</td>
		<td class="tdleft" width=10% nowrap align="center">模板标题</td>
		<td class = "tdright" width=20%><input name="templetTitle" size=20></td>
	</tr>
	<tr>
		<td height=20 colspan=4></td>
	</tr>
</table>
<table border=0 style="font-size:9pt;" width=90% cellpadding=2 cellspacing=2 validate="onchange" invalidColor="yellow" mark year4>
	<tr>
		<td width=70%></td>
		<td nowrap><input type="hidden" name="pageIndex" id="pageIndex" value="1">每页显示<input size=3 value=20 id=pageSize name=pageSize filter="[0-9]">项</td>
		<td class="btnBigOnFocus" onclick="javascript:doSearch();" nowrap>查找</td>
		<td class="btnBigOnFocus" align=right onclick="javascript:doClear();" nowrap>清空</td>
	</tr>
</table>
<input id="reset" type="reset" style="display:none;">
<input type=hidden name="nodeID" id="nodeID" value="<%=nodeID %>">
</form>
</center>
</body>
</html>
