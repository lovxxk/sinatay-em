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
<div class="public_fun_title">������������</div>
<div style="height:10px;clear:both;">&nbsp;</div>
<center>
<table id="productAttributeRelateTree" align="center" width="50%" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang frmCreate_kuang_header">������������</td>
	</tr>
	<tr>
		<td class="td_head" style="text-align:left;border-left:1px solid #dcdcdc;border-right:1px solid #dcdcdc;border-bottom:1px solid #dcdcdc; padding-top:15px;" valign="top">
			<div id="productCompontTree" style="overflow-x:hidden;"></div>
		</td>
	</tr>
	<tr>
		<td align="right" style="padding-top:10px;">
			<table id="operatorTable">
				<tr>
					<td id="saveOperatorTable" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="bindProductAttribute();">����</td>
					<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close(); ">ȡ��</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</center>
<div style="height: 100px"></div>
</body>

<script type="text/javascript">
var count = 0;
//��Ʒ������
function bindProductAttribute(){
	if(count==1)
		return;
	
	count = 1; 
	children = tree.getAllCheckedBranches();
	
	$.ajax({
	    url: "${ctx}/productManage/addRiskDuty.do",
	    data: {"attributes":children,"allRisk":"${allRisk}","allDuty":"${allDuty}","productId":"${geProductMain.coreProductCode}"},
	    type: 'POST',
	    dataType: 'text',
	    error: function(){
	        alert("����ʧ��,���Ժ����!");
	        count = 0;
	    },
	    success: function(data){
		    if(data == "suc"){
				alert("�����������óɹ���");
				window.close();
				window.opener.parent.document.getElementById("productIframe").src = window.opener.parent.document.getElementById("productIframe").src;
				return;
			}else if(data=='workFlowError'){
				alert("���������������쳣��");
				count = 0;
			} else{
				alert("�����쳣,���Ժ����!");
				count = 0;
			}

	    }
	});
}



	var menu = new dhtmlXMenuObject("productCompontTree");
	var menuXml = '<?xml version="1.0" encoding="GBK"?><menu><item id="productCompont_ADD" text="��Ӳ�Ʒ����" img="new.gif"/><item type="separator"/><item id="productCompont_EDIT" text="�༭��Ʒ����" img="edit.gif"/><item id="productDutyKind_EDIT" text="�༭��Ʒ����" img="edit.gif"/><item type="separator"/><item id="productCompont__DEL" text="ɾ����Ʒ����" img="del.gif"/><item id="productDutyKind_DEL" text="ɾ����Ʒ����" img="del.gif"/><item type="separator"/></menu>';
	menu.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxMenu/");
	menu.setIconsPath("${ctx}/global/dhtmlXTree/images/MenuIcon/");
	menu.renderAsContextMenu(true);
	menu.attachEvent("onClick", onMenuClick);
	menu.loadXMLString(menuXml);
	var tree = new dhtmlXTreeObject("productCompontTree", "100%", "100%", 0);
	
	tree.enableThreeStateCheckboxes(true);
	tree.enableCheckBoxes(true, true);
	
	tree.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableDragAndDrop(true);
	tree.enableContextMenu(menu);
	tree.setOnClickHandler(onTreeClick);
	tree.loadXMLString("${productCompontTree}");
	
	
	var productAttributeRelateTree = document.getElementById("productCompontTree");
	var attributeTree = document.getElementById("productCompontTree");
	productAttributeRelateTree.style.height = document.body.clientHeight - 160;
	attributeTree.style.height = document.body.clientHeight - 140;
	if(document.body.clientWidth > 550){
		productAttributeRelateTree.style.width = 550;
	}
	
	
	function openTreeItem(obj, id) {
		tree = obj;
		tree.openItem(id);
	}
	
	function onMenuClick(menuitemId, type) {
		var id = tree.rclk_id;
		if (id == "") {
			alert("ѡ��Ҫ���������Խڵ㣡");
			return null;
		}
		if ("attribute_ADD" == menuitemId) {
			$("#directoryAttribute").attr("src",
					"${ctx}/business/cmpProductManage/productDirectory/directoryAttribute/create/directoryAttribute.jsp?parentAttrID=" + id);
		} else if ("attributeValue_DEL" == menuitemId) {
			delAttribute(id);
		} else if ("attribute_EDIT" == menuitemId) {
			editAttribute(id);
		}
	}
	function onTreeClick(id) {
		$("#directoryAttribute").attr("src","${ctx}/productDirectory/findGeDirectoryAttributeInfoByAttrId.do?operator=display&attrId="+ id);
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
</script>

</html>