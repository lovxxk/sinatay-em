<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/lib/jquery-1.5.min.js"></script>
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" nowrap width="40" id="idNum">序号</td>
		<td class="search_head" nowrap width="130"><a class="search_head_sort" href="javascript:doSearch('sortId');">用户组编号</a></td>
		<td class="search_head" nowrap width="130"><a class="search_head_sort" href="javascript:doSearch('sortName');">用户组名称</a></td>
		<td class="search_head" nowrap width="130">用户组类型</td>
		<td class="search_head" nowrap width="160">创建机构</td>
		<td class="search_head" nowrap width="80">创建时间</td>
	</tr>
	<c:forEach items="${geGroupList}" var="geGroup" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="40">${pageSize*(pageNo-1)+status.index+1}</td>
			<td class="search_body" width="130"><a class="zc-lk1" href="javascript:showGroupDetail('${geGroup.groupid}');">${geGroup.groupid}</a></td>
			<td class="search_body" width="130">${geGroup.groupname}</td>
			<td class="search_body" width="130">${geGroup.grouptypename}</td>
			<td class="search_body" width="160">${departmentMap[geGroup.deptid]}</td>
			<td class="search_body_center" width="80" nowrap>
				<fmt:formatDate value='${geGroup.createtime}' type="date" pattern="yyyy-MM-dd" />  
			</td>
		</tr>
	</c:forEach>
	<c:if test="${totalCount == 0}">
		<tr>
			<td colspan="7">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
//重新加载page.jsp页面
window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
										+"${pageSize}&totalPage=${totalPage}&totalCount=${totalCount}" ;
function showGroupDetail(idStr){
	window.open("${ctx}/system/groupManage/queryGeGroupDetail.do?id=" + idStr,"查看用户组" ,"top=20, left=0, width=1000,height=650,toolbar=no,scrollbars=yes");	
}

if(window.parent.fraSearchForm.document.getElementById("sortId").value=='asc'){
	$("#data_table tr:eq(0) td:eq(1) a").html($("#data_table tr:eq(0) td:eq(1) a").html()+"<img  style='vertical-align:middle;border:0px' src='${ctx}/global/images/asc.png'></img>");
}else if(window.parent.fraSearchForm.document.getElementById("sortId").value=='desc'){
	$("#data_table tr:eq(0) td:eq(1) a").html($("#data_table tr:eq(0) td:eq(1) a").html()+"<img style='vertical-align:middle;border:0px' src='${ctx}/global/images/desc.png' ></img>");
}else{
	$("#data_table tr:eq(0) td:eq(1) a").html($("#data_table tr:eq(0) td:eq(1) a").html()+"<img style='vertical-align:middle;border:0px' src='${ctx}/global/images/ascdesc.png' ></img>");
}
if(window.parent.fraSearchForm.document.getElementById("sortName").value=='asc'){
	$("#data_table tr:eq(0) td:eq(2) a").html($("#data_table tr:eq(0) td:eq(2) a").html()+"<img  style='vertical-align:middle;border:0px' src='${ctx}/global/images/asc.png'></img>");
}else if(window.parent.fraSearchForm.document.getElementById("sortName").value=='desc'){
	$("#data_table tr:eq(0) td:eq(2) a").html($("#data_table tr:eq(0) td:eq(2) a").html()+"<img style='vertical-align:middle;border:0px' src='${ctx}/global/images/desc.png' ></img>");
}else{
	$("#data_table tr:eq(0) td:eq(2) a").html($("#data_table tr:eq(0) td:eq(2) a").html()+"<img style='vertical-align:middle;border:0px' src='${ctx}/global/images/ascdesc.png' ></img>");
}

function doSearch(sortName){
	//window.parent.fraToolbar.document.getElementById("idStr").value = "";
	//window.parent.fraToolbar.document.getElementById("count").value = "";
	window.parent.fraSearchForm.document.getElementById("pageNo").value=1;
	if(sortName=="sortId")
		window.parent.fraSearchForm.document.getElementById("sortName").value="";
	else
		window.parent.fraSearchForm.document.getElementById("sortId").value="";
		if(window.parent.fraSearchForm.document.getElementById(sortName).value==''){
			window.parent.fraSearchForm.document.getElementById(sortName).value='asc';
		}
		else if(window.parent.fraSearchForm.document.getElementById(sortName).value=='asc'){
			window.parent.fraSearchForm.document.getElementById(sortName).value='desc';
		}else{
		window.parent.fraSearchForm.document.getElementById(sortName).value='';
	}
	window.parent.fraSearchForm.document.getElementById("frmInput").submit();
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
