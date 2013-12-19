<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="38px" nowrap id="idNum">序号</td>
		<td class="search_head" width="200px" nowrap>属性编号</td>
		<td class="search_head" width="300px" nowrap>属性名称</td>
		<td class="search_head" style="min-width:150px" nowrap><div style="width:160px">属性描述</div></td>
	</tr>
	<c:forEach items="${geDeptInfoList}" var="geDeptInfo" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38px" nowrap>${pageSize*(pageNo-1)+status.index+1}</td>
			<td class="search_body" width="200px" ><a class="zc-lk1" href="javascript:showGeDeptInfoDetail('${geDeptInfo.attrID}');">${geDeptInfo.attrID}</a></td>
			<td class="search_body" width="300px" >${geDeptInfo.attrName}</td>
			<td class="search_body" style="min-width:150px" ><div style="width:160px">${geDeptInfo.attrDescription}</div></td>
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
	function showGeDeptInfoDetail(idStr){
		window.open("${ctx}/system/deptInfoManage/detail.do?geDeptInfo.attrID=" + idStr,"查看机构属性" ,"top=100, left=100, width=900,height=600,toolbar=no");
	}				
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
