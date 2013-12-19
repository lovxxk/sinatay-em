<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:set var="GeRole" value="${requestScope.GeRoleForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>电子商务后台管理系统-编辑角色信息</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			编辑角色
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="height:10px">&nbsp;</div>
<form action="${ctx}/system/roleManage/updateGeRole.do" id="frmInput" method="post" target="myFrame">
<table id="kuang" class="frmCreate_kuang" width="95%" align="center" cellspacing="0" cellpadding="0" >
	<tr>
		<td class="frmCreate_kuang_header" style="text-align:center;border-right:1px solid #dcdcdc;">角色基本信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;width:325px;">角色权限信息</td>
	</tr>
	<tr>
		<td valign="top" id="basicInfo" style="border-right:1px solid #dcdcdc;">
			<table align="left" style="line-height:25px;">
			<tr>
				<td class="td_head" width="70px" nowrap>角色编号：</td>
				<td class="td_body">
				<span id="roleid_msg" style="width:100">
					${geRole.roleID}
					<input id="roleid" name="geRole.roleID" type="hidden" value="${geRole.roleID}" style="width:170px;" class=required maxlength=20>
					</span>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>角色名称：</td>
				<td  class="td_body">
					<input id="rolename" name="geRole.roleName" type="text" value="${geRole.roleName}" style="width:170px;" class=required maxlength=20>
					<span id="rolename_msg"></span>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>角色描述：</td>
				<td  class="td_body">
				<textarea id="roledesp" name="geRole.roleDesp"  style="width:380px;height: 90px;">${geRole.roleDesp}</textarea>
				</td>
			</tr>
			<tr><td>&nbsp;</td><td><span id="roledesp_msg"></span></td></tr>
			</table>
		</td>
		<td valign="top">
			<div id="showListLoading" style="position:absolute;">
				<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
			</div>
			<div id="authorityTree" style="overflow-x:hidden;width:100%;"></div>
		</td>
	</tr>
</table>
<table align="center" style="width:100%;line-height:25px;">
<tr height="60px">
	<td colspan="2">
		<table width="100" align="center">
		<tr>
			<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>返回</td>
			<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>修改</td>
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >关闭</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<input type=hidden name="authoritys" id="authoritys">
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
kuang.style.height = document.body.clientHeight-140;

if(document.body.clientWidth > 900){
	kuang.style.width = 900;
}

var authorityTrees = document.getElementById("authorityTree");
authorityTrees.style.height = document.body.clientHeight-165;

var basicInfo = document.getElementById("basicInfo");
basicInfo.style.height = document.body.clientHeight-165;

//初始化tree----------------------------------------------------------////
var tree=new dhtmlXTreeObject("authorityTree","100%","100%",0); 
tree.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(true);
tree.enableThreeStateCheckboxes(true);
tree.setOnCheckHandler(toncheck);
tree.loadXML("${ctx}/system/roleManage/findRoleAuthority.do?roleID=${geRole.roleID}",loadOver);
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);

function toncheck(id,state){
	var children = tree.getAllCheckedBranches();
	$("#authoritys").val(children);
}

function loadOver(){
	$("#showListLoading").hide();
	var children = tree.getAllCheckedBranches();
	$("#authoritys").val(children);
	$("#updateButton").show();
}

function doUpdate(){
	if(tt.validate()){
		$("#frmInput").submit();
	}	
}
function doClear(){
	window.location.reload();
	$("#frmInput")[0].reset();
}
function doBack(){
	window.location.href="${ctx}/system/roleManage/detail.do?geRole.roleID=${geRole.roleID}";
}
var ids = ['roleid_msg'];
var contents = ['角色唯一标识'];
for ( var i = 0 ; i < ids.length ; i++ ){
	$('#' + ids[i]).qtip({ 
		content:contents[i], 
		position: { 
			corner: { 
			tooltip: 'leftMiddle', 
			target: 'rightMiddle'
			} 
		}, 
		 style: { 
		border: { 
			width: 2,
			radius: 2,
			color: '#00739f'
			},
			width:100,
			padding: 10, 
			textAlign: 'left',
			background: '#e0f2ff', 
			tip:true//控制左侧尖角是否出现
			//name: 'green' 
		} 
	}); 
}
</script>
<script type="text/javascript">
	var rolename =new tt.Field("","","rolename").setMsgId("rolename_msg");
	var roledesp =new tt.Field("","","roledesp").setMsgId("roledesp_msg");
	tt.vf.req.add(rolename,roledesp);
	new tt.LV().set(0,500).add(roledesp);
</script>
</html>
