<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>

<title>新建用户-用户组设置</title>
</head>
<body>
<div class="public_fun_title">新建用户-用户组设置</div>
<div style="height:10px">&nbsp;</div>
<div class="public_list_title" style="width:95%;color:#4aa78c;">用户组列表信息如下：</div>
<table align="center" cellpadding=0 cellspacing=0 border=0 style="width:95%;">
	<tr>
		<td class="td_head" width="80px" nowrap>用户组编号：</td>
		<td class="td_body" width="140px">
			<input type="text" id="groupId" name="groupid" style="width:120px;" maxlength="20">
		</td>
		<td class="td_head" width="80px" nowrap>用户组名称：</td>
		<td class="td_body" width="140px">
			<input type="text" id="groupName" name="groupName" style="width:120px;" maxlength="20">
		</td>
		<td class="td_head" width="80px" nowrap>用户组类型：</td>
		<td class="td_body" width="100px">
			<select id="groupTypeId" name="geGroup.grouptypeid" >
				<option value="">--请选择--</option>
				<c:forEach items="${geGroupTypeList}" var="grouptype">
					<option value="${grouptype.grouptypeid}">${grouptype.grouptypename} </option>
				</c:forEach>
			</select>
		</td>
		<td>
			<table align="left">
				<tr>
					<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="queryGroups();" nowrap>查询</td>
					<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>清空</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<table border=0 style="font-size:9pt;width:95%;" cellpadding=0 cellspacing=0 align="center"  id="groupTable">
	<tr id="title">
		<td class="search_head" width="40px"><input type="checkbox" onclick="checkAll(this)" style="border:0px solid black;"></td>
		<td class="search_head" width="60px" nowrap id="serial">序号</td>
		<td class="search_head" width="20%" nowrap id="groupId">用户组编号</td>
		<td class="search_head" nowrap id="groupName">用户组名称</td>
		<td class="search_head" width="20%" nowrap id="groupType">用户组类型</td>
		<td class="search_head" width="80px" nowrap id="operate">操作</td>
	</tr>
	<c:forEach items="${geGroupList}" var="geGroup" varStatus="status">
	<tr>
		<td class="search_body_center">
			<input type="checkbox"  name="checkbox_roleAll" value="${geGroup.groupid}" style="border:0px solid black;">
		</td>
		<td class="search_body_center" nowrap> ${status.index+1}&nbsp;</td>
		<td class="search_body_center" nowrap>${geGroup.groupid}&nbsp;</td>
		<td class="search_body_center" nowrap>${geGroup.groupname}&nbsp;</td>
		<td class="search_body_center" nowrap>${geGroup.grouptypename}&nbsp;</td>
		<td class="search_body_center" nowrap>
			<span style="width:20px;"><a class="zc-lk1" href="javascript:showGroupDetail('${geGroup.groupid}');">详细</a></span>
		</td>
	</tr>
	</c:forEach>
</table>
<table align="center" height="60px;">
<tr>
	<td>
		<table width="100%" align="center">
			<tr>
			<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="addUserToGroup();" nowrap>保存设置 </td>
		</table>
	</td>
</tr>
</table>
<table id="demo" style="display:none">
	<tr>
		<td class="search_body_center">
			<input type="checkbox" class="checkbox_roleAll" name="checkbox_roleAll" style="border:0px solid black;">
		</td>
		<td class="search_body_center serialClass" nowrap> </td>
		<td class="search_body_center groupIdClass" nowrap></td>
		<td class="search_body_center groupNameClass" nowrap></td>
		<td class="search_body_center groupTypeClass"  nowrap></td>
		<td class="search_body_center" nowrap>
			<span style="width:20px;"><a class="zc-lk1 detailClass">详细</a></span>
		</td>
	</tr>
</table>
<input type="hidden" id="createUserId" name="createUserId" value="${createUserId}">
<iframe id="myFrame" name="myFrame" style="display:none;"></iframe>
</body>
<script type="text/javascript">
function checkAll(obj){
	var checkArray = document.getElementsByName("checkbox_roleAll");
	for(var i = 0; i < checkArray.length; i++){
		checkArray[i].checked = obj.checked;
	}
}


function getCheckedOperators(){
	var checkedOperators = "";
	var checkArray = document.getElementsByName("checkbox_roleAll");
	for(var i = 0; i < checkArray.length; i++){
		if(checkArray[i].checked == true){
			if(checkedOperators == ""){
				checkedOperators = checkArray[i].value;
			}else{
				checkedOperators += "," + checkArray[i].value;
			}
		}
	}
	return checkedOperators;
}



function addUserToGroup(){
	var checkGeGroup=getCheckedOperators();
       
	$.ajax({
		cache :false,
		type: "POST",
		url:"${ctx}/system/userManage/addUserToGroup.do",
		data: {"checkGeGroup":checkGeGroup,"createUserId":$("#createUserId").val()},
		dataType:"json",
		success: function(data){
			if(data == "success"){
				if(confirm("新建用户成功,是否继续添加用户?")){
					window.location = "${ctx}/system/userManage/user/create/frmCreate.jsp";
				}else{
					document.getElementById("myFrame").src = "${ctx}/system/userManage/createResult.do";
				}
			}else{
				alert(data);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert(errorThrown);
		}
	}); 
}

function queryGroups(){
	$.ajax({
		cache :false,
		type: "POST",
		url:"${ctx}/system/userManage/queryGroups.do?groupname="+$("#groupName").val(),
		data: {"groupid":$("#groupId").val(),"grouptypeid":$("#groupTypeId").val()},
		dataType:"json",
		success: function(data){
			if(data == "false"){
				alert("查询失败");
			}else{
				$("#groupTable").find("tr:gt(0)").remove();
				$.each(data, function(index, item){
					var num = parseInt(index+1);
					var row = $("#demo").find("tr:first").clone();
					row.find(".checkbox_roleAll").val(item.groupid);
					row.find(".serialClass").text(num);
					
					row.find(".groupIdClass").text(item.groupid);
					row.find(".groupNameClass").text(item.groupname);
					row.find(".groupTypeClass").text(item.grouptypename);
					row.find(".detailClass").attr("href","javascript:showGroupDetail('"+item.groupid+"');");
					$("#groupTable").append(row);
				});
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert(errorThrown);
		}
	}); 
}

/**
 * 查看用户组详细
 */
function showGroupDetail(idStr){
	 window.open("${ctx}/system/groupManage/queryGeGroupDetail.do?id=" + idStr,"查看用户组详细" ,"top=200, left=300, width=900,height=600,toolbar=no,scrollbars=yes");
}

function doClear(){
	$("#groupName").val("");
	$("#groupId").val("");
	$("#groupTypeId").val("");
}


</script>
</html>