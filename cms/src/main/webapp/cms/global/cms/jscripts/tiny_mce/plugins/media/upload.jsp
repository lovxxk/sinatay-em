<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
</head>
<body>
<form id="frmUpload" name="frmUpload" enctype="multipart/form-data" method="post" target="_self" action="saveMedia.jsp">
	<table class="properties">
		<tr> 
			<td class="column1"><label id="titlelabel" for="title">上传</label></td> 
			<td colspan="2"><input id="upload" name="upload" type="file"  value=""/></td> 
		</tr>
	</table>
	<input id="docID" name="docID" type="hidden" value="" />
	<input id="tag" name="tag" type="hidden" value="" />
	<input type="submit" style="WIDTH:   100px;   POSITION:   absolute;   HEIGHT:   20px" value="上传" />
</form>
</body>
<script>
	var ID=window.top.opener.parent.document.getElementById("ID").value;
	var tag=window.top.opener.parent.document.getElementById("tag").value;
	document.getElementById("docID").value=ID;
	document.getElementById("tag").value=tag;
</script>
</html>

