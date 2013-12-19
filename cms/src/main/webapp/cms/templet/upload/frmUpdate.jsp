<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@page import="cn.com.sinosoft.ebusiness.cms.domain.CmsTemplet" %>
<%
CmsTemplet templet = (CmsTemplet)request.getAttribute("templet");
String templetID = templet.getTplID() + "";
String result = (String)request.getAttribute("result") == null?"":(String)request.getAttribute("result");
String msgFlag = (String) request.getAttribute("msgFlag") == null ? "" : (String) request.getAttribute("msgFlag");
%>
<c:if test="${result eq 'updateFailed' && msgFlag ne '1'}">
	<script type="text/javascript">
	alert('修改样式失败!');
	</script>
</c:if>
<c:if test="${result eq 'updateDone' && msgFlag ne '1'}">
	<script type="text/javascript">
	alert('修改样式成功!');
	parent.document.getElementById("fraSearchList").src="${ctx}/cms/global/ui/info.jsp?message=updateTempletSuccess";
	</script>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<title>样式属性</title>
<script type="text/javascript">
	function addNode(){
		document.getElementById('frmInput').submit();
	}
</script>
</head>
<body>
<form name="frmInput" id="frmInput" action="${ctx}/templetManage/toUpdateTemplet.do" method="post">
	 <input type="hidden" value="<%=templetID %>" name="templetID">
	 <table class="table_style" align="center" width="372px" id="productTable" >
		<tr>
			<td class="td_head td_in1" nowrap>模板标题</td>
			<td class="td_in2" nowrap></td>
			<td class="td_body td_in3" nowrap>
				<input type="text" value="<%=templet.getTplStoreName() %>" name="templetTitle" size=30/>
			</td>
		</tr>
		<tr>
			<td class="td_head td_in1" width=10% nowrap >模板类型</td>
			<td class="td_in2" nowrap></td>
			<td class="td_body td_in3" width=20%>
				<select name="templetType" id="templetType">
					<option value="<%=templet.getTplType() %>"><%=templet.getTplType() %></option>
					<c:forEach var="tpl" items="${tplType}">
					<option value="tpl">${tpl}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head td_in1" nowrap>模板源文件</td>
			<td class="td_in2" nowrap></td>
			<td class="td_body td_in3" nowrap>
				<input type="text" value="<%=templet.getTplName() %>" name="templetFile" size=30 maxlength=100 />
			</td>
		</tr>
		<tr height="60px;">
			<td colspan="4" align="center">
			<table>
				<tr>
					<td id="submit" class="btnBigOnFocus" onclick="javascript:addNode();" nowrap>修改</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
