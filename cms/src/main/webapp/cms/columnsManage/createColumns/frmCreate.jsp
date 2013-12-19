<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String nodeID = request.getParameter("nodeID") == null ? "" : request.getParameter("nodeID");
String message = (String)request.getAttribute("message") == null?"":(String)request.getAttribute("message");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<title>新建子栏目</title>
<c:if test="${message eq 'reChnlName' }">
	<script type="text/javascript">
	alert('栏目名称已存在！');
	</script>
</c:if>
<c:if test="${message eq 'reLinkURL' }">
	<script type="text/javascript">
	alert('栏目链接地址已存在！');
	</script>
</c:if>
<c:if test="${message eq 'createFailed' }">
	<script type="text/javascript">
	alert('创建栏目失败!');
	</script>
</c:if>
<c:if test="${message eq 'createDone' }">
	<script type="text/javascript">
	alert('创建栏目成功!');
	window.parent.parent.parent.location.href="${ctx}/cms/index.jsp?ChannelID=<%=nodeID%>";
	</script>
</c:if>
<script type="text/javascript">
		function addNode(nodeID){
			if(!doValidate()){
				return;
			}
			window.frmInput.submit.click();
		}
		
		function doValidate(){
			var chnlName = document.getElementById("ChnlName").value;
			var chnlDESName = document.getElementById("ChnlDESName").value;
			var chnlType = document.getElementById("ChnlType").value;
			var linkURL = document.getElementById("LinkURL").value;
			if(chnlName==null||chnlName==""){
				alert("请输入栏目名称");
				document.getElementById("ChnlName").focus();
				return false;
			}else if(chnlDESName==null||chnlDESName==""){
				alert("请输入栏目显示名称");
				document.getElementById("ChnlDESName").focus();
				return false;
			}else if(chnlType==null||chnlType==""){
				alert("请选择栏目类型");
				document.getElementById("ChnlType").focus();
				return false;
			}else if(linkURL==null||linkURL==""){
				alert("请输入栏目链接地址");
				document.getElementById("LinkURL").focus();
				return false;
			}else{
				return true;
			}
			
		}
</script>
</head>
<body>
<form name="frmInput" id="frmInput" action="${ctx}/columnsManage/toAddNode.do" method="post">
 <input type="hidden" value="<%=nodeID%>" name="nodeID">
 <table class="table_style" align="center" width="372px" id="productTable" >
	<tr>
		<td class="td_head td_in1" nowrap>栏目名称</td>
		<td class="td_in2" nowrap></td>
		<td class="td_body td_in3" nowrap>
			<input type="text" value="" name="ChnlName" id="ChnlName" size=30 maxlength=100 />
		</td>
	</tr>
	<tr>
		<td class="td_head td_in1" nowrap>显示名称</td>
		<td class="td_in2" nowrap></td>
		<td class="td_body td_in3" nowrap>
			<input type="text" value="" name="ChnlDESName" id="ChnlDESName" size=30 maxlength=100 />
		</td>
	</tr>
	<tr>
		<td class="td_head td_in1" nowrap>栏目类型</td>
		<td class="td_in2" nowrap></td>
		<td class="td_body td_in3">
			<select name="ChnlType" id="ChnlType">
					<option value="">请选择</option>
					<option value="0">普通栏目</option>
					<!--  
					<option value="1">链接栏目</option>
					-->
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head td_in1" nowrap>链接地址</td>
		<td class="td_in2" nowrap></td>
		<td class="td_body td_in3">
			<input type="text" value="" name="LinkURL" id="LinkURL" size=30 maxlength=200 />
		</td>
	</tr>
	
	<tr height="60px;">
		<td colspan="4" align="center">
		<table>
			<tr>
				<td id="submit" class="btnBigOnFocus" onclick="javascript:addNode(<%=nodeID%>);" nowrap>增加节点</td>
			</tr>
		</table>
		</td>
	</tr> 
</table>
<input id=optionStr name="optionStr" style="display:none;">
<input id="submit" name="submit" type="submit" style="display:none;">
</form>
</body>
</html>
