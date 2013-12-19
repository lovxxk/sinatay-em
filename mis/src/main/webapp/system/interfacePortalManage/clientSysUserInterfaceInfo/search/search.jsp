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
			<td class="search_head" nowrap>交易代码</td>
			<td class="search_head" nowrap>交易名称</td>
			<td class="search_head" nowrap>客户端用户主键 </td>
		</tr>
		<c:if test="${page.totalCount == 0}">
		<tr>
			<td colspan="8">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
		</c:if>
		<s:if test="page.result!=null">
		<s:iterator value="page.result" var="interfaceInfo" status="st">
			<tr id="tr_${st.index}">
				<td class="search_body_center" nowrap>${st.index+1+page.pageSize*(page.currentPageNo-1)}</td>
				<td class="search_body_center" nowrap>
					<a href="javascript:viewInterfaceInfoDetail('<s:property value="#interfaceInfo.transCode"/>');">
						<s:property value="#interfaceInfo.transCode" />
					</a>
				</td>
				<td class="search_body_center" nowrap><s:property value="#interfaceInfo.transName" />
				</td>
				<td class="search_body" nowrap><s:property value="#interfaceInfo.clientUser.id" />
				</td>
			</tr>			
		</s:iterator>
		</s:if>
	</table>
</body>
<script type="text/javascript">
//详细
function detail(id,taskid,workflowid){
	window.open("${ctx}/productManage/toDetail.do?id=" + id+"&taskID="+taskid+"&workFlowID="+workflowid+"&type=${type}","产品信息详细","top=100, left=100,width=1000,,height=700,toolbar=no,scrollbars=yes");
}
function viewInterfaceInfoDetail(transCode){
	window.open("${ctx}/interfacePortal/viewInterfaceInfoDetailNew.do?interfaceInfo.transCode=" + transCode ,"接口信息详细","top=100, left=100,width=1000,,height=700,toolbar=no,scrollbars=yes");
}

window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
<script type="text/javascript">
</script>
</html>