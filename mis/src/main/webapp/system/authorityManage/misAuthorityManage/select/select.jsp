<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/page2.js"></script>
<title>Ȩ����ϸ��Ϣ</title>
</head>
<body leftmargin="0px" topmargin="0px">
<div style="padding:10px 0px 0px 5px;">
	<table align="center" style="font-size:9pt;width:95%;" cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td class="td_head" width="65px;" nowrap>Ȩ�ޱ�ţ�</td>
			<td class="td_body">${geAuthority.authorityID}</td>
			<td class="td_head" width="65px;" nowrap>Ȩ�����ƣ�</td>
			<td  class="td_body">${geAuthority.authorityName}</td>
		</tr>
		<tr>
			<td class="td_head" width="65px;" nowrap>�˵����ͣ�</td>
			<td  class="td_body">${geAuthority.isMenu=='1'?"�˵�":(geAuthority.isMenu=='2'?"�ǲ˵�":"")}</td>
			<td class="td_head" width="65px;" nowrap>Ȩ����ţ�</td>
			<td  class="td_body">${geAuthority.authorityorder}</td>
		</tr>
		<tr style="display:none;">
			<td class="td_head" width="65px;" nowrap>Ȩ�޲㼶��</td>
			<td  class="td_body" colspan="3">${geAuthority.authorityLevel}</td>
		</tr>
		<tr>
			<td class="td_head" width="65px;" nowrap>����ϵͳ��</td>
			<td  class="td_body">${geCode.codeCName}</td>
			<td class="td_head" width="65px;" nowrap>Ȩ��������</td>
			<td  class="td_body">${geAuthority.authorityDesp}</td>
		</tr>
		<tr>
			<c:if test="${geAuthority.authorityType=='02'}">
				<td class="td_head" width="65px;" nowrap>�������ͣ�</td>
				<td  class="td_body">����</td>
				<td class="td_head" width="65px;" nowrap>�򿪷�ʽ��</td>
				<td  class="td_body">
					${geAuthority.opentype=="01" ? "����":(geAuthority.opentype=="02" ? "�ǵ���":(""))}
				</td>
			</c:if>
			<c:if test="${geAuthority.authorityType=='01'}">
				<td class="td_head" width="65px;" nowrap>�������ͣ�</td>
				<td  class="td_body" colspan="3">������</td>
			</c:if>
		</tr>
		<tr style="display:${geAuthority.authorityType=='02'?'':'none'}">
			<td class="td_head" width="65px;" nowrap>���ӵ�ַ��</td>
			<td  class="td_body" colspan="3">${geAuthority.authorityLink}</td>
		</tr>
	</table>
	<div class="public_list_title" style="width:95%;color:#4aa78c;">ӵ�и�Ȩ�޵Ľ�ɫ���£�</div>
	<table align="left" cellpadding=0 cellspacing=0 border=0 style="width:100%;padding-left: 0px;">
	<tr style="padding-left: 0px;">
		<td class="td_head" width="80px" nowrap>����������</td>
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
				</tr>
			</table>
		</td>
	</tr>
</table>
	<div id="roleDiv" style="clear:both;">
		<table border=0 style="font-size:9pt;width:95%;" cellpadding=0 cellspacing=0 align="center"  id="roleTable">
			<tr id="title">
				<td class="search_head" width="10%" nowrap id="serial">���</td>
				<td class="search_head" width="20%" nowrap id="roleId">��ɫ���</td>
				<td class="search_head" width="20%" nowrap id="roleName">��ɫ����</td>
				<td class="search_head" width="20%" nowrap id="roleDeptName">��������</td>
				<td class="search_head" width="20%" nowrap id="operate">����</td>
			</tr>
		</table>
		<table border=0 style="font-size:9pt;width:95%;" cellpadding=0 cellspacing=0 align="center">
			<tr>
				<td>
					<div style="float: none;"><input type="hidden" name="l" formPara="pageNo" id="pageNo" value="1"/></div>
					<div class="page_div" id="page" style="clear:none;margin-top:10px;float: right;"></div>
				</td>
			</tr>
		</table>
	</div>
</div>
<div style="height:10px;">&nbsp;</div>
<form id="frmInput" name="frmInput" method="post" target="#">
</form>
</body>
<script type="text/javascript">

function showRoleDetail(idStr){
	window.open("<%=request.getContextPath() %>/system/roleManage/detail.do?geRole.roleID=" + idStr,"��ɫ��ϸ" ,"top=50, left=80, width=900,height=600,toolbar=no,scrollbars=yes");
}

function deptAuthIdQuery(){
	window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_S_ROLE_M&type=2',
			'����' ,'top=100, left=500, width=400,height=500,toolbar=no');
}
doSearch();
function doSearch(){
	$("#roleTable").find("tr").not(":first").remove();
	$("#page").empty();
	$("#title").after("<tr><td colspan='5' class='search_body_center'><table align='center' style='width:200px;color: #077f52;font-size: 16px;font-family: Microsoft YaHei, Hei, serif;'><tr><td><img src='${ctx}/global/images/loading1.gif' /></td><td>���ݼ����У����Ժ� . . .</td></tr></table></td></tr>");
	 
	var authorityId = '${geAuthority.authorityID}';
	var authorityChoose = $("#authorityid").val();
	$.ajax({
		   cache :false,
		   type: "POST",
		   url:"${ctx}/authorityManage/findRoleByAuth.do",
		   data: {pageNo: $("#pageNo").val(), pageSize:"8",authority:authorityId,authorityid:authorityChoose},
		   dataType:"json",
		   success: function(data){
			   $("#roleTable").find("tr").not(":first").remove();
			   $("#page").empty();
			   var totalCount = data.totalCount;
			  if(data.result==""){
				$("#title").after("<tr style='background:#fff;border:1px solid #a1a29c;'><td class='search_body_center'  nowrap colspan='5' height='20' align='center' valign='middle'>���޽�ɫӵ�и�Ȩ��</td></tr>");
			  }else{
				$.each(data.result, function(index, item){
					var num = data.pageSize*(data.currentPageNo-1) + parseInt(index+1);
					var row = $("#roleTable").find("tr:first").clone();
					row.find("#serial").removeClass("search_head").addClass("search_body_center").text(num);
					row.find("#roleId").removeClass("search_head").addClass("search_body_center").text(item.roleID);
					row.find("#roleName").removeClass("search_head").addClass("search_body_center").text(item.roleName);
					row.find("#roleDeptName").removeClass("search_head").addClass("search_body_center").text(item.deptName);
					row.find("#operate").removeClass("search_head").addClass("search_body_center").html("<span style=' width:30px;'><a class='zc-lk1' href='javascript:showRoleDetail(\"" + item.roleID + "\");'>��ϸ</a></span>");
					row.appendTo("#roleTable");
				 });
				if(totalCount>8){
					pageChange(data.currentPageNo,data.pageSize,data.totalPageCount,data.totalCount,"page");
				}else{
					$("#page").append("<span style='float:right;padding-right:9px;'>��" + totalCount + "����ȫ����ʾ</span>");
				}
			  }
		   },
		   error:function(XMLHttpRequest, textStatus, errorThrown){
			   
		   }
		}); 
	var ids=document.getElementById("authorityDepartmentManager").value;
}

function doClear(){
	document.getElementById("authorityDepartmentManager").value = "--ȫ��--";
	document.getElementById("authorityid").value = "";
	document.getElementById("frmInput").reset();
}
</script>
</html>