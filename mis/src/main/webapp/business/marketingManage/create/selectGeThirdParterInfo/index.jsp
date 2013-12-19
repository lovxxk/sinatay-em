<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
	String nameCount = (String)request.getParameter("nameCount");
	String deptId =  (String)request.getParameter("deptId");
%>
<frameset id="fraTop" rows="170,30,*,40,0" border="0" frameborder="no" framespacing="0">
	<frame name="fraSearchForm" src="${ctx}/party/prepareFindThirdParterInfo.do?opertion=marketing&deptId=<%=deptId%>" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraToolbar" src="${ctx}/business/marketingManage/create/selectGeThirdParterInfo/toolbar.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraSearchList" src="${ctx}/global/ui/emptyTable.jsp" scrolling="auto" noresize></frame>
	<frame name="frapage" src="<%=request.getContextPath()%>/global/inc/page.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraHidden" src=""></frame>
</frameset>
