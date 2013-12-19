<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/global/js/lib/jquery-1.5.min.js"></script>
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="38" nowrap id="idNum">序号</td>
		<td class="search_head"  nowrap>用户组类型名称</td>
		<td class="search_head" width="500" nowrap>用户组类型描述</td>
	</tr>
	<c:forEach items="${geGroupTypeList}" var="geGrouptype" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38">${pageSize*(pageNo-1)+status.index+1}</td>
			<td class="search_body" ><a class="zc-lk1" href="javascript:showDetail('${geGrouptype.grouptypeid}');">${geGrouptype.grouptypename}</a></td>
			<td class="search_body" width="500">
			${geGrouptype.grouptypedesc}
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty geGroupTypeList}">
		<tr>
			<td colspan="3">
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
function showDetail(idStr){
	window.open("${ctx}/system/groupManage/queryTypeDetail.do?id=" + idStr,"查看用户组类型详细" ,"top=50, left=0, width=800,height=550,toolbar=no,scrollbars=yes");
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
