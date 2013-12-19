<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx}/global/js/page2.js"></script>
<title>电子商务后台管理系统-查看角色信息</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			角色详细信息
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div>
<div style="height:10px;clear:both;">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" style="width:95%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang_header" style="border-right:1px solid #dcdcdc;text-align:center;">角色详细信息</td>
		<td class="frmCreate_kuang_header" style="width:420px;text-align:center;">角色权限信息</td>
	</tr>
	<tr>
		<td id="basicInfo" valign="top" style="border-right:1px solid #dcdcdc;">
			<table align="left" style="width:100%;line-height:25px;" cellpadding="0" cellspacing="0">
			<tr>
				<td class="td_head" width="65px" nowrap>角色编号：</td>
				<td class="td_body" width="362px">${geRole.roleID}</td>
			</tr>
			<tr>
				<td class="td_head" width="65px" nowrap>角色名称：</td>
				<td class="td_body" width="362px">${geRole.roleName}</td>
			</tr>
			<tr height="5px">
				<td colspan="2"></td>
			</tr>
			<tr>
				<td colspan="2" class="frmCreate_kuang" valign="top">
					<div class="frmCreate_kuang_header" style="padding-left: 0px;">
						角色描述：
					</div>
					<div style="padding-left:3px;height: 80px;overflow-y:auto;width: 427px;">
						<textarea class="textarea_disabled" style="height: 98%; " readonly="readonly">${geRole.roleDesp}</textarea>
					</div>
				</td>
			</tr>
			
			</table>
		</td>
		<td style="width:320px;padding:0px;margin:0px;" valign="top">
			<div id="showListLoading"  style="position:absolute;">
				<div class="loading_process" style="height:50px; font-size: 17px;">权限树加载中，请稍后 . . .</div>
			</div>
			<div style="padding-left:3px; height:100%;">
				<div id="authorityTree" style="overflow: no; width:100%;height:100%;"></div>
			</div>
		</td>
	</tr>
</table>
<div style="padding-top:18px;"></div>
<table style="width:95%;" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="2">
			<div class="public_list_title" style="color:#4aa78c;width:100%;clear:both;">拥有该角色的用户组如下：</div>
			<!--<div style="clear:both;">
				 <table align="left" cellpadding=0 cellspacing=0 border=0 style="padding-left: 0px;">
				
					<tr style="padding-left: 0px;">
						<td class="td_head" width="65px" nowrap>创建机构：</td>
						<td class="td_body" width="250px">
							<div>
								<div style="width:140px;float:left;">
									<input type="text" id="authorityDepartmentManager" value="--全部--" style="width:130px;" readonly>
									<input type="hidden" id="authorityid" name="authorityid" value=""/>
								</div>
								<div style="float:left;">
									<input style="width:100px;" onclick="deptAuthIdQuery();" type="button" value="选择机构权限…"/>
								</div>
					  	  </div>
						</td>
						<td>
							<table align="left">
								<tr>
									<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
										onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
								</tr>
							</table>
						</td>
					</tr>
				</table> 
			</div>-->
			<div style="clear:both;">
				<table id="groupTable" border=0 style="width:100%;font-size:9pt;" cellpadding=0 cellspacing=0 align="center">
					<tr id="title">
						<td class="search_head" width="10%" nowrap id="serial">序号</td>
						<td class="search_head" width="20%" id="groupId">组编号</td>
						<td class="search_head" width="20%" id="groupName">组名称</td>
						<td class="search_head" width="20%" id="deptName">创建机构</td>
						<td class="search_head" width="20%" id="groupBusinessArea">业务领域</td>
					<!--  <td class="search_head" width="20%" id="operate">操作</td>-->
					</tr>
				</table>
				<table border=0 style="width:100%;font-size:9pt;" cellpadding=0 cellspacing=0 align="center">
					<tr>
						<td>
							<div style="clear:both;"><input type="hidden" name="l" id="pageNo" value="1"/></div>
							<div class="page_div" id="page" style="clear:both;margin-top:10px;float: right;"></div>
						</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
</table>
<table align="center">
	<tr>
		<c:if test="${geRole.deptID == geOperator.deptid}">
		<acc:showView source="ROLE_S_ROLE_U">
		<td id="updateButton" width="82" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
			onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>编辑</td>
		</acc:showView>
		<acc:showView source="ROLE_S_ROLE_D">
		<td id="resetButton" width="82" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
			onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>删除</td>
		</acc:showView>
		</c:if>
		<td id="closeButton" width="82" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>关闭</td>
	</tr>
