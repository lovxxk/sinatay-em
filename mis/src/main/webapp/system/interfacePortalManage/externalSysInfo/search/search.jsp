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
			<td class="search_head" nowrap>�ⲿϵͳ����</td>
			<td class="search_head" nowrap>�ⲿϵͳ����</td>
			<td class="search_head" nowrap>��ע</td>
		</tr>
		<c:if test="${totalCount == 0}">
		<tr>
			<td colspan="8">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
		</c:if>
		<s:if test="page.result!=null">
		<s:iterator value="page.result" var="externalSysInfo" status="status">	
			<tr id="tr_${status.index}">
				<td class="search_body_center" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
				<td class="search_body_center" nowrap>
					<a href="javascript:viewExternalSysInfoDetail('<s:property value="#externalSysInfo.externalSysId"/>');">
						<s:property value="#externalSysInfo.externalSysId" />
					</a>
				</td>
				<td class="search_body_center" nowrap><s:property value="#externalSysInfo.externalSysName" /></td>
				<td class="search_body_center" nowrap>
					<s:if test="#externalSysInfo.externalSysType == 1 ">����ϵͳ</s:if>
					<s:if test="#externalSysInfo.externalSysType == 2 ">�ⲿ��֯ϵͳ</s:if>
				</td>			
			    <td class="search_body_center" nowrap><s:property value="#externalSysInfo.remark" /></td>
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
function viewExternalSysInfoDetail(externalSysId){
	window.open("${ctx}/interfacePortal/viewExternalSysInfoDetail.do?externalSysInfo.externalSysId=" + externalSysId ,"�ⲿϵͳ��ϸ","top=100, left=100,width=1000,,height=550,toolbar=no,scrollbars=yes");
}

window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
<script type="text/javascript">
function checkSingleRow(no,taskID,workFlowID){
	var idStr = "";
	var count = 0;
	var checkArray = document.getElementsByName("checkChild");
	var tr_selected;
	var value_checked;
	var status = "";
	for (var i = 0; i < checkArray.length; i++){
		tr_selected = document.getElementById("tr_" + i);
		if(checkArray[i].checked){
			tr_selected.className = "search_tr_selected";
			value_checked = checkArray[i].value;
			if(idStr == ""){
				idStr = value_checked;
			}else {
				idStr = idStr + "," + value_checked;
			}
			count++;
		}else{
			if(i%2 == 0){
				tr_selected.className = "";
			}else{
				tr_selected.className = "search_tr_ou";
			}
		}
	}
	status = document.getElementById("status_"+no);
	
	//top.frames[1].frames[2].frames[1].document.getElementById("status").value = status.value;
	
	window.parent.frames[1].document.getElementById("status").value = status.value;
	
	window.parent.frames[1].document.getElementById("idStr").value = idStr;
	window.parent.frames[1].document.getElementById("count").value = count;
	
	window.parent.frames[1].document.getElementById("taskID").value = taskID;
	window.parent.frames[1].document.getElementById("workFlowID").value = workFlowID;
}
</script>
</html>
