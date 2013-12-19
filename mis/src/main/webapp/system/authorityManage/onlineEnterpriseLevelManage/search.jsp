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
		<td class="search_head" nowrap>用户等级</td>
		<td class="search_head" nowrap>等级描述</td>
		<td class="search_head" nowrap>操作</td>
	</tr>
	<c:forEach items="${levelList}" var="level" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" nowrap>${pageSize*(pageNo-1)+status.index+1}</td>
			<td class="search_body_center" nowrap>${level.id.codeCode}</td>
			<td class="search_body_center" nowrap>${level.codeCName}</td>
			<td class="search_body_center" nowrap><a class="zc-lk1" href="javascript:doFrontPer('${level.id.codeCode}');">设置权限</a></td>
		</tr>
	</c:forEach>
	<c:if test="${empty levelList}">
		<tr>
			<td colspan="4">
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
	function doFrontPer(idStr){
		window.open("${ctx}/system/authorityManage/onlineEnterpriseLevelManage/setAuthority/index.jsp?userLevel=" + idStr,"前台权限设置" ,"top=100, left=500, width=400,height=500,toolbar=no");		
	}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>