<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<frameset id="fraTop" rows="180,25,*,40,0" border="0" frameborder="no" framespacing="0">
	<frame name="fraSearchForm" src="${ctx }/marketing/prepareSelectAddShopping.do?operation=addShoppingroduct&deptId=${param.deptId}&xRule=${param.xRule}&yAddShopping=${param.yAddShopping}" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraToolbar" src="${ctx }/business/marketingManage/create/addShoppingProduct/toolbar.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraSearchList" src="${ctx}/global/ui/emptyTable.jsp" scrolling="auto" noresize></frame>
	<frame name="frapage" src="${ctx}/global/inc/page.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraHidden" src=""></frame>
</frameset>