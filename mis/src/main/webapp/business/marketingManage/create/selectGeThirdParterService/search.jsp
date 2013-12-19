<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
</head>
<style type="text/css">
.prompt_inquiry_en3{clear:both; height:120px; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 300px 42px !important; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 310px 42px ;padding:64px 0px 0px 0px; color:#318f5b;}
</style>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<%/*<input type="radio" onclick="checkAll(this)">*/%>
		<td class="search_head" width="38px" nowrap id="idNum">序号</td>
		<td class="search_head" width="250px" nowrap>商品名称</td>
		<td class="search_head" width="150px" nowrap>公司名称</td>
		<td class="search_head" width="100px"  nowrap>商品总数量</td>
		<td class="search_head" width="100px"  nowrap>商品库存量</td>
		<td class="search_head" width="120px"  nowrap>商品创建时间</td>
		<td class="search_head" style="min-width:90px" nowrap>
		<div style="width:100px">
		关联商品信息
		</div>
		</td>
		
	</tr>
	<c:forEach var="geThirdParterService" items="${geThirdParterServiceList}" varStatus="stu">
	<tr <s:if test="#stu.index%2==0"> id="tr_${stu.index}"</s:if><s:else>id="tr_${stu.index}"</s:else>>
					<td class="search_body_center" width="38px" nowrap>${stu.index+1+pageSize*(pageNo-1)}</td>
					<td class="search_body_center" width="250px" nowrap>${geThirdParterService.itemName}</td>
					<td id="thirdParterName_${stu.index}" class="search_body_center" width="150px" nowrap>${geThirdParterService.geThirdParterInfo.thirdParterName}</td>
					<td class="search_body_center" width="100px" nowrap>${geThirdParterService.totalNumber}</td>
					<td class="search_body_center" width="100px" nowrap>${geThirdParterService.surplus}</td>
					<td class="search_body_center" width="120px" nowrap><fmt:formatDate value="${geThirdParterService.createDate}" pattern="yyyy-MM-dd"/></td>
					<td class="search_body_center" style="min-width:90px;">
					 <div style="width:100px">
					  <input type="button" value="关联商品信息" id="info" onclick="getGeCodeType('${geThirdParterService.itemID}', '${geThirdParterService.itemName}', '${geThirdParterService.surplus}');">
					 </div>
					</td>
			 </tr>
	</c:forEach>
		<c:if test="${totalCount == 0}">
		<tr>
			<td colspan="10" >
			<div  align="center" style="width:100%;">
				 <div id="ch_div_" class="prompt_inquiry_en3">抱歉！没有查询到相关的信息。</div>
			</div>
			</td>
		</tr>
	</c:if>
	</table>
	<input type="hidden" id="nameCount" name="nameCount" value="<s:property value="nameCount"/>"/>
</body>
<script type="text/javascript">
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${pageNo}&pageSize=${pageSize}&totalCount=${totalCount}&totalPage=${totalPage}";
if(document.getElementsByName("nameCount")[0].value!=""){
	window.parent.frames[1].document.getElementById("nameCount").value = document.getElementsByName("nameCount")[0].value;
}




function getGeCodeType(itemID, itemName, surplus){
	/*var locurl=top.location.href;
//	alert(locurl);
	var start=locurl.indexOf("?");
	var end=locurl.length;
	var tempstr=locurl.substring(start+1,end)
	var temp=tempstr.split("=");
	var itemNum=temp[3];
	//alert("pid is:"+itemID);
	if(itemNum!=null&&itemNum!=""){
		var countNum=itemNum.substring(8);
	}
	//alert(countNum+"xxx");*/
if(confirm("确定关联该商品？")){
		var nameCount = document.getElementById("nameCount").value;
		if(parseInt(surplus)==0){
			alert("商品库存不足");
		}else{
			/*if(countNum!=null&&countNum!=""){
				alert(top.opener.document.getElementsByName("itemName")[countNum].value);
				top.opener.document.getElementsByName("itemName")[countNum].value = itemName;
				top.opener.document.getElementsByName("itemID")[countNum].value = itemID;
			}else{*/
				top.opener.document.getElementsByName("temp")[nameCount].value = itemName;//2012年6月13日9:44:55 之前的写法
				top.opener.document.getElementsByName("itemID")[nameCount].value = itemID;
			//}
			top.close();
		}
	}
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
