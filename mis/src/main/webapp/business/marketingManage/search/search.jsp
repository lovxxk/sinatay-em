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
<!--[if IE 8]>
 <style type="text/css">
.prompt_inquiry_en3{clear:both; height:120px; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 180px 42px !important; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 180px 42px ;padding:64px 0px 0px 0px; color:#318f5b;}
</style>
<![endif]-->
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="38px" nowrap id="idNum">序号</td>
		<td class="search_head" width="250px" nowrap>活动名称</td>
		<td class="search_head" width="100px" nowrap>起始时间</td>
		<td class="search_head" width="100px" nowrap>结束时间</td>
		<td class="search_head" width="80px" nowrap>是否有效</td>
		<td class="search_head" width="100px" nowrap>创建时间</td>
		<td class="search_head" style="width-min:150px;display: none;" nowrap>
		 <div style="width:160px">
		         状态
		 </div>
		</td>
	</tr>
	<s:if test="page.result!=null">
	 <% 
     long pageNo = (Long)request.getAttribute("pageNo");
	 int pageSize = (Integer)request.getAttribute("pageSize");
	 long varstatus = pageSize*(pageNo-1)+1;
    %>
	<s:iterator value="page.result" var="geAddServiceActivity" status="ind" step="1">
		<tr id="tr_${ind.index}" class="${ind.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" nowrap width="38px"><%=varstatus++%></td>
			<td class="search_body_center" nowrap width="250px"><a href="javascript:marketDetialOpenWindow('${ctx}/marketing/view.do?activityId=<s:property value='#geAddServiceActivity.activityId'/>&bizType=${param.bizType}&searchType=${searchType}&taskId=<s:property value="#geAddServiceActivity.runintTaskId"/>&workflowId=<s:property value="#geAddServiceActivity.workFlowId"/>')"><s:property value="#geAddServiceActivity.activityName"/>
			</a></td>
			<td class="search_body_center" nowrap width="100px"><s:date name="#geAddServiceActivity.activityStartDate" format="yyyy-MM-dd"/></td>
			<td class="search_body_center" nowrap width="100px"><s:date name="#geAddServiceActivity.activityEndDate" format="yyyy-MM-dd"/></td>
			<td class="search_body_center" nowrap width="80px">
				<s:if test="#geAddServiceActivity.validInd==1"> 
					<c:out value="有效"></c:out>
				</s:if> 
				<s:elseif test="#geAddServiceActivity.validInd==0"> 
					<c:out value="无效"></c:out>
				</s:elseif>
			</td>
			<td class="search_body_center" nowrap width="150px"><s:date name="#geAddServiceActivity.createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
			
			<td class="search_body_center" style="width-min:150px; display: none;" nowrap>
			<div style="width:160px">
				<s:if test="#geAddServiceActivity.status==0">待审核</s:if>
				<s:if test="#geAddServiceActivity.status==1">工作流处理中</s:if>
				<s:if test="#geAddServiceActivity.status==2">回退</s:if>
				<s:if test="#geAddServiceActivity.status==3">已发布</s:if>
				<s:if test="#geAddServiceActivity.status==4">已处理</s:if>
				<s:if test="#geAddServiceActivity.status==5">撤销</s:if>
				<s:if test="#geAddServiceActivity.status==6">已放弃</s:if>			
			</div>
			</td>
		</tr>
	</s:iterator>
	<s:iterator value="#request.rollList" var="geAddServiceActivity" status="ind" step="1">
		<%if(varstatus<=pageSize){ %>
			<tr id="tr_${ind.index}" class="${ind.index%2 == 0?'':'search_tr_ou'}">
				<td class="search_body_center" nowrap width="38px"><%=varstatus++%></td>
				<td class="search_body_center" nowrap width="250px"><a href="javascript:marketDetialOpenWindow('${ctx}/marketing/view.do?activityId=<s:property value='#geAddServiceActivity.activityId'/>&bizType=${param.bizType}&searchType=${searchType}&taskId=<s:property value="#geAddServiceActivity.runintTaskId"/>&workflowId=<s:property value="#geAddServiceActivity.workFlowId"/>')"><s:property value="#geAddServiceActivity.activityName"/>
				</a></td>
				<td class="search_body_center" nowrap width="180px"><s:date name="#geAddServiceActivity.createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="search_body_center" nowrap style="width:150px" style="display: none;">
				 <div style="width:160px">
					<s:if test="#geAddServiceActivity.status==0">待审核</s:if>
					<s:if test="#geAddServiceActivity.status==1">工作流处理中</s:if>
					<s:if test="#geAddServiceActivity.status==2">回退</s:if>
					<s:if test="#geAddServiceActivity.status==3">已发布</s:if>
					<s:if test="#geAddServiceActivity.status==4">已处理</s:if>
					<s:if test="#geAddServiceActivity.status==5">撤销</s:if>
					<s:if test="#geAddServiceActivity.status==6">已放弃</s:if>			
				</div>
				</td>
			</tr>
		<%} %>
	</s:iterator>
	</s:if>
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
var vv_height = screen.availHeight-35;
var vv_width = screen.availWidth-25;
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
function selectSingleTask(url){
	//window.open(url,"处理" ,"top=100, left=100, width=900,toolbar=no");
	window.open(url,"处理","top=100, left=100, width=1100,height=600,toolbar=no,menubar=no,scrollbars=yes");
}
function marketDetialOpenWindow(obj) {
	//window.open(obj, "活动详细aaaaad", "top=100, left=100, width=900, height=600, scrollbars, resizable=yes");
	window.open(obj, "活动详细" ," width=800,height=600,toolbar=no,menubar=no,scrollbars=yes");
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>