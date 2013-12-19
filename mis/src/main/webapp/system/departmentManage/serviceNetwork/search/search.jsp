<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.prompt_inquiry_en3{clear:both; height:120px; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 162px 42px;; padding:64px 0px 0px 210px; color:#318f5b;}
	</style>
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<!--<td class="search_head" width="5%" nowrap><input type="checkbox" onclick="checkAll(this)"></td>
		--><td class="search_head" width="30px;" nowrap id="idNum">序号</td>
		<td class="search_head" width="80px;" nowrap>服务网点名称</td>
		<td class="search_head" width="80px;" nowrap>服务网点代码</td>
		<td class="search_head" width="60px;" nowrap>所属单位</td>
		<td class="search_head" width="40px;" nowrap>邮编</td>
		<td class="search_head" width="60px;" nowrap>服务热线</td>
		<td class="search_head" width="30px;" nowrap>类型</td>
	</tr>
	<c:forEach items="${geStationInfos}" var="geStationInfo" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<!--<td class="search_body_center" nowrap>
				<input type="checkbox" width="5%"  name="checkChild" id="check${status.index}" onclick="checkSingleRow();" value="${geStationInfo.obid}">
			</td>
			--><td class="search_body_center" width="30px;" >${status.index+1+pageSize*(pageNo-1)}</td>
			<td class="search_body_center" width="80px;"><a href="javascript:void(0);" style="color:#308AC7;text-decoration: none;" onclick="doDetail('${geStationInfo.obid}');">${geStationInfo.unitName}</a></td>
			<td class="search_body_center" width="80px;">${geStationInfo.obid}</td>
			<td class="search_body_center" width="60px;">${geStationInfo.corpName}</td>
			<td class="search_body_center" width="40px;">${geStationInfo.zipCode}</td>
			<td class="search_body_center" width="60px;">${geStationInfo.telePhone}</td>
			<td class="search_body_center" width="30px;">${geStationInfo.type == 'SX' ? '寿险' :(geStationInfo.type == 'CX' ? '财险' :(geStationInfo.type == 'JG' ? '企业年金' :''))}</td>
		</tr>
	</c:forEach>
	<c:if test="${totalCount == 0}">
		<tr>
			<td colspan="7">
				<div class="prompt_inquiry_en3">抱歉！没有查询到相关的信息。</div>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
	//重新加载page.jsp页面
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
											+"${pageSize}&totalPage=${totalPage}&totalCount=${totalCount}" ;
	
	function doDetail(obid){
		top.document.getElementById("operate").src = "${ctx}/business/businessManage/serviceNetwork/findServiceNetworkForDetail.do?geStationInfo.obid=" + obid;

	}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>