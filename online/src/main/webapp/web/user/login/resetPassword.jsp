<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-1.5.2.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信泰首页</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/web/user/login/css/menu.css" />
<script type="text/javascript">
	function checkResetPassword() {
		var parameter = $("#parameter").val();
		if (parameter == "") {
			Sinosoft.alert({
				contentStr : "请输入手机号码或邮箱地址",
				width : 480,
				okStr : '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc : function() {
				}
			});
// 			alert("请输入手机号码或邮箱地址");
			return false;
		}
		
		if (!validationMailOrPhone(parameter)) {
			Sinosoft.alert({
				contentStr : "请输入正确手机号或邮箱地址",
				width : 480,
				okStr : '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc : function() {
				}
			});
// 			alert("请输入正确手机号或邮箱地址.");
			return false;
		}
		var dataFlag = true;
		$.ajax({
			type : "POST",
			async : false,
			url : "${ctx}/login/validateMailOrPhone.do",
			dataType : 'text',
			data : {resetPassword : parameter },
			success: function(data) {
				if (data == "success") {
					dataFlag = true;
				} else {
					dataFlag = false;
				}
			}
		});

		if (dataFlag) {
			return true;
		} else {
			Sinosoft.alert({
				contentStr : "该手机号码或邮箱尚未进行注册,请检查输入是否有误",
				width : 480,
				okStr : '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc : function() {
				}
			});
// 			alert("该手机号码或邮箱尚未进行注册,请检查输入是否有误.");
			return false;
		}
	}
	
	function validationMailOrPhone(parameter) {
		var flag = true;
		//js 验证手机号是否合法
		if (resetPassword.length == 11 && !isNaN(parameter)) {
			var phone = /^(1[3|5|8|4][0-9]\d{8})$/;
			if (!phone.exec(parameter)) {
				flag = false;
			}
		} else {//js 验证邮箱是否合法
			var mail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (!mail.test(parameter)) {
				flag = false;
			}
		}
		return flag;
	}
</script>
<body>
	<s:form action="/login/resetPassword.do" id="resetPassword" method="post">
		请输入您的邮箱或手机号：
			<input type="text" name="parameter" id="parameter" />
			<input type="submit" name="submit" value="确定" onclick="return checkResetPassword();" />
			<s:token />
	</s:form>
</body>
</html>