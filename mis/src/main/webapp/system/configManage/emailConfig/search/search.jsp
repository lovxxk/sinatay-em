<%@ page language="java" contentType="text/html;charset=GBK" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="<%=request.getContextPath()%>/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="38" nowrap id="idNum">序号</td>
		<td class="search_head"  width="80" nowrap>机构名称</td>
		<td class="search_head"  width="160" nowrap>适用功能</td>
		<td class="search_head"  width="260" nowrap>邮箱</td>
		<td class="search_head"  width="90" nowrap>手机</td>
		<td class="search_head"  width="120" nowrap>创建时间</td>
		<td class="search_head"  style="min-width:80px" nowrap><div style="width:90px">有效标志</div></td>
	</tr>
	<c:forEach items="${page.result}" var="tempGeDeptMail"  step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center"  width="38" >${status.index+1+pageSize*(pageNo-1)}</td>
			<td class="search_body_center"  width="80" >
               <a href="javascript:deptMailView('${ctx}/system/configManage/emailConfig/viewDeptMail.do?geDeptMail.deptMailID=${tempGeDeptMail.deptMailID}');" >${tempGeDeptMail.departNmae}</a>
			</td>
			<td class="search_body_center"  width="160" >
                ${tempGeDeptMail.sendMailName}
			</td>
			<td class="search_body_center"  width="260" >${tempGeDeptMail.mail}</td>
			<td class="search_body_center"  width="90" >${tempGeDeptMail.mobile}</td>
			<td class="search_body_center"  width="120" >
			     <fmt:formatDate value="${tempGeDeptMail.createTime}" pattern="yyyy-MM-dd"/>
			  </td>
			<td class="search_body_center" style="min-width:80px" nowrap>
			<div style="width:90px">
				<c:if test="${tempGeDeptMail.validInd=='1'}">有效</c:if>
				<c:if test="${tempGeDeptMail.validInd=='0'}">无效</c:if>
			</div>
			</td>
		</tr>
	</c:forEach>
    <c:if test="${empty page || page.totalCount == 0}">
      <tr>
         <td colspan="6">
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
	function deptMailView(url){
		window.open(url, "机构邮箱详细", "top=100, left=100, width=900, height=600, scrollbars, resizable=yes");
	}
</script>
</html>
