<%@page import="cn.com.sinosoft.util.encode.EncodeUtils"%>
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="net.fckeditor.FCKeditor"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ page import="java.net.*"%>
<%
	request.setCharacterEncoding("GBK");
	response.setCharacterEncoding("GBK");
	
	//获取FCK的基本地址
	String basePath = "/global/fckeditor";
	
	String noticeValue = request.getParameter("noticeValue");
	String editorWidth = request.getParameter("editorWidth");
	String editorHeight = request.getParameter("editorHeight");
	FCKeditor fc = new FCKeditor(request, "EditorDefault");
	fc.setBasePath(basePath);
	fc.setWidth(editorWidth + "px");
	fc.setHeight(editorHeight + "px");
	fc.setInputName("content");
	fc.setConfig("SkinPath", "skins/office2003/");
	fc.setValue(noticeValue);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=GBK">
	<link href="<%= request.getContextPath() %>/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<title>电子商务管理系统-产品基本信息</title>
</head>
<body style="background-color:#a1a29c;">
	<form id="frmInput" name="frmInput" action="" onsubmit="return doConfirm();">
	<div style="float:left;left:0px;">
		<div style="width:'<%=editorWidth%>'px;"><%out.println(fc);%></div>
	</div>
	<div style="text-align:center;clear:both;">
		<input type="submit" value="应用" style="width:60px;">
		<input type="reset" onclick="quit();" value="取消" style="width:60px;">
	</div>
	</form>
</body>
<script type="text/javascript">
//alert(parent.document.getElementById("bbb"));
	var submitCount = 1;  //记录提交次数
	function doConfirm(){
		if(submitCount == 1){ //第一次提交直接返回false,并且1秒以后再执行validate().
			submitCount ++;
			setTimeout('doConfirm()',1000);
			return false;
		}
	
	    //第二此提交的时候直接可以从表单中取得值了.
	    var noticeInfo = document.getElementsByName("content")[0].value;
	    
	   
	    parent.selectedObj.value = noticeInfo;
	    window.parent.document.getElementById("myFrame").style.display = "none";
	    window.parent.document.getElementById("noticeInfo").disabled = false;
	    return true;
	}
	
	function quit(){
		window.parent.document.getElementById("myFrame").style.display = "none";
	    window.parent.document.getElementById("noticeInfo").disabled = false;
	}
</script>
</html>
