<%@ page language="java" contentType="text/html;charset=GBK"%>
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
		<td class="search_head" width="80px" nowrap id="idNum">序号</td>
		<td class="search_head" width="160px" nowrap>功能类型</td>
		<td class="search_head" width="180px" nowrap>业务领域</td>
		<td class="search_head" width="180px" nowrap width="240px">文件类型</td>
		<td class="search_head" nowrap>操作</td>
	</tr>
	<c:forEach items="${geWorkFlowList}" var="workFlow" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}">
			<td class="search_body_center" width="80px">${status.index + 1}</td>
			<td class="search_body_center" width="160px">
				<c:choose>
					<c:when test="${searchType=='0'}">
						全部
					</c:when>
					<c:otherwise>
						${workFlowMap[workFlow.id.funcitontype]}
					</c:otherwise>
				</c:choose>
			</td>
			<td class="search_body_center" width="180px">
				<c:choose>
					<c:when test="${searchType=='0'}">
						全部
					</c:when>
					<c:otherwise>
						${businessAreaMap[workFlow.id.area]}
					</c:otherwise>
				</c:choose>
			</td>
			<td class="search_body_center" width="180px">
				<c:if test="${workFlow.id.filetype=='0'}">任务人员配置文件</c:if>
				<c:if test="${workFlow.id.filetype=='1'}">工作流流程配置xml文件</c:if>
			</td>
			<td class="search_body_center" ><a href="${ctx }/workFlow/download.do?geWorkflow.id.area=${workFlow.id.area}&geWorkflow.id.filetype=${workFlow.id.filetype}&geWorkflow.id.funcitontype=${workFlow.id.funcitontype}">下载</a></td>
		</tr>
	</c:forEach>
	
	<c:if test="${totalCount == 0}">
		<tr>
			<td colspan="8">
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
</script>
</html>