<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geGroupOperators" value="${requestScope.geGroupOperators}" />
<c:set var="roleExist" value="${requestScope.roleExist}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<title>电子商务后台管理系统-查看用户组详细</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			用户组详细
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="height:15px;clear:both;">&nbsp;</div>
<div class="public_list_title" style="color:#4aa78c;width:95%;">用户组信息如下：</div>
<table id="kuang" class="frmCreate_kuang" style="width:95%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="text-align:center;">用户组基本信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">用户组角色信息</td>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:290px;text-align:center;">用户组机构权限</td>
	
	</tr>
	<tr>
		<td style="width:270px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
			<table class="public_table1" width="95%" style="width:250px;">
			<tr>
				<td class="td_head" width="80px" nowrap>用户组编号：</td>
				<td class="td_body" width="190">${geGroup.groupid}</td>
			</tr>
			<tr>
				<td class="td_head" width="80px" nowrap>用户组名称：</td>
				<td class="td_body"width="190">${geGroup.groupname}</td>
			</tr>
			<tr height="5px">
				<td colspan="2"></td>
			</tr>
			<tr>
				<td colspan="2" class="frmCreate_kuang" valign="top">
					<div class="frmCreate_kuang_header" style="padding-left: 0px;">
						用户组描述：
					</div>
					<div style="padding-left:3px;height: 145px;overflow-y:auto;width: 270px;">
						<textarea class="textarea_disabled" style="height: 98%; " readonly="readonly">${geGroup.groupdesp}</textarea>
					</div>
				</td>
			</tr>
			
			</table>
		</td>
		<td valign="top" style="padding:0px;">
			<iframe id="framem" style="overflow-x:hidden;padding-left:0px;width:100%;height:100%;" src="${ctx}/system/groupManage/group/detail/role.jsp?groupid=${geGroup.groupid}&areaMap=${areaMap}" frameborder="0" scrolling="yes"></iframe>
		</td>
		<td style="width:270px;border-left:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
			<div id="showListLoading" style="position:absolute;">
				<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
			</div>
			<div id="groupDeptAuthTree" style="overflow-x:hidden;width:295px;"></div>
		</td>
	</tr>
	
</table>
<table style="width:95%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="3">
			<div class="public_list_title" style="color:#4aa78c;width:100%">该组用户信息如下：</div>
			<iframe id="ifameUser"  onload="iFrameHeight();" style="padding-left:0px;" frameborder="0" scrolling="no" width="100%"></iframe>
		</td>
	</tr>
</table>
<div style="padding-top:15px;"></div>
<table align="center">
	<tr>
		<c:if test="${geGroup.deptid == geOperator.deptid}">
		<acc:showView source="ROLE_S_GROUP_U">
		<td id="updateButton" class="btnBigOnBlur" width="82px" onmouseover="this.className='btnBigOnFocus'" 
			onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>编辑</td>
		</acc:showView>
		<acc:showView source="ROLE_S_GROUP_D">
		<td id="deleteButton" class="btnBigOnBlur" width="82px" onmouseover="this.className='btnBigOnFocus'" 
			onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>删除</td>
		</acc:showView>
		</c:if>
		<td id="closeButton" class="btnBigOnBlur" width="82px" onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>关闭</td>
	</tr>
</table>
<input type="hidden" id="bussMap" value="${areaMap}" />	
<form action="${ctx}/system/groupManage/update.do" id="frmInput" method="post" target="myFrame">
	<input type="hidden" name="id" value="${misUser.id}">
</form>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">

var kuang = document.getElementById("kuang");
kuang.style.height = document.body.clientHeight-420;
var groupDeptAuthTree = document.getElementById("groupDeptAuthTree");
groupDeptAuthTree.style.height = document.body.clientHeight-448;
var ifm=document.getElementById("ifameUser");
$("#ifameUser").attr("src", "${ctx}/system/groupManage/group/detail/user.jsp?groupid=${geGroup.groupid}&deptid=${geGroup.deptid}&areaMap="+UrlEncode("${areaMap}"));
function iFrameHeight(){
	var subWeb = document.frames ? document.frames["ifameUser"].document : ifm.contentDocument;
	if(ifm != null && subWeb != null) {
		ifm.height = document.frames ? subWeb.body.scrollHeight: subWeb.body.offsetHeight;
	} 
}
/******************tree js****************************/
var tree=new dhtmlXTreeObject("groupDeptAuthTree","100%","100%",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.loadXML("${ctx}/system/groupManage/groupDeptAuthDetailTree.do?id=0&groupId=${geGroup.groupid}",loadOver);
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);

function loadOver(){
	$("#showListLoading").hide();
	var groupDeptAuthTree = $("#groupDeptAuthTree").text();
	if(groupDeptAuthTree==''){
		$("#groupDeptAuthTree").html("<div style='color:#FF9000;font-weight:bold;text-align:center;padding-top:15px;'>提示：该组暂未分配机构权限</div>");
	}
}

function doEdit(){
	window.location.href = "${ctx}/system/groupManage/queryGeGroupAuthorityForUpdate.do?deptid=${geOperator.deptid}&id=${geGroup.groupid}&odeptid=${geOperator.deptid}";
}
function doDelete(){
	if(confirm("确定删除此用户组吗？")){
		$("#frmInput")[0].action =  "${ctx}/system/groupManage/delete.do?idStr=${geGroup.groupid}";
		$("#frmInput")[0].submit();
	}
}
function doUserSet(){
	window.location.href = "${ctx}/system/groupManage/queryGeGroupOperatorsForUpdate.do?id=${geGroup.groupid}";
}
function doAuthoritySet(){
	window.location.href = "${ctx}/system/groupManage/queryGeGroupAuthorityForUpdate.do?odeptid=${geOperator.deptid}&id=${geGroup.groupid}";
}
</script>

</html>
