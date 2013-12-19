<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" nowrap id="idNum">序号</td>
		<td class="search_head" nowrap>险种代码</td>
		<td class="search_head" nowrap>险种名称</td>
		<td class="search_head" nowrap>业务领域</td>
		<td class="search_head" nowrap>险种状态</td>
		<td class="search_head" nowrap>操作员编号</td>
		<td class="search_head" nowrap>创建时间</td>
		<td class="search_head" nowrap>关联产品信息</td>
	</tr>
	 
	<c:forEach items="${page.result}" var="geRisk" begin="0" end="${page.totalCount}" step="1" varStatus="stu">
		<tr id="tr_${stu.index}" class="${stu.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" nowrap>${stu.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" nowrap>${geRisk.riskCode}</td>
			<td class="search_body_center" id = "td${stu.index}" nowrap>${geRisk.riskCName}</td>
			<td class="search_body_center" nowrap>
				<c:forEach items="${geCodeBusinessAreaList }" var="businessArea">
					<c:if test="${geRisk.businessArea==businessArea.id.codeCode}">${businessArea.codeCName }</c:if>
				</c:forEach>
			</td>
			<td class="search_body_center" nowrap>
				<c:if test="${geRisk.validInd=='1'}">有效</c:if>
			</td>
			<td class="search_body_center" nowrap>${geRisk.operatorID}</td>
			<td class="search_body_center" nowrap>
				<fmt:formatDate value="${geRisk.createDate}"/>
			</td>
			<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="goSetthirdParterName(td${stu.index});" nowrap >关     联</td>	
		</tr>
	</c:forEach>
</table>
</body>
<script type="text/javascript">
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
	function goSetthirdParterName(obj){
		if(confirm("确定关联该供应商?")){
	   	top.opener.document.getElementsByName("coreProductName")[1].value=$(obj).html();
	    top.close();
		}
	}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
