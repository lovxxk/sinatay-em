<%@ page language="java" contentType="text/html;charset=GBK"%>
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
<!-- ��ʾ��ʼ -->
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>�����û�Ȩ����Ϣ</title>
</head>
<body leftmargin="0" topmargin="0">
</body>
<div class="public_fun_title">�����û�Ȩ����Ϣ<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
<div style="height:10px;clear:both;">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" style="width:99%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:270px;text-align:center;">Ȩ������Ϣ</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">Ȩ����ϸ������</td>
	</tr>
	<tr>
		<td style="width:270px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
			<div id="onlineUserAuthorityManageTree" style="width:275px;"></div>
		</td>
		<td>
			<iframe id="frmonlineUserAuthorityManage"  style="overflow-x:hidden;height:100%;width:100%;" name="frmonlineUserAuthorityManage"  src="" frameborder="0" ></iframe>
		</td>
	</tr>
</table>
<script>
$(document).ready(function(){
	//pop��ʾ��Ϣ
    	var ids = ['des'];
    	var contents = ['˵����ά��ǰ̨�����û����ܲ˵�Ȩ��<br/>ʹ����Ⱥ��Ȩ�޹�����Ա<br/>����������<br/>ע��������ܲ˵���Ȩ����Ž�������չʾ'];
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
								tip:true,//����������Ƿ����
								padding :10
							}
						});
        	}
//pop��ʾ��Ϣ����
});
	var kuang = document.getElementById("kuang");
	kuang.style.height = document.body.clientHeight-80;

	var onlineUserAuthorityManageTree = document.getElementById("onlineUserAuthorityManageTree");
	onlineUserAuthorityManageTree.style.height = document.body.clientHeight-108;
	
	if(document.body.clientWidth > 900){
		kuang.style.width = 900;
	}
	
	var menu = new dhtmlXMenuObject("onlineUserAuthorityManageTree");
	menu.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxMenu/");
	menu.setIconsPath("${ctx}/global/dhtmlXTree/images/MenuIcon/");
	menu.renderAsContextMenu(true);
	menu.attachEvent("onClick", onMenuClick);
	menu.loadXML("onlineUserAuthorityManageMenu.xml");
	var tree = new dhtmlXTreeObject("onlineUserAuthorityManageTree", "100%", "100%", 0);
	tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableContextMenu(menu);
	tree.setOnClickHandler(onTreeClick);
	tree.setXMLAutoLoading("${ctx}/authorityManage/findOnlineUserAuthorityTreeForManager.do");
	tree.loadXML("${ctx}/authorityManage/findOnlineUserAuthorityTreeForManager.do?id=0");
	function myErrorHandler(type, desc, erData){
	   return;
	}
	dhtmlxError.catchError("ALL",myErrorHandler);
	function onMenuClick(menuitemId, type) {
		var id = tree.getSelectedItemId();
		var level = tree.getLevel(id);
		
		if (id == "") {
			alert("ѡ��Ҫ������Ȩ�ޣ�");
			changeStyle('');
			return;
		}
		
		var parentId = tree.getParentId(id);
		if ("muItem_ADD" == menuitemId) {
			level = level - 1;
			createOnlineUserAuthorityManage(id,level);
		} else if ("muItem_DEL" == menuitemId) {
			if(level < 2){
				alert("������ֹ����");
				return false;
			}
			if(tree.hasChildren(id) != 0){
				alert("�޷�ɾ����������ɾ����Ȩ�޵���Ȩ�ޣ�");
				return false;
			}
			var edpName = tree.getItemText(id);
			if(confirm("ȷ��Ҫɾ��Ȩ��"+edpName+"���������Ϣ��")){
				delOnlineUserAuthorityManage(id);
			}
		} else if ("muItem_EDIT" == menuitemId) {
			if(level < 2){
				alert("������ֹ����");
				return false;
			}
			
			level = level - 1;
			editOnlineUserAuthorityManage(id,parentId,level);
		}
	}

	function onTreeClick(id) {
		var level = tree.getLevel(id);
		changeStyle(id);
		if(id == "ROOT"){
			document.getElementById("frmonlineUserAuthorityManage").src = "";
		}else{
			document.getElementById("frmInput").action = "${ctx}/authorityManage/findOnlineUserAuthorityInfo.do";
			document.getElementById("frmInput").target = "frmonlineUserAuthorityManage";
			document.getElementById("authorityID").value = id;
			document.getElementById("frmInput").submit();
			return true;
		}
	}
	
	function createOnlineUserAuthorityManage(id,level) {
		document.getElementById("frmInput").action = "${ctx}/authorityManage/frmCreateOnlineUserAuthority.do";
		document.getElementById("frmInput").target = "frmonlineUserAuthorityManage";
		document.getElementById("parentID").value = id;
		document.getElementById("authorityLevel").value = level+1;
		document.getElementById("frmInput").submit();
		return true;
	}
	
	function editOnlineUserAuthorityManage(id,parentId,level) {
		document.getElementById("frmInput").action = "${ctx}/authorityManage/findOnlineUserAuthorityForUpdate.do";
		document.getElementById("frmInput").target = "frmonlineUserAuthorityManage";
		document.getElementById("authorityID").value = id;
		document.getElementById("parentID").value = parentId;
		document.getElementById("authorityLevel").value = level;
		document.getElementById("frmInput").submit();
	}
	//ɾ������
	function delOnlineUserAuthorityManage(id) {
		document.getElementById("frmInput").action = "${ctx}/authorityManage/deleteOnlineUserAuthority.do";
		document.getElementById("frmInput").target = "myFrame";
		document.getElementById("authorityID").value = id;
		document.getElementById("frmInput").submit();
		return true;
	}
	
	//ˢ�½ڵ�
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
<form id="frmInput" action="" target="">
	<input type="hidden" id="authorityID" name="geUserAuthority.authorityID">
	<input type="hidden" id="parentID" name="geUserAuthority.parentID">
	<input type="hidden" id="authorityLevel" name="geUserAuthority.authorityLevel">
	<iframe style="display:none;" id="myFrame" name="myFrame"></iframe>
</form>
</html>

<!--  
<frameset id="fraTop" rows="1000" cols="270,*" border="0"  frameborder="no" framespacing="0">
	<frame id="onlineUserAuthorityManageTree" style="overflow-x:hidden;" name="onlineUserAuthorityManageTree" src="onlineUserAuthorityManageTree.jsp"  scrolling="auto" frameborder="0" noresize></frame>
	<frame id="frmonlineUserAuthorityManage"  style="overflow-x:hidden;" name="frmonlineUserAuthorityManage" src="" frameborder="0"  scrolling="auto"></frame>
</frameset>
-->
