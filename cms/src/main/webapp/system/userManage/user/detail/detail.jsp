<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="GeOperator" value="${requestScope.GeOperatorForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/page2.js"></script>
<title>电子商务后台管理系统-编辑用户信息</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			用户详细
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="padding-top:10px;">
	<form action="${ctx}/system/userManage/updateGeOperator.do" id="frmInput" method="post" target="myFrame">
	<table class="public_table1" style="width:650px" cellpadding="0" cellspacing="0">
	<tr>
		<td class="td_head" width="65px" nowrap>登录账户：</td>
		<td class="td_body" width="200px">${geOperator.operatorid}</td>
		<td class="td_head" nowrap>用户姓名：</td>
		<td class="td_body" >${geOperator.operatorname}</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>用户性别：</td>
		<td class="td_body">${geOperator.sex == '1'?'男':'女'}</td>
		<td class="td_head" nowrap>联系电话：</td>
		<td class="td_body" >${geOperator.phone}</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>电子邮箱：</td>
		<td class="td_body" >${geOperator.email}</td>
		<td class="td_head" nowrap>用户状态：</td>
		<td class="td_body">${geOperator.state eq '01'?'可用':'不可用'}</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>所属机构：</td>
		<td class="td_body" colspan="3">${cityOld}</td>
	</tr>
	</table>
	<div class="public_list_title" style="width:650px;color:#4aa78c;">该用户所属用户组如下：</div>
	<!-- <table align="center" cellpadding=0 cellspacing=0 border=0 style="width:650px;">
		<tr>
			<td class="td_head" width="65px" nowrap>所属机构：</td>
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
	</table>-->
	<div id="groupDiv" style="clear:both;">
		<table border=0 style="font-size:9pt;width:650px;" cellpadding=0 cellspacing=0 align="center"  id="groupTable">
			<tr id="title">
				<td class="search_head" width="10%" nowrap id="serial">序号</td>
				<td class="search_head" width="20%" nowrap id="groupId">用户组编号</td>
				<td class="search_head" width="20%" nowrap id="groupName">用户组名称</td>
				<td class="search_head" width="20%" nowrap id="groupDeptName">所属机构</td>
				<td class="search_head" width="20%" nowrap id="groupBusinessArea">业务领域</td>
				<!--  <td class="search_head" width="20%" nowrap id="operate">操作</td>-->
			</tr>
			<tr>
				<td colspan="5" class="search_body_center">
				</td>
			</tr>
		</table>
		<table border=0 style="font-size:9pt;width:650px;" cellpadding=0 cellspacing=0 align="center">
			<tr>
				<td>
					<div style="float: none;"><input type="hidden" name="l" id="pageNo" value="1"/></div>
					<div class="page_div" id="page" style="clear:none;margin-top:10px;float: right;"></div>
				</td>
			</tr>
		</table>
	</div>
	<div style="padding-top:10px;"></div>
	<table width=164 align="center">
		<tr>
		<acc:showView source="ROLE_S_USER_U">
			<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>编辑</td>
		</acc:showView>
		<acc:showView source="ROLE_S_USER_D">
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>删除</td>
		</acc:showView>
		<acc:showView source="ROLE_S_USER_M_P">
			<td id="resetPassButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="resetPass();" nowrap>重置密码</td>
		</acc:showView>
			<td id="closeButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>关闭</td>
		</tr>
	</table>
	</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
function getMapValue(key,map){
	var arr = map.replace("{","").replace("}","").split(",");
	for(var i = 0; i < arr.length; i++){
		var keyAndValue = arr[i].replace(/(^\s*)|(\s*$)/g, "").split("=");
		if(keyAndValue[0].replace(/(^\s*)|(\s*$)/g, "") == key){
			return keyAndValue[1];
		}
	}
}
function doEditGeGroups(){
	window.location.href="${ctx}/system/userManage/queryGeGroupForUser.do?userId=${geOperator.operatorid}";
}
function showGroupDetail(idStr){
	 window.open("${ctx}/system/groupManage/queryGeGroupDetail.do?id=" + idStr,"查看用户组" ,"top=200, left=300, width=900,height=600,toolbar=no,scrollbars=yes");
}
function doEdit(){
	window.location.href = "${ctx}/system/userManage/queryGeOperatorForUpdate.do?geOperator.operatorid=${geOperator.operatorid}";
}
function doDelete(){
	if(confirm("确定删除此用户吗？")){
		$("#frmInput")[0].action = "${ctx}/system/userManage/deleteGeOperator.do?idStr=${geOperator.operatorid}";
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
	 
	var operatorid = '${geOperator.operatorid}';
	var authorityChoose = $("#authorityid").val();
	$.ajax({
		   cache :false,
		   type: "POST",
		   url:"${ctx}/system/userManage/findGroupByAuth.do?geOperator.operatorid="+operatorid,
		   data: {pageNo: $("#pageNo").val(), pageSize:"8",authorityid:authorityChoose},
		   dataType:"json",
		   success: function(data){
			   $("#groupTable").find("tr").not(":first").remove();
			   $("#page").empty();
			   var totalCount = data.totalCount;
			  if(data.result==""){
				$("#title").after("<tr style='background:#fff;border:1px solid #a1a29c;'><td class='search_body_center'  nowrap colspan='6' height='20' align='center' valign='middle'>没有查询到符合条件的数据</td></tr>");
			  }else{
				$.each(data.result, function(index, item){
					var num = data.pageSize*(data.currentPageNo-1) + parseInt(index+1);
					var row = $("#groupTable").find("tr:first").clone();
					row.find("#serial").removeClass("search_head").addClass("search_body_center").text(num);
					row.find("#groupId").removeClass("search_head").addClass("search_body_center").text(item.groupid);
					row.find("#groupName").removeClass("search_head").addClass("search_body_center").text(item.groupname);
					row.find("#groupDeptName").removeClass("search_head").addClass("search_body_center").text(item.deptname);
					row.find("#groupBusinessArea").removeClass("search_head").addClass("search_body_center").text(getMapValue('${geOperator.businessarea}','${areaMap}'));
					row.find("#operate").removeClass("search_head").addClass("search_body_center").html("<span style=' width:20px;'><a class='zc-lk1' href='javascript:showGroupDetail(\"" + item.groupid + "\");'>详细</a></span>");
					row.appendTo("#groupTable");
				 });
				if(totalCount>8){
					pageChange(data.currentPageNo,data.pageSize, data.totalPageCount,data.totalCount,"page");
				}else{
					$("#page").append("<span style='float:right;padding-right:9px;'>共" + totalCount + "条，全部显示</span>");
				}
			  }
		   },
		   error:function(XMLHttpRequest, textStatus, errorThrown){
			   
		   }
		}); 
	var ids=document.getElementById("authorityDepartmentManager").value;
}

function resetPass(){
	if(confirm("确定重置此用户密码吗？")){
		$.ajax({
		   cache :false,
		   type: "POST",
		   url:"${ctx}/system/userManage/updateOpPassword.do",
		   data: {"operatorid": "${geOperator.operatorid}"},
		   dataType:"json",
		   success: function(data){
			   if(data[0] == "1"){
				   alert("重置密码成功,新密码为："+data[1]);
			   }else{
				   alert(data[1]); 
			   }
		   },
		   error:function(XMLHttpRequest, textStatus, errorThrown){
			   
		   }
		}); 
	}
}
</script>
</html>
