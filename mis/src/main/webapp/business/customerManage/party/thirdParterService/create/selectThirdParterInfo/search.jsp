<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<style type="text/css">
.prompt_inquiry_en3{clear:both; height:120px; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 300px 42px !important; background:url(${ctx}/global/images/prompt_inquiry_pic2.jpg) no-repeat 310px 42px ;padding:64px 0px 0px 0px; color:#318f5b;}

</style>

<body>
	<table width="100%" class="table_Show" id="data_table">
	<tr>
<!--		<td class="search_head" width="30" nowrap><input type="checkbox" onclick="checkAll(this)"></td>-->
		<td class="search_head" width="38px" nowrap>���</td>
		<td class="search_head" width="200px" nowrap>��˾����</td>
		<td class="search_head" width="150px" nowrap>��˾����</td>
		<td class="search_head" width="120px" nowrap>��������</td>
		<td class="search_head" width="150px" nowrap>��������</td>
		<td class="search_head" width="150px" nowrap>��˾��ַ</td>
		<td class="search_head" style="min-width:100px" nowrap><div style="width:110px">������Ӧ��</div></td>
		<input type="hidden" value="<s:property value="nameCount"/>" name="nameCount"/>
	</tr>
	
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="GeThirdParterInfo" status="status" step="1">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
<!--			<td class="search_body_center" nowrap>-->
<!--				<input type="checkbox" name="checkChild" id="check${status.index}" onclick="checkSingleRow();" value="<s:property value="#GeThirdParterInfo.thirdParterID"/>@<s:property value="#GeThirdParterInfo.thirdParterName"/>">-->
<!--			</td>-->
			<td class="search_body_center" width="38px" nowrap >${status.index+1+pageSize*(pageNo-1)}</td>
			<td class="search_body_center" width="200px" nowrap id="td_0_r_w${status.index}"><div><s:property value="#GeThirdParterInfo.thirdParterName"/><div></td>
			<td class="search_body_center" width="150px" nowrap>
				<s:property value="geCodeThirdCompanyTypeMap[#GeThirdParterInfo.companyType]"/>
			</td>
			<td class="search_body_center" width="120px" nowrap>
				<s:date name="#GeThirdParterInfo.createDate" format="yyyy-MM-dd"/>
			</td>
			<td class="search_body_center" width="150px" nowrap><s:property value="#GeThirdParterInfo.email"/></td>
			<td class="search_body_center" width="150px" nowrap><div><s:property value="#GeThirdParterInfo.url"/></div></td>
			<td class="search_body_center" width="100px" nowrap><div style="width:110px"><input type="button" value="������Ӧ��" onclick="getGeCodeType('<s:property value="#GeThirdParterInfo.thirdParterID"/>','<s:property value="#GeThirdParterInfo.thirdParterName"/>');"/></div></td>
		</tr>
	</s:iterator>
	</s:if>
			<c:if test="${totalCount == 0}">
		<tr>
			<td colspan="10" >
			<div  align="center" style="width:100%;">
				 <div id="ch_div_" class="prompt_inquiry_en3" >��Ǹ��û�в�ѯ����ص���Ϣ��</div>
			</div>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
	//���¼���page.jspҳ��
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
											+"${pageSize}&totalPage=${totalPage}&totalCount=${totalCount}" ;
	window.parent.frames[1].document.getElementById("nameCount").value = document.getElementsByName("nameCount")[0].value;

	//function goSetthirdParterName(obj){
	//	if(confirm("ȷ�������ù�Ӧ��?")){
	//   top.opener.document.getElementsByName("thirdParterName")[0].value=$(obj).text();
	//    top.close();
	//	}
	//}
	function getGeCodeType(itemID, itemName){
        if(confirm("ȷ�������ù�Ӧ�̣�")){
			top.opener.document.getElementsByName("thirdParterName")[0].value = itemName;
			top.opener.document.getElementsByName("geThirdParterService.geThirdParterInfo.thirdParterID")[0].value = itemID;
			top.close();	
	}
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
