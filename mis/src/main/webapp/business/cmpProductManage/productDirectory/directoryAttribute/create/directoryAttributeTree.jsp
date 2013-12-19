<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title>产品目录属性树</title>
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>	
</head>
<body leftmargin="0" topmargin="0">
<div class="public_fun_title">产品目录属性树</div>
<div style="height:10px;clear:both;">&nbsp;</div>
<table id="diretoryAttributeTable" class="frmCreate_kuang" style="width:98%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;width:250px;text-align:center;">产品目录属性树</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">产品目录属性</td>
	</tr>
	<tr>
		<td style="width:250px;border-right:1px solid #dcdcdc;padding:0px;margin:0px;" valign="top">
			<div id="attributeTree" style="overflow-x:hidden;width:250px;"></div>
		</td>
		<td>
			<iframe id="directoryAttribute"  style="ovherflow-x:hidden;height:100%;width:100%;" name="directoryAttribute"  src="" frameborder="0" scrolling="auto"></iframe>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	var menu = new dhtmlXMenuObject("attributeTree");
	var menuXml = '<?xml version="1.0" encoding="GBK"?><menu><item id="attribute_ADD" text="添加属性" img="new.gif"/><item type="separator"/><item id="attribute_EDIT" text="编辑属性" img="edit.gif"/><item type="separator"/><item id="attributeValue_DEL" text="删除属性值" img="del.gif"/><item type="separator"/></menu>';
	menu.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxMenu/");
	menu.setIconsPath("${ctx }/global/dhtmlXTree/images/MenuIcon/");
	menu.renderAsContextMenu(true);
	menu.attachEvent("onClick", onMenuClick);
	menu.loadXMLString(menuXml);
	var tree = new dhtmlXTreeObject("attributeTree", "100%", "100%", 0);
	tree.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableDragAndDrop(true);
	tree.enableContextMenu(menu);
	tree.setOnClickHandler(onTreeClick);
	tree.loadXMLString("${treeXml}");
	function openTreeItem(obj, id) {
		tree = obj;
		tree.openItem(id);
	}
	
	if ("${attrId}" != "") {
		onTreeClick("${attrId}");
	}
	$(document).ready(function(){
		$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
		var ids = ['des'];
		    	var contents = ['说明:对产品目录进行的分类，在前台按产品目录属性进行分类展示<br/>'
		    	                + '使用人群:产品配置人员<br/>'
		    	                + '配置条件:<br/>'
		    	                + '注意事项:各属性的显示顺序与在前台展示顺序有关<br/>'];
		        	for ( var i = 0 ; i < ids.length ; i++ ){
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
		});
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
	
	var diretoryAttributeTable = document.getElementById("diretoryAttributeTable");
	diretoryAttributeTable.style.height = document.body.clientHeight-80;

	if(document.body.clientWidth > 900){
		diretoryAttributeTable.style.width = 900;
	}
</script>

</html>
