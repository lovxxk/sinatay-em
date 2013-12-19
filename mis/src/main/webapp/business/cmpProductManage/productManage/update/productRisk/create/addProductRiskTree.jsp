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
<div class="public_fun_title">险种责任配置</div>
<div style="height:10px;clear:both;">&nbsp;</div>
<center>
<table id="productAttributeRelateTree" align="center" width="50%" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang frmCreate_kuang_header">险种责任配置</td>
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
						onmouseout="this.className='btnBigOnBlur'" onclick="bindProductAttribute();">保存</td>
					<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close(); ">取消</td>
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
//产品绑定属性
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
	        alert("操作失败,请稍后操作!");
	        count = 0;
	    },
	    success: function(data){
		    if(data == "suc"){
				alert("险种责任配置成功！");
				window.close();
				window.opener.parent.document.getElementById("productIframe").src = window.opener.parent.document.getElementById("productIframe").src;
				return;
			}else if(data=='workFlowError'){
				alert("启动工作流出现异常！");
				count = 0;
			} else{
				alert("操作异常,请稍后操作!");
				count = 0;
			}

	    }
	});
}



	var menu = new dhtmlXMenuObject("productCompontTree");
	var menuXml = '<?xml version="1.0" encoding="GBK"?><menu><item id="productCompont_ADD" text="添加产品险种" img="new.gif"/><item type="separator"/><item id="productCompont_EDIT" text="编辑产品险种" img="edit.gif"/><item id="productDutyKind_EDIT" text="编辑产品责任" img="edit.gif"/><item type="separator"/><item id="productCompont__DEL" text="删除产品险种" img="del.gif"/><item id="productDutyKind_DEL" text="删除产品责任" img="del.gif"/><item type="separator"/></menu>';
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
			alert("选择要操作的属性节点！");
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
</script>

</html>