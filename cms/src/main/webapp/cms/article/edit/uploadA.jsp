<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<script type="text/javascript">

function upload(){
	var aa=window.opener.document.getElementById("a").contentWindow.document;
	var aaNum=aa.getElementById("attNum").value;
	var aaInt=parseInt(aaNum);
	var oNewNode2 = aa.createElement("input");
	oNewNode2.setAttribute("type","text");
	oNewNode2.name="FileName"+aaInt;
	oNewNode2.id ="FileName"+aaInt;
	oNewNode2.value=document.getElementById("filename").value
	aa.getElementById("abc").appendChild(oNewNode2);
	aa.getElementById("attNum").value=aaInt;
	var bbshow=window.opener.document.getElementById("show");
	if(!oNewNode2.value==""){
		var newspan=window.opener.document.createElement("span");
		newspan.id="newspan"+aaInt;
		newspan.innerHTML=oNewNode2.value+"<br><a href='#' onclick=deleteSpan('"+aaInt+"') >删除</a>";
		newspan.innerHTML+="&nbsp;&nbsp;<a href='#' onclick=updateSpan('"+aaInt+"') >修改名称</a><br/>";
		bbshow.appendChild(newspan);
	}	
	
	window.close();
}

function getfile(){
	var aa=window.opener.document.getElementById("a").contentWindow.document;
	var aaNum=aa.getElementById("attNum").value;
	var aaInt=parseInt(aaNum);
	var oNewNode1 = aa.createElement("input");
	oNewNode1.setAttribute("type","file");
	oNewNode1.name="UploadFileAdd"+aaInt;
	oNewNode1.id ="UploadFileAdd"+aaInt;
	aa.getElementById("abc").appendChild(oNewNode1);	
	oNewNode1.click();
	
	var filename=window.opener.getFileName(oNewNode1.value);
	document.getElementById("filename").value=filename;	
	document.getElementById("filename").focus();
	
	aaInt++;
	aa.getElementById("attNum").value=aaInt;
}

</script>
</head>
<body topmargin=0 leftmargin=0>
<table border="0" width="100%" cellpadding="0" cellspacing="0">
<tr>
	<td style="BACKGROUND: url(../../../global/images/cms_tc_top_bg.jpg);font-size:13px;color:#4a4c4b;font-weight:bold;" width=135 height=36 valign="top">&nbsp;&nbsp;<img src="../../../global/images/cms_tc_top_ico.jpg">&nbsp;&nbsp;新建附件</td>
	<td valign="top" width=37><img src="../../../global/images/cms_tc_top_se.jpg"></td>
	<td background="../../../global/images/cms_tc_top_bg2.jpg" valign="top" width=328></td>
</tr>
<tr>
	<td colspan=3 height=30></td>
</tr>
<tr>
	<td width ="30%" class="font_t1" align="right" height=50>附件上传地址：</td>
	<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>&nbsp;&nbsp;</td>
				<td class="btn_ord2" onclick="getfile();" width="100">&nbsp;&nbsp;浏览</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td width ="30%" class="font_t1" align="right" height=50>附件显示名称：</td>
	<td width ="70%" colspan="2">&nbsp;&nbsp;<input type="text" id="filename" name="filename" value=""/></td>
</tr>
<tr align="center">
	<td colspan="3">
		<table border="0" width="100" cellpadding="0" cellspacing="0">
			<tr>
				<td class="btn_ord2" onclick="upload();">&nbsp;&nbsp;确定</td>
			</tr>
		</table>
	</td>
</tr>
</table>
</body>
</html>
