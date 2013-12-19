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
		<td class="search_head" nowrap width="38px" id="idNum">序号</td>
		<td class="search_head" width="100px" nowrap>险种代码</td>
		<td class="search_head" width="100px" nowrap>险别代码</td>
		<td class="search_head" width="300px" nowrap>险别中文名称</td>
		<td class="search_head"  nowrap>险别标志</td>
	</tr>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="geKind" status="status">	
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38px" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" width="100px" nowrap><s:property value="#geKind.id.riskCode"/></td>
			<td class="search_body_center" width="100px" nowrap>
			<a href='javascript:kindView("${ctx }/business/cmpProductManage/riskAndKindManage/viewKind.do?geKind.id.riskCode=<s:property value="#geKind.id.riskCode"/>&geKind.id.kindCode=<s:property value="#geKind.id.kindCode"/>&geKind.geKindRelates.id.relateKindCode=<s:property value="#geKind.geKindRelates.id.relateKindCode"/>");'>	<s:property value="#geKind.id.kindCode"/></a>
			</td>
			<td class="search_body_center" width="300px" nowrap>
				<s:property value="#geKind.kindCName"/>
			</td>
			<td class="search_body_center" style="min-width:60px;" nowrap>
					<s:if test="#geKind.kindflag==01">
						主险
					</s:if>
					<s:if test="#geKind.kindflag==02">
						附加险
					</s:if>
			</td>
		</tr>
	</s:iterator>
	</s:if>
	  <c:if test="${empty page || page.totalCount == 0}">
		<tr>
			<td colspan="7">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
function showReason(obj){
	alert("加入黑名单原因:"+obj);
}
function kindView(url){
	window.open(url, "", "top=100, left=100, width=900, height=600, scrollbars=yes, resizable=yes");
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
