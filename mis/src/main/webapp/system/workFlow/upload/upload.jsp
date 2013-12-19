<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx }/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx }/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<div class="select_header_top_bg">
	<div class="select_header_top_left1"></div>
	<div class="select_header_top_left2"></div>
	<div class="select_header_top_title">
		<div class="public_fun_title">工作流配置文件上传</div>
	</div>
	
	
	<div class="select_header_top_right1"></div>
	<div class="select_header_top_right2"></div>
</div>
<div style="width:100%;height:30px;">&nbsp;</div>
<form enctype="multipart/form-data" action="${ctx }/workFlow/getUploadAndDeploy.do" method="post" id="frmInput">
<table class="table_style" align="center"  width="650px" id="tableShow">
<tr>
	<td class="td_head td_head_center" nowrap>文件类型：	</td>
	<td class="td_body">
		<select name="geWorkflow.id.filetype" style="width:200px" onchange="doConfig(this)" id="fileType">
			<option value="0">任务人员配置文件</option>
			<option value="1">工作流流程配置文件</option>
		</select>
	</td>
</tr>
<tr id="area" style="display: none">
	<td class="td_head td_head_center" width="180px" nowrap>业务领域：</td>
	<td class="td_body">
		<select name="geWorkflow.id.area" style="width:200px" id="areas">
			<option value="">--请选择--</option>
			<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
				<option value="${GeCode_businessArea.id.codeCode}">${GeCode_businessArea.codeCName}</option>
			</c:forEach>
		</select>
	</td>
</tr>	
<tr id="funcitontype" style="display: none">
	<td class="td_head td_head_center" nowrap>工作流功能：	</td>
	<td class="td_body">
		<select name="geWorkflow.id.funcitontype" style="width:200px" id="funcitontypes">
			<option value="">--请选择--</option>
			<c:forEach items="${workFlow}" var="workflow">
				<option value="${workflow.key }">${workflow.value }</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td class="td_head td_head_center" nowrap>上传流程图：</td>
	<td class="td_body">
		<input type="file" name="file" id="file" size="50"/>
	</td>
</tr>
<tr height=25><td></td></tr> 
<tr>
	<td colspan=2>
		<table width=200 align="center">
		<tr>
			<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>上传文件 </td>
			<td width=5>&nbsp;</td>
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</form>

<script>
tt.vf.req.addName("geWorkflow.id.area","geWorkflow.id.funcitontype","geWorkflow.id.filetype");
function doCreate(){
	if($("#fileType").val() == 0){
		tt.vf.req.rmName("geWorkflow.id.area","geWorkflow.id.funcitontype");
	}else{
		tt.vf.req.addName("geWorkflow.id.area","geWorkflow.id.funcitontype");
	}
	if(tt.validate()){
		if($("#file").val()==null||$("#file").val()==""){
			alert('请选择要上传的文件');	
			return false;
		}
		if($("#file").val().indexOf(".xml") <= 0){
			alert("只能上传xml文件");
			document.getElementById("file").focus();
			return false;
		}
		if($("#fileType").val() == 0){
			$("#funcitontype").hide();
			$("#area").hide();
			$("#funcitontypes").val("WORKFLOWCONFIG");
			$("#areas").val("CC");
		}
		document.getElementById("frmInput").submit();
	}
}
function doConfig(obj){
	if($(obj).val() == 0){
		$("#funcitontype").hide();
		$("#area").hide();
		tt.vf.req.rmName("geWorkflow.id.area","geWorkflow.id.funcitontype");
	}else{
		$("#funcitontype").show();
		$("#area").show();
		$("input[name='geWorkflow.id.area']").val("");
		$("input[name='geWorkflow.id.funcitontype']").val("");
		tt.vf.req.addName("geWorkflow.id.area","geWorkflow.id.funcitontype");
	}
}
function doClear(){
	document.getElementById("frmInput").reset();
}
</script>
</body>
</html>