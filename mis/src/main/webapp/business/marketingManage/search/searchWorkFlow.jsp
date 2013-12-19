<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
.prompt_inquiry_en3{clear:both; height:120px; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 180px 42px !important; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 180px 42px ;padding:64px 0px 0px 0px; color:#318f5b;}
</style>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="38px"nowrap id="idNum">序号</td>
		<td class="search_head" width="250px"nowrap>活动名称</td>
		<td class="search_head" width="180px"nowrap>活动创建时间</td>
		<td class="search_head" style="min-width:150px" nowrap>
		<div style="width:160px"> 
		 状态
		 </div>
		</td>
	</tr>
	
	<c:forEach var="workFlow" items="${workFlowList}" varStatus="stu">
	<tr <s:if test="#stu.index%2==0">id="tr_${stu.index}"</s:if><s:else>id="tr_${stu.index}"</s:else>>
			        <td class="search_body_center" width="38px" nowrap>${stu.index+1+pageSize*(pageNo-1)}</td>
					<td class="search_body_center" width="250px" nowrap>
						<a href="javascript:viewMarketing('${workFlow.entity.activityId}','${workFlow.task.id}','${workFlow.workFlowID }')">
							${workFlow.entity.activityName}
						</a>
					</td>
					<td class="search_body_center" nowrap width="180px"><fmt:formatDate value="${workFlow.entity.createDate}" pattern="yyyy-MM-dd"/></td>
					<td class="search_body_center" style="min-width:180px" nowrap>
					<div style="width:160px">
						<c:if test="${workFlow.entity.status==0}">待审核</c:if>
						<c:if test="${workFlow.entity.status==1}">工作流处理中</c:if>
						<c:if test="${workFlow.entity.status==2}">回退</c:if>
						<c:if test="${workFlow.entity.status==3}">已完成</c:if>
					</div>
					</td>
			 </tr>
	</c:forEach>
			<c:if test="${totalCount == 0}">
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
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${pageNo}&pageSize=${pageSize}&totalCount=${totalCount}&totalPage=${totalPage}";
function selectSingleTask(url){
	//window.open(url,"处理" ,"top=100, left=100, width=900,toolbar=no");
	window.open(url,"处理","top=100, left=100, width=1100,height=600,toolbar=no,menubar=no,scrollbars=yes");
}
function marketDetialOpenWindow(obj) {
	window.open(obj, "活动详细", "top=100, left=100, width=900, height=600, scrollbars, resizable=yes");
	
}
/**
 * 查看套餐区域
 * @param areaCode
 * @param renewalflag
 * @returns
 */

function viewMarketing(activityId,taskId,workflowId){
	//var url = contextRootPath + '/carManage/prepareViewComboArea.do?areaCode='+areaCode+'&status='+status+'&opreateType=${opreateType}'+"&taskId="+taskId+"&workflowId="+workflowId+"&searchType=${searchType}";
	var url = contextRootPath + '/marketing/view.do?activityId='+activityId+'&workFlowType=workFlow'+"&taskId="+taskId+"&workflowId="+workflowId;
	window.open(url);
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>