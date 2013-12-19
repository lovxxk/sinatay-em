<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
<input type="hidden" id="curr_search_status" value="${curr_search_status}"/>

	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="25" nowrap><input type="checkbox" onclick="checkAll(this)" style="border: 0px" ></td>
		<td class="search_head" width="28" nowrap id="idNum">序号</td>
		<td class="search_head" width="53" nowrap>产品代码</td>
		<td class="search_head" width="140" nowrap>产品全称</td>
		<td class="search_head" width="130" nowrap>产品简称</td>
		<td class="search_head" width="51" nowrap>业务领域</td>
		<td class="search_head" width="63" nowrap>产品状态</td>
		<td class="search_head" width="51" nowrap>是否上线</td>
		<td class="search_head" width="77" nowrap>创建时间</td>
		<td class="search_head" nowrap>最近更新时间</td>
	</tr>
	
	<c:if test="${type eq 'workflow'}">
		<c:forEach items="${workFlowList}" var="obj" begin="0" end="${totalCount}" step="1" varStatus="status">
			<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
				<td class="search_body_center" width="25">
					<input type="checkbox" style="border: 0px" name="checkChild" id="check${status.index}" onclick="checkSingleRow('${status.index}','${obj.task.id}','${obj.workFlowID}');" value="${obj.entity.coreProductCode}">
				</td>
				<td class="search_body_center" width="28">${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
				<td class="search_body_center" width="53">
					${obj.entity.coreProductCode}
					<input type="hidden" id="${obj.entity.coreProductCode}_status" value="${obj.entity.productStatus}"/>
				</td>
				<td class="search_body" width="140">${obj.entity.productName}</td>
				<td class="search_body" width="130">${obj.entity.coreProductSimpleName}</td>
				<td class="search_body" width="51">
					<c:if test="${obj.entity.businessArea=='1'}">集团</c:if>
					<c:if test="${obj.entity.businessArea=='2'}">寿险</c:if>
					<c:if test="${obj.entity.businessArea=='3'}">财险</c:if>
					<c:if test="${obj.entity.businessArea=='4'}">养老险</c:if>
					<c:if test="${obj.entity.businessArea=='9'}">其他</c:if>
				</td>
				<td class="search_body" width="63">
					<c:forEach items="${code}" var="code" varStatus="stas">
						<c:if test="${code.id.codeCode eq obj.entity.productStatus}">${code.codeCName}</c:if>
					</c:forEach>
				</td>
				<td class="search_body" width="51">
					<c:if test="${productMain.isOnline=='1'}">已上线</c:if>
					<c:if test="${productMain.isOnline!='1'}">否</c:if>
				</td>
				<td class="search_body_center" width="77"><fmt:formatDate value="${obj.entity.createDate}" pattern="yyyy-MM-dd"/></td>
				<td class="search_body_center" nowrap><fmt:formatDate value="${obj.entity.updateDate}" pattern="yyyy-MM-dd"/></td>
				<input id="status_${status.index}" type="hidden" name="" value="${obj.entity.productStatus}"/>
			</tr>
		</c:forEach>
	</c:if>
	
	<c:if test="${'workflow' != type}">
			<c:forEach items="${page.result}" var="productMain" begin="0" end="${page.totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="25">
				<input type="checkbox"  style="border: 0px"  name="checkChild" id="check${status.index}" onclick="checkSingleRow('${status.index}','','');" value="${productMain.coreProductCode}">
			</td>
			<td class="search_body_center" width="28">${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" width="53">
				${productMain.coreProductCode}
			</td>
			<td class="search_body" width="140">${productMain.productName}</td>
			<td class="search_body" width="140">${productMain.coreProductSimpleName}</td>
			<td class="search_body" width="51">
				<c:if test="${productMain.businessArea=='1'}">集团</c:if>
				<c:if test="${productMain.businessArea=='2'}">寿险</c:if>
				<c:if test="${productMain.businessArea=='3'}">财险</c:if>
				<c:if test="${productMain.businessArea=='4'}">养老险</c:if>
				<c:if test="${productMain.businessArea=='9'}">其他</c:if>
			</td>
			<td class="search_body_center" width="63">
					<c:forEach items="${code}" var="code" varStatus="stas">
						<c:if test="${code.id.codeCode eq productMain.productStatus}">${code.codeCName}</c:if>
					</c:forEach>
			</td>
			<td class="search_body" width="51">
				<c:if test="${productMain.isOnline=='1'}">已上线</c:if>
				<c:if test="${productMain.isOnline!='1'}">否</c:if>
			</td>
			<td class="search_body_center" width="67"><fmt:formatDate value="${productMain.createDate}" pattern="yyyy-MM-dd"/></td>
			<td class="search_body_center" width="160" nowrap>
				<fmt:formatDate value="${productMain.updateDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
			</td>
			
			<input id="status_${status.index}" type="hidden" name="" value="${productMain.productStatus}"/>
		</tr>
	</c:forEach>
	</c:if>
	
	<c:if test="${totalCount == 0}">
		<tr>
			<td colspan="8">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>

	</table>
	
</body>
<script type="text/javascript">
//详细
function detail(id,taskid,workflowid){
	window.open("${ctx}/productManage/toDetail.do?id=" + id+"&taskID="+taskid+"&workFlowID="+workflowid+"&type=${type}","产品信息详细","top=100, left=100,width=1000,,height=550,toolbar=no,scrollbars=yes");
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
