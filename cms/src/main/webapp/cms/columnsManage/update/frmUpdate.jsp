<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@page import="cn.com.sinosoft.ebusiness.cms.domain.CmsChannel" %>
<%
CmsChannel channel = (CmsChannel)request.getAttribute("channel");
String channelID = channel.getChannelID()+"";
String chnlType = channel.getChnlType();
String chnlName = "栏目名称";
String chnlTypeName = "普通栏目";
String chnlDESName = "显示名称";
String channelIDName = "栏目id";
String linkURL = "链接地址";
if("1".equals(chnlType)){
	chnlTypeName="链接栏目";
}else if("2".equals(chnlType)){
	channelIDName="站点id";
	chnlTypeName="站点";
	chnlName="站点名称";
	chnlDESName="站点显示名称";
	linkURL="站点地址";
}else if("3".equals(chnlType)){
	chnlTypeName="复制栏目";
}else if("4".equals(chnlType)){
	chnlTypeName="镜像栏目";
}
String message = (String)request.getAttribute("message") == null?"":(String)request.getAttribute("message");
String msgFlag = (String) request.getAttribute("msgFlag") == null ? "" : (String) request.getAttribute("msgFlag");
%>

<c:if test="${message eq 'updateFailed' && msgFlag ne '1'}">
	<script type="text/javascript">
	alert('修改栏目失败!');
	</script>
</c:if>
<c:if test="${message eq 'updateDone' && msgFlag eq '1'}">
	<script type="text/javascript">
	alert('修改栏目成功!');
	window.parent.parent.parent.location.href="${ctx}/cms/index.jsp?ChannelID=<%=channelID%>";
	</script>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<title>栏目属性</title>
<script type="text/javascript">
	function addNode(nodeID){
		document.getElementById('frmInput').submit();
	}
</script>
</head>
<body>
<form name="frmInput" id="frmInput" action="${ctx}/columnsManage/toUpdateNode.do" method="post">
	 <input type="hidden" value="<%=channelID %>" name="nodeID">
	 <table class="table_style" align="center" width="372px" id="productTable" >
		<tr>
			<td class="td_head td_in1" nowrap><%=channelIDName %></td>
			<td class="td_in2" nowrap></td>
			<td class="td_body td_in3" nowrap>
				<input type="text" value="<%=channelID %>" name="ChannelID" size=30  readonly/>
			</td>
		</tr>
		<tr>
			<td class="td_head td_in1" nowrap><%=chnlName %></td>
			<td class="td_in2" nowrap></td>
			<td class="td_body td_in3" nowrap>
				<input type="text" value="<%=channel.getChnlName() %>" name="ChnlName" size=30 maxlength=100 />
			</td>
		</tr>
		<tr>
			<td class="td_head td_in1" nowrap><%=chnlDESName %></td>
			<td class="td_in2" nowrap></td>
			<td class="td_body td_in3" nowrap>
				<input type="text" value="<%=channel.getChnlDesName() %>" name="ChnlDESName" size=30 maxlength=100 />
			</td>
		</tr>
		<tr>
			<td class="td_head td_in1" nowrap>栏目类型</td>
			<td class="td_in2" nowrap></td>
			<td class="td_body td_in3">
				<input type="text" value="<%=chnlTypeName %>" name="ChnlTypeName" size=30 readOnly>
				<input type="hidden" value="<%=chnlType %>" name="ChnlType" size=10 >
			</td>
		</tr>
		<tr>
			<td class="td_head td_in1" nowrap><%=linkURL %></td>
			<td class="td_in2" nowrap></td>
			<td class="td_body td_in3">
				<input type="text" value="<%=channel.getLinkUrl() %>" name="LinkURL" id="LinkURL" size=30 maxlength=200 />
			</td>
		</tr>
		
		<tr height="60px;">
			<td colspan="4" align="center">
			<table>
				<tr>
					<td id="submit" class="btnBigOnFocus" onclick="javascript:addNode(<%=channelID %>);" nowrap>修改</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
