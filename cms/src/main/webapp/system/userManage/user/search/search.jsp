<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" nowrap width="38" id="idNum">序号</td>
		<td class="search_head" nowrap width="117"><a class="search_head_sort" href="javascript:doSearch('sortId');">登录账户</a></td>
		<td class="search_head" nowrap width="90"><a class="search_head_sort" href="javascript:doSearch('sortName');">用户姓名</a></td>
		<td class="search_head" nowrap width="30">性别</td>
		<td class="search_head" nowrap width="70">联系电话</td>
		<td class="search_head" nowrap width="150">电子邮箱</td>
		<td class="search_head" nowrap width="70">用户状态</td>
		<td class="search_head" nowrap width="70">所属机构</td>
		<td class="search_head" nowrap width="70">业务领域</td>
		<td class="search_head" nowrap>创建时间</td>
	</tr>
	<c:forEach items="${geOperatorList}" var="misUser" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38" >${pageSize*(pageNo-1)+status.index+1}</td>
			<td class="search_body" width="117"><a class="zc-lk1" href="javascript:showUserDetail('${misUser.operatorid}');">${misUser.operatorid}</a></td>
			<td class="search_body" width="90">${misUser.operatorname}</td>
			<td class="search_body_center" width="30">
			<c:if test="${misUser.sex eq '1' }">男</c:if>
			<c:if test="${misUser.sex eq '2' }">女</c:if>
			</td>
			<td class="search_body" width="70">${misUser.phone}</td>
			<td class="search_body" width="150">${misUser.email}</td>
			<td class="search_body_center" nowrap>
			<c:if test="${misUser.state eq '01'}">可用</c:if>
			<c:if test="${misUser.state eq '02'}">不可用</c:if>
			</td>
			<td class="search_body" width="70">${departmentMap[misUser.deptid]}</td>
			<td class="search_body_center" width="70">${areaMap[misUser.businessarea]}</td>
			<td class="search_body_center" nowrap>
			<div style="width:80">
			${fn:substring(misUser.createtime,0,10)}
			</div></td>
		</tr>
	</c:forEach>
	<c:if test="${totalCount == 0}">
		<tr>
			<td colspan="10">
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
		
function showUserDetail(idStr){
	window.open("${ctx}/system/userManage/queryGeOperatorForDetail.do?geOperator.operatorid=" + idStr,"查看用户" ,"top=100, left=100, width=900,height=600,toolbar=no");
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
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
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
