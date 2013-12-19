<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<!-- 提示框开始 -->
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>frmCreate.jsp</title>
</head>
<body>
<div class="public_fun_title">企业权限信息<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
<div style="padding:10px 0px 0px 5px;">
	<table id="kuang" class="frmCreate_kuang" style="width:98%;" align="center" cellspacing="0" cellpadding="0">
		<tr>
			<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:290px;text-align:center;">企业权限树信息</td>
			<td class="frmCreate_kuang_header" style="text-align:center;">企业权限详细</td>
		</tr>
		<tr>
			<td style="width:290px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
				<div id="onlineEnterpriseAuthorityManageTree" style="overflow-x:hidden;width:295px;"></div>
			</td>
			<td>
				<iframe id="frmonlineEnterpriseAuthorityManage"  style="overflow-x:hidden;height:100%;padding:0px;margin:0px;width:100%;" name="frmonlineEnterpriseAuthorityManage"  src="" frameborder="0" scrolling="auto"></iframe>
			</td>
		</tr>
	</table>
</div>
<form id="frmInput" action="" target="">
	<input type="hidden" id="authorityID" name="geEnterpriseAuthority.authorityID">
	<input type="hidden" id="parentID" name="geEnterpriseAuthority.parentID">
	<input type="hidden" id="authorityLevel" name="geEnterpriseAuthority.authorityLevel">
	<iframe style="display:none;" id="myFrame" name="myFrame"></iframe>
</form>
<script type="text/javascript">
$(document).ready(function(){
	//pop提示信息
    	var ids = ['des'];
    	var contents = ['说明：维护前台企业功能菜单权限<br/>使用人群：权限管理人员<br/>配置条件：<br/>注意事项：功能菜单以权限序号进行排序展示'];
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
var kuang = document.getElementById("kuang");
kuang.style.height = document.body.clientHeight-80;

var onlineEnterpriseAuthorityManageTree = document.getElementById("onlineEnterpriseAuthorityManageTree");
onlineEnterpriseAuthorityManageTree.style.height = document.body.clientHeight-108;

if(document.body.clientWidth > 900){
	kuang.style.width = 900;
}
/******************tree js****************************/
 var menu = new dhtmlXMenuObject("onlineEnterpriseAuthorityManageTree");
	menu.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxMenu/");
	menu.setIconsPath("${ctx}/global/dhtmlXTree/images/MenuIcon/");
	menu.renderAsContextMenu(true);
	menu.attachEvent("onClick", onMenuClick);
	menu.loadXML("onlineEnterpriseAuthorityManageMenu.xml");
	var tree = new dhtmlXTreeObject("onlineEnterpriseAuthorityManageTree", "100%", "100%", 0);
	tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableContextMenu(menu);
	tree.setOnClickHandler(onTreeClick);
	tree.setXMLAutoLoading("${ctx}/authorityManage/findOnlineEnterpriseAuthorityTreeForManager.do");
	tree.loadXML("${ctx}/authorityManage/findOnlineEnterpriseAuthorityTreeForManager.do?id=0");
	function myErrorHandler(type, desc, erData){
	   return;
	}
	dhtmlxError.catchError("ALL",myErrorHandler);
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
			level = level - 1;
			createOnlineEnterpriseAuthorityManage(id,level);
		} else if ("muItem_DEL" == menuitemId) {
			if(level < 2){
				alert("根结点禁止操作");
				return false;
			}
			
			var edpName = tree.getItemText(id);
			if(confirm("确认要删除权限"+edpName+"及其相关信息吗？")){
				delOnlineEnterpriseAuthorityManage(id);
			}
		} else if ("muItem_EDIT" == menuitemId) {
			if(level < 2){
				alert("根结点禁止操作");
				return false;
			}
			
			level = level - 1;
			editOnlineEnterpriseAuthorityManage(id,parentId,level);
		}
	}

	function onTreeClick(id) {
		var level = tree.getLevel(id);
		changeStyle(id);
		if(id == "ROOT"){
			document.getElementById("frmonlineEnterpriseAuthorityManage").src = "";
		}else{
			document.getElementById("frmInput").action = "${ctx}/authorityManage/findOnlineEnterpriseAuthorityInfo.do";
			document.getElementById("frmInput").target = "frmonlineEnterpriseAuthorityManage";
			document.getElementById("authorityID").value = id;
			document.getElementById("frmInput").submit();
			return true;
		}
	}
	
	function createOnlineEnterpriseAuthorityManage(id,level) {
		document.getElementById("frmInput").action = "${ctx}/authorityManage/frmCreateOnlineEnterpriseAuthority.do";
		document.getElementById("frmInput").target = "frmonlineEnterpriseAuthorityManage";
		document.getElementById("parentID").value = id;
		document.getElementById("authorityLevel").value = level+1;
		document.getElementById("frmInput").submit();
		return true;
	}
	
	function editOnlineEnterpriseAuthorityManage(id,parentId,level) {
		document.getElementById("frmInput").action = "${ctx}/authorityManage/findOnlineEnterpriseAuthorityForUpdate.do";
		document.getElementById("frmInput").target = "frmonlineEnterpriseAuthorityManage";
		document.getElementById("authorityID").value = id;
		document.getElementById("parentID").value = parentId;
		document.getElementById("authorityLevel").value = level;
		document.getElementById("frmInput").submit();
	}
	//删除属性
	function delOnlineEnterpriseAuthorityManage(id) {
		document.getElementById("frmInput").action = "${ctx}/authorityManage/deleteOnlineEnterpriseAuthority.do";
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
			tree.setItemStyle(idArr[i],"background-color:white;color:black;font-weight:normal;");
		}
		tree.setItemStyle('ROOT',"background-color:white;color:black;font-weight:normal;");
		tree.setItemStyle(itemId,"background-color:#819FF7;color:white;font-weight:bold;");
	}
</script>
</body>
