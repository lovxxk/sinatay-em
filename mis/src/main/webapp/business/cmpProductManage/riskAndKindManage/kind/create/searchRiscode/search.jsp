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
		<td class="search_head" nowrap>业务领域</td>
		<td class="search_head" nowrap>险种名称</td>
		<td class="search_head" nowrap>关联险种信息</td>
	</tr>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="risk" status="status" step="1">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<!--<td class="search_body" nowrap>
				<input type="checkbox" name="checkChild" id="check${status.index}" onclick="checkSingleRow();" value="<s:property value="#risk.riskCode"/>"/>
			</td>
			--><td class="search_body" nowrap>${status.index+1+pg.pageSize*(pageNo-1)}</td>
			<td class="search_body" nowrap><s:property value="#risk.riskCode"/></td>
			<td class="search_body" nowrap>
			<s:property value="mapBusiness[#risk.businessArea]"/>
			</td>
			<td class="search_body" nowrap>
				<s:property  value="#risk.riskCName"/>
			</td>
		    <td class="search_body"><input type="button" value="关联到险种代码" id="info" onclick="getGeCodeType('<s:property value="#risk.riskCode"/>', '<s:property value="#risk.riskCName"/>');">
		</tr>
	</s:iterator>
	</s:if>
	</table>
</body>
<script type="text/javascript">
	//重新加载page.jsp页面
	window.parent.frames[3].document.location.href = "/mis/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
											+"${pageSize}&totalPage=${totalPage}&totalCount=${totalCount}" ;
											
function getGeCodeType(riskCode,riskCName){
	if(confirm("确定关联该险种代码?")){
		//将具体的值付给险种代码
		top.opener.document.getElementById("riskCodeV").value = riskCode;
		top.close();		
	}
}											
											
											
	//window.parent.frames[1].document.getElementById("nameCount").value = document.getElementsByName("nameCount")[0].value;
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
