<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>电子商务后台管理系统-用户设置</title>
</head>
<body>
<table id="demo" style="display:none;">
	<tr id = "personalSet_tr_demo" style="display:none">
	  	<td class="search_body_center"><input type="checkbox" id="" name="checkChild" value=""></td>
		<td class="search_body_center"></td>
		<td class="search_body_center"></td>
		<td class="search_body_center"></td>
		<td class="search_body_center"></td>
	</tr>
	<tr id="operatorsDelete_menu_demo" style="display:none">
		<td class="search_head"><input onclick="checkAll(this)" type="checkbox" id="" value=""></td>
		<td id="operatorId" class="search_head">登录名</td>
		<td id="operatorName" class="search_head">用户姓名</td>
		<td id="operatorDept" class="search_head">所属机构</td>
		<td id="operatorBus" class="search_head">业务领域</td>
	</tr>
</table>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			用户设置
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="height:10px">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" width="90%" align="center" cellspacing="0" cellpadding="0" >
	<tr>
		<td class="frmCreate_kuang_header" width="300px" style="border-right:1px solid #dcdcdc;text-align:center;">机构列表</td>
		<td class="frmCreate_kuang_header" style="text-align:center;">机构用户列表</td>
	</tr>
	<tr>
		<td valign="top" style="border-right:1px solid #dcdcdc;">
			<div id="showListLoading" style="position:absolute;">
				<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
			</div>
			<div id="authDeptTree" style="overflow-x:hidden;width:305px;"></div>
		</td>
		<td valign="top">
			<div id="list" style="overflow-y:auto;padding:5px 2px 0px 2px;text-align:center;margin:0px auto;">
				<table id="personalSet" class="public_table1" style="width:95%;">
  				</table>
	 		</div>
		</td>
	</tr>
</table>
<table align="center" height="60px;">
<tr>
	<td>
		<table width="100%" align="center">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" id="saveGroupUser">保存设置</td>
				<td id="backButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>返回</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<input type="hidden" id="selectCity" value="">
</body>
<script type="text/javascript">
var kuang = document.getElementById("kuang");
kuang.style.height = document.body.clientHeight-165;

if(document.body.clientWidth > 900){
	kuang.style.width = 900;
}

var authorityTrees = document.getElementById("authDeptTree");
authorityTrees.style.height = document.body.clientHeight-168;

var list = document.getElementById("list");
list.style.height = document.body.clientHeight-165;

function doBack(){
	window.location.href="${ctx}/system/groupManage/queryGeGroupDetail.do?id=${groupid}";
}

//初始化tree----------------------------------------------------------////
tree=new dhtmlXTreeObject("authDeptTree","100%","100%",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.setOnClickHandler(HandleTreeClk);//设置鼠标点击事件
tree.loadXML("${ctx}/system/groupManage/operatorSetDeptAuthTree.do?id=0&groupId=${groupid}",loadOver);
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);

function HandleTreeClk(id){
	if(tree.hasChildren(id) != 0){
		noStyle(id);
		if(tree.getOpenState(id) == 1){
			tree.closeItem(id);
		}else{
			tree.openItem(id);
		}
		return false;
	}else{
		var param = {"groupid":"${groupid}","deptid":id};
		purviewAjax("${ctx}/system/groupManage/queryGeGroupDeptOperators.do",param,"queryGeGroupDeptOperators","操作失败,请稍后操作!");
		changeStyle(id);
		$("#selectCity").val(id);
	}
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

$(document).ready(function(){
	$("#saveGroupUser").click(function(){/**保存组用户信息*/
		var updateOperator = getCheckedOperators(); 
		var groupid = "${groupid}";
		var selectCity = $("#selectCity").val();
		var param = {"groupid":groupid,"selectCity":selectCity,"updateOperator":updateOperator};
		purviewAjax("${ctx}/system/groupManage/updateGeGroupOperators.do",param,"updateGeGroupOperators","操作失败,请稍后操作!");
	});
});

/**点击机构查询用户信息*/
function queryGeGroupDeptOperators(data){
	initialArray = new Array(); 
	var tem_menu = $("#operatorsDelete_menu_demo").clone();
	$(tem_menu).attr("id","operatorsDelete_menu");
	$(tem_menu).css("display","");
	$("#personalSet").html(tem_menu);
	if(data.length > 0){
		$.each(data, function(i,item) {   
			var tem_tr = $("#personalSet_tr_demo").clone();
			$(tem_tr).attr("id","tr_personalSet_"+this.OPERATORID);
			var tem_input = $(tem_tr).find("input[type='checkbox']");
			$(tem_input).attr("id","sr_"+this.OPERATORID);
			$(tem_input).val(this.OPERATORID);
			if(this.GROUPID==null){
				$(tem_input).attr("checked",false);
			}else{
				$(tem_input).attr("checked",true);
				initialArray[initialArray.length]=this.OPERATORID;
			}
			$(tem_tr).find("td").eq(1).append(this.OPERATORID);
			$(tem_tr).find("td").eq(2).append(this.OPERATORNAME);
			$(tem_tr).find("td").eq(3).append(this.DEPTNAME);
			$(tem_tr).find("td").eq(4).append(getMapValue(this.BUSINESSAREA,'${areaMap}'));
			$(tem_tr).css("display","");
			$("#personalSet").append(tem_tr);
		});
		$("#personalSet").append("<tr><td colspan=5>&nbsp;</td></tr>");
	}else{
		$("#personalSet").append("<tr><td colspan=5 class='search_body_center'>该机构下暂时没有用户</td></tr>");
	}
	
}

//更新用户组中用户
function updateGeGroupOperators(date){
	if(date == "success"){
		alert("保存成功！");
		doBack();
	}else{
		alert(date);
	}
}

//异步传输
function purviewAjax(url, param, fun, errorMess){
	$.ajax({
	    url: url,
	    data: param,	   
	    type: 'POST',
	    dataType: 'json',
	    error: function(){
	        alert(errorMess);
	    },
	    success: function(data){
	    	eval(fun+"(data)");
	    }
	});
}

function getMapValue(key,map){
	var arr = map.replace("{","").replace("}","").split(",");
	for(var i = 0; i < arr.length; i++){
		var keyAndValue = arr[i].replace(/(^\s*)|(\s*$)/g, "").split("=");
		if(keyAndValue[0].replace(/(^\s*)|(\s*$)/g, "") == key){
			return keyAndValue[1];
		}
	}
}

function checkAll(obj){
	var checkArray = document.getElementsByName("checkChild");
	for(var i = 0; i < checkArray.length; i++){
		checkArray[i].checked = obj.checked;
	}
}

function getCheckedOperators(){
	var checkedOperators = "";
	var checkArray = document.getElementsByName("checkChild");
	for(var i = 0; i < checkArray.length; i++){
		if(checkArray[i].checked == true){
			if(checkedOperators == ""){
				checkedOperators = checkArray[i].value;
			}else{
				checkedOperators += "," + checkArray[i].value;
			}
		}
		
	}
	return checkedOperators;
}

function loadOver(){
	$("#showListLoading").hide();
}
</script>
</html>
