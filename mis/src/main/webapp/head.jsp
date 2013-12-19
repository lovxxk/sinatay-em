<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>电子商务管理系统</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/global/css/misBasic.css">
</head>
<body topmargin="0" leftmargin="0">
<div id="header_main">
	<div class="header">
		<div class="header_logo"></div>
		<div class="header_right">
			<div class="header_rightContent">
				<div id="header_homePage"><span><a href="javascript:goHomePage();">主页</a></span></div>
				<div id="header_userLoginInfo"><span>您好！${empty (geOperator.operatorname) ? (geOperator.operatorid):(geOperator.operatorname)}</span></div>
			</div>
		</div>
	</div>
	<div id="header_bar">
		<div class="header_bar_left">
			<div class="header_bar_left_funSep"></div>
			<div class="header_bar_left_fun" id="focus_name" style="width:262px;">功能列表</div>
			<div class="header_bar_left_funSep"></div>
			<div class="header_bar_left_separate"></div>
		</div>
		<div class="header_bar_right">
			<div class="header_bar_right_line"></div>
			<div class="header_bar_right_modifyPassword" onclick="javascript:modifyPassword(this);">修改密码</div>
			<div class="header_bar_right_line"></div>
			<div class="header_bar_right_logout" onclick="javascript:logout();">退出</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function goHomePage(){
	window.top.location='${ctx}/index.jsp';
}
function modifyPassword(obj){
	window.open("${ctx}/system/userManage/passport/modifyPassword/index.jsp","修改密码" ,"top=200, left=300, width=700,height=350,toolbar=no");		
}

function logout() {
	$.ajaxSetup({
		  async: false
		});
	var IP ="";
	//$.post("${ctx}/business/nonBusinessManager/reportManager/reportUrlIp.do",function(data){IP=data;});
	$.post("http://"+IP+"/p2pd/servlet/dispatch?b_action=xts.run&m=portal/logoff.xts&h_CAM_action=logoff");
	$.ajaxSetup({
		  async: true
		});
	window.top.location.href = "<c:url value="${ctx}/j_spring_security_logout" />";
}
</script>
</html>