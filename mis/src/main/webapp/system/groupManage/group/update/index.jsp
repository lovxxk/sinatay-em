<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[type='checkbox']").addClass("checkbox_border");
	});
</script>
<title>电子商务后台管理系统-编辑用户组信息</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			编辑用户组
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/system/groupManage/update.do" name="frmInput" id="frmInput" method="post" target="myFrame">
	<table class="table_style" align="center" width="680px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="150px" nowrap>用户组编号：</td>
		<td class="td_body" width="530px" >
			${geGroup.groupid}
			<input type="hidden" id="groupId" name="geGroup.groupid" style="width:170px;" value="${geGroup.groupid}" maxlength=20 readonly>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>用户组名称：</td>
		<td class="td_body" >
			<input id="groupName" name="geGroup.groupname" type="text" value="${geGroup.groupname}" style="width:170px;" maxlength=15>
			<span id="groupName_msg"></span>
	</tr>
	
	<tr>
		<td class="td_head" style="height:30px;" nowrap>用户组类型：</td>
		<td class="td_body" >
			<select id="groupTypeId" name="geGroup.grouptypeid">
				<option value="">--请选择--</option>
				<c:forEach items="${geGrouptypeList}" var="grouptype" varStatus="status">
				<option value="${grouptype.grouptypeid}"  ${geGroup.grouptypeid==grouptype.grouptypeid?"selected":""}>${grouptype.grouptypename}</option>
				</c:forEach>
			</select>
			<span id="groupTypeId_msg"></span>
			<input type="hidden" id="groupTypeName" name="geGroup.grouptypename" value="">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>用户组描述：</td>
		<td class="td_body" >
			<textarea  id="groupDesp" name="geGroup.groupdesp" style="width:520px;" rows="5">${geGroup.groupdesp}</textarea>
		</td>
	</tr>
	
	<tr><td>&nbsp;</td><td><span  id="groupDesp_msg"></span></td></tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width=85 align="center">
			<tr>
				<td id="updateButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>修改</td>
				<td id="backButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>返回</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<input type="hidden" name="id" value="${misUser.id}">
	<input type="hidden" id="groupTypeName" name="geGroup.grouptypename" value="${geGroup.grouptypename} ">
	</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
function doUpdate(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}
$("#groupTypeId").change(function(){
	$("#groupTypeName").attr("value",$("#groupTypeId").find("option:selected").text());
});
function doBack(){
	window.location.href="${ctx}/system/groupManage/queryGeGroupDetail.do?id=${geGroup.groupid}";
}
</script>

<script type="text/javascript">
	//设置显示区域
	var groupName =new tt.Field("","","groupName").setMsgId("groupName_msg");
	var groupDesp =new tt.Field("","","groupDesp").setMsgId("groupDesp_msg");
	var groupTypeId =new tt.Field("","","groupTypeId").setMsgId("groupTypeId_msg");
	//非空验证
	tt.vf.req.add(groupName,groupTypeId);
	new tt.LV().set(0,500).add(groupDesp);
	//针对不同需求的正则表达式验证
	new tt.RV().set(new RegExp(/^([\u4E00-\u9FA5]|\w){0,100}$/),"不能多于100个字!").add(groupDesp);
</script>
</html>
