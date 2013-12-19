<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
response.setHeader("Cache-Control","No-Cache");//HTTP 1.1
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
<head>
<title>权限管理</title>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/css/mis_basic.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<table id="diretoryAttributeTable" style="background:url(${ctx}/global/images/face/left_bg.jpg) repeat-x;"
	cellspacing="0" cellpadding="0" border="0">
	<tr height="100%">
		<td>
			<table border=0 width="100%" height="100%" cellpadding=0 cellspacing=0>
				<tr>
					<td style="text-align: left;" valign="top">
						<div style="overflow-y:auto;overflow-x:hidden;">
							<div id="leftMenuTree" style="width: 260px; padding-top:5px;"></div>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
<script>
	if(window.navigator.userAgent.indexOf("MSIE")>=1){//如果浏览器为IE
		$("#leftMenuTree").css("height",window.parent.document.getElementById('fraLEFT').height-15);
	}
	$("#leftMenuTree").css("height",document.body.clientHeight);
	var headMenu = "${param['headMenu']}";
	var headMenuName = "${param['headMenuName']}";
	var tree = new dhtmlXTreeObject("leftMenuTree", "100%", "100%", 0);
	tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_bluebooks/");
	tree.setOnClickHandler(onTreeClick);
	tree.setXMLAutoLoading("${ctx}/authorityManage/findLeftMenu.do");
	tree.setOnOpenStartHandler(validateLogin);
	tree.loadXML("${ctx}/authorityManage/findLeftMenu.do?id=0",loadOver);
	tree.setOnOpenEndHandler(loadOver);
	function myErrorHandler(type, desc, erData){
	   return;
	}
	dhtmlxError.catchError("ALL",myErrorHandler);

	function onTreeClick(id) {
		changeStyle(id);
		
		if(id.indexOf("↑↑") != -1){
			var ids = id.split("↑↑");
			var idAutority = ids[0];
			var openType = ids[2];
			var url = ids[1];
			if(openType=="02"){
				window.parent.document.getElementById("fraMAIN").src = '${ctx}'+url;
			}else{
				window.open(url);
			}
			
		}else{
			if(tree.getOpenState(id) == 1){
				tree.closeItem(id);
			}else{
				tree.openItem(id);
			}
		}
	}
	
	function changeStyle(itemId){
		var idArr = tree.getAllSubItems('0').split(",");
		for(var i = 0; i < idArr.length; i++){
			tree.setItemStyle(idArr[i],"background:none;color:black;font-weight:bold;font-size:12px;font-family: Microsoft YaHei, Hei, serif;");
		}
		tree.setItemStyle(itemId,"background:#318F5B;color:white;font-weight:bold;font-size:12px;font-family: Microsoft YaHei, Hei, serif;");
	}
	
	function loadOver(){
		var idArr = tree.getAllSubItems('0').split(",");
		for(var i = 0; i < idArr.length; i++){
			tree.setItemStyle(idArr[i],"font-weight:bold;font-size:12px;font-family: Microsoft YaHei, Hei, serif;}");
		}
	}
	
	function validateLogin(){
		$.post("${ctx}/authorityManage/validateLogin.do",function(data){
			if(data.flg2== "false"){
				window.top.location.href = "${ctx}/system/userManage/passport/login/index.jsp";
			}	
		});
		return true;
	}
</script>
</html>