<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<frameset id="fraTop" rows="135,30,*,60,0" border="0" frameborder="no" framespacing="0">
	<frame name="fraSearchForm" src="frmSearch.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame name="fraToolbar" src="toolbar.jsp" frameborder="0" scrolling="no" noresize></frame>
	<frame id="fraSearchList" name="fraSearchList" src="${ctx }/global/ui/emptyTable.jsp" scrolling="auto" noresize></frame>
	<frame name="fraHidden" src="${ctx}/global/inc/page.jsp"></frame>
</frameset>
 