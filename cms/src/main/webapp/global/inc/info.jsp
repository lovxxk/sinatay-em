<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>电子商务管理系统-系统信息</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
</head>
<body topmargin="0" leftmargin="0">
<div style="height:20px"></div>
<div id="info_main">
	<div class="info_top">
		<div class="info_title">系统信息</div>
	</div>
	<div class="info_body">
		<div class="info_body_left"></div>
		<div class="info_body_content">
			<div class="info_body_message">
				${!empty param.message ? param.message : message}
				<br style="clear:both;">
				<br style="clear:both;">
				<div style="padding-right:40px;float:right">
				<c:if test="${!empty param.buttonValue}">
					<c:if test="${param.buttonValue == 'close'}">
						<input type="button" value="关闭" onclick="javascript：window.top.close();">
					</c:if>
					<c:if test="${param.buttonValue == 'back'}">
						<input type="button" value="关闭" onclick="javascript：window.top.close();">
					</c:if>
					<c:if test="${param.buttonValue == 'homePage'}">
						<input type="button" value="返回首页" onclick="javascript：window.top.location.href = '${ctx}/index.jsp';">
					</c:if>
				</c:if>
				</div>
			</div>
		</div>
		<div class="info_body_right"></div>
	</div>
</div>
</body>
</html>