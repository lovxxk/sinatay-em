<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="<%=request.getContextPath()%>/global/css/mis_basic.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="td_head" width="30" nowrap><input type="checkbox" onclick="checkAll(this)"></td>
		<td class="td_head" nowrap>序号</td>
		<td class="td_head" nowrap>公司名称</td>
		<td class="td_head" nowrap>公司电话</td>
		<td class="td_head" nowrap>公司地址</td>
		<td class="td_head" nowrap>电子邮箱</td>
		<td class="td_head" nowrap>公司网址</td>
		<input type="hidden" value="<s:property value="nameCount"/>" name="nameCount"/>
	</tr>
	
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="GeThirdParterInfo" status="status" step="1">
		<tr id="tr_${status.index}">
			<td class="td_body_center" nowrap>
				<input type="checkbox" name="checkChild" id="check${status.index}" onclick="checkSingleRow();" value="<s:property value="#GeThirdParterInfo.thirdParterID"/>">
			</td>
			<td class="td_body_center" nowrap>${status.index+1+pg.pageSize*(pageNo-1)}</td>
			<td class="td_body_center" nowrap><s:property value="#GeThirdParterInfo.thirdParterName"/></td>
			<td class="td_body_center" nowrap>
				<s:property value="#GeThirdParterInfo.companyPhone"/>
			</td>
			<td class="td_body_center" nowrap>
				<s:property value="#GeThirdParterInfo.address"/>
			</td>
			<td class="td_body_center" nowrap>
				<s:property value="#GeThirdParterInfo.email"/>
			</td>
			<td class="td_body_center" nowrap><s:property value="#GeThirdParterInfo.url"/></td>
		</tr>
	</s:iterator>
	</s:if>
	</table>
</body>
<script type="text/javascript">
	//重新加载page.jsp页面
	window.parent.frames[3].document.location.href = "/mis/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
											+"${pageSize}&totalPage=${totalPage}&totalCount=${totalCount}" ;
	window.parent.frames[1].document.getElementById("nameCount").value = document.getElementsByName("nameCount")[0].value;
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
