<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@page import="cn.com.sinosoft.ebusiness.cms.domain.CmsChannel" %>
<%
CmsChannel channel = (CmsChannel)request.getAttribute("channel");
String nodeID = channel.getChannelID()+"";
String channelType = channel.getChnlType();
boolean tag =((Boolean)request.getAttribute("tag")).booleanValue();
String docId = (String)request.getAttribute("docId");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<title>电子商务管理系统</title>
<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script>
	function doNode(){
		if(<%=channelType %>=="1"){
			alert("链接频道下不能新建子频道!");
			return;
		}
		parent.document.getElementById("fraBODYS").src="createColumns/frmCreate.jsp?nodeID=<%=nodeID%>";
	}
	function deleteNode(){
		var content = "";
		if("<%=nodeID %>"=="1"){
			alert("该栏目不可删除！");
			return;
		}else if(<%=tag %>){
			content = "该栏目下有子栏目，是否删除栏目?";
		}else if("<%=docId %>" != ""){
			content = "该栏目下有文章，是否删除栏目?";
		}else{
			content = "是否删除栏目?";
		}
		if(confirm(content)){
			document.all.getAPFrame.src="${ctx}/columnsManage/toDeleteNode.do?nodeID=<%=nodeID%>";
		}
	}
	function columnOrder(){
		parent.document.getElementById("fraBODYS").src="${ctx}/columnsManage/toGetChannelForOrderUpdate.do?nodeID=<%=nodeID %>";
	}
	function doUpdate(){
		parent.document.getElementById("fraBODYS").src="${ctx}/columnsManage/toGetChannelForFrmUpdate.do?nodeID=<%=nodeID %>";
	}
	function completeDelete(){
		parent.document.getElementById("fraBODYS").src="recycleBin/index.jsp";
	}	
</script>
</head>
<body topmargin="0" leftmargin="0">
<div class="cms_header_top_bg">
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick="doNode()">新建子栏目</div>
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick="doUpdate()">栏目属性</div>
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick="columnOrder()">栏目排序</div>
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick="deleteNode()">删除栏目</div>
<div class="cms_header_top_space"></div>
<!--<div class="cms_header_top_button" onclick="completeDelete()">回收站</div>
<div class="cms_header_top_space"></div>
--></div>
<iframe style="display:none" width="200" height="200" name="getAPFrame" id="getAPFrame"></iframe>
</body>
</html>
