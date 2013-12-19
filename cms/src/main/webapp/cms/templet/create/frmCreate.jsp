<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String result = (String)request.getParameter("result") == null ? "" : (String)request.getParameter("result");
%>
<c:if test="${result eq '失败' }">
	<script type="text/javascript">
	alert('创建样式失败!');
	</script>
</c:if>
<c:if test="${result eq '成功' }">
	<script type="text/javascript">
	alert('创建样式成功!');
	window.parent.location.href="${ctx}/cms/global/ui/info.jsp?message=createTempletSuccess";
	</script>
</c:if>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>上传模板</title>
</head>
<body>
<div class="select_header_top_bg">
	<div class="select_header_top_left1"></div>
	<div class="select_header_top_left2"></div>
	<div class="select_header_top_title">
		<div class="select_header_top_title_content" style="width:180px;">上传模板</div>
	</div>
	<div class="select_header_top_right1"></div>
	<div class="select_header_top_right2"></div>
</div>
<form action="${ctx }/templetManage/toCreateTemplet.do" id="frmInput" method="post" target="myFrame">
<table class="table_style" align="center" width="650px" id="productTable">
<tr>
	<td height=10>&nbsp;</td>
</tr>
<tr>
	<td class="td_head" style="height:30px;" nowrap>模板文件名：</td>
	<td  class="td_body">
		<input id="templetFile" name="templetFile" type="text" style="width:170px;" class=required maxlength="32">
		<span id='templetFile_msg'></span>
	</td>
</tr>
<tr>
	<td class="td_head" width="120px" style="height:30px;" nowrap>模板名称：</td>
	<td class="td_body" width="530px" >
		<input id="templetName" name="templetName" type="text" style="width:170px;" class=required maxlength="20">
		<span id="templetName_msg"></span>
	</td>
</tr>
<tr>
	<td class="td_head" style="height:30px;" nowrap>模板类型：</td>
	<td  class="td_body">
		<select id="templetType" name="templetType" class=required>
			<option value="">请选择</option>
			<c:forEach var="tpl" items="${tplType}">
				<option value="${tpl}">${tpl}</option>
			</c:forEach>
		</select>
		<span id='templetType_msg'></span>
	</td>
</tr>

<tr height=25><td></td></tr>
<tr>
	<td colspan="2">
		<table width="200" align="center">
		<tr>
			<td id="createButton" align=right class="btnBigOnFocus"  onclick="doCreate();" nowrap>创建 </td>
			<td width="5">&nbsp;</td>
			<td id="resetButton" class="btnBigOnFocus" align=right onclick="doClear();" nowrap>重置</td>
		</tr>
		</table>
	</td>
</tr>
</table> 
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
function doCreate(){
		$("#frmInput")[0].submit();
}

function doClear(){
	$("#frmInput")[0].reset();
}
</script>
</html>
