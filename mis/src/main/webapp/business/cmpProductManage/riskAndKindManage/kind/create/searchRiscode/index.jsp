<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
	String nameCount = (String)request.getParameter("nameCount");
%>
<frameset id="fraTop" rows="170,30,*,40,0" border="0" frameborder="no" framespacing="0">
	<frame name="fraSearchForm" src="${ctx}/business/cmpProductManage/riskAndKindManage/businessAreaSearch.do" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraToolbar" src="toolbar.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraSearchList" src="${ctx}/global/ui/emptyTable.jsp" scrolling="auto" noresize></frame>
	<frame name="frapage" src="/mis/global/inc/page.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraHidden" src=""></frame>
</frameset>
