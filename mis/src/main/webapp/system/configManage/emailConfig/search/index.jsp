<%@ page language="java" contentType="text/html;charset=GBK"%>
<frameset id="fraTop" rows="150,30,*,60,0" border="0" frameborder="no" framespacing="0">
    <frame name="fraSearchForm" src="<%=request.getContextPath()%>/system/configManage/emailConfig/findDeptMailPrepare.do" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraToolbar" src="<%=request.getContextPath()%>/system/configManage/emailConfig/search/toolbar.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraSearchList" src="<%=request.getContextPath()%>/global/ui/emptyTable.jsp" scrolling="auto" noresize></frame>
	<frame name="frapage" src="<%=request.getContextPath()%>/global/inc/page.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraHidden" src=""></frame>
</frameset>
