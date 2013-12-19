<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ page import="net.fckeditor.FCKeditor"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%
	//获取FCK的基本地址
	/*
	String basePath = "/global/fckeditor";
	FCKeditor fc = new FCKeditor(request, "proposalNotice");
	fc.setBasePath(basePath);
	fc.setHeight("320px");
	fc.setInputName("proposalNotice");
	fc.setConfig("SkinPath", "skins/office2003/");
	fc.setValue((String)request.getAttribute("proposalNotice"));
	*/
%>

<html>
<head>
	<title>电子商务管理系统-产品详细信息-投保声明</title>
	<meta http-equiv="content-type" content="text/html; charset=GBK">
	<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript">
		function doSave(){
			if(checkEditor()) {
				$("#frmInput").submit();
			}
		}
		function checkEditor() {
			var oEditor = FCKeditorAPI.GetInstance('proposalNotice');
			var checkContent = oEditor.GetXHTML();
			if(checkContent == "") {
				alert("投保声明内容不能为空！");
				oEditor.Focus();
				return false;
			}
			return true;
		}
	</script>
</head>
<body style="margin:0 auto;">
<div style="width:100%;text-align:center;">
	<form id="frmInput" action="${ctx }/productManage/saveOrUpdateProposalNotice.do" method="post" target="myFrame">
	<div style="width:100%;margin-top:20px;">
		<div class="frmCreate_kuang">
			<div class="frmCreate_kuang_header" style="text-align:center;" >投保声明内容</div>
		</div>
		<%-- <div class="editor"><div style="width:800px;float:left;"><%out.println(fc);%></div></div>--%>
		
		<%
			String proposalNotice = (String)request.getAttribute("proposalNotice");
		%>
		<div style="width:100%;float:left;">
			<FCK:editor instanceName="proposalNotice" basePath="/global/fckeditor" height="320px" inputName="proposalNotice" value="<%=proposalNotice%>" >
				<FCK:config SkinPath="skins/office2003/"/>
			</FCK:editor>
		</div>
		<input type="hidden" name="geProductMain.coreProductCode" value="${geProductMain.coreProductCode }">
		<table align="center" style="margin-bottom:10px;padding-top:10px;">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doSave();" nowrap >保存</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.close();" nowrap >关闭</td>
			</tr>
		</table>
	</div>
	</form>
	<div id="edit">
	</div>
</div>
<iframe id="myFrame" name="myFrame" style="display:none"></iframe>
</body>
</html>
