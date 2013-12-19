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
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>电子商务后台管理系统-权限设置</title>
</head>
<body> 
<div class="public_fun_title">新建用户组</div>
<div style="height:10px">&nbsp;</div>
<form action="${ctx}/system/groupManage/createGeGroup.do" name="frmInput" id="frmInput" method="post" target="myFrame">
<table id="kuang" class="frmCreate_kuang" width="85%" align="center" cellspacing="0" cellpadding="0" >
	<tr>
		<td class="frmCreate_kuang_header" style="text-align:center;width:375px;">用户组基本信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">全部角色信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;width:250px;">机构信息</td>
	</tr>
	<tr>
		<td style="width:500px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
					<table class="table_style" align="center" width="100%" id="productTable">
					<tr>
						<td height=10>&nbsp;</td>
					</tr>
					<tr>
						<td class="td_head" width="80px" nowrap>用户组编号：</td>
						<td class="td_body">
							<input id="groupId" onblur="readonblur();" name="geGroup.groupid" type="text" style="width:140px;" class=required maxlength=20>
							<span id="groupId_msg"></span>
						</td>
					</tr>
					<tr>
						<td class="td_head" nowrap>用户组名称：</td>
						<td class="td_body" >
							<input id="groupName" name="geGroup.groupname" type="text" value="" style="width:140px;" maxlength=15>
							<span id="groupName_msg"></span>
					</tr>
					
					<tr>
						<td class="td_head" style="height:30px;" nowrap>用户组类型：</td>
						<td class="td_body" >
							<select id="groupTypeId" name="geGroup.grouptypeid">
								<option value="">--请选择--</option>
								<c:forEach items="${geGrouptypeList}" var="grouptype" varStatus="status">
								<option value="${grouptype.grouptypeid}">${grouptype.grouptypename}</option>
								</c:forEach>
							</select>
							<span id="groupTypeId_msg"></span>
							<input type="hidden" name="id" value="${misUser.id}">
							<input type="hidden" id="groupTypeName" name="geGroup.grouptypename" value="">
						</td>
					</tr>
					<tr>
						<td class="td_head" nowrap>用户组描述：</td>
						<td class="td_body" >
							<textarea  id="groupDesp" name="geGroup.groupdesp" style="width:220px;" rows="5" ></textarea>
							
						</td>
					</tr>
					<tr><td>&nbsp;</td><td><span  id="groupDesp_msg"></span></td></tr>
					</table>
				
		</td>
		<td valign="top">
			<div id="list" style="overflow-y:scroll;height:365px;">
				<table class="public_table1" style="width:260px;">
	 				<tr id="roleAll_menu">
						<td class="search_head" width="10%"><input type="checkbox" disfocus="disfocus"style="border: 0" onclick="checkAll(this)"></td>
						<td class="search_head" width="30%">角色ID</td>
						<td class="search_head">角色名称</td>
					</tr>
		 			<c:forEach items="${geRoleList}" var="geRoleList" begin="0" end="${fn:length(geRoleList)}" step="1" varStatus="status">
						<tr id="tr_roleAll_${geRoleList.roleID}">
							<td class="search_body_center">
								<input type="checkbox" disfocus="disfocus" style="border: 0" name = "checkbox_roleAll" value="${geRoleList.roleID}" ${roleAll.STATUS==0?'checked':''}>
							</td>
							<td class="search_body_center">${geRoleList.roleID}</td>
							<td class="search_body_center">${geRoleList.roleName}</td>
						</tr>
					</c:forEach>
	 			</table>
	 		</div>
		</td>
		<td valign="top">
			<div id="showListLoading" style="position:absolute;">
				<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
			</div>
			<div id="authorityTree" style="overflow-x:hidden;width:230px;height:365px;"></div>
		</td>
	</tr>
</table>
<table align="center" height="60px;">
<tr>
	<td>
		<table width="100%" align="center">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doCreate()">创建</td>
				<td id="backButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<input type="hidden" id="deptid" name="geGroup.deptid" value="${geOperator.deptid}">
<input type="hidden" name="roleid" id="roleid" value=""/>
<input type="hidden" name="deptidSave" id="deptidSave" value=""/>
</form>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
//非空验证
var groupName =new tt.Field("","","groupName").setMsgId("groupName_msg");
var groupDesp =new tt.Field("","","groupDesp").setMsgId("groupDesp_msg");
var groupTypeId =new tt.Field("","","groupTypeId").setMsgId("groupTypeId_msg");
var groupId =new tt.Field("","","groupId").setMsgId("groupId_msg");
tt.vf.req.add(groupName,groupTypeId,groupId);
new tt.LV().set(0,500).add(groupDesp);
//初始化tree
tree=new dhtmlXTreeObject("authorityTree","100%","100%",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(true);
tree.enableThreeStateCheckboxes(true);
tree.loadXML("${ctx}/system/groupManage/authoritySetDeptAuthTree.do?id=0&groupId=${groupid}",loadOver);

/*function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);
*/
//提交
function doCreate(){
	var roleid = getAllCheckedRole();
	var deptidSave = getAuthDepts();
	if(tt.validate()&&validateRoleAndDept(roleid,deptidSave)&&readonblur()){
		$("#roleid").val(roleid);
		$("#deptidSave").val(deptidSave);
		$("#groupTypeName").val($("#groupTypeId").find("option:selected").text());
		$("#frmInput").submit();
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
function doClear(){
	 window.location.reload();
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

var bol=false;
function readonblur(){
			var groupId = $("#groupId").val();
			$.ajax({
				url : "${ctx }/system/groupManage/geGroupById.do",
				data : {
					"geGroup.groupid" : groupId
				},//传入参数
				type : 'POST',
				dataType : 'json',
				error : function() {
				},
				cache :false,
				success : function(data) {
					if (data.resultFlag == "success") {
						//$("#groupId").val("");
						$("#coreProductCodeContent").remove();
						$("#groupId").parent().append("<span id='coreProductCodeContent'><span class='talentErrMsg'>用户组编号已存在!</span></span>");
						return bol;
					} else {
						$("#coreProductCodeContent").remove();
						bol=true;
					}
					
				}
			});
			return bol;
}
$(document).ready(function(){
	$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
	var ids = ['des'];
	    	var contents = ['说明:控制后台权限<br/>'
	    	                + '使用人群:用户组管理人员<br/>'
	    	                + '配置条件:要先配置角色（如果已有需要的角色则不用）<br/>'
	    	                + '注意事项:用户组中只有加入了角色才能具有相应的功能权限<br/>'];
	        	for ( var i = 0 ; i < ids.length ; i++ ){
	    			$('#' + ids[i]).qtip({ 
	    				content:contents[i], 
	    				position: { 
							corner: { 
							tooltip: 'topMiddle',
							target: 'bottomMiddle'
							} ,
							adjust: { 
								screen: true 
								}
						}, 
						 style: {
								border: { 
									width: 1,
									radius: 1,
									color: '#00739f'
									},
									width:450,
									textAlign: 'left',
									background: '#e0f2ff', 
									tip:true,//控制左侧尖角是否出现
									padding :10
								}
							});
	        	}
	});

</script>
</html>
