<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@page import="cn.com.sinosoft.ebusiness.cms.domain.CmsChannel" %>
<%@page import="java.util.*" %>
<%
String path = request.getContextPath();
String childIDStr = request.getAttribute("childIDStr")==null?"1":request.getAttribute("childIDStr").toString();
String nodeID = request.getAttribute("nodeID")==null?"":request.getAttribute("nodeID").toString();
String message = (String)request.getAttribute("message") == null?"":(String)request.getAttribute("message");
List channelList =(List) request.getAttribute("channelList")==null?new ArrayList():(List) request.getAttribute("channelList");
if("".equals(childIDStr)){
	response.sendRedirect(path+"/cms/global/ui/info.jsp?message=noColumn");
}
String[] strID = childIDStr.split(",");
 
%>

<c:if test="${message eq 'orderFailed' }">
	<script type="text/javascript">
	alert('排序出错!');
	</script>
</c:if>
<c:if test="${message eq 'orderDone' }">
	<script type="text/javascript">
	alert('排序成功！');
	window.parent.parent.parent.location.href="${ctx}/cms/index.jsp?ChannelID=<%=nodeID%>";
	</script>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<title>栏目排序</title>
<script type="text/javascript">
var x=null; 
var listObj=null; 
//鼠标按下不放时的操作 
function setTimeStart(type){ 
 	listObj=document.getElementById('forder'); 
 	//超过0.3秒启动连续的向上(下)的操作 
 	if(type=="up"){ 
 		x=setTimeout(upListItem,300); 
 	}else{ 
 		x=setTimeout(downListItem,300); 
 	} 
} 
//将选中item向上 
function upListItem(){ 
 	var selIndex=listObj.selectedIndex;
 	if(selIndex<0){ 
 		if(x!=null){
 			clearTimeout(x);
 		} 
 		return; 
 	} 
 	if(selIndex==0){ 
 		if(x!=null){
 			clearTimeout(x);
 		} 
 		return; 
 	}
 	 
 	var selValue=listObj.options[selIndex].value; 
 	var selText=listObj.options[selIndex].text; 
 	listObj.options[selIndex].value=listObj.options[selIndex-1].value; 
 	listObj.options[selIndex].text=listObj.options[selIndex-1].text; 
 	listObj.options[selIndex-1].value=selValue; 
 	listObj.options[selIndex-1].text=selText; 
 	listObj.selectedIndex=selIndex-1; 
 	if(selIndex+1>0){ 
 		x=setTimeout(upListItem,200) 
	} 
} 
//将选中item向下 
function downListItem(){ 
 	var selIndex=listObj.selectedIndex; 
 	if(selIndex<0){ 
 		if(x!=null){
 			clearTimeout(x);
 		} 
 		return; 
 	} 
 	if(selIndex==listObj.options.length-1){ 
 		if(x!=null){
 			clearTimeout(x);
 		} 
 		return; 
	} 
 	var selValue=listObj.options[selIndex].value; 
 	var selText=listObj.options[selIndex].text; 
 	listObj.options[selIndex].value=listObj.options[selIndex+1].value; 
 	listObj.options[selIndex].text=listObj.options[selIndex+1].text; 
 	listObj.options[selIndex+1].value=selValue; 
 	listObj.options[selIndex+1].text=selText; 
 	listObj.selectedIndex=selIndex+1; 
 	if(selIndex+1<listObj.options.length-1){ 
 		x=setTimeout(downListItem,200) 
 	} 
} 
</script>
<script type="text/javascript">
function saveOrder(){
	if(confirm("确认排序？")){
		var forder=document.getElementById("forder");
		var forderValue="";
		for(var i=0;i<forder.options.length;i++){
			forderValue=forderValue+forder.options[i].value+",";
		}
		document.getElementById("forderValue").value=forderValue;
		document.frmResume.submit();
	}
}
</script>
</head>
<body>
  <form action="${ctx}/columnsManage/toUpdateOrder.do" name="frmResume" id="frmResume">
    <table  align="center" border=0 cellpadding=0 cellspacing=0 class="table_Show">
		<tr>
			<td width="220" align=center valign="top">
			<br><br>
			<select id="forder"  style="WIDTH: 170px; HEIGHT: 240px" size="15">
			<%
				for(int i=0;i<channelList.size();i++){
					CmsChannel channel = (CmsChannel)channelList.get(i);
				
			%> 
				<option value="<%=strID[i]%>"><%=channel.getChnlDesName()%></option> 
			<%} %>
 			</select>
			</td>
			<td width="60" align=center>
			<br><br>
			<input  type="button" value="↑" onmousedown="setTimeStart('up');" onmouseup="clearTimeout(x);" 
				onclick="listObj=document.getElementById('forder');upListItem();clearTimeout(x);" id="Button1" name="Button1"> 
 			<input  type="button" value="↓" onmousedown="setTimeStart('down');" onmouseup="clearTimeout(x);" 
 				onclick="listObj=document.getElementById('forder');downListItem();clearTimeout(x);" id="Button2" name="Button2">
			</td>
		</tr>
		<tr>
			<td width=130 height=63>
				<table>
					<tr>
						<td class="btn_ord2"  onclick="saveOrder();">保存</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input name="forderValue" id="forderValue" type="hidden" value=""/>
	<input name="nodeID" id="nodeID" type="hidden" value="<%=nodeID %>" />
	</form>
  </body>

</html>
