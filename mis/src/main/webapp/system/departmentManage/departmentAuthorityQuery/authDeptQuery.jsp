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
				<div id="authorityTree" style="width: 400px;height:445px; "></div>
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
</body>
<script type="text/javascript">
var checkAuthorityid =$("#authorityid",window.top.opener.document).val();
var openType="${openType}";
var type="${type}";
var checkType="${checkType}";
var parentFilterId = "${param.parentFilterId}";
if(parentFilterId == "")parentFilterId = "0";
var resultStr = "";
//初始化tree----------------------------------------------------------////
tree=new dhtmlXTreeObject("authorityTree","100%","100%",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(1);
tree.enableThreeStateCheckboxes(true);
tree.loadXML("${ctx}/deptAuthority/findQueryDeptAuthTree.do?id="+parentFilterId+"&isFilterAuthority=1&deptID=${param.authorityid}",loadOver);
$("#sub").click(function(){
	var departmentIds = tree.getAllChecked();/**所有被选中的节点*/
	var authorityArray = departmentIds.split(",");
	var receivedObjName = "";/**选中节点对应的名称*/

	if(checkType == 2){
		var department = tree.getAllChildless();/**所有被选中的子节点*/
		var authority = department.split(",");
		if(authority.length==1){
			departmentIds = authority;
			receivedObjName = tree.getItemText(authority)
			$("#authorityDepartmentManager",window.top.opener.document).val(receivedObjName);
			$("#authorityid",window.top.opener.document).val(departmentIds);
			window.top.opener.authorityid = departmentIds;
			window.top.close();
			return;
		}
		if(authorityArray.length > 1){//单选
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
				
				var showItemArr = getShowItem(authorityArray,departmentIds);
				for(var i = 0; i < showItemArr.length; i++){
					var NodeText;
					if(tree.getLevel(showItemArr[i])==3&&tree.getItemText(showItemArr[i]).indexOf('总公司')==-1){
						NodeText = tree.getItemText(tree.getParentId(showItemArr[i])) + tree.getItemText(showItemArr[i]);
					}else if(tree.getLevel(showItemArr[i])==4){
						NodeText = tree.getItemText(tree.getParentId(tree.getParentId(showItemArr[i]))) + tree.getItemText(showItemArr[i]);
					}else{
						NodeText = tree.getItemText(showItemArr[i]);
					}
					if( i == showItemArr.length-1){
						receivedObjName = receivedObjName + NodeText;
					}else{
						receivedObjName = receivedObjName + NodeText + ",";
					}
				}
			}
			
			if(receivedObjName.length > 100){
				receivedObjName = receivedObjName.substring(0,100)+"...";
			}
			$("#authorityDepartmentManager",window.top.opener.document).val(receivedObjName);
		
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

function loadOver(){
	var checkedItemArr = checkAuthorityid.split(",");
	for(var i = 0; i < checkedItemArr.length; i++){
		tree.setCheck(checkedItemArr[i],1);
	}
	$("#showListLoading").hide();
}
function getShowItem(authorityArray,departmentIds){
	
	resultStr  = ","+departmentIds+",";
	getFilterItem("parent_1");
	for(var i = 0; i < authorityArray.length; i++){
		if(resultStr.indexOf((","+authorityArray[i]+",")) == -1)continue;
		var allSubArr = tree.getAllSubItems(authorityArray[i]).split(",");
		for(var j = 0; j < allSubArr.length; j++){
			if(resultStr.indexOf(","+allSubArr[j]+",") != -1){
				resultStr = resultStr.replace((","+allSubArr[j]+","),",");
			}
		}
	}
	resultStr = resultStr.substring(1,resultStr.length-1);
	return resultStr.split(",");
	
}

function getFilterItem(itemId){
	var subChilds = tree.getSubItems(itemId);
	if(subChilds.indexOf(",") == -1 && itemId.indexOf("parent_") != -1){
		resultStr = resultStr.replace((","+itemId+","),",");
		getFilterItem(subChilds);
	}
}
</script>
</html>
