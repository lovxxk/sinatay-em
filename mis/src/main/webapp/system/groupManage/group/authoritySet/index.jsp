<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>电子商务后台管理系统-权限设置</title>
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
<div style="height:10px">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" width="95%" align="center" cellspacing="0" cellpadding="0" >
	<tr>
		<td class="frmCreate_kuang_header" style="text-align:center;">修改用户组基本信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">全部角色信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;width:300px;">机构信息</td>
	</tr>
	<tr>
		<td style="width:270px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
				<form action="${ctx}/system/groupManage/update.do" id="frmInput" method="post" target="myFrame">
					<table class="table_style" align="center" width="100%" id="productTable">
					<tr>
						<td height=10>&nbsp;</td>
					</tr>
					<tr>
						<td class="td_head" width="80px" nowrap>用户组编号：</td>
						<td class="td_body">
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
						</td>
					</tr> 
					<tr>
						<td class="td_head" nowrap>用户组描述：</td>
						<td class="td_body" >
							<textarea  id="groupDesp" name="geGroup.groupdesp" style="width:220px;" rows="5" >${geGroup.groupdesp}</textarea>
						</td>
					</tr>
					<tr><td>&nbsp;</td><td><span  id="groupDesp_msg"></span></td></tr>
					</table>
				<input type="hidden" name="id" value="${misUser.id}">
				<input type="hidden" id="groupTypeName" name="geGroup.grouptypename" value="${geGroup.grouptypename} ">
				</form>
		</td>
		<td valign="top">
			<div id="list" style="overflow-y:scroll;">
				<table class="public_table1" style="width:300px;">
	 				<tr id="roleAll_menu">
						<td class="search_head" width="10%"><input type="checkbox" onclick="checkAll(this)"></td>
						<td class="search_head" width="30%">角色ID</td>
						<td class="search_head">角色名称</td>
					</tr>
		 			<c:forEach items="${roleAll}" var="roleAll" begin="0" end="${fn:length(roleAll)}" step="1" varStatus="status">
						<tr id="tr_roleAll_${roleAll.ROLEID}">
							<td class="search_body_center">
								<input type="checkbox"  name = "checkbox_roleAll" value="${roleAll.ROLEID}" ${roleAll.STATUS==0?'checked':''}>
							</td>
							<td class="search_body_center">${roleAll.ROLEID}</td>
							<td class="search_body_center">${roleAll.ROLENAME}</td>
						</tr>
					</c:forEach>
	 			</table>
	 		</div>
		</td>
		<td valign="top">
			<div id="showListLoading" style="position:absolute;">
				<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
			</div>
			<div id="authorityTree" style="overflow-x:hidden;width:305px;"></div>
		</td>
	</tr>
</table>
<table align="center" height="60px;">
<tr>
	<td>
		<table width="100%" align="center">
			<tr>
			<td id="backButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>返回</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" id="saveDept">修改</td>
			<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" id="saveDept"  onclick="doClear();">重置</td>
					
			<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >关闭</td>
				</tr>
		</table>
	</td>
</tr>
</table>
</body>
<script type="text/javascript">
var kuang = document.getElementById("kuang");
kuang.style.height = document.body.clientHeight-140;

if(document.body.clientWidth > 900){
	kuang.style.width = 900;
}

var authorityTrees = document.getElementById("authorityTree");
authorityTrees.style.height = document.body.clientHeight-165;

var list = document.getElementById("list");
list.style.height = document.body.clientHeight-165;

function doBack(){
	window.location.href="${ctx}/system/groupManage/queryGeGroupDetail.do?id=${groupid}";
}

//初始化tree----------------------------------------------------------////
tree=new dhtmlXTreeObject("authorityTree","100%","100%",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(true);
tree.enableThreeStateCheckboxes(true);
tree.loadXML("${ctx}/system/groupManage/authoritySetDeptAuthTree.do?id=0&groupId=${groupid}",loadOver);

/**exception execute*/
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);

$(document).ready(function(){
	/**保存角色机构信息*/
	$("#saveDept").click(function(){
		var groupid = "${groupid}";
		var roleid = getAllCheckedRole();
		var allAuthDepts = getAuthDepts();/**所有选中的叶子节点*/
		if(validateRoleAndDept(roleid,allAuthDepts)){
			var param = {"groupid":groupid,"roleid":roleid,"deptidSave":allAuthDepts,"geGroup.groupid":$("#groupId").val(),"geGroup.grouptypeid":$("#groupTypeId").val()};
			if(tt.validate()){
			purviewAjax("${ctx}/system/groupManage/updateGroupRoleDept.do?groupname="+$("#groupName").val()+"&groupdesp="+$("#groupDesp").val()+"&grouptypename="+$("#groupTypeName").val(),param,"updateGroupRoleDept","操作失败,请稍后操作!");
			window.parent.opener.parent.frames[0].document.getElementById("frmInput").submit();
			window.close();
			}
		}
	});
	$("input[type='checkbox']").addClass("checkbox_border");
	$("#groupTypeId").change(function(){
		$("#groupTypeName").attr("value",$("#groupTypeId").find("option:selected").text());
	});
});

//异步传输
function purviewAjax(url, param, fun, errorMess){
	url = encodeURI(url);
	url = encodeURI(url);
	$.ajax({
		async:false,
	    url: url,
	    data: param,	
	    contentType: "application/x-www-form-urlencoded; charset=GBK",
	    type: 'POST',
	    dataType: 'json',
	    success: function(data){
	    	eval(fun+"(data)");
	    	doBack();
	    },
	    error: function(){
	        alert(errorMess);
	    }
	});
}
function doClear(){
	window.location.reload();
}

function updateGroupRoleDept(date){
	if($.trim(date)=="success"){
		alert("操作成功！");
	}else{
		alert(date);
	}
}

function checkAll(obj){
	var checkArray = document.getElementsByName("checkbox_roleAll");
	for(var i = 0; i < checkArray.length; i++){
		checkArray[i].checked = obj.checked;
	}
}

function getAllCheckedRole(){
	var roleid = "";
	var checkArray = document.getElementsByName("checkbox_roleAll");
	for (var i = 0; i < checkArray.length; i++){
		if(checkArray[i].checked){
			if(roleid == ""){
				roleid = checkArray[i].value;
			}else {
				roleid += "," + checkArray[i].value;
			}
		}
	}
	return roleid;
}

function validateRoleAndDept(roleids,deptids){
	if(roleids == '' && deptids != ''){
		alert("请选择角色");
		return false;
	}else if(roleids != '' && deptids == ''){
		alert("请选择机构");
		return false;
	}
	return true;
}

function getAuthDepts(){
	var allAuthDepts = "";
	var allSelected=tree.getAllCheckedBranches();
	
	var listId=allSelected.split(",");
	for(var i=0;i<listId.length;i++){
		if(!tree.hasChildren(listId[i])){
			allAuthDepts+=listId[i]+",";
		}
	}

	allAuthDepts = allAuthDepts.substring(0,allAuthDepts.length-1);
	return allAuthDepts;
}

function loadOver(sIdNow){
	$("#showListLoading").hide();
}
</script>

<script type="text/javascript">
	//设置显示区域
	var groupName =new tt.Field("","","groupName").setMsgId("groupName_msg");
	var groupDesp =new tt.Field("","","groupDesp").setMsgId("groupDesp_msg");
	var groupTypeId =new tt.Field("","","groupTypeId").setMsgId("groupTypeId_msg");
	//非空验证
	tt.vf.req.add(groupName,groupTypeId);
	//长度校验
	new tt.LV().set(0,500).add(groupDesp);
</script>
</html>
