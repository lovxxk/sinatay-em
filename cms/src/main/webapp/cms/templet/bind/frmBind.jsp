<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@page import="cn.com.sinosoft.ebusiness.cms.domain.CmsTemplet"%>
<%@page import="java.util.*" %>
<%
String nodeID = request.getParameter("nodeID") == null ? "" : request.getParameter("nodeID");
List docListTemp = (List)request.getAttribute("docListTemp") == null ? new ArrayList() : (List)request.getAttribute("docListTemp");
List docDetailTemp = (List)request.getAttribute("docDetailTemp") == null ? new ArrayList() : (List)request.getAttribute("docDetailTemp");
List singleDocTemp = (List)request.getAttribute("singleDocTemp") == null ? new ArrayList() : (List)request.getAttribute("singleDocTemp");
List specialConTemp = (List)request.getAttribute("specialConTemp") == null ? new ArrayList() : (List)request.getAttribute("specialConTemp");
List firstChildTemp = (List)request.getAttribute("firstChildTemp") == null ? new ArrayList() : (List)request.getAttribute("firstChildTemp");
List includeTemp = (List)request.getAttribute("includeTemp") == null ? new ArrayList() : (List)request.getAttribute("includeTemp");

String result = (String) request.getAttribute("result") == null ? "" : (String) request.getAttribute("result");
String msgFlag = (String) request.getAttribute("msgFlag") == null ? "" : (String) request.getAttribute("msgFlag");

%>
<c:if test="${result eq 'createFailed' && msgFlag ne '1'}">
	<script type="text/javascript">
	alert('绑定样式失败!');
	</script>
</c:if>
<c:if test="${result eq 'createDone' && msgFlag ne '1' }">
	<script type="text/javascript">
	alert('绑定样式成功!');
	window.parent.location.href="${ctx}/cms/global/ui/info.jsp?message=bindTempletSuccess";
	</script>
</c:if>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>样式绑定</title>
</head>
<body>
<div class="select_header_top_bg">
	<div class="select_header_top_left1"></div>
	<div class="select_header_top_left2"></div>
	<div class="select_header_top_title">
		<div class="select_header_top_title_content" style="width:180px;">样式绑定</div>
	</div>
	<div class="select_header_top_right1"></div>
	<div class="select_header_top_right2"></div>
</div>
<form action="${ctx}/templetManage/toBindTemplet.do" id="frmInput" method="post" target="myFrame">
<div id = "current">
<table>
<tr>
<td class="td_head" style="height:30px;" nowrap colspan="3">现有样式</td>
</tr>

<c:forEach var="tpl" items="${tplList}">
	<tr>
		<td class="td_head" style="height:30px;" nowrap >${tpl.tplType}</td>
		<td class="td_body">${tpl.tplStoreName}</td>
		<td class="td_head" style="height:30px;" nowrap ><a onclick="doDelete('${tpl.tplID}')" style="cursor: pointer;">删除</a></td>
	</tr>
</c:forEach>

</table>
</div>

<table class="table_style" align="center" width="650px" id="productTable">
<tr>
	<td height=10>&nbsp;</td>
</tr>
<tr>
	<td class="td_head" style="height:30px;" nowrap>跳转到首个子栏目：</td>
	<td  class="td_body">
		<input id="firstChild" name="templet" type="radio" value="firstChild" onclick='showDIV()' >
		<span id='firstChild_msg'></span>
	</td>
	<td class="td_head" style="height:30px;" nowrap>文章列表：</td>
	<td  class="td_body">
		<input id="docList" name="templet" type="radio" value="docList" onclick='showDIV()' >
		<span id='docList_msg'></span>
	</td>
	<td class="td_head" style="height:30px;"nowrap>单篇文章：</td>
	<td class="td_body">
		<input id="singleDoc" name="templet" type="radio" value="singleDoc" onclick='showDIV()' >
		<span id="singleDoc_msg"></span>
	</td>
	<td class="td_head" style="height:30px;" nowrap>特殊内容：</td>
	<td  class="td_body">
		<input id="specialContent" name="templet" type="radio" value="specialContent" onclick='showDIV()' >
		<span id="specialContent_msg"></span>
	</td>
</tr>
</table> 
<div id = "div0" style="display:none">
<table>
<tr>
<td class="td_head" style="height:30px;" nowrap>跳转样式：</td>
<td  class="td_body">
	<select id="firstChild0" name="firstChild0">
		<%for(int i = 0;i<firstChildTemp.size();i++) {%>
			<option value="<%=((CmsTemplet)firstChildTemp.get(i)).getTplID()+"" %>"><%=((CmsTemplet)firstChildTemp.get(i)).getTplStoreName() %></option>
		<%} %>
	</select>
