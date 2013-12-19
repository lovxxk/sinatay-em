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
	<tr><!--
		<td class="search_head" width="30" nowrap><input type="checkbox" onclick="checkAll(this)"></td>
		--><td class="search_head" nowrap>序号</td>
		<td class="search_head" nowrap>险种代码</td>
		<td class="search_head" nowrap>险别主险代码</td>
		<td class="search_head" nowrap>险别附加险代码</td>
		<td class="search_head" nowrap>业务领域</td>
		<td class="search_head" nowrap>标识</td>
	</tr>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="kind" status="status" step="1">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
		    <td class="search_body" nowrap>${status.index+1+pg.pageSize*(pageNo-1)}</td>
			<td class="search_body" nowrap>
			<a href="javascript:void(0)" onclick="viewKindRelate('<s:property value="#kind.id.riskCode"/>','<s:property value="#kind.id.kindCode"/>','<s:property value="#kind.id.relateKindCode"/>')"> <s:property value="#kind.id.riskCode"/></a>
			</td>
			<td class="search_body" nowrap><s:property value="#kind.id.kindCode"/></td>
			<td class="search_body" nowrap><s:property value="#kind.id.relateKindCode"/></td>
			<td class="search_body" nowrap>
			  <s:iterator value="list" var="businessArea">
			     <s:if test="#businessArea.id.codeCode==#kind.businessArea">
			          <s:property value="#businessArea.codeCName"/>
			     </s:if>
			  </s:iterator>
			</td>
			<td class="search_body" nowrap><s:property value="#kind.flag"/></td>
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
	//重新加载page.jsp页面
	window.parent.frames[3].document.location.href = "/mis/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
											+"${pageSize}&totalPage=${totalPage}&totalCount=${totalCount}" ;
	function viewKindRelate(riskCode,kindCode,kindRelateCode){
		var url = contextRootPath + '/business/cmpProductManage/riskAndKindManage/viewKindRelate.do?geKindRelate.id.riskCode='+riskCode+'&geKindRelate.id.kindCode='+kindCode+'&geKindRelate.id.relateKindCode='+kindRelateCode+'&view=view';
		window.open(url,"", "top=100, left=100, width=900, height=600, scrollbars=yes, resizable=yes");
	}																															
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
