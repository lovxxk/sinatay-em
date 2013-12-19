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
<!-- ��ʾ��ʼ -->
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>frmCreate.jsp</title>
</head>
<body leftmargin="0" topmargin="0">
<div class="public_fun_title">������Ϣ<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
<div style="height:20px;clear:both;">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" style="width:99%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:290px;text-align:center;">��������Ϣ</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">������ϸ������</td>
	</tr>
	<tr>
		<td style="width:290px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
			<div id="areaManageTree" style="overflow-x:hidden;;width:295px;"></div>
		</td>
		<td>
			<iframe id="frmAreaManage"  style="ovherflow-x:hidden;height:100%;width:100%;" name="frmAreaManage"  src="" frameborder="0" scrolling="auto"></iframe>
		</td>
	</tr>
</table>
<form id="frmInput" action="" target="">
	<input type="hidden" id="area_id" name="geAreaAuthority.gid">
	<input type="hidden" id="parent_area_id" name="geAreaAuthority.pgid">
	<input type="hidden" id="level" name="geAreaAuthority.glevel">
	<input type="hidden" id=areaType name="areaType">
	<iframe style="display:none;" id="myFrame" name="myFrame"></iframe>
</form>
<script type="text/javascript">
$(document).ready(function(){
	//pop��ʾ��Ϣ
    	var ids = ['des'];
    	var contents = ['˵�������ñ��չ�˾������������<br/>ʹ����Ⱥ��������Ա<br/>����������<br/>ע������ù��ܿ�����ӡ��༭������������ͨ���Ҽ����в���'];
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
if(window.navigator.userAgent.indexOf("MSIE")>=1){//��������ΪIE
	$("#kuang").css("height",window.parent.document.getElementById('fraLEFT').height-140);
}
var kuang = document.getElementById("kuang");
kuang.style.height = document.body.clientHeight-140;

var areaManageTree = document.getElementById("areaManageTree");
areaManageTree.style.height = document.body.clientHeight-168;

if(document.body.clientWidth > 900){
	kuang.style.width = 900;
}

/******************tree js****************************/
	var menu = new dhtmlXMenuObject("areaManageTree");
	menu.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxMenu/");
	menu.setIconsPath("${ctx}/global/dhtmlXTree/images/MenuIcon/");
	menu.renderAsContextMenu(true);
	menu.attachEvent("onClick", onMenuClick);
	menu.loadXML("areaManageMenu.xml");
	var tree = new dhtmlXTreeObject("areaManageTree", "100%", "100%", 0);
	tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableContextMenu(menu);
	tree.setOnClickHandler(onTreeClick);
	tree.setXMLAutoLoading("${ctx}/areaAction/findGeAreaActionTree.do");
	tree.loadXML("${ctx}/areaAction/findGeAreaActionTree.do?id=0");
	function myErrorHandler(type, desc, erData){
	   return;
	}
	dhtmlxError.catchError("ALL",myErrorHandler);
	function onMenuClick(menuitemId, type) {
		var id = tree.getSelectedItemId();
		var level = tree.getLevel(id);
		
		if (id == "") {
			alert("ѡ��Ҫ�����Ļ�����");
			changeStyle('');
			return;
		}
		
		var parentId = tree.getParentId(id);
		//if(level < 2){
		//	alert("������ֹ����");
		//	return false;
		//}
		if ("muItem_ADD" == menuitemId) {
			createDepartmentManage(id,level);
		} else if ("muItem_DEL" == menuitemId) {
			if(level == 1){
				alert("���ҽ�ֹɾ��");
				return false;
			}
			if(tree.hasChildren(id) != 0){
				alert("�޷�ɾ����������ɾ���������������");
				return false;
			}
			var edpName = tree.getItemText(id);
			if(confirm("ȷ��Ҫɾ��"+edpName+"���������Ϣ��")){
				delDepartmentManage(id);
			}
		} else if ("muItem_EDIT" == menuitemId) {
			if(level == 1){
				alert("���ҽ�ֹ�༭");
				return false;
			}
			editDepartmentManage(id,parentId,level);
		}
	}

	function onTreeClick(id) {
		var level = tree.getLevel(id);
		if(level == 1){
			changeStyle(id);
			document.getElementById("frmAreaManage").src = "";
			return false;
		}
		changeStyle(id);
		document.getElementById("frmInput").action = "${ctx}/areaAction/findAreaManage.do";
		document.getElementById("frmInput").target = "frmAreaManage";
		document.getElementById("area_id").value = id;
		document.getElementById("frmInput").submit();
		return true;
	}
	
	function createDepartmentManage(id,level) {
		document.getElementById("frmInput").action = "${ctx}/areaAction/frmCreateGeArea.do";
		document.getElementById("frmInput").target = "frmAreaManage";
		document.getElementById("parent_area_id").value = id;
		document.getElementById("level").value = level;
		document.getElementById("frmInput").submit();
		return true;
	}
	
	function editDepartmentManage(id,parentId,level) {
		document.getElementById("frmInput").action = "${ctx}/areaAction/findGeAreaForUpdate.do";
		document.getElementById("frmInput").target = "frmAreaManage";
		document.getElementById("area_id").value = id;
		document.getElementById("parent_area_id").value = parentId;
		document.getElementById("level").value = level;
		document.getElementById("frmInput").submit();
	}
	//ɾ������
	function delDepartmentManage(id) {
		document.getElementById("frmInput").action = "${ctx}/areaAction/deleteGeArea.do";
		document.getElementById("frmInput").target = "myFrame";
		document.getElementById("area_id").value = id;
		document.getElementById("frmInput").submit();
		return true;
	}
	
	//ˢ�½ڵ�
	function flushNode(parentId){
		tree.refreshItem(parentId);
		return true;
	}
	
	function changeStyle(itemId){
		var idArr = tree.getAllSubItems('0').split(",");
		for(var i = 0; i < idArr.length; i++){
			tree.setItemStyle(idArr[i],"background:none;color:black;font-weight:normal;");
		}
		tree.setItemStyle('0',"background:none;color:black;font-weight:normal;");
		tree.setItemStyle(itemId,"background:#819FF7;color:white;font-weight:bold;");
	}
</script>
</body>
