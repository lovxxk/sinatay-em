<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="<%=request.getContextPath()%>/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100%" class="table_Show" id="data_table">
	<tr><!--
		<td class="search_head" width="30" nowrap><input type="checkbox" onclick="checkAll(this)"></td>
		--><td class="search_head" nowrap>���</td>
		<td class="search_head" nowrap>�ձ����</td>
		<td class="search_head" nowrap>���ִ���</td>
		<td class="search_head" nowrap>�ձ���������</td>
		<td class="search_head" nowrap>�ձ����</td>
		<td class="search_head" nowrap>�����ձ���Ϣ</td>
	</tr>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="kind" status="status" step="1">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
		    <td class="search_body" nowrap>${status.index+1+pg.pageSize*(pageNo-1)}</td>
			<td class="search_body" nowrap><s:property value="#kind.id.kindCode"/></td>
			<td class="search_body" nowrap><s:property value="#kind.id.riskCode"/></td>
			<td class="search_body" nowrap><s:property value="#kind.kindCName"/></td>
			<td class="search_body" nowrap>
			 <s:if test="#kind.validInd==1">
			          ����
			 </s:if>
			 <s:if test="#kind.validInd==0">
			          ������
			 </s:if>
		    <td class="search_body"><input type="button" value="�������ձ����"  onclick="getKindGeCodeType('<s:property value="#kind.id.kindCode"/>', '<s:property value="#kind.validInd"/>');">
		</tr>
	</s:iterator>
	</s:if>
	</table>
</body>
<script type="text/javascript">
	//���¼���page.jspҳ��
	window.parent.frames[3].document.location.href = "/mis/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
											+"${pageSize}&totalPage=${totalPage}&totalCount=${totalCount}" ;
											
function getKindGeCodeType(kindCode,validInd){
	if(confirm("ȷ���������ձ����?")){
		//�������ֵ�������ִ���
		if(validInd=='1'){
			top.opener.document.getElementById("kindCodeMain").value = kindCode;
		}else{
			top.opener.document.getElementById("kindCodeAd").value = kindCode;
		}
		top.close();		
	}
}											
											
											
	//window.parent.frames[1].document.getElementById("nameCount").value = document.getElementsByName("nameCount")[0].value;
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
