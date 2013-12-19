<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx }/global/css/mis_basic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<title>�����������ϵͳ-����</title>
	<link href="${ctx }/business/cmpProductManage/productManage/update/preview/css/preview.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/business/cmpProductManage/productManage/update/js/product.js"></script>
</head>
<body style="padding-top: 15px;">
	<div id="proceeContent">
		<div style="text-align: center;">
			<div class="pHeader">&gt;&gt;��̨�½���Ʒ״��</div>
			<div class="pContent">
				<c:set value="${geProductMain.productStatus}" var="status" />
				<table class="table_style" align="left" style="width: 750px;">
					<tr>
						<td class="td_head td_head_center" width="140px">�½���Ʒ</td>
						<td class="td_body">
							<c:if test="${empty status}">δ���</c:if>
							<c:if test="${not empty status}">�����</c:if>
						</td>
					</tr>
					<tr>
						<td class="td_head td_head_center">���̶���</td>
						<td class="td_body">
							<c:if test="${status eq '01'}">δ����</c:if>
							<c:if test="${status ne '01'}">�Ѷ���</c:if>
						</td>
					</tr>
					<tr>
						<td class="td_head td_head_center">��Ʒ����</td>
						<td class="td_body">
							<c:if test="${status eq '01' || status eq '02'}">δ����</c:if>
							<c:if test="${status ne '01' && status ne '02'}">�Ѷ���</c:if>
						</td>
					</tr>
					<tr>
						<td class="td_head td_head_center">���״̬</td>
						<td class="td_body">
							<c:if test="${status ne '04' && status ne '05'}">δ���</c:if>
							<c:if test="${status eq '04' || status eq '05'}">�����</c:if>
						</td>
					</tr>
					<tr>
						<td class="td_head td_head_center">����״̬</td>
						<td class="td_body">
							<c:if test="${status ne '05'}">δ����</c:if>
							<c:if test="${status eq '05'}">�ѷ���</c:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		
		<div style="text-align: center; clear: both;">
			<div class="pHeader">
				&gt;&gt;ǰ̨�����������(����鿴Ч��)
			</div>
			<div class="pContent">
				<a href="">��Ʒ�б�</a>->
				<a href="">��Ʒ��Ϣ</a>->
				<c:forEach items="${flow}" var="flow" step="1" varStatus="status">
					<c:forEach items="${code}" var="code" varStatus="stas">
						<c:if test="${code.id.codeCode eq flow}">
							<c:if test="${flow eq 1}">
								<a href="${ctx}/productManage/toConfigQuestion.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>��>
							</c:if>
							<c:if test="${flow eq 2}">
								<a href="${ctx}/productManage/toConfigProductQuote.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>��>
							</c:if>
							<c:if test="${flow eq 3}">
								<a href="${ctx}/productManage/toConfigLegalNotices.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>��>
							</c:if>
							<c:if test="${flow eq 4}">
								<a href="${ctx}/productManage/toConfigInformBook.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>��>
							</c:if>
							<c:if test="${flow eq 5}">
								<a href="${ctx}/productManage/toConfigProposalNotices.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>��>
							</c:if>
							<c:if test="${flow eq 6}">
								<a href="${ctx}/productManage/toConfigPolicy.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>" >${code.codeCName}</a>
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
		<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value="geProductMain.coreProductCode"/>">
	</div>
</body>

</html>
