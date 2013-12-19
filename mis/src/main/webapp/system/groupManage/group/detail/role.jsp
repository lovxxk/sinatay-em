<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geGroupOperators" value="${requestScope.geGroupOperators}" />
<c:set var="roleExist" value="${requestScope.roleExist}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/page2.js"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<title>电子商务后台管理系统-查看用户组详细</title>
</head>
<body leftmargin="0" topmargin="0">
<table class="public_table1"  id="roleTable" style="width:100%;">
	<tr id="title">
		<td class="search_head" width="35%" nowrap id="roleId">角色编号</td>
		<td class="search_head" width="50%" nowrap id="roleName">角色名称</td>
		<!--  <td class="search_head" width="15%" nowrap id="operate">操作</td>-->
	</tr>
</table>
</body>
<script type="text/javascript">

doSearch();
function doSearch(){
	$("#roleTable").find("tr").not(":first").remove();
	$("#page").empty();
	$("#title").after("<tr><td colspan='6' class='search_body_center'><table align='center' style='width:200px;color: #077f52;font-size: 16px;font-family: Microsoft YaHei, Hei, serif;'><tr><td><img src='${ctx}/global/images/loading1.gif' /></td><td>数据加载中，请稍后 . . .</td></tr></table></td></tr>");
	
	var groupid = '${param.groupid}';
	var authorityChoose = $("#authorityid").val();
	$.ajax({
		cache :false,
		type: "POST",
		url:"${ctx}/system/groupManage/roleByGroup.do",
		data: {pageNo: "1", pageSize:"1000",authorityid:authorityChoose,groupid:groupid},
		dataType:"json",
		success: function(data){
			$("#roleTable").find("tr").not(":first").remove();
			if(data == ""){
				$("#title").after("<tr style='background:#fff;border:1px solid #a1a29c;'><td class='search_body_center'  nowrap colspan='6' height='20' align='center' valign='middle'>该组暂无角色</td></tr>");
			}else{
			$.each(data, function(index, item){
					var row = $("#roleTable").find("tr:first").clone();
					row.find("#roleId").removeClass("search_head").addClass("search_body_center").text(item.roleID);
					row.find("#roleName").removeClass("search_head").addClass("search_body_center").text(item.roleName);
					row.find("#operate").removeClass("search_head").addClass("search_body_center").html("<span style=' width:40px;'><a class='zc-lk1' href='javascript:showRoleDetail(\"" + item.roleID + "\");'>详细</a></span>");
					row.appendTo("#roleTable");
				 });
			  }
		   },
		   error:function(XMLHttpRequest, textStatus, errorThrown){
			   
		   }
		}); 
}
function deptAuthIdQuery(){
	window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_S_ROLE_M&type=2',
			'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
}

function showRoleDetail(idStr){
	window.open("<%=request.getContextPath() %>/system/roleManage/detail.do?geRole.roleID=" + idStr,"角色详细" ,"top=100, left=100, width=900,height=600,toolbar=no,scrollbars=yes");
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
</script>

</html>
