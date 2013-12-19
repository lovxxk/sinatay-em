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
			<td class="search_head" width="40px" nowrap id="idNum">序号</td>
			<td class="search_head" width="100px" nowrap>客户号</td>
			<td class="search_head" width="110px" nowrap>登录账号</td>
			<td class="search_head" width="90px" nowrap>姓名</td>
			<td class="search_head" width="30px" nowrap>性别</td>
			<td class="search_head" width="90px" nowrap>证件类型</td>
			<td class="search_head" width="140px" nowrap>证件号码</td>
			<td class="search_head" nowrap>注册时间</td>
		</tr>
		<c:forEach items="${page.result}" var="geUserPersonal" begin="0" end="${page.totalCount}" step="1" varStatus="status">
			<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
				<td class="search_body_center"  width="40px" >${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
				<td class="search_body_center"  width="100px" ><a href="javascript:doShow('${geUserPersonal.userID }');">${geUserPersonal.userID}</a></td>
				<td class="search_body" width="110px" >${geUserPersonal.userAccount}</td>
				<td class="search_body"  width="90px" >${geUserPersonal.userName }</td>
				<td class="search_body_center"  width="30px" >${geUserPersonal.sex=="1"?"男":(geUserPersonal.sex=="2"?"女":"")}</td>
				<td class="search_body" width="90px"  >
					<c:forEach items="${identifyList }" var="ind">
						<c:if test="${geUserPersonal.identifyType==ind.id.codeCode}">
							${ind.codeCName }
						</c:if>
					</c:forEach>
				</td>
				<td class="search_body"  width="140px" >${geUserPersonal.identifyNumber}</td>
				<td class="search_body_center" nowrap>
				<div style="width:80">
				${geUserPersonal.makeDate }
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

function doShow(id){
	window.open("${ctx }/business/customerManage/personalUser/queryGeUserPersonalForUpdateOrShow.do?handle=show&buttonFlag=0&geUserPersonal.userID=" + id,"客户详细信息" ,"top=100, left=100, width=900,height=450,toolbar=no,scrollbars=yes");
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
