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
<title>���������̨����ϵͳ-�鿴�û�����ϸ</title>
</head>
<body>
<!--<table class="public_table1" style="width:100%;">
	<tr style="padding-left: 0px;">
		<td class="td_head" width="65px" nowrap>����������</td>
		<td class="td_body" width="250px">
			<div>
				<div style="width:140px;float:left;">
					<input type="text" id="authorityDepartmentManager" value="--ȫ��--" style="width:130px;" readonly>
					<input type="hidden" id="authorityid" name="authorityid" value=""/>
				</div>
				<div style="float:left;">
					<input style="width:100px;" onclick="deptAuthIdQuery();" type="button" value="ѡ�����Ȩ�ޡ�"/>
				</div>
		    </div>
		</td>
		<td>
			<table align="left">
				<tr>
					<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>��ѯ</td>
						<acc:showView source="ROLE_S_GROUP_M_US">
		<td id="userSetButton" class="btnBigOnBlur" width="82px" onmouseover="this.className='btnBigOnFocus'" 
			onmouseout="this.className='btnBigOnBlur'" onclick="doUserSet();" nowrap>�û�����</td>
		</acc:showView>
				</tr>
			</table>
		</td>
	</tr>
</table>  -->
<table class="public_table1" id="userTable" style="width:100%;" id="contentT">
	<tr  id="title">
		<td class="search_head" width="10%" nowrap id="serial">���</td>
		<td class="search_head" width="20%" nowrap id="userid" >��¼��</td>
		<td class="search_head" width="20%" nowrap id="username">�û�����</td>
		<td class="search_head" width="20%" nowrap id="deptname">��������</td>
		<td class="search_head" width="20%" nowrap id="buss">ҵ������</td>
		<!-- <td class="search_head" width="20%" nowrap id="operate">����</td> -->
	</tr>
</table>
<table border=0 style="font-size:9pt;width:100%;" cellpadding=0 cellspacing=0 align="center">
			<tr>
				<td>
					<div style="float: none;"><input type="hidden" name="l" formPara="pageNo" id="pageNo" value="1"/></div>
					<div class="page_div" id="page" style="clear:none;margin-top:10px;float: right;"></div>
				</td>
			</tr>
		</table>
</body>
<script type="text/javascript">
doSearch();
function doSearch(){
	$("#userTable").find("tr").not(":first").remove();
	$("#page").empty();
	$("#title").after("<tr><td colspan='6' class='search_body_center'><table align='center' style='width:200px;color: #077f52;font-size: 16px;font-family: Microsoft YaHei, Hei, serif;'><tr><td><img src='${ctx}/global/images/loading1.gif' /></td><td>���ݼ����У����Ժ� . . .</td></tr></table></td></tr>");
	
	var groupid = '${param.groupid}';
	var authorityChoose = $("#authorityid").val();
	var bussMap = $(window.parent.document).find("#bussMap").val();      
	$.ajax({
		   cache :false,
		   type: "POST",
		   url:"${ctx}/system/groupManage/userByGroup.do",
		   data: {pageNo: $("#pageNo").val(), pageSize:"5",groupid:groupid,authorityid:authorityChoose},
		   async:false,
		   dataType:"json",
		   success: function(data){
			   $("#userTable").find("tr").not(":first").remove();
			   $("#page").empty();
			   var totalCount = data.totalCount;
			  if(data.result==""){
				$("#title").after("<tr style='background:#fff;border:1px solid #a1a29c;'><td class='search_body_center'  nowrap colspan='6' height='20' align='center' valign='middle'>���������û�</td></tr>");
			  }else{
				$.each(data.result, function(index, item){
					var num = data.pageSize*(data.currentPageNo-1) + parseInt(index+1);
					var row = $("#userTable").find("tr:first").clone();
					row.find("#serial").removeClass("search_head").addClass("search_body_center").text(num);
					row.find("#userid").removeClass("search_head").addClass("search_body_center").text(item.operatorid);
					row.find("#username").removeClass("search_head").addClass("search_body_center").text(item.operatorname);
					row.find("#deptname").removeClass("search_head").addClass("search_body_center").text(item.deptname);
					row.find("#buss").removeClass("search_head").addClass("search_body_center").text(getMapValue(item.businessarea, bussMap));
					
					var exeHtml = "<span style=' width:20px;'><a class='zc-lk1' href='javascript:showUserDetail(\"" + item.operatorid + "\");'>��ϸ</a>&nbsp;&nbsp;&nbsp;</span>";
					if("${param.deptid}" == "${geOperator.deptid}" && "${empty permission['ROLE_S_GROUP_M_US']}"){
						exeHtml +=  "<span style=' width:5px;'></span>"+
						  "<span style=' width:20px;'><a class='zc-lk1' href='javascript:deleteUserFromGroup(\"" + item.operatorid + "\",\"${param.groupid}\");'>ɾ��</a></span>";
					}			  
				  	row.find("#operate").removeClass("search_head").addClass("search_body_center").html(exeHtml);
					row.appendTo("#userTable");
				 });
				if(totalCount>3){
					pageChange(data.currentPageNo,data.pageSize,data.totalPageCount,data.totalCount, "page");
				}else{
					$("#page").append("<span style='float:right;padding-right:19px;'>��" + totalCount + "����ȫ����ʾ</span>");
				}
			  }
		   },
		   error:function(XMLHttpRequest, textStatus, errorThrown){
			   
		   }
		}); 
}

function deptAuthIdQuery(){
	window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_S_USER_M_S&type=2',
			'����' ,'top=100, left=500, width=400,height=500,toolbar=no');
}

function showUserDetail(idStr){
	window.open("<%=request.getContextPath()%>/system/userManage/queryGeOperatorForDetail.do?geOperator.operatorid=" + idStr,"�û���ϸ" ,"top=100, left=100, width=900,height=600,toolbar=no,scrollbars=yes");
}

function deleteUserFromGroup(operatorId,groupId){
	if(confirm("ȷ��Ҫ�Ӹ�����ɾ�����û���?")){
		$.ajax({
		   cache :false,
		   type: "POST",
		   url:"${ctx}/system/groupManage/deleteUserFromGroup.do",
		   data: {"operatorId":operatorId,"groupId":groupId},
		   dataType:"json",
		   success: function(data){
			  if(data == "success"){
				  doSearch();
			  }else{
				  alert(data);
			  }
		   },
		   error:function(XMLHttpRequest, textStatus, errorThrown){
			   alert(errorThrown);
		   }
		}); 
	}
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
function doUserSet(){
	window.top.location.href = "${ctx}/system/groupManage/queryGeGroupOperatorsForUpdate.do?id=${param.groupid}";
}
</script>

</html>
