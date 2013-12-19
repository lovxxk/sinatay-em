<%@ page language="java" contentType="text/html;charset=GBK"%>
<frameset id="fraTop" rows="170,30,*,60,0" border="0" frameborder="no" framespacing="0">
	<frame name="fraSearchForm" src="<%=request.getContextPath()%>/business/businessManage/dataDictionary/frmSearchType.do" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraToolbar" src="toolbar.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraSearchList" src="<%=request.getContextPath()%>/global/ui/emptyTable.jsp" scrolling="auto" noresize></frame>
	<frame name="frapage" src="<%=request.getContextPath()%>/global/inc/page.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraHidden" src=""></frame>
</frameset>
