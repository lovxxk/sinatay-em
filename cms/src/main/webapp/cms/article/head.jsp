<%@page import="cn.com.sinosoft.ebusiness.cms.domain.CmsChannel"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	%>
<%@ include file="/global/ui/taglibs.jsp"%>

<%
	CmsChannel channel = (CmsChannel)request.getAttribute("channel");
	String nodeID = channel.getChannelID() + "";
	String chnlType = channel.getChnlType();
	if("4".equals(chnlType)){
		nodeID = channel.getMirrorID();
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<title>电子商务管理系统</title>
<script>
	function doCreate(){
		if("4"==<%=chnlType%>){
			alert("镜像栏目不允许新建文章！");
			return;
		}
		if(<%=chnlType%>=="1"){
			alert("链接栏目不允许新建文章!");
			return;
		}
//		window.open("${ctx}/cms/article/edit/frmMid.jsp?ID="+"<%=nodeID%>","_blank","width=500,height=226,toolbar=no,scrollbars,top=200,left=300");
		parent.document.getElementById("fraBODYS").src="edit/frmMid.jsp?ID=<%=nodeID%>";
	}
	function doSearch(){
		parent.document.getElementById("fraBODYS").src="search/index.jsp?nodeID=<%=nodeID%>";
	}
	function copyArticle(){
		parent.document.getElementById("fraBODYS").src="copyArticle/index.jsp";
	}
	function completeDelete(){
		parent.document.getElementById("fraBODYS").src="recycleBin/index.jsp";
	}	
</script>
</head>
<body topmargin="0" leftmargin="0">
<div class="cms_header_top_bg">
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick="doCreate()">新建文章</div>
<div class="cms_header_top_space"></div>
<div class="cms_header_top_button" onclick="doSearch()">编辑/删除文章</div>
<div class="cms_header_top_space"></div>
<!--<div class="cms_header_top_button" onclick="copyArticle()">复制栏目文章</div>
<div class="cms_header_top_space"></div>
--><!--<div class="cms_header_top_button" onclick="completeDelete()">回收站</div>
<div class="cms_header_top_space"></div>
--></div>
</body>
</html>
