<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
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
<div class="public_fun_title">机构信息<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
<div style="height:10px;clear:both;">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" style="width:98%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:290px;text-align:center;">机构树信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">机构详细及操作</td>
	</tr>
	<tr>
		<td style="width:290px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
			<div id="showListLoading" >
				<div class="loading_process" style="height:50px;font-size:14px;">机构树加载中，请稍后 . . .</div>
			</div>
			<div id="departmentManageTree" style="overflow-x:hidden;width:295px;"></div>
		</td>
		<td>
			<iframe id="frmDepartmentManage"  style="ovherflow-x:hidden;height:100%;width:100%;" name="frmDepartmentManage"  src="" frameborder="0" scrolling="auto"></iframe>
		</td>
	</tr>
</table>
<form id="frmInput" action="" target="">
	<input type="hidden" id="dep_id" name="geDepartment.deptid">
	<input type="hidden" id="parent_dep_id" name="geDepartment.parentid">
	<input type="hidden" id="level" name="geDepartment.deptlevel">
	<input type="hidden" id=areaType name="areaType">
	<iframe style="display:none;" id="myFrame" name="myFrame"></iframe>
</form>
<script type="text/javascript">
$(document).ready(function(){
	//pop提示信息
    	var ids = ['des'];
    	var contents = ['说明：配置保险公司下属所有机构<br/>使用人群：权限管理人员<br/>配置条件：可以配置当前用户在该功能所拥有的机构权限下的所有机构信息<br/>注意事项：该功能除了配置机构的基本信息外还可以配置机构网点信息和机构扩展属性信息。当前机构属性包含车险属性配置，这些操作需在机构树上右键点击'];
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

var departmentManageTree = document.getElementById("departmentManageTree");
departmentManageTree.style.height = document.body.clientHeight-108;
/******************tree js****************************/
var menu = new dhtmlXMenuObject("departmentManageTree");
	menu.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxMenu/");
	menu.setIconsPath("${ctx}/global/dhtmlXTree/images/MenuIcon/");
	menu.renderAsContextMenu(true);
	menu.attachEvent("onClick", onMenuClick);
	menu.loadXML("departmentManageMenu.xml");
	var tree = new dhtmlXTreeObject("departmentManageTree", "100%", "100%", 0);
	tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableContextMenu(menu);
	tree.setOnClickHandler(onTreeClick);
	tree.setOnRightClickHandler(onTreeRightClick);
	/*tree.setXMLAutoLoading("${ctx}/departmentManage/findGeDepartmentTreeForManager.do?isFilterAuthority=1&deptID=ROLE_S_DEMA_M");
	tree.loadXML("${ctx}/departmentManage/findGeDepartmentTreeForManager.do?id=0&isFilterAuthority=1&deptID=ROLE_S_DEMA_M");*/
	tree.loadXML("${ctx}/departmentManage/findDeptmentTree.do",loadOver);
	function myErrorHandler(type, desc, erData){
	   return;
	}
	dhtmlxError.catchError("ALL",myErrorHandler);
	function loadOver(){
		var tree = $("#departmentManageTree").text();
		if(tree == ''){
			$("#departmentManageTree").html("<div style='color:#FF9000;font-weight:bold;text-align:center;padding-top:15px;'>提示：该组暂未分配机构权限</div>");
		}
		$("#showListLoading").hide();
	}
	function onTreeRightClick(id){
		var level = tree.getLevel(id);
		menu.hideItem("muItem_SERVICEEDIT");
		menu.hideItem("muItem_ATTREDIT");
		menu.hideItem("muItem_ADD");
		menu.hideItem("muItem_EDIT");
		menu.hideItem("muItem_DEL");
		<acc:showView source="ROLE_S_DEMA_M_I">
			menu.showItem("muItem_ADD");
		</acc:showView>
		<acc:showView source="ROLE_S_DEMA_U">
			menu.showItem("muItem_EDIT");
		</acc:showView>
		<acc:showView source="ROLE_S_DEMA_D">
			menu.showItem("muItem_DEL");
		</acc:showView>
		if(level >= 3){
			<acc:showView source="ROLE_SERVICE">
				menu.showItem("muItem_SERVICEEDIT");
			</acc:showView>
			<acc:showView source="ROLE_ATTRIBUTE">
				menu.showItem("muItem_ATTREDIT");
			</acc:showView>
		}
	}
	
	function onMenuClick(menuitemId, type) {
		var id = tree.getSelectedItemId();
		var tid = id.replace("city","");
		var level = tree.getLevel(id);
		
		if (id == "") {
			alert("选择要操作的机构！");
			changeStyle('');
			return;
		}
		
		var parentId = tree.getParentId(id);
		if ("muItem_ADD" == menuitemId) {
			createDepartmentManage(tid,level);
		} else if ("muItem_DEL" == menuitemId) {
			if(tree.hasChildren(id) != 0){
				alert("其下有子节点，不可以删除！");
				return false;
			}
			
			var edpName = tree.getItemText(id);
			if(confirm("确认要删除"+edpName+"及其相关信息吗？")){
				delDepartmentManage(tid);
			}
		} else if ("muItem_EDIT" == menuitemId) {
			editDepartmentManage(tid,parentId,level);
		} else if ("muItem_SERVICEEDIT" == menuitemId) {
			if(level < 3){
				alert("该类机构不允许设置网点信息");
				return false;
			}
			
			var city,province,cityName,provinceName;
			if(level == 3){/**直辖市、沿海城市*/
				city = id;
				province = id;
				cityName = tree.getItemText(city);
				provinceName = tree.getItemText(province);
			}else if(level == 4){/**省级*/
				city = id;
				cityName = tree.getItemText(city);
				province = tree.getParentId(city);
				provinceName = tree.getItemText(province);
			}
			window.open("${ctx}/system/departmentManage/serviceNetwork/search/index.jsp?deptid="+tid+"&city="+city+"&cityName="+cityName+"&province="+province+"&provinceName="+provinceName,"机构网点配置","top=100, left=100, width=1050,height=600,toolbar=no");
		} else if ("muItem_ATTREDIT" == menuitemId) {
			if(level < 3){
				alert("该类机构不允许设置属性信息");
				return false;
			}
			var deptname = tree.getItemText(id);
			window.open("${ctx}/system/departmentManage/deptAttribute/search/index.jsp?deptname="+deptname+"&deptid="+tid,"机构属性配置","top=100, left=100, width=1050,height=600,toolbar=no");
		} 
	}

	function onTreeClick(id) {
		var level = tree.getLevel(id);
		if(tree.hasChildren(id) != 0){
			if(tree.getOpenState(id) == 1){
				tree.closeItem(id);
			}else{
				tree.openItem(id);
			}
		}
		changeStyle(id);
		var tid = id.replace("city","");
		document.getElementById("frmInput").action = "${ctx}/departmentManage/findGeDepartmentInfo.do";
		document.getElementById("frmInput").target = "frmDepartmentManage";
		document.getElementById("dep_id").value = tid;
		document.getElementById("frmInput").submit();
		return true;
	}
	
	function createDepartmentManage(tid,level) {
		document.getElementById("frmInput").action = "${ctx}/departmentManage/frmCreateGeDepartment.do";
		document.getElementById("frmInput").target = "frmDepartmentManage";
		document.getElementById("parent_dep_id").value = tid;
		document.getElementById("level").value = level;
		document.getElementById("frmInput").submit();
		return true;
	}
	
	function editDepartmentManage(tid,parentId,level) {
		document.getElementById("frmInput").action = "${ctx}/departmentManage/findGeDepartmentForUpdate.do";
		document.getElementById("frmInput").target = "frmDepartmentManage";
		document.getElementById("dep_id").value = tid;
		document.getElementById("parent_dep_id").value = parentId;
		document.getElementById("level").value = level;
		document.getElementById("frmInput").submit();
	}
	//删除属性
	function delDepartmentManage(tid) {
		document.getElementById("frmInput").action = "${ctx}/departmentManage/deleteGeDepartment.do";
		document.getElementById("frmInput").target = "myFrame";
		document.getElementById("dep_id").value = tid;
		document.getElementById("frmInput").submit();
		return true;
	}
	
	//刷新节点
	function flushNode(parentId){
		tree.refreshItem(parentId);
		return true;
	}
	
	function changeStyle(itemId){
		var idArr = tree.getAllSubItems('0').split(",");
		for(var i = 0; i < idArr.length; i++){
			tree.setItemStyle(idArr[i],"background-color:white;color:black;font-weight:normal;");
		}
		tree.setItemStyle('0',"background-color:white;color:black;font-weight:normal;");
		tree.setItemStyle(itemId,"background-color:#819FF7;color:white;font-weight:bold;");
	}
</script>
</body>
