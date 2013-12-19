<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
	   <td class="search_head" width="25" nowrap><input type="checkbox" onclick="checkAll(this)" style="border: 0px" ></td>
		<td class="search_head" width="38px" nowrap id="idNum">序号</td>
		<td class="search_head" width="180px" nowrap>投保单号</td>
		<td class="search_head" width="150px" nowrap>用户名id</td>
		<td class="search_head" width="250px" nowrap>商品名称</td>
		<td class="search_head" width="100px" nowrap>数量</td>
		<td class="search_head" width="150px" nowrap>投保地区</td>
		<td class="search_head"  style="min-width:100px;"nowrap>
		 <div style="width:110px">
		        是否赠送
		 </div>  
		</td>
	</tr>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="geThirdParterSerialNumber" status="status" step="1">	
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
		<td class="search_body_center" width="25">
					<input type="checkbox" style="border: 0px" name="checkChild" id="check${status.index}" onclick="checkSingleRow('${status.index}','<s:property value="#geThirdParterSerialNumber.searialNo"/>','<s:property value="#geThirdParterSerialNumber.validInd"/>');" value="<s:property value="#geThirdParterSerialNumber.searialNo"/>@<s:property value="#geThirdParterSerialNumber.validInd"/>@${status.index+1+page.pageSize*(page.currentPageNo-1)}" >
				</td>
			<td class="search_body_center" width="38px" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" width="180px" nowrap><a href="javascript:partyDetialOpenWindow('${ctx}/party/viewNum.do?geThirdParterSerialNumber.searialNo=<s:property value='#geThirdParterSerialNumber.searialNo'/>')"><s:property value="#geThirdParterSerialNumber.proposalNo"/></a></td>
			<td class="search_body_center" width="150px" nowrap><s:property value="#geThirdParterSerialNumber.userID"/></td>
			<td class="search_body_center" width="250px" nowrap><s:property value="#geThirdParterSerialNumber.geThirdParterService.itemName"/></td>
			<td class="search_body_center" width="100px" nowrap><s:property value="#geThirdParterSerialNumber.count"/></td>
			<td class="search_body_center" width="150px" nowrap><s:property value="#geThirdParterSerialNumber.proposalAreaName"/></td>
			<td class="search_body_center" style="min-width:100px;" nowrap>
			<div style="width:110px">
				<s:if test="#geThirdParterSerialNumber.validInd==1">
					已赠送
				</s:if>
				<s:if test="#geThirdParterSerialNumber.validInd==0">
					未赠送
				</s:if>
			</div>
			</td>
		</tr>
	</s:iterator>
	</s:if>
	
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
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
function partyDetialOpenWindow(obj) {
	window.open(obj, "商品赠送详细", "top=100, left=100, width=900, height=600, scrollbars, resizable=yes");
}

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
	
	// window.parent.frames[1].document.getElementById("status").value = status.value;
	
	window.parent.frames[1].document.getElementById("idStr").value = idStr;
	window.parent.frames[1].document.getElementById("count").value = count;
	
	//window.parent.frames[1].document.getElementById("taskID").value = taskID;
	// window.parent.frames[1].document.getElementById("workFlowID").value = workFlowID;
}
</script>
</html>
