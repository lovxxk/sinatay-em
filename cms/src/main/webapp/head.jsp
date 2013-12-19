<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>CMS管理系统-标题页</title>
<link type="text/css" rel="stylesheet" href="${ctx }/global/css/mis_basic.css">
</head>
<body topmargin="0" leftmargin="0">
<div id="main">
	<table id="header" cellpadding="0" cellspacing="0">
	<tr>
		<td width="290px"><img src="${ctx }/global/images/logo.jpg"></td>
<%-- 		<td style="background-color: #66a0ff;"><img src="${ctx }/global/images/top_bg.jpg"></td> --%>
	</tr>
	</table>
	<div id="menu">
		<div class="leftTab_gap"></div>
		<div class="leftTab" id="focus_name">功能列表</div>
		<div class="leftTab_gap"></div>
		<div class="leftTab_gap1"></div>
		<acc:showView source="ROLE_S">
			<div id="tab0" class="tabOnBlur" onclick="javascript:go2tab(this);">CMS管理</div>
		</acc:showView>
		<div class="menu_gap"></div>
		<div class="menu_right_div">
<!-- 			<div class="menu_gap"></div> -->
<!-- 			<div id="tab1" class="tabOnBlur" onclick="javascript:go2tab(this);">修改密码</div> -->
			<div class="menu_gap"></div>
			<div class="menu_logout" onclick="javascript:logout();">退出</div>
		</div>
	</div>
</div>
<form id="frmInput" action="" method="post" target=""></form>
</body>
<script type="text/javascript">
var menu = new Array();
menu[0] = ["fraBODY","${ctx}/cms/index.jsp"];
menu[1] = ["fraBODY","${ctx}/system/userManage/passport/modifyPassword/index.jsp"];
function go2tab(this_obj) {
	for(var i = 0; i < menu.length; i++) {
		var temp = document.getElementById("tab" + i);
		if(temp != null) {
			temp.className = "tabOnBlur";
		}
	}
	this_obj.className = "tabOnFocus";
	document.getElementById("focus_name").innerHTML = this_obj.innerHTML;
	var obj = document.getElementById("frmInput");
	var cur = this_obj.id.substring(3);
	obj.target = menu[cur][0];
	obj.action = menu[cur][1];
	obj.method = "post";
	obj.submit();
}

function logout() {
	window.top.location.href = "${ctx}/j_spring_security_logout";
}
</script>
</html>
