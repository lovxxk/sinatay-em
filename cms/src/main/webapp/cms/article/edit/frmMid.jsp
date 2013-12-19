<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String nodeID = request.getParameter("ID")== null ? "" : request.getParameter("ID").trim();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<title>新建文章</title>
<script type="text/javascript">
function next(){
	var docTypeObj=document.getElementById("docType");
	var value="";	
	for(var i=0;i<docTypeObj.length;i++){
		if(docTypeObj.options[i].selected){
			value=docTypeObj.options[i].value;
			}
	}
	if(value=="0"){
		window.open("${ctx}/articleManage/toGetChannelForFrmEdit.do?nodeID="+"<%=nodeID%>","","top=100, left=100, width=900,height=800,toolbar=no,scrollbars");
	}else if(value=="1"){
		window.open("${ctx}/articleManage/toGetChannelForFrmEditLink.do?nodeID=<%=nodeID%>","","top=100, left=100, width=900,height=375,toolbar=no,scrollbars");
	}
	//else{
		//window.open("${ctx}/articleManage/toGetChannelForFrmEditEnc.do?nodeID=<%=nodeID%>","","top=100, left=100, width=900,height=375,toolbar=no,scrollbars");
	//}
}

</script>
</head>
<table width="100%" cellpadding=0 cellspacing=0>
	<tr>
		<td style="BACKGROUND: url(${ctx }/cms/global/images/cms_tc_top_bg.jpg);font-size:13px;color:#4a4c4b;font-weight:bold;" width=135 height=36 valign="top">&nbsp;<img src="${ctx }/cms/global/images/cms_tc_top_ico.jpg">&nbsp;新建文档</td>
		<td valign="top" width=37><img src="${ctx }/cms/global/images/cms_tc_top_se.jpg"></td>
		<td background="${ctx }/cms/global/images/cms_tc_top_bg2.jpg" valign="top" width=1328></td>
	</tr>
	<tr>
		<td colspan=3 height=30></td>
	</tr>
	<tr>
		<td colspan=3 style="BACKGROUND: url(${ctx }/cms/global/images/cms_tc_foot_bg.jpg) repeat-x bottom;vertical-align:top;" height=160 align=center>
		<table border=0 width=400 cellpadding=0 cellspacing=0 class="table_Show">
			<tr>
				<td class="td_in1" width=120 nowrap height=40>请选择文档类型</td>
				<td width=2 nowrap></td>
				<td class="td_in2" width=348>&nbsp;
					<select name="docType" id="docType">
						<option value=0>普通文章</option>
						<!--
						<option value=1>链接文章</option>
						<option value=2>附件文章</option>-->
					</select>
				</td>
			</tr>
			<tr>
				<td colspan=3 height=60 align="right">
					<table>
						<tr>
							<td class="btn_ord2" onclick="javascript:next();" nowrap>下一步</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan=3 height=2 align="right">
					<table width="100%">
						<tr>
							<td height=2  style="background: url(../../global/images/cms_tc_foot_line.jpg)">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan=3 height=2 align="right"></td>
			</tr>
		</table>		
		</td>
	</tr>
</table>
