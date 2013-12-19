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
		<td class="search_head" width="38px" nowrap id="idNum">序号</td>
		<td class="search_head" width="300px" nowrap>短信名称</td>
		<td class="search_head" width="200" nowrap>适用功能</td>
		<td class="search_head" style="min-width:80px" nowrap><div style="width:90px">有效标志</div></td>
	</tr>
	<c:forEach items="${page.result}" var="tempGeSmsConfig"  step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38px" nowrap>${status.index+1+pageSize*(pageNo-1)}</td>
			<td class="search_body_center" width="200px" nowrap><a href="javascript:smsConfigView('${ctx}/system/configManage/SMSConfig/viewSms.do?geSmsConfig.smsId=${tempGeSmsConfig.smsId}');" >${tempGeSmsConfig.smsName}</a></td>
			<td class="search_body_center" width="150" nowrap>
				${tempGeSmsConfig.sendSmsName}
			<td class="search_body_center" style="min-width:80px" nowrap>
			 <div style="width:90px">
				<c:if test="${tempGeSmsConfig.validInd=='1'}">有效</c:if>
				<c:if test="${tempGeSmsConfig.validInd=='0'}">无效</c:if>
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
   function smsConfigView(url){
	   window.open(url, "短信模板配置", "top=100, left=100, width=900, height=600, scrollbars, resizable=yes");
   }
</script>
</html>
