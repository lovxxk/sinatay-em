<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>电子商务后台管理系统-欢迎页面</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body topmargin="0" leftmargin="0" class="homePage_body">
<!--<div class="${false?'homePage_main':'homePage_main_kuang'}">
	-->
<div class="homePage_main_kuang" id="homePage_main_kuangDIV">
	<div style="height:230px;">&nbsp;</div>
	<div class="homePage_content">
		<div class="homePage_content_block">
			<table class="homePage_content_table" id="autorityTable" style="line-height: 25px">
			</table>
		</div>
		<div class="homePage_content_block">
			<table id="table2" class="homePage_content_table">
			</table>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	// 如果没任何权限 不显示背景
	//if($("#autorityTable tr").length<1 && $("#table2 tr").length<1)
	
	if(trim($("#autorityTable").html())==""){
		$("#homePage_main_kuangDIV").removeClass("homePage_main_kuang").addClass("homePage_main");
	}
	
});

function trim(str){  
	return str.replace(/(^\s*)|(\s*$)/g, ""); 
}
</script>
</html>