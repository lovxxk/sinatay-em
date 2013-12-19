<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ page import = "java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>电子商务后台管理系统</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
</head>
<body leftmargin="0" topmargin="0" style="background:url(${ctx}/global/images/face/left_bg.jpg) repeat-x;" >
<table id="diretoryAttributeTable" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td style="border:1px solid #BEBEBE;">
		<div id="showListLoading" >
			<div class="loading_process" style="height:50px;">机构树加载中，请稍后 . . .</div>
		</div>
			<div style="overflow-y:auto;overflow-x:hidden;">
				<div id="userManageTree" style="width: 400px;height:445px; "></div>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border:1px solid #BEBEBE;height:50px;">
			<table align="center">
				<tr>
					<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" id="sub" nowrap>确认</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<input type="hidden" id="authorityDepartmentManager" value="${geOperator.deptid}"/>
<input type="hidden" id="authorityid" value="${geOperator.deptid}"/>

</body>
<script type="text/javascript">

//初始化tree----------------------------------------------------------////
var tree=new dhtmlXTreeObject("userManageTree","100%","100%",0); 
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.setOnDblClickHandler(tondblclick);
tree.setOnClickHandler(tonclick);
tree.setXMLAutoLoading("${ctx}/system/userManage/operatorManageDeptTree.do");
tree.loadXML("${ctx}/system/userManage/operatorManageDeptTree.do?id=0",loadOver);
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);
function tonclick(id){
	changeStyle(id);
	var level = tree.getLevel(id);
	var comName = "";
	var pId = id;
	var dId = id;
	if(tree.hasChildren(id) != 0){
		if(tree.getOpenState(id) == 1){
			tree.closeItem(id);
		}else{
			tree.openItem(id);
		}
	}
	if(dId.indexOf('parent_') >= 0){
		dId = id.substring(id.lastIndexOf('_')+1,id.length);
	}
	for(var i = level; i > 0; i--){
		if(i == level){
			comName = tree.getItemText(pId);
		}else{
			comName = tree.getItemText(pId)+"/" + comName;
		}
		var pId = tree.getParentId(pId);
	}
	$("#authorityDepartmentManager").val(comName);
	$("#authorityid").val(dId);
}
function tondblclick(id){
	doSearch();
}

function changeStyle(itemId){
	var idArr = tree.getAllSubItems('0').split(",");
	for(var i = 0; i < idArr.length; i++){
		tree.setItemStyle(idArr[i],"background:none;color:black;font-weight:normal;");
	}
	tree.setItemStyle('0',"background:none;color:black;font-weight:normal;");
	tree.setItemStyle(itemId,"background:#819FF7;color:white;font-weight:bold;");
}

function noStyle(itemId){
	tree.setItemStyle(itemId,"background:none;color:black;font-weight:normal;");
}
function loadOver(sIdNow){
	$("#showListLoading").hide();
	$("#authorityDepartmentManager").val($("#authorityDepartmentManager",window.top.opener.document).val());
	$("#authorityid").val($("#authorityid",window.top.opener.document).val());
}
function doSearch(){
	if($("#authorityid").val() != ''){
		$("#authorityDepartmentManager",window.top.opener.document).val($("#authorityDepartmentManager").val());
		$("#authorityid",window.top.opener.document).val($("#authorityid").val());
		window.top.close();
	}else{
		alert("请选择所属机构!");
		return false;
	}
}
</script>
</html>
