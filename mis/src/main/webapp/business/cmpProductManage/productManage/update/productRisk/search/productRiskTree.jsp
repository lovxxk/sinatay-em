<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title>��Ʒ��������</title>
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
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:250px;text-align:center;">��Ʒ����������</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">�������� </td>
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
	var menuXml = '<?xml version="1.0" encoding="GBK"?><menu><item id="productCompont_ADD" text="������������" img="new.gif"/><item type="separator"/><item id="productCompont_EDIT" text="�༭��Ʒ����" img="edit.gif"/><item id="productCompont_DEL" text="ɾ����Ʒ����" img="del.gif"/><item id="productDutyKind_EDIT" text="�༭��Ʒ����" img="edit.gif"/><item type="separator"/><item id="productDutyKind_DEL" text="ɾ����Ʒ����" img="del.gif"/><item type="separator"/></menu>';
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
	 * ���Ʋ˵�Ȩ��
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
			alert("ѡ��Ҫ���������Խڵ㣡");
			return null;
		}
		
		if("productCompont_ADD" == menuitemId){ // ������������
			openWindow(id);
		}else if("productCompont_EDIT" == menuitemId){// �༭��Ʒ����
			editRisk(id);
		}else if("productDutyKind_EDIT" == menuitemId){// �༭��Ʒ����
			editDuty(id);
		}else if("productCompont_DEL" == menuitemId){// ɾ������
			delRisk(id);
		}else if("productDutyKind_DEL" == menuitemId){// ɾ������
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
	//ɾ������
	function delAttribute(attrID) {
		if( confirm("ɾ������'" + tree.getItemText(attrID) + "'��ͬʱɾ����Ʒ�Ը����Լ��������Եİ󶨣���ȷ��ɾ����������") ){
		$.ajax({
				url : "${ctx}/productDirectory/delGeDirectoryAttribute.do",
				data : {
					"attrID" : attrID
				},//�������
				type : 'POST',
				dataType : 'json',
				error : function() {
					alert("ɾ������ֵ,���Ժ����!");
				},
				success : function(data) {
					if (data.resultFlag == "success") {
						alert("ɾ������ֵ�ɹ�");
						tree.deleteItem(attrID, true);
					} else {
						alert(data.resultMessage);
					} 
				}
			});
		}
	}
	
	// ������������
	function openWindow(id){
		window.open("${ctx}/productManage/toAddProductRisk.do?coreProductCode="+id,"������������","top=100, left=100, width=900,height=700,toolbar=no");
	}
	// �༭����
	function editRisk(serialNo){
		$("#directoryAttribute").attr("src","${ctx}/productManage/toUpdateRisk.do?serialNo="+serialNo+"&productCode=${productCode}");
	}
	// �༭����
	function editDuty(id){
		$("#directoryAttribute").attr("src","${ctx}/productManage/toUpdateDuty.do?configSerialNo="+id+"&productCode=${productCode}");
	}
	function delRisk(id){
		if(confirm("��ȷ��Ҫɾ����������")){
			$.ajax({
				url : "${ctx}/productManage/delRisk.do",
				data : {
					"id" : id
				},
				type : 'POST',
				dataType : 'text',
				error : function() {
					alert("ɾ������ֵ,���Ժ����!");
				},
				success : function(data) {
					if (data == "suc") {
						alert("ɾ�����ֳɹ�");
						tree.deleteItem(id, true);
					} else {
						alert("ɾ�������쳣");
					} 
				}
			});
		}
	}
	function delDuty(id){
		if(confirm("��ȷ��Ҫɾ����������")){
			$.ajax({
				url : "${ctx}/productManage/delDuty.do",
				data : {
					"id" : id
				}, 
				type : 'POST',
				dataType : 'json',
				error : function() {
					alert("ɾ������ֵ,���Ժ����!");
				},
				success : function(data) {
					if (data.re == "suc") {
						alert("ɾ�����γɹ�");
						tree.deleteItem(id, true);
					}else if(data.re == "del") { // �h����������
						alert("ɾ�����γɹ�");
						tree.deleteItem(data.id);
					}else {
						alert("ɾ�����γ����쳣");
					} 
				}
			});
		}
	}
	
	var productCompontTreeTable = document.getElementById("productCompontTreeTable");
	productCompontTreeTable.style.height = document.body.clientHeight-80;
</script>

</html>