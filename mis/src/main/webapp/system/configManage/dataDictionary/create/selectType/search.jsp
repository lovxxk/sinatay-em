<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="<%=request.getContextPath()%>/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="30px;" nowrap>序号</td>
		<td class="search_head" nowrap>代码类型</td>
		<!--<td class="search_head" nowrap>类型标志</td>-->
		<td class="search_head" nowrap>简体描述</td>
		<td class="search_head" nowrap>英文描述</td>
		<td class="search_head" nowrap>繁体描述</td>
		<td class="search_head" nowrap>操作</td>
	</tr>
	<c:forEach items="${geCodeTypeList}" var="geCodeType" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" nowrap>${status.index+1+pg.pageSize*(pageNo-1)}</td>
			<td class="search_body" nowrap>
				${geCodeType.codeType}
				<input type="hidden" id="id_${status.index}" name="id_${status.index}" value="${geCodeType.codeType}">
				<input type="hidden" id="idName_${status.index}" name="idName_${status.index}" value="${geCodeType.codeTypeCDesc}">
			</td>
			<!--
			<td class="search_body_center" nowrap>
			<c:if test="${geCodeType.typeInd=='0'}">普通代码类型（允许进行基础代码设置）</c:if>
			<c:if test="${geCodeType.typeInd=='1'}">特殊代码类型（不允许进行基础代码设置）</c:if>
			</td>
			-->
			<td class="search_body" nowrap>${geCodeType.codeTypeCDesc}</td>
			<td class="search_body" nowrap>${geCodeType.codeTypeEDesc}</td>
			<td class="search_body" nowrap>${geCodeType.codeTypeTDesc}</td>
			<td class="search_body" nowrap>
				<a href="javascript:getGeCodeType('${status.index}')">关联到数据字典</a>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty geCodeTypeList || totalCount == 0}">
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
	function getGeCodeType(obj){
		top.opener.document.getElementById("codeType").value = document.getElementById("idName_"+obj).value;
		top.opener.document.getElementById("codeTypeBack").value = document.getElementById("id_"+obj).value;
		top.close();
	}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
