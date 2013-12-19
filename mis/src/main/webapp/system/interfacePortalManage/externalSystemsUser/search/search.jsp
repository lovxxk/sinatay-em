<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
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
			<td class="search_head" nowrap id="idNum">���</td>
			<td class="search_head" nowrap>�ⲿϵͳ����</td>
			<td class="search_head" nowrap>�˺�</td>
			<td class="search_head" nowrap>����</td>
			<td class="search_head" nowrap>�û�ID��ַ</td>
			<td class="search_head" nowrap>�˻�״̬</td>
			<td class="search_head" nowrap>����ʱ��</td>
			<td class="search_head" nowrap>����ʱ��</td>
			<td class="search_head" nowrap>��ע</td>
		</tr>
		<c:if test="${page.totalCount == 0}">
		<tr>
			<td colspan="8">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
		</c:if>
		<s:if test="page.result!=null">
		<s:iterator value="page.result" var="externalSystemsUser" status="st">
			<tr id="tr_${st.index}">
				<td class="search_body_center" nowrap>${st.index+1+page.pageSize*(page.currentPageNo-1)}</td>
				<td class="search_body_center" nowrap><s:property value="#externalSystemsUser.externalSysInfo.externalSysName" />
				<td class="search_body_center" nowrap>
					<a href="javascript:viewExternalSystemsUserDetail('<s:property value="#externalSystemsUser.loginName"/>');">
						<s:property value="#externalSystemsUser.loginName" />
					</a>
				</td>
				<td class="search_body_center" nowrap><s:property value="#externalSystemsUser.password" />
				<td class="search_body_center" nowrap><s:property value="#externalSystemsUser.ipAddress" /></td>
				<td class="search_body_center" nowrap>
					<s:if test="#externalSystemsUser.status == 0 ">����</s:if>
					<s:if test="#externalSystemsUser.status == 1 ">ͣ��</s:if>
				</td>
				<td class="search_body_center" nowrap><s:property value="#externalSystemsUser.createDate" /></td>
				<td class="search_body_center" nowrap><s:property value="#externalSystemsUser.updateDate" /></td>
			    <td class="search_body_center" nowrap><s:property value="#externalSystemsUser.remark" /></td>
			</tr>			
		</s:iterator>
		</s:if>
	</table>
</body>
<script type="text/javascript">
//��ϸ
function detail(id,taskid,workflowid){
	window.open("${ctx}/productManage/toDetail.do?id=" + id+"&taskID="+taskid+"&workFlowID="+workflowid+"&type=${type}","��Ʒ��Ϣ��ϸ","top=100, left=100,width=1000,,height=550,toolbar=no,scrollbars=yes");
}
function viewExternalSystemsUserDetail(loginName){
	window.open("${ctx}/interfacePortal/viewExternalSystemsUserDetail.do?externalSystemsUser.loginName=" + loginName ,"��������������ʱΪ�ⲿϵͳ������û���Ϣ��ϸ","top=100, left=100,width=1000,,height=550,toolbar=no,scrollbars=yes");
}

window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
<script type="text/javascript">
</script>
</html>