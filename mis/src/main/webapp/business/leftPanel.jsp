<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll"%>
<c:set var="leftMenu" value="${springctx.geAuthorityService.menu}" />
<c:set var="headMenu" value="${param['headMenu']}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>电子商务管理系统业务管理</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<script language="javascript" src="${ctx}/global/js/leftPanel.js"></script>
</head>
<body topmargin="0" leftmargin="0">
<div id="leftPanel_main" style="padding-top:5px;">
	<c:forEach items="${leftMenu[headMenu]}" var="menuLv2" varStatus="status">
	<acc:showView source="${menuLv2.AUTHORITYID}">
	<div class="leftPanel" >
		<div class="leftPanel_title_down" onclick="javascript:arrow(this);">${menuLv2.AUTHORITYNAME}</div>
		<div class="leftPanel_list">
			<ul>
			<c:forEach items="${leftMenu[menuLv2.AUTHORITYID]}" var="menuLv3" varStatus="status">
				<acc:showView source="${menuLv3.AUTHORITYID}">
				<li onclick="changeLi(this,'${ctx}${menuLv3.AUTHORITYLINK}')">${menuLv3.AUTHORITYNAME}</li>
				</acc:showView>
			</c:forEach>
			</ul>
		</div>
	</div>
	</acc:showView>
	</c:forEach>
	<div class="leftPanel" >
		<div class="leftPanel_title_down" onclick="javascript:arrow(this);">投诉咨询测试</div>
		<div class="leftPanel_list">
			<ul>
				<li onclick="changeLi(this,'${ctx}/business/businessManage/inquriy/search/index.jsp?functionType=Consulting')">咨询</li>
				<li onclick="changeLi(this,'${ctx}/business/businessManage/inquriy/search/index.jsp?functionType=Complaint')">投诉</li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>















