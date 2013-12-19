<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<frameset id="fraTop" rows="170,25,*,60,0" border="0" frameborder="no" framespacing="0">
	<frame name="fraSearchForm" src="${ctx}/system/interfacePortalManage/externalSysUserInterfaceInfo/create/externalSystemsUserList/frmSearch.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraToolbar" src="${ctx}/system/interfacePortalManage/externalSysUserInterfaceInfo/create/externalSystemsUserList/toolbar.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraSearchList" src="${ctx}/global/ui/emptyTable.jsp" scrolling="auto" noresize></frame>
	<frame name="frapage" src="${ctx}/global/inc/page.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraHidden" src=""></frame>
</frameset>