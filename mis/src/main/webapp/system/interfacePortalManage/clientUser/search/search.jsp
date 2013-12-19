<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
		<tr>
			<td class="search_head" nowrap id="idNum">序号</td>
			<td class="search_head" nowrap>外部系统名称</td>
			<td class="search_head" nowrap>账号</td>
			<td class="search_head" nowrap>密码</td>
			<td class="search_head" nowrap>账户状态</td>
			<td class="search_head" nowrap>创建时间</td>
			<td class="search_head" nowrap>更新时间</td>
			<td class="search_head" nowrap>备注</td>
		</tr>
		<c:if test="${page.totalCount == 0}">
		<tr>
			<td colspan="8">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
		</c:if>
		<s:if test="page.result!=null">
		<s:iterator value="page.result" var="clientUser" status="st">
			<tr id="tr_${st.index}">
				<td class="search_body_center" nowrap>${st.index+1+page.pageSize*(page.currentPageNo-1)}</td>
				<td class="search_body_center" nowrap><s:property value="#clientUser.externalSysInfo.externalSysName" />
				</td>
				<td class="search_body" nowrap>
					<a href="javascript:viewClientUserDetail('<s:property value="#clientUser.id"/>','<s:property value="#clientUser.externalSysInfo.externalSysId"/>');">
						<s:property value="#clientUser.loginName" />
					</a>
				</td>
				<td class="search_body_center" nowrap><s:property value="#clientUser.password" />
				<td class="search_body_center" nowrap>
					<s:if test="#clientUser.status == 0 ">启用</s:if>
					<s:if test="#clientUser.status == 1 ">停用</s:if>
				</td>
				<td class="search_body" nowrap><s:property value="#clientUser.createDate" /></td>
				<td class="search_body" nowrap><s:property value="#clientUser.updateDate" /></td>
			    <td class="search_body" nowrap><s:property value="#clientUser.remark" /></td>
			</tr>			
		</s:iterator>
		</s:if>
	</table>
</body>
<script type="text/javascript">
//详细
function detail(id,taskid,workflowid){
	window.open("${ctx}/productManage/toDetail.do?id=" + id+"&taskID="+taskid+"&workFlowID="+workflowid+"&type=${type}","产品信息详细","top=100, left=100,width=1000,,height=550,toolbar=no,scrollbars=yes");
}
function viewClientUserDetail(id){
	window.open("${ctx}/interfacePortal/viewClientUserDetail.do?clientUser.id=" + id,"客户端用户信息详细","top=100, left=100,width=1000,,height=550,toolbar=no,scrollbars=yes");
}

window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
<script type="text/javascript">
</script>
</html>