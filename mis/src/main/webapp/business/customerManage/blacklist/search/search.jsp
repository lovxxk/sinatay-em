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
		<td class="search_head" width="38" nowrap  id="idNum">序号</td>
		<td class="search_head" width="60"  nowrap>姓名</td>
		<td class="search_head"  width="30" nowrap>性别</td>
		<td class="search_head"  width="64" nowrap>出生日期</td>
		<td class="search_head"  width="110" nowrap>证件类型</td>
		<td class="search_head"  width="140" nowrap>证件号码</td>
		<td class="search_head"  width="80" nowrap>业务领域</td>
		<td class="search_head" nowrap>加入黑名单原因</td>
	</tr> 
	<c:forEach items="${page.result}" var="geBlackList" begin="0" end="${page.totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center"  width="38" >${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body"  width="60" >
				<a href="javascript:doEdit('${status.index}')">${geBlackList.userName}</a>
				<input type="hidden" id="id_${status.index}" value="${geBlackList.id}">
			</td>
			<td class="search_body_center"  width="30" >
				<c:forEach items="${sexList}" var="GeCode_sex" step="1" varStatus="status">
					<c:if test="${GeCode_sex.id.codeCode == geBlackList.sex}">
						${GeCode_sex.codeCName}
					</c:if>
				</c:forEach>
			</td>
			<td class="search_body_center"  width="64" >${geBlackList.birthDay}</td>
			<td class="search_body"  width="110" >
				<c:forEach items="${idTypeList}" var="GeCode_idType" step="1" varStatus="status">
					<c:if test="${GeCode_idType.id.codeCode == geBlackList.identifyType}">
						${GeCode_idType.codeCName}
					</c:if>
				</c:forEach>
			</td>
			<td class="search_body"  width="140" >${geBlackList.identifyNumber}</td>
			<td class="search_body_center"  width="80">
				<c:forEach items="${bussList}" var="bussArea" step="1" varStatus="status">
					<c:if test="${geBlackList.businessArea==bussArea.id.codeCode}">
						${bussArea.codeCName}
					</c:if>
				</c:forEach>
			</td>
			<td class="search_body" style="white-space: normal;word-wrap:break-word; " >
						${geBlackList.reason }
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
	window.open("${ctx }/business/customerManage/blacklist/queryGeBlackListForShow.do?geBlackList.id=" + idStr,"黑名单详细信息" ,"top=100, left=100, width=900,height=400,toolbar=no");
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
