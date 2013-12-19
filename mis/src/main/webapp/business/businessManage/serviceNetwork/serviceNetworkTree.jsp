<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
response.setHeader("Cache-Control","No-Cache");//HTTP 1.1
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
<head>
<title>�����������</title>
<link href="${ctx}/global/css/mis_basic.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
</head>
<body>
<div style="height: 30px"></div>
<center>
<table id="diretoryAttributeTable" class="diretoryAttributeTableStyle"
	cellspacing="0" cellpadding="0" border="0">
	<tr height="1000">
		<td width=25 valign="top">
		<table border=0 width="100" height="100%" cellpadding=0 cellspacing=0>
			<tr>
				<td style="font-size:12px;font-weight:bold;padding-left:10px;">��������ṹ��</td>
			</tr>
			<tr>
				<td style="text-align: left; padding-top: 3mm;" valign="top">
				<div id="serviceNetworkTree"
					style="overflow: auto; width: 300px; height: 2000px"></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</center>
</body>
<script>
	var menu = new dhtmlXMenuObject("serviceNetworkTree");
	menu.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	menu.setIconsPath("${ctx}/global/dhtmlXTree/images/MenuIcon/");
	menu.renderAsContextMenu(true);
	menu.attachEvent("onClick", onMenuClick);
	menu.loadXML("serviceNetworkMenu.xml");
	var tree = new dhtmlXTreeObject("serviceNetworkTree", "100%", "1000px", 0);
	tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableContextMenu(menu);
	tree.setOnClickHandler(onTreeClick);
	tree.setXMLAutoLoading("loadServiceNetworkTree.jsp");
	tree.loadXML("loadServiceNetworkTree.jsp?id=0");
	function myErrorHandler(type, desc, erData){
	   return;
	}
	dhtmlxError.catchError("ALL",myErrorHandler);
	function onMenuClick(menuitemId, type) {
		var selectItemId = tree.getSelectedItemId();
		var bussinessArea = selectItemId.split("_")[0];
		var id = selectItemId.split("_")[1];
		if (id == "") {
			alert("ѡ��Ҫ������������Ļ�����");
			return;
		}
		if ("serviceNetwork_ADD" == menuitemId) {
			if(id.indexOf("serviceNetWork")!= -1){/**��������*/
				alert("���������޷�������Ӳ���.");
				window.parent.document.getElementById("frmCreateServiceNetwork").src = "";
				return;
			}else{/**�Ƿ�������*/
				var level = tree.getLevel(selectItemId);
				if(level == 1){
					alert("���ڵ��޷�������Ӳ���.");
					window.parent.document.getElementById("frmCreateServiceNetwork").src = "";
					return;
				}else{
					/**�жϸýڵ��Ƿ���½���������*/
					if(id.indexOf("lastCity") == -1){
						alert("�ýڵ��޷�ֱ�ӽ�����Ӳ������������ӽڵ������Ӳ���.");
						window.parent.document.getElementById("frmCreateServiceNetwork").src = "";
						return;
					}else{
						var town = "";
						var city = "";
						var province = "";
						if(level == 2){/**ֱϽ�С��غ�����*/
							city = id.replace("lastCity","");
							province = tree.getParentId(selectItemId).split("_")[1];
						}else if(level == 3){/**ʡ��*/
							town = id.replace("lastCity","");
							city = tree.getParentId(selectItemId).split("_")[1];
							province = tree.getParentId(tree.getParentId(selectItemId)).split("_")[1];
						}
						createServiceNetwork(town, city, province, bussinessArea);
					}
				}
			}
		} else if ("serviceNetwork_DEL" == menuitemId) {
			if(id.indexOf("serviceNetWork")== -1){
				alert("�ýڵ�Ƿ�������,�޷�����ɾ������.");
				window.parent.document.getElementById("frmCreateServiceNetwork").src = "";
				return;
			}else{
				id = id.replace("serviceNetWork","");
			}
			if(confirm("ȷ��ɾ����������"+tree.getSelectedItemText()+"?")){
				delServiceNetwork(id);
			}
		} else if ("serviceNetwork_EDIT" == menuitemId) {
			if(id.indexOf("serviceNetWork")== -1){
				alert("ѡ�нڵ㲻�Ƿ�������,�޷����б༭����.");
				window.parent.document.getElementById("frmCreateServiceNetwork").src = "";
				return;
			}else{
				id = id.replace("serviceNetWork","");
			}
			editServiceNetwork(id);
		}
	}

	function onTreeClick(id) {
		if(id.indexOf("serviceNetWork")!= -1){
			id = id.split("_")[1].replace("serviceNetWork","");
			document.getElementById("frmInput").action = "${ctx}/business/businessManage/serviceNetwork/findServiceNetworkInfo.do";
			document.getElementById("frmInput").target = "frmCreateServiceNetwork";
			document.getElementById("geStationInfoObid").value = id;
			document.getElementById("frmInput").submit();
		}else{
			window.parent.document.getElementById("frmCreateServiceNetwork").src = "";
		}
		return true;
	}
	
	function createServiceNetwork(town,city,province,businessArea) {
		window.parent.document.getElementById("frmCreateServiceNetwork").src = 
			"${ctx}/business/businessManage/serviceNetwork/create/frmCreate.jsp?town="+town+"&city="+city+"&province="+province + "&businessArea=" + businessArea;
	}
	
	function editServiceNetwork(id) {
		document.getElementById("frmInput").action = "${ctx}/business/businessManage/serviceNetwork/findServiceNetworkForUpdate.do";
		document.getElementById("frmInput").target = "frmCreateServiceNetwork";
		document.getElementById("geStationInfoObid").value = id;
		document.getElementById("frmInput").submit();
	}
	//ɾ������
	function delServiceNetwork(id) {
		document.getElementById("frmInput").action = "${ctx}/business/businessManage/serviceNetwork/deleteServiceNetwork.do";
		document.getElementById("frmInput").target = "myFrame";
		document.getElementById("geStationInfoObid").value = id;
		document.getElementById("frmInput").submit();
	}
	
	//ˢ�½ڵ�
	function flushNode(parentId){
		tree.refreshItem(parentId);
	}
</script>
<form id="frmInput" action="" target="">
	<input type="hidden" id="geStationInfoObid" name="geStationInfo.obid">
	<input type="hidden" id="geStationInfoProvince" name="geStationInfo.province">
	<input type="hidden" id="geStationInfoCity" name="geStationInfo.city">
	<input type="hidden" id="geStationInfoTown" name="geStationInfo.town">
	<input type="hidden" id="geStationProvinceCicoObid" name="geStationProvinceCico.obid">
	<iframe style="display:none;" id="myFrame" name="myFrame"></iframe>
</form>
</html>