</td>
</tr>
</table>
</div>

<div id = "div1" style="display:none">
<table>
<tr>
<td class="td_head" style="height:30px;" nowrap>列表样式：</td>
<td  class="td_body">
	<select id="docList0" name="docList0">
		<%for(int i = 0;i<docListTemp.size();i++) {%>
			<option value="<%=((CmsTemplet)docListTemp.get(i)).getTplID()+"" %>"><%=((CmsTemplet)docListTemp.get(i)).getTplStoreName() %></option>
		<%} %>
	</select>
</td>
<td class="td_head" style="height:30px;" nowrap>文章明细样式：</td>
<td  class="td_body">
	<select id="docList1" name="docList1">
		<%for(int i = 0;i<docDetailTemp.size();i++) {%>
			<option value="<%=((CmsTemplet)docDetailTemp.get(i)).getTplID()+"" %>"><%=((CmsTemplet)docDetailTemp.get(i)).getTplStoreName() %></option>
		<%} %>
	</select>
</td>
</tr>
</table>
</div>
<div id = "div2" style="display:none">
<table>
<tr>
<td class="td_head" style="height:30px;" nowrap>单篇文章样式：</td>
<td  class="td_body">
	<select id="singleDoc0" name="singleDoc0">
		<%for(int i = 0;i<singleDocTemp.size();i++) {%>
			<option value="<%=((CmsTemplet)singleDocTemp.get(i)).getTplID()+"" %>"><%=((CmsTemplet)singleDocTemp.get(i)).getTplStoreName() %></option>
		<%} %>
	</select>
</td>
</tr>
</table>
</div>
<div id = "div3" style="display:none">
	<table>
<tr>
<td class="td_head" style="height:30px;" nowrap>包含：</td>
<td  class="td_body">
	<select id="specialContent0" name="specialContent0">
		<%for(int i = 0;i<includeTemp.size();i++) {%>
			<option value="<%=((CmsTemplet)includeTemp.get(i)).getTplID()+"" %>"><%=((CmsTemplet)includeTemp.get(i)).getTplStoreName() %></option>
		<%} %>
	</select>
</td>
</tr>

</table>
</div>
<p style="height:100"></p>
<table width="200" align="center">
		<tr>
			<td id="createButton" align=right class="btnBigOnFocus"  onclick="doBind();" nowrap>确定 </td>
			<td width="5">&nbsp;</td>
			<td id="resetButton" class="btnBigOnFocus" align=right onclick="doClear();" nowrap>重置</td>
		</tr>
		</table>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
<input type="hidden" name="fc" id="fc" value=""/>
<input type="hidden" name="del" id="del" value="no"/>
<input type="hidden" name="num" id="num" value=""/>
<input type="hidden" name="nodeID" id="nodeID" value="<%=nodeID %>"/>
</form>
</body>
<script type="text/javascript">
function doBind(){
	$("#frmInput")[0].submit();
}

function doDelete(tplid){
	$("#del").val(tplid);
	$("#frmInput")[0].submit();
}

function showDIV(){
		var docListTempSize = <%=docListTemp.size() %>;
		var docDetailTempSize = <%=docDetailTemp.size() %>;
		var singleDocTempSize = <%=singleDocTemp.size() %>;
		var specialConTempSize = <%=includeTemp.size() %>;
		if($('input:radio[id="firstChild"]:checked').val() != null){
			$("#fc").val($("#firstChild").val());
			$("#num").val(1);
			$("#div0").show();
			$("#div1").hide();
			$("#div2").hide();
			$("#div3").hide();
		}else if($('input:radio[id="docList"]:checked').val() != null){
			if(!docListTempSize>0 && !docDetailTempSize>0){
				alert("无匹配内容!");
				return false;
			}
			$("#fc").val($("#docList").val());
			$("#num").val(2);
			$("#div0").hide();
			$("#div1").show();
			$("#div2").hide();
			$("#div3").hide();
		}else if($('input:radio[id="singleDoc"]:checked').val() != null){
			if(!singleDocTempSize>0){
				alert("无匹配内容!");
				return false;
			}
			$("#fc").val($("#singleDoc").val());
			$("#num").val(1);
			$("#div0").hide();
			$("#div1").hide();
			$("#div2").show();
			$("#div3").hide();
		}else if($('input:radio[id="specialContent"]:checked').val() != null){
			if(!specialConTempSize>0){
				alert("无匹配内容!");
				return false;
			}
			$("#fc").val($("#specialContent").val());
			$("#num").val(1);
			$("#div0").hide();
			$("#div1").hide();
			$("#div2").hide();
			$("#div3").show();
		}
}

function doClear(){
	$("#frmInput")[0].reset();
}
</script>
</html>
