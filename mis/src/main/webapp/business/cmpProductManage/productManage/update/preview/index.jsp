<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx }/global/css/mis_basic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<title>电子商务管理系统-总览</title>
	<link href="${ctx }/business/cmpProductManage/productManage/update/preview/css/preview.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/business/cmpProductManage/productManage/update/js/product.js"></script>
</head>
<body style="padding-top: 15px;">
	<div id="proceeContent">
		<div style="text-align: center;">
			<div class="pHeader">&gt;&gt;后台新建产品状况</div>
			<div class="pContent">
				<c:set value="${geProductMain.productStatus}" var="status" />
				<table class="table_style" align="left" style="width: 750px;">
					<tr>
						<td class="td_head td_head_center" width="140px">新建产品</td>
						<td class="td_body">
							<c:if test="${empty status}">未完成</c:if>
							<c:if test="${not empty status}">已完成</c:if>
						</td>
					</tr>
					<tr>
						<td class="td_head td_head_center">流程定制</td>
						<td class="td_body">
							<c:if test="${status eq '01'}">未定制</c:if>
							<c:if test="${status ne '01'}">已定制</c:if>
						</td>
					</tr>
					<tr>
						<td class="td_head td_head_center">产品定义</td>
						<td class="td_body">
							<c:if test="${status eq '01' || status eq '02'}">未定义</c:if>
							<c:if test="${status ne '01' && status ne '02'}">已定义</c:if>
						</td>
					</tr>
					<tr>
						<td class="td_head td_head_center">审核状态</td>
						<td class="td_body">
							<c:if test="${status ne '04' && status ne '05'}">未审核</c:if>
							<c:if test="${status eq '04' || status eq '05'}">已审核</c:if>
						</td>
					</tr>
					<tr>
						<td class="td_head td_head_center">发布状态</td>
						<td class="td_body">
							<c:if test="${status ne '05'}">未发布</c:if>
							<c:if test="${status eq '05'}">已发布</c:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		
		<div style="text-align: center; clear: both;">
			<div class="pHeader">
				&gt;&gt;前台销售流程浏览(点击查看效果)
			</div>
			<div class="pContent">
				<a href="">产品列表</a>->
				<a href="">产品信息</a>->
				<c:forEach items="${flow}" var="flow" step="1" varStatus="status">
					<c:forEach items="${code}" var="code" varStatus="stas">
						<c:if test="${code.id.codeCode eq flow}">
							<c:if test="${flow eq 1}">
								<a href="${ctx}/productManage/toConfigQuestion.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>—>
							</c:if>
							<c:if test="${flow eq 2}">
								<a href="${ctx}/productManage/toConfigProductQuote.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>—>
							</c:if>
							<c:if test="${flow eq 3}">
								<a href="${ctx}/productManage/toConfigLegalNotices.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>—>
							</c:if>
							<c:if test="${flow eq 4}">
								<a href="${ctx}/productManage/toConfigInformBook.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>—>
							</c:if>
							<c:if test="${flow eq 5}">
								<a href="${ctx}/productManage/toConfigProposalNotices.do?geProductMain.coreProductCode=<s:property value="geProductMain.coreProductCode"/>">${code.codeCName}</a>—>
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
