<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
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
<!-- 提示框开始 -->
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>frmCreate.jsp</title>
</head>
<body leftmargin="0" topmargin="0">
<div class="public_fun_title">权限信息<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
<div style="height:10px;clear:both;">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" style="width:98%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:290px;text-align:center;">权限树信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">权限详细及操作</td>
	</tr>
	<tr>
		<td style="width:290px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
			<div id="misAuthorityManageTree" style="overflow-x:hidden;width:295px;"></div>
		</td>
		<td>
			<iframe id="frmMisAuthorityManage"  style="ovherflow-x:hidden;height:100%;width:100%;" name="frmMisAuthorityManage"  src="" frameborder="0" scrolling="auto"></iframe>
		</td>
	</tr>
</table>
<form id="frmInput" action="" target="">
	<input type="hidden" id="authorityID" name="geAuthority.authorityID">
	<input type="hidden" id="parentID" name="geAuthority.parentID">
	<input type="hidden" id="authorityLevel" name="geAuthority.authorityLevel">
	<input type="hidden" id="systype" name="geAuthority.systype"/>
	<iframe style="display:none;" id="myFrame" name="myFrame"></iframe>
</form>
<script type="text/javascript">
$(document).ready(function(){
	//pop提示信息
    	var ids = ['des'];
    	var contents = ['说明：维护后台功能菜单权限<br/>使用人群：权限管理人员<br/>配置条件：<br/>注意事项：功能菜单以权限序号进行排序展示'];
        	for ( var i = 0 ; i < 1 ; i++ ){
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
//pop提示信息结束
});
if(window.navigator.userAgent.indexOf("MSIE")>=1){//如果浏览器为IE
	$("#kuang").css("height",window.parent.document.getElementById('fraLEFT').height-140);
}
var kuang = document.getElementById("kuang");
kuang.style.height = document.body.clientHeight-80;

if(document.body.clientWidth > 900){
	kuang.style.width = 900;
}

var misAuthorityManageTree = document.getElementById("misAuthorityManageTree");
misAuthorityManageTree.style.height = document.body.clientHeight-108;
/******************tree js****************************/
var menu = new dhtmlXMenuObject("misAuthorityManageTree");
menu.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxMenu/");
menu.setIconsPath("${ctx}/global/dhtmlXTree/images/MenuIcon/");
menu.renderAsContextMenu(true);
menu.attachEvent("onClick", onMenuClick);
menu.loadXML("misAuthorityManageMenu.xml");
var tree = new dhtmlXTreeObject("misAuthorityManageTree", "100%", "100%", 0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableContextMenu(menu);
tree.setOnClickHandler(onTreeClick);
tree.setXMLAutoLoading("${ctx}/authorityManage/findMisAuthorityTreeForManager.do");
tree.loadXML("${ctx}/authorityManage/findMisAuthorityTreeForManager.do?id=0");
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);

var systemType;
function treeSystemType(id){
	if(tree.getLevel(id)==2){
		systemType=id;
	}else{
		var levels= tree.getLevel(id);
		for(var i=0;i<levels-2;i++){
			tree.getLevel(tree.getParentId(id));
			id=tree.getParentId(id);
		}
		systemType=id;
	}
}

function onMenuClick(menuitemId, type) {
	var id = tree.getSelectedItemId();
	var level = tree.getLevel(id);
	if (id == "") {
		alert("选择要操作的权限！");
		changeStyle('');
		return;
	}
	
	var parentId = tree.getParentId(id);
	if ("muItem_ADD" == menuitemId) {
		if(level < 2){
			alert("根结点禁止操作");
			return false;
		}
		treeSystemType(id);
		createMisAuthorityManage(id,level,systemType);
	} else if ("muItem_DEL" == menuitemId) {
		if(level < 3){
			alert("该节点禁止删除操作");
			return false;
		}
		if(tree.hasChildren(id) != 0){
			alert("无法删除，请您先删除该权限的子权限！");
			return false;
		}
		var edpName = tree.getItemText(id);
		if(confirm("确认要删除权限"+edpName+"及其相关信息吗？")){
			delMisAuthorityManage(id);
		}
	} else if ("muItem_EDIT" == menuitemId) {
		if(level < 3){
			alert("该结点禁止编辑操作");
			return false;
		}
		
		level = level - 1;
		editMisAuthorityManage(id,parentId,level);
	}
}

function onTreeClick(id) {
	var level = tree.getLevel(id);
	if(level > 2){
		changeStyle(id);
		document.getElementById("frmInput").action = "${ctx}/authorityManage/findMisAuthorityInfo.do";
		document.getElementById("frmInput").target = "frmMisAuthorityManage";
		document.getElementById("authorityID").value = id;
		document.getElementById("systype").value = systemType;
		document.getElementById("frmInput").submit();
		return true;
	}else{
		noStyle(id);
		if(tree.getOpenState(id) == 1){
			tree.closeItem(id);
		}else{
			tree.openItem(id);
		}
	}
}

function createMisAuthorityManage(id,level,systemType) {
	document.getElementById("frmInput").action = "${ctx}/authorityManage/frmCreateMisAuthority.do";
	document.getElementById("frmInput").target = "frmMisAuthorityManage";
	document.getElementById("authorityLevel").value = level+1;
	document.getElementById("systype").value = systemType;
	if(level==2){
		document.getElementById("parentID").value = "ROOT";
	}else{
		document.getElementById("parentID").value = id;
	}
	document.getElementById("frmInput").submit();
	return true;
}

function editMisAuthorityManage(id,parentId,level) {
	document.getElementById("frmInput").action = "${ctx}/authorityManage/findMisAuthorityForUpdate.do";
	document.getElementById("frmInput").target = "frmMisAuthorityManage";
	document.getElementById("authorityID").value = id;
	document.getElementById("parentID").value = parentId;
	document.getElementById("authorityLevel").value = level;
	document.getElementById("frmInput").submit();
}
//删除属性
function delMisAuthorityManage(id) {
	document.getElementById("frmInput").action = "${ctx}/authorityManage/deleteMisAuthority.do";
	document.getElementById("frmInput").target = "myFrame";
	document.getElementById("authorityID").value = id;
	document.getElementById("frmInput").submit();
	return true;
}

//刷新节点
function flushNode(parentId){
	tree.refreshItem(parentId);
	return true;
}

function changeStyle(itemId){
	var idArr = tree.getAllSubItems('ROOT').split(",");
	for(var i = 0; i < idArr.length; i++){
		tree.setItemStyle(idArr[i],"background:none;color:black;font-weight:normal;");
	}
	tree.setItemStyle('ROOT',"background:none;color:black;font-weight:normal;");
	tree.setItemStyle(itemId,"background:#819FF7;color:white;font-weight:bold;");
}

function noStyle(itemId){
	tree.setItemStyle(itemId,"background:none;color:black;font-weight:normal;");
}
</script>
</body>
