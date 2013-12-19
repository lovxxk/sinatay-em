<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
.prompt_inquiry_en3{clear:both; height:120px; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 150px 42px !important; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 150px 42px ;padding:64px 0px 0px 0px; color:#318f5b;}
.prompt_inquiry_en1{
width:180px;
height:30px;
word-break:keep-all;
white-space:nowrap;
overflow:hidden;
text-overflow:ellipsis;
}
</style>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" nowrap width="38px" id="idNum">���</td>
		<td class="search_head" nowrap width="200px">��˾����</td>
		<td class="search_head" nowrap width="150px">��˾����</td>
		<td class="search_head" nowrap width="120px">��������</td>
		<td class="search_head" width="150px" nowrap>��������</td>
		<td class="search_head" style="min-width:150px" nowrap>
		 <div style="width:180px">
		            ��˾��ַ
		  </div> 
		</td>
	</tr>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="geThirdParterInfo" status="status" step="1">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38px" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" width="200px" nowrap><div class="prompt_inquiry_en1"><a href="javascript:partyDetialOpenWindow('${ctx}/party/viewCom.do?geThirdParterInfo.thirdParterID=<s:property value='#geThirdParterInfo.thirdParterID'/>')"><s:property value="#geThirdParterInfo.thirdParterName"/></a></div></td>
			<td class="search_body_center" width="150px" nowrap><s:property value="geCodeThirdCompanyTypeMap[#geThirdParterInfo.companyType]"/></td>
			<td class="search_body_center" width="120px" nowrap><s:date name="#geThirdParterInfo.createDate" format="yyyy-MM-dd"/></td>
			<td class="search_body_center" width="150px" nowrap><s:property value="#geThirdParterInfo.email"/></td>
			<td class="search_body_center" style="min-width:150px" nowrap><div style="width:180px;"><s:property value="#geThirdParterInfo.url"/></div></td>
		</tr>
	</s:iterator>
	</s:if>
	
	<c:if test="${empty page || page.totalCount == 0}">
		<tr>
			<td colspan="10" >
			<div  align="center" style="width:100%;">
				 <div id="ch_div_" class="prompt_inquiry_en3" style="width: 600px">��Ǹ��û�в�ѯ����ص���Ϣ��</div>
			</div>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
function partyDetialOpenWindow(obj) {
	window.open(obj, "��Ӧ����ϸ", "top=100, left=100, width=900, height=600, scrollbars, resizable=yes");
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
