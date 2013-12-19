<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<title>机构权限</title>
<frameset id="fraTop" rows="*,50" border="0" frameborder="no" framespacing="0" style="width:500px;">
	<frame name="depAuthIdQueryTree" 
		src="${ctx}/business/marketingManage/create/selectDept/deptAuthIdQuery.jsp?opentype=${openType}&type=${type}&authorityid=${authorityid}&checkType=${checkType}&receivedObj=${receivedObj}&receivedObjName=${receivedObjName}&deptIdCount=${deptIdCount}&fromWorkFlow=${fromWorkFlow}" 
		frameborder="0" scrolling="no" noresize></frame>
	<frame name="configue" src="${ctx}/business/marketingManage/create/selectDept/configue.jsp" frameborder="0" scrolling="no" noresize></frame>
</frameset>
