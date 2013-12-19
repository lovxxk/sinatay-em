<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page import="net.fckeditor.FCKeditor"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

<%
	//获取FCK的基本地址
	/*
	String basePath = "/global/fckeditor";
	FCKeditor fc = new FCKeditor(request, "EditorDefault");
	fc.setBasePath(basePath);
	fc.setHeight("320px");
	fc.setInputName("legalNotice");
	fc.setConfig("SkinPath", "skins/office2003/");
	fc.setValue(""+request.getAttribute("legalNotice"));
	*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>电子商务管理系统-产品详细信息-法律声明</title>
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
			var oEditor = FCKeditorAPI.GetInstance('legalNotice');
			var checkContent = oEditor.GetXHTML();
			if(checkContent == "") {
				alert("法律声明内容不能为空！");
				oEditor.Focus();
				return false;
			}
			return true;
		}
	</script>
</head>
<body >
<div >
	<form id="frmInput" action="${ctx }/productManage/saveOrUpdateLegalNotice.do" method="post" target="myFrame">
	<div style="width:100%;margin-top:20px;">
		<div class="frmCreate_kuang">
			<div class="frmCreate_kuang_header" style="text-align:center;" >法律声明内容</div>
		</div>
		<div class="editor">
			<%--<div style="width:800px;float:left;"><%out.println(fc);%></div>--%>
			<%
				String legalNotice = (String)request.getAttribute("legalNotice");
			%>
			<div style="width:100%;float:left;">    <!-- (String)request.getAttribute("legalNotice");-->
				<FCK:editor instanceName="legalNotice" basePath="/global/fckeditor" height="320px" inputName="legalNotice" value="<%=legalNotice %>" >
					<FCK:config SkinPath="skins/office2003/"/>
				</FCK:editor>
			</div>
			
		</div>
		<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode'/>">
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
	<div id="edit"></div>
</div>
<iframe id="myFrame" name="myFrame" style="display:none"></iframe>
</body>
</html>
