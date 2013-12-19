<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<frameset id="fraTop" rows="170,30,*,60,0" border="0" frameborder="no" framespacing="0">
	<c:choose>
		<c:when test="${not empty param.coreProductCode}">
			<frame name="fraSearchForm" src="${ctx }/business/cmpProductManage/productManage/search/frmSearch.jsp?coreProductCode=${param.coreProductCode}&fm=${fm}" frameborder="0" scrolling="no" noresize></frame>
		</c:when>
		<c:otherwise>
			<frame name="fraSearchForm" src="${ctx }/business/cmpProductManage/productManage/search/frmSearch.jsp?fm=${fm}" frameborder="0" scrolling="no" noresize></frame>
		</c:otherwise>
	</c:choose>
	<frame name="fraToolbar" src="${ctx }/business/cmpProductManage/productManage/search/toolbar.jsp?fm=${fm}" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraSearchList" src="${ctx}/global/ui/emptyTable.jsp" scrolling="auto" noresize></frame>
	<frame name="frapage" src="${ctx}/global/inc/page.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraHidden" src=""></frame>
</frameset>