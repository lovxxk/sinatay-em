<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<style>
.prompt_inquiry_en3{clear:both; height:120px; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 150px 42px !important; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 150px 42px ;padding:64px 0px 0px 0px; color:#318f5b;}
}
</style>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<input type="hidden" value="<s:property value="nameCount"/>" name="nameCount"/>
	<tr>
		<td class="search_head" width="38px" nowrap id="idNum">序号</td>
		<td class="search_head" width="200px" nowrap>商品名称</td>
		<td class="search_head" width="180px" nowrap>公司名称</td>
		<td class="search_head" width="100px" nowrap>商品总数量</td>
		<td class="search_head" width="100px" nowrap>商品库存量</td>
		<td class="search_head" width="210px" nowrap>商品代码</td>
		<td class="search_head" style="min-width:120px" nowrap><div style="width:130px">商品创建时间</div></td>
		
	</tr>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="geThirdParterService" status="status" step="1">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38px" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" width="200px" nowrap>
				<a href="javascript:partyDetialOpenWindow('${ctx}/party/view.do?geThirdParterService.itemID=<s:property value='#geThirdParterService.itemID'/>')"><s:property value="#geThirdParterService.itemName"/></a>
			</td>
			<td class="search_body_center" width="180px" nowrap>
				<s:property value="#geThirdParterService.geThirdParterInfo.thirdParterName"/>
			</td>
			<td class="search_body_center" width="100px" nowrap><s:property value="#geThirdParterService.totalNumber"/></td>
			<td class="search_body_center" width="100px" nowrap><s:property value="#geThirdParterService.surplus"/></td>
			<td class="search_body_center" width="210px" nowrap><s:property value="#geThirdParterService.itemID"/></td>
			<td class="search_body_center" style="min-width:120px" nowrap><div style="width:130px"></div><s:date name="#geThirdParterService.createDate" format="yyyy-MM-dd HH:mm:ss"/></div></td>
		</tr>
	</s:iterator>
	</s:if>
		<c:if test="${empty page || page.totalCount == 0}">
		<tr>
			<td colspan="10" >
			<div  align="center" style="width:100%;">
				 <div id="ch_div_" class="prompt_inquiry_en3" style="width: 600px">抱歉！没有查询到相关的信息。</div>
			</div>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
if(document.getElementsByName("nameCount")[0].value!=""){
	window.parent.frames[1].document.getElementById("nameCount").value = document.getElementsByName("nameCount")[0].value;
}
function partyDetialOpenWindow(obj) {
	window.open(obj, "第三方产品详细", "top=100, left=100, width=900, height=600, scrollbars, resizable=yes");
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
