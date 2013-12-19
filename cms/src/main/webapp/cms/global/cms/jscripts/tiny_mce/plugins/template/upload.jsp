<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/imgPreview/CJL.0.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/global/js/imgPreview/ImagePreviewd.js"></script>
</head>
<body>
<form id="frmUpload" name="frmUpload" enctype="multipart/form-data" method="post" target="_self" action="${ctx}/articleManage/updateImg.do?type=att">
	<table class="properties">
		<tr> 
			<s:file id="upload" name="upload" onchange="addPreviewFace(this.id);" label="上传"></s:file><br/>
		</tr>
	</table>
	<input id="docID" name="docID" type="hidden" value="" />
	<input id="tag" name="tag" type="hidden" value="" />
	<input id="fileName" name="fileName" type="hidden" value="" />
	<input type="submit" style="WIDTH:   100px;   POSITION:   absolute;   HEIGHT:   20px" value="上传" />
</form>
</body>
<script>
	$(document).ready(function(){
	});

	var ID=window.top.opener.parent.document.getElementById("ID").value;
	var tag=window.top.opener.parent.document.getElementById("tag").value;
	document.getElementById("docID").value=ID;
	document.getElementById("tag").value=tag;
	
	
	function addPreviewFace(obj){
		document.getElementById('fileName').value = document.getElementById('upload').value;
	}
</script>
</html>