</table>
<form action="" id="frmInput" method="post" target="myFrame">
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
<script language="javascript">
function getMapValue(key,map){
	var arr = map.replace("{","").replace("}","").split(",");
	for(var i = 0; i < arr.length; i++){
		var keyAndValue = arr[i].replace(/(^\s*)|(\s*$)/g, "").split("=");
		if(keyAndValue[0].replace(/(^\s*)|(\s*$)/g, "") == key){
			return keyAndValue[1];
		}
	}
}
var kuang = document.getElementById("kuang");
kuang.style.height = document.body.clientHeight-420;

if(document.body.clientWidth > 900){
	kuang.style.width = 900;
}

var authorityTrees = document.getElementById("authorityTree");
authorityTrees.style.height = document.body.clientHeight-448;

var basicInfo = document.getElementById("basicInfo");
basicInfo.style.height = document.body.clientHeight-448;

var tree=new dhtmlXTreeObject("authorityTree","100%","100%",0); 
tree.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.loadXML("${ctx}/system/roleManage/findRoleAuthorityDetail.do?roleID=${geRole.roleID}",loadOver);
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);

function loadOver(){
	$("#showListLoading").hide();
}

function showGroupDetail(idStr){
	window.open("${ctx}/system/groupManage/queryGeGroupDetail.do?id=" + idStr,"查看用户组" ,"top=100, left=100, width=900,height=600,toolbar=no,scrollbars=yes");		
}
function doEdit(){
	window.location.href = "${ctx}/system/roleManage/queryGeRoleForUpdate.do?geRole.roleID=${geRole.roleID}";
}
function doDelete(){
	if(confirm("确定删除此角色吗？")){
		$("#frmInput")[0].action = "${ctx}/system/roleManage/deleteGeRole.do?idStr=${geRole.roleID}";
		$("#frmInput")[0].submit();
	}
}
function deptAuthIdQuery(){
	window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_S_ROLE_M&type=2',
			'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
}
doSearch();
function doSearch(){
	$("#groupTable").find("tr").not(":first").remove();
	$("#page").empty();
	$("#title").after("<tr><td colspan='6' class='search_body_center'><table align='center' style='width:200px;color: #077f52;font-size: 16px;font-family: Microsoft YaHei, Hei, serif;'><tr><td><img src='${ctx}/global/images/loading1.gif' /></td><td>数据加载中，请稍后 . . .</td></tr></table></td></tr>");
	
	var roleId = '${geRole.roleID}';
	var authorityChoose = $("#authorityid").val();
	$.ajax({
		   cache :false,
		   type: "POST",
		   url:"${ctx}/system/roleManage/findGroupByAuth.do",
		   data: {pageNo: $("#pageNo").val(), pageSize:"6",roleId:roleId,authorityid:authorityChoose},
		   dataType:"json",
		   success: function(data){
			   $("#groupTable").find("tr").not(":first").remove();
			   $("#page").empty();
			   var totalCount = data.totalCount;
			  if(data.result==""){
				$("#title").after("<tr style='background:#fff;border:1px solid #a1a29c;'><td class='search_body_center'  nowrap colspan='6' height='20' align='center' valign='middle'>该角色未分配到任何组内</td></tr>");
			  }else{
				$.each(data.result, function(index, item){
					var num = data.pageSize*(data.currentPageNo-1) + parseInt(index+1);
					var row = $("#groupTable").find("tr:first").clone();
					row.find("#serial").removeClass("search_head").addClass("search_body_center").text(num);
					row.find("#groupId").removeClass("search_head").addClass("search_body_center").text(item.groupid);
					row.find("#groupName").removeClass("search_head").addClass("search_body_center").text(item.groupname);
					row.find("#deptName").removeClass("search_head").addClass("search_body_center").text(item.deptname);
					row.find("#groupBusinessArea").removeClass("search_head").addClass("search_body_center").text(getMapValue(item.deptid.substring(0,1),'${areaMap}'));
					row.find("#operate").removeClass("search_head").addClass("search_body_center").html("<span style=' width:50px;'><a class='zc-lk1' href='javascript:showGroupDetail(\"" + item.groupid + "\");'>详细</a></span>");
					row.appendTo("#groupTable");
				 });
				if(totalCount>6){
					pageChange(data.currentPageNo,data.pageSize,data.totalPageCount,data.totalCount,"page");
				}else{
					$("#page").append("<span style='float:right;padding-right:9px;'>共" + totalCount + "条，全部显示</span>");
				}
			  }
		   },
		   error:function(XMLHttpRequest, textStatus, errorThrown){
			   
		   }
		}); 
	//var ids=document.getElementById("authorityDepartmentManager").value;
}
</script>
</body>
</html>
