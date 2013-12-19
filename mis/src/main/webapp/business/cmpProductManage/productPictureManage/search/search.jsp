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
		<td class="search_head" width="38" nowrap id="idNum">序号</td>
		<td class="search_head" width="205" nowrap>名称</td>
		<td class="search_head" width="311" nowrap>描述</td>
		<td class="search_head" width="50" nowrap>是否启用</td>
		<td class="search_head"  nowrap>创建时间</td>
	</tr>
	<c:forEach items="${page.result}" var="geProductPictureDetail" begin="0" end="${page.totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38">${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body" style="width:205px;">
				<a href="javascript:doEdit('${status.index}')">${geProductPictureDetail.picturename}</a>
				<input type="hidden" id="id_${status.index}" value="${geProductPictureDetail.detailid}">
			</td>
			<td class="search_body" width="311px" style="white-space: normal;word-wrap:break-word;" >${geProductPictureDetail.picturedesc}</td>
			<td class="search_body_center" width="50">${geProductPictureDetail.flag eq "1" ? "是":"否"}</td>
			<td class="search_body_center" nowrap>
			<div style="width:80">
			${fn:substring(geProductPictureDetail.createTime,0,10)}
			</div>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty page || page.totalCount == 0}">
		<tr>
			<td colspan="8">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
function doEdit(obj){
	var idIndex = "id_"+obj;
	var idStr = document.getElementById(idIndex).value;
	window.open("${ctx }/business/cmpProductManage/productPictureManage/queryGeProductPictureForShow.do?geProductPictureDetail.detailid=" + idStr,"产品图片详细信息" ,"top=100, left=200, width=900,height=600,scrollbars, resizable=yes,toolbar=no");
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
