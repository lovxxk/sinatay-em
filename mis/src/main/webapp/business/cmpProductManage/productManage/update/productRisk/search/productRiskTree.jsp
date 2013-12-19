<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title>产品险种配置</title>
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
</head>
<body leftmargin="0" topmargin="0">
<div style="height:10px;clear:both;">&nbsp;</div>
<table id="productCompontTreeTable" class="frmCreate_kuang" style="width:100%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:250px;text-align:center;">产品险种责任树</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">险种责任 </td>
	</tr>
	<tr>
		<td style="width:250px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
			<div id="productCompontTree" style="overflow-x:hidden;width:100%px;"></div>
		</td>
		<td>
			<iframe id="directoryAttribute"  style="ovherflow-x:hidden;height:100%;width:100%;" name="directoryAttribute"  src="" frameborder="0" scrolling="auto"></iframe>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	var menu = new dhtmlXMenuObject("productCompontTree");
	var menuXml = '<?xml version="1.0" encoding="GBK"?><menu><item id="productCompont_ADD" text="险种责任配置" img="new.gif"/><item type="separator"/><item id="productCompont_EDIT" text="编辑产品险种" img="edit.gif"/><item id="productCompont_DEL" text="删除产品险种" img="del.gif"/><item id="productDutyKind_EDIT" text="编辑产品责任" img="edit.gif"/><item type="separator"/><item id="productDutyKind_DEL" text="删除产品责任" img="del.gif"/><item type="separator"/></menu>';
	menu.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxMenu/");
	menu.setIconsPath("${ctx}/global/dhtmlXTree/images/MenuIcon/");
	menu.renderAsContextMenu(true);
	menu.attachEvent("onClick", onMenuClick);
	menu.loadXMLString(menuXml);
	var tree = new dhtmlXTreeObject("productCompontTree", "100%", "100%", 0);
	tree.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableDragAndDrop(true);
	
	tree.enableContextMenu(menu);
	tree.setOnClickHandler(onTreeClick);
	tree.setOnRightClickHandler(onTreeRightClick);
	tree.loadXMLString("${productCompontTree}");
	
	function openTreeItem(obj, id) {
		tree = obj;
		tree.openItem(id);
	}
	
	/**
	 * 控制菜单权限
	 *
	 **/
	function onTreeRightClick(id){
		var nodeType = tree.getItemAttribute(id,"itemType");
		if (nodeType == "product") {
			menu.setItemEnabled("productCompont_ADD");
			menu.setItemDisabled("productCompont_EDIT");
			menu.setItemDisabled("productDutyKind_EDIT");
			menu.setItemDisabled("productCompont_DEL");
			menu.setItemDisabled("productDutyKind_DEL");
		} else if (nodeType == "risk") {
			menu.setItemDisabled("productCompont_ADD");
			menu.setItemEnabled("productCompont_EDIT");
			menu.setItemEnabled("productCompont_DEL");
			menu.setItemDisabled("productDutyKind_EDIT");
			menu.setItemDisabled("productDutyKind_DEL");
		} else if (nodeType == "duty") {
			menu.setItemDisabled("productCompont_ADD");
			menu.setItemDisabled("productCompont_EDIT");
			menu.setItemDisabled("productCompont_DEL");
			menu.setItemEnabled("productDutyKind_EDIT");
			menu.setItemEnabled("productDutyKind_DEL");
		}
	}
	function onMenuClick(menuitemId, type) {
		var id = tree.rclk_id;
		if (id == "") {
			alert("选择要操作的属性节点！");
			return null;
		}
		
		if("productCompont_ADD" == menuitemId){ // 险种责任配置
			openWindow(id);
		}else if("productCompont_EDIT" == menuitemId){// 编辑产品险种
			editRisk(id);
		}else if("productDutyKind_EDIT" == menuitemId){// 编辑产品责任
			editDuty(id);
		}else if("productCompont_DEL" == menuitemId){// 删除险种
			delRisk(id);
		}else if("productDutyKind_DEL" == menuitemId){// 删除责任
			delDuty(id);
		}
	}
	function onTreeClick(id) {
		var nodeType = tree.getItemAttribute(id,"itemType");
		if("risk"==nodeType){
			$("#directoryAttribute").attr("src","${ctx}/productManage/toDetailRisk.do?serialNo="+ id);
		}else if("duty"==nodeType){
			$("#directoryAttribute").attr("src","${ctx}/productManage/toDetailDuty.do?configSerialNo="+ id);
		}
	}
	function editAttribute(id) {
		$("#directoryAttribute")
				.attr(
						"src",
						"${ctx}/productDirectory/findGeDirectoryAttributeInfoByAttrId.do?operator=update&attrId="
								+ id);
	}
	//删除属性
	function delAttribute(attrID) {
		if( confirm("删除属性'" + tree.getItemText(attrID) + "'将同时删除产品对该属性及其子属性的绑定，您确定删除该属性吗？") ){
		$.ajax({
				url : "${ctx}/productDirectory/delGeDirectoryAttribute.do",
				data : {
					"attrID" : attrID
				},//传入参数
				type : 'POST',
				dataType : 'json',
				error : function() {
					alert("删除属性值,请稍后操作!");
				},
				success : function(data) {
					if (data.resultFlag == "success") {
						alert("删除属性值成功");
						tree.deleteItem(attrID, true);
					} else {
						alert(data.resultMessage);
					} 
				}
			});
		}
	}
	
	// 险种责任配置
	function openWindow(id){
		window.open("${ctx}/productManage/toAddProductRisk.do?coreProductCode="+id,"险种责任配置","top=100, left=100, width=900,height=700,toolbar=no");
	}
	// 编辑险种
	function editRisk(serialNo){
		$("#directoryAttribute").attr("src","${ctx}/productManage/toUpdateRisk.do?serialNo="+serialNo+"&productCode=${productCode}");
	}
	// 编辑责任
	function editDuty(id){
		$("#directoryAttribute").attr("src","${ctx}/productManage/toUpdateDuty.do?configSerialNo="+id+"&productCode=${productCode}");
	}
	function delRisk(id){
		if(confirm("您确认要删除该险种吗？")){
			$.ajax({
				url : "${ctx}/productManage/delRisk.do",
				data : {
					"id" : id
				},
				type : 'POST',
				dataType : 'text',
				error : function() {
					alert("删除属性值,请稍后操作!");
				},
				success : function(data) {
					if (data == "suc") {
						alert("删除险种成功");
						tree.deleteItem(id, true);
					} else {
						alert("删除出现异常");
					} 
				}
			});
		}
	}
	function delDuty(id){
		if(confirm("您确认要删除该责任吗？")){
			$.ajax({
				url : "${ctx}/productManage/delDuty.do",
				data : {
					"id" : id
				}, 
				type : 'POST',
				dataType : 'json',
				error : function() {
					alert("删除属性值,请稍后操作!");
				},
				success : function(data) {
					if (data.re == "suc") {
						alert("删除责任成功");
						tree.deleteItem(id, true);
					}else if(data.re == "del") { // 刪除整個险种
						alert("删除责任成功");
						tree.deleteItem(data.id);
					}else {
						alert("删除责任出现异常");
					} 
				}
			});
		}
	}
	
	var productCompontTreeTable = document.getElementById("productCompontTreeTable");
	productCompontTreeTable.style.height = document.body.clientHeight-80;
</script>

</html>