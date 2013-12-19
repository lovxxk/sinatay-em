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
		<!--<td class="td_head" width="30" nowrap><input type="checkbox" onclick="checkAll(this)"></td>
		-->
		<td class="search_head" nowrap id="idNum">序号</td>
		<td class="search_head" nowrap>产品名称</td>
		<td class="search_head" nowrap>结算日期</td>
		<td class="search_head" nowrap>结算日利率</td>
		<td class="search_head" nowrap>折合年结算利率</td>
	</tr>
	  <c:if test="${empty page || page.totalCount == 0}">
		<tr>
			<td colspan="7">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="universal" status="status">	
		<tr id="tr_${status.index}">
			<td class="search_body_center" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" nowrap>
			<a href='javascript:doView("${ctx}/universal/viewUniversal.do?universal.serialNo=<s:property value="#universal.serialNo"/>");'>
			<s:property value="#universal.riskName"/>
			</a>
			</td>
			<td class="search_body_center" nowrap><s:property value="#universal.culDate"/></td>
			<td class="search_body_center" nowrap><s:property value="#universal.dateRate"/></td>
			<td class="search_body_center" nowrap><s:property value="#universal.yearRate"/></td>
			<!-- 
			<td class="td_body_center" nowrap>
				<s:if test="#geRisk.insuAccFlag==1">
					有账户
				</s:if>
				<s:if test="#geRisk.insuAccFlag==0">
					无账户
				</s:if>
			</td>
		  
			 -->			
		</tr>
	</s:iterator>
	</s:if>
	</table>
</body>
<script type="text/javascript">
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
function showReason(obj){
	alert("加入黑名单原因:"+obj);
}
// 查看险种
function doView(url){
    window.open(url,"查看利率" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

