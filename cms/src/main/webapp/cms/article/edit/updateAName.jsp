<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String sId = request.getParameter("sId") == null ? "" : request.getParameter("sId"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<script type="text/javascript">
function update(){
	var aa=window.opener.document.getElementById("a").contentWindow.document;
	aa.getElementById("FileName<%=sId%>").value=document.getElementById("filename").value
	var newspan=window.opener.document.getElementById("newspan<%=sId%>");
	newspan.innerHTML=document.getElementById("filename").value+"<br><a href='#' onclick=deleteSpan('<%=sId%>') >删除</a>";
	newspan.innerHTML+="&nbsp;&nbsp;<a href='#' onclick=updateSpan('<%=sId%>') >修改名称</a><br/>";		
	window.close();
}
</script>
</head>
<body topmargin=0 leftmargin=0>
<table border="0" width="100%" cellpadding="0" cellspacing="0">
<tr>
	<td style="BACKGROUND: url(../../../global/images/cms_tc_top_bg.jpg);font-size:13px;color:#4a4c4b;font-weight:bold;" width=135 height=36 valign="top">&nbsp;&nbsp;<img src="../../../global/images/cms_tc_top_ico.jpg">&nbsp;&nbsp;修改附件名称</td>
	<td valign="top" width=37><img src="../../../global/images/cms_tc_top_se.jpg"></td>
	<td background="../../../global/images/cms_tc_top_bg2.jpg" valign="top" width=328></td>
</tr>
<tr>
	<td colspan=3 height=30></td>
</tr>
<tr>
	<td width ="30%" class="font_t1" align="right" height=50>附件显示名称：</td>
	<td width ="70%" colspan="2">&nbsp;&nbsp;<input type="text" id="filename" name="filename" value=""/></td>
</tr>
<tr align="center">
	<td colspan="3">
		<table border="0" width="100" cellpadding="0" cellspacing="0">
			<tr>
				<td class="btn_ord2" onclick="update();">&nbsp;&nbsp;确定</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<script>
var aa=window.opener.document.getElementById("a").contentWindow.document;
var oldname=aa.getElementById("FileName<%=sId%>").value;
document.getElementById("filename").value=oldname;
</script>
</body>
</html>
