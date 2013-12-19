<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@page import="cn.com.sinosoft.ebusiness.cms.domain.CmsTemplet" %>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%
int listCount = (Integer)request.getAttribute("listCount") == null ? 9 : ((Integer)request.getAttribute("listCount")).intValue(); 
List templetList = (List)request.getAttribute("templetList") == null ? new ArrayList() : (List)request.getAttribute("templetList");
CmsTemplet templet = new CmsTemplet();
if(listCount==0){
	response.sendRedirect("../../../global/ui/info.jsp?message=noRecord");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/table.css">
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
<title>查询结果</title>
</head>
<body leftmargin="0" topmargin="0">
<form id="frmLIST" method="get">
<table width="100%" cellpadding="0" cellspacing="0" border="0" class="table_Show">
	<tr>
		<td class="td_head" width="30" align="center" nowrap>序号</td>
		<td class="td_head" width="25" align=center><input type="checkbox" id="checkAll" title="全部选中" onclick="javascript:checkAllorders();"></td>
		<td class="td_head" width="100" nowrap>模板标题</td>
		<td class="td_head" width="100" nowrap>模板类型</td>		
		<td class="td_head" width="100" nowrap>模板源文件</td>
	</tr>
	<%
		int i;
		for(i=0;i<templetList.size();i++){
			templet =(CmsTemplet)templetList.get(i);
			String templetTitle = templet.getTplStoreName();
			String templetType = templet.getTplType();
			String templetFile = templet.getTplName();
	%>
	<tr id="tr<%=i+1%>">
		<td class="td_head" width="30"></td>
		<td class="td_head" width="25" align=center><input type="checkbox" id="check<%=i+1%>" onclick="window.parent.fraToolbar.checkID('<%=i+1%>','<%=templet.getTplID()%>',this.checked);"></td>
		<td class="td_body" width="100" nowrap><%=templetTitle %>&nbsp;</td>
		<td class="td_body" width="100" nowrap><%=templetType %>&nbsp;</td>
		<td class="td_body" width="100" nowrap><%=templetFile %>&nbsp;</td>
	</tr>
	<%} %>
</table>
</form>
<script type="text/javascript">
	function checkAllorders(){
		var i;
		var check1=window.frmLIST.checkAll.checked;
		var check2;
		
		if (check1)
			window.frmLIST.checkAll.title="全部清除";
		else
			window.frmLIST.checkAll.title="全部选中";
		var count = '<%=i+1%>';	
		for (i=1;i<count;i++){
			eval("check2=window.frmLIST.check" + i + ".checked;");
			if (check1!=check2)
				eval("window.frmLIST.check" + i + ".click();");
		}
	}
</script>
</body>
</html>
