<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>

<title>�༭�û�-�û�������</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			�û�������
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="height:550px;overflow-y:auto;">
	<div style="height:10px">&nbsp;</div>
	<div class="public_list_title" style="width:95%;color:#4aa78c;">�û����б���Ϣ���£�</div>
	<table align="center" cellpadding=0 cellspacing=0 border=0 style="width:95%;">
		<tr>
			<td class="td_head" width="80px" nowrap>�û����ţ�</td>
			<td class="td_body" width="140px">
				<input type="text" id="groupId" name="groupid" style="width:120px;" maxlength="20">
				<input type="hidden" id="userId" name="userId" value="${userId}">
			</td>
			<td class="td_head" width="80px" nowrap>�û������ƣ�</td>
			<td class="td_body" width="140px">
				<input type="text" id="groupName" name="groupName" style="width:120px;" maxlength="20">
			</td>
			<td class="td_head" width="80px" nowrap>�û������ͣ�</td>
			<td class="td_body" width="100px">
				<select id="groupTypeId" name="geGroup.grouptypeid" >
					<option value="">--��ѡ��--</option>
					<c:forEach items="${geGroupTypeList}" var="grouptype">
						<option value="${grouptype.grouptypeid}">${grouptype.grouptypename} </option>
					</c:forEach>
				</select>
			</td>
			<td>
				<table align="left">
					<tr>
						<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="queryGroups();" nowrap>��ѯ</td>
						<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>���</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table border=0 style="font-size:9pt;width:95%;" cellpadding=0 cellspacing=0 align="center"  id="groupTable">
		<tr id="title">
			<td class="search_head" width="40px"><input type="checkbox" onclick="checkAll(this)" style="border:0px solid black;"></td>
			<td class="search_head" width="60px" nowrap id="serial">���</td>
			<td class="search_head" width="20%" nowrap id="groupId">�û�����</td>
			<td class="search_head" nowrap id="groupName">�û�������</td>
			<td class="search_head" width="20%" nowrap id="groupType">�û�������</td>
			<td class="search_head" width="80px" nowrap id="operate">����</td>
		</tr>
		<c:forEach items="${geOperatorGroupList}" var="geGroup" varStatus="status">
		<tr>
			<td class="search_body_center">
			<input type="checkbox"  name="checkbox_roleAll" value="${geGroup[0].groupid}" style="border:0px solid black;" ${geGroup[1] eq 1?"checked":""}>
			</td>
			<td class="search_body_center" nowrap> ${status.index+1}&nbsp;</td>
			<td class="search_body_center" nowrap>${geGroup[0].groupid}&nbsp;</td>
			<td class="search_body_center" nowrap>${geGroup[0].groupname}&nbsp;</td>
			<td class="search_body_center" nowrap>${geGroup[0].grouptypename}&nbsp;</td>
			<td class="search_body_center" nowrap>
				<span style="width:20px;"><a class="zc-lk1" href="javascript:showGroupDetail('${geGroup[0].groupid}');">��ϸ</a></span>
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
					onmouseout="this.className='btnBigOnBlur'" onclick="updateGroupForUser();" nowrap>�������� </td>
				<td id="closeButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="returnPage();" nowrap>����</td>
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
				<span style="width:20px;"><a class="zc-lk1 detailClass">��ϸ</a></span>
			</td>
		</tr>
	</table>
	<input type="hidden" id="createUserId" name="createUserId" value="${createUserId}">
	<iframe id="myFrame" name="myFrame" style="display:none;"></iframe>
</div>
</body>
<script type="text/javascript">
function returnPage(){
	window.location.href="${ctx}/system/userManage/queryGeOperatorForDetail.do?geOperator.operatorid=${userId}";
}
function checkAll(obj){
	var checkArray = document.getElementsByName("checkbox_roleAll");
	for(var i = 0; i < checkArray.length; i++){
		checkArray[i].checked = obj.checked;
	}
}


function getCheckedUpdateGroups(){
	var checkedUpdateGroups = "";
	$("#groupTable input:gt(0):checked").each(function(){
		if(checkedUpdateGroups == ""){
			checkedUpdateGroups = $(this).val();
		}else{
			checkedUpdateGroups += "," + $(this).val();
		}
	});
	return checkedUpdateGroups;
}
function getAllUpdateGroups(){
	var allUpdateGroups = "";
	$("#groupTable input:gt(0)").each(function(){
		if(allUpdateGroups == ""){
			allUpdateGroups = $(this).val();
		}else{
			allUpdateGroups += "," + $(this).val();
		}
	});
	return allUpdateGroups;
}


function updateGroupForUser(){
	var checkedUpdateGroups=getCheckedUpdateGroups();
    var allUpdateGroups=getAllUpdateGroups(); 
	$.ajax({
		cache :false,
		type: "POST",
		url:"${ctx}/system/userManage/updateGroupForUser.do",
		data: {"allUpdateGroups":allUpdateGroups,"checkedUpdateGroups":checkedUpdateGroups,"userId":$("#userId").val()},
		dataType:"json",
		success: function(data){
			if(data == "success"){
				alert('�޸ĳɹ���');
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
		url:"${ctx}/system/userManage/queryContainUserGroups.do?groupname="+$("#groupName").val(),
		data: {"groupid":$("#groupId").val(),"grouptypeid":$("#groupTypeId").val(),"userId":$("#userId").val()},
		dataType:"json",
		success: function(data){
			if(data == "false"){
				alert("��ѯʧ��");
			}else{
				$("#groupTable").find("tr:gt(0)").remove();
				$.each(data, function(index, item){
					var num = parseInt(index+1);
					var row = $("#demo").find("tr:first").clone();
					row.find(".checkbox_roleAll").val(item[0].groupid);
					if(item[1]=='1'){
						row.find(".checkbox_roleAll").attr("checked",true);
					}
					row.find(".serialClass").text(num);
					row.find(".groupIdClass").text(item[0].groupid);
					row.find(".groupNameClass").text(item[0].groupname);
					row.find(".groupTypeClass").text(item[0].grouptypename);
					row.find(".detailClass").attr("href","javascript:showGroupDetail('"+item[0].groupid+"');");
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
 * �鿴�û�����ϸ
 */
function showGroupDetail(idStr){
	 window.open("${ctx}/system/groupManage/queryGeGroupDetail.do?id=" + idStr,"�鿴�û�����ϸ" ,"top=200, left=300, width=900,height=600,toolbar=no,scrollbars=yes");
}

function doClear(){
	$("#groupName").val("");
	$("#groupId").val("");
	$("#groupTypeId").val("");
}


</script>
</html>