<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<frameset id="fraTop" rows="150,30,*,40,0" border="0" frameborder="no" framespacing="0">
	<frame name="fraSearchForm" src="${ctx}/business/marketingManage/search/frmSearch.jsp?bizType=${param.bizType}&fm=${param.fm}" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraToolbar" src="toolbar.jsp?fm=${param.fm}" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraSearchList" src="${ctx }/global/ui/emptyTable.jsp" scrolling="auto" noresize></frame>
	<frame name="frapage" src="${ctx }/global/inc/page.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraHidden" src=""></frame>
</frameset>