<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ page import = "java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>电子商务后台管理系统</title>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
</head>
<body style="background:url(${ctx}/global/images/left_bg.jpg) repeat-x;" leftmargin="0" topmargin="0">
<table id="diretoryAttributeTable" style="background:url(${ctx}/global/images/left_bg.jpg) repeat-x;"
	cellspacing="0" cellpadding="0" border="0" style="width:100%;height:100%;">
	<tr height="100%">
		<td>
			<div style="overflow-y:auto;overflow-x:hidden;">
				<div id="authorityTree" style="width: 400px; height: 445px;"></div>
			</div>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
var openType="${param.openType}";
var type="${param.type}";
var checkType="${param.checkType}";
var deptIdCount = "${param.deptIdCount}";
alert("openType="+openType);
alert("type="+type);
alert("checkType="+checkType);
alert("deptIdCount"+deptIdCount);
//######
var ft="${param.fromWorkFlow}";


tree=new dhtmlXTreeObject("authorityTree","400px","445px",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(1);
tree.setXMLAutoLoading("${ctx}/deptAuthority/findQueryDeptAuthTree.do?isFilterAuthority=1&deptID=${param.authorityid}");
tree.loadXML("${ctx}/deptAuthority/findQueryDeptAuthTree.do?id=0&isFilterAuthority=1&deptID=${param.authorityid}&receivedObj=${param.receivedObj}");
tree.setOnCheckHandler(checkHandler);
tree.setOnOpenEndHandler(loadOver);	
$("#sub",window.top.configue.document).click(function(){
	var departmentIds = tree.getAllChecked();/**所有被选中的节点*/
	var authorityArray = departmentIds.split(",");
	var receivedObjName = "";/**选中节点对应的名称*/
	


	if(checkType==2){//单选
		if(authorityArray.length>1){
			alert("只能选择一个机构！");
			return;
		}
	}
	if(openType=="iframe"){//iframe
		if(type==2){//input赋值
			parent.document.getElementById('authorityid').value= departmentIds;
		}else{//js赋值
			parent.authorityid = departmentIds;
		}
	}else{//window.open
		if(type==2){
			$("#authorityid",window.top.opener.document).val(departmentIds);
			if(departmentIds == ""){
				if(checkType==2){//单选
					receivedObjName = "--请选择--";
				}else{
					receivedObjName = "--全部--";
				}
			}else{
				for(var i = 0; i < authorityArray.length; i++){
					if( i == authorityArray.length-1){
						receivedObjName = receivedObjName + tree.getItemText(authorityArray[i]);
					}else{
						receivedObjName = receivedObjName+tree.getItemText(authorityArray[i]) + ",";
					}
				}
			}
			//$("#authorityDepartmentManager",window.top.opener.document).val(receivedObjName);
			alert("departmentIds="+departmentIds);
			alert("deptIdCount="+deptIdCount);
			window.top.opener.document.getElementsByName("deptID")[parseInt(deptIdCount)].value=receivedObjName;
			//
			//回写录入域
			//回写隐藏域代码
			
			window.top.close();
			if($("#authorityid",window.top.opener.document).attr("backFunction") != undefined){
				window.top.opener.deptAuthBack();
			}
		}else{//js赋值
			window.top.opener.authorityid = departmentIds;
			window.top.close();
		}
	}
});

function checkHandler(sIdNow){
	var checkedBranches = tree.getAllCheckedBranches().split(",");
	/*
	for (var i = 0; i < checkedBranches.length; i++) {
		if (checkedBranches[i].length > 0 && checkedBranches[i].substr(0,1) != sIdNow.substr(0,1)) {
			tree.setCheck(sIdNow,"0");
			//alert("只能选择一个业务领域！");
			//return;
		}
	}*/
	if(checkType != 2){/**多选*/
		if(tree.getOpenState(sIdNow) == 1){
			if(tree.isItemChecked(sIdNow)==0){
				tree.setSubChecked(sIdNow,"0");/**update child*/
				unCheckParentNodes(sIdNow);/**update parent*/
			}else{
				tree.setSubChecked(sIdNow,"1");
				tree.openItem(sIdNow);
			}
		}else{
			if(tree.isItemChecked(sIdNow)==0){
				unCheckParentNodes(sIdNow);/**update parent*/
			}else{
				checkParentNodes(sIdNow);/**update parent*/
			}
			tree.openItem(sIdNow);
		}
	}else{/**单选*/
		tree.setSubChecked("0","0");
		tree.setCheck(sIdNow,"1");
	}
}

function loadOver(sIdNow){
	if(checkType != 2){/**多选*/
		if(sIdNow.indexOf("area") != -1)return  true;
		if(tree.isItemChecked(sIdNow)==0){
			tree.setSubChecked(sIdNow,"0");
		}else if(tree.isItemChecked(sIdNow)==1){
			var bl = true;
			var childArr = tree.getSubItems(sIdNow).split(",");
			for(var j = 0; j < childArr.length; j++){
				if(tree.isItemChecked(childArr[j])==1){
					bl = false;
					break;
				}
			}
			if(bl){
				tree.setSubChecked(sIdNow,"1");
			}
		}
		if(tree.getOpenState(sIdNow) == 1){
			var idArr = tree.getAllSubItems(sIdNow).split(",");
			for(var i = 0; i < idArr.length; i++){
				tree.openItem(idArr[i]);
			}
		}else {/**单选*/
			return true;
		}
	}else{
		return true;
	}
}

/**
 * update parent node
 */
function unCheckParentNodes(sIdNow){
	var level = tree.getLevel(sIdNow);
	var childId = sIdNow;
	var bl = false;/**false表示没有子节点被选中*/
	if(level > 3){
		for(var i = level-1; i > 2; i--){
			childId = tree.getParentId(childId);
			var childArr = tree.getSubItems(childId).split(",");
			for(var j = 0; j < childArr.length; j++){
				if(tree.isItemChecked(childArr[j])==1){
					bl = true;
					break;
				}
			}
			if(bl){
				break;
			}else{
				tree.setCheck(childId,"0");
			}
		}
	}
}

/**
 * update parent node
 */
function checkParentNodes(sIdNow){
	var level = tree.getLevel(sIdNow);
	var childId = sIdNow;
	var bl = false;/**false表示没有子节点被选中*/
	if(level > 3){
		for(var i = level-1; i > 2; i--){
			childId = tree.getParentId(childId);
			tree.setCheck(childId,"1");
		}
	}
}
</script>
</html>
