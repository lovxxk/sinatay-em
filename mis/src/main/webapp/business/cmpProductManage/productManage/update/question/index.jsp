<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head> 
	<title>电子商务管理系统-产品详细信息-调查问卷</title>
	<meta http-equiv="content-type" content="text/html; charset=GBK">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body>
	<s:form name="frmInput" id="frmInput" action="questionPageDoFileUpload" method="post" enctype="multipart/form-data" target="">
	<div style="height:50px"></div>
	<div class="editor" style="text-align:left;text-align:center;padding-bottom:10px;">
		<table id="productTable" class="table_style" align="center">
		<tr>
			<td class="td_head td_head_center" width="190px" nowrap>请选择要上传的调查问卷文件：</td>
			<td class="td_body">
				<s:file id="productUploadFile" name="productUploadFile" label="File"></s:file>
			</td>
		</tr>
		</table>
	</div>
	<table align="center" style="margin-bottom:30px;">
	<tr>
		<td id="SubmitButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" nowrap>上传</td>
		<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.close();" nowrap >关闭</td>
	</tr>
	</table>
	<input type="hidden" id="elementCode" name="elementCode" value="${param.elementCode}" />
	<input type="hidden" id="coreProductCode" name="coreProductCode" value="${param.coreProductCode}" />
	</s:form>
	<iframe id="myFrame" name="myFrame" style="display:none"></iframe>
</body>
<script type="text/javascript" >

//表单提交
$("#SubmitButton").click(function(){
		if($("#productUploadFile").val()==null||$("#productUploadFile").val()==""){
			alert('请选择要上传的文件');	
			return false;
		}
		if($("#productUploadFile").val().indexOf(".jsp") <= 0){
			alert("只能上传jsp文件");
			document.getElementById("productUploadFile").focus();
			return false;
		}
		$("#frmInput").submit();
});
</script>
</html>
